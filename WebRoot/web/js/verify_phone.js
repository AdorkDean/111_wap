
var wait=60;
var timer;
function autoTime(o) {
	if (wait == 0) {
//		o.removeAttribute("disabled");	
		o.innerHTML="获取验证码";
//		o.value="免费获取验证码";
		wait = 60;
	} else {
//		o.setAttribute("disabled", true);
//		o.innerHTML="重新发送(" + wait + ")";
		o.innerHTML=wait + "秒后重发";
//		o.value="重新发送(" + wait + ")";
		wait--;
		setTimeout(function() {
			autoTime(o)
		},
		1000)
	}
}
function timeDesc(){
    
	if(wait>0){
		wait--;
		$("#sendBtn").text(wait+"秒重新获取");
		$("#sendBtn").attr("disabled", true);
		//$("#sendBtn").prop("disabled", "disabled");
	}else{
		$("#sendBtn").text("获取验证码");
		$("#sendBtn").attr("disabled",false);
		//$("#sendBtn").prop("disabled", "");
		wait = 60;
		clearInterval(timer);
		timer = "";
	}
}

function setPhoneBtn(obj){
	var _messgeCode = $("input[name='code']").val();
	if(obj!=undefined && obj.value.length==11 &&_messgeCode!=undefined &&_messgeCode.length==6){
		$("#nextBtn").attr("style","background-color: #00b4b8;");
	}else{
		$("#nextBtn").attr("style","background-color: #cccdd0;");
	}
}
function setVerifyBtn(obj){
	var _phone = $("input[name='phone']").val();
	if(obj!=undefined && obj.value.length==6 && _phone!=undefined &&_phone.length==11){
		$("#nextBtn").attr("style","background-color: #00b4b8;");
	}else{
		$("#nextBtn").attr("style","background-color: #cccdd0;");
	}
}
/**
 * 发短信操作
 */
$(function(){
	$("#sendBtn").click(function(){
		if (wait == 60) {
			var phone = $("input[name='phone']").val();
			if(phone==null||phone==""){
				$alert("warn","手机号不能为空");
				return false;
			}
			if(!phone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/)){
				$alert("warn","请输入有效的手机号码!");
				return false;
			}
			
			$.ajax({// setShowGoodsComment
				url: "../healthLeader/sendPhoneCode!sendPhoneCode.action",
				type: "POST",
				async:false,
				data: {'phone':phone} ,
				success: function(data){
					if(data=="-1"){
						$alert("warn","手机号码已绑定领秀");
						return;
					}else if(data=="1"){
						timer = window.setInterval("timeDesc()", 1000);
						$alert("warn","发送成功");
						return;
					}else{
						$alert("warn","发送失败");
						return;					
					}
				},error:function(data){
				}
			});
		}
	})
})





/**
 * 下一步
 * 
 * @returns
 */
function nextBtn(){
	var phone = $("input[name='phone']").val();
	var code = $("input[name='code']").val();
	if(phone==null||phone==""){
		$alert("warn","请输入验证码手机号");
		return false;
	}
	if(code==null||code==""){
		$alert("warn","请输入验证码");
		return false;
	}
	if(!phone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/)){
		$alert("warn","请输入有效的手机号码!");
		return false;
	}
	var selectBox = $("#selectBox").attr("class");
	if(selectBox!=undefined && selectBox!="login_sel checklabel"){
		$alert("warn","请同意活动声明");
		return false;
	}
	var flag = verifyCode(phone,code);
	if(flag){
		//提交
		window.location.href="../healthLeader/addHealthLeaderPage!addHealthLeaderPage.action?phone="+phone;
	}
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