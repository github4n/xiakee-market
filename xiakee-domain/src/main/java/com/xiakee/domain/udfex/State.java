package com.xiakee.domain.udfex;

import java.util.Date;

public class State {
    private Integer cdStateId;

    /** 省，州编码 */
    private String stateCode;

    /** 省，州名 */
    private String stateName;

    /** 省，州中文名 */
    private String stateChineseName;

    /** 省，州英文名 */
    private String stateEnglishName;

    /** 国家代码 */
    private String countryCode;

    /** 备注 */
    private String memo;

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

    public Integer getCdStateId() {
        return cdStateId;
    }

    public void setCdStateId(Integer cdStateId) {
        this.cdStateId = cdStateId;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode == null ? null : stateCode.trim();
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName == null ? null : stateName.trim();
    }

    public String getStateChineseName() {
        return stateChineseName;
    }

    public void setStateChineseName(String stateChineseName) {
        this.stateChineseName = stateChineseName == null ? null : stateChineseName.trim();
    }

    public String getStateEnglishName() {
        return stateEnglishName;
    }

    public void setStateEnglishName(String stateEnglishName) {
        this.stateEnglishName = stateEnglishName == null ? null : stateEnglishName.trim();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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