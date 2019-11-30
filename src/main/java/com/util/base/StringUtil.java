package com.util.base;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import sun.misc.BASE64Encoder;

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
		if(StringUtil.isNullOrEmpty(format)) {
			format="yyyy-MM-dd HH:mm:ss";
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
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 生成指定位数的随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String randomInt(int length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			str = str + new Random().nextInt(10);
		}
		return str;
	}

	/**
	 * MD5进行64位加密
	 * 
	 * @param str
	 * @return
	 */
	public static String Md5BASE64(String str) {
		String newstr = "";
		try {
			// 确定计算方法
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			// 加密后的字符串
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newstr;
	}
}
