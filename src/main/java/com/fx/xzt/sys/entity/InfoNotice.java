/*
 * @ClassName InfoNotice
 * @Description 
 * @version 1.0
 * @Date 2017-09-11 14:52:57
 */
package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class InfoNotice implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * @Fields serialNo 序号
     */
    private Long serialNo;
    /**
     * @Fields title 标题
     */
    private String title;
    /**
     * @Fields content 内容
     */
    private String content;
    /**
     * @Fields appID 应用ID
     */
    private String appID;
    /**
     * @Fields skipPath 跳转路径
     */
    private String skipPath;
    /**
     * @Fields skipType 跳转类型 1：站内，2：站外
     */
    private Short skipType;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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