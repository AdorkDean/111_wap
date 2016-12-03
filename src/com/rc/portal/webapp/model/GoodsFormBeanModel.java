package com.rc.portal.webapp.model;

import java.util.List;

import com.rc.portal.vo.TCategory;

public class GoodsFormBeanModel {
	private String shortname;//商品名称
	private String goodsno;//商品编号
	private String goodsimgid;//商品图片
	private String goodsgids;//组合商品id
	private String goodsgidsm;//组合商品主商品id
	private String goodspids;//赠送商品id
	private String categoryId;//分类id
	private String yiyaoguanId;//医药馆id
	private String yiyaoguanName;//医药馆name;
	private String categoryId1;//分类id
	private String yiyaoguanId1;//医药馆id
	private String fl;//分类下拉
	private String fl1;
	private String fl2;
	private String fl3;
	private String yyg;//医药馆下拉
	private String yyg1;
	private String yyg2;
	private String yyg3;
	private List<TCategory> list1;//医药馆
	private List<TCategory> list2;
	private List<TCategory> list3;
	private List<TCategory> list4s;
	private List<Long> list4;
	private String manufacturer;//生产厂家名称
	private String manufacturerId;//生产厂家id
	private String dose;//剂量下拉
	private String brand;//品牌下拉
	
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getGoodsimgid() {
		return goodsimgid;
	}
	public void setGoodsimgid(String goodsimgid) {
		this.goodsimgid = goodsimgid;
	}
	public String getGoodsgids() {
		return goodsgids;
	}
	public void setGoodsgids(String goodsgids) {
		this.goodsgids = goodsgids;
	}
	public String getGoodsgidsm() {
		return goodsgidsm;
	}
	public void setGoodsgidsm(String goodsgidsm) {
		this.goodsgidsm = goodsgidsm;
	}
	public String getGoodspids() {
		return goodspids;
	}
	public void setGoodspids(String goodspids) {
		this.goodspids = goodspids;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getYiyaoguanId() {
		return yiyaoguanId;
	}
	public void setYiyaoguanId(String yiyaoguanId) {
		this.yiyaoguanId = yiyaoguanId;
	}
	public String getFl() {
		return fl;
	}
	public void setFl(String fl) {
		this.fl = fl;
	}
	public String getYyg() {
		return yyg;
	}
	public void setYyg(String yyg) {
		this.yyg = yyg;
	}
	public String getFl1() {
		return fl1;
	}
	public void setFl1(String fl1) {
		this.fl1 = fl1;
	}
	public String getFl2() {
		return fl2;
	}
	public void setFl2(String fl2) {
		this.fl2 = fl2;
	}
	public String getFl3() {
		return fl3;
	}
	public void setFl3(String fl3) {
		this.fl3 = fl3;
	}
	public String getCategoryId1() {
		return categoryId1;
	}
	public void setCategoryId1(String categoryId1) {
		this.categoryId1 = categoryId1;
	}
	public String getYiyaoguanId1() {
		return yiyaoguanId1;
	}
	public void setYiyaoguanId1(String yiyaoguanId1) {
		this.yiyaoguanId1 = yiyaoguanId1;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getYyg1() {
		return yyg1;
	}
	public void setYyg1(String yyg1) {
		this.yyg1 = yyg1;
	}
	public String getYyg2() {
		return yyg2;
	}
	public void setYyg2(String yyg2) {
		this.yyg2 = yyg2;
	}
	public String getYyg3() {
		return yyg3;
	}
	public void setYyg3(String yyg3) {
		this.yyg3 = yyg3;
	}
	public List<TCategory> getList1() {
		return list1;
	}
	public void setList1(List<TCategory> list1) {
		this.list1 = list1;
	}
	public List<TCategory> getList2() {
		return list2;
	}
	public void setList2(List<TCategory> list2) {
		this.list2 = list2;
	}
	public List<TCategory> getList3() {
		return list3;
	}
	public void setList3(List<TCategory> list3) {
		this.list3 = list3;
	}
	public String getYiyaoguanName() {
		return yiyaoguanName;
	}
	public void setYiyaoguanName(String yiyaoguanName) {
		this.yiyaoguanName = yiyaoguanName;
	}
	public List<Long> getList4() {
		return list4;
	}
	public void setList4(List<Long> list4) {
		this.list4 = list4;
	}
	public String getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public List<TCategory> getList4s() {
		return list4s;
	}
	public void setList4s(List<TCategory> list4s) {
		this.list4s = list4s;
	}
	
}
