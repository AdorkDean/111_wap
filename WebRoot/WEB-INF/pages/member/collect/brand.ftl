
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
    <title>关注品牌</title>
</head>
<body>
<#assign title="关注品牌">
<#if list?exists && list?has_content>
<section class="common-main">
	
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
	<header class="header">
	    <a href="javascript:void(0)" onclick="history.go(-1)" class="iconfont top-left-btn">B</a>
	    <h2 class="header-title">商品收藏</h2>
	    <a href="javascript:void(0);" class="top-right-btn"><span id="edit_goods" >编辑</span></a>
	</header>
    <article class="member-main">
        <div class="attention-list">
            <div class="attention-tit"><span>已关注品牌</span></div>
            <ul>
            	<#list list?if_exists as resc>
                <li>
                    <div class="attention-img">
                        <a href="${base}/goods/goodsList!getGoodsListByBrandId.action?brandid=${resc.relevance_id?default()}">
                        	<img src="${ui1}${resc.logo?default('')}" alt="${resc.brand_name?default('')}" />
                        </a>
                        <div class="shade-bg" style="display:none;"><b class="delete-ico" val="${resc.id?default('')}">-</b></div>
                    </div>
                </li>
                </#list>               
            </ul>
        </div>
    </article>
    <div class="footer-bottom">
	<#include "/static/inc/wap/footer.ftl">
	</div>
</section>
<#else>
<section class="common-main">
    <#include "/static/inc/wap/header.ftl">
    <article class="order-box">
        <article class="comment_auto">
                <dl>
                    <dt><img src="${base}/web/images/comment.svg"/></dt>
                    <dd>
                    	<p>暂无关注品牌</p>
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
<link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript" charset="utf-8">
$(function(){

	$(".delete-ico").click(function(){
		var def = $(this);
		$.ajax({
			url: "${base}/member/collect!cancel.action",
			type: "GET",
			data:{
				"id":def.attr("val"),
			},
			cache: false,
			success: function(data){
				def.parent().parent().parent().remove();
			}
		});
	});
	
	$("#edit_goods").click(function(){
		if($(this).html()=="编辑"){
			$(this).html("完成");
			$(".shade-bg").css({display:"block"});
		}else{
			$(this).html("编辑");
			$(".shade-bg").css({display:"none"});
		}
	});
});
</script>