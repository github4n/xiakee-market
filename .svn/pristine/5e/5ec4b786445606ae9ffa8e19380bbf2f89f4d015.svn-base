package com.xiakee.service.sku.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xiakee.crawler.bean.CrawlerMall;
import com.xiakee.service.sku.SkuCrawlerService;
import com.xiakee.service.utils.SkuCrawlerUtil;

@Service
public class SkuCrawlerServiceImpl implements SkuCrawlerService {

	private static Map<Integer, CrawlerMall> mallMap = new HashMap<Integer, CrawlerMall>();

	@Value("${crawler.url}")
	private String domain;

	@Override
	public String crawlerSku(String skuCode, String urls, String type, String cat, int weight) {
		SkuCrawlerUtil.setDomain(domain);
		return SkuCrawlerUtil.crawlerSku(skuCode, urls, type, cat, weight);
	}

	@Override
	public String getSkuInfo(String skuCode) {
		SkuCrawlerUtil.setDomain(domain);
		return SkuCrawlerUtil.getSkuInfo(skuCode);
	}

	@Override
	public String getPriceBySkuCode(String skuCode, int type) {
		SkuCrawlerUtil.setDomain(domain);
		return SkuCrawlerUtil.getPriceBySkuCode(skuCode, type);
	}

	@Override
	public String getSkuCodeByUrls(String urls) {
		SkuCrawlerUtil.setDomain(domain);
		return SkuCrawlerUtil.getSkuCodeByUrls(urls);
	}

	@Override
	public String getImageBySkuCode(String skuCode) {
		SkuCrawlerUtil.setDomain(domain);
		return SkuCrawlerUtil.getImageBySkuCode(skuCode);
	}

	@Override
	public CrawlerMall getMallById(int mallId) {
		CrawlerMall mall = mallMap.get(mallId);
		if (mall == null) {
			SkuCrawlerUtil.setDomain(domain);
			String mallStr = SkuCrawlerUtil.getMallById(mallId);
			if (StringUtils.isNotBlank(mallStr)) {
				mall = JSON.parseObject(mallStr, CrawlerMall.class);
				mallMap.put(mallId, mall);
			}
		}
		return mall;
	}

	@Override
	public int getMallById(String skuCode) {
		int mallid = Integer.valueOf(SkuCrawlerUtil.getMallIdBySkuCode(skuCode));
		return mallid;
	}

}
