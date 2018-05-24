package com.xiakee.view.sku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiakee.dao.sku.SkuManagerDao;
import com.xiakee.domain.sku.SkuManagerBean;
import com.xiakee.service.sku.SkuImportShopService;

@Controller
public class SkuImportShopController {

	@Autowired
	private SkuManagerDao managerDao;
	@Autowired
	private SkuImportShopService skuImportShopService;

	@RequestMapping("importShop")
	@ResponseBody
	public String importShop(@RequestParam String skuCode) throws Exception {
		SkuManagerBean bean = managerDao.findSkuManagerBeanBySkuCode(skuCode);
		if (bean != null) {
			skuImportShopService.importData(bean);
		}
		return "1";
	}

}
