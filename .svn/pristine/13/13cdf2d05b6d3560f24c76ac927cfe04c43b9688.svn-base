package com.xiakee.dao.udfex;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.udfex.State;

public class StateSqlProvider {

    public String insertSelective(State record) {
        BEGIN();
        INSERT_INTO("cd_state");
        
        if (record.getCdStateId() != null) {
            VALUES("CD_STATE_ID", "#{cdStateId,jdbcType=INTEGER}");
        }
        
        if (record.getStateCode() != null) {
            VALUES("STATE_CODE", "#{stateCode,jdbcType=VARCHAR}");
        }
        
        if (record.getStateName() != null) {
            VALUES("STATE_NAME", "#{stateName,jdbcType=VARCHAR}");
        }
        
        if (record.getStateChineseName() != null) {
            VALUES("STATE_CHINESE_NAME", "#{stateChineseName,jdbcType=VARCHAR}");
        }
        
        if (record.getStateEnglishName() != null) {
            VALUES("STATE_ENGLISH_NAME", "#{stateEnglishName,jdbcType=VARCHAR}");
        }
        
        if (record.getCountryCode() != null) {
            VALUES("COUNTRY_CODE", "#{countryCode,jdbcType=CHAR}");
        }
        
        if (record.getMemo() != null) {
            VALUES("MEMO", "#{memo,jdbcType=VARCHAR}");
        }
        
        if (record.getRecordVersion() != null) {
            VALUES("RECORD_VERSION", "#{recordVersion,jdbcType=INTEGER}");
        }
        
        if (record.getCreateUserCode() != null) {
            VALUES("CREATE_USER_CODE", "#{createUserCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDateTime() != null) {
            VALUES("CREATE_DATE_TIME", "#{createDateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTimeZone() != null) {
            VALUES("CREATE_TIME_ZONE", "#{createTimeZone,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateUserCode() != null) {
            VALUES("UPDATE_USER_CODE", "#{updateUserCode,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDateTime() != null) {
            VALUES("UPDATE_DATE_TIME", "#{updateDateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTimeZone() != null) {
            VALUES("UPDATE_TIME_ZONE", "#{updateTimeZone,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(State record) {
        BEGIN();
        UPDATE("cd_state");
        
        if (record.getStateCode() != null) {
            SET("STATE_CODE = #{stateCode,jdbcType=VARCHAR}");
        }
        
        if (record.getStateName() != null) {
            SET("STATE_NAME = #{stateName,jdbcType=VARCHAR}");
        }
        
        if (record.getStateChineseName() != null) {
            SET("STATE_CHINESE_NAME = #{stateChineseName,jdbcType=VARCHAR}");
        }
        
        if (record.getStateEnglishName() != null) {
            SET("STATE_ENGLISH_NAME = #{stateEnglishName,jdbcType=VARCHAR}");
        }
        
        if (record.getCountryCode() != null) {
            SET("COUNTRY_CODE = #{countryCode,jdbcType=CHAR}");
        }
        
        if (record.getMemo() != null) {
            SET("MEMO = #{memo,jdbcType=VARCHAR}");
        }
        
        if (record.getRecordVersion() != null) {
            SET("RECORD_VERSION = #{recordVersion,jdbcType=INTEGER}");
        }
        
        if (record.getCreateUserCode() != null) {
            SET("CREATE_USER_CODE = #{createUserCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDateTime() != null) {
            SET("CREATE_DATE_TIME = #{createDateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTimeZone() != null) {
            SET("CREATE_TIME_ZONE = #{createTimeZone,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateUserCode() != null) {
            SET("UPDATE_USER_CODE = #{updateUserCode,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDateTime() != null) {
            SET("UPDATE_DATE_TIME = #{updateDateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTimeZone() != null) {
            SET("UPDATE_TIME_ZONE = #{updateTimeZone,jdbcType=VARCHAR}");
        }
        
        WHERE("CD_STATE_ID = #{cdStateId,jdbcType=INTEGER}");
        
        return SQL();
    }
}