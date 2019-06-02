package com.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetUtil {

	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 */
	public static String send(String url, String json) throws IOException {
		URL getUrl = new URL(url + "?" + json);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.setDoOutput(false);
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		String returnMsg = "";
		String lines;

		while ((lines = reader.readLine()) != null) {
			returnMsg = returnMsg + lines;
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		return returnMsg;
	}

}
