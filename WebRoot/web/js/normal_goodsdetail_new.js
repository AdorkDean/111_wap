$(function()
{
	//滚动条监听
	new IScroll('#wrapper', { eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false });
	
	//商品ID
	var id = $.trim($("#goodsid").val());
	
	//获取商品价格
	try{getPriceFromServer(id);}catch(e){console.log("["+e+"]");}
	
	//计算折扣
	try{discount();}catch(e){console.log("["+e+"]");}
	
	//检查用户是否收藏
	if(checklogin())
	{
		$.get("/index!isattent.action?id="+id,null,function(data)
		{
			if(data == 1)
			{
				document.getElementById("collect-btn").className = "iconfont top-heart-btn";
				$("#collect-btn").html("H");
			}
			if(data == 0)
			{
				document.getElementById("collect-btn").className = "iconfont top-heart-btn alr";
				$("#collect-btn").html("I");
			}
		});
	}
	//获取购物车数量
	try{getnum();}catch(e){console.log("["+e+"]");}
	
	//收藏商品事件
	$("#collect-btn").click(function()
	{
		if(checklogin())
		{
			$.get("/index!attentionpro.action?id="+id+"&type=0",null,function(data)
			{
				if(data == 0)
				{
					document.getElementById("collect-btn").className = "iconfont top-heart-btn";
					$("#collect-btn").html("H");
				}
				if(data == 1)
				{
					document.getElementById("collect-btn").className = "iconfont top-heart-btn alr";
					$("#collect-btn").html("I");
				}
			});
		}
		else
		{
			window.location.href = "/login/login!index.action?redirectUrl="+location.href;
		}
	});
	//图片轮播插件
	var imglg = $(".slider").find("ul li").size();
	if(imglg == 0)
	{
		$(".cur-num").html("0");
		$("#imglg").html("0");
	}
	else
	{
		$("#imglg").html(imglg);
	}
	//立即购买
	$("#nowbuy").click(function()
	{
		shortBug(id);
	});
	
	//返回按钮判断
    judeBackBtn();
    
    //图片详情、说明书、评论面板切换监听事件
	var tabsSwiper = new Swiper('.swiper-container',
	{
		speed:500,
		onSlideChangeStart: function()
		{
			$(".tabs .active").removeClass('active');
			var j = tabsSwiper.activeIndex;
			$(".tabs a").eq(j).addClass('active');
			$(".swiper-wrapper").css("height",$("#big_content_"+(j+1)).height());
		}
	});
	$(".tabs a").on('touchstart mousedown',function(e)
	{
		e.preventDefault()
		$(".tabs .active").removeClass('active');
		$(this).addClass('active');
		var i = $(this).index();
		$(".swiper-wrapper").css("height",$("#big_content_"+(i+1)).height());
		tabsSwiper.swipeTo(i);
	});
	$(".tabs a").click(function(e)
	{
		e.preventDefault();
	});
	
	//商品评论标签点击事件
	$('.comment-grade li').click(function()
	{
		$(this).addClass('current').siblings().removeClass('current');	
	});
	$(".comment-grade li").each(function(index)
	{
		$(this).click(function()
		{
			if(index == 0)
			{
				$("#content1").show();
				$("#content2").hide();
				$("#content3").hide();
				console.log("height1=" + $(".swiper-wrapper").height());
				var lg = $("#content1 li").length;
				if(lg > 0)
				{
					$(".swiper-wrapper").css("height",lg*65+150+"px");
				}
				console.log("height2=" + $(".swiper-wrapper").height());
			}
			if(index == 1)
			{
				$("#content1").hide();
				$("#content2").show();
				$("#content3").hide();
				console.log("height1=" + $(".swiper-wrapper").height());
				var lg = $("#content2 li").length;
				if(lg > 0)
				{
					$(".swiper-wrapper").css("height",lg*65+200+"px");
				}
				console.log("height2=" + $(".swiper-wrapper").height());
			}
			if(index == 2)
			{
				$("#content1").hide();
				$("#content2").hide();
				$("#content3").show();
				console.log("height1=" + $(".swiper-wrapper").height());
				var lg = $("#content3 li").length;
				if(lg > 0)
				{
					$(".swiper-wrapper").css("height",lg*65+150+"px");
				}
				console.log("height2=" + $(".swiper-wrapper").height());
			}
		});
	});
	//获取评论数据
	getComments();
	
	//查看更多评论
	$(".more_cri").click(function() 
	{
		window.location.href = "/goodscomment/gcmt!list.action?id="+id;
	});
	
	//图片放大功能
	try{imageTurnBig();}catch(e){console.log("e=["+e+"]")}
	
});

