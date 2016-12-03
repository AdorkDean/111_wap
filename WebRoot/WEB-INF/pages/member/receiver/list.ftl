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
    <title>地址管理</title>
    
</head>
<body>
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
<link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
<header class="header">
    <a href="${base}/member/profile.action" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">地址管理</h2>
    <a href="/" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
<#if receivers?exists && receivers?has_content>
<article class="member-main">
	<#list  receivers as receiver>
		
	    <div class="address-details select <#if receiver.isDefault?exists && receiver.isDefault == 1>select-current</#if>">
	        <div class="ad-main">
	            <p class="ad-txt ad-name"><span>${receiver.receiver?default('')}</span><span>${receiver.mobile?default('')}</span></p>
	            <p class="ad-txt">${receiver.area?default('')}&nbsp;&nbsp;${receiver.address?default('')}</p>
	        </div>
	        <div class="ad-icon">
	            <i class="iconfont">M</i>
	            <a href="javascript:;" class="iconfont">P</a>
	        </div>
	        <input type="hidden" value="${receiver.id}" class="rid"/>
	    </div>
    
	</#list>
    
    <div class="follow-btn"><a href="${base}/member/receiver!add.action">添加新的收货地址</a></div>
</article>
<#include "/static/inc/wap/footer.ftl">
<script type="text/javascript" src="${base}/web/js/member-center.js"></script>
<#else>
<section class="common-main">
    <article class="order-box">
        <article class="comment_auto">
                <dl>
                    <dt><img src="${base}/web/images/comment.svg"/></dt>
                    <dd><p><a href="${base}/member/receiver!add.action">添加收货地址</a></p></dd>
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