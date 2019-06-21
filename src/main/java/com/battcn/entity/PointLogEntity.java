package com.battcn.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bin on 2018/6/13.
 */
@Table(name = "p_point_log")
public class PointLogEntity implements java.io.Serializable{
    @Id
    @Column(name = "orderNo", unique = true, nullable = false)
    private String orderNo;
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "point")
    private Long point;
    @Column(name = "source")
    private String source;//order 兑换 checkin 签到  realName  实名  pay  交易  transfer 划拨
    @Column(name = "merchantId")
    private String merchantId;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "institutionId")
    private String institutionId;
    @Column(name = "agentId")
    private String agentId;
    @Column(name = "appId")
    private String appId;
    @Column(name = "remarks")
    private String remarks;

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

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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
}
