package com.xiakee.service.logistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.xiakee.dao.logistics.LogisticsDao;
import com.xiakee.dao.yz.YouzanOrderDao;
import com.xiakee.domain.ecgoods.XiakeeHaul;
import com.xiakee.domain.ecgoods.XiakeeHaulProcess;
import com.xiakee.domain.logistics.LogisticsBean;
import com.xiakee.domain.logistics.LogisticsDiyouBean;
import com.xiakee.domain.order.EcOrderLogistBean;
import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.domain.yz.YzordersBean;
import com.xiakee.ecdao.order.EcOrderDao;
import com.xiakee.ecdao.order.XiakeeHaulMapper;
import com.xiakee.ecdao.order.XiakeeHaulProcessMapper;
import com.xiakee.service.AutoExecuteTask;
import com.xiakee.service.utils.TimeUtils;

public class OrderLogistNoticeDiyouTasker extends AutoExecuteTask<List<LogisticsDiyouBean>> {
	private static Logger log = Logger.getLogger(OrderLogistNoticeDiyouTasker.class);

	private YouzanOrderDao orderDao;
	private LogisticsDao logisticsDao;
	private EcOrderDao ecOrderDao;
	private XiakeeHaulMapper xiakeeHaulMapper;
	private XiakeeHaulProcessMapper xiakeeHaulProcessMapper;

	public OrderLogistNoticeDiyouTasker(YouzanOrderDao orderDao, LogisticsDao logisticsDao, EcOrderDao ecOrderDao, XiakeeHaulMapper xiakeeHaulMapper,XiakeeHaulProcessMapper xiakeeHaulProcessMapper) {
		this.orderDao = orderDao;
		this.logisticsDao = logisticsDao;
		this.ecOrderDao = ecOrderDao;
		this.xiakeeHaulMapper = xiakeeHaulMapper;
		this.xiakeeHaulProcessMapper = xiakeeHaulProcessMapper;
	}

