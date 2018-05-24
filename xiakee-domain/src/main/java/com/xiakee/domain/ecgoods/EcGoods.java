package com.xiakee.domain.ecgoods;

import java.math.BigDecimal;

public class EcGoods {
    /** 商品ID */
    private Long goodsId;

    /** 商品编号 */
    private String bn;

    /** 商品名称 */
    private String name;

    /** 销售价 */
    private BigDecimal price;

    /** 类型 */
    private Integer typeId;

    /** 分类 */
    private Integer catId;

    /** 品牌 */
    private Integer brandId;

    /** 上架 */
    private String marketable;

    /** 库存 */
    private Integer store;

    /** 缺货登记 */
    private Integer notifyNum;

    /** 上架时间 */
    private Integer uptime;

    /** 下架时间 */
    private Integer downtime;

    /** 更新时间 */
    private Integer lastModify;

    /** 排序 */
    private Integer pOrder;

    /** 动态排序 */
    private Integer dOrder;

    /** 积分 */
    private Integer score;

    /** 成本价 */
    private BigDecimal cost;

    /** 市场价 */
    private BigDecimal mktprice;

    /** 重量 */
    private BigDecimal weight;

    /** 单位 */
    private String unit;

    /** 商品简介 */
    private String brief;

    /** 销售类型 */
    private String goodsType;

    /** 默认图片 */
    private String imageDefaultId;

    /** 是否用户自定义图 */
    private String udfimg;

    /** 缩略图 */
    private String thumbnailPic;

    /** 小图 */
    private String smallPic;

    /** 大图 */
    private String bigPic;

    /** 库位 */
    private String storePlace;

    /** 起定量 */
    private Integer minBuy;

    /** 打包比例 */
    private BigDecimal packageScale;

    /** 打包单位 */
    private String packageUnit;

    /** 是否开启打包 */
    private String packageUse;

    private String scoreSetting;

    /** 库存提示规则 */
    private Integer storePrompt;

    /** 是否开启无库存销售 */
    private String nostoreSell;

    private String disabled;

    /** google page rank count */
    private Integer rankCount;

    /** 评论次数 */
    private Integer commentsCount;

    /** 周浏览次数 */
    private Integer viewWCount;

    /** 浏览次数 */
    private Integer viewCount;

    /** 购买次数 */
    private Integer buyCount;

    /** 购买次数 */
    private Integer buyWCount;

    private Integer p1;

    private Integer p2;

    private Integer p3;

    private Integer p4;

    private Integer p5;

    private Integer p6;

    private Integer p7;

    private Integer p8;

    private Integer p9;

    private Integer p10;

    private Integer p11;

    private Integer p12;

    private Integer p13;

    private Integer p14;

    private Integer p15;

    private Integer p16;

    private Integer p17;

    private Integer p18;

    private Integer p19;

    private Integer p20;

    private String p21;

    private String p22;

    private String p23;

    private String p24;

    private String p25;

    private String p26;

    private String p27;

    private String p28;

    private String p29;

    private String p30;

    private String p31;

    private String p32;

    private String p33;

    private String p34;

    private String p35;

    private String p36;

    private String p37;

    private String p38;

    private String p39;

    private String p40;

    private String p41;

    private String p42;

    private String p43;

    private String p44;

    private String p45;

    private String p46;

    private String p47;

    private String p48;

    private String p49;

    private String p50;

    /** 详细介绍 */
    private String intro;

    /** 商品设置 */
    private String goodsSetting;

    /** 货品规格序列化 */
    private String specDesc;

    /** 商品规格序列化 */
    private String params;

    /** 统计数据序列化 */
    private String countStat;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getBn() {
        return bn;
    }

