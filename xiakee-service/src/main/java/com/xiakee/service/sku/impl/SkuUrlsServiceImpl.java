package com.xiakee.service.sku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.dao.sku.SkuUrlsDao;
import com.xiakee.domain.sku.SkuUrlsBean;
import com.xiakee.service.sku.SkuUrlsService;

@Service
public class SkuUrlsServiceImpl implements SkuUrlsService {

	@Autowired
	private SkuUrlsDao skuUrlsDao;

	@Override
	public Integer addSkuUrlsBean(SkuUrlsBean bean) {
		return skuUrlsDao.addSkuUrlsBean(bean);
	}

	@Override
	public Integer delSkuManagerBeanBySkuCode(String skuCode) {
		return skuUrlsDao.delSkuManagerBeanBySkuCode(skuCode);
	}

	@Override
	public List<SkuUrlsBean> getSkuUrlsBeanBySkuCode(String skuCode) {
		return skuUrlsDao.getSkuUrlsBeanBySkuCode(skuCode);
	}

}
