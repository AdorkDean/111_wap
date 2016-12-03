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
    <title>设置登录密码</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
</head>

<body>
<#assign title="设置登录密码">
<#include "/static/inc/wap/header.ftl">
<section class="login-main">
    <article class="verification_info">
        <span>验证短信已发送至${username?default('')}</span>
    </article>
    <article class="login_id m_btm">
        <ul>
            <li id="border_no"><span>验证码</span><input class='infocus' placeholder="短信验证码" name="mobile_code" id="mobile_code" type="tel"/>
            <b class="getcode redcolor"><input style="display:block;" type="button" id="getSmsCode" value="获取验证码" /></b>
           </li>
        </ul>
    </article>
    <article class="login_id">
        <ul>
            <li><span>设置密码</span><input class='infocus' placeholder="6-20位字母、数字组合" type="password" name="password" id="password"/></li>
            <li><span>确认密码</span><input class='infocus' placeholder="再次输入密码" type="password" name="rePassword" id="rePassword"/></li>
        </ul>
    </article>
    <article class="login_btn">
        <a href="javascript:void(0)" id="registerButton">注册</a>
    </article>
    <article style="height:24px; line-height:24px;" class="login_agre">
        <label class="checklabel" for="login_sel_d"></label><input type="checkbox" checked="checked" id="login_sel_d" class="login_sel" style="display: none;">
        <a class="old_agre" href="${base}/register/register!agree.action"><b>已阅读并同意</b><span style="color:#00b4b8;">《111医药馆用户注册协议》</span></a>
    </article>
</section>
	<#include "/static/inc/wap/footer.ftl">
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
</body>
<script type="text/javascript">
		var count = 61;
		var timer ;
function timeDesc(){
	if(count>0){
		count--;
		$("#getSmsCode").val(count+"秒后重新获取");
		$("#getSmsCode").css("color","#9d9d9d");
		$("#getSmsCode").prop("disabled", "disabled");
	}else{
		$("#getSmsCode").val("获取验证码");
		$("#getSmsCode").css("color","#ff4a4a");
		$("#getSmsCode").prop("disabled", "");
		count = 61;
		clearInterval(timer);
		timer = "";
	}
}
	<#if smsSuccess?exists>
	  	 <#if smsSuccess?default('')=="smsSuccess">
	  		timer = window.setInterval("timeDesc()", 1000);
	  		${session.removeAttribute("smsSuccess")}
	   	</#if>
	</#if>
$(function(){
		var $password = $("#password");
		var $rePassword = $("#rePassword");
		$("#getSmsCode").click(function(){
	    	if(count == 61){
				$.ajax({
					url: "${base}/register/register!validateMobileCode.action",
					type: "GET",
					data: {
						username: ${username?default('')},
					},
					cache: false,
					success: function(data) {
						if(data==0){
						timer = window.setInterval("timeDesc()", 1000);
							$alert("warn","验证码发送成功！");
						}
					}
				});
			}
		});
	  $("#registerButton").click(function(){
			var password = $("#password").val();
			var username = ${username?default('')};
			var rePassword = $("#rePassword").val();
			var mobile_code = $("#mobile_code").val();
			var mobile_code = $("#mobile_code").val();
			reg = /^(?!(?:\d*$))[A-Za-z0-9]{6,20}$/;
			if(mobile_code == ''|| mobile_code==null ){
				$alert("warn","请输入验证码！");
				return false;
            }
            if(password == ''|| password==null ){
				$alert("warn","请输入有效的密码！");
				return false;
            }
            if(!reg.test(password)){
				$alert("warn","由6-20位字母，数字，符号两种组合以上!");
	            return false;
	      	}
           	if(rePassword != password){
				$alert("warn","两次输入密码不一致！");
	       		return false;
        	}
        	if(!$("#login_sel_d").attr("checked")){
	 			$alert("warn","请您阅读并勾选同意“《111医药馆用户使用条款》”");
	 			return false;
 			}
 			var url="";
   			url="${base}/register/register!mobileRegister.action"
			 $.ajax({
				    url: url,
				    type: "GET",
				 	data: { 
				 		password: $("#password").val(),
				 		username:username,
				 		mobile_code: $("#mobile_code").val(),
				 	},
				  	cache: false,
			      	success: function(data) {
					if(data==0){
						$alert("warn","用户已经存在！");
						return false;
					}else if(data==3){
						$alert("warn","短信验证码不正确！");
						return false;
					}else{
						<#if redirectUrl?exists>
							location.href = "${redirectUrl}";
						<#else>
							location.href = "${base}/";
						</#if>
					}		
					return true;
				}
			   });
			 });
});
</script>
</html>















