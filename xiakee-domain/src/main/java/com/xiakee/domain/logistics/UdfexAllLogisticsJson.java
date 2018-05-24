package com.xiakee.domain.logistics;

import java.util.List;

public class UdfexAllLogisticsJson {
	//运单分拆类型  0:无分拆 1:合单主单 2:合单子单 3:拆单主单 4:拆单子单
	private String consignmentSplitType;
	private MasterDto masterConsignmentDetailDto;
	private List<MasterDto> slaverConsignmentDetailDtoList;

	
	public String getConsignmentSplitType() {
		return consignmentSplitType;
	}

	public void setConsignmentSplitType(String consignmentSplitType) {
		this.consignmentSplitType = consignmentSplitType;
	}

	public MasterDto getMasterConsignmentDetailDto() {
		return masterConsignmentDetailDto;
	}

	public void setMasterConsignmentDetailDto(MasterDto masterConsignmentDetailDto) {
		this.masterConsignmentDetailDto = masterConsignmentDetailDto;
	}

	public List<MasterDto> getSlaverConsignmentDetailDtoList() {
		return slaverConsignmentDetailDtoList;
	}

	public void setSlaverConsignmentDetailDtoList(List<MasterDto> slaverConsignmentDetailDtoList) {
		this.slaverConsignmentDetailDtoList = slaverConsignmentDetailDtoList;
	}

	public static class MasterDto{
		private List<UdfexDetailBean> consignmentDetailHistoryDtoList;
		//递优运单号
		private String consignmentNo;
		//身份证姓名
		private String idcardFullName;
		//身份证号码
		private String idcardNo;
		//海外物流号
		private String logisticsNo;
		//海外物流公司
		private String logisticsVendor;
		private List<PiecesItem> piecesItems;
		//商户关联物流号
		private String refLogisticsNo;
		//服务编码
		private String serviceProductCode;
		//运单数据库主键号码
		private Long tmConsignmentId;
		//仓库编码
		private String warehouseCode;
		public List<UdfexDetailBean> getConsignmentDetailHistoryDtoList() {
			return consignmentDetailHistoryDtoList;
		}
		public void setConsignmentDetailHistoryDtoList(List<UdfexDetailBean> consignmentDetailHistoryDtoList) {
			this.consignmentDetailHistoryDtoList = consignmentDetailHistoryDtoList;
		}
		public String getConsignmentNo() {
			return consignmentNo;
		}
		public void setConsignmentNo(String consignmentNo) {
			this.consignmentNo = consignmentNo;
		}
		public String getIdcardFullName() {
			return idcardFullName;
		}
		public void setIdcardFullName(String idcardFullName) {
			this.idcardFullName = idcardFullName;
		}
		public String getIdcardNo() {
			return idcardNo;
		}
		public void setIdcardNo(String idcardNo) {
			this.idcardNo = idcardNo;
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
		public List<PiecesItem> getPiecesItems() {
			return piecesItems;
		}
		public void setPiecesItems(List<PiecesItem> piecesItems) {
			this.piecesItems = piecesItems;
		}
		public String getRefLogisticsNo() {
			return refLogisticsNo;
		}
		public void setRefLogisticsNo(String refLogisticsNo) {
			this.refLogisticsNo = refLogisticsNo;
		}
		public String getServiceProductCode() {
			return serviceProductCode;
		}
		public void setServiceProductCode(String serviceProductCode) {
			this.serviceProductCode = serviceProductCode;
		}
		public Long getTmConsignmentId() {
			return tmConsignmentId;
		}
		public void setTmConsignmentId(Long tmConsignmentId) {
			this.tmConsignmentId = tmConsignmentId;
		}
		public String getWarehouseCode() {
			return warehouseCode;
		}
		public void setWarehouseCode(String warehouseCode) {
			this.warehouseCode = warehouseCode;
		}
		
	}
	
	public static class PiecesItem{
		//总价
		private Double amount;
		//品牌
		private String brandName;
		//币种
		private String currency;
		//品类
		private String goodsCode;
		//品类说明
		private String goodsDescription;
		//件数
		private Integer piecesItemQty; 
		//规格
		private String spec;
		//税号
		private String tariffNumber;
		//包裹ID
		private Long tmPiecesItemId;
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public String getBrandName() {
			return brandName;
		}
		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getGoodsCode() {
			return goodsCode;
		}
		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}
		public String getGoodsDescription() {
			return goodsDescription;
		}
		public void setGoodsDescription(String goodsDescription) {
			this.goodsDescription = goodsDescription;
		}
		public Integer getPiecesItemQty() {
			return piecesItemQty;
		}
		public void setPiecesItemQty(Integer piecesItemQty) {
			this.piecesItemQty = piecesItemQty;
		}
		public String getSpec() {
			return spec;
		}
		public void setSpec(String spec) {
			this.spec = spec;
		}
		public String getTariffNumber() {
			return tariffNumber;
		}
		public void setTariffNumber(String tariffNumber) {
			this.tariffNumber = tariffNumber;
		}
		public Long getTmPiecesItemId() {
			return tmPiecesItemId;
		}
		public void setTmPiecesItemId(Long tmPiecesItemId) {
			this.tmPiecesItemId = tmPiecesItemId;
		}
	}
}
