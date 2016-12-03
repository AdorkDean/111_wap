<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--允许全屏-->
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="fullscreen=yes,preventMove=no" name="ML-Config">
    <!--缩放比例-->
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="format-detection" content="email=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <title>支付</title>
     <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
</head>
<body id="phone">
<header class="header">
    <a href="${base}/member/orderList!getOrderList.action?tag=pay" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">支付</h2>
    <a href="${base}/member/orderList!getOrderList.action?tag=pay" class="top-right-btn" style="width:60px;"><span style="width:60px;">订单中心</span></a>
</header>
<article class="pay-wap-page">
    <div class="pay-way-money">
        <span>待支付金额</span>
        <label for="">¥<#if order?exists>${currency(order.payableAmount?default(''))}</#if></label>
    </div>
    <ul class="pay-info-box" >
        <li id="alipayWapPlugin">
            <a href="#" onclick="orderpay('alipayWapPlugin')">
                <img src="${base}/web/images/single_ico1.png">
                <span>支付宝支付</span>
            </a>
            <b class="new-s-icon-right"></b>
        </li>
        <li id="payWapYktPlugin" >
            <a href="#" onclick="orderpay('payWapYktPlugin')">
                <img src="${base}/web/images/ykt_icon.png">
                <span>医卡通</span>
            </a>
            <b class="new-s-icon-right"></b>
        </li>
        <li id="wzfWapPlugin" style="display:none;" >
            <a href="#" onclick="orderpay('wzfWapPlugin')">
               <img src="${base}/web/images/single_ico2.png">
                <span>微信支付</span>
            </a>
            <b class="new-s-icon-right"></b>
        </li>
    </ul>
</article>
<form name="orderPayForm" id="orderPayForm" method="post">
      <input id="orderId" name="orderId" type="hidden" value="<#if order?exists>${order.id?default('')}</#if>"/>
      <input id="paymentPluginId" name="paymentPluginId" type="hidden" value=""/>
      <input id="paymentMethodId" name="paymentMethodId" type="hidden" value="1"/>
</form>
</body>
<script type="text/javascript">
$().ready(function() {
	function detectWeixinApi(callback){
	    if(typeof window.WeixinJSBridge == 'undefined' || typeof window.WeixinJSBridge.invoke == 'undefined'){
	        setTimeout(function(){
	            detectWeixinApi(callback);
	        },200);
	    }else{
	        callback();
	    }
	}
	detectWeixinApi(function(){
	  	$("#wzfWapPlugin").show();
	  	$("#alipayWapPlugin").hide();
	});
});
//支付
function orderpay(paymentPluginId){
    $("#paymentPluginId").val(paymentPluginId);
    $("#orderPayForm").attr("action","${base}/member/order!payOrder.action");
	$("#orderPayForm").submit();
}

</script>

</html>

