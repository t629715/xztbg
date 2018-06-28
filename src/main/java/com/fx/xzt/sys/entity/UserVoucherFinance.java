package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserVoucherFinance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;                 //主键
	private Long deductionValue;     //加成面额，单位分
	private Double deductionPercent; //加成比例
	private String description;      //描述
	private Long userId;             //所属人id
	private Date createTime;         //发放日期
	private Short useStatus;         //使用状态0：未使用；1：已使用
	private Date useTime;            //使用时间
	private Date dataStart;          //有效期开始
	private Date dateEnd;            //有效期结束
	private Short addType;           //增值类型1、按比例；2、按面额
	private Short validateDays;      //加息时间
	private String validateCycle;    //可用的交易周期
	private String source;           //来源

	private Long agentId;//归宿代理商id
	private Long brokerId;//归宿经纪人id
	private Short type;//卡券类型
	private BigDecimal goldCondition;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDeductionValue() {
		return deductionValue;
	}
	
	public void setDeductionValue(Long deductionValue) {
		this.deductionValue = deductionValue;
	}
	
	public Double getDeductionPercent() {
		return deductionPercent;
	}
	
	public void setDeductionPercent(Double deductionPercent) {
		this.deductionPercent = deductionPercent;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Short getUseStatus() {
		return useStatus;
	}
	
	public void setUseStatus(Short useStatus) {
		this.useStatus = useStatus;
	}
	
	public Date getUseTime() {
		return useTime;
	}
	
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	
	public Date getDataStart() {
		return dataStart;
	}
	
	public void setDataStart(Date dataStart) {
		this.dataStart = dataStart;
	}
	
	public Date getDateEnd() {
		return dateEnd;
	}
	
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	public Short getAddType() {
		return addType;
	}
	
	public void setAddType(Short addType) {
		this.addType = addType;
	}
	
	public Short getValidateDays() {
		return validateDays;
	}
	
	public void setValidateDays(Short validateDays) {
		this.validateDays = validateDays;
	}
	
	public String getValidateCycle() {
		return validateCycle;
	}
	
	public void setValidateCycle(String validateCycle) {
		this.validateCycle = validateCycle;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public BigDecimal getGoldCondition() {
		return goldCondition;
	}

	public void setGoldCondition(BigDecimal goldCondition) {
		this.goldCondition = goldCondition;
	}
}
