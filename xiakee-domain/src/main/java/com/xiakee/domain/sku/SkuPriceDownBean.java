package com.xiakee.domain.sku;

import java.math.BigDecimal;

public class SkuPriceDownBean extends SkuGoodsBean {
	private String zhName;

	private Long brand;
	private Long classify;
	private Long types;
	private String brandName;
	private String classifyName;
	private String typesName;

	private int grossId;
	private String grossName;
	private BigDecimal grossProfitMargin;

	private int isImport;

	private String xiakeeUrl;

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public Long getBrand() {
		return brand;
	}

	public void setBrand(Long brand) {
		this.brand = brand;
	}

	public Long getClassify() {
		return classify;
	}

	public void setClassify(Long classify) {
		this.classify = classify;
	}

	public Long getTypes() {
		return types;
	}

	public void setTypes(Long types) {
		this.types = types;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getTypesName() {
		return typesName;
	}

	public void setTypesName(String typesName) {
		this.typesName = typesName;
	}

	public int getGrossId() {
		return grossId;
	}

	public void setGrossId(int grossId) {
		this.grossId = grossId;
	}

	public String getGrossName() {
		return grossName;
	}

	public void setGrossName(String grossName) {
		this.grossName = grossName;
	}

	public BigDecimal getGrossProfitMargin() {
		return grossProfitMargin;
	}

	public void setGrossProfitMargin(BigDecimal grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}

	public int getIsImport() {
		return isImport;
	}

	public void setIsImport(int isImport) {
		this.isImport = isImport;
	}

	public String getXiakeeUrl() {
		return xiakeeUrl;
	}

	public void setXiakeeUrl(String xiakeeUrl) {
		this.xiakeeUrl = xiakeeUrl;
	}

}
