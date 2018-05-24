package com.xiakee.service.analy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xiakee.dao.analy.OrderOriginDao;
import com.xiakee.dao.order.ErpOrderDao;
import com.xiakee.dao.sku.SkuManagerDao;
import com.xiakee.domain.analy.AnalyOrderSumsBean;
import com.xiakee.domain.analy.AnalyResultBean;
import com.xiakee.domain.analy.HighchartsColumnJson;
import com.xiakee.domain.analy.HighchartsJson;
import com.xiakee.domain.order.EcOrderSourceBean;
import com.xiakee.domain.sku.AddSkuSumBean;
import com.xiakee.domain.urm.EcDesktopUserBean;
import com.xiakee.domain.utils.AnalyTypeEnum;
import com.xiakee.ecdao.sso.EcDesktopUserDao;
import com.xiakee.service.analy.AnalyticalService;
import com.xiakee.service.utils.TimeUtils;

@Service
public class AnalyticalServiceImpl implements AnalyticalService {
	private static Logger log = Logger.getLogger(AnalyticalServiceImpl.class);
	
	private static final String[] COLORS = new String[]{"#058DC7", "#50B432", "#ED561B","#DDDF00", "#24CBE5", "#64E572", "#FF9655", "#FFF263", "#6AF9C4"};
	
	@Autowired
	private ErpOrderDao orderDao;
	@Autowired
	private EcDesktopUserDao userDao;
	@Autowired
	private SkuManagerDao skuDao;
	
	@Autowired
	private OrderOriginDao originDao;

	/**
	 * 已支付订单数的来源分析
	 * date：时间区间，如果为空，则默认是本月度数据
	 * type：总类，1为每小时数据，2为每天数据，3为省份数据
	 * @Method  analyOrderOrigin
	 * @Return HighchartsJson
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年10月23日 下午9:41:34
	 * @Version 1.0
	 */
	@Override
	public Object analyOrderOrigin(String date,AnalyTypeEnum type) {
		String begin = null;
		String end = null;
		Object json = null;
		if(StringUtils.isNotBlank(date)){
			String[] dates = date.split(" - ");
			begin = dates[0].replace("/", "-") + " 00:00:00";
			end = dates[1].replace("/", "-") + " 23:59:59";
		}else {
			if(AnalyTypeEnum.ORDER_MONTH == type || AnalyTypeEnum.COST_MONTH == type){
				begin = TimeUtils.getXiakeeBeginDate();
			}else{
				begin = TimeUtils.getCurrentMonthDate();
			}
			end = TimeUtils.displayCurrentTime();
		}
		switch (type) {
		case ORDER_HOUR:
			json = analyHourOrderData(begin,end);
			break;
			
		case ORDER_DATE:
			json = analyDateOrderData(begin,end);
			break;
			
		case ORDER_PROVINCE:
			json = analyProvinceOrderData(begin, end);
			break;
			
		case ORDER_REFER:
			json = analyOrderSourceSum(begin,end);
			break;
			
		case SKU_SUM:
			json = analyAddSkusSum(begin, end);
			break;
			
		case ORDER_MONTH:
			json = analyMonthOrderData(begin,end);
			break;
			
		case COST_MONTH:
			json = analyMonthOrderCost(begin,end);
			break;

		default:
			json = analyDateOrderData(begin,end);
			break;
		}
		
		return json;
	}

	private HighchartsJson analyHourOrderData(String begin,String end){
		String title = "订单小时区间数据趋势";
		List<AnalyResultBean> AllBeans = this.originDao.analyAllHourOrderData(begin, end);
		List<AnalyResultBean> beans = this.originDao.analyHourOrderData(begin, end);

		Map<String, Integer> succOrders = new HashMap<String, Integer>();
		for(AnalyResultBean bean:beans){
			succOrders.put(bean.getKey(), bean.getValue());
		}
		return analyAllOrderData(AllBeans,succOrders,title,"line",begin, end);
	}

