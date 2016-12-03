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
    <title>基本资料</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
</head>
<body>
<article class="health-basic">
	<form action="../healthLeader/healthLeader!saveHealthLeaderBaseInfo.action" id="subForm" method="post" enctype="multipart/form-data">
   <div class="basic-icon first-page">
       <div class="gray-line"></div>
       <div class="yellow-line"></div>
       <ul class="basic-list clearfix">
           <li><b class="one-icon cur-icon"></b><span>基本资料</span></li>
           <li><b class="two-icon"></b><span>资质资料</span></li>
           <li><b class="three-icon"></b><span>完成</span></li>
       </ul>
   </div>
   <input name="phone" value="${phone?if_exists}" type="hidden"/>
    <ul class="data-list">
        <li class="clearfix header-img-new">
            <span>头像</span>
            <div class="data-main-right data-header-first">
                <b class="iconfont">J</b>
                <input type="file" accept="image/*" id="headFile" style="width:50px;height:50px;opacity: 0;position: absolute;margin-top: 0px; margin-bottom: 0px;" name="headImage" class="divInput"/>
                <!--<a href="javascript:void(0)">+</a>-->
                <img src="${base}/web/images/health-header-img.png" style="border-radius: 50%;" alt="" width="50" height="50"/>
            </div>
        </li>
        <li class="clearfix">
            <span>姓名</span>
            <div class="data-main-right">
                <input type="text" maxlength="8" placeholder="请输入您的姓名"name="realName"/>
            </div>
        </li>
        <li class="clearfix">
            <span>昵称</span>
            <div class="data-main-right">
                <input type="text" maxlength="8" placeholder="请输入您的昵称" name="nickName"/>
            </div>
        </li>
        <li class="clearfix">
            <span>性别</span>
            <div class="data-main-right">
                <select class="single-select" name="sex" id="sex" style="float:right;">
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
            </div>
        </li>
    </ul>
    <ul class="data-list">
        <li class="clearfix">
            <span>区域</span>
            <div class="data-main-right">
                <p>
					<select class="single-select" onchange="selectLocation()" name="firstLocation" id="firstLocation" style="width:68px;">
						<option>请选择</option>
						<#list firstList as location>
						<option value="${location.id?if_exists}">${location.name?if_exists}</option>
						</#list>
					</select>
					<select  onchange="selectCity()" id="secondLocation" name="secondLocation"  class="single-select" style="width:68px;">
						<option>请选择</option>
						</select>
					<select class="single-select" id="thirdLocation" name="thirdLocation" style="width:68px;">
						<option value="">请选择</option>
					</select>
                </p>
            </div>
        </li>
        <li class="clearfix">
            <span>地址</span>
            <div class="data-main-right">
                <input type="text" maxlength="40" placeholder="请输入您的地址"name="address"/>
            </div>
        </li>
    </ul>
    <!--<ul class="data-list">
        <li class="clearfix">
            <span>职业</span>
            <div class="data-main-right">
                <input type="text" maxlength="40" placeholder="请输入您的职业" name="profession"/>
            </div>
        </li>
    </ul>-->
    <ul class="data-list">
        <li class="clearfix">
            <span>邀请码</span>
            <div class="data-main-right">
                <input type="text" maxlength="40" placeholder="选填" name="inviteCode"/>
            </div>
        </li>
    </ul>
    <div class="apply-for-btn">
        <a href="javascript:void(0)" id="goonWriteInfo">继续填写资料</a>
    </div>
    </form>
</article>
</body>
</html>


<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/health_leader_add_basic.js"></script>
