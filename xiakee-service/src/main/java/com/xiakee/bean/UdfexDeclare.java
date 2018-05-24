package com.xiakee.bean;

import java.util.List;

public class UdfexDeclare {

	private String infoId;
	private ConsigneeInfo consigneeInfo;
	private ConsigneeInfo consigneeInfoDto;
	private String logisticsNo;
	private String logisticsVendor;
	private String refLogisticsNo;
	private String sellerName;
	private String sellerOrderNo;
	private String warehouseCode;
	private String serviceProductCode;
	private List<PiecesItem> piecesItems;
	private List<PiecesItem> piecesItemDetailDtoList;

	public ConsigneeInfo getConsigneeInfoDto() {
		return consigneeInfoDto;
	}

	public void setConsigneeInfoDto(ConsigneeInfo consigneeInfoDto) {
		this.consigneeInfoDto = consigneeInfoDto;
	}

	public List<PiecesItem> getPiecesItemDetailDtoList() {
		return piecesItemDetailDtoList;
	}

	public void setPiecesItemDetailDtoList(List<PiecesItem> piecesItemDetailDtoList) {
		this.piecesItemDetailDtoList = piecesItemDetailDtoList;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public ConsigneeInfo getConsigneeInfo() {
		return consigneeInfo;
	}

	public void setConsigneeInfo(ConsigneeInfo consigneeInfo) {
		this.consigneeInfo = consigneeInfo;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public String getLogisticsVendor() {
		return logisticsVendor;
	}

	public void setLogisticsVendor(String logisticsVendor) {
		this.logisticsVendor = logisticsVendor;
	}

	public String getRefLogisticsNo() {
		return refLogisticsNo;
	}

	public void setRefLogisticsNo(String refLogisticsNo) {
		this.refLogisticsNo = refLogisticsNo;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerOrderNo() {
		return sellerOrderNo;
	}

	public void setSellerOrderNo(String sellerOrderNo) {
		this.sellerOrderNo = sellerOrderNo;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getServiceProductCode() {
		return serviceProductCode;
	}

	public void setServiceProductCode(String serviceProductCode) {
		this.serviceProductCode = serviceProductCode;
	}

	public List<PiecesItem> getPiecesItems() {
		return piecesItems;
	}

	public void setPiecesItems(List<PiecesItem> piecesItems) {
		this.piecesItems = piecesItems;
	}

}
