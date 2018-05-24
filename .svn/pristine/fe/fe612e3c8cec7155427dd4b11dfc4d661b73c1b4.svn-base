package com.xiakee.service.smsc.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xiakee.dao.logistics.LogisticsDao;
import com.xiakee.dao.smsc.SmscRecordDao;
import com.xiakee.dao.smsc.SmscResultDao;
import com.xiakee.dao.yz.YouzanOrderDao;
import com.xiakee.domain.logistics.LogisticsBean;
import com.xiakee.domain.utils.LogistNodeEnum;
import com.xiakee.domain.utils.SmscContentTypeEnum;
import com.xiakee.ecdao.order.EcOrderDao;
import com.xiakee.service.AutoExecuteTasker;
import com.xiakee.service.logistics.OrderLogistNoticeTasker;
import com.xiakee.service.smsc.OrderSmscNoticeService;

@Service
public class OrderSmscNoticeServiceImpl implements OrderSmscNoticeService {
	private static final Logger log = Logger
			.getLogger(OrderSmscNoticeServiceImpl.class);

	@Value("${smsc.isSendSmsc}")
	private boolean isSendSmsc;
	@Autowired
	private YouzanOrderDao orderDao;
	@Autowired
	private LogisticsDao logisticsDao;
	@Autowired
	private SmscRecordDao recordDao;
	@Autowired
	private SmscResultDao resultDao;
	@Autowired
	private EcOrderDao ecOrderDao;

	@Override
	public boolean isOpenSendNotice() {
		return this.isSendSmsc;
	}

	@Override
	public void sendOrderNotice(LogisticsBean logisticsBean) {
		if(logisticsBean != null){
			logisticsBean.setOpenSmsc(this.isSendSmsc);
			OrderLogistNoticeTasker task = new OrderLogistNoticeTasker(orderDao,
					logisticsDao, recordDao, resultDao,ecOrderDao);
			task.setTaskBean(logisticsBean);
			AutoExecuteTasker.addAutoExecuteTasker(task);
		}
	}

}
