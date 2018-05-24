package com.xiakee.ecdao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.xiakee.domain.ecgoods.EcSpecValues;

public interface EcSpecValuesDao {
	@Delete({ "delete from sdb_b2c_spec_values", "where spec_value_id = #{specValueId,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer specValueId);

	@Insert({ "insert into sdb_b2c_spec_values (spec_value_id, spec_id, ", "spec_value, alias, ", "spec_image, p_order)", "values (#{specValueId,jdbcType=INTEGER}, #{specId,jdbcType=INTEGER}, ",
			"#{specValue,jdbcType=VARCHAR}, #{alias,jdbcType=VARCHAR}, ", "#{specImage,jdbcType=CHAR}, #{pOrder,jdbcType=INTEGER})" })
	@Options(useGeneratedKeys = true, keyProperty = "specValueId")
	int insert(EcSpecValues record);

	@InsertProvider(type = EcSpecValuesSqlProvider.class, method = "insertSelective")
	@Options(useGeneratedKeys = true, keyProperty = "specValueId")
	int insertSelective(EcSpecValues record);

	@Select({ "select", "spec_value_id, spec_id, spec_value, alias, spec_image, p_order", "from sdb_b2c_spec_values", "where spec_value_id = #{specValueId,jdbcType=INTEGER}" })
	@Results({ @Result(column = "spec_value_id", property = "specValueId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "spec_id", property = "specId", jdbcType = JdbcType.INTEGER),
			@Result(column = "spec_value", property = "specValue", jdbcType = JdbcType.VARCHAR), @Result(column = "alias", property = "alias", jdbcType = JdbcType.VARCHAR),
			@Result(column = "spec_image", property = "specImage", jdbcType = JdbcType.CHAR), @Result(column = "p_order", property = "pOrder", jdbcType = JdbcType.INTEGER) })
	EcSpecValues selectByPrimaryKey(Integer specValueId);

	@UpdateProvider(type = EcSpecValuesSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(EcSpecValues record);

	@Update({ "update sdb_b2c_spec_values", "set spec_id = #{specId,jdbcType=INTEGER},", "spec_value = #{specValue,jdbcType=VARCHAR},", "alias = #{alias,jdbcType=VARCHAR},", "spec_image = #{specImage,jdbcType=CHAR},",
			"p_order = #{pOrder,jdbcType=INTEGER}", "where spec_value_id = #{specValueId,jdbcType=INTEGER}" })
	int updateByPrimaryKey(EcSpecValues record);

	@Select("select * from sdb_b2c_spec_values  where spec_id = #{specId} AND spec_value = #{specValue}")
	@Results({ @Result(column = "spec_value_id", property = "specValueId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "spec_id", property = "specId", jdbcType = JdbcType.INTEGER),
			@Result(column = "spec_value", property = "specValue", jdbcType = JdbcType.VARCHAR), @Result(column = "alias", property = "alias", jdbcType = JdbcType.VARCHAR),
			@Result(column = "spec_image", property = "specImage", jdbcType = JdbcType.CHAR), @Result(column = "p_order", property = "pOrder", jdbcType = JdbcType.INTEGER) })
	List<EcSpecValues> selectBySpecValueAndType(Map<String, Object> params);

	@Select("select max(p_order) from sdb_b2c_spec_values")
	int selectMaxOrder();
}