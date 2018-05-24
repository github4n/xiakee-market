package com.xiakee.service.analy;

import java.util.List;

import com.xiakee.domain.analy.OrderReferrerBean;
import com.xiakee.domain.analy.ReferrerUrlBean;
import com.xiakee.domain.order.EcOrderBean;

public interface OrderReferrerService {
	Integer addOrderRefCode(OrderReferrerBean bean);
	
	List<OrderReferrerBean> findOwnOrderReferByUserId();
	
	OrderReferrerBean findOrderReferrerBeanById(Long id);
	
	List<EcOrderBean> findOrderByReferCode(String referCode);
	
	Integer addReferrerUrl(ReferrerUrlBean bean);
	
	List<ReferrerUrlBean> findReferrerUrlsByUserId();
	
	Integer deleteReferUrl(Long id);
	
	/**
	 * 根据推广链接获取所有推广组织的最终链接
	 * @param id
	 * @return
	 */
	List<OrderReferrerBean> getPromotionUrls(Long id);
}
