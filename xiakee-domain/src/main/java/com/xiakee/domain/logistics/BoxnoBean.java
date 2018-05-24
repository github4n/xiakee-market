package com.xiakee.domain.logistics;

public class BoxnoBean {
	private Long infoId;
	private Long abroadId;
	private String expressno;//国外包裹ID
	private int declared;//是否已申报,//0,默认未申报，1，已成功申报，2，申报失败，重复申报
	private String boxno;
	private String status;
	private String price;
	private int sum;
	private int trend;
	private String modified;//海外包裹录入时间
	private String created;//海外订单生成时间
	private int transfer_id;//转运公司代码
	private String transfer;//转运公司名称
	
	private String title;//不关联表属性，单独前台显示
	private String sku_properties_name;//不关联表属性，单独前台显示
	private String abroadOrderName;//不关联表属性，单独前台显示
	private String name;
	private String remark;
	private String url;
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	public Long getAbroadId() {
		return abroadId;
	}
	public void setAbroadId(Long abroadId) {
		this.abroadId = abroadId;
	}
	public String getExpressno() {
		return expressno;
	}
	public void setExpressno(String expressno) {
		this.expressno = expressno;
	}
	public int getDeclared() {
		return declared;
	}
	public void setDeclared(int declared) {
		this.declared = declared;
	}
	public String getBoxno() {
		return boxno;
	}
	public void setBoxno(String boxno) {
		this.boxno = boxno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getTrend() {
		return trend;
	}
	public void setTrend(int trend) {
		this.trend = trend;
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
	public String getAbroadOrderName() {
		return abroadOrderName;
	}
	public void setAbroadOrderName(String abroadOrderName) {
		this.abroadOrderName = abroadOrderName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public int getTransferId() {
		if(transfer_id==3){
			return 2;
		}
		return transfer_id;
	}
	public int getTransfer_id() {
		return transfer_id;
	}
	public void setTransfer_id(int transfer_id) {
		this.transfer_id = transfer_id;
	}
	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
