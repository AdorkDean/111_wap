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
    <link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/add_prescription.js"></script>
     <#include "/WEB-INF/pages/inc/taglibs.ftl">
</head>
<body>
<#assign title='添加病历'>
<#include "/static/inc/wap/header.ftl">
<article class="member-main member-wrap">
<form action="../pre/prescription!addPerscription.action" id="formBtn" method="post" enctype="multipart/form-data">
    <div class="service-type2 clearfix">
        	<div class="service-op2">
            	<span style="float:right; margin-left: 5px;">
            		<input name="imageFile1" type="file" accept="image/jpg,image/jpeg" style="width: 40px; height: 40px; display: block; float: right; margin-left: -40px; margin-top: 10px;opacity: 0;"/>
            		<a href="#" class="upload-btn2"></a>
            		<div class="health-close"></div>
            	</span>
            	<span style="float:right; margin-left: 5px;">
	            	<input name="imageFile2" type="file" accept="image/jpg,image/jpeg" style="width: 40px; height: 40px; display: block; float: right; margin-left: -40px; margin-top: 10px;opacity: 0;"/>
	            	<a href="#" class="upload-btn2"></a>
	            	<div class="health-close"></div>
            	</span>
            	<span style="float:right; margin-left: 5px;">
            		<input name="imageFile3" type="file" accept="image/jpg,image/jpeg" style="width: 40px; height: 40px; display: block; float: right; margin-left: -40px; margin-top: 10px;opacity: 0;"/>
	            	<a href="#" class="upload-btn2"></a>
	            	<div class="health-close"></div>
            	</span>
            	<span style="float:right; margin-left: 5px;">
	            	<input name="imageFile4" type="file" accept="image/jpg,image/jpeg" style="width: 40px; height: 40px; display: block; float: right; margin-left: -40px; margin-top: 10px;opacity: 0;"/>
	            	<a href="#" class="upload-btn2"></a>
	            	<div class="health-close"></div>
            	</span>
            </div>
        	<span>上传图片</span>
            <div class="img-breviary2" id="img">
            	<!--<b id="imgB"><i>-</i><img src="${base}/web/images/applyaftersale.png"></b>
            	<b id="imgB"><i>-</i><img src="${base}/web/images/applyaftersale.png"></b>-->
            </div>
        </div>
    <div class="case-main-txt clearfix">
        <span>病情描述</span>
        <div class="case-area">
        	<textarea name="diseaseDescrip" id="diseaseDescrip" placeholder="请描述您的病情"></textarea>
        </div>
    </div>
    <div class="case-help clearfix select">
        <span>寻求药师帮助</span>
        <strong class="iconfont" id="ifhelpc">L</strong>
        <input type="hidden" name="ifhelp" id="ifhelp" value="0"/>
    </div>
    <div class="follow-btn"><a href="#" onclick="subForm()">确认上传</a></div>
    </form>
</article>
<#include "/static/inc/wap/footer.ftl">
</body>
 <script type="text/javascript">
$(function(){
    $(".select").click(function(){
    	if($(this).hasClass('select-current')){
            $(this).removeClass('select-current');
            $(this).find("strong").html('L');
            $(this).siblings().find("strong").html('M');
            $("#ifhelp").val(0);
        }else{
            $(this).addClass('select-current').siblings().removeClass('select-current');
            $(this).find("strong").html('M');
            $(this).siblings().find("strong").html('L');
            $("#ifhelp").val(1);
        }
    });
    
    	
});
</script>		
</html>