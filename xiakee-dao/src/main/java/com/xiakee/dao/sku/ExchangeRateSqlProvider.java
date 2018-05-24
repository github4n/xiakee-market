package com.xiakee.dao.sku;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.sku.ExchangeRate;

public class ExchangeRateSqlProvider {

    public String insertSelective(ExchangeRate record) {
        BEGIN();
        INSERT_INTO("xiakee_exchange_rate");
        
        if (record.getCurrency() != null) {
            VALUES("currency", "#{currency,jdbcType=VARCHAR}");
        }
        
        if (record.getZhcurrency() != null) {
            VALUES("zhcurrency", "#{zhcurrency,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            VALUES("value", "#{value,jdbcType=DECIMAL}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(ExchangeRate record) {
        BEGIN();
        UPDATE("xiakee_exchange_rate");
        
        if (record.getZhcurrency() != null) {
            SET("zhcurrency = #{zhcurrency,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            SET("value = #{value,jdbcType=DECIMAL}");
        }
        
        WHERE("currency = #{currency,jdbcType=VARCHAR}");
        
        return SQL();
    }
}