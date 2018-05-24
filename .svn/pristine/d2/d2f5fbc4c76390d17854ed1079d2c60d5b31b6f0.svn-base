package com.xiakee.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.order.PurchLogsBean;

public interface PurchLogsDao {
	
	@Insert("INSERT INTO purch_logs(infoId,userid,name,content,created) VALUES(#{infoId},#{userid},#{name},#{content},NOW())")
	Integer addPurchLogBean(PurchLogsBean bean);
	
	@Select("select name,content,date_format(created,'%Y-%m-%d %T') as created from purch_logs where infoId = #{infoId}")
	List<PurchLogsBean> findPurchLogsByInfoId(Long infoId);
}
