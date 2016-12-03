package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.util.JsonUtils;
import com.rc.commons.util.InfoUtil;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.portal.memcache.MemCached;
import com.rc.portal.service.ICartManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponCardManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TMemberThreeBindingManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.CodeUtil;
import com.rc.portal.util.CookieUtil;
import com.rc.portal.util.CustomDigestUtils;
import com.rc.portal.util.NetworkUtil;
import com.rc.portal.util.ParametersCommon;
import com.rc.portal.util.WapWxAutoUtil;
import com.rc.portal.util.cookieManager;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberBaseMessageExt;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.vo.TMemberGrade;
import com.rc.portal.vo.TMemberThreeBinding;
import com.rc.portal.webapp.util.MD5;



public class LoginAction extends BaseAction {
	private TMemberManager tmembermanager;
	private TMember tmember;
	private String redirectUrl;
	private ICartManager cartmanager;
	private TCouponCardManager tcouponcardmanager;
	private TMemberThreeBinding memberThreeBinding;
	private TMemberThreeBindingManager tmemberthreebindingmanager;
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

	public TCouponCardManager getTcouponcardmanager() {
		return tcouponcardmanager;
	}

	public void setTcouponcardmanager(TCouponCardManager tcouponcardmanager) {
		this.tcouponcardmanager = tcouponcardmanager;
	}

	public ICartManager getCartmanager() {
		return cartmanager;
	}

	public void setCartmanager(ICartManager cartmanager) {
		this.cartmanager = cartmanager;
	}

	private TSysParameterManager tsysparametermanager;

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	private OpenSqlManage opensqlmanage;

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TMember getTmember() {
		return tmember;
	}

	public void setTmember(TMember tmember) {
		this.tmember = tmember;
	}

	private static final long serialVersionUID = 2123141313L;

