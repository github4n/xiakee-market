package com.xiakee.service.yz;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.xiakee.domain.ecos.ApiResultJson;
import com.xiakee.domain.ecos.TypeApiJson;
import com.xiakee.domain.sku.SkuCatalogBean;
import com.xiakee.domain.sku.SkuTypeBean;
import com.xiakee.service.utils.EcstoreApiUtil;

public class WebUtils {
	public static final String DEFAULT_CHARSET = "UTF-8";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_GET = "GET";
	// 签名密钥，就是token，也就是 /config/certi.php
	private static final String secret = "ec7f3926c47f21428fc224a13e21d9fd097d2c645695d490a814c2a705436c1f";

	public static void main(String[] args) {
		List<SkuCatalogBean> beans = EcstoreApiUtil.getCatalogApiJson("34");
//		SkuTypeBean bean = EcstoreApiUtil.getTypeApiJson("15");
		System.out.println("============" + beans.size());
	}
	
	public static void mains(String[] args) {
		// 如果系统需要走公有矩阵，则token不能自定义，必须使用certi.php中的值
		Map<String, String> params = new HashMap<String, String>();
		
//		params.put("method", "b2c.brand.get_brand_detail");// 请求的接口名称，ecstore接口列表请参考文档：http://www.ec-os.net/ecstore/append-a/index.html
//		params.put("date", "2008-01-25 20:23:30");
//		params.put("format", "json");
//		params.put("product_id", "[580]");
//		params.put("type_id", "1");
		
//		params.put("method", "b2c.order.search");
//		params.put("start_time", "2014-04-01");
//		params.put("end_time", "2015-06-01");
		
//		params.put("method", "b2c.order.detail");
//		params.put("tid", "150520165914092");
		
//		params.put("method", "b2c.goods.get_goods_detail");
//		params.put("product_id", "2016");
		
//		params.put("method", "shopex_shop_login");
//		params.put("user_name", "admin");
//		params.put("password", "1qaz@WSX");
//		params.put("is_admin", "1");
		
//		params.put("method", "b2c.member.add");
//		params.put("account", "xiejianbo");
//		params.put("password", "guxinghanshe168");
//		params.put("psw_confirm", "guxinghanshe168");
//		params.put("email", "boge@xiakee.com");
		
		//商品类型，实体属性，没有则获取顶级分类，即商城中的商品类型，
		//返回的type_id即为商品类型id，即商城中的商品分类ID
//		params.put("method", "b2c.goods.get_cat_list");
//		params.put("cat_id", "0");///上装
		
		//商品分类，虚拟分类属性
		params.put("method", "b2c.goods.get_type_detial");
		params.put("type_id", "15");///上装
		
		//尚未开放接口
//		params.put("method", "b2c.goods.get_cat");
		
		//获取品牌数据
//		params.put("method", "b2c.brand.get_brand_detail");
//		params.put("brand_name	", "xxx");
//		params.put("brand_id", "xxx");
//		params.put("page_no", "1");
//		params.put("page_size", "100");

		//根据货品ID获取单个货品的详细信息
//		params.put("method", "b2c.goods.get_goods_detail");
//		params.put("product_id", "5164");
		
		try {
			TypeApiJson bean = null;
			if(params != null && params.size() > 0){
				params.put("direct", "true");
				params.put("sign", WebUtils.getShopexSign(secret, params));// 签名
				try {
					String postResult = WebUtils.doPost("http://test.xiakee.com/index.php/api",
							params, 3000, 3000);// POST请求，url为例子，网店地址需要改成自己的域名
					bean = JSON.parseObject(postResult, TypeApiJson.class);
					System.out.println(bean.getData().get("type_id"));
				} catch (IOException e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ApiResultJson getEcstoreApiResultBean(Map<String, String> params){
		ApiResultJson bean = null;
		if(params != null && params.size() > 0){
			params.put("direct", "true");
			params.put("sign", WebUtils.getShopexSign(secret, params));// 签名
			try {
				String postResult = WebUtils.doPost("http://test.xiakee.com/index.php/api",
						params, 3000, 3000);// POST请求，url为例子，网店地址需要改成自己的域名
				bean = JSON.parseObject(postResult, ApiResultJson.class);
			} catch (IOException e) {
			}
		}
		return bean;
	}

	private WebUtils() {
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
	public static String doPost(String url, Map<String, String> params,
			int connectTimeout, int readTimeout) throws IOException {
		return doPost(url, params, DEFAULT_CHARSET, connectTimeout, readTimeout);
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
	public static String doPost(String url, Map<String, String> params,
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

	public static String doPost(String url, String queryParams,
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
	public static String doPost(String url, String ctype, byte[] content,
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
	public static String doGet(String url, Map<String, String> params)
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
	public static String doGet(String url, Map<String, String> params,
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

	public static String buildQuery(Map<String, String> params, String charset)
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

	protected static String getResponseAsString(HttpURLConnection conn)
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
	public static String decode(String value) {
		return decode(value, DEFAULT_CHARSET);
	}

	/**
	 * 使用默认的UTF-8字符集编码请求参数值。
	 * 
	 * @param value
	 *            参数值
	 * @return 编码后的参数值
	 */
	public static String encode(String value) {
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
	public static String decode(String value, String charset) {
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
	public static String encode(String value, String charset) {
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
	public static Map<String, String> splitUrlQuery(String query) {
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
	public static String getShopexSign(String secret,
			Map<String, String> paramMap) {
		String mixParam = WebUtils.mixRequestParams(paramMap);
		String sign = null;
		try {
			String signTemp = WebUtils.byte2hex(WebUtils.encryptMD5(mixParam),
					true);
			sign = WebUtils.byte2hex(WebUtils.encryptMD5(signTemp + secret),
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
	public static byte[] encryptHMAC(String data, String secret)
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
	public static byte[] encryptHmacSHA256(String data, String secret)
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
	 * 使用MD5加密
	 * 
	 * @param data
	 *            加密前字符串
	 * @return
	 * @throws java.io.IOException
	 */
	public static byte[] encryptMD5(String data) throws IOException {
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
	public static String byte2hex(byte[] bytes, boolean isToUpper) {
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
	public static String mixRequestParams(Map<String, Object> sysParams,
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

	public static String mixRequestParams(Map<String, String> params) {
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