package com.xiakee.dao.sku;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.sku.SkuGoodsNoBean;

public interface SkuGoodsNoDao {
	String TABLE_NAME = "sku_goodsno";
	String ALL_FIELD = "goodsno,crawler_goods_id,mall_id";
	String VALUES = "#{goodsno},#{crawler_goods_id},#{mall_id}";

	@Insert("INSERT INTO " + TABLE_NAME + "(" + ALL_FIELD + ") VALUES(" + VALUES + ")")
	public Integer insert(SkuGoodsNoBean bean);

	@Select("select count(*) from sku_goodsno where goodsno = #{goodsno} and crawler_goods_id = #{crawler_goods_id} ")
	public Integer selectCountByGoodId(SkuGoodsNoBean bean);

	@Select("select * from sku_goodsno where crawler_goods_id = #{crawler_goods_id}")
	public List<SkuGoodsNoBean> selectByCrawlerGoodsId(Long crawler_goods_id);

	@Select("select * from sku_goodsno where goodsno = #{goodsno}")
	public List<SkuGoodsNoBean> selectByGoodsNo(String goodsno);

	@Delete("delete from sku_goodsno where goodsno like CONCAT('%', #{goodsno},'%' ) ")
	public int delBySkuCode(SkuGoodsNoBean bean);
	
	@Select("select * from sku_goodsno where goodsno = #{goodsno} and mall_id = #{mall_id}")
	public List<SkuGoodsNoBean> selectByGoodsNoAndMallId(SkuGoodsNoBean bean);
	
	@Select("select * from sku_goodsno where mall_id = 2")
	public List<SkuGoodsNoBean> selectByMallId();

}
