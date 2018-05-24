package com.xiakee.view.task;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.xiakee.crawler.bean.CrawlerGoodsPrice;
import com.xiakee.dao.sku.SkuGoodsDao;
import com.xiakee.dao.sku.SkuGoodsNoDao;
import com.xiakee.dao.sku.SkuManagerDao;
import com.xiakee.domain.ecgoods.EcGoods;
import com.xiakee.domain.ecgoods.EcProducts;
import com.xiakee.domain.sku.SkuGoodsBean;
import com.xiakee.domain.sku.SkuGoodsNoBean;
import com.xiakee.domain.sku.SkuManagerBean;
import com.xiakee.ecdao.order.EcGoodsDao;
import com.xiakee.ecdao.order.EcProductsDao;
import com.xiakee.service.sku.ExchangeRateService;
import com.xiakee.service.sku.SkuCrawlerService;
import com.xiakee.service.smsc.AccountEmailService;

@Component
public class PriceMonitorTask {

	private static Logger log = Logger.getLogger(PriceMonitorTask.class);
	private static ThreadFactory threadFactory = new ThreadFactory() {
		private final AtomicInteger integer = new AtomicInteger();

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "myThreadPool thread:" + integer.getAndIncrement());
		}
	};
	private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 10000, TimeUnit.SECONDS, workQueue, threadFactory);
	@Autowired
	private SkuGoodsDao skuGoodsDao;
	@Autowired
	private SkuGoodsNoDao skuGoodsNoDao;
	@Autowired
	private SkuCrawlerService skuCrawlerService;
	@Autowired
	private AccountEmailService emailService;
	@Autowired
	private EcGoodsDao ecGoodsDao;
	@Autowired
	private EcProductsDao ecProductsDao;
	@Autowired
	private ExchangeRateService exchangeRateService;
	@Autowired
	private SkuManagerDao skuManagerDao;

	public void priceMonitorTask() throws InterruptedException, ExecutionException {
		// 创建一个线程池
		log.info("价格更新任务");
		// Map<Long, List<String>> emailContentMap = new LinkedHashMap<Long,
		// List<String>>();
		List<SkuManagerBean> beanList = skuManagerDao.selectAll();
		for (SkuManagerBean skuManagerBean : beanList) {
			EcGoods goods = ecGoodsDao.selectBySkuCode(skuManagerBean.getSkuCode());
			if (goods != null) {
				threadPool.execute(new UpdatePriceThread(skuManagerBean));
			}
		}
		// while (threadPool.getQueue().size() > 0) {
		// Thread.sleep(10 * 60 * 1000);
		// }
		// Thread.sleep(10 * 60 * 1000);
		// if (emailContentMap.size() > 0) {
		// for (Long brandId : emailContentMap.keySet()) {
		// StringBuffer contentSb = new StringBuffer();
		// List<String> contentList = emailContentMap.get(brandId);
		// for (String content : contentList) {
		// contentSb.append(content).append("\n");
		// }
		// sendEmail(contentSb.toString());
		// }
		// }
	}

	private void comparePrice(SkuManagerBean skuManagerBean) {
		EcGoods goods = ecGoodsDao.selectBySkuCode(skuManagerBean.getSkuCode());
		if (goods != null && !goods.getName().contains("售罄")) {
			Map<Long, Object[]> priceAndStoreMap = getPriceInfo(skuManagerBean.getSkuCode());
			List<SkuGoodsBean> goodsList = skuGoodsDao.selectBySkuCode(skuManagerBean.getSkuCode());
			for (SkuGoodsBean skuGoodsBean : goodsList) {
				EcProducts product = ecProductsDao.selectByBn(skuGoodsBean.getGoodsNo());
				if (product != null) {
					List<SkuGoodsNoBean> goodsNoBeans = skuGoodsNoDao.selectByGoodsNo(skuGoodsBean.getGoodsNo());
					float price = Float.MAX_VALUE;
					int store = 0;
					for (SkuGoodsNoBean skuGoodsNoBean : goodsNoBeans) {
						// 抓取价格
						Object[] priceObj = priceAndStoreMap.get(skuGoodsNoBean.getCrawler_goods_id());
						if (priceObj != null) {
							float price0 = Float.parseFloat(String.valueOf(priceObj[0]));
							int store0 = Integer.valueOf(String.valueOf(priceObj[1]));
							if (store0 > 0 && price0 < price) {
								price = price0;
							}
							store = store + store0;
							if (store > 100) {
								store = 100;
							}
						}
					}

					if (price == Float.MAX_VALUE) {
						price = product.getCost().floatValue();
					}
					if (product.getFreez() != null && store < product.getFreez()) {
						store = product.getFreez();
					}

					EcProducts updateProduct = new EcProducts();
					updateProduct.setProductId(product.getProductId());
					if (product.getStore() != store) {
						updateProduct.setStore(store);
					}
					updateProduct.setStore(store);
					SkuGoodsBean updateGoodsBean = new SkuGoodsBean();
					updateGoodsBean.setGoodsNo(skuGoodsBean.getGoodsNo());
					updateGoodsBean.setTotalStore(store);
					int priceIncrease = (int) ((price - product.getCost().floatValue()) / product.getCost().floatValue() * 100);
					if (price > 0) {
						if (priceIncrease != 0) {
							if ((priceIncrease >= 3 || priceIncrease <= -10 || price > product.getPrice().floatValue())
									&& (skuManagerBean.getPriceLockTime() == null || skuManagerBean.getPriceLockTime().getTime() < System.currentTimeMillis())) {
								// 涨价超过3% 或者 降价超过10%
								updateProduct.setPrice(new BigDecimal(((int) (price * (skuManagerBean.getGrossProfitMargin().floatValue() + 1)) / 10 * 10) + product.getPrice().intValue() % 10));
								updateProduct.setCost(new BigDecimal(price));
							}
						}
						updateGoodsBean.setPriceIncrease(priceIncrease);
					}
					skuGoodsDao.updateByPrimaryKeySelective(updateGoodsBean);
					if (updateProduct.getStore() != null || updateProduct.getCost() != null) {
						if ((updateProduct.getStore() == null ? product.getStore() : updateProduct.getStore()) > 0
								&& (updateProduct.getCost() == null ? product.getCost() : updateProduct.getCost()).floatValue() > 0) {
							updateProduct.setMarketable("true");
						} else {
							updateProduct.setMarketable("false");
						}
						ecProductsDao.updateByPrimaryKeySelective(updateProduct);
					}
				}
			}
		}
	}

	// private void sendEmail(String content) {
	// if (StringUtils.isNotEmpty(content)) {
	// EmailContentBean email = new EmailContentBean();
	// // email.setTo("moniorder@xiakee.com");
	// email.setTo("huangzhaoshui@xiakee.com");
	// email.setSubject("小遐提醒：有商品价格大幅变更...");
	// EmailAutoSendTask emailTask = new EmailAutoSendTask();
	// emailTask.setEmailService(emailService);
	// emailTask.setTaskBean(email);
	// email.setHtmlText(content);
	// AutoExecuteTasker.addAutoExecuteTasker(emailTask);
	// }
	// }

	class UpdatePriceThread implements Runnable {
		private SkuManagerBean skuManagerBean;

		public UpdatePriceThread(SkuManagerBean skuManagerBean) {
			this.skuManagerBean = skuManagerBean;
		}

		@Override
		public void run() {
			comparePrice(skuManagerBean);
		}
	}

	/**
	 * 获取价格库存信息
	 * 
	 * @param skuCode
	 * @param mallId
	 * @return
	 */
	private Map<Long, Object[]> getPriceInfo(String skuCode) {
		Map<Long, Object[]> priceAndStoreMap = new HashMap<Long, Object[]>();
		String priceStr = skuCrawlerService.getPriceBySkuCode(skuCode, 1);
		@SuppressWarnings("unchecked")
		Map<String, String> map = JSON.parseObject(priceStr, Map.class);
		for (String key : map.keySet()) {
			int mallId = Integer.valueOf(key);
			BigDecimal exchangeRate = exchangeRateService.getExchangeRate(mallId);
			List<CrawlerGoodsPrice> priceList = JSON.parseArray(String.valueOf(map.get(key)), CrawlerGoodsPrice.class);
			for (CrawlerGoodsPrice goodsPrice : priceList) {
				float price = 0;
				int store = 0;
				try {
					price = Float.parseFloat(goodsPrice.getPrice().replaceAll("[^0-9|.]", "")) * exchangeRate.floatValue();
					if (goodsPrice.getStock() != null) {
						String storeStr = goodsPrice.getStock().replaceAll("[^0-9]", "").trim();
						if (StringUtils.isNotEmpty(storeStr)) {
							store = Integer.valueOf(storeStr);
						} else {
							if (goodsPrice.getStock().toLowerCase().contains("in stock")) {
								store = 100;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Object[] priceAndStore = new Object[2];
				priceAndStore[0] = price;
				priceAndStore[1] = store;
				priceAndStoreMap.put(goodsPrice.getGoodsId(), priceAndStore);
			}
		}
		return priceAndStoreMap;
	}

}