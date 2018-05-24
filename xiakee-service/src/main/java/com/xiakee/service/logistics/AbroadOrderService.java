package com.xiakee.service.logistics;

import java.util.List;

import com.xiakee.domain.logistics.AbroadOrderBean;
import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.logistics.DeclareGoodsBean;
import com.xiakee.domain.yz.YzorderInfoBean;

public interface AbroadOrderService {
	Long addAbroadOrderBean(AbroadOrderBean bean);
	
	/**
	 * 根据abroadId获取还没填写价格等信息的其中一条
	 * @Method  getYzorderInfoBeanByAbroadId
	 * @Return YzorderInfoBean
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月2日 下午2:52:58
	 * @Version 1.0
	 */
	YzorderInfoBean getYzorderInfoBeanByAbroadId(Long abroadId);

	/**
	 * 根据真实的海外订单号，字符串获取具体货品信息
	 * @Method  getYzorderInfoByAbroadName
	 * @Return List<BoxnoBean>
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年7月2日 下午5:48:43
	 * @Version 1.0
	 */
	List<BoxnoBean> getYzorderInfoByAbroadName(String outAbroadId);
	
	List<AbroadOrderBean> getAllAbroadOrderByPage(int page,String outOrderId,String expressno);
}
