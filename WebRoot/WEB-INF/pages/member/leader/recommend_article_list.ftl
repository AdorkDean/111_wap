<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="renderer" content="webkit" />
    <#include "/WEB-INF/pages/inc/taglibs.ftl" />
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="fullscreen=yes,preventMove=no" name="ML-Config">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="format-detection" content="email=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <title>文章推荐</title>
    <link href="${base}/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
<#include "/static/inc/wap/common.html"> 
</head>
<body class="bg-white">
<#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://img.zdfei.com"/> 
<article class="new-health-box">
	<div class="new-health-article-list">
		<input type='hidden' id="leaderId" name="leaderId" value="${leader.id?default('')}"/>
	    <input type='hidden' id="leaderCode" name="leaderCode" value="${leader.code?default('')}"/>
    	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
        <div class="explain-title">
	        	<span>推荐文章</span>
	        </div>
	        <ul id="appendUl">
            <#list pw.result as leaderArt>
            <#if leaderArt_index == 0>
	    		<input type='hidden' id="ga_id" name="ga_id" value="${leaderArt.id?default('')}"/>
				<input type='hidden' id="ga_title" name="ga_title" value="${leaderArt.title?default('')}"/>
				<input type='hidden' id="ga_share_image_url" name="ga_share_image_url" value="${leaderArt.shareImageUrl?default('')}"/>
				<input type='hidden' id="ga_share_title" name="ga_share_title" value="${leaderArt.shareTitle?default('')}"/>
			</#if>            
	    		<li class="clearfix">
	            	<a href="/static/leader/${leaderArt.id?default('')}.html?code=${leader.code?default('')}&id=${leader.id?default('')}" class="article-link">
	                    <img src="<#if leaderArt?exists && leaderArt.share_image_url?exists>${ui1}${leaderArt.share_image_url?if_exists}<#else>..</#if>">
	                    <div class="article-introduce">
	                        <p class="article-title">${leaderArt.title?if_exists}</p>
	                        <div class="article-state clearfix">
	                        	<span><font class="fga_${leaderArt.id?default('')}">${leaderArt.click_like_sum?if_exists?default(0)}</font>点赞</span>
	                        </div>
	                        <p class="article-info">${leaderArt.share_title?if_exists}</p>
	                    </div>
	                </a>
	                <a href="javascript:void(0);" onclick="getGoodsArticleShareUrlJs('${leaderArt.title?if_exists}','${leaderArt.share_title?if_exists}','${leaderArt.share_image_url?if_exists}','${leaderArt.id?if_exists}','${leader.id?if_exists}','${leader.code?if_exists}');showShareImg();" class="share">分享</a>
	                <i class="cut-line"></i>
	            </li>
    	   </#list>
    	</ul>
    	
    	<#if pw?exists&&pw.result?exists && pw.result?size gt 0>
	        <div class="no-more-tips" id="noMore" style="display:none">没有更多文章了~</div>
	        <!--加载提示-->
	        <div class="no-more-tips" id="getMore">向下拉加载更多文章~</div>
	        <!--加载动画-->
		<#else>
	        <div class="no-more-tips" id="noMore">没有更多文章了~</div>
	        <div class="no-more-tips" id="getMore" style="display:none">向下拉加载更多文章~</div>
        </#if>
        <div class="no-more-tips" id="lazy_dh" style="display:none"><img src="${base}/web/images/new-health/tips_img.png"/></div>
    </div>
</article>
<footer class="new-health-footer">
	<nav class="new-health-footer-nav">
    	<ul>
        	<li>
            	<a href="../leader/leaderArticle!recommendArticle.action" class="footer-current">
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
            	<a href="../leader/leaderCenter!index.action">
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
<script type="text/javascript" src="${base}/web/js/??jquery.min.js,new-health.js,common.new.js,cookieUtil.js,alert.main.js,loadingbefore.js,jweixin-1.0.0.js,weiChatShare.js,recommend_article_list.js"></script>
<#include "/WEB-INF/pages/member/leader/showshareimg.ftl">
</body>
</html>