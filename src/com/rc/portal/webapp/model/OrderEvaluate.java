package com.rc.portal.webapp.model;

import java.io.Serializable;

import com.rc.portal.vo.TOrder;

public class OrderEvaluate extends TOrder implements Serializable{

	private static final long serialVersionUID = 3523534251L;
	
	private String abbreviationPicture;

	public String getAbbreviationPicture() {
		return abbreviationPicture;
	}

	public void setAbbreviationPicture(String abbreviationPicture) {
		this.abbreviationPicture = abbreviationPicture;
	}
}
