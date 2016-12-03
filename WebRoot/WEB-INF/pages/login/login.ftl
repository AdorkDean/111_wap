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
    <link href="/web/css/??common.css,iconfont.css" rel="stylesheet" type="text/css" />
    <title>登录</title>
	<style>
		 input[type="button"], input[type="submit"], input[type="reset"] {-webkit-appearance: none;}
        .phone_login{ width: 186px; height: 24px; line-height: 24px; position: absolute; left: 50%; top:50%; margin-left:-92px; margin-top:-12px; }
        .phone_login li{ width: 93px; height: 24px; float: left;}
        .phone_login li a{ text-align:center; width: 93px; height: 24px; display: block; color:#191623; font-size: 15px;}
        .phone_login li.w96{ width:96px; height: 24px; float: left;}
        .phone_login li.w96 a{ text-align:center; width:96px; height: 24px; display: block; color:#191623; font-size: 15px;}
        .phone_login li.w80{ width:80px; height: 24px; float: left;}
        .phone_login li.w80 a{ text-align:center; width:80px; height: 24px; display: block; color:#191623; font-size: 15px;}
        .phone_login li a.cover{ color:#00a7a1; border-bottom:2px solid #00a6a0;}
        .login_head2{ text-align: center; padding-top:44px;}
        .login_newbtn a{ background-color: white; border:1px solid #0fa9a3; color:#0fa9a3;}
        .login_weixin{display: none;}
        .login_weixin a{ background-color: #199c18;}
        .wechat_m{ margin-bottom:5px;}
        .new_loginbtn{ padding:10px 12px 4px 12px;}
        .new_loginbtn a{ height:40px; line-height:40px;}
        .newotherway{ height:20px; line-height:20px;}
    </style>
</head>
<body>
<section class="login_wrapper">
    <header class="header">
        <a href="javascript:;" class="iconfont top-left-btn">B</a>
        <h2 style="position: relative;" class="header-title">
            <ul class="phone_login">
                <li class="w96"><a class="cover" style="text-align:center;" href="javascript:void(0);">手机快捷登录</a></li>
                <li class="w80"><a style="text-align:center; margin-left:10px;" href="javascript:void(0);">用户名登录</a></li>
            </ul>
        </h2>
      <a  href="/"><span class="iconfont top-right-btn" id="show-list">h</span></a>
    </header>
    <section>
        <article class="login_head2">
            <a href="javascript:void(0)"><img style="width:156px; margin-top: 28px; margin-bottom: 20px;" src="http://img.zdfei.com/static/image/temp/20151126/062e74ab728db83c36587a5d3e7406fd.png"/></a>
        </article>
        <article class="login_id">
            <ul>
                <li><span>手机号</span><input class='infocus' id="mobile_username" placeholder="请输入您的手机号" type="tel"/></li>
                <li><span>验证码</span><input class='infocus' id="smsCode" type="tel" placeholder="短信验证码"/><a class="getcode redcolor" href="javascript:void(0)"><input style="-webkit-appearance: none;" class="btn" id="getCode" type="button" value="获取验证码"/></a></li>
            </ul>
        </article>
        <article class="login_btn new_loginbtn">
            <a href="javascript:void(0)" id="mobile_submit">登录</a>
        </article>
        <article class="otherway newotherway"><a href="javascript:void(0)"><b>其他登录方式</b></a></article>
        <article class="login_cont wechat_m">
            <!-- <a class="wechat" href="/login/qqlogin!index.action<#if redirectUrl?exists>?qqLoginRedirect=${redirectUrl}</#if>"><img src="/web/images/otherway1.png" /></a>-->
          <!--  <a class="wechat" href="/login/weibologin!index.action<#if redirectUrl?exists>?weiboredirectUrl=${redirectUrl}</#if>"><img src="/web/images/otherway2.png" /></a> -->
          <!--   <a class="wechat" href="javascript:void(0)" id="alipayLogin"><img src="/web/images/otherway3.png" /></a>-->
            <a class="wechat" href="/login/yktlogin!index.action<#if redirectUrl?exists>?yktLoginRedirect=${redirectUrl}</#if>" ><img src="/web/images/otherway4.png" /></a>
        </article>
          <article style="padding-top:0; " class="login_btn login_weixin new_loginbtn">
            <a href="/login/wxlogin!authUrl.action<#if redirectUrl?exists>?wxLoginRedirect=${redirectUrl}</#if>" >微信一键登录</a>
        </article>
    </section>
    <section class="login_wrapper" style="display: none;">
     	<article class="login_head2">
            <a href="/"><img style="width:156px; margin-top: 28px; margin-bottom: 20px;" src="http://img.zdfei.com/static/image/temp/20151126/062e74ab728db83c36587a5d3e7406fd.png"/></a>
        </article>
        <article class="login_id">
            <ul>
                <li><span>账号</span><input class='infocus' placeholder="手机号/昵称/邮箱" type="text" name="username" id="username"/></li>
                <li><span>密码</span><input class='infocus' placeholder="登录密码" type="password" name="password" id="password"/></li>
            </ul>
        </article>
        <article class="login_btn new_loginbtn">
            <a href="javascript:void(0)" id="username_submit">登录</a>
        </article>
        <article class="login_cont"><a style="margin:4px 12px 0px 0;" class="forgetpsd" href="/findpassword/findpassword!index.action">忘记密码?</a></article>
        <article class="login_cont"><a href="/register/register!index.action"><b>还没有账号？</b><span>手机注册</span></a></article>
        <!--支付宝登录form    --begin    --> 
                <div id="alipayDiv"  style="display:none;">
					<form  id="alipaysubmit" name="alipaysubmit"  action="https://mapi.alipay.com/gateway.do?_input_charset=utf-8" method="get">
						<input type="hidden" name="partner" value=""/>
						<input type="hidden"  name="target_service" value=""/>
						<input type="hidden" name="service" value=""/>
						<input type="hidden"  name="_input_charset" value=""/>
						<input type="hidden"  name="sign" value=""/>
						<input type="hidden"  name="return_url" value=""/>
						<input type="hidden"  name="exter_invoke_ip" value=""/>
						<input type="hidden"  name="sign_type" value=""/>
						<input  type="submit" value="确认" style="display:none;" >
					</form>
				</div>
		 <!--支付宝登录form    --end    -->   
        <article class="otherway newotherway"><a href="javascript:void(0)"><b>其他登录方式</b></a></article>
        <article class="login_cont wechat_m">
           <!-- <a class="wechat" href="/login/qqlogin!index.action<#if redirectUrl?exists>?qqLoginRedirect=${redirectUrl}</#if>"><img src="/web/images/otherway1.png" /></a>-->
           <!-- <a class="wechat" href="/login/weibologin!index.action<#if redirectUrl?exists>?weiboredirectUrl=${redirectUrl}</#if>"><img src="/web/images/otherway2.png" /></a> -->
        <!--    <a class="wechat" href="javascript:void(0)" id="alipayLogin"><img src="/web/images/otherway3.png" /></a>-->
            <a class="wechat" href="/login/yktlogin!index.action<#if redirectUrl?exists>?yktLoginRedirect=${redirectUrl}</#if>" ><img src="/web/images/otherway4.png" /></a>
        </article>
        <article style="padding-top:0;" class="login_btn login_weixin new_loginbtn">
            <a href="/login/wxlogin!authUrl.action<#if redirectUrl?exists>?wxLoginRedirect=${redirectUrl}</#if>" >微信一键登录</a>
        </article>
    </section>
    <script type="text/javascript" src="/web/js/??jquery-1.7.2.js,cookieUtil.js,common.new.js,alert.main.js"></script>
    <#include "/static/inc/wap/footer.ftl">
</body>
<script type="text/javascript">    
 	//支付宝登录
	$("#alipayLogin").click(function(){
	       $.ajax({
				url: "/login/alipaylogin!index.action",
				data:{
					<#if redirectUrl?exists>
					alipayRedirectUrl:"${redirectUrl}"
					</#if>
				},
				type: "GET",
				dataType: "json",
				cache: false,
				success: function(data) {
			          $("#alipayDiv").find("input[name='partner']").val(data.partner);
				      $("#alipayDiv").find("input[name='target_service']").val(data.target_service);
				      $("#alipayDiv").find("input[name='service']").val(data.service);
			     	  $("#alipayDiv").find("input[name='_input_charset']").val(data._input_charset);
				 	  $("#alipayDiv").find("input[name='sign']").val(data.sign);
				 	  $("#alipayDiv").find("input[name='return_url']").val(data.return_url);
				      $("#alipayDiv").find("input[name='exter_invoke_ip']").val(data.exter_invoke_ip);
				      $("#alipayDiv").find("input[name='sign_type']").val(data.sign_type);
			         $("#alipaysubmit").submit();
				}
		   });
	   return false;		  	
	});
</script>
<script type="text/javascript">
	var count = 61;
	var timer;
	function timeDesc(){
		if(count>0){
			count--;
			$("#getCode").val(count+"秒后重新获取");
			$("#getCode").css("color","#9d9d9d");
			$("#getCode").prop("disabled", "disabled");
		}else{
			$("#getCode").val("获取验证码");
			$("#getCode").css("color","#ff4a4a");
			$("#getCode").prop("disabled", "");
			count = 61;
			clearInterval(timer);
			timer = "";
		}
	}
$().ready(function() {
	var $mobile_username = $("#mobile_username");
	var $smsCode = $("#smsCode");
	//手机快捷登录
	$("#mobile_submit").click(function (){
		if($mobile_username.val()==null || $.trim($mobile_username.val()) == ""){
			$alert("warn","手机号码不能为空！");
			return false;
		}
		if($smsCode.val()==null || $.trim($smsCode.val()) == ""){
			$alert("warn","短信验证码不能为空！");
			return false;
		}
		 $.ajax({
				url: "/login/login!userMobileLogin.action",
				data:{
				mobile_username:$.trim($mobile_username.val()),
				smsCode:$.trim($smsCode.val()),
				},
				type:"post",
				async:false,
				cache:false,
				success: function(data) {
					if(data==3){
						$alert("warn","该帐户已被停用!");
						return false;
					}else if(data==1){
						$alert("warn","短信验证码不正确!");
						return false;
					}else if(data==2){
						<#if redirectUrl?exists>
							location.href = "${redirectUrl}";
						<#else>
							location.href = "/index.html";
						</#if>
					}
				}
		   });
	});
	 $("#getCode").click(function(){
   		var $mobile_username = $("#mobile_username");
		var $smsCode = $("#smsCode");
		var pattern = new RegExp(/^[1][3,4,7,5,8][0-9]{9}$/);
		var isPass =  pattern.test($.trim($mobile_username.val()));
		var phoneCode = $("#phoneCode").val();
		if($mobile_username.val()==null || $.trim($mobile_username.val()) == ""){
			$alert("warn","手机号码不能为空！");
			return false;
		}
		if(!isPass){
			$alert("warn","请输入11位有效手机号!");
			return false;
		 }
   		if(count == 61){
		$.ajax({
			url: "/login/login!validateMobileCode.action",
			type: "GET",
			data: {
				mobile_username: $.trim($mobile_username.val()),
			},
			cache: false,
			success: function(data) {
				if(data==0){
				timer = window.setInterval("timeDesc()", 1000);
					$alert("warn","验证码发送成功!");
				}else if(data==1){
					$alert("warn","验证码发送失败!");
				}else if(data==3){
					$alert("warn","请输入11位有效手机号!");
				}
			}
		});
   		}
	});
	//用户名验证登录
	var $username = $("#username");
	var $password = $("#password");
	$("#username_submit").click(function (){
		if($username.val()==null || $.trim($username.val()) == ""){
			$alert("warn","账号或密码不能为空！");
			return false;
		}
		if($password.val()==null || $.trim($password.val()) == ""){
			$alert("warn","账号或密码不能为空！");
			return false;
		}
		 $.ajax({
				url: "/login/login!userLogin.action",
				data:{
				username:$.trim($username.val()),
				password:$.trim($password.val()),
				},
				type:"post",
				async:false,
				cache:false,
				success: function(data) {
					if(data==1){
						$alert("warn","该账号不存在,请注册后登录!");
						return false;
					}else if(data==2){
						$alert("warn","该帐户已被停用!");
						return false;
					}else if(data==3){
						$alert("warn","账号或密码不正确!");
						return false;
					}else if(data==5){
						<#if redirectUrl?exists>
							location.href = "${redirectUrl}";
						<#else>
							location.href = "/index.html";
						</#if>
					}
				}
		   });
	});
});
</script>
<script>
    $(".phone_login").on("click","li",function(){
        var index=$(this).index();
        $(this).find("a").addClass("cover").parent().siblings().find("a").removeClass("cover");
        $(".login_wrapper>section").eq(index).show().siblings("section").hide();
    })
</script>
<script type="text/javascript">
if(isWeiXin()){
    	$(".login_weixin").show();
    }
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}
</script>

</html>















