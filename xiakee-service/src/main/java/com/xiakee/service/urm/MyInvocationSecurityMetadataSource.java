package com.xiakee.service.urm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import com.xiakee.service.utils.InitUrmResourceUtil;

/**
 * 对于资源的访问权限的定义，我们通过实现FilterInvocationSecurityMetadataSource这个接口来初始化数据
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * 看看loadResourceDefine方法，我在这里，假定index.jsp和i.jsp这两个资源，需要ROLE_ADMIN角色的用户才能访问。
 * 这个类中，还有一个最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。注意，
 * 我例子中使用的是AntUrlPathMatcher这个path
 * matcher来检查URL是否与资源定义匹配，事实上你还要用正则的方式来匹配，或者自己实现一个matcher。 Title:
 * MyInvocationSecurityMetadataSource.java Description: 主要为了学习计划而建立的代码测试工程
 * Copyright: Copyright (c) 2011 Company: http://guxinghanshe.com
 * Makedate:2012-3-12 下午3:52:03
 * 
 * @author 谢坚柏
 * @Email xiejianbo8@gmail.com
 * @version %I%, %G%
 * @since 1.0
 */
public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private static Logger log = Logger.getLogger(MyInvocationSecurityMetadataSource.class);
	
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	public MyInvocationSecurityMetadataSource() {
		loadResourceDefine();
	}
	
	/**
	 * 此块功能可以放在系统初始化阶段插入，定义好类变量即可，在角色与资源有变动的时候进行更新操作即可
	 * @Method  loadResourceDefine void
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com
	 * @Date 2015年6月29日 下午11:25:58
	 * @Version 1.0
	 */
	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
//		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//		ConfigAttribute ca = new SecurityConfig("ROLE_NORMAL");
//		atts.add(ca);
//		resourceMap.put("/**", atts);
			
		List<String> urls = InitUrmResourceUtil.getAllResources();
		for(String url:urls){
			log.info("权限拦截路径：" + url);
			if(StringUtils.contains(url, ",")){
				String[] roles = url.split(",");
				int length = roles.length;
				Collection<ConfigAttribute> urlAttr = new ArrayList<ConfigAttribute>();
				for(int i = 1;i < length;i ++){
					ConfigAttribute urlCai = new SecurityConfig(roles[i]);
					urlAttr.add(urlCai);
				}
				resourceMap.put("/" + roles[0] + ".do", urlAttr);
			}
		}
	}

	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
