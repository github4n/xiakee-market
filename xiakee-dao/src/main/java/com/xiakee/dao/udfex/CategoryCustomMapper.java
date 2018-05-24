package com.xiakee.dao.udfex;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.xiakee.domain.udfex.CategoryCustom;

public interface CategoryCustomMapper {
    @Delete({
        "delete from cd_category_custom",
        "where CD_CATEGORY_ID = #{cdCategoryId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cdCategoryId);

    @Insert({
        "insert into cd_category_custom (CD_CATEGORY_ID, CATEGORY_NAME, ",
        "MEMO, TARIFF_RATE, ",
        "DUTY_PRICE, TARIFF_NUMBER, ",
        "PARENT_CATEGORY_ID, IS_PARENT, ",
        "IS_ACTIVE, RECORD_VERSION, ",
        "CREATE_USER_CODE, CREATE_DATE_TIME, ",
        "CREATE_TIME_ZONE, UPDATE_USER_CODE, ",
        "UPDATE_DATE_TIME, UPDATE_TIME_ZONE)",
        "values (#{cdCategoryId,jdbcType=INTEGER}, #{categoryName,jdbcType=VARCHAR}, ",
        "#{memo,jdbcType=VARCHAR}, #{tariffRate,jdbcType=DECIMAL}, ",
        "#{dutyPrice,jdbcType=DECIMAL}, #{tariffNumber,jdbcType=VARCHAR}, ",
        "#{parentCategoryId,jdbcType=INTEGER}, #{isParent,jdbcType=BIT}, ",
        "#{isActive,jdbcType=BIT}, #{recordVersion,jdbcType=INTEGER}, ",
        "#{createUserCode,jdbcType=VARCHAR}, #{createDateTime,jdbcType=TIMESTAMP}, ",
        "#{createTimeZone,jdbcType=VARCHAR}, #{updateUserCode,jdbcType=VARCHAR}, ",
        "#{updateDateTime,jdbcType=TIMESTAMP}, #{updateTimeZone,jdbcType=VARCHAR})"
    })
    int insert(CategoryCustom record);

    @InsertProvider(type=CategoryCustomSqlProvider.class, method="insertSelective")
    int insertSelective(CategoryCustom record);

    @Select({
        "select",
        "CD_CATEGORY_ID, CATEGORY_NAME, MEMO, TARIFF_RATE, DUTY_PRICE, TARIFF_NUMBER, ",
        "PARENT_CATEGORY_ID, IS_PARENT, IS_ACTIVE, RECORD_VERSION, CREATE_USER_CODE, ",
        "CREATE_DATE_TIME, CREATE_TIME_ZONE, UPDATE_USER_CODE, UPDATE_DATE_TIME, UPDATE_TIME_ZONE",
        "from cd_category_custom",
        "where CD_CATEGORY_ID = #{cdCategoryId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="CD_CATEGORY_ID", property="cdCategoryId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CATEGORY_NAME", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="MEMO", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(column="TARIFF_RATE", property="tariffRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="DUTY_PRICE", property="dutyPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="TARIFF_NUMBER", property="tariffNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_CATEGORY_ID", property="parentCategoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="IS_PARENT", property="isParent", jdbcType=JdbcType.BIT),
        @Result(column="IS_ACTIVE", property="isActive", jdbcType=JdbcType.BIT),
        @Result(column="RECORD_VERSION", property="recordVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_USER_CODE", property="createUserCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_DATE_TIME", property="createDateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_TIME_ZONE", property="createTimeZone", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_USER_CODE", property="updateUserCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE_TIME", property="updateDateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME_ZONE", property="updateTimeZone", jdbcType=JdbcType.VARCHAR)
    })
    CategoryCustom selectByPrimaryKey(Integer cdCategoryId);

    @UpdateProvider(type=CategoryCustomSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CategoryCustom record);

    @Update({
        "update cd_category_custom",
        "set CATEGORY_NAME = #{categoryName,jdbcType=VARCHAR},",
          "MEMO = #{memo,jdbcType=VARCHAR},",
          "TARIFF_RATE = #{tariffRate,jdbcType=DECIMAL},",
          "DUTY_PRICE = #{dutyPrice,jdbcType=DECIMAL},",
          "TARIFF_NUMBER = #{tariffNumber,jdbcType=VARCHAR},",
          "PARENT_CATEGORY_ID = #{parentCategoryId,jdbcType=INTEGER},",
          "IS_PARENT = #{isParent,jdbcType=BIT},",
          "IS_ACTIVE = #{isActive,jdbcType=BIT},",
          "RECORD_VERSION = #{recordVersion,jdbcType=INTEGER},",
          "CREATE_USER_CODE = #{createUserCode,jdbcType=VARCHAR},",
          "CREATE_DATE_TIME = #{createDateTime,jdbcType=TIMESTAMP},",
          "CREATE_TIME_ZONE = #{createTimeZone,jdbcType=VARCHAR},",
          "UPDATE_USER_CODE = #{updateUserCode,jdbcType=VARCHAR},",
          "UPDATE_DATE_TIME = #{updateDateTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME_ZONE = #{updateTimeZone,jdbcType=VARCHAR}",
        "where CD_CATEGORY_ID = #{cdCategoryId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CategoryCustom record);
    
    @Select({
        "select",
        "CD_CATEGORY_ID, CATEGORY_NAME, MEMO, TARIFF_RATE, DUTY_PRICE, TARIFF_NUMBER, ",
        "PARENT_CATEGORY_ID, IS_PARENT, IS_ACTIVE, RECORD_VERSION, CREATE_USER_CODE, ",
        "CREATE_DATE_TIME, CREATE_TIME_ZONE, UPDATE_USER_CODE, UPDATE_DATE_TIME, UPDATE_TIME_ZONE",
        "from cd_category_custom",
        "where PARENT_CATEGORY_ID = #{parentCategoryId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="CD_CATEGORY_ID", property="cdCategoryId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CATEGORY_NAME", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="MEMO", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(column="TARIFF_RATE", property="tariffRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="DUTY_PRICE", property="dutyPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="TARIFF_NUMBER", property="tariffNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_CATEGORY_ID", property="parentCategoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="IS_PARENT", property="isParent", jdbcType=JdbcType.BIT),
        @Result(column="IS_ACTIVE", property="isActive", jdbcType=JdbcType.BIT),
        @Result(column="RECORD_VERSION", property="recordVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_USER_CODE", property="createUserCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_DATE_TIME", property="createDateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_TIME_ZONE", property="createTimeZone", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_USER_CODE", property="updateUserCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE_TIME", property="updateDateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME_ZONE", property="updateTimeZone", jdbcType=JdbcType.VARCHAR)
    })
    List<CategoryCustom> selectByParentCategoryId(Integer parentCategoryId);
}