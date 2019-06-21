package com.battcn.entity;

import javax.persistence.*;

/**
 * Created by bin on 2018/8/22.
 */
@Table(name = "epos_shop_commodity")
public class CommodityEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commodityId", unique = true, nullable = false)
    private Long commodityId;

    @Column(name = "institutionId")
    private String institutionId;//机构号
    @Column(name = "appId")
    private String appId;//APPID

    @Column(name = "open")
    private String open;

    @Column(name = "commodityName")
    private String commodityName;
    @Column(name = "description")
    private String description;
    @Column(name = "img")
    private String img;

    @Column(name = "price")
    private Long price;
    @Column(name = "salesVolume")
    private Long salesVolume;

    @Column(name = "remarks")
    private String remarks;

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
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

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Long salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
