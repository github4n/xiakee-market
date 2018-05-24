package com.xiakee.service.sku.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiakee.dao.sku.SkuBrandDao;
import com.xiakee.dao.sku.SkuCatalogDao;
import com.xiakee.dao.sku.SkuTypeDao;
import com.xiakee.domain.sku.SkuBrandBean;
import com.xiakee.domain.sku.SkuCatalogBean;
import com.xiakee.domain.sku.SkuTypeBean;
import com.xiakee.service.sku.SynchSkuDataService;
import com.xiakee.service.utils.EcstoreApiUtil;

@Service
public class SynchSkuDataServiceImpl implements SynchSkuDataService {
	private static Logger log = Logger.getLogger(SynchSkuDataServiceImpl.class);

	@Autowired
	private SkuCatalogDao catalogDao;

	@Autowired
	private SkuTypeDao typeDao;

	@Autowired
	private SkuBrandDao skuBrandDao;

	@Override
	public void synchCatalogTypeData() {
		// Catalog
		synchCatalog("0");
		// Type
		List<Long> typeIds = catalogDao.getAllDistinctTypeIds();
		if (typeIds != null) {
			for (Long typeId : typeIds) {
				SkuTypeBean bean = EcstoreApiUtil.getTypeApiJson(String.valueOf(typeId));
				if (bean != null) {
					SkuTypeBean oBean = typeDao.findSkuTypeById(typeId);
					if (oBean == null) {
						typeDao.addSkuTypeBean(bean);
						log.info("商品类型为空，执行插入操作：" + bean);
					} else {
						typeDao.updateSkuTypeBean(bean);
					}
				}
			}
		}

		// Brand
		List<SkuBrandBean> brandList = EcstoreApiUtil.getBrandApiJson("0");
		if (brandList != null) {
			for (SkuBrandBean skuBrandBean : brandList) {
				SkuBrandBean oSkuBrandBean = skuBrandDao.findSkuBrandById(skuBrandBean.getId());
				if (oSkuBrandBean == null) {
					skuBrandDao.addSkuBrandBean(skuBrandBean);
					log.info("品牌信息不存在，执行添加操作" + skuBrandDao);
				} else {
					skuBrandDao.updateSkuBrandBean(skuBrandBean);
				}
			}
		}
	}

	/**
	 * 同步分类
	 * 
	 * @param parentId
	 */
	public void synchCatalog(String parentId) {
		List<SkuCatalogBean> beans = EcstoreApiUtil.getCatalogApiJson(parentId);
		if (beans != null) {
			for (SkuCatalogBean bean : beans) {
				SkuCatalogBean oBean = catalogDao.findSkuCatalogById(bean.getCat_id());
				if (oBean == null) {
					catalogDao.addSkuCatalog(bean);
				} else {
					catalogDao.updateSkuCatalog(bean);
				}
				synchCatalog(String.valueOf(bean.getCat_id()));
			}
		}
	}
}
