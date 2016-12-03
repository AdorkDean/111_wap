package com.rc.portal.payplugin.wzfWapPlugin;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rc.portal.util.InfoUtil;
import com.rc.portal.util.NetworkUtil;
import com.rc.portal.util.Signature;
import com.rc.portal.vo.TOrder;

/**
 * 微信wap支付
 * @author user3
 *
 */
public class WzfWapPlugin {
	public String getName() {
		return "微信Wap支付";
	}

	public String getRequestUrl() {
		//正式
		return "http://weixin.111yao.com/wd2728cc386/goWxJsPay.do";
		//测试
//		return "http://testweixin.111yao.com/wd2728cc386/goWxJsPay.do";
	}

	public String getRequestMethod() {
		return "POST";
	}

	public String getRequestCharset() {
		return "UTF-8";
	}
	
	public Map<String, String> getParameterMap(TOrder order, String description, HttpServletRequest request) throws UnsupportedEncodingException {
			Map<String,String> wzfMap = new HashMap<String,String>();
			String body =description;
			if(body.length() > 10){
				body = body.substring(0,10);
			}
		    String total_fee = order.getPayableAmount().setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(0).toString();
		    wzfMap.put("input_charset", "UTF-8");
		    wzfMap.put("body",body);    //商品描述  商品或支付单简要描述  (必填)
		    wzfMap.put("detail",body);    //商品详情 商品名称明细列表
		    wzfMap.put("out_trade_no",order.getOrderSn());    //商户订单号  商户系统内部的订单号 (必填)
		    wzfMap.put("fee_type","CNY");    //货币类型   CNY
		    wzfMap.put("total_fee",total_fee);    //总金额  (必填)
//		    wzfMap.put("total_fee","1");//上线时 要修改
		    wzfMap.put("spbill_create_ip",NetworkUtil.getIpAddress(request));    //终端IP  APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。  (必填)
		    wzfMap.put("time_start","");    //交易起始时间  订单生成时间，格式为yyyyMMddHHmms
		    wzfMap.put("time_expire","");    //交易结束时间   订单失效时间，格式为yyyyMMddHHmmss
		    wzfMap.put("goods_tag","");    //商品标记   商品标记，代金券或立减优惠功能的参数，说明详见 WXG
		    wzfMap.put("notify_url",InfoUtil.getInstance().getInfo("config","pay.payServiceUri")+InfoUtil.getInstance().getInfo("config", "wxPayNotifyUrl") );    //通知地址  接收微信支付异步通知回调地址   (必填)
		    wzfMap.put("pay_success_url",InfoUtil.getInstance().getInfo("config","pay_return_url")+"?order_no="+order.getOrderSn());
		    wzfMap.put("pay_fail_url",InfoUtil.getInstance().getInfo("config","pay_return_url")+"?order_no="+order.getOrderSn());
		    //		    wzfMap.put("trade_type","JSAPI");    //交易类型  取值如下：JSAPI，NATIVE，APP，WAP  (必填)
		    wzfMap.put("product_id",String.valueOf(order.getId()) );    //商品ID  rade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
			String sign = Signature.getWXSign(wzfMap, InfoUtil.getInstance().getInfo("config", "wxPaymentPk"));//Signature.getSign(wzfMap);
			wzfMap.put("sign", sign); // 签名 (必填)
			wzfMap.put("body", URLEncoder.encode(body,"UTF-8")); // 签名 (必填)
			wzfMap.put("detail", URLEncoder.encode(body,"UTF-8")); // 签名 (必填)
//			wzfMap.put("notify_url", URLEncoder.encode(InfoUtil.getInstance().getInfo("config","pay.payServiceUri")+InfoUtil.getInstance().getInfo("config", "wxPayNotifyUrl"),"UTF-8")); // 签名 (必填)
//			wzfMap.put("pay_success_url",URLEncoder.encode(InfoUtil.getInstance().getInfo("config","pay_return_url")+"?order_no="+order.getOrderSn(),"UTF-8"));
//		    wzfMap.put("pay_fail_url",URLEncoder.encode(InfoUtil.getInstance().getInfo("config","pay_return_url")+"?order_no="+order.getOrderSn(),"UTF-8"));
			return wzfMap;
	}
	
}
