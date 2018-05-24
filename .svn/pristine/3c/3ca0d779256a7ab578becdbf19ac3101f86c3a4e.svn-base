package com.xiakee.service.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.shangpin.openapi.api.sdk.client.MD5;
import com.xiakee.domain.ecos.ApiResultJson;
import com.xiakee.domain.ecos.BrandApiJson;
import com.xiakee.domain.ecos.CatalogApiJson;
import com.xiakee.domain.ecos.TypeApiJson;
import com.xiakee.domain.ecos.UserApiJson;
import com.xiakee.domain.sku.SkuBrandBean;
import com.xiakee.domain.sku.SkuCatalogBean;
import com.xiakee.domain.sku.SkuTypeBean;

public class EcstoreApiUtil {
	private static Logger log = Logger.getLogger(EcstoreApiUtil.class);

	/**
	 * 执行具体的方法，并且返回ecstore的结果，格式如下： Map<String, String> params = new
	 * HashMap<String, String>(); params.put("method", "b2c.order.search");
	 * params.put("具体参数", "b2c.order.search");
	 * 
	 * @Method getEcstoreApiResultBean
	 * @Return ApiResultBean
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年6月6日 下午3:43:01
	 * @Version 1.0
	 */
	public static ApiResultJson getEcstoreApiResultBean(
			Map<String, String> params) {
		ApiResultJson bean = null;
		if (params != null && params.size() > 0) {
			try {
				String postResult = EcstoreApiBase.doPost(params);// POST请求，url为例子，网店地址需要改成自己的域名
				bean = JSON.parseObject(postResult, ApiResultJson.class);
				log.info("成功获取ecstore数据：" + bean.getData());
			} catch (IOException e) {
				log.error("ecstore后台读取数据失败", e);
			}
		}
		return bean;
	}

	/**
	 * 获取商城目录信息
	 * 
	 * @Method getCatalogApiJson
	 * @param parentId
	 * @return CatalogApiJson
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com
	 * @Date 2015年6月18日 上午12:27:52
	 * @Version 1.0
	 */
	public static List<SkuCatalogBean> getCatalogApiJson(String parentId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "b2c.goods.get_cat_list");
		params.put("cat_id", parentId);// /上装

