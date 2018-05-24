package com.xiakee.service.logistics;

import java.util.List;
import java.util.Map;

import com.xiakee.bean.UdfexDeclare;
import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.logistics.DeclareGoodsBean;

public interface BoxnoService {
	/**
	 * 获取所有的尚未填写国外包裹ID的信息
	 * @Method  getAllNoExpressnoBean
	 * @Return List<BoxnoBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月21日 下午4:19:09
	 * @Version 1.0
	 */
	List<BoxnoBean> getAllNoExpressnoBean();
	
	/**
	 * 获取所有已经填写国外物流单号的信息
	 * @Method  getAllExpressnoedBean
	 * @Return List<BoxnoBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月28日 下午2:29:40
	 * @Version 1.0
	 */
	List<BoxnoBean> getAllExpressnoedBean();
	
	/**
	 * 标识国外包裹信息中的物流ID号
	 * @Method  updateExpressno
	 * @Return Integer
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年8月26日 下午4:07:18
	 * @Version 1.0
	 */
	Integer updateExpressno(Long infoId,String expressno,String transfer_id);
	
	/**
	 * 获取所有可以申报的信息列表
	 * @Method  getAllDeclareBean
	 * @Return List<BoxnoBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月22日 下午1:56:33
	 * @Version 1.0
	 */
	List<BoxnoBean> getAllDeclareBean(Map<String, Object> param);
	
	/**
	 * 获取所有已经申报的信息
	 * @Method  getDeclaredBean
	 * @Return List<BoxnoBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月28日 下午1:55:38
	 * @Version 1.0
	 */
	List<BoxnoBean> getDeclaredBean();
	
	/**
	 * 获取需要申报的信息，提供页面进行修改
	 * @param infoId
	 * @return
	 */
	DeclareGoodsBean getDeclareGoodsBean(String infoId);
	
	/**
	 * 获取需要递优申报的信息，提供页面进行修改
	 * @param infoId
	 * @return
	 */
	List<UdfexDeclare> getUdfexDeclareGoodsBean(String expressno);

	/**
	 * 执行申报柏威物流信息功能
	 * @Method  declareBaiweiOrderInfo
	 * @Return void
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月22日 下午8:03:05
	 * @Version 1.0
	 */
	void declareBaiweiOrderInfo(DeclareGoodsBean bean);
	void declareBaiweiOrderInfo(List<DeclareGoodsBean> beans);
}
