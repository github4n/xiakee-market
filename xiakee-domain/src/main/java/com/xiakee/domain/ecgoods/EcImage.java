package com.xiakee.domain.ecgoods;

public class EcImage {
    /** 图片ID */
    private String imageId;

    /** 存储引擎 */
    private String storage;

    /** 图片名称 */
    private String imageName;

    private String ident;

    /** 网址 */
    private String url;

    /** 大图唯一标识 */
    private String lIdent;

    /** 大图URL地址 */
    private String lUrl;

    /** 中图唯一标识 */
    private String mIdent;

    /** 中图URL地址 */
    private String mUrl;

    /** 小图唯一标识 */
    private String sIdent;

    /** 小图URL地址 */
    private String sUrl;

    /** 宽度 */
    private Integer width;

    /** 高度 */
    private Integer height;

    /** 有水印 */
    private String watermark;

    /** 更新时间 */
    private Integer lastModified;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId == null ? null : imageId.trim();
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage == null ? null : storage.trim();
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName == null ? null : imageName.trim();
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident == null ? null : ident.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getlIdent() {
        return lIdent;
    }

    public void setlIdent(String lIdent) {
        this.lIdent = lIdent == null ? null : lIdent.trim();
    }

    public String getlUrl() {
        return lUrl;
    }

    public void setlUrl(String lUrl) {
        this.lUrl = lUrl == null ? null : lUrl.trim();
    }

    public String getmIdent() {
        return mIdent;
    }

    public void setmIdent(String mIdent) {
        this.mIdent = mIdent == null ? null : mIdent.trim();
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl == null ? null : mUrl.trim();
    }

    public String getsIdent() {
        return sIdent;
    }

    public void setsIdent(String sIdent) {
        this.sIdent = sIdent == null ? null : sIdent.trim();
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl == null ? null : sUrl.trim();
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark == null ? null : watermark.trim();
    }

    public Integer getLastModified() {
        return lastModified;
    }

    public void setLastModified(Integer lastModified) {
        this.lastModified = lastModified;
    }
}