<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="renderer" content="webkit" />
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
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
    <title>我的文章</title>
    <link href="/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
<#include "/static/inc/wap/common.html"> 
</head>
<body class="bg-white">
<#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://img.zdfei.com"/> 
<article class="new-health-box">
	<div class="new-health-article-list">
    	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
		
		<input type='hidden' id="leaderId" name="leaderId" value="${leader.id?default('')}"/>
	    <input type='hidden' id="leaderCode" name="leaderCode" value="${leader.code?default('')}"/>
		<#if accountFlag=='0'>
		   <div class="share-tips clearfix">
        	 <b></b>分享文章即可赚取您的第一份收益
           </div>
        <#else>
           <div class="profit-tips clearfix">
        	<p>我的收益<b>${tmemberaccount.remainingAmount?default(0.00)}</b>壹贝</p>
            <p>［ 未包含待入账收益 ］</p>
           </div>
		</#if>
        <#if goodsarticleFlag =='1'>
	        <div class="explain-title">
	        	<span>推荐文章</span>
	        </div>
	        <ul>
            <#list goodsarticleList as leaderArt>
            <#if leaderArt_index == 0>
	    		<input type='hidden' id="ga_id" name="ga_id" value="${leaderArt.id?default('')}"/>
				<input type='hidden' id="ga_title" name="ga_title" value="${leaderArt.title?default('')}"/>
				<input type='hidden' id="ga_share_image_url" name="ga_share_image_url" value="${leaderArt.shareImageUrl?default('')}"/>
				<input type='hidden' id="ga_share_title" name="ga_share_title" value="${leaderArt.shareTitle?default('')}"/>
			</#if>
    		<li class="clearfix">
            	<a href="/static/leader/${leaderArt.id?default('')}.html?code=${leader.code?default('')}&id=${leader.id?default('')}" class="article-link">
                    <img src="<#if leaderArt?exists && leaderArt.shareImageUrl?exists>${leaderArt.shareImageUrl?if_exists}<#else>..</#if>">
                    <div class="article-introduce">
                        <p class="article-title">${leaderArt.title?if_exists}</p>
                        <div class="article-state clearfix">
                        	<#--<span>16评论</span>
                            <span>22感谢</span>
                            <span>10分享</span>-->
                        	<span><font class="fga_${leaderArt.id?default('')}">${leaderArt.clickLikeSum?if_exists}</font>点赞</span>
                        </div>
                        <p class="article-info">${leaderArt.shareTitle?if_exists}</p>
                    </div>
                </a>
                <a href="javascript:void(0);" onclick="getGoodsArticleShareUrlJs('${leaderArt.title?if_exists}','${leaderArt.shareTitle?if_exists}','${leaderArt.shareImageUrl?if_exists}','${leaderArt.id?if_exists}','${leader.id?if_exists}','${leader.code?if_exists}');showShareImg();" class="share">分享</a>
                <i class="cut-line"></i>
            </li>
    	</#list>
    	</ul>
        </#if>
	    <div class="explain-title" style="margin-top:10px;">
        	<span>我的文章</span>
        </div>
    	<ul id="appendUl">
    	<#list pw.result as leaderArt>
	    	<#if leaderArt_index == 0>
	    		<input type='hidden' id="leaderId" name="leaderId" value="${leader.id?default('')}"/>
	    		<input type='hidden' id="leaderCode" name="leaderCode" value="${leader.code?default('')}"/>
	    		
	    		<input type='hidden' id="id" name="id" value="${leaderArt.id?default('')}"/>
				<input type='hidden' id="title" name="title" value="${leaderArt.title?default('')}"/>
				<input type='hidden' id="share_image_url" name="share_image_url" value="${leaderArt.shareImageUrl?default('')}"/>
				<input type='hidden' id="share_title" name="share_title" value="${leaderArt.shareTitle?default('')}"/>
				<input type='hidden' id="leader_id" name="leader_id" value="${leaderArt.leader_id?default('')}"/>
			</#if>
    		<li class="clearfix">
            	<a href="/static/leader/user/${leaderArt.id?default('')}.html?code=${leader.code?default('')}&id=${leader.id?default('')}" class="article-link">
                    <img src="<#if leaderArt?exists && leaderArt.shareImageUrl?exists>${leaderArt.shareImageUrl?if_exists}<#else>..</#if>">
                    <div class="article-introduce">
                        <p class="article-title">${leaderArt.title?if_exists}</p>
                        <div class="article-state clearfix">
                        	<#--<span>16评论</span>
                            <span>22感谢</span>
                            <span>10分享</span>-->
                        	<span><font class="f_${leaderArt.id?default('')}">${leaderArt.clickLikeSum?if_exists}</font>点赞</span>
                            <span>${leaderArt.lookSum?if_exists}阅读</span>
                            <span>${leaderArt.commentSum?if_exists}分享</span>
                        </div>
                        <p class="article-info">${leaderArt.shareTitle?if_exists}</p>
                    </div>
                </a>
                <a href="javascript:void(0);" onclick="getArticleShareUrlJs('${leaderArt.title?if_exists}','${leaderArt.shareTitle?if_exists}','${leaderArt.shareImageUrl?if_exists}','${leaderArt.id?if_exists}','${leader.id?if_exists}','${leader.code?if_exists}');showShareImg();" class="share">分享</a>
                <i class="cut-line"></i>
            </li>
    	</#list>
        	<#--<li class="clearfix">
            	<a href="#" class="article-link">
                    <img src="images/new-health/articel_img.jpg">
                    <div class="article-introduce">
                        <p class="article-title">攀岩对一个人的影响到攀岩对一个人的影响到攀岩对一个人的影响到</p>
                        <div class="article-state clearfix">
                        	<span>16评论</span>
                            <span>22感谢</span>
                            <span>10分享</span>
                        </div>
                        <p class="article-info">近年来已明确颈椎管内径，尤其是矢状径，不仅对颈椎病</p>
                    </div>
                </a>
                <a href="#" class="share">分享</a>
                <i class="cut-line"></i>
            </li>
            -->
        </ul>
        <#if pw?exists&&pw.result?exists && pw.result?size gt 0>
        <div class="no-more-tips" id="noMore" style="display:none">没有更多文章了~</div>
        <!--加载提示-->
        <div class="no-more-tips" id="getMore">向下拉加载更多文章~</div>
         <#else>
	        <div class="no-more-tips" id="noMore">没有更多文章了~</div>
        </#if>
        <!--加载动画-->
        <div class="no-more-tips" id="lazy_dh" style="display:none"><img src="${base}/web/images/new-health/tips_img.png"/></div>
    </div>
</article>
<footer class="new-health-footer">
	<nav class="new-health-footer-nav">
    	<ul>
        	<li>
            	<a href="../leader/leaderArticle!recommendArticle.action">
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
<!--微信分享 start-->
<#include "/WEB-INF/pages/member/leader/showshareimg.ftl">
<script type="text/javascript" src="/web/js/??jquery.min.js,new-health.js,common.new.js,cookieUtil.js,alert.main.js,loadingbefore.js,jweixin-1.0.0.js,weiChatShare.js,t_leader_article.js,t_leader_article_list.js"></script>
</body>
</html>