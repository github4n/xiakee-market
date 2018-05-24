package com.xiakee.domain.sku;

public class SkuGoodsBean {
	/** 货品号 */
	private String goodsNo;

	/** sku编号 */
	private String skuCode;

	/** 颜色 */
	private String color;

	/** 大小 */
	private String size;

	/** 代表图 */
	private String image;

	/** 价格涨幅 */
	private Integer priceIncrease;

	/** 总库存 */
	private Integer totalStore;

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo == null ? null : goodsNo.trim();
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode == null ? null : skuCode.trim();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color == null ? null : color.trim();
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size == null ? null : size.trim();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public Integer getPriceIncrease() {
		return priceIncrease;
	}

	public void setPriceIncrease(Integer priceIncrease) {
		this.priceIncrease = priceIncrease;
	}

	public Integer getTotalStore() {
		return totalStore;
	}

	public void setTotalStore(Integer totalStore) {
		this.totalStore = totalStore;
	}
}