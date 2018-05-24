package com.xiakee.ecdao.order;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.ecgoods.EcProducts;

public class EcProductsSqlProvider {

    public String insertSelective(EcProducts record) {
        BEGIN();
        INSERT_INTO("sdb_b2c_products");
        
        if (record.getProductId() != null) {
            VALUES("product_id", "#{productId,jdbcType=INTEGER}");
        }
        
        if (record.getGoodsId() != null) {
            VALUES("goods_id", "#{goodsId,jdbcType=BIGINT}");
        }
        
        if (record.getBarcode() != null) {
            VALUES("barcode", "#{barcode,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getBn() != null) {
            VALUES("bn", "#{bn,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            VALUES("price", "#{price,jdbcType=DECIMAL}");
        }
        
        if (record.getCost() != null) {
            VALUES("cost", "#{cost,jdbcType=DECIMAL}");
        }
        
        if (record.getMktprice() != null) {
            VALUES("mktprice", "#{mktprice,jdbcType=DECIMAL}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getWeight() != null) {
            VALUES("weight", "#{weight,jdbcType=DECIMAL}");
        }
        
        if (record.getUnit() != null) {
            VALUES("unit", "#{unit,jdbcType=VARCHAR}");
        }
        
        if (record.getStore() != null) {
            VALUES("store", "#{store,jdbcType=INTEGER}");
        }
        
        if (record.getStorePlace() != null) {
            VALUES("store_place", "#{storePlace,jdbcType=VARCHAR}");
        }
        
        if (record.getFreez() != null) {
            VALUES("freez", "#{freez,jdbcType=INTEGER}");
        }
        
        if (record.getGoodsType() != null) {
            VALUES("goods_type", "#{goodsType,jdbcType=CHAR}");
        }
        
        if (record.getIsDefault() != null) {
            VALUES("is_default", "#{isDefault,jdbcType=CHAR}");
        }
        
        if (record.getQrcodeImageId() != null) {
            VALUES("qrcode_image_id", "#{qrcodeImageId,jdbcType=VARCHAR}");
        }
        
        if (record.getUptime() != null) {
            VALUES("uptime", "#{uptime,jdbcType=INTEGER}");
        }
        
        if (record.getLastModify() != null) {
            VALUES("last_modify", "#{lastModify,jdbcType=INTEGER}");
        }
        
        if (record.getDisabled() != null) {
            VALUES("disabled", "#{disabled,jdbcType=CHAR}");
        }
        
        if (record.getMarketable() != null) {
            VALUES("marketable", "#{marketable,jdbcType=CHAR}");
        }
        
        if (record.getSpecInfo() != null) {
            VALUES("spec_info", "#{specInfo,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getSpecDesc() != null) {
            VALUES("spec_desc", "#{specDesc,jdbcType=LONGVARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(EcProducts record) {
        BEGIN();
        UPDATE("sdb_b2c_products");
        
        if (record.getGoodsId() != null) {
            SET("goods_id = #{goodsId,jdbcType=BIGINT}");
        }
        
        if (record.getBarcode() != null) {
            SET("barcode = #{barcode,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getBn() != null) {
            SET("bn = #{bn,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            SET("price = #{price,jdbcType=DECIMAL}");
        }
        
        if (record.getCost() != null) {
            SET("cost = #{cost,jdbcType=DECIMAL}");
        }
        
        if (record.getMktprice() != null) {
            SET("mktprice = #{mktprice,jdbcType=DECIMAL}");
        }
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getWeight() != null) {
            SET("weight = #{weight,jdbcType=DECIMAL}");
        }
        
        if (record.getUnit() != null) {
            SET("unit = #{unit,jdbcType=VARCHAR}");
        }
        
        if (record.getStore() != null) {
            SET("store = #{store,jdbcType=INTEGER}");
        }
        
        if (record.getStorePlace() != null) {
            SET("store_place = #{storePlace,jdbcType=VARCHAR}");
        }
        
        if (record.getFreez() != null) {
            SET("freez = #{freez,jdbcType=INTEGER}");
        }
        
        if (record.getGoodsType() != null) {
            SET("goods_type = #{goodsType,jdbcType=CHAR}");
        }
        
        if (record.getIsDefault() != null) {
            SET("is_default = #{isDefault,jdbcType=CHAR}");
        }
        
        if (record.getQrcodeImageId() != null) {
            SET("qrcode_image_id = #{qrcodeImageId,jdbcType=VARCHAR}");
        }
        
        if (record.getUptime() != null) {
            SET("uptime = #{uptime,jdbcType=INTEGER}");
        }
        
        if (record.getLastModify() != null) {
            SET("last_modify = #{lastModify,jdbcType=INTEGER}");
        }
        
        if (record.getDisabled() != null) {
            SET("disabled = #{disabled,jdbcType=CHAR}");
        }
        
        if (record.getMarketable() != null) {
            SET("marketable = #{marketable,jdbcType=CHAR}");
        }
        
        if (record.getSpecInfo() != null) {
            SET("spec_info = #{specInfo,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getSpecDesc() != null) {
            SET("spec_desc = #{specDesc,jdbcType=LONGVARCHAR}");
        }
        
        WHERE("product_id = #{productId,jdbcType=INTEGER}");
        
        return SQL();
    }
}