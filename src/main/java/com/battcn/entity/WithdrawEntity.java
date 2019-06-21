package com.battcn.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "api_order_withdraw")
public class WithdrawEntity implements java.io.Serializable{
    
	private static final long serialVersionUID = 2351325682794309564L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oId", unique = true, nullable = false)
    private Long oId;
    //订单号
    @Column(name = "orderNo")
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
    private BigDecimal amount;
    //到账金额
    @Column(name = "arrivalAmount")
    private BigDecimal arrivalAmount;
    //手续费
    @Column(name = "profitFee")
    private BigDecimal profitFee;
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
    @Column(name = "appName")
    private String appName;
    //卡ID
    @Column(name = "cardId")
    private Long cardId;
    //银行编码
    @Column(name = "bankCode")
    private String bankCode;
    //卡号
    @Column(name = "cardNo")
    private String cardNo;
    
    @Column(name = "firstTime")
    private Long firstTime;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "secondTime")
    private Long secondTime;
    @Column(name = "secondName")
    private String secondName;
    @Column(name = "remarks")
    private String remarks;

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public BigDecimal getProfitFee() {
        return profitFee;
    }

    public void setProfitFee(BigDecimal profitFee) {
        this.profitFee = profitFee;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
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

    public BigDecimal getArrivalAmount() {
        return arrivalAmount;
    }

    public void setArrivalAmount(BigDecimal arrivalAmount) {
        this.arrivalAmount = arrivalAmount;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

	public Long getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Long firstTime) {
		this.firstTime = firstTime;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getSecondTime() {
		return secondTime;
	}

	public void setSecondTime(Long secondTime) {
		this.secondTime = secondTime;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
    
	public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
}