	private HighchartsJson analyProvinceOrderData(String begin,String end){
		String title = "已支付订单省份总量占比";
		List<AnalyResultBean> beans = this.originDao.analyProvinceOrderData(begin, end);
		return analyOrderData(beans,title,"pie",begin, end);
	}
	
	private HighchartsColumnJson analyAddSkusSum(String begin,String end){
		String title = "SKU上新（个人）总量排行榜";
		HighchartsColumnJson json = null;
		List<AddSkuSumBean> beans = this.skuDao.sectionAddSkuSums(begin, end);
		
		if(beans != null){
			json = new HighchartsColumnJson(title, begin + "至" + end,"SKU上新总量");
			List<String> color = new ArrayList<String>();
			List<HighchartsColumnJson.DataInfo> datas = new ArrayList<HighchartsColumnJson.DataInfo>();
			List<String> categories = new ArrayList<String>();
			int i = 0;
			for(AddSkuSumBean bean:beans){
				HighchartsColumnJson.DataInfo data1 = new HighchartsColumnJson.DataInfo();
				
				data1.setColor(COLORS[i]);
				
				EcDesktopUserBean userBean = this.userDao.findAllDesktopUserById(bean.getUserId());
				String userName = "无名氏";
				if(userBean != null){
					userName = userBean.getName();
				}
				data1.setName(userName);
				data1.setY(bean.getSum());
				datas.add(data1);
				
				color.add(COLORS[i]);
				categories.add(userName);
				i ++;
			}
			json.setColors(color);
			
			HighchartsColumnJson.Xaxis xaxis = new HighchartsColumnJson.Xaxis(categories);
			json.setxAxis(xaxis);
			
			HighchartsColumnJson.Series serie = new HighchartsColumnJson.Series();
			serie.setName("SKU个人上新总量");
			serie.setData(datas);
			
			List<HighchartsColumnJson.Series> series = new ArrayList<HighchartsColumnJson.Series>();
			series.add(serie);
			
			json.setSeries(series);
		}
		return json;
	}

	public HighchartsJson analyOrderSourceSum(String begin,String end) {
		HighchartsJson json = null;
		String title = "已支付订单访问来源总量占比";
		List<EcOrderSourceBean> beans = this.orderDao.sectionEcOrderSourceSum(TimeUtils.formatTimeForPhp(begin), TimeUtils.formatTimeForPhp(end));
		if(beans != null){
			json = new HighchartsJson(title, begin + "至" + end,"pie","下单访问来源总量");
			List<String> color = new ArrayList<String>();
			
			List<HighchartsJson.DataInfo> datas = new ArrayList<HighchartsJson.DataInfo>();
			
			int i = 0;
			for(EcOrderSourceBean bean:beans){
				HighchartsJson.DataInfo data1 = new HighchartsJson.DataInfo();
				
				data1.setColor(COLORS[i]);
				if("weixin".equals(bean.getSource())){
					data1.setName("微信");
				}else if("pc".equals(bean.getSource())){
					data1.setName("电脑");
				}else {
					data1.setName("手机");
				}
				data1.setY(bean.getSum());
				datas.add(data1);
				
				color.add(COLORS[i]);
				i ++;
			}
			json.setColors(color);
			
			HighchartsJson.Series serie = new HighchartsJson.Series();
			serie.setName("订单总量");
			serie.setData(datas);
			
			List<HighchartsJson.Series> series = new ArrayList<HighchartsJson.Series>();
			series.add(serie);
			
			json.setSeries(series);
		}
		return json;
	}

	private HighchartsJson analyDateOrderData(String begin,String end){
		HighchartsJson json = null;
		String title = "订单每天数据趋势";
		List<AnalyResultBean> beans = this.originDao.analyDateOrderData(begin, end);
		List<AnalyResultBean> AllBeans = this.originDao.analyAllDateOrderData(begin, end);
		Map<String, Integer> succOrders = new HashMap<String, Integer>();
		for(AnalyResultBean bean:beans){
			succOrders.put(bean.getKey(), bean.getValue());
		}
		return analyAllOrderData(AllBeans,succOrders,title,"line",begin, end);
	}

