package com.xiakee.ecdao.order;

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

import com.xiakee.domain.ecgoods.EcGoodsKeywords;

public interface EcGoodsKeywordsDao {
	@Delete({ "delete from sdb_b2c_goods_keywords", "where goods_id = #{goodsId,jdbcType=BIGINT}", "and keyword = #{keyword,jdbcType=VARCHAR}", "and res_type = #{resType,jdbcType=CHAR}" })
	int deleteByPrimaryKey(@Param("goodsId") Long goodsId, @Param("keyword") String keyword, @Param("resType") String resType);

	@Insert({ "insert into sdb_b2c_goods_keywords (goods_id, keyword, ", "res_type, refer, last_modify)", "values (#{goodsId,jdbcType=BIGINT}, #{keyword,jdbcType=VARCHAR}, ",
			"#{resType,jdbcType=CHAR}, #{refer,jdbcType=VARCHAR}, #{lastModify,jdbcType=INTEGER})" })
	int insert(EcGoodsKeywords record);

	@InsertProvider(type = EcGoodsKeywordsSqlProvider.class, method = "insertSelective")
	int insertSelective(EcGoodsKeywords record);

	@Select({ "select", "goods_id, keyword, res_type, refer, last_modify", "from sdb_b2c_goods_keywords", "where goods_id = #{goodsId,jdbcType=BIGINT}", "and keyword = #{keyword,jdbcType=VARCHAR}", "and res_type = #{resType,jdbcType=CHAR}" })
	@Results({ @Result(column = "goods_id", property = "goodsId", jdbcType = JdbcType.BIGINT, id = true), @Result(column = "keyword", property = "keyword", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "res_type", property = "resType", jdbcType = JdbcType.CHAR, id = true), @Result(column = "refer", property = "refer", jdbcType = JdbcType.VARCHAR),
			@Result(column = "last_modify", property = "lastModify", jdbcType = JdbcType.INTEGER) })
	EcGoodsKeywords selectByPrimaryKey(@Param("goodsId") Long goodsId, @Param("keyword") String keyword, @Param("resType") String resType);

	@UpdateProvider(type = EcGoodsKeywordsSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(EcGoodsKeywords record);

	@Update({ "update sdb_b2c_goods_keywords", "set refer = #{refer,jdbcType=VARCHAR},", "last_modify = #{lastModify,jdbcType=INTEGER}", "where goods_id = #{goodsId,jdbcType=BIGINT}", "and keyword = #{keyword,jdbcType=VARCHAR}",
			"and res_type = #{resType,jdbcType=CHAR}" })
	int updateByPrimaryKey(EcGoodsKeywords record);
}