package com.rc.portal.webapp.action;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.commons.util.InfoUtil;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.dst.client.util.ClientSubmitPublic;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponManager;
import com.rc.portal.service.TMemberBaseMessageExtManager;
import com.rc.portal.service.TMemberIntegralManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TMemberReceiverManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.CodeUtil;
import com.rc.portal.util.CustomDigestUtils;
import com.rc.portal.util.JsonUtil;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberBaseMessageExt;

/**
 * 会员中心
 * @author 刘天灵
 *
 */
public class MemberProfileAction extends BaseAction{

	private static final long serialVersionUID = 35345345341L;
	
	private OpenSqlManage  opensqlmanage;
	
	private TMemberManager tmembermanager;
	
	private TMemberIntegralManager tmemberintegralmanager;
	
	private TMemberReceiverManager tmemberreceivermanager;
	
	
	private TMemberBaseMessageExtManager tmemberbasemessageextmanager;
	
	TSysParameterManager tsysparametermanager;
	
	private TCouponManager tcouponmanager;
	
	private  List<Map<String, Object>> resultList;
	
	public List<Map<String, Object>> getResultList() {
		return resultList;
	}

	public void setResultList(List<Map<String, Object>> resultList) {
		this.resultList = resultList;
	}

	public TCouponManager getTcouponmanager() {
		return tcouponmanager;
	}

	public void setTcouponmanager(TCouponManager tcouponmanager) {
		this.tcouponmanager = tcouponmanager;
	}

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

	public TMemberReceiverManager getTmemberreceivermanager() {
		return tmemberreceivermanager;
	}

	public void setTmemberreceivermanager(
			TMemberReceiverManager tmemberreceivermanager) {
		this.tmemberreceivermanager = tmemberreceivermanager;
	}

	public TMemberIntegralManager getTmemberintegralmanager() {
		return tmemberintegralmanager;
	}

