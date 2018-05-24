package com.xiakee.dao.material;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xiakee.domain.material.Material;

public class MaterialSqlProvider {

    public String insertSelective(Material record) {
        BEGIN();
        INSERT_INTO("xiakee_material");
        
        if (record.getMaterialId() != null) {
            VALUES("material_id", "#{materialId,jdbcType=BIGINT}");
        }
        
        if (record.getMaterialName() != null) {
            VALUES("material_name", "#{materialName,jdbcType=VARCHAR}");
        }
        
        if (record.getMaterialUrl() != null) {
            VALUES("material_url", "#{materialUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            VALUES("price", "#{price,jdbcType=DECIMAL}");
        }
        
        if (record.getMktprice() != null) {
            VALUES("mktprice", "#{mktprice,jdbcType=DECIMAL}");
        }
        
        if (record.getMaterialDesc() != null) {
            VALUES("material_desc", "#{materialDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getMaterialImage() != null) {
            VALUES("material_image", "#{materialImage,jdbcType=VARCHAR}");
        }
        
        if (record.getMaterialStatus() != null) {
            VALUES("material_status", "#{materialStatus,jdbcType=INTEGER}");
        }
        
            VALUES("create_time", "now()");
        
            VALUES("update_time", "now()");
        
        if (record.getLastUpdateUser() != null) {
            VALUES("last_update_user", "#{lastUpdateUser,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(Material record) {
        BEGIN();
        UPDATE("xiakee_material");
        
        if (record.getMaterialName() != null) {
            SET("material_name = #{materialName,jdbcType=VARCHAR}");
        }
        
        if (record.getMaterialUrl() != null) {
            SET("material_url = #{materialUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            SET("price = #{price,jdbcType=DECIMAL}");
        }
        
        if (record.getMktprice() != null) {
            SET("mktprice = #{mktprice,jdbcType=DECIMAL}");
        }
        
        if (record.getMaterialDesc() != null) {
            SET("material_desc = #{materialDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getMaterialImage() != null) {
            SET("material_image = #{materialImage,jdbcType=VARCHAR}");
        }
        
        if (record.getMaterialStatus() != null) {
            SET("material_status = #{materialStatus,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
            SET("update_time = now()");
        
        if (record.getLastUpdateUser() != null) {
            SET("last_update_user = #{lastUpdateUser,jdbcType=VARCHAR}");
        }
        
        WHERE("material_id = #{materialId,jdbcType=BIGINT}");
        
        return SQL();
    }
}