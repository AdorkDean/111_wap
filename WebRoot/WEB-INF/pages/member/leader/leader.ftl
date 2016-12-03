<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="renderer" content="webkit">
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
<title>个人中心</title>
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${base}/web/js/shopDomain.js"></script>
</head>

<body>
<#include "/static/inc/wap/header2.ftl" />

<script>
$(function(){
	//定位标签
	$("#tabs li:eq(0)").addClass("cur");
});
</script>
<article class="health-center-main">
	<#if top_center_notice?exists>
	<div class="notice-box-wrap">
		<span class="notice-line"></span>
	    <div class="notice-box" style="height:auto;">
	        <p>${top_center_notice.centent?default('')}</p>
	    </div>
    </div>
    </#if>
    
    <#if newtleader?exists>
    
    <#if newtleader.isShop?exists && newtleader.isShop == 0>
    <div class="center-bar" id="openMyPharmacy" onclick="openMyHouse(${newtleader.id})">
        <a class="exclusive-code">
            <b class="iconfont fr">J</b>
            <i style="width:38px; height:30px; background:url(/web/images/code_ico_02.png) no-repeat; background-size:38px; margin:7px 5px;"></i>
            <span>点击开通我的专属药房</span>
        </a>
    </div>
    </#if>
    
    <#if newtleader.isShop?exists && newtleader.isShop == 1>
    <div class="center-bar" id="toMyPharmacy" onclick="shareMyHouse()">
        <a class="exclusive-code">
            <b class="iconfont fr">J</b>
            <i style="width:38px; height:30px; background:url(/web/images/code_ico_02.png) no-repeat; background-size:38px; margin:7px 5px;"></i>
            <span>点击分享我的专属药房</span>
        </a>
    </div>
    </#if>
    
    </#if>
    
   <div class="center-bar" id="toMyPharmacy" onclick="shareMyHouse()" style="display:none;">
        <a class="exclusive-code">
            <b class="iconfont fr">J</b>
            <i style="width:38px; height:30px; background:url(/web/images/code_ico_02.png) no-repeat; background-size:38px; margin:7px 5px;"></i>
            <span>点击分享我的专属药房</span>
        </a>
    </div>
    <#if newtleader?exists>
    <input type="hidden" value="${newtleader.nickName?default('')}" id="nickname_"/>
    <input type="hidden" value="${newtleader.realName?default('')}" id="realname_"/>
    <input type="hidden" value="${newtleader.id?default(0)}" id="leaderId_"/>
    </#if>
    
    
    <div class="center-bar">
    	<a href="#" class="exclusive-code" id="shareSecondCode">
        	<b class="iconfont fr">J</b>
            <i></i>
        	<span>点击分享我的专属二维码</span>
        </a>
    </div>
    <div class="center-bar">
    	<a href="javascript:void(0)">${memberAccount.totalAmount?default(0)-memberAccount.remainingAmount?default(0)-memberAccount.freezeAmount?default(0)}<span>已提现壹贝</span></a>
    </div>
    <ul class="eb-state clearfix clear">
    	<li class="blue1">
        	<a href="${base}/leader/leader!runningWater.action"><span>${waitAmount?default(0)}</span><p>待入账壹贝</p></a>
        </li>
        <li class="blue2">
        	<a href="#"><span>${memberAccount.remainingAmount?default(0)}</span><p>可提现壹贝</p></a>
        </li>
        <li class="blue3">
        	<a href="#"><span>${memberAccount.freezeAmount?default(0)}</span><p>提现中壹贝</p></a>
        </li>
    </ul>
    <a href="javascript:void(0)" id="tixianBtn" class="health-common-btn mt15">提取现金</a>
</article>

<div class="black-cover black-cover-wechat" style="display:none">
	<div class="hb-img">
		<a href="javascript:void(0)" class="close-hb"></a>
    	<img class="poster-img" src="${ui1}${poster?default('')}">
    	<div class="share-tips-corner fr" style="display:none;"></div>
    </div>
</div>
<#include "/static/inc/wap/footer.ftl"/>
</body>
</html>
<script type="text/javascript">
var poster_url = getErWeiCodeUrl(${leader.id});
$(function()
{
	//提现
	$("#tixianBtn").click(function()
	{
		window.location.href="${base}/member/amountout!amountOut.action";
	});
	
	//分享二维码
	$('#shareSecondCode').click(function()
	{
		shareMyErWeiCode(1);
	});
	
	//关闭分享二维码
	$('.close-hb').click(function()
	{
		$('.black-cover').hide();	
	});
	
	//微信认证初始化	
	var host = "http://"+window.location.host;
    var surl = host + "/leader/leader!leader.action";
	$.ajax(
	{
	    url: "/wx/wechat!getSignInfoFromRemote.action?signUrl="+surl,
		type: "GET",
		cache: false,
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
			shareMyErWeiCode(2);
	     }
     });
});

