package com.xiakee.domain.material;

import java.math.BigDecimal;
import java.util.Date;

public class Material {
	/** 素材ID */
	private Long materialId;

	/** 素材名称 */
	private String materialName;

	/** url */
	private String materialUrl;

	/** 销售价 */
	private BigDecimal price;

	/** 市场价 */
	private BigDecimal mktprice;

	/** 描述 */
	private String materialDesc;

	/** 图片 */
	private String materialImage;

	/** 状态 */
	private Integer materialStatus;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date updateTime;

	/** 最后修改人 */
	private String lastUpdateUser;

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName == null ? null : materialName.trim();
	}

	public String getMaterialUrl() {
		return materialUrl;
	}

	public void setMaterialUrl(String materialUrl) {
		this.materialUrl = materialUrl == null ? null : materialUrl.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMktprice() {
		return mktprice;
	}

	public void setMktprice(BigDecimal mktprice) {
		this.mktprice = mktprice;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc == null ? null : materialDesc.trim();
	}

	public String getMaterialImage() {
		return materialImage;
	}

	public void setMaterialImage(String materialImage) {
		this.materialImage = materialImage == null ? null : materialImage.trim();
	}

	public Integer getMaterialStatus() {
		return materialStatus;
	}

	public void setMaterialStatus(Integer materialStatus) {
		this.materialStatus = materialStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
	}
}