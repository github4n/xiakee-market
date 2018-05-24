package com.xiakee.service.logistics;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.xiakee.dao.logistics.BoxnoDao;
import com.xiakee.domain.logistics.BoxnoBean;
import com.xiakee.domain.logistics.DeclareGoodsBean;
import com.xiakee.service.AutoExecuteTask;
import com.xiakee.service.AutoExecuteTasker;
import com.xiakee.service.utils.DeclareBaiweiUtil;

public class BaiweiDeclareTasker extends AutoExecuteTask<DeclareGoodsBean> {
	private static Logger log = Logger.getLogger(BaiweiDeclareTasker.class);
	private static final String ERROR_MESSAGE = "[{\"Result\":true,\"ErrorInfo\":\"\"}]";
	
	private BoxnoDao boxnoDao;
	
	public BaiweiDeclareTasker(BoxnoDao boxnoDao) {
		this.boxnoDao = boxnoDao;
	}
	
	@Override
	public boolean executeTask() {
		boolean isOK = false;
		if(getTaskBean() != null){
			String message = DeclareBaiweiUtil.declareInfoByGoodsBean(getTaskBean());
			if(StringUtils.isNotBlank(message) && message.length() > 20){
				BoxnoBean boxnoBean = new BoxnoBean();
				boxnoBean.setInfoId(getTaskBean().getInfoId());
				if(StringUtils.equals(ERROR_MESSAGE, message)){//如果申报失败，默认修改为待出库方式申报
					boxnoBean.setDeclared(1);//成功代码
					log.info("申报成功：" + getTaskBean());
					isOK = true;
				}else {
					boxnoBean.setDeclared(2);//成功代码
					log.info("申报失败：" + message);
				}
				boxnoDao.updateDeclate(boxnoBean);
			}
		}	
		return isOK;
	}

}
