package com.xiakee.service.sku.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.xiakee.crawler.bean.CrawlerGoodsPrice;
import com.xiakee.crawler.bean.CrawlerImages;
import com.xiakee.dao.sku.SkuGoodsDao;
import com.xiakee.dao.sku.SkuGoodsNoDao;
import com.xiakee.dao.sku.SkuManagerDao;
import com.xiakee.domain.ecgoods.EcGoods;
import com.xiakee.domain.ecgoods.EcGoodsKeywords;
import com.xiakee.domain.ecgoods.EcGoodsSpecIndex;
import com.xiakee.domain.ecgoods.EcImage;
import com.xiakee.domain.ecgoods.EcImageAttach;
import com.xiakee.domain.ecgoods.EcProducts;
import com.xiakee.domain.ecgoods.EcSpecValues;
import com.xiakee.domain.ecgoods.EcSpecification;
import com.xiakee.domain.sku.SkuBrandBean;
import com.xiakee.domain.sku.SkuCrawlerImageBean;
import com.xiakee.domain.sku.SkuGoodsBean;
import com.xiakee.domain.sku.SkuGoodsNoBean;
import com.xiakee.domain.sku.SkuManagerBean;
import com.xiakee.ecdao.order.EcGoodsDao;
import com.xiakee.ecdao.order.EcGoodsKeywordsDao;
import com.xiakee.ecdao.order.EcGoodsSpecIndexDao;
import com.xiakee.ecdao.order.EcImageAttachDao;
import com.xiakee.ecdao.order.EcImageDao;
import com.xiakee.ecdao.order.EcProductsDao;
import com.xiakee.ecdao.order.EcSpecValuesDao;
import com.xiakee.ecdao.order.EcSpecificationDao;
import com.xiakee.service.sku.ExchangeRateService;
import com.xiakee.service.sku.SkuBrandService;
import com.xiakee.service.sku.SkuCrawlerService;
import com.xiakee.service.sku.SkuImportShopService;
import com.xiakee.service.utils.MD5Util;
import com.xiakee.service.utils.php.PHPSerializer;

@Service
public class SkuImportShopServiceImpl implements SkuImportShopService {

	private final static String HTTP = "http://";
	private final static String IMGPATH = "img.xiakee.com";
	private final static int[] priceSuffix = { 6, 8, 9 };
	@Autowired
	private SkuManagerDao managerDao;
	@Autowired
	private SkuGoodsNoDao goodsNoDao;
	@Autowired
	private SkuGoodsDao skuGoodsDao;
	@Autowired
	private SkuBrandService brandService;
	@Autowired
	private SkuCrawlerService skuCrawlerService;
	@Autowired
	private ExchangeRateService exchangeRateService;
	@Autowired
	private EcGoodsDao ecGoodsDao;
	@Autowired
	private EcImageDao imageDao;
	@Autowired
	private EcImageAttachDao imageAttachDao;
	@Autowired
	private EcSpecValuesDao specValuesDao;
	@Autowired
	private EcSpecificationDao specificationDao;
	@Autowired
	private EcProductsDao productsDao;
	@Autowired
	private EcGoodsSpecIndexDao goodsSpecIndexDao;
	@Autowired
	private EcGoodsKeywordsDao goodsKeywordsDao;

