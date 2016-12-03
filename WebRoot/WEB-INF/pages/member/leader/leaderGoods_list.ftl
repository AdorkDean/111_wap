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
    <!--上线时请删除-->
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <!--上线时请删除-->
    <title>个人中心</title>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
</head>
<body class="bg-white">

<header class="header">
    <a href="javascript:history.go(-1);" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">健康领秀</h2>
    <a href="javascript:;" class="iconfont top-right-btn" style="width:70px;"></a>
    <a href="../member/profile.action" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
<article class="health-list">
    <ul class="health-info">
        <li><a href="${base}/leader/leader!leader.action">领秀中心</a></li>
        <li class="current"><a href="#">领秀药房</a></li>
        <li><a href="${base}/leader/leader!ranking.action">领秀排行</a></li>
    </ul>
    <div class="health-pharmacy">
        <table width="100%" cellpadding="0" cellspacing="0" id="goodstr">
            <tr>
                <td height="30" align="center" valign="middle" width="30%">商品名称</td>
                <td height="30" align="center" valign="middle" width="15%">规格</td>
                <td height="30" align="center" valign="middle" width="20%">价格</td>
                <td height="30" align="center" valign="middle" width="25%">佣金比例</td>
                <td height="30" align="center" valign="middle" width="10%"></td>
            </tr>
            <#list pw.result?if_exists as resc> 
            <tr onclick="extendEvent(${resc_index})" id="extend_${resc_index}">
                <td height="60" align="center" valign="middle" width="30%" id="trstyle${resc_index}">${resc.short_name?default('')}</td>
                <td height="60" align="center" valign="middle" width="15%" id="trstyle${resc_index}">${resc.spec?default('')}</td>
                <td height="60" align="center" valign="middle" width="20%" id="trstyle${resc_index}">￥${resc.wap_price?default('')}</td>
                <td height="60" align="center" valign="middle" width="25%" id="trstyle${resc_index}">${resc.brokerage?default('')}%</td>
                <td height="30" align="center" valign="middle" width="10%" id="trstyle${resc_index}"><img width="18" height="18" src="/web/images/s_t_down.png" id="img_turn_${resc_index}" style="margin-right:5px;"/></td>
            	<input type='hidden' value='1' id="tempV_${resc_index}"/>
            </tr>
            </#list> 
            
            <div class="showdata">
            <#list pw.result?if_exists as resc>
            <tr id="operation_${resc_index}" style="display:none;">
            	<td colspan="5">
		            <div class="lx-btn clearfix" style="width:100%; padding:15px 0; background:#d7d7d7; box-shadow:0 2px 3px 0 #b9b9b9 inset;">
		            	<span style="float:left; width:20%; text-align:center; display:block;">&nbsp;</span>
		                <span style="float:left; width:20%; text-align:center; display:block;"><img width="22" height="31" src="/web/images/s_detail_btn.png" onclick="window.location.href='/m/${resc.goods_id}.html';"/></span>
		                <span style="float:left; width:20%; text-align:center; display:block;"><img width="22" height="31" src="/web/images/s_h_buy.png" onclick="shortBug(${resc.goods_id})"/></span>
		                <span style="float:left; width:20%; text-align:center; display:block;"><img width="22" height="31" src="/web/images/s_h_btn.png" onclick="initWX('${img_ui1}${resc.abbreviation_picture?default('')}','${resc.short_name?default('')}','${resc.goods_id}')"/></span>
		                <span style="float:left; width:20%; text-align:center; display:block;">&nbsp;</span>
		            </div>
	            </td>
            </tr>
            </#list>
            </div> 
            
            <input type="hidden" id="size" value="${pw.result?size}"/>
        </table>
    </div>
</article>

<div class="mask-ui"></div>
<img class="share-img" src="${base}/web/images/share.png"/>
<style>
.mask-ui 
{
	position:fixed;
	z-index:99;
	background: rgba(22,22,22,.9);
	top:0;
	left:0;
	width:100%;
	height:100%;
	display:none;
}
.share-img
{
	position:fixed;
	z-index:999;
	top:0;
	left:0;
	width:100%;
	display:none;
}
</style>

