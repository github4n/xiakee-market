package com.xiakee.dao.sku;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.sku.SkuUrlsBean;

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
public interface SkuUrlsDao {
	String TABLE_NAME = "sku_urls";
	String ALL_FIELD = "skuCode,url,defUrl,created";
	String VALUES = "#{skuCode},#{url},#{defUrl},now()";

	@Insert("INSERT INTO " + TABLE_NAME + "(" + ALL_FIELD + ") VALUES(" + VALUES + ")")
	@Options(useGeneratedKeys=true, keyProperty="id")
	public Integer addSkuUrlsBean(SkuUrlsBean bean);
	
	@Delete("delete from sku_urls where skuCode = #{skuCode}")
	public Integer delSkuManagerBeanBySkuCode(String skuCode);
	
	@Select("SELECT * FROM sku_urls where skuCode = #{skuCode}")
	public List<SkuUrlsBean> getSkuUrlsBeanBySkuCode(String skuCode);
	
}
