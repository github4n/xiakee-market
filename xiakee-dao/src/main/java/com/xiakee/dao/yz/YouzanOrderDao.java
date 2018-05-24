package com.xiakee.dao.yz;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiakee.domain.logistics.LogisticsBean;
import com.xiakee.domain.logistics.LogisticsDiyouBean;
import com.xiakee.domain.print.RecipientBean;
import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.domain.yz.YzordersBean;

public interface YouzanOrderDao {
    String TABLE_NAME = "xiakee_yzorders";
    String ALL_FIELD = "orderid,price,payment,status,name,province,city,district,address,zipCode,mobile,created,update_time,pay_time,title";
    String VALUES = " #{orderid}, #{price}, #{payment}, #{status}, #{name}, #{province}, #{city}, #{district}, #{address}, #{zipCode}, #{mobile}, #{created}, #{update_time}, #{pay_time}, #{title}";
    
    @Insert("insert into " + TABLE_NAME +"(" + ALL_FIELD + ") values(" + VALUES + ")")  
    public Integer importYouzanOrder(YzordersBean bean);
    
    @Select("SELECT update_time from " + TABLE_NAME + " order by update_time desc limit 1")
    public String lastOrderDate();

    @Select("SELECT id," + ALL_FIELD + " from " + TABLE_NAME + " where mobile = #{mobile} order by pay_time desc")
    public List<YzordersBean> findUserOrderByMobile(String mobile);
    
    @Select("SELECT id," + ALL_FIELD + " from " + TABLE_NAME + " where status in ('WAIT_SELLER_SEND_GOODS','WAIT_BUYER_CONFIRM_GOODS') order by pay_time desc")
    public List<YzordersBean> getAllYzordersByWaitSend();
    
    @Select("SELECT id," + ALL_FIELD + " from " + TABLE_NAME + " where status in ('WAIT_SELLER_SEND_GOODS','WAIT_BUYER_CONFIRM_GOODS') order by pay_time desc LIMIT #{begin},#{end}")
    public List<YzordersBean> getOrdersNotAbroadInfoByPage(@Param("begin") int begin,@Param("end") int end);
    
    @Select("SELECT * FROM xiakee_yzorders WHERE orderid = #{orderid} LIMIT 1")
    public YzordersBean findOrderByOrderid(String orderid);

    @Select("SELECT a.id,a.orderid,a.name,a.mobile,a.created,a.title FROM xiakee_yzorders a INNER JOIN xiakee_yzorderinfo b ON a.orderid = b.orderid WHERE b.id =  #{infoId}")
    public YzordersBean findOrderByInfoId(Long infoId);

    /**
     * 根据infoId查询该条信息所关联的订单是否已经发送过短信
     * @Method  findOrderLogistByInfoId
     * @Return YzordersBean
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年5月27日 下午5:05:51
     * @Version 1.0
     */
    @Select("SELECT a.id,a.orderid,a.name,a.mobile,a.created,a.title FROM xiakee_yzorders a INNER JOIN xiakee_yzorderinfo b ON a.orderid = b.orderid LEFT JOIN xiakee_logistics l ON a.orderid = l.orderid WHERE b.id =  #{infoId} AND l.node = #{node} limit 1")
    public YzordersBean findOrderLogistByInfoId(LogisticsBean bean);
    @Select("SELECT a.id,a.orderid,a.name,a.mobile,a.created,a.title FROM xiakee_yzorders a LEFT JOIN xiakee_logistics l ON a.orderid = l.orderid WHERE a.orderid =  #{orderid} AND l.node = #{node} limit 1")
    public YzordersBean findOrderLogistByorderId(LogisticsBean bean);
    
    @Select("SELECT a.id,a.orderid,a.name,a.mobile,a.created,a.title FROM xiakee_yzorders a INNER JOIN xiakee_yzorderinfo b ON a.orderid = b.orderid LEFT JOIN xiakee_logistics l ON a.orderid = l.orderid WHERE b.id =  #{infoId} AND l.node = #{node} limit 1")
    public YzordersBean findOrderLogistDiyouByInfoId(LogisticsDiyouBean bean);
    @Select("SELECT a.id,a.orderid,a.name,a.mobile,a.created,a.title FROM xiakee_yzorders a LEFT JOIN xiakee_logistics l ON a.orderid = l.orderid WHERE a.orderid =  #{orderid} AND l.node = #{node} limit 1")
    public YzordersBean findOrderLogistDiyouByorderId(LogisticsDiyouBean bean);
    
    @Update("UPDATE " + TABLE_NAME + " SET status = #{status},payment = #{payment},update_time = #{update_time},pay_time = #{pay_time} WHERE orderid = #{orderid}")
    public Integer updateOrderStatusByOrderId(YzordersBean bean);
    
    @Select("SELECT a.name name,a.province destination,a.province province,a.city city,a.district district,a.address address,a.mobile mobile,concat(b.title,b.sku_properties_name) as fileName from xiakee_yzorders a,xiakee_yzorderinfo b WHERE a.orderid = b.orderid AND b.id = #{infoId}")
    public RecipientBean findPrintByInfoId(String infoId);
    
    @Delete("delete from xiakee_yzorders where orderid = #{orderid}")
    public Integer deleteOrderByOrderid(String orderid);
    
    @Insert("insert into xiakee_yzorderinfo(item_id,orderid,title,sku_properties_name,num,price) values(#{item_id},#{orderid},#{title},#{sku_properties_name},#{num},#{price})")  
    public Integer addOrderInfoByOrderid(YzorderInfoBean bean);
    
