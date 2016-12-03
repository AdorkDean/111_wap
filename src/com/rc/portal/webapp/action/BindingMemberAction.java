package com.rc.portal.webapp.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.util.JsonUtils;
import com.rc.portal.service.ICartManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponCardManager;
import com.rc.portal.service.TCouponManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TMemberThreeBindingManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.CookieUtil;
import com.rc.portal.util.CustomDigestUtils;
import com.rc.portal.util.NetworkUtil;
import com.rc.portal.util.ParametersCommon;
import com.rc.portal.util.WapWxAutoUtil;
import com.rc.portal.util.cookieManager;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.vo.TCouponCardExample;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberGrade;
import com.rc.portal.vo.TMemberThreeBinding;

public class BindingMemberAction extends BaseAction{

	private static final long serialVersionUID = -6589237063833811313L;
	private TMemberManager tmembermanager;
	private TMember tmember;
	private ICartManager cartmanager;
	private TCouponCardManager tcouponcardmanager;
	private TMemberThreeBinding memberThreeBinding;
	private TMemberThreeBindingManager tmemberthreebindingmanager;
	private OpenSqlManage opensqlmanage;
	private TSysParameterManager tsysparametermanager;
	private TCouponManager tcouponmanager;
	public TCouponManager getTcouponmanager() {
		return tcouponmanager;
	}

	public void setTcouponmanager(TCouponManager tcouponmanager) {
		this.tcouponmanager = tcouponmanager;
	}

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

	public ICartManager getCartmanager() {
		return cartmanager;
	}

	public void setCartmanager(ICartManager cartmanager) {
		this.cartmanager = cartmanager;
	}

	public TCouponCardManager getTcouponcardmanager() {
		return tcouponcardmanager;
	}

	public void setTcouponcardmanager(TCouponCardManager tcouponcardmanager) {
		this.tcouponcardmanager = tcouponcardmanager;
	}

	public TMemberThreeBinding getMemberThreeBinding() {
		return memberThreeBinding;
	}

	public void setMemberThreeBinding(TMemberThreeBinding memberThreeBinding) {
		this.memberThreeBinding = memberThreeBinding;
	}

	public TMemberThreeBindingManager getTmemberthreebindingmanager() {
		return tmemberthreebindingmanager;
	}

