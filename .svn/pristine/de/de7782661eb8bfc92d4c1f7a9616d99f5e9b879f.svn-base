package com.xiakee.dao.logistics;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiakee.domain.logistics.TransportBean;

public interface TransportDao {
	
	@Insert("INSERT INTO logist_transport(expressno,orderid,transportno,transfer_id,packageno,target,modify) VALUES(#{expressno},#{orderid},#{transportno},#{transfer_id},#{packageno},#{target},#{modify})")
	Integer addTransportBean(TransportBean bean);
	
	@Select("select id,expressno,orderid,transportno,transfer_id,packageno,target,modify from logist_transport where target != 'OK'")
	List<TransportBean> findTransportsByNotOk();
	
	@Update("UPDATE logist_transport SET target = #{target},modify = #{modify} WHERE id = #{id}")
	Integer updateTargetStatus(Long id);
	
	@Select("SELECT id,expressno,orderid,transportno,transfer_id,packageno,target,modify from logist_transport where expressno = #{expressno}")
	List<TransportBean> findTransportsByExpressno(String expressno);
	
}
