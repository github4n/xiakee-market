package com.xiakee.service;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.xiakee.service.utils.EcstoreApiBase;
import com.xiakee.service.yz.InitOrderDataService;

/**
 * 后台自动执行计划
 * @Product: xiakee-service
 * @Title: AutomaticExecutorScheduled.java
 * @Package com.xiakee.service
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年5月12日 上午10:21:05
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
@Service
public class AutomaticExecutorScheduled implements
		ApplicationListener<ApplicationEvent> {
	private static Logger log = Logger.getLogger(AutomaticExecutorScheduled.class);
	private static boolean isStart = false;
	
	//配置系统open api的通讯地址
	@Value("${ecos.url}")
	private String ecostUrl;
	
	
	@Autowired
	private InitOrderDataService initOrderDataService;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (!isStart) {
			isStart = true;
			if(StringUtils.isNotBlank(ecostUrl)){
				EcstoreApiBase.setECSTORE_URL(ecostUrl);
				log.info("设置系统参数，商城系统openapi的通讯地址：" + ecostUrl);
			}
			new Thread(new AutoExecuteTasker()).start();
			initOrderDataService.checkOrderItemsData();
		}
	}

}
