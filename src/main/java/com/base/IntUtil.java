package com.base;

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
}
