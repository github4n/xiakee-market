package com.xiakee.domain.order;

public class EcOrderLogistBean {
	//对象ID,即订单ID
	private String rel_id;
	//订单日志处理时间
	private Long alttime;
	
	//订单日志处理内容
	private String log_text;
	
	
	//订单日志显示时间
	private String altTimeStr;
	private int node;//本地日志的节点索引index
	public String getRel_id() {
		return rel_id;
	}
	public void setRel_id(String rel_id) {
		this.rel_id = rel_id;
	}
	public Long getAlttime() {
		return alttime;
	}
	public void setAlttime(Long alttime) {
		this.alttime = alttime;
	}
	public String getLog_text() {
		return log_text;
	}
	public void setLog_text(String log_text) {
		this.log_text = log_text;
	}
	
	public String getAltTimeStr() {
		return altTimeStr;
	}
	public void setAltTimeStr(String altTimeStr) {
		this.altTimeStr = altTimeStr;
	}
	
	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	@Override
	public String toString() {
		return "EcOrderLogistBean=订单ID" + getRel_id() + "  操作时间：" + getAltTimeStr() + "  物流内容：" + getLog_text() ;
	}
}
