package com.util.img;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Base64.Encoder;

public class IMGUtil {
	/**
	 * 
	 * 将图片转换成Base64编码
	 */
	public static String encode(String imgFile) {
		File file = new File(imgFile);
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(file);
			data = new byte[in.available()];
			in.read(data);
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		Encoder encoder = Base64.getEncoder();
		byte[] encodeData = encoder.encode(data);
		return new String(encodeData);
	}

	public static void main(String[] args) {
		System.out.println(encode("F:\\logo.jpg"));
	}

}