	@Override
	@Transactional
	public void importData(SkuManagerBean bean) throws Exception {
		long time = System.currentTimeMillis();
		// 检查是否已经导入
		EcGoods goods = ecGoodsDao.selectBySkuCode(bean.getSkuCode());
		if (goods == null) {
			// 之前未导入
			goods = new EcGoods();
			goods.setName(bean.getZhName());
			goods.setBrandId(Integer.valueOf(String.valueOf(bean.getBrand())));
			goods.setCatId(Integer.valueOf(String.valueOf(bean.getClassify())));
			goods.setTypeId(Integer.valueOf(String.valueOf(bean.getTypes())));
			goods.setWeight(new BigDecimal(bean.getWeight()));
			goods.setMarketable("false");
			goods.setStorePrompt(1);
			goods.setP50(bean.getSkuCode());
			SkuBrandBean brand = brandService.findSkuBrandById(bean.getBrand());
			StringBuffer sb = new StringBuffer();
			sb.append("<img src=\"http://file.xiakee.com/img/temp/dropship.jpg?1433323093#w\"><div><img src=\"http://file.xiakee.com/img/temp/productinfo.jpg?1433323165#w\"></div><div><ul><li><font face=\"Microsoft Yahei\" size=\"2\">品牌：");
			if (brand != null) {
				sb.append(brand.getName());
			}
			sb.append("</font></li> <li><font face=\"Microsoft Yahei\" size=\"2\">材质：</font></li><li><font face=\"Microsoft Yahei\" size=\"2\">适用场景：</font></li> </ul></div><p></p><p></p><div><img src=\"http://file.xiakee.com/img/temp/advantage.jpg?1433323133#h\"></div>");
			goods.setIntro(sb.toString());
			ecGoodsDao.insertSelective(goods);
			if (StringUtils.isNotBlank(bean.getKeyword())) {
				for (String keyword : bean.getKeyword().split("\\|")) {
					if (StringUtils.isNotBlank(keyword)) {
						EcGoodsKeywords keywords = new EcGoodsKeywords();
						keywords.setGoodsId(goods.getGoodsId());
						keywords.setKeyword(keyword);
						keywords.setLastModify(Integer.valueOf(String.valueOf(time / 1000)));
						try {
							goodsKeywordsDao.insertSelective(keywords);
						} catch (Exception e) {

						}
					}
				}
			}
		}
		// 图片
		Map<String, List<String>> imageMap = getImageBySkuCode(bean.getSkuCode(), goods.getGoodsId(), time);
		// 默认图片
		if (imageMap.size() > 0 && StringUtils.isEmpty(goods.getImageDefaultId())) {
			goods.setImageDefaultId(imageMap.get(bean.getSkuCode() + "-01").get(0));
		}
		// 获取该类型对应的规格
		List<EcSpecification> specList = specificationDao.selectByType(goods.getTypeId());
		// 颜色
		EcSpecification colorSpecification = null;
		// 尺寸
		EcSpecification sizeSpecification = null;
		if (specList != null && specList.size() > 0) {
			for (EcSpecification ecSpecification : specList) {
				if ("颜色".equals(ecSpecification.getSpecName().trim())) {
					colorSpecification = ecSpecification;
				} else {
					sizeSpecification = ecSpecification;
				}
			}
		}
		List<EcGoodsSpecIndex> goodsSpecIndexList = new ArrayList<EcGoodsSpecIndex>();

		Map<Integer, Object> descSpecMap = new LinkedHashMap<Integer, Object>();
		Map<String, Object> colorSpecMap = new LinkedHashMap<String, Object>();
		Map<String, Object> sizeSpecMap = new LinkedHashMap<String, Object>();

		List<SkuGoodsBean> beans = skuGoodsDao.selectBySkuCode(bean.getSkuCode());

		// 存储规格值ID
		Map<String, Integer> colorSpecValueIdMap = new LinkedHashMap<String, Integer>();
		Map<String, String> colorSpecValueNameMap = new LinkedHashMap<String, String>();
		Map<String, Integer> sizeSpecValueIdMap = new LinkedHashMap<String, Integer>();
		int index = 1;// 颜色号
		int priceSuffixI = priceSuffix[new Random().nextInt(3)];
		int mallId = skuCrawlerService.getMallById(bean.getSkuCode());
		Map<String, Object[]> priceMap = getPriceInfo(bean.getSkuCode(), mallId);
		Integer minPriceProductId = null;
		Float minPrice = null;
		for (int i = 0; i < beans.size(); i++) {
			SkuGoodsBean goodsBean = beans.get(i);
			// 检测货品是否存在
			EcProducts products = productsDao.selectByBn(goodsBean.getGoodsNo());
			// 价格
			float price = 0;
			// 库存
			int store = 0;
			if (products == null) {
				// 不存在
				Object[] priceObj = priceMap.get(goodsBean.getGoodsNo());
				price = Float.parseFloat(String.valueOf(priceObj[0]));
				store = Integer.parseInt(String.valueOf(priceObj[1]));
				store = store > 100 ? 100 : store;
			}
			// 货品图片
			List<String> imageList = imageMap.get(goodsBean.getGoodsNo());
			// 颜色和尺寸
			Map<Object, Object> specPricateValueIdMap = new LinkedHashMap<Object, Object>();
			Map<Object, Object> specValueMap = new LinkedHashMap<Object, Object>();
			Map<Object, Object> specValueIdMap = new LinkedHashMap<Object, Object>();

			String specInfo = null;
			String colorValue = goodsBean.getColor();
			String sizeValue = goodsBean.getSize();

			EcGoodsSpecIndex colorGoodsSpecIndex = null;
			EcGoodsSpecIndex sizeGoodsSpecIndex = null;

			if (StringUtils.isNotBlank(colorValue)) {
				if (colorSpecification == null) {
					throw new Exception("该类型没有对应的颜色规格！");
				} else {
					String newColorValue = colorSpecValueNameMap.get(colorValue);
					Integer specValueId = colorSpecValueIdMap.get(colorValue);
					if (newColorValue == null) {
						newColorValue = index + "号色";
						specValueId = getIdBySpecValue(newColorValue, colorSpecification.getSpecId());
						colorSpecValueIdMap.put(colorValue, specValueId);
						colorSpecValueNameMap.put(colorValue, newColorValue);
						index++;
					}
					String private_spec_value_id = "" + time / 1000 + specValueId;
					specPricateValueIdMap.put(colorSpecification.getSpecId(), private_spec_value_id);
					specValueMap.put(colorSpecification.getSpecId(), newColorValue);
					specValueIdMap.put(colorSpecification.getSpecId(), specValueId);
					specInfo = colorSpecification.getSpecName() + "：" + newColorValue;

					colorGoodsSpecIndex = new EcGoodsSpecIndex();
					colorGoodsSpecIndex.setTypeId(goods.getTypeId());
					colorGoodsSpecIndex.setSpecId(colorSpecification.getSpecId());
					colorGoodsSpecIndex.setSpecValueId(specValueId);
					colorGoodsSpecIndex.setGoodsId(goods.getGoodsId());
					colorGoodsSpecIndex.setLastModify(Integer.valueOf(String.valueOf(time / 1000)));

					Map<String, Object> colorMap = new LinkedHashMap<String, Object>();
					colorMap.put("private_spec_value_id", private_spec_value_id);
					colorMap.put("spec_value", newColorValue);
					colorMap.put("spec_value_id", specValueId);
					if (imageList != null && imageList.size() > 0) {
						colorMap.put("spec_image", imageList.get(0));
						String spec_goods_images = "";
						for (String imageId : imageList) {
							spec_goods_images += imageId + ",";
						}
						colorMap.put("spec_goods_images", spec_goods_images.substring(0, spec_goods_images.length() - 1));
					}
					colorSpecMap.put(private_spec_value_id, colorMap);
				}
			}
			if (StringUtils.isNotBlank(sizeValue)) {
				if (sizeSpecification == null) {
					throw new Exception("该类型没有对应的大小规格！");
				} else {
					Integer specValueId = sizeSpecValueIdMap.get(sizeValue);
					if (specValueId == null) {
						specValueId = getIdBySpecValue(sizeValue, sizeSpecification.getSpecId());
						sizeSpecValueIdMap.put(sizeValue, specValueId);
					}

					String private_spec_value_id = "" + time / 1000 + specValueId;
					specPricateValueIdMap.put(sizeSpecification.getSpecId(), private_spec_value_id);
					specValueMap.put(sizeSpecification.getSpecId(), sizeValue);
					specValueIdMap.put(sizeSpecification.getSpecId(), specValueId);
					if (specInfo == null) {
						specInfo = sizeSpecification.getSpecName() + "：" + sizeValue;
					} else {
						specInfo = specInfo + "、" + sizeSpecification.getSpecName() + "：" + sizeValue;
					}

					sizeGoodsSpecIndex = new EcGoodsSpecIndex();
					sizeGoodsSpecIndex.setTypeId(goods.getTypeId());
					sizeGoodsSpecIndex.setSpecId(sizeSpecification.getSpecId());
					sizeGoodsSpecIndex.setSpecValueId(specValueId);
					sizeGoodsSpecIndex.setGoodsId(goods.getGoodsId());
					sizeGoodsSpecIndex.setLastModify(Integer.valueOf(String.valueOf(time / 1000)));

					Map<String, Object> sizeMap = new LinkedHashMap<String, Object>();
					sizeMap.put("private_spec_value_id", private_spec_value_id);
					sizeMap.put("spec_value", sizeValue);
					sizeMap.put("spec_value_id", specValueId);
					sizeMap.put("spec_goods_images", "");
					sizeSpecMap.put(private_spec_value_id, sizeMap);
				}
			}

			if (products == null) {
				// 不存在
				products = new EcProducts();
				products.setGoodsId(goods.getGoodsId());
				products.setBn(goodsBean.getGoodsNo());
				products.setName(goods.getName());
				products.setGoodsType("normal");
				products.setSpecInfo(specInfo);
				if (store > 0 && price > 0) {
					products.setMarketable("true");
				} else {
					products.setMarketable("false");
				}
				products.setDisabled("false");
				products.setLastModify(Integer.valueOf(String.valueOf(time / 1000)));
				// 价格库存
				if (price > 0) {
					products.setPrice(new BigDecimal(((int) (price * (bean.getGrossProfitMargin().floatValue() + 1)) / 10 * 10) + priceSuffixI));
					products.setCost(new BigDecimal(price));
					products.setMktprice(new BigDecimal((int) (price * 1.8) / 10 * 10 + priceSuffixI));
				} else {
					products.setPrice(new BigDecimal(0));
					products.setCost(new BigDecimal(0));
					products.setMktprice(new BigDecimal(0));
				}
				products.setStore(store);
				products.setWeight(new BigDecimal(bean.getWeight()));

				if (specInfo != null) {
					// produces spec_desc
					Map<String, Object> specDescMap = new LinkedHashMap<String, Object>();
					specDescMap.put("spec_private_value_id", specPricateValueIdMap);
					specDescMap.put("spec_value", specValueMap);
					specDescMap.put("spec_value_id", specValueIdMap);
					String specDesc = new String(PHPSerializer.serialize(specDescMap));
					products.setSpecDesc(specDesc);
				}

				productsDao.insertSelective(products);

				if (colorGoodsSpecIndex != null) {
					colorGoodsSpecIndex.setProductId(products.getProductId());
					goodsSpecIndexList.add(colorGoodsSpecIndex);
				}

				if (sizeGoodsSpecIndex != null) {
					sizeGoodsSpecIndex.setProductId(products.getProductId());
					goodsSpecIndexList.add(sizeGoodsSpecIndex);
				}

				if (i == 0) {
					if (price > 0) {
						goods.setPrice(new BigDecimal(((int) (price * (bean.getGrossProfitMargin().floatValue() + 1)) / 10 * 10) + priceSuffixI));
						goods.setCost(new BigDecimal(price));
						goods.setMktprice(new BigDecimal((int) (price * 1.8) / 10 * 10 + priceSuffixI));
					} else {
						goods.setPrice(new BigDecimal(0));
						goods.setCost(new BigDecimal(0));
						goods.setMktprice(new BigDecimal(0));
					}
					goods.setStore(store);
				}
			} else {
				// produces spec_desc
				Map<String, Object> specDescMap = new LinkedHashMap<String, Object>();
				specDescMap.put("spec_private_value_id", specPricateValueIdMap);
				specDescMap.put("spec_value", specValueMap);
				specDescMap.put("spec_value_id", specValueIdMap);
				String specDesc = new String(PHPSerializer.serialize(specDescMap));
				EcProducts productsUpdate = new EcProducts();
				productsUpdate.setProductId(products.getProductId());
				productsUpdate.setSpecDesc(specDesc);
				products.setIsDefault("false");
				productsDao.updateByPrimaryKeySelective(productsUpdate);
			}
			if (products.getPrice().floatValue() > 0 && products.getStore() > 0) {
				if (minPriceProductId == null) {
					minPriceProductId = products.getProductId();
					minPrice = products.getPrice().floatValue();
				} else {
					if (products.getPrice().floatValue() < minPrice) {
						minPriceProductId = products.getProductId();
						minPrice = products.getPrice().floatValue();
					}
				}
			}
		}

		if (minPriceProductId != null) {
			EcProducts productsUpdate = new EcProducts();
			productsUpdate.setProductId(minPriceProductId);
			productsUpdate.setIsDefault("true");
			productsDao.updateByPrimaryKeySelective(productsUpdate);
		}

		if (goodsSpecIndexList.size() > 0) {
			goodsSpecIndexDao.insertBacth(goodsSpecIndexList);
		}

		if (colorSpecMap.size() > 0) {
			descSpecMap.put(colorSpecification.getSpecId(), colorSpecMap);
		}

		if (sizeSpecMap.size() > 0) {
			descSpecMap.put(sizeSpecification.getSpecId(), sizeSpecMap);
		}

		String specDesc = null;
		if (descSpecMap.size() > 0) {
			specDesc = new String(PHPSerializer.serialize(descSpecMap));
			goods.setSpecDesc(specDesc);
		}
		ecGoodsDao.updateByPrimaryKeySelective(goods);
		bean.setIsImport(1);
		managerDao.updateIsImport(bean.getId());
	}

