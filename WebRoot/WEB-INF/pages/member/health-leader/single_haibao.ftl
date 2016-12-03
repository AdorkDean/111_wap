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
    <title>我的海报</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <#include "/static/inc/wap/header2.ftl" />
<script>
$(function(){
	//定位标签
	$("#tabs li:eq(3)").addClass("cur");
});
</script>
</head>
<body class="bg-white">
<!--<header class="header">
    <a href="javascript:history.go(-1);" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">我的海报</h2>
    <a href="../member/profile.action" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>-->
<article class="health-center-poster">
	<!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper">
    	<#if tLeaderQrCode?exists && tLeaderQrCode.imgUrl?exists>
	        	<img  src="${img_ui1?if_exists}/${tLeaderQrCode.imgUrl?if_exists}" alt="" style="width:100%; height:100%;"/>
    	<#else>
	    	<#if tLeader?exists>
	        	<img src="${img_ui1?if_exists}/static/image/haibao/${tLeader.code?default('')}_${tLeader.id?default('')}.jpg" alt="" style="width:100%; height:100%;"/>
	        </#if>
        </#if>
        <!--<a id="share_btn" style="width:120px; height:36px; line-height:36px; display:block; margin:10px auto; border-radius:4px; -webkit-border-radius:4px; text-align:center; border:1px solid #00a6a0; color:#00a6a0;">点击分享</a>-->
        <a href="../healthLeader/healthLeader!backgroundHaibaoList.action" style="width:120px; height:36px; line-height:36px; display:block; margin:10px auto; border-radius:4px; -webkit-border-radius:4px; text-align:center; border:1px solid #00a6a0; color:#00a6a0;"
        	 id="createHaibao" style="">重新生成海报</a>
    </div>
</article>
</body>
</html>
<!--<div class="mask-ui"></div>
<img class="share-img" src="${base}/web/images/share.png"/>-->
<style>
.mask-ui 
{
	position:fixed;
	z-index:99;
	background: rgba(22,22,22,.9);
	top:0;
	left:0;
	width:100%;
	height:100%;
}
.share-img
{
	position:fixed;
	z-index:999;
	top:0;
	left:0;
	width:100%;
}
</style>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/jweixin-1.0.0.js"></script>
<!-- 微信分享 -->
<script>
</script>




