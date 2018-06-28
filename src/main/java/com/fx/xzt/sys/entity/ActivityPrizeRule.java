package com.fx.xzt.sys.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "activity_prize_rule")
public class ActivityPrizeRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 活动id
     */
    @Column(name = "act_id")
    private Long actId;

    /**
     * 小号为同一个的，概率加到一起必须是100，概率基数是100
     */
    @Column(name = "pri_act_id")
    private Integer priActId;

    /**
     * 奖品名称
     */
    private String name;

    /**
     * 奖品初始个数 -1表示无限多
     */
    @Column(name = "init_num")
    private Long initNum;

    /**
     * 奖品剩余个数 -1表示无限多
     */
    @Column(name = "surplus_num")
    private Long surplusNum;

    /**
     * 奖品单位
     */
    private String unit;

    /**
     * 奖品类型，1黄金，2加息券，3优惠卡券，
     */
    private Integer type;

    /**
     * 奖品描述
     */
    private String info;

    /**
     * 奖品图片
     */
    private String img;

    /**
     * 可使用天数(针对加息券)
     */
    @Column(name = "use_days")
    private Integer useDays;

    /**
     * 奖品价值最小值（如奖品时固定值，则最大值和最小值一致）
     */
    @Column(name = "value_range_min")
    private BigDecimal valueRangeMin;

    /**
     * 奖品最大值
     */
    @Column(name = "value_range_max")
    private BigDecimal valueRangeMax;

    /**
     * 概率起始值，概率基数是100，如该奖品对应的概率是20则可以最小值设为1，最大值设为20
     */
    @Column(name = "probability_min")
    private BigDecimal probabilityMin;

    /**
     * 获取奖品概率最大值
     */
    @Column(name = "probability_max")
    private BigDecimal probabilityMax;

    /**
     * 卡券满多少克黄金可用，单位克
     */
    @Column(name = "invest_gold_min")
    private Integer investGoldMin;

    /**
     * 奖品有效时长 -1表示无线长
     */
    @Column(name = "valid_time")
    private Integer validTime;

    /**
     * 加息券可用的交易周期,以y英文逗号,分隔,为空时默认全部可以使用
     */
    @Column(name = "validate_cycle")
    private String validateCycle;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 修改时间
     */
    @Column(name = "udpate_at")
    private Date udpateAt;

    /**
     * 修改人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 修改备注
     */
    @Column(name = "update_remark")
    private String updateRemark;

    /**
     * 活动code
     */
    @Column(name = "activity_code")
    private String activityCode;

    /**
     * 奖励code
     */
    @Column(name = "prize_code")
    private String prizeCode;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取活动id
     *
     * @return act_id - 活动id
     */
    public Long getActId() {
        return actId;
    }

    /**
     * 设置活动id
     *
     * @param actId 活动id
     */
    public void setActId(Long actId) {
        this.actId = actId;
    }

    /**
     * 获取小号为同一个的，概率加到一起必须是100，概率基数是100
     *
     * @return pri_act_id - 小号为同一个的，概率加到一起必须是100，概率基数是100
     */
    public Integer getPriActId() {
        return priActId;
    }

    /**
     * 设置小号为同一个的，概率加到一起必须是100，概率基数是100
     *
     * @param priActId 小号为同一个的，概率加到一起必须是100，概率基数是100
     */
    public void setPriActId(Integer priActId) {
        this.priActId = priActId;
    }

    /**
     * 获取奖品名称
     *
     * @return name - 奖品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置奖品名称
     *
     * @param name 奖品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取奖品初始个数 -1表示无限多
     *
     * @return init_num - 奖品初始个数 -1表示无限多
     */
    public Long getInitNum() {
        return initNum;
    }

    /**
     * 设置奖品初始个数 -1表示无限多
     *
     * @param initNum 奖品初始个数 -1表示无限多
     */
    public void setInitNum(Long initNum) {
        this.initNum = initNum;
    }

    /**
     * 获取奖品剩余个数 -1表示无限多
     *
     * @return surplus_num - 奖品剩余个数 -1表示无限多
     */
    public Long getSurplusNum() {
        return surplusNum;
    }

    /**
     * 设置奖品剩余个数 -1表示无限多
     *
     * @param surplusNum 奖品剩余个数 -1表示无限多
     */
    public void setSurplusNum(Long surplusNum) {
        this.surplusNum = surplusNum;
    }

    /**
     * 获取奖品单位
     *
     * @return unit - 奖品单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置奖品单位
     *
     * @param unit 奖品单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取奖品类型，1黄金，2加息券，3优惠卡券，
     *
     * @return type - 奖品类型，1黄金，2加息券，3优惠卡券，
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置奖品类型，1黄金，2加息券，3优惠卡券，
     *
     * @param type 奖品类型，1黄金，2加息券，3优惠卡券，
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取奖品描述
     *
     * @return info - 奖品描述
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置奖品描述
     *
     * @param info 奖品描述
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 获取奖品图片
     *
     * @return img - 奖品图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置奖品图片
     *
     * @param img 奖品图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取可使用天数(针对加息券)
     *
     * @return use_days - 可使用天数(针对加息券)
     */
    public Integer getUseDays() {
        return useDays;
    }

    /**
     * 设置可使用天数(针对加息券)
     *
     * @param useDays 可使用天数(针对加息券)
     */
    public void setUseDays(Integer useDays) {
        this.useDays = useDays;
    }

    /**
     * 获取奖品价值最小值（如奖品时固定值，则最大值和最小值一致）
     *
     * @return value_range_min - 奖品价值最小值（如奖品时固定值，则最大值和最小值一致）
     */
    public BigDecimal getValueRangeMin() {
        return valueRangeMin;
    }

    /**
     * 设置奖品价值最小值（如奖品时固定值，则最大值和最小值一致）
     *
     * @param valueRangeMin 奖品价值最小值（如奖品时固定值，则最大值和最小值一致）
     */
    public void setValueRangeMin(BigDecimal valueRangeMin) {
        this.valueRangeMin = valueRangeMin;
    }

    /**
     * 获取奖品最大值
     *
     * @return value_range_max - 奖品最大值
     */
    public BigDecimal getValueRangeMax() {
        return valueRangeMax;
    }

    /**
     * 设置奖品最大值
     *
     * @param valueRangeMax 奖品最大值
     */
    public void setValueRangeMax(BigDecimal valueRangeMax) {
        this.valueRangeMax = valueRangeMax;
    }

    /**
     * 获取概率起始值，概率基数是100，如该奖品对应的概率是20则可以最小值设为1，最大值设为20
     *
     * @return probability_min - 概率起始值，概率基数是100，如该奖品对应的概率是20则可以最小值设为1，最大值设为20
     */
    public BigDecimal getProbabilityMin() {
        return probabilityMin;
    }

    /**
     * 设置概率起始值，概率基数是100，如该奖品对应的概率是20则可以最小值设为1，最大值设为20
     *
     * @param probabilityMin 概率起始值，概率基数是100，如该奖品对应的概率是20则可以最小值设为1，最大值设为20
     */
    public void setProbabilityMin(BigDecimal probabilityMin) {
        this.probabilityMin = probabilityMin;
    }

    /**
     * 获取获取奖品概率最大值
     *
     * @return probability_max - 获取奖品概率最大值
     */
    public BigDecimal getProbabilityMax() {
        return probabilityMax;
    }

    /**
     * 设置获取奖品概率最大值
     *
     * @param probabilityMax 获取奖品概率最大值
     */
    public void setProbabilityMax(BigDecimal probabilityMax) {
        this.probabilityMax = probabilityMax;
    }

    /**
     * 获取卡券满多少克黄金可用，单位克
     *
     * @return invest_gold_min - 卡券满多少克黄金可用，单位克
     */
    public Integer getInvestGoldMin() {
        return investGoldMin;
    }

    /**
     * 设置卡券满多少克黄金可用，单位克
     *
     * @param investGoldMin 卡券满多少克黄金可用，单位克
     */
    public void setInvestGoldMin(Integer investGoldMin) {
        this.investGoldMin = investGoldMin;
    }

    /**
     * 获取奖品有效时长 -1表示无线长
     *
     * @return valid_time - 奖品有效时长 -1表示无线长
     */
    public Integer getValidTime() {
        return validTime;
    }

    /**
     * 设置奖品有效时长 -1表示无线长
     *
     * @param validTime 奖品有效时长 -1表示无线长
     */
    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    /**
     * 获取加息券可用的交易周期,以y英文逗号,分隔,为空时默认全部可以使用
     *
     * @return validate_cycle - 加息券可用的交易周期,以y英文逗号,分隔,为空时默认全部可以使用
     */
    public String getValidateCycle() {
        return validateCycle;
    }

    /**
     * 设置加息券可用的交易周期,以y英文逗号,分隔,为空时默认全部可以使用
     *
     * @param validateCycle 加息券可用的交易周期,以y英文逗号,分隔,为空时默认全部可以使用
     */
    public void setValidateCycle(String validateCycle) {
        this.validateCycle = validateCycle;
    }

    /**
     * 获取创建时间
     *
     * @return create_at - 创建时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取修改时间
     *
     * @return udpate_at - 修改时间
     */
    public Date getUdpateAt() {
        return udpateAt;
    }

    /**
     * 设置修改时间
     *
     * @param udpateAt 修改时间
     */
    public void setUdpateAt(Date udpateAt) {
        this.udpateAt = udpateAt;
    }

    /**
     * 获取修改人
     *
     * @return update_by - 修改人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置修改人
     *
     * @param updateBy 修改人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取修改备注
     *
     * @return update_remark - 修改备注
     */
    public String getUpdateRemark() {
        return updateRemark;
    }

    /**
     * 设置修改备注
     *
     * @param updateRemark 修改备注
     */
    public void setUpdateRemark(String updateRemark) {
        this.updateRemark = updateRemark;
    }

    /**
     * 获取活动code
     *
     * @return activity_code - 活动code
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * 设置活动code
     *
     * @param activityCode 活动code
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    /**
     * 获取奖励code
     *
     * @return prize_code - 奖励code
     */
    public String getPrizeCode() {
        return prizeCode;
    }

    /**
     * 设置奖励code
     *
     * @param prizeCode 奖励code
     */
    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode;
    }
}