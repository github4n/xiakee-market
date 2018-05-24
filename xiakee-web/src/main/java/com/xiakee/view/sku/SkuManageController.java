package com.xiakee.view.sku;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talkyun.apus.mybatis.plugin.Page;
import com.xiakee.dao.sku.SkuGoodsDao;
import com.xiakee.dao.sku.SkuGoodsNoDao;
import com.xiakee.dao.sku.SkuGrossProfitDao;
import com.xiakee.domain.sku.SkuBrandBean;
import com.xiakee.domain.sku.SkuCatalogBean;
import com.xiakee.domain.sku.SkuGoodsBean;
import com.xiakee.domain.sku.SkuGoodsNoBean;
import com.xiakee.domain.sku.SkuGrossProfit;
import com.xiakee.domain.sku.SkuManagerBean;
import com.xiakee.domain.sku.SkuTypeBean;
import com.xiakee.domain.sku.SkuUrlsBean;
import com.xiakee.domain.urm.SecurityUser;
import com.xiakee.domain.utils.Constants;
import com.xiakee.service.sku.SkuBrandService;
import com.xiakee.service.sku.SkuCatalogService;
import com.xiakee.service.sku.SkuCrawlerService;
import com.xiakee.service.sku.SkuManagerService;
import com.xiakee.service.sku.SkuTypeService;
import com.xiakee.service.sku.SkuUrlsService;
import com.xiakee.service.sku.SynchSkuDataService;
import com.xiakee.view.task.CheckPriceStoreTask;
import com.xiakee.view.task.PriceMonitorTask;

@Controller
public class SkuManageController extends BaseController {

