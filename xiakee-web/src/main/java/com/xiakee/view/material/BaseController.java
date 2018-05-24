package com.xiakee.view.material;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.talkyun.apus.mybatis.plugin.Page;
import com.xiakee.domain.urm.SecurityUser;

public class BaseController {
	
	protected Page buildPage(int start, int pageSize) {
		Page page = new Page();
		page.setCurrentPage(start);
		page.setShowCount(pageSize);
		return page;
	}
	
	protected SecurityUser getUser() {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (object != null && object instanceof UserDetails) {
			SecurityUser userDetails = (SecurityUser) object;
			return userDetails;
		}
		return null;
	}
}
