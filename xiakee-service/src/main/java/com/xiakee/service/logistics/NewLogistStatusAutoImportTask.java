package com.xiakee.service.logistics;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiakee.dao.logistics.BoxnoDao;
import com.xiakee.dao.logistics.LogisticsDao;
import com.xiakee.dao.yz.YouzanOrderDao;
import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.logistics.BoxnoJson;
import com.xiakee.domain.logistics.LogisticsBean;
import com.xiakee.domain.logistics.LogisticsDiyouBean;
import com.xiakee.domain.logistics.UdfexDetailBean;
import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.domain.utils.LogistNodeEnum;
import com.xiakee.domain.utils.SmscContentTypeEnum;
import com.xiakee.domain.utils.TransferTypeEnum;
import com.xiakee.domain.utils.UdfexParamTypeEnum;
import com.xiakee.ecdao.order.EcOrderDao;
import com.xiakee.ecdao.order.XiakeeHaulMapper;
import com.xiakee.ecdao.order.XiakeeHaulProcessMapper;
import com.xiakee.service.AutoExecuteTasker;
import com.xiakee.service.smsc.OrderSmscNoticeService;
import com.xiakee.service.utils.BaiweiBoxnoUtil;
import com.xiakee.service.utils.UdfexApiUtil;

public class NewLogistStatusAutoImportTask {
	private static Logger log = Logger.getLogger(NewLogistStatusAutoImportTask.class);
	private static Boolean execute = true;// 执行程序开关

	@Autowired
	private LogisticsDao logisticsDao;
	@Autowired
	private BoxnoDao boxnoDao;
	@Autowired
	private YouzanOrderDao orderDao;
	@Autowired
	private EcOrderDao ecOrderDao;

	@Autowired
	private OrderSmscNoticeService smscNoticeService;
	@Autowired
	private XiakeeHaulMapper xiakeeHaulMapper;
	@Autowired
	private XiakeeHaulProcessMapper xiakeeHaulProcessMapper;

	/**
	 * 后台导入最新的订单物流节点信息
	 * 
	 * @Method importLastLogistNodeData
	 * @Return void
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月20日 下午5:01:18
	 * @Version 1.0
	 */
	public void importLastLogistNodeData() {
		log.info("后台自动执行物流信息监控程序：" + execute);
		if (execute) {
			try {
				execute = false;// 锁住程序，保持单次运行
				// 返回order_id，ship_name
				List<LogisticsBean> orders = logisticsDao.findAllNotLogistOrdersByInfoId();
				if (orders != null) {
					for (LogisticsBean bean : orders) {
						executeOrderStatus(bean);
					}
				} else {
					log.info("没有订单物流信息执行任务");
				}
				log.info("本次执行物流信息监控程序结束");
			} catch(Exception e){
				e.printStackTrace();
			}finally {
				// 恢复使用状态
				execute = true;
			}
		} else {
			log.info("上次程序执行尚未完毕，本次忽略");
		}
	}

