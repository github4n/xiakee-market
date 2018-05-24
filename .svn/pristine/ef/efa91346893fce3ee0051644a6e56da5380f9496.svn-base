package com.xiakee.service.yz.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.xiakee.domain.print.RecipientBean;
import com.xiakee.service.yz.OrderAutoPrintService;

@Service
public class OrderAutoPrintServiceImpl implements OrderAutoPrintService {
	private static Logger log = Logger.getLogger(OrderAutoPrintServiceImpl.class);
	private static final BlockingQueue<RecipientBean> PRINT_TASKER = new LinkedBlockingQueue<RecipientBean>();
	
	@Override
	public void addPrintTasker(RecipientBean bean) {
		try {
			PRINT_TASKER.put(bean);
		} catch (InterruptedException e) {
			log.info("===OrderAutoPrintUtils=====addOrderPrintTasker======",e);
		}
	}

	@Override
	public List<RecipientBean> getAllPrintTasker() {
		List<RecipientBean> beans = Collections.EMPTY_LIST;
		if(!PRINT_TASKER.isEmpty()){
			beans = new ArrayList<RecipientBean>();
			PRINT_TASKER.drainTo(beans);
		}
		return beans;
	}

}
