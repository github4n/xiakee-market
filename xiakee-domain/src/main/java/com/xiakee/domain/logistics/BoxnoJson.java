package com.xiakee.domain.logistics;

import java.util.List;

public class BoxnoJson {
	private int code;
	private String msg;
	private List<Boxno> data;
	
	
	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public List<Boxno> getData() {
		return data;
	}


	public void setData(List<Boxno> data) {
		this.data = data;
	}


	public static class Boxno{
		private String time;
		private String message;
		private String boxno;
		private String title;
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getBoxno() {
			return boxno;
		}
		public void setBoxno(String boxno) {
			this.boxno = boxno;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
	}
}
