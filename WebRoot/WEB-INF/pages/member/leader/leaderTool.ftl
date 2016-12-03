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
<title>健康领秀-工具</title>
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
</head>

<body>
<#include "${base}/static/inc/wap/header2.ftl" />
<script type="text/javascript">
$(function(){
	//定位标签
	$("#tabs li:eq(3)").addClass("cur");
});
</script>
<article class="health-center-tools">
	<ul>
    	<li><a href="${base}/healthLeader/healthLeader!gotoHaibaoPage.action"><b class="iconfont">J</b><span><i class="generate"></i>生成我的领秀海报</span></a></li>
        <li><a href="javascript:void(0)" onclick="myShare()" ><b class="iconfont">J</b><span><i class="share-gift"></i>分享红包</span></a></li>
        <li><a href="${base}/leader/leader!leaderImg.action"><b class="iconfont">J</b><span><i class="material"></i>推广素材库</span></a></li>
        <li><a href="${base}/leader/leader!report.action"><b class="iconfont">J</b><span><i class="effect"></i>推广效果统计</span></a></li>
    </ul>
</article>
<#include "${base}/static/inc/wap/footer.ftl"/>
</body>
</html>

<script>


function myShare()
{
    var currentDomain = "http://"+window.location.host;
	var myurl = "${base}/healthLeader/lxredpack!share.action";
	if(isWeiXin())
	{
    	this.location="${base}/login/login!authUrl.action?currentDomain=" + currentDomain;
    }
    else
    {
		window.location.href = myurl;
    }
}


function islogin(){
  var login = 0;
  jQuery.ajax
   ({
       type: "post",
       url: ${base}+"/carts/cart!isLogin.action",	  
        async:false,
       success: function(data)
       {
    	  
    	  login = data;
       }
   }); 
   return login; 
}


/*
function myShare(){
	var login = islogin();
	var myurl = "${base}/healthLeader/lxredpack!share.action";
	if(login>0){
		window.location.href = myurl;
	}else{
		window.location.href = "${base}/login/login!index.action?redirectUrl="+myurl;
	}
   
}
*/
//是否是微信浏览器
function isWeiXin(){
	    var ua = window.navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	        return true;
	    }else{
	        return false;
	    }
	}
</script>