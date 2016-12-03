package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TBrandManager;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.service.TMemberCollectManager;
import com.rc.portal.util.InfoUtil;
import com.rc.portal.vo.TBrand;
import com.rc.portal.vo.TCategory;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberCollectExample;
import com.rc.portal.webapp.util.PageResult;

public class GoodsListAction extends BaseAction {
	private Condition model = new Condition();
	private OpenSqlManage  opensqlmanage;
	private TCategoryManager tcategorymanager;
	private TBrandManager tbrandmanager;
	private TMemberCollectManager tmembercollectmanager;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private TCategory category;
	private TBrand brand;
	private String uri=InfoUtil.getInstance().getInfo("config", "ui1");
	
	public String getGoodsList() throws Exception{
		rs.setP_pageSize(10);
		Map map=new HashMap();
		if(model.getCategoryid()!=null&&model.getCategoryid()!=-1){
			map.put("categoryid", model.getCategoryid());
		}
		if(model.getSort()!=null&&model.getSort()!=-1){
			map.put("sort", model.getSort());
		}else{
			model.setSort(1);
			map.put("sort", 1);
		}
		category = tcategorymanager.selectByPrimaryKey(model.getCategoryid());
		pw=opensqlmanage.selectForPageByMap(map, "t_goods.selectCount", "t_goods.select_product_category", rs.getP_curPage(), rs.getP_pageSize());
		return "getGoodsList";
	}

	public void appendGoodsList() throws Exception{
		this.getRequest().setCharacterEncoding("UTF-8");
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out =this.getResponse().getWriter();
		rs.setP_pageSize(10);
		Map map=new HashMap();
		if(model.getCategoryid()!=null&&model.getCategoryid()!=-1){
			map.put("categoryid", model.getCategoryid());
		}
		if(model.getSort()!=null&&model.getSort()!=-1){
			map.put("sort", model.getSort());
		}else{
			model.setSort(1);
			map.put("sort", 1);
		}
		pw=opensqlmanage.selectForPageByMap(map, "t_goods.selectCount", "t_goods.select_product_category", rs.getP_curPage(), rs.getP_pageSize());
	
		 List <Map> result = pw.getResult();
		 String content="";
		 if(result.size()>0){
			 for(int i=0;i<result.size();i++){
				 content+="<li><a href='"+this.getRequest().getContextPath()+"/m/"+result.get(i).get("id")+".html' class='list-pro-info'><div class='new-list-pro'>";
						 if(result.get(i).get("discount")!=null&&new Long(result.get(i).get("discount").toString()).intValue()!=0&&new Long(result.get(i).get("discount").toString()).intValue()!=10){
							 content+="<span class='new-discount'>" +result.get(i).get("discount")+"折<i></i></span> ";
						 }
				 		 content+="<img src='"+uri+result.get(i).get("abbreviation_picture")+"'></div>  <p class='new-list-pro-title'>"+result.get(i).get("short_name")+"</p><p class='new-list-pro-price'>¥"
				 		 +result.get(i).get("price")+"</p><p><b class='new-list-pro-original'>￥"+result.get(i).get("market_price")+"</b></p>";
				 		 if((Integer)result.get(i).get("type")==2||(Integer)result.get(i).get("type")==3){
				 			content+= "<a href='"+this.getRequest().getContextPath()+"/m/"+result.get(i).get("id")+".html' class='iconfont new-list-pro-into-rx'></a>";
				 		 }else{
				 			 content+="<a href='javascript:void(0)' id='"+result.get(i).get("id")+"' class='iconfont new-list-pro-into-cart'></a>";
				 		 }
				 		content+="</a></li>";
			 }
		 }else{
			 content="-1";
		 }
			out.print(content);
			out.close();
	}
	
	
	public String getGoodsListByBrandId() throws Exception{
		rs.setP_pageSize(10);
		Map map=new HashMap();
		if(model.getBrandid()!=null&&model.getBrandid()!=-1){
			map.put("brandid", model.getBrandid());
		}
		if(model.getSort()!=null&&model.getSort()!=-1){
			map.put("sort", model.getSort());
		}else{
			model.setSort(1);
			map.put("sort", 1);
		}
		 brand = tbrandmanager.selectByPrimaryKey(model.getBrandid());
		 Integer flag=0;
		 TMember tm = (TMember)this.getRequest().getSession().getAttribute("member");
		 if(tm!=null){
			 TMemberCollectExample tce=new TMemberCollectExample();
			 tce.createCriteria().andMemberIdEqualTo(tm.getId()).andRelevanceIdEqualTo(brand.getId()).andCollectTypeEqualTo(1);
			 List l = tmembercollectmanager.selectByExample(tce);
			 if(l!=null&&l.size()>0){
				 flag=1;
			 }
		 }
		 this.getRequest().setAttribute("flag", flag);
		pw=opensqlmanage.selectForPageByMap(map, "t_goods.selectCount_brand", "t_goods.select_product_brand", rs.getP_curPage(), rs.getP_pageSize());
		return "getGoodsListByBrandId";
	}

