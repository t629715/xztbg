package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: UserVoucher 
* @Description: 金权交易优惠券卡券明细
* @author htt
* @date 2017-11-8 下午1:41:39 
*
 */
public class UserVoucher implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;                 //主键
	private Long userId;             //所属id
	private Integer goldCondition;   //满多少克黄金可用
	private Long deductionValue;     //卡券抵扣金额
	private Double deductionPercent; //抵扣比例
	private String description;      //描述
	private Date createtime;         //领取时间
	private Short useStatus;         //使用状态0：未使用；1：已使用
	private Date useTime;            //使用时间
	private Date dateStart;          //有效期开始
	private Date dateEnd;            //有效期结束
	private Short type;              //增值类型
	private String source;           //来源
	
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
	
	public Integer getGoldCondition() {
		return goldCondition;
	}
	
	public void setGoldCondition(Integer goldCondition) {
		this.goldCondition = goldCondition;
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
	
	public Date getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
	
	public Date getDateStart() {
		return dateStart;
	}
	
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	
	public Date getDateEnd() {
		return dateEnd;
	}
	
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	public Short getType() {
		return type;
	}
	
	public void setType(Short type) {
		this.type = type;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}

}
