package com.xiakee.service.yz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.dao.yz.YouzanOrderDao;
import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.service.yz.OrderInfoSevice;

@Service
public class OrderInfoServiceImpl implements OrderInfoSevice {
	
	@Autowired
	private YouzanOrderDao orderDao;

	@Override
	public YzorderInfoBean findYzorderInfoBeanById(Long id) {
		return orderDao.getYzorderInfoBeanById(id);
	}

}
