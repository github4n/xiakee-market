package com.xiakee.view.print;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xiakee.domain.logistics.LogistCompBean;
import com.xiakee.domain.print.RecipientBean;
import com.xiakee.service.logistics.LogistCompService;
import com.xiakee.service.yz.OrderAutoPrintService;
import com.xiakee.service.yz.YouzanOrderService;

@Controller
public class PrintOrderController {
	private static Logger log = Logger.getLogger(PrintOrderController.class);

	@Autowired
	private OrderAutoPrintService orderService;
	
	@Autowired
	private YouzanOrderService orderInfoService;
	
	@Autowired
	private LogistCompService logistCompService;
	
	@RequestMapping(value="/allOrderPrintTasker", produces="application/json; charset=utf-8")
	@ResponseBody
    public Object getAllOrderPrintTasker() {
		return orderService.getAllPrintTasker();
	}
	
	@RequestMapping("/beforeManualPrint")
    public String beforeManualPrint(ModelMap model) {
		RecipientBean bean = new RecipientBean();
		model.addAttribute("print", bean);
		return "logist/addManualPrintTasker";
	}
	
	@RequestMapping("/addManualPrintTasker")
	public String addManualPrintTasker(@Param("bean") RecipientBean bean){
		if(bean != null && StringUtils.isNotBlank(bean.getName()) && StringUtils.isNotBlank(bean.getMobile()) && StringUtils.isNotBlank(bean.getAddress())){
			orderService.addPrintTasker(bean);
		}
		return "redirect:/displayOrderList.do";
	}
	
	@RequestMapping("/addOrderTasker")
	public String addOrderPrintTasker(@Param("id") String id,@Param("title") String title){
		log.info("打印订单====" + id + "    title:" + title);
		if(StringUtils.isBlank(title)){
			title = "【尊贵客户，优先派送】";
		}else {
			title = "【" + title + "】";
		}
		if(StringUtils.isNotBlank(id)){
			RecipientBean tasker = orderInfoService.findPrintInfoByInfoid(id);
			tasker.setTitle(title);
			orderService.addPrintTasker(tasker);
			LogistCompBean bean = logistCompService.findLogistComp(id);
			if(bean == null){
				bean = new LogistCompBean();
				bean.setInfoId(Long.parseLong(id));
				logistCompService.addLogistComp(bean);
			}
		}
		return "redirect:/displayOrderList.do";
	}
	
	@RequestMapping("/addOrderPrintTask")
	public String addOrderPrintTasker(@Param("id") String id,@Param("title") String title,@Param("expressno") String expressno){
		log.info("打印订单====" + id + "    title:" + title);
		if(StringUtils.isBlank(title)){
			title = "【尊贵客户，优先派送】";
		}else {
			title = "【" + title + "】";
		}
		if(StringUtils.isNotBlank(id)){
			RecipientBean tasker = orderInfoService.findPrintInfoByInfoid(id);
			tasker.setTitle(title);
			orderService.addPrintTasker(tasker);
			LogistCompBean bean = logistCompService.findLogistComp(id);
			if(bean == null){
				bean = new LogistCompBean();
				bean.setInfoId(Long.parseLong(id));
				logistCompService.addLogistComp(bean);
			}
		}
		return "redirect:/searchLogistDetails.do?expressno=" + expressno + "&msg=1";
	}
	
	@RequestMapping("displayOrderList")
	public String displayOrderList(@RequestParam(value = "page", defaultValue = "1") int page,ModelMap model){
		log.info("获取尚未打印的订单信息");
		if(page < 1){
			page = 1;
		}
		List<RecipientBean> beans = orderInfoService.displayOrderPrintTasker(page);
		if(beans == null && beans.size() < 1){
			page = 1;
		}
		model.addAttribute("beans", beans);
		model.addAttribute("page", page);
		return "logist/displayOrderList";
	}
	
	@RequestMapping("searchOrderPrintTask")
	public String searchOrderPrintTask(@Param("bean") RecipientBean bean,ModelMap model){
		log.info("查询订单打印任务：RecipientBean=" + bean);
		List<RecipientBean> beans = orderInfoService.searchOrderPrintTask(bean);
		
		model.addAttribute("beans", beans);
		model.addAttribute("page", 1);
		return "logist/displayOrderList";
	}
	
	@RequestMapping("searchLogisComp")
	public String searchLogisComp(@Param("bean") RecipientBean bean,ModelMap model){
		log.info("查询订单打印任务：RecipientBean=" + bean);
		List<RecipientBean> beans = orderInfoService.searchOrderPrintTask(bean);
		
		model.addAttribute("beans", beans);
		model.addAttribute("page", 1);
		return "logist/displayOrderList";
	}
	
	@RequestMapping("displayPrintedList")
	public String displayPrintedList(@RequestParam(value = "page", defaultValue = "1") int page,ModelMap model){
		log.info("获取打印过的订单信息");
		if(page < 1){
			page = 1;
		}
		List<RecipientBean> beans = orderInfoService.displayOrderPrintedLIst(page);
		if(beans == null && beans.size() < 1){
			page = 1;
		}
		model.addAttribute("beans", beans);
		model.addAttribute("page", page);
		return "logist/displayPrintedList";
	}
}
