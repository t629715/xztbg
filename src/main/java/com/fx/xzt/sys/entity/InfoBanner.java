/*
 * @ClassName InfoBanner
 * @Description 
 * @version 1.0
 * @Date 2017-08-25 13:45:24
 */
package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Banner 图
* @Title: InfoBanner.java 
* @Package com.fx.xzt.sys.entity
* @author SYan  
* @date 2017年8月25日 下午1:46:36 
* @version V1.0
 */
public class InfoBanner implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * @Fields serialNo 序号
     */
    private Long serialNo;
    /**
     * @Fields page 页面 1：首页，2：比赛
     */
    private Short page;
    /**
     * @Fields picturePath 图片地址
     */
    private String picturePath;
    /**
     * @Fields appID 应用ID
     */
    private String appID;
    /**
     * @Fields skipPath 跳转地址
     */
    private String skipPath;
    /**
     * @Fields skipType 跳转类型 1：站内，2：站外
     */
    private Short skipType;
    /**
     * @Fields description 描述
     */
    private String description;
    /**
     * @Fields sortNo 排序号
     */
    private Integer sortNo;
    /**
     * @Fields valid 有效标识 0：无效，1：有效
     */
    private Short valid;
    /**
     * @Fields createTime 创建时间
     */
    private Date createTime;
    /**
     * @Fields modifyTime 修改时间
     */
    private Date modifyTime;
    /**
     * @Fields operator 操作人
     */
    private String operator;

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public Short getPage() {
        return page;
    }

    public void setPage(Short page) {
        this.page = page;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath == null ? null : picturePath.trim();
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID == null ? null : appID.trim();
    }

    public String getSkipPath() {
        return skipPath;
    }

    public void setSkipPath(String skipPath) {
        this.skipPath = skipPath == null ? null : skipPath.trim();
    }

    public Short getSkipType() {
        return skipType;
    }

    public void setSkipType(Short skipType) {
        this.skipType = skipType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Short getValid() {
        return valid;
    }

    public void setValid(Short valid) {
        this.valid = valid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}