	@Override
	public boolean executeTask() {
		// getTaskBean()该bean中必须存在node值，orderid和infoid两个值必须存在一个,还有是否需要发送邮件
		boolean isOK = false;
		for (LogisticsDiyouBean diyouBean : getTaskBean()) {
			if (getTaskBean() != null && this.orderDao != null && this.logisticsDao != null) {
				log.info("后台自动执行物流节点信息：" + getTaskBean());
				YzordersBean bean = null;

				String infoId = String.valueOf(diyouBean.getInfoId());
				// 获取当前物流节点信息是否存在
				if (StringUtils.isNotBlank(infoId) && infoId != null && infoId != "null") {
					bean = this.orderDao.findOrderLogistDiyouByInfoId(diyouBean);
				} else {
					bean = this.orderDao.findOrderLogistDiyouByorderId(diyouBean);
				}

				if (bean == null) {// 当前物流节点不存在
					if (StringUtils.isNotBlank(infoId) && infoId != null && infoId != "null") {
						bean = this.orderDao.findOrderByInfoId(diyouBean.getInfoId());
					} else {
						bean = this.orderDao.findOrderByOrderid(diyouBean.getOrderid());
					}

					LogisticsBean logistBean = new LogisticsBean();
					logistBean.setInfoId(diyouBean.getInfoId());
					logistBean.setOrderid(bean.getOrderid());
					logistBean.setContent(diyouBean.getUdfexDetailBean().getMemo());
					logistBean.setNode(diyouBean.getUdfexDetailBean().getSeqNo());
					int sum = logisticsDao.addLogistics(logistBean);
					if (sum > 0) {// 本地物流节点信息插入成功，把该信息插入到商场订单物流中
						if (StringUtils.isNotBlank(infoId) && infoId != null && infoId != "null") {
							YzorderInfoBean yzorderBean = orderDao.getYzorderInfoBeanById(Long.valueOf(infoId));
							if(yzorderBean != null){
								try{
									Map map = new HashMap();
									map.put("orderId", yzorderBean.getOrderid());
									map.put("itemId", yzorderBean.getItem_id());
								XiakeeHaul xiakeeHaul = xiakeeHaulMapper.selectByOrderIdAndItemId(map);
								
								if(xiakeeHaul != null){
								XiakeeHaulProcess xiakeeHaulProcess = new XiakeeHaulProcess();
								xiakeeHaulProcess.setRelId(xiakeeHaul.getHaulId());
								xiakeeHaulProcess.setOrderId(xiakeeHaul.getOrderId());
								xiakeeHaulProcess.setOpId(1);
								xiakeeHaulProcess.setOpName("admin");
								xiakeeHaulProcess.setBillType("order");
								xiakeeHaulProcess.setBehavior("updates");
								xiakeeHaulProcess.setResult("SUCCESS");
								
								String time = diyouBean.getUdfexDetailBean().getActionDateTime();
								if (StringUtils.isNotBlank(time)) {
									xiakeeHaulProcess.setAlttime(TimeUtils.formatTimeForPhp(time).intValue());
								} else {
									xiakeeHaulProcess.setAlttime(TimeUtils.getCurrentTimeForPhp().intValue());
								}
								//xiakeeHaulProcess.setAlttime(TimeUtils.getCurrentTime().intValue());
								xiakeeHaulProcess.setLogText(diyouBean.getUdfexDetailBean().getMemo());
								int xiakeeNum = xiakeeHaulProcessMapper.insert(xiakeeHaulProcess);
								if(xiakeeNum > 0){
									if ("OK".equals(diyouBean.getUdfexDetailBean().getActionCode())) {
										// boolean delivery =
										// EcstoreApiUtil.updateOrderStatus(bean.getOrderid(),
										// "finish");
										Map<String, Object> params = new HashMap<String, Object>();
										params.put("status", "finish");
										params.put("order_id", bean.getOrderid());
										int row = ecOrderDao.updateOrderStatus(params);
										log.info("成功修改商城订单的结束状态：" + (row > 0));
									}
								}
								}
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						} 
						/*List<XiakeeHaul> xiakeeHaulList = xiakeeHaulMapper.selectByOrderId(Long.parseLong(bean.getOrderid()));
						if(xiakeeHaulList == null || xiakeeHaulList.size() <= 0){
							log.info("本地物流信息节点插入成功，即将导入到商城订单物流节点上");
							EcOrderLogistBean ecBean = new EcOrderLogistBean();
							ecBean.setRel_id(bean.getOrderid());
							String time = diyouBean.getUdfexDetailBean().getActionDateTime();
							if (StringUtils.isNotBlank(time)) {
								ecBean.setAlttime(TimeUtils.formatTimeForPhp(time));
							} else {
								ecBean.setAlttime(TimeUtils.getCurrentTimeForPhp());
							}
							ecBean.setLog_text(diyouBean.getUdfexDetailBean().getMemo());

							int ecSum = ecOrderDao.addOrderLogist(ecBean);
							if (ecSum > 0) {
								log.info("成功插入商城物流节点：" + ecBean);// 录入海外包裹信息阶段修改商城订单发货状态
								if ("OK".equals(diyouBean.getUdfexDetailBean().getActionCode())) {
									// boolean delivery =
									// EcstoreApiUtil.updateOrderStatus(bean.getOrderid(),
									// "finish");
									Map<String, Object> params = new HashMap<String, Object>();
									params.put("status", "finish");
									params.put("order_id", bean.getOrderid());
									int row = ecOrderDao.updateOrderStatus(params);
									log.info("成功修改商城订单的结束状态：" + (row > 0));
								}
							} else {
								log.error("插入商城物流节点失败：" + ecBean);
							}	
						}*/
					}
				} else {
					log.info("该订单已发送过短信" + bean);
				}
				isOK = true;
			}
		}
		return isOK;
	}

}
