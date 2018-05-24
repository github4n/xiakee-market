package com.xiakee.service.urm.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Service;

import com.xiakee.dao.sys.SystemLogDao;
import com.xiakee.domain.sys.SystemLogBean;
import com.xiakee.domain.urm.EcAccountBean;
import com.xiakee.domain.urm.EcDesktopUserBean;
import com.xiakee.domain.urm.SecurityUser;
import com.xiakee.ecdao.sso.EcAccountDao;
import com.xiakee.ecdao.sso.EcDesktopUserDao;
import com.xiakee.service.urm.UserService;
import com.xiakee.service.utils.EcstoreApiBase;

@Service
public class UserServiceImpl implements UserService{
	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private EcAccountDao accountDao;
	@Autowired
	private EcDesktopUserDao userDao;
	@Autowired
	private SystemLogDao systemLogDao;

	@Override
	public SecurityUser findUserBeanByloginName(String username,String password) {
		if(StringUtils.isNotBlank(username)){
			SystemLogBean bean = new SystemLogBean();
			bean.setUserid(0L);//登录日志默认为0
			bean.setUrl("用户登录操作，登录名称：" + username);
			this.systemLogDao.addSystemLogBean(bean);
		}
		
		SecurityUser user = null;
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
			EcAccountBean accountBean = accountDao.findAccountBeanByLoginName(username);
			if(accountBean != null){
				EcDesktopUserBean userBean = userDao.findDesktopUserById(accountBean.getAccount_id());
				if(userBean != null){
					//userBean == null，则意味着该账号没有启用
					String pass = EcstoreApiBase.getPasswordByUsername(accountBean.getLogin_name(), password, accountBean.getCreatetime());
					if(StringUtils.equals(pass, accountBean.getLogin_password().trim())){
						Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
//						GrantedAuthorityImpl auth = new GrantedAuthorityImpl("ROLE_NORMAL");
//						auths.add(auth);
						List<String> roles = userDao.findRolesUserById(accountBean.getAccount_id());
						for(String role:roles){
							GrantedAuthorityImpl authRole = new GrantedAuthorityImpl(role);
							auths.add(authRole);
						}
						log.info("成功登录用户信息：" + userBean);
						user = new SecurityUser(username, password, true, true, true, true, auths,userBean.getUser_id(),userBean.getName(),"4009994868");
					}else {
						log.error("用户登录校验失败，登录名称：" + accountBean.getLogin_name());
						log.error("加密串：" + pass);
						log.error("原始串：" + accountBean.getLogin_password());
					}
				}else {
					log.info("获取用户具体信息为空,该账号被禁用，userBean：" + username);
				}
			}else {
				log.info("没法从商城获取用户信息accountBean：" + username);
			}
		}else {
			log.info("用户名和密码为空，请仔细检查登录传参情况");
		}
		return user;
	}

}
