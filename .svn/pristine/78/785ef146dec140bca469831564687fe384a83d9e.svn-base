package com.xiakee.view.sku;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xiakee.crawler.bean.CrawlerGoodsPrice;
import com.xiakee.dao.sku.SkuGoodsDao;
import com.xiakee.dao.sku.SkuGoodsNoDao;
import com.xiakee.dao.sku.SkuGrossProfitDao;
import com.xiakee.dao.sku.SkuManagerDao;
import com.xiakee.domain.ecgoods.EcGoods;
import com.xiakee.domain.ecgoods.EcProducts;
import com.xiakee.domain.sku.SkuGoodsBean;
import com.xiakee.domain.sku.SkuGoodsNoBean;
import com.xiakee.domain.sku.SkuGrossProfit;
import com.xiakee.domain.sku.SkuManagerBean;
import com.xiakee.ecdao.order.EcGoodsDao;
import com.xiakee.ecdao.order.EcProductsDao;
import com.xiakee.service.sku.ExchangeRateService;
import com.xiakee.service.sku.SkuCatalogService;
import com.xiakee.service.sku.SkuCrawlerService;
import com.xiakee.service.sku.SkuTypeService;

@Controller
public class SkuPriceChangeController extends BaseController {

	private static Logger log = Logger.getLogger(SkuPriceChangeController.class);
	@Autowired
	private SkuManagerDao skuMnagerDao;
	@Autowired
	private SkuGoodsDao skuGoodsDao;
	@Autowired
	private SkuGoodsNoDao skuGoodsNoDao;
	@Autowired
	private SkuCatalogService skuCatalogService;
	@Autowired
	private SkuTypeService skuTypeService;
	@Autowired
	private EcGoodsDao ecGoodsDao;
	@Autowired
	private EcProductsDao ecProductsDao;
	@Autowired
	private SkuCrawlerService skuCrawlerService;
	@Autowired
	private ExchangeRateService exchangeRateService;
	@Autowired
	private SkuGrossProfitDao skuGrossProfitDao;

