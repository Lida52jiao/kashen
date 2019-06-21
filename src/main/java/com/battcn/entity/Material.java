package com.battcn.entity;

import javax.persistence.*;

/**
 * Created by 61968 on 2018/7/11.
 */
public class Material implements java.io.Serializable {
    private Long id;
    private String faceURL;
    private String name;
    private String oneURL;
    private String twoURL;
    private String threeURL;
    private String fourURL;
    private String fiveURL;
    private String sixURL;
    private String sevenURL;
    private String eightURL;
    private String nineURL;
    private String content;
    private Long creatDate;
    private Long answerTimes;
    private String amount;
    private String shows;
    private String institutionId;
    private String appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFaceURL() {
        return faceURL;
    }

    public void setFaceURL(String faceURL) {
        this.faceURL = faceURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOneURL() {
        return oneURL;
    }

    public void setOneURL(String oneURL) {
        this.oneURL = oneURL;
    }

    public String getTwoURL() {
        return twoURL;
    }

    public void setTwoURL(String twoURL) {
        this.twoURL = twoURL;
    }

    public String getThreeURL() {
        return threeURL;
    }

    public void setThreeURL(String threeURL) {
        this.threeURL = threeURL;
    }

    public String getFourURL() {
        return fourURL;
    }

    public void setFourURL(String fourURL) {
        this.fourURL = fourURL;
    }

    public String getFiveURL() {
        return fiveURL;
    }

    public void setFiveURL(String fiveURL) {
        this.fiveURL = fiveURL;
    }

    public String getSixURL() {
        return sixURL;
    }

    public void setSixURL(String sixURL) {
        this.sixURL = sixURL;
    }

    public String getSevenURL() {
        return sevenURL;
    }

    public void setSevenURL(String sevenURL) {
        this.sevenURL = sevenURL;
    }

    public String getEightURL() {
        return eightURL;
    }

    public void setEightURL(String eightURL) {
        this.eightURL = eightURL;
    }

    public String getNineURL() {
        return nineURL;
    }

    public void setNineURL(String nineURL) {
        this.nineURL = nineURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Long creatDate) {
        this.creatDate = creatDate;
    }

    public Long getAnswerTimes() {
        return answerTimes;
    }

    public void setAnswerTimes(Long answerTimes) {
        this.answerTimes = answerTimes;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getShows() {
        return shows;
    }

    public void setShows(String shows) {
        this.shows = shows;
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
}
