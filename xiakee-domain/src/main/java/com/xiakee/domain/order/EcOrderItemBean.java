package com.xiakee.domain.order;

/**
 * 商城订单表明细表
 * @Product: xiakee-domain
 * @Title: EcOrderItemBean.java
 * @Package com.xiakee.domain.order
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年7月10日 下午3:57:56
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public class EcOrderItemBean {
	//订单明细ID
	private Long item_id;
	//订单ID
	private Long order_id;
	//订单明细对应的商品对象ID, 对应到sdb_b2c_order_objects表
	private Long obj_id;
	//货品ID
	private Long product_id;
	//商品ID
	private Long goods_id;
	//商品类型ID
	private Long type_id;
	//明细商品的品牌名
	private String bn;
	//明细商品的名称
	private String name;
	//明细商品的成本
	private Double cost;
	//明细商品总额
	private Double amount;
	//明细商品的销售价(购入价)
	private Double price;
	//明细商品购买数量
	private Long nums;
	//明细商品的规格属性
	private String addon;
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getObj_id() {
		return obj_id;
	}
	public void setObj_id(Long obj_id) {
		this.obj_id = obj_id;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public Long getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	public Long getType_id() {
		return type_id;
	}
	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}
	public String getBn() {
		return bn;
	}
	public void setBn(String bn) {
		this.bn = bn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getNums() {
		return nums;
	}
	public void setNums(Long nums) {
		this.nums = nums;
	}
	public String getAddon() {
		return addon;
	}
	public void setAddon(String addon) {
		this.addon = addon;
	}
	@Override
	public String toString() {
		return "EcOrderItemBean信息：货品名称：" + getName() + " 规格：" + getAddon();
	}
	
}