<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
<#include "/static/inc/wap/footer.ftl"/>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/wap-cart.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript">
	//行展开函数
	function extendEvent(index)
	{
		var ctrl = parseInt($.trim($("#tempV_"+index).val()));
		if(ctrl == 1)
		{
			$("#extend_"+index).after("<tr id='operation_"+index+"'>"+$("#operation_"+index).html()+"</tr>");
			$("#tempV_"+index).val("2");
			$("#img_turn_"+index).attr("src","/web/images/s_t_up.png");
			try{
				for(var i=0;i<5;i++)
				{
					if(i != index)
					{
						$("#operation_"+i).hide();
						$("#tempV_"+i).val("1");
						$("#img_turn_"+i).attr("src","/web/images/s_t_down.png");
					}
				}
			}catch(e){}
		}
		else
		{
			$("#operation_"+index).hide();
			$("#tempV_"+index).val("1");
			$("#img_turn_"+index).attr("src","/web/images/s_t_down.png");
		}
		
	}
	
	$(document).ready(function(){
		$(window).scroll(function()
		{
			if(isBottom(this))
			{
				var p_curPage=parseInt($("#p_curPage").val())+1;
				$("#p_curPage").val(parseInt($("#p_curPage").val())+1);
				var pageSize=$("#pageSize").val();
				$.post(jsCtx+"/leader/leader!appendGoodsList.action",{"rs.p_curPage":p_curPage,"rs.pageSize":pageSize,"random":Math.random()},function(data)
				{
					if(data=="-1")
					{
					  flag = false;
					}
					else
					{
						var content=data;
						$("#goodstr").append(content);
						var s=$("#goodstr").clone().html();
					}
				});
			}
		});
		
		//微信认证初始化	
		$.ajax(
		{
		    url: "/wx/wechat!getSignInfoFromRemote.action?signUrl=/leader/leader!list.action",
			type: "GET",
			cache: false,
			async: false,
			success: function(data)
			{
				eval("var datas = " + data+";");
				wx.config(
				{
				    debug: false, 
				    appId: datas.appId, 
				    timestamp: datas.timestamp, 
				    nonceStr: datas.nonceStr, 
				    signature: datas.signature,
				    jsApiList: datas.jsApiList
				});
		     }
	     });
});

/**
  * imgUrl: 分享图片URL
  * goodsName：分享商品名称
  */
function initWX(imgUrl,goodsName,index)
{
	//显示/隐藏/遮罩层
	$(".mask-ui").show();
	$(".share-img").show();
	window.setTimeout(function()
	{
		$(".mask-ui").hide();
		$(".share-img").hide();
	},2000);
	
	var article = "/zt/article/"+index+".html";
	
	//跳转URL
	var url_ = "http://m.111yao.com/leader/leader!shareLeaderHtml.action?hurl="+encodeURIComponent(article)+"&code=${leader.code?default('')}&id=${leader.id?default(0)}"; 
	var desc = "111医药馆注册即超值豪礼！要健康、要美丽、要时尚@111医药馆";
	wx.ready(function(){
		//分享给朋友
		wx.onMenuShareAppMessage({
		    title: goodsName, 
		    desc: desc,
		    link: url_,
		    imgUrl: imgUrl,
		    type: '',
		    dataUrl: '',
		    success: function ()
		    { 
				$.post("http://m.111yao.com/artic/artic!setShareArticle.action?id="+index,{random:Math.random()}, function(data){});
		    },
		    cancel: function (){ 
		    }
		});
		//分享到朋友圈
		wx.onMenuShareTimeline({
		    title: goodsName,
		    link: url_,
		    imgUrl: imgUrl,
		    success: function () { 
		    	$.post("http://m.111yao.com/artic/artic!setShareArticle.action?id="+index,{random:Math.random()}, function(data){});
		    },
		    cancel: function () { 
		    }
		});
		//分享到QQ
		wx.onMenuShareQQ({
			title: goodsName,
			desc: desc,
			link: url_,
			imgUrl: imgUrl,
		    success: function () { 
		    	$.post("http://m.111yao.com/artic/artic!setShareArticle.action?id="+index,{random:Math.random()}, function(data){});
		    },
		    cancel: function () { 
		    }
		});
	});	
}

</script>
