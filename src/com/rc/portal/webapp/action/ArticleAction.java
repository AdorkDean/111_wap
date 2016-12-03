package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLxLookCollectionManager;
import com.rc.portal.service.TLxShareCollectionManager;
import com.rc.portal.vo.TLxLookCollection;
import com.rc.portal.vo.TLxShareCollection;

/**
 * 健康领秀文章统计
 * @author Administrator
 *
 */
public class ArticleAction extends BaseAction {
	
	
	
	private OpenSqlManage opensqlmanage;
	private TLxShareCollectionManager tlxsharecollectionmanager;
	private TLxLookCollectionManager tlxlookcollectionmanager;
	
	/**
	 * 获取分享文章的数量
	 * @throws Exception
	 */
	public void getArticle() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    String id = this.getRequest().getParameter("id");
	    Map map = new HashMap();
	    map.put("id", id);
	    Integer count = (Integer) opensqlmanage.selectForObjectByMap(map, "t_lx_share_collection.selectCount_share");
	    System.out.println(count);
		PrintWriter out =  getResponse().getWriter();
		out.print(count);
		out.close();
	}
	
	/**
	 * 打开分享文章的次数
	 * @throws Exception
	 */
	public void openArticle() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    String id = this.getRequest().getParameter("id");
	    Map map = new HashMap();
	    map.put("id", id);
	    Integer count = (Integer) opensqlmanage.selectForObjectByMap(map, "t_lx_look_collection.selectCount_look");
	    System.out.println(count);
		PrintWriter out =  getResponse().getWriter();
		out.print(count);
		out.close();
	}

	/**
	 * 添加文章打开
	 * @throws Exception
	 */
	public void setOpenArticle() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("id");
	    try {
			TLxLookCollection look = new TLxLookCollection();
			look.setCreateDt(new Date());
			look.setRelationType(1);
			look.setRelationId(new Long(id));
			tlxlookcollectionmanager.insertSelective(look);
		} catch (Exception e) {
			
		}
		out.print("");
		out.close();
	}
	
	/**
	 * 添加文章商品打开
	 * @throws Exception
	 */
	public void setOpenArticleGoods() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    String id = this.getRequest().getParameter("id");
	    String aid = this.getRequest().getParameter("aid");
	    PrintWriter out =  this.getResponse().getWriter();
	    try {
	    	//打开商品
			TLxLookCollection look = new TLxLookCollection();
			look.setCreateDt(new Date());
			look.setRelationType(2);
			look.setRelationId(new Long(id));
			tlxlookcollectionmanager.insertSelective(look);

			//打开文章商品
			TLxLookCollection look1 = new TLxLookCollection();
			look1.setCreateDt(new Date());
			look1.setRelationType(3);
			look1.setRelationId(new Long(aid));
			tlxlookcollectionmanager.insertSelective(look1);
		} catch (Exception e) {
		}
		out.print("");
		out.close();
	}
	/**
	 * 添加分享文章
	 * @throws Exception
	 */
	public void setShareArticle() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("id");
	    try {
			TLxShareCollection share = new TLxShareCollection();
			share.setCreateDt(new Date());
			share.setRelationId(new Long(id));
			share.setRelationType(1);
			tlxsharecollectionmanager.insertSelective(share);
		} catch (Exception e) {
		}
		out.print("");
		out.close();
	}
	
	/**
	 * 文章点赞
	 */
	public void clickLikeSum()throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("id");
	    Map map = new HashMap();
	    map.put("id", id);
	    try {
			opensqlmanage.selectForObjectByMap(map,
					"t_lx_goods_article.clickLikeSum");
		} catch (Exception e) {
		}
		out.print("1");
		out.close();
	}
	
	/**
	 * 文章点赞2
	 */
	public void clickGoodsArticleLikeSum()throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("id");
	    Map map = new HashMap();
	    map.put("id", id);
	    long click_like_sum = 0;
	    try {
			opensqlmanage.selectForObjectByMap(map,
					"t_lx_goods_article.clickLikeSum");
			Map rsMap = (Map) opensqlmanage.selectForObjectByMap(map,
					"t_lx_goods_article.getLookAndLike");
			click_like_sum = (long) rsMap.get("click_like_sum");
		} catch (Exception e) {
		}
		out.print(click_like_sum);
		out.close();
	}
	
	
	/**
	 * 获取文章阅读数量以及点赞数量
	 * @throws Exception
	 */
	public void getReadArticle()throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("id");
	    Map map = new HashMap();
	    map.put("id", id);
	    String str = "-1";
	    try {
	    	Map rmap = (Map) opensqlmanage.selectForObjectByMap(map,
					"t_lx_goods_article.getLookAndLike");
	    	if(null != rmap){
	    		if(null != rmap.get("look")){
	    			str = rmap.get("look")+"_";
	    		}else{
	    			str = "0_";
	    		}
	    		if(null != rmap.get("click_like_sum")){
	    			str += rmap.get("click_like_sum");
	    		}else{
	    			str += "0";
	    		}
	    		System.out.println("-----"+str);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(str);
		out.close();
	}

	
	
	public void ajaxClickLikeLeaderArticle() throws NumberFormatException, SQLException, IOException{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("articleId");
	    Map map = new HashMap();
	    map.put("id", id);
	    int articleLikeSum = 0;
	    try {
			opensqlmanage.selectForObjectByMap(map,
					"t_leader_article.clickArticleLikeSum");
			articleLikeSum  = (int) opensqlmanage.selectForObjectByMap(map,
					"t_leader_article.getArticleLikeSum");
		} catch (Exception e) {
		}
		out.print(articleLikeSum);
		out.close();
		
	}
	
	public void clickLeaderArticleShareSum() throws NumberFormatException, SQLException, IOException{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("articleId");
	    Map map = new HashMap();
	    map.put("id", id);
	    int articleCommentSum = 0;
	    try {
			opensqlmanage.selectForObjectByMap(map,
					"t_leader_article.clickArticleShareSum");
			articleCommentSum  = (int) opensqlmanage.selectForObjectByMap(map,
					"t_leader_article.getArticleCommentSum");
		} catch (Exception e) {
		}
		out.print(articleCommentSum);
		out.close();
		
	}
	
	public void ajaxGetLeaderArticleInfo() throws NumberFormatException, SQLException, IOException{
	    PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("articleId");
	    Map map = new HashMap();
	    map.put("id", id);
	    Map articleInfoMap = new HashMap();
	    try {
			articleInfoMap  =  (Map) opensqlmanage.selectForObjectByMap(map,
					"t_leader_article.getLeaderArticleInfo");
		} catch (Exception e) {
		}
	    writeObjectToResponse(articleInfoMap,ContentType.application_json);
		
	}
	/**
	 * 修改阅读数
	 * @throws NumberFormatException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void addLeaderArticleLookSum() throws NumberFormatException, SQLException, IOException{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("articleId");
	    Map map = new HashMap();
	    map.put("id", id);
	    try {
			opensqlmanage.selectForObjectByMap(map,
					"t_leader_article.addLookSum");
		} catch (Exception e) {
		}
		out.print("1");
		out.close();
		
	}
	
	/**
	 * 修改阅读数
	 * @throws NumberFormatException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void addLookSum() throws NumberFormatException, SQLException, IOException{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("articleId");
	    Map map = new HashMap();
	    map.put("id", id);
	    int articleLikeSum = 0;
	    try {
			opensqlmanage.selectForObjectByMap(map,
					"t_leader_article.addLookSum");
			articleLikeSum  = (int) opensqlmanage.selectForObjectByMap(map,
					"t_leader_article.getArticleLookSum");
		} catch (Exception e) {
		}
		out.print(articleLikeSum);
		out.close();
		
	}
	public static void main(String[] args) {

	}
	
	public TLxLookCollectionManager getTlxlookcollectionmanager() {
		return tlxlookcollectionmanager;
	}

	public void setTlxlookcollectionmanager(
			TLxLookCollectionManager tlxlookcollectionmanager) {
		this.tlxlookcollectionmanager = tlxlookcollectionmanager;
	}

	public TLxShareCollectionManager getTlxsharecollectionmanager() {
		return tlxsharecollectionmanager;
	}

	public void setTlxsharecollectionmanager(
			TLxShareCollectionManager tlxsharecollectionmanager) {
		this.tlxsharecollectionmanager = tlxsharecollectionmanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub
	}
	
}
