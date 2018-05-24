package com.xiakee.service.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.xiakee.domain.urm.SecurityUser;

/**
 * 获取session中用户信息
 * @author boge
 *
 */
public class SessionUserDetailUtil {
	public static SecurityUser findUserDetail(){
		SecurityUser user = null;
    	SecurityContext context = SecurityContextHolder.getContext();
    	if(context != null){
	    	Authentication auth = context.getAuthentication();
	    	if(auth != null){
	    		Object object = auth.getPrincipal();
		    	if(object != null && object instanceof UserDetails){
		        	user = (SecurityUser) object;
		    	}
	    	}
    	}
    	return user;
	}
}
