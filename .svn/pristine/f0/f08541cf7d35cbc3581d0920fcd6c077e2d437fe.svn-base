package com.xiakee.dao.sku;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.sku.SkuGrossProfit;

public interface SkuGrossProfitDao {

	@Select("select * from sku_goods_gross")
	public List<SkuGrossProfit> selectAll();

	@Select("select * from sku_goods_gross where id = #{id}")
	public SkuGrossProfit selectByPrimaryKey(int id);

}
