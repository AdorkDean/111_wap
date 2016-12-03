package com.rc.portal.webapp.model.cart;


public class CartParam {

	
	private boolean islogin = false;
	
	private long userId;
	
	private String cartkey;
	
	private long goodsId;
	
	public CartParam(){}
	

	public CartParam(boolean islogin, long userId, String cartkey, long goodsId) {
		this.islogin = islogin;
		this.userId = userId;
		this.cartkey = cartkey;
		this.goodsId = goodsId;
	}


	public CartParam(boolean islogin, long userId, String cartkey) {
		this.islogin = islogin;
		this.userId = userId;
		this.cartkey = cartkey;
	}


	public boolean isIslogin() {
		return islogin;
	}


	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getCartkey() {
		return cartkey;
	}


	public void setCartkey(String cartkey) {
		this.cartkey = cartkey;
	}


	public long getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	

}
