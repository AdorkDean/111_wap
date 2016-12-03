<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="renderer" content="webkit">
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
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
     <#include "/WEB-INF/pages/inc/taglibs.ftl">
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
</head>
<body>
     <#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://img.zdfei.com"/>  
<header class="header">
    <a href="${base}/member/profile.action" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">病历</h2>
     <a href="${base}/pre/prescription!toAddPerscription.action" class="iconfont top-right-btn"><span>添加</span></a>
</header>
<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
<article class="member-main" id="preList">
<#list pw.result?if_exists as resc>
<a href="${base}/pre/prescription!toAddPerscription.action?preid=${resc.id?default('')}">
    <dl class="case-list clearfix">
        <dt><img src="${img_ui1}${resc.image_url?default('')}" alt=""/></dt>
        <dd>
            <div class="case-describe"><span>病情描述</span><time pubdate="pubdate"><#if resc.create_date?exists>${resc.create_date?string('yyyy-MM-dd')}</#if></time></div>
            <p class="case-con">
            <#if resc.disease_descrip?exists>
              <#if resc.disease_descrip?length lt 42>   
				${resc.disease_descrip?default('')}
			<#else> 
				${resc.disease_descrip[0..40]}... 
			</#if>
		</#if>	
            </p>
        </dd>
    </dl>
  </a>
</#list> 
</article>
<#include "/static/inc/wap/footer.ftl">
<script type="text/javascript">
	$(document).ready(function(){
		$(window).scroll(function(){
		if(isBottom(this))
		{
		var p_curPage=parseInt($("#p_curPage").val())+1;
		$("#p_curPage").val(parseInt($("#p_curPage").val())+1);
		var pageSize=$("#pageSize").val();
				$.post(jsCtx+"/pre/prescription!appendPrescriptionList.action",{"rs.p_curPage":p_curPage,"rs.pageSize":pageSize,"random":Math.random()},function(data){
					var content=data;
					$("#preList").append(content);
					var s=$("#preList").clone().html();
				});
		}
			});
});
</script>
</body>
</html>

