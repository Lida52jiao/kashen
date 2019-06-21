package com.battcn.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dada on 2018/11/20.
 */
@Table(name = "t_mp_agentarea")
public class AgentBindArea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "agentId")
    private String agentId;
    @Column(name = "merChantId")
    private String merChantId;
    @Column(name = "areaCode")
    private String areaCode;
    @Column(name = "areaLevel")
    private Integer areaLevel;
    @Column(name = "areaName")
    private String areaName;
    @Column(name = "areaId")
    private Integer areaId;
    @Column(name = "areaParentId")
    private Integer areaParentId;
    @Column(name = "createdTime")
    private Date createdTime;
    @Column(name = "merName")
    private String merName;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getMerChantId() {
        return merChantId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setMerChantId(String merChantId) {
        this.merChantId = merChantId;
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getAreaParentId() {
        return areaParentId;
    }

    public void setAreaParentId(Integer areaParentId) {
        this.areaParentId = areaParentId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }
}
