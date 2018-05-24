package com.xiakee.service.utils;

import java.util.Stack;

/**
 * 六十二进制数据获取与转换，主要应用在位数有限，但需要存放大数量的应用
 * 可以把十进制转换为62进制进行存储
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
public class SixtyTwoNumber {
	private static final char[] SIXTY_TWO_NUMBER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
			.toCharArray();

	/**
	 * 将10进制转化为62进制
	 * 转化成的62进制长度，不足length长度的话高位补0
	 * @Method  convertTenToSixtyTwo
	 * @Return String
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年6月10日 下午5:13:17
	 * @Version 1.0
	 */
	public static String convertTenToSixtyTwo(long number, int length) {
		Long rest = number;
		Stack<Character> stack = new Stack<Character>();
		StringBuilder result = new StringBuilder(0);
		while (rest != 0) {
			stack.add(SIXTY_TWO_NUMBER[new Long((rest - (rest / 62) * 62)).intValue()]);
			rest = rest / 62;
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

	/**
	 * 将62进制转换成10进制数
	 * @Method  convertSixtyTwoToTen
	 * @Return String
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年6月10日 下午5:13:42
	 * @Version 1.0
	 */
	private static String convertSixtyTwoToTen(String ident62) {
		int decimal = 0;
		int base = 62;
		int keisu = 0;
		int cnt = 0;

		byte ident[] = ident62.getBytes();
		for (int i = ident.length - 1; i >= 0; i--) {
			int num = 0;
			if (ident[i] > 48 && ident[i] <= 57) {
				num = ident[i] - 48;
			} else if (ident[i] >= 65 && ident[i] <= 90) {
				num = ident[i] - 65 + 10;
			} else if (ident[i] >= 97 && ident[i] <= 122) {
				num = ident[i] - 97 + 10 + 26;
			}
			keisu = (int) java.lang.Math.pow((double) base, (double) cnt);
			decimal += num * keisu;
			cnt++;
		}
		return String.format("%08d", decimal);
	}
}
