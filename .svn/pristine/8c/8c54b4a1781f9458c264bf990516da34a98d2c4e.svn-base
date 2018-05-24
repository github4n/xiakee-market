package com.xiakee.ecdao.order;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.ecgoods.EcGoodsKeywords;

public class EcGoodsKeywordsSqlProvider {

	public String insertSelective(EcGoodsKeywords record) {
		BEGIN();
		INSERT_INTO("sdb_b2c_goods_keywords");

		if (record.getGoodsId() != null) {
			VALUES("goods_id", "#{goodsId,jdbcType=BIGINT}");
		}

		if (record.getKeyword() != null) {
			VALUES("keyword", "#{keyword,jdbcType=VARCHAR}");
		}

		if (record.getResType() != null) {
			VALUES("res_type", "#{resType,jdbcType=CHAR}");
		}

		if (record.getRefer() != null) {
			VALUES("refer", "#{refer,jdbcType=VARCHAR}");
		}

		if (record.getLastModify() != null) {
			VALUES("last_modify", "#{lastModify,jdbcType=INTEGER}");
		}

		return SQL();
	}

	public String updateByPrimaryKeySelective(EcGoodsKeywords record) {
		BEGIN();
		UPDATE("sdb_b2c_goods_keywords");

		if (record.getRefer() != null) {
			SET("refer = #{refer,jdbcType=VARCHAR}");
		}

		if (record.getLastModify() != null) {
			SET("last_modify = #{lastModify,jdbcType=INTEGER}");
		}

		WHERE("goods_id = #{goodsId,jdbcType=BIGINT}");
		WHERE("keyword = #{keyword,jdbcType=VARCHAR}");
		WHERE("res_type = #{resType,jdbcType=CHAR}");

		return SQL();
	}
}