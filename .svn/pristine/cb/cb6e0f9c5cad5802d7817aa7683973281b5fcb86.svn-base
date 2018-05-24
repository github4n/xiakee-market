package com.xiakee.service.yz;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiakee.dao.order.ErpOrderDao;
import com.xiakee.dao.smsc.SmscRecordDao;
import com.xiakee.dao.smsc.SmscResultDao;
import com.xiakee.dao.yz.YouzanOrderDao;
import com.xiakee.domain.logistics.LogisticsBean;
import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.domain.order.EcOrderItemBean;
import com.xiakee.domain.smsc.EmailContentBean;
import com.xiakee.domain.smsc.SmscContentBean;
import com.xiakee.domain.utils.LogistNodeEnum;
import com.xiakee.domain.utils.SmscContentTypeEnum;
import com.xiakee.domain.yz.YouzanResponseJson;
import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.domain.yz.YzordersBean;
import com.xiakee.ecdao.order.EcOrderDao;
import com.xiakee.service.AutoExecuteTasker;
import com.xiakee.service.smsc.AccountEmailService;
import com.xiakee.service.smsc.EmailAutoSendTask;
import com.xiakee.service.smsc.OrderSmscNoticeService;
import com.xiakee.service.smsc.SmscControlTasker;
import com.xiakee.service.utils.EmailContentUtil;
import com.xiakee.service.utils.SmscTempletUtil;
import com.xiakee.service.utils.TimeUtils;
import com.xiakee.service.utils.php.PhpSerializerUtil;

public class YzOrderLeadInTasker{
	private static Logger log = Logger.getLogger(YzOrderLeadInTasker.class);

	@Autowired
	private YouzanOrderDao orderDao;
	@Autowired
	private AccountEmailService emailService;
	@Autowired
	private EcOrderDao ecOrderDao;
	@Autowired
	private ErpOrderDao erpOrderDao;
	@Autowired
	private OrderSmscNoticeService smscNoticeService;
	@Autowired
	private SmscRecordDao recordDao;
	@Autowired
	private SmscResultDao resultDao;
	
	public void executeEcOrderDataLoading() {
		try {
			//获取最后的更新时间
			Long last_modified = erpOrderDao.lastOrderDate();
			if(last_modified == null){
				last_modified = 0L;
			}
			
//			last_modified = TimeUtils.getCurrentWeekTime();
			
			List<EcOrderBean> orderBeans = ecOrderDao.findAllEcstoreOrders(last_modified);
			log.info("开始执行商城订单数据同步程序：");
			for(EcOrderBean bean:orderBeans){
				//为了矫正之前漏录入的信息
//				if(bean.getOrder_id() == 151001104942640L){
//					log.info("测试执行导入数据：" + bean);
//					importOrderDataToYzOrder(bean);
//				}
				
				EcOrderBean oldOrder = erpOrderDao.findOrderById(bean.getOrder_id());
				boolean isSendEMail = false;//以便判断是否发送邮件
				if(oldOrder == null){//本条数据不存在,执行插入操作
					if(StringUtils.equals("1", bean.getPay_status()) || StringUtils.equals("2", bean.getPay_status())){
						isSendEMail = true;
					} 
					erpOrderDao.importEcstoreOrder(bean);
					List<EcOrderItemBean> itemBeans = ecOrderDao.findAllOrderItemsByOrderId(bean.getOrder_id());
					for(EcOrderItemBean itemBean:itemBeans){
						erpOrderDao.importEcstoreOrderItem(itemBean);
					}
				}else {
					if(!StringUtils.equals("1", oldOrder.getPay_status()) && !StringUtils.equals("2", oldOrder.getPay_status())){
						if(StringUtils.equals("1", bean.getPay_status()) || StringUtils.equals("2", bean.getPay_status())){
							isSendEMail = true;
						} 
					} 
					erpOrderDao.updateEcstoreOrderByOrderId(bean);
				}
				
				if(isSendEMail){
//					//发送订单支付成功短信
//					LogisticsBean logistBean = new LogisticsBean();
//					logistBean.setOrderid(String.valueOf(bean.getOrder_id()));
//					logistBean.setLogistNode(LogistNodeEnum.PAY);
//					logistBean.setSmscType(SmscContentTypeEnum.LOGISTONE);
//					this.smscNoticeService.sendOrderNotice(logistBean);
					SmscContentBean smsc = SmscTempletUtil.getSmscLogistics(bean.getShip_mobile(), bean.getShip_name(), TimeUtils.displayLogistTime(bean.getCreatetime()), SmscContentTypeEnum.LOGISTONE);
					SmscControlTasker task = new SmscControlTasker(recordDao,resultDao);
					task.setTaskBean(smsc);
					AutoExecuteTasker.addAutoExecuteTasker(task);
					importOrderDataToYzOrder(bean);
				}
			}
		} catch (Exception e) {
			log.error("商城订单数据同步失败",e);
		}
	}
	
