package com.xiakee.domain.ecgoods;

public class EcGoodsTypeSpec {
    /** 规格ID */
    private Integer specId;

    /** 类型ID */
    private Integer typeId;

    /** 渐进式搜索时的样式 */
    private String specStyle;

    private Integer ordernum;

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getSpecStyle() {
        return specStyle;
    }

    public void setSpecStyle(String specStyle) {
        this.specStyle = specStyle == null ? null : specStyle.trim();
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }
}