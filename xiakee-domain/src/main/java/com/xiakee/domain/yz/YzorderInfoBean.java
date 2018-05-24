package com.xiakee.domain.yz;

public class YzorderInfoBean {
	private Long id;
	private Long item_id;
	private String orderid;
	private String title;
	private String sku_properties_name;
	private String num;
	private String price;
	private String remark;//采购备注信息
	private String name;//单单使用在前端显示订单客户名称，与表结构无关
	private String mobile;
	private String address;
	private String prodUrl;//商品详细页，新商城使用
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSku_properties_name() {
		return sku_properties_name;
	}
	public void setSku_properties_name(String sku_properties_name) {
		this.sku_properties_name = sku_properties_name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProdUrl() {
		return prodUrl;
	}
	public void setProdUrl(String prodUrl) {
		this.prodUrl = prodUrl;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "=======Title=" + getTitle() + "===sku_properties_name=" + getSku_properties_name() + "====num=" + getNum() + "====price=" + getPrice() + "===========";
	}
	
}
