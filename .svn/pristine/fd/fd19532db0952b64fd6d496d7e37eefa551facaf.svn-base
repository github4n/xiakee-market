package com.xiakee.view.material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.talkyun.apus.mybatis.plugin.Page;
import com.xiakee.dao.material.MaterialMapper;
import com.xiakee.domain.ecgoods.EcProducts;
import com.xiakee.domain.material.Material;
import com.xiakee.domain.urm.SecurityUser;
import com.xiakee.domain.utils.Constants;
import com.xiakee.ecdao.order.EcProductsDao;

@Controller
public class MaterialController extends BaseController {

	@Autowired
	private MaterialMapper materialMapper;
	@Autowired
	private EcProductsDao ecProductsDao;

	@RequestMapping("material")
	public String materialList(HttpServletRequest request) {
		int materialStatus = ServletRequestUtils.getIntParameter(request, "materialStatus", 1);
		int currPage = ServletRequestUtils.getIntParameter(request, "currPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 15);
		Map<String, Object> param = new HashMap<String, Object>();
		Page page = buildPage(currPage, pageSize);
		param.put(Constants.PAGE, page);
		param.put("materialStatus", materialStatus);
		List<Material> materialList = materialMapper.selectByStatus_page(param);
		request.setAttribute("materialList", materialList);
		request.setAttribute("page", page);
		request.setAttribute("materialStatus", materialStatus);
		return "material/list";
	}

	@RequestMapping("toAddMaterial")
	public String toAddMaterial() {
		return "material/add";
	}

	/**
	 * @param material
	 * @return
	 */
	@RequestMapping("addMaterial")
	public String addMaterial(Material material) {
		SecurityUser user = getUser();
		if (user == null) {
			return null;
		}
		material.setLastUpdateUser(user.getName());
		materialMapper.insert(material);
		return "redirect:material.do";
	}

	@RequestMapping("toUpdateMaterial")
	public String toUpdatematerial(@RequestParam Long materialId, HttpServletRequest request) {
		Material material = materialMapper.selectByPrimaryKey(materialId);
		request.setAttribute("material", material);
		return "material/update";
	}

	@RequestMapping("updateMaterial")
	public String toUpdatematerial(Material material, HttpServletRequest request) {
		SecurityUser user = getUser();
		if (user == null) {
			return null;
		}
		material.setLastUpdateUser(user.getName());
		materialMapper.updateByPrimaryKeySelective(material);
		return "redirect:material.do";
	}

	@ResponseBody
	@RequestMapping("getPrice")
	public String getPrice(@RequestParam String url) {
		Map<String, Object> priceMap = new HashMap<String, Object>();
		priceMap.put("result", 0);
		Pattern p = Pattern.compile("product-\\d+.html");
		Matcher m = p.matcher(url);
		if (m.find()) {
			String productId = m.group(0).replace("product-", "").replace(".html", "");
			EcProducts products = ecProductsDao.selectByPrimaryKey(Integer.valueOf(productId));
			if (products != null) {
				priceMap.put("result", 1);
				priceMap.put("price", products.getPrice().floatValue());
				priceMap.put("mktprice", products.getMktprice().floatValue());
			}
		}
		return JSON.toJSONString(priceMap);
	}

}
