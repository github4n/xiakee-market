package com.xiakee.ecdao.order;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.ecgoods.EcGoods;

public class EcGoodsSqlProvider {

    public String insertSelective(EcGoods record) {
        BEGIN();
        INSERT_INTO("sdb_b2c_goods");
        
        if (record.getGoodsId() != null) {
            VALUES("goods_id", "#{goodsId,jdbcType=BIGINT}");
        }
        
        if (record.getBn() != null) {
            VALUES("bn", "#{bn,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            VALUES("price", "#{price,jdbcType=DECIMAL}");
        }
        
        if (record.getTypeId() != null) {
            VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getCatId() != null) {
            VALUES("cat_id", "#{catId,jdbcType=INTEGER}");
        }
        
        if (record.getBrandId() != null) {
            VALUES("brand_id", "#{brandId,jdbcType=INTEGER}");
        }
        
        if (record.getMarketable() != null) {
            VALUES("marketable", "#{marketable,jdbcType=CHAR}");
        }
        
        if (record.getStore() != null) {
            VALUES("store", "#{store,jdbcType=INTEGER}");
        }
        
        if (record.getNotifyNum() != null) {
            VALUES("notify_num", "#{notifyNum,jdbcType=INTEGER}");
        }
        
        if (record.getUptime() != null) {
            VALUES("uptime", "#{uptime,jdbcType=INTEGER}");
        }
        
        if (record.getDowntime() != null) {
            VALUES("downtime", "#{downtime,jdbcType=INTEGER}");
        }
        
        if (record.getLastModify() != null) {
            VALUES("last_modify", "#{lastModify,jdbcType=INTEGER}");
        }
        
        if (record.getpOrder() != null) {
            VALUES("p_order", "#{pOrder,jdbcType=INTEGER}");
        }
        
        if (record.getdOrder() != null) {
            VALUES("d_order", "#{dOrder,jdbcType=INTEGER}");
        }
        
        if (record.getScore() != null) {
            VALUES("score", "#{score,jdbcType=INTEGER}");
        }
        
        if (record.getCost() != null) {
            VALUES("cost", "#{cost,jdbcType=DECIMAL}");
        }
        
        if (record.getMktprice() != null) {
            VALUES("mktprice", "#{mktprice,jdbcType=DECIMAL}");
        }
        
        if (record.getWeight() != null) {
            VALUES("weight", "#{weight,jdbcType=DECIMAL}");
        }
        
        if (record.getUnit() != null) {
            VALUES("unit", "#{unit,jdbcType=VARCHAR}");
        }
        
        if (record.getBrief() != null) {
            VALUES("brief", "#{brief,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsType() != null) {
            VALUES("goods_type", "#{goodsType,jdbcType=CHAR}");
        }
        
        if (record.getImageDefaultId() != null) {
            VALUES("image_default_id", "#{imageDefaultId,jdbcType=VARCHAR}");
        }
        
        if (record.getUdfimg() != null) {
            VALUES("udfimg", "#{udfimg,jdbcType=CHAR}");
        }
        
        if (record.getThumbnailPic() != null) {
            VALUES("thumbnail_pic", "#{thumbnailPic,jdbcType=VARCHAR}");
        }
        
        if (record.getSmallPic() != null) {
            VALUES("small_pic", "#{smallPic,jdbcType=VARCHAR}");
        }
        
        if (record.getBigPic() != null) {
            VALUES("big_pic", "#{bigPic,jdbcType=VARCHAR}");
        }
        
        if (record.getStorePlace() != null) {
            VALUES("store_place", "#{storePlace,jdbcType=VARCHAR}");
        }
        
        if (record.getMinBuy() != null) {
            VALUES("min_buy", "#{minBuy,jdbcType=INTEGER}");
        }
        
        if (record.getPackageScale() != null) {
            VALUES("package_scale", "#{packageScale,jdbcType=DECIMAL}");
        }
        
        if (record.getPackageUnit() != null) {
            VALUES("package_unit", "#{packageUnit,jdbcType=VARCHAR}");
        }
        
        if (record.getPackageUse() != null) {
            VALUES("package_use", "#{packageUse,jdbcType=CHAR}");
        }
        
        if (record.getScoreSetting() != null) {
            VALUES("score_setting", "#{scoreSetting,jdbcType=CHAR}");
        }
        
        if (record.getStorePrompt() != null) {
            VALUES("store_prompt", "#{storePrompt,jdbcType=INTEGER}");
        }
        
        if (record.getNostoreSell() != null) {
            VALUES("nostore_sell", "#{nostoreSell,jdbcType=CHAR}");
        }
        
        if (record.getDisabled() != null) {
            VALUES("disabled", "#{disabled,jdbcType=CHAR}");
        }
        
        if (record.getRankCount() != null) {
            VALUES("rank_count", "#{rankCount,jdbcType=INTEGER}");
        }
        
        if (record.getCommentsCount() != null) {
            VALUES("comments_count", "#{commentsCount,jdbcType=INTEGER}");
        }
        
        if (record.getViewWCount() != null) {
            VALUES("view_w_count", "#{viewWCount,jdbcType=INTEGER}");
        }
        
        if (record.getViewCount() != null) {
            VALUES("view_count", "#{viewCount,jdbcType=INTEGER}");
        }
        
        if (record.getBuyCount() != null) {
            VALUES("buy_count", "#{buyCount,jdbcType=INTEGER}");
        }
        
        if (record.getBuyWCount() != null) {
            VALUES("buy_w_count", "#{buyWCount,jdbcType=INTEGER}");
        }
        
        if (record.getP1() != null) {
            VALUES("p_1", "#{p1,jdbcType=INTEGER}");
        }
        
        if (record.getP2() != null) {
            VALUES("p_2", "#{p2,jdbcType=INTEGER}");
        }
        
        if (record.getP3() != null) {
            VALUES("p_3", "#{p3,jdbcType=INTEGER}");
        }
        
        if (record.getP4() != null) {
            VALUES("p_4", "#{p4,jdbcType=INTEGER}");
        }
        
        if (record.getP5() != null) {
            VALUES("p_5", "#{p5,jdbcType=INTEGER}");
        }
        
        if (record.getP6() != null) {
            VALUES("p_6", "#{p6,jdbcType=INTEGER}");
        }
        
        if (record.getP7() != null) {
            VALUES("p_7", "#{p7,jdbcType=INTEGER}");
        }
        
        if (record.getP8() != null) {
            VALUES("p_8", "#{p8,jdbcType=INTEGER}");
        }
        
        if (record.getP9() != null) {
            VALUES("p_9", "#{p9,jdbcType=INTEGER}");
        }
        
        if (record.getP10() != null) {
            VALUES("p_10", "#{p10,jdbcType=INTEGER}");
        }
        
        if (record.getP11() != null) {
            VALUES("p_11", "#{p11,jdbcType=INTEGER}");
        }
        
        if (record.getP12() != null) {
            VALUES("p_12", "#{p12,jdbcType=INTEGER}");
        }
        
        if (record.getP13() != null) {
            VALUES("p_13", "#{p13,jdbcType=INTEGER}");
        }
        
        if (record.getP14() != null) {
            VALUES("p_14", "#{p14,jdbcType=INTEGER}");
        }
        
        if (record.getP15() != null) {
            VALUES("p_15", "#{p15,jdbcType=INTEGER}");
        }
        
        if (record.getP16() != null) {
            VALUES("p_16", "#{p16,jdbcType=INTEGER}");
        }
        
        if (record.getP17() != null) {
            VALUES("p_17", "#{p17,jdbcType=INTEGER}");
        }
        
        if (record.getP18() != null) {
            VALUES("p_18", "#{p18,jdbcType=INTEGER}");
        }
        
        if (record.getP19() != null) {
            VALUES("p_19", "#{p19,jdbcType=INTEGER}");
        }
        
        if (record.getP20() != null) {
            VALUES("p_20", "#{p20,jdbcType=INTEGER}");
        }
        
        if (record.getP21() != null) {
            VALUES("p_21", "#{p21,jdbcType=VARCHAR}");
        }
        
        if (record.getP22() != null) {
            VALUES("p_22", "#{p22,jdbcType=VARCHAR}");
        }
        
        if (record.getP23() != null) {
            VALUES("p_23", "#{p23,jdbcType=VARCHAR}");
        }
        
        if (record.getP24() != null) {
            VALUES("p_24", "#{p24,jdbcType=VARCHAR}");
        }
        
        if (record.getP25() != null) {
            VALUES("p_25", "#{p25,jdbcType=VARCHAR}");
        }
        
        if (record.getP26() != null) {
            VALUES("p_26", "#{p26,jdbcType=VARCHAR}");
        }
        
        if (record.getP27() != null) {
            VALUES("p_27", "#{p27,jdbcType=VARCHAR}");
        }
        
        if (record.getP28() != null) {
            VALUES("p_28", "#{p28,jdbcType=VARCHAR}");
        }
        
        if (record.getP29() != null) {
            VALUES("p_29", "#{p29,jdbcType=VARCHAR}");
        }
        
        if (record.getP30() != null) {
            VALUES("p_30", "#{p30,jdbcType=VARCHAR}");
        }
        
        if (record.getP31() != null) {
            VALUES("p_31", "#{p31,jdbcType=VARCHAR}");
        }
        
        if (record.getP32() != null) {
            VALUES("p_32", "#{p32,jdbcType=VARCHAR}");
        }
        
        if (record.getP33() != null) {
            VALUES("p_33", "#{p33,jdbcType=VARCHAR}");
        }
        
        if (record.getP34() != null) {
            VALUES("p_34", "#{p34,jdbcType=VARCHAR}");
        }
        
        if (record.getP35() != null) {
            VALUES("p_35", "#{p35,jdbcType=VARCHAR}");
        }
        
        if (record.getP36() != null) {
            VALUES("p_36", "#{p36,jdbcType=VARCHAR}");
        }
        
        if (record.getP37() != null) {
            VALUES("p_37", "#{p37,jdbcType=VARCHAR}");
        }
        
        if (record.getP38() != null) {
            VALUES("p_38", "#{p38,jdbcType=VARCHAR}");
        }
        
        if (record.getP39() != null) {
            VALUES("p_39", "#{p39,jdbcType=VARCHAR}");
        }
        
        if (record.getP40() != null) {
            VALUES("p_40", "#{p40,jdbcType=VARCHAR}");
        }
        
        if (record.getP41() != null) {
            VALUES("p_41", "#{p41,jdbcType=VARCHAR}");
        }
        
        if (record.getP42() != null) {
            VALUES("p_42", "#{p42,jdbcType=VARCHAR}");
        }
        
        if (record.getP43() != null) {
            VALUES("p_43", "#{p43,jdbcType=VARCHAR}");
        }
        
        if (record.getP44() != null) {
            VALUES("p_44", "#{p44,jdbcType=VARCHAR}");
        }
        
        if (record.getP45() != null) {
            VALUES("p_45", "#{p45,jdbcType=VARCHAR}");
        }
        
        if (record.getP46() != null) {
            VALUES("p_46", "#{p46,jdbcType=VARCHAR}");
        }
        
        if (record.getP47() != null) {
            VALUES("p_47", "#{p47,jdbcType=VARCHAR}");
        }
        
        if (record.getP48() != null) {
            VALUES("p_48", "#{p48,jdbcType=VARCHAR}");
        }
        
        if (record.getP49() != null) {
            VALUES("p_49", "#{p49,jdbcType=VARCHAR}");
        }
        
        if (record.getP50() != null) {
            VALUES("p_50", "#{p50,jdbcType=VARCHAR}");
        }
        
        if (record.getIntro() != null) {
            VALUES("intro", "#{intro,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getGoodsSetting() != null) {
            VALUES("goods_setting", "#{goodsSetting,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getSpecDesc() != null) {
            VALUES("spec_desc", "#{specDesc,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getParams() != null) {
            VALUES("params", "#{params,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getCountStat() != null) {
            VALUES("count_stat", "#{countStat,jdbcType=LONGVARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(EcGoods record) {
        BEGIN();
        UPDATE("sdb_b2c_goods");
        
        if (record.getBn() != null) {
            SET("bn = #{bn,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            SET("price = #{price,jdbcType=DECIMAL}");
        }
        
        if (record.getTypeId() != null) {
            SET("type_id = #{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getCatId() != null) {
            SET("cat_id = #{catId,jdbcType=INTEGER}");
        }
        
        if (record.getBrandId() != null) {
            SET("brand_id = #{brandId,jdbcType=INTEGER}");
        }
        
        if (record.getMarketable() != null) {
            SET("marketable = #{marketable,jdbcType=CHAR}");
        }
        
        if (record.getStore() != null) {
            SET("store = #{store,jdbcType=INTEGER}");
        }
        
        if (record.getNotifyNum() != null) {
            SET("notify_num = #{notifyNum,jdbcType=INTEGER}");
        }
        
        if (record.getUptime() != null) {
            SET("uptime = #{uptime,jdbcType=INTEGER}");
        }
        
        if (record.getDowntime() != null) {
            SET("downtime = #{downtime,jdbcType=INTEGER}");
        }
        
        if (record.getLastModify() != null) {
            SET("last_modify = #{lastModify,jdbcType=INTEGER}");
        }
        
        if (record.getpOrder() != null) {
            SET("p_order = #{pOrder,jdbcType=INTEGER}");
        }
        
        if (record.getdOrder() != null) {
            SET("d_order = #{dOrder,jdbcType=INTEGER}");
        }
        
        if (record.getScore() != null) {
            SET("score = #{score,jdbcType=INTEGER}");
        }
        
        if (record.getCost() != null) {
            SET("cost = #{cost,jdbcType=DECIMAL}");
        }
        
        if (record.getMktprice() != null) {
            SET("mktprice = #{mktprice,jdbcType=DECIMAL}");
        }
        
        if (record.getWeight() != null) {
            SET("weight = #{weight,jdbcType=DECIMAL}");
        }
        
        if (record.getUnit() != null) {
            SET("unit = #{unit,jdbcType=VARCHAR}");
        }
        
        if (record.getBrief() != null) {
            SET("brief = #{brief,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsType() != null) {
            SET("goods_type = #{goodsType,jdbcType=CHAR}");
        }
        
        if (record.getImageDefaultId() != null) {
            SET("image_default_id = #{imageDefaultId,jdbcType=VARCHAR}");
        }
        
        if (record.getUdfimg() != null) {
            SET("udfimg = #{udfimg,jdbcType=CHAR}");
        }
        
        if (record.getThumbnailPic() != null) {
            SET("thumbnail_pic = #{thumbnailPic,jdbcType=VARCHAR}");
        }
        
        if (record.getSmallPic() != null) {
            SET("small_pic = #{smallPic,jdbcType=VARCHAR}");
        }
        
        if (record.getBigPic() != null) {
            SET("big_pic = #{bigPic,jdbcType=VARCHAR}");
        }
        
        if (record.getStorePlace() != null) {
            SET("store_place = #{storePlace,jdbcType=VARCHAR}");
        }
        
        if (record.getMinBuy() != null) {
            SET("min_buy = #{minBuy,jdbcType=INTEGER}");
        }
        
        if (record.getPackageScale() != null) {
            SET("package_scale = #{packageScale,jdbcType=DECIMAL}");
        }
        
        if (record.getPackageUnit() != null) {
            SET("package_unit = #{packageUnit,jdbcType=VARCHAR}");
        }
        
        if (record.getPackageUse() != null) {
            SET("package_use = #{packageUse,jdbcType=CHAR}");
        }
        
        if (record.getScoreSetting() != null) {
            SET("score_setting = #{scoreSetting,jdbcType=CHAR}");
        }
        
        if (record.getStorePrompt() != null) {
            SET("store_prompt = #{storePrompt,jdbcType=INTEGER}");
        }
        
        if (record.getNostoreSell() != null) {
            SET("nostore_sell = #{nostoreSell,jdbcType=CHAR}");
        }
        
        if (record.getDisabled() != null) {
            SET("disabled = #{disabled,jdbcType=CHAR}");
        }
        
        if (record.getRankCount() != null) {
            SET("rank_count = #{rankCount,jdbcType=INTEGER}");
        }
        
        if (record.getCommentsCount() != null) {
            SET("comments_count = #{commentsCount,jdbcType=INTEGER}");
        }
        
        if (record.getViewWCount() != null) {
            SET("view_w_count = #{viewWCount,jdbcType=INTEGER}");
        }
        
        if (record.getViewCount() != null) {
            SET("view_count = #{viewCount,jdbcType=INTEGER}");
        }
        
        if (record.getBuyCount() != null) {
            SET("buy_count = #{buyCount,jdbcType=INTEGER}");
        }
        
        if (record.getBuyWCount() != null) {
            SET("buy_w_count = #{buyWCount,jdbcType=INTEGER}");
        }
        
        if (record.getP1() != null) {
            SET("p_1 = #{p1,jdbcType=INTEGER}");
        }
        
        if (record.getP2() != null) {
            SET("p_2 = #{p2,jdbcType=INTEGER}");
        }
        
        if (record.getP3() != null) {
            SET("p_3 = #{p3,jdbcType=INTEGER}");
        }
        
        if (record.getP4() != null) {
            SET("p_4 = #{p4,jdbcType=INTEGER}");
        }
        
        if (record.getP5() != null) {
            SET("p_5 = #{p5,jdbcType=INTEGER}");
        }
        
        if (record.getP6() != null) {
            SET("p_6 = #{p6,jdbcType=INTEGER}");
        }
        
        if (record.getP7() != null) {
            SET("p_7 = #{p7,jdbcType=INTEGER}");
        }
        
        if (record.getP8() != null) {
            SET("p_8 = #{p8,jdbcType=INTEGER}");
        }
        
        if (record.getP9() != null) {
            SET("p_9 = #{p9,jdbcType=INTEGER}");
        }
        
        if (record.getP10() != null) {
            SET("p_10 = #{p10,jdbcType=INTEGER}");
        }
        
        if (record.getP11() != null) {
            SET("p_11 = #{p11,jdbcType=INTEGER}");
        }
        
        if (record.getP12() != null) {
            SET("p_12 = #{p12,jdbcType=INTEGER}");
        }
        
        if (record.getP13() != null) {
            SET("p_13 = #{p13,jdbcType=INTEGER}");
        }
        
        if (record.getP14() != null) {
            SET("p_14 = #{p14,jdbcType=INTEGER}");
        }
        
        if (record.getP15() != null) {
            SET("p_15 = #{p15,jdbcType=INTEGER}");
        }
        
        if (record.getP16() != null) {
            SET("p_16 = #{p16,jdbcType=INTEGER}");
        }
        
        if (record.getP17() != null) {
            SET("p_17 = #{p17,jdbcType=INTEGER}");
        }
        
        if (record.getP18() != null) {
            SET("p_18 = #{p18,jdbcType=INTEGER}");
        }
        
        if (record.getP19() != null) {
            SET("p_19 = #{p19,jdbcType=INTEGER}");
        }
        
        if (record.getP20() != null) {
            SET("p_20 = #{p20,jdbcType=INTEGER}");
        }
        
        if (record.getP21() != null) {
            SET("p_21 = #{p21,jdbcType=VARCHAR}");
        }
        
        if (record.getP22() != null) {
            SET("p_22 = #{p22,jdbcType=VARCHAR}");
        }
        
        if (record.getP23() != null) {
            SET("p_23 = #{p23,jdbcType=VARCHAR}");
        }
        
        if (record.getP24() != null) {
            SET("p_24 = #{p24,jdbcType=VARCHAR}");
        }
        
        if (record.getP25() != null) {
            SET("p_25 = #{p25,jdbcType=VARCHAR}");
        }
        
        if (record.getP26() != null) {
            SET("p_26 = #{p26,jdbcType=VARCHAR}");
        }
        
        if (record.getP27() != null) {
            SET("p_27 = #{p27,jdbcType=VARCHAR}");
        }
        
        if (record.getP28() != null) {
            SET("p_28 = #{p28,jdbcType=VARCHAR}");
        }
        
        if (record.getP29() != null) {
            SET("p_29 = #{p29,jdbcType=VARCHAR}");
        }
        
        if (record.getP30() != null) {
            SET("p_30 = #{p30,jdbcType=VARCHAR}");
        }
        
        if (record.getP31() != null) {
            SET("p_31 = #{p31,jdbcType=VARCHAR}");
        }
        
        if (record.getP32() != null) {
            SET("p_32 = #{p32,jdbcType=VARCHAR}");
        }
        
        if (record.getP33() != null) {
            SET("p_33 = #{p33,jdbcType=VARCHAR}");
        }
        
        if (record.getP34() != null) {
            SET("p_34 = #{p34,jdbcType=VARCHAR}");
        }
        
        if (record.getP35() != null) {
            SET("p_35 = #{p35,jdbcType=VARCHAR}");
        }
        
        if (record.getP36() != null) {
            SET("p_36 = #{p36,jdbcType=VARCHAR}");
        }
        
        if (record.getP37() != null) {
            SET("p_37 = #{p37,jdbcType=VARCHAR}");
        }
        
        if (record.getP38() != null) {
            SET("p_38 = #{p38,jdbcType=VARCHAR}");
        }
        
        if (record.getP39() != null) {
            SET("p_39 = #{p39,jdbcType=VARCHAR}");
        }
        
        if (record.getP40() != null) {
            SET("p_40 = #{p40,jdbcType=VARCHAR}");
        }
        
        if (record.getP41() != null) {
            SET("p_41 = #{p41,jdbcType=VARCHAR}");
        }
        
        if (record.getP42() != null) {
            SET("p_42 = #{p42,jdbcType=VARCHAR}");
        }
        
        if (record.getP43() != null) {
            SET("p_43 = #{p43,jdbcType=VARCHAR}");
        }
        
        if (record.getP44() != null) {
            SET("p_44 = #{p44,jdbcType=VARCHAR}");
        }
        
        if (record.getP45() != null) {
            SET("p_45 = #{p45,jdbcType=VARCHAR}");
        }
        
        if (record.getP46() != null) {
            SET("p_46 = #{p46,jdbcType=VARCHAR}");
        }
        
        if (record.getP47() != null) {
            SET("p_47 = #{p47,jdbcType=VARCHAR}");
        }
        
        if (record.getP48() != null) {
            SET("p_48 = #{p48,jdbcType=VARCHAR}");
        }
        
        if (record.getP49() != null) {
            SET("p_49 = #{p49,jdbcType=VARCHAR}");
        }
        
        if (record.getP50() != null) {
            SET("p_50 = #{p50,jdbcType=VARCHAR}");
        }
        
        if (record.getIntro() != null) {
            SET("intro = #{intro,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getGoodsSetting() != null) {
            SET("goods_setting = #{goodsSetting,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getSpecDesc() != null) {
            SET("spec_desc = #{specDesc,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getParams() != null) {
            SET("params = #{params,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getCountStat() != null) {
            SET("count_stat = #{countStat,jdbcType=LONGVARCHAR}");
        }
        
        WHERE("goods_id = #{goodsId,jdbcType=BIGINT}");
        
        return SQL();
    }
}