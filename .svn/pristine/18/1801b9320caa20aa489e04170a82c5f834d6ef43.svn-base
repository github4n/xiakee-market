package com.xiakee.view.analy;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiakee.domain.utils.AnalyTypeEnum;
import com.xiakee.service.analy.AnalyticalService;

@Controller
public class AnalyticalController {
	private static Logger log = Logger.getLogger(AnalyticalController.class);
	
	@Autowired
	private AnalyticalService analyService;
	
	@RequestMapping("/analyOrderOrigin")
	@ResponseBody
    public Object analyOrderOrigin(@Param("date") String date,@Param("type") int type) {
		AnalyTypeEnum analyType = AnalyTypeEnum.enumValueOf(type);
		log.info("订单来源数据分析请求类型：" + type);
		return this.analyService.analyOrderOrigin(date, analyType);
	}
	
	@RequestMapping("/statisticsSource")
	public String statisticsSource(){
		return "analy/statisticsSource";
	}
	
	@RequestMapping("/statisticsProvince")
	public String statisticsProvince(){
		return "analy/statisticsProvince";
	}
	
	@RequestMapping("/statisticsDate")
	public String statisticsDate(){
		return "analy/statisticsDate";
	}
	
	@RequestMapping("/statisticsTime")
	public String statisticsTime(){
		return "analy/statisticsTime";
	}
	
	@RequestMapping("/statisticsMonth")
	public String statisticsMonth(){
		return "analy/statisticsMonth";
	}
	
	@RequestMapping("/statisticsCost")
	public String statisticsCost(){
		return "analy/statisticsCost";
	}
}
