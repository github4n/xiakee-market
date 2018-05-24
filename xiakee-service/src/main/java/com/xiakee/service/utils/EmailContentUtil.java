package com.xiakee.service.utils;

import org.apache.log4j.Logger;

import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.domain.yz.YzordersBean;

public class EmailContentUtil {

	private static Logger log = Logger.getLogger(EmailContentUtil.class);
	
	public static String getEmailContentFromYzorder(YzordersBean bean){
		StringBuffer sb = new StringBuffer();
		if(bean != null){
			sb.append("恭喜您，又有新的订单了，赶紧起床采购吧！");
			sb.append("\n订单号："+ bean.getOrderid());
			sb.append("\n客户名称："+ bean.getName());
			sb.append("\n付款总额：" + bean.getPayment());
			sb.append("\n客户手机号：" + bean.getMobile());
			sb.append("\n客户省份：" + bean.getProvince());
			sb.append("\n客户市县：" + bean.getCity());
			sb.append("\n客户区域：" + bean.getDistrict());
			sb.append("\n街道地址：" + bean.getAddress());
			sb.append("\n下单时间：" + bean.getCreated());
			sb.append("\n----------------------");
		}
		return sb.toString();
	}
	
	public static String addOrderInfoToEmailContent(String emailContent,YzorderInfoBean bean){
		if(bean != null){
			StringBuffer sb = new StringBuffer();
			sb.append("\n产品标题：" + bean.getTitle());
			sb.append("\n产品规格：" + bean.getSku_properties_name());
			sb.append("\n产品单价：" + bean.getPrice());
			sb.append("\n产品数量：" + bean.getNum());
			sb.append("\n----------------------");
			emailContent += sb.toString();
		}
		return emailContent;
	}
}