    public void setBn(String bn) {
        this.bn = bn == null ? null : bn.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getMarketable() {
        return marketable;
    }

    public void setMarketable(String marketable) {
        this.marketable = marketable == null ? null : marketable.trim();
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getNotifyNum() {
        return notifyNum;
    }

    public void setNotifyNum(Integer notifyNum) {
        this.notifyNum = notifyNum;
    }

    public Integer getUptime() {
        return uptime;
    }

    public void setUptime(Integer uptime) {
        this.uptime = uptime;
    }

    public Integer getDowntime() {
        return downtime;
    }

    public void setDowntime(Integer downtime) {
        this.downtime = downtime;
    }

    public Integer getLastModify() {
        return lastModify;
    }

    public void setLastModify(Integer lastModify) {
        this.lastModify = lastModify;
    }

    public Integer getpOrder() {
        return pOrder;
    }

    public void setpOrder(Integer pOrder) {
        this.pOrder = pOrder;
    }

    public Integer getdOrder() {
        return dOrder;
    }

    public void setdOrder(Integer dOrder) {
        this.dOrder = dOrder;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getMktprice() {
        return mktprice;
    }

    public void setMktprice(BigDecimal mktprice) {
        this.mktprice = mktprice;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public String getImageDefaultId() {
        return imageDefaultId;
    }

    public void setImageDefaultId(String imageDefaultId) {
        this.imageDefaultId = imageDefaultId == null ? null : imageDefaultId.trim();
    }

    public String getUdfimg() {
        return udfimg;
    }

    public void setUdfimg(String udfimg) {
        this.udfimg = udfimg == null ? null : udfimg.trim();
    }

    public String getThumbnailPic() {
        return thumbnailPic;
    }

    public void setThumbnailPic(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic == null ? null : thumbnailPic.trim();
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic == null ? null : smallPic.trim();
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic == null ? null : bigPic.trim();
    }

    public String getStorePlace() {
        return storePlace;
    }

    public void setStorePlace(String storePlace) {
        this.storePlace = storePlace == null ? null : storePlace.trim();
    }

    public Integer getMinBuy() {
        return minBuy;
    }

    public void setMinBuy(Integer minBuy) {
        this.minBuy = minBuy;
    }

    public BigDecimal getPackageScale() {
        return packageScale;
    }

    public void setPackageScale(BigDecimal packageScale) {
        this.packageScale = packageScale;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit == null ? null : packageUnit.trim();
    }

    public String getPackageUse() {
        return packageUse;
    }

    public void setPackageUse(String packageUse) {
        this.packageUse = packageUse == null ? null : packageUse.trim();
    }

    public String getScoreSetting() {
        return scoreSetting;
    }

    public void setScoreSetting(String scoreSetting) {
        this.scoreSetting = scoreSetting == null ? null : scoreSetting.trim();
    }

    public Integer getStorePrompt() {
        return storePrompt;
    }

    public void setStorePrompt(Integer storePrompt) {
        this.storePrompt = storePrompt;
    }

    public String getNostoreSell() {
        return nostoreSell;
    }

    public void setNostoreSell(String nostoreSell) {
        this.nostoreSell = nostoreSell == null ? null : nostoreSell.trim();
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled == null ? null : disabled.trim();
    }

    public Integer getRankCount() {
        return rankCount;
    }

    public void setRankCount(Integer rankCount) {
        this.rankCount = rankCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getViewWCount() {
        return viewWCount;
    }

    public void setViewWCount(Integer viewWCount) {
        this.viewWCount = viewWCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getBuyWCount() {
        return buyWCount;
    }

    public void setBuyWCount(Integer buyWCount) {
        this.buyWCount = buyWCount;
    }

    public Integer getP1() {
        return p1;
    }

    public void setP1(Integer p1) {
        this.p1 = p1;
    }

    public Integer getP2() {
        return p2;
    }

    public void setP2(Integer p2) {
        this.p2 = p2;
    }

    public Integer getP3() {
        return p3;
    }

    public void setP3(Integer p3) {
        this.p3 = p3;
    }

    public Integer getP4() {
        return p4;
    }

    public void setP4(Integer p4) {
        this.p4 = p4;
    }

    public Integer getP5() {
        return p5;
    }

    public void setP5(Integer p5) {
        this.p5 = p5;
    }

    public Integer getP6() {
        return p6;
    }

    public void setP6(Integer p6) {
        this.p6 = p6;
    }

    public Integer getP7() {
        return p7;
    }

    public void setP7(Integer p7) {
        this.p7 = p7;
    }

    public Integer getP8() {
        return p8;
    }

    public void setP8(Integer p8) {
        this.p8 = p8;
    }

    public Integer getP9() {
        return p9;
    }

    public void setP9(Integer p9) {
        this.p9 = p9;
    }

    public Integer getP10() {
        return p10;
    }

    public void setP10(Integer p10) {
        this.p10 = p10;
    }

    public Integer getP11() {
        return p11;
    }

    public void setP11(Integer p11) {
        this.p11 = p11;
    }

    public Integer getP12() {
        return p12;
    }

    public void setP12(Integer p12) {
        this.p12 = p12;
    }

    public Integer getP13() {
        return p13;
    }

    public void setP13(Integer p13) {
        this.p13 = p13;
    }

    public Integer getP14() {
        return p14;
    }

    public void setP14(Integer p14) {
        this.p14 = p14;
    }

    public Integer getP15() {
        return p15;
    }

    public void setP15(Integer p15) {
        this.p15 = p15;
    }

    public Integer getP16() {
        return p16;
    }

    public void setP16(Integer p16) {
        this.p16 = p16;
    }

    public Integer getP17() {
        return p17;
    }

    public void setP17(Integer p17) {
        this.p17 = p17;
    }

    public Integer getP18() {
        return p18;
    }

    public void setP18(Integer p18) {
        this.p18 = p18;
    }

    public Integer getP19() {
        return p19;
    }

    public void setP19(Integer p19) {
        this.p19 = p19;
    }

    public Integer getP20() {
        return p20;
    }

    public void setP20(Integer p20) {
        this.p20 = p20;
    }

    public String getP21() {
        return p21;
    }

    public void setP21(String p21) {
        this.p21 = p21 == null ? null : p21.trim();
    }

    public String getP22() {
        return p22;
    }

    public void setP22(String p22) {
        this.p22 = p22 == null ? null : p22.trim();
    }

    public String getP23() {
        return p23;
    }

    public void setP23(String p23) {
        this.p23 = p23 == null ? null : p23.trim();
    }

    public String getP24() {
        return p24;
    }

    public void setP24(String p24) {
        this.p24 = p24 == null ? null : p24.trim();
    }

    public String getP25() {
        return p25;
    }

    public void setP25(String p25) {
        this.p25 = p25 == null ? null : p25.trim();
    }

    public String getP26() {
        return p26;
    }

    public void setP26(String p26) {
        this.p26 = p26 == null ? null : p26.trim();
    }

    public String getP27() {
        return p27;
    }

    public void setP27(String p27) {
        this.p27 = p27 == null ? null : p27.trim();
    }

    public String getP28() {
        return p28;
    }

    public void setP28(String p28) {
        this.p28 = p28 == null ? null : p28.trim();
    }

    public String getP29() {
        return p29;
    }

    public void setP29(String p29) {
        this.p29 = p29 == null ? null : p29.trim();
    }

    public String getP30() {
        return p30;
    }

    public void setP30(String p30) {
        this.p30 = p30 == null ? null : p30.trim();
    }

    public String getP31() {
        return p31;
    }

    public void setP31(String p31) {
        this.p31 = p31 == null ? null : p31.trim();
    }

    public String getP32() {
        return p32;
    }

    public void setP32(String p32) {
        this.p32 = p32 == null ? null : p32.trim();
    }

    public String getP33() {
        return p33;
    }

    public void setP33(String p33) {
        this.p33 = p33 == null ? null : p33.trim();
    }

    public String getP34() {
        return p34;
    }

    public void setP34(String p34) {
        this.p34 = p34 == null ? null : p34.trim();
    }

    public String getP35() {
        return p35;
    }

    public void setP35(String p35) {
        this.p35 = p35 == null ? null : p35.trim();
    }

    public String getP36() {
        return p36;
    }

    public void setP36(String p36) {
        this.p36 = p36 == null ? null : p36.trim();
    }

    public String getP37() {
        return p37;
    }

    public void setP37(String p37) {
        this.p37 = p37 == null ? null : p37.trim();
    }

    public String getP38() {
        return p38;
    }

    public void setP38(String p38) {
        this.p38 = p38 == null ? null : p38.trim();
    }

    public String getP39() {
        return p39;
    }

    public void setP39(String p39) {
        this.p39 = p39 == null ? null : p39.trim();
    }

    public String getP40() {
        return p40;
    }

    public void setP40(String p40) {
        this.p40 = p40 == null ? null : p40.trim();
    }

    public String getP41() {
        return p41;
    }

    public void setP41(String p41) {
        this.p41 = p41 == null ? null : p41.trim();
    }

    public String getP42() {
        return p42;
    }

    public void setP42(String p42) {
        this.p42 = p42 == null ? null : p42.trim();
    }

    public String getP43() {
        return p43;
    }

    public void setP43(String p43) {
        this.p43 = p43 == null ? null : p43.trim();
    }

    public String getP44() {
        return p44;
    }

    public void setP44(String p44) {
        this.p44 = p44 == null ? null : p44.trim();
    }

    public String getP45() {
        return p45;
    }

    public void setP45(String p45) {
        this.p45 = p45 == null ? null : p45.trim();
    }

    public String getP46() {
        return p46;
    }

    public void setP46(String p46) {
        this.p46 = p46 == null ? null : p46.trim();
    }

    public String getP47() {
        return p47;
    }

    public void setP47(String p47) {
        this.p47 = p47 == null ? null : p47.trim();
    }

    public String getP48() {
        return p48;
    }

    public void setP48(String p48) {
        this.p48 = p48 == null ? null : p48.trim();
    }

    public String getP49() {
        return p49;
    }

    public void setP49(String p49) {
        this.p49 = p49 == null ? null : p49.trim();
    }

    public String getP50() {
        return p50;
    }

    public void setP50(String p50) {
        this.p50 = p50 == null ? null : p50.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getGoodsSetting() {
        return goodsSetting;
    }

    public void setGoodsSetting(String goodsSetting) {
        this.goodsSetting = goodsSetting == null ? null : goodsSetting.trim();
    }

    public String getSpecDesc() {
        return specDesc;
    }

    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc == null ? null : specDesc.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getCountStat() {
        return countStat;
    }

    public void setCountStat(String countStat) {
        this.countStat = countStat == null ? null : countStat.trim();
    }
}