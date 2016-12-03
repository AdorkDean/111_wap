$(function(){
	//获取秀粉信息
    $.ajax(
    {
        url: "/leader/leader!getinfo.action",
        type: 'POST',
        cache: false,
        success: function(data)
        {
        	if(data != null && data != '' && data != undefined)
        	{
	        	var headurl = data.head_url;
	        	if(headurl != null && headurl != '' && headurl != undefined)
	        	{
	        		headurl = "" + headurl;
	        	}
	        	else
	        	{
	        		headurl = "/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png";
	        	}
	        	var displayName = "";
	        	var nickname = data.nick_name;
	        	var realname = data.real_name;
	        	if(nickname != null && nickname !='' && nickname != undefined)
	        	{
	        		displayName = nickname;
	        	}
	        	else
	        	{
	        		if(realname != null && realname != '' && realname != undefined)
	        		{
	        			displayName = realname;
	        		}
	        	}
	        	var count = data.count;
	        	$("#img148").attr("src",headurl);
	        	$("#nickname").html(displayName);
	        	$("#xfcount").html(count);
        	}
        }
    }); 
});    

function getName()
    { 
        if(navigator.userAgent.indexOf("MSIE")>0) { return "MSIE";}
        if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){return "Firefox";}
        if(isSafari=navigator.userAgent.indexOf("Safari")>0) { return "Safari";}
        if(isCamino=navigator.userAgent.indexOf("Camino")>0){return "Camino";}
        if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){return "Gecko";}
    }
    var version = getName(),CssCode;
    if(version == 'MSIE'){ CssCode = '-ms-';}
    if(version == 'Firefox'){ CssCode = '-moz-';}
    if(version == 'Safari'){ CssCode = '-webkit-';}
    if(version != 'MSIE' && version != 'Firefox' && version != 'Safari'){ CssCode = '-o-';}//Opera
    var Du = 90;
    var num = 1;
    var angel = 0;
    function fransform()
    {
    	$alert("warn","用你的两个手指可以旋转图片哦！");
    	return;
    	var u = navigator.userAgent;
    	var isIOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
    	if(isIOS)
    	{
    	}
    	angel = Du*num;
        var Objnode = document.getElementById("photo-clip-rotateLayer").getElementsByTagName("img")[0];
        Objnode.setAttribute('style',''+CssCode+'-webkit-transform:rotate('+ angel +'deg); '+CssCode+'-webkit-transform-origin: 50% 50%;'+CssCode+'transform:rotate('+ angel +'deg); '+CssCode+'transform-origin: 50% 50%;');
        ++num;
        if(angel == 360)
        {
        	Du = 90; 
        	num = 1;
        }
    }

var dataURL = "";

$("#clipArea").photoClip({
	width: 160,
	height: 160,
	file: "#file",
	view: "#view",
	ok: "#upload_btn",
	strictSize: false,
	loadStart: function() {
		console.log("照片读取中");
	},
	loadComplete: function() {
		console.log("照片读取完成");
	},
	clipFinish: function(dataURL) {
		console.log(dataURL);
		//$("#dataURL").val(dataURL);
		imagesLoad(dataURL);
	}
});

function showImageUpload()
{
	$(".zwcMask").fadeIn();
}

function hideImageUpload()
{
	$(".photo-clip-rotateLayer").html("");
	$("#view").css("backgroundImage","url('')"); 
	$(".zwcMask").fadeOut();
	window.location.reload();
}

function imagesLoad(imageData)
{
	$("#upload_btn").hide();
	$("#upload_btn_").show();
	$(".divMasks").show();
	//var imageData = $("#dataURL").val();
	if(imageData != null && imageData != '')
	{
		$.ajax(
		{
		   url: "/leader/leader!uploadCustomsImage.action",	
	       type: "post",
	       data: {imageData:imageData},
	       async:false,
	       success: function(data)
	       {
	       	  window.location.reload();
	       }
	   }); 
	}
	else
	{
		$alert("warn","您还没有裁取图片！");
		$("#upload_btn").show();
		$("#upload_btn_").hide();
		$(".divMasks").hide();
	}
}