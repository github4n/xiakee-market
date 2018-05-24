package com.xiakee.ecdao.order;

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

import com.xiakee.domain.ecgoods.EcSpecification;

public interface EcSpecificationDao {
	@Delete({ "delete from sdb_b2c_specification", "where spec_id = #{specId,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer specId);

	@Insert({ "insert into sdb_b2c_specification (spec_id, spec_name, ", "spec_show_type, spec_type, ", "spec_memo, p_order, ", "disabled, alias)", "values (#{specId,jdbcType=INTEGER}, #{specName,jdbcType=VARCHAR}, ",
			"#{specShowType,jdbcType=CHAR}, #{specType,jdbcType=CHAR}, ", "#{specMemo,jdbcType=VARCHAR}, #{pOrder,jdbcType=INTEGER}, ", "#{disabled,jdbcType=CHAR}, #{alias,jdbcType=VARCHAR})" })
	int insert(EcSpecification record);

	@InsertProvider(type = EcSpecificationSqlProvider.class, method = "insertSelective")
	int insertSelective(EcSpecification record);

	@Select({ "select", "spec_id, spec_name, spec_show_type, spec_type, spec_memo, p_order, disabled, ", "alias", "from sdb_b2c_specification", "where spec_id = #{specId,jdbcType=INTEGER}" })
	@Results({ @Result(column = "spec_id", property = "specId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "spec_name", property = "specName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "spec_show_type", property = "specShowType", jdbcType = JdbcType.CHAR), @Result(column = "spec_type", property = "specType", jdbcType = JdbcType.CHAR),
			@Result(column = "spec_memo", property = "specMemo", jdbcType = JdbcType.VARCHAR), @Result(column = "p_order", property = "pOrder", jdbcType = JdbcType.INTEGER),
			@Result(column = "disabled", property = "disabled", jdbcType = JdbcType.CHAR), @Result(column = "alias", property = "alias", jdbcType = JdbcType.VARCHAR) })
	EcSpecification selectByPrimaryKey(Integer specId);

	@UpdateProvider(type = EcSpecificationSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(EcSpecification record);

	@Update({ "update sdb_b2c_specification", "set spec_name = #{specName,jdbcType=VARCHAR},", "spec_show_type = #{specShowType,jdbcType=CHAR},", "spec_type = #{specType,jdbcType=CHAR},", "spec_memo = #{specMemo,jdbcType=VARCHAR},",
			"p_order = #{pOrder,jdbcType=INTEGER},", "disabled = #{disabled,jdbcType=CHAR},", "alias = #{alias,jdbcType=VARCHAR}", "where spec_id = #{specId,jdbcType=INTEGER}" })
	int updateByPrimaryKey(EcSpecification record);

	@Select("select A.* from sdb_b2c_specification A left join sdb_b2c_goods_type_spec B on A.spec_id = B.spec_id where B.type_id = #{typeId}")
	@Results({ @Result(column = "spec_id", property = "specId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "spec_name", property = "specName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "spec_show_type", property = "specShowType", jdbcType = JdbcType.CHAR), @Result(column = "spec_type", property = "specType", jdbcType = JdbcType.CHAR),
			@Result(column = "spec_memo", property = "specMemo", jdbcType = JdbcType.VARCHAR), @Result(column = "p_order", property = "pOrder", jdbcType = JdbcType.INTEGER),
			@Result(column = "disabled", property = "disabled", jdbcType = JdbcType.CHAR), @Result(column = "alias", property = "alias", jdbcType = JdbcType.VARCHAR) })
	List<EcSpecification> selectByType(Integer typeId);
}