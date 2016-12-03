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
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
</head>
<body class="bg-white" style="padding-top:0px;">
<header class="header" style="padding-top: 20px;">
	<a href="javascript:history.go(-1);" class="iconfont top-left-btn" style="padding-top: 20px;">B</a>
    <h2 class="header-title" style="padding-top: 20px;">佣金排行榜</h2>
</header>
<article class="health-list" style="padding-top: 65px;">
    <ul class="health-sort clearfix">
    <#list pw.result?if_exists as resc>
        <li>
          <a href="#">
            <div class="health-sort-head clearfix">
                <span>${resc_index+1}</span>
                <#if resc?exists && resc.head_portrait?exists && (resc.head_portrait.substring(0,4)=='http')>
                	<img src="${resc.head_portrait?default('/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png')}" alt=""/>
                <#else>
                	<img src="${img_ui1}${resc.head_portrait?default('/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png')}" alt=""/>
                </#if>
                <b>${resc.nick_name?default('')}</b>
            </div>
            <p class="health-sort-name"><b>${resc.total_amount?default(0)}</b>壹贝</p>
          </a>  
        </li>
     </#list>
    </ul>
</article>
</body>
</html>
<script type="text/javascript" src="js/jquery.min.js"></script>
