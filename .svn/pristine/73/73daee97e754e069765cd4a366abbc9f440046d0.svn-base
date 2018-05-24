package com.xiakee.service.analy.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xiakee.dao.analy.OrderReferrerDao;
import com.xiakee.dao.analy.ReferrerUrlDao;
import com.xiakee.dao.logistics.BoxnoDao;
import com.xiakee.dao.order.ErpOrderDao;
import com.xiakee.dao.yz.YouzanOrderDao;
import com.xiakee.domain.analy.OrderReferrerBean;
import com.xiakee.domain.analy.ReferrerUrlBean;
import com.xiakee.domain.ecanaly.EcReferrerBean;
import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.domain.order.EcOrderItemBean;
import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.ecdao.order.EcOrderReferrerDao;
import com.xiakee.service.analy.OrderReferrerService;
import com.xiakee.service.utils.PromotionUrlUtil;
import com.xiakee.service.utils.SessionUserDetailUtil;
import com.xiakee.service.utils.TimeUtils;

@Service
public class OrderReferrerServiceImpl implements OrderReferrerService {

	@Autowired
	private OrderReferrerDao refCodeDao;
	@Value("${analy.referCode}")
	private String referCodeKey;
	@Autowired
	private EcOrderReferrerDao ecRefDao;
	@Autowired
	private ErpOrderDao erpOrderDao;
	@Autowired
	private BoxnoDao boxnoDao;
	@Autowired
	private ReferrerUrlDao urlDao;
	
	@Override
	public Integer addOrderRefCode(OrderReferrerBean bean) {
		Integer sum = 0;
		if(bean != null){
			bean.setUserid(SessionUserDetailUtil.findUserDetail().getId());
//			String code = bean.getUserid() + ":" + bean.getPromotion() + ":" + bean.getTitle();
//			code = MD5Util.getMD5Str(code);
//			bean.setCode(code);
			sum = refCodeDao.addOrderReferrerBean(bean);
		}
		return sum;
	}

	@Override
	public List<OrderReferrerBean> findOwnOrderReferByUserId() {
		List<OrderReferrerBean> beans = this.refCodeDao.findOrderReferrerBeanByUserId(SessionUserDetailUtil.findUserDetail().getId());
		for(OrderReferrerBean bean:beans){
			Integer sumInt = ecRefDao.getOrderSumByReferrer(String.valueOf(bean.getId()));
			Integer sumLike = ecRefDao.getOrderSumByReferrerLike(bean.getId() + ".%");
			int sum = 0;
			if(sumInt != null){
				sum = sumInt.intValue();
			}
			if(sumLike != null){
				sum += sumLike.intValue();
			}
			bean.setSum(sum);
		}
		return beans;
	}

	@Override
	public OrderReferrerBean findOrderReferrerBeanById(Long id) {
		return this.refCodeDao.findOrderReferrerBeanById(id);
	}

	@Override
	public List<EcOrderBean> findOrderByReferCode(String referCode) {
		List<EcOrderBean> beans = null;
		if(StringUtils.isNotBlank(referCode)){
			List<String> orderIds = ecRefDao.findReferrerByOrderid(referCode);
			List<String> orderLikes = ecRefDao.findReferrerByLikeid(referCode + ".%");
			orderIds.addAll(orderLikes);
			if(orderIds != null){
				beans = new ArrayList<EcOrderBean>();
				for(String orderid:orderIds){
					EcOrderBean ecOrder = erpOrderDao.findOrderById(Long.valueOf(orderid));
					if(ecOrder != null){
						List<EcOrderItemBean> itemeans = erpOrderDao.findAllItenByOrderid(String.valueOf(ecOrder.getOrder_id()));
						ecOrder.setBeans(itemeans);
						ecOrder.setTimeStr(TimeUtils.displayLogistTime(ecOrder.getCreatetime()));
					}
					beans.add(ecOrder);
				}
			}
		}
		return beans;
	}

	@Override
	public Integer addReferrerUrl(ReferrerUrlBean bean) {
		if(bean != null){
			bean.setUserid(SessionUserDetailUtil.findUserDetail().getId());
			return this.urlDao.addReferrerUrlBean(bean);
		}
		return null;
	}

	@Override
	public List<ReferrerUrlBean> findReferrerUrlsByUserId() {
		return this.urlDao.findReferrerUrlBeanById(SessionUserDetailUtil.findUserDetail().getId());
	}

	@Override
	public Integer deleteReferUrl(Long id) {
		return this.urlDao.deleteUrlById(id);
	}

	@Override
	public List<OrderReferrerBean> getPromotionUrls(Long id) {
		if(id != null){
			ReferrerUrlBean urlBean = urlDao.getReferrerUrlBeanById(id);
			if(urlBean != null){
				List<OrderReferrerBean> beans = this.refCodeDao.findOrderReferrerBeanByUserId(SessionUserDetailUtil.findUserDetail().getId());
				for(OrderReferrerBean bean:beans){
					String code = bean.getId() + "." + id;
					String[] urls = PromotionUrlUtil.createPromotionLink(code, urlBean.getUrl());
					bean.setPcUrl(urls[0]);
					bean.setWapUrl(urls[1]);
				}
				return beans;
			}
		}
		return null;
	}
}
