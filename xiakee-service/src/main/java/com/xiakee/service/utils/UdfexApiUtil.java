package com.xiakee.service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.xiakee.bean.SplitOrder;
import com.xiakee.bean.UdfexDeclare;
import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.logistics.UdfexAllLogisticsJson;
import com.xiakee.domain.logistics.UdfexDeclareResultJson;
import com.xiakee.domain.logistics.UdfexDetailBean;
import com.xiakee.domain.logistics.UdfexLogisticsJson;
import com.xiakee.domain.logistics.UdfexResultJson;
import com.xiakee.domain.logistics.UdfexSplitOrderResultJson;
import com.xiakee.domain.utils.UdfexParamTypeEnum;
import com.xiakee.service.utils.gson.GsonUtils;

public class UdfexApiUtil {
	private static Logger log = Logger.getLogger(UdfexApiUtil.class);
	// private static final String _URL =
	// "http://192.168.8.211:3090/b2b/rest";//开发
	// private static final String _URL =
	// "http://test.www.udfex.com/b2b/rest";// 测试
	private static final String _URL = "http://api.udfex.com/b2b/rest";// 生产

	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_GET = "GET";
	private static final int TIMEOUT = 10000;
	private static final SimpleDateFormat DATAFORMAT = new SimpleDateFormat("YYYYMMDDHHMMSS");
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final Map<String, String> UDFEXDETAILTEXT = new HashMap<String, String>() {
		{
			put("UC", "您的商品已到达遐客行海外仓，正办理入仓手续，等待分拣打包");
			put("CI", "您的商品已到达遐客行海外仓，正办理入仓手续，等待分拣打包");
			put("PK", "您的商品已打包完毕，并已购买回国机票，等待前往机场");
			put("CO", "您的商品已出库，正前往机场等待登机");
			put("AL", "您的商品已到达机场货运站，正在等待登机回国");
			put("UL", "您的商品已登机回国，遐客行正全程护送，即将送达国内");
			put("DL", "航班已安全着陆，您的商品正等待前往遐客行通关口岸仓");
			put("IS", "您的商品已安全到达遐客行通关口岸仓，正在等待海关检查");
			put("RC", "您的商品已顺利通关，正前往遐客行国内转运仓");
			put("IR", "您的商品已到达遐客行国内转运仓，正在等待国内派送");
			put("TR", "您的商品已派送，");
			put("OK", "您的商品已成功签收");
		}
	};

	public static void main(String[] args) {
		// UdfexDetailBean data = findUdfexLogistict("TEST_FO101501",6);
		// System.out.println(data.getMemo());
		// List<UdfexDetailBean> datas = findUdfexLogistict("TBA621004441039");
		List<UdfexDetailBean> datas = findUdfexLogistict("1Z602E9V0341050889");
		System.err.println(JSON.toJSONString(datas));

		// UdfexAllLogisticsJson.MasterDto masterDto =
		// findAllUdfexLogistict("TBA621004441039","asd",5);
		// if(masterDto != null){
		// for(UdfexDetailBean
		// detail:masterDto.getConsignmentDetailHistoryDtoList()){
		// String mess = UDFEXDETAILTEXT.get(detail.getActionCode());
		// if(StringUtils.equals("AL", detail.getActionCode())){
		// mess = "您的商品" + detail.getMemo() + ",正在等待登机回国";
		// }
		// if(StringUtils.equals("TR", detail.getActionCode())){
		// String[] transports = detail.getMemo().split("快递公司：");
		// if(transports.length > 1){
		// mess = "您的商品已派送,快递公司：" + transports[1];
		// }
		// }
		// if(StringUtils.isNotBlank(mess)){
		// System.out.println(mess);
		// }
		// }
		// }else {
		// System.out.println("包裹不存在");
		// }
	}

	public static UdfexDetailBean findUdfexLogistict(String logisticsNo, int nodeIndex) {
		Map<String, String> map = getParameters(logisticsNo);
		UdfexDetailBean data = null;
		try {
			String content = doPost(_URL, map, DEFAULT_CHARSET, TIMEOUT, TIMEOUT);
			log.info("递优读取物流原始信息：" + content);
			System.out.println(content);
			UdfexResultJson bean = JSON.parseObject(content, UdfexResultJson.class);
			if (bean != null && StringUtils.equals("0", bean.getStatus()) && StringUtils.isNotBlank(bean.getResult())) {
				UdfexLogisticsJson logisticsJson = JSON.parseObject(bean.getResult(), UdfexLogisticsJson.class);
				List<UdfexDetailBean> infoDatas = logisticsJson.getConsignmentHistoryDtoList();

				Collections.sort(infoDatas, new Comparator<UdfexDetailBean>() {

					@Override
					public int compare(UdfexDetailBean o1, UdfexDetailBean o2) {
						if (o1.getSeqNo() > o2.getSeqNo()) {
							return 1;
						} else if (o1.getSeqNo() == o2.getSeqNo()) {
							return 0;
						} else {
							return -1;
						}
					}
				});

				for (UdfexDetailBean infoData : infoDatas) {
					UdfexDetailBean newData = findInfoDataByActionCode(infoData, nodeIndex);
					if (newData != null) {
						return newData;
					}
				}
			}
		} catch (IOException e) {
			log.error("递优读取物流节点失败：", e);
		}
		return data;
	}

