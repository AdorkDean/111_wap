<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="renderer" content="webkit" />
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
    <title>管理中心</title>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <link href="${base}/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
</head>
<body class="bg-white">
<article class="new-health-box">
	<article class="new-health-manage" style="padding-bottom: 55px;">
    	<div class="new-health-manage-top clearfix">
        	<ul class="new-health-info">
            	<li>
                	<p>我的收益</p>
                    <p class="fs18">${incomeAmount?default('0')}</p>
                </li>
                <li>
                	<p>我的秀粉</p>
                    <p class="fs18">${fanCount?default('0')}</p>
                </li>
            </ul>
        	<div class="new-health-manage-header clearfix">
            	<img src="<#if leader.headUrl?exists && leader.headUrl != ''>  ${ui1}${leader.headUrl?default('')}<#else>${ui1}/static/image/temp/20160127/73569e0fe07f5e60234179baddec94eb.jpg</#if>"/>
                <#if leader.nickName?exists && leader.nickName != ''>${leader.nickName?default('')}<#elseif leader.realName?exists && leader.realName != ''>${leader.realName?default('')}<#else>健康领秀</#if>
            </div>
        </div>
        <article class="new-health-manage-list">
            <ul>
                <li class="clearfix">
                    <a href="/leader/leaderArticle!leaderArticle.action">
                        <span></span>
                        <b class="ico1"></b>
                        我的文章
                    </a>
                    <i class="cut-line"></i>
                </li>
                <li class="clearfix">
                    <#--<a href="${base}/healthLeader/healthLeader!gotoHaibaoPage.action">-->
                    <a href="${base}/leader/leaderIndex!myPosterPage.action">
                        <span></span>
                        <b class="ico2"></b>
                        领秀海报
                    </a>
                    <i class="cut-line"></i>
                </li>
                <#--<li class="clearfix">
                    <a href="${base}/leader/leaderIndex!myPharmacyPage.action">
                        <span></span>
                        <b class="ico3"></b>
                        药房海报
                    </a>
                    <i class="cut-line"></i>
                </li>-->
                <li class="clearfix">
                    <a href="${base}/leader/leaderArticle!leaderImg.action">
                        <span></span>
                        <b class="ico4"></b>
                        推广素材库
                    </a>
                    <i class="cut-line"></i>
                </li>
                <li class="clearfix">
                    <a href="/leader/leaderArticle!leaderPowderList.action">
                        <span></span>
                        <b class="ico5"></b>
                        我的秀粉
                    </a>
                    <i class="cut-line"></i>
                </li>
                <li class="clearfix">
                    <a href="/leader/leaderArticle!leaderProfit.action">
                        <span></span>
                        <b class="ico6"></b>
                        我的收益
                    </a>
                    <i class="cut-line"></i>
                </li>
                <li class="clearfix">
                    <a href="/healthLeader/lxredpack!share.action">
                        <span></span>
                        <b class="ico7"></b>
                        分享红包
                    </a>
                    <i class="cut-line"></i>
                </li>
                <li class="clearfix">
                    <a href="/leader/leaderCenter!leaderFunctionIntroduce.action">
                        <span></span>
                        <b class="ico8"></b>
                        特色功能
                    </a>
                    <i class="cut-line"></i>
                </li>
            </ul>
        </article>
        <div class="new-health-btn" style="margin-top:20px">
            <a href="javascript:;" onclick="loginout()">退 出</a>
        </div>
    </article>  
</article>
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
<script type="text/javascript">
  function loginout(){
    window.location.href="${base}/login/login!logout.action"
  }
</script>
<script type="text/javascript" src="${base}/web/js/??jquery.min.js,new-health.js"></script>
</body>
</html>