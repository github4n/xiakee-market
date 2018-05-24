package com.xiakee.domain.sys;

/**
 * 系统操作日志类型
 * @Product: xiakee-domain
 * @Title: SystemLogBean.java
 * @Package com.xiakee.domain.sys
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年9月10日 下午3:36:41
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public class SystemLogBean {
	private Long id;
	private Long userid;
	private String url;
	private String created;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "系统操作日志类>>>SystemLogBean:Userid " + getUserid() + "   user " + getUrl() + "   created " + getCreated();
	}
	
}
