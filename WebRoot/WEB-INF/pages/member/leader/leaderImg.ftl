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
<title>健康领秀-素材-图片</title>
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/new-health.js"></script>
    <script type="text/javascript" src="${base}/web/js/common.new.js"></script>
    <script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
<script src="${base}/web/js/waterfall.js" type="text/javascript"></script>
<style type="text/css">
#main{position: relative;}
.pin{float:left;}
.box img{height:auto; display:block;}
</style>
<script type="text/javascript">
	$(function(){
		$('.box img').width($(window).width()/2-1);	
	})
</script>
</head>

<body>
<article class="health-center-material">
	<p class="share-sc-tips" onclick="test();">选择自己喜欢的图片、文字保存到手机分享给朋友</p>
    <div class="material-tag">
    	<div class="wrap">
            <div class="tabs">
                <a href="${base}/leader/leaderArticle!leaderImg.action" class="active">图片</a>
                <a href="${base}/leader/leaderArticle!leaderContent.action">文字</a>
            </div>    
            <div class="picture-container">
            	<div id="main">
            	    <#list  pw.result?if_exists as resc>
                    <div class="pin">
                        <div class="box">
                            <img src="http://img.zdfei.com/${resc.imgurl}"/>
                        </div>
                    </div>
					</#list>          	
					<#if pw.result?size==0>
						<span style="height:30px; line-height:30px; text-align:center; display:block">暂时没有数据可以显示！</span>
					</#if>
                </div>
            </div>
        </div>
    </div>
</article>
<div class="black-cover img-yl" style=" display:none;">
	<div class="hb-img">
    	<a href="javascript:void(0)" class="close-hb"></a>
    	<img src="${base}/web/images/hb.jpg">
    </div>
</div>
<style>
.img-yl{width:100%; height:100%; position:fixed; left:0; top:0; right:0; bottom:0; text-align:center;display:-webkit-box;display:-ms-flexbox;display:-webkit-flex;display:flex;-webkit-box-pack:center;-ms-flex-pack:center;-webkit-justify-content:center;justify-content:center;-webkit-box-align:center;-ms-flex-align:center;-webkit-align-items:center;align-items:center;flex:1; z-index:10;}
.img-yl img{margin:0;}
</style>
</body>
<script>
    $(function(){
		//关闭预览
		$('.close-hb').click(function(){
			$('.black-cover').hide();	
		})
		//显示预览
        $(".box img").click(function(){
			imgUrl = $(this).attr("src");
			$('.black-cover').show();
			$('.img-yl img').attr("src",imgUrl);
        })
    })
</script>
</html>