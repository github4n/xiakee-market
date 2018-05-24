package com.xiakee.dao.logistics;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.logistics.LogistCompBean;

public interface LogistCompDao {
	String TABLE_NAME = "xiakee_logistComp";
	
	@Insert("INSERT INTO xiakee_logistComp(infoId,logistComp,created) VALUES(#{infoId},#{logistComp},NOW())")
	Integer addLogistComp(LogistCompBean bean);
	
	@Select("select infoId,logistComp,created from xiakee_logistComp where infoId = #{infoId}")
	LogistCompBean findLogistComp(String infoId);
}
