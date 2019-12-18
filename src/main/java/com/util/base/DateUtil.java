package com.util.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	public static List<String> FORMATS = new ArrayList<>();
	static {
		FORMATS.add("yyyy-MM-dd HH:mm:ss");
		FORMATS.add("yyyy/M/d HH:mm:ss");
		FORMATS.add("yyyy/MM/dd HH:mm:ss");
		FORMATS.add("yyyy-MM-dd");
		FORMATS.add("yyyy/M/d");
		FORMATS.add("yyyy/MM/dd");
	}

	/**
	 * 得到年龄
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) { // 出生日期晚于当前时间，无法计算
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR); // 当前年份
		int monthNow = cal.get(Calendar.MONTH); // 当前月份
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); // 当前日期
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth; // 计算整岁数
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;// 当前日期在生日之前，年龄减一
			} else {
				age--;// 当前月份在生日之前，年龄减一
			}
		}
		return age;
	}

	/**
	 * 
	 * 添加解析日期的格式 默认包含 yyyy-MM-dd yyyy/M/d yyyy/MM/dd yyyy-MM-dd HH:mm:ss yyyy/M/d
	 * HH:mm:ss yyyy/MM/dd HH:mm:ss
	 */
	public static void addParseTimestampFormat(String format) {
		// 为空判断
		if (StringUtil.isNullOrEmpty(format)) {
			return;
		}
		// 防止重复添加
		for (String string : FORMATS) {
			if (string.equals(format)) {
				return;
			}
		}
		FORMATS.add(format);
	}

	/**
	 * @name 解析时间
	 * @description 将 string 类型 转换成 date 类型
	 *              如果需要解析其他类型，通过操作addParseTimestampFormat来添加特殊特使
	 * @author 赵丰登
	 * @return
	 */
	public static Date parseTimestamp(String dateText) {

		for (String sdfStr : FORMATS) {
			SimpleDateFormat sdf = new SimpleDateFormat(sdfStr);
			try {
				return sdf.parse(dateText);
			} catch (Exception e) {
			}
		}

		return null;
	}

	/**
	 * 解析时间
	 * 
	 * @param dateText
	 * @param format   默认格式： yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parseTimestamp(String dateText, String format) {
		if (StringUtil.isNullOrEmpty(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateText);
		} catch (Exception e) {
		}

		return null;
	}

	/**
	 * 增加天数，负数为减
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 增加小时数，负数为减
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addHour(Date date, Integer hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

	/**
	 * 增加月份，负数为减
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date date, Integer month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	/**
	 * 增加年，负数为减
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addYear(Date date, Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}

	/**
	 * 根据日期获取当天是周几
	 * 
	 * @param datetime 日期
	 * @return 周几
	 */
	public static String getWeek(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDays[w];
	}

	/**
	 * 格式如下:当天 凌晨1:20 早上6:30 中午10:00 下午3:30 晚上7:40 昨天 前天 近七天内显示周几
	 * 其余时间走format格式，默认是yyyy/MM/dd
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getCNText(Date date, String format) {
		int dateInt = Integer.valueOf(StringUtil.formatDate(date, "yyyyMMdd"));
		int curDate = Integer.valueOf(StringUtil.formatDate(new Date(), "yyyyMMdd"));
		String dateStr = StringUtil.formatDate(date, " HH:mm");
		// 今天
		if (curDate == dateInt) {
			dateStr = StringUtil.formatDate(date, " hh:mm");
			int hour = date.getHours();
			if (hour == 0) {
				dateStr = StringUtil.formatDate(date, " HH:mm");
			}
			if (hour < 5) {
				return "凌晨 " + dateStr;
			}
			if (hour < 10) {
				return "早上 " + dateStr;
			}
			if (hour < 13) {
				return "中午 " + dateStr;
			}
			if (hour < 18) {
				return "下午 " + dateStr;
			}
			return "晚上 " + dateStr;
		}

		int chaDay = curDate - dateInt;
		// 昨天
		if (chaDay == 1) {
			return "昨天 " + dateStr;
		}
		// 前天
		if (chaDay == 2) {
			return "前天 " + dateStr;
		}
		if (chaDay > 0 && chaDay < 7) {
			return getWeek(date) + dateStr;
		}
		if (StringUtil.isNullOrEmpty(format)) {
			format = "yyyy/MM/dd";
		}
		return StringUtil.formatDate(date, format);

	}

	/**
	 * 得到两个日期的天数差
	 * 
	 * @param d2
	 * @param d1
	 * @return
	 */
	public static long getDifferenceDay(Date d2, Date d1) {
		long time = d2.getTime();
		long time2 = d1.getTime();
		long differenceTime;
		if (time < time2) {
			differenceTime = time2 - time;
		} else {
			differenceTime = time - time2;
		}
		long daysBetween = (differenceTime + 1000000) / (60 * 60 * 24 * 1000);
		return daysBetween;
	}
}
