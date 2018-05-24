package com.xiakee.ecdao.order;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.List;

import com.xiakee.domain.ecgoods.EcImage;

public class EcImageSqlProvider {

	public String insertSelective(EcImage record) {
		BEGIN();
		INSERT_INTO("sdb_image_image");

		if (record.getImageId() != null) {
			VALUES("image_id", "#{imageId,jdbcType=CHAR}");
		}

		if (record.getStorage() != null) {
			VALUES("storage", "#{storage,jdbcType=VARCHAR}");
		}

		if (record.getImageName() != null) {
			VALUES("image_name", "#{imageName,jdbcType=VARCHAR}");
		}

		if (record.getIdent() != null) {
			VALUES("ident", "#{ident,jdbcType=VARCHAR}");
		}

		if (record.getUrl() != null) {
			VALUES("url", "#{url,jdbcType=VARCHAR}");
		}

		if (record.getlIdent() != null) {
			VALUES("l_ident", "#{lIdent,jdbcType=VARCHAR}");
		}

		if (record.getlUrl() != null) {
			VALUES("l_url", "#{lUrl,jdbcType=VARCHAR}");
		}

		if (record.getmIdent() != null) {
			VALUES("m_ident", "#{mIdent,jdbcType=VARCHAR}");
		}

		if (record.getmUrl() != null) {
			VALUES("m_url", "#{mUrl,jdbcType=VARCHAR}");
		}

		if (record.getsIdent() != null) {
			VALUES("s_ident", "#{sIdent,jdbcType=VARCHAR}");
		}

		if (record.getsUrl() != null) {
			VALUES("s_url", "#{sUrl,jdbcType=VARCHAR}");
		}

		if (record.getWidth() != null) {
			VALUES("width", "#{width,jdbcType=INTEGER}");
		}

		if (record.getHeight() != null) {
			VALUES("height", "#{height,jdbcType=INTEGER}");
		}

		if (record.getWatermark() != null) {
			VALUES("watermark", "#{watermark,jdbcType=CHAR}");
		}

		if (record.getLastModified() != null) {
			VALUES("last_modified", "#{lastModified,jdbcType=INTEGER}");
		}

		return SQL();
	}

	public String updateByPrimaryKeySelective(EcImage record) {
		BEGIN();
		UPDATE("sdb_image_image");

		if (record.getStorage() != null) {
			SET("storage = #{storage,jdbcType=VARCHAR}");
		}

		if (record.getImageName() != null) {
			SET("image_name = #{imageName,jdbcType=VARCHAR}");
		}

		if (record.getIdent() != null) {
			SET("ident = #{ident,jdbcType=VARCHAR}");
		}

		if (record.getUrl() != null) {
			SET("url = #{url,jdbcType=VARCHAR}");
		}

		if (record.getlIdent() != null) {
			SET("l_ident = #{lIdent,jdbcType=VARCHAR}");
		}

		if (record.getlUrl() != null) {
			SET("l_url = #{lUrl,jdbcType=VARCHAR}");
		}

		if (record.getmIdent() != null) {
			SET("m_ident = #{mIdent,jdbcType=VARCHAR}");
		}

		if (record.getmUrl() != null) {
			SET("m_url = #{mUrl,jdbcType=VARCHAR}");
		}

		if (record.getsIdent() != null) {
			SET("s_ident = #{sIdent,jdbcType=VARCHAR}");
		}

		if (record.getsUrl() != null) {
			SET("s_url = #{sUrl,jdbcType=VARCHAR}");
		}

		if (record.getWidth() != null) {
			SET("width = #{width,jdbcType=INTEGER}");
		}

		if (record.getHeight() != null) {
			SET("height = #{height,jdbcType=INTEGER}");
		}

		if (record.getWatermark() != null) {
			SET("watermark = #{watermark,jdbcType=CHAR}");
		}

		if (record.getLastModified() != null) {
			SET("last_modified = #{lastModified,jdbcType=INTEGER}");
		}

		WHERE("image_id = #{imageId,jdbcType=CHAR}");

		return SQL();
	}
}