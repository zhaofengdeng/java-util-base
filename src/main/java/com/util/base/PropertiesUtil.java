package com.util.base;

import java.util.Date;

/**
 * 属性Util
 * @author 赵丰登
 *
 */
public class PropertiesUtil {
	public static String toString(Object obj) {
		if(obj==null) {
			return "null";
		}
		String fieldName = obj.getClass().getTypeName();
		if("java.util.Date".equals(fieldName)) {
			return StringUtil.formatDate((Date)obj, null);
		}
		return obj.toString();
	}

}
