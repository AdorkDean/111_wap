package com.rc.portal.webapp.action;

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
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberAccount;
import com.rc.portal.vo.TMemberAmountOut;
import com.rc.portal.webapp.util.PageResult;
/*
 * 健康领袖会员提现
 */
public class MemberAmountOutAction extends BaseAction{
	private static final long serialVersionUID = 1313132313;
	private TMemberAccountManager tmemberaccountmanager;
	private TMemberAmountOutManager tmemberamountoutmanager;
	private TMemberAmountOut amountOut;
	private TMemberAccount  account;
	private OpenSqlManage opensqlmanage;
	
	private TLeaderManager tleadermanager;
	
	
	/**
	 * 提现申请,判断状态
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void judgeLeader() throws NumberFormatException, SQLException{
		String leaderId = this.getRequest().getParameter("leaderId");
		ResultData resultData = new ResultData();
		if(leaderId==null || "".equals(leaderId)){
			resultData.setStatus(3);//不是领袖
			resultData.setMessage("您尚未申请领袖");
		}else{
			TLeader tLeader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
			if(tLeader==null){
				resultData.setStatus(3);//不是领袖
				resultData.setMessage("您尚未申请领袖");
			}else if(tLeader.getAuditType()!=null && tLeader.getAuditType()!=1){
				resultData.setStatus(2);//不是领袖
				resultData.setMessage("未审核通过");
			}
		}
	}
	
	
	
	
	/**
	 * 提现申请跳转
	 * @return
	 * @throws SQLException 
	 */
	public String amountOut() throws SQLException{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		TMemberAccount account = tmemberaccountmanager.selectByPrimaryKey(sessionMember.getId());
		
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		TLeader tLeader = null;
		List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);
		if(list==null || list.size()<=0){
			return "apply_healt_leader";
		}else{
			tLeader = list.get(0);
		}
		
		if(tLeader.getAuditType()==3){//游客;
			return "youke_page";
		}else if(tLeader.getAuditType()==2){//不通过
			return "not_pass";
		}else if(tLeader.getAuditType()==1){//通过
			Map<String,Object> q = new HashMap<String,Object>();
			q.put("id", sessionMember.getId());
			Map qmap = (Map) opensqlmanage.selectForObjectByMap(q, "t_leader.selectLeaderObject");
			this.getRequest().setAttribute("account", account);
			return "amountOut";
		}else if(tLeader.getAuditType()==0){//已提交
			return "has_submit";
		}
		
		return "apply_healt_leader";
		
	}
	
	/**
	 * 提交提现申请
	 * @return
	 * @throws SQLException
	 */
	public String subAmountOut() throws SQLException{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		amountOut.setMemberId(sessionMember.getId());
		Long id = tmemberamountoutmanager.insertAmountOut(amountOut);
		this.getRequest().setAttribute("err", id);
		TMemberAccount account = tmemberaccountmanager.selectByPrimaryKey(sessionMember.getId());
		this.getRequest().setAttribute("account", account);
		return "amountOut";
	}	
	
	/**
	 * 提现列表
	 * @return
	 * @throws SQLException
	 */
	public String list()throws SQLException{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		Map<String,Object> querMap = new HashMap<String,Object>();
		querMap.put("member_id", sessionMember.getId());
		rs.setP_pageSize(5);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_member_amount_out.selectListPageCount", "t_member_amount_out.selectListPage", rs.getP_curPage(), rs.getP_pageSize());
		Double lj = (Double) opensqlmanage.selectForObjectByMap(querMap, "t_member_amount_out.selectAmountSum");
		this.getRequest().setAttribute("lj", lj);
		querMap.put("status", 3);
		Double ljs = (Double) opensqlmanage.selectForObjectByMap(querMap, "t_member_amount_out.selectAmountSum");
		this.getRequest().setAttribute("ljs", ljs);
		account = tmemberaccountmanager.selectByPrimaryKey(sessionMember.getId());
		
		return "amountList";
	}
	
	public TMemberAccountManager getTmemberaccountmanager() {
		return tmemberaccountmanager;
	}

	public void setTmemberaccountmanager(TMemberAccountManager tmemberaccountmanager) {
		this.tmemberaccountmanager = tmemberaccountmanager;
	}

	public TMemberAmountOutManager getTmemberamountoutmanager() {
		return tmemberamountoutmanager;
	}

	public void setTmemberamountoutmanager(
			TMemberAmountOutManager tmemberamountoutmanager) {
		this.tmemberamountoutmanager = tmemberamountoutmanager;
	}

	public TMemberAmountOut getAmountOut() {
		return amountOut;
	}

	public void setAmountOut(TMemberAmountOut amountOut) {
		this.amountOut = amountOut;
	}

	public TMemberAccount getAccount() {
		return account;
	}

	public void setAccount(TMemberAccount account) {
		this.account = account;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TLeaderManager getTleadermanager() {
		return tleadermanager;
	}


	public void setTleadermanager(TLeaderManager tleadermanager) {
		this.tleadermanager = tleadermanager;
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

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub
		
	}
	
	class ResultData{
		private int status;
		private String message;
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
	}

}
