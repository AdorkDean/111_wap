package com.rc.portal.vo;

import java.util.Date;

public class CImage {
    private Integer id;

    private String imgurl;

    private String name;

    private Date shootingDt;

    private String bak;

    private Date createDt;

	private Integer imgType;
    
    private Integer weight;
    
    private Integer status;
    
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getWeight() {
    	return weight;
    }
    
    public void setWeight(Integer weight) {
    	this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getShootingDt() {
        return shootingDt;
    }

    public void setShootingDt(Date shootingDt) {
        this.shootingDt = shootingDt;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }
}