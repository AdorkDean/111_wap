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
    <title>个人中心</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
</head>
<body >
<header class="header">
    <a href="javascript:history.go(-1);" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">申请领秀</h2>
    <a href="../member/profile.action" class="iconfont top-right-btn" style="width:70px;"></a>
    <a href="../member/profile.action" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
<article class="uploading-top clearfix">
	<form action="../healthLeader/saveHealthLeader!saveHealthLeader.action" id="subForm" method="post" enctype="multipart/form-data">
    <input name="phone" value="${phone?if_exists}" type="hidden"/>
    <input name="id" value="${tLeader.id?if_exists}" type="hidden"/>
    
    <ul class="personal-data">
        <li class="clearfix">
            <span>昵称</span>
            <div class="personal-data-text"><input maxlength="8" type="text" value="${tLeader.nickName?if_exists}" name="realName" placeholder="请输入您的昵称"/></div>
        </li>
        <li class="clearfix">
            <span>姓名</span>
            <div class="personal-data-text"><input maxlength="8" type="text" value="${tLeader.realName?if_exists}" name="nickName" placeholder="请输入您的姓名"/></div>
        </li>
        <li class="clearfix">
            <span>性别</span>
            <div class="personal-data-text">
            	<select class="single-select" name="sex" id="sex" style="float:right;">
                    <option value="1" <#if tLeader.sex?exists && tLeader.sex==1>selected</#if>>男</option>
                    <option value="1" <#if tLeader.sex?exists && tLeader.sex==0>selected</#if>>女</option>
                </select>
            </div>
        </li>
        <li class="clearfix">
            <span>身份证号</span>
            <div class="personal-data-text"><input maxlength="18" type="text"  value="${tLeader.cardCode?if_exists}" name="cardCode" placeholder="请输入您的身份证号"/></div>
        </li>
        <li class="clearfix">
            <span>联系地址</span>
            <div class="personal-data-text clearfix">
                <select class="single-select" onchange="selectLocation()" name="firstLocation" id="firstLocation" style="width:68px;">
                    <option>请选择</option>
                    <#list firstList as location>
	                    <option  <#if firstLocation?exists && location.id?exists && firstLocation.id==location.id>selected</#if> value="${location.id?if_exists}">${location.name?if_exists}</option>
	                </#list>
                </select>
                <select  onchange="selectCity()" id="secondLocation" name="secondLocation"  class="single-select" style="width:68px;">
                    <option>请选择</option>
                     <#list secondList as location>
                        <option <#if secondLocation?exists && location.id?exists && secondLocation.id==location.id>selected</#if> value="${location.id?if_exists}">${location.name?if_exists}</option>
                    </#list>
                </select>
                <select class="single-select" id="thirdLocation" name="thirdLocation" style="width:68px;">
                    <option>请选择</option>
                    <#list thirdList as location>
                        <option <#if thirdLocation?exists && location.id?exists && thirdLocation.id==location.id>selected</#if> value="${location.id?if_exists}">${location.name?if_exists}</option>
                    </#list>
                </select>
            </div>
        </li>
        <li class="clearfix">
            <span>详细地址</span>
            <div class="personal-data-text"><input maxlength="40" type="text"  value="${tLeader.address?if_exists}" name="address" placeholder="请输入您的详细地址"/></div>
        </li>
    </ul>
    
    <ul class="health-uploading clearfix">
        <li class="clearfix">
            <span>请上传您的头像</span>
            <div class="up-pic">
                <input type="file" name="headImage" class="divInput"/>
				<#if tLeader?exists && tLeader.headUrl?exists>
            		<img style="height:50px;width:50px;" src="${tLeader.headUrl?if_exists}"/>
            	<#else>
	                <a href="#">+</a>
            	</#if>
            </div>
        </li>
    </ul>
    <div class="up-text"><p>图片不大于2M,格式限JPG</p></div>
    <ul class="health-uploading clearfix">
        <li class="clearfix">
            <span>请上传您的资质</span>
            <div class="up-pic">
                <input class="divInput" name="leaderImage1" type="file"/>
				<#if leaderImage1?exists><input type="hidden" name="leaderImage1Id" value="${leaderImage1.id?if_exists}"/></#if>
	            	<#if leaderImage1?exists && leaderImage1.imageUrl?exists>
	            		<img style="height:50px;width:50px;" src="${leaderImage1.imageUrl?if_exists}"/>
	            	<#else>
                	<a href="#">+</a>
            	</#if>
	
            </div>
            <div class="up-pic">
                <input class="divInput" name="leaderImage2" type="file"/>
				<#if leaderImage2?exists><input type="hidden" name="leaderImage2Id" value="${leaderImage2.id?if_exists}"/></#if>
	            	<#if leaderImage2?exists && leaderImage2.imageUrl?exists>
	            		<img style="height:50px;width:50px;" src="${leaderImage2.imageUrl?if_exists}"/>
	            	<#else>
                	<a href="#">+</a>
            	</#if>
            </div>
            <div class="up-pic">
                <input class="divInput" name="leaderImage3" type="file"/>
				<#if leaderImage3?exists><input type="hidden" name="leaderImage3Id" value="${leaderImage3.id?if_exists}"/></#if>
	            	<#if leaderImage3?exists && leaderImage3.imageUrl?exists>
	            		<img style="height:50px;width:50px;" src="${leaderImage3.imageUrl?if_exists}"/>
	            	<#else>
                	<a href="#">+</a>
            	</#if>
            </div>
            <div class="up-pic">
                <input class="divInput" name="leaderImage4" type="file"/>
				<#if leaderImage4?exists><input type="hidden" name="leaderImage4Id" value="${leaderImage4.id?if_exists}"/></#if>
	            	<#if leaderImage4?exists && leaderImage4.imageUrl?exists>
	            		<img style="height:50px;width:50px;" src="${leaderImage4.imageUrl?if_exists}"/>
	            	<#else>
                	<a href="#">+</a>
            	</#if>
            </div>
            <div class="up-pic">
                <input class="divInput" name="leaderImage5" type="file"/>
				<#if leaderImage5?exists><input type="hidden" name="leaderImage5Id" value="${leaderImage5.id?if_exists}"/></#if>
	            	<#if leaderImage5?exists && leaderImage5.imageUrl?exists>
	            		<img style="height:50px;width:50px;" src="${leaderImage5.imageUrl?if_exists}"/>
	            	<#else>
                	<a href="#">+</a>
            	</#if>
            </div>
        </li>
    </ul>
    <div class="up-explain"><p>最少上传1张最多5张资质比如执业医师，执业药师，健康管理师，育婴师，药士，初中级药师，主管药师，护士资格证，中药调剂员等资质，图片不大于2M,格式限JPG</p></div>
    <ul class="health-uploading clearfix">
        <li class="clearfix">
            <span>请上传您的身份证正反面</span>
            <div class="up-pic">
                <input class="divInput" name="cardFirst" type="file"/>
                <#if tLeader?exists && tLeader.cardFirstUrl?exists>
            		<img style="height:50px;width:50px;" src="${tLeader.cardFirstUrl?if_exists}"/>
            	<#else>
		            <a href="#">+</a>
            	</#if>
            </div>
            <div class="up-pic">
                <input class="divInput" name="cardTwo" type="file"/>
				<#if tLeader?exists && tLeader.cardTwoUrl?exists>
            		<img style="height:50px;width:50px;" src="${tLeader.cardTwoUrl?if_exists}"/>
            	<#else>
            		<a href="#">+</a>
            	</#if>
            </div>
        </li>
    </ul>
    <div class="up-text"><p>图片不大于2M,格式限JPG</p></div>
    
</form>
</article>
<div class="health-btn"><a href="javascript:void(0)" id="submitBtn">提交申请</a></div>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/health_leader_edit.js"></script>
