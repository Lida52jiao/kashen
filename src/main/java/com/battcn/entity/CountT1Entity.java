package com.battcn.entity;


import java.util.Date;

/**
 * Created by Dada on 2017/12/22.
 */

public class CountT1Entity implements java.io.Serializable {

    private Long id;
    private String createTime;
    private String countDate;
    private String institutionId;
    private String module;
    private String aisleCode;
    private Long totalAmount;
    private Long institutionFee;
    private Long notInstitutionFee;
    private Long allFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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

    public Long getInstitutionFee() {
        return institutionFee;
    }

    public void setInstitutionFee(Long institutionFee) {
        this.institutionFee = institutionFee;
    }

    public Long getNotInstitutionFee() {
        return notInstitutionFee;
    }

    public void setNotInstitutionFee(Long notInstitutionFee) {
        this.notInstitutionFee = notInstitutionFee;
    }

    public Long getAllFee() {
        return allFee;
    }

    public void setAllFee(Long allFee) {
        this.allFee = allFee;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
