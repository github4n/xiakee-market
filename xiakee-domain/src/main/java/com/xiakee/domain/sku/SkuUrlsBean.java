package com.xiakee.domain.sku;

import java.util.Date;

public class SkuUrlsBean {
	private Long id;
	private String skuCode;
	private String url;
	private int defUrl;
	private Date created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDefUrl() {
		return defUrl;
	}

	public void setDefUrl(int defUrl) {
		this.defUrl = defUrl;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
