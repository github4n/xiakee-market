package com.xiakee.service.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.xiakee.domain.logistics.DeclareGoodsBean;
import com.xiakee.service.wcf.ISjzyBatchDataOperate;
import com.xiakee.service.wcf.SjzyBatchDataOperate;

public class DeclareBaiweiUtil {
	private static Logger log = Logger.getLogger(DeclareBaiweiUtil.class);
	private static final Map<String,String> CHILDTYPES = new HashMap<String, String>();
	
	static{
		CHILDTYPES.put("1", "二线品牌包");
		CHILDTYPES.put("2", "品牌眼镜");
		CHILDTYPES.put("3", "男式/女式鞋");
		CHILDTYPES.put("4", "童鞋");
		CHILDTYPES.put("5", "羽绒服");
		CHILDTYPES.put("6", "童装");
		CHILDTYPES.put("7", "西装");
		CHILDTYPES.put("8", "靴子");
		CHILDTYPES.put("9", "衬衫");
		CHILDTYPES.put("10", "T恤");
		CHILDTYPES.put("11", "毛衣");
		CHILDTYPES.put("12", "大衣");
		CHILDTYPES.put("13", "棉衣");
		CHILDTYPES.put("14", "内衣");
		CHILDTYPES.put("15", "裤装");
		CHILDTYPES.put("16", "帽子");
		CHILDTYPES.put("17", "普通眼镜");
		CHILDTYPES.put("18", "皮带");
		CHILDTYPES.put("19", "皮带扣");
		CHILDTYPES.put("20", "领带夹");
		CHILDTYPES.put("21", "钱包");
		CHILDTYPES.put("22", "手包");
		CHILDTYPES.put("23", "拎包");
		CHILDTYPES.put("24", "单肩包");
		CHILDTYPES.put("25", "双肩背包");
		CHILDTYPES.put("26", "旅行包");
		CHILDTYPES.put("27", "行李箱");
		CHILDTYPES.put("28", "COACH包");
		CHILDTYPES.put("29", "服装其他");
		CHILDTYPES.put("30", "鞋类其他");
		CHILDTYPES.put("31", "包类其他");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjzyBatchDataOperate client = new SjzyBatchDataOperate();

		ISjzyBatchDataOperate inputData = client
				.getBasicHttpBindingISjzyBatchDataOperate();
		String name = getBaiweiInfo();
		// String message = inputData.batchInputData(name, data);
		String message = inputData.batchDeleteData(name, "USPS1234567");
		
//		inputData.branchBox(userName, buyRecordByJson, boxesByJson, buyThingsByJson, boxServiceChargeTermByJson, buyRecordServiceChargeTermByJson);
		System.out.println(message.trim());
	}

	public static String declareInfoByGoodsBean(DeclareGoodsBean bean) {
		SjzyBatchDataOperate client = new SjzyBatchDataOperate();
		ISjzyBatchDataOperate inputData = client
				.getBasicHttpBindingISjzyBatchDataOperate();
		String name = getBaiweiInfo();
		String data = getDeclareInfo(bean);
		String message = inputData.batchInputData(name, data);
		log.info("===柏威申报信息======" + data);
		log.info("===百威物流申报结果=====" + message);
		return message;
	}

	public static String declareInfoByGoodsBean(List<DeclareGoodsBean> beans) {
		SjzyBatchDataOperate client = new SjzyBatchDataOperate();
		ISjzyBatchDataOperate inputData = client
				.getBasicHttpBindingISjzyBatchDataOperate();
		String name = getBaiweiInfo();
		String data = getDeclareInfo(beans);
		String message = inputData.batchInputData(name, data);
		log.info("柏威合箱申报即刻出库信息" + data);
		log.info("百威合箱申报即刻出库结果" + message);
		return message;
	}

	private static String getBaiweiInfo() {
		String username = null;
		Map<String, String> uinfoMap = new HashMap<String, String>();
		uinfoMap.put("UserName", "iaistar");
		uinfoMap.put("Password", "YLvmscLoCSQblePwsJdKzg==");
		username = JSON.toJSONString(uinfoMap);
		return username;
	}

	private static String getDeclareInfo(DeclareGoodsBean bean) {
		String declareInfo = null;
		if (bean != null) {
			List<Map<String, String>> allOrder = new ArrayList<Map<String, String>>();
			Map<String, String> order = new HashMap<String, String>();
			order.put("品名", bean.getGoodsName());
			order.put("所属类别", bean.getType());
			order.put("子类别", bean.getChildType());
			order.put("英文名", bean.getEnName());
			order.put("品牌", bean.getBrand());
			order.put("单价", bean.getPrice());
			order.put("数量", bean.getSum() + "");
			order.put("用户名", bean.getUserName());
			order.put("包裹名称", bean.getWrapName());
			order.put("货物网站", bean.getUrl());
			order.put("快递公司", bean.getExpComp());
			order.put("快递单号", bean.getExpressno());
			
			if(bean.getNow() != 0){
				order.put("收货人姓名", bean.getReceiverName());
				order.put("收货人手机", bean.getReceiverMobile());
				order.put("收货人地址", bean.getReceiverAddress());
				order.put("转运线路", bean.getTransport());
			}else {
				order.put("收货人姓名","");
				order.put("收货人手机", "");
				order.put("收货人地址", "");
				order.put("转运线路", "");
			}
			
			order.put("邮编", bean.getReceiverZip());
			allOrder.add(order);
			declareInfo = JSON.toJSONString(allOrder);
		}
		return declareInfo;
	}

	private static String getDeclareInfo(List<DeclareGoodsBean> beans) {
		String declareInfo = null;
		if (beans != null && !beans.isEmpty()) {
			List<Map<String, String>> allOrder = new ArrayList<Map<String, String>>();
			for(DeclareGoodsBean bean:beans){
				Map<String, String> order = new HashMap<String, String>();
				order.put("品名", bean.getGoodsName());
				order.put("所属类别", bean.getType());
				order.put("子类别", bean.getChildType());
				order.put("英文名", bean.getEnName());
				order.put("品牌", bean.getBrand());
				order.put("单价", bean.getPrice());
				order.put("数量", bean.getSum() + "");
				order.put("用户名", bean.getUserName());
				order.put("包裹名称", bean.getWrapName());
				order.put("货物网站", bean.getUrl());
				order.put("快递公司", bean.getExpComp());
				order.put("快递单号", bean.getExpressno());
				
				if(bean.getNow() != 0){
					order.put("收货人姓名", bean.getReceiverName());
					order.put("收货人手机", bean.getReceiverMobile());
					order.put("收货人地址", bean.getReceiverAddress());
					order.put("转运线路", bean.getTransport());
				}else {
					order.put("收货人姓名","");
					order.put("收货人手机", "");
					order.put("收货人地址", "");
					order.put("转运线路", "");
				}
				
				order.put("邮编", bean.getReceiverZip());
				allOrder.add(order);
			}
			declareInfo = JSON.toJSONString(allOrder);
		}
		return declareInfo;
	}
}
