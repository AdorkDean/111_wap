package com.rc.portal.webapp.action;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TLeaderPharmacyManager;
import com.rc.portal.webapp.util.PageResult;

/**
 * 领袖药房
 * 
 * @author WWF
 * @createTime 2016-6-3 下午2:36:32
 */
public class TLeaderPharmacyAction extends BaseAction {

	private static final long serialVersionUID = 6629641554866274534L;

	private TLeaderManager tleadermanager;
	private OpenSqlManage opensqlmanage;
	private TLeaderPharmacyManager tleaderpharmacymanager;

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();

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

	public TLeaderPharmacyManager getTleaderpharmacymanager() {
		return tleaderpharmacymanager;
	}

	public void setTleaderpharmacymanager(TLeaderPharmacyManager tleaderpharmacymanager) {
		this.tleaderpharmacymanager = tleaderpharmacymanager;
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
}