	public void appendGoodsListByBrandId() throws Exception{
		this.getRequest().setCharacterEncoding("UTF-8");
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out =this.getResponse().getWriter();
		rs.setP_pageSize(10);
		Map map=new HashMap();
		if(model.getBrandid()!=null&&model.getBrandid()!=-1){
			map.put("brandid", model.getBrandid());
		}
		if(model.getSort()!=null&&model.getSort()!=-1){
			map.put("sort", model.getSort());
		}else{
			model.setSort(1);
			map.put("sort", 1);
		}
		pw=opensqlmanage.selectForPageByMap(map, "t_goods.selectCount_brand", "t_goods.select_product_brand", rs.getP_curPage(), rs.getP_pageSize());
	
		 List <Map> result = pw.getResult();
		 String content="";
		 if(result.size()>0){
			 for(int i=0;i<result.size();i++){
				 content+="<li><a href='"+this.getRequest().getContextPath()+"/m/"+result.get(i).get("id")+".html' class='list-pro-info'><div class='new-list-pro'>";
				 if(result.get(i).get("discount")!=null&&new Long(result.get(i).get("discount").toString()).intValue()!=0&&new Long(result.get(i).get("discount").toString()).intValue()!=10){
				 		content+="<span class='new-discount'>" +result.get(i).get("discount")+"折<i></i></span> ";
				 }
				 content+="<img src='"+uri+result.get(i).get("abbreviation_picture")+"'> </div><p class='new-list-pro-title'>"+result.get(i).get("short_name")+"</p><p class='new-list-pro-price'>¥"
				 		 +result.get(i).get("price")+"</p><p><b class='new-list-pro-original'>￥"+result.get(i).get("market_price")+"</b></p>" ;
				 
						 if((Integer)result.get(i).get("type")==2||(Integer)result.get(i).get("type")==3){
					 			content+= "<a href='"+this.getRequest().getContextPath()+"/m/"+result.get(i).get("id")+".html' class='iconfont new-list-pro-into-rx'></a>";
					 		 }else{
					 			 content+="<a href='javascript:void(0)' id='"+result.get(i).get("id")+"' class='iconfont new-list-pro-into-cart'></a>";
					 		 }
					 		content+="</a></li>";
			 }
		 }else{
			 content="-1";
		 }
			out.print(content);
			out.close();
	}
	
	/*
	 	map.put("parnterid", "1001");固定参数
		map.put("method", "search");固定参数
	 map.put("from", "1"); 来源 1-pc 2-wap 3-app
	map.put("sort", "1"); 排序字段 1-销量 2-评论 3-价格 4-折扣
	map.put("order", "2"); 排序 1-升序 2-降序
	map.put("pricea", "0"); 价格区间 低价格
	map.put("priceb", "9000"); 价格区间 高价格
	map.put("typeid", "-1"); 药品类型 1-OTC 2-处方A 3-处方B
	map.put("brandid", "-1"); 品牌id
	map.put("keyword","显瘦简约百"); 搜索关键字
	map.put("filtertype","1"); 搜索类型 1-返回搜索结果 2-返回品牌的集合 
	map.put("page", "1"); 分页 页码
	map.put("size", "20"); 分页 每页数量*/
	
