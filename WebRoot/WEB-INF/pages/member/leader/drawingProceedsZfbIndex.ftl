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
    <title>提现</title>
    <link href="/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/web/js/??jquery.min.js,new-health.js"></script>
</head>
<body class="bg-white">
<article class="new-health-box">
	<article class="new-health-register">
    	<div class="account-info-box">
    	<#list result.get(0)?if_exists as resc>
        	<div class="account-number clearfix">
            	<span>到账账号：</span>
                <p>
                	<img src="${base}/web/images/new-health/card_alipay_ico.png">
                   ${resc.account_number?default("")}
                </p>
            </div>
         </#list>
            <div class="fill-money-box">
            	<p>提现金额</p>
                <div class="fill-money-info">
                	<div class="for-fill-input">
                    	<b>￥</b>
                    	<input type="tel" value="80"/>
                    </div>
                    <div class="fill-tips">可提现收益(壹贝)¥0.13</div>
                </div>
            </div>
        </div>
    	<div class="new-health-btn">
        	<!--可用-->
        	<a href="#">确认</a>
            <!--不可用-->
            <a href="JavaScript:void(0)" class="gray">确认</a>
        </div>
    </article>
</article>
</body>
</html>