	/**
	 * 图片录入
	 * 
	 * @param skuCode
	 * @param goodsid
	 * @return
	 */
	private Map<String, List<String>> getImageBySkuCode(String skuCode, Long goodsid, Long time) {
		String imageMapStr = skuCrawlerService.getImageBySkuCode(skuCode);
		SkuCrawlerImageBean crawlerImageBean = JSON.parseObject(imageMapStr, SkuCrawlerImageBean.class);
		Map<String, List<String>> imageMap = new LinkedHashMap<String, List<String>>();
		if (crawlerImageBean != null && crawlerImageBean.getImageMap() != null) {
			for (Long key : crawlerImageBean.getImageMap().keySet()) {
				String goodsNo = null;
				List<SkuGoodsNoBean> goodsNoBeans = goodsNoDao.selectByCrawlerGoodsId(key);
				if (goodsNoBeans != null && goodsNoBeans.size() > 0) {
					goodsNo = goodsNoBeans.get(0).getGoodsno();
				}
				List<CrawlerImages> crawlerImageList = crawlerImageBean.getImageMap().get(key);
				if (crawlerImageList != null && crawlerImageList.size() > 0) {
					List<String> imageIdList = new ArrayList<String>();
					for (CrawlerImages crawlerImages : crawlerImageList) {
						// 检查图片是否存在
						List<EcImage> hasImgList = imageDao.selectByIdent(HTTP + IMGPATH + crawlerImages.getLocalUrl());
						if (hasImgList == null || hasImgList.size() <= 0) {
							// 不存在
							EcImage image = new EcImage();
							image.setImageId(MD5Util.getMD5Str("" + new Random().nextInt(1000) + System.currentTimeMillis()));
							image.setStorage("network");
							image.setIdent(HTTP + IMGPATH + crawlerImages.getLocalUrl());
							image.setUrl(HTTP + IMGPATH + crawlerImages.getLocalUrl());
							image.setlIdent(HTTP + IMGPATH + "/i/600-600-1/" + IMGPATH + crawlerImages.getLocalUrl());
							image.setlUrl(HTTP + IMGPATH + "/i/600-600-1/" + IMGPATH + crawlerImages.getLocalUrl());
							image.setmIdent(HTTP + IMGPATH + "/i/500-500-1/" + IMGPATH + crawlerImages.getLocalUrl());
							image.setmUrl(HTTP + IMGPATH + "/i/500-500-1/" + IMGPATH + crawlerImages.getLocalUrl());
							image.setsIdent(HTTP + IMGPATH + "/i/220-220-1/" + IMGPATH + crawlerImages.getLocalUrl());
							image.setsUrl(HTTP + IMGPATH + "/i/220-220-1/" + IMGPATH + crawlerImages.getLocalUrl());
							image.setWatermark("false");
							image.setLastModified(Integer.valueOf(String.valueOf(time / 1000)));
							imageIdList.add(image.getImageId());
							try {
								imageDao.insert(image);
								EcImageAttach imageAttach = new EcImageAttach();
								imageAttach.setImageId(image.getImageId());
								imageAttach.setTargetId(goodsid);
								imageAttach.setTargetType("goods");
								imageAttach.setLastModified(image.getLastModified());
								imageAttachDao.insert(imageAttach);
							} catch (Exception e) {
								hasImgList = imageDao.selectByIdent(HTTP + IMGPATH + crawlerImages.getLocalUrl());
							}
						} else {
							// 已存在
							imageIdList.add(hasImgList.get(0).getImageId());
							EcImageAttach imageAttach = imageAttachDao.selectByImageId(hasImgList.get(0).getImageId());
							if (imageAttach == null) {
								imageAttach = new EcImageAttach();
								imageAttach.setImageId(hasImgList.get(0).getImageId());
								imageAttach.setTargetId(goodsid);
								imageAttach.setTargetType("goods");
								imageAttach.setLastModified(Integer.valueOf(String.valueOf(time / 1000)));
								imageAttachDao.insert(imageAttach);
							} else {
								if (goodsid != imageAttach.getTargetId()) {
									imageAttach.setTargetId(goodsid);
									imageAttachDao.updateByPrimaryKeySelective(imageAttach);
								}
							}
						}
					}
					imageMap.put(goodsNo, imageIdList);
				}
			}
		}
		return imageMap;
	}

