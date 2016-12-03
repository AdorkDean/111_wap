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
<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
</head>
<body class="bg-white">
<article class="new-health-box">
	<article class="new-health-register">
	<form id="inputForm" name="form1" action="${base}/newleader/newleader!regLeader.action" method="post">
    	<ul class="new-health-register-list">
    	    <#if  stype=="1" && stype?exists >
        	<li>
            	<b>输入密码</b>
                <p class="step2"><input id="password" name="password" type="password" placeholder="数字加字母组成，且不少于8位" maxlength="16" onBlur="ckpassword()" /></p>
            </li>
            <li>
            	<b>确认密码</b>
                <p class="step2"><input id="rePassword" name="rePassword" type="password" placeholder="再次输入密码" maxlength="16" onBlur="ckrePassword()"/></p>
            </li>
            </#if>
        	<li>
            	<b>输入昵称</b>
                <p class="step2"><input type="text" placeholder="请设置您的昵称" maxlength="8" id="nickName" name="nickName"/></p>
            </li>
            <li>
            	<b>真实姓名</b>
                <p class="step2"><input type="text" placeholder="请输入您的真实姓名" maxlength="16" id="realName" name="realName"/></p>
            </li>
        </ul>
        <input type="hidden" id="stype" name="stype" value="${stype?if_exists}"/>
        <input type="hidden" id="phone" name="phone" value="${phone?if_exists}"/>
        </from>
        <div class="new-health-btn" style="margin-top:45px;" id='nextBtn'>
        	<!--可用-->
        	<a href="javascript:void(0)" onclick="reg()">完成</a>
        </div>
    </article>
</article>
</body>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script>
function ckpassword(){
  var password =  $('#password').val();
  if($.trim(password)=="" || password.length==0 ){
     $alert("warn","密码不能为空");
	 return false;
  }
  var p = /^(?!(?:\d*$))[A-Za-z0-9]{6,20}$/;
  if(!p.test(password)){
	$alert("warn","请输入有效的密码!");
	return false;
  }
}
function ckrePassword(){
  var rePassword =  $('#rePassword').val();
  if($.trim(rePassword)=="" || rePassword.length==0 ){
     $alert("warn","确认密码不能为空");
	 return false;
  }
  var p = /^(?!(?:\d*$))[A-Za-z0-9]{6,20}$/;
  if(!p.test(rePassword)){
	$alert("warn","请输入有效的确认密码!");
	return false;
  }
  var password =  $('#password').val();
  if(password!=rePassword){
  	$alert("warn","两次密码不一致!");
	return false;
  }
}

function reg(){
   nextBtnClass("1");
   var s = $('#stype').val();
   if(s=="1"){
      var password =  $('#password').val();
  	  if($.trim(password)=="" || password.length==0 ){
       	$alert("warn","密码不能为空");
       	nextBtnClass("0");
	 	return false;
  	  }
	  var p = /^(?!(?:\d*$))[A-Za-z0-9]{6,20}$/;
	  if(!p.test(password)){
		$alert("warn","请输入有效的密码!");
		nextBtnClass("0");
		return false;
	  }
	  var rePassword =  $('#rePassword').val();
	  if($.trim(rePassword)=="" || rePassword.length==0 ){
	     $alert("warn","确认密码不能为空");
	     nextBtnClass("0");
		 return false;
	  }
	  if(!p.test(rePassword)){
		$alert("warn","请输入有效的确认密码!");
		nextBtnClass("0");
		return false;
	  }
	  var password =  $('#password').val();
	  if(password!=rePassword){
	  	$alert("warn","两次密码不一致!");
	  	nextBtnClass("0");
		return false;
	  }
   }
   var nickName =  $('#nickName').val();
   if($.trim(nickName)=="" || nickName.length==0 ){
   	$alert("warn","昵称不能为空");
   	nextBtnClass("0");
 	return false;
   }
   var realName =  $('#realName').val();
   if($.trim(realName)=="" || realName.length==0 ){
       	$alert("warn","真实姓名不能为空");
       	nextBtnClass("0");
	 	return false;
  }   
  $('#inputForm').submit(); 
}

function nextBtnClass(s){
    if(s=="1"){
    	$("#nextBtn").html('');
   		$("#nextBtn").append("<a href='JavaScript:void(0)' class='gray'>下一步</a>");
    }else{
    	$("#nextBtn").html('');
   		$("#nextBtn").append("<a href='JavaScript:void(0)' onclick='reg();'>下一步</a>");    
    }
}
</script>
</html>