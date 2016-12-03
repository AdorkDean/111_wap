<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="renderer" content="webkit" />
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="yes" name="apple-touch-fullscreen" />
    <meta content="fullscreen=yes,preventMove=no" name="ML-Config" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="format-detection" content="email=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <title>
    <#if leader.nickName?exists>
    	<#if leader.nickName != ''>
    	${leader.nickName?default('')}_的药房
    	<#else>
    	我的药房
    	</#if>
    <#else>我的药房</#if>
    </title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
   <!-- <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/new-health.js"></script>
    <script type="text/javascript" src="${base}/web/js/common.new.js"></script>
    <script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
	<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
	<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
	<script type="text/javascript" src="${base}/web/js/t_leader_pharmacy_list.js"></script>-->
	
 <link href="${base}/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
<#include "/static/inc/wap/common.html"> 
</head>
<body class="bg-white">
<#assign ui1 = "http://img.zdfei.com"/> 
<article class="new-health-box">
	<div class="new-health-article-list">
		<input type="hidden" id="leaderCode" name="leaderCode" value="${leader.code?if_exists}" >
    	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
        <div class="profit-tips clearfix">
        	<p>我的收益<b>${tmemberaccount.remainingAmount?default(0.00)}</b>壹贝</p>
            <p>［ 未包含待入账收益 ］</p>
            <a href="${base}/leader/leaderPharmacy!leaderPharmacyGoodsList.action" class="add-pro-btn">添加</a>
        </div>
        <#if recommendListSize == '1' >
        <div class="explain-title">
        	<span>推荐药品</span>
        </div>
    	<ul>
    	    <#list recommendList as leaderPha>
    	        <#if leaderPha_index == 0>
    				<input type='hidden' id="goodId" name="goodId" value="${leaderPha.goodsId?default('')}"/>
    				<input type='hidden' id="short_name" name="short_name" value="${leaderPha.short_name?default('')}"/>
    				<input type='hidden' id="main_title" name="main_title" value="${leaderPha.main_title?default('')}"/>
    				<input type='hidden' id="sub_title" name="sub_title" value="${leaderPha.sub_title?default('')}"/>
    				<input type='hidden' id="abbreviation_picture" name="abbreviation_picture" value="${leaderPha.abbreviation_picture?default('')}"/>
    			</#if>
	            <li class="clearfix">
	            	<a href="${base}/m/${leaderPha.goodsId?default('')}.html" class="article-link">
	                    <img src="${ui1}${leaderPha.abbreviation_picture?if_exists}">
	                    <div class="article-introduce">
	                        <p class="pharmacy-title">${leaderPha.goods_name?if_exists}</p>
	                        <p class="pharmacy-rebate">返佣：¥${leaderPha.rebate_amount?if_exists}</p>
	                        <p class="pharmacy-price">价格：¥${leaderPha.price?if_exists}</p>
	                    </div>
	                </a>
	                <a href="javascript:void(0);" class="share" onclick="getGoodsShareUrlJs(${leaderPha.goodsId?if_exists},'${leaderPha.goods_name?if_exists}','${leaderPha.main_title?if_exists}','${leaderPha.sub_title?if_exists}','${leaderPha.abbreviation_picture?if_exists}','${leader.code?if_exists}');showShareImg();">分享</a>
	                <i class="cut-line"></i>
	            </li>
            </#list>
        </ul>
        </#if>
        <div class="explain-title" style="margin-top:10px;">
        	<span>我的药品</span>
        </div>
    	<ul id="appendUl">
    	      <#list pw.result as leaderPha>
    	        <#if leaderPha_index == 0 &&recommendListSize =='0'>
    				<input type='hidden' id="goodId" name="goodId" value="${leaderPha.goodsId?default('')}"/>
    				<input type='hidden' id="short_name" name="short_name" value="${leaderPha.short_name?default('')}"/>
    				<input type='hidden' id="main_title" name="main_title" value="${leaderPha.main_title?default('')}"/>
    				<input type='hidden' id="sub_title" name="sub_title" value="${leaderPha.sub_title?default('')}"/>
    				<input type='hidden' id="abbreviation_picture" name="abbreviation_picture" value="${leaderPha.abbreviation_picture?default('')}"/>
    			</#if>
	            <li class="clearfix">
	            	<a href="${base}/m/${leaderPha.goodsId?default('')}.html" class="article-link">
	                    <img src="${ui1}${leaderPha.abbreviation_picture?if_exists}">
	                    <div class="article-introduce">
	                        <p class="pharmacy-title">${leaderPha.goods_name?if_exists}</p>
	                        <p class="pharmacy-rebate">返佣：¥${leaderPha.rebate_amount?if_exists}</p>
	                        <p class="pharmacy-price">价格：¥${leaderPha.price?if_exists}</p>
	                    </div>
	                </a>
	                <a href="javascript:void(0);" class="share" onclick="getGoodsShareUrlJs(${leaderPha.goodsId?if_exists},'${leaderPha.short_name?if_exists}','${leaderPha.main_title?if_exists}','${leaderPha.sub_title?if_exists}','${leaderPha.abbreviation_picture?if_exists}','${leader.code?if_exists}');showShareImg();">分享</a>
	                <a href="javascript:void(0);" class="delete-pro" onclick="javascript:deleteMyPharmacyGoods('${leaderPha.pharmacy_id?default(0)}')">删除</a>
	                <i class="cut-line"></i>
	            </li>
            </#list>
            
        </ul>
        <#if pw?exists&&pw.result?exists && pw.result?size gt 0>
	        <div class="no-more-tips" id="noMore" style="display:none">没有更多药品了~</div>
	        <!--加载提示-->
	        <div class="no-more-tips" id="getMore">向下拉加载更多药品~</div>
	    <#else>
	        <div class="no-more-tips" id="noMore">没有更多药品了~</div>
	        <div class="no-more-tips" id="getMore" style="display:none">向下拉加载更多药品~</div>
        </#if>
        <!--加载动画-->
        <div class="no-more-tips" id="lazy_dh" style="display:none"><img src="${base}/web/images/new-health/tips_img.png"/></div>
    </div>
