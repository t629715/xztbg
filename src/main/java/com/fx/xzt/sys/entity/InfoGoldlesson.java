package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @Author:  tianliya
 * @Description:黄金课堂实体类
 * @Date:10:14 2017/10/17
*/
public class InfoGoldlesson implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 序号
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