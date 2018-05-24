package com.xiakee.service.yz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.junit.Ignore;

import com.alibaba.fastjson.JSONObject;
import com.xiakee.domain.yz.YouzanResponseJson;

@Ignore
public class KDTApiTest {
	private static final String APP_ID = "51d0fa76180db02dc3"; //这里换成你的app_id
	private static final String APP_SECRET = "7b349948807ccc61b0270c25cca62ba9"; //这里换成你的app_secret
	
	public static void main(String[] args){
//		sendGet();
//		sendPost();
		tradeGet();
	}
	
	/*
	 * 测试获取单个商品信息
	 */
	private static void sendGet(){
//		String method = "kdt.items.inventory.get";
		String method = "kdt.trades.sold.get";
		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("fields", "tid,title,receiver_city,price,receiver_district,receiver_name,receiver_state,receiver_address,receiver_mobile,status,total_fee,"
//				+ "payment,created,update_time,pay_time,receiver_zip,sku_properties_name");
		params.put("page_size", "10000");
//		params.put("status", "WAIT_SELLER_SEND_GOODS");//等待卖家发货，即：买家已付款
//		params.put("status", "WAIT_BUYER_CONFIRM_GOODS");//等待买家确认收货，即：卖家已发货
//		params.put("status", "TRADE_BUYER_SIGNED");//买家已签收
//		params.put("start_update", "2015-04-30 11:02:49");
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(APP_ID, APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

//			System.out.println(result.toString());
			YouzanResponseJson json = JSONObject.parseObject(result.toString(), YouzanResponseJson.class);
			List<YouzanResponseJson.Trades> list = json.getResponse().getTrades();
			System.out.println("总量：" + list.size() + "  " + json.getResponse().getHas_next());
			for(YouzanResponseJson.Trades trades:list){
//				System.out.print(trades.getReceiver_mobile() + "    ");
//				System.out.print(trades.getReceiver_name() + "    ");
				System.out.print(trades.getTitle() + "    ");
//				System.out.print(trades.getReceiver_district() + "    ");
//				System.out.print(trades.getReceiver_address() + "    ");
//				System.out.print(trades.getReceiver_state() + "    ");
//				System.out.print(trades.getPayment() + "    ");
//				System.out.print(trades.getReceiver_city() + "    ");
				System.out.print(trades.getPay_time() + "    ");
//				List<YouzanResponseJson.order> orders = trades.getOrders();
//				if(orders != null){
//					int  i = 1;
//					for(YouzanResponseJson.order o:orders){
//						System.out.print("*" + i + "*" + o.getSku_properties_name() + "    ");
//						System.out.print("*" + i + "*" + o.getTitle() + "    ");
//						System.out.print("*" + i + "*" + o.getNum() + "    ");
//						System.out.print("*" + i + "*" + o.getPrice() + "    ");
//						i ++;
//					}
//				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	private static void tradeGet(){
//		String method = "kdt.items.inventory.get";
		String method = "kdt.trade.get";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("fields", "status,update_time,pay_time");
		params.put("tid", "E20150517191946921655500");
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(APP_ID, APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			YouzanResponseJson json = JSONObject.parseObject(result.toString(), YouzanResponseJson.class);
			List<YouzanResponseJson.Trades> list = json.getResponse().getTrades();
			System.out.println("总量：" + list.size() + "  " + json.getResponse().getHas_next());
			for(YouzanResponseJson.Trades trades:list){
				System.out.print(trades.getStatus() + "    ");
				System.out.print(trades.getUpdate_time() + "    ");
				System.out.print(trades.getPay_time() + "    ");
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 测试获取添加商品
	 */
	private static void sendPost(){
		String method = "kdt.item.add";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("price", "999.01");
		params.put("title", "测试商品");
		params.put("desc", "这是一个号商铺");
		params.put("is_virtual", "0");
		params.put("post_fee", "10.01");
		params.put("sku_properties", "");
		params.put("sku_quantities", "");
		params.put("sku_prices", "");
		params.put("sku_outer_ids", "");
		String fileKey = "images[]";
		List<String> filePaths = new ArrayList<String>();
		filePaths.add("/Users/xuexiaozhe/Desktop/1.png");
		filePaths.add("/Users/xuexiaozhe/Desktop/2.png");
		
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(APP_ID, APP_SECRET);
			response = kdtApiClient.post(method, params, filePaths, fileKey);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
