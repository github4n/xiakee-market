package com.xiakee.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.xiakee.dao.smsc.SmscRecordDao;
import com.xiakee.dao.smsc.SmscResultDao;
import com.xiakee.dao.sys.SystemLogDao;
import com.xiakee.domain.smsc.SmscContentBean;
import com.xiakee.domain.smsc.SmscRecordBean;
import com.xiakee.domain.smsc.SmscResultBean;
import com.xiakee.domain.smsc.SmscResultJson;
import com.xiakee.domain.sys.SystemLogBean;
import com.xiakee.ecdao.order.EcIdcardDao;
import com.xiakee.service.AutoExecuteTasker;
import com.xiakee.service.smsc.SmscControlTasker;
import com.xiakee.service.sys.SystemLogService;
import com.xiakee.service.sys.SystemLogTask;
import com.xiakee.service.utils.SmscSendUtils;
import com.xiakee.service.utils.SmscTempletUtil;

@Service
public class SystemLogServiceImpl implements SystemLogService {

	@Autowired
	private SystemLogDao systemLogDao;
	
	@Autowired
	private EcIdcardDao idcardDao;
	@Autowired
	private SmscRecordDao recordDao;
	@Autowired
	private SmscResultDao resultDao;
	
	@Override
	public boolean addSystemLogBean(SystemLogBean bean) {
		if(bean != null){
			SystemLogTask task = new SystemLogTask(systemLogDao);
			task.setTaskBean(bean);
			AutoExecuteTasker.addAutoExecuteTasker(task);
			return true;
		}
		return false;
	}

	/**
	 * 一次性执行任务
	 */
	@Override
	public void sendMemberFriday() {
		List<String> mobiles = idcardDao.getAllMemberAddrsMobiles();
//		mobiles.add("13401190238");
		String content = "【遐客行】亲，感恩有你！这个感恩节，小遐最最不能忘记的，就是您啦！我们正在成长，给您送上黑色星期五运动装备全球购的优惠券，50元、100元随便领，领取地址： http://www.xiakee.com/wap/action-h5.html ,请您笑纳。请来 xiakee.com 看看黑五专场，惊喜不断噢！退订回N";
		boolean begin = false;
		for(String mobile:mobiles){
			if(StringUtils.isNotBlank(mobile)){
				if(!begin){
					if(StringUtils.equals("13482164964", mobile)){
						begin = true;
					}
					continue;
				}
				
				try {
					SmscContentBean smsc = new SmscContentBean();
					smsc.setContent(content);
					smsc.setMobile(mobile);
					SmscResultJson bean = SmscSendUtils.sendSmscTasker(smsc);
					
					SmscRecordBean recordBean = new SmscRecordBean();
					recordBean.setMobile(mobile);
					recordBean.setContent(content);
					recordBean.setTypes(1);
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
				} catch (Exception e) {
					
				}
			}
		}
	}

}
