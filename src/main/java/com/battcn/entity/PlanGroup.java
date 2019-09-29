package com.battcn.entity;

public class PlanGroup {
    private String groupId;
    private String createTime;
    private String state;
    private String merchantId;
    private String institutionId;
    private String aisleCode;

    private String executeState;//1执行中2中断
    private String clearState;//1未清算2清算中3清算成功
    private String agentId;
    private String appId;
    private Long totalRepAmount;//总还款金额
    private Long totalFee;//总手续费
    private Long basicAmount;//预留金金额
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getExecuteState() {
        return executeState;
    }

    public void setExecuteState(String executeState) {
        this.executeState = executeState;
    }

    public String getClearState() {
        return clearState;
    }

    public void setClearState(String clearState) {
        this.clearState = clearState;
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

    public Long getTotalRepAmount() {
        return totalRepAmount;
    }

    public void setTotalRepAmount(Long totalRepAmount) {
        this.totalRepAmount = totalRepAmount;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getBasicAmount() {
        return basicAmount;
    }

    public void setBasicAmount(Long basicAmount) {
        this.basicAmount = basicAmount;
    }

    public String getAisleCode() {
        return aisleCode;
    }

    public void setAisleCode(String aisleCode) {
        this.aisleCode = aisleCode;
    }
}
