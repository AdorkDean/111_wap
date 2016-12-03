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
    <title>领取红包</title>
    <link href="/web/css/??common.css,iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/web/js/??jquery.min.js,alert.main.js"></script>
</head>
<body class="bg-white">
<article class="new-red-bg">
<#list cpList?if_exists as coup>
	<img src="${base}/web/images/new-health/new_red_bg2.jpg">
    <p class="open-tips">恭喜你抢到</p>
    <p class="open-result">${coup.dis_price?default(0)}元优惠券</p>
    <div class="red-tel-ipt">
    	<input type="tel" placeholder="请输入手机号码" id="mobile">
    </div>
    <div class="red-code-ipt">
    	<input type="tel" placeholder="请输入验证码" id="mobileCode">
    </div>
    <div id="red-code-btn-box">
		<a class="get-code" id="getCode" onclick="getcode()">获取验证码</a>
		<span class="code-countdown" style="display:none;">60s</span>
	</div>
    <p class="red-count-tips">抢到1个红包</p>
    <a href="#" class="get-red-now" onclick="javascript:getRed('${coup.id?default(0)}')"><span>马上领取</span></a>
</#list>
</article>
</body>
</html>
<style type="text/css">
html,body{height:100%; max-width:720px; margin:0 auto; position:relative;}
.new-red-bg{ position:relative; height:100%; width:100%;}
.new-red-bg img{width:100%; height:100%; display:block; position:absolute; left:0; top:0; right:0; bottom:0;}
.open-tips{ width:100%; text-align:center; position:absolute; top:14%; color:#f31c3a; font-size:18px;}
.open-result{ width:100%; text-align:center; position:absolute; top:18%; color:#f31c3a; font-size:28px; font-weight:bold;}
.red-tel-ipt{border:1px solid #c10e30; border-radius:4px; -webkit-border-radius:4px; background:#fff; height:36px; box-shadow:0 2px 0 0 rgba(0,0,0,.2); width:60%; position:absolute; top:35%; left:20%; right:20%;}
.red-tel-ipt input{padding:10px 0; width:94%; margin:0 3%; overflow:hidden; height:16px; line-height:16px;}
.red-code-ipt{border:1px solid #c10e30; border-radius:4px; -webkit-border-radius:4px; background:#fff; height:36px; box-shadow:0 2px 0 0 rgba(0,0,0,.2); width:32%; position:absolute; top:44%; left:20%; right:48%;}
.red-code-ipt input{padding:10px 0; width:88%; margin:0 6%; overflow:hidden; height:16px; line-height:16px;}
#red-code-btn-box{}
#red-code-btn-box a{border:1px solid #c10e30; background:#ffef54; color:#ab8110; border-radius:4px; -webkit-border-radius:4px; height:36px; box-shadow:0 2px 0 0 rgba(0,0,0,.2); width:26%; position:absolute; top:44%; left:54%; right:20%; line-height:36px; text-align:center;}
#red-code-btn-box span{border:1px solid #ccc; background:#f0f0f0; color:#999; border-radius:4px; -webkit-border-radius:4px; height:36px; box-shadow:0 2px 0 0 rgba(0,0,0,.2); width:26%; position:absolute; top:44%; left:54%; right:20%; line-height:36px; text-align:center; z-index:2;}
.red-count-tips{width:100%; text-align:center; position:absolute; top:56.5%; color:#8d0318; font-size:14px;}
.get-red-now{ height:7%; width:52%; display:block; position:absolute; left:24%; right:24%; top:62%;}
.get-red-now span{display:none;}
</style>
<script type="text/javascript">
   var getCode = document.getElementById("getCode");
    var timer = null;
    var count = 60;
    function timeDesc() {
        if(count > 0) {
            count--;
            getCode.innerHTML = count + "s";
            getCode.style.background = "#f3f5f7";
        } else {
            getCode.innerHTML = "获取验证码";
            getCode.style.background = "#ffef54";
            count = 60;
            window.clearInterval(timer);
            timer = "";
        }
    }
    var $mobile = $("#mobile");
	var $mobileCode = $("#mobileCode");
	var pattern = new RegExp(/^[1][3,4,7,5,8][0-9]{9}$/);
     function getcode(){
     	var isPass =  pattern.test($.trim($mobile.val()));
				if($mobile.val()==null || $.trim($mobile.val()) == ""){
					$alert("warn","请输入手机号！","友情提示",null);
					return false;
				}
				if(!isPass){
					$alert("warn","请输入11位有效手机号!");
					return false;
				 }
				if(count == 60){
					$.ajax({
							url: "${base}/coupon/openred!validateMobileCode.action",
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
			};
		
	 function getRed(couponId){
     	var isPass =  pattern.test($.trim($mobile.val()));
				if($mobile.val()==null || $.trim($mobile.val()) == ""){
					$alert("warn","请输入手机号！","友情提示",null);
					return false;
				}
				if(!isPass){
					$alert("warn","请输入11位有效手机号!");
					return false;
				 }
				 if($mobileCode.val()==null || $.trim($mobileCode.val()) == ""){
					$alert("warn","请输入验证码！","友情提示",null);
					return false;
				}
				$.ajax({
							url: "${base}/coupon/openred!receiveRed.action",
							data:{
								mobile:$.trim($mobile.val()),
								mobileCode:$.trim($mobileCode.val()),
								couponId:couponId,
							    },
							type:"post",
							async:false,
							cache:false,
							success: function(data) {
								if(data==1){
									$alert("warn","验证码不正确！","友情提示",null);
									return false;
								}else if(data==3){
									$alert("warn","该账户数据异常！","友情提示",null);
									return false;
								}else if(data==4){
									$alert("warn","该优惠券无效！","友情提示",null);
									return false;
								}else if(data==5){
									$alert("warn","今天已经领过该红包！","友情提示",null);
									return false;
								}else{
									$alert("warn","恭喜您领取成功！","友情提示",null);
									setTimeout(function()
									{
											location.href="${base}/index.html";
									}, 1500);
								}
							}
					});
			};	
</script>
