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
    <link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/member-center.js"></script>
    <script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
</head>
<body>
     <#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://img.zdfei.com"/>  
<header class="header">
    <a href="${base}/member/profile.action" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">病例</h2>
     <a href="${base}/pre/prescription!toAddPerscription.action" class="iconfont top-right-btn"><span>添加</span></a>
</header>
<article class="member-no-net">
    <dl>
		<dt><img src="${base}/web/images/comment.svg"/></dt>
		<dd><p>暂无病历</p></dd>
	</dl>
</article>
<div class="footer-bottom">
<#include "/static/inc/wap/footer.ftl">
</div>
</body>
</html>