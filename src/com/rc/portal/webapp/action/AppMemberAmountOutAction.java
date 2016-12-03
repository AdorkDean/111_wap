package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.service.TMemberAmountOutManager;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberAccount;
import com.rc.portal.vo.TMemberAmountOut;
import com.rc.portal.webapp.util.PageResult;

public class AppMemberAmountOutAction extends BaseAction{
	private static final long serialVersionUID = 13131323134l;
	private TMemberAccountManager tmemberaccountmanager;
	private TMemberAmountOutManager tmemberamountoutmanager;
	private TMemberAmountOut amountOut;
	private TMemberAccount  account;
	private OpenSqlManage opensqlmanage;
	/**
	 * 提现申请跳转
	 * @return
	 * @throws SQLException 
	 */
	public String amountOut() throws SQLException{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		TMemberAccount account = tmemberaccountmanager.selectByPrimaryKey(sessionMember.getId());
		Map<String,Object> q = new HashMap<String,Object>();
		q.put("id", sessionMember.getId());
		Map qmap = (Map) opensqlmanage.selectForObjectByMap(q, "t_leader.selectLeaderObject");
		this.getRequest().setAttribute("account", account);
		return "amountOut";
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
}
