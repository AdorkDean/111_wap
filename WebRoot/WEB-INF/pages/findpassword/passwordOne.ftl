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
</head>

<body>
<#assign title="忘记密码">
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
    </article>
    <article class="login_cont"><a class="old_region" href="${base}/login/login!index.action"><b>已有账号？</b><span>马上登录</span></a></article>
</section>
 <#include "/static/inc/wap/footer.ftl">
</body>
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript">
$(function(){
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
				    url: "${base}/findpassword/findpassword!validateMobileCode.action",
				    type: "GET",
				 	data: { 
				 		username: $("#username").val(),
				 	},
				  	cache: false,
			      	success: function(data) {
					if(data==3){
						$alert("warn","请输入11位有效手机号！");
						return false;
					}else if(data==2){
						$alert("warn","该用户未绑定手机号！");
						return false;
					}else if(data==4){
						$alert("warn","手机号码未注册！");
						return false;
					}else{
						location.href="${base}/findpassword/findpassword!nextStep.action?username="+username;
					}		
					return true;
				}
			 });
	  });
});
</script>
</html>















