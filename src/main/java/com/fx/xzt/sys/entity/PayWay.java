package com.fx.xzt.sys.entity;

import java.io.Serializable;

/**
 * 付款方式实体类
 */
public class PayWay implements Serializable{

    /**
     * 付款方式主键
     */
    private Integer id;
    /**
     * 付款方式
     */
    private Short payWay;
    /**
     * 支持的设备
     */
    private Short payDevice;
    /**
     * 支付URL
     */
    private String payUrl;
    /**
     * 异步通知URL
     */
    private String notifyUrl;
    /**
     * 同步回调URL
     */
    private String returnUrl;
    /**
     * 排序号
     */
    private Short sort;
    /**
     * 状态 0：不可用 1：可用
     */
    private Short status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getPayWay() {
        return payWay;
    }

    public void setPayWay(Short payWay) {
        this.payWay = payWay;
    }

    public Short getPayDevice() {
        return payDevice;
    }

    public void setPayDevice(Short payDevice) {
        this.payDevice = payDevice;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl == null ? null : payUrl.trim();
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PayWay{" +
                "id=" + id +
                ", payWay=" + payWay +
                ", payDevice=" + payDevice +
                ", payUrl='" + payUrl + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                '}';
    }
}