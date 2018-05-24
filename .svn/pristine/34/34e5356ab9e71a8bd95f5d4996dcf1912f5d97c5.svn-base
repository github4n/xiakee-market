package com.xiakee.ecdao.order;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.xiakee.domain.ecgoods.EcProducts;

public interface EcProductsDao {
	@Delete({ "delete from sdb_b2c_products", "where product_id = #{productId,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer productId);

	@Insert({ "insert into sdb_b2c_products (product_id, goods_id, ", "barcode, title, ", "bn, price, cost, ", "mktprice, name, ", "weight, unit, store, ", "store_place, freez, ", "goods_type, is_default, ", "qrcode_image_id, uptime, ",
			"last_modify, disabled, ", "marketable, spec_info, ", "spec_desc)", "values (#{productId,jdbcType=INTEGER}, #{goodsId,jdbcType=BIGINT}, ", "#{barcode,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
			"#{bn,jdbcType=VARCHAR}, #{price,jdbcType=DECIMAL}, #{cost,jdbcType=DECIMAL}, ", "#{mktprice,jdbcType=DECIMAL}, #{name,jdbcType=VARCHAR}, ", "#{weight,jdbcType=DECIMAL}, #{unit,jdbcType=VARCHAR}, #{store,jdbcType=INTEGER}, ",
			"#{storePlace,jdbcType=VARCHAR}, #{freez,jdbcType=INTEGER}, ", "#{goodsType,jdbcType=CHAR}, #{isDefault,jdbcType=CHAR}, ", "#{qrcodeImageId,jdbcType=VARCHAR}, #{uptime,jdbcType=INTEGER}, ",
			"#{lastModify,jdbcType=INTEGER}, #{disabled,jdbcType=CHAR}, ", "#{marketable,jdbcType=CHAR}, #{specInfo,jdbcType=LONGVARCHAR}, ", "#{specDesc,jdbcType=LONGVARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "productId")
	int insert(EcProducts record);

	@InsertProvider(type = EcProductsSqlProvider.class, method = "insertSelective")
	@Options(useGeneratedKeys = true, keyProperty = "productId")
	int insertSelective(EcProducts record);

	@Select({ "select", "product_id, goods_id, barcode, title, bn, price, cost, mktprice, name, weight, ", "unit, store, store_place, freez, goods_type, is_default, qrcode_image_id, uptime, ",
			"last_modify, disabled, marketable, spec_info, spec_desc", "from sdb_b2c_products", "where product_id = #{productId,jdbcType=INTEGER}" })
	@Results({ @Result(column = "product_id", property = "productId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "goods_id", property = "goodsId", jdbcType = JdbcType.BIGINT),
			@Result(column = "barcode", property = "barcode", jdbcType = JdbcType.VARCHAR), @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR), @Result(column = "bn", property = "bn", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL), @Result(column = "cost", property = "cost", jdbcType = JdbcType.DECIMAL), @Result(column = "mktprice", property = "mktprice", jdbcType = JdbcType.DECIMAL),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "weight", property = "weight", jdbcType = JdbcType.DECIMAL), @Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
			@Result(column = "store", property = "store", jdbcType = JdbcType.INTEGER), @Result(column = "store_place", property = "storePlace", jdbcType = JdbcType.VARCHAR),
			@Result(column = "freez", property = "freez", jdbcType = JdbcType.INTEGER), @Result(column = "goods_type", property = "goodsType", jdbcType = JdbcType.CHAR),
			@Result(column = "is_default", property = "isDefault", jdbcType = JdbcType.CHAR), @Result(column = "qrcode_image_id", property = "qrcodeImageId", jdbcType = JdbcType.VARCHAR),
			@Result(column = "uptime", property = "uptime", jdbcType = JdbcType.INTEGER), @Result(column = "last_modify", property = "lastModify", jdbcType = JdbcType.INTEGER),
			@Result(column = "disabled", property = "disabled", jdbcType = JdbcType.CHAR), @Result(column = "marketable", property = "marketable", jdbcType = JdbcType.CHAR),
			@Result(column = "spec_info", property = "specInfo", jdbcType = JdbcType.LONGVARCHAR), @Result(column = "spec_desc", property = "specDesc", jdbcType = JdbcType.LONGVARCHAR) })
	EcProducts selectByPrimaryKey(Integer productId);

	@UpdateProvider(type = EcProductsSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(EcProducts record);

	@Update({ "update sdb_b2c_products", "set goods_id = #{goodsId,jdbcType=BIGINT},", "barcode = #{barcode,jdbcType=VARCHAR},", "title = #{title,jdbcType=VARCHAR},", "bn = #{bn,jdbcType=VARCHAR},", "price = #{price,jdbcType=DECIMAL},",
			"cost = #{cost,jdbcType=DECIMAL},", "mktprice = #{mktprice,jdbcType=DECIMAL},", "name = #{name,jdbcType=VARCHAR},", "weight = #{weight,jdbcType=DECIMAL},", "unit = #{unit,jdbcType=VARCHAR},", "store = #{store,jdbcType=INTEGER},",
			"store_place = #{storePlace,jdbcType=VARCHAR},", "freez = #{freez,jdbcType=INTEGER},", "goods_type = #{goodsType,jdbcType=CHAR},", "is_default = #{isDefault,jdbcType=CHAR},", "qrcode_image_id = #{qrcodeImageId,jdbcType=VARCHAR},",
			"uptime = #{uptime,jdbcType=INTEGER},", "last_modify = #{lastModify,jdbcType=INTEGER},", "disabled = #{disabled,jdbcType=CHAR},", "marketable = #{marketable,jdbcType=CHAR},", "spec_info = #{specInfo,jdbcType=LONGVARCHAR},",
			"spec_desc = #{specDesc,jdbcType=LONGVARCHAR}", "where product_id = #{productId,jdbcType=INTEGER}" })
	int updateByPrimaryKeyWithBLOBs(EcProducts record);

	@Update({ "update sdb_b2c_products", "set goods_id = #{goodsId,jdbcType=BIGINT},", "barcode = #{barcode,jdbcType=VARCHAR},", "title = #{title,jdbcType=VARCHAR},", "bn = #{bn,jdbcType=VARCHAR},", "price = #{price,jdbcType=DECIMAL},",
			"cost = #{cost,jdbcType=DECIMAL},", "mktprice = #{mktprice,jdbcType=DECIMAL},", "name = #{name,jdbcType=VARCHAR},", "weight = #{weight,jdbcType=DECIMAL},", "unit = #{unit,jdbcType=VARCHAR},", "store = #{store,jdbcType=INTEGER},",
			"store_place = #{storePlace,jdbcType=VARCHAR},", "freez = #{freez,jdbcType=INTEGER},", "goods_type = #{goodsType,jdbcType=CHAR},", "is_default = #{isDefault,jdbcType=CHAR},", "qrcode_image_id = #{qrcodeImageId,jdbcType=VARCHAR},",
			"uptime = #{uptime,jdbcType=INTEGER},", "last_modify = #{lastModify,jdbcType=INTEGER},", "disabled = #{disabled,jdbcType=CHAR},", "marketable = #{marketable,jdbcType=CHAR}", "where product_id = #{productId,jdbcType=INTEGER}" })
	int updateByPrimaryKey(EcProducts record);

	@Select({ "select", "product_id, goods_id, barcode, title, bn, price, cost, mktprice, name, weight, ", "unit, store, store_place, freez, goods_type, is_default, qrcode_image_id, uptime, ",
			"last_modify, disabled, marketable, spec_info, spec_desc", "from sdb_b2c_products", "where bn = #{bn,jdbcType=VARCHAR}" })
	@Results({ @Result(column = "product_id", property = "productId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "goods_id", property = "goodsId", jdbcType = JdbcType.BIGINT),
			@Result(column = "barcode", property = "barcode", jdbcType = JdbcType.VARCHAR), @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR), @Result(column = "bn", property = "bn", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL), @Result(column = "cost", property = "cost", jdbcType = JdbcType.DECIMAL), @Result(column = "mktprice", property = "mktprice", jdbcType = JdbcType.DECIMAL),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "weight", property = "weight", jdbcType = JdbcType.DECIMAL), @Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
			@Result(column = "store", property = "store", jdbcType = JdbcType.INTEGER), @Result(column = "store_place", property = "storePlace", jdbcType = JdbcType.VARCHAR),
			@Result(column = "freez", property = "freez", jdbcType = JdbcType.INTEGER), @Result(column = "goods_type", property = "goodsType", jdbcType = JdbcType.CHAR),
			@Result(column = "is_default", property = "isDefault", jdbcType = JdbcType.CHAR), @Result(column = "qrcode_image_id", property = "qrcodeImageId", jdbcType = JdbcType.VARCHAR),
			@Result(column = "uptime", property = "uptime", jdbcType = JdbcType.INTEGER), @Result(column = "last_modify", property = "lastModify", jdbcType = JdbcType.INTEGER),
			@Result(column = "disabled", property = "disabled", jdbcType = JdbcType.CHAR), @Result(column = "marketable", property = "marketable", jdbcType = JdbcType.CHAR),
			@Result(column = "spec_info", property = "specInfo", jdbcType = JdbcType.LONGVARCHAR), @Result(column = "spec_desc", property = "specDesc", jdbcType = JdbcType.LONGVARCHAR) })
	EcProducts selectByBn(String bn);

	@Update({ "update sdb_b2c_products", "set price = #{price,jdbcType=DECIMAL},", "cost = #{cost,jdbcType=DECIMAL},", "marketable = #{marketable,jdbcType=CHAR},", "store = #{store,jdbcType=INTEGER}",
			"where bn = #{bn,jdbcType=VARCHAR}" })
	int updateByBn(EcProducts record);
	
	@Select("select product_id, goods_id, bn, price, store, freez, is_default from sdb_b2c_products where goods_id = #{goodsId,jdbcType=BIGINT}")
	@Results({ @Result(column = "product_id", property = "productId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "goods_id", property = "goodsId", jdbcType = JdbcType.BIGINT),
			@Result(column = "bn", property = "bn", jdbcType = JdbcType.VARCHAR), @Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
			@Result(column = "store", property = "store", jdbcType = JdbcType.INTEGER), @Result(column = "freez", property = "freez", jdbcType = JdbcType.INTEGER),
			@Result(column = "is_default", property = "isDefault", jdbcType = JdbcType.CHAR) })
	List<EcProducts> selectByGoodsId(Long goodsId);
}