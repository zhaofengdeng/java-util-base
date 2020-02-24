package com.util.base;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignUtil {
	/**
	 * map
	 * @param map
	 * @param sginBegin
	 * @param signEnd
	 * @return
	 */
	public static String toRequestParams(Map<String,String> map,String sginBegin,String signEnd) {
		List<String> keys = MapUtil.keys(map);
		Collections.sort(keys);
		String paramUrl="";
		String sign=sginBegin;
		for (String key : keys) {
			sign=sign+key+map.get(key);
			System.out.println(key);
			paramUrl=paramUrl+"&"+key+"="+URLEncoder.encode(map.get(key));
		}
		sign=sign+signEnd;
		sign=StringUtil.MD5(sign).toUpperCase();
		paramUrl="sign="+sign+paramUrl;
		return paramUrl;
		
	}
	public static String toRequestParams2(Map<String,Object> map,String sginBegin,String signEnd) {
		List<String> keys = MapUtil.keys(map);
		Collections.sort(keys);
		String paramUrl="";
		String sign=sginBegin;
		for (String key : keys) {
			Object object = map.get(key);
			if(object==null) {
				continue;
			}
			if (object instanceof List) {
			   List<String> list=( List<String>)object;
			   for (String value : list) {
				   sign=sign+key+value;
					paramUrl=paramUrl+"&"+key+"="+URLEncoder.encode(value);
			}
			   } else {
				   sign=sign+key+object;
					paramUrl=paramUrl+"&"+key+"="+URLEncoder.encode(object.toString());
			   }
			
		}
		sign=sign+signEnd;
		sign=StringUtil.MD5(sign).toUpperCase();
		paramUrl="sign="+sign+paramUrl;
		return paramUrl;
		
	}
}
