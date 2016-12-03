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
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
</head>
<body>
<#assign title='查看病历'>
<#include "/static/inc/wap/header.ftl">
<article class="member-main member-wrap">
    <div class="case-img clearfix">
        <span>病历图片</span>
        <ul class="case-img-list clearfix">
        <#list preImgList?if_exists as resc>
            <li><img src="${img_ui1}${resc.path?default('')}" alt=""/></li>
        </#list>
        </ul>
    </div>
    <div class="case-main-txt clearfix">
        <span>病情描述</span>
        <div class="case-area">
        	<textarea readonly="readonly" ><#if pre?exists>${pre.diseaseDescrip?default('')}</#if></textarea>
        </div>
    </div>
    <div class="case-help clearfix">
        <span>寻求药师帮助</span>
        <strong class="iconfont">
        <#if pre?exists>
        	<#if pre.ifhelp?default(0)==1>
        	M
        	<#else>
        	L
        	</#if>
        <#else>
        L
        </#if>
        </strong>
    </div>
    <div class="follow-btn"><a href="javascript:void(0);" onclick="deletePre(${pre.id?default('')})">删除病历</a></div>
</article>
<#include "/static/inc/wap/footer.ftl">
</body>
<script type="text/javascript">
function deletePre(preid){
$.post("${base}/pre/prescription!deletePerscription.action",
{preid:preid,random:Math.random()},function(data){
   if(data==0){
     alert('删除失败');
   }else{
     alert('删除成功');
      window.location.href="${base}/pre/prescription!getPrescriptionList.action";
   }
});
}
</script>
</html>