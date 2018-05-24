package com.xiakee.domain.logistics;

import java.util.List;

import com.xiakee.domain.yz.YzorderInfoBean;

public class LogistDetailsBean {
	private String orderId;
	private List<LogisticsBean> logBeans;
	private List<YzorderInfoBean> infoBeans;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<LogisticsBean> getLogBeans() {
		return logBeans;
	}
	public void setLogBeans(List<LogisticsBean> logBeans) {
		this.logBeans = logBeans;
	}
	public List<YzorderInfoBean> getInfoBeans() {
		return infoBeans;
	}
	public void setInfoBeans(List<YzorderInfoBean> infoBeans) {
		this.infoBeans = infoBeans;
	}
}
