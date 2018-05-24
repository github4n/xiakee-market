package com.xiakee.service.urm;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 在这个类中，最重要的是decide方法，如果不存在对该资源的定义，直接放行；否则，如果找到正确的角色，即认为拥有权限，并放行，否则throw new
 * AccessDeniedException("no right");这样，就会进入上面提到的403.jsp页面。 Title:
 * MyAccessDecisionManager.java Description: 主要为了学习计划而建立的代码测试工程 Copyright:
 * Copyright (c) 2011 Company: http://guxinghanshe.com Makedate:2012-3-12
 * 下午3:54:00
 * 
 * @author 谢坚柏
 * @Email xiejianbo8@gmail.com
 * @version %I%, %G%
 * @since 1.0
 */
public class MyAccessDecisionManager implements AccessDecisionManager {
	private static Logger log = Logger.getLogger(MyAccessDecisionManager.class);
	// In this method, need to compare authentication with configAttributes.
	// 1, A object is a URL, a filter was find permission configuration by this
	// URL, and pass to here.
	// 2, Check authentication has attribute in permission configuration
	// (configAttributes)
	// 3, If not match corresponding authentication, throw a
	// AccessDeniedException.
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
//		log.info("自定义拦截地址：" + object.toString());// object is a URL.
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.equals(ga.getAuthority())) { // ga is user's role.
					return;
				}
			}
		}
		throw new AccessDeniedException("no right");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