	public static UdfexDetailBean findUdfexLogistict(String logisticsNo, String name, int nodeIndex) {
		UdfexAllLogisticsJson.MasterDto masterDto = findAllUdfexLogistict(logisticsNo, name, 1);
		UdfexDetailBean data = null;
		if (masterDto != null) {
			List<UdfexDetailBean> infoDatas = masterDto.getConsignmentDetailHistoryDtoList();
			for (UdfexDetailBean infoData : infoDatas) {
				UdfexDetailBean newData = findInfoDataByActionCode(infoData, nodeIndex);
				if (newData != null) {
					return newData;
				}
			}
		}
		return data;
	}

	public static UdfexDeclareResultJson udfexDeclare(UdfexDeclare bean) {
		try {
			String data = GsonUtils.toJson(bean);
			Map<String, String> map = declareParam(data);
			String content = doPost(_URL, map, DEFAULT_CHARSET, TIMEOUT, TIMEOUT);
			log.info("递优申报精品先：" + data);
			UdfexDeclareResultJson resultBean = JSON.parseObject(content, UdfexDeclareResultJson.class);
			return resultBean;
		} catch (Exception e) {

		}
		return null;
	}

	public static UdfexSplitOrderResultJson udfexSplitOrder(SplitOrder bean) {
		try {
			String data = GsonUtils.toJson(bean);
			Map<String, String> map = splitOrderParam(data);
			String content = doPost(_URL, map, DEFAULT_CHARSET, TIMEOUT, TIMEOUT);
			log.info("递优申报拆单：" + data);
			log.info("递优申报拆单结果：" + content);
			UdfexSplitOrderResultJson resultBean = JSON.parseObject(content, UdfexSplitOrderResultJson.class);
			return resultBean;
		} catch (Exception e) {

		}
		return null;
	}

	public static List<UdfexDetailBean> findUdfexLogistict(String logisticsNo, String name) {
		UdfexAllLogisticsJson.MasterDto masterDto = findAllUdfexLogistict(logisticsNo, name, 1);
		if (masterDto != null) {
			List<UdfexDetailBean> infoDatas = masterDto.getConsignmentDetailHistoryDtoList();
			return infoDatas;
		}
		return null;
	}

