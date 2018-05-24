package com.xiakee.dao.urm;

import org.apache.ibatis.annotations.Insert;

import com.xiakee.domain.urm.IdentityNumberBean;

public interface IdentityNumberDao {
    String TABLE_NAME = "xiakee_userinfo";
    String ALL_FIELD = "mobile,identity_number";
    String VALUES = "#{mobile},#{identityNumber}";
    
    @Insert("INSERT INTO " + TABLE_NAME +"(" + ALL_FIELD + ") VALUES(" + VALUES + ")")  
    public Integer addIdentityNumber(IdentityNumberBean bean);
}
