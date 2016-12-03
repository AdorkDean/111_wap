
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
    <title>退换货订单详情(填写物流信息)</title>
</head>

<body>
<#assign title="我的售后">
<#include "/static/inc/wap/header.ftl">
<article class="order-box pt45">
    <article class="order-info-box">
        <header><h1>订单信息</h1></header>
        <ul class="order-info-text">
            <li>
                <span>订单编号</span>
                <p>${order.orderSn?default('')}</p>
            </li>
            <li>
                <span>下单时间</span>
                <p><time pubdate="pubdate"><#if order.createDate?has_content>${order.createDate?string('yyyy-MM-dd hh:mm:ss')}</#if></time></p>
            </li>
            <li>
                <span>支付方式</span>
                <p><#if orderPaymentWay?has_content>${orderPaymentWay.name?default('')}</#if></p>
            </li>
            <li>
                <span>订单状态</span>
                <p>
                	<#if returnItemMap.order_status?exists && returnItemMap.order_status == 0>
                		未处理 
                	<#elseif returnItemMap.order_status?exists && returnItemMap.order_status == 1>
                		已通过
                	<#elseif returnItemMap.order_status?exists && returnItemMap.order_status == 2>
                		未通过
                	<#elseif returnItemMap.order_status?exists && returnItemMap.order_status == 3>
                		验货通过
                	<#elseif returnItemMap.order_status?exists && returnItemMap.order_status == 4>
                		验货未通过 
                	<#elseif returnItemMap.order_status?exists && returnItemMap.order_status == 5>
                		退款中
                	<#elseif returnItemMap.order_status?exists && returnItemMap.order_status == 6>
                		处理中 
                	<#elseif returnItemMap.order_status?exists && returnItemMap.order_status == 7>
                		验货中
                	<#elseif returnItemMap.order_status?exists && returnItemMap.order_status == 8>
                		换货中
                	<#elseif returnItemMap.order_status?exists && returnItemMap.order_status == 9>
                		结束
                	<#else>
                		未处理 	
                	</#if>
                </p>
            </li>
            <li>
                <span>退货编号</span>
                <p>${returnItemMap.order_sn?default('')}</p>
            </li>
            <li>
                <span>问题描述</span>
                <p>${returnItemMap.refund_describe?default('')}</p>
            </li>
            <li>
                <span>提交时间</span>
                <p><time pubdate="pubdate"><#if returnItemMap.create_time?has_content>${returnItemMap.create_time?string('yyyy-MM-dd hh:mm:ss')}</#if></time></time></p>
            </li>
            <#if returnItemMap.refund_remark?exists && returnItemMap.refund_remark != "">
            <li>
                <span><b style="visibility: hidden;">备注</b>备注</span>
                <p>${returnItemMap.refund_remark?default('')}</p>
            </li>
            </#if>
            <#if returnItemMap.order_status?exists && returnItemMap.order_status == 1 && returnItemMap.service_type?exists && (returnItemMap.service_type == 1 || returnItemMap.service_type == 0)>
            <li>
                <span>物流信息</span>
                <a href="${base}/member/return!logistics.action?id=${returnItemMap.return_id?default('')}"><p class="order-info-warning">填写物流信息</p></a>
            </li>
            </#if>
        </ul>
    </article>
    <article class="order-info-box">
        <header><h1>商品信息</h1></header>
        <ul class="order-product-info">
        	<a href="${base}/m/${returnItemMap.goods_id?default('')}.html">
            <li>
                <img src="${ui1}${returnItemMap.abbreviation_picture?default('')}">
                <div class="order-product">
                    <div class="order-product-unit">
                        <p class="order-product-price"><#if returnItemMap.service_type?exists && returnItemMap.service_type != 1>${currency(returnItemMap.product_amount?default(0),'true')}</#if></p>
                        <p class="order-product-count">x${returnItemMap.quantity?default(0)}</p>
                    </div>
                    <div class="order-product-title">
                        <h2>${returnItemMap.goods_name?default(0)}</h2>
                        <p class="order-product-spec">规格:${returnItemMap.spec?default('')}</p>
                    </div>
                </div>
            </li>
            </a>
        </ul>
    </article>
    <article class="order-info-box">
        <header><h1>收货信息</h1></header>
        <ul class="order-info-text">
            <li>
            	<span>收货人</span>
                <p>${order.receiver?default('')}</p>
            </li>
            <li>
            	<span>手机号码</span>
                <p>${order.phone?default('')}</p>
            </li>
            <li>
            	<span>收货地址</span>
                <p>${order.areaName?default('')}${order.detailedAddress?default('')}</p>
            </li>
        </ul>
    </article>
    <article class="order-info-box">
        <header><h1>订单金额</h1></header>
        <ul class="order-info-text order-info-price">
            <li>
        	<#assign itemTotal=0>
        	<#list orderItems as item>
        		<#assign itemTotal = item.price * item.quantity + itemTotal>
            </#list>
            	<span>商品总金额</span>
                <p>${currency(itemTotal,'true')}</p>
            </li>
            <li>
            	<span>运费</span>
                <p>${currency(order.shippingFee?default(0),'true')}</p>
            </li>
            <li>
            	<span>应付金额</span>
                <p class="final-price">${currency(order.payableAmount?default(0),'true')}</p>
            </li>
        </ul>
    </article>
</article>
<#include "/static/inc/wap/footer.ftl">
</body>
</html>