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
    <title>需求确认</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/reserveOrderAdd.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/header2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
    <#assign ui1="http://img.zdfei.com/">
    <style>
     .single-select-order{float:left; margin:10px 5px 0 0; width:50px;max-width:50px;}
	   .brand_sale .capital{border:none; font-weight:normal;}
        .brand_cont{top:45px; position: absolute;}
    .street-list li i{position: absolute; top:50%; left:-20px; margin-top:-11px; display: block; height:21px; width:14px; line-height:18px; text-align:center; background: url("${base}/web/images/street_icon02.png") no-repeat; background-size:14px; color:#FFF; font-size:12px; font-style: normal;}
	
	</style>
</head>
<body id="phone">
<!--封装生成预订单需要的参数表单-->
<form name="reserveOrderForm" id="reserveOrderForm">
      <input id="receiverId" name="receiverId" type="hidden" value="${receiverId?default('')}"/>
      <input id="remark" name="remark" type="hidden" value=""/>
      <input id="deliveryId" name="deliveryId" type="hidden" value=""/>
      <input id="rxImgUrl" name="rxImgUrl" type="hidden" value=""/>
      <input id="goodsId" name="goodsId" type="hidden" value="${goods.id?default('')}"/>
      <input id="goodsNum" name="goodsNum" type="hidden" value="1"/>
      <input id="replyMobile" name="replyMobile" type="hidden" value=""/>
</form>

<header class="header">
    <a href="javascript:;" class="iconfont top-left-btn" onclick="window.history.back();">B</a>
    <h2 class="header-title">需求确认</h2>
</header>
<article class="order-box pb70">
    <div class="settle-address" onclick="receiverListShowNew()">
        <#if receiver?exists>
        	   <p class="receipt-p" id="ajaxReceiver_renmobile">收货人:${receiver.receiver?default('')}<span>${receiver.mobile?default('')}</span></p>
               <p class="address-p" id="ajaxReceiver_areaaddress">${receiver.area?default('')}&nbsp;&nbsp;${receiver.locationAddress?default('')}${receiver.address?default('')}</p>
        <#else>
               <p class="receipt-p" id="ajaxReceiver_renmobile">请添加收货地址</p>
               <p id="ajaxReceiver_areaaddress"></p>
        </#if>
        <b class="new-s-icon-left"></b>
        <b class="new-s-icon-right"></b>
    </div>
    <!-- 商品金额-->
    <div class="shopping-sum">
        <ul>
            <li class="sum-list04">
                <h2><b>手　机：</b><input id="replyMobileTemp" name="replyMobileTemp" type="tel" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"  type="text" placeholder="药师会根据您预留的手机号回拨"/></h2>
            </li>
            <li class="sum-list02">
                <h2 style="padding-right:15px;">拍照上传处方
                    <span class="camera-yf"   onclick="$('.zwcMask').show();"></span>
                    <img id="photo_img" src="" style="display:none;position:absolute;right:5px;  width:38px; height:38px; padding:1px;  text-align: center; font-size:12px; color:#FFF;"/>
                    <span id="photo_img_delete" onclick="photoImgDelete()" style="display: none;position:absolute;right:5px; width:30px; height:30px; padding:5px; background: rgba(0,0,0,.2); text-align: center; font-size:12px; color:#FFF; z-index:10;">点击删除</span>
                </h2>
                <p style="padding-right:70px;"><b>提示</b>上传处方可以缩短审核时间，只能上传一张</p>
            </li>
        </ul>
    </div>
    <!-- 商品信息-->
    <dl class="shop-message">
        <dt><img src="${ui1}${goods.abbreviation_picture?default('')}" alt=""/></dt>
        <dd>
            <p>${goods.goodsName?default('')}</p>
            <p><b style="float: right;" id="goodsNumTemp">＊1</b>规格：${goods.spec?default('')}</p>
            <span class="pc_price">￥${goods.wap_price?string("0.00")}</span>
        </dd>
    </dl>
    <!-- 预约数量-->
    <div class="shopping-sum">
        <ul>
            <li class="sum-list03">
                <h2 style="padding-right:15px;"><span class="num-btn"><b class="left-yf" onclick="updateCount($(this),'sub');"></b><b class="number-yf count-text">1</b><b class="right-yf" onclick="updateCount($(this),'add');"></b></span>预约数量</h2>
            </li>
            <li class="sum-list04">
                <h2><b>备　注：</b><input type="text" id="remarkTemp" maxlength="200" placeholder="选填"/></h2>
            </li>
            <li class="sum-list02">
                <h2  style="padding-right:15px;"><span style="color:#fc4044; font-size:19px;" class="freight"></span>服务费 </h2>
                <p style="padding-right:25px;"><b>提示</b>多次预约只收取一次服务费</p>
            </li>
        </ul>
    </div>
    <!-- 总金额-->
    <div class="total-money">
        <p style="padding-top:12px;">共 <span class="sum-cnt" style="margin-left:0;">1</span> 件</p>
        <p style="padding-top:2px;">应付金额 <span class="total_amount"></span></p>
        <a href="javascript:saveReserveOrder()" id="tijiaoOrder" class="new-settle-btn">确认需求</a>
    </div>
</article>


<!-- 头像上传区域 -->
<div class="zwcMask" style="display:none;">
	<div id="clipArea"></div>
	<input type="file" id="file" />
	<button class="exitBtn" onclick="hideImageUpload()">取消</button>
	<span class="hand-top"></span>
	<span class="hand-bottom"></span>
	<div class="submit" id="upload_btn" style="margin:10px auto;">确定上传</div>
	<div class="submit" id="upload_btn_" style="margin:10px auto;display:none;">正在上传中...</div>
</div>

<input type="hidden" value="" id="dataURL"/>
<div class="divMasks"></div>

<script src="${base}/web/js/jquery.min.js"></script>
<script src="${base}/web/js/iscroll-zoom.js"></script>
<script src="${base}/web/js/hammer.js"></script>
<script src="${base}/web/js/jquery.photoClip.copy.js"></script>

<script>
$("#clipArea").photoClip({
	width: 200,
	height: 300,
	file: "#file",
	view: "#view",
	ok: "#upload_btn",
	strictSize: false,
	loadStart: function() {
		console.log("照片读取中");
	},
	loadComplete: function() {
		console.log("照片读取完成");
	},
	clipFinish: function(dataURL) {
		console.log(dataURL);
		//$("#dataURL").val(dataURL);
		imagesLoad(dataURL);
	}
});

function showImageUpload()
{
	$(".zwcMask").fadeIn();
}

function hideImageUpload()
{
	$(".photo-clip-rotateLayer").html("");
	$("#view").css("backgroundImage","url('')"); 
	$(".zwcMask").fadeOut();
	window.location.reload();
}

function imagesLoad(imageData)
{
	$("#upload_btn").hide();
	$("#upload_btn_").show();
	$(".divMasks").show();
	//var imageData = $("#dataURL").val();
	if(imageData != null && imageData != '')
	{
		$.ajax(
		{
		   url: "../member/reserveOrder!uploadCustomsImage.action",	
	       type: "post",
	       data: {imageData:imageData},
	       async:false,
	       success: function(data)
	       {
	       		if(data.state == 1){
		       		$("#rxImgUrl").val(data.webPath);
		       		$(".zwcMask").fadeOut();
		       		$(".divMasks").hide();
		       		$(".camera-yf").hide();
					$("#photo_img").attr("src",imageData);
					$("#photo_img").show();
					$("#photo_img_delete").show();
	       		}else if(data.state == 0){
	       			$alert("warn","上传图片大小不能大于5M!");
	       			$("#upload_btn").show();
					$("#upload_btn_").hide();
					$(".divMasks").hide();
	       		}else if(data.state == 2){
	       			$alert("warn","上传异常!");
	       		}
	       		
	       }
	   }); 
	}
	else
	{
		$alert("warn","您还没有裁取图片！");
		$("#upload_btn").show();
		$("#upload_btn_").hide();
		$(".divMasks").hide();
	}
}


function photoImgDelete(){
	//图片src置为空
	$("#photo_img").attr("src","");
	$(".photo-clip-rotateLayer img").attr("src","");
	
	//图片隐藏
	$("#photo_img").hide();
	//删除图片隐藏
	$("#photo_img_delete").hide();
	//照相机显示
	$(".zwcMask").fadeOut();
	//表单图片url置为空
	$("#rxImgUrl").val("");
	$("#upload_btn").show();
	$("#upload_btn_").hide();
	$(".camera-yf").show();
}
</script>



<#include "/WEB-INF/pages/member/reserveorder/reserve_order_add_shade.ftl">   
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=51293f48fbe2e1e51de5299446404273&plugin=AMap.PlaceSearch,AMap.Geocoder"></script>
<script type="text/javascript" src="${base}/web/js/reserve_order_add.js"></script>
<script type="text/javascript">
    /*$(function(){
        $('.add-current').click(function(){
            if($(this).children('b').hasClass('invoice-m-no')){
                $(this).children('b').removeClass('invoice-m-no')
            }else{
                $(this).children('b').addClass('invoice-m-no');
            }
        })
    })
    $(".settle-address").click(function(){
        $(".click-select").show();
        $('.click-address').css({bottom:"0"});

    });
    $(".address-close").click(function(){
        $(".click-select").hide();
        $('.click-address').css({bottom:"-100%"});
        $(".click-select-edit").hide();
        $('.click-address-edit').css({bottom:"-100%"});
        $(".my-coupon").hide();
        $('.coupon-content').animate({bottom:"-100%"});
    });
    $(".augment-address").click(function(){
        $(".click-select-edit").show();
        $(".click-select").hide();
        $('.click-address-edit').css({bottom:"0"});

    });
    $(".set-icon").click(function(){
        $(".click-select-edit").show();
        $(".click-select").hide();
        $('.click-address-edit').css({bottom:"0"});

    });
    $(".sum-coupon").click(function(){
        $(".click-my-coupon").show();
        $('.click-coupon-content').css({bottom:"0"});

    });
    $(".sum-distribution").click(function(){
        $(".distribution-way").show();
        $('.distribution-content').css({bottom:"0"});

    });
	*/

	var count_minus_plus_clickFlag = true;//用于控制点击是否可用 true:可点击 false:不可点击
	function updateCount(elementId,operator){
		if(count_minus_plus_clickFlag == false){
			return;
		}
		
		count_minus_plus_clickFlag = false;//关门
		
		var count = elementId.siblings(".count-text").html();
		var countBack = count;
		
		count = parseInt(count);
		if(count <= 1 && operator == 'sub'){
			count_minus_plus_clickFlag = true;
			return;
		}
		if(count >= 99 && operator == 'add'){
			count_minus_plus_clickFlag = true;
			$alert("warn","您所需要的数量过大,请拨打客服电话进行订购!",null,null);
			return;
		}
		if(operator == 'add'){
			count += 1;
			elementId.siblings(".count-text").html(count);
		}else if(operator == 'sub'){
			count -= 1;
			elementId.siblings(".count-text").html(count);
		}
		
		//ajax获取商品价格，小计，运费（参数 商品id,商品数量,receiverId）
		var goodsId = $("#goodsId").val();
		var goodsNum = elementId.siblings(".count-text").html();
		var receiverId = $("#receiverId").val();
		
		//alert("goodsId:"+goodsId+",goodsNum:"+goodsNum+",receiverId:"+receiverId);
		
		$.ajax({
			url:'reserveOrder!calculatePrice.action',
			data:{"goodsId":goodsId,"goodsNum":goodsNum,"receiverId":receiverId},
			type:'post',
			dataType: 'json',
			success:function(data){
				/*
				alert("运费:"+data.freight);
				alert("预订单总金额:"+data.totalAmount);
				alert("商品单价:"+data.pcPrice);
				alert("state:"+data.state);
				*/
				if(data.state == 1){
					$(".pc_price").html('￥'+data.wapPrice.toFixed(2));
					//$(".goods_total").html(data.goodsTotal);
					$(".total_amount").html('￥'+data.totalAmount);
					$(".freight").html('￥'+data.freight);
					$("#goodsNum").val(count);
					$("#goodsNumTemp").html('＊'+count);
					$(".sum-cnt").html(count);
					count_minus_plus_clickFlag = true;
					return;
				}else if(data.state == 2){//库存不足
					elementId.siblings(".count-text").html(data.stock);
					$alert("warn","商品库存不足!",null,null);
					count_minus_plus_clickFlag = true;
					return;
				}else{
					$alert("warn",data.msg,null,null);
					count_minus_plus_clickFlag = true;
					return;
				}
			},
			error:function(){
				//alert("error");
				count_minus_plus_clickFlag = true;
			}
		});
		
	}
	
	
	function calculatePrice(){
		$.ajax({
			url:'reserveOrder!calculatePrice.action',
			data:{"goodsId":$("#goodsId").val(),"goodsNum":$("#goodsNum").val(),"receiverId":$("#receiverId").val()},
			type:'post',
			dataType: 'json',
			success:function(data){
				if(data.state == 1){
					$(".pc_price").html('￥'+data.wapPrice.toFixed(2));
					$(".goods_total").html(data.goodsTotal);
					$(".total_amount").html('￥'+data.totalAmount);
					$(".freight").html('￥'+data.freight);
					return;
				}else if(data.state == 2){//库存不足
					//elementId.siblings(".count-text").val(count);
					$alert("warn","商品库存不足!",null,null);
					return;
				}else if(data.state == 3){//商品不存在
					$alert("warn","商品不存在!",null,null);
					return;
				}else if(data.state == 4){//商品不存在
					$alert("warn","商品不存在!",null,null);
					return;
				}else if(data.state == 5){//商品数量为空
					$alert("warn","请填写药品数量!",null,null);
					return;
				}else{
					$alert("warn",data.msg,null,null);
					return;
				}
			},
			error:function(){
				$alert("warn","网络异常!",null,null);
			}
		});
	}
	
	$(document).ready(function() {
		calculatePrice();
	});
		
	
	//保存预订单
	function saveReserveOrder() {
		//$("#replyMobileTempMsg").html("");
		
		$("#replyMobile").val($("#replyMobileTemp").val());// 回拨电话
		$("#remark").val($("#remarkTemp").val());// 备注
		
		var receiverId = $("#receiverId").val();// 收货地址
		if (receiverId == '') {
			$alert("warn","请添加收货地址!",null,null);
			$("#tijiaoOrder").attr("href", "javascript:saveReserveOrder()");
			$("#tijiaoOrder").html("提交预定");
			return;
		}
		if(!ajaxCheckArea('',receiverId)){
			return;
		}
		
		// 手机号码
		var reply_mobile = $.trim($("#replyMobile").val());
		if (reply_mobile == '') {
			//$("#replyMobileTempMsg").html("请填写回拨电话!");
			$alert("warn","请填写回拨手机号码!",null,null);
			$("#replyMobileTemp").focus();
			return;
		} else {
			var re = /^[1][3,4,7,5,8][0-9]{9}$/;
			if (!re.test(reply_mobile)) {
				//$("#replyMobileTempMsg").html("请输入11位有效回拨电话!");
				$alert("warn","请输入11位有效回拨手机号码!",null,null);
				$("#replyMobileTemp").focus();
				return;
			} else {
				//$("#replyMobileTempMsg").html("");
			}
		}
		
		$("#tijiaoOrder").attr("href", "javascript:void(0)");
		$("#tijiaoOrder").html("提交中...");
		var formInfo = $("#reserveOrderForm").serialize();
		$.ajax({
			url : "../member/reserveOrder!saveReserveOrder.action",
			type : "post",
			data : formInfo,
			success : function(data) {
				if (data.flag) { // '1'更新,'2'修改
					$("#m-shade-bg").show();
					/*
					$("#reserveOrderId").val(data.rxTReserveOrderId);
					$("#orderAmount").val(data.orderAmount);
					$("#reserveOrderPayForm").attr("action","../member/reserveOrder!reserveOrderAddSuccess.action");
					$("#reserveOrderPayForm").submit();
					*/
					return;
				} else {
					if (data.message == '1') {
						$alert("warn","请选择收货地址!",null,null);
					} else if (data.message == '2') {
						//$("#replyMobileTempMsg").html("请填写回拨电话!");
						$alert("warn","请填写回拨手机号码!",null,null);
						$("#replyMobileTemp").focus();
					} else if (data.message == '21') {
						//$("#replyMobileTempMsg").html("请输入11位有效回拨电话!");
						$alert("warn","请输入11位有效回拨手机号码!",null,null);
						$("#replyMobileTemp").focus();
					} else if (data.message == '3') {
						$alert("warn","请输入商品数量!",null,null);
					} else if (data.message == '4') {
						$alert("warn","请选择商品!",null,null);
					} else if (data.message == '5') {
						$alert("warn","商品不存在!",null,null);
					} else if (data.message == '6') {
						$alert("warn","该商品库存不足!",null,null);
					}else if (data.message == '7') {
						$alert("warn","保存预订单失败!",null,null);
					}else if (data.message == '8') {
						$alert("warn","该商品库存不足!",null,null);
					}else if (data.message == '9') {
						$alert("warn","商品不存在!",null,null);
					}else if (data.message == '10') {
						$alert("warn","该商品已下架!",null,null);
					}
					
					$("#tijiaoOrder")
							.attr("href", "javascript:saveReserveOrder()");
					$("#tijiaoOrder").html("提交预定");
				}
			},
			error : function() {
				$alert("error","系统异常，请稍后再试",null,null);
				$("#tijiaoOrder").attr("href", "javascript:saveReserveOrder()");
				$("#tijiaoOrder").html("提交预定");
			}
		});
	}
	
	
	//检查收货地址 层级  
	function ajaxCheckArea(areaId,receiverId){
	   //var flag = false;
	   //$.ajax({
		//		url : "../location/area!ajaxCheckArea.action",
		//		type : "post",
		//		cache:false,  
	    //   		async:false,  
		//		data : {"areaId":areaId,"receiverId":receiverId},
		//		success : function(data) {
		//		    if(data.flag=='true'){
		//		      flag=true;
		//		    }
		//		},
		//		error : function() {
		//		}
		//	});
		//if(!flag){
		//   $alert("warn","收货地址所在地区信息不完整,请完善!",null,null);
		//}
		//return flag;
		
		return true;
	}
</script>
<#include "../order/shippingAddressAddDiv.ftl"/>
<#include "../order/shippingAddressSelectDiv.ftl"/>