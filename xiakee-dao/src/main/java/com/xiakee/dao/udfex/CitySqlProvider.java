package com.xiakee.dao.udfex;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.udfex.City;

public class CitySqlProvider {

    public String insertSelective(City record) {
        BEGIN();
        INSERT_INTO("cd_city");
        
        if (record.getCdCityId() != null) {
            VALUES("CD_CITY_ID", "#{cdCityId,jdbcType=INTEGER}");
        }
        
        if (record.getCityCode() != null) {
            VALUES("CITY_CODE", "#{cityCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCityName() != null) {
            VALUES("CITY_NAME", "#{cityName,jdbcType=VARCHAR}");
        }
        
        if (record.getCityEnglishName() != null) {
            VALUES("CITY_ENGLISH_NAME", "#{cityEnglishName,jdbcType=VARCHAR}");
        }
        
        if (record.getStateCode() != null) {
            VALUES("STATE_CODE", "#{stateCode,jdbcType=VARCHAR}");
        }
        
        if (record.getIsRemoteArea() != null) {
            VALUES("IS_REMOTE_AREA", "#{isRemoteArea,jdbcType=INTEGER}");
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

    public String updateByPrimaryKeySelective(City record) {
        BEGIN();
        UPDATE("cd_city");
        
        if (record.getCityCode() != null) {
            SET("CITY_CODE = #{cityCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCityName() != null) {
            SET("CITY_NAME = #{cityName,jdbcType=VARCHAR}");
        }
        
        if (record.getCityEnglishName() != null) {
            SET("CITY_ENGLISH_NAME = #{cityEnglishName,jdbcType=VARCHAR}");
        }
        
        if (record.getStateCode() != null) {
            SET("STATE_CODE = #{stateCode,jdbcType=VARCHAR}");
        }
        
        if (record.getIsRemoteArea() != null) {
            SET("IS_REMOTE_AREA = #{isRemoteArea,jdbcType=INTEGER}");
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
        
        WHERE("CD_CITY_ID = #{cdCityId,jdbcType=INTEGER}");
        
        return SQL();
    }
}