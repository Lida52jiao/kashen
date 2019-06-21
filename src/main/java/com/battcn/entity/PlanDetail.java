package com.battcn.entity;


/**
 * 还款计划详情
 */
public class PlanDetail implements java.io.Serializable{
	
	private static final long serialVersionUID = -3217341933405438166L;

    private String orderNo;
  
    private Long planId;
   
    private String state;
   
    private String payState;
   
    private String repaymentState;
    
    private String repaymentOrderExpect;
    
    private String repaymentOrderReality;
    
    private Integer day;
    
    private Integer time;
    
    private Integer number;
    
    private String payType;//1还款2消费
    
    private Long amount;
    
    private Long arrivalAmount;
   
    private Long fee;
    
    private String createTime;
    
    private Long executeTime;
    
    private Long finishTime;
    
    private String merchantId;
    
    private String easyUserId;
   
    private String name;
    
    private String phone;
    
    private String bankCode;
    
    private String idCardNo;
    
    private String cardNo;
    
    private String year;
    
    private String month;
    
    private String cvv2;
    
    private String bindId;

    private String agentId;
    
    private String institutionId;
    
    private String appId;
    
    private String starttime;
    
    private String finishtime;
    
    private String executestartTime;
    
    private String executefinishTime;

    private String remarks;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getRepaymentState() {
        return repaymentState;
    }

    public void setRepaymentState(String repaymentState) {
        this.repaymentState = repaymentState;
    }

    public String getRepaymentOrderExpect() {
        return repaymentOrderExpect;
    }

    public void setRepaymentOrderExpect(String repaymentOrderExpect) {
        this.repaymentOrderExpect = repaymentOrderExpect;
    }

    public String getRepaymentOrderReality() {
        return repaymentOrderReality;
    }

    public void setRepaymentOrderReality(String repaymentOrderReality) {
        this.repaymentOrderReality = repaymentOrderReality;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getEasyUserId() {
        return easyUserId;
    }

    public void setEasyUserId(String easyUserId) {
        this.easyUserId = easyUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
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

	public String getExecutestartTime() {
		return executestartTime;
	}

	public void setExecutestartTime(String executestartTime) {
		this.executestartTime = executestartTime;
	}

	public String getExecutefinishTime() {
		return executefinishTime;
	}

	public void setExecutefinishTime(String executefinishTime) {
		this.executefinishTime = executefinishTime;
	}

	public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
