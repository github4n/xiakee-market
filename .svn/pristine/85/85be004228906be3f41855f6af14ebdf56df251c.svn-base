package com.xiakee.dao.wechat;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiakee.domain.wechat.WechatOpenIdBean;

public interface WechatBindingMobileDao {
    String TABLE_NAME = "xiakee_wxopenid";
    String ALL_FIELD = " openid, mobile,random,verify ";
    String VALUES = "#{openid},#{mobile},#{random}, #{verify}";
    
    @Insert("insert into " + TABLE_NAME +"(" + ALL_FIELD + ") values(" + VALUES + ")")  
    public int add(WechatOpenIdBean bean);
    
    @Select("SELECT random from " + TABLE_NAME + " where openid = #{openid} and verify = 0")
    public String findRandom(String openid);

    @Select("SELECT mobile from " + TABLE_NAME + " where openid = #{openid} and verify = 1")
    public String getUserMobile(String openid);

    @Update("update " + TABLE_NAME + " set verify = 1 where openid = #{openid}")
    public int verifyRandom(String openid);
}