	/**
	 * 用户登录
	 */
	public void userLogin() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mobileMap = new HashMap<String, Object>();
		Map<String, Object> emailMap = new HashMap<String, Object>();
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		String key = cookieManager.getCookieValueByName(this.getRequest(), CartAction.cartKey);
		map.put("user_name", username);
		mobileMap.put("mobile", username);
		emailMap.put("email", username);
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		TMember member = (TMember) opensqlmanage.selectObjectByObject(map,"t_member.ibatorgenerated_selectByUserName");
		TMember mobileMember = (TMember) this.opensqlmanage.selectObjectByObject(mobileMap, "t_member.ibatorgenerated_selectMemberByMobile");
		TMember emailMember = (TMember) this.opensqlmanage.selectObjectByObject(emailMap, "t_member.ibatorgenerated_selectMemberByEmail");
		Pattern pattern = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号
		if(username != null && pattern.matcher(username).matches() && mobileMember!=null && mobileMember.getStatus() == 0){
			if (mobileMember.getStatus() == 0) {
				if (!CustomDigestUtils.md5Hex(password, mobileMember).equals(mobileMember.getPassword()) && !MD5.MD5("111"+password+"yao").equals(mobileMember.getPassword())) {
					flag = 3;// 输入的密码不正确
				} else if (mobileMember.getIsMobileCheck()==1) {
					mobileMember.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
					mobileMember.setLastDate(new Date());
					tmembermanager.updateByPrimaryKey(mobileMember);
					if(key!=null){
						cartmanager.synCart(mobileMember.getId(), key);
					}
					flag = 5;
					this.getSession().setAttribute("member", mobileMember);
					if(mobileMember.getUserName()!=null && !"".equals(mobileMember.getUserName())){
						CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", mobileMember.getUserName(),30*24*60*60, "/", ".111yao.com", false);
					}else{
						CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", mobileMember.getMobile(),30*24*60*60, "/", ".111yao.com", false);
					}
					CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", mobileMember.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
				}else{
					flag=6;//该手机号未验证
				}
			}else{
				flag = 2;// 该帐户被停用
			}
		}else if(member!=null){
			if(member.getStatus() == 0){
				 if (!CustomDigestUtils.md5Hex(password, member).equals(member.getPassword()) && !MD5.MD5("111"+password+"yao").equals(member.getPassword())) {
						flag = 3;// 输入的密码不正确
					} 
					member.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
					member.setLastDate(new Date());
					tmembermanager.updateByPrimaryKey(member);
					if(key!=null){
						cartmanager.synCart(member.getId(), key);
					}
					flag = 5;
					this.getSession().setAttribute("member", member);
					if(member.getUserName()!=null && !"".equals(member.getUserName())){
						CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member.getUserName(),30*24*60*60, "/", ".111yao.com", false);
					}else{
						CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member.getMobile(),30*24*60*60, "/", ".111yao.com", false);
					}
					CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", member.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
			}else{
						flag = 2;// 该帐户被停用
			}
		}else if(emailMember!=null && emailMember.getStatus() == 0){
			if (!CustomDigestUtils.md5Hex(password, emailMember).equals(emailMember.getPassword()) && !MD5.MD5("111"+password+"yao").equals(emailMember.getPassword())) {
				flag = 3;// 输入的密码不正确
			}else{
				emailMember.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
				emailMember.setLastDate(new Date());
				tmembermanager.updateByPrimaryKey(emailMember);
				if(key!=null){
					cartmanager.synCart(emailMember.getId(), key);
				}
				flag = 5;
				this.getSession().setAttribute("member", emailMember);
				if(emailMember.getUserName()!=null && !"".equals(emailMember.getUserName())){
					CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", emailMember.getUserName(),30*24*60*60, "/", ".111yao.com", false);
				}else{
					CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", emailMember.getMobile(),30*24*60*60, "/", ".111yao.com", false);
				}
				CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", emailMember.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
			}
		}else{
			flag = 1;// 用户不存在
		}
		out.print(flag);
		out.close();
	}
	/**
	 * 手机快捷登录
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void userMobileLogin() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = this.getRequest().getParameter("mobile_username");
		String smsCode = this.getRequest().getParameter("smsCode");//前台获取的短信验证码
		String amoblieCode = (String) MemCached.getmcc().get(username);//后台比较的验证码
		String key = cookieManager.getCookieValueByName(this.getRequest(), CartAction.cartKey);
		TMemberGrade memberGrade1 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectDefaultGrade");
		TMemberGrade memberGrade2 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectlowGrade");
		map.put("mobile", username);
		
		TMember member = (TMember) this.opensqlmanage.selectObjectByObject(map, "t_member.ibatorgenerated_selectMemberByMobile");
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		//TMember usnameMember = (TMember) opensqlmanage.selectObjectByObject(map,"t_member.ibatorgenerated_selectByUserName");
		if (member == null) {
			tmember=new TMember();
			TMemberBaseMessageExt tmemberBaseMessageext=new TMemberBaseMessageExt();
			if(!smsCode.equalsIgnoreCase(amoblieCode)){//amoblieCode
				flag=1;//短信验证码不正确
			}else{
				tmember.setUserName("yyg_"+username);
				tmemberBaseMessageext.setNickName(username);
				tmember.setMobile(username);
				tmember.setPassword(MD5.MD5("111"+username+"yao"));
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
				TLeader leader = getLeader();
				tmembermanager.insertSelective(tmember,leader);
				tmemberBaseMessageext.setMemberId(tmember.getId());
				tmembermanager.savetmemberbasemessageext(tmemberBaseMessageext);
				this.getSession().setAttribute("member", tmember);
				if(key!=null){
					cartmanager.synCart(tmember.getId(), key);
				}
				//新用户送优惠券
				String cp = InfoUtil.getInstance().getInfo("config", "regCouponId");
				List regCouponMap = new ArrayList();
				String regCouponId="";
				Map couponMap=new HashMap();
				if(cp!=null&&!cp.equals("")){
					String c[] = cp.split(",");
					for (int i = 0; i < c.length; i++) {
						regCouponId=c[i];
						couponMap.put("regCouponId", regCouponId);
						TCoupon coupon = (TCoupon) opensqlmanage.selectObjectByObject(couponMap, "t_coupon.selectCouponByid");
						regCouponMap.add(coupon);
					}
					tcouponcardmanager.bindingCoupon(regCouponMap,tmember.getId(), 1);
				}
				if(tmember.getUserName()!=null && !"".equals(tmember.getUserName())){
					CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", tmember.getUserName(),30*24*60*60, "/", ".111yao.com", false);
				}else{
					CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", tmember.getMobile(),30*24*60*60, "/", ".111yao.com", false);
				}
				CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", tmember.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
				flag=2;//成功
			}
		} else {
			if (member.getStatus() == 0) {
				 if (!smsCode.equalsIgnoreCase(amoblieCode)) {//amoblieCode
					flag = 1;// 短信验证码不正确
				} else{
					member.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
					member.setLastDate(new Date());
					tmembermanager.updateByPrimaryKey(member);
					if(key!=null){
						cartmanager.synCart(member.getId(), key);
					}
					this.getSession().setAttribute("member", member);
					flag = 2;//成功
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
	/**
	 * 红包分享快捷登录
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void redMobileLogin() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> unmap = new HashMap<String, Object>();
		
		String leader_code = this.getRequest().getParameter("leader_code");
		String packetId = this.getRequest().getParameter("packetId");
		String couponId = this.getRequest().getParameter("couponId");
		String username = this.getRequest().getParameter("mobile_username");
		String leaderid = this.getRequest().getParameter("lid");
		String wxUnionId = (String) this.getSession().getAttribute("wxUnionId");
		
		System.out.println("username----------------->"+username);
		System.out.println("couponId----------------->"+couponId);
		System.out.println("packetId----------------->"+packetId);
		System.out.println("leader_code----------------->"+leader_code);
		System.out.println("leaderid----------------->"+leaderid);
		
		String key = cookieManager.getCookieValueByName(this.getRequest(), CartAction.cartKey);
		String smsCode =  this.getRequest().getParameter("smsCode");//前台获取的短信验证码
		String amoblieCode = (String) MemCached.getmcc().get(username);//后台比较的验证码
		TMemberGrade memberGrade1 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectDefaultGrade");
		TMemberGrade memberGrade2 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectlowGrade");
		map.put("mobile", username);
		unmap.put("binding_uuid", wxUnionId);
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		TMember member = (TMember) opensqlmanage.selectObjectByObject(map,"t_member.ibatorgenerated_selectMemberByMobile");
		//TMember member1 = (TMember) opensqlmanage.selectObjectByObject(unmap, "t_member.selectmemberByWxOpenid");
		
		memberThreeBinding = (TMemberThreeBinding) this.opensqlmanage.selectForObjectByMap(unmap, "t_member_three_binding.ibatorgenerated_selectByBindingUuid");
		if (member == null) {
			tmember=new TMember();
			TMemberBaseMessageExt tmemberBaseMessageext=new TMemberBaseMessageExt();
			if(!smsCode.equalsIgnoreCase(amoblieCode)){//amoblieCode
				flag=1;//短信验证码不正确
			}else{
			tmember.setUserName("yyg_"+username);
			tmemberBaseMessageext.setNickName(username);
			tmember.setMobile(username);
			tmember.setPassword(MD5.MD5("dst111yao"));
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
			Map<String, Object> redMap = new HashMap<String, Object>();
			redMap.put("code", leader_code);
			TLeader leader  = new TLeader();//(TLeader) opensqlmanage.selectObjectByObject(redMap, "t_leader.selectLeaderByCode");
			leader.setId(Long.parseLong(leaderid));
			tmembermanager.insertSelective(tmember,leader);
			tmemberBaseMessageext.setMemberId(tmember.getId());
			tmembermanager.savetmemberbasemessageext(tmemberBaseMessageext);
			this.getSession().setAttribute("member", tmember);
			if(memberThreeBinding==null){
				memberThreeBinding=new TMemberThreeBinding();
				memberThreeBinding.setMemberId(tmember.getId());
				memberThreeBinding.setSource(0);//微信
				memberThreeBinding.setCreateDate(new Date());
				memberThreeBinding.setBindingUuid(wxUnionId);
				tmembermanager.saveMemberThreeBinding(memberThreeBinding);
			}
			tcouponcardmanager.leaderBindingCoupon(Long.valueOf(couponId),tmember,1,Long.valueOf(packetId));
			//新用户送优惠券
			String cp = InfoUtil.getInstance().getInfo("config", "regCouponId");
			List regCouponMap = new ArrayList();
			String regCouponId="";
			Map couponMap=new HashMap();
			if(cp!=null&&!cp.equals("")){
				String c[] = cp.split(",");
				for (int i = 0; i < c.length; i++) {
					regCouponId=c[i];
					couponMap.put("regCouponId", regCouponId);
					TCoupon coupon = (TCoupon) opensqlmanage.selectObjectByObject(couponMap, "t_coupon.selectCouponByid");
					regCouponMap.add(coupon);
				}
				tcouponcardmanager.bindingCoupon(regCouponMap,tmember.getId(), 1);
			}
			flag=2;//成功
			}
		}else {
			if (member.getStatus() == 0) {
				 if (!smsCode.equalsIgnoreCase(amoblieCode)) {//amoblieCode
					flag = 1;// 短信验证码不正确
				} else if (username.equalsIgnoreCase(member.getMobile())) {
					member.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
					member.setLastDate(new Date());
					tmembermanager.updateByPrimaryKey(member);
					if(key!=null){
						cartmanager.synCart(member.getId(), key);
					}
					this.getSession().setAttribute("member", member);
					
					Map<String, Object> countMap = new HashMap<String, Object>();
					countMap.put("redId", packetId);
					countMap.put("mobile", username);
					Integer count = (Integer) opensqlmanage.selectObjectByObject(countMap, "t_member.select_mobile_count");
					if(count>0){
						System.out.println("111111111111111111111");
						flag = -100;
					}else{
						System.out.println("2222222222222222222222222");
						//flag = 2;//成功
						long bindid = tcouponcardmanager.leaderBindingCoupon(Long.valueOf(couponId),member,1,Long.valueOf(packetId));
						System.out.println("bindid------->"+bindid);
						if(bindid<=0){
							flag = -1;
						}else{
							flag = 2;
						}
					}
				}
			}else{
				flag = 3;// 该帐户被停用
			}
		
		}
		out.print(flag);
		out.close();
	}
	/*
	 * 发送验证码
	 */
	public void validateMobileCode() throws Exception {
		String captcha = CodeUtil.getVcode(4);
		String username = this.getRequest().getParameter("mobile_username");
		 MemCached.getmcc().set(username,captcha,new Date(300));
		Pattern pattern = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		if (username != null && pattern.matcher(username).matches()) {
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("mobiles", username);
				map.put("smsContent", "您的111医药馆验证码:"+captcha+"。影视明星何政军先生推荐的中国好药房@111医药馆！");
				String YAO_GATEWAY_URL =tsysparametermanager.getKeys("sms");
				String buildRequestBySMS = ClientSubmit.buildRequestBySMS(map,YAO_GATEWAY_URL);
				System.out.println(buildRequestBySMS);
				System.out.println("lijianlong code------>"+captcha);
				flag = 0;
			} catch (Exception e) {
				flag = 1;
				e.printStackTrace();
			}
		
		}else{
			flag=3;//手机号码格式不对
		}
		out.print(flag);
		out.close();
	}
	public int checkUserNameIsExist() throws Exception{
		int flag=1;
		String username = this.getRequest().getParameter("username");
		TMemberExample e = new TMemberExample();
		e.createCriteria().andUserNameEqualTo(username);
		int countByExample = this.tmembermanager.countByExample(e);
		if(countByExample > 0){
			flag=0;
		}
		return flag;
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
	/**
	 * 退出登录
	 */
		public String logout(){
			this.getSession().removeAttribute("member");
			this.getSession().removeAttribute("leader");
			CookieUtil.removeCookie(getRequest(), getResponse(), "username", "/", "");
			CookieUtil.removeCookie(getRequest(), getResponse(), "nickname", "/", "");
			CookieUtil.removeCookie(getRequest(), getResponse(), "cookusername", "/", ".111yao.com");
			CookieUtil.removeCookie(getRequest(), getResponse(), "cookpassword", "/", ".111yao.com");
			return "logout";	
		}
	
	public void home() throws IOException
	{
		getResponse().sendRedirect("/index.html");
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
	/**
	 * 转到登录页面
	 */
	public String index() {
		return "login";
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
		String currentDomain = this.getRequest().getParameter("currentDomain");
		this.getSession().setAttribute("currentDomain", currentDomain);
		String redirectUrl="";
		if(getTicket()!=null&&!getTicket().equals("")){
		Map json = JsonUtils.toObject(getTicket(), Map.class);
		String ticket=json.get("ticket").toString();
		System.out.println("返回的ticket------>"+ticket);
		if(currentDomain==""||"".equals(currentDomain)){
			redirectUrl="http://m.111yao.com/login/login!getUnionid.action";
		}else{
			redirectUrl=currentDomain+"/login/login!getUnionid.action";
		}
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
        	System.out.println("LoginRedirect:"+wxLoginRedirect);
        	this.getResponse().sendRedirect(oauthurl);
        } catch (Exception e) {
            e.printStackTrace();
        }
		}else{
			this.getResponse().sendRedirect("http://m.111yao.com");
		}
	}
	/*
	 * 获取微信unionid
	 */
	public void getUnionid() throws IOException{
		String currentDomainUrl = (String) this.getSession().getAttribute("currentDomain");
		String unionId = this.getRequest().getParameter("unionId");
		this.getSession().setAttribute("wxUnionId", unionId);
		System.out.println("微信用户当前的unionId-----》："+unionId);
		if(currentDomainUrl==""||"".equals(currentDomainUrl)){
			currentDomainUrl="http://m.111yao.com/healthLeader/lxredpack!share.action";
		}else{
			currentDomainUrl=currentDomainUrl+"/healthLeader/lxredpack!share.action";
		}
		this.getResponse().sendRedirect(currentDomainUrl);
	}
	/*
	 * 进入首页通过cookie取Member信息
	 *
	 */
	public void getMemberInfo() throws Exception{
		String username = CookieUtil.getCookie(this.getRequest(), "cookusername");
		String cookpassword = CookieUtil.getCookie(this.getRequest(), "cookpassword");
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  getResponse().getWriter();
	    
	    int flag=1;
		Map<String, Object> mapkey = new HashMap<String, Object>();
		mapkey.put("cookpassword", cookpassword);
		mapkey.put("user_name", username);
		TMember member = (TMember)  opensqlmanage.selectObjectByObject(mapkey,"t_member.selectMemberByCookie");
		if(member!=null){
			this.getSession().setAttribute("member", member);
		}
		out.print(flag);
		out.close();
	}
	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {
	}

	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}
	
}
