package com.rc.portal.vo;

import java.util.Date;

public class THealthyPlan {
    private Long id;

    private Long classificationId;

    private String name;

    private String planExplain;

    private String bannerImgUrl;

    private String planImgUrl;

    private String reminder;

    private Integer weight;

    private Integer status;

    private Date createDt;

    private Integer deleteStatus;

    private String wxShareUrl;

    private String wxShareContent;

    private Long goodsCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanExplain() {
        return planExplain;
    }

    public void setPlanExplain(String planExplain) {
        this.planExplain = planExplain;
    }

    public String getBannerImgUrl() {
        return bannerImgUrl;
    }

    public void setBannerImgUrl(String bannerImgUrl) {
        this.bannerImgUrl = bannerImgUrl;
    }

    public String getPlanImgUrl() {
        return planImgUrl;
    }

    public void setPlanImgUrl(String planImgUrl) {
        this.planImgUrl = planImgUrl;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getWxShareUrl() {
        return wxShareUrl;
    }

    public void setWxShareUrl(String wxShareUrl) {
        this.wxShareUrl = wxShareUrl;
    }

    public String getWxShareContent() {
        return wxShareContent;
    }

    public void setWxShareContent(String wxShareContent) {
        this.wxShareContent = wxShareContent;
    }

    public Long getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(Long goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }
}