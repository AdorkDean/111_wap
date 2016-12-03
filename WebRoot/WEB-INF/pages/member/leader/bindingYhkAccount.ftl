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
</head>
<body class="bg-white">
<article class="new-health-box">
	<article class="new-health-register">
    	<ul class="new-health-register-list new-health-account">
        	<li>
            	<b>银行名称</b>
                <p class="step2"><input type="text" placeholder="请输入银行名称" id="bankName"/></p>
            </li>
            <li>
            	<b>持卡人姓名</b>
                <p class="step2"><input type="text" placeholder="请输入持卡人姓名" id="accountRealName"/></p>
            </li>
            <li>
            	<b>银行账号</b>
                <p class="step2"><input type="text" placeholder="请输入银行账号" id="numberAmount"/></p>
            </li>
            <li>
            	<b>确认账号</b>
                <p class="step2"><input type="text" placeholder="请确认银行账号" id="reNumberAmount"/></p>
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
        	<a href="JavaScript:void(0)" onclick="doit('2')">完成</a>
        </div>
    </article>
</article>
</body>
</html>
<script type="text/javascript" src="/web/js/??jquery.min.js,new-health.js,alert.main.js"></script>
<script type="text/javascript">
    var getCode = document.getElementById("getCode");
    var timer = null;
    var count = 60;
    function timeDesc() {
        if(count > 0) {
            count--;
            getCode.innerHTML = count + "s";
            getCode.style.background = "#d7d7d7";
        } else {
            getCode.innerHTML = "重发";
            getCode.style.background = "#fe4310";
            getCode.style.color="#d7d7d7";
            count = 60;
            window.clearInterval(timer);
            timer = "";
        }
    }
    var $numberAmount = $("#numberAmount");
	var $reNumberAmount = $("#reNumberAmount");
	var $mobileCode = $("#mobileCode");
	var $bankName = $("#bankName");
	var $accountRealName = $("#accountRealName");
	var $mobileCode = $("#mobileCode");
     $("#getCode").click(function(){
				var mobile=  '${mobile?default('')}';
				if($bankName.val()==null || $.trim($bankName.val()) == ""){
					$alert("warn","银行名称不能为空！","友情提示",null);
					return false;
				}
				if($numberAmount.val()==null || $.trim($numberAmount.val()) == ""){
					$alert("warn","账号不能为空！","友情提示",null);
					return false;
				}
				if(($reNumberAmount.val()) != ($numberAmount.val())){
					$alert("warn","两次账号不一致！","友情提示",null);
					return false;
				}
				if(count == 60){
					$.ajax({
							url: "${base}/leader/bindingAmount!validateMobileCode.action",
							data:{
								mobile:$.trim(mobile),
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
	function doit(numberType){
				if($bankName.val()==null || $.trim($bankName.val()) == ""){
					$alert("warn","银行名称不能为空！","友情提示",null);
					return false;
				}
				if($numberAmount.val()==null || $.trim($numberAmount.val()) == ""){
					$alert("warn","账号不能为空！","友情提示",null);
					return false;
				}
				if(($reNumberAmount.val()) != ($numberAmount.val())){
					$alert("warn","两次账号不一致！","友情提示",null);
					return false;
				}
				if($mobileCode.val()==null || $.trim($mobileCode.val()) == "" || $mobileCode.val()=="请输入手机验证码"){
					$alert("warn","验证码不能为空！","友情提示",null);
					return false;
				}
				$.ajax({
							url: "${base}/leader/bindingAmount!bindingAmount.action",
							data:{
								numberType:numberType,
								numberAmount:$numberAmount.val(),
								bankName:$bankName.val(),
								accountRealName:$accountRealName.val(),
								mobileCode:$mobileCode.val(),
							    },
							type:"post",
							async:false,
							cache:false,
							success: function(data) {
								if(data==0){//绑定成功
									$alert("warn","绑定成功！","友情提示",null);
									location.href = "${base}/leader/bindingAmount!accountManage.action";
								}else{
									$alert("warn","验证码错误！","友情提示",null);
								}
							}
				});
	}
</script>