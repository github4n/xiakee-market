package com.xiakee.dao.analy;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.analy.ReferrerUrlBean;


public interface ReferrerUrlDao {
	
	@Insert("INSERT INTO analy_order_url(userid,url,title,created) VALUES(#{userid},#{url},#{title},NOW())")  
    public Integer addReferrerUrlBean(ReferrerUrlBean bean);
	
	@Select("SELECT * FROM analy_order_url WHERE id = #{id}")
	public ReferrerUrlBean getReferrerUrlBeanById(Long id);

	@Select("select id,userid,url,title,date_format(created,'%Y-%m-%d %H:%i') as created from analy_order_url where userid = #{userid}  order by id desc")
    public List<ReferrerUrlBean> findReferrerUrlBeanById(Long userid);
	
	@Delete("DELETE FROM analy_order_url WHERE id = #{id}")
	Integer deleteUrlById(Long id);
}
