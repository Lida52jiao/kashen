package com.battcn.entity;

import java.math.BigDecimal;

public class KSAgentRateTable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.agent_level
     *
     * @mbggenerated
     */
    private String agentLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.rate
     *
     * @mbggenerated
     */
    private BigDecimal rate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.d0_fee
     *
     * @mbggenerated
     */
    private Long d0Fee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.aisle_code
     *
     * @mbggenerated
     */
    private String aisleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.aisle_name
     *
     * @mbggenerated
     */
    private String aisleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.app_id
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.is_ld
     *
     * @mbggenerated
     */
    private String isLd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.is_repayment
     *
     * @mbggenerated
     */
    private String isRepayment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.remarks
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ks_agent_rate.remarks_two
     *
     * @mbggenerated
     */
    private String remarksTwo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.id
     *
     * @return the value of ks_agent_rate.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.id
     *
     * @param id the value for ks_agent_rate.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.agent_level
     *
     * @return the value of ks_agent_rate.agent_level
     *
     * @mbggenerated
     */
    public String getAgentLevel() {
        return agentLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.agent_level
     *
     * @param agentLevel the value for ks_agent_rate.agent_level
     *
     * @mbggenerated
     */
    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel == null ? null : agentLevel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.rate
     *
     * @return the value of ks_agent_rate.rate
     *
     * @mbggenerated
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.rate
     *
     * @param rate the value for ks_agent_rate.rate
     *
     * @mbggenerated
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.d0_fee
     *
     * @return the value of ks_agent_rate.d0_fee
     *
     * @mbggenerated
     */
    public Long getD0Fee() {
        return d0Fee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.d0_fee
     *
     * @param d0Fee the value for ks_agent_rate.d0_fee
     *
     * @mbggenerated
     */
    public void setD0Fee(Long d0Fee) {
        this.d0Fee = d0Fee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.aisle_code
     *
     * @return the value of ks_agent_rate.aisle_code
     *
     * @mbggenerated
     */
    public String getAisleCode() {
        return aisleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.aisle_code
     *
     * @param aisleCode the value for ks_agent_rate.aisle_code
     *
     * @mbggenerated
     */
    public void setAisleCode(String aisleCode) {
        this.aisleCode = aisleCode == null ? null : aisleCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.aisle_name
     *
     * @return the value of ks_agent_rate.aisle_name
     *
     * @mbggenerated
     */
    public String getAisleName() {
        return aisleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.aisle_name
     *
     * @param aisleName the value for ks_agent_rate.aisle_name
     *
     * @mbggenerated
     */
    public void setAisleName(String aisleName) {
        this.aisleName = aisleName == null ? null : aisleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.app_id
     *
     * @return the value of ks_agent_rate.app_id
     *
     * @mbggenerated
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.app_id
     *
     * @param appId the value for ks_agent_rate.app_id
     *
     * @mbggenerated
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.is_ld
     *
     * @return the value of ks_agent_rate.is_ld
     *
     * @mbggenerated
     */
    public String getIsLd() {
        return isLd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.is_ld
     *
     * @param isLd the value for ks_agent_rate.is_ld
     *
     * @mbggenerated
     */
    public void setIsLd(String isLd) {
        this.isLd = isLd == null ? null : isLd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.is_repayment
     *
     * @return the value of ks_agent_rate.is_repayment
     *
     * @mbggenerated
     */
    public String getIsRepayment() {
        return isRepayment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.is_repayment
     *
     * @param isRepayment the value for ks_agent_rate.is_repayment
     *
     * @mbggenerated
     */
    public void setIsRepayment(String isRepayment) {
        this.isRepayment = isRepayment == null ? null : isRepayment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.remarks
     *
     * @return the value of ks_agent_rate.remarks
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.remarks
     *
     * @param remarks the value for ks_agent_rate.remarks
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ks_agent_rate.remarks_two
     *
     * @return the value of ks_agent_rate.remarks_two
     *
     * @mbggenerated
     */
    public String getRemarksTwo() {
        return remarksTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ks_agent_rate.remarks_two
     *
     * @param remarksTwo the value for ks_agent_rate.remarks_two
     *
     * @mbggenerated
     */
    public void setRemarksTwo(String remarksTwo) {
        this.remarksTwo = remarksTwo == null ? null : remarksTwo.trim();
    }
}