		List<SkuCatalogBean> beans = null;
		if (params != null && params.size() > 0) {
			try {
				String postResult = EcstoreApiBase.doPost(params);// POST请求，url为例子，网店地址需要改成自己的域名
				CatalogApiJson jsonBean = JSON.parseObject(postResult,
						CatalogApiJson.class);
				if (jsonBean != null) {
					beans = jsonBean.getData();
				}
			} catch (IOException e) {
				log.error("ecstore后台读取数据失败", e);
			}
		}
		return beans;
	}

	/**
	 * 获取商城类型信息
	 * 
	 * @Method getTypeApiJson
	 * @Return SkuTypeBean
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年6月18日 上午11:06:27
	 * @Version 1.0
	 */
	public static SkuTypeBean getTypeApiJson(String typeId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "b2c.goods.get_type_detial");
		params.put("type_id", typeId);// /上装

		SkuTypeBean bean = null;
		if (params != null && params.size() > 0) {
			try {
				String postResult = EcstoreApiBase.doPost(params);// POST请求，url为例子，网店地址需要改成自己的域名
				TypeApiJson jsonBean = JSON.parseObject(postResult,
						TypeApiJson.class);
				if (jsonBean != null) {
					System.out.println(jsonBean.getData().get("spec_value"));
					bean = new SkuTypeBean();
					bean.setType_id(Long.parseLong(jsonBean.getData().get(
							"type_id")));
					bean.setName(jsonBean.getData().get("name"));
				}
				log.info("成功获取商城商品目录数据：" + bean);
			} catch (IOException e) {
				log.error("ecstore后台读取数据失败", e);
			}
		}
		return bean;
	}
	
	public static Map<String, String> getUserApiJson() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "b2c.member.operateboge");

		if (params != null && params.size() > 0) {
			try {
				String postResult = EcstoreApiBase.doPost(params);// POST请求，url为例子，网店地址需要改成自己的域名
				UserApiJson jsonBean = JSON.parseObject(postResult,
						UserApiJson.class);
				if (jsonBean != null) {
					return jsonBean.getData();
				}
			} catch (IOException e) {
				log.error("ecstore后台读取数据失败", e);
			}
		}
		return null;
	}
	
	public static String setSmsc(String mobile,String msg,String result,String type,String ok) {
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("method", "b2c.member.add_user_msg");
		 params.put("mobile", mobile);
		 params.put("msg", msg);
		 params.put("result", result);
		 params.put("type", type);
		 params.put("ok", ok);
		 String rest = "";
		 try {
			 rest = EcstoreApiBase.doPost(params);
		 } catch (IOException e) {
			 log.error("ecstore后台更新订单状态失败", e);
		 }
		 return rest;
	}

	/**
	 * 获取商城品牌信息
	 * 
	 * @Method getBrandApiJson
	 * @Return List<SkuBrandBean>
	 * @Author huangzhaoshui
	 * @Date 2015年6月18日 上午11:06:27
	 * @Version 1.0
	 */
	public static List<SkuBrandBean> getBrandApiJson(String brandId) {
		List<SkuBrandBean> beans = new ArrayList<SkuBrandBean>();
		getBrand(beans, 1);
		return beans;
	}

	private static void getBrand(List<SkuBrandBean> beans, int page_no) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "b2c.brand.get_brand_detail");
		params.put("page_no", String.valueOf(page_no));
		if (params != null && params.size() > 0) {
			try {
				String postResult = EcstoreApiBase.doPost(params);// POST请求，url为例子，网店地址需要改成自己的域名
				BrandApiJson jsonBean = JSON.parseObject(postResult,
						BrandApiJson.class);
				if (jsonBean != null) {
					String brandData = jsonBean.getData().get("brandData");
					String[] brandDatas = brandData.split("ordernum");
					for (String str : brandDatas) {
						if (str.contains("brand_id")) {
							SkuBrandBean bean = new SkuBrandBean();
							String brand_id = str.substring(
									str.indexOf("brand_id") + 11,
									str.indexOf("brand_logo") - 3);
							String brand_name = str.substring(
									str.indexOf("brand_name") + 13,
									str.indexOf("brand_url") - 3);
							bean.setId(Long.valueOf(brand_id));
							bean.setName(brand_name);
							beans.add(bean);
						}
					}
					if (brandDatas.length >= 20) {
						getBrand(beans, ++page_no);
					}
				}
			} catch (IOException e) {
				log.error("ecstore后台读取数据失败", e);
			}
		}
	}

	/**
	 * 在确定海外物流id后修改用户订单日志状态为发货
	 * 
	 * @param orderid
	 * @return
	 */
	public static boolean updateShipOrderStatus(String orderid,String status) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "b2c.order.ship_status_update");
		params.put("order_bn", orderid);// 订单ID
		params.put("ship_status", status);// 修改订单状态为已发货
		boolean isOK = false;

		if (params != null && params.size() > 0) {
			try {
				String postResult = EcstoreApiBase.doPost(params);// POST请求，url为例子，网店地址需要改成自己的域名
				BrandApiJson jsonBean = JSON.parseObject(postResult,
						BrandApiJson.class);
				if (StringUtils.equals("succ", jsonBean.getRsp())) {
					isOK = true;
				}
			} catch (IOException e) {
				log.error("ecstore后台更新订单状态失败", e);
			}
		}
		return isOK;
	}
	
	public static boolean updateOrderStatus(String orderid,String status) {
		Map<String, String> params = new HashMap<String, String>();
		 params.put("method", "b2c.order.status_update");
		 params.put("order_bn", orderid);
		 params.put("status", status);
		boolean isOK = false;

		if (params != null && params.size() > 0) {
			try {
				String postResult = EcstoreApiBase.doPost(params);// POST请求，url为例子，网店地址需要改成自己的域名
				BrandApiJson jsonBean = JSON.parseObject(postResult,
						BrandApiJson.class);
				if (StringUtils.equals("succ", jsonBean.getRsp())) {
					isOK = true;
				}
			} catch (IOException e) {
				log.error("ecstore后台更新订单状态失败", e);
			}
		}
		return isOK;
	}

	public static void main(String[] args) {
//		 Map<String, String> params = new HashMap<String, String>();
//		 params.put("method", "b2c.member.operateboge");
//		 params.put("method", "b2c.member.add_user_msg");
//		 params.put("mobile", "13401190238");
//		 params.put("msg", "遐客行必胜");
//		 params.put("result", "欢迎插入信息咯");
//		 params.put("type", "2");
//		 params.put("ok", "1");
//		 boolean isOK = false;
//		
//		 if (params != null && params.size() > 0) {
//		 try {
//		 String postResult = EcstoreApiBase.doPost(params);//
//		 System.out.println(postResult);
//		 POST请求，url为例子，网店地址需要改成自己的域名
//		 BrandApiJson jsonBean = JSON.parseObject(postResult,
//		 BrandApiJson.class);
//		 if (StringUtils.equals("succ", jsonBean.getRsp())) {
//		 System.out.println(jsonBean.getData());
//		 }
//		 } catch (IOException e) {
//		 log.error("ecstore后台更新订单状态失败", e);
//		 }
//		 }
//		 System.out.println(EcstoreApiBase.getPasswordByUsername("elena", "", "1432002534"));
		 
		Map<String, String> users = getUserApiJson();
		Iterator iter = users.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			System.out.println(entry.getKey());
		}
	}
}
