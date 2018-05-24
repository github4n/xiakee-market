package com.xiakee.service.logistics;

import com.xiakee.domain.logistics.LogistCompBean;

public interface LogistCompService {
	/**
	 * 添加打印标识符
	 * @Method  addLogistComp
	 * @Return Integer
	 * @Author 谢坚柏
	 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
	 * @Date 2015年5月25日 下午2:32:21
	 * @Version 1.0
	 */
	Integer addLogistComp(LogistCompBean bean);
	LogistCompBean findLogistComp(String infoId);
}
