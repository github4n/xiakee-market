package com.xiakee.service.sku;

import java.util.List;

import com.xiakee.domain.sku.SkuTypeBean;

public interface SkuTypeService {
	
	SkuTypeBean findSkuTypeById(Long typeId);
	
	List<SkuTypeBean> getAllTypeBeans();

	Integer updateTypeCode(SkuTypeBean bean);

	SkuTypeBean findByTypeCode(String typeCode);
}
