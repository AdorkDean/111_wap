function autoTimeBtn_goon(o) {
	var _val = o.innerHTML;
//	alert(_val);
	if(_val=="立即提交申请"){
		o.innerHTML="提交中.";
	}
	if(_val=="提交中."){
		o.innerHTML="提交中..";
	}
	if(_val=="提交中.."){
		o.innerHTML="提交中...";
	}
	if(_val=="提交中..."){
		o.innerHTML="提交中.";
	}
	setTimeout(function() {
		autoTimeBtn_goon(o)
	},
	1000)
}


$(function(){
	var lijie_flag = 0;
	$("#liji").click(function(){
		if(lijie_flag==0){
			var cardCode = $("input[name='cardCode']").val();
			var leaderImage1 = $("input[name='leaderImage1']").val();//资质
			var leaderImage2 = $("input[name='leaderImage2']").val();
			var leaderImage3 = $("input[name='leaderImage3']").val();
			var leaderImage4 = $("input[name='leaderImage4']").val();
			var leaderImage5 = $("input[name='leaderImage5']").val();
			var cardFirst = $("input[name='cardFirst']").val();//身份证正面
			var cardTwo = $("input[name='cardTwo']").val();//身份证反面
			if(cardCode==null||cardCode==""){
				$alert("warn","请填写您的身份证号");
				return;
			}
			if(!isChinaIDCard(cardCode)){
				$alert("warn","请输入有效的身份证号!");
				return;
			}
			
			if(cardFirst==null||cardFirst==""){
				$alert("warn","请先上传您的身份证正面");
				return;
			}
			if(cardTwo==null||cardTwo==""){
				$alert("warn","请先上传您的身份证反面");
				return;
			}
			
			if((leaderImage1==null||leaderImage1=="")&&(leaderImage2==null||leaderImage2=="")&&
					(leaderImage3==null||leaderImage3=="")&&(leaderImage4==null||leaderImage4=="")
					&&(leaderImage5==null||leaderImage5=="") ){
				$alert("warn","您至少要上传一张您的资质");
				return;
			}
			lijie_flag = 1;
			autoTimeBtn_goon(this);
			$("#liji").attr("style","background-color:#777777;");
			$("#subForm").submit();
		}
		
		//"立即申请"		
	});
	$("#youke").click(function(){
		window.location.href="../healthLeader/healthLeader!gotoLeaderPageByNoAudit.action";
//		alert("游客");
	});

	$(document).ready(function(){
		$("input[type=file]").on('click',function(){
				if(typeof FileReader==='undefined'){ 
				$(this).change(function(){
						loadImgForIE(this);
					})
				}else{ 
					$(this).change(function(){
						readFile(this)
					})
				} 
		})
	});
	function readFile(obj){
		var file = obj.files[0]; 
		if(typeof(file) == "undefined"){
			return false;
		}
		if(!/image\/jpeg/.test(file.type)){ 
			$(obj).val("");
			$(obj).siblings("a").children("img").attr("src","/web/images/applyaftersaleaddimgbg.jpg");
			$alert("warn","文件必须为JPG图片！"); 
			return false; 
		}
		if(file.size>2097152){ 
			$(obj).val("");
			$(obj).siblings("a").children("img").attr("src","/web/images/applyaftersaleaddimgbg.jpg");
			$alert("warn","文件必须小于等于2M"); 
			return false; 
		}
		var reader = new FileReader(); 
		reader.readAsDataURL(file); 
		reader.onload = function(e){
			$(obj).siblings("a")[0].innerHTML = '<img style="border-radius: 4px;" src="'+this.result+'" alt="" id=""/>' ;
		} 
	}
	function loadImgForIE(obj){
		var imgPath=$(obj).val();
		$(obj).siblings("a")[0].innerHTML = '<img style="border-radius: 4px;" src="'+this.result+'" alt="" id=""/>' ;
	}
})



