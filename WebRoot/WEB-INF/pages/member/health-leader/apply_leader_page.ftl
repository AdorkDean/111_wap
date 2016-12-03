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
    <!-- Link Swiper's CSS -->
    <link rel="stylesheet" href="${base}/web/css/swiper.min.css">
    <link rel="stylesheet" href="${base}/web/css/share.css">
</head>
<body class="font-white">
<!-- Swiper -->
<div class="swiper-container">
    <div class="swiper-wrapper">
        <div class="swiper-slide health-index-page1">
            <div class="flip-corner"></div>
            <div class="join-btn">
                <a href="../healthLeader/applyHealthLeaderPage!applyHealthLeaderPage.action">成为健康领秀</a>
            </div>
        </div>
        <div class="swiper-slide health-index-page2">
            <div class="flip-corner"></div>
            <div class="activity-part">
                <p>健康领秀首期招募国内城市合伙人<b>100</b>名</p>
                <p class="text-bottom"><b>0</b>元拥有一家网上药店</p>
                <ul class="text-par clearfix">
                    <li>1　定期的培训、聚会、徒步、K歌等活动；</li>
                    <li>2　晋升为区域领秀或城市领秀，分享更多的荣誉、地位和收益。</li>
                </ul>
            </div>
        </div>
        <div class="swiper-slide health-index-page3">
            <div class="flip-corner"></div>
            <div class="partner-plan">
                <span class="plan-tit">合伙人计划</span>
                <ul class="plan-list">
                    <li>假如你是</li>
                    <li>药师，我们需要你</li>
                    <li>医师，我们需要你</li>
                    <li>护士，我们需要你</li>
                    <li>营养师，我们需要你</li>
                    <li>医药工作者，我们需要你</li>
                    <li>健康达人，我们也需要你</li>
                </ul>
            </div>
        </div>
        <div class="swiper-slide health-index-page4">
            <div class="three-step">
                <span class="plan-tit">只需三步即可成为健康领秀</span>
                <ul class="step-list">
                    <li>1、填写基本信息</li>
                    <li>2、上传证件资质</li>
                    <li>3、通过审核</li>
                </ul>
                <div class="join-btn-bottom">
                    <a href="../healthLeader/applyHealthLeaderPage!applyHealthLeaderPage.action">成为健康领秀</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Swiper JS -->
<script src="${base}/web/js/jquery.min.js"></script>
<script src="${base}/web/js/swiper.animate1.0.2.min.js"></script>
<script src="${base}/web/js/swiper.min.js"></script>
<!-- Initialize Swiper -->
<script>
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        direction: 'vertical',
        onInit: function(swiper){ //Swiper2.x的初始化是onFirstInit
            swiperAnimateCache(swiper); //隐藏动画元素
            swiperAnimate(swiper); //初始化完成开始动画
        },
        onSlideChangeEnd: function(swiper){
            swiperAnimate(swiper); //每个slide切换结束时也运行当前slide动画
        }
    });
</script>
</body>
</html>