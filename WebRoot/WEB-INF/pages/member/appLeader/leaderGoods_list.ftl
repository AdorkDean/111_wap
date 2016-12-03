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
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
</head>
<body class="bg-white" style="padding-top:0px;">

<header class="header" style="padding-top:20px;">
	<a href="javascript:centre()" class="iconfont top-left-btn" style="padding-top:20px;">B</a>
    <h2 class="header-title" style="padding-top:20px;">健康领秀</h2>
</header>
<article class="health-list" style="padding-top:65px;">
    <ul class="health-info">
        <li><a href="${base}/appleader/appleader!leader.action">领秀中心</a></li>
        <li class="current"><a href="#">领秀药房</a></li>
        <li><a href="${base}/appleader/appleader!ranking.action">领秀排行</a></li>
    </ul>
    <div class="health-pharmacy">
        <table width="100%" cellpadding="0" cellspacing="0" id="goodstr">
            <tr>
                <td height="30" align="center" valign="middle" width="30%">商品名称</td>
                <td height="30" align="center" valign="middle" width="20%">商品规格</td>
                <td height="30" align="center" valign="middle" width="25%">本站价格</td>
                <td height="30" align="center" valign="middle" width="25%">佣金比例</td>
            </tr>
            <#list pw.result?if_exists as resc>  
            <tr>
                <td height="60" align="center" valign="middle" width="30%">${resc.short_name?default('')}</td>
                <td height="60" align="center" valign="middle" width="20%">${resc.spec?default('')}</td>
                <td height="60" align="center" valign="middle" width="25%">￥${resc.wap_price?default('')}</td>
                <td height="60" align="center" valign="middle" width="25%">${resc.brokerage?default('')}%</td>
            </tr>
            </#list> 
        </table>
    </div>
</article>
<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(window).scroll(function(){
		if(isBottom(this))
		{
		var p_curPage=parseInt($("#p_curPage").val())+1;
		$("#p_curPage").val(parseInt($("#p_curPage").val())+1);
		var pageSize=$("#pageSize").val();
				$.post(jsCtx+"/leader/leader!appendGoodsList.action",{"rs.p_curPage":p_curPage,"rs.pageSize":pageSize,"random":Math.random()},function(data){
					if(data=="-1"){
					  flag = false;
					}else{
						var content=data;
						$("#goodstr").append(content);
						var s=$("#goodstr").clone().html();
					}
				});
		}
			});
});


function centre(){
 	document.location = "type*4";
}
</script>
