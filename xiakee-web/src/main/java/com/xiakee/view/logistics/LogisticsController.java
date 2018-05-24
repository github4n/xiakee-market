package com.xiakee.view.logistics;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiakee.domain.ecgoods.XiakeeHaul;
import com.xiakee.domain.ecgoods.XiakeeHaulProcess;
import com.xiakee.domain.logistics.AbroadOrderBean;
import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.order.EcOrderBean;
import com.xiakee.domain.order.EcOrderItemBean;
import com.xiakee.domain.print.RecipientBean;
import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.domain.yz.YzordersBean;
import com.xiakee.ecdao.order.EcOrderDao;
import com.xiakee.ecdao.order.XiakeeHaulMapper;
import com.xiakee.ecdao.order.XiakeeHaulProcessMapper;
import com.xiakee.service.logistics.AbroadOrderService;
import com.xiakee.service.logistics.LogisticsService;
import com.xiakee.service.utils.TimeUtils;
import com.xiakee.service.yz.OrderInfoSevice;
import com.xiakee.service.yz.YouzanOrderService;

@Controller
public class LogisticsController {
	private static Logger log = Logger.getLogger(LogisticsController.class);

	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private YouzanOrderService orderInfoService;
	
	@Autowired
	private OrderInfoSevice infoSevice;
	
	@Autowired
	private AbroadOrderService abroadOrderService;
	
	@Autowired
	private EcOrderDao ecorderDao;
	
	@Autowired
	private XiakeeHaulMapper xiakeeHaulMapper;
	
	@Autowired
	private XiakeeHaulProcessMapper xiakeeHaulProcessMapper;
	/* 
	 * 添加物流信息，并且发送短信
	 */
	@RequestMapping("/beforeAddAbroadInfos")
	public String beforeAddAbroadOrderInfo(@Param("infos") String infos,HttpServletRequest req){
		log.info("获取用户订单物品清单以便添加海外订单信息====" + infos);
//		logisticsService.addLogistics(id);
		List<YzorderInfoBean> beans = null;
		if(StringUtils.isNotBlank(infos)){
			String[] infoIds = infos.split(",");
			beans = new ArrayList<YzorderInfoBean>();
			for(String infoId:infoIds){
				YzorderInfoBean bean = infoSevice.findYzorderInfoBeanById(Long.parseLong(infoId));
				beans.add(bean);
			}
			req.setAttribute("infos", infos);
			req.setAttribute("beans", beans);
			
			return "purchase/addAbroadOrder";
		}
		return "redirect:/displayAllNoExpressno.do";
	}
	
	@RequestMapping("/addAbroadInfos")
	public String addAbroadOrderInfo(@Param("bean") AbroadOrderBean bean,HttpServletRequest req){
		log.info("录入海外订单信息====" + bean);
		Long infoId = abroadOrderService.addAbroadOrderBean(bean);
		YzorderInfoBean infoBean = null;
		if(infoId > -1){
			infoBean = infoSevice.findYzorderInfoBeanById(infoId);
		}
		if(infoBean != null){
			req.setAttribute("infoId",infoBean.getId());
			req.setAttribute("title", infoBean.getTitle() + "【" + infoBean.getSku_properties_name() + "】");
			req.setAttribute("abroadId", bean.getId());
			return "purchase/updateBoxnoInfo";
		}else {
			return "redirect:/displayAllNoExpressno.do";
		}
	}
	
	@RequestMapping(value ="/updateBoxnoInfo", method = RequestMethod.POST)
	public String updateBoxnoInfo(@Param("bean") BoxnoBean bean,HttpServletRequest req){
		log.info("录入海外订单信息中的具体物品信息" + bean);
		logisticsService.updateBoxnoInfo(bean);
		YzorderInfoBean infoBean = abroadOrderService.getYzorderInfoBeanByAbroadId(bean.getAbroadId());
		if(infoBean != null){
			req.setAttribute("infoId",infoBean.getId());
			req.setAttribute("title", infoBean.getTitle() + "【" + infoBean.getSku_properties_name() + "】");
			req.setAttribute("abroadId", bean.getAbroadId());
			return "purchase/updateBoxnoInfo";
		}
		return "redirect:/displayAllNoExpressno.do";
	}
	
	/**
	 * 撤销单次错误信息，并返回重新填写
	 * @Method  updateBoxnoInfo
	 * @Return String
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月2日 下午4:13:13
	 * @Version 1.0
	 */
	@RequestMapping(value ="/updateBoxnoInfo", method = RequestMethod.GET)
	public String updateBoxnoInfo(@Param("infoId") String infoId,@Param("abroadId") String abroadId,ModelMap model){
		if(StringUtils.isNotBlank(infoId) && StringUtils.isNotBlank(abroadId)){
			log.info("修改海外订单信息中的具体物品信息====" + infoId);
			logisticsService.resetBoxnoInfo(Long.parseLong(infoId),Long.parseLong(abroadId));
			YzorderInfoBean infoBean = abroadOrderService.getYzorderInfoBeanByAbroadId(Long.parseLong(abroadId));
			if(infoBean != null){
				model.addAttribute("infoId",infoBean.getId());
				model.addAttribute("title", infoBean.getTitle() + "【" + infoBean.getSku_properties_name() + "】");
				model.addAttribute("abroadId", abroadId);
				return "purchase/updateBoxnoInfo";
			}
		}
		return "redirect:/displayAllNoExpressno.do";
	}
	
