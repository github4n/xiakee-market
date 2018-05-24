package com.xiakee.dao.udfex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.xiakee.domain.udfex.District;

public interface DistrictMapper {
	@Delete({ "delete from cd_district", "where CD_DISTRICT_ID = #{cdDistrictId,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer cdDistrictId);

	@Insert({ "insert into cd_district (CD_DISTRICT_ID, DISTRICT_CODE, ", "DISTRICT_NAME, DISTRICT_ENGLISH_NAME, ", "CITY_CODE, IS_REMOTE_AREA, ", "RECORD_VERSION, CREATE_USER_CODE, ",
			"CREATE_DATE_TIME, CREATE_TIME_ZONE, ", "UPDATE_USER_CODE, UPDATE_DATE_TIME, ", "UPDATE_TIME_ZONE)", "values (#{cdDistrictId,jdbcType=INTEGER}, #{districtCode,jdbcType=VARCHAR}, ",
			"#{districtName,jdbcType=VARCHAR}, #{districtEnglishName,jdbcType=VARCHAR}, ", "#{cityCode,jdbcType=VARCHAR}, #{isRemoteArea,jdbcType=INTEGER}, ",
			"#{recordVersion,jdbcType=INTEGER}, #{createUserCode,jdbcType=VARCHAR}, ", "#{createDateTime,jdbcType=TIMESTAMP}, #{createTimeZone,jdbcType=VARCHAR}, ",
			"#{updateUserCode,jdbcType=VARCHAR}, #{updateDateTime,jdbcType=TIMESTAMP}, ", "#{updateTimeZone,jdbcType=VARCHAR})" })
	int insert(District record);

	@InsertProvider(type = DistrictSqlProvider.class, method = "insertSelective")
	int insertSelective(District record);

	@Select({ "select", "CD_DISTRICT_ID, DISTRICT_CODE, DISTRICT_NAME, DISTRICT_ENGLISH_NAME, CITY_CODE, ", "IS_REMOTE_AREA, RECORD_VERSION, CREATE_USER_CODE, CREATE_DATE_TIME, CREATE_TIME_ZONE, ",
			"UPDATE_USER_CODE, UPDATE_DATE_TIME, UPDATE_TIME_ZONE", "from cd_district", "where CD_DISTRICT_ID = #{cdDistrictId,jdbcType=INTEGER}" })
	@Results({ @Result(column = "CD_DISTRICT_ID", property = "cdDistrictId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "DISTRICT_CODE", property = "districtCode", jdbcType = JdbcType.VARCHAR), @Result(column = "DISTRICT_NAME", property = "districtName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "DISTRICT_ENGLISH_NAME", property = "districtEnglishName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "CITY_CODE", property = "cityCode", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_REMOTE_AREA", property = "isRemoteArea", jdbcType = JdbcType.INTEGER),
			@Result(column = "RECORD_VERSION", property = "recordVersion", jdbcType = JdbcType.INTEGER),
			@Result(column = "CREATE_USER_CODE", property = "createUserCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "CREATE_DATE_TIME", property = "createDateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "CREATE_TIME_ZONE", property = "createTimeZone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "UPDATE_USER_CODE", property = "updateUserCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "UPDATE_DATE_TIME", property = "updateDateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "UPDATE_TIME_ZONE", property = "updateTimeZone", jdbcType = JdbcType.VARCHAR) })
	District selectByPrimaryKey(Integer cdDistrictId);

	@UpdateProvider(type = DistrictSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(District record);

	@Update({ "update cd_district", "set DISTRICT_CODE = #{districtCode,jdbcType=VARCHAR},", "DISTRICT_NAME = #{districtName,jdbcType=VARCHAR},",
			"DISTRICT_ENGLISH_NAME = #{districtEnglishName,jdbcType=VARCHAR},", "CITY_CODE = #{cityCode,jdbcType=VARCHAR},", "IS_REMOTE_AREA = #{isRemoteArea,jdbcType=INTEGER},",
			"RECORD_VERSION = #{recordVersion,jdbcType=INTEGER},", "CREATE_USER_CODE = #{createUserCode,jdbcType=VARCHAR},", "CREATE_DATE_TIME = #{createDateTime,jdbcType=TIMESTAMP},",
			"CREATE_TIME_ZONE = #{createTimeZone,jdbcType=VARCHAR},", "UPDATE_USER_CODE = #{updateUserCode,jdbcType=VARCHAR},", "UPDATE_DATE_TIME = #{updateDateTime,jdbcType=TIMESTAMP},",
			"UPDATE_TIME_ZONE = #{updateTimeZone,jdbcType=VARCHAR}", "where CD_DISTRICT_ID = #{cdDistrictId,jdbcType=INTEGER}" })
	int updateByPrimaryKey(District record);

	@Select({ "select C.STATE_CODE,A.CITY_CODE,B.DISTRICT_CODE from cd_city A,cd_district B,cd_state C where A.CITY_CODE = B.CITY_CODE and A.STATE_CODE = C.STATE_CODE and DISTRICT_NAME like CONCAT('%',#{district},'%') and CITY_NAME like CONCAT('%',#{city},'%') and STATE_NAME like CONCAT('%',#{state},'%')" })
	List<CodeBean> selectCodeByDistrictName(Map<String, Object> params);
	
	@Select({ "select", "CD_DISTRICT_ID, DISTRICT_CODE, DISTRICT_NAME, DISTRICT_ENGLISH_NAME, CITY_CODE, ", "IS_REMOTE_AREA, RECORD_VERSION, CREATE_USER_CODE, CREATE_DATE_TIME, CREATE_TIME_ZONE, ",
		"UPDATE_USER_CODE, UPDATE_DATE_TIME, UPDATE_TIME_ZONE", "from cd_district", "where CITY_CODE = #{cityCode,jdbcType=VARCHAR}" })
	@Results({ @Result(column = "CD_DISTRICT_ID", property = "cdDistrictId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "DISTRICT_CODE", property = "districtCode", jdbcType = JdbcType.VARCHAR), @Result(column = "DISTRICT_NAME", property = "districtName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "DISTRICT_ENGLISH_NAME", property = "districtEnglishName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "CITY_CODE", property = "cityCode", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_REMOTE_AREA", property = "isRemoteArea", jdbcType = JdbcType.INTEGER),
			@Result(column = "RECORD_VERSION", property = "recordVersion", jdbcType = JdbcType.INTEGER),
			@Result(column = "CREATE_USER_CODE", property = "createUserCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "CREATE_DATE_TIME", property = "createDateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "CREATE_TIME_ZONE", property = "createTimeZone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "UPDATE_USER_CODE", property = "updateUserCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "UPDATE_DATE_TIME", property = "updateDateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "UPDATE_TIME_ZONE", property = "updateTimeZone", jdbcType = JdbcType.VARCHAR) })
	List<District> selectByCityCode(String cityCode);
}