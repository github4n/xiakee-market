package com.xiakee.service.logistics.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xiakee.bean.ConsigneeInfo;
import com.xiakee.bean.PiecesItem;
import com.xiakee.bean.UdfexDeclare;
import com.xiakee.dao.logistics.AbroadOrderDao;
import com.xiakee.dao.logistics.BoxnoDao;
import com.xiakee.dao.order.ErpOrderDao;
import com.xiakee.dao.order.PurchLogsDao;
import com.xiakee.dao.sku.SkuBrandDao;
import com.xiakee.dao.udfex.CodeBean;
import com.xiakee.dao.udfex.DistrictMapper;
import com.xiakee.dao.yz.YouzanOrderDao;
import com.xiakee.domain.ecgoods.EcGoods;
import com.xiakee.domain.ecgoods.EcProducts;
import com.xiakee.domain.logistics.AbroadOrderBean;
import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.logistics.DeclareGoodsBean;
import com.xiakee.domain.logistics.LogisticsBean;
import com.xiakee.domain.order.PurchLogsBean;
import com.xiakee.domain.sku.SkuBrandBean;
import com.xiakee.domain.utils.LogistNodeEnum;
import com.xiakee.domain.utils.TransferTypeEnum;
import com.xiakee.domain.yz.YzorderInfoBean;
import com.xiakee.ecdao.order.EcGoodsDao;
import com.xiakee.ecdao.order.EcProductsDao;
import com.xiakee.service.AutoExecuteTasker;
import com.xiakee.service.logistics.BaiweiDeclareListTasker;
import com.xiakee.service.logistics.BaiweiDeclareTasker;
import com.xiakee.service.logistics.BoxnoService;
import com.xiakee.service.smsc.OrderSmscNoticeService;

@Service
public class BoxnoServiceImpl implements BoxnoService {
	private static Logger log = Logger.getLogger(BoxnoServiceImpl.class);

	@Autowired
	private BoxnoDao boxnoDao;

	@Autowired
	private YouzanOrderDao orderDao;

	@Autowired
	private AbroadOrderDao abroadDao;

	@Autowired
	private OrderSmscNoticeService smscNoticeService;

	@Autowired
	private PurchLogsDao purchlogDao;

	@Autowired
	private DistrictMapper districtMapper;

	@Autowired
	private ErpOrderDao erpOrderDao;

	@Autowired
	private EcGoodsDao ecGoodsDao;

	@Autowired
	private EcProductsDao ecProductsDao;

	@Autowired
	private SkuBrandDao skuBrandDao;

	@Override
	public List<BoxnoBean> getAllNoExpressnoBean() {
		List<BoxnoBean> beans = boxnoDao.getAllNoExpressnoBean();
		for (BoxnoBean bean : beans) {
			YzorderInfoBean orderBean = orderDao.getYzorderNameById(bean.getInfoId());
			String title = orderBean.getTitle();
			if (StringUtils.isNotBlank(orderBean.getSku_properties_name())) {
				title += orderBean.getSku_properties_name();
			}
			bean.setTitle(title);
			bean.setName(orderBean.getName());

			List<PurchLogsBean> purchLogsBeans = purchlogDao.findPurchLogsByInfoId(bean.getInfoId());
			String content = "";
			if (purchLogsBeans != null) {
				for (PurchLogsBean logBean : purchLogsBeans) {
					if (StringUtils.isNotBlank(logBean.getContent())) {
						content += logBean.getCreated() + "【" + logBean.getName() + "】:" + logBean.getContent() + "<br>";
					}
				}
			}
			bean.setRemark(content);
		}
		return beans;
	}

	@Override
	public Integer updateExpressno(Long infoId, String expressno, String transfer_id) {
		Integer sum = 0;
		if (infoId != 0 && StringUtils.isNotBlank(expressno) && StringUtils.isNotBlank(transfer_id)) {
			BoxnoBean bean = new BoxnoBean();
			bean.setInfoId(infoId);
			bean.setExpressno(expressno);
			bean.setTransfer_id(Integer.parseInt(transfer_id));
			sum = boxnoDao.updateExpressno(bean);
			if (sum > 0) {
				LogisticsBean logistBean = new LogisticsBean();
				logistBean.setInfoId(infoId);
				logistBean.setLogistNode(LogistNodeEnum.EXPRESSNO);
				logistBean.setSmscType(null);// 该节点需要发送邮件
				this.smscNoticeService.sendOrderNotice(logistBean);
			} else {
				log.info("海外包裹ID更新失败======" + bean);
			}
		}
		return sum;
	}

