package com.battcn.entity;

/**
 * Created by bin on 2018/5/29.
 */
public class State implements java.io.Serializable{
    public String status;
    public String repayPlanId;
    public String cooperator_item_id;
    public String channelStatus;
    public String tip;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRepayPlanId() {
        return repayPlanId;
    }

    public void setRepayPlanId(String repayPlanId) {
        this.repayPlanId = repayPlanId;
    }

    public String getCooperator_item_id() {
        return cooperator_item_id;
    }

    public void setCooperator_item_id(String cooperator_item_id) {
        this.cooperator_item_id = cooperator_item_id;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
