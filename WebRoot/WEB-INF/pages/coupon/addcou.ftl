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
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
</head>
<body>
<#assign title="添加优惠劵">
<#include "/static/inc/wap/header.ftl">
<article class="member-main">
    <div class="verification-tel">
        <div class="verification-tel-box">
            <ul>
                <li>
                    <div class="box-code"><input type="text" name="code" id="code" placeholder="请输入您的兑换码"/></div>
                </li>
                <li>
                    <div class="for-btn">
                        <a href="${base}/member/coupon!list.action" class="close-out">取消</a>
                    </div>
                    <div class="for-btn last-btn">
                        <a href="#" class="close-out" id="codeButton">确认</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</article>
<div class="footer-bottom">
<#include "/static/inc/wap/footer.ftl">
</div>
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