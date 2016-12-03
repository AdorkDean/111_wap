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
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.util.CookieUtil;
import com.rc.portal.vo.TMember;

/**
 * 领秀登录拦截器
 * @author cpf
 *
 */
public class leaderLoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 56589424541L;

	/** "重定向URL"参数名称 */
	private static final String REDIRECT_URL_PARAMETER_NAME = "redirectUrl";

	/** "领秀"属性名称 */
	private static final String LEADER_ATTRIBUTE_NAME = "leader";
	
	/** "会员"属性名称 */
	private static final String MEMBER_ATTRIBUTE_NAME = "member";
	private OpenSqlManage opensqlmanage;
	
	public void destroy() {}

	public void init() {}
	
	private String redirectUrl;
	
	private TLeaderManager tleadermanager;

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

	public TLeaderManager getTleadermanager() {
		return tleadermanager;
	}

	public void setTleadermanager(TLeaderManager tleadermanager) {
		this.tleadermanager = tleadermanager;
	}

	/**
	 * 会员是否登录
	 */
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		redirectUrl = request.getRequestURL().toString();
		String username = CookieUtil.getCookie(request, "cookusername");
		String cookpassword = CookieUtil.getCookie(request, "cookpassword");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", username);
		Map<String, Object> mobilemap = new HashMap<String, Object>();
		mobilemap.put("mobile", username);
		TMember member = (TMember) opensqlmanage.selectObjectByObject(map,"t_member.ibatorgenerated_selectByUserName");
		if(member==null){
			member=(TMember) opensqlmanage.selectObjectByObject(mobilemap,"t_member.ibatorgenerated_selectMemberByMobile");
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
				return "leader_login";
			}
		}else{
			Object loginUser = ServletActionContext.getRequest().getSession().getAttribute(MEMBER_ATTRIBUTE_NAME);
			if (loginUser == null) {
				
				if(redirectUrl != null){
					OgnlValueStack stack = invocation.getStack();
					stack.getContext().put(REDIRECT_URL_PARAMETER_NAME, URLEncoder.encode(redirectUrl, "utf-8"));
				}
				return "leader_login";
			}
		}
		return invocation.invoke();
	}

}