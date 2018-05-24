package com.xiakee.service.smsc;

import org.apache.log4j.Logger;

import com.xiakee.dao.smsc.SmscRecordDao;
import com.xiakee.dao.smsc.SmscResultDao;
import com.xiakee.domain.smsc.SmscContentBean;
import com.xiakee.domain.smsc.SmscRecordBean;
import com.xiakee.domain.smsc.SmscResultBean;
import com.xiakee.domain.smsc.SmscResultJson;
import com.xiakee.service.AutoExecuteTask;
import com.xiakee.service.utils.SmscSendUtils;

/**
 * 短信发送任务
 * @Product: xiakee-service
 * @Title: SmscControlTasker.java
 * @Package com.xiakee.service.smsc
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年5月25日 下午6:10:18
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public class SmscControlTasker extends AutoExecuteTask<SmscContentBean> {
	private static Logger log = Logger.getLogger(SmscControlTasker.class);
	
	private SmscRecordDao recordDao;
	private SmscResultDao resultDao;
	
	public SmscControlTasker(SmscRecordDao recordDao,SmscResultDao resultDao) {
		this.recordDao = recordDao;
		this.resultDao = resultDao;
	}
	
	@Override
	public boolean executeTask() {
		boolean isOK = false;
		if(getTaskBean() != null){
			log.info("========开始执行短信发送任务=======");
			SmscResultJson bean = SmscSendUtils.sendSmscTasker(getTaskBean());
			log.info("短信发送结果：" + bean);
			SmscRecordBean recordBean = new SmscRecordBean();
			recordBean.setMobile(getTaskBean().getMobile());
			recordBean.setContent(getTaskBean().getContent());
			recordBean.setTypes(getTaskBean().getType().toCode());
			recordBean.setStatus(bean.getStatus());
			recordBean.setCounts(bean.getCount());
			this.recordDao.addSmscRecored(recordBean);
			for(SmscResultJson.Mids mids:bean.getList()){
				SmscResultBean resultBean = new SmscResultBean();
				resultBean.setSmscId(recordBean.getId());
				resultBean.setMobile(mids.getP());
				resultBean.setMid(mids.getMid());
				resultBean.setMsg(mids.getMsg());
				this.resultDao.addSmscResult(resultBean);
			}
			isOK = true;
		}else {
			log.info("短信发送功能关闭或者短信内容为空");
		}
		return isOK;
	}
}