	private void importOrderDataToYzOrder(EcOrderBean bean){
		try {
			YzordersBean yzBean = new YzordersBean();
			yzBean.setOrderid(String.valueOf(bean.getOrder_id()));
			yzBean.setName(bean.getShip_name());
			String mobile = bean.getShip_mobile();
			if(StringUtils.isBlank(mobile)){
				mobile = bean.getShip_tel();
			}
			yzBean.setMobile(mobile);
			yzBean.setTitle(bean.getShip_addr());
			yzBean.setStatus("WAIT_SELLER_SEND_GOODS");
			
			String area = bean.getShip_area();
			if(StringUtils.isNotBlank(area)){
				String[] arr = area.split(":");
				if(arr.length == 3){
					String[] pcd = arr[1].split("/");
					if(pcd != null && pcd.length > 0){
						if(pcd.length > 0){
							yzBean.setProvince(pcd[0]);
						}
						if(pcd.length > 1){
							yzBean.setCity(pcd[1]);
						}
						if(pcd.length > 2){
						    yzBean.setDistrict(pcd[2]);
						}
					}
				}
			}
			
			yzBean.setAddress(bean.getShip_addr());
			yzBean.setCreated(TimeUtils.displayLogistTime(bean.getCreatetime()));
			String updateTime = TimeUtils.displayLogistTime(bean.getLast_modified());
			yzBean.setUpdate_time(updateTime);
			yzBean.setPay_time(updateTime);
			yzBean.setPayment(String.valueOf(bean.getPayed()));
			yzBean.setPrice(String.valueOf(bean.getPayed()));
			yzBean.setZipCode(bean.getShip_zip());
			orderDao.importYouzanOrder(yzBean);
			String emailContetn = EmailContentUtil.getEmailContentFromYzorder(yzBean);
			
			List<EcOrderItemBean> itemBeans = ecOrderDao.findAllOrderItemsByOrderId(bean.getOrder_id());
			for(EcOrderItemBean itemBean:itemBeans){
				YzorderInfoBean orderInfoBean = new YzorderInfoBean();
				orderInfoBean.setOrderid(String.valueOf(itemBean.getOrder_id()));
				orderInfoBean.setNum(String.valueOf(itemBean.getNums()));
				orderInfoBean.setPrice(String.valueOf(itemBean.getPrice()));
				orderInfoBean.setSku_properties_name(PhpSerializerUtil.getProductAttr(itemBean.getAddon()));
				orderInfoBean.setTitle(itemBean.getName());
				orderInfoBean.setItem_id(itemBean.getItem_id());

				orderDao.addOrderInfoByOrderid(orderInfoBean);
				emailContetn = EmailContentUtil.addOrderInfoToEmailContent(
						emailContetn, orderInfoBean);
			}
			sendNewOrderMail(emailContetn);
		} catch (Exception e) {
			log.error("把商城数据导入到成功订单信息表失败：",e);
		}
	}

//	public void executeOrderDataLoading() {
//		String method = "kdt.trades.sold.get";
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("page_size", "10000");
//
//		String updateTime = orderDao.lastOrderDate();
//		updateTime = TimeUtils.increaseSecond(updateTime);
//		params.put("start_update", updateTime);
//		KdtApiClient kdtApiClient;
//		HttpResponse response;
//
//		try {
//			kdtApiClient = new KdtApiClient(APP_ID, APP_SECRET);
//			response = kdtApiClient.get(method, params);
//			// log.info("==============开始抓起有赞后台订单状态更新的时间============="
//			// + updateTime);
//			// log.info("==============抓取状态==========Response Code : ==="
//			// + response.getStatusLine().getStatusCode());
//			BufferedReader bufferedReader = new BufferedReader(
//					new InputStreamReader(response.getEntity().getContent()));
//			StringBuffer result = new StringBuffer();
//			String line = "";
//			while ((line = bufferedReader.readLine()) != null) {
//				result.append(line);
//			}
//
//			YouzanResponseJson json = JSONObject.parseObject(result.toString(),
//					YouzanResponseJson.class);
//			if (json != null && json.getResponse() != null) {
//				List<YouzanResponseJson.Trades> list = json.getResponse()
//						.getTrades();
//				// log.info("==========抓取新生成的订单信息==========总条数======"
//				// + list.size());
//				for (YouzanResponseJson.Trades trades : list) {
//					String orderid = trades.getTid();
//					YzordersBean bean = orderDao.findOrderByOrderid(orderid);
//					List<YouzanResponseJson.order> orders = trades.getOrders();
//					boolean isFirst = true;// 是第一次出现的付款状态
//					if (bean != null) {
//						if (ORDER_STATUS.equals(bean.getStatus())) {
//							isFirst = false;
//						}
//						if (!StringUtils.equals(bean.getStatus(),
//								trades.getStatus())
//								|| !StringUtils.equals(bean.getPayment(),
//										trades.getPayment())
//								|| !StringUtils.equals(bean.getUpdate_time(),
//										trades.getUpdate_time())
//								|| !StringUtils.equals(bean.getPay_time(),
//										trades.getPay_time())) {
//							bean.setStatus(trades.getStatus());
//							bean.setPayment(trades.getPayment());
//							bean.setUpdate_time(trades.getUpdate_time());
//							bean.setPay_time(trades.getPay_time());
//							orderDao.updateOrderStatusByOrderId(bean);
//							log.info("订单已存在,执行修改状态操作：" + bean);
//						} else {
////							log.info("执行时间：" + updateTime + "存在完全相同订单，不执行任何操作" + bean);
//						}
//					} else {
//						bean = new YzordersBean();
//						bean.setOrderid(orderid);
//						bean.setName(trades.getReceiver_name());
//						bean.setMobile(trades.getReceiver_mobile());
//						bean.setTitle(trades.getTitle());
//						bean.setStatus(trades.getStatus());
//						bean.setProvince(trades.getReceiver_state());
//						bean.setCity(trades.getReceiver_city());
//						bean.setDistrict(trades.getReceiver_district());
//						bean.setAddress(trades.getReceiver_address());
//						bean.setCreated(trades.getCreated());
//						bean.setUpdate_time(trades.getUpdate_time());
//						bean.setPay_time(trades.getPay_time());
//						bean.setPayment(trades.getPayment());
//						bean.setPrice(trades.getPrice());
//						bean.setZipCode(trades.getReceiver_zip());
//
//						int sum = orderDao.importYouzanOrder(bean);
//						if (sum > 0) {
//							if (orders != null) {
//								for (YouzanResponseJson.order o : orders) {
//									YzorderInfoBean orderInfoBean = new YzorderInfoBean();
//									orderInfoBean.setOrderid(orderid);
//									orderInfoBean.setNum(o.getNum());
//									orderInfoBean.setPrice(o.getPrice());
//									orderInfoBean.setSku_properties_name(o
//											.getSku_properties_name());
//									orderInfoBean.setTitle(o.getTitle());
//
//									orderDao.addOrderInfoByOrderid(orderInfoBean);
//								}
//							}
//						}
//					}
//
//					if (ORDER_STATUS.equals(bean.getStatus()) && isFirst) {
//						sendNewOrderMail(bean, orders);
//					}
//				}
//			} else {
//				// log.info("============本次抓取任务没有抓取到成功订单的数据============");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	private void sendNewOrderMail(YzordersBean bean,
			List<YouzanResponseJson.order> orders) {
		String emailContetn = EmailContentUtil.getEmailContentFromYzorder(bean);
		for (YouzanResponseJson.order o : orders) {
			YzorderInfoBean orderInfoBean = new YzorderInfoBean();
			orderInfoBean.setNum(o.getNum());
			orderInfoBean.setPrice(o.getPrice());
			orderInfoBean.setSku_properties_name(o.getSku_properties_name());
			orderInfoBean.setTitle(o.getTitle());

			emailContetn = EmailContentUtil.addOrderInfoToEmailContent(
					emailContetn, orderInfoBean);
		}

		EmailContentBean email = new EmailContentBean();
		 email.setTo("order@xiakee.com");
//		email.setTo("boge@xiakee.com");
		email.setSubject("小遐提醒：又有新订单了...");
		email.setHtmlText(emailContetn);

		EmailAutoSendTask emailTask = new EmailAutoSendTask();
		emailTask.setEmailService(this.emailService);
		emailTask.setTaskBean(email);
		AutoExecuteTasker.addAutoExecuteTasker(emailTask);
	}

	private void sendNewOrderMail(String emailContext) {
		EmailContentBean email = new EmailContentBean();
		 email.setTo("order@xiakee.com");
//		email.setTo("boge@xiakee.com");
		email.setSubject("小遐提醒：又有新订单了...");
		email.setHtmlText(emailContext);

		EmailAutoSendTask emailTask = new EmailAutoSendTask();
		emailTask.setEmailService(this.emailService);
		emailTask.setTaskBean(email);
		AutoExecuteTasker.addAutoExecuteTasker(emailTask);
	}
}