	@RequestMapping("price")
	public String priceChange(Model model, @RequestParam String skuCode) {
		List<SkuGrossProfit> grossProfits = skuGrossProfitDao.selectAll();
		SkuManagerBean managerBean = skuMnagerDao.findSkuManagerBeanBySkuCode(skuCode);
		List<SkuGoodsBean> goodsBeanList = skuGoodsDao.selectBySkuCode(skuCode);
		List<SkuGoodsPriceBean> goodsPriceBeanList = new ArrayList<SkuGoodsPriceBean>();
		int priceSuffix = 0;
		for (SkuGoodsBean skuGoodsBean : goodsBeanList) {
			EcProducts product = ecProductsDao.selectByBn(skuGoodsBean.getGoodsNo());
			if (product != null && product.getPrice() != null && product.getPrice().intValue() > 0) {
				priceSuffix = product.getPrice().intValue() % 10;
				break;
			}
		}

		EcGoods goods = ecGoodsDao.selectBySkuCode(skuCode);
		if (goods != null) {
			Map<Long, Object[]> priceAndStoreMap = getPriceInfo(skuCode);
			DecimalFormat df = new DecimalFormat("#.###");
			for (SkuGoodsBean skuGoodsBean : goodsBeanList) {
				EcProducts product = ecProductsDao.selectByBn(skuGoodsBean.getGoodsNo());
				if (product != null) {
					SkuGoodsPriceBean goodsPriceBean = new SkuGoodsPriceBean();
					goodsPriceBean.setGoodsno(skuGoodsBean.getGoodsNo());
					if (product.getSpecInfo() != null) {
						goodsPriceBean.setSpecInfo(product.getSpecInfo().replace("、", "<br />"));
					}
					goodsPriceBean.setImage("http://img.xiakee.com/i/50-50-1/img.xiakee.com" + skuGoodsBean.getImage());
					goodsPriceBean.setPrice(product.getPrice());
					goodsPriceBean.setCost(product.getCost());
					goodsPriceBean.setStore(product.getStore());
					List<SkuPriceBean> skuPriceBeanList = new ArrayList<SkuPriceBean>();
					List<SkuGoodsNoBean> goodsNoBeanList = skuGoodsNoDao.selectByGoodsNo(skuGoodsBean.getGoodsNo());
					float price = Float.MAX_VALUE;
					int store = 0;
					for (SkuGoodsNoBean skuGoodsNoBean : goodsNoBeanList) {
						SkuPriceBean priceBean = new SkuPriceBean();
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
							priceBean.setCrawlerPrice(new BigDecimal(df.format(price0)));
							priceBean.setCrawlerStore(store0);
							int costRose = (int) ((price0 - product.getCost().floatValue()) / product.getCost().floatValue() * 100);
							priceBean.setCostRose(costRose);

							priceBean.setMall(skuCrawlerService.getMallById(skuGoodsNoBean.getMall_id()).getMallName());
							skuPriceBeanList.add(priceBean);
						}
					}
					if (price == Float.MAX_VALUE) {
						price = 0;
					}
					if (product.getFreez() != null && store < product.getFreez()) {
						store = product.getFreez();
					}
					if (price > 0) {
						goodsPriceBean.setUpdatePrice(new BigDecimal(((int) (price * (managerBean.getGrossProfitMargin().floatValue() + 1))) / 10 * 10 + priceSuffix));
						goodsPriceBean.setUpdateCost(new BigDecimal(df.format(price)));
					} else {
						goodsPriceBean.setUpdatePrice(product.getPrice() == null ? new BigDecimal(0) : product.getPrice());
						goodsPriceBean.setUpdateCost(product.getCost() == null ? new BigDecimal(0) : product.getCost());
					}
					goodsPriceBean.setUpdateStore(store);
					goodsPriceBean.setSkuPriceBeanList(skuPriceBeanList);
					goodsPriceBeanList.add(goodsPriceBean);
				}
			}
		}
		model.addAttribute("goodsPriceBeanList", goodsPriceBeanList);
		model.addAttribute("managerBean", managerBean);
		model.addAttribute("grossProfits", grossProfits);
		return "sku/price";
	}

	@RequestMapping("updatePrice")
	@ResponseBody
	public String updatePrice(Model model, @RequestParam String goodsno, @RequestParam String price, @RequestParam String cost, @RequestParam String store, @RequestParam String skuCode, @RequestParam int grossId, @RequestParam float grossProfitMargin) {
		if (StringUtils.isEmpty(goodsno)) {
			return "0";
		}
		try {
			JSONArray goodsnoArray = JSON.parseArray(goodsno);
			JSONArray priceArray = JSON.parseArray(price);
			JSONArray costArray = JSON.parseArray(cost);
			JSONArray storeArray = JSON.parseArray(store);
			for (int i = 0; i < goodsnoArray.size(); i++) {
				EcProducts products = new EcProducts();
				products.setBn(goodsnoArray.getString(i));
				products.setPrice(new BigDecimal(priceArray.getFloatValue(i)));
				products.setCost(new BigDecimal(costArray.getFloatValue(i)));
				products.setStore(storeArray.getIntValue(i));
				if ((products.getPrice().floatValue() > 0 && products.getStore() > 0) || "true".equals(products.getIsDefault())) {
					products.setMarketable("true");
				} else {
					products.setMarketable("false");
				}
				ecProductsDao.updateByBn(products);
//				SkuGoodsBean goodsBean = new SkuGoodsBean();
//				goodsBean.setGoodsNo(goodsnoArray.getString(i));
//				goodsBean.setPriceIncrease(0);
//				skuGoodsDao.updateByPrimaryKeySelective(goodsBean);
			}
			SkuManagerBean bean = new SkuManagerBean();
			bean.setSkuCode(skuCode);
			bean.setGrossId(grossId);
			bean.setGrossProfitMargin(new BigDecimal(grossProfitMargin));
			skuMnagerDao.updateSkuManagerBeanBySkuCode(bean);
		} catch (Exception e) {
			log.error("update price is error !", e);
		}
		return "1";
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
		String priceStr = skuCrawlerService.getPriceBySkuCode(skuCode, 0);
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
