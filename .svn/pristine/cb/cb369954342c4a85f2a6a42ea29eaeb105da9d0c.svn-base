package com.xiakee.dao.sys;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.xiakee.domain.sys.SystemLogBean;

public interface SystemLogDao {
	@Insert("INSERT INTO `sys_logs`(`userid`,`url`,`created`) VALUES(#{userid},#{url},NOW())")  
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id", keyProperty = "id", resultType = Long.class)
    Integer addSystemLogBean(SystemLogBean bean);
}
