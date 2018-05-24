package com.xiakee.domain.smsc;

/**
 * 短信发送记录表，入库记录bean
 * @Product: xiakee-domain
 * @Title: SmscRecordBean.java
 * @Package com.xiakee.domain.smsc
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年6月4日 下午9:02:42
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public class SmscRecordBean {
	private Long id;
	private String mobile;
	private String content;
	private int types;//短信发送类型，例如公众号验证等
	private int status;
	private int counts;
	private String created;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTypes() {
		return types;
	}
	public void setTypes(int types) {
		this.types = types;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	@Override
	public String toString() {
		return "短发发送记录SmscRecordBean>>mobile:" + getMobile() + "==content:" + getContent() + "==status:" + getStatus();
	}
}