	public static UdfexAllLogisticsJson.MasterDto findAllUdfexLogistict(String logisticsNo, String name, int sum) {
		UdfexAllLogisticsJson.MasterDto masterDto = null;
		try {
			Map<String, String> map = getAllParameters(logisticsNo);
			String content = doPost(_URL, map, DEFAULT_CHARSET, TIMEOUT, TIMEOUT);
			// System.out.println("递优读取物流原始信息：" + content);
			log.info("递优读取物流原始信息：" + content);
			UdfexResultJson bean = JSON.parseObject(content, UdfexResultJson.class);
			if (bean != null && StringUtils.isNotBlank(bean.getResult())) {
				UdfexAllLogisticsJson logisticsJson = JSON.parseObject(bean.getResult(), UdfexAllLogisticsJson.class);
				if (StringUtils.equals("0", logisticsJson.getConsignmentSplitType())) {
					masterDto = logisticsJson.getMasterConsignmentDetailDto();
				} else if (StringUtils.equals("3", logisticsJson.getConsignmentSplitType())) {
					for (UdfexAllLogisticsJson.MasterDto mast : logisticsJson.getSlaverConsignmentDetailDtoList()) {
						if (StringUtils.equals(name, mast.getIdcardFullName())) {
							masterDto = mast;
							break;
						} else if (mast.getPiecesItems().size() == sum) {
							masterDto = mast;
						}
					}
				} else if (StringUtils.equals("4", logisticsJson.getConsignmentSplitType())) {
					for (UdfexAllLogisticsJson.MasterDto mast : logisticsJson.getSlaverConsignmentDetailDtoList()) {
						if (StringUtils.equals(logisticsNo, mast.getLogisticsNo())) {
							masterDto = mast;
							break;
						}
					}
				}

				if (masterDto == null) {
					masterDto = logisticsJson.getSlaverConsignmentDetailDtoList().get(0);
				}

				if (masterDto != null) {
					Collections.sort(masterDto.getConsignmentDetailHistoryDtoList(), new Comparator<UdfexDetailBean>() {

						@Override
						public int compare(UdfexDetailBean o1, UdfexDetailBean o2) {
							if (o1.getSeqNo() > o2.getSeqNo()) {
								return 1;
							} else if (o1.getSeqNo() == o2.getSeqNo()) {
								return 0;
							} else {
								return -1;
							}
						}
					});
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("手动查询递优读取物流节点(" + logisticsNo + ")失败：", e);
		}

		return masterDto;
	}

	/**
	 * 根据海外物流id返回完整的物流信息
	 * 
	 * @Method findUdfexLogistict
	 * @Return List<UdfexDetailBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年10月4日 下午2:47:40
	 * @Version 1.0
	 */
	public static List<UdfexDetailBean> findUdfexLogistict(String logisticsNo) {
		List<UdfexDetailBean> infoDatas = null;
		try {
			Map<String, String> map = getParameters(logisticsNo);
			String content = doPost(_URL, map, DEFAULT_CHARSET, TIMEOUT, TIMEOUT);
			log.info("递优读取物流原始信息：" + content);
			System.out.println(content);
			UdfexResultJson bean = JSON.parseObject(content, UdfexResultJson.class);
			if (bean != null && StringUtils.equals("0", bean.getStatus()) && StringUtils.isNotBlank(bean.getResult())) {
				UdfexLogisticsJson logisticsJson = JSON.parseObject(bean.getResult(), UdfexLogisticsJson.class);
				infoDatas = logisticsJson.getConsignmentHistoryDtoList();

				Collections.sort(infoDatas, new Comparator<UdfexDetailBean>() {

					@Override
					public int compare(UdfexDetailBean o1, UdfexDetailBean o2) {
						if (o1.getSeqNo() > o2.getSeqNo()) {
							return 1;
						} else if (o1.getSeqNo() == o2.getSeqNo()) {
							return 0;
						} else {
							return -1;
						}
					}
				});
			}
		} catch (IOException e) {
			log.error("手动查询递优读取物流节点失败：", e);
		}
		return infoDatas;
	}

	private static Map<String, String> getAllParameters(String logisticsNo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("version", "1.0");
		map.put("isSync", "");
		map.put("requestId", System.currentTimeMillis() + "");
		map.put("requestDate", DATAFORMAT.format(new Date()));
		map.put("requestSrc", "xiakee");

		// 正式
		map.put("docType", "P");
		map.put("prinipleCode", "UDF-CNGVVV");
		String auth = encrypt(new StringBuilder().append(map.get("requestId")).append(map.get("requestDate")).append(map.get("requestSrc")).append(map.get("prinipleCode")).toString(),
				"77648dcd14602435cf5fd570f33a58c9275de1b0d7b3e09698495311de65b917");
		map.put("data", "{\"logisticsNo\":\"" + logisticsNo + "\",\"memberName\":\"18001389773\"}");

		// B0010测试
		// map.put("docType", "T");
		// map.put("prinipleCode", "UDF-MCVLJA");
		// String auth = encrypt(new
		// StringBuilder().append(map.get("requestId"))
		// .append(map.get("requestDate")).append(map.get("requestSrc"))
		// .append(map.get("prinipleCode")).toString(),
		// "3Y6fPAfNc2SHLIq2D44tQL23IUH4h6ixv6HE3Na6LLsuqKybU");
		// map.put("data", "{\"logisticsNo\":\"" + logisticsNo +
		// "\",\"memberName\":\"18962674251\"}");

		map.put("auth", auth);
		map.put("bizCode", "B0010");
		map.put("callBackUrl", "callBackUrl");
		return map;
	}

	private static Map<String, String> declareParam(String data) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("version", "1.0");
		map.put("isSync", "");
		map.put("requestId", System.currentTimeMillis() + "");
		map.put("requestDate", DATAFORMAT.format(new Date()));
		map.put("requestSrc", "xiakee");

		// 正式
		map.put("docType", "P");
		map.put("prinipleCode", "UDF-CNGVVV");
		String auth = encrypt(new StringBuilder().append(map.get("requestId")).append(map.get("requestDate")).append(map.get("requestSrc")).append(map.get("prinipleCode")).toString(),
				"77648dcd14602435cf5fd570f33a58c9275de1b0d7b3e09698495311de65b917");
		map.put("data", data);

		// B0010测试
		// map.put("docType", "T");
		// map.put("prinipleCode", "UDF-MCVLJA");
		// String auth = encrypt(new
		// StringBuilder().append(map.get("requestId")).append(map.get("requestDate")).append(map.get("requestSrc")).append(map.get("prinipleCode")).toString(),
		// "3Y6fPAfNc2SHLIq2D44tQL23IUH4h6ixv6HE3Na6LLsuqKybU");
		// map.put("data", data);
		map.put("auth", auth);
		map.put("bizCode", "B0001");
		map.put("callBackUrl", "callBackUrl");
		return map;
	}

	private static Map<String, String> splitOrderParam(String data) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("version", "1.0");
		map.put("isSync", "");
		map.put("requestId", System.currentTimeMillis() + "");
		map.put("requestDate", DATAFORMAT.format(new Date()));
		map.put("requestSrc", "xiakee");

		// 正式
		map.put("docType", "P");
		map.put("prinipleCode", "UDF-CNGVVV");
		String auth = encrypt(new StringBuilder().append(map.get("requestId")).append(map.get("requestDate")).append(map.get("requestSrc")).append(map.get("prinipleCode")).toString(),
				"77648dcd14602435cf5fd570f33a58c9275de1b0d7b3e09698495311de65b917");
		map.put("data", data);

		// B0010测试
		// map.put("docType", "T");
		// map.put("prinipleCode", "UDF-MCVLJA");
		// String auth = encrypt(new
		// StringBuilder().append(map.get("requestId")).append(map.get("requestDate")).append(map.get("requestSrc")).append(map.get("prinipleCode")).toString(),
		// "3Y6fPAfNc2SHLIq2D44tQL23IUH4h6ixv6HE3Na6LLsuqKybU");
		// map.put("data", data);
		map.put("auth", auth);
		map.put("bizCode", "B0012");
		map.put("callBackUrl", "callBackUrl");
		return map;
	}

