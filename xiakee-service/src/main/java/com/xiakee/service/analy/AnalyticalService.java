package com.xiakee.service.analy;

import com.xiakee.domain.utils.AnalyTypeEnum;

public interface AnalyticalService {
	
	/**
	 * 数据统计接口
	 * @param date
	 * @param type
	 * @return
	 */
	Object analyOrderOrigin(String date,AnalyTypeEnum type);
}