	private Object analyMonthOrderData(String begin,String end){
		String title = "月度订单总量发展趋势";
		List<AnalyResultBean> beans = this.originDao.analyMonthOrderData(begin, end);
		List<AnalyResultBean> AllBeans = this.originDao.analyAllMonthOrderData(begin, end);
		Map<String, Integer> succOrders = new HashMap<String, Integer>();
		for(AnalyResultBean bean:beans){
			succOrders.put(bean.getKey(), bean.getValue());
		}
		return analyOrderColumnData(AllBeans,succOrders,title,begin, end);
	}

	private Object analyMonthOrderCost(String begin,String end){
		String title = "月度订单销售、成本、物流、优惠等金额汇总";
		List<AnalyResultBean> costBeans = this.originDao.analyMonthOrderCost(begin, end);
		List<AnalyOrderSumsBean> allBeans = this.originDao.analyMonthOrderSums(begin, end);
		if(allBeans != null){
			int length = allBeans.size();
			for(AnalyOrderSumsBean aBean:allBeans){
				AnalyResultBean cost = null;
				for(AnalyResultBean b:costBeans){
					if(StringUtils.equals(aBean.getKey(), b.getKey())){
						cost = b;
						break;
					}
				}
				
				if(cost != null ){
					aBean.setCost(Double.valueOf(cost.getValue()));
				}
			}
		}
		return analyOrderColumnCost(allBeans,title,begin, end);
	}
	
	private HighchartsJson analyOrderData(List<AnalyResultBean> beans,String title,String chartType,String begin,String end){
		HighchartsJson json = null;
		if(beans != null){
			json = new HighchartsJson(title, begin + "至" + end,chartType,"订单总量");
			List<String> color = new ArrayList<String>();
			List<HighchartsJson.DataInfo> datas = new ArrayList<HighchartsJson.DataInfo>();
			int i = 0;
			List<String> categories = new ArrayList<String>();
			for(AnalyResultBean bean:beans){
				HighchartsJson.DataInfo data1 = new HighchartsJson.DataInfo();
				if(i >= COLORS.length){
					i = 0;
				}
				data1.setColor(COLORS[i]);
				
				data1.setName(bean.getKey());
				
				data1.setY(bean.getValue());
				datas.add(data1);
				
				color.add(COLORS[i]);
				i ++;
				categories.add(bean.getKey());
			}
			json.setColors(color);
			
			Map<String, List<String>> xAxis = new HashMap<String, List<String>>();
			xAxis.put("categories", categories);
			
			json.setxAxis(xAxis);
			
			HighchartsJson.Series serie = new HighchartsJson.Series();
			serie.setName("已支付订单总量");
			serie.setData(datas);
			
			List<HighchartsJson.Series> series = new ArrayList<HighchartsJson.Series>();
			series.add(serie);
			
			json.setSeries(series);
		}
		log.info("订单统计数据结果集：" + JSONObject.toJSONString(json));
		return json;
	}
	
