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
    <title>健康领秀</title>
	<link rel="stylesheet" href="${base}/web/css/common.css">
    <link rel="stylesheet" href="${base}/web/css/swiper.min.css">
    <style>
    .swiper-container {
        width: 100%;
        height: 100%;
    }
    .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;

        /* Center slide text vertically */
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
    }
	.swiper-pagination-bullet-active{ background:#00c9f4;}
	.swiper-slide img{width:100%; display:block;}
	.jump-btn{ position:absolute; z-index:9; width:60px; height:24px; line-height:24px; border:1px solid #00c9f4; color:#00c9f4; display:block; text-align:center; font-size:12px; border-radius:14px; -webkit-border-radius:14px; right:15px; top:25px;}
    </style>
</head>
<body>
    <!-- Swiper -->
    <div class="swiper-container">
    	<a href="${base}/leader/leaderArticle!leaderPharmacy.action" class="jump-btn">跳过</a>
        <div class="swiper-wrapper">
            <div class="swiper-slide">
            	<img src="${base}/web/images/new/health01.jpg">
            </div>
            <div class="swiper-slide">
            	<img src="${base}/web/images/new/health02.jpg">
            </div>
            <div class="swiper-slide">
            	<img src="${base}/web/images/new/health03.jpg">
            </div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>

    <!-- Swiper JS -->
    <script src="${base}/web/js/swiper.min.js"></script>

    <!-- Initialize Swiper -->
    <script>
	function delayer(){
	 window.location = "${base}/leader/leaderArticle!leaderPharmacy.action";
	}
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
		onSlideChangeEnd: function(swiper){
			if(swiper.activeIndex == 2){
				setTimeout('delayer()', 2000);
			}
		}
    });

    </script>
</body>
</html>