package com.xiakee.service.yz.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.xiakee.dao.logistics.LogisticsDao;
import com.xiakee.dao.order.PurchLogsDao;
import com.xiakee.dao.smsc.SmscRecordDao;
import com.xiakee.dao.smsc.SmscResultDao;
import com.xiakee.dao.wechat.WechatBindingMobileDao;
import com.xiakee.dao.yz.YouzanOrderDao;
import com.xiakee.domain.order.PurchLogsBean;
import com.xiakee.domain.print.RecipientBean;
import com.xiakee.domain.smsc.SmscContentBean;
import com.xiakee.domain.urm.SecurityUser;
import com.xiakee.domain.wechat.WechatOpenIdBean;
import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.domain.yz.YzordersBean;
import com.xiakee.service.AutoExecuteTask;
import com.xiakee.service.AutoExecuteTasker;
import com.xiakee.service.smsc.SmscControlTasker;
import com.xiakee.service.utils.SmscTempletUtil;
import com.xiakee.service.yz.YouzanOrderService;

@Service
public class YouzanOrderServiceImpl implements YouzanOrderService {
	private static Logger log = Logger.getLogger(YouzanOrderServiceImpl.class);
	private static final int PAGESIZE = 30;

	@Autowired
	private YouzanOrderDao orderDao;
	@Autowired
	private WechatBindingMobileDao wechatBindingDao;
	
	@Autowired
	private LogisticsDao logistDao;
	
	@Autowired
	private SmscRecordDao recordDao;
	
	@Autowired
	private SmscResultDao resultDao;
	
	@Autowired
	private PurchLogsDao purchlogsDao;

	@Override
	public String findOrderInfo(String fromUsername) {
		String orderInfo = "";
		String mobile = wechatBindingDao.getUserMobile(fromUsername);
		if (StringUtils.isNotBlank(mobile)) {
			List<YzordersBean> beans = orderDao
					.findUserOrderByMobile(mobile);
			if (beans.size() > 0) {
				for (YzordersBean bean : beans) {
					// resultStr += "订单ID:" + bean.getOrderid();
					orderInfo += "宝贝标题:" + bean.getTitle() + "\n";
					String status = bean.getStatus();
					if ("交易完成".equals(status)) {
						status = "国外已发货，请耐心等待";
					}
					orderInfo += "订单状态:" + status + "\n";
					orderInfo += "收货姓名:" + bean.getName() + "\n";
					orderInfo += "---------------";
				}
			} else {
				orderInfo = "<a href=\"http://xiakee.com\">点击进入遐客行</a>";
			}
		} else {
			orderInfo = "很抱歉，您的账号还没进行手机绑定，无法使用该服务！\n" + "回复手机号：138xxxxxxxx\n"
					+ "即可进行绑定，谢谢合作！";
		}

		return orderInfo;
	}

	@Override
	public String bindingMobile(WechatOpenIdBean bean) {
		String bingdingInfo = "";
		String random = SmscTempletUtil.getRandom();
		bean.setRandom(random);
		log.info(bean);
		int sum = wechatBindingDao.add(bean);
		if (sum > 0) {
			SmscContentBean smsc = SmscTempletUtil.getSmscContentYZ(bean.getMobile(), random);
			
			AutoExecuteTask<SmscContentBean> task = new SmscControlTasker(recordDao,resultDao);
			task.setTaskBean(smsc);
			AutoExecuteTasker.addAutoExecuteTasker(task);
			
			bingdingInfo = "谢谢您的绑定，短信已发送，请查收并回复验证，\n" + "格式如下：\n" + "xxxxxx";
		} else {
			bingdingInfo = "您的帐号已经绑定过了，欢迎使用其他功能！\n"
					+ "<a href=\"http://xiakee.com\">点击进入遐客行</a>\n"
					+ "回复（订单首字母）：dd，即可查询订单信息";
		}
		return bingdingInfo;
	}

	@Override
	public String verifyRandom(String openId, String random) {
		String verifyResult = "<a href=\"http://xiakee.com\">帐号绑定失败，请进入遐客行联系管理员</a>";
		String oldRandom = wechatBindingDao.findRandom(openId);
		if (oldRandom.equals(random)) {
			int sum = wechatBindingDao.verifyRandom(openId);
			if (sum > 0) {
				verifyResult = "绑定成功，你可以使用如下功能\n" + "回复（订单首字母）：dd，即可查询订单信息";
			}
		}
		return verifyResult;
	}

	@Override
	public YzordersBean findOrderInfoByOrderid(String orderid) {
		return orderDao.findOrderByOrderid(orderid);
	}

	@Override
	public List<RecipientBean> displayOrderPrintTasker(int page) {
		int begin = (page - 1) * PAGESIZE;
		List<RecipientBean> beans = orderDao.displayOrderPrintTasker(begin,PAGESIZE);
		return beans;
	}

	@Override
	public RecipientBean findPrintInfoByInfoid(String infoId) {
		return orderDao.findPrintByInfoId(infoId);
	}

	@Override
	public List<RecipientBean> displayOrderPrintedLIst(int page) {
		int begin = (page - 1) * PAGESIZE;
		return orderDao.displayPrintedList(begin,PAGESIZE);
	}

	@Override
	public List<RecipientBean> searchOrderPrintTask(RecipientBean bean) {
		String orderid = bean.getOrderid();
		if(StringUtils.isNotBlank(orderid)){
			orderid = "%" + orderid.trim() + "%";
		}else {
			orderid = "%";
		}
		String name = bean.getName();
		if(StringUtils.isNotBlank(name)){
			name = "%" + name.trim() + "%";
		}else {
			name = "%";
		}
		String mobile = bean.getMobile();
		if(StringUtils.isNotBlank(mobile)){
			mobile = "%" + mobile.trim()+ "%";
		}else {
			mobile = "%";
		}
		return this.orderDao.searchOrderPrintTask(orderid, name, mobile);
	}

	@Override
	public Integer updateOrderInfoRemarkById(Long id, String remark) {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer sum = null;
    	if(object != null && object instanceof UserDetails && StringUtils.isNotBlank(remark)){
        	SecurityUser userDetails = (SecurityUser) object;
        	PurchLogsBean bean = new PurchLogsBean();
        	bean.setInfoId(id);
        	bean.setUserid(userDetails.getId());
        	bean.setName(userDetails.getName());
        	bean.setContent(remark);
        	sum = purchlogsDao.addPurchLogBean(bean);
    	}
		return sum;
	}

	@Override
	public String findOrderInfoRemarkById(Long id) {
		List<PurchLogsBean> purchLogsBeans = purchlogsDao.findPurchLogsByInfoId(id);
		String content =  "";
		if(purchLogsBeans != null){
			for(PurchLogsBean logBean:purchLogsBeans){
				if(StringUtils.isNotBlank(logBean.getContent())){
					content += "<p>" + logBean.getCreated() + "【" + logBean.getName() + "】:" + logBean.getContent() + "</p>";
				}
			}
		}
		return content;
	}
}
