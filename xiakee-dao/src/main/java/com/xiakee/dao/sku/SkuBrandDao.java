package com.xiakee.dao.sku;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiakee.domain.sku.SkuBrandBean;

/**
 * 商城品牌信息操作类
 * 
 * @Product: xiakee-dao
 * @Title: SkuBrandDao.java
 * @Package com.xiakee.dao.sku
 * @Description: 遐客行后台管理系统
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com
 * @Date 2015年6月17日 下午11:55:18
 * @Version 1.0
 * @Copyright: Copyright (c) 2014
 */
public interface SkuBrandDao {
	String TABLE_NAME = "sku_brand";
	String ALL_FIELD = "id,name,brandCode";
	String VALUES = "#{id},#{name},#{brandCode}";

	@Insert("INSERT INTO " + TABLE_NAME + "(" + ALL_FIELD + ") VALUES(" + VALUES + ")")
	public Integer addSkuBrandBean(SkuBrandBean bean);

	@Select("select * from sku_brand where id = #{id}")
	public SkuBrandBean findSkuBrandById(Long id);

	@Update("UPDATE sku_brand SET name = #{name} WHERE id = #{id}")
	public Integer updateSkuBrandBean(SkuBrandBean bean);

	@Update("UPDATE sku_brand SET brandCode = #{brandCode} WHERE id = #{id}")
	public Integer updateBrandCode(SkuBrandBean bean);

	@Select("SELECT * FROM sku_brand")
	public List<SkuBrandBean> getAllBrandBeans();

	@Select("select * from sku_brand where brandCode = #{brandCode}")
	public SkuBrandBean findByBrandCode(String brandCode);
}
