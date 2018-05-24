package com.xiakee.dao.analy;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.analy.AnalyOrderSumsBean;
import com.xiakee.domain.analy.AnalyResultBean;

public interface OrderOriginDao {
	/**
	 * 每小时订单数，小时区间
	 * @Method  analyHourOrderData
	 * @Return List<AnalyResultBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年10月23日 下午9:36:41
	 * @Version 1.0
	 */
	@Select("SELECT date_format(created, '%H') as `key` , SUM(1) AS value FROM xiakee_yzorders WHERE `status` in ('TRADE_BUYER_SIGNED','WAIT_SELLER_SEND_GOODS','WAIT_BUYER_CONFIRM_GOODS') AND created BETWEEN #{begin} AND #{end}  GROUP BY `key`")
    public List<AnalyResultBean> analyHourOrderData(@Param("begin") String begin,@Param("end") String end);

	@Select("SELECT from_unixtime(createtime, '%H') as `key` ,SUM(1) AS value FROM ecos_orders WHERE from_unixtime(createtime,'%Y-%m-%d %H:%i:%s') BETWEEN #{begin} AND #{end} GROUP BY `key`")
    public List<AnalyResultBean> analyAllHourOrderData(@Param("begin") String begin,@Param("end") String end);
	
	/**
	 * 每天订单数，一天区间
	 * @Method  analyProvinceOrderData
	 * @Return List<AnalyResultBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年10月23日 下午9:37:03
	 * @Version 1.0
	 */
	@Select("SELECT date_format(created, '%Y-%m-%d') as `key` , SUM(1) AS value FROM xiakee_yzorders WHERE `status` in ('TRADE_BUYER_SIGNED','WAIT_SELLER_SEND_GOODS','WAIT_BUYER_CONFIRM_GOODS') AND created BETWEEN #{begin} AND #{end} GROUP BY `key`")
    public List<AnalyResultBean> analyDateOrderData(@Param("begin") String begin,@Param("end") String end);
	@Select("SELECT from_unixtime(createtime, '%Y-%m-%d') as `key` , SUM(1) AS value FROM ecos_orders WHERE from_unixtime(createtime,'%Y-%m-%d %H:%i:%s') BETWEEN #{begin} AND #{end} GROUP BY `key`")
    public List<AnalyResultBean> analyAllDateOrderData(@Param("begin") String begin,@Param("end") String end);

	@Select("SELECT date_format(created, '%Y-%m') as `key` , SUM(1) AS value FROM xiakee_yzorders WHERE `status` in ('TRADE_BUYER_SIGNED','WAIT_SELLER_SEND_GOODS','WAIT_BUYER_CONFIRM_GOODS') AND created BETWEEN #{begin} AND #{end} GROUP BY `key`")
    public List<AnalyResultBean> analyMonthOrderData(@Param("begin") String begin,@Param("end") String end);
	@Select("SELECT from_unixtime(createtime, '%Y-%m') as `key` , SUM(1) AS value FROM ecos_orders WHERE from_unixtime(createtime,'%Y-%m-%d %H:%i:%s') BETWEEN #{begin} AND #{end} GROUP BY `key`")
    public List<AnalyResultBean> analyAllMonthOrderData(@Param("begin") String begin,@Param("end") String end);
	
	@Select("select from_unixtime(createtime, '%Y-%m') as `key`,sum(cost_item) as items,sum(pmt_goods + pmt_order) as pmt,sum(cost_freight) as freight,sum(payed) as payed from ecos_orders where pay_status in ('1','2','3')  and from_unixtime(createtime,'%Y-%m-%d %H:%i:%s') BETWEEN #{begin} AND #{end} GROUP BY `key` order by `key`")
    public List<AnalyOrderSumsBean> analyMonthOrderSums(@Param("begin") String begin,@Param("end") String end);
	@Select("select from_unixtime(ord.createtime, '%Y-%m') as `key`,sum(box.price * box.`sum`) as `value`  from xiakee_boxno box inner join xiakee_yzorderinfo info on box.infoId = info.id left join ecos_orders ord on info.orderid = ord.order_id where  ord.pay_status in ('1','2','3') and from_unixtime(ord.createtime,'%Y-%m-%d %H:%i:%s') BETWEEN #{begin} AND #{end} GROUP BY `key` order by `key`")
    public List<AnalyResultBean> analyMonthOrderCost(@Param("begin") String begin,@Param("end") String end);
	
	/**
	 * 按照省份来源订单数
	 * @Method  analyDateOrderData
	 * @Return List<AnalyResultBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年10月23日 下午9:37:17
	 * @Version 1.0
	 */
	@Select("SELECT province AS `key`, SUM(1) AS `value` FROM xiakee_yzorders WHERE `status` in ('TRADE_BUYER_SIGNED','WAIT_SELLER_SEND_GOODS','WAIT_BUYER_CONFIRM_GOODS') AND created BETWEEN #{begin} AND #{end} GROUP BY `key` ORDER BY `value` DESC;")
    public List<AnalyResultBean> analyProvinceOrderData(@Param("begin") String begin,@Param("end") String end);
}
