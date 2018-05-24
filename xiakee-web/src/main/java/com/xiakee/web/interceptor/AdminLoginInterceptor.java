package com.xiakee.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xiakee.domain.sys.SystemLogBean;
import com.xiakee.domain.urm.SecurityUser;
import com.xiakee.service.sys.SystemLogService;
import com.xiakee.service.utils.SessionUserDetailUtil;


/**
 * 获取用户信息，并且展示在前端
 * @Product: xiakee-web
 * @Title: AdminLoginInterceptor.java
 * @Package com.xiakee.web.interceptor
 * @Description: 遐客行后台管理系统
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com
 * @Date 2015年7月1日 下午11:37:30
 * @Version 1.0
 * @Copyright: Copyright (c) 2014
 */
public class AdminLoginInterceptor implements HandlerInterceptor {
    private static final Log log = LogFactory.getLog(AdminLoginInterceptor.class);
    
    @Autowired
    private SystemLogService systemLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getServletPath();
        if(StringUtils.isNotBlank(url) && url.contains(".do")){
        	if(url.contains("allOrderPrintTasker.do")){
        		return true;
        	}
        	
        	SecurityUser user = SessionUserDetailUtil.findUserDetail();
        	if(user != null){
	        	request.setAttribute("user", user);
	        	SystemLogBean bean = new SystemLogBean();
	        	bean.setUserid(user.getId());
	        	String originalURL = request.getRequestURI();
	        	if(StringUtils.isNotBlank(request.getQueryString())){
	        		originalURL += "?" + request.getQueryString();
	        	}
	        	bean.setUrl(originalURL);
	        	this.systemLogService.addSystemLogBean(bean);
        	}else {
        		response.sendRedirect("login.html");
        		return false;
			}
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
