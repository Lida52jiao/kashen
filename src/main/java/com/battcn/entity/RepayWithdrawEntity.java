package com.battcn.entity;

import javax.persistence.*;

@Table(name = "acc_alipay_withdraw")
public class RepayWithdrawEntity implements java.io.Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -611993717141164916L;
	//订单号
    @Id
    @Column(name = "orderNo", unique = true, nullable = false)
    private String orderNo;
    //状态
    @Column(name = "state")
    private String state;
    //交易类型
    @Column(name = "type")
    private String type;
    //创建时间
    @Column(name = "createTime")
    private String createTime;
    //金额
    @Column(name = "amount")
    private Long amount;
    //到账金额
    @Column(name = "arrivalAmount")
    private Long arrivalAmount;
    //手续费
    @Column(name = "profitFee")
    private Long profitFee;
    //商户号
    @Column(name = "merchantId")
    private String merchantId;
    @Column(name = "merchantName")
    private String merchantName;
    @Column(name = "merchantPhone")
    private String merchantPhone;
    //代理商号
    @Column(name = "agentId")
    private String agentId;
    //机构号
    @Column(name = "institutionId")
    private String institutionId;
    //APPID
    @Column(name = "appId")
    private String appId;

    @Column(name = "aliUserId")
    private String aliUserId;
    //到账账号
    @Column(name = "aliLoginId")
    private String aliLoginId;

    @Column(name = "aliAppId")
    private String aliAppId;

    @Column(name = "remarks")
    private String remarks;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getArrivalAmount() {
        return arrivalAmount;
    }

    public void setArrivalAmount(Long arrivalAmount) {
        this.arrivalAmount = arrivalAmount;
    }

    public Long getProfitFee() {
        return profitFee;
    }

    public void setProfitFee(Long profitFee) {
        this.profitFee = profitFee;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
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

    public String getAliUserId() {
        return aliUserId;
    }

    public void setAliUserId(String aliUserId) {
        this.aliUserId = aliUserId;
    }

    public String getAliLoginId() {
        return aliLoginId;
    }

    public void setAliLoginId(String aliLoginId) {
        this.aliLoginId = aliLoginId;
    }

    public String getAliAppId() {
        return aliAppId;
    }

    public void setAliAppId(String aliAppId) {
        this.aliAppId = aliAppId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
