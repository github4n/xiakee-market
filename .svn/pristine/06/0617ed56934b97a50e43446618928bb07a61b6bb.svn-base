package com.xiakee.ecdao.order;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.ecgoods.EcGoodsTypeSpec;

public class EcGoodsTypeSpecSqlProvider {

    public String insertSelective(EcGoodsTypeSpec record) {
        BEGIN();
        INSERT_INTO("sdb_b2c_goods_type_spec");
        
        if (record.getSpecId() != null) {
            VALUES("spec_id", "#{specId,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getSpecStyle() != null) {
            VALUES("spec_style", "#{specStyle,jdbcType=CHAR}");
        }
        
        if (record.getOrdernum() != null) {
            VALUES("ordernum", "#{ordernum,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(EcGoodsTypeSpec record) {
        BEGIN();
        UPDATE("sdb_b2c_goods_type_spec");
        
        if (record.getSpecStyle() != null) {
            SET("spec_style = #{specStyle,jdbcType=CHAR}");
        }
        
        if (record.getOrdernum() != null) {
            SET("ordernum = #{ordernum,jdbcType=INTEGER}");
        }
        
        WHERE("spec_id = #{specId,jdbcType=INTEGER}");
        WHERE("type_id = #{typeId,jdbcType=INTEGER}");
        
        return SQL();
    }
}