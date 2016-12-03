package com.rc.portal.webapp.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.CLocationManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberReceiverManager;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberReceiver;
import com.rc.portal.vo.TMemberReceiverExample;

/**
 * 收货地址管理
 * @author 刘天灵 LGP
 * @date 2015-09-18
 */
@SuppressWarnings("unchecked")
public class MemberReceiverAction extends BaseAction {

	private static final long serialVersionUID = 54984714474392403L;
	private TMemberReceiverManager tmemberreceivermanager;
	private CLocationManager clocationmanager;
	private OpenSqlManage opensqlmanage;
	private TMemberReceiver tmemberreceiver = new TMemberReceiver();
	
	public CLocationManager getClocationmanager() {
		return clocationmanager;
	}

	public void setClocationmanager(CLocationManager clocationmanager) {
		this.clocationmanager = clocationmanager;
	}

	public TMemberReceiverManager getTmemberreceivermanager() {
		return tmemberreceivermanager;
	}

	public void setTmemberreceivermanager(
			TMemberReceiverManager tmemberreceivermanager) {
		this.tmemberreceivermanager = tmemberreceivermanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TMemberReceiver getTmemberreceiver() {
		return tmemberreceiver;
	}

	public void setTmemberreceiver(TMemberReceiver tmemberreceiver) {
		this.tmemberreceiver = tmemberreceiver;
	}

	/**
	 * 收货地址列表
	 */
	public String list()throws Exception{
		TMemberReceiverExample example= new TMemberReceiverExample();
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		example.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		this.getRequest().setAttribute("receivers", tmemberreceivermanager.selectByExample(example));
		return "list";
	}
	
	/**
	 * 选择地区
	 */
	public void clocationSelect() throws IOException {
		String parentid = this.getRequest().getParameter("parentid");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", Integer.valueOf(parentid));
		List<Map<String, Object>> clocationList = opensqlmanage.selectForListByMap(paramMap, "c_location.selectLocationByMap");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clocationList", clocationList);
		if (clocationList != null && clocationList.size() > 0) {
			map.put("level", clocationList.get(0).get("grade"));
		}
		writeObjectToResponse(map, ContentType.text_html);
	}
	
	/**
	 * 弹出编辑收货地址页
	 */
	public String edit() throws NumberFormatException, SQLException{
		if(StringUtils.isNumeric(this.getRequest().getParameter("id"))) {
			getRequest().getSession().setAttribute("tmrr", tmemberreceivermanager.selectByPrimaryKey(Long.valueOf(this.getRequest().getParameter("id"))));
		} 
		return "edit";
	}

	public  void queryAllReceiver() throws Exception{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		TMemberReceiverExample example = new TMemberReceiverExample();
		example.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		example.setOrderByClause(" is_default desc");
		List<TMemberReceiver> receiverList = tmemberreceivermanager.selectByExample(example);
		writeObjectToResponse(receiverList, ContentType.application_json);
	}
	
	/**
	 * 新增或是修改收货地址
	 */
	public String saveOrUpdate() throws Exception {
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		if(tmemberreceiver.getIsDefault() == 1){
			TMemberReceiverExample example= new TMemberReceiverExample();
			example.createCriteria().andIsDefaultEqualTo(1);
			example.createCriteria().andMemberIdEqualTo(sessionMember.getId());
			List<TMemberReceiver> datas = tmemberreceivermanager.selectByExample(example);
			if(datas != null){
				for(TMemberReceiver tr : datas){
					tr.setIsDefault(0);
					tmemberreceivermanager.updateByPrimaryKeySelective(tr);
				}
			}
		}
		if (tmemberreceiver.getId() != null) {
			String selectAreaName = clocationmanager.selectAreaName(tmemberreceiver.getAreaId());
			tmemberreceiver.setArea(selectAreaName);
			tmemberreceivermanager.updateByPrimaryKeySelective(tmemberreceiver);
		} else {
			tmemberreceiver.setMemberId(sessionMember.getId());
			tmemberreceiver.setCreateDate(new Date());
			String selectAreaName = clocationmanager.selectAreaName(tmemberreceiver.getAreaId());
			tmemberreceiver.setArea(selectAreaName);
			tmemberreceivermanager.insertSelective(tmemberreceiver);
		}
		return "success";
	}
	
	/**
	 * 根据id查询收货地址
	 */
	public void getReceiver() throws Exception {
		if (StringUtils.isNumeric(this.getRequest().getParameter("id"))) {
			TMemberReceiver receiver =this.tmemberreceivermanager.selectByPrimaryKey(Long.valueOf(this.getRequest().getParameter("id")));
			writeObjectToResponse(receiver, ContentType.application_json);
		}
	}
	
	/**
	 * 删除收货地址
	 */
	public String delete() throws NumberFormatException, SQLException {
		if (StringUtils.isNumeric(this.getRequest().getParameter("id"))) {
			this.tmemberreceivermanager.deleteByPrimaryKey(Long.valueOf(this.getRequest().getParameter("id")));
		}
		return "success";
	}

	/**
	 * 设为默认收货地址
	 */
	public void setDefault() throws Exception {
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		if (StringUtils.isNumeric(this.getRequest().getParameter("id"))) {
			this.tmemberreceivermanager.updateReceiverDefault(Long.valueOf(this.getRequest().getParameter("id")),sessionMember.getId());
			writeObjectToResponse("1", ContentType.text_html);
		} else {
			writeObjectToResponse("0", ContentType.text_html);
		}
	}

	/**
	 * 弹出添加收货地址页
	 */
	public String add(){
		return "add";
	}
	
	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {}

}
