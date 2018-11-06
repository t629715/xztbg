package com.fx.xzt.sys.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "gold_buy_back_conf")
public class GoldBuyBackConf {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 公司地址
     */
    @Column(name = "company_address")
    private String companyAddress;

    /**
     * 公司电话
     */
    @Column(name = "company_phone")
    private String companyPhone;

    /**
     * 收件人
     */
    private String name;

    /**
     * 上门取货时间
     */
    @Column(name = "pick_up_time")
    private String pickUpTime;

    @Column(name = "pick_up_date_revise")
    private Integer pickUpDateRevise;

    /**
     * 调整值
     */
    @Column(name = "revise_value")
    private BigDecimal reviseValue;

    /**
     * 固定回收价格 (由type决定)
     */
    @Column(name = "revise_price")
    private BigDecimal revisePrice;

    /**
     * 回收价取值方式、0:实时金价+调整值、1:昨收价+调整值、2:固定值(revise_price)
     */
    private Short type;

    /**
     * 回购状态、0:正常回购  1禁止回购  2:强制回购
     */
    private Short state;

    /**
     *  本条配置是否使用:0否1是 (只能有一条生效[值为1])
     */
    @Column(name = "is_status")
    private Boolean isStatus;

    /**
     * 每天可预约次数
     */
    @Column(name = "pre_num")
    private Integer preNum;

    /**
     * 预约单失效分钟数
     */
    @Column(name = "invalid_time")
    private Integer invalidTime;

    /**
     * 备用字段
     */
    private String spare;

    /**
     * 创建时间
     */
    @Column(name = "gtm_create")
    private Date gtmCreate;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "max_gold_weight")
    private BigDecimal maxGoldWeight;
    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取公司地址
     *
     * @return company_address - 公司地址
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 设置公司地址
     *
     * @param companyAddress 公司地址
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * 获取公司电话
     *
     * @return company_phone - 公司电话
     */
    public String getCompanyPhone() {
        return companyPhone;
    }

    /**
     * 设置公司电话
     *
     * @param companyPhone 公司电话
     */
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    /**
     * 获取收件人
     *
     * @return name - 收件人
     */
    public String getName() {
        return name;
    }

    /**
     * 设置收件人
     *
     * @param name 收件人
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取上门取货时间
     *
     * @return pick_up_time - 上门取货时间
     */
    public String getPickUpTime() {
        return pickUpTime;
    }

    /**
     * 设置上门取货时间
     *
     * @param pickUpTime 上门取货时间
     */
    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    /**
     * 获取调整值
     *
     * @return revise_value - 调整值
     */
    public BigDecimal getReviseValue() {
        return reviseValue;
    }

    /**
     * 设置调整值
     *
     * @param reviseValue 调整值
     */
    public void setReviseValue(BigDecimal reviseValue) {
        this.reviseValue = reviseValue;
    }

    /**
     * 获取固定回收价格 (由type决定)
     *
     * @return revise_price - 固定回收价格 (由type决定)
     */
    public BigDecimal getRevisePrice() {
        return revisePrice;
    }

    /**
     * 设置固定回收价格 (由type决定)
     *
     * @param revisePrice 固定回收价格 (由type决定)
     */
    public void setRevisePrice(BigDecimal revisePrice) {
        this.revisePrice = revisePrice;
    }

    /**
     * 获取回收价取值方式、0:实时金价+调整值、1:昨收价+调整值、2:固定值(revise_price)
     *
     * @return type - 回收价取值方式、0:实时金价+调整值、1:昨收价+调整值、2:固定值(revise_price)
     */
    public Short getType() {
        return type;
    }

    /**
     * 设置回收价取值方式、0:实时金价+调整值、1:昨收价+调整值、2:固定值(revise_price)
     *
     * @param type 回收价取值方式、0:实时金价+调整值、1:昨收价+调整值、2:固定值(revise_price)
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * 获取回购状态、0:正常回购  1禁止回购  2:强制回购
     *
     * @return state - 回购状态、0:正常回购  1禁止回购  2:强制回购
     */
    public Short getState() {
        return state;
    }

    /**
     * 设置回购状态、0:正常回购  1禁止回购  2:强制回购
     *
     * @param state 回购状态、0:正常回购  1禁止回购  2:强制回购
     */
    public void setState(Short state) {
        this.state = state;
    }

    /**
     * 获取 本条配置是否使用:0否1是 (只能有一条生效[值为1])
     *
     * @return is_status -  本条配置是否使用:0否1是 (只能有一条生效[值为1])
     */
    public Boolean getIsStatus() {
        return isStatus;
    }

    /**
     * 设置 本条配置是否使用:0否1是 (只能有一条生效[值为1])
     *
     * @param isStatus  本条配置是否使用:0否1是 (只能有一条生效[值为1])
     */
    public void setIsStatus(Boolean isStatus) {
        this.isStatus = isStatus;
    }

    /**
     * 获取每天可预约次数
     *
     * @return pre_num - 每天可预约次数
     */
    public Integer getPreNum() {
        return preNum;
    }

    /**
     * 设置每天可预约次数
     *
     * @param preNum 每天可预约次数
     */
    public void setPreNum(Integer preNum) {
        this.preNum = preNum;
    }

    /**
     * 获取预约单失效分钟数
     *
     * @return invalid_time - 预约单失效分钟数
     */
    public Integer getInvalidTime() {
        return invalidTime;
    }

    /**
     * 设置预约单失效分钟数
     *
     * @param invalidTime 预约单失效分钟数
     */
    public void setInvalidTime(Integer invalidTime) {
        this.invalidTime = invalidTime;
    }

    /**
     * 获取备用字段
     *
     * @return spare - 备用字段
     */
    public String getSpare() {
        return spare;
    }

    /**
     * 设置备用字段
     *
     * @param spare 备用字段
     */
    public void setSpare(String spare) {
        this.spare = spare;
    }

    /**
     * 获取创建时间
     *
     * @return gtm_create - 创建时间
     */
    public Date getGtmCreate() {
        return gtmCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gtmCreate 创建时间
     */
    public void setGtmCreate(Date gtmCreate) {
        this.gtmCreate = gtmCreate;
    }

    /**
     * 获取修改时间
     *
     * @return gmt_modified - 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     *
     * @param gmtModified 修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Boolean getStatus() {
        return isStatus;
    }

    public void setStatus(Boolean status) {
        isStatus = status;
    }

    public BigDecimal getMaxGoldWeight() {
        return maxGoldWeight;
    }

    public void setMaxGoldWeight(BigDecimal maxGoldWeight) {
        this.maxGoldWeight = maxGoldWeight;
    }


    public Integer getPickUpDateRevise() {
        return pickUpDateRevise;
    }

    public void setPickUpDateRevise(Integer pickUpDateRevise) {
        this.pickUpDateRevise = pickUpDateRevise;
    }
}