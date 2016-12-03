<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="yes" name="apple-touch-fullscreen" />
<meta content="fullscreen=yes,preventMove=no" name="ML-Config" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<title>商品收藏</title>
</head>
<body>

<#assign title="商品收藏">
<#if pw?exists && pw.result?has_content>
<#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://img.zdfei.com"/>  
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
<script type="text/javascript" src="${base}/web/js/jquery.fly.min.js"></script>
<header class="header">
    <a href="javascript:void(0)" onclick="history.go(-1)" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">商品收藏</h2>
    <a href="javascript:void(0);" class="top-right-btn"><span id="edit_goods" >编辑</span></a>
</header>
<article class="list-main-box">
    <div class="product-list-all" style="padding-top:0;">
    	<div class="alr-p">
        	<a href="${base}/member/collect!brand.action">
            	<b class="iconfont fr">J</b>
            	<b class="alr-star iconfont fl">D</b>
                	查看已关注的品牌
            </a>
        </div>
        <ul class="product-list-box clearfix">
        	<#list pw.result?if_exists as resc>
            <li>
            	<a href="${base}/m/${resc.relevance_id?default('')}.html" class="list-pro-info">
	                <div class="new-list-pro">
	                 <#if resc.wap_price?default(0) != resc.price?default(0)&&resc.wap_price?default(0)!=0>
	                	<span class="new-discount">${(resc.wap_price?default(1)/resc.price?default(1)*10)?string("#.#")}折<i></i></span>
	                	</#if>
	                    <img src="${ui1}${resc.abbreviation_picture?default('')}">
	                </div>
                    <p class="new-list-pro-title">${resc.goods_name?default('')}</p>
                    <p class="new-list-pro-price">${currency(resc.wap_price?default(0),'true')}</p>
                    <p><b class="new-list-pro-original">${currency(resc.price?default(0),'true')}</b></p>
	              <#if resc.type?default(0)==2||resc.type?default(0)==3>
                    <a href="${base}/m/${resc.relevance_id?default('')}.html"  class="iconfont new-list-pro-into-rx"></a>
                    <#else>
                    <a href="javascript:void(0)" id="${resc.relevance_id?default('')}" class="iconfont new-list-pro-into-cart"></a>
                    </#if>
                    <div class="atten-shade">
                    	<b class="delete" val="${resc.id?default('')}">-</b>
                    </div> 
                </a>
            </li>
            </#list>
        </ul>
    </div>
</article>
<#include "/static/inc/wap/footer.ftl">
<link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript" src="${base}/web/js/wap-cart.js"></script>
<#else>
<section class="common-main">
    <#include "/static/inc/wap/header.ftl">
    <article class="order-box">
        <article class="comment_auto">
                <dl>
                    <dt><img src="${base}/web/images/comment.svg"/></dt>
                    <dd><p>暂无收藏商品</p></dd>
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
					url: "${base}/member/collect!more.action",
					type: "GET",
					data:{
						"rs.p_curPage":page,
					},
					cache: false,
					success: function(data){
						$(".product-list-box").append(data);
					}
				});
			}
		}
	});
	</#if>
	
	$("#edit_goods").click(function(){
		if($(this).html()=="编辑"){
			$(this).html("完成");
			$(".atten-shade").css({display:"block"});
		}else{
			$(this).html("编辑");
			$(".atten-shade").css({display:"none"});
		}
	});
	
	$(".delete").click(function(){
		var def = $(this);
		$.ajax({
			url: "${base}/member/collect!cancel.action",
			type: "GET",
			data:{
				id:def.attr("val"),
			},
			cache: false,
			success: function(data){
				def.parent().parent().remove();
			}
		});
	});
	
});

getCartSum(2);
</script>

<style>
.atten-pro{ position:relative;}
.atten-shade{position:absolute; left:0; top:0; right:10px; bottom:0; background:rgba(0,0,0,.5); border-radius:5px; -webkit-border-radius:5px; z-index:10;display:none;}
.atten-shade b{width:14px; height:14px; background:#ff3333; line-height:14px; color:#fff; border-radius:50%; -webkit-border-radius:50%; position:absolute; z-index:11; right:5px; top:5px; text-align:center;}
</style>
