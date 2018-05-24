package com.xiakee.service.utils;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.shangpin.openapi.api.sdk.client.SpClient;
import com.shangpin.openapi.api.sdk.model.ApiResponse;
import com.shangpin.openapi.api.sdk.model.BrandPage;
import com.shangpin.openapi.api.sdk.model.CategoryPage;
import com.shangpin.openapi.api.sdk.model.PageInfo;
import com.shangpin.openapi.api.sdk.model.ProductAdd;
import com.shangpin.openapi.api.sdk.model.ProductAttributeData;
import com.shangpin.openapi.api.sdk.model.ProductMainColor;
import com.shangpin.openapi.api.sdk.model.SopCurrency;
import com.shangpin.openapi.api.sdk.model.SopSkuPriceApply;
import com.shangpin.openapi.api.sdk.model.Supply;

public class ShangpinApiUtil {
	private static Logger log = Logger.getLogger(ShangpinApiUtil.class);

	private static final String APP_KEY = "5b9f2e5f7f004e72acbbedd27f284b6e";
	private static final String APP_SECRET = "5c56d057f4534c389d6b9c7214f766e6";
	private static final String SHANGPIN_URL = "http://111.204.231.201:9090";

	public static void main(String[] args) {
		try {
			//获取货币类型
			ApiResponse<List<SopCurrency>> response = SpClient.FindCurrency(
					SHANGPIN_URL, APP_KEY, APP_SECRET, new Date());
			System.out.println(JSON.toJSONString(response));
			
			//供货价查询
			Supply supply = new Supply();
			supply.setStarttime("2015-08-10 12:12:00");
			supply.setEndtime("2015-08-01 12:12:00");
			supply.setPriceStatus(1);
			supply.setSkuNos("asdfasdfasdfasdf");
			ApiResponse<List<SopSkuPriceApply>> deli = SpClient.FindPrice(SHANGPIN_URL, APP_KEY, APP_SECRET, new Date(), supply );
			System.out.println(JSON.toJSONString(deli));
			
			//新增sku
			ProductAdd addSku = new ProductAdd();
			addSku.setProductName("遐客行测试商品名称");
			addSku.setCategoryNo("0021");
			addSku.setBrandNo("4");
			addSku.setProductModel("RDUYBJ0000001");
			addSku.setProductUnit("1");
			addSku.setProductSlogan("专业户外，品质保证");
			addSku.setSkuDyaAttr(0);
			addSku.setProductSex(2);
			addSku.setMarketTime(2015);
			addSku.setMarketSeason("四季");
			addSku.setPCDescription("专业户外，品质保证专业户外，品质保证专业户外，品质保证专业户外，品质保证");
			addSku.setMobileDescription("专业户外，品质保证专业户外，品质保证专业户外，品质保证专业户外，品质保证");
			
			ProductAttributeData one = new ProductAttributeData();
			one.setAttibuteId(34);
			one.setAttrValueId(424);
			one.setInputAttrValue("asdfasdfasdf");
			one.setTemplateId(2);
			ProductAttributeData[] dataice = new ProductAttributeData[]{
					one
			};
			addSku.setProductAttributeDataIces(dataice);
			
//			SkuInfo info = new SkuInfo();
//			info.setBarCode("XXL");
//			info.setColourScheme("亮骚黄");
//			info.setProductSizeText("asdfasdfasd");
//			SkuInfo[] infos = new SkuInfo[]{info};
//			addSku.setSkuInfoIces(infos);
//			ApiResponse<Integer> product = SpClient.AddSku(SHANGPIN_URL, APP_KEY, APP_SECRET, new Date(), addSku);
//			System.out.println(JSON.toJSONString(product));
			
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageIndex(1);
			pageInfo.setPageSize(200);
			ApiResponse<BrandPage> brands = SpClient.FindBrandByPage(SHANGPIN_URL, APP_KEY, APP_SECRET, new Date(),  pageInfo );
			System.out.println(JSON.toJSONString(brands));
			
			pageInfo = new PageInfo();
			pageInfo.setPageIndex(1);
			pageInfo.setPageSize(200);
			ApiResponse<CategoryPage> categorys = SpClient.FindCategoryByPage(SHANGPIN_URL, APP_KEY, APP_SECRET, new Date(),  pageInfo );
			System.out.println(JSON.toJSONString(categorys));
			
			ApiResponse<List<ProductMainColor>>  colors = SpClient.FindColor(SHANGPIN_URL, APP_KEY, APP_SECRET, new Date());
			System.out.println(JSON.toJSONString(colors));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}