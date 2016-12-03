package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.List;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.util.CookieUtil;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TMember;

public class LinShiAction extends BaseAction{
	private TLeaderManager tleadermanager;
	public TLeaderManager getTleadermanager() {
		return tleadermanager;
	}

	public void setTleadermanager(TLeaderManager tleadermanager) {
		this.tleadermanager = tleadermanager;
	}

	/**
	 * 健康领秀临时方法
	 */
	private static final long serialVersionUID = -4578692763615274741L;

	@SuppressWarnings("unchecked")
	public void isLeader() throws Exception{
		int flag=-1;
		PrintWriter out = this.getResponse().getWriter();
		TMember member = (TMember)this.getSession().getAttribute("member");
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(member.getId());
		List<TLeader> lists = tleadermanager.selectByExample(tle);
		if(lists.size()>0){
			flag=1;	//领秀存在
		}else{
			this.getSession().removeAttribute("member");
			this.getSession().removeAttribute("leader");
			CookieUtil.removeCookie(getRequest(), getResponse(), "username", "/", "");
			CookieUtil.removeCookie(getRequest(), getResponse(), "nickname", "/", "");
			CookieUtil.removeCookie(getRequest(), getResponse(), "cookusername", "/", ".111yao.com");
			CookieUtil.removeCookie(getRequest(), getResponse(), "cookpassword", "/", ".111yao.com");
			flag=2;//领秀不存在
		}
		out.print(flag);
		out.close();
	}
	/*
	 * 转到客服客服咨询页
	 * @see com.rc.app.framework.webapp.action.BaseAction#getModel()
	 */
	public String customerIndex(){
		
		return "customerIndex";
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
