package com.xiakee.dao.sku;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.xiakee.domain.sku.SkuGoodsBean;

public class SkuGoodsBeanSqlProvider {

	public String insertSelective(SkuGoodsBean record) {
		BEGIN();
		INSERT_INTO("sku_goods");

		if (record.getGoodsNo() != null) {
			VALUES("goods_no", "#{goodsNo,jdbcType=VARCHAR}");
		}

		if (record.getSkuCode() != null) {
			VALUES("sku_code", "#{skuCode,jdbcType=VARCHAR}");
		}

		if (record.getColor() != null) {
			VALUES("color", "#{color,jdbcType=VARCHAR}");
		}

		if (record.getSize() != null) {
			VALUES("size", "#{size,jdbcType=VARCHAR}");
		}

		if (record.getImage() != null) {
			VALUES("image", "#{image,jdbcType=VARCHAR}");
		}

		if (record.getPriceIncrease() != null) {
			VALUES("price_increase", "#{priceIncrease,jdbcType=INTEGER}");
		}

		if (record.getTotalStore() != null) {
			VALUES("total_store", "#{totalStore,jdbcType=INTEGER}");
		}

		return SQL();
	}

	public String updateByPrimaryKeySelective(SkuGoodsBean record) {
		BEGIN();
		UPDATE("sku_goods");

		if (record.getSkuCode() != null) {
			SET("sku_code = #{skuCode,jdbcType=VARCHAR}");
		}

		if (record.getColor() != null) {
			SET("color = #{color,jdbcType=VARCHAR}");
		}

		if (record.getSize() != null) {
			SET("size = #{size,jdbcType=VARCHAR}");
		}

		if (record.getImage() != null) {
			SET("image = #{image,jdbcType=VARCHAR}");
		}

		if (record.getPriceIncrease() != null) {
			SET("price_increase = #{priceIncrease,jdbcType=INTEGER}");
		}

		if (record.getTotalStore() != null) {
			SET("total_store = #{totalStore,jdbcType=INTEGER}");
		}

		WHERE("goods_no = #{goodsNo,jdbcType=VARCHAR}");

		return SQL();
	}

	public String selectBySkuCodeAndSpec(SkuGoodsBean record) {

		BEGIN();
		SELECT("*");
		FROM("sku_goods");

		if (record.getSkuCode() != null) {
			WHERE("sku_code = #{skuCode,jdbcType=VARCHAR}");
		}

		if (record.getColor() != null) {
			WHERE("color = #{color,jdbcType=VARCHAR}");
		}

		if (record.getSize() != null) {
			WHERE("size = #{size,jdbcType=VARCHAR}");
		}
		return SQL();
	}

	public String selectByPriceDown_page(Map<String, Object> params) {
		StringBuffer sqlsb = new StringBuffer();
		sqlsb.append("SELECT A.*,zhName,isImport,grossId,grossProfitMargin,classify,types,brand FROM sku_goods A LEFT JOIN sku_manage B ON A.sku_code = B.skuCode where A.total_store>5 ");
		if (!StringUtils.isEmpty(params.get("classify"))) {
			sqlsb.append(" AND B.classify=" + params.get("classify"));
		}
		if (!StringUtils.isEmpty(params.get("brand"))) {
			sqlsb.append(" AND B.brand=" + params.get("brand"));
		}
		if (!StringUtils.isEmpty(params.get("priceIncrease"))) {
			sqlsb.append(" AND A.price_increase<=" + (0 - Integer.valueOf(params.get("priceIncrease").toString())));
		}
		return sqlsb.toString() + " order by goods_no DESC,B.created DESC";
	}

}