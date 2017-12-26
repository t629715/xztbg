package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: UserAccountRecord 
* @Description: 用户账户记录
* @author htt
* @date 2017-11-7 下午4:48:21 
*
 */
public class UserAccountRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;          //id
    private Long userId;      //用户id
    private String userName;  //用户名
    private String side;      //进出方向 I：进 O：出
    private String action;    //行为 10：充值 20：提现 30：理财收益 40：实金兑换 50:交易收益 60:冻结，70：活期理财赎回，80：理财,90:黄金赎回
    private Long rmb;         //人民币金额
    private Date createTime;  //创建时间
    private Integer status;   //状态  0：未审核；1：已审核
    private String description;//描述
    private String withdrawId; //提现号
    private Long agentId;      //用户归属代理商
    private Long brokerId;     //用户对数经纪人
    
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
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getSide() {
		return side;
	}
	
	public void setSide(String side) {
		this.side = side;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public Long getRmb() {
		return rmb;
	}
	
	public void setRmb(Long rmb) {
		this.rmb = rmb;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getWithdrawId() {
		return withdrawId;
	}

	public void setWithdrawId(String withdrawId) {
		this.withdrawId = withdrawId;
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
    
}