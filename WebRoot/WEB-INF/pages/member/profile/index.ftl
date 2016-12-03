<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="renderer" content="webkit">
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
    <title>个人中心</title>
    <link href="${base}/web/css/??new_footer.css,member-center.css,profile.css" rel="stylesheet" type="text/css" />
</head>
<body>
<section class="common-main">
	<#assign title="个人中心">
	<#include "/static/inc/wap/header.ftl">
    <header class="member-top">
        <div class="member-top-info">
        	<div class="head-portrait">
           		 <img src="<#if headPortrait?exists && headPortrait?has_content><#if !headPortrait?starts_with('http:')>${ui1}</#if>${headPortrait?default('')}<#else>${ui1}/static/image/temp/20160127/73569e0fe07f5e60234179baddec94eb.jpg</#if>" alt=""/>
	        </div>
	        <div class="head-name">
	           	<span style="width:200px;white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow: hidden;"><#if nickName?exists>${nickName?default('111医药馆会员')}<#elseif realName?exists>${realName?default('')}<#elseif member.userName?exists>${member.userName?default('')}<#else>111医药馆会员</#if></span>
            	<a href="${base}/member/jifen!getThirtyJifen.action" id="member_point">积分：...</a>
	        </div>
        </div>
        <ul class="member-wait clearfix">
            <li><a href="${base}/member/order.action?type=2"><b class="w-01"><#if unpaidCount?default(0)!=0>
            <#if unpaidCount?default(0)&gt;99>
            <i class="order-number num-3">99+</i>
            <#elseif unpaidCount?default(0)&lt;99&&unpaidCount?default(0)&gt;9>
            <i class="order-number num-2">${unpaidCount?default(0)}</i>
            <#else>
            <i class="order-number num-1">${unpaidCount?default(0)}</i>
            </#if>
            </#if></b>待支付</a></li>
            <li><a href="${base}/member/order.action?type=3"><b class="w-02"></b>待收货</a></li>
            <li class="no-bor"><a href="${base}/member/order.action?type=4"><b class="w-03"></b>待评论</a></li>
        </ul>
    </header>
    <article class="member-main member-padding">
        <ul class="member-list">
            <li><a href="${base}/member/orderList!getOrderList.action"><b class="iconfont">J</b>全部订单</a></li>
            <li><a href="${base}/member/return.action"><b class="iconfont">J</b>我的售后</a></li>
        </ul>
        <ul class="member-list">
            <li><a href="JavaScript:void(0)" onclick="doit()"><span></span><b class="iconfont">J</b>健康领秀</a></li>
            <li><a href="${base}/member/collect!goods.action"><span></span><b class="iconfont">J</b>商品收藏</a></li>
            <li><a href="${base}/member/collect!brand.action"><span></span><b class="iconfont">J</b>品牌关注</a></li>
        </ul>
        
        <ul class="member-list">
            <li><a href="${base}/member/coupon.action"><span></span><b class="iconfont">J</b>我的卡券</a></li>
            <li><a href="${base}/pre/prescription!getPrescriptionList.action"><span></span><b class="iconfont">J</b>我的病历</a></li>
            <li><a href="${base}/help/about_us.html"><span></span><b class="iconfont">J</b>关于我们</a></li>
        </ul>
        <div class="follow-btn"><a href="${base}/login/login!logout.action">退出登录</a></div>
    </article>
	<#include "/static/inc/wap/new_footer.html">
</section>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/input.js"></script>
<style>
    .back-to-top {bottom:55px;display:none;} 
    .kefu {display: none;}
    .follow-btn {margin-bottom:55px;}
</style>
<script type="text/javascript">
$(function() 
{
	//异步刷新会员积分
	$.ajax(
	{
		url:"${base}/member/profile!ajaxMemberInfo.action",
		type:"POST",
		datatype:"json",
		success:function(data) 
		{
			if(data != null) 
			{ 
				$("#member_point").html("积分："+data.integral+" >");
			}
		},
		error:function(){}
	});
	//标签定位
	$("#tab_class_4").addClass("current");
}); 

function doit(){
				$.ajax({
							url: "${base}/lsUser/lsUser!isLeader.action",
							type:"post",
							async:false,
							cache:false,
							success: function(data) {
								if(data==1){//领秀存在
									location.href = "${base}/leader/leaderArticle!leaderPharmacy.action";
								}else{//领秀不存在移出session
									location.href = "${base}/leader/leaderArticle!leaderPharmacy.action";
								}
							}
				});
	}	         
</script>

