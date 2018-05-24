package com.xiakee.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class TimeUtils {
	private static Logger log = Logger.getLogger(TimeUtils.class);
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat FORMATTER_YMD = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static String increaseSecond(String time) {
		String reTime = "2015-03-01 01:01:01";
		if (StringUtils.isNotBlank(time)) {
			Date date;
			try {
				date = FORMATTER.parse(time);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.SECOND, -600);
				reTime = FORMATTER.format(calendar.getTime());
			} catch (ParseException e) {
				log.info("===数据格式化报错，主要处理自动增加一秒的功能====", e);
			}
		}
		return reTime;
	}

	public static String getYYYY_MM_DD(String time) {
		String yyyyMmDd = null;
		if (StringUtils.isNotBlank(time) && time.length() > 10) {
			yyyyMmDd = time.substring(0, 4);
			yyyyMmDd += "年";
			String month = time.substring(5, 7);
			if (month.startsWith("0")) {
				month = month.replace("0", "");
			}
			yyyyMmDd += month;
			yyyyMmDd += "月";
			String day = time.substring(8, 10);
			if (day.startsWith("0")) {
				day = day.replace("0", "");
			}
			yyyyMmDd += day;
			yyyyMmDd += "日";
		}
		return yyyyMmDd;
	}

	/**
	 * 获取当前时间戳，为了使用php使用过滤掉后三位
	 * 
	 * @Method getCurrentTimeForPhp
	 * @return Long
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com
	 * @Date 2015年6月30日 下午11:05:15
	 * @Version 1.0
	 */
	public static Long getCurrentTimeForPhp() {
		Long currentTime = System.currentTimeMillis() / 1000;
		return currentTime;
	}

	/**
	 * 格式化字符串时间成为php可以识别的时间
	 * 
	 * @Method formatTimeForPhp
	 * @return Long
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com
	 * @Date 2015年7月15日 下午11:18:11
	 * @Version 1.0
	 */
	public static Long formatTimeForPhp(String time) {
		Long currentTime = null;
		if (StringUtils.isNotBlank(time)) {
			try {
				Date date = FORMATTER.parse(time);
				currentTime = date.getTime() / 1000;
			} catch (ParseException e) {
				log.error("字符串时间格式化成php时间失败");
			}
		}
		return currentTime;
	}

	/**
	 * 获取一个月之前的时间
	 * 
	 * @Method getBeforeOndMonthTime
	 * @return Long
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com
	 * @Date 2015年6月30日 下午11:13:37
	 * @Version 1.0
	 */
	public static Long getBeforeOndMonthTime() {
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(calendar.MONTH, -1); // 设置为前一个月
		dBefore = calendar.getTime(); // 得到前1月的时间
		return dBefore.getTime() / 1000;
	}
	public static Long getBeforeTwoMonthTime() {
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(calendar.MONTH, -2); // 设置为前一个月
		dBefore = calendar.getTime(); // 得到前2月的时间
		return dBefore.getTime() / 1000;
	}

	public static String displayLogistTime(Long time) {
		String format = null;
		if (time > 999999999) {
			time = time * 1000;
			Date date = new Date();
			date.setTime(time);
			format = FORMATTER.format(date);
		}
		return format;
	}

	public static String displayCurrentTime() {
		return FORMATTER.format(new Date());
	}

	public static Long getXiakeeBeginTime() {
		Long beginTime = 0L;
		try {
			Date beginDate = FORMATTER.parse("2015-04-15 00:00:00");
			beginTime = beginDate.getTime() / 1000;
		} catch (Exception e) {
			log.error("获取遐客行公司开始时间失败", e);
		}
		return beginTime;
	}

	public static String getXiakeeBeginDate() {
		return "2015-04-15 00:00:00";
	}
	
	public static Long getCurrentTime() {
		 Calendar c = Calendar.getInstance();
		 c.set(Calendar.HOUR_OF_DAY, 0);
		 c.set(Calendar.MINUTE, 0);
		 c.set(Calendar.SECOND, 0);
		return c.getTimeInMillis() / 1000;
	}
	
	public static String getCurrentDate() {
		 Calendar c = Calendar.getInstance();
		 c.set(Calendar.HOUR_OF_DAY, 0);
		 c.set(Calendar.MINUTE, 0);
		 c.set(Calendar.SECOND, 0);
		 return FORMATTER.format(c.getTime());
	}
	
	public static Long getCurrentWeekTime() {
		 Calendar c = Calendar.getInstance();
		 int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		 if (day_of_week == 0){
		   day_of_week = 7;
		 }
		 c.add(Calendar.DATE, -day_of_week + 1);
		 c.set(Calendar.HOUR_OF_DAY, 0);
		 c.set(Calendar.MINUTE, 0);
		 c.set(Calendar.SECOND, 0);
		return c.getTimeInMillis() / 1000;
	}
	
	public static String getCurrentWeekDate() {
		 Calendar c = Calendar.getInstance();
		 int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		 if (day_of_week == 0){
		   day_of_week = 7;
		 }
		 c.add(Calendar.DATE, -day_of_week + 1);
		 c.set(Calendar.HOUR_OF_DAY, 0);
		 c.set(Calendar.MINUTE, 0);
		 c.set(Calendar.SECOND, 0);
		return FORMATTER.format(c.getTime());
	}
	
	public static Long getCurrentMonthTime() {
		 Calendar c = Calendar.getInstance();
		 int month = c.get(Calendar.MONTH);
		 c.set(Calendar.MONTH, month);
		 c.set(Calendar.DATE, 1);
		 c.set(Calendar.HOUR_OF_DAY, 0);
		 c.set(Calendar.MINUTE, 0);
		 c.set(Calendar.SECOND, 0);
		return c.getTimeInMillis() / 1000;
	}
	
	public static String getCurrentMonthDate() {
		 Calendar c = Calendar.getInstance();
		 int month = c.get(Calendar.MONTH);
		 c.set(Calendar.MONTH, month);
		 c.set(Calendar.DATE, 1);
		 c.set(Calendar.HOUR_OF_DAY, 0);
		 c.set(Calendar.MINUTE, 0);
		 c.set(Calendar.SECOND, 0);
		return FORMATTER.format(c.getTime());
	}

	public static void main(String[] args) {
		System.out.println(displayCurrentTime());
		System.out.println(getCurrentMonthDate());
	}
}
