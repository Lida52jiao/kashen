package com.battcn.entity;

/**
 * Created by Dada on 2018/11/12.
 */
public class AlipayOutlist {
    String merchantId;
    String agentId;
    String phone;
    String orderNo;
    String state;
    String type;
    String appId;
    String starttime;
    String finishtime;

    public String getMerchantId() {
        return merchantId;
    }

    public AlipayOutlist(String merchantId, String agentId, String phone, String orderNo, String state, String type, String appId, String starttime, String finishtime) {
        this.merchantId = merchantId;
        this.agentId = agentId;
        this.phone = phone;
        this.orderNo = orderNo;
        this.state = state;
        this.type = type;
        this.appId = appId;
        this.starttime = starttime;
        this.finishtime = finishtime;
    }

    public AlipayOutlist() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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
