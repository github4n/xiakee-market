package com.xiakee.service.sku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.dao.sku.SkuBrandDao;
import com.xiakee.domain.sku.SkuBrandBean;
import com.xiakee.service.sku.SkuBrandService;

@Service
public class SkuBrandServiceImpl implements SkuBrandService {

	@Autowired
	private SkuBrandDao skuBrandDao;

	@Override
	public List<SkuBrandBean> getAllSkuBrandBean() {
		return skuBrandDao.getAllBrandBeans();
	}

	@Override
	public Integer updateBrandCode(SkuBrandBean bean) {
		return skuBrandDao.updateBrandCode(bean);
	}

	@Override
	public SkuBrandBean findByBrandCode(String brandCode) {
		return skuBrandDao.findByBrandCode(brandCode);
	}

	@Override
	public SkuBrandBean findSkuBrandById(Long id) {
		return skuBrandDao.findSkuBrandById(id);
	}

}
