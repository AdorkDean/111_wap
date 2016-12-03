var $leaderId = $("#leaderId").val();
var $leaderCode = $("#leaderCode").val();

$(function(){
	$("input[name='rs.p_curPage']").val(1);
	$("input[name='rs.p_curSize']").val(10);
});
$(function()
{
	//初始化微信
	try
	{
		var url = "/leader/leaderArticle!leaderArticle.action";;
		initWxConfigCommon(url);
		//initWxConfigForArticle();
	}catch(e){}

	
	//获取领袖文章信息,默认取第一条文章
	var articleId = $("#ga_id").val();
	var $title = $("#ga_title").val();
	var $share_title = $("#ga_share_title").val();
	var $share_image_url = $("#ga_share_image_url").val();
	
	getGoodsArticleShareUrlJs($title,$share_title,$share_image_url,articleId,$leaderId,$leaderCode);
});

