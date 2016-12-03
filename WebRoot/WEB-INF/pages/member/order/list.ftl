
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
    <title>
    	<#if type== "2">
    		<#assign title="待支付订单">
    		待支付订单
		<#elseif type=="3">
			<#assign title="待收货订单">
			待收货订单
		<#elseif type=="4">
			<#assign title="待评价订单">
			待评价订单
		<#else>
			<#assign title="全部订单">
			全部订单	    		
    	</#if>
    </title>
</head>

<body>
<#if pw?exists && pw.result?has_content>
<section class="common-main">
	
	<#if type== "2" || type== "3" || type== "4" || tag=="">
    	<#include "/static/inc/wap/header.ftl">		
	<#else>
		<#assign www = "http://m.111yao.com"/>
		<#assign ui = "http://ui.111yao.com"/>  
		<#assign ui1 = "http://img.zdfei.com"/>  
		<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
		<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
		<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
		<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
		<header class="header">
		    <a href="${base}/member/profile.action"  class="iconfont top-left-btn">B</a>
		    <h2 class="header-title">${title}</h2>
		    <a href="${www}" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
		</header>	    		
	</#if>
	
    <article class="order-box">
        <section>
            <ul class="colloctgoods_cont">
            	<#list pw.result?if_exists as resc>
              	<li class="colloctgoods clearfix">
                    <a href="${base}/member/order!detail.action?id=${resc.id?default('')}">
                        <dl>
                            <dt><img src="${ui1}${resc.abbreviationPicture?default('')}"/></dt>
                            <dd>
                            	<p><span>订单编号</span><b>${resc.orderSn?default('')}</b></p>
                                <p><span>订单金额</span><b>￥${currency(resc.payableAmount?default(0))}</b></p>
                                <p><span>订单状态</span>
                                	<b>
                                		<#if resc.orderStatus?exists && resc.orderStatus == 0>
                                			待支付		                                      		
				                    	</#if>                                    	
				                    	<#if resc.orderStatus?exists && resc.orderStatus == 1>
                                			待发货	                                      		
				                    	</#if>
				                    	<#if resc.orderStatus?exists && resc.orderStatus == 2>
				                        	待收货
				                    	</#if>
				                    	<#if resc.orderStatus?exists && resc.orderStatus == 3>
				                        	已完成
				                    	</#if>
				                    	<#if resc.orderStatus?exists && resc.orderStatus == 4>
				                    		已取消
				                    	</#if>
				                    	<#if resc.orderStatus?exists && resc.orderStatus == 5>
				                    		已过期
				                    	</#if>
                    			</b></p>
                            </dd>
                        </dl>
                    </a>
                                      
                    <div class="colloctgoods_button">
                    <#if type== "2">
			    		<a class="colloctgoods_bluebtn" href="${base}/member/order!detail.action?id=${resc.id?default('')}">去支付</a>
					<#elseif type=="3">
						<a class="colloctgoods_yellowbtn" href="${base}/member/order!logistics.action?id=${resc.id?default('')}">查看物流</a>
						<a class="colloctgoods_bluebtn complete" href="javascript:void(0);" val="${resc.id?default('')}">确认收货</a>
					<#elseif type=="4">
						<a class="colloctgoods_bluebtn" href="${base}/member/comment.action?id=${resc.id?default('')}">去评论</a>					
			    	</#if>
			    	</div>
                </li>
                </#list>                
            </ul>
        </section>
    </article>
    <#include "/static/inc/wap/footer.ftl">
</section>
<link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
<#else>
<section class="common-main">
    <#include "/static/inc/wap/header.ftl">
    <article class="order-box">
        <article class="comment_auto">
                <dl>
                    <dt><img src="${base}/web/images/comment.svg"/></dt>
                    <dd>
                    	<p>
	                	<#if type== "2">
			    		暂无待支付订单
						<#elseif type=="3">
						暂无待收货订单
						<#elseif type=="4">
						暂无待评价订单
						<#else>
						暂时没有订单	    		
			    		</#if>
	    				</p>
	    			</dd>
                </dl>
        </article>
    </article>
    <div class="footer-bottom">
	<#include "/static/inc/wap/footer.ftl">
	</div>
</section>
</#if>
</body>
</html>
<script type="text/javascript" charset="utf-8">
$(function(){
	<#if pw.pageInfo.pages?exists && pw.pageInfo.pages gt 1>
	var page = 1;
	var totalPage = ${pw.pageInfo.pages?default(0)};
	$(window).scroll(function(){
		if(isLoad(this)){
			
			if(page < totalPage){
				page += 1;
				$.ajax({
					url: "${base}/member/order!more.action",
					type: "GET",
					data:{
						"type":"${type?default('1')}",
						"rs.p_curPage":page
					},
					cache: false,
					success: function(data){
						$(".colloctgoods_cont").append(data);
					}
				});
			}
			
		}
	});
	</#if>
	<#if type== "3">
	//确认收货
	$(".complete").click(function(){
		var def = $(this);
		if(confirm("确认收货?")){
			$.ajax({
		        type: "post",
		        cache: true,	
		        async: false,	        
		        url: "${base}/member/order!complete.action",
		        data: {
		        	id:def.attr("val")
		        },	            
		        success: function(data, textStatus){
		        	alert(data.message);
		        	if(data.flag){
		        		location.reload();
		        	}
		        }
		    });
		}
	});
	</#if>
});
</script>
<script type="text/javascript" src="${base}/web/js/common.js"></script>
