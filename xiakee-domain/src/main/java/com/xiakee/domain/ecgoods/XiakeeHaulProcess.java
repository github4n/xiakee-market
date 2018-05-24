package com.xiakee.domain.ecgoods;

public class XiakeeHaulProcess {
    /** 运单进度ID */
    private Integer processId;

    /** 对象ID */
    private Long relId;

    /** 订单号 */
    private Long orderId;

    /** 操作员ID */
    private Integer opId;

    /** 操作人名称 */
    private String opName;

    /** 操作时间 */
    private Integer alttime;

    /** 操作人员姓名 */
    private String billType;

    /** 日志记录操作的行为 */
    private String behavior;

    /** 日志结果 */
    private String result;

    /** 操作内容 */
    private String logText;

    /** 序列化数据 */
    private String addon;

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public Integer getAlttime() {
        return alttime;
    }

    public void setAlttime(Integer alttime) {
        this.alttime = alttime;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType == null ? null : billType.trim();
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior == null ? null : behavior.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText == null ? null : logText.trim();
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon == null ? null : addon.trim();
    }
}