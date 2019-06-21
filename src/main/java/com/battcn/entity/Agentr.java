package com.battcn.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Dada on 2018/11/21.
 */
@Table(name = "t_mp_agentrate")
public class Agentr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "merchantid")
    private String merchantid;
    @Column(name = "agentid")
    private String agentid;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "d0fee")
    private Integer d0fee;
    @Column(name = "aislecode")
    private String aislecode;

    public Agentr() {
    }

    public Agentr(String merchantid, String agentid, BigDecimal rate, Integer d0fee, String aislecode) {
        this.merchantid = merchantid;
        this.agentid = agentid;
        this.rate = rate;
        this.d0fee = d0fee;
        this.aislecode = aislecode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getD0fee() {
        return d0fee;
    }

    public void setD0fee(Integer d0fee) {
        this.d0fee = d0fee;
    }

    public String getAislecode() {
        return aislecode;
    }

    public void setAislecode(String aislecode) {
        this.aislecode = aislecode;
    }
}
