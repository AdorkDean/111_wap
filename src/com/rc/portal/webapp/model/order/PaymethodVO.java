package com.rc.portal.webapp.model.order;

import java.io.Serializable;
/**
 * 购物车支付方式 VO 根据地址变化
 * @author user3
 *
 */
public class PaymethodVO implements Serializable{
	private static final long serialVersionUID = -8013490270541390262L;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 支付方式id
	 */
	private String paymethodId;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPaymethodId() {
		return paymethodId;
	}

	public void setPaymethodId(String paymethodId) {
		this.paymethodId = paymethodId;
	}
	

}
