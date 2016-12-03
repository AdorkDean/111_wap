var $leaderId = $("#leaderId").val();
var $leaderCode = $("#leaderCode").val();

$(function(){
	$("input[name='rs.p_curPage']").val(1);
	$("input[name='rs.p_curSize']").val(10);
});

var ii = 0; 
$(document).ready(function(){
	$(window).scroll(function(){
	
		if(isBottom(this) && ii == 0) {
			var p_curPage=parseInt($("#p_curPage").val())+1;
			$("#p_curPage").val(parseInt($("#p_curPage").val())+1);
			var pageSize=$("#pageSize").val();
			$("#lazy_dh").show();
			$.ajax({
				url: "../leader/leaderArticle!ajaxRecommendArticle.action",
				type: "POST",
				data: {"rs.p_curPage":p_curPage,"rs.p_pageSize":pageSize,"random":Math.random()},
				async:false,
				success: function(data){
					var resultList = data.result;
					if(resultList!=undefined&&resultList.length>0){
						 for ( var i = 0; i < resultList.length; i++) {
						 	var leaderArt = resultList[i];
						 	var appendHtml = ""
							 	+"<li class=\"clearfix\">"
					            +"	<a href=\"\/static\/leader\/" + leaderArt.id + ".html?code="+ $leaderCode +"&id=" +$leaderId+ "\" class=\"article-link\">"
					            +"        <img src=\"http://img.zdfei.com/"+leaderArt.share_image_url+"\">"
					            +"        <div class=\"article-introduce\">"
					            +"            <p class=\"article-title\">"+leaderArt.title+"</p>"
					            +"            <div class=\"article-state clearfix\">"
					            +"            	<span><font class=\"fga_" + leaderArt.id + "\">" + leaderArt.click_like_sum + "</font>点赞</span>"
					            +"            </div>"
					            +"            <p class=\"article-info\">"+leaderArt.share_title+"</p>"
					            +"        </div>"
					           +"     </a>"
					           +"	  <a href='javascript:void(0);' onclick=\"getGoodsArticleShareUrlJs('" + leaderArt.title +"','" + leaderArt.share_title +"','" + leaderArt.share_image_url +"','" + leaderArt.id + "','" + $leaderId + "','" + $leaderCode +"');showShareImg();\" class='share'>分享</a>"
					            +"    <i class=\"cut-line\"></i>"
					           +" </li>";
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
				var url = "/leader/leaderArticle!recommendArticle.action";
				initWxConfigCommon(url);
			}catch(e){}
			//获取领袖文章信息,默认取第一条文章
			var articleId = $("#ga_id").val();
			var $title = $("#ga_title").val();
			var $share_title = $("#ga_share_title").val();
			var $share_image_url = $("#ga_share_image_url").val();
			getGoodsArticleShareUrlJs($title,$share_title,$share_image_url,articleId,$leaderId,$leaderCode);
		});