var hlp_url = ROUTER_SERVER_URI + "/sltRouter?method=getPriceByFlatform";
var jsonp_url = WAP_SERVER_URI + "/healthyplan/healthyplan!getPlanCategorysJSONP.action";
$(function()
{
	//收起详细内容
	$('.describ-x').click(function()
	{
		$('.b-cover').hide();	
	});
	
	//获取头部导航分类名称
	try{getNavication();}catch(e){console.log(e)}
	
	//动态获取关联商品的价格
    try{getPriceInGoods();}catch(e){console.log(e)}
	
	//组合套餐动态获取商品价格
    try{getDynamicPriceWithCombination();}catch(e){console.log(e)}
	
});

//动态获取关联商品的价格
function getPriceInGoods()
{
	var pln = $.trim($("#priceLooperNumber").val());
	var ps = "";
	for(var i=0;i<pln;i++)
	{
		//关联一个商品
		var oneObj = document.getElementById("goods_price_one_in_"+i);
		if(oneObj != null)
		{
			var hj = "goods_price_one_in_"+i;
			var goods_id = $.trim($("#goods_id_one_in_"+i).val());
			var p1 = "{'goodsId':"+goods_id+",'nowPrice':'0','platform':'3','htmlObjId':'"+hj+"'}";
			ps += p1 + ",";
		}
		//关联两个商品
		var twoObj = document.getElementById("obj_two_"+i);
		if(twoObj != null)
		{
			var twoSize = parseInt($.trim($("#obj_two_size_"+i).val()));
			for(var j=0; j<twoSize; j++)
			{
				var hj = "goods_price_two_in_"+i+"_"+j;
				var goods_id = $.trim($("#goods_id_two_in_"+i+"_"+j).val());
				var p2 = "{'goodsId':"+goods_id+",'nowPrice':'0','platform':'3','htmlObjId':'"+hj+"'}";
				ps += p2 + ",";
			}
		}
		//关联三个商品
		var threeObj = document.getElementById("obj_three_"+i);
		if(threeObj != null)
	    {
	    	var hj1 = "goods_price_three_in_1_"+i;
			var goods_id_1 = $.trim($("#goods_id_three_in_1_"+i).val());
			var p3_1 = "{'goodsId':"+goods_id_1+",'nowPrice':'0','platform':'3','htmlObjId':'"+hj1+"'}";
			
	    	var hj2 = "goods_price_three_in_2_"+i;
			var goods_id_2 = $.trim($("#goods_id_three_in_2_"+i).val());
			var p3_2 = "{'goodsId':"+goods_id_2+",'nowPrice':'0','platform':'3','htmlObjId':'"+hj2+"'}";
			
	    	var hj3 = "goods_price_three_in_3_"+i;
			var goods_id_3 = $.trim($("#goods_id_three_in_3_"+i).val());
			var p3_3 = "{'goodsId':"+goods_id_3+",'nowPrice':'0','platform':'3','htmlObjId':'"+hj3+"'}";
			
			var $p3 = p3_1 + ","+p3_2+","+p3_3;
			ps += $p3 + ",";
	    }
	}
	ps = ps.substring(0,ps.length-1);
	var p_start = "[" ;
	var p_end = "]" ;
	var params = p_start + ps + p_end ;
    $.ajax
    ({
        type: "GET",
        url: hlp_url,
        data: {"params":params},
        async: false,
		dataType: "jsonp",
		jsonp: "jsoncallback",
        success: function(datas)
        {
        	var data = eval(datas);
		    if(data != null && data != '' && data != undefined)
            {
                for(var i=0; i<data.length; i++)
                {
                    var nowPrice = data[i].nowPrice;
                    var htmlObjId = data[i].htmlObjId;
                    $("#"+htmlObjId).html("¥"+nowPrice);
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

//组合套餐动态获取商品价格
function getDynamicPriceWithCombination()
{
	var bigSize = $.trim($("#bigSize").val());
	var ps = "";
	for(var i=0; i<bigSize; i++)
	{
		var smallSize = $.trim($("#smallSize_"+i).val());
		for(var j=0; j<smallSize; j++)
		{
			var goods_id = $.trim($("#goods_id_"+i+"_"+j).val());
	  		var hj = "app_price_"+i+"_"+j;
	  		var p1 = "{'goodsId':"+goods_id+",'nowPrice':'00.00','platform':'3','htmlObjId':'"+hj+"'}"; 
	  		ps += p1 + ",";
		}
	}
	ps = ps.substring(0,ps.length-1);
  	var p_start = "[" ;
	var p_end = "]" ;
	var params = p_start + ps + p_end ;
    $.ajax
    ({
        type: "GET",
        url: hlp_url,
        data: {"params":params},
        async: false,
		dataType: "jsonp",
		jsonp: "jsoncallback",
        success: function(datas)
        {
        	var data = eval(datas);
            if(data != null && data != '' && data != undefined)
            {
                for(var i=0; i<data.length; i++)
                {
                    var nowPrice = data[i].nowPrice;
                    var htmlObjId = data[i].htmlObjId;
                    $("#"+htmlObjId).val(nowPrice);
                }
                combinationPrice();
            }
            else
            {
                console.log("Datas null !");
                combinationPrice();
            }
        },
        error: function()
        {
            console.log("Server error !");
            combinationPrice();
        }
    });
}

//组合套装计算价格
function combinationPrice()
{
	var bigSize = $.trim($("#bigSize").val());
	for(var i=0; i<bigSize; i++)
	{
		var totalWapPrice = 0;
		var totalOldPrice = 0;
		var savePrice = 0;
		var smallSize = $.trim($("#smallSize_"+i).val());
		for(var j=0; j<smallSize; j++)
		{
			//APP站价格
			var app_price = parseFloat($.trim($("#app_price_"+i+"_"+j).val()));
			//原价格
			var old_price = parseFloat($.trim($("#old_price_"+i+"_"+j).val()));
			totalWapPrice += app_price;
			totalOldPrice += old_price;
		}
		savePrice = parseFloat(totalOldPrice - totalWapPrice);
		savePrice = savePrice.toFixed(2);
		//共节省
		$("#totalSave_"+i).html("¥"+savePrice);
		//组合价
		$("#combinePrce_"+i).html("¥"+totalWapPrice.toFixed(2));
		//原价
		$("#oldPrice_"+i).html("¥"+totalOldPrice.toFixed(2));
	}
}


//产品详情页
function product(id,name)
{
	document.location = "id*"+id+"*"+name;
}

//展开详细内容
function zhankai(t,i)
{
	var v = "";
	if(1==t)
	{
		v=$("#exp"+i+"").val();
	}
	else
	{
		v=$("#ccexp"+i+"").val();
	}
	$('.describ-details').text(v);
	$('.b-cover').show();
	$('.describ').animate({top:"30%"});
}

//获取头部导航分类名称
function getNavication()
{
	var hid = $.trim($("#hid").val());
	$.ajax
	({
		type: "get",
		url: jsonp_url,
		async: false,
		data: {"hid":hid},
		dataType: "jsonp",
		jsonp: "jsoncallback",
		success: function(data) 
		{  
			var liobj = "";
			for(var i=0; i<data.length; i++)
			{
				var cname = data[i].name;
				var id = data[i].id;
				var c = "";
				if(id == hid)
				{
					c = "curr";
				}
				liobj += "<li class='"+c+"' onclick='toHealty("+id+")'><a>"+cname+"</a></li>";
			}
			$(".health-nav-box-list ul").append(liobj);
			//动态控制滚动条长度
			var lg = document.getElementById("newscroller").getElementsByTagName("li").length;
			$(".health-nav-box-list").css("width",lg*90+"px");
			var isoll = new IScroll('.wrapper3', { eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false });
			var clientWidth = $(window).width();
		    var showLiNum = Math.floor(clientWidth / 90);
		    var lg = $("#newscroller li").length-1;
		    var currIndex = $("#newscroller li.curr").index();
		    if(lg == currIndex)
		    {
			    if (currIndex > (showLiNum - 1)) 
			    {
			    	var u = navigator.userAgent, app = navigator.appVersion;
			    	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
			    	if(isiOS)
			    	{
			    		isoll.scrollTo((-(currIndex - showLiNum + 1) * 90)+45, 0);
			    	}
			    	else
			    	{
				        isoll.scrollTo(-(currIndex - showLiNum + 1) * 90, 0);
			    	}
			    }
		    }
		    else
		    {
			    if (currIndex > (showLiNum - 1)) 
			    {
			        isoll.scrollTo(-(currIndex - showLiNum + 1) * 90, 0);
			    }
		    }
		    //为导航重新排序
			reSortNavicator();
			isoll.scrollTo(0, 0);
        },  
        error: function() 
        {  
            console.log("Server error !"); 
        }  
	});
}

//菜单点击事件
function toHealty(id)
{
	window.location.href = ROUTER_SERVER_URI + "/jklx/"+id+"-a.html";
}

//为导航重新排序
function reSortNavicator()
{
	var o = $("#newscroller li").eq(0);
	var n = null;
	$("#newscroller li").each(function(i)
	{
		var m = $(this);
		if(m.hasClass("curr"))
		{
			n = m;
		}
	});
	n.insertBefore(o);
}

//查看伴随症状内容
function show_content(type, index)
{
	if(type == 1)
	{
		$("#old-c-id-1-"+index).hide();
		$("#old-c-id-2-"+index).hide();
		$("#b-a-d-e-"+index).hide();
		var v = $.trim($("#exp-"+index).val());
		$("#s-p-s-z-z-"+index).html(v);
		$("#b-a-u-e-"+index).show();
	}
	if(type == 2)
	{
		$("#old-c-id-1-"+index).show();
		$("#old-c-id-2-"+index).show();
		$("#b-a-d-e-"+index).show();
		$("#s-p-s-z-z-"+index).html("");
		$("#b-a-u-e-"+index).hide();
	}
	if(type == 3)
	{
		$("#_old-c-id-1-"+index).hide();
		$("#_old-c-id-2-"+index).hide();
		$("#_b-a-d-e-"+index).hide();
		var v = $.trim($("#_exp-"+index).val());
		$("#_s-p-s-z-z-"+index).html(v);
		$("#_b-a-u-e-"+index).show();
	}
	if(type == 4)
	{
		$("#_old-c-id-1-"+index).show();
		$("#_old-c-id-2-"+index).show();
		$("#_b-a-d-e-"+index).show();
		$("#_s-p-s-z-z-"+index).html("");
		$("#_b-a-u-e-"+index).hide();
	}
}
