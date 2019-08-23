package com.battcn.entity;

public class Cardinformation {

    private Long cardid;

    private String merchantid;

    private String cardtype;

    private String cardnumber;

    private String mermp;

    private String issuingbank;

    private String statementdate;

    private String repaymentdate;

    private String cv2;

    private String effectiveyear;

    private String effectivemonth;

    private String sign;

    private String carddefault;

    private String creatdate;

    private String bindid;

    private String agentid;

    private String institutionid;

    private String appid;

    private String remarks;

    public Long getCardid() {
        return cardid;
    }

    public void setCardid(Long cardid) {
        this.cardid = cardid;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype == null ? null : cardtype.trim();
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber == null ? null : cardnumber.trim();
    }

    public String getMermp() {
        return mermp;
    }

    public void setMermp(String mermp) {
        this.mermp = mermp == null ? null : mermp.trim();
    }

    public String getIssuingbank() {
        return issuingbank;
    }

    public void setIssuingbank(String issuingbank) {
        this.issuingbank = issuingbank == null ? null : issuingbank.trim();
    }

    public String getStatementdate() {
        return statementdate;
    }

    public void setStatementdate(String statementdate) {
        this.statementdate = statementdate == null ? null : statementdate.trim();
    }

    public String getRepaymentdate() {
        return repaymentdate;
    }

    public void setRepaymentdate(String repaymentdate) {
        this.repaymentdate = repaymentdate == null ? null : repaymentdate.trim();
    }

    public String getCv2() {
        return cv2;
    }

    public void setCv2(String cv2) {
        this.cv2 = cv2 == null ? null : cv2.trim();
    }

    public String getEffectiveyear() {
        return effectiveyear;
    }

    public void setEffectiveyear(String effectiveyear) {
        this.effectiveyear = effectiveyear == null ? null : effectiveyear.trim();
    }

    public String getEffectivemonth() {
        return effectivemonth;
    }

    public void setEffectivemonth(String effectivemonth) {
        this.effectivemonth = effectivemonth == null ? null : effectivemonth.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getCarddefault() {
        return carddefault;
    }

    public void setCarddefault(String carddefault) {
        this.carddefault = carddefault == null ? null : carddefault.trim();
    }

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(String creatdate) {
        this.creatdate = creatdate == null ? null : creatdate.trim();
    }

    public String getBindid() {
        return bindid;
    }

    public void setBindid(String bindid) {
        this.bindid = bindid == null ? null : bindid.trim();
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid == null ? null : agentid.trim();
    }

    public String getInstitutionid() {
        return institutionid;
    }

    public void setInstitutionid(String institutionid) {
        this.institutionid = institutionid == null ? null : institutionid.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}