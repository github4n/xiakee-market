package com.xiakee.ecdao.order;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.ecgoods.XiakeeHaulProcess;

public class XiakeeHaulProcessSqlProvider {

    public String insertSelective(XiakeeHaulProcess record) {
        BEGIN();
        INSERT_INTO("sdb_xiakee_haul_process");
        
        if (record.getProcessId() != null) {
            VALUES("process_id", "#{processId,jdbcType=INTEGER}");
        }
        
        if (record.getRelId() != null) {
            VALUES("rel_id", "#{relId,jdbcType=BIGINT}");
        }
        
        if (record.getOrderId() != null) {
            VALUES("order_id", "#{orderId,jdbcType=BIGINT}");
        }
        
        if (record.getOpId() != null) {
            VALUES("op_id", "#{opId,jdbcType=INTEGER}");
        }
        
        if (record.getOpName() != null) {
            VALUES("op_name", "#{opName,jdbcType=VARCHAR}");
        }
        
        if (record.getAlttime() != null) {
            VALUES("alttime", "#{alttime,jdbcType=INTEGER}");
        }
        
        if (record.getBillType() != null) {
            VALUES("bill_type", "#{billType,jdbcType=CHAR}");
        }
        
        if (record.getBehavior() != null) {
            VALUES("behavior", "#{behavior,jdbcType=CHAR}");
        }
        
        if (record.getResult() != null) {
            VALUES("result", "#{result,jdbcType=CHAR}");
        }
        
        if (record.getLogText() != null) {
            VALUES("log_text", "#{logText,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getAddon() != null) {
            VALUES("addon", "#{addon,jdbcType=LONGVARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(XiakeeHaulProcess record) {
        BEGIN();
        UPDATE("sdb_xiakee_haul_process");
        
        if (record.getRelId() != null) {
            SET("rel_id = #{relId,jdbcType=BIGINT}");
        }
        
        if (record.getOrderId() != null) {
            SET("order_id = #{orderId,jdbcType=BIGINT}");
        }
        
        if (record.getOpId() != null) {
            SET("op_id = #{opId,jdbcType=INTEGER}");
        }
        
        if (record.getOpName() != null) {
            SET("op_name = #{opName,jdbcType=VARCHAR}");
        }
        
        if (record.getAlttime() != null) {
            SET("alttime = #{alttime,jdbcType=INTEGER}");
        }
        
        if (record.getBillType() != null) {
            SET("bill_type = #{billType,jdbcType=CHAR}");
        }
        
        if (record.getBehavior() != null) {
            SET("behavior = #{behavior,jdbcType=CHAR}");
        }
        
        if (record.getResult() != null) {
            SET("result = #{result,jdbcType=CHAR}");
        }
        
        if (record.getLogText() != null) {
            SET("log_text = #{logText,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getAddon() != null) {
            SET("addon = #{addon,jdbcType=LONGVARCHAR}");
        }
        
        WHERE("process_id = #{processId,jdbcType=INTEGER}");
        
        return SQL();
    }
}