package com.xiakee.ecdao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.xiakee.domain.ecgoods.XiakeeHaulProcess;
import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.domain.order.EcOrderItemBean;
import com.xiakee.domain.order.EcOrderLogistBean;

public interface EcOrderDao {

	/**
	 * 根据最后修改时间获取商城所有订单数据
	 * @Method  findAllEcstoreOrders
	 * @Return List<EcOrderBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月10日 下午9:14:38
	 * @Version 1.0
	 */
	@Select("SELECT * FROM sdb_b2c_orders WHERE last_modified > #{last_modified} ORDER BY createtime DESC")
	List<EcOrderBean> findAllEcstoreOrders(Long last_modified);
	
	@Update("update sdb_b2c_orders set ship_status = #{ship_status} where order_id = #{order_id}")
	int updateOrderShipStatus(Map<String, Object> params);
	
	@Update("update sdb_b2c_orders set status = #{status},last_modified = now() where order_id = #{order_id}")
	int updateOrderStatus(Map<String, Object> params);
	
	@Select("SELECT * FROM sdb_b2c_order_items WHERE order_id = #{order_id}")
	List<EcOrderItemBean> findAllOrderItemsByOrderId(Long order_id);
	
	/**
	 * 根据订单ID获取该订单所有的物流信息
	 * @Method  findAllLogistByOrderId
	 * @param orderId
	 * @return List<EcOrderLogistBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com
	 * @Date 2015年6月30日 下午11:39:10
	 * @Version 1.0
	 */
	@Select("SELECT alttime,log_text FROM sdb_b2c_order_log WHERE rel_id = #{orderId}")
	List<EcOrderLogistBean> findAllLogistByOrderId(Long orderId);
	
	/**
	 * 插入一条物流信息
	 * @Method  addOrderLogist
	 * @param bean
	 * @return int
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com
	 * @Date 2015年6月30日 下午11:52:03
	 * @Version 1.0
	 */
	@Insert("INSERT INTO sdb_b2c_order_log(rel_id,op_id,op_name,alttime,bill_type,behavior,result,log_text) VALUES(#{rel_id},1,'admin',#{alttime},'order','updates','SUCCESS',#{log_text})")
	Integer addOrderLogist(EcOrderLogistBean bean);
	
	/**
	 * 根据会员ID获取商城登录用户名
	 * @Method  findLoginNameByMemberId
	 * @param memberId
	 * @return List<String>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com
	 * @Date 2015年7月8日 上午12:00:25
	 * @Version 1.0
	 */
	@Select("SELECT login_account FROM sdb_pam_members WHERE member_id = #{memberId}")
	List<String> findLoginNameByMemberId(Long memberId);
	
	/**
	 * 根据订单号获取该订单物流信息的最后一条信息时间节点
	 * @Method  findLastAlttimeByOrderid
	 * @Return Long
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月22日 下午4:55:03
	 * @Version 1.0
	 */
	@Select("SELECT alttime FROM sdb_b2c_order_log WHERE rel_id = #{orderId} ORDER BY alttime DESC LIMIT 1")
	Long findLastAlttimeByOrderid(String orderId);
	
	@Select("SELECT * FROM sdb_b2c_orders WHERE status =#{status}")
	List<EcOrderBean> findAllEcstoreOrdersByStatus(String status);
	
	
	@Select({
        "select",
        "rel_id, op_id, op_name, alttime, bill_type, behavior, ",
        "result, log_text, addon",
        "from sdb_b2c_order_log ",
        "where rel_id = #{orderId}"
    })
    @Results({
        @Result(column="rel_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="op_id", property="opId", jdbcType=JdbcType.INTEGER),
        @Result(column="op_name", property="opName", jdbcType=JdbcType.VARCHAR),
        @Result(column="alttime", property="alttime", jdbcType=JdbcType.INTEGER),
        @Result(column="bill_type", property="billType", jdbcType=JdbcType.CHAR),
        @Result(column="behavior", property="behavior", jdbcType=JdbcType.CHAR),
        @Result(column="result", property="result", jdbcType=JdbcType.CHAR),
        @Result(column="log_text", property="logText", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="addon", property="addon", jdbcType=JdbcType.LONGVARCHAR)
    })
	List<XiakeeHaulProcess> findAllXiakeeHaulByOrderId(Long orderId);
}
