package com.xiakee.dao.smsc;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.xiakee.domain.smsc.SmscRecordBean;

/**
 * 短信发送信息记录表操作
 * @Product: xiakee-dao
 * @Title: SmscRecordDao.java
 * @Package com.xiakee.dao.smsc
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年6月4日 下午9:15:01
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public interface SmscRecordDao {
    String TABLE_NAME = "smsc_record";
    String ALL_FIELD = "mobile,content,types,status,counts,created";
    String VALUES = "#{mobile},#{content},#{types},#{status},#{counts},NOW()";
    
    @Insert("INSERT INTO " + TABLE_NAME +"(" + ALL_FIELD + ") VALUES(" + VALUES + ")") 
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id", keyProperty = "id", resultType = Long.class)
    public Integer addSmscRecored(SmscRecordBean bean);
}
