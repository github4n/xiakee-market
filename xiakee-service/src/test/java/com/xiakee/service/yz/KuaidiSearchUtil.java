package com.xiakee.service.yz;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class KuaidiSearchUtil {
	public static String searchkuaiDiInfo(String key, String com, String nu) {
		String content = "";
		try {
			URL url = new URL("http://api.kuaidi100.com/api?id=" + key +
					"&com=" + com + "&nu=" + nu + "&valicode=&show=0&muti=1&order=desc");
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					// String newContent = new String(b, 0, numRead);
					String newContent = new String(b, 0, numRead, "UTF-8");
					content += newContent;
				}
			}
			urlStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static void main(String[] args) {
		String key = "90e6c8d306741673";
		String com = "shentong";
		String nu = "229139282326";
		System.out.println(searchkuaiDiInfo(key,com,nu));
	}
}
