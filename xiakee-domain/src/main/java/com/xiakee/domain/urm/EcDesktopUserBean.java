package com.xiakee.domain.urm;

public class EcDesktopUserBean {
	private Long user_id;
	private String status;
	private String name;
	private Long lastlogin;
	private Long logincount;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Long lastlogin) {
		this.lastlogin = lastlogin;
	}
	public Long getLogincount() {
		return logincount;
	}
	public void setLogincount(Long logincount) {
		this.logincount = logincount;
	}
	@Override
	public String toString() {
		return "erp系统账号通用户名称信息：EcDesktopUserBean" + getUser_id() + getName() + getStatus() + getLastlogin();
	}
	
}
