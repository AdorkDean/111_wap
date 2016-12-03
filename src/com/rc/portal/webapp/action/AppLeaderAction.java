package com.rc.portal.webapp.action;


import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.service.TMemberAmountOutManager;
import com.rc.portal.util.cookieManager;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberAmountOut;
import com.rc.portal.webapp.util.PageResult;

/**
 * 领袖
 *
 */
public class AppLeaderAction extends BaseAction{

	private static final long serialVersionUID = 672274541L;

	private TMemberAccountManager tmemberaccountmanager;
	
	private TMemberAmountOutManager tmemberamountoutmanager;
	
	private TMemberAmountOut amountOut;
	
	private OpenSqlManage opensqlmanage;
	
	private TLeaderManager tleadermanager;
	
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	
	private String name ;
	private String type = "1";
	private String url;
	
	/**
	 * 领袖商品列表
	 * @return
	 * @throws SQLException
	 */
	public String list()throws SQLException{
		Map<String,Object> querMap = new HashMap<String,Object>();
		querMap.put("orderby", " ORDER BY b.brokerage DESC");
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_goods_brokerage.selectListPageCount", "t_goods_brokerage.selectListPage", rs.getP_curPage(), rs.getP_pageSize());
		return "list";
	}
	
	/**
	 * 下拉分页list
	 * @return
	 * @throws SQLException
	 */
	public void appendGoodsList()throws Exception{
		this.getRequest().setCharacterEncoding("UTF-8");
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out =this.getResponse().getWriter();
		rs.setP_pageSize(10);
		
		Map<String,Object> querMap = new HashMap<String,Object>();
		querMap.put("orderby", " ORDER BY b.brokerage DESC");
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_goods_brokerage.selectListPageCount", "t_goods_brokerage.selectListPage", rs.getP_curPage(), rs.getP_pageSize());

		 List <Map> result = pw.getResult();
		 String content="";
		 if(result.size()>0){
			 for(int i=0;i<result.size();i++){
			 content+= "<tr><td height='60' align='center' valign='middle' width='30%'>"+result.get(i).get("short_name")+"</td><td height='60' align='center' valign='middle' width='20%'>"+result.get(i).get("spec")+
					   "</td><td height='60' align='center' valign='middle' width='25%'>￥"+result.get(i).get("wap_price")+"</td><td height='60' align='center' valign='middle' width='25%'>"+result.get(i).get("brokerage")+"%</td></tr>";
			 }
		 }else{
			 content="-1";
		 }
		 out.print(content);
		 out.close();
		
	}
	
   /**
    * 领袖排行
    * @return
 * @throws SQLException 
    */
	public String ranking() throws SQLException{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		Map<String,Object> querMap = new HashMap<String,Object>();
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		List  list = tleadermanager.selectByExample(tle);
		if(null != list && list.size()>0){
			TLeader  tleader = (TLeader) list.get(0);
			this.getRequest().setAttribute("tleader", tleader); 
		}
		rs.setP_pageSize(5);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_member_leader.selectCountLeaderCount", "t_member_leader.selectCountLeader",  rs.getP_curPage(), rs.getP_pageSize());
		List leaderList = pw.getResult();
		this.getRequest().setAttribute("leaderList", leaderList); //领袖药丸数排行
		pw = opensqlmanage.selectForPageByMap(querMap, "t_member_leader.selectLeaderAmountCount", "t_member_leader.selectLeaderAmount",  rs.getP_curPage(), rs.getP_pageSize());
		List amountList = pw.getResult();
		this.getRequest().setAttribute("amountList", amountList); //领袖佣金排行
		
		return "ranking";
	}
	
	/**
	 * 领袖药丸排行
	 * @return
	 * @throws SQLException
	 */
	public String leaderPill() throws SQLException{
		Map<String,Object> querMap = new HashMap<String,Object>();
		rs.setP_pageSize(20);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_member_leader.selectCountLeaderCount", "t_member_leader.selectCountLeader",  rs.getP_curPage(), rs.getP_pageSize());
		return "pill";
	}
	
	/**
	 * 佣金排行
	 * @return
	 * @throws SQLException
	 */
	public String leaderBrokerage() throws SQLException{
		Map<String,Object> querMap = new HashMap<String,Object>();
		rs.setP_pageSize(20);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_member_leader.selectLeaderAmountCount", "t_member_leader.selectLeaderAmount",  rs.getP_curPage(), rs.getP_pageSize());
		return "brokerage";
	}
	
	/**
	 * 领袖中心
	 * @return
	 */
	public String leader(){
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		Map<String,Object> querMap = new HashMap<String,Object>();
//		TMember member = new TMember();
//		member.setId(101L);
//		this.getSession().setAttribute("member", member);
//		querMap.put("id", 101);
		querMap.put("id", sessionMember.getId());
		Map map = (Map) opensqlmanage.selectForObjectByMap(querMap, "t_leader.selectLeaderMember");
		this.getRequest().setAttribute("leaderMap", map);
		return "leader";
	}
	
	/**
	 * 分享领袖
	 * @return
	 * @throws SQLException 
	 */
	public String shareLeader() throws SQLException{
		String code = this.getRequest().getParameter("code"); //获取领袖编码
		String hurl = this.getRequest().getParameter("hurl"); //获取跳转地址
		if(null != code && !("").equals(code) && null != hurl && !("").equals(hurl)){
			TLeaderExample te = new TLeaderExample();
			te.createCriteria().andCodeEqualTo(code);
			List list = tleadermanager.selectByExample(te);
			if(null != list && list.size()==1){
				cookieManager.addCookie(this.getRequest(), this.getResponse(), "leader", code, 100000,".111yao.com");
			    url = hurl;
			}else{
				url="/";
			}
		}else{
			url="/";
		}
		return "url";
	}
	
	
	public TMemberAccountManager getTmemberaccountmanager() {
		return tmemberaccountmanager;
	}

	public void setTmemberaccountmanager(TMemberAccountManager tmemberaccountmanager) {
		this.tmemberaccountmanager = tmemberaccountmanager;
	}

	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {}

	public TMemberAmountOut getAmountOut() {
		return amountOut;
	}

	public void setAmountOut(TMemberAmountOut amountOut) {
		this.amountOut = amountOut;
	}

	public TMemberAmountOutManager getTmemberamountoutmanager() {
		return tmemberamountoutmanager;
	}

	public void setTmemberamountoutmanager(
			TMemberAmountOutManager tmemberamountoutmanager) {
		this.tmemberamountoutmanager = tmemberamountoutmanager;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TLeaderManager getTleadermanager() {
		return tleadermanager;
	}

	public void setTleadermanager(TLeaderManager tleadermanager) {
		this.tleadermanager = tleadermanager;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
