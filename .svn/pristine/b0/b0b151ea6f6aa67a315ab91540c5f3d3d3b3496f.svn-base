package com.xiakee.service.sku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.dao.sku.SkuCatalogDao;
import com.xiakee.domain.sku.SkuCatalogBean;
import com.xiakee.service.sku.SkuCatalogService;

@Service
public class SkuCatalogServiceImpl implements SkuCatalogService {

	@Autowired
	private SkuCatalogDao catalogDao;
	
	@Override
	public SkuCatalogBean findSkuCatalogById(Long catId) {
		return catalogDao.findSkuCatalogById(catId);
	}

	@Override
	public List<SkuCatalogBean> getAllSkuCatalogBeans() {
		return catalogDao.getAllCatalogBeans();
	}

	@Override
	public Integer updateCatalogCode(SkuCatalogBean bean) {
		return catalogDao.updateCatalogCode(bean);
	}

	@Override
	public SkuCatalogBean findByCatalogCode(String catalogCode) {
		return catalogDao.findByCatalogCode(catalogCode);
	}

	@Override
	public List<SkuCatalogBean> getCatalogByParentId(Integer parent_id) {
		return catalogDao.getCatalogByParentId(parent_id);
	}

}
