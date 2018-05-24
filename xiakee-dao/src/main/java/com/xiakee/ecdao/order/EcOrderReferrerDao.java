package com.xiakee.ecdao.order;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.ecanaly.EcReferrerBean;

public interface EcOrderReferrerDao {
	@Select("select order_id from sdb_xiakee_referrer where referrer = #{referrer} group by order_id")
    public List<String> findReferrerByOrderid(String referrer);
	@Select("select order_id from sdb_xiakee_referrer where referrer Like #{referrer} group by order_id")
    public List<String> findReferrerByLikeid(String referrer);
	
	@Select("select sum(1) from (select distinct(order_id) from sdb_xiakee_referrer where referrer = #{referrer})  as ordersum")
    public Integer getOrderSumByReferrer(String referrer);
	@Select("select sum(1) from (select distinct(order_id) from sdb_xiakee_referrer where referrer Like #{referrer})  as ordersum")
    public Integer getOrderSumByReferrerLike(String referrer);
}