//获取购物车数量
function getnum()
{
	jQuery.ajax
	({
       type: "post",
       url: "/carts/cart!getCartSum.action",	 
       async:false, 
       success: function(data)
       {
    	   var count = parseInt(data);
    	   if(count > 0)
    	   {
    		   if(count > 99)
    		   {
    			   $("#iconfont").css("left","-17px");
    			   $(".count-msg").html("99+");
    		   }
    		   else
    		   {
    			   if(count >= 10)
    			   {
    				   $("#iconfont").css("left","-15px");
    			   }	
    			   else
				   {
    				   $("#iconfont").css("left","-10px");
				   }
    			   $(".count-msg").html(data);
    		   }
    		   $(".count-msg").show();
    	   }
       }
     }); 	  
}

//去购物车
function gocart()
{
	window.location.href="/carts/cart!page.action?url="+window.location.href;
}

//动态获取商品价格
function getPriceFromServer(id)
{
	var params = "[{'goodsId':"+id+",'nowPrice':'0','platform':'2','htmlObjId':'mprice'}]" ;
    $.ajax
    ({
        type: "post",
        url: "/index!countpricefrompc.action",
        async: false,
        data: {"params":params},
        success: function(data)
        {
            if(data != null && data != '' && data != undefined)
            {
                for(var i=0; i<data.length; i++)
                {
                    var nowPrice = data[i].nowPrice;
                    var htmlObjId = data[i].htmlObjId;
                    $("#"+htmlObjId).html(nowPrice);
                }
            }
            else
            {
                console.log("Datas null !");
            }
        },
        error: function()
        {
            console.log("Server error !");
        }
    });
}

//折扣数判断
function discount()
{
	var oldPrice = parseFloat($.trim($("#oprice").html()));
	var nowPrice = parseFloat($.trim($("#mprice").html()));
	var dc = ((nowPrice / oldPrice) * 10).toFixed(2) ;
	if(dc == "0.00" || dc == "10.00")
	{
		$("#pcount").hide();
	}
}

//返回按钮判断
function judeBackBtn()
{
	var url = window.location.href;
	if(url.indexOf("code") > 0)
	{
		$("#btn_back_step").hide();
	}
}

