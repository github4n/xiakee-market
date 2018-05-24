package com.xiakee.dao.udfex;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.udfex.CategoryCustom;

public class CategoryCustomSqlProvider {

    public String insertSelective(CategoryCustom record) {
        BEGIN();
        INSERT_INTO("cd_category_custom");
        
        if (record.getCdCategoryId() != null) {
            VALUES("CD_CATEGORY_ID", "#{cdCategoryId,jdbcType=INTEGER}");
        }
        
        if (record.getCategoryName() != null) {
            VALUES("CATEGORY_NAME", "#{categoryName,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            VALUES("MEMO", "#{memo,jdbcType=VARCHAR}");
        }
        
        if (record.getTariffRate() != null) {
            VALUES("TARIFF_RATE", "#{tariffRate,jdbcType=DECIMAL}");
        }
        
        if (record.getDutyPrice() != null) {
            VALUES("DUTY_PRICE", "#{dutyPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getTariffNumber() != null) {
            VALUES("TARIFF_NUMBER", "#{tariffNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getParentCategoryId() != null) {
            VALUES("PARENT_CATEGORY_ID", "#{parentCategoryId,jdbcType=INTEGER}");
        }
        
        if (record.getIsParent() != null) {
            VALUES("IS_PARENT", "#{isParent,jdbcType=BIT}");
        }
        
        if (record.getIsActive() != null) {
            VALUES("IS_ACTIVE", "#{isActive,jdbcType=BIT}");
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

    public String updateByPrimaryKeySelective(CategoryCustom record) {
        BEGIN();
        UPDATE("cd_category_custom");
        
        if (record.getCategoryName() != null) {
            SET("CATEGORY_NAME = #{categoryName,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            SET("MEMO = #{memo,jdbcType=VARCHAR}");
        }
        
        if (record.getTariffRate() != null) {
            SET("TARIFF_RATE = #{tariffRate,jdbcType=DECIMAL}");
        }
        
        if (record.getDutyPrice() != null) {
            SET("DUTY_PRICE = #{dutyPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getTariffNumber() != null) {
            SET("TARIFF_NUMBER = #{tariffNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getParentCategoryId() != null) {
            SET("PARENT_CATEGORY_ID = #{parentCategoryId,jdbcType=INTEGER}");
        }
        
        if (record.getIsParent() != null) {
            SET("IS_PARENT = #{isParent,jdbcType=BIT}");
        }
        
        if (record.getIsActive() != null) {
            SET("IS_ACTIVE = #{isActive,jdbcType=BIT}");
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
        
        WHERE("CD_CATEGORY_ID = #{cdCategoryId,jdbcType=INTEGER}");
        
        return SQL();
    }
}