package com.rc.portal.vo;

import java.util.Date;

public class TPrescription {
    private Long id;

    private Long userId;

    private String userName;

    private String diseaseDescrip;

    private Integer status;

    private String callUsername;

    private Date callTime;

    private String phone;

    private String orderSn;

    private String imageUrl;

    private Date uploadTime;

    private Date createDate;

    private Date modifyDate;

    private Integer ifhelp;

    private String returning;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDiseaseDescrip() {
        return diseaseDescrip;
    }

    public void setDiseaseDescrip(String diseaseDescrip) {
        this.diseaseDescrip = diseaseDescrip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCallUsername() {
        return callUsername;
    }

    public void setCallUsername(String callUsername) {
        this.callUsername = callUsername;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getIfhelp() {
        return ifhelp;
    }

    public void setIfhelp(Integer ifhelp) {
        this.ifhelp = ifhelp;
    }

    public String getReturning() {
        return returning;
    }

    public void setReturning(String returning) {
        this.returning = returning;
    }
}