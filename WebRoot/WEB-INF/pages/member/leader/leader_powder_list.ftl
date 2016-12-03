
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
    <title>我的秀粉</title>
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
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://img.zdfei.com"/> 
</head>

<body class="bg-white">
<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
<input type="hidden" name="orderby" value="${orderby?default(1)}" id="orderby">


<article class="my-pharmacy-fans">
	<div class="my-fans-total">秀粉总计<b>${powder_count?default(0)}</b></div>
    <ul class="sort-way clearfix">
    	<li><a href="/leader/leaderArticle!leaderPowderList.action?orderby=1" <#if orderby=='1'>class="curr"</#if>>下单次数</a></li>
        <li><a href="/leader/leaderArticle!leaderPowderList.action?orderby=2" <#if orderby=='2'>class="curr"</#if>>累计收益</a></li>
    </ul>
    <ul class="my-fans-list" id="appendUl">
        <#list pw.result as powder>
	          <li class="clearfix">
	        	<a href="#">
	                <img src="<#if powder.head_portrait?exists && powder.head_portrait?has_content><#if !powder.head_portrait?starts_with('http:')>${ui1}</#if>${powder.head_portrait?default('')}<#else>${ui1}/static/image/temp/20160127/73569e0fe07f5e60234179baddec94eb.jpg</#if>">
	                <div class="my-fans-info">
	                    <p class="fans-name"><#if powder.nick_name?exists><#if powder.nick_name?length gt 6>${powder.nick_name[0..6]}***<#else>${powder.nick_name?default('')}</#if> </#if></p>
	                    <p>累计收益 ${powder.powder_money?string('###0.00')}</p>
	                    <p>下单次数 ${powder.order_count?default('')}</p>
	                    <p class="fans-time">成为秀粉时间：${powder.register_date?default('')}</p>
	                </div>
	            </a>
	            <i class="cut-line"></i>
	        </li>
        </#list>
    </ul>
    <#if pw?exists&&pw.result?exists && pw.result?size gt 0>
	    <div class="no-more-tips" id="noMore" style="display:none">没有更多秀粉了~</div>
	    <!--加载提示-->
	    <div class="no-more-tips" id="getMore">向下拉加载更多秀粉~</div>
    <#else>
	    <div class="no-more-tips" id="noMore">没有更多秀粉了~</div>
	    <!--加载提示-->
	    <div class="no-more-tips" id="getMore" style="display:none">向下拉加载更多秀粉~</div>
    </#if>
    <!--加载动画-->
    <div class="no-more-tips" id="lazy_dh" style="display:none"><img src="${base}/web/images/new-health/tips_img.png"/></div>
</article>
<footer class="new-health-footer">
	<nav class="new-health-footer-nav">
    	<ul>
        	<li>
            	<a href="../leader/leaderArticle!recommendArticle.action">
                	<b class="my-article"></b>
                    <p>文章推荐</p>
                </a>
            </li>
            <li>
            	<a href="../leader/leaderArticle!leaderPharmacy.action">
                	<b class="my-pharmacy"></b>
                    <p>我的药房</p>
                </a>
            </li>
            <li>
            	<a href="../leader/leaderCenter!index.action">
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
<script type="text/javascript">
$(function(){
	$("input[name='rs.p_curPage']").val(1);
	$("input[name='rs.p_curSize']").val(10);
});


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
					url: "../leader/leaderArticle!ajaxLeaderPowderList.action",
					type: "POST",
					data: {"rs.p_curPage":p_curPage,"rs.p_pageSize":pageSize,"random":Math.random(),"orderby":$("#orderby").val()},
					async:false,
					success: function(data){
						var resultList = data.result;
						if(resultList!=undefined&&resultList.length>0){
							 for ( var i = 0; i < resultList.length; i++) {
							 	var leaderPha = resultList[i];
							 	var appendHtml = "";
							 	appendHtml+="<li class=\"clearfix\">";
						        appendHtml+="	<a href=\"#\">";
						        if(leaderPha.head_portrait!=null&&leaderPha.head_portrait!=''){
						           if(leaderPha.head_portrait.substring(0,5)=='http:'){
						              appendHtml+="<img src=\""+leaderPha.head_portrait+"\">";
						           }else{
						               appendHtml+="<img src=\"${ui1}"+leaderPha.head_portrait+"\">";
						           }
						        }else{
						          appendHtml+="<img src=\"${ui1}/static/image/temp/20160127/73569e0fe07f5e60234179baddec94eb.jpg\">";
						        }
						        appendHtml+="       <div class=\"my-fans-info\">";
						        if(leaderPha.nick_name!=null&&leaderPha.nick_name.length>6){
						            appendHtml+="            <p class=\"fans-name\">"+leaderPha.nick_name.substring(0,6)+"***</p>";
						        }else{
						           appendHtml+="            <p class=\"fans-name\">"+leaderPha.nick_name+"</p>";
						        }
						       
						        appendHtml+="            <p>累计收益 "+parseFloat(leaderPha.powder_money).toFixed(2)+"</p>";
						        appendHtml+="            <p>下单次数 "+leaderPha.order_count+"</p>";
						        appendHtml+="            <p class=\"fans-time\">成为秀粉时间："+leaderPha.register_date+"</p>";
						        appendHtml+="        </div>";
						        appendHtml+="    </a>";
						        appendHtml+="    <i class=\"cut-line\"></i>";
						        appendHtml+="</li>";
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
