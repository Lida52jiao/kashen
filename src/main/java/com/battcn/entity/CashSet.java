package com.battcn.entity;

/**
 * Created by Dada on 2018/12/24.
 */
public class CashSet {
    private String institutionId;
    private String institutionName;
    private String merHost;
    private String shareType;
    private Long aliWithdrawFee;
    private Long aliWithdrawMin;
    private Long aliWithdrawMax;

    public CashSet() {
    }

    public CashSet(String institutionId, String institutionName, String merHost, String shareType, Long aliWithdrawFee, Long aliWithdrawMin, Long aliWithdrawMax) {
        this.institutionId = institutionId;
        this.institutionName = institutionName;
        this.merHost = merHost;
        this.shareType = shareType;
        this.aliWithdrawFee = aliWithdrawFee;
        this.aliWithdrawMin = aliWithdrawMin;
        this.aliWithdrawMax = aliWithdrawMax;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getMerHost() {
        return merHost;
    }

    public void setMerHost(String merHost) {
        this.merHost = merHost;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public Long getAliWithdrawFee() {
        return aliWithdrawFee;
    }

    public void setAliWithdrawFee(Long aliWithdrawFee) {
        this.aliWithdrawFee = aliWithdrawFee;
    }

    public Long getAliWithdrawMin() {
        return aliWithdrawMin;
    }

    public void setAliWithdrawMin(Long aliWithdrawMin) {
        this.aliWithdrawMin = aliWithdrawMin;
    }

    public Long getAliWithdrawMax() {
        return aliWithdrawMax;
    }

    public void setAliWithdrawMax(Long aliWithdrawMax) {
        this.aliWithdrawMax = aliWithdrawMax;
    }
}