	/**
	 * 执行具体的订单状态更新
	 * 
	 * @Method executeOrderStatus
	 * @Return void
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年8月3日 下午4:04:01
	 * @Version 1.0
	 */
	private void executeOrderStatus(LogisticsBean logisticsBean) {
		try {
			String infoId = String.valueOf(logisticsBean.getInfoId());
			log.info("开始抓取订单物流数据：" + infoId);
			Long index = logisticsDao.getLastLogisNodeByInfoId(infoId);
			int nodeIndex = 0;
			if (index == null) {
				return;
			} else {
				nodeIndex = index.intValue();
				if (nodeIndex >= 100) {
					return;
				}
			}
			if (nodeIndex >= LogistNodeEnum.EXPRESSNO.toCode()) {
				List<BoxnoBean> beans = boxnoDao.findExpressnosByInfoId(infoId);
				if (beans != null) {
					for (BoxnoBean bean : beans) {
						String expressno = bean.getExpressno();
						index = logisticsDao.getLastLogisNodeByInfoId(infoId);// 需要获取的下一个节点
						if (index == null) {
							continue;
						} else {
							nodeIndex = index.intValue();
							nodeIndex++;
						}
						log.info("即将抓取的订单（" + infoId + "）物流(" + expressno + ")   信息节点: " + nodeIndex);
						if (StringUtils.isBlank(expressno) || nodeIndex < 5 || nodeIndex >= 100) {
							continue;
						}
						if (TransferTypeEnum.BAIWEI.toCode() == bean.getTransferId()) {
							log.info("开始执行读取【百威】物流信息任务，海外物流号：" + expressno);
							BoxnoJson.Boxno boxno = BaiweiBoxnoUtil.getBoxnoLogistNodeInfo(expressno, nodeIndex);
							if (boxno == null && nodeIndex == LogistNodeEnum.STORAGE.toCode()) {
								// 有可能是即刻出库，没有入库时间
								nodeIndex++;
								boxno = BaiweiBoxnoUtil.getBoxnoLogistNodeInfo(expressno, nodeIndex);
							}
							if (boxno != null) {
								setLogistNode(boxno.getTime(), boxno.getMessage(), infoId, nodeIndex, TransferTypeEnum.BAIWEI);
							}
						} else if (TransferTypeEnum.UDFEX.toCode() == bean.getTransferId() || TransferTypeEnum.UDFEXJP.toCode() == bean.getTransferId()) {
							log.info("开始执行读取【递优】物流信息任务，海外物流号：" + expressno);
							if (nodeIndex - 1 > LogistNodeEnum.EXPRESSNO.toCode() && nodeIndex - 1 < 20) {
								// 老的物流节点
								UdfexDetailBean udfexData = UdfexApiUtil.findUdfexLogistict(expressno, null, nodeIndex);
								if (udfexData != null) {
									setLogistNode(udfexData.getActionDateTime(), udfexData.getActionDesc(), infoId, udfexData.getNodeIndex(), TransferTypeEnum.UDFEX);
								}
							} else {
								// 新的物流节点
								List<UdfexDetailBean> infoDatas = UdfexApiUtil.findUdfexLogistict(expressno, null);
								if (infoDatas != null && infoDatas.size() > 0) {
									setLogistNode(infoDatas, nodeIndex - 1, infoId);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("本订单物流信息抓取失败", e);
		}
	}

	private void setLogistNode(List<UdfexDetailBean> infoDatas, int nodeIndex, String infoId) {
		List<LogisticsDiyouBean> taskBean = new ArrayList<LogisticsDiyouBean>();
		if (nodeIndex == LogistNodeEnum.EXPRESSNO.toCode()) {
			// 获取第一个节点
			for (UdfexDetailBean udfexDetailBean : infoDatas) {
				if (!(StringUtils.equals(udfexDetailBean.getActionCode(), UdfexParamTypeEnum.SM.toName()) || StringUtils.equals(udfexDetailBean.getActionCode(), UdfexParamTypeEnum.PA.toName())
						|| StringUtils.equals(udfexDetailBean.getActionCode(), UdfexParamTypeEnum.AP.toName()) || StringUtils.equals(udfexDetailBean.getActionCode(), UdfexParamTypeEnum.UC.toName()))) {
					LogisticsDiyouBean diyouBean = new LogisticsDiyouBean();
					diyouBean.setOpenSmsc(false);
					diyouBean.setInfoId(Long.valueOf(infoId));
					if (StringUtils.equals(udfexDetailBean.getActionCode(), UdfexParamTypeEnum.OK.toName())) {
						udfexDetailBean.setSeqNo(100);
					} else {
						udfexDetailBean.setSeqNo(udfexDetailBean.getSeqNo() + 20);
					}
					diyouBean.setUdfexDetailBean(udfexDetailBean);
					diyouBean.setNode(udfexDetailBean.getSeqNo());
					taskBean.add(diyouBean);
				}
			}
		} else {
			// 获取下一个节点
			int seqNo = nodeIndex - 20 + 1;
			for (UdfexDetailBean udfexDetailBean : infoDatas) {
				if (udfexDetailBean.getSeqNo() >= seqNo
						&& !(StringUtils.equals(udfexDetailBean.getActionCode(), UdfexParamTypeEnum.SM.toName()) || StringUtils.equals(udfexDetailBean.getActionCode(), UdfexParamTypeEnum.PA.toName())
								|| StringUtils.equals(udfexDetailBean.getActionCode(), UdfexParamTypeEnum.AP.toName()) || StringUtils.equals(udfexDetailBean.getActionCode(),
								UdfexParamTypeEnum.UC.toName()))) {
					LogisticsDiyouBean diyouBean = new LogisticsDiyouBean();
					diyouBean.setOpenSmsc(false);
					diyouBean.setInfoId(Long.valueOf(infoId));
					if (StringUtils.equals(udfexDetailBean.getActionCode(), UdfexParamTypeEnum.OK.toName())) {
						udfexDetailBean.setSeqNo(100);
					} else {
						udfexDetailBean.setSeqNo(udfexDetailBean.getSeqNo() + 20);
					}
					if(StringUtils.equals(udfexDetailBean.getActionCode(), UdfexParamTypeEnum.CI.toName())) {
						String memo = udfexDetailBean.getMemo();
						udfexDetailBean.setMemo(memo.replaceAll("计费重量\\d*\\.*\\d* +kg，", ""));
					}
					diyouBean.setUdfexDetailBean(udfexDetailBean);
					diyouBean.setNode(udfexDetailBean.getSeqNo());
					taskBean.add(diyouBean);
				}
			}
		}
		if (taskBean.size() > 0) {
			OrderLogistNoticeDiyouTasker task = new OrderLogistNoticeDiyouTasker(orderDao, logisticsDao, ecOrderDao, xiakeeHaulMapper, xiakeeHaulProcessMapper);
			task.setTaskBean(taskBean);
			AutoExecuteTasker.addAutoExecuteTasker(task);
		}
	}

	private boolean setLogistNode(String time, String message, String infoId, int nodeIndex, TransferTypeEnum transType) {
		if (StringUtils.isNotBlank(time) && StringUtils.isNotBlank(message)) {
			log.info("开始导入最新的订单物流节点信息：(" + infoId + ")当前节点信息：" + message + "   nodeIndex:" + nodeIndex);

			boolean isOK = false;// 是否最后一条物流节点信息，如果是可插入结束标识符

			boolean returnFalse = false;// 当没有匹配结果是返回false

			LogisticsBean logistBean = new LogisticsBean();
			logistBean.setInfoId(Long.valueOf(infoId));
			logistBean.setCreated(time);// 物流节点时间

			switch (nodeIndex) {
			case 5:
				logistBean.setLogistNode(LogistNodeEnum.STORAGE);
				logistBean.setSmscType(SmscContentTypeEnum.LOGISTTWO);
				break;

			case 6:
				logistBean.setLogistNode(LogistNodeEnum.TERMINAL);
				logistBean.setSmscType(null);
				break;

			case 7:
				logistBean.setLogistNode(LogistNodeEnum.BOARDING);
				logistBean.setSmscType(SmscContentTypeEnum.LOGISTTHRESS);
				break;

			case 8:
				logistBean.setLogistNode(LogistNodeEnum.CUSTOMS);
				logistBean.setSmscType(null);
				break;

			case 9:
				logistBean.setLogistNode(LogistNodeEnum.DOMESTIC);
				logistBean.setSmscType(SmscContentTypeEnum.LOGISTFOUR);
				if (TransferTypeEnum.BAIWEI == transType) {
					isOK = true;// 百威物流执行最后一条物流节点信息
				}
				break;

			case 10:
				logistBean.setLogistNode(LogistNodeEnum.FINISH);
				logistBean.setSmscType(null);
				if (TransferTypeEnum.UDFEX == transType || TransferTypeEnum.UDFEXJP == transType) {
					isOK = true;// 递优物流执行最后一条物流节点信息
				}
				break;

			default:
				returnFalse = true;
				break;
			}

			if (returnFalse) {
				return false;// 没有匹配的类型
			}

			this.smscNoticeService.sendOrderNotice(logistBean);

			if (isOK) {
				LogisticsBean okBean = new LogisticsBean();
				okBean.setInfoId(Long.valueOf(infoId));
				okBean.setLogistNode(LogistNodeEnum.OK);
				okBean.setSmscType(null);
				this.smscNoticeService.sendOrderNotice(okBean);
			}
			return true;
		} else {
			log.info("本次任务没有抓取到货品(" + infoId + ")节点的信息：" + nodeIndex);
		}
		return false;
	}
}
