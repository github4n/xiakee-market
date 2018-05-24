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

import com.xiakee.domain.ecgoods.EcImageAttach;

public class EcImageAttachSqlProvider {

	public String insertSelective(EcImageAttach record) {
		BEGIN();
		INSERT_INTO("sdb_image_image_attach");

		if (record.getAttachId() != null) {
			VALUES("attach_id", "#{attachId,jdbcType=INTEGER}");
		}

		if (record.getTargetId() != null) {
			VALUES("target_id", "#{targetId,jdbcType=BIGINT}");
		}

		if (record.getTargetType() != null) {
			VALUES("target_type", "#{targetType,jdbcType=VARCHAR}");
		}

		if (record.getImageId() != null) {
			VALUES("image_id", "#{imageId,jdbcType=CHAR}");
		}

		if (record.getLastModified() != null) {
			VALUES("last_modified", "#{lastModified,jdbcType=INTEGER}");
		}

		return SQL();
	}

	public String insertBacth(Map<String, List<EcImageAttach>> map) {
		StringBuffer sqlsb = new StringBuffer();
		List<EcImageAttach> recordList = map.get("list");
		sqlsb.append("insert into sdb_image_image_attach(attach_id, target_id, target_type, image_id, last_modified) values ");
		for (EcImageAttach ecImageAttach : recordList) {
			sqlsb.append("(");
			sqlsb.append(ecImageAttach.getAttachId()).append(",");
			sqlsb.append(ecImageAttach.getTargetId()).append(",");
			sqlsb.append("'" + ecImageAttach.getTargetType() + "'").append(",");
			sqlsb.append("'" + ecImageAttach.getImageId() + "'").append(",");
			sqlsb.append(ecImageAttach.getLastModified());
			sqlsb.append("),");
		}
		String sql = sqlsb.toString();
		System.out.println(sql);
		return sql.substring(0, sql.length() - 1);
	}

	public String updateByPrimaryKeySelective(EcImageAttach record) {
		BEGIN();
		UPDATE("sdb_image_image_attach");

		if (record.getTargetId() != null) {
			SET("target_id = #{targetId,jdbcType=BIGINT}");
		}

		if (record.getTargetType() != null) {
			SET("target_type = #{targetType,jdbcType=VARCHAR}");
		}

		if (record.getImageId() != null) {
			SET("image_id = #{imageId,jdbcType=CHAR}");
		}

		if (record.getLastModified() != null) {
			SET("last_modified = #{lastModified,jdbcType=INTEGER}");
		}

		WHERE("attach_id = #{attachId,jdbcType=INTEGER}");

		return SQL();
	}
}