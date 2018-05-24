package com.xiakee.domain.sku;

import java.util.List;
import java.util.Map;

import com.xiakee.crawler.bean.CrawlerGoods;
import com.xiakee.crawler.bean.CrawlerMall;
import com.xiakee.crawler.bean.CrawlerProduct;

public class SkuCrawlerBean {

	private List<CrawlerProduct> crawlerProductList;
	private Map<Long, List<CrawlerGoods>> crawlerGoodListMap;
	private Map<Integer, CrawlerMall> crawlerMallMap;

	public List<CrawlerProduct> getCrawlerProductList() {
		return crawlerProductList;
	}

	public void setCrawlerProductList(List<CrawlerProduct> crawlerProductList) {
		this.crawlerProductList = crawlerProductList;
	}

	public Map<Long, List<CrawlerGoods>> getCrawlerGoodListMap() {
		return crawlerGoodListMap;
	}

	public void setCrawlerGoodListMap(Map<Long, List<CrawlerGoods>> crawlerGoodListMap) {
		this.crawlerGoodListMap = crawlerGoodListMap;
	}

	public Map<Integer, CrawlerMall> getCrawlerMallMap() {
		return crawlerMallMap;
	}

	public void setCrawlerMallMap(Map<Integer, CrawlerMall> crawlerMallMap) {
		this.crawlerMallMap = crawlerMallMap;
	}

}
