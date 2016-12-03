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
    <title>资质资料</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <style>
    	.card-img,.prove-img{position:relative;}
    	.prove-img{float:left;margin-left: 6px;}
    	.prove-img a img{width:50px;height:50px;}
    	.card-img a img{width:70px;height:70px;}
    	.divInput{width:70px;height:70px;opacity: 0;position: absolute; border:none; background:none;}
    	.zizhi{width:50px;height:50px;opacity: 0;position: absolute; border:none; background:none;}
    </style>
</head>
<body>
<article class="health-basic">
    <div class="basic-icon">
        <div class="gray-line"></div>
        <div class="yellow-line"></div>
        <ul class="basic-list clearfix">
            <li><b class="one-icon cur-icon"></b><span>基本资料</span></li>
            <li><b class="two-icon cur-icon"></b><span>资质资料</span></li>
            <li><b class="three-icon"></b><span>完成</span></li>
        </ul>
    </div>
    
    <form action="../healthLeader/healthLeader!saveZizhiInfo.action" id="subForm" method="post" enctype="multipart/form-data">
    <ul class="data-list" style="margin-bottom:0;">
        <li class="clearfix">
            <span>身份证(必填)</span>
            <div class="data-main-right">
                <input type="text" name="cardCode" placeholder="请输入您的身份证号码"/>
                <input type="hidden" name="leaderId" value="${tLeader.id?if_exists}"/>
            </div>
        </li>
    </ul>
    <div class="identity-card clearfix">
        <p>请上传您的身份证正反面，图片不大于2M，格式为JPG。</p>
        <div class="card-img">
            <input class="divInput" accept="image/*" name="cardFirst" type="file"/>
            <a href="javascript:void(0)"><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"/></a>
        </div>
        <div class="card-img">
            <input class="divInput" accept="image/*" name="cardTwo" type="file"/>
            <a href="javascript:void(0)"><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"/></a>
        </div>
    </div>
    <div class="prove-for" style="">
        <span>请上传您的资质证明</span>
        <div class="prove-img">
        	<input class="divInput zizhi" accept="image/*" name="leaderImage1" type="file"/>
            <a href="javascript:void(0)" style="font-size: 60px;"><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"/></a>
        </div>
        <div class="prove-img">
        	<input class="divInput zizhi" accept="image/*" name="leaderImage2" type="file"/>
            <a href="javascript:void(0)" style="font-size: 60px;"><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"/></a>
        </div>
        <div class="prove-img">
        	<input class="divInput zizhi" accept="image/*" name="leaderImage3" type="file"/>
            <a href="javascript:void(0)" style="font-size: 60px;"><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"/></a>
        </div>
        <div class="prove-img">
        	<input class="divInput zizhi" accept="image/*" name="leaderImage4" type="file"/>
            <a href="javascript:void(0)" style="font-size: 60px;"><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"/></a>
        </div>
        <div class="prove-img">
        	<input class="divInput zizhi" accept="image/*" name="leaderImage5" type="file"/>
            <a href="javascript:void(0)" style="font-size: 60px;"><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"/></a>
        </div>
        <p style="margin-top: 65px;">最少上传1张最多上传5张资质比如执业医师，执业药师，健康管理师，育婴师，药士，初中级药师，主管药师，护士资格证，中药调剂员等资质，图片不大于2M，格式为JPG。</p>
    </div>
    <div class="explain-text">
        <p>请于1个月内完善资质，否则无法正常提现</p>
        <p>客服电话 400-606-3111</p>
    </div>
    <div class="apply-for-btn" style="margin-top:10px;">
        <a href="javascript:void(0)" id="liji">立即提交申请</a>
        <a href="javascript:void(0)" class="no-for" id="youke">先不填写 立即体验</a>
    </div>
    
    </form>
</article>
</body>
</html>


<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>

<script type="text/javascript" src="${base}/web/js/healt_leader_zizhi.js"></script>
