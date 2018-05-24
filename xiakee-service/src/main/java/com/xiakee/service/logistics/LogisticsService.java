package com.xiakee.service.logistics;

import java.util.List;

import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.yz.YzordersBean;

public interface LogisticsService {
	YzordersBean addLogistics(String orderid);
	
	/**
	 * 获取所有尚未填写国外物流信息的物品列表
	 * @Method  displayAllLogistics
	 * @Return List<YzordersBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月20日 上午9:58:32
	 * @Version 1.0
	 */
	List<YzordersBean> displayAllLogistics(int page);
	
	Integer updateBoxnoInfo(BoxnoBean bean);
	
	/**
	 * 撤销上次录入的错误信息
	 * @Method  resetBoxnoInfo
	 * @Return Integer
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月2日 下午4:07:46
	 * @Version 1.0
	 */
	Integer resetBoxnoInfo(Long infoId,Long abroadId);
	
	/**
	 * 删除已经录入的海外订单信息
	 * @Method  deleteAbroadExpressno
	 * @Return Integer
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月2日 下午4:29:42
	 * @Version 1.0
	 */
	Integer deleteAbroadExpressno(String outOrderId);
}
