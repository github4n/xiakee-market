package com.xiakee.service;

/**
 * 自动执行任务封装类，通用类型
 * @Product: xiakee-service
 * @Title: AutoExecuteTask.java
 * @Package com.xiakee.service
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年5月25日 下午4:48:17
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public abstract class AutoExecuteTask<T> {
	private T taskBean;
	private int retrySum = 0;//重试次数
	private static final int RETRYSUM = 5;//重试次数

	
	
	public int getRetrySum() {
		return retrySum;
	}

	public void setRetrySum(int retrySum) {
		this.retrySum = retrySum;
	}

	public T getTaskBean() {
		return taskBean;
	}

	public void setTaskBean(T taskBean) {
		this.taskBean = taskBean;
	}
	
	public boolean execute() throws Exception{
		boolean isOK = false;
		if(getTaskBean() != null && getRetrySum() < RETRYSUM){
			isOK = executeTask();
		}
		return isOK;
	}
	
	public abstract boolean executeTask() throws Exception;
}
