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

import com.xiakee.domain.ecgoods.EcGoods;

public interface EcGoodsDao {
	@Delete({ "delete from sdb_b2c_goods", "where goods_id = #{goodsId,jdbcType=BIGINT}" })
	int deleteByPrimaryKey(Long goodsId);

	@Insert({ "insert into sdb_b2c_goods (goods_id, bn, ", "name, price, type_id, ", "cat_id, brand_id, ", "marketable, store, ", "notify_num, uptime, ", "downtime, last_modify, ", "p_order, d_order, ", "score, cost, mktprice, ",
			"weight, unit, brief, ", "goods_type, image_default_id, ", "udfimg, thumbnail_pic, ", "small_pic, big_pic, ", "store_place, min_buy, ", "package_scale, package_unit, ", "package_use, score_setting, ", "store_prompt, nostore_sell, ",
			"disabled, rank_count, ", "comments_count, view_w_count, ", "view_count, buy_count, ", "buy_w_count, p_1, p_2, ", "p_3, p_4, p_5, ", "p_6, p_7, p_8, ", "p_9, p_10, p_11, ", "p_12, p_13, p_14, ", "p_15, p_16, p_17, ",
			"p_18, p_19, p_20, ", "p_21, p_22, p_23, ", "p_24, p_25, p_26, ", "p_27, p_28, p_29, ", "p_30, p_31, p_32, ", "p_33, p_34, p_35, ", "p_36, p_37, p_38, ", "p_39, p_40, p_41, ", "p_42, p_43, p_44, ", "p_45, p_46, p_47, ",
			"p_48, p_49, p_50, ", "intro, goods_setting, ", "spec_desc, params, ", "count_stat)", "values (#{goodsId,jdbcType=BIGINT}, #{bn,jdbcType=VARCHAR}, ", "#{name,jdbcType=VARCHAR}, #{price,jdbcType=DECIMAL}, #{typeId,jdbcType=INTEGER}, ",
			"#{catId,jdbcType=INTEGER}, #{brandId,jdbcType=INTEGER}, ", "#{marketable,jdbcType=CHAR}, #{store,jdbcType=INTEGER}, ", "#{notifyNum,jdbcType=INTEGER}, #{uptime,jdbcType=INTEGER}, ",
			"#{downtime,jdbcType=INTEGER}, #{lastModify,jdbcType=INTEGER}, ", "#{pOrder,jdbcType=INTEGER}, #{dOrder,jdbcType=INTEGER}, ", "#{score,jdbcType=INTEGER}, #{cost,jdbcType=DECIMAL}, #{mktprice,jdbcType=DECIMAL}, ",
			"#{weight,jdbcType=DECIMAL}, #{unit,jdbcType=VARCHAR}, #{brief,jdbcType=VARCHAR}, ", "#{goodsType,jdbcType=CHAR}, #{imageDefaultId,jdbcType=VARCHAR}, ", "#{udfimg,jdbcType=CHAR}, #{thumbnailPic,jdbcType=VARCHAR}, ",
			"#{smallPic,jdbcType=VARCHAR}, #{bigPic,jdbcType=VARCHAR}, ", "#{storePlace,jdbcType=VARCHAR}, #{minBuy,jdbcType=INTEGER}, ", "#{packageScale,jdbcType=DECIMAL}, #{packageUnit,jdbcType=VARCHAR}, ",
			"#{packageUse,jdbcType=CHAR}, #{scoreSetting,jdbcType=CHAR}, ", "#{storePrompt,jdbcType=INTEGER}, #{nostoreSell,jdbcType=CHAR}, ", "#{disabled,jdbcType=CHAR}, #{rankCount,jdbcType=INTEGER}, ",
			"#{commentsCount,jdbcType=INTEGER}, #{viewWCount,jdbcType=INTEGER}, ", "#{viewCount,jdbcType=INTEGER}, #{buyCount,jdbcType=INTEGER}, ", "#{buyWCount,jdbcType=INTEGER}, #{p1,jdbcType=INTEGER}, #{p2,jdbcType=INTEGER}, ",
			"#{p3,jdbcType=INTEGER}, #{p4,jdbcType=INTEGER}, #{p5,jdbcType=INTEGER}, ", "#{p6,jdbcType=INTEGER}, #{p7,jdbcType=INTEGER}, #{p8,jdbcType=INTEGER}, ", "#{p9,jdbcType=INTEGER}, #{p10,jdbcType=INTEGER}, #{p11,jdbcType=INTEGER}, ",
			"#{p12,jdbcType=INTEGER}, #{p13,jdbcType=INTEGER}, #{p14,jdbcType=INTEGER}, ", "#{p15,jdbcType=INTEGER}, #{p16,jdbcType=INTEGER}, #{p17,jdbcType=INTEGER}, ", "#{p18,jdbcType=INTEGER}, #{p19,jdbcType=INTEGER}, #{p20,jdbcType=INTEGER}, ",
			"#{p21,jdbcType=VARCHAR}, #{p22,jdbcType=VARCHAR}, #{p23,jdbcType=VARCHAR}, ", "#{p24,jdbcType=VARCHAR}, #{p25,jdbcType=VARCHAR}, #{p26,jdbcType=VARCHAR}, ", "#{p27,jdbcType=VARCHAR}, #{p28,jdbcType=VARCHAR}, #{p29,jdbcType=VARCHAR}, ",
			"#{p30,jdbcType=VARCHAR}, #{p31,jdbcType=VARCHAR}, #{p32,jdbcType=VARCHAR}, ", "#{p33,jdbcType=VARCHAR}, #{p34,jdbcType=VARCHAR}, #{p35,jdbcType=VARCHAR}, ", "#{p36,jdbcType=VARCHAR}, #{p37,jdbcType=VARCHAR}, #{p38,jdbcType=VARCHAR}, ",
			"#{p39,jdbcType=VARCHAR}, #{p40,jdbcType=VARCHAR}, #{p41,jdbcType=VARCHAR}, ", "#{p42,jdbcType=VARCHAR}, #{p43,jdbcType=VARCHAR}, #{p44,jdbcType=VARCHAR}, ", "#{p45,jdbcType=VARCHAR}, #{p46,jdbcType=VARCHAR}, #{p47,jdbcType=VARCHAR}, ",
			"#{p48,jdbcType=VARCHAR}, #{p49,jdbcType=VARCHAR}, #{p50,jdbcType=VARCHAR}, ", "#{intro,jdbcType=LONGVARCHAR}, #{goodsSetting,jdbcType=LONGVARCHAR}, ", "#{specDesc,jdbcType=LONGVARCHAR}, #{params,jdbcType=LONGVARCHAR}, ",
			"#{countStat,jdbcType=LONGVARCHAR})" })
	int insert(EcGoods record);

