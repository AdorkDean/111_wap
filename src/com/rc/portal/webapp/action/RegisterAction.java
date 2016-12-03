package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.commons.util.InfoUtil;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.portal.memcache.MemCached;
import com.rc.portal.service.ICartManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponCardManager;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.service.TMemberBaseMessageExtManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TMemberThreeBindingManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.CodeUtil;
import com.rc.portal.util.CookieUtil;
import com.rc.portal.util.NetworkUtil;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberBaseMessageExt;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.vo.TMemberGrade;
import com.rc.portal.vo.TMemberThreeBinding;
import com.rc.portal.webapp.util.MD5;


public class RegisterAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private TMemberManager tmembermanager;
	private TMember tmember ;
	private TMemberAccountManager tmemberaccountmanager;
	private OpenSqlManage opensqlmanage;
	private TSysParameterManager tsysparametermanager;
	private TMemberThreeBindingManager tmemberthreebindingmanager;
	private TMemberThreeBinding memberThreeBinding;
	private ICartManager cartmanager;
	private TCouponCardManager tcouponcardmanager;
	private TMemberBaseMessageExtManager tmemberbasemessageextmanager;
	
	public TMemberBaseMessageExtManager getTmemberbasemessageextmanager() {
		return tmemberbasemessageextmanager;
	}
	public void setTmemberbasemessageextmanager(
			TMemberBaseMessageExtManager tmemberbasemessageextmanager) {
		this.tmemberbasemessageextmanager = tmemberbasemessageextmanager;
	}
	public TCouponCardManager getTcouponcardmanager() {
		return tcouponcardmanager;
	}
	public void setTcouponcardmanager(TCouponCardManager tcouponcardmanager) {
		this.tcouponcardmanager = tcouponcardmanager;
	}

	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ICartManager getCartmanager() {
		return cartmanager;
	}
	public void setCartmanager(ICartManager cartmanager) {
		this.cartmanager = cartmanager;
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
	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}
	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}
	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}
	public TMemberAccountManager getTmemberaccountmanager() {
		return tmemberaccountmanager;
	}
	public void setTmemberaccountmanager(TMemberAccountManager tmemberaccountmanager) {
		this.tmemberaccountmanager = tmemberaccountmanager;
	}
	public TMember getTmember() {
		return tmember;
	}
	public void setTmember(TMember tmember) {
		this.tmember = tmember;
	}
	
	public String index(){
		
		return "registerone";
	}
	/*
	 * 手机注册
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void mobileRegister() throws Exception{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		String username = this.getRequest().getParameter("username");
		this.getRequest().setAttribute("username", username);
		String password = this.getRequest().getParameter("password");
		String mobile_code = this.getRequest().getParameter("mobile_code");
		String amoblieCode = (String) MemCached.getmcc().get(username);
		TMemberGrade memberGrade1 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectDefaultGrade");
		TMemberGrade memberGrade2 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectlowGrade");
		int flag=-1;
		PrintWriter out = this.getResponse().getWriter();
		try {
			if(tmember==null){
				tmember=new TMember();
				TMemberBaseMessageExt tmemberBaseMessageext=new TMemberBaseMessageExt();
				if(!checkMobileUserName(username)){
					flag=0;//用户已经存在
				}else if(!mobile_code.equalsIgnoreCase(amoblieCode)){//amoblieCode
					flag=3;//手机验证码不正确
				}else{
				tmemberBaseMessageext.setNickName(username);
				tmember.setUserName("yyg_"+username);
				tmember.setMobile(username);
				tmember.setPassword(MD5.MD5("111"+password+"yao"));
				if(memberGrade1!=null){
					tmember.setMemberGradeId(memberGrade1.getId());
				}else{
					tmember.setMemberGradeId(memberGrade2.getId());
				}
				tmember.setAreaId(0l);
				tmember.setStatus(0);
				tmember.setEnterpriseDiscount(new BigDecimal(0));
				tmember.setIsLeader(0);
				tmember.setIntegral(0);
				tmember.setSource(5);//5表示手机注册
				tmember.setPlatform(2);//2表示WAP平台
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
				flag=1;
				}
			}
		} catch (Exception e) {
			out.print("0");
			out.close();
			e.printStackTrace();
		}
		out.print(flag);
		out.close();
		System.out.println("===============");
	}
	/*
	 * 发送验证码
	 */
	public void validateMobileCode() throws Exception{
		String captcha = CodeUtil.getVcode(4);
		String username = this.getRequest().getParameter("username");
		 MemCached.getmcc().set(username,captcha,new Date(1000*300));
		//this.getSession().setAttribute(username, captcha);
		Pattern pattern = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		 if (!pattern.matcher(username).matches()) {
				flag = 1;// 手机格式不正确
			}else{
				Map<String, String> smsMap = new HashMap<String, String>();
				smsMap.put("mobiles", username);
				smsMap.put("smsContent", "您的111医药馆验证码:"+captcha+"。影视明星何政军先生推荐的中国好药房@111医药馆！");
				String YAO_GATEWAY_URL = tsysparametermanager.getKeys("sms");
				String buildRequestBySMS = ClientSubmit.buildRequestBySMS(smsMap,YAO_GATEWAY_URL);
				System.out.println(buildRequestBySMS);
				System.out.println("您的手机验证码是:"+captcha);
				this.getSession().setAttribute("smsSuccess", "smsSuccess");
				flag=0;
				
			}
		out.print(flag);
		out.close();
	}
	
	public boolean checkUserName(String username) throws Exception{
		username = this.getRequest().getParameter("username");
		TMemberExample e = new TMemberExample();
		e.createCriteria().andUserNameEqualTo(username);
		int countByExample = this.tmembermanager.countByExample(e);
		if(countByExample > 0){
			return false;
		}
		return true;
	}
	public boolean checkMobileUserName(String username) throws Exception{
		username = this.getRequest().getParameter("username");
		TMemberExample e = new TMemberExample();
		e.createCriteria().andMobileEqualTo(username);
		int countByExample = this.tmembermanager.countByExample(e);
		if(countByExample > 0){
			return false;
		}
		return true;
	}
	public void checkUserNameIsExist() throws Exception{
		int flag=-1;
		String username = this.getRequest().getParameter("username");
		PrintWriter out =  this.getResponse().getWriter();
		TMemberExample e = new TMemberExample();
		e.createCriteria().andMobileEqualTo(username);
		int countByExample = this.tmembermanager.countByExample(e);
		if(countByExample > 0){
			flag=0;
		}else{
			flag=1;
		}
		out.print(flag);
		out.close();
	}
	/*
	 * 跳转到输入短信验证码页面
	 */
	public String nextStep() {
		String username = this.getRequest().getParameter("username");
		this.getRequest().setAttribute("username", username);
		return "registertwo";
	}
	/*
	 * 跳转到同意协议页面
	 */
	public String agree() {
		return "agreement";
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
	 * 跳转到首页
	 */
	public String home() {
		String code = CookieUtil.getCookie(this.getRequest(), "leader");
		this.getSession().setAttribute("leaderCode", code);
		return "index";
	}
	/*
	 * dst微信用户注册成为会员
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void dstWxRegister() throws Exception{
		int flag=-1;
		PrintWriter out =  this.getResponse().getWriter();
		String unionId = this.getRequest().getParameter("unionid");
		String nickname = this.getRequest().getParameter("nickname");
		String headimgurl = this.getRequest().getParameter("headimgurl");
	if(unionId==""||"".equals(unionId)||null==unionId){
		flag=3;//unionId为空，数据异常
	}else{
		Map openmap=new HashMap();
		openmap.put("binding_uuid", unionId);
		TMember member1 = (TMember) opensqlmanage.selectObjectByObject(openmap, "t_member.selectmemberByWxOpenid");
		if(member1!=null){
			if((nickname!=""||!"".equals(nickname)||null!=nickname)&&(headimgurl!=""||!"".equals(headimgurl)||null!=headimgurl)){
				TMemberBaseMessageExt memberBaseMessage =this.tmemberbasemessageextmanager.selectByPrimaryKey(member1.getId());
				if(memberBaseMessage!=null){
					memberBaseMessage.setNickName(nickname);
					memberBaseMessage.setHeadPortrait(headimgurl);
					tmemberbasemessageextmanager.updateByPrimaryKeySelective(memberBaseMessage);
				}
			}
			flag=1;//用户存在
		}else{
			tmember = new TMember();
			TMemberGrade memberGrade1 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectDefaultGrade");
			TMemberGrade memberGrade2 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectlowGrade");
			TMemberBaseMessageExt tmemberBaseMessageext=new TMemberBaseMessageExt();
			tmemberBaseMessageext.setNickName(nickname);
			tmemberBaseMessageext.setHeadPortrait(headimgurl);
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
			memberThreeBinding.setWxAppid(null);
			tmembermanager.saveMemberThreeBinding(memberThreeBinding);
			flag=2;//新用户注册成功！
		 }
		}
		out.print(flag);
		out.close();
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
	 public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}


}
