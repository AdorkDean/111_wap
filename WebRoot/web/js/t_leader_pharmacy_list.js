$(function(){
	$("input[name='rs.p_curPage']").val(1);
	$("input[name='rs.p_curSize']").val(10);
});
var ii = 0;
	$(document).ready(function(){
		$(window).scroll(function(){
		
			if(isBottom(this) && ii == 0) {
//				ii +=1;
				var p_curPage=parseInt($("#p_curPage").val())+1;
				$("#p_curPage").val(parseInt($("#p_curPage").val())+1);
				var pageSize=$("#pageSize").val();
				$("#lazy_dh").show();
				$.ajax({
					url: "../leader/leaderArticle!ajaxLeaderPharmacyPage.action",
					type: "POST",
					data: {"rs.p_curPage":p_curPage,"rs.pageSize":pageSize,"random":Math.random()},
					async:false,
					success: function(data){
						var resultList = data.result;
						if(resultList!=undefined&&resultList.length>0){
							 for ( var i = 0; i < resultList.length; i++) {
							 	var leaderPha = resultList[i];
							 	var appendHtml = ""
								 	+"<li class='clearfix'>"
									+"	<a  href='/m/"+leaderPha.goodsId+".html' class='article-link'>"
									+"		<img src='http://img.zdfei.com"+leaderPha.abbreviation_picture+"'>"
									+"		<div class='article-introduce'>"
									+"			<p class='pharmacy-title'>"+(leaderPha.goods_name==null?"":leaderPha.goods_name)+"</p>"
									+"			<p class='pharmacy-rebate'>返佣：¥"+(leaderPha.rebate_amount==null?"0":leaderPha.rebate_amount)+"</p>"
									+"			<p class='pharmacy-price'>价格：¥"+(leaderPha.price==null?"0":leaderPha.price)+"</p>"
									+"		</div>"
									+"	</a>"
									+"	<a href='javascript:void(0);' onclick=\""+ "getGoodsShareUrlJs(" + leaderPha.goodsId +",'"+ leaderPha.goods_name +"','"+ leaderPha.main_title+"','"+ leaderPha.sub_title+"','"+ leaderPha.abbreviation_picture +"'," + "'${leader.code?if_exists}'"  + ");showShareImg();\""+ "class='share'>分享</a>"
									+"	<a href='javascript:void(0);' onclick=\""+ "deleteMyPharmacyGoods(" + leaderPha.pharmacy_id + ")\""+ "class='delete-pro'>删除</a>"
									+"	<i class='cut-line'></i>"
									+"</li>";
								 	
									
							 	$("#appendUl").append(appendHtml);
							 }
							 $("#lazy_dh").hide();
							 $("#noMore").attr("style","display:none");
							 $("#getMore").attr("style","display:block");
							 
						} else{//没有了
						    $("#lazy_dh").hide();
							$("#getMore").attr("style","display:none");
							$("#noMore").attr("style","display:block");
						}
					},error:function(data){}
				});
			}
		
		});
});
	
	
	
	$(function()
			{
				//初始化微信
				try
				{
					var url = "/leader/leaderArticle!leaderPharmacy.action";
					initWxConfigCommon(url);
					//initWxConfig();
				}catch(e){}

				//获取领袖信息
				var $goodId = $("#goodId").val();
				var $leaderCode = $("#leaderCode").val();
				var $short_name = $("#goods_name").val();
				var $main_title = $("#main_title").val();
				var $sub_title = $("#sub_title").val();
				var $abbreviation_picture = $("#abbreviation_picture").val();
				//($goodId,short_name,main_title,sub_title,abbreviation_picture,$leaderCode){
				
				
				//getGoodsShareUrl($goodId,$leaderCode);
				//getGoodsShareUrlJs($goodId,$short_name,$main_title,$sub_title,$abbreviation_picture,$leaderCode);
				
				

				
				

			});	
	
	$(".mask-ui").hide();
	$(".share-img").hide();