	public String getGoodsListByKeyword() throws Exception{
		rs.setP_pageSize(10);
		Map map=new HashMap();
		map.put("parnterid", "1001");
		map.put("method", "search");
		map.put("from","2");
		if(model.getSort()!=null&&model.getSort()!=-1){
			if(model.getSort()==1){
				map.put("sort", "-1");	
			}else if(model.getSort()==2){
				map.put("sort", "4");
			}else if(model.getSort()==3){
				map.put("sort", "3");
			}
		}else{
			model.setSort(1);
			map.put("sort", "-1");
		}

		map.put("order", "2");
		if(model.getKeyword()!=null&&!"".equals(model.getKeyword().trim())){
			map.put("keyword",model.getKeyword().trim());
		}else{
			map.put("keyword", "");
		}
		map.put("pricea", "0");
		map.put("priceb", "9999999");
		map.put("typeid", "-1");
		map.put("brandid", "-1");
		map.put("filtertype","1");
		map.put("page", rs.getP_curPage().toString());
		map.put("size", rs.getP_pageSize().toString());			
		String YAO_GATEWAY_URL = InfoUtil.getInstance().getInfo("config", "yao_geteway_url");
		String requestByUrl = ClientSubmit.buildRequestForSearch(map,YAO_GATEWAY_URL);
		System.out.println("============="+requestByUrl);
		if(requestByUrl!=null&&!"".equals(requestByUrl)){
			JSONObject jsonObject = JSONObject.fromObject(requestByUrl);
			JSONArray jsonArray = jsonObject.getJSONArray("models");
			List<Map> ll = (List<Map>) jsonArray.toCollection(jsonArray, Map.class);
			for(int i=0;i<ll.size();i++){
				Map mapt=new HashMap();
				mapt.put("id", ll.get(i).get("goodsid"));
				mapt.put("abbreviation_picture",  ll.get(i).get("imgpath"));
				mapt.put("full_name", ll.get(i).get("fullname"));
				mapt.put("price", ll.get(i).get("realprice"));
				mapt.put("market_price", ll.get(i).get("price"));
				mapt.put("discount",ll.get(i).get("discount"));
				mapt.put("type",ll.get(i).get("isrx"));
				pw.getResult().add(mapt);
			}
			
		}
		
	/*	d.id,d.abbreviation_picture,d.short_name,truncate(d.wap_price,2) as price,truncate(d.price,2) as market_price,
		ceil (d.wap_price / d.price) as discount */
		return "getGoodsListByKeyword";
	}

