<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<#include "/WEB-INF/pages/inc/taglibs.ftl">
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
<title>品牌列表</title>
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/jquery.fly.min.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
</head>

<body>
     <#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://img.zdfei.com"/>  
<header class="header">
    <a href="javascript:;" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">
    <#if brand?exists>
    ${brand.brandName?default('')}
	</#if>
	</h2>
  <a id="collect" href="javascript:void(0);" <#if flag?default(0)==1>class="iconfont top-collect-btn alr"<#else>class="iconfont top-collect-btn"</#if>><#if flag?default(0)==1>E<#else>D</#if></a>
	<a href="/category/categoryList!getHomeData.action" class="iconfont top-right-btn">F</a>
</header>
<article class="list-main-box">
    <ul class="list-tag clearfix">
        <li <#if sort?default(1)==1>class="current"</#if>><a href="${base}/goods/goodsList!getGoodsListByBrandId.action?brandid=${brandid?default(-1)}&&sort=1">显示现货</a></li>
        <li <#if sort?default(1)==2>class="current"</#if>><a href="${base}/goods/goodsList!getGoodsListByBrandId.action?brandid=${brandid?default(-1)}&&sort=2">折扣优先</a></li>
        <li <#if sort?default(1)==3>class="current"</#if>><a href="${base}/goods/goodsList!getGoodsListByBrandId.action?brandid=${brandid?default(-1)}&&sort=3">价格优先</a></li>
    </ul>
    <input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
        <input type="hidden" name="brandid" id="brandid" value="${brandid?default(-1)}"/>
        <input type="hidden" name="sort" id="sort" value="${sort?default(-1)}"/>
    <div class="product-list-all">
        <ul id="goodsList" class="product-list-box clearfix">
        <#list  pw.result?if_exists as resc>
            <li>
            	<a href="${base}/m/${resc.id?default('')}.html" class="list-pro-info">
	                <div class="new-list-pro">
	                	<#if resc.discount?default(0)!=0&&resc.discount?default(0)!=10><span class="new-discount">${resc.discount?default(0)}折<i></i></span></#if>
	                    <img src="${img_ui1}${resc.abbreviation_picture?default('')}">
	                </div>
                    <p class="new-list-pro-title">${resc.short_name?default('')}</p>
                    <p class="new-list-pro-price">¥${resc.price?default('')}</p>
                    <p><b class="new-list-pro-original">￥${resc.market_price?default('')}</b></p>
	              <#if resc.type?default(0)==2||resc.type?default(0)==3>
                    <a href="${base}/m/${resc.id?default('')}.html"  class="iconfont new-list-pro-into-rx"></a>
                    <#else>
                    <a href="javascript:void(0)" id="${resc.id?default('')}" class="iconfont new-list-pro-into-cart"></a>
                    </#if>
                </a>
            </li>
            </#list>
        </ul>
    </div>
</article>
<#include "/static/inc/wap/footer.ftl">
<script type="text/javascript">
	$(document).ready(function(){
		$(window).scroll(function(){
		if(isBottom(this))
		{
		var sort=$("#sort").val();
		var brandid=$("#brandid").val();
		var p_curPage=parseInt($("#p_curPage").val())+1;
		$("#p_curPage").val(parseInt($("#p_curPage").val())+1);
		var pageSize=$("#pageSize").val();
				$.post(jsCtx+"/goods/goodsList!appendGoodsListByBrandId.action",{"brandid":brandid,"sort":sort,"rs.p_curPage":p_curPage,"rs.pageSize":pageSize,"random":Math.random()},function(data){
					var content=data;
					if(content=="-1"){
					flag=false;
					}else{
					$("#goodsList").append(content);
					var s=$("#goodsList").clone().html();
					}
				});
		}
			});
});
getCartSum(2);
</script>
<script type="text/javascript">
$(function(){
    $.ajaxSetup
	({
		 cache: false
    });
	//This is collection button
	$("#collect").click(function(){
	    var id = $.trim($("#brandid").val());
		if(checklogin()){
			$.ajax(
			{  
				type: "get",               
				url: "/index!attentionpro.action?id="+id+"&type=1",               
				success: function(data)
				{  
					if(data == 0)
					{
						document.getElementById("collect").className = "iconfont top-collect-btn";
						$("#collect").html("D");
					}
					if(data == 1)
					{
						document.getElementById("collect").className = "iconfont top-collect-btn alr";
						$("#collect").html("E");
					}
				},  
				error: function()
				{  
				}  
			}); 
		}else{
			window.location.href = "/login/login!index.action?redirectUrl="+location.href;
		}
	});
	
});


</script>
</body>
</html>
