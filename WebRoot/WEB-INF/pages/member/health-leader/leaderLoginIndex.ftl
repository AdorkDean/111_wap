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
    <title>领秀登录</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/new-health.js"></script>
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
</head>
<body class="bg-white">
<article class="new-health-box">
	<article class="new-health-login">
    	<div class="new-health-login-bg">
        	<img src="${base}/web/images/new-health/login_bg.jpg">
        </div>
    	<ul class="new-health-login-list new-health-account">
        	<li>
            	<b></b>
                <p><input type="tel" placeholder="请输入手机号" id="leaderMobile"/></p>
            </li>
           <li>
            	<b></b>
                <p><input type="tel" placeholder="请输入验证码" id="mobileCode"/></p>
                <div id="code-btn-box">
                	<a class="get-code" id="getCode">获取验证码</a>
                    <span class="code-countdown" style="display:none;">60s</span>
                </div>
            </li>
        </ul>
        <div class="new-health-btn" style="margin-top:20px">
        	<a href="JavaScript:void(0)" id="leader_submit">登 录</a>
        </div>
        <div class="new-health-login-tips">
        	<a href="${base}/findpassword/findpassword!index.action" class="forget-health-code fr">忘记密码？</a>
            <p>还没有账号？<a href="${base}/newleader/newleader!skipLeader.action" class="new-health-register-now">立即注册</a></p>
        </div>
    </article>
</article>
</body>
<script type="text/javascript">
var getCode = document.getElementById("getCode");
     var timer = null;
     var count = 60;
     function timeDesc() {
        if (count > 0) {
            count--;
            getCode.innerHTML = count + "s";
            getCode.style.background = "#d7d7d7";
        } else {
            getCode.innerHTML = "获取验证码";
            getCode.style.background = "#fe4310";
            getCode.style.color="#FFFFFF";
            count = 60;
            window.clearInterval(timer);
            timer = "";
        }
    }
$().ready(function() {
	//领秀登录
	var $username = $("#leaderMobile");
	var $mobileCode = $("#mobileCode");
	var pattern = new RegExp(/^[1][3,4,7,5,8][0-9]{9}$/);
	
	 $("#getCode").click(function(){
		var isPass =  pattern.test($.trim($username.val()));
		if($username.val()==null || $.trim($username.val()) == ""){
			$alert("warn","账号不能为空！");
			return false;
		}
		if(!isPass){
			$alert("warn","请输入11位有效手机号!");
			return false;
		 }
				if(count == 60){
					$.ajax({
							url: "${base}/healthLeader/leaderlogin!validateMobileCode.action",
							data:{
								mobile:$.trim($username.val()),
							    },
							type:"post",
							async:false,
							cache:false,
							success: function(data) {
								if(data==0){//发送成功
									timer = window.setInterval("timeDesc()", 1000);
								}else if(data==4){
									$alert("warn","该账号不存在,请注册后登录!");
									return false;
								}else{
									$alert("warn","网络异常！","友情提示",null);
								}
							}
					});
				}
			});
	
	$("#leader_submit").click(function (){
	var isPass =  pattern.test($.trim($username.val()));
		if($username.val()==null || $.trim($username.val()) == ""){
			$alert("warn","账号不能为空！");
			return false;
		}
		if($mobileCode.val()==null || $.trim($mobileCode.val()) == ""){
			$alert("warn","验证码不能为空！");
			return false;
		}
		if(!isPass){
			$alert("warn","请输入11位有效手机号!");
			return false;
		 }
		 $.ajax({
				url: "${base}/healthLeader/leaderlogin!leaderLogin.action",
				data:{
				username:$.trim($username.val()),
				mobileCode:$.trim($mobileCode.val()),
				},
				type:"post",
				async:false,
				cache:false,
				success: function(data) {
					if(data==4){
						$alert("warn","该账号不存在,请注册后登录!");
						return false;
					}else if(data==3){
						$alert("warn","该帐户已被停用!");
						return false;
					}else if(data==1){
						$alert("warn","验证码不正确!");
						return false;
					}else if(data==2){
						<#if redirectUrl?exists>
							location.href = "${redirectUrl}";
						<#else>
							location.href = "${base}/leader/leaderArticle!leaderPharmacy.action";
						</#if>
					}
				}
		   });
	});
});
</script>
</html>