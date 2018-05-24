package com.xiakee.service.sku.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.crawler.bean.CrawlerMall;
import com.xiakee.dao.sku.ExchangeRateDao;
import com.xiakee.domain.sku.ExchangeRate;
import com.xiakee.service.sku.ExchangeRateService;
import com.xiakee.service.sku.SkuCrawlerService;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	private static Map<Integer, BigDecimal> exchangeRateMap = new HashMap<Integer, BigDecimal>();

	@Autowired
	private ExchangeRateDao exchangeRateDao;
	@Autowired
	private SkuCrawlerService skuCrawlerService;

	@Override
	public BigDecimal getExchangeRate(int mallId) {
		BigDecimal decimal = exchangeRateMap.get(mallId);
		if (decimal == null) {
			CrawlerMall mall = skuCrawlerService.getMallById(mallId);
			if(mall != null) {
				ExchangeRate rate = exchangeRateDao.selectByPrimaryKey(mall.getMallCurrency());
				if(rate != null) {
					decimal = rate.getValue();
					exchangeRateMap.put(mallId, decimal);
				}
			}
			
		}
		return decimal;
	}

}
