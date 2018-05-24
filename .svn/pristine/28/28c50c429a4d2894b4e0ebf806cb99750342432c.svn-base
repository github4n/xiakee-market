package com.xiakee.service.yz.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.dao.order.ErpOrderDao;
import com.xiakee.domain.order.EcOrderItemBean;
import com.xiakee.ecdao.order.EcOrderDao;
import com.xiakee.service.yz.InitOrderDataService;

@Service
public class InitOrderDataServiceImpl implements InitOrderDataService {
	private static Logger log = Logger.getLogger(InitOrderDataServiceImpl.class);

	@Autowired
	private ErpOrderDao erpOrderDao;
	@Autowired
	private EcOrderDao ecOrderDao;
	
	@Override
	public void checkOrderItemsData() {
		List<Long> orderids = erpOrderDao.findAllOrderidByItemsEmpty();

		try {
			for(Long orderid:orderids){
				List<EcOrderItemBean> itemBeans = ecOrderDao.findAllOrderItemsByOrderId(orderid);
				for(EcOrderItemBean itemBean:itemBeans){
					erpOrderDao.importEcstoreOrderItem(itemBean);
				}
			}
		} catch (Exception e) {
			log.error("订单商品导入失败",e);
		}
	}

}
