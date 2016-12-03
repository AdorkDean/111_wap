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
    <title>${leader.nickName?if_exists}的药房</title>
   <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/new-health.js"></script>
    <script type="text/javascript" src="${base}/web/js/common.new.js"></script>
    <script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
<#include "/static/inc/wap/common.html"> 
</head>
<body class="bg-white">
 <#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://img.zdfei.com"/> 
<article class="new-health-box">
<input type="hidden" id="leaderCode" value="${code}"/>
<article class="new-health-box">
	<div class="new-health-article-list">
    	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
    	<ul id="appendUl">
    		<#list pw.result as leaderPha>
    		
			<li class="clearfix">
            	<a href="#" class="article-link">
                    <img src="${leaderPha.abbreviation_picture?if_exists}">
                    <div class="article-introduce">
                        <p class="pharmacy-title">${leaderPha.goods_name?if_exists}</p>
                        <p class="pharmacy-rebate">返佣：¥${leaderPha.rebate_amount?if_exists}</p>
                        <p class="pharmacy-price">价格：¥${leaderPha.price?if_exists}</p>
                    </div>
                </a>
                <a href="#" class="share">分享</a>
                <i class="cut-line"></i>
            </li>
    		
            </#list>
        </ul>
        <div class="no-more-tips" id="noMore" style="display:none">没有更多药品了~</div>
        <!--加载提示-->
        <div class="no-more-tips" id="getMore">向下拉加载更多药品~</div>
        <!--加载动画-->
        <div class="no-more-tips" id="lazy_dh" style="display:none"><img src="${base}/web/images/new-health/tips_img.png"/></div>
    </div>
</article>
<#--<footer class="new-health-footer" style="left: 0px;right: 0px;">
	<nav class="new-health-footer-nav">
    	<ul>
        	<li>
            	<a href="../leader/leaderArticle!recommendArticle.action">
                	<b class="my-article"></b>
                    <p>文章推荐</p>
                </a>
            </li>
            <li>
            	<a href="javascript:void(0)" class="footer-current">
                	<b class="my-pharmacy"></b>
                    <p>我的药房</p>
                </a>
            </li>
            <li>
            	<a href="#">
                	<b class="my-manage"></b>
                    <p>管理中心</p>
                </a>
            </li>
        </ul>
    </nav>
    <div class="new-health-copy">
    	<h1>健康领秀</h1>
        <p>&copy;111Yao</p>
    </div>
</footer>
-->
<script type="text/javascript">

var ii = 0;
	$(document).ready(function(){
		$(window).scroll(function(){
		
			if(isBottom(this) && ii == 0) {
//				ii +=1;
				var p_curPage=parseInt($("#p_curPage").val())+1;
				$("#p_curPage").val(parseInt($("#p_curPage").val())+1);
				var pageSize=$("#pageSize").val();
				$("#lazy_dh").show();
				$.ajax({
					url: "../leader/leaderArticle!ajaxLeaderPharmacyPageByCode.action",
					type: "POST",
					data: {"rs.p_curPage":p_curPage,"rs.pageSize":pageSize,"random":Math.random(),"code":$("#leaderCode").val()},
					async:false,
					success: function(data){
						var resultList = data.result;
						if(resultList!=undefined&&resultList.length>0){
							 for ( var i = 0; i < resultList.length; i++) {
							 	var leaderPha = resultList[i];
							 	var appendHtml = ""
								 	+"<li class='clearfix'>"
									+"	<a href='#' class='article-link'>"
									+"		<img src='"+leaderPha.abbreviation_picture+"'>"
									+"		<div class='article-introduce'>"
									+"			<p class='pharmacy-title'>"+(leaderPha.goods_name==null?"":leaderPha.goods_name)+"</p>"
									+"			<p class='pharmacy-rebate'>返佣：¥"+(leaderPha.rebate_amount==null?"0":leaderPha.rebate_amount)+"</p>"
									+"			<p class='pharmacy-price'>价格：¥"+(leaderPha.price==null?"0":leaderPha.price)+"</p>"
									+"		</div>"
									+"	</a>"
									+"	<a href='#' class='share'>分享</a>"
									+"	<i class='cut-line'></i>"
									+"</li>";
								 	
									
							 	$("#appendUl").append(appendHtml);
							 }
							 $("#lazy_dh").hide();
							 $("#noMore").attr("style","display:none");
							 $("#getMore").attr("style","display:block");
						} else{//没有了
						    $("#lazy_dh").hide();
							$("#getMore").attr("style","display:none");
							$("#noMore").attr("style","display:block");
						}
					},error:function(data){}
				});
			}
		
		});
});

</script>
</body>
</html>