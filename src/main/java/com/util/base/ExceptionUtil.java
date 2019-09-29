package com.util.base;


import java.io.PrintWriter;

import java.io.StringWriter;

public class ExceptionUtil {
	/**
	 * 将异常堆栈中的信息转换为String字符串
	 * 
	 * @param e
	 * @return
	 */
	public static String getErrorInfoFromException(Exception e) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			sw.close();
			pw.close();
			return "\r\n" + sw.toString() + "\r\n";

		} catch (Exception e2) {
			return "ErrorInfoFromException";
		}
	}

}
