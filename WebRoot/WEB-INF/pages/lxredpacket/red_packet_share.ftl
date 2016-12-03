<!doctype html>
<html lang="zh-CN">
<head>
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<#include "/WEB-INF/pages/member/leader/showshareimg.ftl">
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
    <title>${red.shareTitle?default('')}</title>
    <link href="/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="/web/css/new-health.css" rel="stylesheet" type="text/css" />
    
	<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
 	
 	<style>
 	.cart-no-pro{width:100%; height:100%; position:absolute; left:0; top:0; right:0; bottom:0; text-align:center;display:-webkit-box;display:-ms-flexbox;display:-webkit-flex;display:flex;-webkit-box-pack:center;-ms-flex-pack:center;-webkit-justify-content:center;justify-content:center;-webkit-box-align:center;-ms-flex-align:center;-webkit-align-items:center;align-items:center;flex:1; z-index:10;}
	a.no-pro-btn{border:1px solid #00b4b8; display: block;  margin:20px 70px 0;border-radius: 6px; text-align: center; line-height:40px; height:40px;font-size:18px !important; color:#00b4b8 !important;}
	.cart-no-pro dl{ -webkit-box-flex: 1; text-align: center; margin-top:-100px;}
	.cart-no-pro img{ width:50%;}
	.cart-no-pro dd{ margin-top: 15px;  color: #c8c8c8; font-size: 24px;}
	
	.new-health-manage-top {
	  height: 48px;
	  background: #00c9f4;
	}
	.new-health-info li p {
	  height: 20px;
	  line-height: 60px;
	  font-size: 18px;
	}
	.new-health-info {
	  float: center;
	  width: 40%;
	  text-align: center;
	  margin: 0 auto;
	}
	
	.top-left-btn {
	  width: 45px;
	  height: 45px;
	  line-height: 45px;
	  position: absolute;
	  left: 0;
	  top: 0;
	  z-index: 9;
	  font-size: 32px;
	  color: #fff;
	  text-align: center;
	}
 	</style>
 	
</head>

<html>


<body>


<article class="share-rule">
	<div class="new-health-manage-top clearfix">
		<a href="javascript:history.go(-1);" class="iconfont top-left-btn">B</a>
    	<ul class="new-health-info">
        	<li>
            	<p>我的红包</p>
            </li>
        </ul>
    </div>
    <div class="playing-method">
        <div class="method-text">
            <p>红包玩法：领秀每天可发一次大红包，每个大红包含多张现金券，可被10位新老会员通过红包链接随机领取。</p>
        </div>
        <div class="share-friends">
            <p>分享红包到朋友圈</p>
            <p>快速提升秀粉数量</p>
        </div>
        <div class="share-method-temp"><a href="#"></a></div>
    </div>
    <div class="my-share"><a href="javascript:void(0)" onclick="initWX()"  >我要分享</a></div>
</article>


<input type="hidden" id="ishare" value="${ishare?default()}" 　/>
<div class="mask-ui"></div>
<img class="share-img1" src="${base}/web/images/share.png"/>
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
	display:block;
}
.share-img1
{
	position:fixed;
	z-index:999;
	top:0;
	left:0;
	width:100%;
	display:none;
}
</style>


</body>

<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/jweixin-1.0.0.js"></script>


<script>
try{
	myinit();
}catch(e){}

initWX();

$(".mask-ui").hide();
$(".share-img").hide();

function myinit(){
    var surl = document.location.href;
    
	//微信认证初始化	
	$.ajax(
	{
	    url: "/wx/wechat!getSignInfoFromRemote.action?signUrl="+surl,
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
}

function initWX()
{

    /*
    var ishare = $('#ishare').val();
    if(ishare =='true'){
    	$alert("warn","您今天已经发过一次红包了!明天再发吧","111医药馆提示您",null);
    	return;
    }*/
	var imgUrl = "http://img.zdfei.com/${ico?default('')}";
	//显示/隐藏/遮罩层
	$(".mask-ui").show();
	$(".share-img").show();
	window.setTimeout(function()
	{
		$(".mask-ui").hide();
		$(".share-img").hide();
	},4000);
	
	
	//跳转URL
	var yu = "http://"+window.location.host;
	var url_ = yu+"/web/redpacket/my_red_packet.html?lid="+${leaderid}+"&lcode="+${lcode?default('0')};
	
	var desc = "111医药馆注册即超值豪礼！要健康、要美丽、要时尚@111医药馆";
	wx.ready(function(){
		//分享给朋友
		wx.onMenuShareAppMessage({
		    title: "${red.shareTitle?default('')}", 
		    desc: desc,
		    link: url_,
		    imgUrl: imgUrl,
		    type: '',
		    dataUrl: '',
		    success: function (){ 
		    },
		    cancel: function (){ 
		    }
		});
		//分享到朋友圈
		wx.onMenuShareTimeline({
		    title: "${red.shareTitle?default('')}",
		    link: url_,
		    imgUrl: imgUrl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
		//分享到QQ
		wx.onMenuShareQQ({
			title: "${red.shareTitle?default('')}",
			desc: desc,
			link: url_,
			imgUrl: imgUrl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
	});	
}




</script>

</html>
