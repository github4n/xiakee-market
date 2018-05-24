package com.xiakee.domain.urm;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 扩展对security的User类支持
 * @Product: xiakee-domain
 * @Title: UserBean.java
 * @Package com.xiakee.domain.urm
 * @Description: 遐客行后台管理系统
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com
 * @Date 2015年6月29日 下午11:36:24
 * @Version 1.0
 * @Copyright: Copyright (c) 2014
 */
public class SecurityUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//用户ID，用在记录用户各种操作记录上
	private Long id;
	//用户名称，不是登录名称，是显示的昵称
	private String name;
	//用户的手机号码
	private String mobile;

	public SecurityUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public SecurityUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,Long id,String name,String mobile) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.id = id;
		this.name = name;
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		String allInfo = super.toString();
		allInfo = "用户ID：" + getId() + ",用户昵称：" + getName() + ",手机号码：" + getMobile() + "，其他信息：" + allInfo;
		return allInfo;
	}
	
}