	/**
	 * 获取所有尚未申报的信息列表
	 * 
	 * @Method getAllDeclareBean
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月22日 上午11:23:09
	 * @Version 1.0
	 */
	@Override
	public List<BoxnoBean> getAllDeclareBean(Map<String, Object> param) {
		List<BoxnoBean> beans = boxnoDao.getAllDeclareBean_page(param);
		if (beans != null) {
			for (BoxnoBean bean : beans) {
				String title = bean.getTitle();
				if (StringUtils.isNotBlank(bean.getSku_properties_name())) {
					title += bean.getSku_properties_name();
				}
				bean.setTitle(title);
				if (bean.getTransferId() > 0) {
					TransferTypeEnum transfer = TransferTypeEnum.enumValueOf(bean.getTransferId());
					
					if(transfer != null){
						bean.setTransfer(transfer.toDescription());
					}
				} else {
					bean.setTransfer("其它");
				}
				List<PurchLogsBean> purchLogsBeans = purchlogDao.findPurchLogsByInfoId(bean.getInfoId());
				String content = "";
				if (purchLogsBeans != null) {
					for (PurchLogsBean logBean : purchLogsBeans) {
						if (StringUtils.isNotBlank(logBean.getContent())) {
							content += logBean.getCreated() + "【" + logBean.getName() + "】:" + logBean.getContent() + "<br>";
						}
					}
				}
				bean.setRemark(content);
			}
		}
		return beans;
	}

	/**
	 * 获取所有已经申报的信息列表
	 * 
	 * @Method getDeclaredBean
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月22日 上午11:23:22
	 * @Version 1.0
	 */
	@Override
	public List<BoxnoBean> getDeclaredBean() {
		List<BoxnoBean> beans = boxnoDao.getDeclaredBeans();
		if (beans != null) {
			for (BoxnoBean bean : beans) {
				String title = bean.getTitle();
				if (StringUtils.isNotBlank(bean.getSku_properties_name())) {
					title += bean.getSku_properties_name();
				}
				bean.setTitle(title);
				if (bean.getTransferId() > 0) {
					TransferTypeEnum transfer = TransferTypeEnum.enumValueOf(bean.getTransferId());
					bean.setTransfer(transfer.toDescription());
				} else {
					bean.setTransfer("其它");
				}
			}
		}
		return beans;
	}

	@Override
	public DeclareGoodsBean getDeclareGoodsBean(String infoId) {
		Long infoIdL = Long.parseLong(infoId);
		DeclareGoodsBean bean = boxnoDao.getDeclareBean(infoIdL);
		// bean.getUrl()临时放abroadId
		String url = abroadDao.getAbroadOrderBean(Long.parseLong(bean.getUrl()));
		if (StringUtils.isBlank(bean.getReceiverZip())) {
			bean.setReceiverZip("100000");
		}
		String wrapName = bean.getExpressno() + "-" + infoId + "-" + bean.getReceiverName();
		bean.setWrapName(wrapName);
		bean.setUrl(url);
		bean.setInfoId(infoIdL);
		String goodsName = bean.getTitle();
		if (StringUtils.isNotBlank(bean.getSku_properties_name())) {
			goodsName += "[" + bean.getSku_properties_name() + "]";
		}
		goodsName += "_" + infoId;
		bean.setGoodsName(goodsName);

		String province = bean.getProvince();
		String city = bean.getCity();
		String district = bean.getDistrict();
		String address = bean.getAddress();

		if (StringUtils.isNotBlank(address)) {
			if (!address.contains(district)) {
				address = district + address;
			}
			if (!address.contains(city)) {
				address = city + address;
			}
			if (!address.contains(province)) {
				address = province + address;
			}
			bean.setReceiverAddress(address);
		}

		return bean;
	}

