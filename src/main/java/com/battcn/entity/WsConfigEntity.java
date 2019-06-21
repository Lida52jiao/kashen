package com.battcn.entity;

import javax.persistence.*;

@Table(name = "ws_config")
public class WsConfigEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "userPrice")
    private Long userPrice;
    @Column(name = "wsName")
    private String wsName;
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "institutionId")
    private String institutionId;

    @Column(name = "self")
    private Long self;
    @Column(name = "oneMer")
    private Long oneMer;
    @Column(name = "twoMer")
    private Long twoMer;
    @Column(name = "oneAgent")
    private Long oneAgent;
    @Column(name = "towAgent")
    private Long towAgent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(Long userPrice) {
        this.userPrice = userPrice;
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
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

    public Long getSelf() {
        return self;
    }

    public void setSelf(Long self) {
        this.self = self;
    }

    public Long getOneMer() {
        return oneMer;
    }

    public void setOneMer(Long oneMer) {
        this.oneMer = oneMer;
    }

    public Long getTwoMer() {
        return twoMer;
    }

    public void setTwoMer(Long twoMer) {
        this.twoMer = twoMer;
    }

    public Long getOneAgent() {
        return oneAgent;
    }

    public void setOneAgent(Long oneAgent) {
        this.oneAgent = oneAgent;
    }

    public Long getTowAgent() {
        return towAgent;
    }

    public void setTowAgent(Long towAgent) {
        this.towAgent = towAgent;
    }
}
