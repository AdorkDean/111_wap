package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.portal.memcache.MemCached;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.CodeUtil;
import com.rc.portal.util.CookieUtil;
import com.rc.portal.util.NetworkUtil;
import com.rc.portal.vo.TMember;
import com.rc.portal.webapp.util.MD5;

public class FindPasswordAction extends BaseAction{

	private static final long serialVersionUID = 6028685551974978147L;
	private TMemberManager tmembermanager;
	private TMember tmember;
	private OpenSqlManage opensqlmanage;
	private TSysParameterManager tsysparametermanager;
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
	/*
	 * 发送验证码
	 */
	public void validateMobileCode() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String captcha = CodeUtil.getVcode(4);
		String username = this.getRequest().getParameter("username");
		MemCached.getmcc().set(username,captcha,new Date(1000*300));
		map.put("mobile", username);
		//this.getSession().setAttribute(username, captcha);
		Pattern pattern = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		TMember member = (TMember) opensqlmanage.selectObjectByObject(map,"t_member.ibatorgenerated_selectMemberByMobile");
		if (member != null) {
			if (member.getIsMobileCheck() != 1) {
				flag = 2;// 该用户未绑定手机号
			} else if (!pattern.matcher(member.getMobile()).matches()) {
				flag = 3;// 手机格式不正确
			}else{
				Map<String, String> smsMap = new HashMap<String, String>();
				smsMap.put("mobiles", member.getMobile());
				smsMap.put("smsContent", "您的111医药馆验证码:"+captcha+"。影视明星何政军先生推荐的中国好药房@111医药馆！");
				String YAO_GATEWAY_URL = tsysparametermanager.getKeys("sms");
				String buildRequestBySMS = ClientSubmit.buildRequestBySMS(smsMap,YAO_GATEWAY_URL);
				System.out.println(buildRequestBySMS);
				this.getSession().setAttribute("smsSuccess", "smsSuccess");
				flag=0;
			}
		} else {
			flag = 4;//用户名不存在
		}
		out.print(flag);
		out.close();
	}

	/*
	 * 跳转到输入短信验证码页面
	 */
	public String nextStep() {
		String username = this.getRequest().getParameter("username");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", username);
		try {
			tmember = (TMember) opensqlmanage.selectObjectByObject(map,
					"t_member.ibatorgenerated_selectMemberByMobile");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findpasswordtwo";
	}

	/*
	 * 重置密码
	 */
	public void resetPassword() throws Exception {
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		String password = this.getRequest().getParameter("password");
		String inputCode = this.getRequest().getParameter("mobile_code");
		String username = this.getRequest().getParameter("username");
		String phoneCode =(String) MemCached.getmcc().get(username);
		//String phoneCode = (String) this.getSession().getAttribute(username);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", username);
		if (!inputCode.equalsIgnoreCase(phoneCode)) {//phoneCode
			flag = 1;// 短信验证码不对
		}else{
			try {
				tmember = (TMember) opensqlmanage.selectObjectByObject(map,
						"t_member.ibatorgenerated_selectMemberByMobile");
				tmember.setPassword(MD5.MD5("111"+password+"yao"));
				tmember.setLastDate(new Date());
				tmember.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
				tmembermanager.updateByPrimaryKeySelective(tmember);
				this.getSession().setAttribute("member", tmember);
				CookieUtil.addCookie(getRequest(), getResponse(), "username", username, 7*24*60*60, "/", "", false);
				flag = 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		out.print(flag);
		out.close();
	}


	/*
	 * 重新获取验证码
	 */
	public void againGetMobileCode() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String captcha = CodeUtil.getVcode(4);
		String username = this.getRequest().getParameter("username");
		 MemCached.getmcc().set(username,captcha,new Date(1000*300));
		map.put("mobile", username);
		//this.getSession().setAttribute(username, captcha);
		Pattern pattern = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		TMember member = (TMember) opensqlmanage.selectObjectByObject(map,
				"t_member.ibatorgenerated_selectMemberByMobile");
		if (member != null && username.equalsIgnoreCase(member.getUserName())) {
			if (member.getIsMobileCheck() != 1) {
				flag = 2;// 该用户未绑定手机号
			} else if (!pattern.matcher(member.getMobile()).matches()) {
				flag = 3;// 手机格式不正确
			}else{
				Map<String, String> smsMap = new HashMap<String, String>();
				smsMap.put("mobiles", username);
				smsMap.put("smsContent", "您的111医药馆验证码:"+captcha+"。影视明星何政军先生推荐的中国好药房@111医药馆！");
				String YAO_GATEWAY_URL = tsysparametermanager.getKeys("sms");
				String buildRequestBySMS = ClientSubmit.buildRequestBySMS(smsMap,YAO_GATEWAY_URL);
				System.out.println(buildRequestBySMS);
				flag=0;
			}
		} else {
			flag = 4;
		}
		out.print(flag);
		out.close();
	}
	/*
	 * 转到找回密码页面
	 */
	public String index() {

		return "findpasswordone";
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
