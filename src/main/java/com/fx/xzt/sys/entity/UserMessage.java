package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* @Title: UserMessage.java 
* @Package com.fx.xzt.sys.entity
* @Description: TODO
* @author SYan  
* @date 2017年8月23日 下午6:06:49 
* @version V1.0
 */
public class UserMessage implements Serializable {
    private Long msgID;

    private Long userID;

    private String msgTypeID;

    private Date msgTime;

    private String msgContent;

    private String appID;

    private String skipPath;

    private Short skipType;

    private String msgFrom;

    private static final long serialVersionUID = 1L;

    public Long getMsgID() {
        return msgID;
    }

    public void setMsgID(Long msgID) {
        this.msgID = msgID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getMsgTypeID() {
        return msgTypeID;
    }

    public void setMsgTypeID(String msgTypeID) {
        this.msgTypeID = msgTypeID == null ? null : msgTypeID.trim();
    }

    public Date getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
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

    public String getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom == null ? null : msgFrom.trim();
    }
}