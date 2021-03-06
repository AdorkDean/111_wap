package com.rc.portal.webapp.model;

import java.math.BigDecimal;

public class ShortGoods {
    private Long id;

    private Long goodsId;

    private BigDecimal price;

    private Integer limitCount;

    private Integer totalCount;

    private Long shortBuyId;
    
    private String gname;
    
    
    
    
    public String getGname() {
		return gname;
	}


	public void setGname(String gname) {
		this.gname = gname;
	}


	


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Long getShortBuyId() {
        return shortBuyId;
    }

    public void setShortBuyId(Long shortBuyId) {
        this.shortBuyId = shortBuyId;
    }
}