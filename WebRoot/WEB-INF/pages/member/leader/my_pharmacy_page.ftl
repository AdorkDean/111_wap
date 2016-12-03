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
    <title>药房</title>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/new-health.js"></script>
</head>
<body class="bg-white">
<article class="new-health-box" style="padding:0">
	<div class="new-health-hb">
        <div class="hb-top1">
        	<img class="for-hb-bg1" src="${url?if_exists}" style="width:100%; height:100%;">
            <#--<img class="hb-header-img1" src="images/new-health/my_header.png">
            <img class="hb-code1" src="images/new-health/articel_img.jpg">-->
            <a href="#" class="into-btn"><span>点击进入</span></a>
        </div>
        <h2 class="hot-pro">·热销商品·</h2>
        <ul class="hb-pro-list clearfix">
        	<li>
            	<a href="#">
                	<img src="${base}/web/images/new-health/articel_img.jpg">
                    <p>汇仁肾宝片</p>
                    <p><strong>126片调和阴阳 温阳补肾扶正固本</strong></p>
                </a>
            </li>
            <li>
            	<a href="#">
                	<img src="${base}/web/images/new-health/articel_img.jpg">
                    <p>汇仁肾宝片</p>
                    <p><strong>126片调和阴阳 温阳补肾扶正固本</strong></p>
                </a>
            </li>
        </ul>
        <a href="${moreUrl?if_exists}" class="show-more-hbpro">查看更多商品 &gt;</a>
    </div>
</article>
<footer class="hb-footer">
	<img src="${base}/web/images/new-health/hb_bottom.jpg">
</footer>
</body>
</html>