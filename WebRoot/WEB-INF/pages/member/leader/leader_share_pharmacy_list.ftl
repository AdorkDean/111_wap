
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
<title>领秀分享的药房</title>
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/new-health.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
<script type="text/javascript" src="${base}/web/js/wap-cart.js"></script>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://img.zdfei.com"/> 
</head>

<body>
<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
<input type="hidden" name="leaderId" value="${leaderId}" id="leaderId">
<input type="hidden" name="orderby" value="${orderby?default(1)}" id="orderby">

<img src="${haibaoImg}" alt="" style="width:100%;" class="new-head-portrait"/>
            
<header class="header header-yf">
        <#--<div class="new-header-img">
            <img src="<#if headPortrait?exists && headPortrait?has_content><#if !headPortrait?starts_with('http:')>${ui1}</#if>${headPortrait?default('')}<#else>${ui1}/static/image/temp/20160127/73569e0fe07f5e60234179baddec94eb.jpg</#if>" alt="" class="new-head-portrait"/>
            <div class="new-title-name">
                <img src="${base}/web/images/header_logo_icon.png" alt=""/>
            </div>
        </div>-->
        <div class="new-header-top clearfix">
            <div class="search-box-yf">
                <a href="#" class="iconfont search-btn-yf" id="search-btn"></a>
                <p class="search-input-box-yf">
                   <input class="search-input-yf" placeholder="搜 索"/>
                </p>
            </div>
            <a href="/member/profile.action" class="new-top-member"></a>
        </div>
    </header>
<article class="list-main-box">
    <ul class="list-tag clearfix">
        <li <#if orderby=='1'>class="current"</#if> ><a href="/leader/leaderPharmacy!leaderSharePharmacy.action?orderby=1&leaderCode=${leaderCode}">综合相关</a></li>
        <li <#if orderby=='2'>class="current"</#if> ><a href="/leader/leaderPharmacy!leaderSharePharmacy.action?orderby=2&leaderCode=${leaderCode}">折扣优先</a></li>
        <li <#if orderby=='3'>class="current"</#if> ><a href="/leader/leaderPharmacy!leaderSharePharmacy.action?orderby=3&leaderCode=${leaderCode}">价格优先</a></li>
    </ul>
    <div class="product-list-all">
        <ul class="product-list-box clearfix" id="appendUl">
            <#list pw.result as powder>
	            <li>
	                <a href="${base}/m/${powder.goods_id}.html" class="list-pro-info">
	                    <div class="new-list-pro">
	                        <span class="new-discount"><#if powder.zk?exists>${powder.zk?default('')}折</#if></span>
	                        <img src="${ui1}${powder.abbreviation_picture?if_exists}">
	                    </div>
	                    <p class="new-list-pro-title">${powder.short_name?default('')}</p>
	                    <p class="new-list-pro-price">¥${powder.wap_price?string('###0.00')?default('')}</p>
	                    <p><b class="new-list-pro-original">¥${powder.price?string('###0.00')?default('')}</b></p>
	                    <a href="javascript:void(0)" class="iconfont new-list-pro-into-cart" onclick="add_cart_ajax('${powder.goods_id?default('')}',1)"></a>
	                </a>
	            </li>
            </#list>
        </ul>
    </div>
</article>

<footer class="hb-footer" >
	<img style="position: fixed;bottom: 0px;" src="${base}/web/images/new-health/hb_bottom.jpg">
</footer>
</body>
<script type="text/javascript">
$(function(){
	$("input[name='rs.p_curPage']").val(1);
	$("input[name='rs.p_curSize']").val(10);
});


$("#search-btn").click(function()
	{
		var v = $.trim($(".search-input-yf").val());
		if(v != '' && v != null && v != undefined)
    	{
    		window.location.href = "/goods/goodsList!getGoodsListByKeyword.action?keyword="+v;
    	}
    	else
    	{
    		$(".search-input").focus();
    	}
	});
var ii = 0;
	$(document).ready(function(){
		$(window).scroll(function(){
			if(isBottom(this) && ii == 0) {
//				ii +=1;
				var p_curPage=parseInt($("#p_curPage").val())+1;
				$("#p_curPage").val(parseInt($("#p_curPage").val())+1);
				var pageSize=$("#pageSize").val();
				$.ajax({
					url: "../leader/leaderPharmacy!ajaxLeaderSharePharmacy.action",
					type: "POST",
					data: {"rs.p_curPage":p_curPage,"rs.p_pageSize":pageSize,"random":Math.random(),"orderby":$("#orderby").val(),"leaderId":$("#leaderId").val()},
					async:false,
					success: function(data){
						var resultList = data.result;
						if(resultList!=undefined&&resultList.length>0){
							 for ( var i = 0; i < resultList.length; i++) {
							 	var goods = resultList[i];
							 	var appendHtml = "";
							 	
							 	appendHtml+="<li>";
					            appendHtml+="   <a href=\"${base}/m/"+goods.goods_id+".html\" class=\"list-pro-info\">";
					            appendHtml+="        <div class=\"new-list-pro\">";
					            appendHtml+="            <span class=\"new-discount\">"+goods.zk+"折</span>";
					            appendHtml+="            <img src=\"${ui1}"+goods.abbreviation_picture+"\">";
					            appendHtml+="        </div>";
					            appendHtml+="        <p class=\"new-list-pro-title\">"+goods.short_name+"</p>";
					            appendHtml+="        <p class=\"new-list-pro-price\">¥"+parseFloat(goods.wap_price).toFixed(2)+"</p>";
					            appendHtml+="        <p><b class=\"new-list-pro-original\">¥"+parseFloat(goods.price).toFixed(2)+"</b></p>";
					            appendHtml+="        <a href=\"javascript:void(0)\" class=\"iconfont new-list-pro-into-cart\" onclick=\"add_cart_ajax('"+goods.goods_id+"',1)\"></a>";
					            appendHtml+="    </a>";
					            appendHtml+=" </li>";
							 	$("#appendUl").append(appendHtml);
							 }
						} else{//没有了
							
						}
					},error:function(data){}
				});
			}
		
		});
});

function add_cart_ajax(gid,cnt){
	var status = 0;
	jQuery.ajax
	   ({
	       type: "post",
	       url: jsCtx+"/carts/cart!addCart.action",	  
	       data:{'gid':gid,'cnt':cnt},
	       async:false,
	       success: function(data)
	       {
	    	   
	    	   var s=jQuery.parseJSON(data);
	    	   status = s.status;
	    	   mywarn(status)
	    	   if(s.status>0)
	    	   {
	    		    getCartSum(); 
	    	   }
	       }
	   }); 
}

</script>

</html>
