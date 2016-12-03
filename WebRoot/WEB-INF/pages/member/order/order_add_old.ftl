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
    <title>结算</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
	    <script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
	 <style rel="stylesheet" type="text/css">
        .select-address,.edit-address{display:none;position: fixed; left:0; right:0; top:0; bottom:0; background:rgba(0,0,0,.8); z-index: 99; }
        .address-main,.edit-main{position: absolute; bottom:-100%; left:0; right:0; max-height:100%; background: #FFF; overflow: auto;}
        .edit-main{background: #f0f0f0;}
        .address-header{height:55px; width:100%; background: #FFF;}
        .address-title{height:55px;  width:100%; text-align: center; position: absolute; top:0; left:0; font-size:16px; color:#0d0d0d; line-height:55px;}
        .address-close{height:20px; width:20px; position: absolute; top:17px; right:17px;}
        .address-list{padding:10px; border-top:1px solid #b2b2b2; position: relative; overflow: hidden;}
        .list-state{position: absolute; top:50%; left:15px; margin-top:-15px; font-size:26px;}
        .list-edit{position: absolute; top:50%; right:5px; margin-top:-25px; font-size: 26px;color: #03a7a1;display: inline-block;width: 50px;height: 50px;line-height: 50px;text-align: center;}
        .address-list .list-state{color:#FFF;}
        .select-current .list-state{color:#03a7a1;}
        .no-select-current .list-state{color:#a1a1a1;}
        .address-details{padding:0 45px;}
        .message-name{line-height:18px; font-size:14px; margin-bottom:5px;}
        .message-main{line-height:18px; color:#999;font-size:14px;}
        .augment-address{padding:10px; border-top:1px solid #b2b2b2; position: relative; overflow: hidden;}
        .augment-tit{height:50px; line-height:50px; display: inline-block; padding-left:45px; color:#000; font-size:16px; }
        .augment-icon{position: absolute; top:26px; left:15px; width:16px; height:16px; -webkit-user-select: none;  -moz-user-select: none;  -ms-user-select: none;  user-select: none;}
        .augment-icon img{width:16px; height:16px; display: block;}
        .edit-list{margin-top:-1px; border-bottom:1px solid #dadada;border-top:1px solid #dadada; overflow: hidden;  background: #FFF;}
        .edit-list li{ padding-left:10px; line-height:45px;border-top:1px solid #dadada; overflow: hidden; margin-top:-1px; padding-right:10px;}
        .edit-list li span{float:left; height:50px; line-height:50px; width:65px; color:#000; font-size:13px;}
        .edit-list li input{display: inline-block; line-height:50px; color:#000; border:none;}
        .choose-address{padding-top:13px;}
        .choose-address select{float:left; margin:0 5px 13px 0;}
        .follow-btn{width:100%; }
        .follow-btn a{display: block;  margin:10px; height:35px;line-height:35px; text-align: center; border-radius: 6px; background: #00b4b8; font-size:14px; color:#FFF;}
    	.sel-sin .single-select{max-width:48px; min-width:30px; margin-top:16px; font-size:13px; background-color: transparent;appearance:none;-moz-appearance:none;-webkit-appearance:none; border:none;}
        .ipt-code-num{height:45px; overflow:hidden; position:relative; padding-right:40px;}
        .ipt-code-num input{ height:15px; border:1px solid #8d8d8d; border-radius:4px; -webkit-border-radius:4px; padding:5px 10px; line-height:15px;}
		.ipt-code-num a{ position:absolute; font-size:16px; line-height:45px; color:#00b4b8; right:0; top:0; z-index:2;}
        .frist-select{background: #e74925;padding: 2px;border-radius: 5px;color: #FFF;margin-left: 5px;}
        .no-frist-select{background: #999;padding: 2px;border-radius: 5px;color: #FFF;margin-left: 5px;}
    </style>
</head>

<body onload="jisuanOrderPrice()">
<form name="orderForm" id="orderForm" method="post">
      <input id="paymentWayId" name="paymentWayId" type="hidden" value="<#if paymentWayId?exists>${paymentWayId?default('')}</#if>"/>
      <input id="receiverId" name="receiverId" type="hidden" value="<#if receiverId?exists>${receiverId?default('')}</#if>"/>
      <input id="invoiceTitle" name="invoiceTitle" type="hidden" value="<#if invoiceTitle?exists>${invoiceTitle?default('')}</#if>"/>
      <input id="ifInvoice" name="ifInvoice" type="hidden" value="<#if ifInvoice?exists>${ifInvoice?default('')}</#if>"/>
      <input id="invoiceType" name="invoiceType" type="hidden" value="<#if invoiceType?exists>${invoiceType?default('')}</#if>"/>
      <input id="couponCardId" name="couponCardId" type="hidden" value="<#if couponCardId?exists>${couponCardId?default('')}</#if>"/>
      <input id="couponCardType" name="couponCardType" type="hidden" value="<#if couponCardType?exists>${couponCardType?default('')}</#if>"/>
      <input id="payPluginId" name="payPluginId" type="hidden" value="<#if payPluginId?exists>${payPluginId?default('')}</#if>"/>
      <input id="deliveryId" name="deliveryId" type="hidden" value="<#if deliveryWay?exists>${deliveryWay.id?default('')}</#if>"/>
</form>

<header class="header">
    <a href="${base}/carts/cart!page.action?url=${base}" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">结算</h2>
    <a href="/" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
<article class="order-box pb70">
	<div class="settle-address">
    	<a class="receipt-info amend-address" id="shdz_a" href="javascript:receiverListShow()">
        	<b class="iconfont">J</b>
        	<h2>收货地址</h2>
        	<#if receiver?exists>
        	   <p class="receipt-p" id="ajaxReceiver_renmobile">${receiver.receiver?default('')}<span>${receiver.mobile?default('')}</span></p>
               <p id="ajaxReceiver_areaaddress">${receiver.area?default('')}&nbsp;&nbsp;${receiver.address?default('')}</p>
            <#else>
               <p class="receipt-p" id="ajaxReceiver_renmobile">请添加收货地址</p>
               <p id="ajaxReceiver_areaaddress"></p>
        	</#if>
        </a>
    </div>
    <ul class="settle-info-box">
    	<li class="clearfix" id="spjeli">
        	<span>商品金额</span>¥<#if goodsPrice?exists>${currency(goodsPrice?default(''))}</#if>
        </li>
        <li class="clearfix">
        	<a id="psfsli" href="javascript:ajaxDeliveryWayList()"><span>配送方式</span><b class="amend-coupon" style="color:#00b4b8;"><#if deliveryWay?exists>${deliveryWay.name?default('')}</#if>(邮费¥<#if goodsPrice?exists>${currency(shippingFee?default(''))}</#if>)</b></a>
        </li>
        <li class="clearfix" id="yhjli">
        	<a id="xjdyj_a" href="javascript:couponCardSubmit()"><span>优惠券</span>
        	<#if couponCardType?exists && couponCardType=='2'>
	        	<#if coupon?exists>
	        	     ${coupon.name?default('')}
	        	<#else>
	        	     <b class="amend-coupon" style="color:#00b4b8;">查看可用的优惠券</b>
	        	</#if> 
	        <#else>
	        	     <b class="amend-coupon" style="color:#00b4b8;">查看可用的优惠券</b>
	        </#if> 	           
        	</a>
        </li>
        <li class="clearfix">
        	<span>优惠券编码</span>
            <div class="ipt-code-num" id="couponCard_DivId">
                <#if couponCardType?exists && couponCardType=='1'>
		        	<#if coupon?exists>
		        	     ${coupon.name?default('')}
		        	     <a href="javascript:cardNoQuxiao()" id="couponCard_aHref">取消</a>
		        	<#else>
		        	     <input type="text" id="couponCardNo" name="couponCardNo" placeholder="请输入优惠券编码"/>
                         <a href="javascript:cardNoUser()" id="couponCard_aHref">使用</a>
		        	</#if> 
		        <#else>
		        	    <input type="text" id="couponCardNo" name="couponCardNo" placeholder="请输入优惠券编码"/>
                        <a href="javascript:cardNoUser()" id="couponCard_aHref">使用</a>
		        </#if>
            </div>
        </li>
         <li class="clearfix" id="yfjeli">
        	<span>应付金额</span>¥<#if goodsPrice?exists>${currency(payableAmount?default(''))}</#if>
        </li>
    </ul>
    <ul class="pay-info-box" style="margin-bottom:0;">
        <li id="wzfWapPlugin" <#if payPluginId?exists><#if payPluginId=='wzfWapPlugin'>class="curr"</#if></#if>  style="display:none;"  onclick="zfxz('wzfWapPlugin')">
            <b></b>
            <div class="pay-way clearfix"><img src="${base}/web/images/single_ico2.png">微信支付</div>
        </li>
        <li <#if payPluginId?exists><#if payPluginId=='alipayWapPlugin'>class="curr"</#if></#if> onclick="zfxz('alipayWapPlugin')" id="alipayWapPlugin">
            <b></b>
            <div class="pay-way clearfix"><img src="${base}/web/images/single_ico1.png">支付宝支付</div>
        </li>
        <li id="payWapYktPlugin" <#if payPluginId?exists><#if payPluginId=='payWapYktPlugin'>class="curr"</#if></#if> onclick="zfxz('payWapYktPlugin')">
            <b></b>
            <div class="pay-way clearfix"><img src="${base}/web/images/ykt_icon.png">医卡通支付</div>
        </li>
        <li id="4" onclick="zfxz('4')"  <#if beijingFlag?exists><#if beijingFlag=='0'>style="display:none;"</#if></#if> <#if payPluginId?exists><#if payPluginId=='4'>class="curr"</#if></#if>>
            <b></b>
            <div class="pay-way clearfix"><img src="${base}/web/images/single_ico3.png">货到付款</div>
        </li>
    </ul>
    <div class="fp clearfix">
    	<a href="javascript:fpxz()" class="fp-btn <#if ifInvoice?exists><#if ifInvoice=='1'>fp-btn-check</#if></#if> fl" id="fpxx">
            <b class="iconfont">L</b>发票信息
        </a>
        <div class="fp-info">
        	<input type="text" id="invoiceTitle_text" maxlength="50" placeholder="请输入个人或单位信息" value="<#if invoiceTitle?exists>${invoiceTitle?default('')}</#if>"/>
        </div>
    </div>
</article>
<div class="bottom-btn">
	<a href="javascript:orderSubmit();" class="common-btn" id="order_submit">结算</a>
</div>

<form name="orderPayForm" id="orderPayForm" method="post">
      <input id="orderId" name="orderId" type="hidden" value=""/>
      <input id="paymentPluginId" name="paymentPluginId" type="hidden" value=""/>
      <input id="paymentMethodId" name="paymentMethodId" type="hidden" value=""/>
</form>
<!--选择收货地址弹窗开始-->
<div class="select-address" id="receiverCeng_DivId">
    <div class="address-main" id="receiverCeng_DivId_main">
        <header class="address-header">
            <h2 class="address-title">选择收货地址</h2>
            <div class="address-close" onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
        </header>
         <div id="receiverList_Divid">
          
         </div>
        <div class="augment-address" onclick="addReceiver()">
            <i class="augment-icon"><img src="${base}/web/images/member_icon05.png" alt=""/></i>
            <span class="augment-tit">新增地址</span>
        </div>
    </div>
</div>
<!--选择收货地址弹窗结束-->
<!--编辑收货地址弹窗开始-->
<div class="edit-address">
    <div class="edit-main">
        <header class="address-header">
            <h2 class="address-title">收货地址</h2>
            <div class="address-close" onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
        </header>
        <form name="receiverForm" id="receiverForm" method="post">
            <input id="memberReceiver_id" name="memberReceiver.id" type="hidden" value=""/>
            <input id="memberReceiver_areaId" name="memberReceiver.areaId" type="hidden" value=""/>
            <input id="memberReceiver_area" name="memberReceiver.area" type="hidden" value=""/>
            <input id="memberReceiver_isDefault" name="memberReceiver.isDefault" type="hidden" value=""/>
	        <ul class="edit-list">
	            <li><span>收货人</span><input type="text" id="memberReceiver_receiver" maxlength="10" name="memberReceiver.receiver" placeholder="请填写收货人"/></li>
	            <li><span>手机号码</span><input type="tel" id="memberReceiver_mobile" name="memberReceiver.mobile" placeholder="请填写手机号码" /></li>
	            <li>
	                <span>所在地区</span>
	                <div class="for-ipt sel-sin" id="addOrUpdate_Receiver_Address" style="padding-left:0;">
	                </div>
	            </li>
	            <li><span>详细地址</span><input type="text" id="memberReceiver_address"  maxlength="40" name="memberReceiver.address" placeholder="请填写详细收货地址" style="width: 200px;" /></li>
	            <li><span>邮政编码</span><input type="text" id="memberReceiver_zipCode" name="memberReceiver.zipCode" placeholder="请填写邮政编码(选填)"/></li>
	        </ul>
        </form>
        <div class="follow-btn"><a href="javascript:saveOrUpdateReceiver()">完成</a></div>
    </div>
</div>
<!--编辑收货地址弹窗结束-->


<!--选择优惠券弹窗开始-->
<div class="select-address" id="select-coupon">
    <div class="address-main" id="coupon-main">
        <header class="address-header">
            <h2 class="address-title">选择可用的优惠券</h2>
            <div class="address-close"  onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
        </header>
        <div id="couponList_DivId">
        </div>
    </div>
</div>
<!--选择优惠券弹窗结束-->


<!--配送方式div-->
<div class="select-address" id="select-delivery">
    <div class="address-main" id="delivery-main">
        <header class="address-header">
            <h2 class="address-title">选择配送方式</h2>
            <div class="address-close"  onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
        </header>
        <div id="deliveryList_DivId">
        
        </div>
    </div>
</div>
<!--配送方式div-->


</body>
<script type="text/javascript">
//动态获取配送方式
function ajaxDeliveryWayList(){
    $("#deliveryList_DivId").html("");
	var deliveryId = $("#deliveryId").val();
	var paymentWayId =$("#paymentWayId").val();
	var receiverId = $("#receiverId").val();
	if(receiverId==''){
	  $alert("warn","请先选择收货地址!",null,null);
	  return ;
	}
    $.ajax({
			url : "${base}/member/order!ajaxDeliveryWayList.action",
			type : "post",
			data : {"deliveryId":deliveryId,"paymentWayId":paymentWayId,"receiverId":receiverId},
			success : function(data) {
			    if(data.flag=='1'){
			            var receiverhtml = "";
						for ( var i = 0; i < data.resultList.length; i++) {
						   if(deliveryId==data.resultList[i].id){
						     receiverhtml += "<div class=\"address-list select-current\" id=\"deliveryList_"+data.resultList[i].id+"\" onclick=\"xuanzeDeliveryWay("+data.resultList[i].id+")\"><i class=\"iconfont list-state\">M</i><div class=\"address-details\" ><p class=\"message-name\">"+data.resultList[i].name+"<b class=\"frist-select\">推荐</b></p>";
						   }else{
						     if(data.resultList[i].isflag=='1'){
						      receiverhtml += "<div class=\"address-list select-current\" id=\"deliveryList_"+data.resultList[i].id+"\" onclick=\"xuanzeDeliveryWay("+data.resultList[i].id+")\"><i class=\"iconfont list-state\">L</i><div class=\"address-details\"><p class=\"message-name\">"+data.resultList[i].name+"<b class=\"frist-select\">推荐</b></p>";
						     }else{
						      receiverhtml += "<div class=\"address-list select-current\" id=\"deliveryList_"+data.resultList[i].id+"\"><i class=\"iconfont list-state\">L</i><div class=\"address-details\"><p class=\"message-name\">"+data.resultList[i].name+"<b class=\"no-frist-select\">不可用</b></p>";
						     }
						   }
							receiverhtml += "<p class=\"message-main\">"+data.resultList[i].instro+"</p>";
							receiverhtml += "</div></div>";
						}
			        $("#deliveryList_DivId").html(receiverhtml);
			        $("#select-delivery").show();
                    $('#delivery-main').animate({bottom:"0"});
			    }
			},
			error : function() {
			}
		});
}
//选择配送方式
function xuanzeDeliveryWay(deliveryId){
   $("#deliveryId").val(deliveryId);
   createOrderPrice();
   closeDivCen();
}


//检查收货地址 层级  
function ajaxCheckArea(areaId,receiverId){
   var flag = false;
   $.ajax({
			url : "${base}/location/area!ajaxCheckArea.action",
			type : "post",
			cache:false,  
       		async:false,  
			data : {"areaId":areaId,"receiverId":receiverId},
			success : function(data) {
			    if(data.flag=='true'){
			      flag=true;
			    }
			},
			error : function() {
			}
		});
	if(!flag){
	   $alert("warn","请完善收货地址所在地区信息!",null,null);
	}
	return flag;	
}


$(function(){
		var dHeight = $(window).height(); 
		$('.ipt-yh-code').focus(function(){
			$(document).scrollTop(dHeight/2);
		})	
	})
   //优惠券使用弹出层
function couponCardSubmit(){
     $("#couponList_DivId").html("");
     $.ajax({
			url : "${base}/member/order!ajaxOrderCouponList.action",
			type : "post",
			data : {},
			success : function(data) {
			if(data.length>0){
			    var receiverhtml = "";
	            var couponCardId =$("#couponCardId").val();
					for ( var i = 0; i < data.length; i++) {
					   if(couponCardId==data[i].id){
					     receiverhtml += "<div class=\"address-list select-current\" id=\"couponCard_"+data[i].id+"\">";
					   }else{
					     receiverhtml += "<div class=\"address-list\" id=\"couponCard_"+data[i].id+"\">";
					   }
					    receiverhtml += "<i class=\"iconfont list-state\">M</i><div class=\"address-details\" onclick=\"xuanzeCouponCard("+data[i].id+")\">";
						receiverhtml += "<p class=\"message-name\" id=\"couponCardName_"+data[i].id+"\">"+data[i].name+"</p>";
						receiverhtml += "<p class=\"message-main\">"+data[i].begin_Date+"至"+data[i].end_date+"</p>";
						receiverhtml += "</div></div>";
					}
				 $("#couponList_DivId").html(receiverhtml);
				 $("#select-coupon").show();
                 $('#coupon-main').animate({bottom:"0"});
             }else{
			     $alert("warn","暂无符合使用规则的优惠券!",null,null);
			 }    
			},
			error : function() {
			}
		});
}

//选择优惠券
function xuanzeCouponCard(couponCardId){
     $("#couponCard_"+couponCardId).addClass('select-current').siblings().removeClass('select-current');
     $("#couponCardId").val(couponCardId);
	 $("#couponCardType").val("2");
     $("#couponCard_DivId").html("<input type=\"text\" id=\"couponCardNo\" name=\"couponCardNo\" placeholder=\"请输入优惠券编码\"/><a href=\"javascript:cardNoUser()\" id=\"couponCard_aHref\">使用</a>");
     var couponName =$("#couponCardName_"+couponCardId).html();
     $("#xjdyj_a").html("<span>优惠券</span>"+couponName);
      createOrderPrice();
      closeDivCen();
}
//使用优惠券码
function cardNoUser(){
  var couponCardNo = $("#couponCardNo").val();// 优惠券码
   if($.trim(couponCardNo)==''){
       $alert("warn","请输入优惠券编码",null,null);
       return ;
   }
   $.ajax({
		url : "${base}/member/order!checkCouponCard.action",
		type : "post",
		data : {
			"couponCardNo" : couponCardNo
		},
		success : function(data) {
			if(data.resultFlag=='1'){
			   $("#couponCardId").val(data.couponCardId);
			   $("#couponCardType").val("1");
			   $("#couponCard_DivId").html(data.name+"<a href=\"javascript:cardNoQuxiao()\" id=\"couponCard_aHref\">取消</a>")
			   $("#xjdyj_a").html("<span>优惠券</span><b class=\"amend-coupon\" style=\"color:#00b4b8;\">查看可用的优惠券</b>");
			   createOrderPrice();
			}else if(data.resultFlag=='2'){
			   $alert("warn","请输入优惠券编码!",null,null);
			}else if(data.resultFlag=='3'){
			   $alert("warn","该优惠券不存在!",null,null);
			}else{
			   $alert("warn","该优惠券不符合使用规则,无法使用!",null,null);
			}
		},
		error : function() {
			$alert("error","操作失败，请稍后再试",null,null);
		}
	});
   
}
//优惠券取消
function cardNoQuxiao(){
 $("#couponCardId").val("");
 $("#couponCardType").val("");
 $("#couponCard_DivId").html("<input type=\"text\" id=\"couponCardNo\" name=\"couponCardNo\" placeholder=\"请输入优惠券编码\"/><a href=\"javascript:cardNoUser()\" id=\"couponCard_aHref\">使用</a>")
  createOrderPrice();
}



//收货地址列表弹出层显示
function receiverListShow(){
     $("#receiverList_Divid").html("");
     $.ajax({
			url : "${base}/member/order!ajaxReceiverList.action",
			type : "post",
			data : {},
			success : function(data) {
			   if(data.length>0){
			    var receiverhtml = "";
	            var receiverId =$("#receiverId").val();
				for ( var i = 0; i < data.length; i++) {
				   if(receiverId==data[i].id){
				     receiverhtml += "<div class=\"address-list select-current\" id=\"addressList_"+data[i].id+"\">";
				   }else{
				     receiverhtml += "<div class=\"address-list\" id=\"addressList_"+data[i].id+"\">";
				   }
				    receiverhtml += "<i class=\"iconfont list-state\">M</i><div class=\"address-details\" onclick=\"xuanzeReceiver("+data[i].id+")\">";
					receiverhtml += "<p class=\"message-name\">"+data[i].receiver+"，"+data[i].mobile+"</p>";
					receiverhtml += "<p class=\"message-main\">"+data[i].area+"&nbsp;&nbsp;"+data[i].address+"</p>";
					receiverhtml += "</div><span class=\"iconfont list-edit\" onclick=\"updateReceiver("+data[i].id+")\">P</span></div>";
				}
				$("#receiverList_Divid").html(receiverhtml);
				$("#receiverCeng_DivId").show();
                $('#receiverCeng_DivId_main').animate({bottom:"0"});
			   }else{
			    addReceiver();
			   }
			},
			error : function() {
			}
		});
}
//选择收获地址
function xuanzeReceiver(receiverId){
    if(ajaxCheckArea('',receiverId)){
       $.ajax({
			url : "${base}/member/order!ajaxReceiver.action",
			type : "post",
			data : {"receiverId":receiverId},
			success : function(data) {
	            if(data!=''&&data!=null){
	                $("#addressList_"+receiverId).addClass('select-current').siblings().removeClass('select-current');
	                $("#ajaxReceiver_renmobile").html(data.receiver.receiver+"<span>"+data.receiver.mobile+"</span>");
	                $("#ajaxReceiver_areaaddress").html(data.receiver.area+"&nbsp;&nbsp;"+data.receiver.address);
	                $("#receiverId").val(data.receiver.id);
	                if(data.beijingFlag == '1'){
	                  $("#4").show();
	                }else{
	                  $("#4").hide();
	                  $("#4").removeClass('curr');
	                  if($("#paymentWayId").val()=='4'){
		                  $("#payPluginId").val("");
				          $("#paymentWayId").val("");
	                  }
	                }
	                
	                createOrderPrice();
	                closeDivCen();
	            }
			},
			error : function() {
			}
		});
    }
}
//关闭弹出层
function closeDivCen(){
	$(".select-address").hide();
	$('.address-main').animate({bottom:"-100%"});
	$(".edit-address").hide()
	$('.edit-main').animate({bottom:"-100%"});
}
//ajax 获取一级收获地址		                
function ajaxClocationList(){
    $.ajax({
			url : "${base}/member/order!ajaxClocationList.action",
			type : "post",
			data : {},
			success : function(data) {
	            var receiverhtml = "<select class=\"single-select\" id=\"areaId1\" onchange=\"areaSelect('areaId',1)\" name=\"areaId1\">";
	            receiverhtml += "<option value=\"\">省份</option>";
	            var receiverId =$("#receiverId").val();
				for ( var i = 0; i < data.length; i++) {
					receiverhtml += "<option value=\""+data[i].id+"\">"+data[i].name+"</option>";
				}
				receiverhtml += "</select>";
				$("#addOrUpdate_Receiver_Address").html(receiverhtml);
				
				receiverhtml = "<select class=\"single-select\" id=\"areaId2\" onchange=\"areaSelect('areaId',2)\" name=\"areaId2\">";
	            receiverhtml += "<option value=\"\">城市</option>";
				receiverhtml += "</select>";
				
				$("#addOrUpdate_Receiver_Address").append(receiverhtml);
				
				receiverhtml = "<select class=\"single-select\" id=\"areaId3\" onchange=\"areaSelect('areaId',3)\" name=\"areaId3\">";
	            receiverhtml += "<option value=\"\">区县</option>";
				receiverhtml += "</select>";
				
				$("#addOrUpdate_Receiver_Address").append(receiverhtml);
				
			},
			error : function() {
			}
		});
}
//新增收获地址弹出层
function addReceiver(){
   $("#memberReceiver_id").val("");
   $("#memberReceiver_areaId").val("");
   $("#memberReceiver_area").val("");
   $("#memberReceiver_isDefault").val("");
   $("#memberReceiver_receiver").val("");
   $("#memberReceiver_mobile").val("");
   $("#memberReceiver_address").val("");
   $("#memberReceiver_zipCode").val("");
   $(".edit-address").show();
   $(".select-address").hide();
   $('.edit-main').animate({bottom:"0"});
    ajaxClocationList();
}
//修改收获地址弹出层
function updateReceiver(receiverId){
      $.ajax({
			url : "${base}/member/order!ajaxReceiver.action",
			type : "post",
			data : {"receiverId":receiverId},
			success : function(data) {
	           if(data!=null&&data!=''){
	              $("#memberReceiver_id").val(data.receiver.id);
				   $("#memberReceiver_areaId").val(data.receiver.areaId);
				   $("#memberReceiver_area").val(data.receiver.area);
				   $("#memberReceiver_isDefault").val(data.receiver.isDefault);
				   $("#memberReceiver_receiver").val(data.receiver.receiver);
				   $("#memberReceiver_mobile").val(data.receiver.mobile);
				   $("#memberReceiver_address").val(data.receiver.address);
				   $("#memberReceiver_zipCode").val(data.receiver.zipCode);
				   $("#addOrUpdate_Receiver_Address").html("<span onclick=\"areaViewSelect()\" style=\"width:auto;\">"+data.receiver.area+"</span>")
				   $(".edit-address").show();
				   $(".select-address").hide();
   				   $('.edit-main').animate({bottom:"0"});
	           } 
			},
			error : function() {
			}
		});
}

 function areaViewSelect(){
    $("#memberReceiver_areaId").val("");
    $("#memberReceiver_area").val("");
    ajaxClocationList();
  }


 //选择地区
  function areaSelect(obj,level){
    var parentid=$("#"+obj+level).val();
    if(parentid!=''){  
       $.ajax({
			url:'${base}/member/order!clocationSelect.action',
			data:{"parentid":parentid},
			type:'post',
			success:function(data){
			    data = eval('(' + data + ')');
			     if(data.clocationList.length>0){
			       // var trhtml="<select name=\"areaId"+data.level+"\" onchange=\"areaSelect('areaId',"+data.level+")\" class=\"single-select\" id=\"areaId"+data.level+"\" ><option value=\"\">"+selectName+"</option>";
				   //for(var i=0;i<data.clocationList.length;i++){
				   //  trhtml+=" <option value="+data.clocationList[i].id+">"+data.clocationList[i].name+"</option>";
				  // }
				  //$("#addOrUpdate_Receiver_Address").append(trhtml);
				  var trhtml="";
				  for(var i=0;i<data.clocationList.length;i++){
					  trhtml+=" <option value="+data.clocationList[i].id+">"+data.clocationList[i].name+"</option>";
				  }
				  if(data.level==2){
				   trhtml=" <option value=\"\">城市</option>"+trhtml;
				   $("#"+obj+data.level).html(trhtml);
				   $("#"+obj+(data.level+1)).html("<option value=\"\">区县</option>");
				   if($("#"+obj+"4").val()){
				      $("#"+obj+"4").remove();
				   }
				  }else if(data.level==3){
				   trhtml=" <option value=\"\">区县</option>"+trhtml;
				   $("#"+obj+data.level).html(trhtml);
				   if($("#"+obj+"4").val()){
				      $("#"+obj+"4").remove();
				   }
				  }else if(data.level==4){
				    trhtml=" <select name=\"areaId"+data.level+"\" onchange=\"areaSelect('areaId',"+data.level+")\" class=\"single-select\" id=\"areaId"+data.level+"\" ><option value=\"\">街道</option>"+trhtml+"</select>";
				   	$("#addOrUpdate_Receiver_Address").append(trhtml);
				  }
			    }else if(level==3){
			      if($("#"+obj+"4").val()){
				      $("#"+obj+"4").remove();
				   }
			    }else if(level==2){
				   $("#"+obj+(level+1)).html("<option value=\"\">区县</option>");
			    }
			}
		});
    }else{
       if(level==1){
	     $("#"+obj+"2").html("<option value=\"\">城市</option>");
		 $("#"+obj+"3").html("<option value=\"\">区县</option>");
		 if($("#"+obj+"4").val()){
			 $("#"+obj+"4").remove();
		  }
       }else if(level==2){
         $("#"+obj+"3").html("<option value=\"\">区县</option>");
         if($("#"+obj+"4").val()){
			 $("#"+obj+"4").remove();
		  }
       }else if(level==3){
          if($("#"+obj+"4").val()){
			 $("#"+obj+"4").remove();
		  }
       }
    }
    //for(var i=level+1;i<5;i++){
	//   $("#"+obj+i).remove();
	//}
	$("#memberReceiver_areaId").val(parentid);
	if(parentid==''&&level!=1){
	   $("#memberReceiver_areaId").val($("#"+obj+(level-1)).val());
	}
	var area ="";
	for(var i=0;i<5;i++){
	  if($("#"+obj+(i+1)).val()&&$("#"+obj+(i+1)).val()!=''){
	      if(area==''){
	         area=$("#"+obj+(i+1)).find("option:selected").text();
	      }else{
	         area=area+"-"+$("#"+obj+(i+1)).find("option:selected").text();
	      }
	  }
	}
	$("#memberReceiver_area").val(area);
  }
  //保存或是修改收获地址
  function saveOrUpdateReceiver(){
    if(checkReceiver()&&ajaxCheckArea($("#memberReceiver_areaId").val(),'')){
       var formInfo=$("#receiverForm").serialize();
		$.ajax({
			url:"${base}/member/order!ajaxSaveOrUpdateReceiver.action",
			type:"post",
			data:formInfo,
			success:function(data){
				if(data!=''){ //'1'更新,'2'修改
					$alert("success","操作成功",null,null);
					closeDivCen();
					//receiverListShow();
					xuanzeReceiver(data);
				}else{
				    $alert("error","操作失败，请稍后再试",null,null);
				}
			},
			error:function(){
				$alert("error","操作失败，请稍后再试",null,null);
			}
		});
    }
}
  
  
//检测收货地址 添加项
  function checkReceiver(){
	// 收货人
	var receiver = $.trim($("#memberReceiver_receiver").val());
	if (receiver == '') {
		$alert("warn","请填写收货人",null,null);
		return false;
	} 
	// 手机号码
	var mobile = $.trim($("#memberReceiver_mobile").val());
	if (mobile == '') {
		$alert("warn","请填写手机号码",null,null);
		return false;
	} else {
		var re = /^[1][3,4,7,5,8][0-9]{9}$/;
		if (!re.test(mobile)) {
			$alert("warn","手机号码格式不正确",null,null);
			return false;
		}
	}
	// 邮编
	var zipCode = $.trim($("#memberReceiver_zipCode").val());
	if (zipCode != '') {
		var re = /^[1-9][0-9]{5}$/;
		if (!re.test(zipCode)) {
			$alert("warn","邮编格式不正确",null,null);
			return false;
		} 
	}
	// 地址
	var areaId = $.trim($("#memberReceiver_areaId").val());
	if (areaId == '') {
	    $alert("warn","请填写所在地区",null,null);
		return false;
	} 

	// 详细地址
	var address = $.trim($("#memberReceiver_address").val());
	if (address == '') {
		$alert("warn","请填写详细地址",null,null);
		return false;
	} else {
		$("#addressSpanFont").html("");
	}
	return true;
}


  //跳转到收货地址列表页面
  // function receiverSubmit(){
  //    if($("#orderId").val()==''){
  //       $("#orderForm").attr("action","${base}/member/order!orderReceiverList.action")
  //       $("#orderForm").submit();
  //    }
  // }
  //优惠券使用页面
   //function couponCardSubmit(){
   //if($("#orderId").val()==''){
   //   $("#orderForm").attr("action","${base}/member/order!orderCouponList.action")
   //   $("#orderForm").submit();
   //}   
   //}
   //选择支付方式
   function zfxz(id){
     var liCurr = $(".pay-info-box").children('li');
     var _th = $("#"+id);
	 if(_th.hasClass('curr')){
		_th.siblings('li').removeClass('curr');	 
	 }else{
		_th.addClass('curr');
		_th.siblings('li').removeClass('curr');	
		if($("#orderId").val()==''){
		    $("#payPluginId").val(id);
			if(id=='4'){
			  $("#paymentWayId").val(id);
			}else{
			  $("#paymentWayId").val("1");
			}
		}else{
		    $("#paymentPluginId").val(id);
			if(id=='4'){
			  $("#paymentMethodId").val(id);
			}else{
			  $("#paymentMethodId").val("1");
			}
		}
	 }
	 jisuanOrderPrice();
   }
  //选择发票
  function fpxz(){
    if($("#orderId").val()==''){
	   if($("#fpxx").hasClass('fp-btn-check')){
				$("#fpxx").removeClass('fp-btn-check').find('b').html('L');
				$("#invoiceType").val('');
				$("#ifInvoice").val('');
		  }else{
				$("#fpxx").addClass('fp-btn-check').find('b').html('M');
				$("#invoiceType").val('1');
				$("#ifInvoice").val('1');
	      }
      }
  }

$().ready(function() {
	function detectWeixinApi(callback){
	    if(typeof window.WeixinJSBridge == 'undefined' || typeof window.WeixinJSBridge.invoke == 'undefined'){
	        setTimeout(function(){
	            detectWeixinApi(callback);
	        },200);
	    }else{
	        callback();
	    }
	}
	detectWeixinApi(function(){
	  	$("#wzfWapPlugin").show();
	  	$("#alipayWapPlugin").hide();
	  	zfxz('wzfWapPlugin');
	});
});
//创建订单计算运费
function createOrderPrice(){
var couponCardId = $("#couponCardId").val();// 优惠券id
		var receiverId = $("#receiverId").val();// 收货地址id
		var paymentWayId = $("#paymentWayId").val();// 支付方式
		var deliveryId = $("#deliveryId").val();// 配送方式id
		$.ajax({
			url : "${base}/member/order!getOrderPrice.action",
			type : "post",
			data : {
				"couponCardId" : couponCardId,
				"paymentWayId" : paymentWayId,
				"receiverId" : receiverId,
				"deliveryId" : deliveryId
			},
			success : function(data) {
				data = eval('(' + data + ')');
				$("#spjeli").html("<span>商品金额</span>¥" + data.goodsPrice.toFixed(2));
				$("#psfsli").html("<span>配送方式</span><b class=\"amend-coupon\" style=\"color:#00b4b8;\">"+data.deliveryWay.name+"(邮费¥" + data.shippingFee.toFixed(2) + ")</b>");
				$("#yfjeli").html("<span>应付金额</span>¥" + data.payableAmount.toFixed(2));
				$("#deliveryId").val(data.deliveryWay.id);
			},
			error : function() {
			}
		});

}
//订单生成成功后计算运费
function orderPayChangePrice(){
        var orderId = $("#orderId").val();// 优惠券id
		var paymentWayId = $("#paymentMethodId").val();// 支付方式
		$.ajax({
			url : "${base}/member/order!orderPayChangePrice.action",
			type : "post",
			data : {
				"orderId" : orderId,
				"paymentWayId" : paymentWayId
			},
			success : function(data) {
				data = eval('(' + data + ')');
				$("#spjeli").html("<span>商品金额</span>¥" + data.price.toFixed(2));
				$("#psfsli").html("<span>配送方式</span><b class=\"amend-coupon\" style=\"color:#00b4b8;\">"+data.deliveryWay.name+"(邮费¥" + data.freight.toFixed(2) + ")</b>");
				$("#yfjeli").html("<span>应付金额</span>¥" + data.amountPayable.toFixed(2));
				$("#deliveryId").val(data.deliveryWay.id);
			},
			error : function() {
			}
		});

}
// 计算订单金额
function jisuanOrderPrice() {
    if($("#orderId").val()==''){
		createOrderPrice();
	}else{
	   orderPayChangePrice();
	}
}
//检查订单提交项
function checkOrderForm(){
   var receiverId =$("#receiverId").val();
   if(receiverId==''){
      $alert("warn","请选择收货地址",null,null);
      return false;
   }
   var paymentWayId =$("#paymentWayId").val();
   if(paymentWayId==''){
      $alert("warn","请选择支付方式",null,null);
      return false;
   }
   return true;
}
//创建订单
function createOrder(){
if(checkOrderForm()&&ajaxCheckArea('',$("#receiverId").val())){
    $("#order_submit").attr("href", "javascript:void(0)");
	$("#order_submit").html("提交中...");
    $("#invoiceTitle").val($("#invoiceTitle_text").val());
    var formInfo = $("#orderForm").serialize();
    $.ajax({
				url : "${base}/member/order!saveOrder.action",
				type : "post",
				data : formInfo,
				success : function(data) {
				data = eval('(' + data + ')');
				$("#order_submit").attr("href", "javascript:orderSubmit()");
				$("#order_submit").html("结算");
					if (data.flag) { // '1'更新,'2'修改
					    $("#orderId").val(data.message);
					    $("#shdz_a").attr("href", "javascript:void(0)");
					    $("#xjdyj_a").attr("href", "javascript:void(0)");
					    $("#fpxx").attr("href", "javascript:void(0)");
					    $("#psfsli").attr("href","javascript:void(0)");
					    $("#invoiceTitle_text").attr("disabled","disabled");
					    $("#couponCard_aHref").remove();
					    if($("#paymentWayId").val()!='4'){
						 $("#paymentPluginId").val($("#payPluginId").val());
						 $("#paymentMethodId").val($("#paymentWayId").val());
						 $("#orderPayForm").attr("action","${base}/member/order!payOrder.action");
						 $("#orderPayForm").submit();
					    }else{
                            $alert("success","提交成功!",null,null);
                            //跳转到 我的订单
                            window.location.href="${base}/member/orderList!getOrderList.action?tag=pay";
					    }
					} else {
						if (data.message == '1') {
							$alert("warn","请选择收货地址!",null,null);
						} else if (data.message == '2') {
							$alert("warn","请选择支付方式!",null,null);
						} else if (data.message == '3') {
							$alert("warn","请填写发票抬头!",null,null);
						}else if (data.message == '4') {
							$alert("warn","购物车没有商品,请选购结算商品!",null,null);
						} else if (data.message == '5') {
							$alert("warn","购物车中存在库存不足商品,请返回购物车修改!",null,null);
						} else if (data.message == '6') {
							$alert("warn","购物车存在已经下架商品!",null,null);
						} else if (data.message == '9') {
							$alert("warn","提交订单失败!",null,null);
						}else if (data.message == '7') {
							$alert("warn","商品:"+data.messageContent+"为处方药不可在线购买!",null,null);
						}else if (data.message == '8') {
							$alert("warn","商品:"+data.messageContent+"是处方药，目前处方药仅支持北京望京地区以货到付款的方式购买。",null,null);
						}else {
							$alert("warn",data.message,null,null);
						}
					}
				},
				error : function() {
					$alert("error","系统异常，请稍后再试",null,null);
					$("#order_submit").attr("href", "javascript:orderSubmit()");
					$("#order_submit").html("结算");
				}
			});
  }
}
//提交订单
function orderSubmit(){
  if($("#orderId").val()==''){
     createOrder();//创建订单
  }else{
    updateOrderPayMethod();
  }
}
//修改订单支付方式
function updateOrderPayMethod(){
 $("#order_submit").attr("href", "javascript:void(0)");
			$("#order_submit").html("提交中...");
             var formInfo = $("#orderPayForm").serialize();
              $.ajax({
				url : "${base}/member/order!updateOrderPaymentMethod.action",
				type : "post",
				data : formInfo,
				success : function(data) {
				data = eval('(' + data + ')');
				$("#order_submit").attr("href", "javascript:orderSubmit()");
				$("#order_submit").html("结算");
					if (data.flag) { // '1'更新,'2'修改
					    if($("#paymentMethodId").val()!='4'){
					     $("#orderId").val($("#orderId").val());
					     $("#paymentPluginId").val($("#paymentPluginId").val());
						 $("#paymentMethodId").val($("#paymentMethodId").val());
						 $("#orderPayForm").attr("action","${base}/member/order!payOrder.action");
						 $("#orderPayForm").submit();
					    }else{
                            $alert("success","提交成功!",null,null);
                            //跳转到 我的订单
                            window.location.href="${base}/member/orderList!getOrderList.action?tag=pay";
					    }
					} else {
						if (data.message == '0') {
							$alert("warn","结算失败!",null,null);
						} else if (data.message == '2') {
							$alert("warn","该订单不存在!",null,null);
						} else {
							$alert("warn",data.message,null,null);
						}
					}
				},
				error : function() {
					$alert("error","系统异常，请稍后再试",null,null);
					$("#order_submit").attr("href", "javascript:orderSubmit()");
					$("#order_submit").html("结算");
				}
			});

}


</script>


</html>
