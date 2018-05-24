package com.xiakee.service.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class SkuCrawlerUtil {

	private static Logger log = Logger.getLogger(SkuCrawlerUtil.class);
	private static String domain = "http://209.9.106.207";

	public static void setDomain(String domain) {
		SkuCrawlerUtil.domain = domain;
	}

	private static String sendRequest(String requestUrl, String requestMethod, Map<String, Object> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			if (requestMethod.equals("POST")) {
				HttpPost httpPost = new HttpPost(requestUrl);
				List<NameValuePair> paramsName = new ArrayList<NameValuePair>();
				for (String key : params.keySet()) {
					paramsName.add(new BasicNameValuePair(key, "" + params.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(paramsName, "UTF-8"));
				response = httpClient.execute(httpPost);
			} else if (requestMethod.equals("GET")) {
				HttpGet httpget = new HttpGet(requestUrl);
				response = httpClient.execute(httpget);
			}
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String result = entity != null ? EntityUtils.toString(entity) : null;
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("sendRequest is error, requestUrl = " + requestUrl + ", requestMethod = " + requestMethod + ", error = " + e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String crawlerSku(String skuCode, String urls, String type, String cat, int weight) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("skuCode", skuCode);
		params.put("urls", urls);
		params.put("type", type);
		params.put("cat", cat);
		params.put("weight", weight);
		return sendRequest(domain + "/addSkuUrls", "POST", params);
	}

	public static String getSkuInfo(String skuCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("skuCode", skuCode);
		return sendRequest(domain + "/getAllSkuInfos", "POST", params);
	}

	public static String getPriceBySkuCode(String skuCode, int type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("skuCode", skuCode);
		params.put("type", type);
		return sendRequest(domain + "/getPriceBySkuCode", "POST", params);
	}

	public static String getSkuCodeByUrls(String urls) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("urls", urls);
		String results = sendRequest(domain + "/getSkuCodeByUrl", "POST", params);
		return results;
	}

	public static String getImageBySkuCode(String skuCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("skuCode", skuCode);
		return sendRequest(domain + "/getImageBySkuCode", "POST", params);
	}

	public static String getMallIdBySkuCode(String skuCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("skuCode", skuCode);
		return sendRequest(domain + "/getMallIdBySkuCode", "POST", params);
	}

	public static String getMallById(int mallId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mallId", mallId);
		return sendRequest(domain + "/getMallById", "POST", params);
	}

	public static void main(String[] args) {
		System.out.println(crawlerSku("PXPXAS000000CK", "http://www.backcountry.com/pit-viper-smoke-lens-sunglasses?skid=PIT0002-THRAPKPU-ONESIZ&ti=Ok5ldyBBcnJpdmFsczoxOjI6",
				"16", "57", 0));
	}

}
