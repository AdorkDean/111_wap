package com.rc.portal.webapp.action.unionlogin;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.util.JsonUtils;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.dst.client.util.ClientSubmitPublic;
import com.rc.portal.memcache.MemCached;
import com.rc.portal.service.ICartManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponCardManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TMemberThreeBindingManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.CodeUtil;
import com.rc.portal.util.CookieUtil;
import com.rc.portal.util.NetworkUtil;
import com.rc.portal.util.ParametersCommon;
import com.rc.portal.util.WapWxAutoUtil;
import com.rc.portal.util.cookieManager;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberBaseMessageExt;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.vo.TMemberGrade;
import com.rc.portal.vo.TMemberThreeBinding;
import com.rc.portal.webapp.action.CartAction;
import com.rc.portal.webapp.util.MD5;

public class wxLoginAction extends BaseAction{

	private static final long serialVersionUID = -1472840739209948178L;
	private static final String ticket = getTicket();
	private TMemberManager tmembermanager;
	private TMemberThreeBinding memberThreeBinding;
	private TMemberThreeBindingManager tmemberthreebindingmanager;
	private OpenSqlManage opensqlmanage;
	private ICartManager cartmanager;
	private TCouponCardManager tcouponcardmanager;
	private TMember tmember ;
	private TSysParameterManager tsysparametermanager;
	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}
	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}
	public TMember getTmember() {
		return tmember;
	}
	public void setTmember(TMember tmember) {
		this.tmember = tmember;
	}
	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}
	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
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
	/*
	 * 微信自动登录---获取ticket值
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getTicket(){
		String ticket="";
		String key="5k8264iltkch16cq2502si8znmtm67vs";
		Map map=new HashMap();
		map.put("ticketUseSet", "get_user_info_longtime");
		map.put("appIdInt", "10004");
		String sign=ParametersCommon.getWXSign(map, key);
		String url="http://weixin.111yao.com/bec15bf762a/wxTicket.do?ticketUseSet=get_user_info_longtime&appIdInt=10004&sign="+sign;
		ticket=WapWxAutoUtil.sendGet(url);
		Map json = JsonUtils.toObject(ticket, Map.class);
		ticket=json.get("ticket").toString();
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
		if(ticket!=null&&!ticket.equals("")){
		System.out.println("返回的ticket------>"+ticket);
		String redirectUrl="http://m.111yao.com/login/wxlogin!wxAutoLogin.action";
		String key="5k8264iltkch16cq2502si8znmtm67vs";
		Map map=new HashMap();
		map.put("ticketUseSet", "get_user_info_longtime");
		map.put("appIdInt", "10004");
		map.put("ticket", ticket);
		map.put("redirectUrl", redirectUrl);
		map.put("reqScope", "snsapi_userinfo");
		map.put("fromCache", "1");
		String sign=ParametersCommon.getWXSign(map, key);
		@SuppressWarnings("deprecation")
		String oauthurl="http://weixin.111yao.com/publish/user_info.jsp?ticketUseSet=get_user_info_longtime&ticket="+ticket+"&appIdInt=10004&redirectUrl="+URLEncoder.encode(redirectUrl)+"&reqScope=snsapi_userinfo&fromCache=1&sign="+sign;
		try {
        	if(wxLoginRedirect != null){
        		this.getSession().setAttribute("wapLoginRedirect", wxLoginRedirect);
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
	 * 如果unionId不存在进行第二次授权
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void twouthUrl() throws Exception{
		String wxLoginRedirect = this.getRequest().getParameter("wxLoginRedirect");
		if(ticket!=null&&!ticket.equals("")){
		System.out.println("返回的ticket------>"+ticket);
		String redirectUrl="http://m.111yao.com/login/wxlogin!wxAutoLogin.action?wxsign=wxsign";
		String key="5k8264iltkch16cq2502si8znmtm67vs";
		Map map=new HashMap();
		map.put("ticketUseSet", "get_user_info_longtime");
		map.put("appIdInt", "10004");
		map.put("ticket", ticket);
		map.put("redirectUrl", redirectUrl);
		map.put("reqScope", "snsapi_userinfo");
		map.put("fromCache", "0");
		String sign=ParametersCommon.getWXSign(map, key);
		@SuppressWarnings("deprecation")
		String oauthurl="http://weixin.111yao.com/publish/user_info.jsp?ticketUseSet=get_user_info_longtime&ticket="+ticket+"&appIdInt=10004&redirectUrl="+URLEncoder.encode(redirectUrl)+"&reqScope=snsapi_userinfo&fromCache=0&sign="+sign;
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
	/* * 微信用户自动登录*/
	 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String wxAutoLogin() throws Exception{
		String unionId = this.getRequest().getParameter("unionId");
		String nickname = this.getRequest().getParameter("nickname");
		String wxsign = this.getRequest().getParameter("wxsign");
		if(wxsign!=""&&!"".equals(wxsign)&& wxsign!=null){
			if(unionId==""||"".equals(unionId)||null==unionId || "null".equalsIgnoreCase( unionId )){
				this.getResponse().sendRedirect("http://m.111yao.com");
			}
		}
		if(unionId==""||"".equals(unionId)||null==unionId || "null".equalsIgnoreCase( unionId )){
			twouthUrl();
			if(unionId==""||"".equals(unionId)||null==unionId || "null".equalsIgnoreCase( unionId )){
				this.getResponse().sendRedirect("http://m.111yao.com");
			}
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
			if(member1!=null){
				if(key!=null){
					cartmanager.synCart(member1.getId(), key);
				}
				//this.getSession().setAttribute("member", member1);
//				if(member1.getUserName()!=null && !"".equals(member1.getUserName())){
//					CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member1.getUserName(),30*24*60*60, "/", ".111yao.com", false);
//				}else{
//					CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member1.getMobile(),30*24*60*60, "/", ".111yao.com", false);
//				}
//				CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", member1.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
				System.out.println(member1);
				String attribute = (String) this.getSession().getAttribute("wapLoginRedirect");
				if(member1.getMobile()==null || member1.getMobile()==""){
					return "popup";
				}else{
					this.getSession().setAttribute("member", member1);
					if(member1.getUserName()!=null && !"".equals(member1.getUserName())){
						CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member1.getUserName(),30*24*60*60, "/", ".111yao.com", false);
					}else{
						CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member1.getMobile(),30*24*60*60, "/", ".111yao.com", false);
					}
					CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", member1.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
					if (attribute == null) {
						this.getResponse().sendRedirect("/index.html");
					} else {
						this.getSession().removeAttribute("wxLoginRedirect");
						this.getResponse().sendRedirect(attribute);
					}
				}
			}else{
				tmember = new TMember();
				TMemberGrade memberGrade1 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectDefaultGrade");
				TMemberGrade memberGrade2 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectlowGrade");
				TMemberBaseMessageExt tmemberBaseMessageext=new TMemberBaseMessageExt();
				tmemberBaseMessageext.setNickName(nickname);
				tmember.setUserName("wx_"+unionId);
				if(memberGrade1!=null){
					tmember.setMemberGradeId(memberGrade1.getId());
				}else{
					tmember.setMemberGradeId(memberGrade2.getId());
				}
				tmember.setPassword(MD5.MD5("111"+unionId+"yao"));
				tmember.setStatus(0);
				tmember.setEnterpriseDiscount(new BigDecimal(0));
				tmember.setIsLeader(0);
				tmember.setIntegral(0);
				tmember.setSource(0);//0表示微信注册
				tmember.setPlatform(2);//1表示PC平台
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
				tmemberBaseMessageext.setMemberId(tmember.getId());
				tmembermanager.savetmemberbasemessageext(tmemberBaseMessageext);
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
				//this.getSession().setAttribute("member", tmember);
//				if(tmember.getUserName()!=null && !"".equals(tmember.getUserName())){
//					CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", tmember.getUserName(),30*24*60*60, "/", ".111yao.com", false);
//				}else{
//					CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", tmember.getMobile(),30*24*60*60, "/", ".111yao.com", false);
//				}
//				CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", tmember.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
				String attribute = (String) this.getSession().getAttribute("wxLoginRedirect");
				if(tmember.getMobile()==null || tmember.getMobile()==""){
					return "popup";
				}else{
					this.getSession().setAttribute("member", tmember);
					if(tmember.getUserName()!=null && !"".equals(tmember.getUserName())){
						CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", tmember.getUserName(),30*24*60*60, "/", ".111yao.com", false);
					}else{
						CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", tmember.getMobile(),30*24*60*60, "/", ".111yao.com", false);
					}
					CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", tmember.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
					if (attribute == null) {
						this.getResponse().sendRedirect("/index.html");
					} else {
						this.getSession().removeAttribute("wxLoginRedirect");
						this.getResponse().sendRedirect(attribute);
					}
				}
			}
			return "index";
		}
		return "index";
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
	/*
	 * 绑定手机号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void bindingMobile() throws Exception {
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		String mobile = this.getRequest().getParameter("mobile");
		String smsCode = this.getRequest().getParameter("smsCode");
		String code = (String) MemCached.getmcc().get(mobile);
		String unionId = (String) this.getRequest().getSession().getAttribute("bdunionId");
		Map map = new HashMap();
		Map usermap = new HashMap();
		map.put("binding_uuid", unionId);
		usermap.put("user_name", unionId);
		// 根据openid判断用户信息是否已经存在
		memberThreeBinding = (TMemberThreeBinding) this.opensqlmanage.selectForObjectByMap(map,"t_member_three_binding.ibatorgenerated_selectByBindingUuid");
		if(memberThreeBinding!=null){
			Map openmap = new HashMap();
			openmap.put("binding_uuid", unionId);
			TMember member = (TMember) opensqlmanage.selectObjectByObject(openmap, "t_member.selectmemberByOpenid");
			if(member!=null){
				if(!smsCode.equalsIgnoreCase(code)){//code
					flag =1;//验证码不正确
				}else{
					if (!checkUserName(mobile)) {
						Map<String, Object> mobileMap = new HashMap<String, Object>();
						mobileMap.put("mobile", mobile);
						TMember mobileMember = (TMember) this.opensqlmanage.selectObjectByObject(mobileMap, "t_member.ibatorgenerated_selectMemberByMobile");
						String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
						Map<String,String> publicMap = new HashMap<String,String>();
						publicMap.put("memberid_old", String.valueOf(member.getId()));//第三方用户id
						publicMap.put("memberid_new", String.valueOf(mobileMember.getId()));//此手机号注册的用户id
						String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"memberMergeURIService");
						//String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, "http://192.168.101.136:8080/111_yao_common/sltRouter?method=memberMergeURIService");
						JSONObject resultJson = JSONObject.fromObject(resultJsonstr);
						System.out.println("返回的resultJson:------>："+resultJson);
						if(resultJson.get("code").equals("1")){
							this.getSession().setAttribute("member", mobileMember);
							if(mobileMember.getUserName()!=null && !"".equals(mobileMember.getUserName())){
								CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", mobileMember.getUserName(),30*24*60*60, "/", ".111yao.com", false);
							}else{
								CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", mobileMember.getMobile(),30*24*60*60, "/", ".111yao.com", false);
							}
							CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", mobileMember.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
							flag=2;//绑定成功
							System.out.println("绑定成功状态flag---------》："+flag);
						}else{
							flag=3;//账号异常,请联系客服
						}
					}else{
						member.setMobile(mobile);
						member.setIsMobileCheck(1);
						tmembermanager.updateByPrimaryKeySelective(member);
						this.getSession().setAttribute("member", member);
						if(member.getUserName()!=null && !"".equals(member.getUserName())){
							CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member.getUserName(),30*24*60*60, "/", ".111yao.com", false);
						}else{
							CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", member.getMobile(),30*24*60*60, "/", ".111yao.com", false);
						}
						CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", member.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
						flag=2;//绑定成功
					};
				}
			}else{
				flag=3;//数据异常
			}
		}
		out.print(flag);
		out.close();
	}
	public boolean checkUserName(String username) throws Exception{
		TMemberExample e = new TMemberExample();
		e.createCriteria().andMobileEqualTo(username);
		int countByExample = this.tmembermanager.countByExample(e);
		if(countByExample > 0){
			return false;
		}
		return true;
	}
	/*
	 * 发送验证码
	 */
	public void validateMobileCode() throws Exception {
		String captcha = CodeUtil.getVcode(4);
		String username = this.getRequest().getParameter("mobile");
		MemCached.getmcc().set(username,captcha,new Date(300));
		Pattern pattern = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		if (username != null && pattern.matcher(username).matches()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("mobiles", username);
			map.put("smsContent","您的111医药馆验证码:"+captcha+"。影视明星何政军先生推荐的中国好药房@111医药馆！");
			String YAO_GATEWAY_URL = tsysparametermanager.getKeys("sms");
			String buildRequestBySMS = ClientSubmit.buildRequestBySMS(map,YAO_GATEWAY_URL);
			System.out.println(buildRequestBySMS);
			flag=0;
		}
		System.out.println("验证码captcha----------》："+captcha);
		out.print(flag);
		out.close();
	}
	/*
	 * 转到绑定页
	 */
	public String index(){
		
		return "bingpage";
	}
	/*
	 *转到绑定成功页
	 */
	public String success(){
		return "success";
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
