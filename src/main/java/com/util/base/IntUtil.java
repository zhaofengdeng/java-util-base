package com.util.base;

import java.util.Random;

public class IntUtil {
	/**
	 * 解析int
	 * 
	 * @param str
	 * @return
	 */
	public static Integer parseInt(String str) {
		if (StringUtil.isNullOrEmpty(str)) {
			return null;
		}
		String intV = str;
		if (intV.indexOf(".") > 0) {
			intV = intV.substring(0, intV.indexOf("."));
		}
		return Integer.valueOf(intV);

	}

	/**
	 * 是否是int
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isInt(String text) {
		if (StringUtil.isNullOrEmpty(text)) {
			return false;
		}
		for (int i = text.length(); --i >= 0;) {
			int chr = text.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	
}
