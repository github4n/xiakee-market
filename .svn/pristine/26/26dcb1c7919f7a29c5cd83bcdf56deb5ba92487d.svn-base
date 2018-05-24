package com.xiakee.view.sku;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xiakee.domain.sku.SkuCatalogBean;
import com.xiakee.service.sku.SkuCatalogService;

@Controller
public class SkuCatalogController {
	private static Logger log = Logger.getLogger(SkuCatalogController.class);

	@Autowired
	private SkuCatalogService catalogService;

	@RequestMapping("/displayCatalogs")
	public String displayCatalogs(ModelMap model) {
		List<SkuCatalogBean> beans = catalogService.getAllSkuCatalogBeans();
		log.info("获取全部的商品分类");
		model.addAttribute("beans", beans);
		return "displayCatalogs";
	}

	@RequestMapping("/updateCatalogCode")
	@ResponseBody
	public String updateCatalogCode(@RequestParam Long id, @RequestParam String code) {
		SkuCatalogBean oBean = catalogService.findByCatalogCode(code);
		if (oBean != null && oBean.getCat_id() != id) {
			return String.valueOf(-1);
		}
		SkuCatalogBean bean = new SkuCatalogBean();
		bean.setCat_id(id);
		bean.setCatCode(code);
		Integer row = catalogService.updateCatalogCode(bean);
		return JSON.toJSONString(row);
	}

	@RequestMapping(value = "/getCatalogByParentId", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCatalogByParentId(@RequestParam int parent_id) {
		List<SkuCatalogBean> beans = catalogService.getCatalogByParentId(parent_id);
		return JSON.toJSONString(beans);
	}

	@RequestMapping(value = "/getCatalogTypeById", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCatalogTypeById(@RequestParam Long id) {
		SkuCatalogBean bean = catalogService.findSkuCatalogById(id);
		return String.valueOf(bean.getType_id());
	}
}
