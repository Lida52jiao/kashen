package com.battcn.entity;


/**
 * Created by Dada on 2018/11/19.
 */
public class Agentshow {

    private String merId;
    private String merName;
    private String merMp;
    private String accountNumber;
    private String mailbox;
    private String oneMerId;
    private String totalCode;
    private String generatedCode;
    private String used;
    private String notused;
    private String assign;
    private String creatDate;
    private String remarks;
    private String merChantId;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMerMp() {
        return merMp;
    }

    public void setMerMp(String merMp) {
        this.merMp = merMp;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getOneMerId() {
        return oneMerId;
    }

    public void setOneMerId(String oneMerId) {
        this.oneMerId = oneMerId;
    }

    public String getTotalCode() {
        return totalCode;
    }

    public void setTotalCode(String totalCode) {
        this.totalCode = totalCode;
    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getNotused() {
        return notused;
    }

    public void setNotused(String notused) {
        this.notused = notused;
    }

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMerChantId() {
        return merChantId;
    }

    public void setMerChantId(String merChantId) {
        this.merChantId = merChantId;
    }

    public Agentshow() {
    }

    public Agentshow(String merId, String merName, String merMp, String accountNumber, String mailbox, String oneMerId, String totalCode, String generatedCode, String used, String notused, String assign, String creatDate, String remarks, String merChantId) {
        this.merId = merId;
        this.merName = merName;
        this.merMp = merMp;
        this.accountNumber = accountNumber;
        this.mailbox = mailbox;
        this.oneMerId = oneMerId;
        this.totalCode = totalCode;
        this.generatedCode = generatedCode;
        this.used = used;
        this.notused = notused;
        this.assign = assign;
        this.creatDate = creatDate;
        this.remarks = remarks;
        this.merChantId = merChantId;
    }
}
