package com.battcn.entity;

import javax.persistence.*;

/**
 * Created by bin on 2018/7/11.
 */
@Table(name = "p_config")
public class ConfigEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "institutionId")
    private String institutionId;
    @Column(name = "appId")
    private String appId;
    @Column(name = "checkin")
    private Long checkin;
    @Column(name = "realName")
    private Long realName;
    @Column(name = "pay")
    private Long pay;
    @Column(name = "remarks")
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCheckin() {
        return checkin;
    }

    public void setCheckin(Long checkin) {
        this.checkin = checkin;
    }

    public Long getRealName() {
        return realName;
    }

    public void setRealName(Long realName) {
        this.realName = realName;
    }

    public Long getPay() {
        return pay;
    }

    public void setPay(Long pay) {
        this.pay = pay;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