	public void setTmemberthreebindingmanager(
			TMemberThreeBindingManager tmemberthreebindingmanager) {
		this.tmemberthreebindingmanager = tmemberthreebindingmanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
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
	/*
	 * 微信自动登录---获取ticket值
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getTicket(){
		String ticket="";
		String key="5k8264iltkch16cq2502si8znmtm67vs";
		Map map=new HashMap();
		map.put("ticketUseSet", "get_user_info");
		map.put("appIdInt", "10001");
		String sign=ParametersCommon.getWXSign(map, key);
		String url="http://weixin.111yao.com/bec15bf762a/wxTicket.do?ticketUseSet=get_user_info&appIdInt=10001&sign="+sign;
		ticket=WapWxAutoUtil.sendGet(url);
		System.out.println("json格式的ticket值："+ticket);
		return ticket;
	}
	/**
	 * 发起授权的url
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void authUrl() throws Exception{
		String wxLoginRedirect = this.getRequest().getParameter("wxLoginRedirect");
		if(getTicket()!=null&&!getTicket().equals("")){
		Map json = JsonUtils.toObject(getTicket(), Map.class);
		String ticket=json.get("ticket").toString();
		System.out.println("返回的ticket------>"+ticket);
		String redirectUrl="http://m.111yao.com/member/bindingMember!memberBindingRed.action";
		String key="5k8264iltkch16cq2502si8znmtm67vs";
		Map map=new HashMap();
		map.put("ticketUseSet", "get_user_info");
		map.put("appIdInt", "10001");
		map.put("ticket", ticket);
		map.put("redirectUrl", redirectUrl);
		map.put("reqScope", "snsapi_userinfo");
		String sign=ParametersCommon.getWXSign(map, key);
		@SuppressWarnings("deprecation")
		String oauthurl="http://weixin.111yao.com/publish/user_info.jsp?ticketUseSet=get_user_info&ticket="+ticket+"&appIdInt=10001&redirectUrl="+URLEncoder.encode(redirectUrl)+"&reqScope=snsapi_userinfo&sign="+sign;
		try {
        	if(wxLoginRedirect != null){
        		this.getSession().setAttribute("wxLoginRedirect", wxLoginRedirect);
        	}
        	this.getResponse().sendRedirect(oauthurl);
        } catch (Exception e) {
            e.printStackTrace();
        }
		}else{
			this.getResponse().sendRedirect("http://m.111yao.com");
		}
	}
	/* 
	 * 微信用户绑定红包
	 */
	 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void memberBindingRed() throws Exception{
		String unionId = this.getRequest().getParameter("unionId");
		if(unionId==""||"".equals(unionId)||null==unionId){
			this.getResponse().sendRedirect("http://m.111yao.com");
		}else{
			String openId = this.getRequest().getParameter("openId");
			this.getSession().setAttribute("bdunionId", unionId);
			String key = cookieManager.getCookieValueByName(this.getRequest(), CartAction.cartKey);
			Map openmap=new HashMap();
			openmap.put("binding_uuid", unionId);
			Map umap=new HashMap();
			umap.put("user_name", unionId);
			TMember member1 = (TMember) opensqlmanage.selectObjectByObject(openmap, "t_member.selectmemberByWxOpenid");
			TMemberGrade memberGrade1 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectDefaultGrade");
			TMemberGrade memberGrade2 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectlowGrade");
			if(member1!=null){
				if(key!=null){
					cartmanager.synCart(member1.getId(), key);
				}
				this.getSession().setAttribute("member", member1);
				CookieUtil.addCookie(getRequest(), getResponse(), "username", member1.getUserName(), 7*24*60*60, "/", "", false);
				CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member1.getUserName(),30*24*60*60, "/", ".111yao.com", false);
				CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", member1.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
				//用户绑定优惠券
				Map couponMap=new HashMap();
				couponMap.put("regCouponId", "135");
				TCoupon coupon = (TCoupon) opensqlmanage.selectObjectByObject(couponMap, "t_coupon.selectCouponByid");
				tcouponcardmanager.bindingCoupon(coupon,member1.getId(), 1);
				this.getResponse().sendRedirect("http://m.111yao.com/nzt/m/goddess.html");
			}else{
				tmember = new TMember();
				tmember.setUserName("wx_"+unionId);
				tmember.setAreaId(0l);
				if(memberGrade1!=null){
					tmember.setMemberGradeId(memberGrade1.getId());
				}else{
					tmember.setMemberGradeId(memberGrade2.getId());
				}
				tmember.setPassword(CustomDigestUtils.md5Hex(unionId.substring(0, 5),tmember));
				tmember.setStatus(0);
				tmember.setEnterpriseDiscount(new BigDecimal(0));
				tmember.setIsLeader(0);
				tmember.setIntegral(0);
				tmember.setSource(0);//0表示微信注册
				tmember.setPlatform(2);//1表示PC平台 2表示WAP平台
				tmember.setRegisterIp(NetworkUtil.getIpAddress(this.getRequest()));
				tmember.setRegisterDate(new Date());
				tmember.setLastDate(new Date());
				tmember.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
				tmember.setIsEmailCheck(0);
				tmember.setIsMobileCheck(0);
				tmember.setAgentId(getAgentId());
				tmember.setIsAgent(0);
				TLeader leader = getLeader();
				tmembermanager.insertSelective(tmember,leader);
				memberThreeBinding=new TMemberThreeBinding();
				if (tmember != null) {
					memberThreeBinding.setMemberId(tmember.getId());
				}
				memberThreeBinding.setSource(0);//微信
				memberThreeBinding.setCreateDate(new Date());
				memberThreeBinding.setBindingUuid(unionId);
				memberThreeBinding.setWxOpenid(openId);
				memberThreeBinding.setWxAppid(null);
				tmembermanager.saveMemberThreeBinding(memberThreeBinding);
				if(key!=null){
					cartmanager.synCart(tmember.getId(), key);
				}
				this.getSession().setAttribute("member", tmember);
				//用户绑定优惠券
				Map couponMap=new HashMap();
				couponMap.put("regCouponId", "135");
				TCoupon coupon = (TCoupon) opensqlmanage.selectObjectByObject(couponMap, "t_coupon.selectCouponByid");
				tcouponcardmanager.bindingCoupon(coupon,tmember.getId(), 1);
				CookieUtil.addCookie(getRequest(), getResponse(), "username", tmember.getUserName(), 7*24*60*60, "/", "", false);
				CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", tmember.getUserName(), 30*24*60*60, "/", ".111yao.com", false);
				CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", tmember.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
				this.getResponse().sendRedirect("http://m.111yao.com/nzt/m/goddess.html");
			}
		}
	}
	
	/**
	 * 限制优惠券领取次数
	 * return 1成功 2用户已经绑定 3优惠劵已经发放完毕 4服务器错误 5无效优惠劵6无效用户
	 * @throws IOException
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public Boolean ztBindingCoupon(TMember member,String regCouponId) throws IOException, SQLException{
	    try {
			if(null !=member && null != member.getId()){
				//String couponId = this.getRequest().getParameter("couponId");
				if (null != regCouponId) {
					TCouponCardExample tcce = new TCouponCardExample();
					tcce.createCriteria().andMemberIdEqualTo(member.getId()).andTicketIdEqualTo(new Long(regCouponId));
					List list = tcouponcardmanager.selectByExample(tcce);
					if (null != list && list.size() > 0) { //判断用户是否绑定过该优惠劵
						return false;//2用户已经绑定
					} else {
						TCoupon coupon = tcouponmanager.selectByPrimaryKey(new Long(regCouponId));
						Long id = tcouponcardmanager.bindingCouponLimit(coupon,member.getId(), 1);
						if (id > 0) {
							return true;//1绑定成功
						}else{
							return false;//3优惠劵已经发放完毕
						}
					}
				} else {
					return false;//5无效优惠劵
				}
			}else{
				return false;//6无效用户
			}
		} catch (Exception e) {
			return false;
		}
	}
	/*
	 * 领袖下线
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TLeader getLeader(){
		String code = CookieUtil.getCookie(this.getRequest(), "leader");//通过code，查找leader 
		Map map=new HashMap();
		map.put("code", code);
		TLeader tleader = (TLeader) opensqlmanage.selectObjectByObject(map, "t_leader.selectLeaderByCode");
		return tleader;
	}
	/*
	 * 获取代理人Id
	 */
	public Long getAgentId(){
		String code = CookieUtil.getCookie(this.getRequest(), "agent");//通过code，获取代理人Id
		if(code!=null){
			return Long.parseLong(code);
		}else{
			return null;
		}
	}
	public String test(){
		return "success";
	}
}
