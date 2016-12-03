var _alert 
= "<div id='content' style='display:none;width:80%;height:auto;text-align:center;opacity:.85;filter:alpha(opacity=90);padding:30px 0px 30px 0px;position:fixed;_position:absolute;left:10%;top:30%;border-radius:5px;z-index:99999999;background:#000000;'>"
	+ "<span class='img-bg' style='float:left;background:url(/web/images/warn.png) no-repeat;width:25px;height:25px;margin-left:13px;'></span>"
	+ "<b style='float:left;color:#ffffff;text-decoration:none;margin-left:7px;margin-top:2px;font-family:'微软雅黑','宋体','Microsoft YaHei';' id='cons'>"
	+ "</b></div>";

var _confirm 
    = "<div id='content' style='display:none;width:80%;height:auto;text-align:center;opacity:.85;filter:alpha(opacity=90);padding:30px 0px 30px 0px;position:fixed;_position:absolute;left:10%;top:30%;border-radius:5px;z-index:99999999;background:#000000;'>"
	+ "<span class='img-bg' style='float:left;background:url(/web/images/warn.png) no-repeat;width:25px;height:25px;margin-left:13px;'></span>"
	+ "<b style='float:left;color:#ffffff;text-decoration:none;margin-left:7px;margin-top:2px;font-family:'微软雅黑','宋体','Microsoft YaHei';' id='cons'>"
	+ "</b><div style='position:relative; widht:100%;height:40px;background:#000;top:45px;border-top:#666666 1px solid;line-height:40px;border-bottom-left-radius:5px;border-bottom-right-radius:5px;'>" 
	+ "<div style='position:absolute; width:50%;height:40px;line-height:40px;color:#fff;text-align:center;border-bottom-left-radius:5px;' id='cancel_btn'>取消</div>"
	+ "<div style='position:absolute; width:50%;height:40px;line-height:40px;color:#fff;text-align:center;right:0px;background:red;border-bottom-right-radius:5px;' id='sure_btn'>确定</div>"
	+"</div></div>";

/**
 *  提示信息框函数[可自动消失]
 *  type: 传入类型: warn:警告、success:成功、error:错误
 *  content：提示内容
 */		    
function $alert(type, content)
{
	if(document.getElementById('mask') == null)
	{
		$("body").append(_alert);
	}
	if(type == 'warn')
	{
		$(".img-bg").css("background","url('http://img.zdfei.com/static/image/temp/20160901/81d8e5be1a601f11f19ed14b0f65a44d.png') no-repeat");
	}
	if(type == 'success')
	{
		$(".img-bg").css("background","url('http://img.zdfei.com/static/image/temp/20160901/c9b855e805b39a7263c73bf8fe5f125a.png') no-repeat");
	}
	if(type == 'error')
	{
		$(".img-bg").css("background","url('http://img.zdfei.com/static/image/temp/20160901/632870f778d22c95898ce1736c1c43a1.png') no-repeat");
	}
	$("#content").fadeIn();
	$("#cons").html(content);
	window.setTimeout(function()
	{
		$hide();
	},1000);
}


function $confirm(type, content, callback)
{
	if(document.getElementById('mask') == null)
	{
		$("body").append(_confirm);
	}
	if(type == 'warn')
	{
		$(".img-bg").css("background","url('http://img.zdfei.com/static/image/temp/20160901/81d8e5be1a601f11f19ed14b0f65a44d.png') no-repeat");
	}
	if(type == 'success')
	{
		$(".img-bg").css("background","url('http://img.zdfei.com/static/image/temp/20160901/c9b855e805b39a7263c73bf8fe5f125a.png') no-repeat");
	}
	if(type == 'error')
	{
		$(".img-bg").css("background","url('http://img.zdfei.com/static/image/temp/20160901/632870f778d22c95898ce1736c1c43a1.png') no-repeat");
	}
	$("#content").fadeIn();
	$("#cons").html(content);
	$("#cancel_btn").click(function()
	{
		$hide();
		if(callback)
		{
			callback(false);
		}
	});
	$("#sure_btn").click(function()
	{
		$hide();
		if(callback)
		{
			callback(true);
		}
	});
}

function $hide()
{
	$("#content").fadeOut();
	$("#content").remove();
}
