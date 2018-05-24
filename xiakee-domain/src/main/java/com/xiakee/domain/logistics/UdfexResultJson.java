package com.xiakee.domain.logistics;

public class UdfexResultJson {
	private String status;//返回结果 0:成功 1:失败
	private String message;//响应信息
	private String bizCode;//业务代码
	private String requestDate;//请求时间
	private String requestId;//请求ID
	private String requestSrc;//请求源
	private String result;//处理同步返回的结果
	private String token;//
	private String responseDate;//
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestSrc() {
		return requestSrc;
	}
	public void setRequestSrc(String requestSrc) {
		this.requestSrc = requestSrc;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}
	
}
