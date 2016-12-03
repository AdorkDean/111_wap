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
    <title>评论</title>
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />  

</head>

<body>
<header class="header" style="border-bottom:0;">
    <a href="javascript:history.go(-1);" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">订单评论</h2>
</header>
<article class="order-box">
	<article class="order-info-box">
        <header><h1>订单信息</h1></header>
        <ul class="order-info-text">
        	<li>
            	<span>订单编号</span>
                <p>
                   <#if order?exists && order.orderSn?exists >
               		 ${order.orderSn?default('')}
               		</#if> 
				</p>
            </li>
            <li>
            	<span>下单时间</span>
                <p><time pubdate="pubdate">
                    <#if order?exists && order.accdate?exists>
               		 ${order.accdate?default('')}
               		 </#if>
				   </time></p>
            </li>
            <li>
            	<span>订单状态</span>
                <p><span class="red-state">
                	<#if order?exists && order.orderStatus?exists>
               		 ${order.orderStatus?default('')}
				    </#if>
				   </span></p>
            </li>
        </ul>
    </article>
    
    <div class="common-text">
    	<textarea placeholder="宝贝很好哦，极力向大家推荐！！"  maxlength="100" id="content" onpropertychange="checkLength(this,100);" oninput="checkLength(this,100);"></textarea>
    </div>
    <input type="hidden" id="orderSn" value="${orderSn?default('')}">
    <!--<p class="comment-tips">发表评论赢取积分哟~</p>-->
</article>
<div class="bottom-btn">
	<a href="javascript:void(0)" class="common-btn orange-btn" onclick="sub()" id="btn">提交评论</a>
</div>
</body>

<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript">


 function sub(){
    var orderSn = "${orderSn?default('')}";
    var content = $("#content").val();
    $("#btn").attr("disabled", true);
    if($.trim(content)==""){
      	content = "宝贝很好哦，极力向大家推荐！！";
    }
    $.post("${base}/storeOrder/storeOrder!storeOrderComment.action", {orderSn:orderSn,content:content,random:Math.random()}, function(data){
          alert(data);
          window.location.href="${base}/member/orderList!getOrderList.action"; 
    });
 }
 
function checkLength(obj,maxlength){
    if(obj.value.length > maxlength){
        obj.value = obj.value.substring(0,maxlength);
    }
}

</script>
</html>
