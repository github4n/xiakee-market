package com.xiakee.domain.logistics;

public class DeclareGoodsBean {
	private Long infoId;
	private String goodsName;//品名
	private String type = "服装-箱包";//所属类别
	private String childType;//子类别
	private String enName;//英文名
	private String brand;//品牌	
	private String price;//单价
	private int sum;//数量
	private String userName = "iaistar";//用户名,百威申报的帐号
	private String wrapName;//包裹名称
	private String url = "http://www.amazon.com";//购物网站，只填写域名即可
	private String expComp = "UPS";//快递公司
	private String expressno;//快递单号
	
	//以下四个属性要么全填写，要么全不填写
	private String receiverName;//接收人姓名
	private String receiverMobile;//接收人手机
	private String receiverAddress;//接收人地址
	private String transport = "经济快线";//转运路线
	
	private String receiverZip = "100000";//接收人邮编

	private int now = 0;//是否即可出库，0，默认待出库
	
	
	//为了申报柏威地址使用，主要处理地址的重复情况
	private String province;
	private String city;
	private String district;
	private String address;
	
	private String title;
	private String sku_properties_name;
	
	private String infoIds;//合箱操作中的多条infoID
	
	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChildType() {
		return childType;
	}

	public void setChildType(String childType) {
		this.childType = childType;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWrapName() {
		return wrapName;
	}

	public void setWrapName(String wrapName) {
		this.wrapName = wrapName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExpComp() {
		return expComp;
	}

	public void setExpComp(String expComp) {
		this.expComp = expComp;
	}

	public String getExpressno() {
		return expressno;
	}

	public void setExpressno(String expressno) {
		this.expressno = expressno;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getReceiverZip() {
		return receiverZip;
	}

	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getInfoIds() {
		return infoIds;
	}

	public void setInfoIds(String infoIds) {
		this.infoIds = infoIds;
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

	@Override
	public String toString() {
		return "==DeclareGoodsBean==百威申报信息====name===" + getReceiverName() + "===goodsName==" + getGoodsName();
	}
	
}
