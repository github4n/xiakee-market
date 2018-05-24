package com.xiakee.view.logistics;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiakee.domain.ecos.EcIdcardBean;
import com.xiakee.domain.logistics.LogistDetailsBean;
import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.domain.order.EcOrderLogistBean;
import com.xiakee.domain.yz.YzordersBean;
import com.xiakee.service.logistics.EcOrderLogistService;
import com.xiakee.service.yz.YouzanOrderService;

@Controller
public class EcstoreOrderLogistController {
	private static Logger log = Logger.getLogger(EcstoreOrderLogistController.class);
	private static final List<String> LOGIST_TEXT = Arrays.asList(
			"您的商品正在备货中，请耐心等待",
			"您的商品备货完毕，正在快速送往遐客行美国仓",
			"您的商品已到达遐客行美国仓，正办理回国手续，等待出库", 
			"您的商品已出库，正前往美国机场等待登机回国", 
			"您的商品已登机回国，遐客行正全程护送，即将送达国内",
			"您的商品已经抵达中国，正在海关办理通关手续",
			"您的商品已通关完毕，正在转国内快递");
	
	@Autowired
	private EcOrderLogistService logistService;
	@Autowired
	private YouzanOrderService yzOrderService;
	
	@RequestMapping("/displayEcstoreLogist")
	public String displayEcstoreLogist(ModelMap model){
		List<EcOrderBean> beans = logistService.findAllEcstoreSuccOrders();
		model.addAttribute("beans", beans);
		return "logist/ecstoreLogistManager";
	}
	
	@RequestMapping("/displayOrderLogist")
	public String displayOrderLogist(@Param("orderId") String orderId,ModelMap model){
		if(StringUtils.isNotBlank(orderId)){
			List<EcOrderLogistBean> beans = logistService.findAllLogistByOrderId(orderId);
			model.addAttribute("beans", beans);
			model.addAttribute("orderId",orderId);
			int sum = beans.size();
			
			if(sum <7){
				model.addAttribute("logisText", LOGIST_TEXT.get(sum));
				model.addAttribute("node",sum + 3);//物流1，2节点在商城生成，如果获取的是零，则添加的信息得从三开始
			}
			
			List<LogistDetailsBean> detailBeans = logistService.findOrderLogistInfo(orderId);
			model.addAttribute("express", detailBeans);
			
			YzordersBean bean = yzOrderService.findOrderInfoByOrderid(orderId);
			model.addAttribute("bean", bean);
		}
		return "logist/displayOrderLogist";
	}
	
	@RequestMapping("/searchLogistDetails")
	public String searchLogistDetails(@Param("orderId") String orderId,@Param("expressno") String expressno,@Param("msg") String msg,ModelMap model){
		if(StringUtils.isNotBlank(orderId)){
			return "redirect:/displayOrderLogist.do?orderId=" + orderId;
		}else {
			LogistDetailsBean bean = logistService.findLogistDetailsByExpressno(expressno);
			model.addAttribute("bean", bean);
			if(StringUtils.isNotBlank(msg)){
				if(StringUtils.equals("1", msg)){
					model.addAttribute("msg", "恭喜您，打印成功！");
				}
			}
			return "logist/expressnoDetails";
		}
	}
	
	@RequestMapping("/displayIdcards")
	public String displayIdcards(@Param("memberId") Long memberId,ModelMap model){
		List<EcIdcardBean> beans = logistService.findIdcardBeansByMemberid(memberId);
		model.addAttribute("beans", beans);
		return "logist/displayIdcards";
	}
	
	@RequestMapping("/addOrderLogist")
	public String addOrderLogist(@Param("bean") EcOrderLogistBean bean,ModelMap model){
		logistService.addOrderLogist(bean);
		List<EcOrderLogistBean> beans = logistService.findAllLogistByOrderId(bean.getRel_id());
		model.addAttribute("beans", beans);
		model.addAttribute("orderId",bean.getRel_id());
		int sum = beans.size();
		if(sum <7){
			model.addAttribute("logisText", LOGIST_TEXT.get(sum));
			model.addAttribute("node",sum + 3);//物流1，2节点在商城生成，如果获取的是零，则添加的信息得从三开始
		}
		return "logist/displayOrderLogist";
	}
	
	@RequestMapping("/setupTranspComp")
	public String setupTranspComp(@Param("bean") EcOrderLogistBean bean,ModelMap model){
		if(bean != null && StringUtils.isNotBlank(bean.getRel_id()) && StringUtils.isNotBlank(bean.getLog_text())){
			logistService.manualAddEcosLogist(bean);
		}
		List<EcOrderLogistBean> beans = logistService.findAllLogistByOrderId(bean.getRel_id());
		model.addAttribute("beans", beans);
		model.addAttribute("orderId",bean.getRel_id());
		return "logist/displayOrderLogist";
	}
}
