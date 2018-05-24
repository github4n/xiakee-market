package com.xiakee.dao.logistics;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.logistics.LogisticsBean;
import com.xiakee.domain.order.EcOrderBean;

public interface LogisticsDao {
    String TABLE_NAME = "xiakee_logistics";
    String ALL_FIELD = "infoId,orderid,content,node,created";
    String VALUES = "#{infoId},#{orderid},#{content},#{node},NOW()";
    
    @Insert("INSERT INTO " + TABLE_NAME +"(" + ALL_FIELD + ") VALUES(" + VALUES + ")")  
    public Integer addLogistics(LogisticsBean bean);
    
    @Select("SELECT node from xiakee_logistics where orderid = #{orderid} ORDER BY created DESC")
    public List<Integer> getLogisNodes(String orderid);
    
    /**
     * 获取最后的node，用以判断当前的物流节点到达那一步
     * @Method  getLogisNodes
     * @Return List<Integer>
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年7月20日 下午4:20:16
     * @Version 1.0
     */
    @Select("SELECT node from xiakee_logistics where orderid = #{orderid} ORDER BY node DESC LIMIT 1")
    public Long getLastLogisNode(String orderid);
    
    /**
     * 获取最后的node，用以判断当前的物流节点到达那一步
     * @Method  getLogisNodes
     * @Return List<Integer>
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年7月20日 下午4:20:16
     * @Version 1.0
     */
    @Select("SELECT node from xiakee_logistics where infoId = #{infoid} ORDER BY node DESC LIMIT 1")
    public Long getLastLogisNodeByInfoId(String infoid);
    
    /**
     * 获取所有物流信息尚未结束的订单信息
     * @return
     */
    @Select("SELECT DISTINCT(o.order_id),o.ship_name FROM ecos_orders o INNER JOIN xiakee_logistics l ON o.order_id = l.orderid WHERE l.node < 100 and l.infoId IS NULL")
    public List<EcOrderBean> findAllNotLogistOrders();
    
    /**
     * 获取所有物流信息尚未结束的订单信息
     * @return
     */
    @Select("SELECT DISTINCT(infoId) FROM xiakee_logistics WHERE node < 100 AND infoId > 0")
    public List<LogisticsBean> findAllNotLogistOrdersByInfoId();
}
