/*
 * @ClassName Goods
 * @Description 
 * @version 1.0
 * @Date 2017-08-28 09:30:02
 */
package com.fx.jyg.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
* @Title: Goods.java 
* @Package com.fx.jyg.sys.entity 
* @Description: TODO
* @author SYan  
* @date 2017年8月28日 上午11:13:28 
* @version V1.0
 */
public class Goods implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * @Fields goodsID 商品ID　G+yymmddhhMMssSSS+4位随机数
     */
    private String goodsID;
    /**
     * @Fields goodsCode 商品编码
     */
    private String goodsCode;
    /**
     * @Fields goodsName 商品名称
     */
    private String goodsName;
    /**
     * @Fields goodsType 商品类型 00:虚拟 11：实物
     */
    private String goodsType;
    /**
     * @Fields inventory 库存
     */
    private Integer inventory;
    /**
     * @Fields UAmt U币金额
     */
    private BigDecimal UAmt;
    /**
     * @Fields onlineTime 上线日期
     */
    private Date onlineTime;
    /**
     * @Fields expiryTime 失效日期
     */
    private Date expiryTime;
    /**
     * @Fields imgPath 图片路径
     */
    private String imgPath;
    /**
     * @Fields status 状态 0：下线 1：上线
     */
    private Short status;

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID == null ? null : goodsID.trim();
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getUAmt() {
        return UAmt;
    }

    public void setUAmt(BigDecimal UAmt) {
        this.UAmt = UAmt;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}