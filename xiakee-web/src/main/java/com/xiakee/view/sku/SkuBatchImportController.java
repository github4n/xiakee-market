package com.xiakee.view.sku;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.xiakee.dao.sku.SkuGrossProfitDao;
import com.xiakee.domain.sku.SkuGrossProfit;
import com.xiakee.domain.sku.SkuManagerBean;
import com.xiakee.domain.urm.SecurityUser;
import com.xiakee.service.sku.SkuBatchImportService;
import com.xiakee.service.utils.ExcelOperaterUtil;

@Controller
public class SkuBatchImportController extends BaseController {

	private final static Map<String, Integer> grossMap = new HashMap<String, Integer>();
	static {
		grossMap.put("A", 1);
		grossMap.put("B", 2);
		grossMap.put("C", 3);
		grossMap.put("D", 4);
		grossMap.put("E", 5);
		grossMap.put("F", 6);
		grossMap.put("G", 7);
		grossMap.put("H", 8);
	}

	@Autowired
	private SkuBatchImportService skuBatchImportService;
	@Autowired
	private SkuGrossProfitDao grossProfitDao;

	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String uploadFileBefore() {
		return "sku/upload";
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(HttpServletRequest request) {
		SecurityUser user = getUser();
		CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		boolean multipart = resolver.isMultipart(request);
		if (multipart) {
			resolver.setDefaultEncoding("UTF-8");
			InputStream is = null;
			OutputStream os = null;
			try {
				MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
				MultipartFile file = multipartRequest.getFile("Filedata");
				List<List<String>> results = ExcelOperaterUtil.read(file.getInputStream());
				List<SkuManagerBean> beanList = new ArrayList<SkuManagerBean>();
				if (results != null && results.size() > 0) {
					for (int i = 1; i < results.size(); i++) {
						List<String> dataList = results.get(i);
						if (StringUtils.isNotBlank(dataList.get(0)) && StringUtils.isNotBlank(dataList.get(0)) && StringUtils.isNotBlank(dataList.get(0))) {
							SkuManagerBean bean = new SkuManagerBean();
							bean.setEnName(dataList.get(0));
							bean.setZhName(dataList.get(1));
							bean.setMainUrl(dataList.get(2));
							bean.setKeyword(dataList.get(3));
							bean.setClassify(new BigDecimal(dataList.get(4)).longValue());
							bean.setTypes(new BigDecimal(dataList.get(5)).longValue());
							bean.setWeight(new BigDecimal(dataList.get(6)).intValue());
							bean.setBrand(new BigDecimal(dataList.get(7)).longValue());
							bean.setPattern("1");
							Integer grossId = grossMap.get(dataList.get(8));
							if (grossId != null) {
								bean.setGrossId(grossId);
								SkuGrossProfit profit = grossProfitDao.selectByPrimaryKey(grossId);
								if (profit != null) {
									bean.setGrossProfitMargin(new BigDecimal(profit.getValue()));
								}
							}
							bean.setUserId(user.getId());
							if (dataList.size() > 9) {
								List<String> urls = new ArrayList<String>();
								for (int j = 9; j < dataList.size(); j++) {
									if (StringUtils.isNotBlank(dataList.get(j))) {
										urls.add(dataList.get(j));
									}
								}
								bean.setUrls(urls);
							}
							beanList.add(bean);
						}
					}
					skuBatchImportService.batchImport(beanList, user.getId());
				}
				return "1";
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "0";
	}

	@RequestMapping(value = "surplusQueue")
	@ResponseBody
	public String getSurplusQueue(HttpServletRequest request, HttpServletResponse response) {
		SecurityUser user = getUser();
		int count = 0;
		if (user != null) {
			count = skuBatchImportService.getSurplusCount(user.getId());
		}
		return String.valueOf(count);
	}
}
