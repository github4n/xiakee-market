package com.xiakee.dao.smsc;

import org.apache.ibatis.annotations.Insert;

import com.xiakee.domain.smsc.SmscResultBean;

/**
 * 短信发送详细记录表操作
 * @Product: xiakee-dao
 * @Title: SmscResultDao.java
 * @Package com.xiakee.dao.smsc
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年6月4日 下午9:20:24
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public interface SmscResultDao {
    String TABLE_NAME = "smsc_result";
    String ALL_FIELD = "smscId,mobile,mid,msg,status,created";
    String VALUES = "#{smscId},#{mobile},#{mid},#{msg},#{status},NOW()";
    
    @Insert("INSERT INTO " + TABLE_NAME +"(" + ALL_FIELD + ") VALUES(" + VALUES + ")")  
    public Integer addSmscResult(SmscResultBean bean);
}
