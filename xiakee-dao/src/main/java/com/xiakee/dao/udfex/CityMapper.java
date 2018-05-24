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

import com.xiakee.domain.udfex.City;

public interface CityMapper {
	@Delete({ "delete from cd_city", "where CD_CITY_ID = #{cdCityId,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer cdCityId);

	@Insert({ "insert into cd_city (CD_CITY_ID, CITY_CODE, ", "CITY_NAME, CITY_ENGLISH_NAME, ", "STATE_CODE, IS_REMOTE_AREA, ", "RECORD_VERSION, CREATE_USER_CODE, ",
			"CREATE_DATE_TIME, CREATE_TIME_ZONE, ", "UPDATE_USER_CODE, UPDATE_DATE_TIME, ", "UPDATE_TIME_ZONE)", "values (#{cdCityId,jdbcType=INTEGER}, #{cityCode,jdbcType=VARCHAR}, ",
			"#{cityName,jdbcType=VARCHAR}, #{cityEnglishName,jdbcType=VARCHAR}, ", "#{stateCode,jdbcType=VARCHAR}, #{isRemoteArea,jdbcType=INTEGER}, ",
			"#{recordVersion,jdbcType=INTEGER}, #{createUserCode,jdbcType=VARCHAR}, ", "#{createDateTime,jdbcType=TIMESTAMP}, #{createTimeZone,jdbcType=VARCHAR}, ",
			"#{updateUserCode,jdbcType=VARCHAR}, #{updateDateTime,jdbcType=TIMESTAMP}, ", "#{updateTimeZone,jdbcType=VARCHAR})" })
	int insert(City record);

	@InsertProvider(type = CitySqlProvider.class, method = "insertSelective")
	int insertSelective(City record);

	@Select({ "select", "CD_CITY_ID, CITY_CODE, CITY_NAME, CITY_ENGLISH_NAME, STATE_CODE, IS_REMOTE_AREA, ",
			"RECORD_VERSION, CREATE_USER_CODE, CREATE_DATE_TIME, CREATE_TIME_ZONE, UPDATE_USER_CODE, ", "UPDATE_DATE_TIME, UPDATE_TIME_ZONE", "from cd_city",
			"where CD_CITY_ID = #{cdCityId,jdbcType=INTEGER}" })
	@Results({ @Result(column = "CD_CITY_ID", property = "cdCityId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "CITY_CODE", property = "cityCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "CITY_NAME", property = "cityName", jdbcType = JdbcType.VARCHAR), @Result(column = "CITY_ENGLISH_NAME", property = "cityEnglishName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "STATE_CODE", property = "stateCode", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_REMOTE_AREA", property = "isRemoteArea", jdbcType = JdbcType.INTEGER),
			@Result(column = "RECORD_VERSION", property = "recordVersion", jdbcType = JdbcType.INTEGER),
			@Result(column = "CREATE_USER_CODE", property = "createUserCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "CREATE_DATE_TIME", property = "createDateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "CREATE_TIME_ZONE", property = "createTimeZone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "UPDATE_USER_CODE", property = "updateUserCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "UPDATE_DATE_TIME", property = "updateDateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "UPDATE_TIME_ZONE", property = "updateTimeZone", jdbcType = JdbcType.VARCHAR) })
	City selectByPrimaryKey(Integer cdCityId);

	@UpdateProvider(type = CitySqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(City record);

	@Update({ "update cd_city", "set CITY_CODE = #{cityCode,jdbcType=VARCHAR},", "CITY_NAME = #{cityName,jdbcType=VARCHAR},", "CITY_ENGLISH_NAME = #{cityEnglishName,jdbcType=VARCHAR},",
			"STATE_CODE = #{stateCode,jdbcType=VARCHAR},", "IS_REMOTE_AREA = #{isRemoteArea,jdbcType=INTEGER},", "RECORD_VERSION = #{recordVersion,jdbcType=INTEGER},",
			"CREATE_USER_CODE = #{createUserCode,jdbcType=VARCHAR},", "CREATE_DATE_TIME = #{createDateTime,jdbcType=TIMESTAMP},", "CREATE_TIME_ZONE = #{createTimeZone,jdbcType=VARCHAR},",
			"UPDATE_USER_CODE = #{updateUserCode,jdbcType=VARCHAR},", "UPDATE_DATE_TIME = #{updateDateTime,jdbcType=TIMESTAMP},", "UPDATE_TIME_ZONE = #{updateTimeZone,jdbcType=VARCHAR}",
			"where CD_CITY_ID = #{cdCityId,jdbcType=INTEGER}" })
	int updateByPrimaryKey(City record);

	@Select({ "select", "CD_CITY_ID, CITY_CODE, CITY_NAME, CITY_ENGLISH_NAME, STATE_CODE, IS_REMOTE_AREA, ",
			"RECORD_VERSION, CREATE_USER_CODE, CREATE_DATE_TIME, CREATE_TIME_ZONE, UPDATE_USER_CODE, ", "UPDATE_DATE_TIME, UPDATE_TIME_ZONE", "from cd_city",
			"where STATE_CODE = #{stateCode,jdbcType=VARCHAR}" })
	@Results({ @Result(column = "CD_CITY_ID", property = "cdCityId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "CITY_CODE", property = "cityCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "CITY_NAME", property = "cityName", jdbcType = JdbcType.VARCHAR), @Result(column = "CITY_ENGLISH_NAME", property = "cityEnglishName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "STATE_CODE", property = "stateCode", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_REMOTE_AREA", property = "isRemoteArea", jdbcType = JdbcType.INTEGER),
			@Result(column = "RECORD_VERSION", property = "recordVersion", jdbcType = JdbcType.INTEGER),
			@Result(column = "CREATE_USER_CODE", property = "createUserCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "CREATE_DATE_TIME", property = "createDateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "CREATE_TIME_ZONE", property = "createTimeZone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "UPDATE_USER_CODE", property = "updateUserCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "UPDATE_DATE_TIME", property = "updateDateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "UPDATE_TIME_ZONE", property = "updateTimeZone", jdbcType = JdbcType.VARCHAR) })
	List<City> selectByStateCode(String stateCode);
}