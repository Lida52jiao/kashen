package com.battcn.entity;

import java.util.Date;

public class SureRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_surerecord.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_surerecord.useUser
     *
     * @mbggenerated
     */
    private String useuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_surerecord.undoAgentId
     *
     * @mbggenerated
     */
    private String undoagentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_surerecord.bindAgentId
     *
     * @mbggenerated
     */
    private String bindagentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_surerecord.bindMerchantId
     *
     * @mbggenerated
     */
    private String bindmerchantid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_surerecord.bindTime
     *
     * @mbggenerated
     */
    private Date bindtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mp_surerecord.agentStatus
     *
     * @mbggenerated
     */
    private String agentstatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_surerecord.id
     *
     * @return the value of t_mp_surerecord.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_surerecord.id
     *
     * @param id the value for t_mp_surerecord.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_surerecord.useUser
     *
     * @return the value of t_mp_surerecord.useUser
     *
     * @mbggenerated
     */
    public String getUseuser() {
        return useuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_surerecord.useUser
     *
     * @param useuser the value for t_mp_surerecord.useUser
     *
     * @mbggenerated
     */
    public void setUseuser(String useuser) {
        this.useuser = useuser == null ? null : useuser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_surerecord.undoAgentId
     *
     * @return the value of t_mp_surerecord.undoAgentId
     *
     * @mbggenerated
     */
    public String getUndoagentid() {
        return undoagentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_surerecord.undoAgentId
     *
     * @param undoagentid the value for t_mp_surerecord.undoAgentId
     *
     * @mbggenerated
     */
    public void setUndoagentid(String undoagentid) {
        this.undoagentid = undoagentid == null ? null : undoagentid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_surerecord.bindAgentId
     *
     * @return the value of t_mp_surerecord.bindAgentId
     *
     * @mbggenerated
     */
    public String getBindagentid() {
        return bindagentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_surerecord.bindAgentId
     *
     * @param bindagentid the value for t_mp_surerecord.bindAgentId
     *
     * @mbggenerated
     */
    public void setBindagentid(String bindagentid) {
        this.bindagentid = bindagentid == null ? null : bindagentid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_surerecord.bindMerchantId
     *
     * @return the value of t_mp_surerecord.bindMerchantId
     *
     * @mbggenerated
     */
    public String getBindmerchantid() {
        return bindmerchantid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_surerecord.bindMerchantId
     *
     * @param bindmerchantid the value for t_mp_surerecord.bindMerchantId
     *
     * @mbggenerated
     */
    public void setBindmerchantid(String bindmerchantid) {
        this.bindmerchantid = bindmerchantid == null ? null : bindmerchantid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_surerecord.bindTime
     *
     * @return the value of t_mp_surerecord.bindTime
     *
     * @mbggenerated
     */
    public Date getBindtime() {
        return bindtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_surerecord.bindTime
     *
     * @param bindtime the value for t_mp_surerecord.bindTime
     *
     * @mbggenerated
     */
    public void setBindtime(Date bindtime) {
        this.bindtime = bindtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mp_surerecord.agentStatus
     *
     * @return the value of t_mp_surerecord.agentStatus
     *
     * @mbggenerated
     */
    public String getAgentstatus() {
        return agentstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mp_surerecord.agentStatus
     *
     * @param agentstatus the value for t_mp_surerecord.agentStatus
     *
     * @mbggenerated
     */
    public void setAgentstatus(String agentstatus) {
        this.agentstatus = agentstatus == null ? null : agentstatus.trim();
    }
}