package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.service.TMemberLeaderManager;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberAccount;
import com.rc.portal.vo.TMemberLeaderExample;

/**
 * 新版健康领秀Action
 * 
 * @author WWF
 * @createTime 2016-6-3 下午9:00:42
 */
public class TLeaderCenterAction extends BaseAction {

	private static final long serialVersionUID = 4547545603139602241L;
	private TMemberAccountManager tmemberaccountmanager;
	private TLeaderManager tleadermanager;
	private OpenSqlManage opensqlmanage;
	
	private TMemberLeaderManager tmemberleadermanager;
	
	private TGoodsManager tgoodsmanager;
	
	
	
	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}

	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
	}

	public TMemberLeaderManager getTmemberleadermanager() {
		return tmemberleadermanager;
	}

	public void setTmemberleadermanager(TMemberLeaderManager tmemberleadermanager) {
		this.tmemberleadermanager = tmemberleadermanager;
	}

	public TMemberAccountManager getTmemberaccountmanager() {
		return tmemberaccountmanager;
	}

	public void setTmemberaccountmanager(TMemberAccountManager tmemberaccountmanager) {
		this.tmemberaccountmanager = tmemberaccountmanager;
	}


	/**
	 * 管理页面
	 * @throws SQLException 
	 */
	public String index() throws SQLException {
		//领秀头像,名称,秀粉个数,我的收益;
		TMember member = (TMember)this.getSession().getAttribute("member");
		Long memberId = member.getId();
		TLeader leader = tleadermanager.selectLeaderByMemberId(memberId);
		
		TMemberAccount tMemberAccount = tmemberaccountmanager.selectByPrimaryKey(memberId);
		
		//BigDecimal incomeAmount = getIncomeAmount(tMemberAccount);
		BigDecimal incomeAmount = null;
		if(null != tMemberAccount){
			 incomeAmount = tMemberAccount.getRemainingAmount().add(tMemberAccount.getWaitAmount());
		}else{
			 incomeAmount = new BigDecimal(0);
		}
		TMemberLeaderExample tMemberLeaderExample = new TMemberLeaderExample();
		tMemberLeaderExample.createCriteria().andLeaderIdEqualTo(leader.getId());
		int fanCount = tmemberleadermanager.countByExample(tMemberLeaderExample);
		
		
		getRequest().setAttribute("leader", leader);
		getRequest().setAttribute("incomeAmount", incomeAmount);
		getRequest().setAttribute("fanCount", fanCount);
		return "leadermanagecenter";
	}
	
	
	/**
	 * 跳转到健康领袖功能介绍
	 * @return
	 */
	public String leaderFunctionIntroduce(){
		return "leader_function_introduce";
	}
	
	
	public void getGoodAndLeaderDetail() throws NumberFormatException, SQLException{
		String goodId = getRequest().getParameter("goodId");
		//String leaderId = getRequest().getParameter("leaderId");
		
		//TLeader leader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
		TGoods good = tgoodsmanager.selectByPrimaryKey(Long.valueOf(goodId));
		
		Map goodAndLeaderMap = new HashMap();
		goodAndLeaderMap.put("good", good); 
		//goodAndLeaderMap.put("leader", leader);
		writeObjectToResponse(goodAndLeaderMap, ContentType.application_json);
		
	}
	public void ajaxClickLikeArticle() throws NumberFormatException, SQLException, IOException{
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
	
	public void clickArticleShareSum() throws NumberFormatException, SQLException, IOException{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  this.getResponse().getWriter();
	    String id = this.getRequest().getParameter("articleId");
	    Map map = new HashMap();
	    map.put("id", id);
	    try {
			opensqlmanage.selectForObjectByMap(map,
					"t_leader_article.clickArticleShareSum");
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
	    try {
			opensqlmanage.selectForObjectByMap(map,
					"t_leader_article.addLookSum");
		} catch (Exception e) {
		}
		out.print("1");
		out.close();
		
	}
	
	public BigDecimal getIncomeAmount(TMemberAccount tMemberAccount){
		BigDecimal remainAmount = tMemberAccount.getRemainingAmount()==null?new BigDecimal(0):tMemberAccount.getRemainingAmount();
		
		BigDecimal waitAmount = tMemberAccount.getWaitAmount()==null?new BigDecimal(0):tMemberAccount.getWaitAmount();
		
		return remainAmount.add(waitAmount);
		
	}
	
	/**
	 * 我的领秀海报页面;
	 * @return
	 * @throws SQLException 
	 */
	public String myPosterPage() throws SQLException{
		TMember tMember = (TMember) getSession().getAttribute("member");
		if (tMember == null) {// 返回登录页
			return "profile_page";//member/profile.action
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(tMember.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		
		return "my_poster_page";
	}
	/**
	 * 我的药房海报页面;
	 * @return
	 * @throws SQLException 
	 */
	public String myPharmacyPage() throws SQLException{
		TMember tMember = (TMember) getSession().getAttribute("member");
		if (tMember == null) {// 返回登录页
			return "profile_page";//member/profile.action
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(tMember.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		
		return "my_pharmacy_page";
	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {

	}

	public TLeaderManager getTleadermanager() {
		return tleadermanager;
	}

	public void setTleadermanager(TLeaderManager tleadermanager) {
		this.tleadermanager = tleadermanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

}
