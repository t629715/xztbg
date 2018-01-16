package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: FinanceNewplayerOrder
 * @Description: 新手理财订单
 * @author htt
 * @date 2018-1-11 下午1:21:19
 * 
 */
public class FinanceNewplayerOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;          //id
	private Long userId;      //用户主键
	private String orderNo;   //订单号
	private Long productId;   //产品主键
	private String productNo; //产品编号
	private String productName;//产品名称
	private Long buyAmout;    //买入金额 单位分
	private Date buyTime;     //买入时间
	private Date redeemTime;  //赎回时间
	private Short status;     //1:持有中2:已赎回
	private Long income;      //收益支出 单位分
	private Integer initialPrice;//初始价 单位分
	private Integer clearPrice;//结算价 单位分
	private Float yearIncomPercent;//固定年化收益率
	private Float floatPercentMax;//最大浮动收益率
	private Integer cycle;      //交易周期
	private Integer minWeight;  //起投黄金重量，单位克
	private Integer startDay;
	private Integer endDay;
	private Integer sendDay;
	
	private Short redeemMethod; //1:手动赎回2:自动赎回
	private Short settleMethod; //1:赎回时付2:按月付息3:按天付息
	private Short allocationMethod; //收益发放方式：1一次性还本付息
	private Integer type;       //类型；2：黄金看涨，3：黄金看跌
	private Long sellAmount;    //发售金重，单位克
	private String riskType;    //风险类型
	private String estimatePercent;//预计年化收益
	private Long agentId;      //代理商id
	private Long brokerId;     //经纪人id
	private Double gram;       //购买克重

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getBuyAmout() {
		return buyAmout;
	}

	public void setBuyAmout(Long buyAmout) {
		this.buyAmout = buyAmout;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public Date getRedeemTime() {
		return redeemTime;
	}

	public void setRedeemTime(Date redeemTime) {
		this.redeemTime = redeemTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Long getIncome() {
		return income;
	}

	public void setIncome(Long income) {
		this.income = income;
	}

	public Integer getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Integer initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Integer getClearPrice() {
		return clearPrice;
	}

	public void setClearPrice(Integer clearPrice) {
		this.clearPrice = clearPrice;
	}

	public Float getYearIncomPercent() {
		return yearIncomPercent;
	}

	public void setYearIncomPercent(Float yearIncomPercent) {
		this.yearIncomPercent = yearIncomPercent;
	}

	public Float getFloatPercentMax() {
		return floatPercentMax;
	}

	public void setFloatPercentMax(Float floatPercentMax) {
		this.floatPercentMax = floatPercentMax;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(Integer minWeight) {
		this.minWeight = minWeight;
	}

	public Short getRedeemMethod() {
		return redeemMethod;
	}

	public void setRedeemMethod(Short redeemMethod) {
		this.redeemMethod = redeemMethod;
	}

	public Short getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(Short settleMethod) {
		this.settleMethod = settleMethod;
	}

	public Short getAllocationMethod() {
		return allocationMethod;
	}

	public void setAllocationMethod(Short allocationMethod) {
		this.allocationMethod = allocationMethod;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(Long sellAmount) {
		this.sellAmount = sellAmount;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getEstimatePercent() {
		return estimatePercent;
	}

	public void setEstimatePercent(String estimatePercent) {
		this.estimatePercent = estimatePercent;
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

	public Double getGram() {
		return gram;
	}

	public void setGram(Double gram) {
		this.gram = gram;
	}

	public Integer getStartDay() {
		return startDay;
	}

	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}

	public Integer getEndDay() {
		return endDay;
	}

	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
	}

	public Integer getSendDay() {
		return sendDay;
	}

	public void setSendDay(Integer sendDay) {
		this.sendDay = sendDay;
	}

	
}
