package com.xiakee.view.sku;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiakee.domain.sku.SkuTypeBean;
import com.xiakee.service.sku.SkuTypeService;

@Controller
public class SkuTypeController {
	private static Logger log = Logger.getLogger(SkuTypeController.class);

	@Autowired
	private SkuTypeService skuTypeService;

	@RequestMapping(value = "/displayType", produces = "application/json; charset=utf-8")
	public String displayCatalogs(ModelMap model) {
		List<SkuTypeBean> beans = skuTypeService.getAllTypeBeans();
		log.info("获取全部的商品类别");
		model.addAttribute("beans", beans);
		return "displayType";
	}

	@RequestMapping("/updateTypeCode")
	@ResponseBody
	public String updateBrandCode(@RequestParam Long id, @RequestParam String code) {
		SkuTypeBean oBean = skuTypeService.findByTypeCode(code);
		if (oBean != null && oBean.getType_id() != id) {
			// 已存在，唯一索引
			return String.valueOf(-1);
		}
		SkuTypeBean bean = new SkuTypeBean();
		bean.setType_id(id);
		bean.setTypeCode(code);
		Integer row = skuTypeService.updateTypeCode(bean);
		// 0-失败 1-成功
		return String.valueOf(row > 0 ? 1 : 0);
	}
}
