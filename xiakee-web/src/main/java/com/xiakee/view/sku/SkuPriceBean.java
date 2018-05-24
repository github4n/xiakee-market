package com.xiakee.view.sku;

import java.math.BigDecimal;

public class SkuPriceBean {

	private String mall;
	private BigDecimal crawlerPrice;
	private int crawlerStore;
	private int costRose;

	public String getMall() {
		return mall;
	}

	public void setMall(String mall) {
		this.mall = mall;
	}

	public BigDecimal getCrawlerPrice() {
		return crawlerPrice;
	}

	public void setCrawlerPrice(BigDecimal crawlerPrice) {
		this.crawlerPrice = crawlerPrice;
	}

	public int getCrawlerStore() {
		return crawlerStore;
	}

	public void setCrawlerStore(int crawlerStore) {
		this.crawlerStore = crawlerStore;
	}

	public int getCostRose() {
		return costRose;
	}

	public void setCostRose(int costRose) {
		this.costRose = costRose;
	}

}
