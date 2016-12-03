package com.rc.portal.webapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;

import net.sf.json.JSONObject;

import com.rc.commons.util.InfoUtil;
import com.rc.portal.memcache.MemCached;

/**
 * 向微信服务器获取ACCESS_TOKEN&TICKET
 * @author LGP
 * @date 2015年10月26日
 */
public class GetJsApiTicket 
{
	private static final String	JSAPI_TICKET_URL_0 = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";
	private static final String	JSAPI_TICKET_URL_1 = "&type=jsapi";
	private static final String	JSAPI_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+InfoUtil.getInstance().getInfo("config", "appid")+"&secret="+InfoUtil.getInstance().getInfo("config", "secret");
	
	/**
	 * 通过HTTP协议获取获取ACCESS_TOKEN
	 * @return ACCESS_TOKEN
	 */
	public static String getJsApiAccessToken() 
	{
		String access_token = null;
		try 
		{
			StringBuffer sTotalString = getJsonStringByConnection(JSAPI_ACCESS_TOKEN_URL);
			JSONObject jsonObject = JSONObject.fromObject(sTotalString.toString());
			access_token = jsonObject.getString("access_token");
		} 
		catch (Exception e) 
		{
			System.out.println(e.getStackTrace());
		}
		return access_token;
	}
	
	
	/**
	 * 通过HTTP协议获取获取TICKET
	 * @return
	 */
	public static String getJsApiTicket() 
	{
		String jsAPITicktUrl = JSAPI_TICKET_URL_0 + getJsApiAccessToken() + JSAPI_TICKET_URL_1;
		String jsAPITick = null;
		try 
		{
			StringBuffer sTotalString = getJsonStringByConnection(jsAPITicktUrl);
			JSONObject jsonObject = JSONObject.fromObject(sTotalString.toString());
			jsAPITick = jsonObject.getString("ticket");
			//把TICKET放入缓存
			MemCached.getmcc().set("TICKET",jsAPITick,new Date(1000*3600));
		} 
		catch (Exception e) 
		{
			System.out.println(e.getStackTrace());
		}
		return jsAPITick;
	}
	
	/**
	 * 通过HTTP协议向第三方请求数据
	 * @param urlString
	 * @return 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static StringBuffer getJsonStringByConnection(String urlString) throws MalformedURLException, IOException
	{
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		InputStream urlStream = connection.getInputStream();
		connection.setConnectTimeout(2000);
		connection.setReadTimeout(5000);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream,Charset.forName("utf-8")));
		StringBuffer sTotalString = new StringBuffer();
		String sCurrentLine = "";
		while((sCurrentLine = bufferedReader.readLine()) != null) 
		{
			sTotalString.append(sCurrentLine);
		}
		bufferedReader.close();
		connection.disconnect();
		return sTotalString;
	}
	
}