//获取评论数据
function getComments()
{
	var id = $.trim($("#goodsid").val());
	$.ajax
    ({
        type: "POST",
        url: "/goodscomment/gcmt!getComments.action",
        async: false,
        data: {"id":id},
        success: function(data)
        {
        	$(".show-count").html(data.totalNum);
        	$("#cri_num_new").html("评论（"+data.totalNum+"）");
        	$("#good_cri").html("好评("+data.bestNum+")");
        	$("#better_cri").html("中评("+data.betterNum+")");
        	$("#poor_cri").html("差评("+data.poorNum+")");
        	//是否显示加载更多评论按钮
        	if(data.totalNum > 10)
        	{
        		$(".more_cri").show();
        	}
        	//商品满意度
        	var pr = ((data.bestNum/data.totalNum)*100).toFixed(0)+"%";
        	if(data.totalNum == 0)
        	{
        		pr = "0%";
        	}
        	$(".show-percent").html(pr);
        	//好评
        	var lis1 = "";
        	var lg1 = data.rms1.length;
        	for(var i=0; i<lg1; i++)
        	{
        		var comment = data.rms1[i].comment;
				var create_time = data.rms1[i].create_time;
				var _date_obj = new Date(create_time);
				var _year = _date_obj.getFullYear();
				var _month = _date_obj.getMonth()+1;
				var _day = _date_obj.getDate();
				var _hour = _date_obj.getHours();
				var _minute = _date_obj.getMinutes();
				var _second = _date_obj.getSeconds();
				var _create_time = _year+"-"+_month+"-"+_day+" " + _hour + ":" +_minute+":"+_second;
				var user_name = data.rms1[i].user_name;
				lis1 += 
				"<li class='product-comment'>"
			        +"<article class='comment-time-user clearfix'>"
			            +"<span class='comment-user'>"+user_name+"</span>"
			            +"<time class='comment-time' pubdate='pubdate'>"+_create_time+"</time>"
			        +"</article>"
			        +"<p class='comment-text'>"+comment+"</p>"
			    +"</li>";
        	}
        	$("#content1").append(lis1);
        	//中评
        	var lis2 = "";
        	var lg2 = data.rms2.length;
        	for(var i=0; i<lg2; i++)
        	{
        		var comment = data.rms2[i].comment;
        		var create_time = data.rms2[i].create_time;
				var _date_obj = new Date(create_time);
				var _year = _date_obj.getFullYear();
				var _month = _date_obj.getMonth()+1;
				var _day = _date_obj.getDate();
				var _hour = _date_obj.getHours();
				var _minute = _date_obj.getMinutes();
				var _second = _date_obj.getSeconds();
				var _create_time = _year+"-"+_month+"-"+_day+" " + _hour + ":" +_minute+":"+_second;
        		var user_name = data.rms2[i].user_name;
        		lis2 += 
        			"<li class='product-comment'>"
        			+"<article class='comment-time-user clearfix'>"
        			+"<span class='comment-user'>"+user_name+"</span>"
        			+"<time class='comment-time' pubdate='pubdate'>"+_create_time+"</time>"
        			+"</article>"
        			+"<p class='comment-text'>"+comment+"</p>"
        			+"</li>";
        	}
        	$("#content2").append(lis2);
        	//差评
        	var lis3 = "";
        	var lg3 = data.rms3.length;
        	for(var i=0; i<lg3; i++)
        	{
        		var comment = data.rms3[i].comment;
        		var create_time = data.rms3[i].create_time;
				var _date_obj = new Date(create_time);
				var _year = _date_obj.getFullYear();
				var _month = _date_obj.getMonth()+1;
				var _day = _date_obj.getDate();
				var _hour = _date_obj.getHours();
				var _minute = _date_obj.getMinutes();
				var _second = _date_obj.getSeconds();
				var _create_time = _year+"-"+_month+"-"+_day+" " + _hour + ":" +_minute+":"+_second;
        		var user_name = data.rms3[i].user_name;
        		lis3 += 
        			"<li class='product-comment'>"
        			+"<article class='comment-time-user clearfix'>"
        			+"<span class='comment-user'>"+user_name+"</span>"
        			+"<time class='comment-time' pubdate='pubdate'>"+_create_time+"</time>"
        			+"</article>"
        			+"<p class='comment-text'>"+comment+"</p>"
        			+"</li>";
        	}
        	$("#content3").append(lis3);
        },
        error: function(e){console.log(e)}
    });
}

//图片放大功能
function imageTurnBig()
{
	$("#big_content_1").find("div.content-slide").find("p").find("img").each(function()
	{
		$(this).click(function()
		{
			var o = $(this).clone();
			o.css({"position":"fixed", "top":"0px", "left":"0px", "z-index":"999999"});
			$(".mask_div_img").show();
			$(".mask_div_img").html(o);
			new RTP.PinchZoom(o, {});
		});
	});
	$(".mask_div_img").click(function()
	{
		$(this).hide();
		$(this).html("");
	});
}