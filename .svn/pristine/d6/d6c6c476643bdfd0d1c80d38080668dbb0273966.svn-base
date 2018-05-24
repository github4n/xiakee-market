package com.xiakee.service.utils;

import java.util.Stack;

/**
 * 36进制数据获取与转换，主要应用在位数有限，但需要存放大数量的应用
 * 可以把十进制转换为36进制进行存储
 * @Product: xiakee-service
 * @Title: SixtyTwoNumber.java
 * @Package com.xiakee.service.utils
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年6月10日 下午5:14:28
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public class ThirtyTwoNumber {
	private static final char[] SIXTY_TWO_NUMBER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();

	/**
	 * 将10进制转化为36进制
	 * 转化成的36进制长度，不足length长度的话高位补0
	 * @Method  convertTenToSixtyTwo
	 * @Return String
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年6月10日 下午5:13:17
	 * @Version 1.0
	 */
	public static String convertTenToThirtyTwo(long number, int length) {
		Long rest = number;
		Stack<Character> stack = new Stack<Character>();
		StringBuilder result = new StringBuilder(0);
		while (rest != 0) {
			stack.add(SIXTY_TWO_NUMBER[new Long((rest - (rest / 36) * 36)).intValue()]);
			rest = rest / 36;
		}
		for (; !stack.isEmpty();) {
			result.append(stack.pop());
		}
		int result_length = result.length();
		StringBuilder temp0 = new StringBuilder();
		for (int i = 0; i < length - result_length; i++) {
			temp0.append('0');
		}
		return temp0.toString() + result.toString();
	}
	
}
