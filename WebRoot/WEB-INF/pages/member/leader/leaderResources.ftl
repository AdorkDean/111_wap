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
<title>健康领秀-素材</title>
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${base}/web/css/idangerous.swiper.css">
<style>
	.loading-img {display:none;position:fixed;height:auto;z-index:100000;top:45%;left:45%;background:gray;padding:10px;border-radius:10px;opacity:.6;filter:alpha(opacity=60);}
	.loading-img img {widht:30px;height:30px;}
</style>
</head>

<body>
<#assign title="111">
<#include "${base}/static/inc/wap/header.ftl">
<nav class="health-nav">
	<ul class="clearfix clear">
    	<li><a href="#"><span><b class="health-center"></b>中心</span></a></li>
        <li><a href="#"><span><b class="health-pharmacy"></b>药房</span></a></li>
        <li><a href="#"><span><b class="health-ranking"></b>排行</span></a></li>
        <li class="cur"><a href="#"><span><b class="health-tools"></b>工具</span></a></li>
    </ul>
</nav>
<input type="hidden" id="tabv" value="1"/>
<article class="health-center-material">
	<p class="share-tips">选择自己喜欢的图片、文字保存到手机分享给朋友</p>
    <div class="material-tag">
    	<div class="wrap">
            <div class="tabs">
                <a href="#" hidefocus="true" class="active" tabv="1">图片</a>
                <a href="#" hidefocus="true" tabv="2">文字</a>
            </div>    
            <div class="swiper-container" style="height:100%;">
                <div class="swiper-wrapper" style="height:100%;">
                    <div class="swiper-slide" style="height:100%;">
                        <div class="content-slide">
                        	<ul class="material-picture clearfix clear" id="imgids">
                            </ul>
                        </div>
                    </div>
                    <div class="swiper-slide" style="height:100%;">
                        <div class="content-slide">
                        	<ul class="material-text clearfix clear" id="conids">
                            </ul>
                        </div>
                    </div>
                </div>
                    <div class="loading-img" id="loadimg"><img src="${base}/web/images/loading_img.jpg"/></div>
            </div>
        </div>
    </div>
</article>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script> 
<script type="text/javascript" src="${base}/web/js/idangerous.swiper.min.js"></script> 
<script type="text/javascript">
	var flag1 = true;
	var flag2 = true;
	var pageNo2 = 1;
    var pageNo1 = 1;
    var pageSize = 10;
    if(pageNo2 == 1||pageNo1 == 1){
    	getdata(1);
    	getdata(2);
    }
    function getdata(tabs){
    var pageNo = '';
    if('1'==tabs){
		pageNo = pageNo1;
	}else{
		pageNo = pageNo2;
	}
	    $.ajax({
				url: "${base}/leader/leader!leaderResourcesList.action?pageNo="+pageNo+"&pageSize="+pageSize+"&tabs="+tabs,
				type: "GET",
				dataType: "json",
				cache: false,
				async: false,
				success: function(data){
					if('1'==tabs){
						pageNo1 += 1;
					}else{
						pageNo2 += 1;
					}
					var liObject = "";
					var datas = eval(data.list);
					if(datas != '' && datas != null){
						for(var i=0;i<datas.length;i++){
							if('1'==tabs){
									liObject += 
									"<li>"+
				                    	"<a href=''><img src='"+imgUrl+datas[i].imgurl+"'></a>"+
				                    "</li>";
							}else{
								liObject += 
									"<li>"+
		                            	"<p class='margin-text-detals'>"+datas[i].context+"</p>"+
		                                "<a href='#'><span><i class='copy_ico'></i><br>复<br>制</span></a>"+
	                           		"</li>";
							}
						}
						}else{
							liObject = "<li>暂时没有数据可以显示！</li>";
						}
						if('1'==tabs){
							$("#imgids").append(liObject);
						}else{
							$("#conids").append(liObject);
						}
				}
			});
    }
		$(function(){
			$(window).scroll(function(){
				var flag ="";
				var tabs = $("#tabv").val();
				if('1'==tabs){
					flag=flag1;
				}else{
					flag=flag2;
				}
				if(isBottom(this) && flag){
				$("#loadimg").show();
				var pageNo = '';
				if('1'==tabs){
					pageNo = pageNo1;
				}else{
					pageNo = pageNo2;
				}		
				$.ajax({
					url: "${base}/leader/leader!leaderResourcesList.action?pageNo="+pageNo+"&pageSize="+pageSize+"&tabs="+tabs,
					type: "GET",
					dataType: "json",
					cache: false,
					async: false,
					success: function(data){
						if('1'==tabs){
							pageNo1 += 1;
						}else{
							pageNo2 += 1;
						}
						var liObject = "";
						var datas = eval(data.list);
						if(datas != '' && datas != null){
							for(var i=0;i<datas.length;i++){
								if('1'==tabs){
									liObject += 
									"<li>"+
				                    	"<img src='"+imgUrl+datas[i].imgurl+"'>"+
				                    "</li>";
								}else{
								liObject += 
									"<li>"+
                                	"<p class='margin-text-detals'>"+datas[i].context+"</p>"+
                                    "<a href='#'><span><i class='copy_ico'></i><br>复<br>制</span></a>"+
                               		"</li>";
								}
							}
							}else{
								alert(tabs+"------tabs")
								alert(flag1+"-----flag1");
								alert(flag2+"-----flag2");
								if('1'==tabs){
									flag1 = false;
									liObject = "<li>暂时没有数据可以显示！</li><li>暂时没有数据可以显示！</li>";
								}else{
									flag2 = false;
									liObject = "<li><p class='margin-text-detals'>暂时没有数据可以显示！</p></li>";
								}
							}
							if('1'==tabs){
								$("#imgids").append(liObject);
							}else{
								$("#conids").append(liObject);
							}
						window.setTimeout(function(){
							$("#loadimg").hide();
						},500)
					}
				});
			}
		});
	});
var tabsSwiper = new Swiper('.swiper-container',{
	speed:500,
	onSlideChangeStart: function(){
		$(".tabs .active").removeClass('active');
		$(".tabs a").eq(tabsSwiper.activeIndex).addClass('active');
		$("#tabv").val($(".tabs a").eq(tabsSwiper.activeIndex).attr("tabv"));
	}
});

$(".tabs a").on('touchstart mousedown',function(e){
	e.preventDefault()
	$(".tabs .active").removeClass('active');
	$(this).addClass('active');
	tabsSwiper.swipeTo($(this).index());
	$("#tabv").val($(this).attr("tabv"));
});

$(".tabs a").click(function(e){
	e.preventDefault();
	$("#tabv").val($(this).attr("tabv"));
});
</script>
<#include "/static/inc/wap/footer.ftl"/>
</body>
</html>