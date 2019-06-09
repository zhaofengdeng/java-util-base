package com.util.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 操作map的常用功能
 * 
 * @author Administrator
 *
 */
public class MapUtil {
	/**
	 * 从map中取 list
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static List<String> getList(Map<String, Object> map, String key) {
		List<String> list = new ArrayList<>();
		if (map.get(key).getClass().getName().equals("java.util.ArrayList")) {
			List<Object> objects = (List<Object>) map.get(key);
			for (Object object : objects) {
				list.add(object.toString());
			}
			return list;
		}
		list.add(map.get(key).toString());
		return list;
	}

	/**
	 * map获取所有的keys
	 * 
	 * @param map
	 * @return
	 */
	public static <T> List<T> keys(Map<T, ?> map) {
		List<T> keys = new ArrayList<>();
		Iterator<T> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();
			keys.add(next);

		}
		return keys;
	}

	/**
	 * 在map中获取string类型的数据，如果为空返回空字符串
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getString(Map<String, Object> map, String key) {
		if (map.get(key) == null) {
			return "";
		}
		return map.get(key).toString();
	}

	/**
	 * json转map
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> formatJson(String jsonStr) {
		Map<String, Object> map = new HashMap<>();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject = new JSONObject(jsonStr);

			Iterator<String> keyIterator = jsonObject.keys();
			while (keyIterator.hasNext()) {
				String key = keyIterator.next();
				map.put(key, jsonObject.get(key));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}
}
