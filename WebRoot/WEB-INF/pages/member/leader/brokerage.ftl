
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
    <title>佣金排行榜</title>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
</head>
<body class="bg-white">
<header class="header">
    <a href="javascript:history.go(-1);" class="iconfont top-left-btn" style="color:#0d0d0d;">B</a>
    <h2 class="header-title header-title-color">佣金排行榜</h2>
</header>
<article class="xf-sort">
    <ul class="sort-list xf-sort-list">
        <li class="list04">
            <div class="sort-part clearfix">
                <div class="sort-name clearfix">
                    <span></span>
                    <img src="<#if member.headPortrait?exists && member.headPortrait?has_content><#if !member.headPortrait?starts_with('http:')>${ui1}</#if>${member.headPortrait?default('')}<#else>${ui1}/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png</#if>" alt=""/>
                    <div class="total-num">
                        <em>${tleader.nick_name?default('')}</em>
                        <em class="total-icon">${tleader.price_ranking?default(99999)}</em>
                    </div>
                </div>
                <p class="sort-num"><strong>${leaderAccount.totalAmount}</strong>壹贝</p>
            </div>
        </li>
        <#list  pw.result?if_exists as resc>
        	<#if resc_index < 3>
        	<li class="list0${resc_index + 1}">
	            <div class="sort-part clearfix">
	                <div class="sort-name clearfix">
	                    <span class="sort-icon icon0${resc_index + 1}"></span>
	                    <img src="<#if resc.head_portrait?exists && resc.head_portrait?has_content><#if !resc.head_portrait?starts_with('http:')>${ui1}</#if>${resc.head_portrait?default('')}<#else>${ui1}/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png</#if>" alt=""/>
	                    <b>${resc.nick_name?default('')}</b>
	                </div>
	                <p class="sort-num"><strong>${resc.total_amount?default(0)}</strong>壹贝</p>
	            </div>
	        </li>
        	<#else>
        	 <li>
	            <div class="sort-part clearfix">
	                <div class="sort-name clearfix">
	                    <span>${resc_index + 1}</span>
	                    <img src="<#if resc.head_portrait?exists && resc.head_portrait?has_content><#if !resc.head_portrait?starts_with('http:')>${ui1}</#if>${resc.head_portrait?default('')}<#else>${ui1}/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png</#if>" alt=""/>
	                    <b>${resc.nick_name?default('')}</b>
	                </div>
	                <p class="sort-num"><strong>${resc.total_amount?default(0)}</strong>壹贝</p>
	            </div>
	        </li>
        	</#if>
        </#list>
        
    </ul>
</article>
<#include "/static/inc/wap/footer.ftl"/>
</body>
</html>

