package com.rc.portal.webapp.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * 健康方案主要内容
 *
 */
public class HealthyPlanContentModel {
	private	Long id;	// 方案id
	private String name;//方案名称
	private String explain;//方案描述
	private String bannerImgUrl;//banner图地址
	private String reminder; //温馨提示
	private Integer status;//是否发布
	
	private List<Symptom> lisths;//并发症
	private List<Combination> listhc;//组合套装
	
	private Integer ssum;   //数量
	private Integer csum;   //数量
	
	private Long goodsCategoryId;
	
	private String categoryName;
	
	private String shareImageUrl;
	private String shareWords;
	
	public String getShareImageUrl() {
		return shareImageUrl;
	}
	public void setShareImageUrl(String shareImageUrl) {
		this.shareImageUrl = shareImageUrl;
	}
	public String getShareWords() {
		return shareWords;
	}
	public void setShareWords(String shareWords) {
		this.shareWords = shareWords;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSsum() {
		return ssum;
	}
	public void setSsum(Integer ssum) {
		this.ssum = ssum;
	}
	public Integer getCsum() {
		return csum;
	}
	public void setCsum(Integer csum) {
		this.csum = csum;
	}
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
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getBannerImgUrl() {
		return bannerImgUrl;
	}
	public void setBannerImgUrl(String bannerImgUrl) {
		this.bannerImgUrl = bannerImgUrl;
	}
	public String getReminder() {
		return reminder;
	}
	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
	public List<Symptom> getLisths() {
		return lisths;
	}
	public void setLisths(List<Symptom> lisths) {
		this.lisths = lisths;
	}
	public List<Combination> getListhc() {
		return listhc;
	}
	public void setListhc(List<Combination> listhc) {
		this.listhc = listhc;
	}
	//并发症
	public class Symptom{
		private Long sid;//并发症id
		private String sname;//并发症名称
		private String sexplain;//并发症描述
		private String sbannerImgUrl;//并发症banner图地址
		private Integer weight;   //权重  由大到小
		private Integer type;//类型   0 辅药   1 主药
		private List<Goods> lists;//并发症商品
		private Integer gsum;   //数量
	
		
		public Integer getGsum() {
			return gsum;
		}

		public void setGsum(Integer gsum) {
			this.gsum = gsum;
		}

		public Long getSid() {
			return sid;
		}
	
		public void setSid(Long sid) {
			this.sid = sid;
		}
	
		public String getSname() {
			return sname;
		}
	
		public void setSname(String sname) {
			this.sname = sname;
		}
	
		public String getSexplain() {
			return sexplain;
		}
	
		public void setSexplain(String sexplain) {
			this.sexplain = sexplain;
		}
	
		public String getSbannerImgUrl() {
			return sbannerImgUrl;
		}
	
		public void setSbannerImgUrl(String sbannerImgUrl) {
			this.sbannerImgUrl = sbannerImgUrl;
		}
	
		public Integer getWeight() {
			return weight;
		}
	
		public void setWeight(Integer weight) {
			this.weight = weight;
		}
	
		public Integer getType() {
			return type;
		}
	
		public void setType(Integer type) {
			this.type = type;
		}
	
		public List<Goods> getLists() {
			return lists;
		}
	
		public void setLists(List<Goods> lists) {
			this.lists = lists;
		}
	}
	//套装组合
	public class Combination{
		private Long cid;//组合id
		private String cname;//组合名称
		private String commentContent;//药师点评
		private BigDecimal savePrice;//节省金额
		private BigDecimal ccPrice;//组合价格
		private BigDecimal cPrice;//组合原价
		
		private String realName;//医师真实姓名
		private String workYear;//医师从业年限
		private String headUrl;//医师头像
		private String remark;//医师备注
		
		private List<Goods> listc;//组合商品
		private Integer gsum;   //数量
		
		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public Integer getGsum() {
			return gsum;
		}

		public void setGsum(Integer gsum) {
			this.gsum = gsum;
		}

		public Long getCid() {
			return cid;
		}
	
		public String getRealName() {
			return realName;
		}

		public void setRealName(String realName) {
			this.realName = realName;
		}

		public String getWorkYear() {
			return workYear;
		}

		public void setWorkYear(String workYear) {
			this.workYear = workYear;
		}

		public String getHeadUrl() {
			return headUrl;
		}

		public void setHeadUrl(String headUrl) {
			this.headUrl = headUrl;
		}

		public void setCid(Long cid) {
			this.cid = cid;
		}
	
		public String getCname() {
			return cname;
		}
	
		public void setCname(String cname) {
			this.cname = cname;
		}
	
		public String getCommentContent() {
			return commentContent;
		}
	
		public void setCommentContent(String commentContent) {
			this.commentContent = commentContent;
		}
	
		public BigDecimal getSavePrice() {
			return savePrice;
		}
	
		public void setSavePrice(BigDecimal savePrice) {
			this.savePrice = savePrice;
		}
	
		public BigDecimal getCcPrice() {
			return ccPrice;
		}
	
		public void setCcPrice(BigDecimal ccPrice) {
			this.ccPrice = ccPrice;
		}
	
		public BigDecimal getcPrice() {
			return cPrice;
		}
	
		public void setcPrice(BigDecimal cPrice) {
			this.cPrice = cPrice;
		}
	
		public List<Goods> getListc() {
			return listc;
		}
	
		public void setListc(List<Goods> listc) {
			this.listc = listc;
		}
	}
	//商品
	public class Goods{
		private Long gid;//商品id
		private String gImgUrl;//商品缩略图
		private BigDecimal appPrice;//APP价格
		private BigDecimal wapPrice;//wap价格
		private BigDecimal price;//市场价格
		private String mainTitle;//主标题
		private String gname;//全称
		private String sname;//简称
		private int type;//1：OTC 2：处方药A 3：处方药B
		private String spec;//规格
		
		public String getSpec() {
			return spec;
		}
		public void setSpec(String spec) {
			this.spec = spec;
		}
		public String getSname() {
			return sname;
		}
		public void setSname(String sname) {
			this.sname = sname;
		}
		public Long getGid() {
			return gid;
		}
		public void setGid(Long gid) {
			this.gid = gid;
		}
		public String getgImgUrl() {
			return gImgUrl;
		}
		public void setgImgUrl(String gImgUrl) {
			this.gImgUrl = gImgUrl;
		}
		public BigDecimal getAppPrice() {
			return appPrice;
		}
		public void setAppPrice(BigDecimal appPrice) {
			this.appPrice = appPrice;
		}
		public BigDecimal getWapPrice() {
			return wapPrice;
		}
		public void setWapPrice(BigDecimal wapPrice) {
			this.wapPrice = wapPrice;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public String getMainTitle() {
			return mainTitle;
		}
		public void setMainTitle(String mainTitle) {
			this.mainTitle = mainTitle;
		}
		public String getGname() {
			return gname;
		}
		public void setGname(String gname) {
			this.gname = gname;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		
	}
	public Long getGoodsCategoryId() {
		return goodsCategoryId;
	}
	public void setGoodsCategoryId(Long goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}
	
}