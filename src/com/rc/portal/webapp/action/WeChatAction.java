package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.util.JsonUtils;
import com.rc.commons.util.InfoUtil;
import com.rc.portal.memcache.MemCached;
import com.rc.portal.util.ParametersCommon;
import com.rc.portal.webapp.util.GetJsApiTicket;
import com.rc.portal.webapp.util.WxUtils;

/**
 * 微信自定义分享
 * @author LGP
 * @date 2015年10月26日
 */
public class WeChatAction extends BaseAction
{
	private static final long serialVersionUID = 1L;

	/**TICKET-URL*/
	private String wxTicketUrl = InfoUtil.getInstance().getInfo("config", "ticketurl")+"&sign=";
	
	/**GET-INFO-URL*/
	private String getInfoUrl = InfoUtil.getInstance().getInfo("config", "getinfourl")+"&debug="+InfoUtil.getInstance().getInfo("config", "wxdebug")+"&jsApiList="+InfoUtil.getInstance().getInfo("config", "jsApiList")+"&ticketUseSet=get_jsapi_ticket_wx_config&appIdInt="+InfoUtil.getInstance().getInfo("config", "appIdInt");
	
	/**微信初始化认证*/
	public void wxinit()
	{
		Map<String, Object> result = new HashMap<String, Object>();
		String jsapi_ticket = null;
		try
		{
			if(MemCached.getmcc().get("TICKET") != null)
			{
				jsapi_ticket = String.valueOf(MemCached.getmcc().get("TICKET"));
			}
			else
			{
				jsapi_ticket = GetJsApiTicket.getJsApiTicket();
			}
		} 
		catch (Exception e) 
		{
			jsapi_ticket = GetJsApiTicket.getJsApiTicket();
		}
		String noncestr = WxUtils.getRandomStringByLength(16);
		Long timestamp = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(3, 13));
		String url = InfoUtil.getInstance().getInfo("config", "wxurl")+getRequest().getParameter("signUrl");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("url", url);
		paramMap.put("noncestr", noncestr);
		paramMap.put("timestamp", timestamp);
		paramMap.put("jsapi_ticket", jsapi_ticket);
		//计算共享地址签名
		String paramSortStr = WxUtils.getParamSortStrFromMap(paramMap);
		String signature = WxUtils.getSha1(paramSortStr);
		result.put("timestamp", timestamp);
		result.put("noncestr", noncestr);
		result.put("signature", signature);
		result.put("appid",InfoUtil.getInstance().getInfo("config", "appid"));
		result.put("url", url);
		//打印一些信息
		System.out.println("微信分享签名程序开启！");
		System.out.println("string1::"+paramSortStr);
		System.out.println("url::"+url);
		System.out.println("noncestr::"+noncestr);
		System.out.println("timestamp::"+timestamp);
		System.out.println("signature::"+signature);
		this.writeObjectToResponse(result, ContentType.application_json);
	}
	
	/**
	 * 向远程服务器获取通过HTTP协议获取认证信息
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void getSignInfoFromRemote() throws MalformedURLException, UnsupportedEncodingException, IOException
	{
		Map<String,String> m = new HashMap<String,String>();
		m.put("ticketUseSet", "get_jsapi_ticket_wx_config");
		m.put("appIdInt", InfoUtil.getInstance().getInfo("config", "appIdInt"));
		String mySign = ParametersCommon.getWXSign(m, InfoUtil.getInstance().getInfo("config", "securitycode"));
		String ticket = String.valueOf(JsonUtils.toObject(GetJsApiTicket.getJsonStringByConnection(wxTicketUrl+mySign).toString(), Map.class).get("ticket"));
		String url = java.net.URLDecoder.decode(getRequest().getParameter("signUrl"), "utf-8");
		String reqUrl = getInfoUrl+"&ticket="+ticket+"&sign="+getSign(url,ticket)+"&url="+URLEncoder.encode(url, "utf-8");
		StringBuffer jsonStr = GetJsApiTicket.getJsonStringByConnection(reqUrl);
		System.out.println("This is request url form WS server:"+reqUrl);
		writeObjectToResponse(jsonStr, ContentType.application_json);
	}
	
	/**
	 * 获取认证字符串
	 * @param url     ：认证URL
	 * @param ticket  ：认证ticket
	 * @return        ：认证字符串
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String getSign(String url, String ticket) throws MalformedURLException, IOException
	{
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("action", "wxConfigJs");
		paramMap.put("appIdInt", InfoUtil.getInstance().getInfo("config", "appIdInt"));
		paramMap.put("debug", InfoUtil.getInstance().getInfo("config", "wxdebug"));
		paramMap.put("jsApiList", InfoUtil.getInstance().getInfo("config", "jsApiList"));
		paramMap.put("ticketUseSet", "get_jsapi_ticket_wx_config");
		paramMap.put("ticket", ticket);
		paramMap.put("url", url);
		System.out.println("This is decode before url :"+url);
		return ParametersCommon.getWXSign(paramMap, InfoUtil.getInstance().getInfo("config", "securitycode"));
	}
	
	@Override
	public Object getModel()
	{
		return null;
	}

	@Override
	public void setModel(Object o)
	{
	}
	
}