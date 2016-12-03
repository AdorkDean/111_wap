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
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
    <style rel="stylesheet" type="text/css">
    input[type="button"], input[type="submit"], input[type="reset"] {-webkit-appearance: none;}
    </style>
    <title>验证手机号</title>
</head>
<body  class="health-for-bg">
	<#assign title="绑定手机">
	<#include "/static/inc/wap/header.ftl">
<article class="health-basic" style="padding-top:45px;">
   <dl class="bind-iphone clearfix">
       <dt>手机号</dt>
       <dd>
           <input type="tel" id="moblie" placeholder="请输入您的手机号" class="input-tel"/>
           <div class="code-btn" id="code-btn"><input type="button" style="display:block; background:#19a69f;" value="获取验证码" id="getSmsCode"></div>
       </dd>
   </dl> 
    <dl class="bind-iphone clearfix">
        <dt>验证码</dt>
        <dd>
            <input type="tel" id="smsCode" placeholder="请输入您收到的验证码" class="input-tel"/>
        </dd>
    </dl>
    <div class="apply-for-btn">
        <a href="#" id="bindingButton" style=" background: #19a69f;">立即绑定</a>
    </div>
</article>
<#include "/static/inc/wap/footer.ftl">
</body>
</html>
<script type="text/javascript">
 		var count = 61;
		var timer ;
	function timeDesc(){
		if(count>0){
			count--;
			$("#getSmsCode").val(count+"秒后重新获取");
			$("#getSmsCode").css({"color":"#FFF","background":"#999999"});
			$("#getSmsCode").prop("disabled", "disabled");
		}else{
			$("#getSmsCode").val("获取验证码");
			$("#getSmsCode").css({"color":"#FFF","background":"#19a69f"});
			$("#getSmsCode").prop("disabled", "");
			count = 61;
			clearInterval(timer);
			timer = "";
		}
	}
	$(function(){
		$("#getSmsCode").click(function(){
		var pattern = new RegExp(/^[1][3,4,7,5,8][0-9]{9}$/);
		var $moblie = $("#moblie").val();
			if($moblie == ''|| $moblie==null ){
				$alert("warn","请输入手机号！");
				return false;
            }
            if(!pattern.test($.trim($moblie))){
            	$alert("warn","请输入手机号！");
				return false;
            }
	    	if(count == 61){
				$.ajax({
					url: "${base}/login/bindingmobile!validateMobileCode.action",
					type: "GET",
					data: {
						moblie: $moblie,
					},
					cache: false,
					success: function(data) {
						if(data==0){
						timer = window.setInterval("timeDesc()", 1000);
							$alert("warn","验证码发送成功！");
						}else if(data==2){
							$alert("warn","手机号已注册或者已绑定！");
						}
					}
				});
			}
		});
	  $("#bindingButton").click(function(){
	 		var $moblie = $("#moblie").val();
			var $smsCode = $("#smsCode").val();
			var pattern = new RegExp(/^[1][3,4,7,5,8][0-9]{9}$/);
			if($moblie == ''|| $moblie==null ){
				$alert("warn","请输入手机号！");
				return false;
            }
            if(!pattern.test($.trim($moblie))){
            	$alert("warn","请输入手机号！");
				return false;
            }
			if($smsCode == ''|| $smsCode==null ){
				$alert("warn","请输入验证码！");
				return false;
            }
			 $.ajax({
				    url: "${base}/login/bindingmobile!bindingMobile.action",
				    type: "GET",
				 	data: { 
				 		moblie:$moblie,
				 		smsCode: $smsCode,
				 	},
				  	cache: false,
			      	success: function(data) {
					if(data==0){
						$alert("warn","手机号已注册或者已绑定！");
						return false;
					}else if(data==1){
						$alert("warn","短信验证码不正确！");
						return false;
					}else if(data==2){
						location.href = "${base}/login/bindingmobile!success.action";
					}		
					return true;
				}
			   });
			 });
});
</script>