	@RequestMapping(value ="/resetAbroadInfos")
	public String resetAbroadInfos(@Param("id") String id){
		log.info("撤销海外订单信息====" + id);
		logisticsService.deleteAbroadExpressno(id);
		return "redirect:/displayAllAbroadOrders.do";
	}
	
	@RequestMapping(value ="/updateOrderInfoRemark", method = RequestMethod.POST)
	public String updateOrderInfoRemark(@Param("id") Long id,@Param("remark") String remark){
		log.info("新增采购备注信息：id=" + id + "   remark=" + remark);
		orderInfoService.updateOrderInfoRemarkById(id, remark);
		return "redirect:/displayAllLogistics.do";
	}
	
	@RequestMapping("/beforeUpdateRemark")
	public String beforeUpdateRemark(@Param("id") Long id,ModelMap model){
		RecipientBean bean = orderInfoService.findPrintInfoByInfoid(String.valueOf(id));
		model.addAttribute("id", id);
		String remark = orderInfoService.findOrderInfoRemarkById(id);
		model.addAttribute("remark", remark);
		model.addAttribute("bean", bean);
		return "logist/udpateOrderRemark";
	}

	@RequestMapping("/displayAllLogistics")
	public String displayAllLogistics(@RequestParam(value = "page", defaultValue = "1") int page,ModelMap model){
		if(page < 1){
			page = 1;
		}
		List<YzordersBean> beans = logisticsService.displayAllLogistics(page);
		if(beans == null || beans.size() < 1){
			page = 1;
		}
		model.addAttribute("beans", beans);
		model.addAttribute("page", page);
		return "purchase/displayAllLogistics";
	}
	/**
	 * 合并老订单
	 */
	@RequestMapping("/mergerAllLog")
	public void mergerAllLog() {
		try{
		//查询老订单中位完成的订单
		List<EcOrderBean> ecorderList= ecorderDao.findAllEcstoreOrdersByStatus("active");
		if(ecorderList != null){
			for(EcOrderBean order:ecorderList){
				//根据订单号查询每个订单下的商品id
				List<EcOrderItemBean> itemList = ecorderDao.findAllOrderItemsByOrderId(order.getOrder_id());
				for(EcOrderItemBean orderItem:itemList){
					//订单+商品id拼接运单号
					String haulId = order.getOrder_id() + "" + orderItem.getItem_id();
					//根据运单号查询该运单是否已存在，若不存在则插入数据库
					XiakeeHaul haul = xiakeeHaulMapper.selectByPrimaryKey(Long.parseLong(haulId));
					if(haul == null){
						XiakeeHaul xiakeeHaul = new XiakeeHaul();
						xiakeeHaul.setHaulId(Long.parseLong(haulId));
						xiakeeHaul.setCreatetime(TimeUtils.getCurrentTime().intValue());
						xiakeeHaul.setOrderId(order.getOrder_id());
						xiakeeHaul.setItemId(orderItem.getItem_id().intValue());
						int xiakeenum = xiakeeHaulMapper.insert(xiakeeHaul);
						//隐射表插入成功，插入物流详情
						if(xiakeenum > 0){
							//根据orderId查询原始log物流信息表，返回xiakeeHaulProcess 集合对象
							List<XiakeeHaulProcess> xiakeeProcessList = ecorderDao.findAllXiakeeHaulByOrderId(order.getOrder_id());
							if(xiakeeProcessList != null){
								for(XiakeeHaulProcess process:xiakeeProcessList){
									XiakeeHaulProcess xiakeeHaulProcess = new XiakeeHaulProcess();
									xiakeeHaulProcess.setAddon(process.getAddon());
									xiakeeHaulProcess.setAlttime(process.getAlttime());
									xiakeeHaulProcess.setBehavior(process.getBehavior());
									xiakeeHaulProcess.setBillType(process.getBillType());
									xiakeeHaulProcess.setLogText(process.getLogText());
									xiakeeHaulProcess.setOpId(process.getOpId());
									xiakeeHaulProcess.setOpName(process.getOpName());
									xiakeeHaulProcess.setOrderId(process.getOrderId());
									xiakeeHaulProcess.setRelId(xiakeeHaul.getHaulId());
									xiakeeHaulProcess.setResult(process.getResult());
									//插入物流信息
									int xiakeeProcessNum = xiakeeHaulProcessMapper.insert(xiakeeHaulProcess);
									if(xiakeeProcessNum > 0){
										log.debug("老物流信息合并成功");
									}
								}
							}
						}
					}
				}
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	}
}
