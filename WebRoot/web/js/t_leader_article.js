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
					url: "../leader/leaderArticle!ajaxLeaderArticlePage.action",
					type: "POST",
					data: {"rs.p_curPage":p_curPage,"rs.pageSize":pageSize,"random":Math.random()},
					async:false,
					success: function(data){
						var resultList = data.result;
						if(resultList!=undefined&&resultList.length>0){
							 for ( var i = 0; i < resultList.length; i++) {
							 	var leaderArt = resultList[i];
							 	var appendHtml = ""
								 	+"<li class='clearfix'>"
								 	 +"	<a href=\"\/static\/leader\/user\/" + leaderArt.id + ".html?code="+ $leaderCode +"&id=" +$leaderId+ "\" class=\"article-link\">"
								 	+"		<a href='javascript:void(0);' class='article-link'>"
								 	+"			<img src='"+leaderArt.shareImageUrl+"'>"
								 	+"			<div class='article-introduce'>"
								 	+"				<p class='article-title'>"+leaderArt.title+"</p>"
								 	+"				<div class='article-state clearfix'>"
								 	+"					<span>"+"<font class=\"f_" + leaderArt.id + "\">" + leaderArt.clickLikeSum + "</font>点赞</span>"
								 	+"					<span>"+leaderArt.lookSum+"阅读</span>"
								 	+"					<span>"+(leaderArt.commentSum==null?0:leaderArt.commentSum)+"分享</span>"
								 	+"				</div>"
								 	+"				<p class='article-info'>"+leaderArt.shareTitle+"</p>"
								 	+"			</div>"
								 	+"		</a>"
								 	+"		<a href='javascript:void(0);' onclick=\"getArticleShareUrlJs('" + leaderArt.title +"','" + leaderArt.shareTitle +"','" + leaderArt.shareImageUrl +"','" + leaderArt.id + "','" + $leaderId + "','" + $leaderCode +"');showShareImg();\" class='share'>分享</a>"
								 	+"		<i class='cut-line'></i>"
								 	+"	</li>";
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
	

/**
 * 点赞我的领袖文章
 * 
 */
function clickLeaderArticleLikeArticle(articleId){
	/*$.ajax({
		url: "/leader/leaderCenter!ajaxClickLikeArticle.action",
		type: "POST",
		data: {"articleId":articleId,"random":Math.random()},
		success: function(data){
			alert(data);
			//$(".f_"+articleId).html(parseInt($(".f_"+articleId).html())+1);
			$(".f_"+articleId).html(data);
		},
		error:function(data){
		}
	});*/
	
	$.post("/artic/artic!ajaxClickLikeLeaderArticle.action?articleId="+articleId,{random:Math.random()}, function(data)
	{
		$(".f_"+articleId).html(data);
	});
}

/**
 * 点赞推荐领袖文章
 * 
 */
function clickGoodsArticleLikeArticle(articleId){
	$.post("/artic/artic!clickGoodsArticleLikeSum.action?id="+articleId,{random:Math.random()}, function(data)
	{
		$(".fga_" + articleId).html(data);
	});
}