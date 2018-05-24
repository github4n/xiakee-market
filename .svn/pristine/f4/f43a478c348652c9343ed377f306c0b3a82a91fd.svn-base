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

import com.xiakee.domain.udfex.State;

public interface StateMapper {
    @Delete({
        "delete from cd_state",
        "where CD_STATE_ID = #{cdStateId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cdStateId);

    @Insert({
        "insert into cd_state (CD_STATE_ID, STATE_CODE, ",
        "STATE_NAME, STATE_CHINESE_NAME, ",
        "STATE_ENGLISH_NAME, COUNTRY_CODE, ",
        "MEMO, RECORD_VERSION, ",
        "CREATE_USER_CODE, CREATE_DATE_TIME, ",
        "CREATE_TIME_ZONE, UPDATE_USER_CODE, ",
        "UPDATE_DATE_TIME, UPDATE_TIME_ZONE)",
        "values (#{cdStateId,jdbcType=INTEGER}, #{stateCode,jdbcType=VARCHAR}, ",
        "#{stateName,jdbcType=VARCHAR}, #{stateChineseName,jdbcType=VARCHAR}, ",
        "#{stateEnglishName,jdbcType=VARCHAR}, #{countryCode,jdbcType=CHAR}, ",
        "#{memo,jdbcType=VARCHAR}, #{recordVersion,jdbcType=INTEGER}, ",
        "#{createUserCode,jdbcType=VARCHAR}, #{createDateTime,jdbcType=TIMESTAMP}, ",
        "#{createTimeZone,jdbcType=VARCHAR}, #{updateUserCode,jdbcType=VARCHAR}, ",
        "#{updateDateTime,jdbcType=TIMESTAMP}, #{updateTimeZone,jdbcType=VARCHAR})"
    })
    int insert(State record);

    @InsertProvider(type=StateSqlProvider.class, method="insertSelective")
    int insertSelective(State record);

    @Select({
        "select",
        "CD_STATE_ID, STATE_CODE, STATE_NAME, STATE_CHINESE_NAME, STATE_ENGLISH_NAME, ",
        "COUNTRY_CODE, MEMO, RECORD_VERSION, CREATE_USER_CODE, CREATE_DATE_TIME, CREATE_TIME_ZONE, ",
        "UPDATE_USER_CODE, UPDATE_DATE_TIME, UPDATE_TIME_ZONE",
        "from cd_state",
        "where CD_STATE_ID = #{cdStateId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="CD_STATE_ID", property="cdStateId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="STATE_CODE", property="stateCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATE_NAME", property="stateName", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATE_CHINESE_NAME", property="stateChineseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATE_ENGLISH_NAME", property="stateEnglishName", jdbcType=JdbcType.VARCHAR),
        @Result(column="COUNTRY_CODE", property="countryCode", jdbcType=JdbcType.CHAR),
        @Result(column="MEMO", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECORD_VERSION", property="recordVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_USER_CODE", property="createUserCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_DATE_TIME", property="createDateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_TIME_ZONE", property="createTimeZone", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_USER_CODE", property="updateUserCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE_TIME", property="updateDateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME_ZONE", property="updateTimeZone", jdbcType=JdbcType.VARCHAR)
    })
    State selectByPrimaryKey(Integer cdStateId);

    @UpdateProvider(type=StateSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(State record);

    @Update({
        "update cd_state",
        "set STATE_CODE = #{stateCode,jdbcType=VARCHAR},",
          "STATE_NAME = #{stateName,jdbcType=VARCHAR},",
          "STATE_CHINESE_NAME = #{stateChineseName,jdbcType=VARCHAR},",
          "STATE_ENGLISH_NAME = #{stateEnglishName,jdbcType=VARCHAR},",
          "COUNTRY_CODE = #{countryCode,jdbcType=CHAR},",
          "MEMO = #{memo,jdbcType=VARCHAR},",
          "RECORD_VERSION = #{recordVersion,jdbcType=INTEGER},",
          "CREATE_USER_CODE = #{createUserCode,jdbcType=VARCHAR},",
          "CREATE_DATE_TIME = #{createDateTime,jdbcType=TIMESTAMP},",
          "CREATE_TIME_ZONE = #{createTimeZone,jdbcType=VARCHAR},",
          "UPDATE_USER_CODE = #{updateUserCode,jdbcType=VARCHAR},",
          "UPDATE_DATE_TIME = #{updateDateTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME_ZONE = #{updateTimeZone,jdbcType=VARCHAR}",
        "where CD_STATE_ID = #{cdStateId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(State record);
    
    @Select({
        "select",
        "CD_STATE_ID, STATE_CODE, STATE_NAME, STATE_CHINESE_NAME, STATE_ENGLISH_NAME, ",
        "COUNTRY_CODE, MEMO, RECORD_VERSION, CREATE_USER_CODE, CREATE_DATE_TIME, CREATE_TIME_ZONE, ",
        "UPDATE_USER_CODE, UPDATE_DATE_TIME, UPDATE_TIME_ZONE",
        "from cd_state"
    })
    @Results({
        @Result(column="CD_STATE_ID", property="cdStateId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="STATE_CODE", property="stateCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATE_NAME", property="stateName", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATE_CHINESE_NAME", property="stateChineseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATE_ENGLISH_NAME", property="stateEnglishName", jdbcType=JdbcType.VARCHAR),
        @Result(column="COUNTRY_CODE", property="countryCode", jdbcType=JdbcType.CHAR),
        @Result(column="MEMO", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECORD_VERSION", property="recordVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_USER_CODE", property="createUserCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_DATE_TIME", property="createDateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_TIME_ZONE", property="createTimeZone", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_USER_CODE", property="updateUserCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE_TIME", property="updateDateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME_ZONE", property="updateTimeZone", jdbcType=JdbcType.VARCHAR)
    })
    List<State> selectByAll();
    
}