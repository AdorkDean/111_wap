package com.rc.portal.payplugin.payWapYkt;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rc.commons.util.InfoUtil;
import com.rc.portal.vo.TOrder;
/**
 * Plugin 医卡通
 * 
 * @author Bing King
 * 
 */
public class PayWapYktPlugin {

	public String getName() {
		return "医卡通Wap";
	}

	public String getRequestUrl() {
//		return "https://www.ebaolife.com/payment/pay.php";  旧网址
		return "https://pay.ebaolife.com/pay.php"; 
	}

	public String getRequestMethod() {
		return "POST";
	}

	public String getRequestCharset() {
		return "UTF-8";
	}

	public Map<String, String> getParameterMap(TOrder order, String description, HttpServletRequest request) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		String un = "bjybysyyysystem";//partner
	    parameterMap.put("un", un);
	    String pw = new StringBuffer(un).append("123456").append("111yao").toString();//pluginConfig.getAttribute("key")
		pw = PlusUtils.getMD5Str(pw);
		parameterMap.put("pw", pw);
		parameterMap.put("storeName", "111医药馆(德生堂旗下网上医药馆)");
		parameterMap.put("operator", "111yao");
		parameterMap.put("serialNo", order.getOrderSn());
		parameterMap.put("callbackURL", InfoUtil.getInstance().getInfo("config","pay.payServiceUri")+InfoUtil.getInstance().getInfo("config","yktNotifyUri"));//
	    parameterMap.put("redirectURL", InfoUtil.getInstance().getInfo("config","pay_return_url"));//医卡通扣款操作完成后，跳转到此URL
	    parameterMap.put("t", "mobile");
	    parameterMap.put("identifyCode", "857566");
	    parameterMap.put("recipient", order.getReceiver()); //收件人姓名，不可为空
	    parameterMap.put("phone", order.getPhone());// 收件人手机 , 不可为空
	    parameterMap.put("address", order.getDetailedAddress().length()>32?order.getDetailedAddress().substring(0, 32):order.getDetailedAddress());//收件人地址, 不可为空, 地址不包含省市区信息
	    parameterMap.put("district",order.getAreaName().replaceAll("-"," "));//收件人街区 “xx省 xx市 xx区”, 不可为空, 空格分离
		return parameterMap;
	}



}
