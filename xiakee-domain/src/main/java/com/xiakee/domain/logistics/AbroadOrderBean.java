package com.xiakee.domain.logistics;

import java.util.List;

/**
 * 国外包裹信息
 * @Product: xiakee-domain
 * @Title: AbroadOrderBean.java
 * @Package com.xiakee.domain.logistics
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年5月15日 下午5:06:30
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public class AbroadOrderBean {
	private Long id;
	//采购网址
	private String url;
	//国外订单号
	private String outOrderId;
	//货币类型
	private String currency;
	
	private String infos;//infoId的合集，以逗号分隔
	
	private List<BoxnoBean> beans;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOutOrderId() {
		return outOrderId;
	}
	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getInfos() {
		return infos;
	}
	public void setInfos(String infos) {
		this.infos = infos;
	}
	
	public List<BoxnoBean> getBeans() {
		return beans;
	}
	public void setBeans(List<BoxnoBean> beans) {
		this.beans = beans;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "====AbroadOrderBean====outOrderId==" + getOutOrderId() + "====infos" + getInfos();
	}
}
