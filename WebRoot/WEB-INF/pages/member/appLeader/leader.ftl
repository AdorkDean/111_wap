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
    <title>个人中心</title>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/app_health.css" rel="stylesheet" type="text/css" />
</head>
<body  class="bg-white" style="padding-top:0px;">
<header class="header" style="padding-top: 20px;">
    <a href="javascript:centre()" class="iconfont top-left-btn" style="padding-top: 20px;">B</a>
    <h2 class="header-title" style="padding-top: 20px;">健康领秀</h2>
</header>
<article class="health-list" style="padding-top: 65px;">
    <ul class="health-info" style="margin-top:10px;">
        <li class="current"><a href="#">领秀中心</a></li>
        <li><a href="${base}/appleader/appleader!list.action">领秀药房</a></li>
        <li><a href="${base}/appleader/appleader!ranking.action">领秀排行</a></li>
    </ul>
    <ul class="health-center clearfix" style="margin-top:20px;">
        <li>
            <div class="health-sort-head clearfix">领秀名称</div>
            <p class="health-sort-name">${leaderMap.nick_name?default('')}</p>
        </li>
        <li>
            <a href="#">
                <div class="health-sort-head clearfix">秀粉</div>
                <p class="health-sort-name">${leaderMap.cnt?default(0)}</p>
            </a>
        </li>
    </ul>
    <div class="health-explain">
        <p>普通会员通过你分享的链接注册成为111医药馆会员则自动成为你的秀粉。每个普通会员只能成为一个领秀的秀粉。</p>
    </div>
    <ul class="health-center clearfix">
        <li onclick="javascript:document.getElementById('qrCode').click();">
        	<a id="qrCode" href="../apphealthLeader/healthLeader!qrCodePage.action?id=${leaderMap.leaderId?if_exists}">
	            <div class="health-sort-head clearfix">我的专属二维码</div>
	            <p class="health-sort-name"><img src="${img_ui1?if_exists}${leaderMap.dimensional_code_url?if_exists}" alt=""/><b class="iconfont">J</b></p>
            </a>
            
        </li>
        <li>
            <div class="health-sort-head clearfix">可提现壹贝</div>
            <p class="health-sort-name">${leaderMap.remaining_amount?default('')}</p>
        </li>
        <li>
            <div class="health-sort-head clearfix">提现中壹贝</div>
            <p class="health-sort-name">${leaderMap.freeze_amount?default('')}</p>
        </li>
        <li>
            <div class="health-sort-head clearfix">待入账壹贝</div>
            <p class="health-sort-name">${leaderMap.wait_amount?default('')}</p>
        </li>
        <li>
            <div class="health-sort-head clearfix">累积佣金已达</div>
            <p class="health-sort-name">${leaderMap.total_amount?default(0)}</p>
        </li>
        <li>
            <div class="health-sort-head clearfix">${leaderMap.phone?default('')}</div>
            <!-- <p class="health-sort-name"><a href="#">更换手机</a></p> -->
        </li>
    </ul>
</article>
<div class="health-btn"><a href="${base}/appleader/amountout!amountOut.action">申请提现</a></div>
</body>
<script>
function centre(){
 	document.location = "type*4";
}
</script>
</html>
