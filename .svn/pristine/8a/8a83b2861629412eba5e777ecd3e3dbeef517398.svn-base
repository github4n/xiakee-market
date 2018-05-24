package com.xiakee.view.task;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiakee.domain.ecgoods.EcGoods;
import com.xiakee.domain.ecgoods.EcProducts;
import com.xiakee.ecdao.order.EcGoodsDao;
import com.xiakee.ecdao.order.EcProductsDao;

@Component
public class CheckPriceStoreTask {
	private static Logger log = Logger.getLogger(CheckPriceStoreTask.class);
	@Autowired
	private EcGoodsDao ecGoodsDao;
	@Autowired
	private EcProductsDao ecProductsDao;

	public void checkPriceAndStore() {
		log.info("默认货品修改任务开始");
		List<Long> goodsIdList = ecGoodsDao.selectIdByAll();
		for (Long goodsId : goodsIdList) {
			EcGoods goodss = ecGoodsDao.selectByPrimaryKey(goodsId);
			if (!goodss.getName().contains("售罄")) {
				List<EcProducts> productList = ecProductsDao.selectByGoodsId(goodsId);
				if (productList != null && productList.size() > 0) {
					Integer productId = null;
					Float minPrice = null;
					Float mktprice = null;
					boolean isP55 = false;
					for (EcProducts product : productList) {
						if (product.getBn() != null && product.getBn().startsWith("P55")) {
							isP55 = true;
							continue;
						}
						if (product.getPrice().floatValue() > 0 && product.getStore() > 0) {
							product.setMarketable("true");
							if (product.getFreez() == null || product.getStore() > product.getFreez()) {
								if (productId == null) {
									productId = product.getProductId();
									minPrice = product.getPrice().floatValue();
									if (product.getMktprice() != null) {
										mktprice = product.getMktprice().floatValue();
									}
								} else {
									if (product.getPrice().floatValue() < minPrice) {
										productId = product.getProductId();
										minPrice = product.getPrice().floatValue();
										if (product.getMktprice() != null) {
											mktprice = product.getMktprice().floatValue();
										}
									}
								}
							}
						} else {
							product.setMarketable("false");
						}
						product.setIsDefault("false");
						ecProductsDao.updateByPrimaryKeySelective(product);
					}
					if (!isP55) {
						if (productId != null) {
							EcProducts product = new EcProducts();
							product.setProductId(productId);
							product.setIsDefault("true");
							ecProductsDao.updateByPrimaryKeySelective(product);
							EcGoods goods = new EcGoods();
							goods.setGoodsId(goodsId);
							if (minPrice != null) {
								goods.setPrice(new BigDecimal(minPrice));
							}
							if (mktprice != null) {
								goods.setMktprice(new BigDecimal(mktprice));
							}
							if (!"true".equals(goodss.getMarketable())) {
								goods.setMarketable("true");
							}
							ecGoodsDao.updateByPrimaryKeySelective(goods);
						} else {
							if (!"false".equals(goodss.getMarketable())) {
								EcGoods goods = new EcGoods();
								goods.setGoodsId(goodsId);
								goods.setMarketable("false");
								ecGoodsDao.updateByPrimaryKeySelective(goods);
							}
						}
					}
				}
			}
		}
	}
}
