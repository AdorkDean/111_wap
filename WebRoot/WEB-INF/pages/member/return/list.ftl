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
    <title>我的售后</title>
</head>

<body>
<#assign title="我的售后">
<#if pw?exists && pw.result?has_content>
<#include "/static/inc/wap/header.ftl">
<article class="single-box pt45">
    <article class="order-info-box m_top">
        <header><h1>商品信息<a class="minesale_right" href="javascript:void(0)">申请售后中</a></h1></header>
        <ul class="order-product-info">
        	<#list pw.result?if_exists as resc>
            <li>
            	<a href="${base}/member/return!detail.action?id=${resc.id?default('')}">
                <img src="${ui1}${resc.abbreviation_picture?default('')}">
                <div class="order-product">
                    <div class="order-product-unit">
                        <p class="order-product-price"><#if resc.service_type?exists && resc.service_type == 0>退货<#elseif resc.service_type?exists && resc.service_type == 1>换货<#else>退款</#if></p>
                        <p class="order-product-count">x${resc.quantity?default(0)}</p>
                    </div>
                    <div class="order-product-title">
                        <h2>${resc.goods_name?default('')}</h2>
                    </div>
                </div>
                </a>	
            </li>
            </#list>
        </ul>
    </article>
</article>
<#include "/static/inc/wap/footer.ftl">
<#else>
<section class="common-main">
    <#include "/static/inc/wap/header.ftl">
    <article class="order-box">
        <article class="comment_auto">
                <dl>
                    <dt><img src="${base}/web/images/comment.svg"/></dt>
                    <dd>
                    	<p>暂无相关售后</p>
	    			</dd>
                </dl>
        </article>
    </article>
    <div class="footer-bottom">
	<#include "/static/inc/wap/footer.ftl">
	</div>
</section>
</#if>
</body>
</html>
<script type="text/javascript" charset="utf-8">
$(function(){
	<#if pw.pageInfo.pages?exists && pw.pageInfo.pages gt 1>
	var page = 1;
	var totalPage = ${pw.pageInfo.pages?default(0)};
	$(window).scroll(function(){
		if(isLoad(this)){
			if(page < totalPage){
				page += 1;
				$.ajax({
					url: "${base}/member/return!more.action",
					type: "GET",
					data:{
						"rs.p_curPage":page
					},
					cache: false,
					success: function(data){
						$(".order-product-info").append(data);
					}
				});
			}
		}
	});
	</#if>
});
</script>
