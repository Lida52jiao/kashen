package com.battcn.entity;

import java.math.BigDecimal;

public class AgentRate {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_agentrate.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_agentrate.merChantId
     *
     * @mbggenerated
     */
    private String merchantid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_agentrate.agentId
     *
     * @mbggenerated
     */
    private String agentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_agentrate.rate
     *
     * @mbggenerated
     */
    private BigDecimal rate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_agentrate.d0Fee
     *
     * @mbggenerated
     */
    private Integer d0fee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_agentrate.aisleCode
     *
     * @mbggenerated
     */
    private String aislecode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_agentrate.id
     *
     * @return the value of t_mp_agentrate.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_agentrate.id
     *
     * @param id the value for t_mp_agentrate.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_agentrate.merChantId
     *
     * @return the value of t_mp_agentrate.merChantId
     *
     * @mbggenerated
     */
    public String getMerchantid() {
        return merchantid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_agentrate.merChantId
     *
     * @param merchantid the value for t_mp_agentrate.merChantId
     *
     * @mbggenerated
     */
    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_agentrate.agentId
     *
     * @return the value of t_mp_agentrate.agentId
     *
     * @mbggenerated
     */
    public String getAgentid() {
        return agentid;
    }

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_agentrate.agentId
     *
     * @param agentid the value for t_mp_agentrate.agentId
     *
     * @mbggenerated
     */
    public void setAgentid(String agentid) {
        this.agentid = agentid == null ? null : agentid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_agentrate.rate
     *
     * @return the value of t_mp_agentrate.rate
     *
     * @mbggenerated
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_agentrate.rate
     *
     * @param rate the value for t_mp_agentrate.rate
     *
     * @mbggenerated
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_agentrate.d0Fee
     *
     * @return the value of t_mp_agentrate.d0Fee
     *
     * @mbggenerated
     */
    public Integer getD0fee() {
        return d0fee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_agentrate.d0Fee
     *
     * @param d0fee the value for t_mp_agentrate.d0Fee
     *
     * @mbggenerated
     */
    public void setD0fee(Integer d0fee) {
        this.d0fee = d0fee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_agentrate.aisleCode
     *
     * @return the value of t_mp_agentrate.aisleCode
     *
     * @mbggenerated
     */
    public String getAislecode() {
        return aislecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_agentrate.aisleCode
     *
     * @param aislecode the value for t_mp_agentrate.aisleCode
     *
     * @mbggenerated
     */
    public void setAislecode(String aislecode) {
        this.aislecode = aislecode == null ? null : aislecode.trim();
    }
}