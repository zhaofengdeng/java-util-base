package com.util.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtil {
	/**
	 * @name 转换时间类型
	 * @description 将 date 类型转换成 string 类型，返回format格式"yyyy-MM-dd HH:mm:ss"
	 * @author 赵丰登
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * @name 判断 string 类型是否为空
	 * @description 判断 string 类型是否为空，是则返回 true ，否则返回 false
	 * @author 赵丰登
	 * @return
	 */
	public static boolean isNullOrEmpty(String text) {

		return text == null || text.isEmpty() || text.equals("null") || "".equals(text.trim())
				|| "undefined".equals(text);
	}

	public static Boolean isNotNullOrEmpty(String str) {
		return !isNullOrEmpty(str);
	}

	private static String lastFileDate = "0";
	private static int fileIndex = 0;

	/**
	 * 通过日期得到不重复的文件名称 不重复仅限调用该方法获取的文件名称
	 * 
	 * @return
	 */
	public static String getFileNameBySysDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String curDate = sdf.format(new Date());
		if (curDate.equals(lastFileDate)) {
			fileIndex++;
		} else {
			fileIndex = 0;
		}
		lastFileDate = curDate;
		return curDate + fileIndex;
	}
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

}
