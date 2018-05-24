package com.xiakee.ecdao.order;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.ecgoods.EcSpecification;

public class EcSpecificationSqlProvider {

    public String insertSelective(EcSpecification record) {
        BEGIN();
        INSERT_INTO("sdb_b2c_specification");
        
        if (record.getSpecId() != null) {
            VALUES("spec_id", "#{specId,jdbcType=INTEGER}");
        }
        
        if (record.getSpecName() != null) {
            VALUES("spec_name", "#{specName,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecShowType() != null) {
            VALUES("spec_show_type", "#{specShowType,jdbcType=CHAR}");
        }
        
        if (record.getSpecType() != null) {
            VALUES("spec_type", "#{specType,jdbcType=CHAR}");
        }
        
        if (record.getSpecMemo() != null) {
            VALUES("spec_memo", "#{specMemo,jdbcType=VARCHAR}");
        }
        
        if (record.getpOrder() != null) {
            VALUES("p_order", "#{pOrder,jdbcType=INTEGER}");
        }
        
        if (record.getDisabled() != null) {
            VALUES("disabled", "#{disabled,jdbcType=CHAR}");
        }
        
        if (record.getAlias() != null) {
            VALUES("alias", "#{alias,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(EcSpecification record) {
        BEGIN();
        UPDATE("sdb_b2c_specification");
        
        if (record.getSpecName() != null) {
            SET("spec_name = #{specName,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecShowType() != null) {
            SET("spec_show_type = #{specShowType,jdbcType=CHAR}");
        }
        
        if (record.getSpecType() != null) {
            SET("spec_type = #{specType,jdbcType=CHAR}");
        }
        
        if (record.getSpecMemo() != null) {
            SET("spec_memo = #{specMemo,jdbcType=VARCHAR}");
        }
        
        if (record.getpOrder() != null) {
            SET("p_order = #{pOrder,jdbcType=INTEGER}");
        }
        
        if (record.getDisabled() != null) {
            SET("disabled = #{disabled,jdbcType=CHAR}");
        }
        
        if (record.getAlias() != null) {
            SET("alias = #{alias,jdbcType=VARCHAR}");
        }
        
        WHERE("spec_id = #{specId,jdbcType=INTEGER}");
        
        return SQL();
    }
}