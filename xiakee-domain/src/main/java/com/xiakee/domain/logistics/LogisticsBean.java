package com.xiakee.domain.logistics;

import com.xiakee.domain.utils.LogistNodeEnum;
import com.xiakee.domain.utils.SmscContentTypeEnum;

public class LogisticsBean {
	private String id;
	private String orderid;
	private Long infoId;
	private String content;
	private int node;//物流信息类型，主要分为国外阶段信息，百威阶段，和有赞阶段
	private SmscContentTypeEnum smscType;//短信发送类型,本条物流节点信息是否发送短信
	private LogistNodeEnum logistNode;//物流信息节点
	private String created;
	
	private String details;//物流节点详情
	
	public LogisticsBean(String orderid, Long infoId,SmscContentTypeEnum smscType, LogistNodeEnum logistNode) {
		super();
		this.orderid = orderid;
		this.infoId = infoId;
		this.smscType = smscType;
		this.logistNode = logistNode;
	}
	public LogisticsBean() {
		super();
	}
	//短信发送功能开关
	private boolean openSmsc = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	public SmscContentTypeEnum getSmscType() {
		return smscType;
	}
	
	public void setSmscType(SmscContentTypeEnum smscType) {
		this.smscType = smscType;
	}
	
	
	public LogistNodeEnum getLogistNode() {
		return logistNode;
	}
	public void setLogistNode(LogistNodeEnum logistNode) {
		this.logistNode = logistNode;
	}
	public boolean isOpenSmsc() {
		return openSmsc;
	}
	public void setOpenSmsc(boolean openSmsc) {
		this.openSmsc = openSmsc;
	}
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "物流节点信息：id=" + getId() + "   orderid=" + getOrderid() + "    infoID=" + getInfoId() + "    content=" + getContent() + 
				"    logistNode=" + getLogistNode() + "    smsc=" + getSmscType();
	}
}
