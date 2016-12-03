package com.rc.portal.webapp.action;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.vo.TLeader;
/**
 * 分享公共action
 * @author 刘天灵
 */
public class ShareLeaderAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {}
	
	private OpenSqlManage opensqlmanage;
	
	private TLeaderManager tleadermanager;
	/**
	 * 根据佣金ID查找所有文章
	 * 获取海报路径
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String poster() throws Exception{
		
		String leaderId = this.getRequest().getParameter("leaderId");
		
		TLeader leader = tleadermanager.selectByPrimaryKey(Long.parseLong(leaderId));
		
		String poster = (String)opensqlmanage.selectObjectByObject(leader.getId(), "t_leader_qr_code.select_poster");
		if(StringUtils.isEmpty(poster)){
			poster = "/static/image/haibao/"+leader.getCode()+"_"+leader.getId()+".jpg";
		}
		
		this.getRequest().setAttribute("poster", poster);
		return "poster";
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
	
}
