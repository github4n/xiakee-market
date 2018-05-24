package com.xiakee.domain.yz;

import java.io.Serializable;
import java.util.List;

public class YouzanResponseJson implements Serializable {
	private Response response;
	

	public Response getResponse() {
		return response;
	}


	public void setResponse(Response response) {
		this.response = response;
	}


	public static class Response{
		private int total_results;
		private List<Trades> trades;
		private String has_next;
		public int getTotal_results() {
			return total_results;
		}
		public void setTotal_results(int total_results) {
			this.total_results = total_results;
		}
		public List<Trades> getTrades() {
			return trades;
		}
		public void setTrades(List<Trades> trades) {
			this.trades = trades;
		}
		public String getHas_next() {
			return has_next;
		}
		public void setHas_next(String has_next) {
			this.has_next = has_next;
		}
	}
	
	public static class Trades{
		private String tid;
		private String title;
		private String price;
		private String payment;
		private String receiver_name;
		private String receiver_city;
		private String receiver_district;
		private String receiver_state;
		private String receiver_address;
		private String receiver_mobile;
		private String receiver_zip;
		private String status;
		private String created;
		private String update_time;
		private String pay_time;
		private List<order> orders;
		
		public String getTid() {
			return tid;
		}
		public void setTid(String tid) {
			this.tid = tid;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getReceiver_city() {
			return receiver_city;
		}
		public void setReceiver_city(String receiver_city) {
			this.receiver_city = receiver_city;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getReceiver_district() {
			return receiver_district;
		}
		public void setReceiver_district(String receiver_district) {
			this.receiver_district = receiver_district;
		}
		public String getReceiver_name() {
			return receiver_name;
		}
		public void setReceiver_name(String receiver_name) {
			this.receiver_name = receiver_name;
		}
		public String getReceiver_state() {
			return receiver_state;
		}
		public void setReceiver_state(String receiver_state) {
			this.receiver_state = receiver_state;
		}
		public String getReceiver_address() {
			return receiver_address;
		}
		public void setReceiver_address(String receiver_address) {
			this.receiver_address = receiver_address;
		}
		public String getReceiver_mobile() {
			return receiver_mobile;
		}
		public void setReceiver_mobile(String receiver_mobile) {
			this.receiver_mobile = receiver_mobile;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getPayment() {
			return payment;
		}
		public void setPayment(String payment) {
			this.payment = payment;
		}
		public String getCreated() {
			return created;
		}
		public void setCreated(String created) {
			this.created = created;
		}
		public String getUpdate_time() {
			return update_time;
		}
		public void setUpdate_time(String update_time) {
			this.update_time = update_time;
		}
		public String getPay_time() {
			return pay_time;
		}
		public void setPay_time(String pay_time) {
			this.pay_time = pay_time;
		}
		public String getReceiver_zip() {
			return receiver_zip;
		}
		public void setReceiver_zip(String receiver_zip) {
			this.receiver_zip = receiver_zip;
		}
		public List<order> getOrders() {
			return orders;
		}
		public void setOrders(List<order> orders) {
			this.orders = orders;
		}
	}
	
	public static class order{
		private String sku_properties_name;
		private String title;
		private String num;
		private String price;
		
		public String getSku_properties_name() {
			return sku_properties_name;
		}
		public void setSku_properties_name(String sku_properties_name) {
			this.sku_properties_name = sku_properties_name;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		
	}
}