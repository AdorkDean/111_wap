package com.rc.portal.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TMember;

/**
 * 领秀拦截器
 * @author 刘天灵
 *
 */
public class LeaderInterceptor implements Interceptor {

	private static final long serialVersionUID = 56589424541L;

	/** "重定向URL"参数名称 */
	private static final String REDIRECT_URL_PARAMETER_NAME = "redirectUrl";

	/** "会员"属性名称 */
	private static final String LEADER_ATTRIBUTE_NAME = "leader";
	
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
	 * 会员是否为领秀拦截,如果不是领秀跳转到领秀注册
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		redirectUrl = request.getRequestURL().toString();
		
		//排除健康领秀需要排除拦截的方法
		if(!(redirectUrl.contains("/leader!shareLeader.action")||redirectUrl.contains("/leader!shareLeaderHtml.action"))){
			//健康领秀,会员登录之后取健康领秀的值存session
			Object leader = ServletActionContext.getRequest().getSession().getAttribute(LEADER_ATTRIBUTE_NAME);
			if(leader == null){
				TMember member = (TMember)ServletActionContext.getRequest().getSession().getAttribute("member");
				
				TLeaderExample example = new TLeaderExample();
				
				example.createCriteria().andMemberIdEqualTo(member.getId());
				
				List selectByExample = tleadermanager.selectByExample(example);
				
				if(selectByExample!=null && selectByExample.size() >0){					
					ServletActionContext.getRequest().getSession().setAttribute("leader", opensqlmanage.selectObjectByObject(member.getId(), "t_leader.select_leader_member_and_count"));
				}else{
					return "leader_register";
				}
			}
		}
			
		return invocation.invoke();
	}

}