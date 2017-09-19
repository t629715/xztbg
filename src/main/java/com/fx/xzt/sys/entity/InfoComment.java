/*
 * @ClassName InfoComment
 * @Description 
 * @version 1.0
 * @Date 2017-08-24 15:11:31
 */
package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class InfoComment implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * @Fields serialNo 序号
     */
    private Long serialNo;
    /**
     * @Fields informationID 资讯ID
     */
    private Long informationID;
    /**
     * @Fields userID 用户ID
     */
    private Long userID;
    /**
     * @Fields commentContent 评论内容
     */
    private String commentContent;
    /**
     * @Fields commentTime 评论时间
     */
    private Date commentTime;
    /**
     * @Fields state 状态 0：隐藏，1：显示
     */
    private Short state;

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public Long getInformationID() {
        return informationID;
    }

    public void setInformationID(Long informationID) {
        this.informationID = informationID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}