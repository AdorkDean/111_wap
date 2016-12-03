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
</head>
<body>
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<#assign title="优惠劵">
<#if bean?exists && bean?has_content>
<#include "/static/inc/wap/header.ftl">
<article class="member-main">
    <#list bean?if_exists as coup>
    <div class="add-coupon">
        <div class="add-coupon-top">
            <div class="coupon-img"><img src="${base}/web/images/member_center_icon04.png" alt=""/></div>
            <div class="coupon-txt">
                <p>${coup.NAME?default('')}</p>
                <b>有效期：${coup.begin_Date?string('yyyy-MM-dd')}至${coup.end_date?string('yyyy-MM-dd')}</b>
            </div>
        </div>
        <div class="add-coupon-bottom"><span>111医药馆　111yao.com</span></div>
    </div>
    </#list>
    <div class="follow-btn"><a href="${base}/member/coupon!addCou.action" id="popup-btn">添加优惠券</a></div>
</article>
<#include "/static/inc/wap/footer.ftl">
<#else>
<#include "/static/inc/wap/header.ftl">
    <article class="member-no-net">
	    <dl>
			<dt><img src="${base}/web/images/member-icon03.png"></dt>
			<dd>
	        	<p>您还没有可用的优惠券哟~</p>
	            <div class="follow-btn"><a href="${base}/member/coupon!addCou.action" id="popup-btn">添加优惠券</a></div>
	        </dd>
		</dl>
	</article>
<div class="footer-bottom">
<#include "/static/inc/wap/footer.ftl">
</div>
</section>
</#if>
</body>
</html>
<script type="text/javascript">
$(function(){
	  $("#codeButton").click(function(){
			var code = $("#code").val();
            if( $.trim(code)==null || $.trim(code)==""){
			$alert("warn","您没有输入兑换码!");
			return false;
	      }
			 $.ajax({
				    url: "${base}/member/coupon!add.action",
				    type: "GET",
				 	data: { 
				 		code: code,
				 	},
				  	cache: false,
			      	success: function(data) {
					if(data==1){
						$alert("warn","优惠券未启用！");
						return false;
					}else if(data==5 || data==6){
						$alert("warn","优惠劵不存在！");
						return false;
					}else if(data==4){
						$alert("warn","优惠券已使用！");
						return false;
					}else{
						location.href="${base}/member/coupon!list.action";
					}		
					return true;
				}
			 });
	  });
});
</script>
<link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />