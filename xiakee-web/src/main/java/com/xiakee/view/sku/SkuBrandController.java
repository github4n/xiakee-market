package com.xiakee.view.sku;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiakee.domain.sku.SkuBrandBean;
import com.xiakee.service.sku.SkuBrandService;

@Controller
public class SkuBrandController {
	private static Logger log = Logger.getLogger(SkuBrandController.class);

	@Autowired
	private SkuBrandService skuBrandService;

	@RequestMapping(value = "/displayBrand", produces = "application/json; charset=utf-8")
	public String displayCatalogs(ModelMap model) {
		List<SkuBrandBean> beans = skuBrandService.getAllSkuBrandBean();
		log.info("获取全部的商品品牌");
		model.addAttribute("beans", beans);
		return "displayBrand";
	}

	@RequestMapping("/updateBrandCode")
	@ResponseBody
	public String updateBrandCode(@RequestParam Long id, @RequestParam String code) {
		SkuBrandBean oBean = skuBrandService.findByBrandCode(code);
		if (oBean != null && oBean.getId() != id) {
			// 已存在，唯一索引
			return String.valueOf(-1);
		}
		SkuBrandBean bean = new SkuBrandBean();
		bean.setId(id);
		bean.setBrandCode(code);
		Integer row = skuBrandService.updateBrandCode(bean);
		// 0-失败 1-成功
		return String.valueOf(row > 0 ? 1 : 0);
	}
}
