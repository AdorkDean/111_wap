package com.rc.portal.interceptor;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.opensymphony.xwork.util.OgnlValueStack;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.util.CookieUtil;
import com.rc.portal.vo.TMember;




/**
 * 会员登录验证
 * @author 刘天灵
 *
 */
public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 56589424541L;

	/** "重定向URL"参数名称 */
	private static final String REDIRECT_URL_PARAMETER_NAME = "redirectUrl";

	/** "会员"属性名称 */
	private static final String MEMBER_ATTRIBUTE_NAME = "member";
	
	private OpenSqlManage opensqlmanage;
	
	public void destroy() {}

	public void init() {}
	
	private String redirectUrl;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	/**
	 * 验证会员是否登录,否则跳转登录页
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		redirectUrl = request.getRequestURL().toString();
		String username = CookieUtil.getCookie(request, "cookusername");
		String cookpassword = CookieUtil.getCookie(request, "cookpassword");
		
//		Map<String, Object> mapkey = new HashMap<String, Object>();
//		mapkey.put("cookpassword", cookpassword);
//		String cookpwd = (String) opensqlmanage.selectObjectByObject(mapkey,"t_member.selectKeyByCookpassword");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", username);
		Map<String, Object> mobilemap = new HashMap<String, Object>();
		mobilemap.put("mobile", username);
		TMember member = (TMember) opensqlmanage.selectObjectByObject(mobilemap,"t_member.ibatorgenerated_selectMemberByMobile");
		if(member==null){
			member=(TMember) opensqlmanage.selectObjectByObject(map,"t_member.ibatorgenerated_selectByUserName");
		}
		if(username!=null&&cookpassword!=null&&member!=null&&cookpassword.equals(member.getPassword())){
			if(cookpassword.equals(member.getPassword())){
				ServletActionContext.getRequest().getSession().setAttribute("member", member);
				return invocation.invoke();
			}else{
				if(redirectUrl != null){
					OgnlValueStack stack = invocation.getStack();
					stack.getContext().put(REDIRECT_URL_PARAMETER_NAME, URLEncoder.encode(redirectUrl, "utf-8"));
				}
				return "login_page";
			}
		}else{
			Object loginUser = ServletActionContext.getRequest().getSession().getAttribute(MEMBER_ATTRIBUTE_NAME);
			if (loginUser == null) {
				
				if(redirectUrl != null){
					OgnlValueStack stack = invocation.getStack();
					stack.getContext().put(REDIRECT_URL_PARAMETER_NAME, URLEncoder.encode(redirectUrl, "utf-8"));
				}
				return "login_page";
			}
		}
		return invocation.invoke();
	}

}
