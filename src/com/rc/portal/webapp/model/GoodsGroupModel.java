package com.rc.portal.webapp.model;

public class GoodsGroupModel {
	private Long id;
	private Long group_id;
	private Integer goods_num;
	private String short_name;
	private String abbreviation_picture;
	private Integer main_goods;
	private Long goodsid;
	private String spec;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Long groupId) {
		group_id = groupId;
	}
	public Integer getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(Integer goodsNum) {
		goods_num = goodsNum;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String shortName) {
		short_name = shortName;
	}
	public String getAbbreviation_picture() {
		return abbreviation_picture;
	}
	public void setAbbreviation_picture(String abbreviationPicture) {
		abbreviation_picture = abbreviationPicture;
	}
	public Integer getMain_goods() {
		return main_goods;
	}
	public void setMain_goods(Integer mainGoods) {
		main_goods = mainGoods;
	}
	public Long getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
}
