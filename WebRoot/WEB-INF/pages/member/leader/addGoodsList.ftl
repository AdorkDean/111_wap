<!doctype html>
<html lang="zh-CN">
<head>
 	<#include "/WEB-INF/pages/inc/taglibs.ftl">
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
    <title>添加商品</title>
    <link href="/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
    
    <#include "/static/inc/wap/common.html"> 
</head>
<body class="bg-white">
<#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://img.zdfei.com"/> 
<article class="new-health-box">
	<div class="new-health-article-list">
	<form name="form1" id="form1"  action="${base}/leader/leaderPharmacy!leaderPharmacyGoodsList.action" method="post">
        <input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(0)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(8)}" id="pageSize">
    	<div class="new-health-search clearfix">
        	<div class="new-health-search-bar">
            	<input type="search" name="name" placeholder="请输入搜索的内容..." value="${name?default('')}" id="goodName">
                <a href="#" class="new-health-search-btn" onclick="submitForm()"></a>
            </div>
        </div>
     </form> 
    	<ul id="appendUl">
    	<#list pw.result?if_exists as resc>
        	<li class="clearfix">
            	<a href="${base}/m/${resc.goods_id?default(0)}.html" class="article-link">
                    <img src="${ui1}${resc.abbreviation_picture?default('')}">
                    <div class="article-introduce">
                        <p class="pharmacy-title">${resc.goods_name?default('')}</p>
                        <p class="pharmacy-rebate">返佣：${resc.rebate_amount?default(0)}</p>
                        <p class="pharmacy-price">价格：¥${resc.wap_price?default(0)}</p>
                    </div>
                </a>
                <a href="#" class="add_product" onclick="javascript:addMyPharmacyGoods('${resc.goods_id?default(0)}')">添加</a>
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
<script type="text/javascript" src="/web/js/??jquery-1.7.2.js,new-health.js,common.new.js,cookieUtil.js,alert.main.js,loadingbefore.js,t_leader_addGoods_list.js"></script>
<script>
  $().ready(function() {
	});		
	function submitForm(){
		$('#p_curPage').val(1);
	 	$("#form1").submit();
	}
</script>
<script type="text/javascript">
function addMyPharmacyGoods(goods_id)
{		alert("您确定要添加该商品吗?");
		$.ajax({
				    url: "${base}/leader/leaderPharmacy!leaderPharmacyAddGoods.action",
				    type: "GET",
				 	data: {
				 		goodsId: goods_id,
				 	},
				  	cache: false,
			      	success: function(data) {
					if(data==1){
						$alert("warn","添加成功","111医药馆提示您",null);
						return false;
					}else if(data==2){
						$alert("warn","已添加过此商品！","111医药馆提示您",null);
						return false;
					}else{
						$alert("warn","您已经超出领秀药房商品上限"+data+"个","111医药馆提示您",null);
						return false;
					}
				 }
			 });
}
</script>
</body>
</html>