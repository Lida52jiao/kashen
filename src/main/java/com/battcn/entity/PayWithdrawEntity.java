package com.battcn.entity;

import javax.persistence.*;

@Table(name = "acc_alipay_withdraw")
public class PayWithdrawEntity implements java.io.Serializable{
    //订单号
    private String orderNo;
    //状态
    private String state;
    //交易类型
    private String type;
    //创建时间
    private String createTime;
    //金额
    private Long amount;
    //到账金额
    private Long arrivalAmount;
    //手续费
    private Long profitFee;
    //商户号
    private String merchantId;
    private String merchantName;
    private String merchantPhone;
    //代理商号
    private String agentId;
    //机构号
    private String institutionId;
    //APPID
    private String appId;

    private String aliUserId;

    private String aliLoginId;

    private String aliAppId;

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
