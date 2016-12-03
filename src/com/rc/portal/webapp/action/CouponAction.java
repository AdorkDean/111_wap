package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.portal.memcache.MemCached;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponCardManager;
import com.rc.portal.service.TCouponManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.CodeUtil;
import com.rc.portal.util.CookieUtil;
import com.rc.portal.util.NetworkUtil;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.vo.TCouponCardExample;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberBaseMessageExt;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.vo.TMemberGrade;
import com.rc.portal.webapp.util.MD5;
import com.rc.portal.webapp.util.PageResult;

/**
 * 用户绑定优惠劵
 * @author Administrator
 *
 */
public class CouponAction extends BaseAction {
	
	
	private OpenSqlManage opensqlmanage;
	private TCouponManager tcouponmanager;
	private TCouponCardManager tcouponcardmanager;
	private TMemberManager tmembermanager;
	private TSysParameterManager tsysparametermanager;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	
	
	/**
	 * 
	 * return 1成功 2用户已经绑定 3优惠劵已经发放完毕 4服务器错误 5无效优惠劵6无效用户
	 * @throws IOException
	 * @throws SQLException
	 */
	public void bindingCoupon() throws IOException, SQLException{
		HttpServletResponse reponse = this.getResponse();
	    reponse.setContentType("text/html;charset=utf-8");
	    PrintWriter out = reponse.getWriter();
	    String flag = "3";
	    try {
			TMember sessionMember = (TMember) this.getSession().getAttribute(
					"member");
			if(null !=sessionMember && null != sessionMember.getId()){
				String couponId = this.getRequest().getParameter("couponId");
				if (null != couponId) {
//					String cp = InfoUtil.getInstance()
//							.getInfo("config", "couponId");
					String cp  = tsysparametermanager.getKeys("couponId");
					// cp 网站活动可以领取的优惠劵ID集合 
					String c[] = cp.split(",");
					boolean b = false;
					for (int i = 0; i < c.length; i++) {
						if (couponId.equals(c[i])) {
							b = true;
							break;
						}
					}
					if (!b) {
						flag = "5";
					} else {
						TCouponCardExample tcce = new TCouponCardExample();
						tcce.createCriteria()
								.andMemberIdEqualTo(sessionMember.getId())
								.andTicketIdEqualTo(new Long(couponId));
						List list = tcouponcardmanager.selectByExample(tcce);
						if (null != list && list.size() > 0) { //判断用户是否绑定过该优惠劵
							flag = "2";
						} else {
							TCoupon coupon = tcouponmanager
									.selectByPrimaryKey(new Long(couponId));
							Long id = tcouponcardmanager.bindingCoupon(coupon,
									sessionMember.getId(), 1);
							if (id > 0) {
								flag = "1";
							}
						}
					}

				} else {
					flag = "5";
				}
			}else{
				flag ="6";
			}
		} catch (Exception e) {
			flag="4";
		}
		out.print(flag);
		out.close();
	}
	
	/**
	 * return 1成功 2用户已经绑定 3优惠劵已经发放完毕 4服务器错误 5无效优惠劵6无效用户
	 * @throws IOException
	 * @throws SQLException
	 */
	public void bindingCouponLimit() throws IOException, SQLException{
		HttpServletResponse reponse = this.getResponse();
	    reponse.setContentType("text/html;charset=utf-8");
	    PrintWriter out = reponse.getWriter();
	    String flag = "3";
	    try {
			TMember sessionMember = (TMember) this.getSession().getAttribute(
					"member");
			if(null !=sessionMember && null != sessionMember.getId()){
				String couponId = this.getRequest().getParameter("couponId");
				if (null != couponId) {
//					String cp = InfoUtil.getInstance()
//							.getInfo("config", "couponId");
					String cp  = tsysparametermanager.getKeys("couponId");
					// cp 网站活动可以领取的优惠劵ID集合 
					String c[] = cp.split(",");
					boolean b = false;
					for (int i = 0; i < c.length; i++) {
						if (couponId.equals(c[i])) {
							b = true;
							break;
						}
					}
					if (!b) {
						flag = "5";
					} else {
						TCouponCardExample tcce = new TCouponCardExample();
						tcce.createCriteria()
								.andMemberIdEqualTo(sessionMember.getId())
								.andTicketIdEqualTo(new Long(couponId));
						List list = tcouponcardmanager.selectByExample(tcce);
						if (null != list && list.size() > 0) { //判断用户是否绑定过该优惠劵
							flag = "2";
						} else {
							TCoupon coupon = tcouponmanager
									.selectByPrimaryKey(new Long(couponId));
							Long id = tcouponcardmanager.bindingCouponLimit(coupon,
									sessionMember.getId(), 1);
							if (id > 0) {
								flag = "1";
							}else{
								flag = "3";
							}
						}
					}

				} else {
					flag = "5";
				}
			}else{
				flag ="6";
			}
		} catch (Exception e) {
			flag="4";
		}
		out.print(flag);
		out.close();
	}
	
