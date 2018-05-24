package com.xiakee.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiakee.domain.ecgoods.XiakeeHaul;
import com.xiakee.domain.ecgoods.XiakeeHaulProcess;
import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.domain.order.EcOrderItemBean;
import com.xiakee.ecdao.order.EcOrderDao;
import com.xiakee.ecdao.order.XiakeeHaulMapper;
import com.xiakee.service.utils.TimeUtils;

public class Test {
	@Autowired
	private EcOrderDao ecorderDao;
	
	@Autowired
	private XiakeeHaulMapper xiakeeHaulMapper;
	

	@org.junit.Test
	public void test() {
		try{
		List<EcOrderBean> ecorderList= ecorderDao.findAllEcstoreOrdersByStatus("active");
		if(ecorderList != null){
			for(EcOrderBean order:ecorderList){
				List<EcOrderItemBean> itemList = ecorderDao.findAllOrderItemsByOrderId(order.getOrder_id());
				for(EcOrderItemBean orderItem:itemList){
					String haulId = order.getOrder_id() + "" + orderItem.getItem_id();
					XiakeeHaul haul = xiakeeHaulMapper.selectByPrimaryKey(Long.parseLong(haulId));
					if(haul == null){
						XiakeeHaul xiakeeHaul = new XiakeeHaul();
						xiakeeHaul.setHaulId(Long.parseLong(haulId));
						xiakeeHaul.setCreatetime(TimeUtils.getCurrentTime().intValue());
						xiakeeHaul.setOrderId(order.getOrder_id());
						int xiakeenum = xiakeeHaulMapper.insert(xiakeeHaul);
						if(xiakeenum > 0){
							List<XiakeeHaulProcess> xiakeeProcessList = ecorderDao.findAllXiakeeHaulByOrderId(order.getOrder_id());
							if(xiakeeProcessList != null){
								for(XiakeeHaulProcess process:xiakeeProcessList){
									XiakeeHaulProcess xiakeeHaulProcess = new XiakeeHaulProcess();
									xiakeeHaulProcess.setAddon(process.getAddon());
									xiakeeHaulProcess.setAlttime(process.getAlttime());
									xiakeeHaulProcess.setBehavior(process.getBehavior());
									xiakeeHaulProcess.setBillType(process.getBillType());
									xiakeeHaulProcess.setLogText(process.getLogText());
									xiakeeHaulProcess.setOpId(process.getOpId());
									xiakeeHaulProcess.setOpName(process.getOpName());
									xiakeeHaulProcess.setOrderId(process.getOrderId());
									xiakeeHaulProcess.setRelId(process.getRelId());
									xiakeeHaulProcess.setResult(process.getResult());
								}
							}
						}
					}
				}
			}
		}
	}catch(Exception e){
		System.out.println("tttttt");
		e.printStackTrace();
	}
	}

}
