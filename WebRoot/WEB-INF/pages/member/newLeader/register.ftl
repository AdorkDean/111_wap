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
<script type="text/javascript" src="${base}/web/js/new-health.js"></script>
</head>
<body class="bg-white">
<article class="new-health-box">
	<article class="new-health-register">
    	<ul class="new-health-register-list">
        	<li>
            	<b>手机号</b>
                <p><input type="text" placeholder="建议使用常用手机" onkeyup="value=value.replace(/[^0-9]/g,'')" maxlength="11" name="phone" value="<#if member?exists && member.mobile?exists>${member.mobile}</#if>" <#if member?exists && member.mobile?exists>readonly</#if>/></p>
                <div id="code-btn-box">
                	<a class="get-code" onclick="sendPhoneCode()">获取验证码</a>
                    <span class="code-countdown" style="display:none;">60s</span>
                </div>
            </li>
            <li>
            	<b>验证码</b>
                <p><input type="text" placeholder="请输入手机验证码" class="code-input" maxlength="6" name="code"/></p>
            </li>
        </ul>
        <!--选中-->
        <div class="accept-rules clearfix">
        	<i class="agree" id="tk"><b></b></i>
            同意<a href="http://www.111yao.com/web/share/agreement.html"  target="_blank" >健康领秀用户使用条款</a>
        </div>
        <div class="new-health-btn" id="nextBtn">
        <!--可用-->
        	<a href="javascript:void(0)" onclick="submitBtn();">下一步</a>
        </div>
    </article>
</article>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript">
    var wait = 60;
    var clear = null;
    var aObject = document.getElementById("code-btn-box").getElementsByTagName("a")[0];
    var spanObject = document.getElementById("code-btn-box").getElementsByTagName("span")[0];
    function ctime(){
        aObject.style.display = "none";
        spanObject.style.display = "block";
        clear = window.setInterval("time()", 1000);
    }
    function time() {
        if(wait == 0) {
            window.clearInterval(clear);
            wait = 59;
            aObject.style.display = "block";
            spanObject.style.display = "none";
        }
        if(wait > 0)
        {
            wait--;
            spanObject.innerHTML = wait+"s";
        }
        if(wait < 0)
        {
            wait = 60;
        }
    }
    
   
function sendPhoneCode(){
			var phone = $("input[name='phone']").val();
			if(phone==null||phone==""){
				$alert("warn","手机号不能为空");
				return false;
			}
			if(!phone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/)){
				$alert("warn","请输入有效的手机号码!");
				return false;
			}
			$.ajax({// setShowGoodsComment
				url: "../newleader/newleader!sendPhoneCode.action",
				type: "POST",
				async:false,
				data: {'phone':phone} ,
				success: function(data){
					if(data=="-1"){
						$alert("warn","手机号码已绑定领秀");
						return;
					}else if(data=="1"){
					    ctime();
						$alert("warn","发送成功");
						return;
					}else{
					    ctime();
						$alert("warn","发送失败");
						return;					
					}
				},error:function(data){
				}
			});
}    
    
    
function submitBtn(){
	nextBtnClass("1");
    var phone = $("input[name='phone']").val();
    if(phone==null||phone==""){
		$alert("warn","手机号不能为空");
		nextBtnClass("0");
		return false;
	}
    if(!phone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/)){
		$alert("warn","请输入有效的手机号码!");
		nextBtnClass("0");
		return false;
	}
    var code = $("input[name='code']").val();
    if(code==null||code==""){
		$alert("warn","验证码不能为空");
		nextBtnClass("0");
		return false;
	}    
    var tk = $('#tk').attr('class');
    if(tk!="agree"){
        $alert("warn","请同意健康领秀用户使用条款");
        nextBtnClass("0");
		return false;
    }
    $.ajax({
		url: "../newleader/newleader!verifyPhoneCode.action",
		type: "POST",
		data: {'phone':phone,'verifyCode':code} ,
		async:false,
		success: function(data){
			if(data.status==1){
				window.location.href="../newleader/newleader!addHealthLeaderPage.action?phone="+phone;
			}else{
				$alert("warn","验证码不正确");
				nextBtnClass("0");
			}
		},error:function(data){
		}
	});
    
}    


/**
 * 验证验证码
 * 
 * @returns
 */
function verifyCode(phone,code){
	var flag = false;
	$.ajax({
		url: "../healthLeader/verifyPhoneCode!verifyPhoneCode.action",
		type: "POST",
		data: {'phone':phone,'verifyCode':code} ,
		async:false,
		success: function(data){
			if(data.status==1){
				flag = true;
			}else{
				$alert("warn","验证码不正确");
			}
		},error:function(data){
		}
	});
	return flag;
}

function nextBtnClass(s){
    if(s=="1"){
    	$("#nextBtn").html('');
   		$("#nextBtn").append("<a href='JavaScript:void(0)' class='gray'>下一步</a>");
    }else{
    	$("#nextBtn").html('');
   		$("#nextBtn").append("<a href='JavaScript:void(0)' onclick='submitBtn();'>下一步</a>");    
    }
}
</script>