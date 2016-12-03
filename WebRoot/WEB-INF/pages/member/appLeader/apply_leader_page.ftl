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
    <title>申请健康领秀</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
</head>
<body  class="login-bg">
<header class="header health-head" style="margin-top: 20px;">
    <a href="javascript:centre()" class="iconfont top-left-btn">B</a>
</header>
<article class="health-index">
    <div class="health-font">
        <p>加盟健康领秀大事业</p>
        <p>开启健康领域全新创业模式</p>
    </div>
    <div class="apply-btn"><a href="../apphealthLeader/applyHealthLeaderPage!applyHealthLeaderPage.action"><b></b><span>申请领秀</span></a></div>
    
    <style>
    </style>
<div class="health_arrow"><span></span></div>
    <div class="health_actrule">
        <div style="padding: 0 10px;">
            <p><strong>领秀活动规则：</strong></p>
            <p>1、注册成为111医药馆健康领秀；</p>
            <p>2、分享您的专属链接到微信、微博、QQ等渠道，通过您的专属链接注册成为111医药馆的用户将自动成为您的“秀粉”，普通会员也可以在健康广场主动申请成为您的“秀粉”；</p>
            <p>3、您的“秀粉”在111医药馆网上消费后（不计使用优惠券抵扣的金额），会按照一定佣金比例为您累积“壹贝”；</p>
            <p>4、当您的“壹贝”数大于10（1壹贝＝¥1）时，您可以在每月5日前向我们申请提现，申请被审核通过后我们会短信通知您，并在每月15日前向您的指定账户返现。</p>
            <p style="height: 10px; line-height: 10px; width: 100%;"></p>
            <p style="font-size: 5px !important; ">注:甘肃地区暂时不参加返佣活动，本活动最终解释权由111医药馆所有，感谢您的参与！</p>
        </div>
    </div>
</article>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>

<script>
    $(".health_arrow").on("click","span",function(){
        var h=$(".health_actrule").height();
        console.log(h);
        if($(this).hasClass("arrowdown")){
            $(this).removeClass("arrowdown");
            $(".health_actrule").hide();

            $(this).css("bottom","20px");
        }else{
            $(this).addClass("arrowdown");

            $(".health_actrule").show();
            $(this).css("bottom",h);
        };
    })
    
    function centre(){
 		document.location = "type*4";
	}
</script>