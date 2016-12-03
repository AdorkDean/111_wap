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
    <title>中国好药房－111医药馆</title>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
</head>
<body class="bg-white">
	
	<article class="share-poster-box" style="height:90%;width:90%;margin-top: 10%;margin-bottom:8%;margin-left:5%;">
		<div style="width:100%; margin:0 0 72px 0;" class="poster-img">
		
	    	<a target="_blank" href="${tiaozhuan?default('#')}">
	    		<img style="width:100%; display:block;"  src="..${url?default('')}" alt=""/>
		    	<img style="width:100%; display:block;" src="../web/images/new-health/hb_bottom2.jpg">
	    	</a>
	    </div>
	</article>
<#if flag?exists && flag=='1'>
	<!--<div class="new-health-common-btn" style="bottom:70px;">
		<a href="javascript:void(0);" class="share" onclick="showShareImg()">分享海报</a>
	</div>-->
<footer style="z-index:999;" class="new-health-footer">
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
<script type="text/javascript" src="/web/js/jquery.min.js"></script>
<script type="text/javascript" src="/web/js/new-health.js"></script>
<script type="text/javascript" src="/web/js/common.new.js"></script>
<script type="text/javascript" src="/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="/web/js/alert.main.js"></script>
<script type="text/javascript" src="/web/js/loadingbefore.js"></script>
<script type="text/javascript" src="/web/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="/web/js/weiChatShare.js"></script>
<script type="text/javascript" src="/web/js/t_leader_article.js"></script>
<script type="text/javascript" src="/web/js/t_leader_article_list.js"></script>
<script type="text/javascript">



<#if flag?exists && flag=='1'>

$(function(){
	var code = '${code?default("")}';
	var coo = getCookie("share_code_poster+_"+code);
	if(coo==null || coo=="undefined"||coo==""||coo=="null"){
		setCookie("share_code_poster_"+code,code);
		showShareImg();
	}
});



function setCookie(name,value) { 
    var exp = new Date(); 
    exp.setTime(exp.getTime() + 99999*24*60*60*1000); 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
} 

function getCookie(name) { 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
        return unescape(arr[2]); 
    } else{
        return "";
    } 
} 
</#if>
</script>
</body>
</html>

