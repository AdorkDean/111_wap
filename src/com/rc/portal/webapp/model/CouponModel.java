package com.rc.portal.webapp.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 壹劵专区领劵
 *
 */
public class CouponModel {
	
	private Long id;

    private String name;

    private Date startTime;

    private Date endTime;

    private BigDecimal disPrice;

    private Integer status;

    private Integer scope;

	private Integer isget;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getDisPrice() {
		return disPrice;
	}

	public void setDisPrice(BigDecimal disPrice) {
		this.disPrice = disPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

	public Integer getIsget() {
		return isget;
	}

	public void setIsget(Integer isget) {
		this.isget = isget;
	}
	
}
