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
    <a href="javascript:receiverSubmit();" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">地址管理</h2>
    <a href="/" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
   <#if receiverList?exists>
   <article class="member-main">
      <#list receiverList as receiver>
	    <div class="address-details select <#if receiver.isDefault==1>select-current</#if>" id="${receiver.id?default('')}Div">
	        <div class="ad-main" id="${receiver.id?default('')}">
	            <p class="ad-txt"><span>${receiver.receiver?default('')}</span><span>${receiver.mobile?default('')}</span></p>
	            <p class="ad-txt">${receiver.area?default('')}${receiver.address?default('')}</p>
	        </div>
	        <div class="ad-icon" onclick="toReceiverSubmit(${receiver.id?default('')})" style="<#if receiver.isDefault==1>width:77px;height:50px<#else>width:50px;height:50px</#if>">
	            <i class="iconfont">M</i>
	            <a href="javascript:void(0)" class="iconfont">P</a>
	        </div>
	    </div>
      </#list>
      </article>
        <div class="follow-btn"><a href="javascript:toReceiverSubmit('')">添加新的收货地址</a></div>
     <#include "/static/inc/wap/footer.ftl">
    <#else>
     <article class="member-no-net">
     <dl>
		<dt><img src="${base}/web/images/member-icon03.png"></dt>
		<dd>
        	<p>您还没有收货地址哟~</p>
            <div class="follow-btn"><a href="javascript:toReceiverSubmit('')">添加收货地址</a></div>
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
    $(".ad-main").click(function(){
        var id = $(this).attr("id");
        //if($("#"+id+"Div").hasClass('select-current')){
        //    $("#"+id+"Div").removeClass('select-current');
       //     $("#"+id+"Div").find("strong").html('L');
       //     $("#"+id+"Div").siblings().find("strong").html('M');
       //     $("#receiverId").val("");
       // }else{
            $("#"+id+"Div").addClass('select-current').siblings().removeClass('select-current');
            $("#"+id+"Div").find("strong").html('M');
            $("#"+id+"Div").siblings().find("strong").html('L');
            $("#receiverId").val($(this).attr("id"));
            receiverSubmit();
       // }
    })
})
function receiverSubmit(){
    $("#orderForm").attr("action","${base}/member/order!toOrderAdd.action");
    $("#orderForm").submit();
}

function toReceiverSubmit(id){
    $("#orderForm").attr("action","${base}/member/order!toReceiver.action?id="+id);
    $("#orderForm").submit();
}

</script>
</html>