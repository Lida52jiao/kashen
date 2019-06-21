package com.battcn.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Dada on 2018/11/13.
 */
@Table(name = "t_mp_hierarchical")
public class Numfee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "merType")
    private String merType;
    @Column(name = "aisleCode")
    private String aisleCode;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "recommendAmount")
    private String recommendAmount;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
    }

    public String getAisleCode() {
        return aisleCode;
    }

    public void setAisleCode(String aisleCode) {
        this.aisleCode = aisleCode;
    }



    public String getRecommendAmount() {
        return recommendAmount;
    }

    public void setRecommendAmount(String recommendAmount) {
        this.recommendAmount = recommendAmount;
    }
}
