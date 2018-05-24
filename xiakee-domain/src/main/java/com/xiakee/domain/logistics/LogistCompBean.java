package com.xiakee.domain.logistics;

/**
 * 国内物流包裹信息
 * @Product: xiakee-domain
 * @Title: LogistCompBean.java
 * @Package com.xiakee.domain.logistics
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年5月25日 下午2:20:55
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public class LogistCompBean {
	private Long infoId;
	private int logistComp = 1;//国内物流公司代码, 默认1，代表申通物流
	private String expressno;
	private String created;
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	public int getLogistComp() {
		return logistComp;
	}
	public void setLogistComp(int logistComp) {
		this.logistComp = logistComp;
	}
	public String getExpressno() {
		return expressno;
	}
	public void setExpressno(String expressno) {
		this.expressno = expressno;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
}
