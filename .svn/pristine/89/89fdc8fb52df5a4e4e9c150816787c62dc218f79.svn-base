package com.xiakee.service.logistics;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.xiakee.domain.logistics.DeclareGoodsBean;
import com.xiakee.service.AutoExecuteTask;
import com.xiakee.service.utils.DeclareBaiweiUtil;

public class BaiweiDeclareListTasker extends AutoExecuteTask<List<DeclareGoodsBean>> {
	private static Logger log = Logger.getLogger(BaiweiDeclareListTasker.class);
	
	@Override
	public boolean executeTask() {
		boolean isOK = false;
		if(getTaskBean() != null && getTaskBean().size() > 0){
			String message = DeclareBaiweiUtil.declareInfoByGoodsBean(getTaskBean());
			if(StringUtils.isNotBlank(message) && message.length() > 20){
				message = message.substring(0,20);
				if(message.contains("false")){//如果申报失败，默认修改为待出库方式申报
					log.info("合箱申报返回错误，任务取消中=======");
				}else {
					isOK = true;
				}
			}
		}	
		return isOK;
	}

}
