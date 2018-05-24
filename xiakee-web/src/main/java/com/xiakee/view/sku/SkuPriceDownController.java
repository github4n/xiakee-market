package com.xiakee.view.sku;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talkyun.apus.mybatis.plugin.Page;
import com.xiakee.dao.sku.SkuGoodsDao;
import com.xiakee.dao.sku.SkuGrossProfitDao;
import com.xiakee.domain.ecgoods.EcProducts;
import com.xiakee.domain.sku.SkuBrandBean;
import com.xiakee.domain.sku.SkuCatalogBean;
import com.xiakee.domain.sku.SkuGrossProfit;
import com.xiakee.domain.sku.SkuPriceDownBean;
import com.xiakee.domain.sku.SkuTypeBean;
import com.xiakee.domain.utils.Constants;
import com.xiakee.ecdao.order.EcProductsDao;
import com.xiakee.service.sku.SkuBrandService;
import com.xiakee.service.sku.SkuCatalogService;
import com.xiakee.service.sku.SkuTypeService;

@Controller
public class SkuPriceDownController extends BaseController {

	@Autowired
	private SkuGoodsDao skuGoodsDao;
	@Autowired
	private SkuCatalogService skuCatalogService;
	@Autowired
	private SkuBrandService skuBrandService;
	@Autowired
	private SkuTypeService skuTypeService;
	@Autowired
	private SkuGrossProfitDao skuGrossProfitDao;
	@Autowired
	private EcProductsDao ecProductsDao;

	@RequestMapping("priceDown")
	public String priceDown(ModelMap model, HttpServletRequest request) {
		String firstClassify = request.getParameter("firstClassify");
		String secondClassify = request.getParameter("secondClassify");
		String thirdClassify = request.getParameter("thirdClassify");
		String classify = request.getParameter("classify");
		String brand = request.getParameter("brand");
		int priceIncrease = ServletRequestUtils.getIntParameter(request, "priceIncrease", 10);
		List<SkuBrandBean> brandBeanList = skuBrandService.getAllSkuBrandBean();
		Map<String, Object> params = new HashMap<String, Object>();
		int currPage = ServletRequestUtils.getIntParameter(request, "currPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
		Page page = buildPage(currPage, pageSize);
		params.put(Constants.PAGE, page);
		if (StringUtils.isNotEmpty(classify)) {
			params.put("classify", classify);
		}

		if (StringUtils.isNotEmpty(brand)) {
			params.put("brand", brand);
		}

		params.put("priceIncrease", priceIncrease);
		List<SkuPriceDownBean> priceDownList = skuGoodsDao.selectByPriceDown_page(params);
		for (SkuPriceDownBean skuPriceDownBean : priceDownList) {
			SkuCatalogBean catalogBean = skuCatalogService.findSkuCatalogById(skuPriceDownBean.getClassify());
			SkuTypeBean typeBean = skuTypeService.findSkuTypeById(skuPriceDownBean.getTypes());
			SkuBrandBean brandBean = skuBrandService.findSkuBrandById(skuPriceDownBean.getBrand());
			if (catalogBean != null) {
				skuPriceDownBean.setClassifyName(catalogBean.getCat_name());
			}
			if (typeBean != null) {
				skuPriceDownBean.setTypesName(typeBean.getName());
			}
			if (brandBean != null) {
				skuPriceDownBean.setBrandName(brandBean.getName());
			}
			SkuGrossProfit gross = skuGrossProfitDao.selectByPrimaryKey(skuPriceDownBean.getGrossId());
			if (gross != null) {
				skuPriceDownBean.setGrossName(gross.getName().substring(0, 2));
			}

			if (skuPriceDownBean.getImage() != null) {
				skuPriceDownBean.setImage("http://img.xiakee.com/i/50-50-1/img.xiakee.com" + skuPriceDownBean.getImage());
			}

			EcProducts product = ecProductsDao.selectByBn(skuPriceDownBean.getGoodsNo());
			if (product != null) {
				skuPriceDownBean.setXiakeeUrl("http://www.xiakee.com/product-" + product.getProductId() + ".html");
			}
			skuPriceDownBean.setPriceIncrease(0 - skuPriceDownBean.getPriceIncrease());
		}
		model.addAttribute("brandBeanList", brandBeanList);
		model.addAttribute("priceDownList", priceDownList);

		model.addAttribute("firstClassify", firstClassify);
		model.addAttribute("secondClassify", secondClassify);
		model.addAttribute("thirdClassify", thirdClassify);
		model.addAttribute("classify", classify);
		model.addAttribute("brand", brand);
		model.addAttribute("priceIncrease", priceIncrease);
		model.addAttribute("page", page);
		return "sku/priceDown";
	}

}
