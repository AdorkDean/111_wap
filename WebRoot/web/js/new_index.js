$(function()
{
	//轮播
	$(".slider").yxMobileSlider({width:720,height:330,during:3000})
	//消息滚动
	$("#marquee6").jCarouselLite({auto:3000,speed:500,vertical:true,visible:1});
	//商品区域滑动事件初始化
	$(document).find(".index-wrapper").each(function()
	{
		var id = $(this).attr("id");
		var scrollCount = $(this).find(".index-scroller ul li").length;
		$(this).find(".index-scroller").css({"width":scrollCount * 90 +"px"});
		new IScroll("#"+id, {eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false });
	});
	//动态获取商品价格
	getDynamicPrice();
	//标签定位
	$("#tab_class_1").addClass("current");
	//搜索
	$("#index_search_input").focus(function()
	{
		$(".search_include_ui").show();
		$("#search-value-ui-id").focus();
		//热搜词初始化滚动事件
		new IScroll('.wrapper3', {eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false});
		$(".new-index-main").hide();
	});
});
//动态获取商品价格
function getDynamicPrice()
{
	var ps = "";
	$(document).find(".index-wrapper").each(function(j)
	{
		$(this).find(".index-scroller ul li").each(function(i)
		{
			var v = $(this).find("input[type='hidden']").val();
			var id = "scroll_pro_price_"+j+"_"+i;
			var hj = $(this).find("p.scroll-pro-price").attr("id",id);
			var p1 = "{'goodsId':"+v+",'nowPrice':'00.00','platform':'2','htmlObjId':'"+id+"'}"; 
	  		ps += p1 + ",";
		});
	});
	ps = ps.substring(0,ps.length-1);
  	var p_start = "[" ;
	var p_end = "]" ;
	var params = p_start + ps + p_end ;
	console.log("params= "+params);
    $.ajax
    ({
        type: "POST",
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
        error: function(e)
        {
            console.log("error:"+e);
        }
    });
}

