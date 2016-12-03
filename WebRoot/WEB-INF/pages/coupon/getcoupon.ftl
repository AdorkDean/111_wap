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
    <title>领券专区</title>
    <script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <script src="${base}/web/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
</head>

<body>
<#assign title="领券专区">
<#include "/static/inc/wap/header.ftl">
<article class="get-coupon">
	<#list cpList?if_exists as coup>
		<input type="hidden"  id="couponId_${coup_index}" name="couponId_${coup_index}" value="${coup.id?default(0)}"/>
		<#if coup.isget?string =="0">
	   	 <div class="get-coupon-part get-down">
	        <div class="get-coupon-details">
	            <label for="">${coup.name?default('')}</label>
	            <label for="">有效期：${coup.start_time?string('yyyy-MM-dd')}至${coup.end_time?string('yyyy-MM-dd')}</label>
	        </div>
	        <div class="get-coupon-num">
	            <label style="font-size:30px;"><b>￥</b>${coup.dis_price?default(0)}</label>
	            <label style="font-size:12px; margin-bottom:5px;">活动券</label>
	            <label style="font-size:15px;"><span id="getcoupon" onclick="javascript:getcoupon(${coup.id?default(0)})">领取</span></label>
	        </div>
	    </div>
	   <#else>
	    <div class="get-coupon-part get-already">
	        <div class="get-coupon-details">
	            <label for="">${coup.name?default('')}</label>
	            <label for="">有效期：${coup.start_time?string('yyyy-MM-dd')}至${coup.end_time?string('yyyy-MM-dd')}</label>
	        </div>
	        <div class="get-coupon-num">
	            <label style="font-size:30px;"><b>￥</b>${coup.dis_price?default(0)}</label>
	            <label style="font-size:12px; margin-bottom:5px;">活动券</label>
	            <label style="font-size:15px;"><span >已领</span></label>
	        </div>
	    </div>    
	 </#if>
    </#list>
    <#include "/static/inc/wap/footer.ftl">
</article>
<div class="get-shade-bg">
    <div class="get-shade-coupon">
        <span class="get-shade-icon"></span>
        <label for="" style="margin-bottom:7px;">领取成功!</label>
        <label for="">感谢您的参与，祝您购物愉快</label>
    </div>
</div>
</body>
<script type="text/javascript">
function getcoupon(couponId)
{
			var rdUrl="${base}/coupon/yjcoupon!getCoupon.action";
			 $.ajax({
				    url: "${base}/coupon/yjcoupon!zqBindingCoupon.action",
				    type: "GET",
				 	data: {
				 		couponId: couponId,
				 	},
				  	cache: false,
			      	success: function(data) {
					if(data==1){
						$alert("warn","领取成功！");
						location.href="/coupon/yjcoupon!getCoupon.action";
						return false;
					}else if(data==2){
						$alert("warn","用户已经领过该优惠券！");
						return false;
					}else if(data==3){
						$alert("warn","优惠劵已经发放完毕！");
						return false;
					}else if(data==4){
						$alert("warn","服务器错误！");
						return false;
					}else if(data==5){
						$alert("warn","无效优惠劵！");
						return false;
					}else if(data==6){
						$.ajax({
							url: "${base}/coupon/yjcoupon!checkLogin.action",
							type: "GET",
							cache: false,
							success: function(data) {
								if(data==1){
									location.href="${base}/login/login!index.action?redirectUrl="+encodeURIComponent(rdUrl);
									return false;
									}	
								}
						});	
					}
				return true;
				}
			 });
}
</script>
</html>
