package com.xiakee.dao.sku;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.xiakee.domain.sku.AddSkuSumBean;
import com.xiakee.domain.sku.SkuManagerBean;

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
public interface SkuManagerDao {
	String TABLE_NAME = "sku_manage";
	String ALL_FIELD = "productId,enName,zhName,skuCode,brand,classify,types,mainUrl,grossId,keyword,grossProfitMargin,weight,pattern,created,userId";
	String VALUES = "#{productId},#{enName},#{zhName},#{skuCode},#{brand},#{classify},#{types},#{mainUrl},#{grossId},#{keyword},#{grossProfitMargin},#{weight},#{pattern},now(),#{userId}";

	@Insert("INSERT INTO " + TABLE_NAME + "(" + ALL_FIELD + ") VALUES(" + VALUES + ")")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public Integer addSkuManagerBean(SkuManagerBean bean);

	@Update("update sku_manage set grossId = #{grossId},grossProfitMargin = #{grossProfitMargin} where skuCode = #{skuCode}")
	public Integer updateSkuManagerBeanBySkuCode(SkuManagerBean bean);

	@Select("select * from sku_manage where id = #{id}")
	public SkuManagerBean findSkuManagerBeanById(Long id);

	@Select("select * from sku_manage where skuCode = #{skuCode}")
	public SkuManagerBean findSkuManagerBeanBySkuCode(String skuCode);

	@Delete("delete from sku_manage where id = #{id}")
	public Integer delSkuManagerBeanById(Long id);

	@SelectProvider(type = SkuManagerDaoSqlProvider.class, method = "selectByBrandAndClassify_page")
	public List<SkuManagerBean> selectByBrandAndClassify_page(Map<String, Object> param);

	@Update("update sku_manage set skuCode = #{skuCode} where id = #{id}")
	public Integer updateSkuCode(SkuManagerBean bean);

	@Select("SELECT * FROM sku_manage where brand = #{brand}")
	public List<SkuManagerBean> getSkuManagerByBrand(Long brand);

	@Select("SELECT * FROM sku_manage where brand is null")
	public List<SkuManagerBean> getSkuManagerNotBrand();

	@Select("SELECT * FROM sku_manage")
	public List<SkuManagerBean> selectAll();

	@Select("SELECT userId , SUM(1) AS sum FROM sku_manage WHERE  created > #{created} GROUP BY userId")
	public List<AddSkuSumBean> getAddSkuSums(String created);

	@Select("SELECT userId , SUM(1) AS sum FROM sku_manage WHERE created BETWEEN #{begin} AND #{end} GROUP BY userId ORDER BY sum DESC")
	public List<AddSkuSumBean> sectionAddSkuSums(@Param("begin") String begin, @Param("end") String end);

	@Update("update sku_manage set isImport = 1 where id = #{id}")
	public Integer updateIsImport(Long id);
	
	@Update("update sku_manage set priceLockTime = #{priceLockTime} where id = #{id}")
	public Integer updatePriceLockTime(SkuManagerBean bean);

}
