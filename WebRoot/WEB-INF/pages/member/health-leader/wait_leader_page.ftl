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
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
</head>
<body  class="login-bg">
<header class="header health-head">
    <a href="../member/profile.action" class="iconfont top-left-btn">B</a>
    <a href="../member/profile.action" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
<article class="health-index">
    <div class="health-font" style="padding-bottom: 40px;padding-top: 200px;">
        <p>加盟健康领秀大事业</p>
        <p>开启健康领域全新创业模式</p>
    </div>
    
    <#if tLeader?exists && tLeader.auditType?exists && tLeader.auditType==2>
    	<div class="apply-btn applying-btn" style="">审核不通过,请您去<i style="color:red;">修改页</i>修改</div>
    	<div class="apply-btn applying-btn" style=""><a style="background-color:#aaa;opacity:0.8;" href="../healthLeader/editHealthLeaderPage!editHealthLeaderPage.action?id=${tLeader.id?if_exists}" style="color:red;"><span style="color:red;padding-left:0px;">修改</span></a></div>
	    <#if tLeader?exists && tLeader.auditRemark?exists>
	        <div class="apply-btn applying-btn"><#if tLeader?exists><div style="color:red;width:40px;margin-top:8px;">原因:</div>${tLeader.auditRemark?if_exists}</#if></div>
	    </#if>
	<#elseif tLeader?exists && tLeader.auditType?exists && tLeader.auditType==0>
	    <div class="apply-btn applying-btn"><a href="javascript:void(0)"><span>领秀身份审核中...</span></a></div>
	</#if>
</article>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