    @Select("select * from xiakee_yzorderinfo where id = #{id}")
    public YzorderInfoBean getYzorderInfoBeanById(Long id);

    /**
     * 获取YzorderInfoBean信息，顺带关联order表把订单客户名称获取到
     * @Method  getYzorderNameById
     * @Return YzorderInfoBean
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年6月9日 上午9:41:05
     * @Version 1.0
     */
    @Select("select o.*,y.`name` from xiakee_yzorderinfo o INNER JOIN xiakee_yzorders y ON o.orderid = y.orderid where o.id = #{id}")
    public YzorderInfoBean getYzorderNameById(Long id);
    
    @Select("SELECT id,orderid,title,sku_properties_name,num,price from xiakee_yzorderinfo where orderid = #{orderid} order by id")
    public List<YzorderInfoBean> findOrderInfoByOrderid(String orderid);

    @Select("SELECT a.id,a.item_id,a.orderid,a.title,a.sku_properties_name,a.num,a.price,a.remark FROM xiakee_yzorderinfo a LEFT JOIN xiakee_boxno b ON a.id = b.infoId WHERE b.abroadId IS NULL AND orderid = #{orderid}")
    public List<YzorderInfoBean> findInfoOutAbroadId(String orderid);

    /**
     * 根据订单号和海外物流号查询具体货品信息
     * @Method  findInfosByExpressNo
     * @Return List<YzorderInfoBean>
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年10月4日 下午7:00:17
     * @Version 1.0
     */
    @Select("SELECT info.* FROM xiakee_yzorderinfo info INNER JOIN xiakee_boxno box ON info.id = box.infoId WHERE box.expressno = #{expressno} AND info.orderid = #{orderid}")
    public List<YzorderInfoBean> findInfosByExpressnoAndOrderid(@Param("expressno") String expressno,@Param("orderid") String orderid);

    /**
     * 根据海外货品号获取具体货品信息和收货人信息
     * @Method  findInfosByExpressNo
     * @Return List<YzorderInfoBean>
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年10月4日 下午7:00:43
     * @Version 1.0
     */
    @Select("SELECT info.*,orders.`name`,orders.mobile,orders.address FROM xiakee_yzorderinfo info INNER JOIN xiakee_boxno box ON info.id = box.infoId INNER JOIN xiakee_yzorders orders ON info.orderid = orders.orderid WHERE box.expressno = #{expressno}")
    public List<YzorderInfoBean> findInfosByExpressNo(@Param("expressno") String expressno);
    
    /**
     * 尚未打印过的订单
     * @Method  displayOrderPrintTasker
     * @Return List<RecipientBean>
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年7月11日 下午2:23:03
     * @Version 1.0
     */
    @Select("SELECT b.id id,a.orderid orderid,a.name name,a.mobile,concat(b.title,b.sku_properties_name) as fileName,c.created "
    		+ "from xiakee_yzorders a JOIN xiakee_yzorderinfo b ON a.orderid = b.orderid "
    		+ "LEFT JOIN xiakee_logistComp c ON b.id = c.infoId WHERE a.`status` "
    		+ "in ('TRADE_BUYER_SIGNED','WAIT_SELLER_SEND_GOODS','WAIT_BUYER_CONFIRM_GOODS') "
    		+ " order by b.id desc LIMIT #{begin},#{end}")
    public List<RecipientBean> displayOrderPrintTasker(@Param("begin") int begin,@Param("end") int end);

    @Select("SELECT b.id id,a.orderid orderid,a.name name,a.mobile,concat(b.title,b.sku_properties_name) as fileName,c.created "
    		+ "from xiakee_yzorders a JOIN xiakee_yzorderinfo b ON a.orderid = b.orderid "
    		+ "LEFT JOIN xiakee_logistComp c ON b.id = c.infoId WHERE a.`status` "
    		+ "in ('TRADE_BUYER_SIGNED','WAIT_SELLER_SEND_GOODS','WAIT_BUYER_CONFIRM_GOODS') "
    		+ " AND a.orderid LIKE #{orderid} AND a.name LIKE #{name} AND a.mobile LIKE #{mobile} order by b.id desc ")
    public List<RecipientBean> searchOrderPrintTask(@Param("orderid") String orderid,@Param("name") String name,@Param("mobile") String mobile);
    
    /**
     * 已经打印过的订单
     * @Method  displayPrintedList
     * @Return List<RecipientBean>
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年7月11日 下午2:22:45
     * @Version 1.0
     */
    @Select("SELECT b.id id,a.orderid orderid,a.name name,a.mobile,concat(b.title,b.sku_properties_name) as fileName "
    		+ "from xiakee_yzorders a JOIN xiakee_yzorderinfo b ON a.orderid = b.orderid "
    		+ "LEFT JOIN xiakee_logistComp c ON b.id = c.infoId WHERE a.`status` in ('TRADE_BUYER_SIGNED','WAIT_SELLER_SEND_GOODS','WAIT_BUYER_CONFIRM_GOODS') "
    		+ "AND c.created IS NOT NULL order by b.id desc LIMIT #{begin},#{end}")
    public List<RecipientBean> displayPrintedList(@Param("begin") int begin,@Param("end") int end);
    
    @Update("UPDATE xiakee_yzorderinfo SET remark = #{remark} WHERE id = #{id}")
    Integer updateOrderInfoRemarkById(@Param("remark") String remark,@Param("id") Long id);
}
