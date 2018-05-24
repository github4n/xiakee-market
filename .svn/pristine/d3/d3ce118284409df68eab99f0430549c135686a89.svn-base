package com.xiakee.service.sku;

import java.util.List;
import java.util.Map;

import com.xiakee.domain.sku.SkuManagerBean;

public interface SkuManagerService {

	public SkuManagerBean findSkuManagerBeanById(Long id);

	public Integer addSkuManagerBean(SkuManagerBean bean, String[] urls);

	public Integer updateSkuCode(SkuManagerBean bean);

	public Integer delSkuManagerBeanById(Long id);

	public List<SkuManagerBean> selectByBrandAndClassify_page(Map<String, Object> param);

	public SkuManagerBean findSkuManagerBeanBySkuCode(String skuCode);

	public List<SkuManagerBean> getSkuManagerByBrand(Long brandId);

	public void comb(String skuCode);
	
	public Integer updatePriceLockTime(SkuManagerBean bean);
	
	public String getSkuCodeByUrls(String urls);

}
