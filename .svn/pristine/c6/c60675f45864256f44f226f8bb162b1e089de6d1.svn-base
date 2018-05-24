package com.xiakee.service.sku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.dao.sku.SkuTypeDao;
import com.xiakee.domain.sku.SkuTypeBean;
import com.xiakee.service.sku.SkuTypeService;

@Service
public class SkuTypeServiceImpl implements SkuTypeService {

	@Autowired
	private SkuTypeDao typeDao;

	@Override
	public List<SkuTypeBean> getAllTypeBeans() {
		return typeDao.getAllTypeBeans();
	}

	@Override
	public Integer updateTypeCode(SkuTypeBean bean) {
		return typeDao.updateTypeCode(bean);
	}

	@Override
	public SkuTypeBean findByTypeCode(String typeCode) {
		return typeDao.findByTypeCode(typeCode);
	}

	@Override
	public SkuTypeBean findSkuTypeById(Long typeId) {
		return typeDao.findSkuTypeById(typeId);
	}

}
