package com.xiakee.service.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.xiakee.domain.smsc.SmscContentBean;
import com.xiakee.domain.smsc.SmscResultJson;

public class InitUrmResourceUtil {
	private static Logger log = Logger.getLogger(InitUrmResourceUtil.class);
	private static final String RESOURCE_PATH = "urls.txt";
	
	public static List<String> getAllResources(){
		List<String> urls = Collections.EMPTY_LIST;
		try {
			URL path = Thread.currentThread().getContextClassLoader().getResource(RESOURCE_PATH);
			log.info("读取资源文件：" + path.getPath());
			if(path != null){
				urls = new ArrayList<String>();
				BufferedReader br=new BufferedReader(new FileReader(path.getFile()));
				String url=null;
				StringBuffer sb=new StringBuffer();
				url=br.readLine();
				while(url!=null){
					if(StringUtils.isNotBlank(url) && !StringUtils.startsWithIgnoreCase(url, "#")){
						urls.add(url);
				    }
				    url=br.readLine();
				}
			}
		} catch (Exception e) {
		}
		return urls;
	}
	
	public static void main(String[] args) {
		Set<String> mobile = new HashSet<String>();
		try {
			String content = "【遐客行】亲爱的遐客行会员：“跨年狂欢，双旦钜惠”亿元红包来袭，数量有限，12月23日至1月6日全场大促。狂戳 xiakee.com 疯抢跑鞋，压缩衣，冲锋衣，各类进口运动装备陪你运动过圣诞。 退订回:N";
			Map<String, String> users = EcstoreApiUtil.getUserApiJson();
			Iterator iter = users.entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, String> entry = (Entry<String, String>) iter.next();
				mobile.add(entry.getValue());
			}
//			mobile.add("13401190238");
//			mobile.add("18001389769");
			System.out.println("短信发送总条数：" + mobile.size());
			for(String m:mobile){
//			if(true){
				SmscContentBean bean = new SmscContentBean();
				bean.setContent(content);
				bean.setMobile(m);
				try {
					String json = SmscSendUtils.sendSmscTaskerMarketing(bean);
					System.out.println(json);
					if(json !=null && json.contains("\"status\":100")){
						EcstoreApiUtil.setSmsc(m,content,json,"2","1");
					}else {
						EcstoreApiUtil.setSmsc(m,content,json,"2","0");
					}
				} catch (Exception e) {
					System.out.println("发送失败");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
		}
	}
}
