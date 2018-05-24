package com.xiakee.service.yz;

import java.util.List;

import com.xiakee.domain.print.RecipientBean;
import com.xiakee.domain.wechat.WechatOpenIdBean;
import com.xiakee.domain.yz.YzordersBean;

public interface YouzanOrderService {
	/**
	 * 根据用户微信OPENID返回订单信息
	 * @param fromUsername
	 * @return
	 */
	String findOrderInfo(String fromUsername);
	
	/**
	 * 微信用户绑定手机号
	 * @Method  bindingMobile
	 * @param bean
	 * @return String
	 * @Author 谢坚柏
	 * @Email xiejianbai@jd.com
	 * @Date 2015年5月3日 下午10:24:43
	 * @Version 1.0
	 */
	String bindingMobile(WechatOpenIdBean bean);
	
	/**
	 * 校验微信用户反馈的短信验证码
	 * @Method  verifyRandom
	 * @param openId
	 * @param random
	 * @return String
	 * @Author 谢坚柏
	 * @Email xiejianbai@jd.com
	 * @Date 2015年5月3日 下午10:36:02
	 * @Version 1.0
	 */
	String verifyRandom(String openId,String random);
	
	YzordersBean findOrderInfoByOrderid(String orderid);
	
	RecipientBean findPrintInfoByInfoid(String infoId);
	
	List<RecipientBean> displayOrderPrintTasker(int page);
	
	List<RecipientBean> displayOrderPrintedLIst(int page);
	
	List<RecipientBean> searchOrderPrintTask(RecipientBean bean);
	
	/**
	 * 更新采购备注信息
	 * @Method  updateOrderInfoRemarkById
	 * @Return Integer
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年9月17日 下午5:03:39
	 * @Version 1.0
	 */
	Integer updateOrderInfoRemarkById(Long id,String remark);

	String findOrderInfoRemarkById(Long id);
}
