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
    <title>绑定账号</title>
    <link href="/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/web/js/??jquery.min.js,new-health.js,alert.main.js"></script>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/new-health.js"></script>
	<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
    <style>
    .new-third-tips{ font-size:18px; text-align:center; padding:20px 0; color:#222;}
	.new-third-p{ padding:20px 0; text-align:center; line-height:18px; color:#666;}
    </style>
</head>
<body class="bg-white">
<article class="new-health-box">
	<article class="new-health-register">
    	<h2 class="new-third-tips">请绑定手机号</h2>
    	<ul class="new-health-register-list new-health-account" style="margin-top:0;">
        	<li>
            	<b>手机号码</b>
                <p class="step2"><input type="tel" placeholder="请输入手机号码" id="mobile"/></p>
            </li>
        	<li>
            	<b>验证码</b>
                <p><input type="tel" placeholder="请输入验证码" id="mobileCode"/></p>
                <div id="code-btn-box">
                	<a class="get-code" id="getCode">获取验证码</a>
                    <span class="code-countdown" style="display:none;">60s</span>
                </div>
            </li>
        </ul>
        <div class="new-health-btn" style="margin-top:20px">
        <!--可用-->
        	<a href="JavaScript:void(0)" onclick="bingingMobile()">绑定账号</a>
        </div>
        <div class="new-third-p">
        	<p>遇到问题？您可以联系客服！</p>
            <p>联系电话：400-606-3111</p>
        </div>
    </article>
</article>
</body>
</html>
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
            getCode.innerHTML = "重发";
            getCode.style.background = "#fe4310";
            getCode.style.color="#FFFFFF";
            count = 60;
            window.clearInterval(timer);
            timer = "";
        }
    }
    $("#getCode").click(function(){
				var $mobile=  $("#mobile");
				var mobileReg = new RegExp(/^[1][3,4,7,5,8][0-9]{9}$/);
				if($mobile.val()==null || $.trim($mobile.val()) == ""){
					$alert("warn","请输入手机号码！","友情提示",null);
					return false;
				}
				if(!mobileReg.test($.trim($mobile.val()))){
					$alert("warn","请输入有效的手机号码！","友情提示",null);
					return false;
				}
				if(count == 60){
					$.ajax({
							url: "${base}/login/wxlogin!validateMobileCode.action",
							data:{
								mobile:$.trim($mobile.val()),
							    },
							type:"post",
							async:false,
							cache:false,
							success: function(data) {
								if(data==0){//发送成功
								timer = window.setInterval("timeDesc()", 1000);
								}else{
									$alert("warn","网络异常！","友情提示",null);
								}
							}
					});
				}
			});
	function bingingMobile(){
	    		var $mobileCode = $("#mobileCode");
	    		var $mobile=  $("#mobile");
	    		var mobileReg = new RegExp(/^[1][3,4,7,5,8][0-9]{9}$/);
	    		if($mobile.val()==null || $.trim($mobile.val()) == ""){
					$alert("warn","请输入手机号码！","友情提示",null);
					return false;
				}
				if($mobileCode.val()==null || $.trim($mobileCode.val()) == ""){
					$alert("warn","验证码不能为空！","友情提示",null);
					return false;
				}
				$.ajax({
							url: "${base}/login/wxlogin!bindingMobile.action",
							data:{
								mobile:$.trim($mobile.val()),
								smsCode:$mobileCode.val(),
							    },
							type:"post",
							async:false,
							cache:false,
							success: function(data) {
								if(data==2){//绑定成功
									$alert("warn","绑定成功！","友情提示",null);
									location.href = "${base}/member/profile.action";
								}else if(data==1){//验证码不正确
										$alert("warn","验证码不正确！","友情提示",null);
									return false;
								}else{
									$alert("warn","该账号异常，请联系客服！","友情提示",null);
									return false;
								}
							}
				});
	}	
</script>