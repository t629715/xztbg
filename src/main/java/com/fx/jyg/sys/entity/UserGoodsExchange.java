/*
 * @ClassName UserGoodsExchange
 * @Description 
 * @version 1.0
 * @Date 2017-08-28 17:35:18
 */
package com.fx.jyg.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 兑换商品
* @Title: UserGoodsExchange.java 
* @Package com.fx.jyg.sys.entity 
* @Description: TODO
* @author SYan  
* @date 2017年8月28日 下午5:35:52 
* @version V1.0
 */
public class UserGoodsExchange implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * @Fields serialNo 序号
     */
    private Long serialNo;
    /**
     * @Fields userID 用户ID
     */
    private Long userID;
    /**
     * @Fields goodsID 商品ID
     */
    private Long goodsID;
    /**
     * @Fields exchangeNum 兑换数量
     */
    private Integer exchangeNum;
    /**
     * @Fields totalUAmt U币金额
     */
    private BigDecimal totalUAmt;
    /**
     * @Fields rechargePhone 充值电话
     */
    private String rechargePhone;
    /**
     * @Fields exchangeTime 兑换时间
     */
    private Date exchangeTime;
    /**
     * @Fields status  '状态 0：未发放 1：已发放',
     */
    private Short status;

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(Long goodsID) {
        this.goodsID = goodsID;
    }

    public Integer getExchangeNum() {
        return exchangeNum;
    }

    public void setExchangeNum(Integer exchangeNum) {
        this.exchangeNum = exchangeNum;
    }

    public BigDecimal getTotalUAmt() {
        return totalUAmt;
    }

    public void setTotalUAmt(BigDecimal totalUAmt) {
        this.totalUAmt = totalUAmt;
    }

    public String getRechargePhone() {
        return rechargePhone;
    }

    public void setRechargePhone(String rechargePhone) {
        this.rechargePhone = rechargePhone == null ? null : rechargePhone.trim();
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}