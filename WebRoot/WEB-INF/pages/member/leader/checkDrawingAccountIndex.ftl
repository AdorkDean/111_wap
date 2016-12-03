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
    <title>选择账号</title>
    <link href="/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/web/js/??jquery.min.js,new-health.js"></script>
</head>
<body class="bg-white">
<article class="new-health-box">
	<article class="new-health-account-main">
		<h2 class="choice-account-tips">请选择到账帐号</h2>
        <ul class="choice-account-list">
         <#list result.get(0)?if_exists as resc>
         <#if resc.bank_name?exists && resc.bank_name?string !="">
        	<li>
            	<a href="${base}/leader/bindingAmount!drawingProceedsIndex.action?accountId=${resc.id?default(0)}" class="account">
                	<img src="${base}/web/images/new-health/bank_card_bg.png">
                    <p class="account-kind"><b class="bank_ico1"></b>${resc.bank_name?default("")}</p>
                    <p class="card-kind">银行卡</p>
                    <p class="account-details">${resc.account_number?default("")}</p>
                </a>
            </li>
            <#else>
            <li>
            	<a href="${base}/leader/bindingAmount!drawingProceedsIndex.action?accountId=${resc.id?default(0)}" class="account">
                	<img src="${base}/web/images/new-health/ali_pay_bg.png">
                    <p class="account-kind"><b class="alipay_ico"></b>支付宝</p>
                    <p class="account-details">${resc.account_number?default("")}</p>
                </a>
            </li>
            </#if>
          </#list>
        </ul>
        <div class="account-manage-btn">
        	<a href="${base}/leader/bindingAmount!checkDrawingMethod.action"><b class="add_ico"></b>新增绑定账号</a>
        </div>
    </article>
</article>
</body>
</html>