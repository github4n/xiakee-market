package com.xiakee.service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.ServletInputStream;

public class WechatXmlUtil {
	// 需要加密的明文
	private static final String ENCODINGAESKEY = "GINNUSiDOigGq1lA0VbL3pu2SbVNJYMKnpI2n8A0xis";
	private static final String TOKEN = "GINNUSiDOigGq1lA0VbL3pu2SbVNJYMK";
	private static final String APPID = "wx2b738016fc328666";

	private static final String TEXTTPL = "<xml>"
			+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
			+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
			+ "<CreateTime>%3$s</CreateTime>"
			+ "<MsgType><![CDATA[%4$s]]></MsgType>"
			+ "<Content><![CDATA[%5$s]]></Content>" + "<FuncFlag>0</FuncFlag>"
			+ "</xml>";

	private static final String MSGTYPE = "text";

	public static String SHA1Encode(String source) {
		String result = null;
		try {
			result = new String(source);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = byte2hexString(md.digest(result.getBytes()));
		} catch (Exception ex) {
		}
		return result;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}

	public static String readStreamParameter(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
	
	public static String getVerifyDigestInfo(String timestamp,String nonce){
		String digest = null;
		String[] str = { TOKEN, timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		digest = WechatXmlUtil.SHA1Encode(bigStr);
		return digest;
	}
	
	public static String formatWechatContent(String fromUsername,String toUsername,String time,String content){
		return TEXTTPL.format(TEXTTPL, fromUsername, toUsername,time, MSGTYPE, content);
	}
}