	@InsertProvider(type = EcGoodsSqlProvider.class, method = "insertSelective")
	@Options(useGeneratedKeys = true, keyProperty = "goodsId")
	int insertSelective(EcGoods record);

	@Select({ "select", "goods_id, bn, name, price, type_id, cat_id, brand_id, marketable, store, notify_num, ", "uptime, downtime, last_modify, p_order, d_order, score, cost, mktprice, weight, ",
			"unit, brief, goods_type, image_default_id, udfimg, thumbnail_pic, small_pic, ", "big_pic, store_place, min_buy, package_scale, package_unit, package_use, score_setting, ",
			"store_prompt, nostore_sell, disabled, rank_count, comments_count, view_w_count, ", "view_count, buy_count, buy_w_count, p_1, p_2, p_3, p_4, p_5, p_6, p_7, p_8, ",
			"p_9, p_10, p_11, p_12, p_13, p_14, p_15, p_16, p_17, p_18, p_19, p_20, p_21, ", "p_22, p_23, p_24, p_25, p_26, p_27, p_28, p_29, p_30, p_31, p_32, p_33, p_34, ",
			"p_35, p_36, p_37, p_38, p_39, p_40, p_41, p_42, p_43, p_44, p_45, p_46, p_47, ", "p_48, p_49, p_50, intro, goods_setting, spec_desc, params, count_stat", "from sdb_b2c_goods", "where goods_id = #{goodsId,jdbcType=BIGINT}" })
	@Results({ @Result(column = "goods_id", property = "goodsId", jdbcType = JdbcType.BIGINT, id = true), @Result(column = "bn", property = "bn", jdbcType = JdbcType.VARCHAR), @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL), @Result(column = "type_id", property = "typeId", jdbcType = JdbcType.INTEGER), @Result(column = "cat_id", property = "catId", jdbcType = JdbcType.INTEGER),
			@Result(column = "brand_id", property = "brandId", jdbcType = JdbcType.INTEGER), @Result(column = "marketable", property = "marketable", jdbcType = JdbcType.CHAR),
			@Result(column = "store", property = "store", jdbcType = JdbcType.INTEGER), @Result(column = "notify_num", property = "notifyNum", jdbcType = JdbcType.INTEGER),
			@Result(column = "uptime", property = "uptime", jdbcType = JdbcType.INTEGER), @Result(column = "downtime", property = "downtime", jdbcType = JdbcType.INTEGER),
			@Result(column = "last_modify", property = "lastModify", jdbcType = JdbcType.INTEGER), @Result(column = "p_order", property = "pOrder", jdbcType = JdbcType.INTEGER),
			@Result(column = "d_order", property = "dOrder", jdbcType = JdbcType.INTEGER), @Result(column = "score", property = "score", jdbcType = JdbcType.INTEGER), @Result(column = "cost", property = "cost", jdbcType = JdbcType.DECIMAL),
			@Result(column = "mktprice", property = "mktprice", jdbcType = JdbcType.DECIMAL), @Result(column = "weight", property = "weight", jdbcType = JdbcType.DECIMAL), @Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
			@Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR), @Result(column = "goods_type", property = "goodsType", jdbcType = JdbcType.CHAR),
			@Result(column = "image_default_id", property = "imageDefaultId", jdbcType = JdbcType.VARCHAR), @Result(column = "udfimg", property = "udfimg", jdbcType = JdbcType.CHAR),
			@Result(column = "thumbnail_pic", property = "thumbnailPic", jdbcType = JdbcType.VARCHAR), @Result(column = "small_pic", property = "smallPic", jdbcType = JdbcType.VARCHAR),
			@Result(column = "big_pic", property = "bigPic", jdbcType = JdbcType.VARCHAR), @Result(column = "store_place", property = "storePlace", jdbcType = JdbcType.VARCHAR),
			@Result(column = "min_buy", property = "minBuy", jdbcType = JdbcType.INTEGER), @Result(column = "package_scale", property = "packageScale", jdbcType = JdbcType.DECIMAL),
			@Result(column = "package_unit", property = "packageUnit", jdbcType = JdbcType.VARCHAR), @Result(column = "package_use", property = "packageUse", jdbcType = JdbcType.CHAR),
			@Result(column = "score_setting", property = "scoreSetting", jdbcType = JdbcType.CHAR), @Result(column = "store_prompt", property = "storePrompt", jdbcType = JdbcType.INTEGER),
			@Result(column = "nostore_sell", property = "nostoreSell", jdbcType = JdbcType.CHAR), @Result(column = "disabled", property = "disabled", jdbcType = JdbcType.CHAR),
			@Result(column = "rank_count", property = "rankCount", jdbcType = JdbcType.INTEGER), @Result(column = "comments_count", property = "commentsCount", jdbcType = JdbcType.INTEGER),
			@Result(column = "view_w_count", property = "viewWCount", jdbcType = JdbcType.INTEGER), @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
			@Result(column = "buy_count", property = "buyCount", jdbcType = JdbcType.INTEGER), @Result(column = "buy_w_count", property = "buyWCount", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_1", property = "p1", jdbcType = JdbcType.INTEGER), @Result(column = "p_2", property = "p2", jdbcType = JdbcType.INTEGER), @Result(column = "p_3", property = "p3", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_4", property = "p4", jdbcType = JdbcType.INTEGER), @Result(column = "p_5", property = "p5", jdbcType = JdbcType.INTEGER), @Result(column = "p_6", property = "p6", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_7", property = "p7", jdbcType = JdbcType.INTEGER), @Result(column = "p_8", property = "p8", jdbcType = JdbcType.INTEGER), @Result(column = "p_9", property = "p9", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_10", property = "p10", jdbcType = JdbcType.INTEGER), @Result(column = "p_11", property = "p11", jdbcType = JdbcType.INTEGER), @Result(column = "p_12", property = "p12", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_13", property = "p13", jdbcType = JdbcType.INTEGER), @Result(column = "p_14", property = "p14", jdbcType = JdbcType.INTEGER), @Result(column = "p_15", property = "p15", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_16", property = "p16", jdbcType = JdbcType.INTEGER), @Result(column = "p_17", property = "p17", jdbcType = JdbcType.INTEGER), @Result(column = "p_18", property = "p18", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_19", property = "p19", jdbcType = JdbcType.INTEGER), @Result(column = "p_20", property = "p20", jdbcType = JdbcType.INTEGER), @Result(column = "p_21", property = "p21", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_22", property = "p22", jdbcType = JdbcType.VARCHAR), @Result(column = "p_23", property = "p23", jdbcType = JdbcType.VARCHAR), @Result(column = "p_24", property = "p24", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_25", property = "p25", jdbcType = JdbcType.VARCHAR), @Result(column = "p_26", property = "p26", jdbcType = JdbcType.VARCHAR), @Result(column = "p_27", property = "p27", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_28", property = "p28", jdbcType = JdbcType.VARCHAR), @Result(column = "p_29", property = "p29", jdbcType = JdbcType.VARCHAR), @Result(column = "p_30", property = "p30", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_31", property = "p31", jdbcType = JdbcType.VARCHAR), @Result(column = "p_32", property = "p32", jdbcType = JdbcType.VARCHAR), @Result(column = "p_33", property = "p33", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_34", property = "p34", jdbcType = JdbcType.VARCHAR), @Result(column = "p_35", property = "p35", jdbcType = JdbcType.VARCHAR), @Result(column = "p_36", property = "p36", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_37", property = "p37", jdbcType = JdbcType.VARCHAR), @Result(column = "p_38", property = "p38", jdbcType = JdbcType.VARCHAR), @Result(column = "p_39", property = "p39", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_40", property = "p40", jdbcType = JdbcType.VARCHAR), @Result(column = "p_41", property = "p41", jdbcType = JdbcType.VARCHAR), @Result(column = "p_42", property = "p42", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_43", property = "p43", jdbcType = JdbcType.VARCHAR), @Result(column = "p_44", property = "p44", jdbcType = JdbcType.VARCHAR), @Result(column = "p_45", property = "p45", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_46", property = "p46", jdbcType = JdbcType.VARCHAR), @Result(column = "p_47", property = "p47", jdbcType = JdbcType.VARCHAR), @Result(column = "p_48", property = "p48", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_49", property = "p49", jdbcType = JdbcType.VARCHAR), @Result(column = "p_50", property = "p50", jdbcType = JdbcType.VARCHAR), @Result(column = "intro", property = "intro", jdbcType = JdbcType.LONGVARCHAR),
			@Result(column = "goods_setting", property = "goodsSetting", jdbcType = JdbcType.LONGVARCHAR), @Result(column = "spec_desc", property = "specDesc", jdbcType = JdbcType.LONGVARCHAR),
			@Result(column = "params", property = "params", jdbcType = JdbcType.LONGVARCHAR), @Result(column = "count_stat", property = "countStat", jdbcType = JdbcType.LONGVARCHAR) })
	EcGoods selectByPrimaryKey(Long goodsId);

	@UpdateProvider(type = EcGoodsSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(EcGoods record);

	@Update({ "update sdb_b2c_goods", "set bn = #{bn,jdbcType=VARCHAR},", "name = #{name,jdbcType=VARCHAR},", "price = #{price,jdbcType=DECIMAL},", "type_id = #{typeId,jdbcType=INTEGER},", "cat_id = #{catId,jdbcType=INTEGER},",
			"brand_id = #{brandId,jdbcType=INTEGER},", "marketable = #{marketable,jdbcType=CHAR},", "store = #{store,jdbcType=INTEGER},", "notify_num = #{notifyNum,jdbcType=INTEGER},", "uptime = #{uptime,jdbcType=INTEGER},",
			"downtime = #{downtime,jdbcType=INTEGER},", "last_modify = #{lastModify,jdbcType=INTEGER},", "p_order = #{pOrder,jdbcType=INTEGER},", "d_order = #{dOrder,jdbcType=INTEGER},", "score = #{score,jdbcType=INTEGER},",
			"cost = #{cost,jdbcType=DECIMAL},", "mktprice = #{mktprice,jdbcType=DECIMAL},", "weight = #{weight,jdbcType=DECIMAL},", "unit = #{unit,jdbcType=VARCHAR},", "brief = #{brief,jdbcType=VARCHAR},", "goods_type = #{goodsType,jdbcType=CHAR},",
			"image_default_id = #{imageDefaultId,jdbcType=VARCHAR},", "udfimg = #{udfimg,jdbcType=CHAR},", "thumbnail_pic = #{thumbnailPic,jdbcType=VARCHAR},", "small_pic = #{smallPic,jdbcType=VARCHAR},", "big_pic = #{bigPic,jdbcType=VARCHAR},",
			"store_place = #{storePlace,jdbcType=VARCHAR},", "min_buy = #{minBuy,jdbcType=INTEGER},", "package_scale = #{packageScale,jdbcType=DECIMAL},", "package_unit = #{packageUnit,jdbcType=VARCHAR},",
			"package_use = #{packageUse,jdbcType=CHAR},", "score_setting = #{scoreSetting,jdbcType=CHAR},", "store_prompt = #{storePrompt,jdbcType=INTEGER},", "nostore_sell = #{nostoreSell,jdbcType=CHAR},", "disabled = #{disabled,jdbcType=CHAR},",
			"rank_count = #{rankCount,jdbcType=INTEGER},", "comments_count = #{commentsCount,jdbcType=INTEGER},", "view_w_count = #{viewWCount,jdbcType=INTEGER},", "view_count = #{viewCount,jdbcType=INTEGER},",
			"buy_count = #{buyCount,jdbcType=INTEGER},", "buy_w_count = #{buyWCount,jdbcType=INTEGER},", "p_1 = #{p1,jdbcType=INTEGER},", "p_2 = #{p2,jdbcType=INTEGER},", "p_3 = #{p3,jdbcType=INTEGER},", "p_4 = #{p4,jdbcType=INTEGER},",
			"p_5 = #{p5,jdbcType=INTEGER},", "p_6 = #{p6,jdbcType=INTEGER},", "p_7 = #{p7,jdbcType=INTEGER},", "p_8 = #{p8,jdbcType=INTEGER},", "p_9 = #{p9,jdbcType=INTEGER},", "p_10 = #{p10,jdbcType=INTEGER},", "p_11 = #{p11,jdbcType=INTEGER},",
			"p_12 = #{p12,jdbcType=INTEGER},", "p_13 = #{p13,jdbcType=INTEGER},", "p_14 = #{p14,jdbcType=INTEGER},", "p_15 = #{p15,jdbcType=INTEGER},", "p_16 = #{p16,jdbcType=INTEGER},", "p_17 = #{p17,jdbcType=INTEGER},",
			"p_18 = #{p18,jdbcType=INTEGER},", "p_19 = #{p19,jdbcType=INTEGER},", "p_20 = #{p20,jdbcType=INTEGER},", "p_21 = #{p21,jdbcType=VARCHAR},", "p_22 = #{p22,jdbcType=VARCHAR},", "p_23 = #{p23,jdbcType=VARCHAR},",
			"p_24 = #{p24,jdbcType=VARCHAR},", "p_25 = #{p25,jdbcType=VARCHAR},", "p_26 = #{p26,jdbcType=VARCHAR},", "p_27 = #{p27,jdbcType=VARCHAR},", "p_28 = #{p28,jdbcType=VARCHAR},", "p_29 = #{p29,jdbcType=VARCHAR},",
			"p_30 = #{p30,jdbcType=VARCHAR},", "p_31 = #{p31,jdbcType=VARCHAR},", "p_32 = #{p32,jdbcType=VARCHAR},", "p_33 = #{p33,jdbcType=VARCHAR},", "p_34 = #{p34,jdbcType=VARCHAR},", "p_35 = #{p35,jdbcType=VARCHAR},",
			"p_36 = #{p36,jdbcType=VARCHAR},", "p_37 = #{p37,jdbcType=VARCHAR},", "p_38 = #{p38,jdbcType=VARCHAR},", "p_39 = #{p39,jdbcType=VARCHAR},", "p_40 = #{p40,jdbcType=VARCHAR},", "p_41 = #{p41,jdbcType=VARCHAR},",
			"p_42 = #{p42,jdbcType=VARCHAR},", "p_43 = #{p43,jdbcType=VARCHAR},", "p_44 = #{p44,jdbcType=VARCHAR},", "p_45 = #{p45,jdbcType=VARCHAR},", "p_46 = #{p46,jdbcType=VARCHAR},", "p_47 = #{p47,jdbcType=VARCHAR},",
			"p_48 = #{p48,jdbcType=VARCHAR},", "p_49 = #{p49,jdbcType=VARCHAR},", "p_50 = #{p50,jdbcType=VARCHAR},", "intro = #{intro,jdbcType=LONGVARCHAR},", "goods_setting = #{goodsSetting,jdbcType=LONGVARCHAR},",
			"spec_desc = #{specDesc,jdbcType=LONGVARCHAR},", "params = #{params,jdbcType=LONGVARCHAR},", "count_stat = #{countStat,jdbcType=LONGVARCHAR}", "where goods_id = #{goodsId,jdbcType=BIGINT}" })
	int updateByPrimaryKeyWithBLOBs(EcGoods record);

	@Update({ "update sdb_b2c_goods", "set bn = #{bn,jdbcType=VARCHAR},", "name = #{name,jdbcType=VARCHAR},", "price = #{price,jdbcType=DECIMAL},", "type_id = #{typeId,jdbcType=INTEGER},", "cat_id = #{catId,jdbcType=INTEGER},",
			"brand_id = #{brandId,jdbcType=INTEGER},", "marketable = #{marketable,jdbcType=CHAR},", "store = #{store,jdbcType=INTEGER},", "notify_num = #{notifyNum,jdbcType=INTEGER},", "uptime = #{uptime,jdbcType=INTEGER},",
			"downtime = #{downtime,jdbcType=INTEGER},", "last_modify = #{lastModify,jdbcType=INTEGER},", "p_order = #{pOrder,jdbcType=INTEGER},", "d_order = #{dOrder,jdbcType=INTEGER},", "score = #{score,jdbcType=INTEGER},",
			"cost = #{cost,jdbcType=DECIMAL},", "mktprice = #{mktprice,jdbcType=DECIMAL},", "weight = #{weight,jdbcType=DECIMAL},", "unit = #{unit,jdbcType=VARCHAR},", "brief = #{brief,jdbcType=VARCHAR},", "goods_type = #{goodsType,jdbcType=CHAR},",
			"image_default_id = #{imageDefaultId,jdbcType=VARCHAR},", "udfimg = #{udfimg,jdbcType=CHAR},", "thumbnail_pic = #{thumbnailPic,jdbcType=VARCHAR},", "small_pic = #{smallPic,jdbcType=VARCHAR},", "big_pic = #{bigPic,jdbcType=VARCHAR},",
			"store_place = #{storePlace,jdbcType=VARCHAR},", "min_buy = #{minBuy,jdbcType=INTEGER},", "package_scale = #{packageScale,jdbcType=DECIMAL},", "package_unit = #{packageUnit,jdbcType=VARCHAR},",
			"package_use = #{packageUse,jdbcType=CHAR},", "score_setting = #{scoreSetting,jdbcType=CHAR},", "store_prompt = #{storePrompt,jdbcType=INTEGER},", "nostore_sell = #{nostoreSell,jdbcType=CHAR},", "disabled = #{disabled,jdbcType=CHAR},",
			"rank_count = #{rankCount,jdbcType=INTEGER},", "comments_count = #{commentsCount,jdbcType=INTEGER},", "view_w_count = #{viewWCount,jdbcType=INTEGER},", "view_count = #{viewCount,jdbcType=INTEGER},",
			"buy_count = #{buyCount,jdbcType=INTEGER},", "buy_w_count = #{buyWCount,jdbcType=INTEGER},", "p_1 = #{p1,jdbcType=INTEGER},", "p_2 = #{p2,jdbcType=INTEGER},", "p_3 = #{p3,jdbcType=INTEGER},", "p_4 = #{p4,jdbcType=INTEGER},",
			"p_5 = #{p5,jdbcType=INTEGER},", "p_6 = #{p6,jdbcType=INTEGER},", "p_7 = #{p7,jdbcType=INTEGER},", "p_8 = #{p8,jdbcType=INTEGER},", "p_9 = #{p9,jdbcType=INTEGER},", "p_10 = #{p10,jdbcType=INTEGER},", "p_11 = #{p11,jdbcType=INTEGER},",
			"p_12 = #{p12,jdbcType=INTEGER},", "p_13 = #{p13,jdbcType=INTEGER},", "p_14 = #{p14,jdbcType=INTEGER},", "p_15 = #{p15,jdbcType=INTEGER},", "p_16 = #{p16,jdbcType=INTEGER},", "p_17 = #{p17,jdbcType=INTEGER},",
			"p_18 = #{p18,jdbcType=INTEGER},", "p_19 = #{p19,jdbcType=INTEGER},", "p_20 = #{p20,jdbcType=INTEGER},", "p_21 = #{p21,jdbcType=VARCHAR},", "p_22 = #{p22,jdbcType=VARCHAR},", "p_23 = #{p23,jdbcType=VARCHAR},",
			"p_24 = #{p24,jdbcType=VARCHAR},", "p_25 = #{p25,jdbcType=VARCHAR},", "p_26 = #{p26,jdbcType=VARCHAR},", "p_27 = #{p27,jdbcType=VARCHAR},", "p_28 = #{p28,jdbcType=VARCHAR},", "p_29 = #{p29,jdbcType=VARCHAR},",
			"p_30 = #{p30,jdbcType=VARCHAR},", "p_31 = #{p31,jdbcType=VARCHAR},", "p_32 = #{p32,jdbcType=VARCHAR},", "p_33 = #{p33,jdbcType=VARCHAR},", "p_34 = #{p34,jdbcType=VARCHAR},", "p_35 = #{p35,jdbcType=VARCHAR},",
			"p_36 = #{p36,jdbcType=VARCHAR},", "p_37 = #{p37,jdbcType=VARCHAR},", "p_38 = #{p38,jdbcType=VARCHAR},", "p_39 = #{p39,jdbcType=VARCHAR},", "p_40 = #{p40,jdbcType=VARCHAR},", "p_41 = #{p41,jdbcType=VARCHAR},",
			"p_42 = #{p42,jdbcType=VARCHAR},", "p_43 = #{p43,jdbcType=VARCHAR},", "p_44 = #{p44,jdbcType=VARCHAR},", "p_45 = #{p45,jdbcType=VARCHAR},", "p_46 = #{p46,jdbcType=VARCHAR},", "p_47 = #{p47,jdbcType=VARCHAR},",
			"p_48 = #{p48,jdbcType=VARCHAR},", "p_49 = #{p49,jdbcType=VARCHAR},", "p_50 = #{p50,jdbcType=VARCHAR}", "where goods_id = #{goodsId,jdbcType=BIGINT}" })
	int updateByPrimaryKey(EcGoods record);

	@Select({ "select", "goods_id, bn, name, price, type_id, cat_id, brand_id, marketable, store, notify_num, ", "uptime, downtime, last_modify, p_order, d_order, score, cost, mktprice, weight, ",
			"unit, brief, goods_type, image_default_id, udfimg, thumbnail_pic, small_pic, ", "big_pic, store_place, min_buy, package_scale, package_unit, package_use, score_setting, ",
			"store_prompt, nostore_sell, disabled, rank_count, comments_count, view_w_count, ", "view_count, buy_count, buy_w_count, p_1, p_2, p_3, p_4, p_5, p_6, p_7, p_8, ",
			"p_9, p_10, p_11, p_12, p_13, p_14, p_15, p_16, p_17, p_18, p_19, p_20, p_21, ", "p_22, p_23, p_24, p_25, p_26, p_27, p_28, p_29, p_30, p_31, p_32, p_33, p_34, ",
			"p_35, p_36, p_37, p_38, p_39, p_40, p_41, p_42, p_43, p_44, p_45, p_46, p_47, ", "p_48, p_49, p_50", "from sdb_b2c_goods", "where p_50 = #{skuCode,jdbcType=VARCHAR}" })
	@Results({ @Result(column = "goods_id", property = "goodsId", jdbcType = JdbcType.BIGINT, id = true), @Result(column = "bn", property = "bn", jdbcType = JdbcType.VARCHAR), @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL), @Result(column = "type_id", property = "typeId", jdbcType = JdbcType.INTEGER), @Result(column = "cat_id", property = "catId", jdbcType = JdbcType.INTEGER),
			@Result(column = "brand_id", property = "brandId", jdbcType = JdbcType.INTEGER), @Result(column = "marketable", property = "marketable", jdbcType = JdbcType.CHAR),
			@Result(column = "store", property = "store", jdbcType = JdbcType.INTEGER), @Result(column = "notify_num", property = "notifyNum", jdbcType = JdbcType.INTEGER),
			@Result(column = "uptime", property = "uptime", jdbcType = JdbcType.INTEGER), @Result(column = "downtime", property = "downtime", jdbcType = JdbcType.INTEGER),
			@Result(column = "last_modify", property = "lastModify", jdbcType = JdbcType.INTEGER), @Result(column = "p_order", property = "pOrder", jdbcType = JdbcType.INTEGER),
			@Result(column = "d_order", property = "dOrder", jdbcType = JdbcType.INTEGER), @Result(column = "score", property = "score", jdbcType = JdbcType.INTEGER), @Result(column = "cost", property = "cost", jdbcType = JdbcType.DECIMAL),
			@Result(column = "mktprice", property = "mktprice", jdbcType = JdbcType.DECIMAL), @Result(column = "weight", property = "weight", jdbcType = JdbcType.DECIMAL), @Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
			@Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR), @Result(column = "goods_type", property = "goodsType", jdbcType = JdbcType.CHAR),
			@Result(column = "image_default_id", property = "imageDefaultId", jdbcType = JdbcType.VARCHAR), @Result(column = "udfimg", property = "udfimg", jdbcType = JdbcType.CHAR),
			@Result(column = "thumbnail_pic", property = "thumbnailPic", jdbcType = JdbcType.VARCHAR), @Result(column = "small_pic", property = "smallPic", jdbcType = JdbcType.VARCHAR),
			@Result(column = "big_pic", property = "bigPic", jdbcType = JdbcType.VARCHAR), @Result(column = "store_place", property = "storePlace", jdbcType = JdbcType.VARCHAR),
			@Result(column = "min_buy", property = "minBuy", jdbcType = JdbcType.INTEGER), @Result(column = "package_scale", property = "packageScale", jdbcType = JdbcType.DECIMAL),
			@Result(column = "package_unit", property = "packageUnit", jdbcType = JdbcType.VARCHAR), @Result(column = "package_use", property = "packageUse", jdbcType = JdbcType.CHAR),
			@Result(column = "score_setting", property = "scoreSetting", jdbcType = JdbcType.CHAR), @Result(column = "store_prompt", property = "storePrompt", jdbcType = JdbcType.INTEGER),
			@Result(column = "nostore_sell", property = "nostoreSell", jdbcType = JdbcType.CHAR), @Result(column = "disabled", property = "disabled", jdbcType = JdbcType.CHAR),
			@Result(column = "rank_count", property = "rankCount", jdbcType = JdbcType.INTEGER), @Result(column = "comments_count", property = "commentsCount", jdbcType = JdbcType.INTEGER),
			@Result(column = "view_w_count", property = "viewWCount", jdbcType = JdbcType.INTEGER), @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
			@Result(column = "buy_count", property = "buyCount", jdbcType = JdbcType.INTEGER), @Result(column = "buy_w_count", property = "buyWCount", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_1", property = "p1", jdbcType = JdbcType.INTEGER), @Result(column = "p_2", property = "p2", jdbcType = JdbcType.INTEGER), @Result(column = "p_3", property = "p3", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_4", property = "p4", jdbcType = JdbcType.INTEGER), @Result(column = "p_5", property = "p5", jdbcType = JdbcType.INTEGER), @Result(column = "p_6", property = "p6", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_7", property = "p7", jdbcType = JdbcType.INTEGER), @Result(column = "p_8", property = "p8", jdbcType = JdbcType.INTEGER), @Result(column = "p_9", property = "p9", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_10", property = "p10", jdbcType = JdbcType.INTEGER), @Result(column = "p_11", property = "p11", jdbcType = JdbcType.INTEGER), @Result(column = "p_12", property = "p12", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_13", property = "p13", jdbcType = JdbcType.INTEGER), @Result(column = "p_14", property = "p14", jdbcType = JdbcType.INTEGER), @Result(column = "p_15", property = "p15", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_16", property = "p16", jdbcType = JdbcType.INTEGER), @Result(column = "p_17", property = "p17", jdbcType = JdbcType.INTEGER), @Result(column = "p_18", property = "p18", jdbcType = JdbcType.INTEGER),
			@Result(column = "p_19", property = "p19", jdbcType = JdbcType.INTEGER), @Result(column = "p_20", property = "p20", jdbcType = JdbcType.INTEGER), @Result(column = "p_21", property = "p21", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_22", property = "p22", jdbcType = JdbcType.VARCHAR), @Result(column = "p_23", property = "p23", jdbcType = JdbcType.VARCHAR), @Result(column = "p_24", property = "p24", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_25", property = "p25", jdbcType = JdbcType.VARCHAR), @Result(column = "p_26", property = "p26", jdbcType = JdbcType.VARCHAR), @Result(column = "p_27", property = "p27", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_28", property = "p28", jdbcType = JdbcType.VARCHAR), @Result(column = "p_29", property = "p29", jdbcType = JdbcType.VARCHAR), @Result(column = "p_30", property = "p30", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_31", property = "p31", jdbcType = JdbcType.VARCHAR), @Result(column = "p_32", property = "p32", jdbcType = JdbcType.VARCHAR), @Result(column = "p_33", property = "p33", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_34", property = "p34", jdbcType = JdbcType.VARCHAR), @Result(column = "p_35", property = "p35", jdbcType = JdbcType.VARCHAR), @Result(column = "p_36", property = "p36", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_37", property = "p37", jdbcType = JdbcType.VARCHAR), @Result(column = "p_38", property = "p38", jdbcType = JdbcType.VARCHAR), @Result(column = "p_39", property = "p39", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_40", property = "p40", jdbcType = JdbcType.VARCHAR), @Result(column = "p_41", property = "p41", jdbcType = JdbcType.VARCHAR), @Result(column = "p_42", property = "p42", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_43", property = "p43", jdbcType = JdbcType.VARCHAR), @Result(column = "p_44", property = "p44", jdbcType = JdbcType.VARCHAR), @Result(column = "p_45", property = "p45", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_46", property = "p46", jdbcType = JdbcType.VARCHAR), @Result(column = "p_47", property = "p47", jdbcType = JdbcType.VARCHAR), @Result(column = "p_48", property = "p48", jdbcType = JdbcType.VARCHAR),
			@Result(column = "p_49", property = "p49", jdbcType = JdbcType.VARCHAR), @Result(column = "p_50", property = "p50", jdbcType = JdbcType.VARCHAR)})
	EcGoods selectBySkuCode(String skuCode);
	
	@Select("select goods_id from sdb_b2c_goods")
	List<Long> selectIdByAll();
}