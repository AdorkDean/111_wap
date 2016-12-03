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
    <title>申请售后</title>

</head>

<body>
<form id="inputForm" action="${base}/member/return!goods.action" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${id?default('')}"/>
<#assign title="申请售后">
<#include "/static/inc/wap/header.ftl">
<article class="order-box pb70">
	<article class="customer-service">
    	<div class="service-type clearfix">
        	<div class="service-op">
            	<select id="type" name="type">
                	<option value="">请选择退款或退货</option>
                    <option value="0">我要退货</option>
                    <option value="1">我要换货</option>
                    <option value="2">退款(无需退货)</option>
                </select>
            </div>
        	<span>服务类型</span>
        </div>
        <div class="service-type clearfix">
        	<!--
        	<div class="service-op">
            	<b>请上传图片,最多4张</b>
            	<a href="#" class="upload-btn"></a>
            </div>
            -->
        	<span>上传图片</span>
            <div class="img-breviary">
            	<b><i style="display:none;">-</i><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"><input name="images" class="up-file" type="file"></b>
                <b><i style="display:none;">-</i><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"><input name="images" class="up-file" type="file"></b>
                <b><i style="display:none;">-</i><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"><input name="images" class="up-file" type="file"></b>
                <b><i style="display:none;">-</i><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"><input name="images" class="up-file" type="file"></b>
            </div>
        </div>
        <div class="service-text">
        	<textarea id="problem" name="problem" placeholder="请输入您遇到的问题" maxlength="249"></textarea>
        </div>
        <div class="agree-operate clearfix">
         	<a id="agree-a" href="#" class="iconfont fl agree-btn agree-btn-checked">M</a>
           	 我已阅读并同意<a href="${base}/help/return_explain.html">《退换货条例》</a>
		</div>
        <ul class="shopping-cart-list">
        	<#list orderItems?if_exists as item>
        	<#assign retunCount=0>
            <#list retrunItems?if_exists as return>
            	<#if return.goods_id == item.goods_id>
            		<#assign retunCount = retunCount + return.quantity?default(0)>
            	</#if>
            </#list>
            <li class="clearfix">
                <a href="#">
                    <dl class="cart-pro-info clearfix">
                        <dt><a href="${base}/m/${item.goods_id?default('')}.html"><img src="${ui1}${item.abbreviation_picture?default('')}"></a></dt>
                        <dd>
                            <div class="order-product">
                                <div class="order-product-unit">
                                    <p class="order-product-price">${currency(item.price?default(0),'true')}</p>
                                    <p class="order-product-count">x${item.quantity?default(0)}</p>
                                    <#if retunCount gt 0><p class="order-product-count">已处理x${retunCount?default(0)}</p></#if>
                                </div>
                                <div class="order-product-title">
                                    <h2>${item.goods_name?default('')}</h2>
                                    <p class="order-product-spec">规格:${item.spec?default('')}</p>
                                </div>
                            </div>
                        </dd>
                    </dl>
                </a>
                <div class="cart-product-count">
                    <a id="min" class="min" href="javascript:void(0);">-</a>
                    
                    <input name="${item.goods_id?default(0)}"  type="input" value="0" maxValue="${item.quantity?default(0)-retunCount?default(0)}" readonly>
                    <a id="max" class="max" href="javascript:void(0);">+</a>
                </div>
            </li>
            </#list >
        </ul>
    </article>
</article>
<div class="cart-bottom-box clearfix">
	<a href="#" class="settle-btn fr">提交</a>    
</div>
</form>
</body>
</html>
<script type="text/javascript">
$().ready(function() {

	<#if flag?exists>
		<#if flag>
			alert("提交成功！");
			window.location.href="${base}/member/return.action";
		<#else>
			alert("提交失败！");
		</#if>
	</#if>
	
	//减
	$(".min").click(function(){
		var val = eval($(this).siblings('input').val());
		var maxValue = eval($(this).siblings('input').attr("maxValue"));
		if(val>0){
			$(this).siblings('input').val(val-1);
		}
	});
	
	
	//加
	$(".max").click(function(){
		var val = eval($(this).siblings('input').val());
		var maxValue = eval($(this).siblings('input').attr("maxValue"));
		if(val < maxValue){
			$(this).siblings('input').val(val + 1);
		}
	});
	
	//agree
	$('.agree-btn').click(function(){
		if($(this).hasClass('agree-btn-checked')){
			$(this).removeClass('agree-btn-checked').html('L');	
		}else{
			$(this).addClass('agree-btn-checked').html('M');		
		}	
	})
	
	
	$(".img-breviary i").live("click",function(){
		$(this).siblings('input').val("");
		$(this).siblings('img').attr("src","${base}/web/images/applyaftersaleaddimgbg.jpg");
		$(this).css({display:'none'});
	});
	
	$(".up-file").live("change",function(){
		
		var def = $(this);
		var file = this.files[0]
		
		if(file == undefined){
			return false;
		}
		var name = file.name;
    	var size = file.size;
    	var type = file.type;
    	
	    if(size > 4 *1024*1024){
	    	alert("文件不能大于4M!");
	    	return false;
	    }
	    
	    //JPG、GIF、PNG、JPEG、BMP
	    if(!(type.toLocaleUpperCase().toString().indexOf('JPG')>0
	    	||type.toLocaleUpperCase().toString().indexOf('GIF')>0
	    	||type.toLocaleUpperCase().toString().indexOf('PNG')>0
	    	||type.toLocaleUpperCase().toString().indexOf('JPEG')>0
	    	||type.toLocaleUpperCase().toString().indexOf('BMP')>0)){
	    	alert("文件格式只能是JPG、GIF、PNG、JPEG、BMP!");
	    	return false;
	    }
		
		if(typeof FileReader == 'undefined'){ 
			var imagePath=input.value;
			$(this).prev().src= imagePath;
		}else{ 
			var reader = new FileReader(); 
			reader.readAsDataURL(file); 
			reader.onload = function(e){
				def.prev().attr("src",this.result);
			}
			def.siblings().css({display:'block'});
			return true; 
		} 
	});
	
	
	//添加图片
	$(".upload-btn").click(function(){
		if($(".img-breviary input").size() < 4){
			var imageHtml = '<b><i>-</i><img src="${base}/web/images/applyaftersaleaddimgbg.jpg"><input class="up-file" type="file"></b>';
			$(".img-breviary").append(imageHtml);
		}	
	});
	
	$(".settle-btn").click(function(){
		
		if($("#type").val() == null || $("#type").val() == ""){
			alert("请选择售后类型");
			return false;	
		}
		
		if(!$("#agree-a").hasClass("agree-btn-checked")){
			alert("请阅读并同意退换货条例！");
			return false;	
		}
		
		if($("#problem").val() == null || $("#problem").val() == ""){
			alert("请填写您遇到的问题！");
			return;	
		}
		
		var flag =  false;
		
		$(".cart-product-count input").each(function(i){
			if(eval($(this).val())>0){
				flag =  true
			}
		});
		if(!flag){
			alert("请选择相关退货商品！");
			return;
		}
		
		$('#inputForm').submit();
		
	});
});
</script>