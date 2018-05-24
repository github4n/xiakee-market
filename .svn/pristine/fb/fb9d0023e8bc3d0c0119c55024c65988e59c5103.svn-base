package com.xiakee.service.utils;

import org.apache.commons.lang.StringUtils;

public class PromotionUrlUtil {

	public static String[] createPromotionLink(String code, String url) {
		String[] urls = null;
		if(StringUtils.isNotBlank(code) && StringUtils.isNotBlank(url) && !url.contains("xkp=")){
			String pc = "http://www.xiakee.com/";
			String wap = "http://www.xiakee.com/wap/";


			url = url.replace("http://www.xiakee.com/wap", "");
			url = url.replace("http://xiakee.com/wap", "");
			url = url.replace("www.xiakee.com/wap", "");
			url = url.replace("xiakee.com/wap", "");
			
			url = url.replace("http://www.xiakee.com", "");
			url = url.replace("http://xiakee.com", "");
			url = url.replace("www.xiakee.com", "");
			url = url.replace("xiakee.com", "");
			
			if (url.startsWith("/")) {
				url = url.substring(1,url.length());
			}
			
			if(url.contains("&") || url.contains("?")){
				url = url + "&xkp=" + code;
			}else {
				url = url + "?xkp=" + code;
			}
			
			urls = new String[2];
			urls[0] = pc + url;
			urls[1] = wap + url;
		}
		return urls;
	}
}
