package com.xiakee.ecdao.order;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.xiakee.domain.ecgoods.EcGoodsSpecIndex;

public interface EcGoodsSpecIndexDao {
	@Delete({ "delete from sdb_b2c_goods_spec_index", "where spec_value_id = #{specValueId,jdbcType=INTEGER}", "and product_id = #{productId,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(@Param("specValueId") Integer specValueId, @Param("productId") Integer productId);

	@Insert({ "insert into sdb_b2c_goods_spec_index (spec_value_id, product_id, ", "type_id, spec_id, ", "goods_id, last_modify)", "values (#{specValueId,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER}, ",
			"#{typeId,jdbcType=INTEGER}, #{specId,jdbcType=INTEGER}, ", "#{goodsId,jdbcType=BIGINT}, #{lastModify,jdbcType=INTEGER})" })
	int insert(EcGoodsSpecIndex record);

	@InsertProvider(type = EcGoodsSpecIndexSqlProvider.class, method = "insertSelective")
	int insertSelective(EcGoodsSpecIndex record);

	@InsertProvider(type = EcGoodsSpecIndexSqlProvider.class, method = "insertBacth")
	int insertBacth(List<EcGoodsSpecIndex> recordList);

	@Select({ "select", "spec_value_id, product_id, type_id, spec_id, goods_id, last_modify", "from sdb_b2c_goods_spec_index", "where spec_value_id = #{specValueId,jdbcType=INTEGER}", "and product_id = #{productId,jdbcType=INTEGER}" })
	@Results({ @Result(column = "spec_value_id", property = "specValueId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "product_id", property = "productId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "type_id", property = "typeId", jdbcType = JdbcType.INTEGER), @Result(column = "spec_id", property = "specId", jdbcType = JdbcType.INTEGER), @Result(column = "goods_id", property = "goodsId", jdbcType = JdbcType.BIGINT),
			@Result(column = "last_modify", property = "lastModify", jdbcType = JdbcType.INTEGER) })
	EcGoodsSpecIndex selectByPrimaryKey(@Param("specValueId") Integer specValueId, @Param("productId") Integer productId);

	@UpdateProvider(type = EcGoodsSpecIndexSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(EcGoodsSpecIndex record);

	@Update({ "update sdb_b2c_goods_spec_index", "set type_id = #{typeId,jdbcType=INTEGER},", "spec_id = #{specId,jdbcType=INTEGER},", "goods_id = #{goodsId,jdbcType=BIGINT},", "last_modify = #{lastModify,jdbcType=INTEGER}",
			"where spec_value_id = #{specValueId,jdbcType=INTEGER}", "and product_id = #{productId,jdbcType=INTEGER}" })
	int updateByPrimaryKey(EcGoodsSpecIndex record);
}