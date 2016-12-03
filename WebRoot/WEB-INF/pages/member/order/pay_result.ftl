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
    <title>支付结果</title>
     <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
     <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
	<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
    <style type="text/css">
        .pay-result{padding-bottom:50px;}
        .top-left-back{position:absolute; top:0; left:0; height:44px; line-height:44px; width:30px; font-size:15px; color:#666;padding-left:24px; background: url("images/new/top_left_back.png") no-repeat 9px center; background-size:9px 15px;}
        .pay-img{width:185px; height:87px; margin:30px auto; }
        .pay-img img{height:87px; width:100%;}
        .pay-result h3{font-size:14px; color:#333; text-align: center;}
        .pay-result p{font-size:12px; color:#999; text-align: center; margin-top:5px;}
        .pay-btn{margin:50px auto; height:40px; width:164px;}
        .pay-btn a{display: block; height:40px; line-height:40px; border-radius:30px; background: #fe4310; text-align: center; font-size:12px; color:#FFF;}
        .pay-two-code{width:70px; height:70px;margin:0 auto;}
        .pay-two-code img{width:100%; height:70px;}
        p.red-txt{color:#f14310;}
        p.pay-see{color:#666;}
        p.pay-see a{color:#f14310; text-decoration: underline;}
    </style>
</head>

<body style="background: #FFF;">
<header class="header">
    <a href="${base}/member/profile.action" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">结算</h2>
    <a href="/" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
<article class="pay-result pt45">
    <div class="pay-img">
        <img src="${base}/web/images/new/pay_result_bg.png" alt=""/>
    </div>
     <#if order.orderStatus==1 &&paymentWay.paymentCode =='hdfk'>
         <h3>订单提交成功</h3>
		 <p>您的商品正在火速配送中</p>
    </#if>
    <#if order.orderStatus==1 &&paymentWay.paymentCode !='hdfk'>
         <h3>订单支付成功</h3>
		 <p>您的商品正在火速配送中</p>
    </#if>
    <#if order.orderStatus==0 &&paymentWay.paymentCode !='hdfk'>
       <h3>订单支付处理中</h3>
	   <p>请您稍后查看</p>         
    </#if> 
    <div class="pay-btn">
        <a href="${base}/member/order!detail.action?id=${order.id}">查看订单详情</a>
    </div>
    <div class="pay-two-code">
        <img src="${base}/web/images/new/pay_two_code.png" alt=""/>
    </div>
    <p class="red-txt">长按二维码 关注111医药馆</p>
    <p class="pay-see">可随时查看<a href="#">订单中心</a></p>
</article>
</body>
</html>

