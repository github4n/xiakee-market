package com.xiakee.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.domain.order.EcOrderItemBean;
import com.xiakee.domain.order.EcOrderSourceBean;

public interface ErpOrderDao {
	@Insert("INSERT INTO ecos_orders(order_id,total_amount,final_amount,pay_status,ship_status,createtime,"
			+ "last_modified,payment,member_id,status,ship_area,ship_name,weight,ship_addr,ship_zip,ship_tel,"
			+ "ship_email,ship_mobile,cost_item,discount,pmt_goods,pmt_order,payed,memo,cost_freight,order_refer,source) "
			+ "VALUES(#{order_id},#{total_amount},#{final_amount},#{pay_status},#{ship_status},#{createtime},"
			+ "#{last_modified},#{payment},#{member_id},#{status},#{ship_area},#{ship_name},#{weight},#{ship_addr},#{ship_zip},#{ship_tel},"
			+ "#{ship_email},#{ship_mobile},#{cost_item},#{discount},#{pmt_goods},#{pmt_order},#{payed},#{memo},#{cost_freight},#{order_refer},#{source})")
	public Integer importEcstoreOrder(EcOrderBean bean);

	@Select("SELECT last_modified from ecos_orders order by last_modified desc limit 1")
	public Long lastOrderDate();

	@Select("SELECT * FROM ecos_orders WHERE order_id = #{order_id}")
	public EcOrderBean findOrderById(Long order_id);

	/**
	 * 更新商城订单数据同步数据
	 * 
	 * @Method updateEcstoreOrderByOrderId
	 * @Return Integer
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月11日 上午10:22:36
	 * @Version 1.0
	 */
	@Update("UPDATE ecos_orders SET total_amount = #{total_amount},final_amount = #{final_amount},pay_status = #{pay_status},"
			+ "ship_status = #{ship_status},createtime = #{createtime},last_modified = #{last_modified},payment = #{payment},member_id = #{member_id},"
			+ "status = #{status},ship_area = #{ship_area},ship_name = #{ship_name},weight = #{weight},ship_addr = #{ship_addr},"
			+ "ship_zip = #{ship_zip},ship_tel = #{ship_tel},ship_email = #{ship_email},ship_mobile = #{ship_mobile},cost_item = #{cost_item},"
			+ "discount = #{discount},pmt_goods = #{pmt_goods},pmt_order = #{pmt_order},payed = #{payed},memo = #{memo},"
			+ "cost_freight = #{cost_freight},order_refer = #{order_refer},source = #{source} WHERE order_id = #{order_id}")
	public Integer updateEcstoreOrderByOrderId(EcOrderBean bean);

	/**
	 * 插入商城订单的详细货品信息
	 * 
	 * @Method importEcstoreOrderItem
	 * @Return Integer
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月11日 上午10:21:59
	 * @Version 1.0
	 */
	@Insert("INSERT INTO ecos_orderitems(item_id,order_id,obj_id,product_id,goods_id,type_id,bn,name,cost,amount,price,nums,addon) "
			+ "VALUES(#{item_id},#{order_id},#{obj_id},#{product_id},#{goods_id},#{type_id},#{bn},#{name},#{cost},#{amount},#{price},#{nums},#{addon})")
	public Integer importEcstoreOrderItem(EcOrderItemBean bean);

	/**
	 * 获取一个月以内成功的订单信息
	 * 
	 * @Method findAllEcstoreSuccOrders
	 * @param createtime
	 * @return List<EcOrderBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com
	 * @Date 2015年7月7日 下午11:47:35
	 * @Version 1.0
	 */
	@Select("SELECT order_id,final_amount,createtime,member_id,ship_area,ship_name,ship_addr,ship_zip,ship_mobile,ship_tel,memo,source  FROM ecos_orders WHERE pay_status != '0' AND status = 'active' AND createtime > #{createtime} ORDER BY createtime DESC")
	List<EcOrderBean> findAllEcstoreSuccOrders(Long createtime);

	@Select("SELECT product_id FROM ecos_orderitems WHERE item_id = #{item_id}")
	Long getProductIdByItemId(Long item_id);

	/**
	 * 获取所有成功支付订单的来源数据
	 * 
	 * @Method getEcOrderSourceSum
	 * @Return List<EcOrderSourceBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年9月10日 下午9:42:12
	 * @Version 1.0
	 */
	@Select("SELECT source , SUM(1) AS sum FROM ecos_orders WHERE pay_status != '0' AND status IN ('active','finish') AND createtime > #{created} GROUP BY source")
	List<EcOrderSourceBean> getEcOrderSourceSum(Long created);

	@Select("SELECT source , SUM(1) AS sum FROM ecos_orders WHERE pay_status != '0' AND status IN ('active','finish') AND createtime BETWEEN #{begin} AND #{end} GROUP BY source")
	List<EcOrderSourceBean> sectionEcOrderSourceSum(@Param("begin") Long begin, @Param("end") Long end);

	@Select("SELECT * FROM ecos_orderitems WHERE order_id = #{order_id}")
	List<EcOrderItemBean> findAllItenByOrderid(@Param("order_id") String order_id);

	@Select("SELECT o.order_id FROM ecos_orders o LEFT JOIN ecos_orderitems i ON o.order_id = i.order_id WHERE i.product_id IS NULL;")
	List<Long> findAllOrderidByItemsEmpty();

	@Select("select bn from ecos_orderitems where name = #{name}")
	List<String> findBnByName(String name);
}
