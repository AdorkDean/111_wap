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
<title>订单详情</title>

</head>

<body>
<#assign title="订单详情">
<#include "/static/inc/wap/header.ftl">
<article class="order-box pb70">
    <article class="order-info-box">
        <header><h1>订单信息</h1></header>
        <ul class="order-info-text">
        	<li>
            	<span>订单编号</span>
                <p>${order.orderSn?default('')}</p>
            </li>
            <li>
            	<span>下单时间</span>
                <p><time pubdate="pubdate"><#if order.createDate?has_content>${order.createDate?string('yyyy-MM-dd hh:mm:ss')}</#if></time></p>
            </li>
            <li>
            	<span>支付方式</span>
                <p><#if orderPaymentWay?has_content>${orderPaymentWay.name?default('')}</#if></p>
            </li>
            <li>
            	<span>订单状态</span>
                <p>
                	<#if order.orderStatus?exists && order.orderStatus == 0>待支付 </#if>                                    	
                	<#if order.orderStatus?exists && order.orderStatus == 1>待发货</#if>
                	<#if order.orderStatus?exists && order.orderStatus == 2>待收货</#if>
                	<#if order.orderStatus?exists && order.orderStatus == 3>已完成</#if>
                	<#if order.orderStatus?exists && order.orderStatus == 4>已取消</#if>
                	<#if order.orderStatus?exists && order.orderStatus == 5>已过期(24小时未支付)</#if>
            	</p>
			</li>
        </ul>
    </article>
    <article class="order-info-box">
        <header><h1>商品信息</h1></header>
        <ul class="order-product-info">
        	<#assign itemTotal=0>
        	<#list orderItems as item>
        	<a href="${base}/m/${item.goods_id?default('')}.html"> 
        	<li>
        		<#assign itemTotal = item.price * item.quantity + itemTotal>
            	<img src="${ui1}${item.abbreviation_picture?default('')}">
                <div class="order-product">
                	<div class="order-product-unit">
                    	<p class="order-product-price">${currency(item.price?default(0),'true')}</p>
                        <p class="order-product-count">x${item.quantity?default(0)}</p>
                    </div>
                	<div class="order-product-title">
                    	<h2>${item.goods_name?default('')}</h2>
                        <p class="order-product-spec">规格:${item.spec?default('')}</p>
                    </div>
                </div>
            </li>
            </a>
            </#list>
        </ul>
        <a href="javascript:void(0)" class="show-more">显示全部</a>
    </article>
    <article class="order-info-box">
        <header><h1>收货信息</h1></header>
        <ul class="order-info-text">
        	<li>
            	<span>收货人</span>
                <p>${order.receiver?default('')}</p>
            </li>
            <li>
            	<span>手机号码</span>
                <p>${order.phone?default('')}</p>
            </li>
            <li>
            	<span>收货地址</span>
                <p>${order.areaName?default('')}&nbsp;&nbsp;${order.detailedAddress?default('')}</p>
            </li>
        </ul>
    </article>
    
    <#if order?exists && order.ifInvoice?exists && order.ifInvoice != 0>
    <article class="order-info-box">
        <header><h1>发票信息</h1></header>
        <ul class="order-info-text">
        	<li>
            	<span>发票类型</span>
                <p>
					<#if order.invoiceType?exists && order.invoiceType ==1>明细</#if>
                	<#if order.invoiceType?exists && order.invoiceType ==2>药品</#if>
                	<#if order.invoiceType?exists && order.invoiceType ==3>保健品</#if>
                	<#if order.invoiceType?exists && order.invoiceType ==4>医疗器械</#if>
				</p>
            </li>
            <li>
            	<span>发票抬头</span>
                <p>${order.invoiceTitle?default('')}</p>
            </li>
        </ul>
    </article>
    </#if>
                    
    <article class="order-info-box">
        <header><h1>订单金额</h1></header>
        <ul class="order-info-text order-info-price">
        	<li>
            	<span>商品总金额</span>
                <p>${currency(itemTotal,'true')}</p>
            </li>
            <li>
            	<span>运费</span>
                <p>${currency(order.shippingFee?default(0),'true')}</p>
            </li>
            <#if order?exists && order.couponId?exists && order.couponId gt 0>
            <li>
            	<span>优惠券折扣</span>
            	<p>${currency(order.couponDiscount?default(0) * - 1,'true')}</p>
            </li>	
            </#if>
            <li>
            	<span>应付金额</span>
                <p class="final-price">${currency(order.payableAmount?default(0),'true')}</p>
            </li>
        </ul>
    </article>
    
    <#if order.orderStatus?exists && order.orderStatus == 0>
    <form name="orderPayForm" id="orderPayForm" method="post">
      <input id="orderId" name="orderId" type="hidden" value="${order.id?default('')}"/>
      <input id="paymentPluginId" name="paymentPluginId" type="hidden" value=""/>        
      <input id="paymentMethodId" name="paymentMethodId" type="hidden" value="1"/>
	</form>
    <ul class="pay-order-details">
        <li id="alipayWapPluginli" val="alipayWapPlugin">
            <b class="invoice-m-no"></b>
            <div class="pay-way clearfix"><img src="${base}/web/images/single_ico1.png">支付宝支付</div>
        </li>
        <li id="payWapYktPlugin" val="payWapYktPlugin">
            <b class="invoice-m-no"></b>
            <div class="pay-way clearfix"><img src="${base}/web/images/ykt_icon.png">医卡通支付</div>
        </li>  
        <li id="wzfWapPluginli" val="wzfWapPlugin" style="display:none;">
            <b class="invoice-m-no"></b>
            <div class="pay-way clearfix"><img src="${base}/web/images/single_ico2.png">微信支付</div>
        </li>       
    </ul>
    </#if>                                    	
