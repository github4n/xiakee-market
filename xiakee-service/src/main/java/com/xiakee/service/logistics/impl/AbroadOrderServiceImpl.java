package com.xiakee.service.logistics.impl;

import java.util.List;

import javax.swing.Box;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.dao.logistics.AbroadOrderDao;
import com.xiakee.dao.logistics.BoxnoDao;
import com.xiakee.domain.logistics.AbroadOrderBean;
import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.logistics.LogisticsBean;
import com.xiakee.domain.utils.LogistNodeEnum;
import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.service.logistics.AbroadOrderService;
import com.xiakee.service.smsc.OrderSmscNoticeService;

@Service
public class AbroadOrderServiceImpl implements AbroadOrderService {
	private static Logger log = Logger.getLogger(AbroadOrderServiceImpl.class);
	@Autowired
	private AbroadOrderDao abroadOrderDao;
	
	@Autowired
	private BoxnoDao boxnoDao;
	
	@Autowired
	private OrderSmscNoticeService smscNoticeService;
	
	private static final int PAGE_SIZE = 20;
	
	/**
	 * 返回第一个infoId
	 * @Method  addAbroadOrderBean
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月21日 上午11:31:14
	 * @Version 1.0
	 */
	@Override
	public Long addAbroadOrderBean(AbroadOrderBean bean) {
		Long firstInfoId = 0L;
		if(bean != null){
			Integer sum = abroadOrderDao.addAbroadOrderBean(bean);
			log.info("=====addAbroadOrderBean=====插入获取的国外物流id=" + bean.getId());
			if(sum > 0 && StringUtils.isNotBlank(bean.getInfos())){
				String[] infos = bean.getInfos().split(",");
				firstInfoId = Long.parseLong(infos[0]);
				for(String infoId:infos){
					log.info("=====abroadID=====" + bean.getId() + "===========infoID=====" + infoId);
					BoxnoBean boxno = new BoxnoBean();
					boxno.setAbroadId(bean.getId());
					boxno.setInfoId(Long.parseLong(infoId));
					boxnoDao.addBoxnoBean(boxno);
					//录入物流节点信息
					LogisticsBean logistBean = new LogisticsBean();
					logistBean.setInfoId(Long.parseLong(infoId));
					logistBean.setLogistNode(LogistNodeEnum.ABROADORDER);
					logistBean.setSmscType(null);//该节点需要发送短信
					this.smscNoticeService.sendOrderNotice(logistBean);
				}
			}
		}
		return firstInfoId;
	}

	@Override
	public YzorderInfoBean getYzorderInfoBeanByAbroadId(Long abroadId) {
		return boxnoDao.findBoxnoBeanByAbroadId(abroadId);
	}

	@Override
	public List<BoxnoBean> getYzorderInfoByAbroadName(String outAbroadId) {
		return boxnoDao.getBoxnoByOutOrderId(outAbroadId);
	}

	@Override
	public List<AbroadOrderBean> getAllAbroadOrderByPage(int page,String outOrderId,String expressno) {
		if(page < 1){
			page = 1;
		}
		int begin = (page - 1) * PAGE_SIZE;
		List<AbroadOrderBean> beans = null;
		if(StringUtils.isNotBlank(outOrderId)){
			outOrderId = "%" + outOrderId + "%";
			beans = abroadOrderDao.getAllAbroadOrderByOutOrderid(outOrderId, begin, PAGE_SIZE);
		}else if(StringUtils.isNotBlank(expressno)){
			expressno = "%" + expressno + "%";
			beans = abroadOrderDao.getAllAbroadOrderByExpressno(expressno, begin, PAGE_SIZE);
		}else {
			beans = abroadOrderDao.getAllAbroadOrderByPage(begin, PAGE_SIZE);
		}
		
		for(AbroadOrderBean bean:beans){
			List<BoxnoBean> boxBeans = this.boxnoDao.getBoxnoByAbroadId(bean.getId());
			bean.setBeans(boxBeans);
		}
		return beans;
	}
}
