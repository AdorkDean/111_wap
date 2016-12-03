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
    <title>111医药馆－专业服务、品质保证、价格优惠，新版上线现在注册（扫描下方二维码或点击链接即可注册）即享积分等超值豪礼！要健康、要美丽、要时尚@111医药馆</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
</head>
<body class="bg-white appHtmlBody" style="padding-top:0px;">

<input type="hidden" id="phone_type" name="phone_type" />

<header class="header" style="padding-top:20px;">
    <a href="javascript:history.go(-1);" class="iconfont top-left-btn" style="padding-top:20px;">B</a>
    <h2 class="header-title" style="padding-top:20px;">二维码</h2>
    
</header>
<article class="health-index" style="top: 20%;">
    <div style="position:relative;" class="two-dimension" style="">
    	<div style="position:absolute; left:50%; margin-left:-110px; top:30%; margin-top:-110px;"><#if tLeader?exists>
        	<img src="${img_ui1?if_exists}/${tLeader.dimensionalCodeUrl?if_exists}" alt=""/>
        </#if>
        <a href="javascript:share('${leaderUrl?default('')}','${tLeader.code?default('')}','${img_ui}${tLeader.dimensionalCodeUrl?if_exists}');" class="share-btn-now" >立即分享</a>
		<style>
		.share-btn-now{width:220px; height:40px; line-height:40px; display:block; margin:20px auto; text-align:center; color:#fff; font-size:16px; background:#00a6a6; border-radius:5px; -webkit-border-radius:5px;}
		</style>

		
    </div>
</article>
</body>
</html>

<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript">


var u = navigator.userAgent, app = navigator.appVersion;
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
if(isAndroid){
	$('#phone_type').val("android");
}else if(isiOS){
	$('#phone_type').val("ios");
}else{
	
}

function ClientObj(){
	var shareName;
	var shareUrl;
	var shareTitle ;
	var shareImgUrl;
}

function share(url,code,pic){
  
  	var system = $('#phone_type').val();
	
	var obj = new ClientObj();
	var url_ = "http://www.111yao.com/web/share/leader.html?code=${tLeader.code?default('')}&id=${tLeader.id?default('')}";
	obj.shareName = "111医药馆－专业服务、品质保证、价格优惠，新版上线现在注册（扫描下方二维码或点击链接即可注册）即享积分等超值豪礼！要健康、要美丽、要时尚@111医馆";
	//obj.shareUrl = "http://"+window.location.host+"/appleader/appleader!shareLeader.action?hurl="+encodeURIComponent(url)+"&code="+code ;
	obj.shareUrl = url_;
	obj.shareTitle = "111医药馆健康领秀";
	var imgsurl = 'http://ui.111yao.com/static/image/codeqr/${tLeader.code?default('')}.jpg';
	obj.shareImgUrl = imgsurl ;
	
	if(system=='ios'){	// 调用苹果的方法
		var json= JSON.stringify(obj);
		document.location = "share*"+json;
	}else{	// 调用Android的方法
		contact.share(obj.shareName,obj.shareUrl,obj.shareTitle,obj.shareImgUrl);
	}
	
}

</script> 

