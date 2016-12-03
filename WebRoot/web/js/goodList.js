	$(document).ready(function(){
		$(window).scroll(function(){
		if(isBottom(this))
		{
			appendGoodList();
		}
			});
});

function appendGoodList(){
	
	var categoryid=$("#categoryid").val();
	var p_curPage=parseInt($("#p_curPage").val())+1;
	$("#p_curPage").val(parseInt($("#p_curPage").val())+1);
			$.post("/goods/goodsList!jiankangGoodsList.action",{"categoryid":categoryid,"rs.p_curPage":p_curPage,"random":Math.random()},function(data){
				var content=data;
				if(content=="-1"){
				flag=false;
				if($.trim($("#goodsList").html()).indexOf("没有更多商品") < 0)
				{
					$("#goodsList").append("<li style='width:100%;text-align:center;padding-top:10px;'>没有更多商品</li>");
				}
				}else{
				$("#goodsList").append(content);
				}
			});
}