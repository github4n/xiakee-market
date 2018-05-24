package com.xiakee.service.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.logistics.BoxnoJson;
import com.xiakee.domain.logistics.BoxnoJson.Boxno;
import com.xiakee.wcf.logist.BsiBuyQueryAPI;
import com.xiakee.wcf.logist.IBsiBuyQueryAPI;

public class BaiweiBoxnoUtil {
	private static Logger log = Logger.getLogger(BaiweiBoxnoUtil.class);

	private static final List<String> LOGIST_NODE = Arrays.asList(
			"入库日期", // node 5
			"出库日期", // node 6
			"登机日期",// node 7
			"清关日期",// node 8
			"中通快递");// node 9

	public static BoxnoJson.Boxno getBoxnoLogistNodeInfo(String expressno,
			int node) {
		if (StringUtils.isBlank(expressno) || node < 5 || node >= 100) {
			return null;
		}
		BsiBuyQueryAPI client = new BsiBuyQueryAPI();
		IBsiBuyQueryAPI queryAPI = client.getBasicHttpBindingIBsiBuyQueryAPI();
		BoxnoJson expressnoJson = getExpressnoById(queryAPI,expressno);
		if (expressnoJson != null) {
			List<BoxnoJson.Boxno> exBeans = expressnoJson.getData();
			if (exBeans != null) {
				for (BoxnoJson.Boxno exBoxno : exBeans) {
					String boxStr = exBoxno.getBoxno();
					if (StringUtils.isNotBlank(boxStr)) {
						String[] boxnoList = boxStr.split("\\|");
						log.info("单个海外物流ID(" + expressno + ")拥有多个百威箱子号："
								+ boxStr + ",个数：" + boxnoList.length);
						for (String singBoxno : boxnoList) {
							BoxnoJson boxnoJson = getBoxnoStatusById(queryAPI,singBoxno);
							if (boxnoJson != null) {
								List<BoxnoJson.Boxno> beans = boxnoJson
										.getData();
								if (beans != null) {
									for (BoxnoJson.Boxno boxno : beans) {
										String nodeText = LOGIST_NODE
												.get(node - 5);
										if (StringUtils.isNotBlank(boxno
												.getMessage())
												&& boxno.getMessage().contains(
														nodeText)) {
											log.info("待导入的百威箱子信息：（" + expressno
													+ "）  节点："
													+ boxno.getMessage());
											return boxno;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static List<BoxnoJson.Boxno> getBoxnoLogistNodes(String expressno) {
		List<BoxnoJson.Boxno> boxnos = Collections.EMPTY_LIST;
		if (StringUtils.isBlank(expressno)) {
			return boxnos;
		}

		BsiBuyQueryAPI client = new BsiBuyQueryAPI();
		IBsiBuyQueryAPI queryAPI = client.getBasicHttpBindingIBsiBuyQueryAPI();
		BoxnoJson expressnoJson = getExpressnoById(queryAPI,expressno);
		if (expressnoJson != null) {
			List<BoxnoJson.Boxno> exBeans = expressnoJson.getData();
			boxnos = new ArrayList<BoxnoJson.Boxno>();
			if (exBeans != null) {
				for (BoxnoJson.Boxno exBoxno : exBeans) {
					String boxStr = exBoxno.getBoxno();
					if (StringUtils.isNotBlank(boxStr)) {
						String[] boxnoList = boxStr.split("\\|");
						log.info("单个海外物流ID(" + expressno + ")拥有多个百威箱子号："
								+ boxStr + ",个数：" + boxnoList.length);
						for (String singBoxno : boxnoList) {
							BoxnoJson boxnoJson = getBoxnoStatusById(queryAPI,singBoxno);
							if (boxnoJson != null) {
								List<BoxnoJson.Boxno> beans = boxnoJson
										.getData();
								if(beans != null){
									boxnos.addAll(beans);
								}
							}
						}
					}
				}
			}
		}
		return boxnos;
	}
	
	/**
	 * 获取百威箱子的"扣费金额"
	 * @Method  getBoxnoPriceById
	 * @Return BoxnoJson.Boxno
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月23日 上午10:42:55
	 * @Version 1.0
	 */
	public static BoxnoJson.Boxno getBoxnoPriceById(String boxnoStr) {
		if(StringUtils.isNotBlank(boxnoStr)){
			try {
				BsiBuyQueryAPI client = new BsiBuyQueryAPI();
				IBsiBuyQueryAPI queryAPI = client.getBasicHttpBindingIBsiBuyQueryAPI();
				String content = queryAPI.boxOpen(boxnoStr);
				log.info("百威箱子状态物流原始信息：" + content);
				if (StringUtils.isNotBlank(content)) {
					BoxnoJson boxnoJson = JSON.parseObject(content, BoxnoJson.class);
					if (boxnoJson != null) {
						List<BoxnoJson.Boxno> beans = boxnoJson
								.getData();
						if (beans != null) {
							for (BoxnoJson.Boxno boxno : beans) {
								if (StringUtils.isNotBlank(boxno
										.getMessage())
										&& boxno.getMessage().contains("扣费金额")) {
									
									boxno.setBoxno(boxnoStr);
									log.info("获取百威箱子（" + boxnoStr + "）的扣费金额：" + boxno.getTime());
									return boxno;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("获取百威箱子扣费金额失败:" + boxnoStr, e);
			}
		}
		return null;
	}

	/**
	 * 根据海外物流id获取该包裹里在柏威阶段的箱子号
	 * @Method  getExpressnoById
	 * @Return BoxnoJson
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月23日 上午10:50:46
	 * @Version 1.0
	 */
	private static BoxnoJson getExpressnoById(IBsiBuyQueryAPI queryAPI,String expressno) {
		BoxnoJson expressnoJson = null;
		try {
			String content = queryAPI.open(expressno);
			log.info("获取百威箱子号原始数据：" + content);
			if (StringUtils.isNotBlank(content)) {
				expressnoJson = JSON.parseObject(content,BoxnoJson.class);
			}
		} catch (Exception e) {
			log.error("获取百威箱子号失败:" + expressno, e);
		}
		return expressnoJson;
	}

	/**
	 * 根据boxno获取柏威物流信息
	 * @Method  getBoxnoStatusById
	 * @Return BoxnoJson
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月23日 上午10:50:58
	 * @Version 1.0
	 */
	private static BoxnoJson getBoxnoStatusById(IBsiBuyQueryAPI queryAPI,
			String boxno) {
		BoxnoJson boxnoJson = null;
		try {
			String content = queryAPI.boxOpen(boxno);
			log.info("百威箱子状态物流原始信息：" + content);
			if (StringUtils.isNotBlank(content)) {
				boxnoJson = JSON.parseObject(content, BoxnoJson.class);
			}
		} catch (Exception e) {
			log.error("获取百威箱子物流信息失败:" + boxno, e);
		}
		return boxnoJson;
	}

	public static void main(String[] args) {
		BsiBuyQueryAPI client = new BsiBuyQueryAPI();
		IBsiBuyQueryAPI queryAPI = client.getBasicHttpBindingIBsiBuyQueryAPI();
		List<BoxnoJson.Boxno> expressBeans = getExpressnoById(queryAPI, "9361289878603910905774").getData();
		for(BoxnoJson.Boxno boxno:expressBeans){
			String expressno = boxno.getBoxno();
			System.out.println("海外订单物流ID:" + expressno);
			String[] bs = expressno.split("\\|");
			for(String b:bs){
				List<BoxnoJson.Boxno> boxnoList = getBoxnoStatusById(queryAPI,b).getData();
				for(BoxnoJson.Boxno boxno2:boxnoList){
					System.out.print(boxno2.getTime());
					System.out.print(boxno2.getTitle());
					System.out.println(boxno2.getMessage());
					System.out.println("================");
				}
			}
		}
	}
}
