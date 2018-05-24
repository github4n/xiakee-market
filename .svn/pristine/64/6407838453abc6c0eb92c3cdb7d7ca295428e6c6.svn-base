package com.xiakee.dao.udfex;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.udfex.District;

public class DistrictSqlProvider {

    public String insertSelective(District record) {
        BEGIN();
        INSERT_INTO("cd_district");
        
        if (record.getCdDistrictId() != null) {
            VALUES("CD_DISTRICT_ID", "#{cdDistrictId,jdbcType=INTEGER}");
        }
        
        if (record.getDistrictCode() != null) {
            VALUES("DISTRICT_CODE", "#{districtCode,jdbcType=VARCHAR}");
        }
        
        if (record.getDistrictName() != null) {
            VALUES("DISTRICT_NAME", "#{districtName,jdbcType=VARCHAR}");
        }
        
        if (record.getDistrictEnglishName() != null) {
            VALUES("DISTRICT_ENGLISH_NAME", "#{districtEnglishName,jdbcType=VARCHAR}");
        }
        
        if (record.getCityCode() != null) {
            VALUES("CITY_CODE", "#{cityCode,jdbcType=VARCHAR}");
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

    public String updateByPrimaryKeySelective(District record) {
        BEGIN();
        UPDATE("cd_district");
        
        if (record.getDistrictCode() != null) {
            SET("DISTRICT_CODE = #{districtCode,jdbcType=VARCHAR}");
        }
        
        if (record.getDistrictName() != null) {
            SET("DISTRICT_NAME = #{districtName,jdbcType=VARCHAR}");
        }
        
        if (record.getDistrictEnglishName() != null) {
            SET("DISTRICT_ENGLISH_NAME = #{districtEnglishName,jdbcType=VARCHAR}");
        }
        
        if (record.getCityCode() != null) {
            SET("CITY_CODE = #{cityCode,jdbcType=VARCHAR}");
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
        
        WHERE("CD_DISTRICT_ID = #{cdDistrictId,jdbcType=INTEGER}");
        
        return SQL();
    }
}