package com.xiakee.dao.logistics;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.logistics.DeclareGoodsBean;
import com.xiakee.domain.yz.YzorderInfoBean;

public interface BoxnoDao {
    String TABLE_NAME = "xiakee_boxno";
    String ALL_FIELD = "abroadId,infoId,boxno,status,price,sum";
    String VALUES = "#{abroadId},#{infoId},#{boxno},#{status},#{price},#{sum}";
    
    @Insert("INSERT INTO xiakee_boxno(abroadId,infoId,created) VALUES(#{abroadId},#{infoId},NOW())")  
    public Integer addBoxnoBean(BoxnoBean bean);
    
    @Update("UPDATE xiakee_boxno SET price= #{price},sum = #{sum},trend = #{trend} WHERE infoId = #{infoId} AND abroadId = #{abroadId}")
    public Integer declaredBoxnoBean(BoxnoBean bean);
    
    @Update("UPDATE xiakee_boxno SET expressno= #{expressno},transfer_id = #{transfer_id},modified = NOW() WHERE infoId = #{infoId}")
    public Integer updateExpressno(BoxnoBean bean);
    
    @Update("UPDATE xiakee_boxno SET declared= #{declared} WHERE infoId = #{infoId}")
    public Integer updateDeclate(BoxnoBean bean);
    
    @Select("select * from xiakee_boxno where infoId = #{id}")
    public BoxnoBean findBoxnoBeanById(Long id);
    
    @Select("SELECT a.* from xiakee_yzorderinfo a INNER JOIN xiakee_boxno b ON a.id = b.infoId WHERE b.abroadId = #{abroadId} AND b.price IS NULL LIMIT 1")
    public YzorderInfoBean findBoxnoBeanByAbroadId(Long abroadId);
    
    @Select("select a.outOrderId abroadOrderName,b.abroadId,a.url,b.infoId,b.price,b.sum from xiakee_boxno b INNER JOIN xiakee_abroadOrder a ON b.abroadId = a.id where b.expressno IS NULL")
    public List<BoxnoBean> getAllNoExpressnoBean();

    @Select("select a.outOrderId abroadOrderName,b.infoId,b.price,b.sum,b.expressno,c.name from xiakee_boxno b INNER JOIN xiakee_abroadOrder a ON b.abroadId = a.id INNER JOIN xiakee_yzorders c ON b.infoId = c.id  where b.expressno IS NOT NULL")
    public List<BoxnoBean> getAllExpressnoedBeans();

    /**
     * 根据海外订单ID，即海外订单号（真实的字符串），以供撤销等操作
     * @Method  getBoxnoByOutOrderId
     * @Return List<BoxnoBean>
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年7月2日 下午4:39:34
     * @Version 1.0
     */
    @Select("select b.abroadId,b.infoId,b.price,b.sum from xiakee_boxno b INNER JOIN xiakee_abroadOrder a ON b.abroadId = a.id where a.outOrderId = #{outOrderId}")
    public List<BoxnoBean> getBoxnoByOutOrderId(String outOrderId);
    @Select("select * from xiakee_boxno where abroadId = #{abroadId}")
    public List<BoxnoBean> getBoxnoById(Long abroadId);
    
    @Select("select b.infoId,b.created,b.modified,b.declared,b.transfer_id,b.expressno,b.price,b.sum,o.title,o.sku_properties_name  from xiakee_boxno b LEFT JOIN xiakee_yzorderinfo o ON b.infoId = o.id where abroadId = #{abroadId}")
    public List<BoxnoBean> getBoxnoByAbroadId(Long abroadId);
    
    @Delete("DELETE FROM xiakee_boxno WHERE abroadId = #{abroadId} AND infoId = #{infoId}")
    Integer deleteBoxnoByAbroadIdAndInfoId(BoxnoBean bean);
    
    /**
     * 获取所有尚未成功申报的信息
     * @Method  getAllDeclareBean
     * @Return List<BoxnoBean>
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年6月17日 下午3:04:38
     * @Version 1.0
     */
    @Select("SELECT b.infoId,b.created,b.modified,b.declared,b.transfer_id,b.expressno,b.price,b.sum,o.title,o.sku_properties_name,u.`name` FROM xiakee_boxno b LEFT JOIN xiakee_yzorderinfo o ON b.infoId = o.id JOIN xiakee_yzorders u ON o.orderid = u.orderid WHERE b.expressno IS NOT NULL AND b.declared in (0,2) ORDER BY b.modified DESC")
    public List<BoxnoBean> getAllDeclareBean_page(Map<String, Object> param);

