package com.xiakee.ecdao.order;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.xiakee.domain.ecgoods.XiakeeHaulProcess;

public interface XiakeeHaulProcessMapper {
    @Delete({
        "delete from sdb_xiakee_haul_process",
        "where process_id = #{processId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer processId);

    @Insert({
        "insert into sdb_xiakee_haul_process (process_id, rel_id, ",
        "order_id, op_id, op_name, ",
        "alttime, bill_type, ",
        "behavior, result, log_text, ",
        "addon)",
        "values (#{processId,jdbcType=INTEGER}, #{relId,jdbcType=BIGINT}, ",
        "#{orderId,jdbcType=BIGINT}, #{opId,jdbcType=INTEGER}, #{opName,jdbcType=VARCHAR}, ",
        "#{alttime,jdbcType=INTEGER}, #{billType,jdbcType=CHAR}, ",
        "#{behavior,jdbcType=CHAR}, #{result,jdbcType=CHAR}, #{logText,jdbcType=LONGVARCHAR}, ",
        "#{addon,jdbcType=LONGVARCHAR})"
    })
    int insert(XiakeeHaulProcess record);

    @InsertProvider(type=XiakeeHaulProcessSqlProvider.class, method="insertSelective")
    int insertSelective(XiakeeHaulProcess record);

    @Select({
        "select",
        "process_id, rel_id, order_id, op_id, op_name, alttime, bill_type, behavior, ",
        "result, log_text, addon",
        "from sdb_xiakee_haul_process",
        "where process_id = #{processId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="process_id", property="processId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rel_id", property="relId", jdbcType=JdbcType.BIGINT),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="op_id", property="opId", jdbcType=JdbcType.INTEGER),
        @Result(column="op_name", property="opName", jdbcType=JdbcType.VARCHAR),
        @Result(column="alttime", property="alttime", jdbcType=JdbcType.INTEGER),
        @Result(column="bill_type", property="billType", jdbcType=JdbcType.CHAR),
        @Result(column="behavior", property="behavior", jdbcType=JdbcType.CHAR),
        @Result(column="result", property="result", jdbcType=JdbcType.CHAR),
        @Result(column="log_text", property="logText", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="addon", property="addon", jdbcType=JdbcType.LONGVARCHAR)
    })
    XiakeeHaulProcess selectByPrimaryKey(Integer processId);

    @UpdateProvider(type=XiakeeHaulProcessSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(XiakeeHaulProcess record);

    @Update({
        "update sdb_xiakee_haul_process",
        "set rel_id = #{relId,jdbcType=BIGINT},",
          "order_id = #{orderId,jdbcType=BIGINT},",
          "op_id = #{opId,jdbcType=INTEGER},",
          "op_name = #{opName,jdbcType=VARCHAR},",
          "alttime = #{alttime,jdbcType=INTEGER},",
          "bill_type = #{billType,jdbcType=CHAR},",
          "behavior = #{behavior,jdbcType=CHAR},",
          "result = #{result,jdbcType=CHAR},",
          "log_text = #{logText,jdbcType=LONGVARCHAR},",
          "addon = #{addon,jdbcType=LONGVARCHAR}",
        "where process_id = #{processId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(XiakeeHaulProcess record);

    @Update({
        "update sdb_xiakee_haul_process",
        "set rel_id = #{relId,jdbcType=BIGINT},",
          "order_id = #{orderId,jdbcType=BIGINT},",
          "op_id = #{opId,jdbcType=INTEGER},",
          "op_name = #{opName,jdbcType=VARCHAR},",
          "alttime = #{alttime,jdbcType=INTEGER},",
          "bill_type = #{billType,jdbcType=CHAR},",
          "behavior = #{behavior,jdbcType=CHAR},",
          "result = #{result,jdbcType=CHAR}",
        "where process_id = #{processId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(XiakeeHaulProcess record);
}