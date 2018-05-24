package com.xiakee.dao.sku;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiakee.domain.sku.SkuTypeBean;

/**
 * 商城商品类型信息操作类
 * @Product: xiakee-dao
 * @Title: SkuTypeDao.java
 * @Package com.xiakee.dao.sku
 * @Description: 遐客行后台管理系统
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com
 * @Date 2015年6月17日 下午11:52:07
 * @Version 1.0
 * @Copyright: Copyright (c) 2014
 */
public interface SkuTypeDao {
    String TABLE_NAME = "sku_type";
    String ALL_FIELD = "type_id,name";
    String VALUES = "#{type_id},#{name}";
    
    @Insert("INSERT INTO " + TABLE_NAME +"(" + ALL_FIELD + ") VALUES(" + VALUES + ")") 
    public Integer addSkuTypeBean(SkuTypeBean bean);
    
    @Update("UPDATE sku_type SET name = #{name} WHERE type_id = #{type_id}")
    public Integer updateSkuTypeBean(SkuTypeBean bean);
    
    @Update("UPDATE sku_type SET typeCode = #{typeCode} WHERE type_id = #{type_id}")
    public Integer updateTypeCode(SkuTypeBean bean);
    
    @Select("select * from sku_type where type_id = #{typeId}")
    public SkuTypeBean findSkuTypeById(Long typeId);
    
    @Select("select * from sku_type")
    public List<SkuTypeBean> getAllTypeBeans();
    
    @Select("select * from sku_type where typeCode = #{typeCode}")
    public SkuTypeBean findByTypeCode(String typeCode);
}