	/*public String getGoodsListByKeyword() throws Exception{
		rs.setP_pageSize(10);
		Map map=new HashMap();
		if(model.getKeyword()!=null&&!"".equals(model.getKeyword().trim())){
			map.put("keyword", model.getKeyword().trim());
		}
		if(model.getSort()!=null&&model.getSort()!=-1){
			map.put("sort", model.getSort());
		}else{
			model.setSort(1);
			map.put("sort", 1);
		}
		pw=opensqlmanage.selectForPageByMap(map, "t_goods.selectCount_keyword", "t_goods.select_product_keyword", rs.getP_curPage(), rs.getP_pageSize());
		return "getGoodsListByKeyword";
	}*/
	public void appendGoodsListByKeyword() throws Exception{
		this.getRequest().setCharacterEncoding("UTF-8");
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out =this.getResponse().getWriter();
		rs.setP_pageSize(10);
		Map map=new HashMap();
		map.put("parnterid", "1001");
		map.put("method", "search");
		map.put("from","2");
		if(model.getSort()!=null&&model.getSort()!=-1){
			if(model.getSort()==1){
				map.put("sort", "-1");	
			}else if(model.getSort()==2){
				map.put("sort", "4");
			}else if(model.getSort()==3){
				map.put("sort", "3");
			}
		}else{
			model.setSort(1);
			map.put("sort", "-1");
		}

		map.put("order", "2");
		if(model.getKeyword()!=null&&!"".equals(model.getKeyword().trim())){
			map.put("keyword",model.getKeyword().trim());
		}else{
			map.put("keyword", "");
		}
		map.put("pricea", "0");
		map.put("priceb", "9999999");
		map.put("typeid", "-1");
		map.put("brandid", "-1");
		map.put("filtertype","1");
		map.put("page", rs.getP_curPage().toString());
		map.put("size", rs.getP_pageSize().toString());			
		String YAO_GATEWAY_URL = InfoUtil.getInstance().getInfo("config", "yao_geteway_url");
		String requestByUrl = ClientSubmit.buildRequestForSearch(map,YAO_GATEWAY_URL);
		System.out.println("============="+requestByUrl);
		if(requestByUrl!=null&&!"".equals(requestByUrl)){
			JSONObject jsonObject = JSONObject.fromObject(requestByUrl);
			JSONArray jsonArray = jsonObject.getJSONArray("models");
			List<Map> ll = (List<Map>) jsonArray.toCollection(jsonArray, Map.class);
			for(int i=0;i<ll.size();i++){
				Map mapt=new HashMap();
				mapt.put("id", ll.get(i).get("goodsid"));
				mapt.put("abbreviation_picture",  ll.get(i).get("imgpath"));
				mapt.put("full_name", ll.get(i).get("fullname"));
				mapt.put("price", ll.get(i).get("realprice"));
				mapt.put("market_price", ll.get(i).get("price"));
				mapt.put("discount",ll.get(i).get("discount"));
				mapt.put("type",ll.get(i).get("isrx"));
				pw.getResult().add(mapt);
			}
			
		}
	
		 List <Map> result = pw.getResult();
		 String content="";
		 if(result.size()>0){
			 for(int i=0;i<result.size();i++){
				 content+="<li><a href='"+this.getRequest().getContextPath()+"/m/"+result.get(i).get("id")+".html' class='list-pro-info'><div class='new-list-pro'>";
				 if(result.get(i).get("discount")!=null&&new Long(result.get(i).get("discount").toString()).intValue()!=0&&new Long(result.get(i).get("discount").toString()).intValue()!=10){
					 content+="<span class='new-discount'>"+result.get(i).get("discount")+"折<i></i></span>";
				 }
				 content+="<img src='"+uri+result.get(i).get("abbreviation_picture")+"'></div><p class='new-list-pro-title'>";
				 
			 if(result.get(i).get("full_name").toString().length()>14){
				 content+=result.get(i).get("full_name").toString().substring(0, 13)+"...";
			 }else{
				 content+=result.get(i).get("full_name");
			 }
				 content+="</p><p class='new-list-pro-price'>¥"+result.get(i).get("price")+"</p><p><b class='new-list-pro-original'>￥"+result.get(i).get("market_price")+"</b></p>" ;
				 
				 if(result.get(i).get("type").equals("1")){
			 			content+= "<a href='"+this.getRequest().getContextPath()+"/m/"+result.get(i).get("id")+".html' class='iconfont new-list-pro-into-rx'></a>";
			 		 }else{
			 			 content+="<a href='javascript:void(0)' id='"+result.get(i).get("id")+"' class='iconfont new-list-pro-into-cart'></a>";
			 		 }
			 		content+="</a></li>";
			 }
		 }else{
			 content="-1";
		 }
			out.print(content);
			out.close();
	}
	/*
	 *健康照顾方案根据分类ID异步获取商品接口 
	 * 
	 */
	public void jiankangGoodsList() throws Exception{
		this.getRequest().setCharacterEncoding("UTF-8");
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out =this.getResponse().getWriter();
		rs.setP_pageSize(6);
		Map map=new HashMap();
		if(model.getCategoryid()!=null&&model.getCategoryid()!=-1){
			map.put("categoryid", model.getCategoryid());
		}
		pw=opensqlmanage.selectForPageByMap(map, "t_goods.selectCount", "t_goods.select_product_category", rs.getP_curPage(), rs.getP_pageSize());
	
		 List <Map> result = pw.getResult();
		 String content="";
		 if(result.size()>0){
			 for(int i=0;i<result.size();i++){
				 content+="<li><a href='"+this.getRequest().getContextPath()+"/m/"+result.get(i).get("id")+".html' class='list-pro-info'><div class='new-list-pro'>" ;
				 if(result.get(i).get("discount")!=null&&new Long(result.get(i).get("discount").toString()).intValue()!=0&&new Long(result.get(i).get("discount").toString()).intValue()!=10){
					 content+="<span class='new-discount'>"+result.get(i).get("discount")+"折</span>";
					 
				 }
				 content+=" <img src='"+uri+result.get(i).get("abbreviation_picture")+"'></div><p class='new-list-pro-title'>"+result.get(i).get("short_name")+"</p><p class='new-list-pro-price'>¥"
						 +result.get(i).get("price")+"</p><p><b class='new-list-pro-original'>￥"+result.get(i).get("market_price")+"</b></p>" ;
				 
						 if((Integer)result.get(i).get("type")==2||(Integer)result.get(i).get("type")==3){
					 			content+= "<a href='"+this.getRequest().getContextPath()+"/m/"+result.get(i).get("id")+".html' class='iconfont new-list-pro-into-rx'></a>";
					 		 }else{
					 			 content+="<a href='javascript:void(0)' onclick='healthyAddCart(1, "+result.get(i).get("id")+")' id='"+result.get(i).get("id")+"' class='iconfont new-list-pro-into-cart'></a>";
					 		 }
					 		content+="</a></li>";
			 }
		 }else{
			 content="-1";
		 }
			out.print(content);
			out.close();
	}
	public class Condition {
		private Long categoryid;
		private Integer sort;//排序   1显示现货 2按照折扣   3按照价格
		private Long brandid;
		private String keyword;
		
		
		
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public Long getBrandid() {
			return brandid;
		}
		public void setBrandid(Long brandid) {
			this.brandid = brandid;
		}
		public Long getCategoryid() {
			return categoryid;
		}
		public void setCategoryid(Long categoryid) {
			this.categoryid = categoryid;
		}
		public Integer getSort() {
			return sort;
		}
		public void setSort(Integer sort) {
			this.sort = sort;
		}
		
		
		
		
	}
	
	
	@Override
	public Object getModel() {
		return this.model;
	}

	@Override
	public void setModel(Object o) {
		this.model = (Condition) o;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public PageWraper getPw() {
		return pw;
	}

	public void setPw(PageWraper pw) {
		this.pw = pw;
	}

	public PageResult getRs() {
		return rs;
	}

	public void setRs(PageResult rs) {
		this.rs = rs;
	}

	public TCategoryManager getTcategorymanager() {
		return tcategorymanager;
	}

	public void setTcategorymanager(TCategoryManager tcategorymanager) {
		this.tcategorymanager = tcategorymanager;
	}

	public TCategory getCategory() {
		return category;
	}

	public void setCategory(TCategory category) {
		this.category = category;
	}

	public TBrandManager getTbrandmanager() {
		return tbrandmanager;
	}

	public void setTbrandmanager(TBrandManager tbrandmanager) {
		this.tbrandmanager = tbrandmanager;
	}

	public TBrand getBrand() {
		return brand;
	}

	public void setBrand(TBrand brand) {
		this.brand = brand;
	}

	public TMemberCollectManager getTmembercollectmanager() {
		return tmembercollectmanager;
	}

	public void setTmembercollectmanager(TMemberCollectManager tmembercollectmanager) {
		this.tmembercollectmanager = tmembercollectmanager;
	}
	
	
}
