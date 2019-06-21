package com.battcn.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bin on 2018/2/26.
 */
@Table(name = "api_order_alipay")
public class AlipayOrderEntity implements java.io.Serializable{
   
	private static final long serialVersionUID = -7664404956548801593L;
	@Id
    @Column(name = "orderNo", unique = true, nullable = false)
    private String orderNo;
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "merchantId")
    private String merchantId;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "state")
    private String state;
    @Column(name = "totalAmount")
    private Long totalAmount;
    @Column(name = "reducedAmount")
    private Long reducedAmount;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "type")
    private String type;

    @Column(name = "agentId")
    private String agentId;
    @Column(name = "institutionId")
    private String institutionId;
    @Column(name = "appId")
    private String appId;

    @Column(name = "remarks")
    private String remarks;
    
    private String starttime;
    
    private String finishtime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getReducedAmount() {
        return reducedAmount;
    }

    public void setReducedAmount(Long reducedAmount) {
        this.reducedAmount = reducedAmount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
    
}
