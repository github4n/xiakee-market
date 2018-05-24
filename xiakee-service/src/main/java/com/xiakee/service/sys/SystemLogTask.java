package com.xiakee.service.sys;

import org.apache.log4j.Logger;

import com.xiakee.dao.sys.SystemLogDao;
import com.xiakee.domain.sys.SystemLogBean;
import com.xiakee.service.AutoExecuteTask;

public class SystemLogTask extends AutoExecuteTask<SystemLogBean> {
	private static Logger log = Logger.getLogger(SystemLogTask.class);
	
	private SystemLogDao systemLogDao;
	
	public SystemLogTask(SystemLogDao systemLogDao) {
		this.systemLogDao = systemLogDao;
	}

	@Override
	public boolean executeTask() throws Exception {
		boolean isOK = false;
		if (getTaskBean() != null && this.systemLogDao != null) {
			Integer sum = this.systemLogDao.addSystemLogBean(getTaskBean());
			if(sum != null && sum > 0){
				log.info("操作日志：" + getTaskBean());
				isOK = true;
			}
		}
		return isOK;
	}

}
