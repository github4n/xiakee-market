package com.xiakee.ecdao.order;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.ecgoods.XiakeeHaul;

public class XiakeeHaulSqlProvider {

    public String insertSelective(XiakeeHaul record) {
        BEGIN();
        INSERT_INTO("sdb_xiakee_haul");
        
        if (record.getHaulId() != null) {
            VALUES("haul_id", "#{haulId,jdbcType=BIGINT}");
        }
        
        if (record.getOrderId() != null) {
            VALUES("order_id", "#{orderId,jdbcType=BIGINT}");
        }
        
        if (record.getItemId() != null) {
            VALUES("item_id", "#{itemId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatetime() != null) {
            VALUES("createtime", "#{createtime,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(XiakeeHaul record) {
        BEGIN();
        UPDATE("sdb_xiakee_haul");
        
        if (record.getOrderId() != null) {
            SET("order_id = #{orderId,jdbcType=BIGINT}");
        }
        
        if (record.getItemId() != null) {
            SET("item_id = #{itemId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatetime() != null) {
            SET("createtime = #{createtime,jdbcType=INTEGER}");
        }
        
        WHERE("haul_id = #{haulId,jdbcType=BIGINT}");
        
        return SQL();
    }
}