package com.xiakee.ecdao.order;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.ecos.EcIdcardBean;

public interface EcIdcardDao {
	@Select("SELECT idcard.*,addrs.`name`,addrs.addr,addrs.mobile FROM sdb_xiakee_idcards idcard INNER JOIN sdb_b2c_member_addrs addrs ON idcard.member_id = addrs.member_id  WHERE idcard.member_id = #{member_id}")
	List<EcIdcardBean> findIdcardBeansByMemberid(Long member_id);
	
	@Select("select distinct(mobile) from sdb_b2c_member_addrs")
	List<String> getAllMemberAddrsMobiles();
}
