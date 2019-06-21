package com.battcn.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: Create By DaDa
 * @Date: 2019/4/2 10:27
 */
public class CountT1O implements Serializable {
    @Id
    private Long id;
    private String createTime;
    private String countDate;
    private String institutionId;
    private String aisleCode;
    private Long totalAmount;
    private String aisleName;
    private Long appFee;
    private Long allFee;//o单商分润
    private Long notAppFee;//O单商下发分润
    private String module;
    private String appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCountDate() {
        return countDate;
    }

    public void setCountDate(String countDate) {
        this.countDate = countDate;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getAisleCode() {
        return aisleCode;
    }

    public void setAisleCode(String aisleCode) {
        this.aisleCode = aisleCode;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAisleName() {
        return aisleName;
    }

    public void setAisleName(String aisleName) {
        this.aisleName = aisleName;
    }

    public Long getAppFee() {
        return appFee;
    }

    public void setAppFee(Long appFee) {
        this.appFee = appFee;
    }

    public Long getAllFee() {
        return allFee;
    }

    public void setAllFee(Long allFee) {
        this.allFee = allFee;
    }

    public Long getNotAppFee() {
        return notAppFee;
    }

    public void setNotAppFee(Long notAppFee) {
        this.notAppFee = notAppFee;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
