package com.xiakee.service.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.xiakee.domain.ecos.BrandApiJson;

/**
 * 读取ecstore系统的api操作工具类
 * @Product: xiakee-service
 * @Title: EcstoreApiUtils.java
 * @Package com.xiakee.service.utils
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年6月6日 下午3:40:35
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public class EcstoreApiBase {
	private static Logger log = Logger.getLogger(EcstoreApiBase.class);
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_GET = "GET";
	// 签名密钥，就是token，也就是 /config/certi.php
	private static final String secret = "ec7f3926c47f21428fc224a13e21d9fd097d2c645695d490a814c2a705436c1f";
	private static String ECSTORE_URL = "http://www.xiakee.com/index.php/api";
	private static final int TIMEOUT = 3000;

	private EcstoreApiBase() {
	}

	public static void setECSTORE_URL(String eCSTORE_URL) {
		ECSTORE_URL = eCSTORE_URL;
	}



	/**
	 * 执行HTTP POST请求。
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return 响应字符串
	 * @throws java.io.IOException
	 */
	public static String doPost(Map<String, String> params) throws IOException {
		params = getLastParams(params);
		return doPost(ECSTORE_URL, params, DEFAULT_CHARSET, TIMEOUT, TIMEOUT);
	}
	
	public static String getPasswordByUsername(String userName,String origPswd){
		String password = null;
		if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(origPswd)){
			try {
				Map<String, String> params = new HashMap<String, String>();
				params.put("method", "b2c.member.get_encrypt_params");
				params.put("uname", "boge");

				String createtime = null;
				if (params != null && params.size() > 0) {
					try {
						String postResult = EcstoreApiBase.doPost(params);// POST请求，url为例子，网店地址需要改成自己的域名
						BrandApiJson jsonBean = JSON.parseObject(postResult, BrandApiJson.class);
						if (StringUtils.equals("succ", jsonBean.getRsp())) {
							createtime = jsonBean.getData().get("createtime");
						}
					} catch (IOException e) {
						log.error("ecstore后台获取用户加密参数失败", e);
					}
				}
				
				String sourPass = encryption(origPswd);
				sourPass += userName;
				sourPass += createtime;
				
				sourPass += encryption(sourPass);
				sourPass = sourPass.substring(0,31);
				password = "s" + sourPass;
			} catch (Exception e) {
				log.error("获取加密密码失败",e);
			}
		}
		return password;
	}
	
	public static String getPasswordByUsername(String userName,String origPswd,String createTime){
		String password = null;
		if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(origPswd) && StringUtils.isNotBlank(createTime)){
			try {				
				String sourPass = encryption(origPswd.trim());
				sourPass += userName;
				sourPass += createTime;
				
				sourPass = encryption(sourPass);
				sourPass = sourPass.substring(0,31);
				password = "s" + sourPass;
				log.info("用户（" + userName + ")加密后的密码串：" + password);
			} catch (Exception e) {
				log.error("获取加密密码失败",e);
			}
		}
		return password;
	}
	
	private static Map<String, String> getLastParams(Map<String, String> params){
		if(params != null){
			params.put("direct", "true");
			params.put("sign", EcstoreApiBase.getShopexSign(params));// 签名
			return params;
		}
		return null;
	}

	/**
	 * 执行HTTP POST请求。
	 *
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param charset
	 *            字符集，如UTF-8, GBK, GB2312
	 * @return 响应字符串
	 * @throws java.io.IOException
	 */
	private static String doPost(String url, Map<String, String> params,
			String charset, int connectTimeout, int readTimeout)
			throws IOException {
		String ctype = "application/x-www-form-urlencoded;charset=" + charset;
		String query = buildQuery(params, charset);
		byte[] content = {};
		if (query != null) {
			content = query.getBytes(charset);
		}
		return doPost(url, ctype, content, connectTimeout, readTimeout);
	}

	private static String doPost(String url, String queryParams,
			int connectTimeout, int readTimeout) throws IOException {
		String ctype = "application/x-www-form-urlencoded;charset="
				+ DEFAULT_CHARSET;
		byte[] content = {};
		if (queryParams != null) {
			content = queryParams.getBytes(DEFAULT_CHARSET);
		}
		return doPost(url, ctype, content, connectTimeout, readTimeout);
	}

	/**
	 * 执行HTTP POST请求。
	 *
	 * @param url
	 *            请求地址
	 * @param ctype
	 *            请求类型
	 * @param content
	 *            请求字节数组
	 * @return 响应字符串
	 * @throws java.io.IOException
	 */
	private static String doPost(String url, String ctype, byte[] content,
			int connectTimeout, int readTimeout) throws IOException {
		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		try {
			try {
				conn = getConnection(new URL(url), METHOD_POST, ctype);
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			} catch (IOException e) {
				throw e;
			}
			try {
				out = conn.getOutputStream();
				out.write(content);
				rsp = getResponseAsString(conn);
			} catch (IOException e) {
				throw e;
			}
		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rsp;
	}

	/**
	 * 执行HTTP GET请求。
	 *
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return 响应字符串
	 * @throws java.io.IOException
	 */
	private static String doGet(String url, Map<String, String> params)
			throws IOException {
		return doGet(url, params, DEFAULT_CHARSET);
	}

	/**
	 * 执行HTTP GET请求。
	 *
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param charset
	 *            字符集，如UTF-8, GBK, GB2312
	 * @return 响应字符串
	 * @throws java.io.IOException
	 */
	private static String doGet(String url, Map<String, String> params,
			String charset) throws IOException {
		HttpURLConnection conn = null;
		String rsp = null;
		try {
			String ctype = "application/x-www-form-urlencoded;charset="
					+ charset;
			String query = buildQuery(params, charset);
			try {
				conn = getConnection(buildGetUrl(url, query), METHOD_GET, ctype);
			} catch (IOException e) {
				throw e;
			}
			try {
				rsp = getResponseAsString(conn);
			} catch (IOException e) {
				throw e;
			}
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rsp;
	}

	private static HttpURLConnection getConnection(URL url, String method,
			String ctype) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
		conn.setRequestProperty("User-Agent", "shopEx");
		conn.setRequestProperty("Content-Type", ctype);
		return conn;
	}

	private static URL buildGetUrl(String strUrl, String query)
			throws IOException {
		URL url = new URL(strUrl);
		if (isEmpty(query)) {
			return url;
		}
		if (isEmpty(url.getQuery())) {
			if (strUrl.endsWith("?")) {
				strUrl = strUrl + query;
			} else {
				strUrl = strUrl + "?" + query;
			}
		} else {
			if (strUrl.endsWith("&")) {
				strUrl = strUrl + query;
			} else {
				strUrl = strUrl + "&" + query;
			}
		}
		return new URL(strUrl);
	}

	private static String buildQuery(Map<String, String> params, String charset)
			throws IOException {
		if (params == null || params.isEmpty()) {
			return null;
		}
		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;
		// 忽略参数名或参数值为空的参数
		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();
			if (!isBlank(name)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}
				if (value == null) {
					value = "";
				}
				query.append(name).append("=")
						.append(URLEncoder.encode(value, charset));
			}
		}
		return query.toString();
	}

	private static String getResponseAsString(HttpURLConnection conn)
			throws IOException {
		String charset = getResponseCharset(conn.getContentType());
		InputStream es = conn.getErrorStream();
		if (es == null) {
			return getStreamAsString(conn.getInputStream(), charset);
		} else {
			String msg = getStreamAsString(es, charset);
			if (isEmpty(msg)) {
				throw new IOException(conn.getResponseCode() + ":"
						+ conn.getResponseMessage());
			} else {
				throw new IOException(msg);
			}
		}
	}

	private static String getStreamAsString(InputStream stream, String charset)
			throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream, charset));
			StringWriter writer = new StringWriter();
			char[] chars = new char[256];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}
			return writer.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	private static String getResponseCharset(String ctype) {
		String charset = DEFAULT_CHARSET;
		if (!isEmpty(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (!isEmpty(pair[1])) {
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}
		return charset;
	}

	/**
	 * 使用默认的UTF-8字符集反编码请求参数值。
	 * 
	 * @param value
	 *            参数值
	 * @return 反编码后的参数值
	 */
	private static String decode(String value) {
		return decode(value, DEFAULT_CHARSET);
	}

	/**
	 * 使用默认的UTF-8字符集编码请求参数值。
	 * 
	 * @param value
	 *            参数值
	 * @return 编码后的参数值
	 */
	private static String encode(String value) {
		return encode(value, DEFAULT_CHARSET);
	}

	/**
	 * 使用指定的字符集反编码请求参数值。
	 * 
	 * @param value
	 *            参数值
	 * @param charset
	 *            字符集
	 * @return 反编码后的参数值
	 */
	private static String decode(String value, String charset) {
		String result = null;
		if (!isEmpty(value)) {
			try {
				result = URLDecoder.decode(value, charset);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	/**
	 * 使用指定的字符集编码请求参数值。
	 * 
	 * @param value
	 *            参数值
	 * @param charset
	 *            字符集
	 * @return 编码后的参数值
	 */
	private static String encode(String value, String charset) {
		String result = null;
		if (!isEmpty(value)) {
			try {
				result = URLEncoder.encode(value, charset);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	/**
	 * 从URL中提取所有的参数。
	 * 
	 * @param query
	 *            URL地址
	 * @return 参数映射
	 */
	private static Map<String, String> splitUrlQuery(String query) {
		Map<String, String> result = new HashMap<String, String>();
		String[] pairs = query.split("&");
		if (pairs != null && pairs.length > 0) {
			for (String pair : pairs) {
				String[] param = pair.split("=", 2);
				if (param != null && param.length == 2) {
					result.put(param[0], param[1]);
				}
			}
		}
		return result;
	}

	private static boolean isEmpty(final String str) {
		return str == null || str.length() == 0;
	}

	private static boolean isBlank(final String str) {
		int length;
		if (str == null || (length = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 得到shopex体系签名
	 * 
	 * @param secret
	 *            密钥
	 * @param paramMap
	 *            请求参数
	 * @return
	 */
	private static String getShopexSign(Map<String, String> paramMap) {
		String mixParam = EcstoreApiBase.mixRequestParams(paramMap);
		String sign = null;
		try {
			String signTemp = EcstoreApiBase.byte2hex(EcstoreApiBase.encryptMD5(mixParam),
					true);
			sign = EcstoreApiBase.byte2hex(EcstoreApiBase.encryptMD5(signTemp + secret),
					true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sign;
	}

	/**
	 * 使用HMAC加密
	 * 
	 * @param data
	 *            加密前字符串
	 * @param secret
	 *            密钥
	 * @return
	 * @throws java.io.IOException
	 */
	private static byte[] encryptHMAC(String data, String secret)
			throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes("utf-8"),
					"HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes("utf-8"));
		} catch (GeneralSecurityException gse) {
			String msg = getStringFromException(gse);
			throw new IOException(msg);
		}
		return bytes;
	}

	/**
	 * 使用HmacSHA256加密
	 * 
	 * @param data
	 *            加密前字符串
	 * @param secret
	 *            密钥
	 * @return
	 * @throws java.io.IOException
	 */
	private static byte[] encryptHmacSHA256(String data, String secret)
			throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes("utf-8"),
					"HmacSHA256");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes("utf-8"));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}
	

	/**
	 * 获取小写的md5加密32值，与php一致
	 * @Method  encryption
	 * @Return String
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年9月8日 下午4:49:13
	 * @Version 1.0
	 */
	private static String encryption(String plain) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plain.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}

	/**
	 * 使用MD5加密
	 * 
	 * @param data
	 *            加密前字符串
	 * @return
	 * @throws java.io.IOException
	 */
	private static byte[] encryptMD5(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			bytes = md.digest(data.getBytes("utf-8"));
		} catch (GeneralSecurityException gse) {
			String msg = getStringFromException(gse);
			throw new IOException(msg);
		}
		return bytes;
	}

	/**
	 * 把二进制数据转化为十六进制
	 * 
	 * @param bytes
	 * @param isToUpper
	 *            是否大写
	 * @return
	 */
	private static String byte2hex(byte[] bytes, boolean isToUpper) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			if (isToUpper)
				sign.append(hex.toUpperCase());
			else
				sign.append(hex);
		}
		return sign.toString();
	}

	/**
	 * <p>
	 * 根据参数名称将你的所有请求参数按照字母先后顺序排序:key + value .... key + value
	 * </p>
	 * <p>
	 * 对除签名和图片外的所有请求参数按key做的升序排列, value无需编码。 例如：将foo=1,bar=2,baz=3
	 * 排序为bar=2,baz=3,foo=1 参数名和参数值链接后，得到拼装字符串bar2baz3foo1
	 * </p>
	 * 
	 * @param sysParams
	 *            系统级参数
	 * @param appParams
	 *            应用级参数
	 * @param isFilterParamOfNullValue
	 *            是否过滤值为NULL的参数，若不过滤则以""空字符串代替
	 * @return 拼装字符串
	 */
	private static String mixRequestParams(Map<String, Object> sysParams,
			Map<String, Object> appParams, boolean isFilterParamOfNullValue) {
		// 第一步：把字典按Key的字母顺序排序
		Map<String, Object> sortedParams = new TreeMap<String, Object>();
		sortedParams.putAll(sysParams);
		sortedParams.putAll(appParams);
		Set<Map.Entry<String, Object>> paramSet = sortedParams.entrySet();
		// 第二步：把所有参数名和参数值串在一起
		StringBuilder query = new StringBuilder();
		for (Map.Entry<String, Object> param : paramSet) {
			if (StringUtils.isNotEmpty(param.getKey())) {
				if (!StringUtils.isBlank(param.getKey())
						&& !"sign".equals(param.getKey())) {
					if (param.getValue() == null
							|| StringUtils.isEmpty(param.getValue().toString())) {
						if (!isFilterParamOfNullValue) {
							query.append(param.getKey()).append("");
						}
					} else {
						query.append(param.getKey()).append(
								param.getValue().toString());
					}
				}
			}
		}
		return query.toString();
	}

	private static String mixRequestParams(Map<String, String> params) {
		Map<String, String> sortedParams = new TreeMap<String, String>(params);
		Set<Map.Entry<String, String>> paramSet = sortedParams.entrySet();
		StringBuilder query = new StringBuilder();
		for (Map.Entry<String, String> param : paramSet) {
			if (!StringUtils.isBlank(param.getKey())
					&& !"sign".equals(param.getKey())) {
				query.append(param.getKey()).append(param.getValue());
			}
		}
		return query.toString();
	}

	private static String getStringFromException(Throwable e) {
		String result = "";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(bos);
		e.printStackTrace(ps);
		try {
			result = bos.toString("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return result;
	}
}