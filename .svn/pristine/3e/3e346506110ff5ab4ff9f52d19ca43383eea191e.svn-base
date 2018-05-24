package com.xiakee.view;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页部分功能
 * @Product: xiakee-web
 * @Title: IndexConfigController.java
 * @Package com.xiakee.web.view
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年5月12日 下午1:51:43
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
@Controller
public class IndexConfigController {
	
	@RequestMapping("/index")
    public String index(ModelMap model) {
        return "index";
    }
	
    @RequestMapping("/error")
    @ExceptionHandler(Exception.class)
    public String error() {
        return "error";
    }
	
    @RequestMapping("/skipPage")
    public String skipPage(@Param("id") String id) {
    	if(StringUtils.isNotBlank(id) && id.startsWith("add")){
    		return id;
    	}
        return "error";
    }

    /**
     * 绑定处理异常，统一处理的方式，这里直接绑定处理异常类AccessDeniedException，
	 * 也可以实现AccessDeniedHandler类重写“handle()”方法，在里面设置你自己的访问逻辑
     * @Method  accessDenied
     * @Return String
     * @Author 谢坚柏
     * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
     * @Date 2015年5月13日 上午11:17:31
     * @Version 1.0
     */
    @RequestMapping("/accessDenied")
    @ExceptionHandler(AccessDeniedException.class)
    public String accessDenied() {
        return "accessDenied";
    }
}