	private static Map<String, String> getParameters(String logisticsNo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("version", "1.0");
		map.put("isSync", "");
		map.put("requestId", System.currentTimeMillis() + "");
		map.put("requestDate", DATAFORMAT.format(new Date()));
		map.put("requestSrc", "xiakee");

		// 正式
		map.put("docType", "P");
		map.put("prinipleCode", "UDF-CNGVVV");
		String auth = encrypt(new StringBuilder().append(map.get("requestId")).append(map.get("requestDate")).append(map.get("requestSrc")).append(map.get("prinipleCode")).toString(),
				"77648dcd14602435cf5fd570f33a58c9275de1b0d7b3e09698495311de65b917");
		map.put("data", "{\"logisticsNo\":\"" + logisticsNo + "\",\"memberUsername\":\"18001389773\"}");

		// 测试
		// map.put("docType", "T");
		// map.put("prinipleCode", "UDF-JFCJQP");
		// String auth = encrypt(new
		// StringBuilder().append(map.get("requestId"))
		// .append(map.get("requestDate")).append(map.get("requestSrc"))
		// .append(map.get("prinipleCode")).toString(),
		// "3Y6fPAfNc2SHLIq2D44tQL23IUH4h6ixv6HE3Na6LLsuqKybU");
		// map.put("data", "{\"logisticsNo\":\"" + logisticsNo +
		// "\",\"memberUsername\":\"18250869851\"}");

		map.put("auth", auth);
		map.put("bizCode", "B0007");
		map.put("callBackUrl", "callBackUrl");
		return map;
	}

	private static UdfexDetailBean findInfoDataByActionCode(UdfexDetailBean infoData, int nodeIndex) {
		UdfexDetailBean data = null;
		if (infoData != null) {
			String actionCode = getActionCode(nodeIndex);
			String targetCode = infoData.getActionCode();

			if (nodeIndex == 5 && StringUtils.equals(UdfexParamTypeEnum.UC.toName(), targetCode)) {
				targetCode = UdfexParamTypeEnum.CI.toName();
				infoData.setActionCode(targetCode);
			}
			// log.info("actionCode:" + actionCode + "     targetCode:" +
			// targetCode);
			while (StringUtils.isNotBlank(actionCode)) {
				if (StringUtils.equals(actionCode, targetCode)) {
					data = infoData;
					data.setNodeIndex(nodeIndex);
					log.info("递优物流本次抓取最终结果：" + data.getActionDesc());
					return data;
				} else {
					actionCode = getActionCode(nodeIndex++);
				}
			}
		}
		return data;
	}

