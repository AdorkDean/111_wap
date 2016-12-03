
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
    <title>评论</title>
</head>
<body>
<#assign title="评论">
<#if orderItems?exists && orderItems?has_content>
<#include "/static/inc/wap/header.ftl">
<#list orderItems?if_exists as item>
<div class="comment_item">
	<article class="order-box">
		<dl class="comment-pro-info clearfix">
	    	<dt><a href="${base}/m/${item.goods_id?default()}.html"><img src="${ui1}${item.abbreviation_picture?default('')}"></a></dt>
	        <dd>
	        	<h3>${item.goods_name?default('')}</h3>
	            <p>商品数量：${item.quantity?default(0)}</p>
	            <p>商品价格：<b>${currency(item.price?default(0),'true')}</b></p>
	        </dd>
	    </dl>
	    <input type="hidden" class="goods_id" value="${item.goods_id?default('')}"/>
	    <input type="hidden" class="order_id" value="${item.order_id?default('')}"/>
	    <div class="comment-star-box">
	    	<ul>
	        	<li class="clearfix">
	            	<div class="star-bg clearfix goods_comment" >
	                	<a href="#" class="star1"></a>
						<a href="#" class="star2"></a>
	                    <a href="#" class="star3"></a>
	                    <a href="#" class="star4"></a>
	                    <a href="#" class="star5 star-active"></a>
	                </div>
	            	<span>商品描述相符</span>
	            </li>
	            <li class="clearfix">
	            	<div class="star-bg clearfix seller_comment" >
	                	<a href="#" class="star1"></a>
						<a href="#" class="star2"></a>
	                    <a href="#" class="star3"></a>
	                    <a href="#" class="star4"></a>
	                    <a href="#" class="star5 star-active"></a>
	                </div>
	            	<span>卖家服务态度</span>
	            </li>
	            <li class="clearfix">
	            	<div class="star-bg clearfix fast_comment">
	                	<a href="#" class="star1"></a>
						<a href="#" class="star2"></a>
	                    <a href="#" class="star3"></a>
	                    <a href="#" class="star4"></a>
	                    <a href="#" class="star5 star-active"></a>
	                </div>
	            	<span>物流发货速度</span>
	            </li>
	            <li class="clearfix">
	            	<div class="star-bg clearfix people_comment">
	                	<a href="#" class="star1"></a>
						<a href="#" class="star2"></a>
	                    <a href="#" class="star3"></a>
	                    <a href="#" class="star4"></a>
	                    <a href="#" class="star5 star-active"></a>
	                </div>
	            	<span>配送人员态度</span>
	            </li>
	        </ul>
	    </div>
	    <div class="common-text">
	    	<textarea id="comment" placeholder="宝贝很好哦，极力向大家推荐！！" maxlength="249"></textarea>
	    </div>
	    <p class="comment-tips">发表评论赢取积分哟~</p>
	</article>
	<div class="follow-box">
		<a href="#" class="common-btn">提交评论</a>
	</div>
</div>
</#list>
<#include "/static/inc/wap/footer.ftl">
<#else>
<section class="common-main">
    <#include "/static/inc/wap/header.ftl">
    <article class="order-box">
        <article class="comment_auto">
                <dl>
                    <dt><img src="${base}/web/images/comment.svg"/></dt>
                    <dd>
                    	<p>暂无评论订单</p>
	    			</dd>
                </dl>
        </article>
    </article>
    <div class="footer-bottom">
	<#include "/static/inc/wap/footer.ftl">
	</div>
</section>
</#if>

</body>
</html>
<#if orderItems?exists && orderItems?has_content>
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {
	$(".common-btn").click(function(){
		var comment_item = $(this).parent().parent();
		
		var count_goods_comment = 5; 		
		var has_goods_comment = comment_item.find(".goods_comment .star-active").length;
		if(has_goods_comment==0){
			alert("请给商品描述打分");
			return;
		}else{
			if(comment_item.find(".goods_comment .star-active").hasClass("star4")){
				count_goods_comment = 4; 	
			}else if(comment_item.find(".goods_comment .star-active").hasClass("star3")){
				count_goods_comment = 3; 	
			}else if(comment_item.find(".goods_comment .star-active").hasClass("star2")){
				count_goods_comment = 2; 	
			}else if(comment_item.find(".goods_comment .star-active").hasClass("star1")){
				count_goods_comment = 1; 	
			}
		}
		
		var count_seller_comment = 5; 		
		var has_seller_comment = comment_item.find(".seller_comment .star-active").length;
		if(has_seller_comment == 0){
			alert("请给卖家服务打分");
			return;
		}else{
			if(comment_item.find(".seller_comment .star-active").hasClass("star4")){
				count_seller_comment = 4; 	
			}else if(comment_item.find(".seller_comment .star-active").hasClass("star3")){
				count_seller_comment = 3; 	
			}else if(comment_item.find(".seller_comment .star-active").hasClass("star2")){
				count_seller_comment = 2; 	
			}else if(comment_item.find(".seller_comment .star-active").hasClass("star1")){
				count_seller_comment = 1; 	
			}
		}
		
		var count_fast_comment = 5; 		
		var has_fast_comment = comment_item.find(".fast_comment .star-active").length;
		if(has_fast_comment==0){
			alert("请给发货速度打分");
			return;
		}else{
			if(comment_item.find(".fast_comment .star-active").hasClass("star4")){
				count_fast_comment = 4; 	
			}else if(comment_item.find(".fast_comment .star-active").hasClass("star3")){
				count_fast_comment = 3; 	
			}else if(comment_item.find(".fast_comment .star-active").hasClass("star2")){
				count_fast_comment = 2; 	
			}else if(comment_item.find(".fast_comment .star-active").hasClass("star1")){
				count_fast_comment = 1; 	
			}
		}
		
		
		var count_people_comment = 5; 
		var has_people_comment = comment_item.find(".people_comment .star-active").length;
		
		if(has_people_comment==0){
			alert("请给配送人员打分");
			return;
		}else{
			if(comment_item.find(".people_comment .star-active").hasClass("star4")){
				count_people_comment = 4; 	
			}else if(comment_item.find(".people_comment .star-active").hasClass("star3")){
				count_people_comment = 3; 	
			}else if(comment_item.find(".people_comment .star-active").hasClass("star2")){
				count_people_comment = 2; 	
			}else if(comment_item.find(".people_comment .star-active").hasClass("star1")){
				count_people_comment = 1; 	
			}
		}
		
		var omment = $("#comment").val();
		
		$.ajax({
			url : "${base}/member/comment!add.action",
			type : "post",
			data : {
				"goodscomment.goods":count_goods_comment,
				"goodscomment.seller":count_seller_comment,
				"goodscomment.fastMail":count_fast_comment,			
				"goodscomment.fastMailPeople":count_people_comment,
				"goodscomment.orderId":comment_item.find(".order_id").val(),
				"goodscomment.comment":omment,
				"goodscomment.goodsId":comment_item.find(".goods_id").val(),
			},
			success : function(message) {
				alert(message.content);
				if (message.type) { 
					location.reload();
				}
			},
			error : function() {
				alert("系统异常，请稍后再试");
			}
		});
	});
});
</script>
</#if>
