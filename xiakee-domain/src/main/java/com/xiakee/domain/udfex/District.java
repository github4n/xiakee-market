package com.xiakee.domain.udfex;

import java.util.Date;

public class District {
    private Integer cdDistrictId;

    /** 行政区编码 */
    private String districtCode;

    /** 行政区名字 */
    private String districtName;

    /** 行政区的英文名 */
    private String districtEnglishName;

    /** 城市编码 */
    private String cityCode;

    /** 是否为偏远地区 */
    private Integer isRemoteArea;

    /** 版本号 */
    private Integer recordVersion;

    /** 创建人代码 */
    private String createUserCode;

    /** 创建时间 */
    private Date createDateTime;

    /** 创建时区 */
    private String createTimeZone;

    /** 修改人代码 */
    private String updateUserCode;

    /** 修改时间 */
    private Date updateDateTime;

    /** 修改时区 */
    private String updateTimeZone;

    public Integer getCdDistrictId() {
        return cdDistrictId;
    }

    public void setCdDistrictId(Integer cdDistrictId) {
        this.cdDistrictId = cdDistrictId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getDistrictEnglishName() {
        return districtEnglishName;
    }

    public void setDistrictEnglishName(String districtEnglishName) {
        this.districtEnglishName = districtEnglishName == null ? null : districtEnglishName.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public Integer getIsRemoteArea() {
        return isRemoteArea;
    }

    public void setIsRemoteArea(Integer isRemoteArea) {
        this.isRemoteArea = isRemoteArea;
    }

    public Integer getRecordVersion() {
        return recordVersion;
    }

    public void setRecordVersion(Integer recordVersion) {
        this.recordVersion = recordVersion;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode == null ? null : createUserCode.trim();
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getCreateTimeZone() {
        return createTimeZone;
    }

    public void setCreateTimeZone(String createTimeZone) {
        this.createTimeZone = createTimeZone == null ? null : createTimeZone.trim();
    }

    public String getUpdateUserCode() {
        return updateUserCode;
    }

    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode == null ? null : updateUserCode.trim();
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getUpdateTimeZone() {
        return updateTimeZone;
    }

    public void setUpdateTimeZone(String updateTimeZone) {
        this.updateTimeZone = updateTimeZone == null ? null : updateTimeZone.trim();
    }
}