	public void setTmemberintegralmanager(
			TMemberIntegralManager tmemberintegralmanager) {
		this.tmemberintegralmanager = tmemberintegralmanager;
	}

	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}

	private TMember tmember = new TMember();
	
	public TMember getTmember() {
		return tmember;
	}

	public void setTmember(TMember tmember) {
		this.tmember = tmember;
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

	@Override
	public void setModel(Object o) {
		
	}
	
	/**
	 * 
	 * 个人资料(会员中心首页)
	 * @return
	 * @throws Exception 
	 */
	public String index() throws Exception{		
		//获取用户消费总额
//		this.getRequest().setAttribute("monetary",opensqlmanage.selectObjectByObject(this.getSession().getAttribute("member"), "t_order.select_member_consume"));
		TMember member = (TMember)this.getSession().getAttribute("member");
		
		TMemberBaseMessageExt memberBaseMessage =this.tmemberbasemessageextmanager.selectByPrimaryKey(member.getId());
		
		if(memberBaseMessage!=null){
			this.getRequest().setAttribute("nickName", memberBaseMessage.getNickName());
			this.getRequest().setAttribute("realName", memberBaseMessage.getRealName());
			this.getRequest().setAttribute("headPortrait", memberBaseMessage.getHeadPortrait());
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("memberId", member.getId());
		//待支付订单
		Object unpaidCount = this.opensqlmanage.selectObjectByObject(param, "t_order.select_unpaid_count");
		this.getRequest().setAttribute("unpaidCount", unpaidCount);
		
		return "index";
	}

	
	/**
	 * 完善资料
	 * @return
	 */
	public String perfect() {
		System.out.println("-----------------------");		
		return "perfect";
	}

	
	/**
	 * 用户头像
	 */
	public String headPortrait(){
		return "upload_head_portrait";
	}
	
	private File head;
	
	private String headFileName;
	
	private String headContentType;
	
	private String diskPath = InfoUtil.getInstance().getInfo("img", "images.public.head.path");
	
	/**
	 * 上传头像 
	 */
	public void uploadHeadPortrait(){
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		String fileType= FilenameUtils.getExtension(headFileName);
		try {
			String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");	
			if(checkFileType(fileType) && head.length() < 4*1024*1024){
				String webPath = diskPath+"/"+UUID.randomUUID()+"."+fileType;
				String fullName = basePath + webPath;
				
				File uploadFile = new File(fullName);
				FileUtils.copyFile(head, uploadFile);//上传图片
				sessionMember.setHeadPortrait(webPath);
				
				this.tmembermanager.updateByPrimaryKeySelective(sessionMember);
				
				this.getSession().setAttribute("member", sessionMember);
				
				this.writeObjectToResponse(1, ContentType.application_json);
			}else{
				this.writeObjectToResponse(0, ContentType.application_json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.writeObjectToResponse(1, ContentType.application_json);
		}
	}

	public File getHead() {
		return head;
	}

	public void setHead(File head) {
		this.head = head;
	}

	public String getHeadFileName() {
		return headFileName;
	}

	public void setHeadFileName(String headFileName) {
		this.headFileName = headFileName;
	}

	public String getHeadContentType() {
		return headContentType;
	}

	public void setHeadContentType(String headContentType) {
		this.headContentType = headContentType;
	}
	
	/**
	 * 检查文件类型
	 * @param type
	 * @return
	 */
	public boolean checkFileType(String type){
		boolean flag=false;
		type = type.toLowerCase();
		String[] arrType={"jpg","png","gif","jpeg"};
		for(String s:arrType){
			if(type.equals(s)){
				return true;
			}
		}
		return flag;
	}
	
	/**
	 * 编辑验证邮箱 
	 */
	public String editEmail(){
		
		
		return "edit_email";
	}
	
	
	/**
	 * 保存邮箱 
	 * @throws SQLException 
	 */
	public String saveEmail() throws SQLException{
		
		String email = this.getRequest().getParameter("email");
		String emailCheck = this.getRequest().getParameter("emailCheck");
		String rand = this.getRequest().getParameter("rand");
		
		String sessionRand = (String)this.getSession().getAttribute("rand");
		
		
		//TODO 检查验邮箱验证码是否匹配session处理(刘天灵)
		String sessionEmailCheck = (String)this.getSession().getAttribute("emailCheck");
		
		this.getRequest().setAttribute("email", email);
		
		if(StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(emailCheck) && emailCheck.equals(sessionEmailCheck) && StringUtils.isNotEmpty(rand)&& sessionRand.equalsIgnoreCase(rand)){
			
			TMember member = (TMember)this.getSession().getAttribute("member");
			
			member.setEmail(email);
			
			member.setIsEmailCheck(1);
			
			this.tmembermanager.updateByPrimaryKeySelective(member);
			
			this.getSession().setAttribute("member", member);
			
			this.getRequest().setAttribute("option", true);
			
		}else{
			this.getRequest().setAttribute("option", false);
		}
		
		return "edit_email_success";
	}
	
	
	/**
	 * 发送邮箱验证码
	 */
	public void sendEmailCheck(){
		
		String email = this.getRequest().getParameter("email");
		
		UUID randomUUID = UUID.randomUUID();

		//TODO 发送验证码到邮箱
		System.out.println(email);
		System.out.println(randomUUID);
		
		this.getSession().setAttribute("emailCheck", randomUUID.toString());
		
		this.writeObjectToResponse(1, ContentType.application_json);
	}
	
	
	/**
	 * 发送手机验证码
	 * @throws Exception 
	 */
	public void sendMobileCheck() throws Exception{
	
		String mobile = this.getRequest().getParameter("mobile");
		
		String numVcode = CodeUtil.getVcode(4);
		
		//发送手机验证码
		System.out.println("短信验证码 session-key:mobileCheck    value:"+mobile+"|"+numVcode);
		
		this.getSession().setAttribute("mobileCheck", mobile+"|"+numVcode);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobiles", mobile);
		String smsContent = "您的账户正在验证有效手机号,校验码:"+numVcode + "。 111医药馆,专做药品特卖的网站！";
		map.put("smsContent", smsContent);
		ClientSubmit.buildRequestBySMS(map,tsysparametermanager.getKeys("sms"));
		
		this.writeObjectToResponse(1, ContentType.application_json);
	}
	
	/**
	 * 编辑验证手机 
	 */
	public String editMobile(){
		
		return "edit_mobile";
	}
	
	/**
	 * 保存验证手机 
	 * @throws SQLException 
	 */
	public String saveMobile() throws SQLException{
		
		String mobile = this.getRequest().getParameter("mobile");
		String mobileCheck = this.getRequest().getParameter("mobileCheck");
		String rand = this.getRequest().getParameter("rand");
		
		String sessionRand = (String)this.getSession().getAttribute("rand");

		//TODO 检查验手机验证码是否匹配session处理(刘天灵)
		String sessionMobileCheck = (String)this.getSession().getAttribute("mobileCheck");
		
		this.getRequest().setAttribute("mobile", mobile);
		
		if(StringUtils.isNotEmpty(mobile) && StringUtils.isNotEmpty(mobileCheck) && StringUtils.isNotEmpty(sessionMobileCheck) && sessionMobileCheck.equals(mobile+"|"+mobileCheck) && StringUtils.isNotEmpty(rand)&& sessionRand.equalsIgnoreCase(rand)){
			
			
			this.getSession().removeAttribute("mobileCheck");
			
			TMember member = (TMember)this.getSession().getAttribute("member");
			
			member.setMobile(mobile);
			
			member.setIsMobileCheck(1);
			
			this.tmembermanager.updateByPrimaryKeySelective(member);
			
			this.getSession().setAttribute("member", member);
			
			this.getRequest().setAttribute("option", true);
			
		}else{
			this.getRequest().setAttribute("option", false);
		}
		
		return "edit_mobile_success";
	}
	
	
	/**
	 * 检查验证码是否正确
	 */
	public void randCheck(){
	
		String rand = this.getRequest().getParameter("rand");
		String sessionRand =(String) this.getSession().getAttribute("rand");
		
		if(StringUtils.isNotEmpty(rand) && StringUtils.isNotEmpty(sessionRand) && rand.equalsIgnoreCase(sessionRand)){
			this.writeObjectToResponse(1, ContentType.application_json);
		}else{
			this.writeObjectToResponse(0, ContentType.application_json);
		}
	}
	
	/**
	 * 修改用户密码
	 */
	public String editPassword(){
		
		return "edit_password";
	}

	
	/**
	 * 保存用户密码
	 */
	public String savePassword(){
		
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		
		String mobileCheck = this.getRequest().getParameter("mobileCheck");
		String password = this.getRequest().getParameter("password");
		String rePassword = this.getRequest().getParameter("rePassword");
		String rand = this.getRequest().getParameter("rand");
		
		String sessionRand = (String)this.getSession().getAttribute("rand");
		
		//TODO 检查验手机验证码是否匹配session处理(刘天灵)
		String sessionMobileCheck = (String)this.getSession().getAttribute("mobileCheck");
		
		if(sessionMobileCheck.equals(sessionMember.getMobile()+"|"+mobileCheck) && password.matches("(?!(?:\\d*$))[A-Za-z0-9]{6,20}")&&password.equals(rePassword)&& StringUtils.isNotEmpty(rand)&& sessionRand.equalsIgnoreCase(rand)){
			tmember.setPassword(CustomDigestUtils.md5Hex(password, tmember));
			this.getRequest().setAttribute("option", true);
		}else{
			this.getRequest().setAttribute("option", false);
		}
		
		
		return "edit_password_success";
	}
	
	/**
	 * 异步获取会员信息
	 */
	public void ajaxMemberInfo() throws Exception{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		TMember selectByPrimaryKey = tmembermanager.selectByPrimaryKey(sessionMember.getId());
		this.getSession().setAttribute("member", selectByPrimaryKey);	
		Map<String,Object> param = new HashMap<String,Object>();
		String homePageConfigId = tsysparametermanager.getKeys("public_service_url");
		param.put("memberId", sessionMember.getId().toString());
		Map result = JsonUtil.getData(param, homePageConfigId+"getJifenOutline");
		if(result.get("statusCode").equals("1")){
			selectByPrimaryKey.setIntegral(selectByPrimaryKey.getIntegral()+(Integer)result.get("jifen"));
		}
		param.put("integral", selectByPrimaryKey.getIntegral());
		param.put("nickName", selectByPrimaryKey.getNickName());
		param.put("realName", selectByPrimaryKey.getRealName());
		
		this.writeObjectToResponse(param, ContentType.application_json);
	}
	
	/**
	 * 转到积分兑换页
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unused", "unchecked", "deprecation" })
	public String pointExchange() throws Exception{
		TMember member = (TMember)this.getSession().getAttribute("member");
		Long memberId = member.getId();
		
		String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		//String publicServiceUrl = "http://192.168.101.181:8080/111_yao_public/sltRouter?method=";
		Map<String,String> publicMap = new HashMap<String,String>();
		publicMap.put("memberId", String.valueOf(memberId));
	    String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"getAllCoupon");
	    Map<String, Object> resultMap = null;
	    //List<Map<String, Object>> resultList = null;
	    //List<String> resultList=new ArrayList<String>();
	    JSONObject jsonObject = JSONObject.fromObject(resultJsonstr);
		if (jsonObject != null) {
			resultMap = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(jsonObject.get("data")),Map.class);
			JSONArray jsonArray = JSONArray.fromObject(resultMap.get("result"));
			resultList = (List<Map<String, Object>>) JSONArray.toCollection(jsonArray, HashMap.class);
		}
	    System.out.println("resultMap:--->"+resultMap);
	    System.out.println("resultList:--->"+resultList);
		return "pointExchange";
	}
	/**
	 * 积分兑换 0积分不足1兑换成功
	 * @throws Exception 
	 * @throws NumberFormatException 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void ajaxExchange() throws NumberFormatException, Exception{
		TMember member = (TMember)this.getSession().getAttribute("member");
		if(StringUtils.isNumeric(this.getRequest().getParameter("couponId"))){
			String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
			//String publicServiceUrl = "http://192.168.101.181:8080/111_yao_public/sltRouter?method=";
			Map<String,String> publicMap = new HashMap<String,String>();
			publicMap.put("memberId", String.valueOf(member.getId()));
			publicMap.put("id", String.valueOf(this.getRequest().getParameter("couponId")));
		    String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"convertCoupon");
		    JSONObject jsonObject = JSONObject.fromObject(resultJsonstr);
		   // Map<String,Object> resultMap =(Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(resultJsonstr),Map.class);
		    if (jsonObject != null) {
				if(jsonObject.get("statusCode").equals("1")){
					this.writeObjectToResponse(1, ContentType.application_json);//兑换成功！
				}else{
					System.out.println("pointMessage:"+ jsonObject.get("message"));
					this.writeObjectToResponse(jsonObject.get("message"), ContentType.application_json);//积分不足的多种情况
				}
			}
		    System.out.println("resultJsonstr:--->"+resultJsonstr);
		}else{
			this.writeObjectToResponse(2, ContentType.application_json);
		}
	}

	public TMemberBaseMessageExtManager getTmemberbasemessageextmanager() {
		return tmemberbasemessageextmanager;
	}

	public void setTmemberbasemessageextmanager(
			TMemberBaseMessageExtManager tmemberbasemessageextmanager) {
		this.tmemberbasemessageextmanager = tmemberbasemessageextmanager;
	}
	
}
