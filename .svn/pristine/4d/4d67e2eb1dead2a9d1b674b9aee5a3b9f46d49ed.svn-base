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

import com.xiakee.domain.ecgoods.EcImageAttach;

public interface EcImageAttachDao {
	@Delete({ "delete from sdb_image_image_attach", "where attach_id = #{attachId,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer attachId);

	@Insert({ "insert into sdb_image_image_attach (attach_id, target_id, ", "target_type, image_id, ", "last_modified)", "values (#{attachId,jdbcType=INTEGER}, #{targetId,jdbcType=BIGINT}, ",
			"#{targetType,jdbcType=VARCHAR}, #{imageId,jdbcType=CHAR}, ", "#{lastModified,jdbcType=INTEGER})" })
	int insert(EcImageAttach record);

	@InsertProvider(type = EcImageAttachSqlProvider.class, method = "insertSelective")
	int insertSelective(EcImageAttach record);

	@InsertProvider(type = EcImageAttachSqlProvider.class, method = "insertBacth")
	int insertBacth(List<EcImageAttach> recordList);

	@Select({ "select", "attach_id, target_id, target_type, image_id, last_modified", "from sdb_image_image_attach", "where attach_id = #{attachId,jdbcType=INTEGER}" })
	@Results({ @Result(column = "attach_id", property = "attachId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "target_id", property = "targetId", jdbcType = JdbcType.BIGINT),
			@Result(column = "target_type", property = "targetType", jdbcType = JdbcType.VARCHAR), @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.CHAR),
			@Result(column = "last_modified", property = "lastModified", jdbcType = JdbcType.INTEGER) })
	EcImageAttach selectByPrimaryKey(Integer attachId);

	@UpdateProvider(type = EcImageAttachSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(EcImageAttach record);

	@Update({ "update sdb_image_image_attach", "set target_id = #{targetId,jdbcType=BIGINT},", "target_type = #{targetType,jdbcType=VARCHAR},", "image_id = #{imageId,jdbcType=CHAR},", "last_modified = #{lastModified,jdbcType=INTEGER}",
			"where attach_id = #{attachId,jdbcType=INTEGER}" })
	int updateByPrimaryKey(EcImageAttach record);

	@Select({ "select", "attach_id, target_id, target_type, image_id, last_modified", "from sdb_image_image_attach", "where image_id = #{imageId}" })
	@Results({ @Result(column = "attach_id", property = "attachId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "target_id", property = "targetId", jdbcType = JdbcType.BIGINT),
			@Result(column = "target_type", property = "targetType", jdbcType = JdbcType.VARCHAR), @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.CHAR),
			@Result(column = "last_modified", property = "lastModified", jdbcType = JdbcType.INTEGER) })
	EcImageAttach selectByImageId(String imageId);
}