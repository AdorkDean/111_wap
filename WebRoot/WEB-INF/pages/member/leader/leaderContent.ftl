<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<!--允许全屏-->
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="fullscreen=yes,preventMove=no" name="ML-Config">
<!--缩放比例-->
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>健康领秀-素材-文字</title>
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/new-health.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
<style rel="stylesheet" type="text/css">
.text-main{width:100%; color:#3e3e3e;  border: none;  overflow: hidden; }
</style>
</head>

<body>
<article class="health-center-material">
	<p class="share-sc-tips" onclick="test();">选择自己喜欢的图片、文字保存到手机分享给朋友</p>
    <div class="material-tag">
    	<div class="wrap">
            <div class="tabs">
               <a href="${base}/leader/leaderArticle!leaderImg.action" >图片</a>
                <a href="${base}/leader/leaderArticle!leaderContent.action" class="active">文字</a>
            </div>     
            <div class="text-container">
            
                 <ul class="material-text clearfix clear" id="conids">
                <#list  pw.result?if_exists as resc>
					<li>
						<div class="margin-text-detals" contenteditable="true" id="pcid${resc.id}">${resc.context}</div>
                        <a href="javascript:void(0);" onclick="javascript:highlightAll('${resc.id}')"><span><i class="copy_ico"></i><br>复<br>制</span></a>
                    </li>
				</#list>
				<#if pw.result?size==0>
					<li>暂时没有数据可以显示！</li>
				</#if>
                </ul>
            </div>
        </div>
    </div>
</article>
<script type="text/javascript">
function highlightAll(cids) {  
	$("#pcid"+cids).setEditable(false);
	$("#pcid"+cids).focus();
	$("#pcid"+cids).select();
}  
var pageNo = 2;
var pageSize = 10;
$(function()
	{
		$(window).scroll(function()
		{
			if(isBottom(this))
			{
				$("#loadimg").show();
				$.ajax(
				{
					url: "${base}/leader/leaderArticle!leaderContentList.action?pageNo="+pageNo+"&pageSize="+pageSize,
					type: "GET",
					dataType: "json",
					cache: false,
					async: false,
					success: function(data) 
					{
						pageNo += 1;
						var liObject = "";
						var datas = eval(data.list);
						if(datas != '' && datas != null){
							for(var i=0;i<datas.length;i++){
						        liObject += 
									"<li>"+
                                	"<p class='margin-text-detals'>"+datas[i].context+"</p>"+
                                    "<a href='#'><span><i class='copy_ico'></i><br>复<br>制</span></a>"+
                               		"</li>";
							}
						}else{
							flag = false;
							liObject += "<li>暂时没有更多数据可以显示！</li>";
						}
						$("#conids").append(liObject);
						window.setTimeout(function()
						{
							$("#loadimg").hide();
						},500)
					}
				});
			}
		});
	});
</script>
</body>
</html>