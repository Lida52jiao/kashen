package com.battcn.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "api_transactional_easy")
public class TransactionalEntity implements java.io.Serializable{
    
	private static final long serialVersionUID = -2793147155585068577L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tlId", unique = true, nullable = false)
    private Long tlId;
    //创建时间

//			"remarks": null"tlId": 92211765,
//			"tlOrderNo": null,
//			"createTime": "2019-06-03 11:05:48.0",
//			"createDate": null,
//			"module": "repayment",
//			"type": "repSubsidize",
//			"merchantId": "M2018070714274708394",
//			"orderMerchantId": "M2018060315302987883",
//			"orderMerchantName": "刘满兰",
//			"orderMerchantPhone": "13788561070",
//			"agentId": "C2018091409432910025",
//			"institutionId": "T00000009",
//			"appId": "0000",
//			"orderNo": "LP578965423053246466",
//			"aisleCode": "ld05",
//			"planId": 294845,
//			"rate": "0.0002",
//			"level": "cityAgent",
//			"fee": 8,
//			"amount": 40000,
    @Column(name = "tlOrderNo")
    private String tlOrderNo;
	@Column(name = "createTime")
	private String createTime;
	@Column(name = "createDate")
	private String createDate;
	@Column(name = "module")
	private String module;
    //交易类型
    @Column(name = "type")
    private String type;
    //商户号
    @Column(name = "merchantId")
    private String merchantId;
    @Column(name = "orderMerchantId")
    private String orderMerchantId;
    @Column(name = "orderMerchantName")
    private String orderMerchantName;
    @Column(name = "orderMerchantPhone")
    private String orderMerchantPhone;
    //代理商
    @Column(name = "agentId")
    private String agentId;
    //机构号
    @Column(name = "institutionId")
    private String institutionId;
    @Column(name = "appId")
    private String appId;
    //订单号
    @Column(name = "orderNo")
    private String orderNo;
    @Column(name = "planId")
    private String planId;
    //底价
    @Column(name = "rate")
    private String rate;
    @Column(name = "level")
    private String level;
    //分润手续费
    @Column(name = "fee")
    private BigDecimal fee;
    //交易金额
    @Column(name = "amount")
    private BigDecimal amount;
	@Column(name = "aisleCode")
	private String aisleCode;
    @Column(name = "remarks")
    private BigDecimal remarks;

	public String getAisleCode() {
		return aisleCode;
	}

	public void setAisleCode(String aisleCode) {
		this.aisleCode = aisleCode;
	}

	public Long getTlId() {
		return tlId;
	}
	public void setTlId(Long tlId) {
		this.tlId = tlId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getOrderMerchantId() {
		return orderMerchantId;
	}
	public void setOrderMerchantId(String orderMerchantId) {
		this.orderMerchantId = orderMerchantId;
	}
	public String getOrderMerchantName() {
		return orderMerchantName;
	}
	public void setOrderMerchantName(String orderMerchantName) {
		this.orderMerchantName = orderMerchantName;
	}
	public String getOrderMerchantPhone() {
		return orderMerchantPhone;
	}
	public void setOrderMerchantPhone(String orderMerchantPhone) {
		this.orderMerchantPhone = orderMerchantPhone;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getRemarks() {
		return remarks;
	}
	public void setRemarks(BigDecimal remarks) {
		this.remarks = remarks;
	}
    
}