	@Autowired
	private SynchSkuDataService skuService;
	@Autowired
	private SkuCatalogService skuCatalogService;
	@Autowired
	private SkuBrandService skuBrandService;
	@Autowired
	private SkuTypeService skuTypeService;
	@Autowired
	private SkuManagerService skuManagerService;
	@Autowired
	private SkuUrlsService skuUrlsService;
	@Autowired
	private SkuCrawlerService skuCrawlerService;
	@Autowired
	private SkuGoodsNoDao skuGoodsNoDao;
	@Autowired
	private SkuGoodsDao skuGoodsDao;
	@Autowired
	private SkuGrossProfitDao skuGrossProfitDao;
	@Autowired
	private PriceMonitorTask priceMonitorTask;
	@Autowired
	private CheckPriceStoreTask checkPriceStoreTask;
	
	
	@RequestMapping("checkPriceStoreTask")
	@ResponseBody
	public String checkPriceStoreTask() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				checkPriceStoreTask.checkPriceAndStore();
			}
		});
		t.start();
		return "0";
	}

	@RequestMapping("startUpPriceTask")
	@ResponseBody
	public String startUpPriceTask() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					priceMonitorTask.priceMonitorTask();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		return "0";
	}

	@RequestMapping("reCrawler")
	public String reCrawler(@RequestParam String skuCode) {
		SkuManagerBean bean = skuManagerService.findSkuManagerBeanBySkuCode(skuCode);
		String rawlerUrls = bean.getMainUrl();
		List<SkuUrlsBean> skuUrlsBeanList = skuUrlsService.getSkuUrlsBeanBySkuCode(skuCode);
		if (skuUrlsBeanList != null && skuUrlsBeanList.size() > 0) {
			for (SkuUrlsBean skuUrlsBean : skuUrlsBeanList) {
				if (!rawlerUrls.equals(skuUrlsBean.getUrl())) {
					rawlerUrls += ";" + skuUrlsBean.getUrl();
				}
			}
		}
		skuCrawlerService.crawlerSku(bean.getSkuCode(), rawlerUrls, bean.getTypes() + "", bean.getClassify() + "", 0);
		skuManagerService.comb(skuCode);
		return "redirect:goodsInfo.do?skuCode=" + skuCode;
	}

	@RequestMapping("beforeAddSku")
	public String beforeAddSku(ModelMap model, HttpServletRequest request) {
		String token = new BigInteger(165, new Random()).toString(36).toUpperCase();
		request.getSession().setAttribute("token", token);
		List<SkuBrandBean> brandBeans = skuBrandService.getAllSkuBrandBean();
		List<SkuTypeBean> typeBeans = skuTypeService.getAllTypeBeans();
		List<SkuGrossProfit> grossProfits = skuGrossProfitDao.selectAll();
		model.addAttribute("brandBeans", brandBeans);
		model.addAttribute("typeBeans", typeBeans);
		model.addAttribute("grossProfits", grossProfits);
		model.addAttribute("defaultgrossProfits", grossProfits.get(0).getValue());
		return "sku/addSku";
	}

	@RequestMapping(value = "/checkUrl")
	public void checkUrl(@RequestParam String urls, HttpServletResponse response) throws Exception {
		String results = skuManagerService.getSkuCodeByUrls(urls);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (!"".equals(results)) {
			out.println(results);
		}
	}

	@RequestMapping("addSkuInfo")
	public String addSkuInfo(SkuManagerBean bean, HttpServletRequest request) {
		SecurityUser user = getUser();
		if (!validToken(request)) {
			return "redirect:beforeAddSku.do";
		}
		bean.setUserId(user.getId());
		String[] urls = request.getParameterValues("urls");
		skuManagerService.addSkuManagerBean(bean, urls);
		return "redirect:skuManager.do";
	}

	public boolean validToken(HttpServletRequest request) {
		String token = request.getParameter("springMVC.token");
		final HttpSession session = request.getSession();
		synchronized (session) {
			if (token != null && token.equals(session.getAttribute("token"))) {
				session.removeAttribute("token");
				return true;
			} else {
				return false;
			}
		}
	}

	@RequestMapping("delSkuInfo")
	@Transactional
	public String delSkuInfo(@RequestParam Long id) {
		SkuManagerBean bean = skuManagerService.findSkuManagerBeanById(id);
		skuManagerService.delSkuManagerBeanById(id);
		skuUrlsService.delSkuManagerBeanBySkuCode(bean.getSkuCode());
		SkuGoodsNoBean goodsNoBean = new SkuGoodsNoBean();
		goodsNoBean.setGoodsno(bean.getSkuCode());
		skuGoodsNoDao.delBySkuCode(goodsNoBean);
		skuGoodsDao.deleteBySkuCode(bean.getSkuCode());
		return "redirect:skuManager.do";
	}

	@RequestMapping("synchSkuData")
	public String synchSkuData() {
		skuService.synchCatalogTypeData();
		return "redirect:skuManager.do";
	}

	@RequestMapping("skuManager")
	public String skuManager(ModelMap model, HttpServletRequest request) {
		String firstClassify = request.getParameter("firstClassify");
		String secondClassify = request.getParameter("secondClassify");
		String thirdClassify = request.getParameter("thirdClassify");
		String classify = request.getParameter("classify");
		String brand = request.getParameter("brand");
		String type = request.getParameter("type");
		String skuCode = request.getParameter("skuCode");
		// String status = request.getParameter("status");
		String isImport = request.getParameter("isImport");
		// String priceIncreaseArithmetic =
		// request.getParameter("priceIncreaseArithmetic");
		// String priceIncrease = request.getParameter("priceIncrease");
		String grossId = request.getParameter("grossId");
		String priceLockDatas = request.getParameter("priceLockDatas");
		int currPage = ServletRequestUtils.getIntParameter(request, "currPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
		Map<String, Object> param = new HashMap<String, Object>();
		Page page = buildPage(currPage, pageSize);
		param.put(Constants.PAGE, page);
		if (StringUtils.isNotBlank(classify)) {
			param.put("classify", classify);
		}
		if (StringUtils.isNotBlank(brand)) {
			param.put("brand", brand);
		}
		if (StringUtils.isNotBlank(skuCode)) {
			if ("0".equals(type)) {
				param.put("skuCode", skuCode);
			} else {
				param.put("name", skuCode);
			}
		}
		// if (StringUtils.isNotEmpty(status)) {
		// param.put("status", status);
		// }
		// if (StringUtils.isNotEmpty(priceIncreaseArithmetic)) {
		// param.put("priceIncreaseArithmetic", priceIncreaseArithmetic);
		// }
		if (StringUtils.isNotEmpty(grossId)) {
			param.put("grossId", grossId);
		}
		// if (StringUtils.isNotEmpty(status)) {
		// param.put("priceIncrease", priceIncrease);
		// }
		if (StringUtils.isNotEmpty(priceLockDatas)) {
			param.put("priceLockDatas", priceLockDatas);
		}
		if (StringUtils.isNotEmpty(isImport)) {
			param.put("isImport", isImport);
		}

		List<SkuManagerBean> beans = skuManagerService.selectByBrandAndClassify_page(param);
		List<SkuBrandBean> brandBeanList = skuBrandService.getAllSkuBrandBean();
		List<SkuGrossProfit> grossProfits = skuGrossProfitDao.selectAll();
		for (SkuManagerBean skuManagerBean : beans) {
			SkuCatalogBean catalogBean = skuCatalogService.findSkuCatalogById(skuManagerBean.getClassify());
			SkuTypeBean typeBean = skuTypeService.findSkuTypeById(skuManagerBean.getTypes());
			SkuBrandBean brandBean = skuBrandService.findSkuBrandById(skuManagerBean.getBrand());
			if (catalogBean != null) {
				skuManagerBean.setClassifyName(catalogBean.getCat_name());
			}
			if (typeBean != null) {
				skuManagerBean.setTypesName(typeBean.getName());
			}
			if (brandBean != null) {
				skuManagerBean.setBrandName(brandBean.getName());
			}
			SkuGrossProfit gross = skuGrossProfitDao.selectByPrimaryKey(skuManagerBean.getGrossId());
			if (gross != null) {
				skuManagerBean.setGrossName(gross.getName().substring(0, 2));
			}
		}
		model.addAttribute("firstClassify", firstClassify);
		model.addAttribute("secondClassify", secondClassify);
		model.addAttribute("thirdClassify", thirdClassify);
		model.addAttribute("classify", classify);
		model.addAttribute("brand", brand);
		model.addAttribute("beans", beans);
		model.addAttribute("type", type);
		model.addAttribute("skuCode", skuCode);
		// model.addAttribute("status", status);
		model.addAttribute("isImport", isImport);
		model.addAttribute("grossId", grossId);
		model.addAttribute("priceLockDatas", priceLockDatas);
		// model.addAttribute("priceIncreaseArithmetic",
		// priceIncreaseArithmetic);
		// model.addAttribute("priceIncrease", priceIncrease);
		model.addAttribute("page", page);
		model.addAttribute("brandBeanList", brandBeanList);
		model.addAttribute("grossProfits", grossProfits);
		return "sku/skuManager";
	}

	@RequestMapping("goodsInfo")
	public String goodsInfo(Model model, @RequestParam String skuCode) {
		// 获取数据
		SkuManagerBean managerBean = skuManagerService.findSkuManagerBeanBySkuCode(skuCode);
		SkuGoodsBean record = new SkuGoodsBean();
		record.setSkuCode(skuCode);
		try{
		List<SkuGoodsBean> goodsBeanList = skuGoodsDao.selectBySkuCodeAndSpec(record);
		model.addAttribute("managerBean", managerBean);
		model.addAttribute("goodsBeanList", goodsBeanList);
		model.addAttribute("skuCode", skuCode);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "sku/goodsInfo";
	}

	@RequestMapping("setPriceLockTime")
	@ResponseBody
	public String setPriceLockTime(@RequestParam Long pid, @RequestParam String time) throws Exception {
		// 获取数据
		Date priceLockTime = null;
		if (StringUtils.isNotBlank(time)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			priceLockTime = sdf.parse(time);
		}
		SkuManagerBean bean = new SkuManagerBean();
		bean.setId(pid);
		bean.setPriceLockTime(priceLockTime);
		skuManagerService.updatePriceLockTime(bean);
		return "1";
	}

}