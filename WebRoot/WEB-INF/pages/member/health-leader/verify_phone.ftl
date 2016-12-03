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
    <title>注册</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
</head>
<body class="health-new-bg">
<article class="health-register">
    <ul class="h-tel-num">
        <li><input type="text" name="phone" placeholder="请输入您的手机号"/></li>
        <li class="code-num">
            <input type="text" name="code" placeholder="请输入验证码" class="code-input"/>
            <div class="code-btn" id="code-btn"><a id="sendBtn" href="javascript:void(0)">获取验证码</a></div>
        </li>
    </ul>
    <div class="apply-for-btn" style="margin-top:40px;">
        <a href="#" id="nextBtn" onclick="nextBtn()">继续填写资料</a>
    </div>
</article>
</body>
</html>


<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript" src="${base}/web/js/verify_phone.js"></script>

<script type="text/javascript">
</script>