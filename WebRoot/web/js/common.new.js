var flag = true;
$(function()
{
	/** Go to the top of page */
	//var oDistance = $(window).height()/2;
    $(window).scroll(function()
    {
        if ($(window).scrollTop() > 50) 
        {
            $(".back-to-top").fadeIn();
        } 
        else 
        {
            $(".back-to-top").fadeOut();
        }
    });
    $(".back-to-top").click(function() 
    		{
        $('body,html').animate(
        {
            scrollTop: 0
        },
        1000);
        return false;
    });
    
	//检测登录
    getUserName();
	
	/** navicator content color */
	var $nav = window.location.search;
	if($nav != '' && $nav != null && $nav != undefined)
	{
		var params = $nav.split("p=");
		$("#header-nav-"+params[1]).addClass("curr");
	}
	else
	{
		$("#scroller ul").find("li").each(function(i)
		{
			if($.trim($(this).find("a").html()) == "首页")
			{
				$(this).addClass("curr");
				return false;
			}
		});
	}
	
	/** Backing pre step of history */
	$(".top-left-btn").click(function()
	{
		window.history.go(-1);
	});
	
	/** Go to home page */
	$("#toHome").click(function(){
		window.location.href = "/";
	});
	
	/** Downlaod APP*/
	$(".footer-adv").click(function(){
		window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.example.yiyaoguan111";
	});
	
});

/**checking login */
function checklogin() 
{
	var result = false;
	$.ajax(
	{
		url: "/index!clogin.action",
		type: "GET",
		dataType: "json",
		cache: false,
		async: false,
		success: function(data) 
		{
			if(data == 1)
			{
				result = true;
			}
		}
	});
	return result;
}

/** Juding the scroll bar was bottom or not*/
function isBottom(i)
{
	var scrollTop = $(i).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(i).height();
	if(((scrollTop + windowHeight) >= (scrollHeight-(windowHeight)/3)) && flag)
	{
		return true;
	}
	return false;
}

function isLoad(i)
{
	var scrollTop = $(i).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(i).height();
	if(scrollTop + windowHeight > scrollHeight-10){
		return true;
	}
	return false;
}

var imgUrl = "http://img.zdfei.com/";

//检查登录、获取用户名
function getUserName()
{
	$.ajax(
	{
		url: "/index!getMemberUserName.action",
		type: "GET",
		dataType: "json",
		cache: false,
		async: false,
		success: function(data) 
		{
			if(data == 0)
			{
				$(".login-btn").html("登录").bind("click",function()
				{
					window.location.href = "/login/login!index.action";
				});
			}
			else
			{
				$(".login-btn").html("个人中心").bind("click",function()
				{
					window.location.href = "/member/profile.action";
				});
			}
		}
	});
}