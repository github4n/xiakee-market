package com.xiakee.service.logistics.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.dao.logistics.LogistCompDao;
import com.xiakee.domain.logistics.LogistCompBean;
import com.xiakee.service.logistics.LogistCompService;

@Service
public class LogistCompServiceImpl implements LogistCompService {

	@Autowired
	private LogistCompDao logistDao;
	
	@Override
	public Integer addLogistComp(LogistCompBean bean) {
		// TODO Auto-generated method stub
		return logistDao.addLogistComp(bean);
	}

	@Override
	public LogistCompBean findLogistComp(String infoId) {
		// TODO Auto-generated method stub
		return logistDao.findLogistComp(infoId);
	}
}
