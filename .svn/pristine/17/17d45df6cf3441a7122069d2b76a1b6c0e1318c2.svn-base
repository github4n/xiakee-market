package com.xiakee.ecdao.sso;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiakee.domain.urm.EcDesktopUserBean;

public interface EcDesktopUserDao {
	@Select("SELECT user_id,status,name,lastlogin,logincount FROM sdb_desktop_users WHERE user_id = #{user_id} AND `status` = '1'")
	EcDesktopUserBean findDesktopUserById(Long user_id);

	@Select("SELECT user_id,status,name,lastlogin,logincount FROM sdb_desktop_users WHERE user_id = #{user_id}")
	EcDesktopUserBean findAllDesktopUserById(Long user_id);
	
	@Update("UPDATE sdb_desktop_users SET `status` = '0' WHERE user_id = #{user_id}")
	Integer updateUserStatusById(Long user_id);

	@Select("select r.role_name from sdb_desktop_roles r join sdb_desktop_hasrole u on r.role_id = u.role_id where r.role_name like 'ROLE_%' and user_id = #{user_id}")
	List<String> findRolesUserById(Long user_id);
}
