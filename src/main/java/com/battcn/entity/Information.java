package com.battcn.entity;

import javax.persistence.*;

/**
 * Created by 61968 on 2018/7/11.
 */
@Table(name = "t_information")
public class Information implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "imgURL")
    private String imgURL;
    @Column(name = "titles")
    private String titles;
    @Column(name = "content")
    private String content;
    @Column(name = "creatDate")
    private Long creatDate;
    @Column(name = "forwardURL")
    private String forwardURL;
    @Column(name = "type")
    private String type;
    @Column(name = "institutionId")
    private String institutionId;
    @Column(name = "appId")
    private String appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
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

    public String getForwardURL() {
        return forwardURL;
    }

    public void setForwardURL(String forwardURL) {
        this.forwardURL = forwardURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
