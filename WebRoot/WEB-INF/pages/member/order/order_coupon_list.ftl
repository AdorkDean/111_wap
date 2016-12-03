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
    <!--上线时请删除-->
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <!--上线时请删除-->
    <title>个人中心</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
	<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
</head>
<body>
<form name="orderForm" id="orderForm" method="post">
      <input id="paymentWayId" name="paymentWayId" type="hidden" value="<#if paymentWayId?exists>${paymentWayId?default('')}</#if>"/>
      <input id="receiverId" name="receiverId" type="hidden" value="<#if receiverId?exists>${receiverId?default('')}</#if>"/>
      <input id="invoiceTitle" name="invoiceTitle" type="hidden" value="<#if invoiceTitle?exists>${invoiceTitle?default('')}</#if>"/>
      <input id="ifInvoice" name="ifInvoice" type="hidden" value="<#if ifInvoice?exists>${ifInvoice?default('')}</#if>"/>
      <input id="invoiceType" name="invoiceType" type="hidden" value="<#if invoiceType?exists>${invoiceType?default('')}</#if>"/>
      <input id="couponCardId" name="couponCardId" type="hidden" value="<#if couponCardId?exists>${couponCardId?default('')}</#if>"/>
      <input id="payPluginId" name="payPluginId" type="hidden" value="<#if payPluginId?exists>${payPluginId?default('')}</#if>"/>
</form>
<header class="header">
    <a href="javascript:couponCardSubmit();" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">优惠券</h2>
    <a href="/" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
<#if couponList?exists>
<article class="member-main">
      <#list couponList as coupon>
	    <div class="add-coupon select" id="${coupon.id?default('')}">
	        <div class="add-coupon-top">
	            <div class="coupon-img"><img src="${base}/web/images/member_center_icon04.png" alt=""/></div>
	            <div class="coupon-txt">
	                <p>${coupon.name?default('')}</p>
	                <b>有效期:${coupon.begin_Date?default('')}--${coupon.end_date?default('')}</b>
	            </div>
	        </div>
	        <div class="pitch-on"><strong class="iconfont">L</strong></div>
	    </div>
    </#list>
    </article>
    <#include "/static/inc/wap/footer.ftl">
    <#else>
    <article class="member-no-net">
	    <dl>
			<dt><img src="${base}/web/images/member-icon03.png"></dt>
			<dd>
	        	<p>您还没有可用的优惠券哟~</p>
	        </dd>
		</dl>
	</article>
	<div class="footer-bottom">
     <#include "/static/inc/wap/footer.ftl">
   </div>
  </#if>
   
</body>
<script type="text/javascript">
$(function(){
    $(".select").click(function(){
        if($(this).hasClass('select-current')){
            $(this).removeClass('select-current');
            $(this).find("strong").html('L');
            $(this).siblings().find("strong").html('M');
            $("#couponCardId").val("");
        }else{
            $(this).addClass('select-current').siblings().removeClass('select-current');
            $(this).find("strong").html('M');
            $(this).siblings().find("strong").html('L');
            $("#couponCardId").val($(this).attr("id"));
            couponCardSubmit();
        }
    })
})
function couponCardSubmit(){
    $("#orderForm").attr("action","${base}/member/order!toOrderAdd.action");
    $("#orderForm").submit();
}
</script>
</html>