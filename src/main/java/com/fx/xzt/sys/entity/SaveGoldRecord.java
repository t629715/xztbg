package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: SaveGoldRecord 
* @Description: 存金记录
* @author htt
* @date 2018-5-29 上午10:44:55 
*
 */
public class SaveGoldRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;            //id
	private Long userId;        //用户id
	private Double amount;      //黄金数量，单位克
	private Long rmbAmount;     //买入金额 单位分
	private String description; //描述
	private Short type;         //1:买入黄金4：卖出黄金
	private Date createTime;    //创建时间
	private Long agentId;       //代理商id
	private Long brokerId;      //经纪人id
	private Long goldPrice;     //买入或卖出金价
	private Long averagePrice;  //单位成本价（分）
	private Long discountAmount;//优惠金额
	private Long discountPrice; //优惠价
	private Long profitAndLoss; //盈亏合计（分）
	private Long saleFee;       //卖出手续费（分）
	private String userName;    //用户名
	
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
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Long getRmbAmount() {
		return rmbAmount;
	}
	
	public void setRmbAmount(Long rmbAmount) {
		this.rmbAmount = rmbAmount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Short getType() {
		return type;
	}
	
	public void setType(Short type) {
		this.type = type;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
	public Long getGoldPrice() {
		return goldPrice;
	}
	
	public void setGoldPrice(Long goldPrice) {
		this.goldPrice = goldPrice;
	}
	
	public Long getAveragePrice() {
		return averagePrice;
	}
	
	public void setAveragePrice(Long averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	public Long getDiscountAmount() {
		return discountAmount;
	}
	
	public void setDiscountAmount(Long discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	public Long getDiscountPrice() {
		return discountPrice;
	}
	
	public void setDiscountPrice(Long discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	public Long getProfitAndLoss() {
		return profitAndLoss;
	}
	
	public void setProfitAndLoss(Long profitAndLoss) {
		this.profitAndLoss = profitAndLoss;
	}
	
	public Long getSaleFee() {
		return saleFee;
	}
	
	public void setSaleFee(Long saleFee) {
		this.saleFee = saleFee;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
