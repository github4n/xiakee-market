package com.xiakee.ecdao.order;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.List;
import java.util.Map;

import com.xiakee.domain.ecgoods.EcGoodsSpecIndex;
import com.xiakee.domain.ecgoods.EcImageAttach;

public class EcGoodsSpecIndexSqlProvider {

	public String insertSelective(EcGoodsSpecIndex record) {
		BEGIN();
		INSERT_INTO("sdb_b2c_goods_spec_index");

		if (record.getSpecValueId() != null) {
			VALUES("spec_value_id", "#{specValueId,jdbcType=INTEGER}");
		}

		if (record.getProductId() != null) {
			VALUES("product_id", "#{productId,jdbcType=INTEGER}");
		}

		if (record.getTypeId() != null) {
			VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
		}

		if (record.getSpecId() != null) {
			VALUES("spec_id", "#{specId,jdbcType=INTEGER}");
		}

		if (record.getGoodsId() != null) {
			VALUES("goods_id", "#{goodsId,jdbcType=BIGINT}");
		}

		if (record.getLastModify() != null) {
			VALUES("last_modify", "#{lastModify,jdbcType=INTEGER}");
		}

		return SQL();
	}

	public String insertBacth(Map<String, List<EcGoodsSpecIndex>> map) {
		StringBuffer sqlsb = new StringBuffer();
		List<EcGoodsSpecIndex> recordList = map.get("list");
		sqlsb.append("insert into sdb_b2c_goods_spec_index(spec_value_id, product_id, type_id, spec_id, goods_id, last_modify) values ");
		for (EcGoodsSpecIndex ecImageAttach : recordList) {
			sqlsb.append("(");
			sqlsb.append(ecImageAttach.getSpecValueId()).append(",");
			sqlsb.append(ecImageAttach.getProductId()).append(",");
			sqlsb.append(ecImageAttach.getTypeId()).append(",");
			sqlsb.append(ecImageAttach.getSpecId()).append(",");
			sqlsb.append(ecImageAttach.getGoodsId()).append(",");
			sqlsb.append(ecImageAttach.getLastModify());
			sqlsb.append("),");
		}
		String sql = sqlsb.toString();
		return sql.substring(0, sql.length() - 1);
	}

	public String updateByPrimaryKeySelective(EcGoodsSpecIndex record) {
		BEGIN();
		UPDATE("sdb_b2c_goods_spec_index");

		if (record.getTypeId() != null) {
			SET("type_id = #{typeId,jdbcType=INTEGER}");
		}

		if (record.getSpecId() != null) {
			SET("spec_id = #{specId,jdbcType=INTEGER}");
		}

		if (record.getGoodsId() != null) {
			SET("goods_id = #{goodsId,jdbcType=BIGINT}");
		}

		if (record.getLastModify() != null) {
			SET("last_modify = #{lastModify,jdbcType=INTEGER}");
		}

		WHERE("spec_value_id = #{specValueId,jdbcType=INTEGER}");
		WHERE("product_id = #{productId,jdbcType=INTEGER}");

		return SQL();
	}
}