	/**
	 * 获取规格值Id
	 * 
	 * @param specValue
	 * @param specId
	 * @return
	 */
	private synchronized int getIdBySpecValue(String specValue, Integer specId) {
		int specValueId = 0;
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("specId", specId);
		params.put("specValue", specValue);
		List<EcSpecValues> specValueList = specValuesDao.selectBySpecValueAndType(params);
		if (specValueList == null || specValueList.size() <= 0) {
			// 没有当前规格值
			EcSpecValues specValues = new EcSpecValues();
			specValues.setSpecId(specId);
			specValues.setSpecValue(specValue);
			specValues.setpOrder(specValuesDao.selectMaxOrder() + 1);
			specValuesDao.insertSelective(specValues);
			return specValues.getSpecValueId();
		} else {
			specValueId = specValueList.get(0).getSpecValueId();
		}
		return specValueId;
	}

	/**
	 * 获取价格库存信息
	 * 
	 * @param skuCode
	 * @param mallId
	 * @return
	 */
	private Map<String, Object[]> getPriceInfo(String skuCode, int mallId) {
		// 汇率
		Map<String, Object[]> priceAndStoreMap = new LinkedHashMap<String, Object[]>();
		BigDecimal exchangeRate = exchangeRateService.getExchangeRate(mallId);
		String priceStr = skuCrawlerService.getPriceBySkuCode(skuCode, 0);
		@SuppressWarnings("unchecked")
		Map<String, String> map = JSON.parseObject(priceStr, Map.class);
		List<CrawlerGoodsPrice> priceList = JSON.parseArray(String.valueOf(map.get(String.valueOf(mallId))), CrawlerGoodsPrice.class);
		for (CrawlerGoodsPrice goodsPrice : priceList) {
			List<SkuGoodsNoBean> goodsNoList = goodsNoDao.selectByCrawlerGoodsId(goodsPrice.getGoodsId());
			if (goodsNoList != null) {
				float price = 0;
				int store = 0;
				try {
					price = Float.parseFloat(goodsPrice.getPrice().replaceAll("[^0-9|.]", "")) * exchangeRate.floatValue();
					if (goodsPrice.getStock() != null) {
						String storeStr = goodsPrice.getStock().replaceAll("[^0-9]", "").trim();
						if (StringUtils.isNotEmpty(storeStr)) {
							store = Integer.valueOf(storeStr);
						} else {
							if (goodsPrice.getStock().toLowerCase().contains("in stock")) {
								store = 100;
							}
						}
					}
				} catch (Exception e) {
				}
				Object[] priceAndStore = new Object[2];
				priceAndStore[0] = price;
				priceAndStore[1] = store;
				priceAndStoreMap.put(goodsNoList.get(0).getGoodsno(), priceAndStore);
			}
		}
		return priceAndStoreMap;
	}

}
