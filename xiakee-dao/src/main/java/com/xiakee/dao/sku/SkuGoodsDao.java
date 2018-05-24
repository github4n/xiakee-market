package com.xiakee.dao.sku;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.xiakee.domain.sku.SkuGoodsBean;
import com.xiakee.domain.sku.SkuPriceDownBean;

public interface SkuGoodsDao {

	@Delete({ "delete from sku_goods", "where sku_code = #{skuCode,jdbcType=VARCHAR}" })
	int deleteBySkuCode(String skuCode);

	@Delete({ "delete from sku_goods", "where goods_no = #{goodsNo,jdbcType=VARCHAR}" })
	int deleteByPrimaryKey(String goodsNo);

	@Insert({ "insert into sku_goods (goods_no, sku_code, ", "color, size, image, ", "price_increase, total_store)", "values (#{goodsNo,jdbcType=VARCHAR}, #{skuCode,jdbcType=VARCHAR}, ",
			"#{color,jdbcType=VARCHAR}, #{size,jdbcType=VARCHAR}, #{image,jdbcType=VARCHAR}, ", "#{priceIncrease,jdbcType=INTEGER}, #{totalStore,jdbcType=INTEGER})" })
	int insert(SkuGoodsBean record);

	@InsertProvider(type = SkuGoodsBeanSqlProvider.class, method = "insertSelective")
	int insertSelective(SkuGoodsBean record);

	@Select({ "select", "goods_no, sku_code, color, size, image, price_increase, total_store", "from sku_goods", "where goods_no = #{goodsNo,jdbcType=VARCHAR}" })
	@Results({ @Result(column = "goods_no", property = "goodsNo", jdbcType = JdbcType.VARCHAR, id = true), @Result(column = "sku_code", property = "skuCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "color", property = "color", jdbcType = JdbcType.VARCHAR), @Result(column = "size", property = "size", jdbcType = JdbcType.VARCHAR), @Result(column = "image", property = "image", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price_increase", property = "priceIncrease", jdbcType = JdbcType.INTEGER), @Result(column = "total_store", property = "totalStore", jdbcType = JdbcType.INTEGER) })
	SkuGoodsBean selectByPrimaryKey(String goodsNo);

	@UpdateProvider(type = SkuGoodsBeanSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(SkuGoodsBean record);

	@Update({ "update sku_goods", "set sku_code = #{skuCode,jdbcType=VARCHAR},", "color = #{color,jdbcType=VARCHAR},", "size = #{size,jdbcType=VARCHAR},", "image = #{image,jdbcType=VARCHAR},", "price_increase = #{priceIncrease,jdbcType=INTEGER},",
			"total_store = #{totalStore,jdbcType=INTEGER}", "where goods_no = #{goodsNo,jdbcType=VARCHAR}" })
	int updateByPrimaryKey(SkuGoodsBean record);

	@SelectProvider(type = SkuGoodsBeanSqlProvider.class, method = "selectBySkuCodeAndSpec")
	@Results({ @Result(column = "goods_no", property = "goodsNo", jdbcType = JdbcType.VARCHAR, id = true), @Result(column = "sku_code", property = "skuCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "color", property = "color", jdbcType = JdbcType.VARCHAR), @Result(column = "size", property = "size", jdbcType = JdbcType.VARCHAR), @Result(column = "image", property = "image", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price_increase", property = "priceIncrease", jdbcType = JdbcType.INTEGER), @Result(column = "total_store", property = "totalStore", jdbcType = JdbcType.INTEGER) })
	List<SkuGoodsBean> selectBySkuCodeAndSpec(SkuGoodsBean record);

	@Select("select * from sku_goods where sku_code = #{skuCode,jdbcType=VARCHAR}")
	@Results({ @Result(column = "goods_no", property = "goodsNo", jdbcType = JdbcType.VARCHAR, id = true), @Result(column = "sku_code", property = "skuCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "color", property = "color", jdbcType = JdbcType.VARCHAR), @Result(column = "size", property = "size", jdbcType = JdbcType.VARCHAR), @Result(column = "image", property = "image", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price_increase", property = "priceIncrease", jdbcType = JdbcType.INTEGER), @Result(column = "total_store", property = "totalStore", jdbcType = JdbcType.INTEGER) })
	List<SkuGoodsBean> selectBySkuCode(String skuCode);

	@Select("select * from sku_goods")
	@Results({ @Result(column = "goods_no", property = "goodsNo", jdbcType = JdbcType.VARCHAR, id = true), @Result(column = "sku_code", property = "skuCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "color", property = "color", jdbcType = JdbcType.VARCHAR), @Result(column = "size", property = "size", jdbcType = JdbcType.VARCHAR), @Result(column = "image", property = "image", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price_increase", property = "priceIncrease", jdbcType = JdbcType.INTEGER), @Result(column = "total_store", property = "totalStore", jdbcType = JdbcType.INTEGER) })
	List<SkuGoodsBean> selectByAll();
	
	@SelectProvider(type = SkuGoodsBeanSqlProvider.class, method = "selectByPriceDown_page")
	@Results({ @Result(column = "goods_no", property = "goodsNo", jdbcType = JdbcType.VARCHAR, id = true), @Result(column = "sku_code", property = "skuCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "color", property = "color", jdbcType = JdbcType.VARCHAR), @Result(column = "size", property = "size", jdbcType = JdbcType.VARCHAR), @Result(column = "image", property = "image", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price_increase", property = "priceIncrease", jdbcType = JdbcType.INTEGER), @Result(column = "total_store", property = "totalStore", jdbcType = JdbcType.INTEGER) })
	List<SkuPriceDownBean> selectByPriceDown_page(Map<String, Object> params);

}