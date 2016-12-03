$(function()
{
	//取消
	$(".search-cancle").click(function()
	{
		var v = $.trim($("#search-value-ui-id").val());
		if(v != "" && v != null && v != undefined)
		{
			$("#category_search_input").val(v);
			$("#index_search_input").val(v);
			$("#subcategory_search_input").val(v);
			$("#header_search_input_common").val(v);
		}
		else
		{
			$("#index_search_input").val("");
			$("#category_search_input").val("");
			$("#subcategory_search_input").val("");
			$("#header_search_input_common").val("");
		}
		$(".search_include_ui").hide();
		$("body").css("overflow","auto");
		try
		{
			$(".new-index-main").show();
			$("#subcategory_article_contents").show();
			$("#result_content_id").show();
		}catch(e){}
	});
	//键盘按起事件
	$("#search-value-ui-id").keyup(function()
	{
		var v = $.trim($(this).val());
		if(v != "" && v != null && v != undefined)
		{
			_getDataFromServer(v, "wap", 1, 10);
		}
		else
		{
			$(".keyword_tips").hide();
		}
	});
	//点击搜索按钮事件
	$("#search_btn_new_id").click(function()
	{
		var v = $.trim($("#search-value-ui-id").val());
		if(v != "" && v != null && v != undefined)
		{
			putKeyWordsInCookies(v);
			var url = "/result.html?keyword="+encodeURIComponent(v);
			window.location.href = url;
		}
		else
		{
			$("#search-value-ui-id").focus();
		}
	});
	//显示历史搜索记录
	var sets = invertStr(decodeURIComponent(getCookie("kws")), "@", 1);
	if(sets != null && sets != '' && sets != undefined && sets != "nul@")
	{
		sets = sets.substring(0,sets.length-1);
		var $kw = sets.split("@");
		var lg = $kw.length;
		var lis = "";
		var $c = "";
		for(var i=0; i<lg; i++)
		{
			if(i >= 10)
			{
				break;
			}
			lis += "<li class='clearfix' "+$c+"><a>"+$kw[i]+"</a></li>";
		}
		$(".search-history ul").append(lis);
		//为历史搜索添加搜索事件
		setHistoryEvent();
	}
	var l = $(".search-history ul").find("li").length;
	if(l <= 1)
	{
		$(".search-history").hide();
	}
	//清楚历史搜索记录
	$(".clear-history").click(function()
	{
		$confirm("warn","您确定要清空所有历史记录吗？",function(result)
		{
			if(result)
			{
				delCookie("kws");
				$(".search-history").hide();
			}
		});
	});
	//返回上一页面
	$(".top_left_btn").click(function()
	{
		/*var lg = window.history.length;
		if(lg > 1)
		{
			window.history.go(-1);
		}
		else
		{
			$(".search-cancle").trigger("click");
		}*/
		window.location.href = "/";
	});
});
//关键字联想
var visitUrl = "http://router.111yao.com";
function _getDataFromServer(v, pric_type, pageNo, pageSize)
{
	$.ajax
	({
		type: "GET",
		url: visitUrl + "/sltRouter?method=getKeyWordTip",	 
		data:{"keyword":v,"pric_type":pric_type,"pageNo":pageNo,"pageSize":pageSize},
		async:false, 
		dataType: "jsonp",
		jsonp: "jsoncallback",
		success: function(data)
		{
			$(".keyword_tips ul").html("");
			var lg = data.list.length;
			if(lg > 0)
			{
				var lis = "";
				var o = data.list;
				var $c = "";
				for(var i=0; i<lg; i++)
				{
					if(i == lg-1)
					{
						$c = "style='border-bottom:#d7d7d7 1px solid;'";
					}
					lis += "<li class='clearfix' "+$c+"><a>"+o[i].name+"</a></li>";
				}
				$(".keyword_tips ul").append(lis);
				$(".keyword_tips").show();
				//赋予点击事件
				$(".keyword_tips ul li").each(function()
				{
					$(this).click(function()
					{
						var v = $.trim($(this).find("a").html());
						putKeyWordsInCookies(v);
						var url = "/result.html?keyword="+encodeURIComponent(v);
						window.location.href = url;
					});
				});
			}
			else
			{
				$(".keyword_tips").hide();
			}
		},
		error: function(e)
		{
			console.log("e=["+e+"]");
		}
	});   
}
//为历史搜索添加搜索事件
function setHistoryEvent()
{
	$(".search-history ul").find("li.clearfix").each(function()
	{
		$(this).click(function()
		{
			var v = $.trim($(this).find("a").html());
			var url = "/result.html?keyword="+encodeURIComponent(v);
			window.location.href = url;
		});
	});
	
}