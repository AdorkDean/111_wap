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
    <title>领取红包</title>
    <link href="/web/css/??common.css,iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
</head>
<body class="bg-white">
<article class="new-red-bg">
	<img src="${base}/web/images/new-health/new_red_bg1.jpg">
    <a href="#" class="open-red-btn">開</a>
</article>
</body>
</html>
<style type="text/css">
html,body{height:100%; max-width:720px; margin:0 auto; position:relative;}
.new-red-bg{ position:relative; height:100%; width:100%;}
.new-red-bg img{width:100%; height:100%; display:block; position:absolute; left:0; top:0; right:0; bottom:0;}
.open-red-btn{width:74px; height:74px; line-height:74px; display:inline-block; background:#ffa63d; border-radius:50%; -webkit-border-radius:50%; box-shadow:0 3px 0 0 rgba(0,0,0,.2); color:#fff; text-align:center; font-size:36px; position:absolute; left:50%; margin-left:-37px; top:38%;-moz-perspective:800px;-moz-transform-style:preserve-3d; -webkit-perspective:800px; -webkit-transform-style:preserve-3d; -moz-backface-visibility:; -webkit-backface-visibility:hidden;}
.red-rotate{-webkit-animation-name:wobble;-webkit-animation-duration:1.2s;-webkit-animation-timing-function:linear;-webkit-animation-delay:0;-webkit-animation-iteration-count:infinite;-webkit-animation-direction:-moz-animation-name:wobble;-moz-animation-duration:1.2s;-moz-animation-timing-function:linear;-moz-animation-delay:0;-moz-animation-iteration-count:infinite;-moz-animation-direction:;}
@-webkit-keyframes wobble{
     0% {
  		-webkit-transform:rotateY(0deg) rotateX(0deg) rotateZ(0deg) translateZ(0px);
     }
     25% {
  		-webkit-transform:rotateY(90deg) rotateX(0deg) rotateZ(0deg) translateZ(0px);
     }
     50% {
  		-webkit-transform:rotateY(180deg) rotateX(0deg) rotateZ(0deg) translateZ(0px);
     }
     75% {
  		-webkit-transform:rotateY(270deg) rotateX(0deg) rotateZ(0deg) translateZ(0px);
     }
     100% {
  		-webkit-transform:rotateY(360deg) rotateX(0deg) rotateZ(0deg) translateZ(0px);
     }
}
@-moz-keyframes wobble{
     0% {
       	-moz-transform:rotateY(0deg) rotateX(0deg) rotateZ(0deg) translateZ(0px);
     }
     25% {
       	-moz-transform:rotateY(90deg) rotateX(0deg) rotateZ(0deg) translateZ(0px);
     }
     50% {
       	-moz-transform:rotateY(180deg) rotateX(0deg) rotateZ(0deg) translateZ(0px);
     }
     75% {
       	-moz-transform:rotateY(270deg) rotateX(0deg) rotateZ(0deg) translateZ(0px);
     }
     100% {
       	-moz-transform:rotateY(360eg) rotateX(0deg) rotateZ(0deg) translateZ(0px);
     }
}
</style>
<script type="text/javascript">
	$('.open-red-btn').click(function(){
		$(this).addClass('red-rotate');	
		setTimeout(function(){
		location.href="${base}/coupon/openred!showRed.action";
		
		},2000);
	})
</script>