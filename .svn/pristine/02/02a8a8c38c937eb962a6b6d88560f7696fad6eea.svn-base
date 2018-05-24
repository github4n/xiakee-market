package com.xiakee.service.smsc;

import com.xiakee.domain.logistics.LogisticsBean;
import com.xiakee.domain.utils.LogistNodeEnum;
import com.xiakee.domain.utils.SmscContentTypeEnum;

/**
 * 定义订单物流短信发送接口
 * @Product: xiakee-service
 * @Title: SmscService.java
 * @Package com.xiakee.service.smsc
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年7月17日 上午9:57:02
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public interface OrderSmscNoticeService {
	/**
	 * 是否开启订单短信推送的服务
	 * @Method  isOpenSendNotice
	 * @Return boolean
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月17日 上午9:59:40
	 * @Version 1.0
	 */
	boolean isOpenSendNotice();
	
	/**
	 * 执行短信发送服务
	 * @Method  sendOrderNotice
	 * @Return void
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月17日 下午4:11:06
	 * @Version 1.0
	 */
	void sendOrderNotice(LogisticsBean logisticsBean);
}
