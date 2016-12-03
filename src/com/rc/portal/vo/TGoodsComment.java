package com.rc.portal.vo;

import java.util.Date;

public class TGoodsComment {
    private Long id;

    private Integer sumFraction;

    private Integer type;

    private Integer seller;

    private Integer fastMail;

    private Integer goods;

    private Integer fastMailPeople;

    private Integer isShow;

    private String comment;

    private Long memberId;

    private Long goodsId;

    private Long orderId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSumFraction() {
        return sumFraction;
    }

    public void setSumFraction(Integer sumFraction) {
        this.sumFraction = sumFraction;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    public Integer getFastMail() {
        return fastMail;
    }

    public void setFastMail(Integer fastMail) {
        this.fastMail = fastMail;
    }

    public Integer getGoods() {
        return goods;
    }

    public void setGoods(Integer goods) {
        this.goods = goods;
    }

    public Integer getFastMailPeople() {
        return fastMailPeople;
    }

    public void setFastMailPeople(Integer fastMailPeople) {
        this.fastMailPeople = fastMailPeople;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}