package com.xiakee.ecdao.analy;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiakee.domain.analy.AnalyResultBean;

public interface EcOrderAnalyDao {
	@Select("SELECT from_unixtime(createtime, '%Y-%m-%d') as `key` , SUM(1) AS value FROM sdb_b2c_orders WHERE from_unixtime(createtime,'%Y-%m-%d %H:%i:%s') BETWEEN #{begin} AND #{end} GROUP BY `key`")
    public List<AnalyResultBean> analyAllDateOrderData(@Param("begin") String begin,@Param("end") String end);
}
