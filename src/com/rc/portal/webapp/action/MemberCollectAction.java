package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberCollectManager;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberCollect;
import com.rc.portal.vo.TMemberCollectExample;
import com.rc.portal.webapp.util.PageResult;

/**
 * 会员收藏
 * @author 刘天灵
 *
 */
public class MemberCollectAction extends BaseAction{

	private static int PAGE_SIZE = 10;
	
	private static final long serialVersionUID = 654464641L;

	private TMemberCollect tmembercollect = new TMemberCollect();
	
	private TMemberCollectManager  tmembercollectmanager;
	
	private OpenSqlManage  opensqlmanage;
	
	
	private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult(); 
	
	
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

	public TMemberCollect getTmembercollect() {
		return tmembercollect;
	}

	public void setTmembercollect(TMemberCollect tmembercollect) {
		this.tmembercollect = tmembercollect;
	}

	public TMemberCollectManager getTmembercollectmanager() {
		return tmembercollectmanager;
	}

	public void setTmembercollectmanager(TMemberCollectManager tmembercollectmanager) {
		this.tmembercollectmanager = tmembercollectmanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {}

	/**
	 * 我的收藏(1商品,2或者其他 品牌)
	 */
	public String index(){
		
		String type = this.getRequest().getParameter("type");
		
		if(!StringUtils.isNotEmpty(type)){
			type = "1";
		}
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map<String,Object> param = new HashMap<String,Object>();
		
		param.put("memberId", member.getId());

		this.getRequest().setAttribute("goodsCount", this.opensqlmanage.selectObjectByObject(param, "t_member_collect.selectCountByGoods"));
		
		this.getRequest().setAttribute("brandCount", this.opensqlmanage.selectObjectByObject(param,"t_member_collect.selectCountByBrand"));
		
		if(type.equals("1")){
			pw = opensqlmanage.selectForPageByMap(param, "t_member_collect.selectCountByGoods","t_member_collect.selectListByGoods",  rs.getP_curPage(), PAGE_SIZE);
			
		}else{
			pw = opensqlmanage.selectForPageByMap(param, "t_member_collect.selectCountByBrand","t_member_collect.selectListByBrand",  rs.getP_curPage(), PAGE_SIZE);
		}
		
		this.getRequest().setAttribute("type", type);
		return "index";
		
	}
	
	/**
	 * 关注商品
	 * @return
	 */
	public String goods(){
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map<String,Object> param = new HashMap<String,Object>();
		
		param.put("memberId", member.getId());
		
		pw = opensqlmanage.selectForPageByMap(param, "t_member_collect.selectCountByGoods","t_member_collect.selectListByGoods",  rs.getP_curPage(), PAGE_SIZE);
			
		return "goods";
	}
	
	/**
	 * 关注商品更多
	 * @return
	 */
	public String more(){
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map<String,Object> param = new HashMap<String,Object>();
		
		param.put("memberId", member.getId());
		
		pw = opensqlmanage.selectForPageByMap(param, "t_member_collect.selectCountByGoods","t_member_collect.selectListByGoods",  rs.getP_curPage(), PAGE_SIZE);
			
		return "more";
	}
	
	/**
	 * 关注品牌
	 * @return
	 */
	public String brand(){
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map<String,Object> param = new HashMap<String,Object>();
		
		param.put("memberId", member.getId());
		
		this.getRequest().setAttribute("list", opensqlmanage.selectListByObject(param, "t_member_collect.selectMemberBrand"));
		
		return "brand";
	}
	
	/**
	 * 取消收藏
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void cancel() throws NumberFormatException, SQLException{
		TMember member = (TMember)this.getSession().getAttribute("member");
		if(StringUtils.isNumeric(this.getRequest().getParameter("id"))){
			TMemberCollectExample example = new  TMemberCollectExample();
			example.createCriteria().andIdEqualTo(Long.parseLong(this.getRequest().getParameter("id"))).andMemberIdEqualTo(member.getId());
			this.tmembercollectmanager.deleteByExample(example);
			this.writeObjectToResponse(1, ContentType.application_json);
		}else{
			this.writeObjectToResponse(0, ContentType.application_json);
		}
	}
	
}
