package com.rc.portal.webapp.model.cart;

import java.math.BigDecimal;

public class CartPromModel {

	private Long goods_id;
	private Integer quantity;
	private Long promotion_id;
	private Integer type;
	private BigDecimal wap_price;
	private Long categoryid;
	private int stock;

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Long getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(Long promotion_id) {
		this.promotion_id = promotion_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCategoryid() {
		return categoryid;
	}

	public BigDecimal getWap_price() {
		return wap_price;
	}

	public void setWap_price(BigDecimal wap_price) {
		this.wap_price = wap_price;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

}
