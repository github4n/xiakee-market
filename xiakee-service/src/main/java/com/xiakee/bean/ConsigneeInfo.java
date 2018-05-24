package com.xiakee.bean;

public class ConsigneeInfo {

	private String consigneeCityCode;
	private String consigneeCountryCode;
	private String consigneeDistrictCode;
	private String consigneeMobileNo;
	private String consigneeName;
	private String consigneePhoneNo;
	private String consigneePostCode;
	private String consigneeStateCode;
	private String consigneeStreet;

	public String getConsigneeCityCode() {
		return consigneeCityCode;
	}

	public void setConsigneeCityCode(String consigneeCityCode) {
		this.consigneeCityCode = consigneeCityCode;
	}

	public String getConsigneeCountryCode() {
		return consigneeCountryCode;
	}

	public void setConsigneeCountryCode(String consigneeCountryCode) {
		this.consigneeCountryCode = consigneeCountryCode;
	}

	public String getConsigneeDistrictCode() {
		return consigneeDistrictCode;
	}

	public void setConsigneeDistrictCode(String consigneeDistrictCode) {
		this.consigneeDistrictCode = consigneeDistrictCode;
	}

	public String getConsigneeMobileNo() {
		return consigneeMobileNo;
	}

	public void setConsigneeMobileNo(String consigneeMobileNo) {
		this.consigneeMobileNo = consigneeMobileNo;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneePhoneNo() {
		return consigneePhoneNo;
	}

	public void setConsigneePhoneNo(String consigneePhoneNo) {
		this.consigneePhoneNo = consigneePhoneNo;
	}

	public String getConsigneePostCode() {
		return consigneePostCode;
	}

	public void setConsigneePostCode(String consigneePostCode) {
		this.consigneePostCode = consigneePostCode;
	}

	public String getConsigneeStateCode() {
		return consigneeStateCode;
	}

	public void setConsigneeStateCode(String consigneeStateCode) {
		this.consigneeStateCode = consigneeStateCode;
	}

	public String getConsigneeStreet() {
		return consigneeStreet;
	}

	public void setConsigneeStreet(String consigneeStreet) {
		this.consigneeStreet = consigneeStreet;
	}

	@Override
	public String toString() {
		return "ConsigneeInfo [consigneeCityCode=" + consigneeCityCode + ", consigneeCountryCode=" + consigneeCountryCode + ", consigneeDistrictCode=" + consigneeDistrictCode + ", consigneeMobileNo="
				+ consigneeMobileNo + ", consigneeName=" + consigneeName + ", consigneePhoneNo=" + consigneePhoneNo + ", consigneePostCode=" + consigneePostCode + ", consigneeStateCode="
				+ consigneeStateCode + ", consigneeStreet=" + consigneeStreet + "]";
	}

}
