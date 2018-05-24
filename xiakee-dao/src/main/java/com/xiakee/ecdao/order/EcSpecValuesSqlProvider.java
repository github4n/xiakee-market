package com.xiakee.ecdao.order;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.ecgoods.EcSpecValues;

public class EcSpecValuesSqlProvider {

    public String insertSelective(EcSpecValues record) {
        BEGIN();
        INSERT_INTO("sdb_b2c_spec_values");
        
        if (record.getSpecValueId() != null) {
            VALUES("spec_value_id", "#{specValueId,jdbcType=INTEGER}");
        }
        
        if (record.getSpecId() != null) {
            VALUES("spec_id", "#{specId,jdbcType=INTEGER}");
        }
        
        if (record.getSpecValue() != null) {
            VALUES("spec_value", "#{specValue,jdbcType=VARCHAR}");
        }
        
        if (record.getAlias() != null) {
            VALUES("alias", "#{alias,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecImage() != null) {
            VALUES("spec_image", "#{specImage,jdbcType=CHAR}");
        }
        
        if (record.getpOrder() != null) {
            VALUES("p_order", "#{pOrder,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(EcSpecValues record) {
        BEGIN();
        UPDATE("sdb_b2c_spec_values");
        
        if (record.getSpecId() != null) {
            SET("spec_id = #{specId,jdbcType=INTEGER}");
        }
        
        if (record.getSpecValue() != null) {
            SET("spec_value = #{specValue,jdbcType=VARCHAR}");
        }
        
        if (record.getAlias() != null) {
            SET("alias = #{alias,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecImage() != null) {
            SET("spec_image = #{specImage,jdbcType=CHAR}");
        }
        
        if (record.getpOrder() != null) {
            SET("p_order = #{pOrder,jdbcType=INTEGER}");
        }
        
        WHERE("spec_value_id = #{specValueId,jdbcType=INTEGER}");
        
        return SQL();
    }
}