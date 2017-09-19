package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 信息发布
* @Title: InfoInforMation.java 
* @Package com.fx.xzt.sys.entity
* @Description: TODO
* @author SYan  
* @date 2017年8月14日 下午3:02:40 
* @version V1.0
 */
public class InfoInforMation implements Serializable {
    private Long serialno;

    private String title;

    private String contentpath;

    private Short contentfromtype;

    private String informationfrom;

    private Date createtime;

    private Date releasetime;

    private Short state;

    private String operator;
    
    private Short topState;
    
    /**
     * @Fields image1Path 图片路径
     */
    private String image1Path;
    /**
     * @Fields image2Path 图片路径
     */
    private String image2Path;
    /**
     * @Fields image3Path 图片路径
     */
    private String image3Path;

    private static final long serialVersionUID = 1L;

    public Long getSerialno() {
        return serialno;
    }

    public void setSerialno(Long serialno) {
        this.serialno = serialno;
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

    public Short getContentfromtype() {
        return contentfromtype;
    }

    public void setContentfromtype(Short contentfromtype) {
        this.contentfromtype = contentfromtype;
    }

    public String getInformationfrom() {
        return informationfrom;
    }

    public void setInformationfrom(String informationfrom) {
        this.informationfrom = informationfrom == null ? null : informationfrom.trim();
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
        this.operator = operator == null ? null : operator.trim();
    }
    
    public Short getTopState() {
        return topState;
    }

    public void setTopState(Short topState) {
        this.topState = topState;
    }
    
    public String getImage1Path() {
        return image1Path;
    }

    public void setImage1Path(String image1Path) {
        this.image1Path = image1Path == null ? null : image1Path.trim();
    }

    public String getImage2Path() {
        return image2Path;
    }

    public void setImage2Path(String image2Path) {
        this.image2Path = image2Path == null ? null : image2Path.trim();
    }

    public String getImage3Path() {
        return image3Path;
    }

    public void setImage3Path(String image3Path) {
        this.image3Path = image3Path == null ? null : image3Path.trim();
    }
}