package com.xiakee.dao.logistics;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.xiakee.domain.logistics.AbroadOrderBean;

public interface AbroadOrderDao {
    String TABLE_NAME = "xiakee_abroadOrder";
    String ALL_FIELD = "url,outOrderId,currency";
    String VALUES = "#{url},#{outOrderId},#{currency}";
    
    @Insert("INSERT INTO " + TABLE_NAME +"(" + ALL_FIELD + ") VALUES(" + VALUES + ")")  
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id", keyProperty = "id", resultType = Long.class)
    Integer addAbroadOrderBean(AbroadOrderBean bean);
    
    @Select("select url from xiakee_abroadOrder where id = #{id}")
    String getAbroadOrderBean(Long id);
    
    @Delete("DELETE FROM xiakee_abroadOrder WHERE id = #{id}")
    Integer deleteAbroadOrderById(Long id);
    
    @Select("SELECT distinct(a.id) as id,a.url,a.outOrderId FROM xiakee_abroadOrder a JOIN xiakee_boxno b ON a.id = b.abroadId order by a.id desc  LIMIT #{begin},#{end}")
    List<AbroadOrderBean> getAllAbroadOrderByPage(@Param("begin") int begin,@Param("end") int end);
    
    @Select("SELECT * FROM xiakee_abroadOrder WHERE outOrderId LIKE #{outOrderId} order by id desc  LIMIT #{begin},#{end}")
    List<AbroadOrderBean> getAllAbroadOrderByOutOrderid(@Param("outOrderId") String outOrderId,@Param("begin") int begin,@Param("end") int end);
    
    @Select("SELECT distinct(a.id) as id,a.url,a.outOrderId FROM xiakee_abroadOrder a left join xiakee_boxno b ON a.id = b.abroadId WHERE b.expressno LIKE #{expressno} order by a.id desc  LIMIT #{begin},#{end}")
    List<AbroadOrderBean> getAllAbroadOrderByExpressno(@Param("expressno") String expressno,@Param("begin") int begin,@Param("end") int end);
    
    @Select("select * from xiakee_abroadOrder where id = #{id}")
    AbroadOrderBean selectAbroadOrderBean(Long id);
}
