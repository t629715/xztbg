package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @date 2018/8/30 13:29
 */
public class DeliveryGoldConf implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private  Integer id;

    /**
     *手续费率
     */
    private BigDecimal handlingFee;

    /**
     *加工费(分)
     */
    private Long processingFee;

    /**
     *发票费率
     */
    private BigDecimal invoiceFee;

    /**
     * 物流费(分)
     */
    private Long logisticsFee;

    /**
     *保管费(分) 分/克天
     */
    private Long custodyFee;

    /**
     *免收保管费天数
     */
    private Integer custodyStartDate;

    /**
     *0:自动交割1:手动交割
     */
    private Integer type;

    /**
     *创建时间
     */
    private Date gmtCreate;

    /**
     *修改时间
     */
    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getHandlingFee() {
        return handlingFee;
    }

    public void setHandlingFee(BigDecimal handlingFee) {
        this.handlingFee = handlingFee;
    }

    public Long getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Long processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getInvoiceFee() {
        return invoiceFee;
    }

    public void setInvoiceFee(BigDecimal invoiceFee) {
        this.invoiceFee = invoiceFee;
    }

    public Long getLogisticsFee() {
        return logisticsFee;
    }

    public void setLogisticsFee(Long logisticsFee) {
        this.logisticsFee = logisticsFee;
    }

    public Long getCustodyFee() {
        return custodyFee;
    }

    public void setCustodyFee(Long custodyFee) {
        this.custodyFee = custodyFee;
    }
    public void setCustodyStartDate(Integer custodyStartDate) {
        this.custodyStartDate = custodyStartDate;
    }
    public Integer getCustodyStartDate() {
        return custodyStartDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String toString() {
        return "DeliveryGoldConf{" +
                "id=" + id +
                ", handlingFee='" + handlingFee + '\'' +
                ", processingFee=" + processingFee +
                ", invoiceFee=" + invoiceFee +
                ", logisticsFee=" + logisticsFee +
                ", custodyFee=" + custodyFee +
                ", custodyStartDate=" + custodyStartDate +
                ", type=" + type +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +'\'' +
                 '}';
    }



}
