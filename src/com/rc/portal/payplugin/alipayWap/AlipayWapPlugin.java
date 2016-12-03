package com.rc.portal.payplugin.alipayWap;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rc.commons.util.InfoUtil;
import com.rc.portal.alipay.config.AlipayConfig;
import com.rc.portal.alipay.util.AlipaySubmit;
import com.rc.portal.alipay.util.UtilDate;
import com.rc.portal.vo.TOrder;

/**
 * Plugin - 支付宝WAP(即时到账)
 * 
 * 
 */
public class AlipayWapPlugin{
	public String getName() {
		return "支付宝WAP(即时到帐)";
	}

	public String getRequestUrl() {
		return "http://wappaygw.alipay.com/service/rest.htm?_input_charset=utf-8";
	}
	public String getRequestCharset() {
		return "UTF-8";
	}
	public Map<String, String> getParameterMap(TOrder order, String description, HttpServletRequest request) {
		try{
			String ALIPAY_GATEWAY_NEW = InfoUtil.getInstance().getInfo("alipay","alipay.ALIPAY_GATEWAY_NEW");
			//返回格式
			String format = InfoUtil.getInstance().getInfo("alipay","alipay.format");
			String v = InfoUtil.getInstance().getInfo("alipay","alipay.v");
			//请求号
			String req_id = UtilDate.getOrderNum();
			//必填，须保证每次请求都是唯一
			//服务器异步通知页面路径
			String notify_url = InfoUtil.getInstance().getInfo("config","pay.payServiceUri")+InfoUtil.getInstance().getInfo("config","alipayWapNotifyUri");
            //页面跳转同步通知页面路径
			String call_back_url =InfoUtil.getInstance().getInfo("config","pay_return_url");
			//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
			//操作中断返回地址
			String merchant_url = InfoUtil.getInstance().getInfo("alipay","alipay.merchant_url");
			//用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数
			//卖家支付宝帐户
			String seller_email =AlipayConfig.seller_email;
			//必填
			//商户订单号
			String out_trade_no = order.getOrderSn();
			//商户网站订单系统中唯一订单号，必填
			String orderName =description;
			if(orderName.length()>100){
				orderName = orderName.substring(0,100);
			}
			//订单名称
			String subject = orderName;
			//付款金额
			String total_fee =order.getPayableAmount().setScale(2).toString();
			//请求业务参数详细
			String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url + "</call_back_url><seller_account_name>" + seller_email + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
			//必填
			//////////////////////////////////////////////////////////////////////////////////
			//把请求参数打包成数组
			Map<String, String> sParaTempToken = new HashMap<String, String>();
			sParaTempToken.put("service", "alipay.wap.trade.create.direct");
			sParaTempToken.put("partner", AlipayConfig.partner);
			sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
			sParaTempToken.put("sec_id", AlipayConfig.sign_type);
			sParaTempToken.put("format", format);
			sParaTempToken.put("v", v);
			sParaTempToken.put("req_id", req_id);
			sParaTempToken.put("req_data", req_dataToken);
			//建立请求
			String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW,"", "",sParaTempToken);
			//URLDECODE返回的信息
			sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,AlipayConfig.input_charset);
			//获取token
			String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
			//业务详细
			String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
			//把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("sec_id", AlipayConfig.sign_type);
			sParaTemp.put("format", format);
			sParaTemp.put("v", v);
			sParaTemp.put("req_data", req_data);
			Map<String, String> sPara = AlipaySubmit.buildRequestPara(sParaTemp);
			return sPara;
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public boolean verifyNotify(TOrder order, HttpServletRequest request) {
		return false;
	}
	public String getRequestMethod() {
		return "GET";
	}

}
