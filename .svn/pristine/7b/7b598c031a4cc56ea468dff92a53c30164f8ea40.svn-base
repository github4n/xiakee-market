package com.xiakee.ecdao.sso;

import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.urm.EcAccountBean;

public interface EcAccountDao {
	
	/**
	 * 根据登录名获取注册时间与密码
	 * @Method  findAccountBeanByLoginName
	 * @Return EcAccountBean
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年9月8日 下午9:16:27
	 * @Version 1.0
	 */
	@Select("SELECT account_id,login_name,login_password,createtime FROM sdb_pam_account WHERE account_type = 'shopadmin' AND login_name = #{login_name}")
	EcAccountBean findAccountBeanByLoginName(String login_name);
}
