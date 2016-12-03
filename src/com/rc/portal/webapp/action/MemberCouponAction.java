package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponCardManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.util.DateUtil;
import com.rc.portal.vo.TCouponCard;
import com.rc.portal.vo.TMember;

public class MemberCouponAction extends BaseAction{
	private static final long serialVersionUID = 124432313L;
	private TMemberManager tmembermanager;
	private TCouponCardManager tcouponcardmanager;
	private OpenSqlManage opensqlmanage;
	List<Object> bean = new ArrayList<Object>();
	public List<Object> getBean() {
		return bean;
	}
	public void setBean(List<Object> bean) {
		this.bean = bean;
	}
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}
	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}
	public TCouponCardManager getTcouponcardmanager() {
		return tcouponcardmanager;
	}
	public void setTcouponcardmanager(TCouponCardManager tcouponcardmanager) {
		this.tcouponcardmanager = tcouponcardmanager;
	}

	private TMember tmember ;
	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}
	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}
	public TMember getTmember() {
		return tmember;
	}
	public void setTmember(TMember tmember) {
		this.tmember = tmember;
	}
	/*
	 *添加优惠券 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add() throws Exception{
		int flag=-1;
		PrintWriter out = this.getResponse().getWriter();
		TMember member = (TMember)this.getSession().getAttribute("member");
		Long memberId = member.getId();
		String couponCode = this.getRequest().getParameter("code");
		String currentTime = DateUtil.getCurrentTime();
		Map map=new HashMap();
		map.put("code", couponCode);
		if(member!=null){
			TCouponCard couponCard = new TCouponCard();
			if(couponCode!=null&&!"".equals(couponCode.trim())){
				Map<String,Object> codeMap = (Map<String, Object>) opensqlmanage.selectForObjectByMap(map, "t_coupon_card.ibatorgenerated_selectByCode");
				couponCard.setCardNo(couponCode);
				couponCard.setMemberId(Long.valueOf(memberId));
				if(codeMap!=null){
					Long id = (Long) codeMap.get("id");
					couponCard.setId(id);
		    		Integer isenabled = (Integer) codeMap.get("status");//是否启用
		    		if(0==isenabled){
		    			flag=1;//优惠劵未启用
		    		}
		    		Long mid = (Long) codeMap.get("member_id");
		    		if(mid==null){
		    			Object obdate = codeMap.get("start_time");
		    			Object oedate =  codeMap.get("end_time");
		    			if(null!=obdate){
		    				String bdate = obdate.toString();
		    				if(1==DateUtil.timeCompare(bdate.split("\\.")[0], currentTime)){
		    					flag=2;//当前日期大于等于开始日期
		    				}
		    			}
		    			if(null!=oedate){
		    				String edate = oedate.toString();
		    				edate = edate.split("\\.")[0];
		    				if(-1==DateUtil.timeCompare(edate, currentTime)){
		    					flag=3;//当前日期小于等于结束日期
		    				}
		    			}
		    			Integer isenab = (Integer) codeMap.get("is_repeat");
		    			if(1==isenab){
		    				tcouponcardmanager.updateByPrimaryKeySelective(couponCard);//可以重复使用
		    			}else {
		    				Integer isused = (Integer) codeMap.get("is_use");
		    				if(0==isused){
		    					tcouponcardmanager.updateByPrimaryKeySelective(couponCard);//0是未使用
		    					flag=0;//绑定成功！
		    				}else {
		    					flag=4;//优惠劵已使用
		    				}
		    			}
		    		}else{
		    			flag=4;//优惠劵已使用
		    		}
				}else{
					flag=6;//优惠劵不存在
				}
			}else{
				flag=5;//优惠劵不存在
			}
		}
		out.print(flag);
		out.close();
	}
	/*
	 *优惠券列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String list() throws Exception{
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map map=new HashMap();
		map.put("memberId", member.getId());
		bean = opensqlmanage.selectForListByMap(map, "t_coupon_card.ibatorgenerated_selectByMember");
		return "couponlist";
	}
	/*
	 *转到添加优惠券的页
	 */
	public String addCou(){
		
		return "addcou";
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
