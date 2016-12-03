
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
    <title>领秀排行</title>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
	<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
    
</head>

<body>
<#include "/static/inc/wap/header2.ftl" />
<script>
$(function(){
	//定位标签
	$("#tabs li:eq(2)").addClass("cur");
});
</script>
<article class="health-sort">
    <div class="notice-box-wrap">
        <span class="notice-line"></span>
        <div class="notice-box" id="marquee6" >
            <ul>
            	<#list list_center_notice?if_exists as notice>
        		<li>${notice.centent?default('')}</li>
            	</#list>
            </ul>
        </div>
    </div>
    <div class="sort-content">
        <div class="sort-title clearfix">
            <a href="${base}/leader/leader!leaderPill.action">秀粉排行榜>></a>
            <span>我的秀粉排名 <b>${leader.people_ranking?default(99999)}</b></span>
        </div>
    </div>
    <ul class="sort-list">
        <#list leaderList?if_exists as resc>
        	<#if resc_index < 3>
				<li class="list0${resc_index+1}">
		            <div class="sort-part clearfix">
		                <div class="sort-name clearfix">
		                    <span class="sort-icon icon0${resc_index+1}"></span>
		                    <img src="<#if resc.head_url?exists && resc.head_url?has_content><#if !resc.head_url?starts_with('http:')>${ui1}</#if>${resc.head_url?default('')}<#else>${ui1}/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png</#if>" alt=""/>
		                    <b>${resc.nick_name?default('')}</b>
		                </div>
		                <p class="sort-num"><strong>${resc.count?default(0)}</strong>秀粉</p>
		            </div>
		            <i class="left-up left-up0${resc_index+1}"></i>
		            <i class="left-down left-down0${resc_index+1}"></i>
		            <i class="right-up right-up0${resc_index+1}"></i>
		            <i class="right-down right-down0${resc_index+1}"></i>
		            <div class="left-side left-side0${resc_index+1}"></div>
		            <div class="right-side right-side0${resc_index+1}"></div>
		        </li>        	
        	<#else>
        		<li>
		            <div class="sort-part clearfix">
		                <div class="sort-name clearfix">
		                    <span>${resc_index+1}</span>
		                    <img src="<#if resc.head_url?exists && resc.head_url?has_content><#if !resc.head_url?starts_with('http:')>${ui1}</#if>${resc.head_url?default('')}<#else>${ui1}/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png</#if>" alt=""/>
		                    <b>${resc.nick_name?default('')}</b>
		                </div>
		                <p class="sort-num"><strong>${resc.count?default(0)}</strong>秀粉</p>
		            </div>
		        </li>	
        	</#if>
        </#list>  
    </ul>
    
    <div class="sort-content">
        <div class="sort-title clearfix">
            <a href="${base}/leader/leader!leaderBrokerage.action">佣金排行榜>></a>
            <span>我的佣金排名 <b>${leader.price_ranking?default(99999)}</b></span>
        </div>
    </div>
    <ul class="sort-list">
        <#list amountList?if_exists as resc>
        	<#if resc_index < 3>
				<li class="list0${resc_index+1}">
		            <div class="sort-part clearfix">
		                <div class="sort-name clearfix">
		                    <span class="sort-icon icon0${resc_index+1}"></span>
		                    <img src="<#if resc.head_url?exists && resc.head_url?has_content><#if !resc.head_url?starts_with('http:')>${ui1}</#if>${resc.head_url?default('')}<#else>${ui1}/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png</#if>" alt=""/>
		                    <b>${resc.nick_name?default('')}</b>
		                </div>
		                <p class="sort-num"><strong>${resc.total_amount?default(0)}</strong>壹贝</p>
		            </div>
		            <i class="left-up left-up0${resc_index+1}"></i>
		            <i class="left-down left-down0${resc_index+1}"></i>
		            <i class="right-up right-up0${resc_index+1}"></i>
		            <i class="right-down right-down0${resc_index+1}"></i>
		            <div class="left-side left-side0${resc_index+1}"></div>
		            <div class="right-side right-side0${resc_index+1}"></div>
		        </li>        	
        	<#else>
        		<li>
		            <div class="sort-part clearfix">
		                <div class="sort-name clearfix">
		                    <span>${resc_index+1}</span>
		                    <img src="<#if resc.head_url?exists && resc.head_url?has_content><#if !resc.head_url?starts_with('http:')>${ui1}</#if>${resc.head_url?default('')}<#else>${ui1}/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png</#if>" alt=""/>
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
<script type="text/javascript" src="${base}/web/js/Marquee.js"></script>
<script type="text/javascript">
    $('#marquee6').kxbdSuperMarquee({
        isMarquee:true,
        isEqual:false,
        scrollDelay:600,
        controlBtn:{up:'#goUM',down:'#goDM'},
        direction:'up'
    });
</script>
</html>