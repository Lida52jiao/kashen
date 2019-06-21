package com.battcn.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bin on 2018/11/22.
 */
@Table(name = "acc_black_list")
public class BlackListEntity implements java.io.Serializable{
    @Id
    @Column(name = "merchantId", unique = true, nullable = false)
    private String merchantId;
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "institutionId")
    private String institutionId;
    @Column(name = "appId")
    private String appId;
    @Column(name = "agentId")
    private String agentId;
    @Column(name = "name")
    private String name;
    @Column(name ="phone")
    private String phone;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
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
