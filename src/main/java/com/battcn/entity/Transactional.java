package com.battcn.entity;

import java.math.BigDecimal;
import java.util.List;


public class Transactional implements java.io.Serializable{
    
	private static final long serialVersionUID = -2793147155585068577L;
	
    private Long tlId;
   
    private String createTime;
    
    private String type;
    
    private String module;
    
    private String starttime;
    
    private String finishtime;
    
    private String merchantId;
    
    private String merName;
   
    private String orderMerchantId;
    
    private String orderMerchantName;
    
    private String orderMerchantPhone;
   
    private String agentId;
   
    private String institutionId;
    
    private String appId;
    
    private String appName;
    
    private String orderNo;
    
    private String planId;
    
    private String rate;
    
    private String level;
    
    private Long fee;
    
    private BigDecimal amount;
    
    private BigDecimal remarks;
    
    private List<String> list;
    
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
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getFinishtime() {
		return finishtime;
	}
	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
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
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
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
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
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
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
    
}
