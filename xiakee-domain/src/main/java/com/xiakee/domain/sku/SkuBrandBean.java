package com.xiakee.domain.sku;

/**
 * 商城品牌信息表
 * @Product: xiakee-domain
 * @Title: SkuBrandBean.java
 * @Package com.xiakee.domain.sku
 * @Description: 遐客行后台管理系统
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com
 * @Date 2015年6月17日 下午11:45:21
 * @Version 1.0
 * @Copyright: Copyright (c) 2014
 */
public class SkuBrandBean {
	private Long id;
	private String name;
	private String brandCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	@Override
	public String toString() {
		return "SkuBrandBean:品牌名称：" + getName() + "  品牌简称：" + getBrandCode();
	}
	
}