    /**
     * 获取已经成功申报的信息
     * @Method  getDeclaredBeans
     * @Return List<BoxnoBean>
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年6月17日 下午3:05:01
     * @Version 1.0
     */
    @Select("SELECT b.infoId,b.created,b.modified,b.expressno,b.transfer_id,b.price,b.sum,CONCAT(o.title,o.sku_properties_name) as title,u.`name` FROM xiakee_boxno b LEFT JOIN xiakee_yzorderinfo o ON b.infoId = o.id JOIN xiakee_yzorders u ON o.orderid = u.orderid WHERE b.expressno IS NOT NULL AND b.declared = 1 ORDER BY b.modified DESC")
    public List<BoxnoBean> getDeclaredBeans();
    
    @Select("SELECT b.abroadId AS url,b.infoId,b.expressno,b.price,b.sum,o.title,o.sku_properties_name,u.name as receiverName,u.mobile AS receiverMobile,u.province,u.city,u.district,u.address,u.zipCode AS receiverZip FROM xiakee_boxno b LEFT JOIN xiakee_yzorderinfo o ON b.infoId = o.id JOIN xiakee_yzorders u ON o.orderid = u.orderid WHERE b.expressno IS NOT NULL AND b.declared in (0,2) AND infoId = #{infoId} ORDER BY b.abroadId DESC")
    public DeclareGoodsBean getDeclareBean(Long infoId);
    
    @Select("SELECT b.abroadId AS url,b.infoId,b.expressno,b.price,b.sum,o.title,o.sku_properties_name,u.name as receiverName,u.mobile AS receiverMobile,u.province,u.city,u.district,u.address,u.zipCode AS receiverZip FROM xiakee_boxno b LEFT JOIN xiakee_yzorderinfo o ON b.infoId = o.id JOIN xiakee_yzorders u ON o.orderid = u.orderid WHERE b.expressno = #{expressno} AND b.declared in (0,2) ORDER BY b.abroadId DESC")
    public List<DeclareGoodsBean> getDeclareBeanByExpressno(String expressno);
    
    /**
     * 获取所有百威箱子号为空的海外包裹ID
     * @Method  getAllExpressnoByBoxnoIsnull
     * @Return List<String>
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年7月15日 上午11:12:53
     * @Version 1.0
     */
    @Select("SELECT infoId,expressno FROM xiakee_boxno WHERE boxno IS NULL AND expressno IS NOT NULL")
    public List<BoxnoBean> getAllExpressnoByBoxnoIsnull();
    
    //获取百威箱子id
    @Update("UPDATE xiakee_boxno SET boxno= #{boxno} WHERE infoId = #{infoId}")
    public Integer updateBoxnoByInfoId(BoxnoBean bean);
    
    @Update("UPDATE xiakee_boxno SET expressno= #{expressno},declared= #{declared} WHERE infoId = #{infoId}")
    public Integer updateExpressnoByInfoId(BoxnoBean bean);
    
    /**
     * 根据orderid获取该订单所有的海外包裹号
     * @Method  findExpressnosByOrderId
     * @param orderid
     * @return List<String>
     * @Author 谢坚柏
     * @Email boge@xiakee.com
     * @Date 2015年7月18日 下午2:56:35
     * @Version 1.0
     */
    @Select("SELECT  DISTINCT(box.expressno),box.infoId,transfer_id FROM xiakee_yzorderinfo item INNER JOIN xiakee_boxno box ON item.id = box.infoId WHERE item.orderid = #{orderid}")
    public List<BoxnoBean> findExpressnosByOrderId(String orderid);
    
    /**
     * 根据orderid获取该订单所有的海外包裹号
     * @Method  findExpressnosByOrderId
     * @param orderid
     * @return List<String>
     * @Author 谢坚柏
     * @Email boge@xiakee.com
     * @Date 2015年7月18日 下午2:56:35
     * @Version 1.0
     */
    @Select("SELECT DISTINCT(box.expressno),transfer_id FROM xiakee_yzorderinfo item INNER JOIN xiakee_boxno box ON item.id = box.infoId WHERE item.id = #{infoId}")
    public List<BoxnoBean> findExpressnosByInfoId(String infoId);

    @Select("SELECT DISTINCT(transfer_id) FROM xiakee_boxno WHERE expressno = #{expressno} LIMIT 1")
    public int findTransferIdByExpressno(String expressno);
}
