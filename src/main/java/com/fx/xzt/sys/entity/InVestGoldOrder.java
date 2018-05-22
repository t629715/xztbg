package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: InVestGoldOrder
 * @Description: 金条投资订单
 * @author htt
 * @date 2018-4-18 下午4:17:28
 * 
 */
public class InVestGoldOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long investGoldId;
	private String investGoldName;
	private Integer investGoldWeight;
	private Double investGoldService;
	private Integer logisticsFee;
	private String logisticsNo;
	private Integer goldNum;
	private Integer goldTotalWeight;
	private Short payType;
	private Integer goldBasePrice;
	private Long totalMoney;
	private Long goldMoney;
	private Long serviceMoney;
	private Short status;
	private Date createTime;
	private Date sendTime;
	private Date updateTime;
	private Long brokerId;
	private Long agentId;
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvestGoldId() {
		return investGoldId;
	}

	public void setInvestGoldId(Long investGoldId) {
		this.investGoldId = investGoldId;
	}

	public String getInvestGoldName() {
		return investGoldName;
	}

	public void setInvestGoldName(String investGoldName) {
		this.investGoldName = investGoldName;
	}

	public Integer getInvestGoldWeight() {
		return investGoldWeight;
	}

	public void setInvestGoldWeight(Integer investGoldWeight) {
		this.investGoldWeight = investGoldWeight;
	}

	public Double getInvestGoldService() {
		return investGoldService;
	}

	public void setInvestGoldService(Double investGoldService) {
		this.investGoldService = investGoldService;
	}

	public Integer getLogisticsFee() {
		return logisticsFee;
	}

	public void setLogisticsFee(Integer logisticsFee) {
		this.logisticsFee = logisticsFee;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public Integer getGoldNum() {
		return goldNum;
	}

	public void setGoldNum(Integer goldNum) {
		this.goldNum = goldNum;
	}

	public Integer getGoldTotalWeight() {
		return goldTotalWeight;
	}

	public void setGoldTotalWeight(Integer goldTotalWeight) {
		this.goldTotalWeight = goldTotalWeight;
	}

	public Short getPayType() {
		return payType;
	}

	public void setPayType(Short payType) {
		this.payType = payType;
	}

	public Integer getGoldBasePrice() {
		return goldBasePrice;
	}

	public void setGoldBasePrice(Integer goldBasePrice) {
		this.goldBasePrice = goldBasePrice;
	}

	public Long getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Long getGoldMoney() {
		return goldMoney;
	}

	public void setGoldMoney(Long goldMoney) {
		this.goldMoney = goldMoney;
	}

	public Long getServiceMoney() {
		return serviceMoney;
	}

	public void setServiceMoney(Long serviceMoney) {
		this.serviceMoney = serviceMoney;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

}
