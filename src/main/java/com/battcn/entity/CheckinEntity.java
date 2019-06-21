package com.battcn.entity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by bin on 2018/6/13.
 */
@Table(name = "p_checkin")
public class CheckinEntity implements java.io.Serializable{
    @Column(name = "orderNo", unique = true, nullable = false)
    private String orderNo;
    @Column(name = "createTime")
    private String createTime;
    @Column(name ="dateStr")
    private String dateStr;
    @Column(name = "point")
    private Long point;
    @Column(name = "number")
    private Long number;
    @Column(name = "merchantId")
    private String merchantId;
    @Column(name = "institutionId")
    private String institutionId;
    @Column(name = "agentId")
    private String agentId;
    @Column(name = "appId")
    private String appId;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
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
    
}
