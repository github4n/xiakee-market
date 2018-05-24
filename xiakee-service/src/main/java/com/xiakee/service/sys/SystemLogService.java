package com.xiakee.service.sys;

import com.xiakee.domain.sys.SystemLogBean;

public interface SystemLogService {
	boolean addSystemLogBean(SystemLogBean bean);
	
	void sendMemberFriday();
}
