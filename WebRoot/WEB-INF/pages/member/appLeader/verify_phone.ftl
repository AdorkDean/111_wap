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
    <title>验证手机</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/app_health.css" rel="stylesheet" type="text/css" />
</head>
<body >
<header class="header" style="margin-top:0px;padding-top: 20px;">
    <a href="javascript:history.go(-1);" class="iconfont top-left-btn" style="padding-top: 20px;">B</a>
    <h2 class="header-title" style="padding-top: 20px;">申请领秀</h2>
</header>
<article>
    <ul class="phone-code clearfix">
        <li style="margin-top:20px;">
            <div class="box-phone-tit">手机号</div>
            <div class="box-phone-verify"><input maxlength="11" onkeyup="setPhoneBtn(this)" type="text" name="phone" placeholder="请填写注册手机号"/></div>
        </li>
        <li>
        	<div class="box-tit">验证码</div>
            <div class="box-verify"><input onkeyup="setVerifyBtn(this)" maxlength="6" type="text" name="code"  placeholder="短信验证码"/></div>
            <div class="box-btn"><a style="width:100%;display: block;" href="javascript:void(0)" id="sendBtn">获取验证码&nbsp;</a></div>
        </li>
    </ul>
</article>
<div class="phone-code-btn"><a href="javascript:void(0)" id="nextBtn" onclick="nextBtn()" class="case-help">下一步</a></div>
</body>
<article class="login_agre">
    <label id="selectBox" class="login_sel checklabel"></label>
    <a class="old_agre" href="${base}/help/app_healthagreement.html"><b>已阅读并同意</b><span style="color:#00b4b8;">《健康领秀活动相关声明》</span></a>
</article>
</html>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript" src="${base}/web/js/app_verify_phone.js"></script>
<script>
function centre(){
 	document.location = "type*4";
}
</script>