package com.xiakee.domain.logistics;

public class TransportBean {
	private Long id;
	private String expressno;
	private String orderid;
	private String transportno;
	private Integer transfer_id;
	private Integer packageno;
	private String target;
	private String modify;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getExpressno() {
		return expressno;
	}


	public void setExpressno(String expressno) {
		this.expressno = expressno;
	}


	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	public String getTransportno() {
		return transportno;
	}


	public void setTransportno(String transportno) {
		this.transportno = transportno;
	}


	public Integer getTransfer_id() {
		return transfer_id;
	}


	public void setTransfer_id(Integer transfer_id) {
		this.transfer_id = transfer_id;
	}


	public Integer getPackageno() {
		return packageno;
	}


	public void setPackageno(Integer packageno) {
		this.packageno = packageno;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}


	public String getModify() {
		return modify;
	}


	public void setModify(String modify) {
		this.modify = modify;
	}


	@Override
	public String toString() {
		return "TransportBean:expressno=" + expressno + "   packageno=" + packageno + "   target=" + target;
	}
	
}
