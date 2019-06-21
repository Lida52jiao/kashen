package com.battcn.entity;

import javax.persistence.*;

/**
 * Created by 61968 on 2018/7/11.
 */
@Table(name = "t_circle")
public class Circle implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "faceURL")
    private String faceURL;
    @Column(name = "name")
    private String name;
    @Column(name = "imgURL")
    private String imgURL;
    @Column(name = "content")
    private String content;
    @Column(name = "creatDate")
    private Long creatDate;
    @Column(name = "answerTimes")
    private Long answerTimes;
    @Column(name = "amount")
    private String amount;
    @Column(name = "shows")
    private String shows;
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
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
