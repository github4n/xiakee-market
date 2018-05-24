package com.xiakee.dao.material;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.xiakee.domain.material.Material;

public interface MaterialMapper {
	@Delete({ "delete from xiakee_material", "where material_id = #{materialId,jdbcType=BIGINT}" })
	int deleteByPrimaryKey(Long materialId);

	@Insert({ "insert into xiakee_material (material_id, material_name, ", "material_url, price, ", "mktprice, material_desc, ", "material_image, material_status, ", "create_time, update_time, ",
			"last_update_user)", "values (#{materialId,jdbcType=BIGINT}, #{materialName,jdbcType=VARCHAR}, ", "#{materialUrl,jdbcType=VARCHAR}, #{price,jdbcType=DECIMAL}, ",
			"#{mktprice,jdbcType=DECIMAL}, #{materialDesc,jdbcType=VARCHAR}, ", "#{materialImage,jdbcType=VARCHAR}, #{materialStatus,jdbcType=INTEGER}, ", "now(), now(), ",
			"#{lastUpdateUser,jdbcType=VARCHAR})" })
	int insert(Material record);

	@InsertProvider(type = MaterialSqlProvider.class, method = "insertSelective")
	int insertSelective(Material record);

	@Select({ "select", "material_id, material_name, material_url, price, mktprice, material_desc, material_image, ", "material_status, create_time, update_time, last_update_user",
			"from xiakee_material", "where material_id = #{materialId,jdbcType=BIGINT}" })
	@Results({ @Result(column = "material_id", property = "materialId", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "material_name", property = "materialName", jdbcType = JdbcType.VARCHAR), @Result(column = "material_url", property = "materialUrl", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL), @Result(column = "mktprice", property = "mktprice", jdbcType = JdbcType.DECIMAL),
			@Result(column = "material_desc", property = "materialDesc", jdbcType = JdbcType.VARCHAR), @Result(column = "material_image", property = "materialImage", jdbcType = JdbcType.VARCHAR),
			@Result(column = "material_status", property = "materialStatus", jdbcType = JdbcType.INTEGER), @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP), @Result(column = "last_update_user", property = "lastUpdateUser", jdbcType = JdbcType.VARCHAR) })
	Material selectByPrimaryKey(Long materialId);

	@UpdateProvider(type = MaterialSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(Material record);

	@Update({ "update xiakee_material", "set material_name = #{materialName,jdbcType=VARCHAR},", "material_url = #{materialUrl,jdbcType=VARCHAR},", "price = #{price,jdbcType=DECIMAL},",
			"mktprice = #{mktprice,jdbcType=DECIMAL},", "material_desc = #{materialDesc,jdbcType=VARCHAR},", "material_image = #{materialImage,jdbcType=VARCHAR},",
			"material_status = #{materialStatus,jdbcType=INTEGER},", "create_time = #{createTime,jdbcType=TIMESTAMP},", "update_time = now(),",
			"last_update_user = #{lastUpdateUser,jdbcType=VARCHAR}", "where material_id = #{materialId,jdbcType=BIGINT}" })
	int updateByPrimaryKey(Material record);

	@Select({ "select", "material_id, material_name, material_url, price, mktprice, material_desc, material_image, ", "material_status, create_time, update_time, last_update_user",
			"from xiakee_material", "where material_status = #{materialStatus,jdbcType=INTEGER} order by material_id desc" })
	@Results({ @Result(column = "material_id", property = "materialId", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "material_name", property = "materialName", jdbcType = JdbcType.VARCHAR), @Result(column = "material_url", property = "materialUrl", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL), @Result(column = "mktprice", property = "mktprice", jdbcType = JdbcType.DECIMAL),
			@Result(column = "material_desc", property = "materialDesc", jdbcType = JdbcType.VARCHAR), @Result(column = "material_image", property = "materialImage", jdbcType = JdbcType.VARCHAR),
			@Result(column = "material_status", property = "materialStatus", jdbcType = JdbcType.INTEGER), @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP), @Result(column = "last_update_user", property = "lastUpdateUser", jdbcType = JdbcType.VARCHAR) })
	List<Material> selectByStatus_page(Map<String, Object> param);
}