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

import com.xiakee.domain.ecgoods.EcImage;

public interface EcImageDao {
    @Delete({
        "delete from sdb_image_image",
        "where image_id = #{imageId,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String imageId);

    @Insert({
        "insert into sdb_image_image (image_id, storage, ",
        "image_name, ident, ",
        "url, l_ident, l_url, ",
        "m_ident, m_url, s_ident, ",
        "s_url, width, height, ",
        "watermark, last_modified)",
        "values (#{imageId,jdbcType=CHAR}, #{storage,jdbcType=VARCHAR}, ",
        "#{imageName,jdbcType=VARCHAR}, #{ident,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR}, #{lIdent,jdbcType=VARCHAR}, #{lUrl,jdbcType=VARCHAR}, ",
        "#{mIdent,jdbcType=VARCHAR}, #{mUrl,jdbcType=VARCHAR}, #{sIdent,jdbcType=VARCHAR}, ",
        "#{sUrl,jdbcType=VARCHAR}, #{width,jdbcType=INTEGER}, #{height,jdbcType=INTEGER}, ",
        "#{watermark,jdbcType=CHAR}, #{lastModified,jdbcType=INTEGER})"
    })
    int insert(EcImage record);

    @InsertProvider(type=EcImageSqlProvider.class, method="insertSelective")
    int insertSelective(EcImage record);

    @Select({
        "select",
        "image_id, storage, image_name, ident, url, l_ident, l_url, m_ident, m_url, s_ident, ",
        "s_url, width, height, watermark, last_modified",
        "from sdb_image_image",
        "where image_id = #{imageId,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="image_id", property="imageId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="storage", property="storage", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_name", property="imageName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ident", property="ident", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="l_ident", property="lIdent", jdbcType=JdbcType.VARCHAR),
        @Result(column="l_url", property="lUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="m_ident", property="mIdent", jdbcType=JdbcType.VARCHAR),
        @Result(column="m_url", property="mUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="s_ident", property="sIdent", jdbcType=JdbcType.VARCHAR),
        @Result(column="s_url", property="sUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="width", property="width", jdbcType=JdbcType.INTEGER),
        @Result(column="height", property="height", jdbcType=JdbcType.INTEGER),
        @Result(column="watermark", property="watermark", jdbcType=JdbcType.CHAR),
        @Result(column="last_modified", property="lastModified", jdbcType=JdbcType.INTEGER)
    })
    EcImage selectByPrimaryKey(String imageId);
    
    @Select({
        "select",
        "image_id, storage, image_name, ident, url, l_ident, l_url, m_ident, m_url, s_ident, ",
        "s_url, width, height, watermark, last_modified",
        "from sdb_image_image",
        "where ident = #{ident,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="image_id", property="imageId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="storage", property="storage", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_name", property="imageName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ident", property="ident", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="l_ident", property="lIdent", jdbcType=JdbcType.VARCHAR),
        @Result(column="l_url", property="lUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="m_ident", property="mIdent", jdbcType=JdbcType.VARCHAR),
        @Result(column="m_url", property="mUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="s_ident", property="sIdent", jdbcType=JdbcType.VARCHAR),
        @Result(column="s_url", property="sUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="width", property="width", jdbcType=JdbcType.INTEGER),
        @Result(column="height", property="height", jdbcType=JdbcType.INTEGER),
        @Result(column="watermark", property="watermark", jdbcType=JdbcType.CHAR),
        @Result(column="last_modified", property="lastModified", jdbcType=JdbcType.INTEGER)
    })
    List<EcImage> selectByIdent(String ident);

    @UpdateProvider(type=EcImageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(EcImage record);

    @Update({
        "update sdb_image_image",
        "set storage = #{storage,jdbcType=VARCHAR},",
          "image_name = #{imageName,jdbcType=VARCHAR},",
          "ident = #{ident,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "l_ident = #{lIdent,jdbcType=VARCHAR},",
          "l_url = #{lUrl,jdbcType=VARCHAR},",
          "m_ident = #{mIdent,jdbcType=VARCHAR},",
          "m_url = #{mUrl,jdbcType=VARCHAR},",
          "s_ident = #{sIdent,jdbcType=VARCHAR},",
          "s_url = #{sUrl,jdbcType=VARCHAR},",
          "width = #{width,jdbcType=INTEGER},",
          "height = #{height,jdbcType=INTEGER},",
          "watermark = #{watermark,jdbcType=CHAR},",
          "last_modified = #{lastModified,jdbcType=INTEGER}",
        "where image_id = #{imageId,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(EcImage record);
}