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
    <title>注册</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
    <style>
    	html,body{ width:100%; height:100%; position:relative;}
    	#layer{ display:none; width:100%; height:100%; position:absolute; left:0; top:0; z-index:999; }
 		#layer img{ width:100%;}
 		#layer span{ position:absolute; left:10px; top:12px; width:20px; height:20px; text-align:center; border:1px solid black; line-height:20px; border-radius:10px; color:black;}
 		#layer p{ width:44px; height44px; position:absolute; top:0; right:0; }
    </style>
</head>

<body style="position:relative;">
<#assign title="注册">
<#include "/static/inc/wap/header.ftl">
<section class="pt45">
    <article class="code-tips">
        <p>我们将发送验证码到你的手机。</p>
    </article>
    <article class="login_id">
        <ul>
            <li id="border_no"><span>手机号</span><input class='infocus' placeholder="请填写注册手机号" name="username" id="username" type="tel"/></li>
        </ul>
    </article>
    <article class="login_btn">
    <a href="javascript:void(0)" id="nextStep1" class="greybgcolor">下一步</a>
  	<!--<b><input class="greybgcolor" type="button" id="nextStep1" value="下一步" /></b>-->
    </article>
    <article class="login_cont"><a class="old_region" href="${base}/login/login!index.action"><b>已有账号？</b><span>马上登录</span></a></article>
</section>
 <#include "/static/inc/wap/footer.ftl">
 <div id="layer" class="layer"><p><span>X</span></p><img src="${base}/web/images/layer.png"></div>
</body>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/input.js"></script>
<script type="text/javascript">
	$("#layer").on("click","img",function(){
		$("#layer").hide();
	})
</script>
<script type="text/javascript">
$(function(){
	//if (getCookie("leader") != null) {
	//	 $("#layer").show();
	//}
	  $("#nextStep1").click(function(){
		var username = $("#username").val();
		var pattern = new RegExp(/^[1][3,4,7,5,8][0-9]{9}$/);
		var isPass =  pattern.test($.trim(username));
            if( $.trim(username)==null || $.trim(username)==""){
				$alert("warn","请输入11位有效手机号！");
				return false;
			}
			if(!isPass){
				$alert("warn","请输入11位有效手机号！");
				return false;
			}
			$.ajax({
				    url: "${base}/register/register!checkUserNameIsExist.action",
				    type: "GET",
				 	data: { username: username},
				  	cache: false,
			      	success: function(data) {
					if(data==0){
					$alert("warn","该手机号已被注册，请试试直接登录！");
					return false;
					}else{
						$.ajax({
				   		 url: "${base}/register/register!validateMobileCode.action",
				    	type: "GET",
				 		data: { 
				 		username: $("#username").val(),
				 		},
				  		cache: false,
			      		success: function(data) {
						if(data==1){
							$alert("warn","请输入11位有效手机号！");
							return false;
						}else{
							$alert("warn","验证码发送成功！");
							location.href="${base}/register/register!nextStep.action?username="+username;
						}		
						return true;
					}
				 });
					}
				  }
			 });
	       
	  });
});
</script>
</html>















