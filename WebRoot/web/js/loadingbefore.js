//程序执行标识
var flag2 = false;
//首页不加载
var index;
try
{
	index = document.getElementById("header-nav").value;
	if(index != null && index != '' && index != undefined)
	{
		if(index != 2)
		{
			flag2 = true;
		}
	}
	else
	{
		flag2 = true;
	}
}
catch(e)
{
	Error(e);
}

if(flag2)
{
	//页面加载前的动画内容
	var divObj = "<div id='bodyLoading' style='width:100%;height:100%;background:#ffffff;position:fixed;top:0;bottom:0;z-index:10000000000;text-align:center;'><span style='display:block;width:64px;height:100px;margin:auto;margin-top:55%;'><img src='/web/images/loading_0.gif' style='float:left;margin-left:0px;max-width:100%;max-height:100%;'/><a style='float:left;width:64px;height:10px;font-size:12px;color:gray;'>请稍后<b id='dot01'>.</b><b id='dot02'>.</b><b id='dot03'>.</b></a></span></div>";
	document.write(divObj);
	//监听页面加载状态
	document.onreadystatechange = function()
	{
		if(document.readyState == 'complete')
		{
			document.getElementById('bodyLoading').style.display = "none";
		}
	}; 
}

//请稍后中点的动画效果
/*displayDot();
function displayDot(){
	$("#dot01").fadeIn(100);
	$("#dot02").fadeIn(400);
	$("#dot03").fadeIn(700);
}
function hideDot(){
	$("#dot01").fadeOut(100);
	$("#dot02").fadeOut(400);
	$("#dot03").fadeOut(700);
	window.setInterval("displayDot()",1200);
}
window.setInterval("hideDot()", 1200);*/