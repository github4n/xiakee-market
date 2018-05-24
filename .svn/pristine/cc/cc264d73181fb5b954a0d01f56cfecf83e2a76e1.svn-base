package com.xiakee.service.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.xiakee.domain.smsc.SmscContentBean;
import com.xiakee.domain.utils.SmscContentTypeEnum;
import com.xiakee.domain.yz.YzordersBean;

public class SmscTempletUtil {
	private static Logger log = Logger.getLogger(SmscTempletUtil.class);

	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	public static boolean isRandom(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[0-9]{6}$"); // 验证手机号
		try {
			m = p.matcher(str);
			b = m.matches();
		} catch (Exception e) {
		}
		return b;
	}

	public static String getRandom() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += array[i];
		}
		return result;
	}

	public static SmscContentBean getSmscContentYZ(String mobile, String random) {
		SmscContentBean bean = null;
		if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(random)) {
			bean = new SmscContentBean();
			StringBuffer sb = new StringBuffer();
			sb.append("【遐客行】校验码");
			sb.append(random);
			sb.append("，你的微信公众号[遐客行]正在进行手机号码绑定，校验码很重要，打死都不能告诉任何人哦！");

			bean.setMobile(mobile);
			bean.setContent(sb.toString());
			bean.setType(SmscContentTypeEnum.PUBLICNUM);
		}
		return bean;
	}

	/**
	 * 短信综合模板 content主要格式：海外仓已发货，请耐心等待；包裹已通关，正在转XXX，快递单号为：xxx 、 已经抵达中国，正在通关
	 * 【遐客行】尊敬的XXX先生/女士，您于xxx在遐客行购买的xxx
	 * ，XXX。更多详情，请关注公众号[Xiakee168]。若有任何问题，请发邮件至service@xiakee.com或电话180 0138 9773
	 * 【遐客行】尊敬的xxx先生/女士，您于xxx在遐客行购买的商品,xxx。更多详情请发邮件至service@xiakee.com或电话18001389773
	 * @Method getSmscLogistics
	 * @Return SmscContentBean
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月15日 下午12:01:07
	 * @Version 1.0
	 */
	public static SmscContentBean getSmscLogistics(String mobile, String name,
			String time,SmscContentTypeEnum type) {
		SmscContentBean bean = null;
		if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(name)) {
			bean = new SmscContentBean();
			StringBuffer sb = new StringBuffer();
			sb.append("【遐客行】尊敬的");
			sb.append(name);
			sb.append("先生/女士，您于");
			sb.append(TimeUtils.getYYYY_MM_DD(time));
			if(SmscContentTypeEnum.LOGISTONE == type){//订单支付成功
				sb.append("在遐客行的订单已支付成功，遐客行海外仓将尽快发货，预计15-20个工作日到货。感谢您的支持，如需帮助，请发邮件至service@xiakee.com或电话400-999-6848，我们将在第一时间回复。");
			}else{
				sb.append("在遐客行购买的商品");
				sb.append(",");
				sb.append(type.toDescription());
				sb.append("。更多详情请发邮件至service@xiakee.com或电话400-999-6848");
			}
			bean.setMobile(mobile.trim());
			bean.setContent(sb.toString());
			bean.setType(type);
		}
		return bean;
	}
}
