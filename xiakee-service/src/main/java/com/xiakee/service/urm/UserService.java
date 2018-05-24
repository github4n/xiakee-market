package com.xiakee.service.urm;

import com.xiakee.domain.urm.SecurityUser;

public interface UserService {
	SecurityUser findUserBeanByloginName(String username,String password);
}
