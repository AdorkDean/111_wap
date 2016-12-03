//获取分类ID
var globerUri = document.location.href;
var us = globerUri.split("keyword=");
var search_result_keyword = decodeURI(decodeURI(us[1]));
var search_result_visitUrl = "http://testrouter.111yao.com";
var search_result_imageUrl = "http://img.zdfei.com/";
var search_result_pageNo = 1;
var search_result_pageSize = 5;
var search_result_ptype = 1;
$(function()
{
	//获取购物车数量
	getCartSum(1);
	
	//获取接口数据
	getDataFromServer(search_result_keyword, search_result_ptype, search_result_pageNo, search_result_pageSize);
	
	$(window).scroll(function()
	{
		if(isBottom(this))
		{
			search_result_pageNo += 1;
			getDataFromServer(search_result_keyword, search_result_ptype, search_result_pageNo, search_result_pageSize);
		}
	});
	
	//按放大镜进行搜索时
	$("#result_search_btn_new_id").click(function()
	{
		//激活搜索事件
		focusSearchEvent($("#result_search_input").val());
	});
	$("#result_search_input").focus(function()
	{
		//激活搜索事件
		focusSearchEvent($("#result_search_input").val());
		//$("#result_content_id").addClass("scroll_content");
	});
	$("#result_search_input").val(search_result_keyword);
	
});

/**
 * 功能：获取接口数据
 * cId： 分类ID
 * pageNo：当前页
 * pageSize: 每页显示条数
 */
function getDataFromServer(search_result_keyword, search_result_ptype, search_result_pageNo, search_result_pageSize)
{
	$.ajax
	({
		type: "GET",
		url: search_result_visitUrl + "/sltRouter?method=getSearchResult",	 
		data:{"keyword":search_result_keyword, "ptype":search_result_ptype, "pageNo":search_result_pageNo, "pageSize":search_result_pageSize,"from":"2"},
		async:false, 
		dataType: "jsonp",
		jsonp: "jsoncallback",
		success: function(data)
		{
			var lg = data.list.length;
			if(lg > 0)
			{
				var lis = "";
				var o = data.list;
				for(var i=0; i<lg; i++)
				{
					var id = o[i].pid;
					var discount = o[i].discount;
					var image = search_result_imageUrl + o[i].image;
					var marketPrice = o[i].marketPrice;
					var name = o[i].name;
					var price = o[i].price;
					var type = o[i].type;
					var isCFY = "<a class='iconfont_"+id+"' id='new_list_pro_into_cart' onclick='flyToCart("+id+")'></a>";
					if(type != 1)
					{
						isCFY = "";
					}
					var isDiscount = "<span class='new-discount'>"+discount+"折<i></i></span>";
					if(discount == 0)
					{
						isDiscount = "";
					}
					lis +=
					"<li>"+
			            "<a class='list-pro-info'>"+
			            "<div class='new-list-pro'>"+
			            isDiscount +
			            "   <img src='"+image+"' onclick=\"window.location.href='/m/"+id+".html'\"/>"+
			            "</div>"+ 
			            "<p class='new-list-pro-title'>"+name+"</p>"+
			            "<p class='new-list-pro-price'>¥"+price+"</p>"+
			            "<p><b class='new-list-pro-original'>¥"+marketPrice+"</b></p>"+
			            isCFY +
			       		"</a>"+
			       		"<input type='hidden' value='"+id+"'/>"+
		            "</li>";
				}
				$("#new_goods_list").append(lis);
			}
			else
			{
				flag = false;
				$alert("warn","已经没有更多商品可以显示了！");
			}
		},
		error: function(e)
		{
			console.log("e=["+e+"]");
		}
	});   
}

/**
 * 功能：根据类型检索商品
 * type: 检索条件、1综合相关、2折扣优先、3价格优先
 * hid:  标签序号
 */
function searchByType(type, hid)
{
	 flag = true;
	for(var i=1; i<=3; i++)
	{
		if(i == hid)
		{
			$("#rel_category_"+i).addClass("current");
		}
		else
		{
			$("#rel_category_"+i).removeClass("current");
		}
	}
	$("#new_goods_list").html("");
	search_result_pageNo = 1;
	search_result_ptype = type;
	getDataFromServer(search_result_keyword, search_result_ptype, search_result_pageNo, search_result_pageSize);
}

//激活搜索事件
function focusSearchEvent(search_result_keyword)
{
	window.scroll(0, 0);
	$("#result_content_id").hide();
	$(".search_include_ui").show();
	$("#search-value-ui-id").focus();
	$("#search-value-ui-id").val(search_result_keyword);
	//热搜词初始化滚动事件
	new IScroll('.wrapper3', {eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false});
}

//飞一样加入购物车
var offset = $(".end").offset();
function flyToCart(id)
{
	var img = $(".iconfont_"+id).parent().find('img').attr('src');
	var flyer = $('<img class="u-flyer" src="'+img+'" >');
	var oWidth = $('.product-list-box li').width()/2;
	var oHeight = $('.product-list-box li').height()/2;
	flyer.css({"width":"50px"});
	var status = add_cart(id, 1);
	if(status>0)
	{
		flyer.fly({
			start: {
				left: $(".iconfont_"+id).offset().left-$(document).scrollLeft()-oWidth,
				top: $(".iconfont_"+id).offset().top-$(document).scrollTop()-oHeight
			},
			end: {
				left: offset.left+20,
				top: offset.top+20,
				width: 0,
				height: 0
			},
			onEnd: function(){
				var count = parseInt($('.count-msg').html());
				count++;
				if(count>99){
					$('.count-msg').html("99+");
				}else{
					$('.count-msg').html(count);
				}
				$(".count-msg").fadeIn();
			}
		});
		window.setTimeout(function()
		{
			$alert("success","已成功添加至购物车！");
		},1000);
		return false;
	}
	if(status<=0)
	{
		mywarn(status);
	}
}