	/**
	 * 获取抢劵专题的 优惠劵集合
	 * @return
	 * @throws Exception 
	 */
	public String getCoupon() throws Exception{
		String cp  = tsysparametermanager.getKeys("yjzq_code");
		List list = new ArrayList();
		if(null != cp && !("").equals(cp)){ //判断是否有发放的优惠劵
			Map<String,Object> querMap = new HashMap<String,Object>();
			querMap.put("cp", cp);
			TMember sessionMember = (TMember) this.getSession().getAttribute(
					"member");
			if(null != sessionMember && null != sessionMember.getId()){ //判断用户是否登录
				querMap.put("member_id", sessionMember.getId());
			}else{
				HttpServletRequest request = this.getRequest();
				String username = CookieUtil.getCookie(request, "cookusername");
				String cookpassword = CookieUtil.getCookie(request, "cookpassword");
				if(null !=username && !("").equals(username) && null != cookpassword && !("").equals(cookpassword)){
					TMemberExample example = new TMemberExample();
					example.createCriteria().andUserNameEqualTo(username).andPasswordEqualTo(cookpassword);
					List memberList = tmembermanager.selectByExample(example);
					if(null != memberList && memberList.size()==1){
						 sessionMember = (TMember)memberList.get(0);
						 this.getSession().setAttribute("member", sessionMember);
						 querMap.put("member_id", sessionMember.getId());
					}
				}
			}
			list =  opensqlmanage.selectForListByMap(querMap, "t_coupon.getCoupon"); 
		}
		this.getRequest().setAttribute("cpList", list);
		return "getcoupon";
	}
	/*
	 * 检查用户是否登录
	 */
	public void checkLogin() throws Exception{
		TMember member = (TMember)this.getSession().getAttribute("member");
		HttpServletResponse reponse = this.getResponse();
	    reponse.setContentType("text/html;charset=utf-8");
	    PrintWriter out = reponse.getWriter();
	    String flag = "-1";
	    if(member==null){
	    	flag="1";
	    }
	    out.print(flag);
		out.close();
	}
	
