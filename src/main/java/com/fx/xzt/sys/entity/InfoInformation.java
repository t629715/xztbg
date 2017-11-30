package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class InfoInformation implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     *序号
     */
    private Long infoId;

    /**
     *标题
     */
    private String title;

    /**
     *内容地址
     */
    private String contentpath;

    /**
     *内容来源类型   1：站内，2：站外，不能编辑'
     */
    private Short contentFromType;

    /**
     *咨询来源
     */
    private String informationFrom;

    /**
     *创建时间
     */
    private Date createtime;

    /**
     *发布时间
     */
    private Date releasetime;

    /**
     *图片地址
     */
    private String imagepath;

    /**
     *状态
     */
    private Short state;

    /**
     *操作人
     */
    private String operator;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContentpath() {
        return contentpath;
    }

    public void setContentpath(String contentpath) {
        this.contentpath = contentpath == null ? null : contentpath.trim();
    }

    public Short getContentFromType() {
        return contentFromType;
    }

    public void setContentFromType(Short contentFromType) {
        this.contentFromType = contentFromType;
    }

    public String getInformationFrom() {
        return informationFrom;
    }

    public void setInformationFrom(String informationFrom) {
        this.informationFrom = informationFrom;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}