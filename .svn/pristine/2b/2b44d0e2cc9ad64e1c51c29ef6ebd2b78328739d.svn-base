package com.xiakee.service.sku;

import java.util.List;

import com.xiakee.domain.sku.SkuCatalogBean;

public interface SkuCatalogService {
	
	SkuCatalogBean findSkuCatalogById(Long catId);
	
	List<SkuCatalogBean> getAllSkuCatalogBeans();

	Integer updateCatalogCode(SkuCatalogBean bean);
	
	SkuCatalogBean findByCatalogCode(String catalogCode);
	
	List<SkuCatalogBean> getCatalogByParentId(Integer parent_id);
}
