package com.xiakee.domain.ecgoods;

public class EcGoodsSpecIndex {
    /** 规格值ID */
    private Integer specValueId;

    /** 货品ID */
    private Integer productId;

    /** 商品类型ID */
    private Integer typeId;

    /** 规格ID */
    private Integer specId;

    /** 商品ID */
    private Long goodsId;

    /** 更新时间 */
    private Integer lastModify;

    public Integer getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Integer specValueId) {
        this.specValueId = specValueId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getLastModify() {
        return lastModify;
    }

    public void setLastModify(Integer lastModify) {
        this.lastModify = lastModify;
    }
}