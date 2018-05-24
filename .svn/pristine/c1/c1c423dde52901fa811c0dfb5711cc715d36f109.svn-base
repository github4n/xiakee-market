package com.xiakee.dao.sku;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiakee.domain.sku.SkuCatalogBean;

/**
 * 操作商城商品目录信息
 * @Product: xiakee-dao
 * @Title: SkuCatalogDao.java
 * @Package com.xiakee.dao.sku
 * @Description: 遐客行后台管理系统
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com
 * @Date 2015年6月17日 下午11:34:00
 * @Version 1.0
 * @Copyright: Copyright (c) 2014
 */
public interface SkuCatalogDao {
    String TABLE_NAME = "sku_catalog";
    String ALL_FIELD = "cat_id,cat_name,parent_id,type_id";
    String VALUES = "#{cat_id},#{cat_name},#{parent_id},#{type_id}";
    
    @Insert("INSERT INTO " + TABLE_NAME +"(" + ALL_FIELD + ") VALUES(" + VALUES + ")") 
    public Integer addSkuCatalog(SkuCatalogBean bean);

    @Update("UPDATE sku_catalog SET cat_name = #{cat_name},parent_id = #{parent_id},type_id = #{type_id} where cat_id = #{cat_id}") 
    public Integer updateSkuCatalog(SkuCatalogBean bean);
    
    @Update("UPDATE sku_catalog SET catCode = #{catCode} where cat_id = #{cat_id}") 
    public Integer updateCatalogCode(SkuCatalogBean bean);
    
    @Select("select * from sku_catalog where cat_id = #{catId}")
    public SkuCatalogBean findSkuCatalogById(Long catId);
    
    @Select("SELECT DISTINCT(type_id) FROM sku_catalog")
    public List<Long> getAllDistinctTypeIds();
    
    @Select("SELECT * FROM sku_catalog")
    public List<SkuCatalogBean> getAllCatalogBeans();
    
    @Select("SELECT * FROM sku_catalog")
    public List<SkuCatalogBean> getAllCatalogBeans_page(Map<String, Object> param);
    
    @Select("SELECT * FROM sku_catalog where catCode = #{catCode}")
    public SkuCatalogBean findByCatalogCode(String catCode);
    
    @Select("SELECT * FROM sku_catalog where parent_id = #{parent_id}")
    public List<SkuCatalogBean> getCatalogByParentId(Integer parent_id);
}
