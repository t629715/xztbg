package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: InvestGoldOrderItem 
* @Description: 金条订单操作记录
* @author htt
* @date 2018-5-30 下午2:51:05 
*
 */
public class InvestGoldOrderItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;          //id
	private Long orderId;     //订单id
	private Short orderStatus;//0：待支付；10：未发货；20：已发货；30：已完成；40：未发货已取消；50：未支付已关闭；
	private Long operatorId;  //操作人id
	private String operatorName;//操作人用户名
	private Date createTime;  //操作时间
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Short getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Long getOperatorId() {
		return operatorId;
	}
	
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	
	public String getOperatorName() {
		return operatorName;
	}
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
