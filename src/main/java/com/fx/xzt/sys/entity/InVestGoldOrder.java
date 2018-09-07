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

	private Long id;                   //id
	private Long investGoldId;         //投资金条id
	private Long userAddressId;        //用户收货地址id
	private String investGoldName;     //投资金条产品名称
	private Integer investGoldWeight;  //投资金条规格，单位克
	private Double investGoldService;  //每笔提金服务费，按黄金价值算 从配置表里查出费率
	private Integer logisticsFee;      //物流费，单位分
	private String logisticsNo;        //物流单号
	private Integer goldNum;           //金条数量
	private Integer goldTotalWeight;   //黄金总重量，单位克
	private Short payType;             //1：现金支付2：存金宝支付,3:金权交易手动交割 4:自动交割'
	private Integer goldBasePrice;     //黄金基准价，单位克/分
	private Long totalMoney;           //订单总价，单位分，存金宝支付时值计算服务费
	private Long goldMoney;            //黄金总价，单位分，存金宝支付时为0
	private Long serviceMoney;         //提金服务费，单位分
	private String userAddress;        //用户收货地址
	private String deliverName;        //收货人姓名
	private String deliverPhone;       //收货人电话
	private String imgUrl;             //订单图片地址
	private Short status;              //0：待支付；10：未发货；20：已发货；30：已完成；40：未发货已取消；50：未支付已关闭；注：订单只要未发货，都可以取消
	private Date createTime;           //订单创建时间
	private Date sendTime;             //发货时间
	private Date updateTime;           //修改时间
	private Date payTime;              //支付时间
	private Long brokerId;             //经纪人id
	private Long agentId;              //代理商id
	private Long userId;               //用户id
	private String updateTimeStr;
	/**
	 * 金权交易交割订单id(pay_type为3时才有值)
	 */
	private Long dealOrderId;

	/**
	 * 加工费，单位分
	 */
	private Integer processingService;

	/**
	 * 发票费，单位分
	 */
	private Integer invoiceService;

	/**
	 * 保管费，单位分
	 */
	private Integer custodyService;





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

	public Long getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getDeliverName() {
		return deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	public String getDeliverPhone() {
		return deliverPhone;
	}

	public void setDeliverPhone(String deliverPhone) {
		this.deliverPhone = deliverPhone;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}


	public Long getDealOrderId() {
		return dealOrderId;
	}

	public void setDealOrderId(Long dealOrderId) {
		this.dealOrderId = dealOrderId;
	}

	public Integer getProcessingService() {
		return processingService;
	}

	public void setProcessingService(Integer processingService) {
		this.processingService = processingService;
	}

	public Integer getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(Integer invoiceService) {
		this.invoiceService = invoiceService;
	}

	public Integer getCustodyService() {
		return custodyService;
	}

	public void setCustodyService(Integer custodyService) {
		this.custodyService = custodyService;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
}
