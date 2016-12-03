//微信初始化认证
function initWxConfigCommon(url)
{	
	var host = "http://"+window.location.host;
    var surl = host+url;
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
			
			window.setTimeout(function(){
				//默认分享药房信息
				var $title = document.title;
				var $leaderCode = $.trim($("#leaderCode").val());
				var $url = host + "/leader/leaderPharmacy!leaderSharePharmacy.action?leaderCode="+$leaderCode;
				var $imgUrl = "http://img.zdfei.com/static/image/temp/20151223/2e493cbc28d11b53e1f9ed0e1f39964a.jpg";
				initMenuShareParam("111医药馆", "111医药馆专做药品特卖的网站！", host, "http://img.zdfei.com/static/image/temp/20160714/5fee911acdbd70e8c0c4cf6ec4e88144.png");
			}, 100);
	     }
     });
}
//微信初始化认证
function initWxConfig()
{	
	var host = "http://"+window.location.host;
    var surl = host + "/leader/leaderPharmacy!leaderPharmacy.action";
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


/*
 * ajax方式获取药品相关分享信息
 * $goodsId:商品id
 * $code:领袖code
 */
function getGoodsShareUrlAjax($goodId,$leaderCode){
	$.ajax(
    {
        url: "/leader/leaderCenter!getGoodAndLeaderDetail.action",
        data: {"goodId":$goodId},
        type: 'POST',
        cache: false,
        success: function(data)
        {
        	if(data != null && data != '' && data != undefined)
        	{
        		var title = data.good.shortName;
        		var imgUrl = "http://img.zdfei.com/" + data.good.abbreviationPicture;
        		var desc = data.good.mainTitle;
        		if(desc == null || $.trim(desc) == '' || desc == undefined){
        			desc = data.good.subTitle;
        			if(desc == null || $.trim(desc) == '' || desc == undefined){
        				desc = title;
        			}
        		}
        		
        		var goodsurl = "/m/"+data.good.id+".html";
        		var yu = "http://"+window.location.host;
				var url = yu+"/leader/leader!shareLeader.action?hurl="+encodeURIComponent(goodsurl)+"&code="+$leaderCode;
	        	initMenuShareParam(title,desc,url,imgUrl);
        	}
        }
    });

}


/**
 * 页面js传值方式设置药品分享信息
 * $goodsId : 商品id
 * $code : 领袖code
 */
function getGoodsShareUrlJs($goodId,short_name,main_title,sub_title,abbreviation_picture,$leaderCode)
{
	var title = short_name;
	var imgUrl = "http://img.zdfei.com/" + abbreviation_picture;
	var desc = main_title;
	if(desc == null || $.trim(desc) == '' || desc == undefined)
	{
		desc = sub_title;
		if(desc == null || $.trim(desc) == '' || desc == undefined)
		{
			desc = title;
		}
	}
	var goodsurl = "/m/"+$goodId+".html";
	var yu = "http://"+window.location.host;
	var url = yu+"/leader/leader!shareLeader.action?hurl="+encodeURIComponent(goodsurl)+"&code="+$leaderCode;
	initMenuShareParam(title, desc, url, imgUrl);
}





//初始化分享参数
function initMenuShareParam(_title,_desc,url,imgUrl)
{
	var title = _title;
	var desc = _desc;
	var obj = window;
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
		    	obj.top.location.reload(true);
		    },
		    cancel: function (){ 
		    	obj.top.location.reload(true);
		    }
		});
		//分享到朋友圈
		wx.onMenuShareTimeline({
		    title: title,
		    link: url,
		    imgUrl: imgUrl,
		    success: function () { 
		    	obj.top.location.reload(true);
		    },
		    cancel: function () { 
		    	obj.top.location.reload(true);
		    }
		});
		//分享到QQ
		wx.onMenuShareQQ({
			title: title,
			desc: desc,
			link: url,
			imgUrl: imgUrl,
		    success: function () { 
		    	obj.top.location.reload(true);
		    },
		    cancel: function () { 
		    	obj.top.location.reload(true);
		    }
		});
	});	
}



//微信初始化认证
function initWxConfigForArticle()
{	
	var host = "http://"+window.location.host;
    var surl = host + "/leader/leaderArticle!leaderArticle.action";
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

//微信初始化认证
function initWxConfigForRecommendArticle()
{	
	var host = "http://"+window.location.host;
    var surl = host + "/leader/leaderArticle!recommendArticle.action";
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

/**
 * 页面js传值方式设置文章分享信息
 * title
 * desc
 */
function getArticleShareUrlJs(title,desc,imgUrl,articleId,leaderId,leaderCode){
	var imgUrlTemp = "http://img.zdfei.com/" + imgUrl;
	var url = "http://" + window.location.host + "/static/leader/user/" + articleId + ".html?code="+ leaderCode+"&id="+leaderId;
	initArticleMenuShareParam(title,desc,url,imgUrlTemp,articleId);
}

/**
 * 初始化文章分享参数
 * title:title
 * desc:share_tile
 * url:m.111yao.com/static/leader/246.html?code=201511171159505950813400&id=290   //领袖code 领袖id
 * var baseurl = "http://img.zdfei.com";
 * imgUrl:share_img     baseurl+'/static/image/leader/20160602/5fe6d725381e669bf948f5e5bd020ed1.jpg';
 */
function initArticleMenuShareParam(_title,_desc,url,imgUrl,articleId)
{
	var title = _title;
	var desc = _desc;
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
		    	$.post("/artic/artic!clickLeaderArticleShareSum.action?articleId="+articleId,{random:Math.random()}, function(data){});
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
		    	$.post("/artic/artic!clickLeaderArticleShareSum.action?articleId="+articleId,{random:Math.random()}, function(data){});
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
		    	$.post("/artic/artic!clickLeaderArticleShareSum.action?articleId="+articleId,{random:Math.random()}, function(data){});
		    },
		    cancel: function () { 
		    }
		});
	});	
}



/**
 * 页面js传值方式设置推荐文章分享信息
 * title
 * desc
 */
function getGoodsArticleShareUrlJs(title,desc,imgUrl,articleId,leaderId,leaderCode){
	var imgUrlTemp = "http://img.zdfei.com/" + imgUrl;
	var url = "http://" + window.location.host + "/static/leader/" + articleId + ".html?code="+ leaderCode+"&id="+leaderId;
	initGoodsArticleMenuShareParam(title,desc,url,imgUrlTemp,articleId);
}

/**
 * 初始化推荐文章分享参数
 * title:title
 * desc:share_tile
 * url:m.111yao.com/static/leader/246.html?code=201511171159505950813400&id=290   //领袖code 领袖id
 * var baseurl = "http://img.zdfei.com";
 * imgUrl:share_img     baseurl+'/static/image/leader/20160602/5fe6d725381e669bf948f5e5bd020ed1.jpg';
 */
function initGoodsArticleMenuShareParam(_title,_desc,url,imgUrl,articleId)
{
	var title = _title;
	var desc = _desc;
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
		    	$.post("/artic/artic!setShareArticle.action?id="+articleId,{random:Math.random()}, function(data){});
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
		    	$.post("/artic/artic!setShareArticle.action?id="+articleId,{random:Math.random()}, function(data){});
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
		    	$.post("/artic/artic!setShareArticle.action?id="+articleId,{random:Math.random()}, function(data){});
		    },
		    cancel: function () { 
		    }
		});
	});	
}