	private HighchartsJson analyAllOrderData(List<AnalyResultBean> allBeans,Map<String, Integer> succOrder,String title,String chartType,String begin,String end){
		HighchartsJson json = null;
		if(allBeans != null){
			json = new HighchartsJson(title, begin + "至" + end,chartType,"订单总量");
			List<String> color = new ArrayList<String>();
			List<HighchartsJson.DataInfo> Alldatas = new ArrayList<HighchartsJson.DataInfo>();
			List<HighchartsJson.DataInfo> succdatas = new ArrayList<HighchartsJson.DataInfo>();
			int i = 0;
			List<String> categories = new ArrayList<String>();
			for(AnalyResultBean bean:allBeans){
				HighchartsJson.DataInfo data1 = new HighchartsJson.DataInfo();
				HighchartsJson.DataInfo data2 = new HighchartsJson.DataInfo();
				if(i >= COLORS.length){
					i = 0;
				}
				data1.setColor(COLORS[i]);
				data2.setColor(COLORS[i]);
				
				data1.setName(bean.getKey());
				data2.setName(bean.getKey());
				
				data1.setY(bean.getValue());
				Integer succSum = succOrder.get(bean.getKey());
				if(succSum != null){
					data2.setY(succSum);
				}else{
					data2.setY(0);
				}
				Alldatas.add(data1);
				succdatas.add(data2);
				
				color.add(COLORS[i]);
				i ++;
				categories.add(bean.getKey());
			}
			json.setColors(color);
			
			Map<String, List<String>> xAxis = new HashMap<String, List<String>>();
			xAxis.put("categories", categories);
			
			json.setxAxis(xAxis);
			
			HighchartsJson.Series serie = new HighchartsJson.Series();
			serie.setName("全部订单总量");
			serie.setData(Alldatas);
			
			HighchartsJson.Series serie2 = new HighchartsJson.Series();
			serie2.setName("已支付订单总量");
			serie2.setData(succdatas);
			
			List<HighchartsJson.Series> series = new ArrayList<HighchartsJson.Series>();
			series.add(serie);
			series.add(serie2);
			
			json.setSeries(series);
		}
		log.info("订单统计数据结果集：" + JSONObject.toJSONString(json));
		return json;
	}
	
	private HighchartsColumnJson analyOrderColumnData(List<AnalyResultBean> allBeans,Map<String, Integer> succOrder,String title,String begin,String end){
		HighchartsColumnJson json = null;
		
		if(allBeans != null){
			json = new HighchartsColumnJson(title, begin + "至" + end,"订单总量");
			List<String> color = new ArrayList<String>();
			List<HighchartsColumnJson.DataInfo> datas = new ArrayList<HighchartsColumnJson.DataInfo>();
			List<HighchartsColumnJson.DataInfo> succdatas = new ArrayList<HighchartsColumnJson.DataInfo>();
			int i = 0;
			List<String> categories = new ArrayList<String>();
			for(AnalyResultBean bean:allBeans){
				HighchartsColumnJson.DataInfo data1 = new HighchartsColumnJson.DataInfo();
				HighchartsColumnJson.DataInfo data2 = new HighchartsColumnJson.DataInfo();
				if(i >= COLORS.length){
					i = 0;
				}
//				data1.setColor(COLORS[i]);
//				data2.setColor(COLORS[i]);
				
				data1.setName(bean.getKey());
				data2.setName(bean.getKey());
				
				data1.setY(bean.getValue());
				Integer succSum = succOrder.get(bean.getKey());
				if(succSum != null){
					data2.setY(succSum);
				}else{
					data2.setY(0);
				}
				datas.add(data1);
				succdatas.add(data2);
				
				color.add(COLORS[i]);
				i ++;
				categories.add(bean.getKey());
			}
			json.setColors(color);
			

			HighchartsColumnJson.Xaxis xaxis = new HighchartsColumnJson.Xaxis(categories);
			json.setxAxis(xaxis);
			
			HighchartsColumnJson.Series serie = new HighchartsColumnJson.Series();
			serie.setName("全部订单总量");
			serie.setData(datas);
			
			HighchartsColumnJson.Series serie2 = new HighchartsColumnJson.Series();
			serie2.setName("已支付订单总量");
			serie2.setData(succdatas);
			
			List<HighchartsColumnJson.Series> series = new ArrayList<HighchartsColumnJson.Series>();
			series.add(serie);
			series.add(serie2);
			
			json.setSeries(series);
		}
		log.info("订单统计数据结果集：" + JSONObject.toJSONString(json));
		return json;
	}
	
