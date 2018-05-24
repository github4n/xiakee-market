package com.xiakee.domain.smsc;

import java.util.List;

/**
 * 短信发送回执封装类
 * @Product: xiakee-domain
 * @Title: SmscResultBean.java
 * @Package com.xiakee.domain.smsc
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年5月25日 下午5:46:00
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public class SmscResultJson {
	private int status;
	private int count;
	private List<Mids> list;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Mids> getList() {
		return list;
	}

	public void setList(List<Mids> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "===短信发送结果回执信息==SmscResultBean===发送状态（100为全部成功）==" + getStatus() + "==成功条数==" + getCount();
	}

	public static class Mids{
		private String p;
		private String mid;
		private String msg;
		public String getP() {
			return p;
		}
		public void setP(String p) {
			this.p = p;
		}
		public String getMid() {
			return mid;
		}
		public void setMid(String mid) {
			this.mid = mid;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}
