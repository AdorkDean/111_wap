var u = navigator.userAgent, app = navigator.appVersion;
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
if(isAndroid){
	alert("android");
	$('#phone_type').val("android");
}else if(isiOS){
	alert("ios");
	$('#phone_type').val("ios");
}else{	// wap
	alert("wap");
}