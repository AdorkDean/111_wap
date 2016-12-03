//导航初始化
var myScroll;
function loaded() 
{
	var sum = $("#csum").val();
	for(var i=0;i<sum;i++)
	{
		myScroll = new IScroll('.wrapper'+i, { eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false });
	}
	$("#p_curPage").val('0');
	appendGoodList();
}
//微信初始化认证
function initWechat()
{	
    var surl = window.location.href;
	$.ajax({
	    url: "/wx/wechat!getSignInfoFromRemote.action?signUrl="+encodeURIComponent(surl),
		type: "GET",
		cache: false,
		async: false,
		success: function(data){
			eval("var datas = " + data+";");
			wx.config({
			    debug: false, 
			    appId: datas.appId, 
			    timestamp: datas.timestamp, 
			    nonceStr: datas.nonceStr, 
			    signature: datas.signature,
			    jsApiList: datas.jsApiList
			});
	     }
     });
}
//初始化分享参数
function initParam()
{
	//分享图片
	var shareImageUrl = $.trim($("#shareImageUrl").val());
	//分享语
	var shareWords = $.trim($("#shareWords").val());
	//Banner图
	var bannerImgUrl = $.trim($("#bannerImgUrl").val());
	var imgUrl;
	if(shareImageUrl == null || shareImageUrl == '' || shareImageUrl == undefined)
	{
		imgUrl = bannerImgUrl;
	}
	else
    {
    	imgUrl = shareImageUrl;
    }
	var title = document.title;
	var desc = shareWords;
	var url = window.location.href;
	wx.ready(function()
	{
		//分享给朋友
		wx.onMenuShareAppMessage({
		    title: title, 
		    desc: desc,
		    link: url,
		    imgUrl: imgUrl,
		    type: '',
		    dataUrl: '',
		    success: function (){ 
		    },
		    cancel: function (){ 
		    }
		});
		//分享到朋友圈
		wx.onMenuShareTimeline({
		    title: title,
		    link: url,
		    imgUrl: imgUrl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
		//分享到QQ
		wx.onMenuShareQQ({
			title: title,
			desc: desc,
			link: url,
			imgUrl: imgUrl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
	});	
}
//菜单点击事件
function toHealty(id)
{
	window.location.href = "/static/healthypla/new/"+id+".html";
}
//关闭阅读层
function closeDocument()
{
	$(".b-cover").hide();
}

$(function()
{
 	//回健康方案列表页
 	$("#backListPage").click(function()
 	{
 		window.location.href = "/web/health_plan/health_plan.html";
 	});
 	
 	try
 	{
	 	//组合套装计算价格
	 	getDynamicPrice();
	 	
	 	//动态获取关联商品的价格
	 	getPriceInGoods();
	 	
 		//获取头部导航分类名称
		getNavication();
		
		//导航初始化
		loaded();
		
		//微信分享初始化
		initWechat();
 	}
 	catch(e)
 	{
 		console.log(e);
 	}

	//分享按钮点击事件
	$("#shareBtnByhealthy").click(function()
	{
		$(".mask-ui").show();
		$(".share-img").show();
		window.setTimeout(function()
		{
			$(".mask-ui").hide();
			$(".share-img").hide();
		},1000);
		initParam();
	});
});

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
			//WAP站价格
			var wap_price = parseFloat($.trim($("#wap_price_"+i+"_"+j).val()));
			//原价格
			var old_price = parseFloat($.trim($("#old_price_"+i+"_"+j).val()));
			totalWapPrice += wap_price;
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

//动态获取商品价格
function getDynamicPrice()
{
	var bigSize = $.trim($("#bigSize").val());
	var ps = "";
	for(var i=0; i<bigSize; i++)
	{
		var smallSize = $.trim($("#smallSize_"+i).val());
		for(var j=0; j<smallSize; j++)
		{
			var goods_id = $.trim($("#goods_id_"+i+"_"+j).val());
	  		var hj = "wap_price_"+i+"_"+j;
	  		var p1 = "{'goodsId':"+goods_id+",'nowPrice':'00.00','platform':'2','htmlObjId':'"+hj+"'}"; 
	  		ps += p1 + ",";
		}
	}
	ps = ps.substring(0,ps.length-1);
  	var p_start = "[" ;
	var p_end = "]" ;
	var params = p_start + ps + p_end ;
    $.ajax
    ({
        type: "post",
        url: "/index!countpricefrompc.action",
        data: {"params":params},
        async: false,
        success: function(data)
        {
            if(data != null && data != '' && data != undefined)
            {
                for(var i=0; i<data.length; i++)
                {
                    var nowPrice = data[i].nowPrice;
                    //HTML对象ID
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
			var p1 = "{'goodsId':"+goods_id+",'nowPrice':'0','platform':'2','htmlObjId':'"+hj+"'}";
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
				var p2 = "{'goodsId':"+goods_id+",'nowPrice':'0','platform':'2','htmlObjId':'"+hj+"'}";
				ps += p2 + ",";
			}
		}
		//关联三个商品
		var threeObj = document.getElementById("obj_three_"+i);
		if(threeObj != null)
	    {
	    	var hj1 = "goods_price_three_in_1_"+i;
			var goods_id_1 = $.trim($("#goods_id_three_in_1_"+i).val());
			var p3_1 = "{'goodsId':"+goods_id_1+",'nowPrice':'0','platform':'2','htmlObjId':'"+hj1+"'}";
			
	    	var hj2 = "goods_price_three_in_2_"+i;
			var goods_id_2 = $.trim($("#goods_id_three_in_2_"+i).val());
			var p3_2 = "{'goodsId':"+goods_id_2+",'nowPrice':'0','platform':'2','htmlObjId':'"+hj2+"'}";
			
	    	var hj3 = "goods_price_three_in_3_"+i;
			var goods_id_3 = $.trim($("#goods_id_three_in_3_"+i).val());
			var p3_3 = "{'goodsId':"+goods_id_3+",'nowPrice':'0','platform':'2','htmlObjId':'"+hj3+"'}";
			
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
        type: "post",
        url: "/index!countpricefrompc.action",
        data: {"params":params},
        async: false,
        success: function(data)
        {
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

//获取头部导航分类名称
function getNavication()
{
	var hid = $.trim($("#hid").val());
	var url = "/healthyplan/healthyplan!getPlanCategorys.action?hid="+hid;
	$.post(url,null,function(data)
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
	});
}

//商品详情
function product(id,name)
{
	window.location.href="/m/"+id+".html"
}

//展开详情
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

//组合套装加入购物车
function healthysAddCart(i)
{
	//显示加载图标
	$(".mask-ui").show();
	$("#loadimg").show();
	
	window.setTimeout(function()
	{
		var smallSize = $.trim($("#smallSize_"+i).val());
		var message = "";
		for(var j=0; j<smallSize; j++)
		{
			//商品ID
			var goodsId = $.trim($("#goods_id_"+i+"_"+j).val());
			//商品名称
			var goodsName = $.trim($("#goods_name_"+i+"_"+j).val());
			var rs = add_cart(goodsId,1);
			message += "【"+goodsName+"】"+ getMessage(rs) + "\n";
		}
		//更新购物车
		getCartSum(1);
		//隐藏加载图标
		$(".mask-ui").fadeOut();
		$("#loadimg").fadeOut();
		window.setTimeout(function()
		{
			alert(message);
		},400);
	},1000);
}

//单品加入购物车
function healthyAddCart(num, gid)
{
	var rs = add_cart(gid,num);
    if(rs>0)
    {
		$alert("success","已成功加入购物车！");
		getCartSum(1);
		return false;
	}
	else if(rs==-2 || rs==-1)
	{
		$alert("warn","库存不足！");
		return false;
	}
	else if(rs==-3)
	{
		$alert("warn","该商品正促销中，超出了购买数量！");
		return false;
	}
	else if(rs==-100)
	{
		$alert("warn","该商品为处方药，请咨询购买！");
		return false;
	}
	else if(rs==-200)
	{
		$alert("warn","该商品为下架商品！");
		return false;
	}
	else if(rs==-300)
	{
		$alert("warn","您已经购买过该促销商品了！");
		return false;
	}
	else if(rs==0)
	{
		$alert("warn","操作失败！");
		return false;
    }
}

//购物车加入成功后信息返馈
function getMessage(rs)
{
	if(rs>0)
    {
		return "已成功加入购物车！";
	}
	else if(rs==-2 || rs==-1)
	{
		return "库存不足！";
	}
	else if(rs==-3)
	{
		return "正促销中，超出了购买数量！";
	}
	else if(rs==-100)
	{
		return "为处方药，请咨询购买！";
	}
	else if(rs==-200)
	{
		return "为下架商品！";
	}
	else if(rs==-300)
	{
		return "您已经购买过该促销商品了！";
	}
	else if(rs==0)
	{
		return "操作失败！";
    }
}