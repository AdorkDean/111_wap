<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="renderer" content="webkit" />
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="yes" name="apple-touch-fullscreen" />
    <meta content="fullscreen=yes,preventMove=no" name="ML-Config" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="format-detection" content="email=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <title>${tla.title?default('')}</title>
        <style>
    html,body,div,span,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,address,cite,code,del,dfn,em,img,ins,kbd,q,samp,small,strong,sub,sup,var,b,i,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,textarea,input,span,figure,aside,time,figcaption{margin:0;padding:0;border:0;font-size:100%;vertical-align:baseline}
	article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block}
	audio,canvas,video{display:inline-block;*display:inline;*zoom:1}
	audio:not([controls]){display:none}
	html,body{height:100%; position:relative}
	html{font-size:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}
	body{width:100%;font-family:"SimHei",Helvetica,Arial,sans-serif;font-size:14px;color:#000;background:#fff; line-height:1.6em;}
	input,textarea{font-family:"SimHei",Helvetica,Arial,sans-serif;color:#000;background: none;}
	input:-moz-placeholder,textarea:-moz-placeholder{color:#c7c7cd}
	input:-ms-input-placeholder,textarea:-ms-input-placeholder{color:#c7c7cd}
	input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{color:#c7c7cd}
	a{color:#0d0d0d;text-decoration:none;/*-webkit-tap-highlight-color:rgba(0,0,0,0.2);*/-webkit-tap-highlight-color:transparent}
	a:hover{text-decoration:none}
	a:focus{outline:none;blr:expression(this.onFocus=this.blur());}
	a:hover,a:active{outline:0}
	textarea,input{resize:none;outline:0;font-size:100%;-webkit-tap-highlight-color:rgba(255,0,0,0)}
	textarea{resize:none;-webkit-appearance:none}
	ul,ol{list-style:none}
	em{font-style:normal}
	h1,h2,h3,h4,h5,h6,b{font-weight:normal;}
	.clearfix {margin-bottom:10px;position:relative}
	.clearfix:after{visibility:hidden;display:block;font-size:0;content:".";clear:both;height:0;overflow:hidden}
	.fl{float:left}
	.fr{float:right}
	.health-article-share{font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif;}
	.article-share{padding:10px; overflow: hidden; background: #f7f7f7;}
	.share-title{padding:0 23px 0 11px;font-size:18px; font-weight:bold; color:#000; margin-bottom:6px;}
	.subhead{font-size:12px; color:#808080; margin-bottom:21px;padding:0 23px 0 11px;}
	.subhead a{font-size:12px; color:#00a6a0;}
	.main-text{overflow: hidden;}
	.main-text p{font-size:15px; line-height:25px; margin-bottom:20px; padding:0 23px 0 11px;}
	.main-img{padding:0 11px; margin-bottom:20px;}
	.main-img img{width:100%;}
	.recommend-shopping{overflow: hidden;}
	.rcm-header{background: #EEE; height:32px; position: relative;}
	.rcm-title{width:100%; text-align: center; font-size:15px; color:#00a6a0; line-height:32px; font-weight:bold;}
	.rcm-line{display:block; border-top:1px solid #b9b9b9; height:0; position: absolute; top:16px; width:38%;}
	.b-left{left:0;}
	.b-right{right:0;}
	.rcm-list{overflow: hidden;}
	.rcm-list dl{margin-bottom:4px; overflow: hidden;}
	.rcm-img{width:125px; height:125px; display: block; float:left;}
	.rcm-img a,.rcm-img a img{width:125px; height:125px; display: block;}
	.rcm-text{margin-left:138px;border-top:1px solid #e4e4e4;}
	.rcm-list dl:first-child .rcm-text{border-top:none;}
	.shopping-name{font-size:13px; color:#000; font-weight: bold; line-height:18px;height:20px; overflow: hidden;margin-top:5px;}
	.sales-promotion{color:#e60116;font-size:13px; margin-bottom:15px;line-height:18px;height:40px;}
	.shopping-norms{font-size:12px; color:#808080;}
	.shopping-price span{float:left; font-size:13px;color:#e60116;}
	.shopping-price span i{font-style: normal;font-size:18px; }
	.shopping-price a{float:right; display:block; height:20px; line-height:20px; border-radius: 4px; padding:0 6px; margin-right:5px; text-align: center; color:#FFF; font-size:13px; background: #e60116;}
	.health-more{border-top:1px solid #e4e4e4; border-bottom:1px solid #e4e4e4; padding:0 15px 0 12px;}
	.health-more a{display:block;width:100%; height:54px;line-height:54px;}
	.health-more a span{float:left; padding-left:32px; font-size:16px; color:#000; background: url("images/health_article_icon.png") no-repeat center left; background-size: 23px;}
	.health-more a p{float:right; font-size:15px; color:#808080; line-height:54px; background: url("images/health_arrow_right.png") no-repeat center right; background-size: 8px; padding-right:18px;}
	.wx-img{padding:20px 0 35px;}
	.wx-top{overflow:hidden;}
	.wx-top li{width:50%; float:left; list-style:none;}
	.wx-top li img{width:80%; display:block; margin:0 auto;}
	.wx-img p{text-align: center; font-size:13px; margin-top:13px;}
	.price-ui {position: absolute;top: 52%;width: 22%;right: 2%;height: auto;padding-top: 15px;padding-bottom: 5px;text-align: center;font-size: 6vw;color: #d10000;background: #fcffef;font-family: 'Arial';font-weight: bold;letter-spacing: -2px;}
	.get-box{height:45px; padding-left:5px; background: #FFF; position: relative;}
	.get-box label{display: block; height:45px; line-height:45px; background: url("http://img.zdfei.com/static/image/temp/20151229/cdf243262446233c557a91daddac4c5d.png") no-repeat left center; background-size: 18px;  padding-left:25px; font-size:12px;color:#999999;}
	.getClick{display:block; position: absolute; top:7px; right:6px; height:30px; width:75px; line-height:30px; text-align: center; font-size:14px; border:1px solid #e74925; color:#e74925; border-radius:5px;font-size:12px;}
	.read-wrap{height:30px; padding-left:20px; border-top:1px solid #d7d7d7;background:#f8f8f8;padding-top:5px;}
	.read-wrap li{float:left; line-height:30px; margin-right:30px; color:#00b8c9;}
	.read-wrap li span{float:left;}
	.read-wrap li b{display:inline-block; float:left; margin-top:3px;height:20px; width:20px; background:url("http://img.zdfei.com/static/image/temp/20160107/fb0bd9f2efc969aed2fd601882219228.png") no-repeat center center; background-size: 16px;}
	.read-wrap li b.click-b{background:url("http://img.zdfei.com/static/image/temp/20160107/fff1569bb7bc35104e7f9c75020c931c.png") no-repeat center center; background-size: 16px;}
	.readnum {margin-left:3px;}
	.open_red_bk {background:#f8f8f8;}
	.open_red_line {width:100%;height:35px;background:#fff;line-height:35px;font-size:15px;font-family:"Microsoft YaHei" ! important;font-weight:bold;}
	.open_red_line span.mid {width:50%;height:35px;display: block;float:left;text-align: center;}
	.open_red_line span.line1 {width:20%;height:20px;border-bottom:#dcdcdc 1px solid;display: block;float:left;margin-left:5%;}
	.open_red_line span.line2 {width:20%;height:20px;border-bottom:#dcdcdc 1px solid;display: block;float:right;margin-right:5%;}
	._open_red_line {width:100%;height:35px;background:#fff;line-height:35px;font-size:15px;font-family:"Microsoft YaHei" ! important;font-weight:bold;}
	._open_red_line span.mid {width:60%;height:35px;display: block;float:left;text-align: center;}
	._open_red_line span.line1 {width:15%;height:20px;border-bottom:#dcdcdc 1px solid;display: block;float:left;margin-left:5%;}
	._open_red_line span.line2 {width:15%;height:20px;border-bottom:#dcdcdc 1px solid;display: block;float:right;margin-right:5%;}
    </style>
    <#assign ui1="http://img.zdfei.com">
    <#assign wap="http://m.111yao.com">
    <link href="${wap}/web/css/new-health.css" rel="stylesheet" type="text/css" />
</head>
<body class="health-article-share">

<!-- 文章内容 -->
<article class="article-share" style="background:#fff;">
	${acontent?default('')}
</article>
<input type="hidden" value="${tla.shareTitle?default('')}" id="shareTitle"/>

<div class="read-wrap">
	<ul>
		<li><span>阅读</span><span class="readnum">1000</span></li>
		<li><b onclick="zanThis(this)" id="dianZan"></b><span class="showZan">100</span></li>
	</ul>
</div>

<!-- 开红包图片 -->
<p class="open_red_bk"><img src="http://img.zdfei.com/static/image/temp/20160803/b29c5e660dc2df80eadfcf437e8bd8fd.jpg" style="width:100%;" onclick="chaiRed()" /></p>
<div class="open_red_line">
	<span class="line1"></span>
	<span class="mid">111医药馆 / 为您推荐</span>
	<span class="line2"></span>
</div>

<!-- 商品推荐 -->
<article class="recommend-shopping">
    <div class="rcm-list" id="tuijianContent">
    	<#list datas?if_exists as resc>
        <div class="clearfix" id="goods_${resc_index+1}">
            <img src="${ui1}${resc.imgurl}" id="img_h_${resc_index+1}" style="width:100%;"/>
        </div>
        <input type="hidden" value="${resc.goodsid?c}" id="goodsId_${resc_index+1}"/>
        </#list>
    </div>
</article>

<!--药师到家-->
<div class="_open_red_line">
	<span class="line1"></span>
	<span class="mid">客服热线 / 400-606-3111</span>
	<span class="line2"></span>
</div>
<p style="padding-top:10px;background:#FFFFFF;"><img src="http://img.zdfei.com/static/image/temp/20160803/578bb3d90776f956d4c9cc3bc733ac5d.jpg" style="width:100%;"></p>

<!--微信开始-->
<div class="wx-img">
    <ul class="wx-top clearfix">
		<li><img src="/web/images/300.jpg" alt="" id="wx-img"/></li>
        <li><img src="http://img.zdfei.com/static/image/temp/20151119/2497b1cf6f525885c8fde3adbcc5b531.jpg" alt=""/></li>
    </ul>
    <p>更多健康知识，更多用药常识，长按进行关注</p>
</div>

<div class="shade-bg">
    <div class="popup-shade">
        <div class="no-coupon">
            <p>今天优惠券领完了~</p>
            <p><a href="/">去首页逛逛>></a></p>
        </div>
    </div>
</div>

<script type="text/javascript" src="/web/js/??jquery.min.js,jweixin-1.0.0.js,alert.main.js,cookieUtil.js"></script>


<script type="text/javascript">
var baseurl = "http://img.zdfei.com";
$(function()
{
	//监听文章内容商品点击事件
	try
	{
		goodsOnClickEvent("goods_img_p_1");
		goodsOnClickEvent("goods_img_p_2");
		goodsOnClickEvent("goods_img_p_3");
		goodsOnClickEvent("goods_img_p_4");
		goodsOnClickEvent("goods_img_p_5");
	}catch(e){console.log(e)}
	
});

//文章内容商品点击事件
function goodsOnClickEvent(id)
{
	var istrue = false;
	var verify = document.getElementById(id);
	if(verify != null)
	{
		var $a = $("#"+id).parent().find("a");
		var $v;
		var $goodsId;
		try
		{
			$v = $a.attr("href").split("//");
			$goodsId = $v[1];
			$a.attr("href","javascript:void(0)");
		}
		catch(e)
		{
			istrue = true;
			console.log(e);
		}
		verify.onclick = function()
		{
			if(istrue)
			{
				$alert("warn","请为该商品添加超链接！");
				return false;
			}
			else
		    {
			  	var goodsurl = "/m/"+$goodsId+".html";
			  	var redirect = "/leader/leader!shareLeader.action?hurl="+encodeURIComponent(goodsurl)+"&code="+code;
			    window.location.href = redirect;
		    }
		};
	}
}

$(function()
{
	//系统文章统计文章点击数
	try{countClickNum('${tla.id?default(0)}');}catch(e){console.log(e)}
	//微信认证初始化	
	try{initWechat();}catch(e){console.log(e)}
	//商品点击事件
	try{goodsClickEvents();}catch(e){console.log(e)}	
});

//生成二维码图片
var code = getParameters().code;
var id = getParameters().id;
var imgurl = "http://img.zdfei.com/static/image/haibao/"+id+"_"+code+".jpg";
if(id == undefined || code == undefined)
{
	imgurl = "http://img.zdfei.com/static/image/temp/20160803/3056c9949053e8c3d2a177b53ede7174.jpg";
}
document.getElementById("wx-img").src = imgurl;
  
//商品点击事件
function goodsClickEvents()
{
  	$("#tuijianContent").find("div.clearfix").each(function(index)
  	{
  		$(this).click(function()
  		{
  			var i = index + 1;
			var goodsId = document.getElementById("goodsId_"+i).value;
		  	var goodsurl = "/m/"+goodsId+".html";
		  	var redirect = "/leader/leader!shareLeader.action?hurl="+encodeURIComponent(goodsurl)+"&code="+code;
		    $.post("/artic/artic!setOpenArticleGoods.action?id="+goodsId+"&aid=${tla.id}",{random:Math.random()},function(data)
			{
				window.location.href = redirect;
			});
  		});
  	});
}

//返回首页  
function toHome()
{
	//var redirect = "/leader/leader!shareLeader.action?hurl="+encodeURIComponent("/")+"&code="+code;
	var redirect = "/web/redpacket/my_red_packet.html?lid="+id+"&lcode="+code;
	window.location.href = redirect;
}
  
//点赞和阅读数获取
try
{
	$.post("/artic/artic!getReadArticle.action?id=${tla.id}",{random:Math.random()}, function(data)
	{
		if(data != '' && data != null && data != undefined && data != "-1")
		{
			var result = data.split("_");
			$(".readnum").html(result[0]);
			$(".showZan").html(result[1]);
		}
		else
		{
			$(".readnum").html("1000+");
			$(".showZan").html("1000+");
		}
	});
	var rnum = parseInt($.trim($(".readnum").html()));
	if(rnum == 0)
	{
		$(".readnum").html("1");
	}
}
catch(e){console.log(e);}

//每天只能点赞一次
var aid = "${tla.id}";
var zanTime = getCookie("zanTime");
var articleId = getCookie("articleId");
if(zanTime != '' && zanTime == getCurrDate() && (aid == articleId))
{
	$("#dianZan").addClass("click-b");
}
//点赞事件
function zanThis(obj)
{
	var $aid = "${tla.id}";
	var $zanTime = getCookie("zanTime");
	var $articleId = getCookie("articleId");
	if($zanTime == '' || $zanTime == null || $zanTime == undefined || $zanTime != getCurrDate() || ($articleId == '') || $articleId == null || $articleId == undefined || ($articleId != $aid))
	{
		setCookie("zanTime", getCurrDate());
		setCookie("articleId", "${tla.id}");
		$.post("/artic/artic!clickLikeSum.action?id=${tla.id}",{random:Math.random()}, function(data)
		{
			$(obj).addClass("click-b");
			var sum = parseInt($.trim($(".showZan").html()));
			$(".showZan").html(sum+1);
		});
	}
	else
	{
		$alert("warn","您今天已经点赞过啦！");
	}
	
}
//获取系统当前时间
function getCurrDate()
{
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	return year+"-"+month+"-"+day;
}


//系统文章统计文章点击数
function countClickNum(articleId)
{
	$.ajax
	({
		type: "POST",
		url: "/artic/artic!setOpenArticle.action",
		async: false,
		data:{"id":articleId},
		dataType: "json",
		success: function(data){},  
	    error: function(e){console.log(e);}  
	 });
}

//系统文章统计分享数
function countShareNum(articleId)
{
	$.ajax
	({
		type: "POST",
		url: "/artic/artic!setShareArticle.action",
		async: false,
		data:{"id":articleId},
		dataType: "json",
		success: function(data){},  
	    error: function(e){console.log(e);}  
	 });
}

//微信认证初始化	
function initWechat()
{
	var url = document.location.href;
	$.ajax(
	{
	    url: "/wx/wechat!getSignInfoFromRemote.action?signUrl="+encodeURIComponent(url),
		type: "GET",
		cache: false,
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
			initWX();
	     }
     });
}

//初始化微信 
function initWX()
{
	var title = document.title;
	var desc = $.trim($("#shareTitle").val());
	var aimUrl = document.location.href;
	var imgUrl = baseurl+'${tla.shareImageUrl?default('')}';
	wx.ready(function()
	{
		//分享给朋友
		wx.onMenuShareAppMessage(
		{
		    title: title, 
		    desc: desc,
		    link: aimUrl,
		    imgUrl: imgUrl,
		    type: '',
		    dataUrl: '',
		    success:function()
		    { 
		    	try{countShareNum(${tla.id?default(0)});}catch(e){console.log(e)}
		    },
		    cancel:function(){}
		});
		//分享到朋友圈
		wx.onMenuShareTimeline(
		{
		    title: title,
		    link: aimUrl,
		    imgUrl: imgUrl,
		    success:function() 
		    { 
		    	try{countShareNum(${tla.id?default(0)});}catch(e){console.log(e)}
		    },
		    cancel:function(){}
		});
		//分享到QQ
		wx.onMenuShareQQ(
		{
			title: title,
			desc: desc,
			link: aimUrl,
			imgUrl: imgUrl,
		    success:function()
		    { 
		    	try{countShareNum(${tla.id?default(0)});}catch(e){console.log(e)}
		    },
		    cancel:function(){}
		});
	});	
}

//拆红包
function chaiRed()
{
	var code = 1;
	var leaderid = id;
	var lcode = code;
	if(id == undefined)
	{
		$alert("warn","分享出去的文章才能拆此红包！");
		return false;
	}
	jQuery.ajax
	({
	    type: "post",
	    url: "/healthLeader/lxredpack!isHaved.action",	 
	    data:{'leaderid':leaderid},
	    async:false, 
	    success: function(data)
	    {
	       if(data>=10){
	       	  $(".shade-bg").show();
	       }else{
	       	  $(".shade-bg").hide();
	       	  var red_coupon = createRed();
	       	  var arry = red_coupon.split("_");
	       	  window.location.href="/web/redpacket/chai_red_packet.html?lid="+leaderid+"&lc="+lcode+"&rid="+arry[0]+"&cid="+arry[1]+"&info="+arry[2];
	       }
	    }
	});    
}

//创建红包
function createRed()
{
	var red_coupon = "";
	var leaderid = id;
	jQuery.ajax
	({
	    type: "post",
	    url: "/healthLeader/lxredpack!sendRedPacket.action",	 
	    data:{'leaderid':leaderid},
	    async:false, 
	    success: function(data)
	    {
	       red_coupon = data;
	    }
	}); 
	return red_coupon;
}

//解析地址栏参数
function getParameters()
{
	var pars = window.location.search;
    if(pars.charAt(0) == '?')
    {
        pars = pars.substring(1,pars.length);
    }
    var arr = pars.split("&");
    var objs = [];
    var eles;
    for(var i=0;i<arr.length;i++)
    {
        eles = arr[i].split("=");
        objs[eles[0]] = eles[1];
    }
    return objs;
}
</script>
</body>
</html>


