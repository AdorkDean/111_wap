
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
<title>查看物流</title>
</head>
<body>
<#assign title="查看物流">
<#include "/static/inc/wap/header.ftl">
<article class="order-box">
	<dl class="logistics-company clearfix">
    	<dt><img src="${base}/web/images/logistics-img.jpg"></dt>
        <dd>
        	<p class="company-title">${orderShipment.logisticsName?default('')}</p>
            <p class="serial-number">运单编号：${orderShipment.logisticsNo?default('')}</p>
            <p class="serial-number">物流状态：
            	<#if orderShipment?exists && orderShipment.state?exists && orderShipment.state == "0">在途 </#if>
            	<#if orderShipment?exists && orderShipment.state?exists && orderShipment.state == "1">揽件 </#if>
            	<#if orderShipment?exists && orderShipment.state?exists && orderShipment.state == "2">疑难</#if>
            	<#if orderShipment?exists && orderShipment.state?exists && orderShipment.state == "3">签收</#if>
            	<#if orderShipment?exists && orderShipment.state?exists && orderShipment.state == "4">退签 </#if>
            	<#if orderShipment?exists && orderShipment.state?exists && orderShipment.state == "5">派件</#if>
            	<#if orderShipment?exists && orderShipment.state?exists && orderShipment.state == "6">退回 </#if>
            	<#if orderShipment?exists && orderShipment.state?exists && orderShipment.state == "7">转投</#if>                                    	
            </p>
        </dd>
    </dl>
    <article class="order-info-box">
        <header><h1>物品信息</h1></header>
        <ul class="order-product-info">
        	<#list orderItems?if_exists as item>
        	<a href="${base}/m/${item.goods_id?default('')}.html"> 
        	<li>
            	<img src="${ui1}${item.abbreviation_picture?default('')}">
                <div class="order-product">
                	<div class="order-product-unit">
                    	<p class="order-product-price">${currency(item.price?default(0),'true')}</p>
                        <p class="order-product-count">x${item.quantity?default(0)}</p>
                    </div>
                	<div class="order-product-title">
                    	<h2>${item.goods_name?default('')}</h2>
                        <p class="order-product-spec">规格:${item.spec?default('')}</p>
                    </div>
                </div>
            </li>
            </a>
            </#list>
        </ul>
        <a href="javascript:void(0)" class="show-more">显示全部</a>
    </article>
    <article class="order-info-box">
        <header><h1>物流跟踪</h1></header>
        <ul class="follow-logistics">
        	<#list result?if_exists as item> 
        	<#if item_index == 0>
        	<li class="first-state">
        	<#else>
        	<li>
        	</#if>
            	<div class="logistics-info">
                    <p class="logistics-state">${item.context?default('')}</p>
                    <p class="logistics-time"><time pubdate="pubdate">${item.ftime?default('')}</time></p>
                </div>
            </li>
            </#list>
        </ul>
    </article>
</article>
<#include "/static/inc/wap/footer.ftl">
</body>
</html>

<script type="text/javascript" src="${base}/web/js/common.js"></script>