	/**
	 * 
	 * return 1成功 2用户已经绑定 3优惠劵已经发放完毕 4服务器错误 5无效优惠劵6无效用户
	 * @throws IOException
	 * @throws SQLException
	 */
	public void zqBindingCoupon() throws IOException, SQLException{
		HttpServletResponse reponse = this.getResponse();
	    reponse.setContentType("text/html;charset=utf-8");
	    PrintWriter out = reponse.getWriter();
	    String flag = "3";
	    try {
			TMember sessionMember = (TMember) this.getSession().getAttribute(
					"member");
			if(null !=sessionMember && null != sessionMember.getId()){
				String couponId = this.getRequest().getParameter("couponId");
				if (null != couponId) {
					String cp  = tsysparametermanager.getKeys("couponId");
					// cp 网站活动可以领取的优惠劵ID集合 
					String c[] = cp.split(",");
					boolean b = false;
					for (int i = 0; i < c.length; i++) {
						if (couponId.equals(c[i])) {
							b = true;
							break;
						}
					}
					if (!b) {
						flag = "5";
					} else {
						TCoupon coupon = tcouponmanager
								.selectByPrimaryKey(new Long(couponId));
						Long id = tcouponcardmanager.bindingCoupon(coupon,
								sessionMember.getId(), 1);
						if (id > 0) {
							flag = "1";
						}
					}

				} else {
					flag = "5";
				}
			}else{
				flag ="6";
			}
		} catch (Exception e) {
			flag="4";
		}
		out.print(flag);
		out.close();
	}
	/*
	 * 关注微信领取红包
	 * 转到拆红包页面
	 */
	public String openRedIndex(){
		
		return "openRedIndex";
	}
	/*
	 * 关注微信领取红包
	 * 显示红包页showRed
	 */
	@SuppressWarnings("rawtypes")
	public String showRed() throws Exception{
		String cp  = tsysparametermanager.getKeys("weixin_redCuopon_id");
		List list = new ArrayList();
		if(null != cp && !("").equals(cp)){ //判断是否有发放的优惠劵
			Map<String,Object> querMap = new HashMap<String,Object>();
			querMap.put("cp", cp);
			TMember sessionMember = (TMember) this.getSession().getAttribute(
					"member");
			if(null != sessionMember && null != sessionMember.getId()){ //判断用户是否登录
				querMap.put("member_id", sessionMember.getId());
			}else{
				HttpServletRequest request = this.getRequest();
				String username = CookieUtil.getCookie(request, "cookusername");
				String cookpassword = CookieUtil.getCookie(request, "cookpassword");
				if(null !=username && !("").equals(username) && null != cookpassword && !("").equals(cookpassword)){
					TMemberExample example = new TMemberExample();
					example.createCriteria().andUserNameEqualTo(username).andPasswordEqualTo(cookpassword);
					List memberList = tmembermanager.selectByExample(example);
					if(null != memberList && memberList.size()==1){
						 sessionMember = (TMember)memberList.get(0);
						 this.getSession().setAttribute("member", sessionMember);
						 querMap.put("member_id", sessionMember.getId());
					}
				}
			}
			list =  opensqlmanage.selectForListByMap(querMap, "t_coupon.getCoupon"); 
		}
		this.getRequest().setAttribute("cpList", list);
		return "showRed";
	}
	/*
	 * 关注微信领取红包
	 * 领取红包receiveRed
	 */
	public void receiveRed() throws Exception{
		String mobile = this.getRequest().getParameter("mobile");
		String mobileCode = this.getRequest().getParameter("mobileCode");
		String couponId = this.getRequest().getParameter("couponId");
		String tcouponId = tsysparametermanager.getKeys("weixin_redCuopon_id");
		String amoblieCode = (String) MemCached.getmcc().get(mobile);//后台比较的验证码
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", mobile);
		
		TMember member = (TMember) this.opensqlmanage.selectObjectByObject(map, "t_member.ibatorgenerated_selectMemberByMobile");
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		if (member == null) {
			TMember	tmember=new TMember();
			TMemberBaseMessageExt tmemberBaseMessageext=new TMemberBaseMessageExt();
			if(!mobileCode.equalsIgnoreCase(amoblieCode)){//amoblieCode
				flag=1;//短信验证码不正确
			}else{
				TMemberGrade memberGrade1 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectDefaultGrade");
				TMemberGrade memberGrade2 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectlowGrade");
				tmember.setUserName("yyg_"+mobile);
				tmemberBaseMessageext.setNickName(mobile);
				tmember.setMobile(mobile);
				tmember.setPassword(MD5.MD5("1116063111yao"));
				if(memberGrade1!=null){
					tmember.setMemberGradeId(memberGrade1.getId());
				}else{
					tmember.setMemberGradeId(memberGrade2.getId());
				}
				tmember.setStatus(0);
				tmember.setEnterpriseDiscount(new BigDecimal(0));
				tmember.setIsLeader(0);
				tmember.setIntegral(0);
				tmember.setSource(5);//5表示手机注册
				tmember.setPlatform(2);//WAP平台
				tmember.setRegisterIp(NetworkUtil.getIpAddress(this.getRequest()));
				tmember.setRegisterDate(new Date());
				tmember.setLastDate(new Date());
				tmember.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
				tmember.setIsEmailCheck(0);
				tmember.setIsMobileCheck(1);
				tmember.setAgentId(getAgentId());
				tmember.setIsAgent(0);
				tmembermanager.insertSelective(tmember,null);
				tmemberBaseMessageext.setMemberId(tmember.getId());
				tmembermanager.savetmemberbasemessageext(tmemberBaseMessageext);
				this.getSession().setAttribute("member", tmember);
				Map<String, Object> countMap = new HashMap<String, Object>();
				countMap.put("couponId", couponId);
				countMap.put("memberId", tmember.getId());
				Long count = (Long) this.opensqlmanage.selectObjectByObject(countMap, "t_coupon.ibatorgenerated_selectByMemberIdAndCouponId");
				if(count==0){
					if(couponId.equals(tcouponId)){
						TCoupon coupon = tcouponmanager.selectByPrimaryKey(new Long(couponId));
						tcouponcardmanager.bindingCoupon(coupon,tmember.getId(), 1);
						flag = 2;//绑定成功
						Map<String, String> mobilemap = new HashMap<String, String>();
						mobilemap.put("mobiles", mobile);
						mobilemap.put("smsContent", "111医药馆提醒您红包已发送至您账户当中，登录帐号为您当前手机号，初始密码为：6063111.");
						String YAO_GATEWAY_URL =tsysparametermanager.getKeys("sms");
						String sms = ClientSubmit.buildRequestBySMS(mobilemap,YAO_GATEWAY_URL);
						System.out.println(sms);
					}else{
						flag=4;//无效优惠券
					}
				}else{
					flag=5;//该用户已经今天已经领取过优惠券
				}
				if(tmember.getUserName()!=null && !"".equals(tmember.getUserName())){
					CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", tmember.getUserName(),30*24*60*60, "/", ".111yao.com", false);
				}else{
					CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", tmember.getMobile(),30*24*60*60, "/", ".111yao.com", false);
				}
				CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", tmember.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
			}
		} else {
			if (member.getStatus() == 0) {
				 if (!mobileCode.equalsIgnoreCase(amoblieCode)) {//amoblieCode
					flag = 1;// 短信验证码不正确
				} else{
					member.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
					member.setLastDate(new Date());
					tmembermanager.updateByPrimaryKey(member);
					this.getSession().setAttribute("member", member);
					
					Map<String, Object> countMap = new HashMap<String, Object>();
					countMap.put("couponId", couponId);
					countMap.put("memberId", member.getId());
					Long count = (Long) this.opensqlmanage.selectObjectByObject(countMap, "t_coupon.ibatorgenerated_selectByMemberIdAndCouponId");
					if(count==0){
						if(couponId.equals(tcouponId)){
							TCoupon coupon = tcouponmanager.selectByPrimaryKey(new Long(couponId));
							tcouponcardmanager.bindingCoupon(coupon,member.getId(), 1);
							flag = 2;//绑定成功
						}else{
							flag=4;//无效优惠券
						}
					}else{
						flag=5;//该用户已经今天已经领取过优惠券
					}
					if(member.getUserName()!=null && !"".equals(member.getUserName())){
						CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member.getUserName(),30*24*60*60, "/", ".111yao.com", false);
					}else{
						CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member.getMobile(),30*24*60*60, "/", ".111yao.com", false);
					}
					CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", member.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
				}
			}else{
				flag = 3;// 该帐户被停用
			}
		}
		out.print(flag);
		out.close();
	}
	/*
	 * 领袖下线
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TLeader getLeader(){
		String code = CookieUtil.getCookie(this.getRequest(), "leader");//通过code，查找leader 
		Map map=new HashMap();
		this.getSession().setAttribute("leaderCode", code);
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
	/*
	 * 发送验证码
	 */
	public void validateMobileCode() throws Exception{
		String captcha = CodeUtil .getVcode(4);
		String mobile = this.getRequest().getParameter("mobile");
		MemCached.getmcc().set(mobile,captcha,new Date(1000*300));
		Pattern pattern = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号 
		int flag=-1;
		PrintWriter out = this.getResponse().getWriter();
		if(mobile!=null &&  pattern.matcher(mobile).matches()){
			Map<String, String> map = new HashMap<String, String>();
			map.put("mobiles", mobile);
			map.put("smsContent", "您的111医药馆验证码:"+captcha+"。影视明星何政军先生推荐的中国好药房@111医药馆！");
			String YAO_GATEWAY_URL =tsysparametermanager.getKeys("sms");
			String buildRequestBySMS = ClientSubmit.buildRequestBySMS(map,YAO_GATEWAY_URL);
			System.out.println(buildRequestBySMS);
			flag=0;
		}
		System.out.println("captcha----->  "+captcha);
		out.print(flag);
		out.close();
	}
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}


	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
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

	public TCouponManager getTcouponmanager() {
		return tcouponmanager;
	}

	public void setTcouponmanager(TCouponManager tcouponmanager) {
		this.tcouponmanager = tcouponmanager;
	}

	public TCouponCardManager getTcouponcardmanager() {
		return tcouponcardmanager;
	}

	public void setTcouponcardmanager(TCouponCardManager tcouponcardmanager) {
		this.tcouponcardmanager = tcouponcardmanager;
	}

	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
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
