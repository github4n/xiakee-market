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

import com.xiakee.domain.ecgoods.EcGoodsTypeSpec;

public interface EcGoodsTypeSpecDao {
    @Delete({
        "delete from sdb_b2c_goods_type_spec",
        "where spec_id = #{specId,jdbcType=INTEGER}",
          "and type_id = #{typeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("specId") Integer specId, @Param("typeId") Integer typeId);

    @Insert({
        "insert into sdb_b2c_goods_type_spec (spec_id, type_id, ",
        "spec_style, ordernum)",
        "values (#{specId,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER}, ",
        "#{specStyle,jdbcType=CHAR}, #{ordernum,jdbcType=INTEGER})"
    })
    int insert(EcGoodsTypeSpec record);

    @InsertProvider(type=EcGoodsTypeSpecSqlProvider.class, method="insertSelective")
    int insertSelective(EcGoodsTypeSpec record);

    @Select({
        "select",
        "spec_id, type_id, spec_style, ordernum",
        "from sdb_b2c_goods_type_spec",
        "where spec_id = #{specId,jdbcType=INTEGER}",
          "and type_id = #{typeId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="spec_id", property="specId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="spec_style", property="specStyle", jdbcType=JdbcType.CHAR),
        @Result(column="ordernum", property="ordernum", jdbcType=JdbcType.INTEGER)
    })
    EcGoodsTypeSpec selectByPrimaryKey(@Param("specId") Integer specId, @Param("typeId") Integer typeId);

    @UpdateProvider(type=EcGoodsTypeSpecSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(EcGoodsTypeSpec record);

    @Update({
        "update sdb_b2c_goods_type_spec",
        "set spec_style = #{specStyle,jdbcType=CHAR},",
          "ordernum = #{ordernum,jdbcType=INTEGER}",
        "where spec_id = #{specId,jdbcType=INTEGER}",
          "and type_id = #{typeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(EcGoodsTypeSpec record);
}