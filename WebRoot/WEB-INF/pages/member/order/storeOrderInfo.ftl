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
<title>门店订单详情</title>
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />  

</head>

<body>
<header class="header" style="border-bottom:0;">
    <a href="javascript:history.go(-1);" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">订单详情</h2>
</header>
<article class="order-box pb70">
    <article class="order-info-box">
        <header><h1>订单信息</h1></header>
        <ul class="order-info-text">
        	<li>
            	<span>订单编号</span>
                <p><#if order?exists && order.orderSn?exists >
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
				   </time>
				</p>
            </li>
            <li>
            	<span>订单状态</span>
                <p><span class="red-state">
					<#if order?exists && order.orderStatus?exists>
               		 ${order.orderStatus?default('')}
				    </#if>
				   </span>
			   </p>
            </li>
        </ul>
    </article>
    <article class="order-info-box">
        <header><h1>商品信息</h1></header>
        <ul class="order-product-info-md"> 
        <#if goodsList?exists >
           <#list goodsList?if_exists as item>
        	<li>
                <div class="order-product">
                	<div class="order-product-unit">
                    	<p class="order-product-price">￥${item.price?default('')}</p>
                        <p class="order-product-count">x${item.orderItemQty?default('')}</p>
                    </div>
                	<div class="order-product-title">
                    	<h2>${item.shortName?default('')}</h2>
                        <p class="order-product-spec">${item.spec?default('')}</p>
                    </div>
                </div>
            </li>
            </#list> 
         </#if> 
        </ul>
    </article>
    <article class="order-info-box">
        <header><h1>订单金额</h1></header>
        <ul class="order-info-text order-info-price" style="padding:0;">
            <li style="border:none;margin:0">
            	<span>应付金额</span>
                <p class="final-price">￥
                   <#if order?exists && order.orderAmount ?exists>
               		 ${order.orderAmount?default('')}
				   </#if>
				</p>
            </li>
        </ul>
    </article>
</article>
<div class="bottom-btn">
<a href="${base}/storeOrder/storeOrder!comment.action?orderSn=${orderSn?default('')}" class="common-btn orange-btn">评 论</a> 
</div>
</body>
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
</html>