//分享我的二维码
function shareMyErWeiCode(type)
{
	var desc = "111医药馆注册即超值豪礼！要健康、要美丽、要时尚@111医药馆";
	wx.ready(function()
	{
		//分享给朋友
		wx.onMenuShareAppMessage({
		    title: desc, 
		    desc: desc,
		    link: poster_url,
		    imgUrl: "${ui1}${poster?default('')}",
		    type: '',
		    dataUrl: '',
		    success: function (){ 
		    },
		    cancel: function (){ 
		    }
		});
		//分享到朋友圈
		wx.onMenuShareTimeline({
		    title: desc,
		    link: poster_url,
		    imgUrl: "${ui1}${poster?default('')}",
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
		//分享到QQ
		wx.onMenuShareQQ({
			title: desc,
			desc: desc,
			link: poster_url,
			imgUrl: "${ui1}${poster?default('')}",
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
	});
	if(type == 1)
	{
		$(".share-tips-corner").show();	
		$('.black-cover-wechat').show();
	}
}

//开通我的药房
function openMyHouse(leaderId)
{
	if(confirm("您确认要开通我的药房么？"))
	{
		$("#loadimg").show();
		$(".mask-ui-3").show();
		$.ajax(
	    {
	        url: "${base}/leader/leader!openMyPharmacy.action?leaderId="+leaderId,
	        type: 'POST',
	        cache: false,
	        success: function(data)
	        {
	        	if(data == 1)
	        	{
					$("#loadimg").hide();
					$(".mask-ui-3").hide();
					$alert("success","恭喜您，已成功开通我的药房！");
					$("#openMyPharmacy").hide();
					$("#toMyPharmacy").show();
	        	}
	        	else
	            {
					$alert("error","开通我的药房失败了！");
					$("#loadimg").hide();
					$(".mask-ui-3").hide();
	            }
	        },
	        error: function()
	        {
				$alert("error","开通我的药房失败了！");
				$("#loadimg").hide();
				$(".mask-ui-3").hide();
	        }
	    }); 
	}
}

//分享我的药房
function shareMyHouse()
{
	var nickname = $.trim($("#nickname_").val());
	var realname = $.trim($("#realname_").val());
	var leaderId = $.trim($("#leaderId_").val());
	var prm = "我的";
	if(nickname != '' && nickname != null)
	{
		prm = nickname+"_的";
	}
	else
	{
		if(realname != '' && realname != null)
		{
			prm = realname+"_的";
		}
	}
	initParam(prm,leaderId);
	$(".mask-ui-2").show();
	$(".share-img-2").show();
	window.setTimeout(function()
	{
		$(".mask-ui-2").hide();
		$(".share-img-2").hide();
	},3000);
}

//初始化分享参数
function initParam(nickname,leaderId)
{
	var title = nickname+"专属药房";
	var desc = nickname+"专属药房";
	var url = getShopDomain(leaderId);
	var imgUrl = "http://img.zdfei.com/static/image/temp/20151223/2e493cbc28d11b53e1f9ed0e1f39964a.jpg";
	wx.ready(function()
	{
		//分享给朋友
		wx.onMenuShareAppMessage({
		    title: title, 
		    desc: desc,
		    link: url,
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
		    title: title,
		    link: url,
		    imgUrl: imgUrl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
		//分享到QQ
		wx.onMenuShareQQ({
			title: title,
			desc: desc,
			link: url,
			imgUrl: imgUrl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
	});	
}

</script>
<div class="loading-img" id="loadimg"><img src="/web/images/loading_img.jpg"/></div>
<div class="mask-ui-2"></div>
<div class="mask-ui-3"></div>
<img class="share-img-2" src="${base}/web/images/share.png"/>
<style>
.mask-ui-2 {position:fixed;z-index:99;background: rgba(22,22,22,.9);top:0;left:0;width:100%;height:100%;display:none;}
.mask-ui-3 {position:fixed;z-index:99;background: rgba(22,22,22,.1);top:0;left:0;width:100%;height:100%;display:none;}
.share-img-2 {position:fixed;z-index:999;top:0;left:0;width:100%;display:none;}
.loading-img {display:none;position:fixed;height:auto;z-index:100000;top:45%;left:45%;background:gray;padding:10px;border-radius:10px;opacity:.6;filter:alpha(opacity=60);}
.loading-img img {widht:30px;height:30px;}
</style>