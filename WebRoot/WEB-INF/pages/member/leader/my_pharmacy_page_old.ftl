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
    <title>我的药房海报</title>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
</head>
<body class="bg-white">
	<article class="share-poster-box" style="height:75%;">
		<div class="poster-img">
	    	<img  src="${url?if_exists}" alt="" style="width:100%; height:100%;"/>
	    </div>
	</article>
	

<#if flag?exists && flag=='1'>
<div class="new-health-common-btn" style="bottom:70px;">
		<a href="javascript:void(0);" class="share" onclick="showShareImg()">分享药房海报</a>
	</div>
	
<footer class="new-health-footer">
	<nav class="new-health-footer-nav">
    	<ul>
        	<li>
            	<a href="../leader/leaderArticle!recommendArticle.action" >
                	<b class="my-article"></b>
                    <p>文章推荐</p>
                </a>
            </li>
            <li>
            	<a href="../leader/leaderArticle!leaderPharmacy.action">
                	<b class="my-pharmacy"></b>
                    <p>我的药房</p>
                </a>
            </li>
            <li>
            	<a href="../leader/leaderCenter!index.action" class="footer-current">
                	<b class="my-manage"></b>
                    <p>管理中心</p>
                </a>
            </li>
        </ul>
    </nav>
    <div class="new-health-copy">
    	<h1>健康领秀</h1>
        <p>&copy;111Yao</p>
    </div>
</footer>
</#if>
<#include "/WEB-INF/pages/member/leader/showshareimg.ftl">
</body>
</html>