////////////////////////验证身份证号///////////////////////////////////////////////////////
/**
 * 按照《中华人民共和国居民身份证法》的规定，公民身份号码是由公安机关按照公民身份号码国家标准编制，由18位数字组成。
 * 前6位为地址码，第7至14位为出生日期码，第15至17位为顺序码，第18位为校验码。
 * 具体含义：
 * 地址码表示公民被赋码时常住户口所在县（市、旗、区）的行政区划代码，
 * 出生日期码表示公民出生的公历年月日，
 * 顺序码表示在同一地址码所标识的区域范围内对同年同月同日出生的人编定的顺序
 * （奇数分配给男性，偶数分配给女性），
 * 校验码采用数据处理校验码系统计算产生。
 * 公民可通过中心提供的免费身份号码解读服务进行“原发证地”和“号码升位”查询。
 *
 * @param StrNo
 * @returns {Boolean}
 */
function isChinaIDCard(StrNo) {
	StrNo = StrNo.toString();
	if (StrNo.length == 18) {
		if (!validateNumber(StrNo.substr(0, 17))) {
			return false;
		}
	} else {
		return false;
	}
	if (!validateBrith(StrNo)) {
		return false;
	}
	if (StrNo.length == 18) {
		return validateCodeBy18IdCard(StrNo);
	}
	return true;
}
/**
 * 15位身份证特殊情况验证不通过
 * 如再有请加到IdCardNo15数组中
 * @param StrNo
 * @returns {Boolean}
 */
/*function validateIdCardNo15(StrNo){
 var len = IdCardNo15.length;
 for (var i = 0; i < len; i++) {
 if(StrNo==IdCardNo15[i]) {
 return false;
 }
 }
 return true;
 }*/
/**
 * 15位升级到18位
 * @param StrNo
 * @returns {Boolean}
 */
/*function convert15to18(StrNo) {
 var idcard17 = StrNo.substr(0, 6) + "19" + StrNo.substr(6, 9);
 var a_idCard = idcard17.split("");// 得到身份证数组
 var sum = 0; // 声明加权求和变量
 for ( var i = 0; i < 16; i++) {
 sum += Wi[i] * a_idCard[i];// 加权求和
 }
 var valCodePosition = sum % 11;// 得到验证码所位置
 return ValideCode[valCodePosition];
 } */
/**
 * 验证校验码
 * @param StrNo
 * @returns {Boolean}
 */
function validateCodeBy18IdCard(StrNo) {
	var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
	var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;
	var a_idCard = StrNo.split("");// 得到身份证数组
	var sum = 0; // 声明加权求和变量
	if (a_idCard[17].toLowerCase() == 'x') {
		a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
	}
	for ( var i = 0; i < 17; i++) {
		sum += Wi[i] * a_idCard[i];// 加权求和
	}
	var valCodePosition = sum % 11;// 得到验证码所位置
	if (a_idCard[17] == ValideCode[valCodePosition]) {
		return true;
	}
	return false;
}
/**
 * 验证出生日期
 * @param StrNo IdCard
 * @returns {Boolean}
 */
function validateBrith(StrNo) {
	var iY = StrNo.substr(6, 4);
	var iM = StrNo.substr(10, 2);
	var iD = StrNo.substr(12, 2);

	if (iY < 1900 || iY > 2020 || !validateNumber(iY)) {
		return false;
	}
	if (!validateNumber(iM)) {
		return false;
	}
	if (!validateNumber(iD)) {
		return false;
	}
	var temp_date = new Date(parseFloat(iY), parseFloat(iM) - 1, parseFloat(iD));
	if (temp_date.getFullYear() != parseFloat(iY)) {
		return false;
	}
	if (temp_date.getMonth() != (parseFloat(iM) - 1)) {
		return false;
	}
	if (temp_date.getDate() != parseFloat(iD)) {
		return false;
	}
	return true;
}
/**
 * 验证是否是数字
 * @param oNum 参数
 * @returns {Boolean}
 */
function validateNumber(oNum) {
	if (!oNum)
		return false;
	var strP = /^\d+(\.\d+)?$/;
	if (!strP.test(oNum))
		return false;
	try {
		if (parseFloat(oNum) != oNum)
			return false;
	} catch (ex) {
		return false;
	}
	return true;
}
////////////////////////////////////////////////////////////////////////////