</article>
<footer class="new-health-footer" >
	<nav class="new-health-footer-nav">
    	<ul>
        	<li>
            	<a href="../leader/leaderArticle!recommendArticle.action" >
                	<b class="my-article"></b>
                    <p>文章推荐</p>
                </a>
            </li>
            <li>
            	<a class="footer-current" href="../leader/leaderArticle!leaderPharmacy.action">
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
<#include "/WEB-INF/pages/member/leader/showshareimg.ftl">
<script type="text/javascript" src="${base}/web/js/??jquery.min.js,new-health.js,common.new.js,cookieUtil.js,alert.main.js,loadingbefore.js,jweixin-1.0.0.js,weiChatShare.js"></script>
</body>
<script type="text/javascript">
function deleteMyPharmacyGoods(pharmacy_goods_id)
{
		var result = confirm('您确定要删除该商品吗?');  
		    if(result){  
		         $.ajax({
				    url: "${base}/leader/leaderPharmacy!delPharmacyGoods.action",
				    type: "GET",
				 	data: {
				 		pharmacy_goods_id: pharmacy_goods_id,
				 	},
				  	cache: false,
			      	success: function(data) {
					if(data==1){
						$alert('warn','删除成功！',null,null);
						setTimeout(function()
						{
							location.href="/leader/leaderArticle!leaderPharmacy.action";
						}, 1500);
						return false;
					}else{
						$alert("warn","删除失败！",null,null)
						location.href="/leader/leaderArticle!leaderPharmacy.action";
						return false;
					}
				 }
			 	});  
		    }else{  
		        alert('删除失败！');  
		    }  
}
</script>

<script>
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
					url: "../leader/leaderArticle!ajaxLeaderPharmacyPage.action",
					type: "POST",
					data: {"rs.p_curPage":p_curPage,"rs.pageSize":pageSize,"random":Math.random()},
					async:false,
					success: function(data){
						var resultList = data.result;
						if(resultList!=undefined&&resultList.length>0){
							 for ( var i = 0; i < resultList.length; i++) {
							 	var leaderPha = resultList[i];
							 	var appendHtml = ""
								 	+"<li class='clearfix'>"
									+"	<a  href='/m/"+leaderPha.goodsId+".html' class='article-link'>"
									+"		<img src='http://img.zdfei.com"+leaderPha.abbreviation_picture+"'>"
									+"		<div class='article-introduce'>"
									+"			<p class='pharmacy-title'>"+(leaderPha.goods_name==null?"":leaderPha.goods_name)+"</p>"
									+"			<p class='pharmacy-rebate'>返佣：¥"+(leaderPha.rebate_amount==null?"0":leaderPha.rebate_amount)+"</p>"
									+"			<p class='pharmacy-price'>价格：¥"+(leaderPha.price==null?"0":leaderPha.price)+"</p>"
									+"		</div>"
									+"	</a>"
									+"	<a href='javascript:void(0);' onclick=\""+ "getGoodsShareUrlJs(" + leaderPha.goodsId +",'"+ leaderPha.short_name +"','"+ leaderPha.main_title+"','"+ leaderPha.sub_title+"','"+ leaderPha.abbreviation_picture +"'," + "'${leader.code?if_exists}'"  + ");showShareImg();\""+ "class='share'>分享</a>"
									+"	<a href='javascript:void(0);' onclick=\""+ "deleteMyPharmacyGoods(" + leaderPha.pharmacy_id + ")\""+ "class='delete-pro'>删除</a>"
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
	
	
	
	$(function()
			{
				//初始化微信
				try
				{
					var url = "/leader/leaderArticle!leaderPharmacy.action";
					initWxConfigCommon(url);
					//initWxConfig();
				}catch(e){}

				//获取领袖信息
				var $goodId = $("#goodId").val();
				var $leaderCode = $("#leaderCode").val();
				var $short_name = $("#short_name").val();
				var $main_title = $("#main_title").val();
				var $sub_title = $("#sub_title").val();
				var $abbreviation_picture = $("#abbreviation_picture").val();
				//($goodId,short_name,main_title,sub_title,abbreviation_picture,$leaderCode){
				
				
				//getGoodsShareUrl($goodId,$leaderCode);
				//getGoodsShareUrlJs($goodId,$short_name,$main_title,$sub_title,$abbreviation_picture,$leaderCode);
				
				

				
				

			});	
	
	$(".mask-ui").hide();
	$(".share-img").hide();
</script>
</html>