	private static String getActionCode(int node) {
		String actionCode = null;
		// /"入库日期", // node 5
		// "出库日期", // node 6
		// "登机日期",// node 7
		// "清关日期",// node 8
		// "中通快递");// node 9
		switch (node) {
		case 5:
			actionCode = UdfexParamTypeEnum.CI.toName();
			break;

		case 6:
			actionCode = UdfexParamTypeEnum.CO.toName();
			break;

		case 7:
			actionCode = UdfexParamTypeEnum.UL.toName();
			break;

		case 8:
			actionCode = UdfexParamTypeEnum.IS.toName();
			break;

		case 9:
			actionCode = UdfexParamTypeEnum.TR.toName();
			break;

		case 10:
			actionCode = UdfexParamTypeEnum.OK.toName();
			break;
		}
		return actionCode;
	}

	/**
	 * 递优加密算法
	 * 
	 * @Method encrypt
	 * @Return String
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年8月26日 下午4:30:42
	 * @Version 1.0
	 */
	private static String encrypt(String str, String salt) {
		MessageDigest messageDigest = null;
		str += salt;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuilder md5StrBuff = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			String hexString = Integer.toHexString(0xFF & byteArray[i]);
			if (hexString.length() == 1)
				md5StrBuff.append("0").append(hexString);
			else
				md5StrBuff.append(hexString);
		}
		return md5StrBuff.toString();
	}

	private static String doPost(String url, Map<String, String> params, String charset, int connectTimeout, int readTimeout) throws IOException {
		String ctype = "application/x-www-form-urlencoded;charset=" + charset;
		String query = buildQuery(params, charset);
		byte[] content = {};
		if (query != null) {
			content = query.getBytes(charset);
		}
		return doPost(url, ctype, content, connectTimeout, readTimeout);
	}

	private static String doPost(String url, String ctype, byte[] content, int connectTimeout, int readTimeout) throws IOException {
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

	private static HttpURLConnection getConnection(URL url, String method, String ctype) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("User-Agent", "xiakee");
		conn.setRequestProperty("Content-Type", ctype);
		return conn;
	}

	private static URL buildGetUrl(String strUrl, String query) throws IOException {
		URL url = new URL(strUrl);
		if (StringUtils.isBlank(query)) {
			return url;
		}
		if (StringUtils.isBlank(url.getQuery())) {
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

	private static String buildQuery(Map<String, String> params, String charset) throws IOException {
		if (params == null) {
			return null;
		} else {
			String timestamp = DATAFORMAT.format(new Date());
			params.put("timestamp", timestamp);
		}
		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;
		// 忽略参数名或参数值为空的参数
		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();
			if (StringUtils.isNotBlank(name)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}
				if (value == null) {
					value = "";
				}
				query.append(name).append("=").append(URLEncoder.encode(value, charset));
			}
		}
		return query.toString();
	}

	private static String getResponseAsString(HttpURLConnection conn) throws IOException {
		String charset = getResponseCharset(conn.getContentType());
		InputStream es = conn.getErrorStream();
		if (es == null) {
			return getStreamAsString(conn.getInputStream(), charset);
		}
		return null;
	}

	private static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
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
		if (StringUtils.isNotBlank(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (StringUtils.isNotBlank(pair[1])) {
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
	 * @Method decode
	 * @Return String
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年8月18日 下午7:47:28
	 * @Version 1.0
	 */
	private static String decode(String value) {
		return decode(value, DEFAULT_CHARSET);
	}

	/**
	 * 使用默认的UTF-8字符集编码请求参数值。
	 * 
	 * @Method encode
	 * @Return String
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年8月18日 下午7:47:20
	 * @Version 1.0
	 */
	private static String encode(String value) {
		return encode(value, DEFAULT_CHARSET);
	}

	/**
	 * 使用指定的字符集反编码请求参数值。
	 * 
	 * @Method decode
	 * @Return String
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年8月18日 下午7:47:02
	 * @Version 1.0
	 */
	private static String decode(String value, String charset) {
		String result = null;
		if (StringUtils.isNotBlank(value)) {
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
	 * @Method encode
	 * @Return String
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年8月18日 下午7:46:39
	 * @Version 1.0
	 */
	private static String encode(String value, String charset) {
		String result = null;
		if (StringUtils.isNotBlank(value)) {
			try {
				result = URLEncoder.encode(value, charset);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	/**
	 * 使用MD5加密
	 * 
	 * @Method encryptMD5
	 * @Return byte[]
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年8月18日 下午7:44:37
	 * @Version 1.0
	 */
	private static String encryptMD5(String data) throws IOException {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = data.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			log.error("sign加密失败", e);
			return null;
		}
	}
}
