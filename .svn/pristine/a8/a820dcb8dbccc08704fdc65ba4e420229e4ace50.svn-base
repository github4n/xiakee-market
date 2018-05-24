package com.xiakee.view.analy;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiakee.domain.analy.OrderReferrerBean;
import com.xiakee.domain.analy.ReferrerUrlBean;
import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.service.analy.OrderReferrerService;
import com.xiakee.service.utils.PromotionUrlUtil;
import com.xiakee.service.utils.QrCodeUtil;
import com.xiakee.service.utils.SessionUserDetailUtil;

@Controller
public class OrderReferrerController {
	private static Logger log = Logger.getLogger(OrderReferrerController.class);
	
	@Autowired
	private OrderReferrerService referService;
	
	@RequestMapping("/createQrcodeImage")
	public void createQrcodeImage(@Param("url") String url,HttpServletResponse resp){
		log.info("生成链接二维码");
		FileInputStream fis = null;
		resp.setContentType("image/jpg");
		try{
			OutputStream out = resp.getOutputStream();
			if(StringUtils.isBlank(url)){
				url = "http://www.xiakee.com/wap";
			}
			boolean flag = ImageIO.write(QrCodeUtil.encode(url, 500, 500), "jpg", out);
			out.flush();
		}catch(Exception e){
			log.error("生成链接二维码失败",e);
		}finally {
			if(fis != null){
				try{
					fis.close();
				}catch(Exception e){
				}
			}
		}
	}
	
	@RequestMapping("/getQrcodeImage")
	public String getQrcodeImage(@Param("url") String url,ModelMap model){
		model.addAttribute("url", url);
		return "analy/createQrcode";
	}
	
	@RequestMapping("/findOrderReferrer")
	public String findOrderReferrer(ModelMap model){
		log.info("获取个人订单跟踪信息");
		List<OrderReferrerBean> beans = this.referService.findOwnOrderReferByUserId();
		model.addAttribute("beans", beans);
		return "analy/findOrderReferrer";
	}
	
	@RequestMapping("/beforeAddOrderReferrerCode")
	public String beforeAddOrderReferrerCode(ModelMap model){
		model.addAttribute("name", SessionUserDetailUtil.findUserDetail().getName());
		return "analy/addOrderReferrerCode";
	}
	
	@RequestMapping("addOrderReferrerCode")
	public String addOrderReferrerCode(@Param("bean") OrderReferrerBean bean){
		referService.addOrderRefCode(bean);
		return "redirect:/findOrderReferrer.do";
	}
	
	@RequestMapping("createReferCode")
	public String createReferCode(@Param("id") Long id,@Param("url") String url,ModelMap model){
		OrderReferrerBean bean = this.referService.findOrderReferrerBeanById(id);
		if(StringUtils.isNotBlank(url)){
			String[] urls = PromotionUrlUtil.createPromotionLink(String.valueOf(id), url);
			if(urls != null && urls.length == 2){
				model.addAttribute("pc",urls[0]);
				model.addAttribute("wap",urls[1]);
				bean.setCode("2");
			}
		}
		model.addAttribute("bean",bean);
		return "analy/createReferCode";
	}	
	
	@RequestMapping("/findOrderByReferCode")
	public String findOrderByReferCode(@Param("id") Long id,ModelMap model){
		List<EcOrderBean> beans = this.referService.findOrderByReferCode(String.valueOf(id));
		model.addAttribute("beans", beans);
		return "analy/referrerOrderList";
	}
	
	@RequestMapping("/beforeAddReferUrl")
	public String beforeAddReferUrl(){
		return "analy/addReferUrl";
	}
	
	@RequestMapping("addReferUrl")
	public String addReferUrl(@Param("bean") ReferrerUrlBean bean){
		referService.addReferrerUrl(bean);
		return "redirect:/displayReferUrls.do";
	}
	
	@RequestMapping("delReferUrlById")
	public String delReferUrlById(@Param("id") Long id){
		referService.deleteReferUrl(id);
		return "redirect:/displayReferUrls.do";
	}
	
	@RequestMapping("displayReferUrls")
	public String displayReferUrls(ModelMap model){
		model.addAttribute("beans", referService.findReferrerUrlsByUserId());
		return "analy/displayReferUrls";
	}
	
	@RequestMapping("getUrlsById")
	public String getUrlsById(@Param("id") Long id,ModelMap model){
		model.addAttribute("beans", referService.getPromotionUrls(id));
		return "analy/getUrlsById";
	}
}