	@Override
	public List<UdfexDeclare> getUdfexDeclareGoodsBean(String expressno) {
		List<DeclareGoodsBean> beanList = boxnoDao.getDeclareBeanByExpressno(expressno);
		List<UdfexDeclare> udfexDeclareList = new ArrayList<UdfexDeclare>();
		for (DeclareGoodsBean bean : beanList) {
			AbroadOrderBean abroadOrder = abroadDao.selectAbroadOrderBean(Long.parseLong(bean.getUrl()));
			if (StringUtils.isBlank(bean.getReceiverZip())) {
				bean.setReceiverZip("100000");
			}
			String goodsName = bean.getTitle();
			if (StringUtils.isNotBlank(bean.getSku_properties_name())) {
				goodsName += "[" + bean.getSku_properties_name() + "]";
			}
			goodsName += "_" + bean.getInfoIds();
			bean.setGoodsName(goodsName);
			String district = bean.getDistrict();
			String state = bean.getProvince();
			String city = bean.getCity();
			if ("市辖区".equals(city)) {
				city = state;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("district", district);
			params.put("state", state);
			params.put("city", StringUtils.isNotBlank(city) ? city.replace("市", "") : "");
			List<CodeBean> codeBeanList = districtMapper.selectCodeByDistrictName(params);
			String stateCode = null;
			String cityCode = null;
			String districtCode = null;
			if (codeBeanList != null && codeBeanList.size() > 0) {
				stateCode = codeBeanList.get(0).getSTATE_CODE();
				cityCode = codeBeanList.get(0).getCITY_CODE();
				districtCode = codeBeanList.get(0).getDISTRICT_CODE();
			}

			UdfexDeclare declare = new UdfexDeclare();
			ConsigneeInfo consigneeInfo = new ConsigneeInfo();
			consigneeInfo.setConsigneeCityCode(cityCode);
			consigneeInfo.setConsigneeDistrictCode(districtCode);
			consigneeInfo.setConsigneeMobileNo(bean.getReceiverMobile());
			consigneeInfo.setConsigneeName(bean.getReceiverName());
			consigneeInfo.setConsigneePhoneNo(bean.getReceiverMobile());
			consigneeInfo.setConsigneePostCode(bean.getReceiverZip());
			consigneeInfo.setConsigneeStateCode(stateCode);
			consigneeInfo.setConsigneeStreet(bean.getAddress());

			declare.setInfoId(String.valueOf(bean.getInfoId()));
			declare.setConsigneeInfo(consigneeInfo);
			declare.setLogisticsNo(bean.getExpressno());
			declare.setLogisticsVendor(bean.getExpComp());
			declare.setSellerName(abroadOrder.getUrl());
			declare.setSellerOrderNo(abroadOrder.getOutOrderId());
			// declare.setWarehouseCode("UDF1631");
			// declare.setServiceProductCode("USE");

			PiecesItem piecesItem = new PiecesItem();
			piecesItem.setAmount(String.valueOf(Float.parseFloat(bean.getPrice()) * bean.getSum()));
			String title = bean.getTitle();
			List<String> bnList = erpOrderDao.findBnByName(title);
			if (bnList != null && bnList.size() > 0) {
				try {
					EcProducts product = ecProductsDao.selectByBn(bnList.get(0));
					EcGoods goods = ecGoodsDao.selectByPrimaryKey(product.getGoodsId());
					SkuBrandBean brand = skuBrandDao.findSkuBrandById(0l + goods.getBrandId());
					piecesItem.setBrandName(brand.getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			piecesItem.setGoodsDescription(title);
			piecesItem.setPiecesItemQty(String.valueOf(bean.getSum()));
			piecesItem.setSpec(bean.getSku_properties_name());
			List<PiecesItem> piecesItems = new ArrayList<PiecesItem>();
			piecesItems.add(piecesItem);
			declare.setPiecesItems(piecesItems);
			udfexDeclareList.add(declare);
		}
		List<UdfexDeclare> udfexDeclareList2 = new ArrayList<UdfexDeclare>();
		for (UdfexDeclare udfexDeclare : udfexDeclareList) {
			if (udfexDeclareList2.size() == 0) {
				udfexDeclareList2.add(udfexDeclare);
			} else {
				int j = -1;
				for (int i = 0; i < udfexDeclareList2.size(); i++) {
					float amount = 0;
					for (PiecesItem piecesItem : udfexDeclareList2.get(i).getPiecesItems()) {
						amount = amount + Float.valueOf(piecesItem.getAmount());
					}
					if (amount > 150 || (amount + Float.valueOf(udfexDeclare.getPiecesItems().get(0).getAmount())) > 150) {
						continue;
					}
					if (udfexDeclareList2.get(i).getConsigneeInfo().toString().equals(udfexDeclare.getConsigneeInfo().toString())) {
						j = i;
					}
				}
				if (j > -1) {
					udfexDeclareList2.get(j).getPiecesItems().add(udfexDeclare.getPiecesItems().get(0));
					String infoId = udfexDeclareList2.get(j).getInfoId() + "-" + udfexDeclare.getInfoId();
					udfexDeclareList2.get(j).setInfoId(infoId);
				} else {
					udfexDeclareList2.add(udfexDeclare);
				}
			}
		}
		return udfexDeclareList2;
	}

	@Override
	public void declareBaiweiOrderInfo(DeclareGoodsBean bean) {
		if (bean != null) {
			BaiweiDeclareTasker task = new BaiweiDeclareTasker(boxnoDao);
			task.setTaskBean(bean);
			AutoExecuteTasker.addAutoExecuteTasker(task);
		} else {
			log.info("===========申报信息不能为空=============");
		}
	}

	@Override
	public void declareBaiweiOrderInfo(List<DeclareGoodsBean> beans) {
		if (beans != null && beans.size() > 0) {
			BaiweiDeclareListTasker task = new BaiweiDeclareListTasker();
			task.setTaskBean(beans);
			AutoExecuteTasker.addAutoExecuteTasker(task);
			for (DeclareGoodsBean bean : beans) {
				BoxnoBean boxnoBean = new BoxnoBean();
				boxnoBean.setInfoId(bean.getInfoId());
				boxnoBean.setDeclared(1);
				boxnoDao.updateDeclate(boxnoBean);
			}
		} else {
			log.info("===========申报信息不能为空=============");
		}
	}

	@Override
	public List<BoxnoBean> getAllExpressnoedBean() {
		List<BoxnoBean> beans = boxnoDao.getAllExpressnoedBeans();
		for (BoxnoBean bean : beans) {
			YzorderInfoBean orderBean = orderDao.getYzorderInfoBeanById(bean.getInfoId());
			String title = orderBean.getTitle();
			if (StringUtils.isNotBlank(orderBean.getSku_properties_name())) {
				title += "［" + orderBean.getSku_properties_name() + "］";
			}
			bean.setTitle(title);
		}
		return beans;
	}

}
