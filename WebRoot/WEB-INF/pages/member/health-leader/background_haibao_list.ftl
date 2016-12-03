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
<title>健康领秀-海报</title>
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/swiper.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<#include "/static/inc/wap/header2.ftl" />
<style>
.swiper-container{width: 100%; padding-top: 15px;}
.swiper-slide{background-position: center;  background-size: cover; width: 170px; height: 260px;}
</style>
<script>
$(function(){
	//定位标签
	$("#tabs li:eq(3)").addClass("cur");
});
</script>
</head>

<body>
<article class="health-center-poster">
	<!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper">
        <input type="hidden" name="leaderId" id="leaderId" value="${leader.id?if_exists}"/>
        	<#if list?exists && list?size lt 1>
        		暂无海报背景图片
        		<input type="hidden" name="hasNo" id="hasNo" value="1"/>
        	<#else>
	        	<#list list?if_exists as obj>
		        	<#if obj?exists && obj.imgurl?exists>
			        	<div class="swiper-slide" style="background-image:url(${base}${obj.imgurl?if_exists})">
			        		<input type="hidden" class="haibaoId" value="${obj.id?if_exists}"/>
			        	</div>
		        	</#if>
	            <!--<div class="swiper-slide" style=" background-image:url(images/comment-img.jpg)"></div>
	            <div class="swiper-slide" style="background-image:url(images/applyaftersale.png)"></div>
	            <div class="swiper-slide" style=" background-image:url(images/comment-img.jpg)"></div>
	            <div class="swiper-slide" style="background-image:url(images/applyaftersale.png)"></div>
	            <div class="swiper-slide" style=" background-image:url(images/comment-img.jpg)"></div>
	            <div class="swiper-slide" style="background-image:url(images/applyaftersale.png)"></div>
	            <div class="swiper-slide" style=" background-image:url(images/comment-img.jpg)"></div>
	            <div class="swiper-slide" style="background-image:url(images/applyaftersale.png)"></div>-->
	            </#list>
        	</#if>
            
        </div>
    </div>
    <!-- Swiper JS -->
    <script src="${base}/web/js/swiper.min.js"></script>
    
    <div class="mlr15"><a id="createBtn" href="javascript:void(0)" onclick="createHaibao()" class="health-common-btn mt15">生成海报</a></div>
</article>
	<!-- Initialize Swiper -->
    <script>
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        effect: 'coverflow',
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: 'auto',
        coverflow: {
            rotate: 50,
            stretch: 0,
            depth: 100,
            modifier: 1,
            slideShadows : true
        }
    });
    
    
    
	function autoTimeBtn() {
		//alert(o.innerText);
		var _val = document.getElementById("createBtn").innerHTML;
		//alert(_val);
		if(_val=="生成海报"){
			$("#createBtn").html("提交中.");
		}
		if(_val=="提交中."){
			$("#createBtn").html("提交中..");
		}
		if(_val=="提交中.."){
			$("#createBtn").html("提交中...");
		}
		if(_val=="提交中..."){
			$("#createBtn").html("提交中.");
		}
		setTimeout(function() {
			autoTimeBtn()
		},
		1000)
	}
    /**
     * 生成海报按钮
     */
    function createHaibao(){
    	var haibaoId = $(".swiper-slide-active").children().val();
    	var leaderId = $("#leaderId").val();
		if(haibaoId !=null && haibaoId!="undefined" && haibaoId!=""){
			autoTimeBtn();
			//$("#createBtn").attr("disabled","true");
			$('#createBtn').removeAttr('href');//去掉a标签中的href属性
			$('#createBtn').removeAttr('onclick');//去掉a标签中的onclick事件
			
			//alert(haibaoId+"|"+leaderId);
			$.ajax({//setShowGoodsComment
				url: "../healthLeader/healthLeader!createHaibaoCustom.action",
				type: "POST",
				data: {'haibaoId':haibaoId,'leaderId':leaderId} ,
				success: function(data){
					if(data.status==1){
						$alert("success",data.message);
						setTimeout(function(){
							window.location.href="../healthLeader/healthLeader!gotoHaibaoPage.action";
						},1000);
						
					}else if(data.status==0){
						$alert("warn",data.message);
					}else if(data.status==3){
						$alert("warn","去申请领秀页面");
					}
				},error:function(data){
					$alert("warn","内部错误,请稍后重试!");
					window.location.reload(true);
				}
			});
		}else{
			var hasNo = $("#hasNo").val();
			if(hasNo !=null && hasNo!="undefined" && hasNo!="" && hasNo=="1"){
				$alert("warn","暂无发布的背景图片");
			}else{
				$alert("warn","请选择海报背景图片");
			}
		}
    }
    
    
    </script>
    
    
</body>
</html>