package com.xiakee.view.sku;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiakee.dao.sku.ExchangeRateDao;
import com.xiakee.domain.sku.ExchangeRate;

@Controller
public class SkuExchangeRateController {

	@Autowired
	private ExchangeRateDao exchangeRateDao;

	@RequestMapping("editExchangeRate")
	public String editExchangeRate(Model model) {
		List<ExchangeRate> exchangeRates = exchangeRateDao.selectByAll();
		model.addAttribute("exchangeRates", exchangeRates);

		return "sku/exchangeRates";
	}

	@RequestMapping("saveExchangeRate")
	public String saveExchangeRate(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, String[]> map = request.getParameterMap();
		for (Object key : map.keySet()) {
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setCurrency(String.valueOf(key));
			exchangeRate.setValue(new BigDecimal(map.get(key)[0]));
			exchangeRateDao.updateByPrimaryKeySelective(exchangeRate);
		}
		return "redirect:editExchangeRate.do";
	}

}
