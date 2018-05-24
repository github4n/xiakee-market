package com.xiakee.domain.analy;

public class AnalyOrderSumsBean {
	private String key;
	//商品采购成本总价
	private Double cost;
	private Double pmt;
	private Double freight;
	private Double payed;
	//商品销售总价
	private Double items;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getPmt() {
		return pmt;
	}
	public void setPmt(Double pmt) {
		this.pmt = pmt;
	}
	public Double getFreight() {
		return freight;
	}
	public void setFreight(Double freight) {
		this.freight = freight;
	}
	public Double getPayed() {
		return payed;
	}
	public void setPayed(Double payed) {
		this.payed = payed;
	}
	public Double getItems() {
		return items;
	}
	public void setItems(Double items) {
		this.items = items;
	}
	
	
}
