package com.xiakee.service.smsc;

/**
 * 定义Email服务的接口
 * @Product: springMvc
 * @Title: AccountEmailService.java
 * @Package cn.edu.bit.mvc.service
 * @Description: SMC数据校验与发布平台
 * @Copyright: Copyright (c) 2013
 * @Company: 华风集团研发中心
 * @author 谢坚柏
 * @date 2013年8月26日 下午4:31:43
 * @version 1.0
 */
public interface AccountEmailService {
	
	/**
	 * 是否启动系统后台自动发送邮件的功能
	 * @Method  isOpenSendEmail
	 * @Return boolean
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月12日 下午5:42:33
	 * @Version 1.0
	 */
	boolean isOpenSendEmail();
	
	/**
	 * 系统发送邮件功能
	 * @Method  sendMail
	 * @Return void
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月12日 下午5:41:54
	 * @Version 1.0
	 */
	void sendMail(String to,String subject,String htmlText) throws Exception;
}