</article>
<div class="bottom-btn">
	<ul class="pay-info-button clearfix">
		<#if order?exists && order.orderStatus == 0>
    		<li><a href="javascript:void(0);" class="cancelOrder" val="${order.id?default('')}">取消订单</a></li>
			<li><a href="javascript:void(0);" onclick="updateOrderPayMethod()">去支付</a></li>
    	</#if>                                    	
    	<#if order?exists && order.orderStatus == 1>
        	<!--<li><a href="javascript:void(0);" class="cancelOrder" val="${order.id?default('')}">取消订单</a></li>-->
    	</#if>
    	<#if order?exists && order.orderStatus == 2>
    		<li><a href="${base}/member/order!logistics.action?id=${order.id?default('')}">查看物流</a></li>
        	<li><a href="javascript:void(0);" class="complete" val="${order.id?default('')}">确认收货</a></li>
    	</#if>
    	<#if order?exists && order.orderStatus == 3>
        	<#if chufang?exists && chufang == '1'>
    			<#else>
        	<li><a href="${base}/carts/cart!rebuy.action?orderid=${order.id?default('')}">再次购买</a></li>
			</#if>
			<li><a href="${base}/member/return!index.action?id=${order.id?default('')}">申请售后</a></li>
    	</#if>
    	<#if order?exists && order.orderStatus == 4>
    		<#if chufang?exists && chufang == '1'>
    			<#else>
    			<li><a href="${base}/carts/cart!rebuy.action?orderid=${order.id?default('')}">重新购买</a></li>
    		</#if>			
    	</#if>
    	<#if order?exists && order.orderStatus == 5>
    		<#if chufang?exists && chufang == '1'>
    			<#else>
    		<li><a href="${base}/carts/cart!rebuy.action?orderid=${order.id?default('')}">重新购买</a></li>
    		</#if>			
    	</#if>
	</ul>
</div>
</body>
</html>
<script type="text/javascript">
$().ready(function() {

//确认收货
$(".complete").click(function(){
	var def = $(this);
	if(confirm("确认收货?")){
		$.ajax({
	        type: "post",
	        cache: true,	
	        async: false,	        
	        url: "${base}/member/order!complete.action",
	        data: {
	        	id:def.attr("val")
	        },	            
	        success: function(data, textStatus){
	        	alert(data.message);
	        	if(data.flag){
	        		location.reload();
	        	}
	        }
	    });
	}
});

//取消订单
$(".cancelOrder").click(function(){

	var def = $(this);
	if(confirm("确认取消订单?")){
		$.ajax({
	        type: "post",
	        cache: true,	
	        async: false,	        
	        url: "${base}/member/order!cancelOrder.action",
	        data: {
	        	orderId:def.attr("val")
	        },	            
	        success: function(data, textStatus){
	        	alert(data.message);
	        	if(data.flag){
	        		location.reload();
	        	}
	        }
	    });
    }
});


//pay way
$(".pay-order-details").children('li').click(function(){
	 var _th = $(this);
	 if(_th.children('b').hasClass('invoice-m-no')){
		_th.children('b').removeClass('invoice-m-no');
		$("#paymentPluginId").val(_th.attr("val"));
		$(".pay-order-details").children('li').each(function(){
		  if($(this).attr("id")!=_th.attr("id")){
		     $(this).children('b').addClass('invoice-m-no');  
		  }
        });
	 }else{
	     _th.children('b').addClass('invoice-m-no');
		$("#paymentPluginId").val("");
	 }
})

//order count
var liCount = $(".order-product-info").children().length;
oPro = $(".order-product-info");
if(liCount == 1){
	oPro.css({"height":90+"px"});
	$(".show-more").css({"display":"none"})
}else if(liCount == 2){
	oPro.css({"height":180+"px"});
	$(".show-more").css({"display":"none"})
}else{
	oPro.css({"height":180+"px"});
	$(".show-more").click(function(){
		oPro = $(".order-product-info");
		if(oPro.hasClass("height-auto")){
			oPro.removeClass("height-auto");
			$(".show-more").html("显示全部");
		}else{
			oPro.addClass("height-auto");
			$(".show-more").html("收起部分商品");
		}	
	});
}

});

//微信支付是否显示
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
  	$("#wzfWapPluginli").show();
  	$("#alipayWapPluginli").hide();
});

function updateOrderPayMethod(){
     var formInfo = $("#orderPayForm").serialize();
     
     if($("#paymentPluginId").val() == null || $("#paymentPluginId").val() == ""){
     	alert("请选择支付方式!");
     	return;
     }
      $.ajax({
		url : "${base}/member/order!updateOrderPaymentMethod.action",
		type : "post",
		data : formInfo,
		success : function(data) {
		data = eval('(' + data + ')');
			if (data.flag) { // '1'更新,'2'修改
			     $("#orderPayForm").attr("action","${base}/member/order!payOrder.action");
				 $("#orderPayForm").submit();
			} else {
				if (data.message == '0') {
					alert("结算失败!");
				} else if (data.message == '2') {
					alert("该订单不存在!");
				} else {
					alert(data.message);
				}
			}
		},
		error : function() {
			alert("系统异常，请稍后再试");
		}
	});
}
</script>

<a href="javascript:;" class="kefu" onclick="easemobIM()" tenantid="2461"></a>
<script src="//kefu.easemob.com/webim/easemob.js?tenantId=2461&amp;hide=true" async="async"></script>
<style>
.kefu {
    width: 42px;
    height: 42px;
    display: inline-block;
    background-size: 42px;
    border-radius: 50%;
    -webkit-border-radius: 50%;
    position: fixed;
    left:88%;
    bottom: 80px;
    font-size: 34px;
    z-index: 99;
}
</style>