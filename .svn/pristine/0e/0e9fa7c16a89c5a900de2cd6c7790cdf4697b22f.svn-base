package com.xiakee.dao.sku;

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

import com.xiakee.domain.sku.ExchangeRate;

public interface ExchangeRateDao {
    @Delete({
        "delete from xiakee_exchange_rate",
        "where currency = #{currency,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String currency);

    @Insert({
        "insert into xiakee_exchange_rate (currency, zhcurrency, ",
        "value)",
        "values (#{currency,jdbcType=VARCHAR}, #{zhcurrency,jdbcType=VARCHAR}, ",
        "#{value,jdbcType=DECIMAL})"
    })
    int insert(ExchangeRate record);

    @InsertProvider(type=ExchangeRateSqlProvider.class, method="insertSelective")
    int insertSelective(ExchangeRate record);

    @Select({
        "select",
        "currency, zhcurrency, value",
        "from xiakee_exchange_rate",
        "where currency = #{currency,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="currency", property="currency", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="zhcurrency", property="zhcurrency", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.DECIMAL)
    })
    ExchangeRate selectByPrimaryKey(String currency);

    @UpdateProvider(type=ExchangeRateSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ExchangeRate record);

    @Update({
        "update xiakee_exchange_rate",
        "set zhcurrency = #{zhcurrency,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=DECIMAL}",
        "where currency = #{currency,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(ExchangeRate record);
    
    @Select("select * from xiakee_exchange_rate")
	@Results({ @Result(column = "currency", property = "currency", jdbcType = JdbcType.VARCHAR, id = true), @Result(column = "value", property = "value", jdbcType = JdbcType.DECIMAL) })
	List<ExchangeRate> selectByAll();
}