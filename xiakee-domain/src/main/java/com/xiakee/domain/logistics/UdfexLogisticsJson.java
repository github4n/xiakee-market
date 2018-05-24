package com.xiakee.domain.logistics;

import java.util.List;

public class UdfexLogisticsJson {
	private String status;
	private String errorMsg;
	private List<UdfexDetailBean> consignmentHistoryDtoList;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<UdfexDetailBean> getConsignmentHistoryDtoList() {
		return consignmentHistoryDtoList;
	}

	public void setConsignmentHistoryDtoList(
			List<UdfexDetailBean> consignmentHistoryDtoList) {
		this.consignmentHistoryDtoList = consignmentHistoryDtoList;
	}
}
