<!doctype html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp"/>
	<!--允许全屏-->
	<meta content="yes" name="apple-mobile-web-app-capable" />
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="fullscreen=yes,preventMove=no" name="ML-Config" />
	<!--缩放比例-->
	<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta name="format-detection" content="telephone=no" />
	<meta name="format-detection" content="email=no" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
    <title>药房</title>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
</head>

<body>
<#include "/static/inc/wap/header2.ftl" />
<style>
		.option-list 
		{
			display:none;
		    position: absolute;
		    top: 0;
		    height: 95px;
		    z-index: 10;
		    background: #fcaa40;
		    border-radius: 0 0px 0px 0;
		    border-right: 1px solid #f5a339;
		    right:0px;
		}
		.article-part-list 
		{
		    //height: 240px;
		    height: 320px;
		    overflow: auto;
		    width: 250px;
		    border-radius:10px;
		    background: rgba(0,0,0,0);
		    position: relative;
		    padding: 30px 20px 0;
		}
		.popup-shade img {float:left;}
		.articlelist {
			width:235px;
			//max-height:210px;
			max-height:305px;
			overflow-y:auto;
			overflow-x:hidden;
			background:#fff;
			border-radius:10px;
			padding:8px 8px 0;
		}
		.popup-shade {z-index:9999999999999999;}
		.loading-img {display:none;position:fixed;height:auto;z-index:100000;top:45%;left:45%;background:gray;padding:10px;border-radius:10px;opacity:.6;filter:alpha(opacity=60);}
		.loading-img img {widht:30px;height:30px;}
		#imgcloseBtn {width:250px;height:30px; position: absolute;top: 9px; right: 7px; z-index:99;}
		#imgcloseBtn img {float:right; background: #333; border-radius: 50%;}
		.nodatas {width:95%;text-align:center;}
		.lab-list p 
		{
		    padding-left: 10px;
		    line-height: 20px;
		    font-size: 12px;
		    text-align: left;
		    overflow: hidden;
		    word-wrap: break-word;
		    white-space: nowrap;
		    text-overflow: ellipsis;
		    -o-text-overflow: ellipsis;
	    }
	    body{ -webkit-tap-highlight-color:rgba(0,0,0,0); }
	        a:focus,
	        input:focus,p:focus,div:focus{-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-user-modify:read-write-plaintext-only;}
	        
	    .img-details p 
	    {
	    	height:24px;line-height:24px;width:95%;overflow:hidden;
	    	white-space:nowrap; 
			text-overflow:ellipsis; 
			-o-text-overflow:ellipsis; 
			overflow: hidden; 
	    }
	    .lab-list{ position: relative; overflow:hidden;}
	    .hot-triangle{position: absolute; top:0px; left:0px; width:30px; height:33px; background: url("${base}/web/images/left_top_icon01.png") no-repeat; background-size:30px;}
        .hot-triangle-new{background: url("${base}/web/images/left_top_icon02.png") no-repeat; background-size:30px;}
		.wrap-popup{overflow: hidden;}
		.coupon-btn a{ display: block; float:left;  position: relative; overflow: hidden; padding:15px 40px 0;}
		.coupon-btn a b{ display:block; height:65px; width:65px; border-radius:50%;  background-size:29px;}
		.coupon-btn a span{ display: inline-block; width:58px;  color:#FFF; margin-top:10px; font-size:14px;}
		.details-btn{ background:#ff7272 url("${base}/web/images/health-yf01.png") no-repeat center center;}
		.buy-btn{background:#fcaa40 url("${base}/web/images/health-yf02.png") no-repeat center center;}
		.my-pharmacy{margin-top:10px; overflow: hidden;}
		.my-pharmacy a{display: block;width:50%; float:left; border-radius:5px 0 0 5px; text-align: center; font-size:15px;background: #FFF; height:44px; line-height:44px; }
		.my-pharmacy a:last-child{border-radius:0 5px 5px 0; margin-left:.5%; width:49.5%;}
</style>

<script>
$(function(){
	//定位标签
	$("#tabs li:eq(1)").addClass("cur");
});
</script>
<article class="health-sort">
    <div class="notice-box-wrap">
        <span class="notice-line"></span>
        <div class="notice-box" id="marquee6" >
            <ul>
                <#list notices?if_exists as notice>
                <li>${notice.centent}</li>
                </#list>
            </ul>
        </div>
    </div>
    
    <#if is_shop?exists && is_shop == 1>
    <div class="my-pharmacy" id="mPharmay">
        <a onclick="toMyHouse()">进入我的药房</a>
        <a onclick="shareMyHouse()">分享我的药房</a>
    </div>
    </#if>
    
    <ul class="drugstore-list">
    
        <#list pw.result?if_exists as resc>
        <li class="click-btn clearfix" onclick="showArticle('${resc_index}','${resc.goods_id}')">
        	<#if resc.istoday == 1>
            <div class="hot-triangle"></div>
            </#if>
            <div class="img-part"><img src="${ui1}${resc.abbreviation_picture?default('')}" /></div>
            <div class="img-details">
                <p>${resc.short_name?default('')}</p>
                <p>${resc.spec?default('')}</p>
                <p><strong>￥${resc.wap_price?default('')}</strong><b>返</b><span>${(resc.brokerage * resc.wap_price) / 100}壹贝</span></p>
            </div>
            <div class="side-bor">
                <b></b>
                <b></b>
                <b></b>
            </div>
        </li>
        <div class="shade-bg" id="shade_bg_${resc_index}">
            <div class="popup-shade">
            	<div class="wrap-popup">
                    <div class="article-part-list">
                    <div id="imgcloseBtn"><img src="${base}/web/images/error.png" onclick="closeArticle('${resc_index}')"/></div>
                         <!-- 内容 -->
                         <div class="articlelist" id="articlelist_${resc_index}">
                         </div>
                    </div>
                    <div class="coupon-btn">
                        <a onclick="goodsDetail('${resc.goods_id}','${resc.short_name}')"><b class="details-btn"></b><span>商品详情</span></a><a onclick="toBuyNow(${resc.goods_id})"><b class="buy-btn"></b><span>直接购买</span></a>  
                    </div>
                </div> 
            </div>
        </div>
        </#list>
    </ul>
</article>
<#include "/static/inc/wap/footer.ftl"/>
<div class="loading-img" id="loadimg"><img src="/web/images/loading_img.jpg"/></div>
<input type="hidden" value="" id="leaderId_"/>
</body>
<script type="text/javascript" src="${base}/web/js/wap-cart.js"></script>
<script type="text/javascript" src="${base}/web/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
//关闭文章列表
function closeArticle(index)
{
	$("#shade_bg_"+index).hide();
}

//打开文章列表
function showArticle(index, goodsid)
{
	$("#loadimg").show();
	$("#articlelist_"+index).html("");
	$.ajax(
	{
		url: "/leader/leader!searchArticles.action?bkid="+goodsid,
		type: "GET",
		dataType: "json",
		cache: false,
		success: function(data) 
		{
			$("#loadimg").hide();
			var htmlObj = "";
			if(data != '' && data != null)
			{
				$("#shade_bg_"+index).css("right","20px").show();
                $("#articlelist_"+index).html("");
				for(var i=0; i<data.length; i++)
				{
					var articleId = data[i].id;
					var imgUrl = "http://img.zdfei.com/"+data[i].share_image_url;
					var title = data[i].title;
					var share_title = data[i].share_title;
					var createdate = data[i].createdate;
					var todayHtml = "";
					if(getNowFormatDate() == createdate)
					{
						todayHtml = "<div class='hot-triangle hot-triangle-new'></div>";
					}
					htmlObj+="<div class='lab-list' style='height:40px;' onclick='readArticle("+articleId+","+index+")'>"+todayHtml+"<a><img src='"+imgUrl+"'/><p style='line-height:35px;'>"+title+"</p></a></div>";
					if(i == 4)
					{
						break;
					}
				}
			}
			else
			{
				htmlObj += "<div style='margin-bottom:15px;margin-top:5px;'>( ^_^ )该商品没有推荐文章哦！！！</div>";
			}
			$("#articlelist_"+index).append(htmlObj);
			$("#shade_bg_"+index).show();
		},
		error: function()
		{
			$("#loadimg").hide();
		}
	});
}

//阅读文章
function readArticle(articleId,index)
{
	window.location.href = "/static/leader/"+articleId+".html?code=${leader.code}&id=${leader.id}";
}
</script>
</html>
<script>
var pageNo = 2;
var pageSize = 10;
var ui1 = "http://img.zdfei.com/";

$(function()
{
	//获取领袖信息
    getLeaderDetail();
    
    //通知滚动函数
    $('#marquee6').kxbdSuperMarquee({
        isMarquee:true,
        isEqual:false,
        scrollDelay:200,
        controlBtn:{up:'#goUM',down:'#goDM'},
        direction:'up'
    });
    
    
    //初始化微信
	try
	{
		initWechat();
	}catch(e){}
	
	$(".shopping-cart-fixed").hide();
	
	//鼠标滚动条函数
	$(window).scroll(function()
	{
		if(isBottom(this))
		{
			$("#loadimg").show();
			$.ajax(
			{
				url: "/leader/leader!listByPage.action?pageNo="+pageNo+"&pageSize="+pageSize,
				type: "GET",
				dataType: "json",
				cache: false,
				async: false,
				success: function(data) 
				{
					pageNo += 1;
					var liObject = "";
					var index = 0;
					if(data != '' && data != null)
					{
						for(var i=0; i<data.length; i++)
						{
							index = (pageNo-2)*10+i;
							//佣金ID
						    var youjinId = data[i].youjinId;
							//商品图片
						    var img = ui1+data[i].abbreviation_picture;
						    //商品名称
						    var shortname = data[i].short_name;
						    var goodsname = encodeURI(shortname);
						    //商品规格
						    var spec = data[i].spec;
						    //价格
						    var wapprice = data[i].wap_price;
						    //计算贝
						    var brokerage = data[i].brokerage;
						    var money = (brokerage*wapprice)/100;
						    //商品ID
						    var goods_id = data[i].goods_id;
						    //文章数
						    var count = data[i].count;
						    //是否今日发布
						    var istoday = data[i].istoday;
						    var istodayHtml = "";
						    if(istoday == 1)
						    {
						    	istodayHtml = "<div class='hot-triangle'></div>";
						    }
						    liObject +=
							"<li class='click-btn clearfix' onclick=showArticle('"+index+"','"+goods_id+"')>"
								+ istodayHtml
							    +"<input type='hidden' value='1' id='hiddenId_"+index+"'/>"
					            +"<div class='img-part'><img src='"+img+"'/></div>"
					            +"<div class='img-details'>"
					                +"<p>"+shortname+"</p>"
					                +"<p>"+spec+"</p>"
					                +"<p><strong>￥"+wapprice+"</strong><b>返</b><span>"+money+"壹贝</span></p>"
					            +"</div>"
					            +"<div class='side-bor'>"
					                +"<b></b>"
					                +"<b></b>"
					                +"<b></b>"
					            +"</div>"
					        +"</li>"
					            +"<div class='shade-bg' id='shade_bg_"+index+"'>"
					                +"<div class='popup-shade'>"
					                    +"<div class='wrap-popup'>"
						                    +"<div class='article-part-list'>"
						                    +"<div id='imgcloseBtn'><img src='/web/images/error.png' onclick='closeArticle("+index+")'/></div>"
						                         +"<div class='articlelist' id='articlelist_"+index+"'>"
						                         +"</div>"
						                    +"</div>"
						                    +"<div class='coupon-btn'>"
						                    	+"<a onclick=goodsDetail('"+goods_id+"','"+goodsname+"')><b class='details-btn'></b><span>商品详情</span></a><a onclick='toBuyNow("+goods_id+")'><b class='buy-btn'></b><span>直接购买</span></a>"  
						                    +"</div>"
					                    +"</div>"
					                +"</div>"
					            +"</div>";
						}
						$(".drugstore-list").append(liObject);
					}
					else
					{
						flag = false;
						$(".drugstore-list").append("<li class='nodatas' style='height:13px;'>( ^_^ )没有更多数据可以显示了！！！</li>");
					}
					window.setTimeout(function()
					{
						$("#loadimg").hide();
					},500)
				}
			});
		}
	});
});

//获取当前日期
function getNowFormatDate() 
{
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) 
    {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) 
    {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

//商品详情
function goodsDetail(goodsid, goodsname)
{
	var name = "";
	if(goodsname.indexOf("E") > 0)
	{
		name = decodeURI(goodsname);
	}
	else
	{
		name = goodsname;
	}
	window.location.href="/m/"+goodsid+".html";
}

//直接购买
function toBuyNow(goodsid)
{
	shortBug(goodsid);
}

//进入我的药房
function toMyHouse()
{
	var leaderId = $.trim($("#leaderId_").val());
	toShopDomain(leaderId);
}

//分享我的药房
function shareMyHouse()
{
	$(".mask-ui").show();
	$(".share-img").show();
	window.setTimeout(function()
	{
		$(".mask-ui").hide();
		$(".share-img").hide();
	},3000);
}
//微信初始化认证
function initWechat()
{	
	var host = "http://"+window.location.host;
    var surl = host + "/leader/leader!list.action";
	$.ajax({
	    url: "/wx/wechat!getSignInfoFromRemote.action?signUrl="+surl,
		type: "GET",
		cache: false,
		async: false,
		success: function(data){
			eval("var datas = " + data+";");
			wx.config({
			    debug: false, 
			    appId: datas.appId, 
			    timestamp: datas.timestamp, 
			    nonceStr: datas.nonceStr, 
			    signature: datas.signature,
			    jsApiList: datas.jsApiList
			});
	     }
     });
}
//初始化分享参数
function initParam(displayName,leaderId,headurl)
{
	var title = displayName;
	var desc = displayName;
	var url = getShopDomain(leaderId);
	var imgUrl = "http://img.zdfei.com/static/image/temp/20151223/2e493cbc28d11b53e1f9ed0e1f39964a.jpg";
	wx.ready(function()
	{
		//分享给朋友
		wx.onMenuShareAppMessage({
		    title: title, 
		    desc: desc,
		    link: url,
		    imgUrl: imgUrl,
		    type: '',
		    dataUrl: '',
		    success: function (){ 
		    },
		    cancel: function (){ 
		    }
		});
		//分享到朋友圈
		wx.onMenuShareTimeline({
		    title: title,
		    link: url,
		    imgUrl: imgUrl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
		//分享到QQ
		wx.onMenuShareQQ({
			title: title,
			desc: desc,
			link: url,
			imgUrl: imgUrl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
	});	
}

//获取领袖信息
function getLeaderDetail()
{
	$.ajax(
    {
        url: "/leader/leader!getinfo.action",
        type: 'POST',
        cache: false,
        success: function(data)
        {
        	if(data != null && data != '' && data != undefined)
        	{
        		var leaderId = data.id;
	        	var headurl = data.head_url;
	        	if(headurl == null || headurl == '' || headurl == undefined)
	        	{
	        		headurl = "${ui1}/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png";
	        	}
	        	else
	        	{
	        		headurl = '${ui1}' + headurl;
	        	}
	        	var displayName = "我的专属药房";
	        	var nickname = data.nick_name;
	        	var realname = data.real_name;
	        	if(nickname != null && nickname != '' && nickname != undefined)
	        	{
	        		displayName = nickname + "_的专属药房";
	        	}
	        	else
	        	{
	        		if(realname != null && realname != '' && realname != undefined)
	        		{
	        			displayName = realname + "_的专属药房";
	        		}
	        	}
	        	var count = data.count;
	        	$("#leaderId_").val(leaderId);
	        	initParam(displayName,leaderId,headurl);
        	}
        }
    }); 
}
</script>
<script type="text/javascript" src="${base}/web/js/Marquee.js"></script>
<script type="text/javascript" src="${base}/web/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${base}/web/js/shopDomain.js"></script>
<div class="mask-ui"></div>
<img class="share-img" src="${base}/web/images/share.png"/>
<style>
.mask-ui {position:fixed;z-index:99;background: rgba(22,22,22,.9);top:0;left:0;width:100%;height:100%;display:none;}
.share-img{position:fixed;z-index:999;top:0;left:0;width:100%;display:none;}
.health-nav li a {-webkit-user-select:none; -moz-user-select:none; -ms-user-select:none; user-select:none;}
</style>
