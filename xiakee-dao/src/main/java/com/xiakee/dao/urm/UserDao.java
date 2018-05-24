package com.xiakee.dao.urm;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.urm.SecurityUser;

public interface UserDao {
	String TABLE_NAME = "xiakee_users";
    String ALL_FIELD = " id, name ";
    String INSERT_FIELD = "erp, friend_erp,friend_im_name, creator,created,mender,modified";
    String VALUES = "#{erp},#{friendErp},#{friendImName}, #{creator},now(),#{mender},now()";
    
    @Select("select "+ALL_FIELD+" from " + TABLE_NAME + " WHERE id= #{id}")
    SecurityUser findUserInfoById(@Param("id") Long id);
}
