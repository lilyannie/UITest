package com.auto.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lijialong
 * 
 * */
public class TimeUtil {

	/**
	 * 获取当天0点时间戳
	 */
	public static Long getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);// 参数为HOUR，12小时制，HOUR_OF_DAY时，24小时制
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}

	/**
	 * 获取当天23：59：59时间戳
	 */
	public static Long getEndTime() {
		Calendar todayEnd = Calendar.getInstance();// 参数为HOUR，12小时制，HOUR_OF_DAY时，24小时制
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime();
	}

	/**
	 * 获取指定格式的当前时间
	 * 
	 * @param format
	 *            时间格式，如yyyy/MM/dd HH:mm
	 */
	public static String getDate(String format) {
		SimpleDateFormat sdf2 = new SimpleDateFormat(format);
		return sdf2.format(new Date());
	}

	/**
	 * 字符串转指定格式时间
	 * 
	 * @param str
	 *            时间字符串
	 * @param format
	 *            指定时间格式
	 */
	public static Date strToDate(String str, String format) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat(format);
		Date date = sdf1.parse(str);
		return date;
	}

	/**
	 * 时间戳转指定格式字符串
	 * 
	 * @param time
	 *            时间戳
	 * @author format 指定时间格式
	 */
	public static String longTodateStr(long time, String format) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat(format);
		String str = sdf1.format(time);
		return str;
	}
//	public static void main(String[] args) {
//		System.out.println(TimeUtil.getDate("yyyyMMddHHmmss"));
//	}

}
