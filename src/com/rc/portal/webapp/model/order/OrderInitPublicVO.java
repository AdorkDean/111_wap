package com.rc.portal.webapp.model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单添加daoVO
 * 
 * @author user3
 * 
 */
public class OrderInitPublicVO implements Serializable {
	private static final long serialVersionUID = -7848888954192790919L;
	/**
	 * 促销折扣
	 */
	private BigDecimal promotionDiscount;
	/**
	 * 订单商品价格总和
	 */
	private BigDecimal price;
	/**
	 * 应付金额
	 */
	private BigDecimal amountPayable;
	/**
	 *  积分抵扣 
	 */
	private BigDecimal pointDiscount;
	/**
	 * 运费
	 */
	private BigDecimal freight;
	/**
	 * 配送方式id
	 */
	private String deliveryId;
	/**
	 * 配送方式名称
	 */
	private String deliveryName;
	/**
	 * 配送方式描述
	 */
	private String deliveryInstro;
	
	/**
	 * 收货地址
	 */
	private ReceiverVo receiver;
	/**
	 * 赠送积分
	 */
	private Long sendPoint;
	/**
	 * 支付方式list
	 * @return
	 */
	private List<PaymethodVO> paymethodList = new ArrayList<PaymethodVO>();
	/**
	 * 购物车商品集合
	 */
	private  List<CartGoodVO> goodsList;
	
	/**
	 * 可用优惠券集合
	 */
	private List<Map<String, Object>> memberCouponUserList;
	
	/**
	 * 可用优惠券id
	 */
    private String couponCardId;
	/**
	 * 优惠券名称
	 */
    private String couponName;
    /**
	 * 优惠券折扣
	 */
	private BigDecimal couponDiscount;
	
	
	public BigDecimal getPromotionDiscount() {
		return promotionDiscount;
	}

	public void setPromotionDiscount(BigDecimal promotionDiscount) {
		this.promotionDiscount = promotionDiscount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(BigDecimal amountPayable) {
		this.amountPayable = amountPayable;
	}

	public BigDecimal getPointDiscount() {
		return pointDiscount;
	}

	public void setPointDiscount(BigDecimal pointDiscount) {
		this.pointDiscount = pointDiscount;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public BigDecimal getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(BigDecimal couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public ReceiverVo getReceiver() {
		return receiver;
	}

	public void setReceiver(ReceiverVo receiver) {
		this.receiver = receiver;
	}

	public Long getSendPoint() {
		return sendPoint;
	}

	public void setSendPoint(Long sendPoint) {
		this.sendPoint = sendPoint;
	}

	public List<PaymethodVO> getPaymethodList() {
		return paymethodList;
	}

	public void setPaymethodList(List<PaymethodVO> paymethodList) {
		this.paymethodList = paymethodList;
	}

	public List<CartGoodVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<CartGoodVO> goodsList) {
		this.goodsList = goodsList;
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryInstro() {
		return deliveryInstro;
	}

	public void setDeliveryInstro(String deliveryInstro) {
		this.deliveryInstro = deliveryInstro;
	}
 

	public List<Map<String, Object>> getMemberCouponUserList() {
		return memberCouponUserList;
	}

	public void setMemberCouponUserList(
			List<Map<String, Object>> memberCouponUserList) {
		this.memberCouponUserList = memberCouponUserList;
	}

	public String getCouponCardId() {
		return couponCardId;
	}

	public void setCouponCardId(String couponCardId) {
		this.couponCardId = couponCardId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	

}
