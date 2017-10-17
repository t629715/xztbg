package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: GoldWithdraw 
* @Description: 实物提金
* @author htt
* @date 2017-10-17 下午1:41:07 
*
 */
public class GoldWithdraw implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;           // id
	private Long userId; // 用户id
	private Short type; // 提取类型，1 实物提金
	private Float amount; // 提取数量
	private Integer fee; // 手续费单位:分
	private Integer insurance; // 保险费:单位分
	private Integer logisticsFee;// 物流费
	private Date applyTime;      // 申请时间
	private Date sendTime;      // 发货时间
	private Short status;       // 状态1:未发货2:已发货
	private String logisticsNo; // 物流单号
	private Date updateTime;    // 修改时间
	
	//业务参数
	private String sendTimeString;    //发货时间
	private String updateTimeString;  //修改时间
	
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

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getInsurance() {
		return insurance;
	}

	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}

	public Integer getLogisticsFee() {
		return logisticsFee;
	}

	public void setLogisticsFee(Integer logisticsFee) {
		this.logisticsFee = logisticsFee;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSendTimeString() {
		return sendTimeString;
	}

	public void setSendTimeString(String sendTimeString) {
		this.sendTimeString = sendTimeString;
	}

	public String getUpdateTimeString() {
		return updateTimeString;
	}

	public void setUpdateTimeString(String updateTimeString) {
		this.updateTimeString = updateTimeString;
	}

}