	private HighchartsColumnJson analyOrderColumnCost(List<AnalyOrderSumsBean> beans,String title,String begin,String end){
		HighchartsColumnJson json = null;
		if(beans != null){
			json = new HighchartsColumnJson(title, begin + "至" + end,"订单金额汇总");
			List<String> color = new ArrayList<String>();
			color.add(COLORS[0]);
			color.add(COLORS[1]);
			color.add(COLORS[2]);
			color.add(COLORS[3]);
			color.add(COLORS[4]);
			json.setColors(color);
			List<HighchartsColumnJson.DataInfo> costs = new ArrayList<HighchartsColumnJson.DataInfo>();
			List<HighchartsColumnJson.DataInfo> pmts = new ArrayList<HighchartsColumnJson.DataInfo>();
			List<HighchartsColumnJson.DataInfo> freights = new ArrayList<HighchartsColumnJson.DataInfo>();
			List<HighchartsColumnJson.DataInfo> payeds = new ArrayList<HighchartsColumnJson.DataInfo>();
			List<HighchartsColumnJson.DataInfo> items = new ArrayList<HighchartsColumnJson.DataInfo>();
			List<String> categories = new ArrayList<String>();
			for(AnalyOrderSumsBean bean:beans){
				HighchartsColumnJson.DataInfo cost = new HighchartsColumnJson.DataInfo();
				HighchartsColumnJson.DataInfo pmt = new HighchartsColumnJson.DataInfo();
				HighchartsColumnJson.DataInfo freight = new HighchartsColumnJson.DataInfo();
				HighchartsColumnJson.DataInfo payed = new HighchartsColumnJson.DataInfo();
				HighchartsColumnJson.DataInfo item = new HighchartsColumnJson.DataInfo();
//				data1.setColor(COLORS[i]);
//				data2.setColor(COLORS[i]);
				
				cost.setName(bean.getKey());
				pmt.setName(bean.getKey());
				freight.setName(bean.getKey());
				payed.setName(bean.getKey());
				item.setName(bean.getKey());
				
				if(bean.getCost() != null){
					cost.setY(bean.getCost().intValue());
				}
				if(bean.getPmt() != null){
					pmt.setY(bean.getPmt().intValue());
				}
				if(bean.getFreight() != null){
					freight.setY(bean.getFreight().intValue());
				}
				if(bean.getPayed() != null){
					payed.setY(bean.getPayed().intValue());
				}
				if(bean.getItems() != null){
					item.setY(bean.getItems().intValue());
				}
				
				costs.add(cost);
				pmts.add(pmt);
				freights.add(freight);
				payeds.add(payed);
				items.add(item);
				
				categories.add(bean.getKey());
			}
			json.setColors(color);
			

			HighchartsColumnJson.Xaxis xaxis = new HighchartsColumnJson.Xaxis(categories);
			json.setxAxis(xaxis);
			
			HighchartsColumnJson.Series serie1 = new HighchartsColumnJson.Series();
			serie1.setName("支付总价");
			serie1.setData(payeds);
			
			HighchartsColumnJson.Series serie2 = new HighchartsColumnJson.Series();
			serie2.setName("商品总价");
			serie2.setData(items);
			
			HighchartsColumnJson.Series serie3 = new HighchartsColumnJson.Series();
			serie3.setName("优惠总价");
			serie3.setData(pmts);
			
			HighchartsColumnJson.Series serie4 = new HighchartsColumnJson.Series();
			serie4.setName("运费总价");
			serie4.setData(freights);
			
			HighchartsColumnJson.Series serie5 = new HighchartsColumnJson.Series();
			serie5.setName("采购总价(美元)");
			serie5.setData(costs);
			
			List<HighchartsColumnJson.Series> series = new ArrayList<HighchartsColumnJson.Series>();
			series.add(serie1);
			series.add(serie2);
			series.add(serie3);
			series.add(serie4);
			series.add(serie5);
			
			json.setSeries(series);
		}
		log.info("订单金融统计数据结果集：" + JSONObject.toJSONString(json));
		return json;
	}
}
