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
    <title>我的专属二维码</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
</head>
<body class="bg-white">
<header class="header">
    <a href="javascript:history.go(-1);" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">二维码</h2>
    <a href="../member/profile.action" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
<article class="health-index" style="top: 20%;">
    <div style="position:relative;" class="two-dimension" style="">
    	<div style="position:absolute; left:50%; margin-left:-110px; top:30%; margin-top:-110px;">
    	<#if tLeader?exists>
        	<img src="${img_ui1?if_exists}/static/image/codeqr/${tLeader.code?default('')}.jpg" alt=""/>
        </#if>
        <a id="share_btn" style="width:120px; height:36px; line-height:36px; display:block; margin:10px auto; border-radius:4px; -webkit-border-radius:4px; text-align:center; border:1px solid #00a6a0; color:#00a6a0;">点击分享</a>
    </div>
    
    <!--<img src="${base}/web/images/share_btn.png" />-->
    
</article>
</body>
</html>
<div class="mask-ui"></div>
<img class="share-img" src="${base}/web/images/share.png"/>
<style>
.mask-ui 
{
	position:fixed;
	z-index:99;
	background: rgba(22,22,22,.9);
	top:0;
	left:0;
	width:100%;
	height:100%;
}
.share-img
{
	position:fixed;
	z-index:999;
	top:0;
	left:0;
	width:100%;
}
</style>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/jweixin-1.0.0.js"></script>
<!-- 微信分享 -->
<script>
$(function()
{
	window.setTimeout(function()
	{
		$(".mask-ui").hide();
		$(".share-img").hide();
	},3000);
	
	$.ajax(
	{
		url: "/wx/wechat!getSignInfoFromRemote.action?signUrl=/healthLeader/healthLeader!qrCodePage.action",
		type: "GET",
		cache: false,
		async: false,
		success: function(data)
		{
			eval("var datas = " + data+";");
			wx.config(
			{
			    debug: false, 
			    appId: datas.appId, 
			    timestamp: datas.timestamp, 
			    nonceStr: datas.nonceStr, 
			    signature: datas.signature,
			    jsApiList: datas.jsApiList
			});
	    }
     });
     //微信初始化
	 window.setTimeout(initWX,500);
	 //点击分享按钮
	 $("#share_btn").click(function()
	 {
 		$(".mask-ui").show();
		$(".share-img").show();
 		window.setTimeout(function()
		{
			$(".mask-ui").hide();
			$(".share-img").hide();
		},3000);
	 });
});

function initWX()
{
    var title = "111医药馆注册即超值豪礼！要健康、要美丽、要时尚@111医药馆";
    var desc = "111医药馆注册即超值豪礼！要健康、要美丽、要时尚@111医药馆";
    var url_ = "http://www.111yao.com/web/share/leader.html?code=${tLeader.code?default('')}&id=${tLeader.id?default('')}"
	//var url_ = "http://kaifawap.111yao.com/leader/leader!shareLeader.action?hurl="+encodeURIComponent('${leaderUrl?default('')}')+"&code=${tLeader.code?default('')}"; 
	var imgsurl = '${img_ui}/static/image/codeqr/${tLeader.code?default('')}.jpg';
	//接口调用	
	wx.ready(function(){
		//分享给朋友
		wx.onMenuShareAppMessage({
		    title: title, 
		    desc: desc,
		    link: url_,
		    imgUrl: imgsurl,
		    type: '',
		    dataUrl: '',
		    success: function (){ 
		    },
		    cancel: function (){ 
		    }
		});
		//分享到朋友圈
		wx.onMenuShareTimeline({
		    title: title,
		    link: url_,
		    imgUrl: imgsurl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
		//分享到QQ
		wx.onMenuShareQQ({
			title: title,
			desc: desc,
			link: url_,
			imgUrl: imgsurl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
	});	
}
</script>




