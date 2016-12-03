$(function()
{
	//判断是否有折扣
	var zkouCount = $.trim($("#zkouCount").val());
	if(zkouCount == 0 || zkouCount == "0")
	{
		$("#pcount").hide();
	}
	
	//商品ID
	var id = $.trim($("#goodsid").val());
	
	//获取商品价格
	getPriceFromServer(id);
	
	//计算折扣
	discount();
	
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
	
	//返回按钮判断
	judeBackBtn();
	
	//图片放大功能
	try{imageTurnBig();}catch(e){console.log("e=["+e+"]")}
});

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

//提交需求
function submitNeed()
{
   var $id = $.trim($("#goodsid").val());
   $.ajax(
   {
		url: "/index!jugeStatus.action?id="+$id,
		type: "POST",
		cache: false,
		async: false,
		success: function(data) 
		{
			var $v = false;
		    //上架
			if(data == 1)
			{
				$v = true;
			}
			//下架
			if(data == 2)
			{
				$alert("warn","该商品已下架！");
			}
			//有库存
			if(data == 3)
			{
				$v = true;
			}
			//无库存
			if(data == 4)
			{
				$alert("warn","该商品已无库存！");
			}
			if($v)
			{
				if(checklogin())
				{
					window.location.href = "/member/reserveOrder!toReserveOrderAdd.action?goodsId="+$id;
				}
				else
				{
					window.location.href = "/login/login!index.action?redirectUrl="+location.href;
				}
			}
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

//图片放大功能
function imageTurnBig()
{
	$(".rx-images").find("p").find("img").each(function()
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