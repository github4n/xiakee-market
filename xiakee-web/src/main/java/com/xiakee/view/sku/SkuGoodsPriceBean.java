package com.xiakee.view.sku;

import java.math.BigDecimal;
import java.util.List;

public class SkuGoodsPriceBean {

	private String goodsno;
	private String specInfo;
	private BigDecimal price;
	private BigDecimal cost;
	private int store;
	private String image;
	private BigDecimal updatePrice;
	private BigDecimal updateCost;
	private BigDecimal updateMktprice;
	private int updateStore;
	private List<SkuPriceBean> skuPriceBeanList;

	public String getGoodsno() {
		return goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

	public String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getUpdatePrice() {
		return updatePrice;
	}

	public void setUpdatePrice(BigDecimal updatePrice) {
		this.updatePrice = updatePrice;
	}

	public int getUpdateStore() {
		return updateStore;
	}

	public void setUpdateStore(int updateStore) {
		this.updateStore = updateStore;
	}

	public BigDecimal getUpdateCost() {
		return updateCost;
	}

	public void setUpdateCost(BigDecimal updateCost) {
		this.updateCost = updateCost;
	}

	public BigDecimal getUpdateMktprice() {
		return updateMktprice;
	}

	public void setUpdateMktprice(BigDecimal updateMktprice) {
		this.updateMktprice = updateMktprice;
	}

	public List<SkuPriceBean> getSkuPriceBeanList() {
		return skuPriceBeanList;
	}

	public void setSkuPriceBeanList(List<SkuPriceBean> skuPriceBeanList) {
		this.skuPriceBeanList = skuPriceBeanList;
	}

}
