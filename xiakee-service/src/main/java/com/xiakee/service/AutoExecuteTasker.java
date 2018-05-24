package com.xiakee.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;


/**
 * 通用类自动执行任务
 * @Product: xiakee-service
 * @Title: AutoExecuteTasker.java
 * @Package com.xiakee.service
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年5月25日 下午4:51:27
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public class AutoExecuteTasker implements Runnable {
	private static Logger log = Logger.getLogger(AutoExecuteTasker.class);
	private static final BlockingQueue<AutoExecuteTask> AUTO_EXECUTE_TASK_QUEUE = new LinkedBlockingQueue<AutoExecuteTask>();
	
	@Override
	public void run() {
		AutoExecuteTask task = null;
		while(true){
			try {
				if(task != null){
					boolean isOK = task.execute();
//					log.info("=========执行通用类型任务状态====" + isOK + "======bean==" + task.getTaskBean().toString() + "===任务队列总量" + AUTO_EXECUTE_TASK_QUEUE.size());
				}
			} catch (Exception e) {
				log.info("==========通用类型自动执行任务失败=========" + task,e);
				int retrySum = task.getRetrySum();
				retrySum ++;
				task.setRetrySum(retrySum);
				addAutoExecuteTasker(task);
			}finally{
				try {
					task = AUTO_EXECUTE_TASK_QUEUE.take();
				} catch (Exception e) {
					log.info("==========获取通用类型自动执行任务失败==============",e);
				}
			}
		}
	}
	
	public static void addAutoExecuteTasker(AutoExecuteTask bean){
		try {
			AUTO_EXECUTE_TASK_QUEUE.put(bean);
//			log.info("==========成功添加通用型任务==============" + bean.getTaskBean());
		} catch (Exception e) {
			log.info("=========通用类型执行任务========" + bean,e);
		}
	}
}
