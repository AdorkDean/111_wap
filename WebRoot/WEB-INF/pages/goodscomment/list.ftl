<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="fullscreen=yes,preventMove=no" name="ML-Config">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="format-detection" content="email=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <title>商品评论</title>
    <style>
	.loading-img {display:none;position:fixed;height:auto;z-index:100000;top:45%;left:45%;background:gray;padding:10px;border-radius:10px;opacity:.6;filter:alpha(opacity=60);}
	.loading-img img {widht:30px;height:30px;}
	</style>
</head>

<body>
<#assign title="商品评论">
<#include "/static/inc/wap/header.ftl">

<article class="comment-percent-count clearfix" style="padding-top:45px;">
    <span class="comment-percent">商品满意度<b class="show-percent"></b></span>
    <span class="comment-count">共<b class="show-count">${totalNum}</b>人评论</span>
</article>
<input type="hidden" id="bestcmt" value="${bestNum}"/>
<input type="hidden" id="goodsid" value="${goodsid}"/>
<input type="hidden" id="type" value="1"/>
<article class="comment-level clearfix">
    <ul class="comment-grade clearfix">
        <li class="current"><a href="#">好评(${bestNum})</a></li>
        <li><a href="#">中评(${betterNum})</a></li>
        <li><a href="#">差评(${poorNum})</a></li>
    </ul>
</article>
<ul class="content" id="content1">
	<#list rms1 as cmt1>
    <li class="product-comment">
        <article class="comment-time-user clearfix">
            <span class="comment-user"><#if cmt1?exists && cmt1.user_name?length gt 5>${cmt1.user_name[0..5]}****<#else>${cmt1.user_name}****</#if></span>
            <time class="comment-time" pubdate="pubdate">${cmt1.create_time}</time>
        </article>
        <p class="comment-text">${cmt1.comment?default('')}</p>
    </li>
    </#list>
</ul>
<ul class="content" id="content2" style="display:none;">
	<#list rms2 as cmt2>
    <li class="product-comment">
        <article class="comment-time-user clearfix">
            <span class="comment-user"><#if cmt2?exists && cmt2.user_name?length gt 5>${cmt2.user_name[0..5]}****<#else>${cmt2.user_name}****</#if></span>
            <time class="comment-time" pubdate="pubdate">${cmt2.create_time}</time>
        </article>
        <p class="comment-text">${cmt2.comment?default('')}</p>
    </li>
    </#list>
</ul>
<ul class="content" id="content3" style="display:none;">
	<#list rms3 as cmt3>
    <li class="product-comment">
        <article class="comment-time-user clearfix">
            <span class="comment-user"><#if cmt3?exists && cmt3.user_name?length gt 5>${cmt3.user_name[0..5]}****<#else>${cmt3.user_name}****</#if></span>
            <time class="comment-time" pubdate="pubdate">${cmt3.create_time}</time>
        </article>
        <p class="comment-text">${cmt3.comment?default('')}</p>
    </li>
    </#list>
</ul>
<div class="loading-img" id="loadimg"><img src="/web/images/loading_img.jpg"/></div>
<#include "/static/inc/wap/footer.ftl">
<script>
	var bestcmt = $.trim($("#bestcmt").val());
	var total = $.trim($(".show-count").text());
	if(total == 0)
	{
		$(".show-percent").html("0%");
	}
	else
    {
		$(".show-percent").html(((bestcmt/total)*100).toFixed(0)+"%");
    }
	$(function()
	{
		var id = $.trim($("#goodsid").val());
		var pageNo1 = 2;
		var pageNo2 = 2;
		var pageNo3 = 2;
		$(window).scroll(function()
		{
			var type = $.trim($("#type").val());
			if(isBottomornot(this,type))
			{
				var url = null;
				if(type == '1')
				{
					url = "/goodscomment/gcmt!more.action?id="+id+"&pageNo="+pageNo1+"&type=1";
				}
				if(type == '2')
				{
					url = "/goodscomment/gcmt!more.action?id="+id+"&pageNo="+pageNo2+"&type=2";
				}
				if(type == '3')
				{
					url = "/goodscomment/gcmt!more.action?id="+id+"&pageNo="+pageNo3+"&type=3";
				}
				$("#loadimg").show();
				$.ajax(
				{
					url: url,
					type: "GET",
					dataType: "json",
					cache: false,
					async: false,
					success: function(data) 
					{
						if(type == '1')
						{
							pageNo1 += 1;
						}
						if(type == '2')
						{
							pageNo2 += 1;
						}
						if(type == '3')
						{
							pageNo3 += 1;
						}
						var liObject = "";
						var datas = eval(data);
						if(datas != '')
						{
							for(var i=0;i<datas.length;i++)
							{
								var comment = datas[i].comment;
								var create_time = datas[i].create_time;
								var user_name = datas[i].user_name;
								liObject += 
								"<li class='product-comment'>"
							        +"<article class='comment-time-user clearfix'>"
							            +"<span class='comment-user'>"+user_name+"</span>"
							            +"<time class='comment-time' pubdate='pubdate'>"+create_time+"</time>"
							        +"</article>"
							        +"<p class='comment-text'>"+comment+"</p>"
							    +"</li>";
							}
						}
						else
						{
							if(type == '1')
							{
								flag1 = false;
								liObject += "<li class='product-comment' style='text-align:center;color:gray;'>没有更多评论可以显示</li>";
							}
							if(type == '2')
							{
								flag2 = false;
								liObject += "<li class='product-comment' style='text-align:center;color:gray;'>没有更多评论可以显示</li>";
							}
							if(type == '3')
							{
								flag3 = false;
								liObject += "<li class='product-comment' style='text-align:center;color:gray;'>没有更多评论可以显示</li>";
							}
						}
						if(type == '1')
						{
							$("#content1").append(liObject);
						}
						if(type == '2')
						{
							$("#content2").append(liObject);
						}
						if(type == '3')
						{
							$("#content3").append(liObject);
						}
						window.setTimeout(function(){
							$("#loadimg").hide();
						},500)
					}
				});
			}
		});
		
	    //Comment area click event change class
		$('.comment-grade li').click(function()
		{
			$(this).addClass('current').siblings().removeClass('current');	
		});
		
		//Click Events
		$(".comment-grade li").each(function(index)
		{
			$(this).click(function()
			{
				if(index == 0)
				{
					$("#content1").show();
					$("#content2").hide();
					$("#content3").hide();
					$("#type").val("1");
				}
				if(index == 1)
				{
					$("#content1").hide();
					$("#content2").show();
					$("#content3").hide();
					$("#type").val("2");
				}
				if(index == 2)
				{
					$("#content1").hide();
					$("#content2").hide();
					$("#content3").show();
					$("#type").val("3");
				}
			});
		});
	});
	
	/** Juding the scroll bar was bottom or not*/
	var flag1 = true;
	var flag2 = true;
	var flag3 = true;
	function isBottomornot(i,type)
	{
		var scrollTop = $(i).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(i).height();
		if(type == '1')
		{
			if(((scrollTop + windowHeight) == scrollHeight) && flag1)
			{
				return true;
			}
		}
		if(type == '2')
		{
			if(((scrollTop + windowHeight) == scrollHeight) && flag2)
			{
				return true;
			}
		}
		if(type == '3')
		{
			if(((scrollTop + windowHeight) == scrollHeight) && flag3)
			{
				return true;
			}
		}
		return false;
	}
</script>
</body>
</html>
