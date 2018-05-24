package com.xiakee.dao.analy;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.analy.OrderReferrerBean;

/**
 * 订单追踪埋点数据表
 * @Product: xiakee-dao
 * @Title: OrderReferrerDao.java
 * @Package com.xiakee.dao.analy
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年10月22日 下午7:42:39
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public interface OrderReferrerDao {
	@Insert("INSERT INTO analy_order_referrer(userid,title,promotion,code,remark,created) VALUES(#{userid},#{title},#{promotion},#{code},#{remark},NOW())")  
    public Integer addOrderReferrerBean(OrderReferrerBean bean);
	
	@Select("select * from analy_order_referrer where id = #{id}")
    public OrderReferrerBean findOrderReferrerBeanById(Long id);
	
	@Select("select id,userid,title,promotion,remark,date_format(created,'%Y-%m-%d') as created from analy_order_referrer where userid = #{userid}")
    public List<OrderReferrerBean> findOrderReferrerBeanByUserId(Long userid);
	
	@Select("select id,userid,title,promotion,remark,date_format(created,'%Y-%m-%d') as created from analy_order_referrer order by id desc")
    public List<OrderReferrerBean> findAllOrderReferrers();
}
