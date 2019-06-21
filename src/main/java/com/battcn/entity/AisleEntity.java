package com.battcn.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by bin on 2018/8/14.
 */
@Table(name = "c_aisle")
public class AisleEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "orderBy")
    private Long orderBy;

    @Column(name = "createTime")
    private String createTime;
    @Column(name = "institutionId")
    private String institutionId;
    @Column(name = "appId")
    private String appId;

    @Column(name = "aisleCode")
    private String aisleCode;
    @Column(name = "aisleName")
    private String aisleName;//
    @Column(name = "description")
    private String description;
    @Column(name = "icon")
    private String icon;
    @Column(name = "open")
    private String open;

    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "d0Fee")
    private Long d0Fee;

    @Column(name = "d0FeeType")
    private String d0FeeType;

    @Column(name ="url")
    private String url;

    @Column(name ="maxAmount")
    private Long maxAmount;

    @Column(name ="minAmount")
    private Long minAmount;

    @Column(name = "remarks")
    private String remarks;

    public String getD0FeeType() {
        return d0FeeType;
    }

    public void setD0FeeType(String d0FeeType) {
        this.d0FeeType = d0FeeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Long orderBy) {
        this.orderBy = orderBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAisleCode() {
        return aisleCode;
    }

    public void setAisleCode(String aisleCode) {
        this.aisleCode = aisleCode;
    }

    public String getAisleName() {
        return aisleName;
    }

    public void setAisleName(String aisleName) {
        this.aisleName = aisleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getD0Fee() {
        return d0Fee;
    }

    public void setD0Fee(Long d0Fee) {
        this.d0Fee = d0Fee;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Long getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Long minAmount) {
        this.minAmount = minAmount;
    }
}
