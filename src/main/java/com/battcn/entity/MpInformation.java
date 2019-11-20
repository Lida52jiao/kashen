package com.battcn.entity;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_mp_information")
public class MpInformation implements Serializable {

    private static final long serialVersionUID = -7346949094430240819L;
    private Long id;
    private String merChantId;
    private String userIDCardA;//身份证正面
    private String userIDCardB;//身份证反面
    private String cardImgA;//银行卡正面
    private String faceImg;//手持身份证
    private String institutionId;
    private String appId;
    private String agentId;
    private String merName;
    private String mobile;
    private Long createTime;
    private String validity;//身份证有效期
    private String state;//活体认证状态（1成功2失败）
    private String artificial;//是否人工审核（1否2是）
    private String scanImg;//活体认证图片

    private String startTime;
    private String finshTime;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(String finshTime) {
        this.finshTime = finshTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerChantId() {
        return merChantId;
    }

    public void setMerChantId(String merChantId) {
        this.merChantId = merChantId;
    }

    public String getUserIDCardA() {
        return userIDCardA;
    }

    public void setUserIDCardA(String userIDCardA) {
        this.userIDCardA = userIDCardA;
    }

    public String getUserIDCardB() {
        return userIDCardB;
    }

    public void setUserIDCardB(String userIDCardB) {
        this.userIDCardB = userIDCardB;
    }

    public String getCardImgA() {
        return cardImgA;
    }

    public void setCardImgA(String cardImgA) {
        this.cardImgA = cardImgA;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
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

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getArtificial() {
        return artificial;
    }

    public void setArtificial(String artificial) {
        this.artificial = artificial;
    }

    public String getScanImg() {
        return scanImg;
    }

    public void setScanImg(String scanImg) {
        this.scanImg = scanImg;
    }
}
