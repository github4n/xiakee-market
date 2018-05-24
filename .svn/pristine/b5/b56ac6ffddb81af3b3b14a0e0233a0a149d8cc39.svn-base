package com.xiakee.service.logistics.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.dao.logistics.AbroadOrderDao;
import com.xiakee.dao.logistics.BoxnoDao;
import com.xiakee.dao.logistics.LogisticsDao;
import com.xiakee.dao.order.ErpOrderDao;
import com.xiakee.dao.order.PurchLogsDao;
import com.xiakee.dao.smsc.SmscRecordDao;
import com.xiakee.dao.smsc.SmscResultDao;
import com.xiakee.dao.yz.YouzanOrderDao;
import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.domain.order.PurchLogsBean;
import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.domain.yz.YzordersBean;
import com.xiakee.service.logistics.LogisticsService;

@Service
public class LogisticsServiceImpl implements LogisticsService {
	private static Logger log = Logger.getLogger(LogisticsServiceImpl.class);

	@Autowired
	private YouzanOrderDao orderDao;
	
	@Autowired
	private LogisticsDao LogisticsDao;
	
	@Autowired
	private AbroadOrderDao abroadDao;
	
	@Autowired
	private BoxnoDao boxnoBao;
	
	@Autowired
	private SmscRecordDao recordDao;
	
	@Autowired
	private SmscResultDao resultDao;
	
	@Autowired
	private ErpOrderDao erpOrderDao;
	
	@Autowired
	private PurchLogsDao purchlogDao;
	
	private static final int PAGE_SIZE = 20;
	
	/**
	 * 暂未使用
	 * @Method  addLogistics
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年6月17日 下午1:56:29
	 * @Version 1.0
	 */
	@Override
	public YzordersBean addLogistics(String orderid) {
		YzordersBean bean = null;
		if(StringUtils.isNotBlank(orderid)){
			bean = orderDao.findOrderByOrderid(orderid);
			log.info("=====LogisticsServiceImpl======YzordersBean======" + bean);
			List<YzorderInfoBean> infos = orderDao.findOrderInfoByOrderid(bean.getOrderid());
			if(infos != null && infos.size() > 1){
				bean.setTitle(bean.getTitle() + "等物品");
			}
//			
//			SmscContentBean smsc = SmscTempletUtil.getSmscContentEX(bean);
//			AutoExecuteTask<SmscContentBean> tasker = new SmscControlTasker(recordDao,resultDao);
//			tasker.setTaskBean(smsc);
//			AutoExecuteTasker.addAutoExecuteTasker(tasker);
//			
//			LogisticsBean logisBean = new LogisticsBean();
//			logisBean.setOrderid(bean.getOrderid());
//			logisBean.setContent(smsc.getContent());
//			LogisticsDao.addLogistics(logisBean);
//			log.info("====LogisticsServiceImpl========addLogistics===成功处理物流节点=" + logisBean);
		}
		return bean;
	}

	@Override
	public List<YzordersBean> displayAllLogistics(int page) {
		int begin = (page - 1) * PAGE_SIZE;
		//获取所有已付款尚未海外下单的信息列表
		List<YzordersBean> beans = orderDao.getAllYzordersByWaitSend();
		//待删除的信息
		List<YzordersBean> reBeans = new ArrayList<YzordersBean>();
		
		for(YzordersBean bean:beans){
			List<YzorderInfoBean> infoBeans = orderDao.findInfoOutAbroadId(bean.getOrderid());
			if(infoBeans != null && infoBeans.size() > 0){
				for(YzorderInfoBean infoBean:infoBeans){
					Long productId = erpOrderDao.getProductIdByItemId(infoBean.getItem_id());
					infoBean.setProdUrl("http://www.xiakee.com/product-" + productId + ".html");
					List<PurchLogsBean> purchLogsBeans = purchlogDao.findPurchLogsByInfoId(infoBean.getId());
					String content =  "";
					if(purchLogsBeans != null){
						for(PurchLogsBean logBean:purchLogsBeans){
							if(StringUtils.isNotBlank(logBean.getContent())){
								content += logBean.getCreated() + "【" + logBean.getName() + "】:" + logBean.getContent() + "<br>";
							}
						}
					}
					infoBean.setRemark(content);
				}
				bean.setBeans(infoBeans);
				if(!bean.getOrderid().contains("E")){
					EcOrderBean ecOrderBean = erpOrderDao.findOrderById(Long.parseLong(bean.getOrderid()));
					if(ecOrderBean != null){
						bean.setMemo(ecOrderBean.getMemo());
					}
				}
			}else {
				reBeans.add(bean);
			}
		}
		//删除为空的数据
		for(YzordersBean bean:reBeans){
			beans.remove(bean);
		}
		int size = beans.size();
		if(begin >= size){
			return null;
		}else if(size - begin > PAGE_SIZE){
			size = begin + PAGE_SIZE;
		}else {
			size = size;
		}
		log.info("内存截取结果集信息：size=" + beans.size() + "  begin=" + begin + "   sum=" + size);
		return beans.subList(begin, size);
	}

	@Override
	public Integer updateBoxnoInfo(BoxnoBean bean) {
		Integer sum = 0;
		if(bean != null){
			sum = boxnoBao.declaredBoxnoBean(bean);
		}
		return sum;
	}

	@Override
	public Integer resetBoxnoInfo(Long infoId,Long abroadId) {
		Integer sum = 0;
		BoxnoBean bean = new BoxnoBean();
		bean.setAbroadId(abroadId);
		bean.setInfoId(infoId);
		bean.setPrice(null);//清空上次录入的错误信息
		bean.setSum(0);
		bean.setTrend(0);
			
		sum = boxnoBao.declaredBoxnoBean(bean);
		return sum;
	}

	@Override
	public Integer deleteAbroadExpressno(String outOrderId) {
		Integer sum = 0;
		if(StringUtils.isNotBlank(outOrderId)){
			List<BoxnoBean> beans = boxnoBao.getBoxnoById(Long.parseLong(outOrderId));
			if(beans != null){
				Long id = beans.get(0).getAbroadId();
				for(BoxnoBean bean:beans){
					boxnoBao.deleteBoxnoByAbroadIdAndInfoId(bean);
				}
				sum = abroadDao.deleteAbroadOrderById(id);
			}
		}
		return sum;
	}
}
