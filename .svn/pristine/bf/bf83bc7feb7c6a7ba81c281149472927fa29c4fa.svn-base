package com.xiakee.service.logistics;

import java.util.List;

import com.xiakee.domain.ecos.EcIdcardBean;
import com.xiakee.domain.logistics.LogistDetailsBean;
import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.domain.order.EcOrderLogistBean;

public interface EcOrderLogistService {

	/**
	 * 获取一个月以内成功的订单信息
	 * @Method  findAllEcstoreSuccOrders
	 * @return List<EcOrderBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com
	 * @Date 2015年6月30日 下午11:34:17
	 * @Version 1.0
	 */
	List<EcOrderBean> findAllEcstoreSuccOrders();
	
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
	List<EcOrderLogistBean> findAllLogistByOrderId(String orderId);
	
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
	int addOrderLogist(EcOrderLogistBean bean);

	int manualAddEcosLogist(EcOrderLogistBean bean);
	
	/**
	 * 根据用户id获取身份证信息
	 * @Method  findIdcardBeansByMemberid
	 * @Return List<EcIdcardBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年9月30日 下午4:25:55
	 * @Version 1.0
	 */
	List<EcIdcardBean> findIdcardBeansByMemberid(Long member_id);
	
	/**
	 * 根据订单ID获取转运公司完整的物流信息
	 * @Method  findOrderLogistInfo
	 * @Return List<LogistDetailsBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年10月4日 下午6:07:14
	 * @Version 1.0
	 */
	List<LogistDetailsBean> findOrderLogistInfo(String orderId);

	LogistDetailsBean findLogistDetailsByExpressno(String expressno);
}
