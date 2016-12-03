// JavaScript Document
//time
var timer=null,i=60;
$("#getcode").on("click",function(){
	timer=setInterval(function(){
		$("#getcode").html(i+"秒后重新获取").css("color","#9d9d9d");
		i--;
		if(i<0){
			clearInterval(timer);
			$("#getcode").html("重新获取验证码").css("color","#ff4a4a");
		}
	},1000);
})

$(function(){
	
	//top collect btn
	$('.top-collect-btn').click(function(){
		if($(this).hasClass('alr')){
			$(this).removeClass('alr').html('D');
		}else{
			$(this).addClass('alr').html('E');	
		}
	})
	//top collect btn
	$('.top-heart-btn').click(function(){
		if($(this).hasClass('alr')){
			$(this).removeClass('alr').html('H');
		}else{
			$(this).addClass('alr').html('I');	
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
	})
}
	
	//shpping-cart listcheck
	/*
	$('.check-pro').click(function(){
		if($(this).hasClass('check-pro-curr')){
			$(this).removeClass('check-pro-curr').html('L');	
		}else{
			$(this).addClass('check-pro-curr').html('M');	
		}
	})*/
	
	//shopping-cart allcheck
	
	/*
	$('.check-all').click(function(){
		if($(this).hasClass('check-all-curr')){
			$(this).removeClass('check-all-curr').find('b').html('L');
		}else{
			$(this).addClass('check-all-curr').find('b').html('M');
		}	
	})
	
	*/
	//fp
	$('.fp-btn').click(function(){
		if($(this).hasClass('fp-btn-check')){
			$(this).removeClass('fp-btn-check').find('b').html('L');
		}else{
			$(this).addClass('fp-btn-check').find('b').html('M');
		}	
	})
	
	//sort
	$('.list-tag li').click(function(){
		$(this).addClass('current').siblings().removeClass('current');	
	})
	
	//show list
	$('#show-list').click(function(){
		var oShow = $(this).find('#showbox');
		if($(this).hasClass('show')){
			$(this).removeClass('show');
			oShow.fadeOut();
		}else{
			$(this).addClass('show');
			oShow.fadeIn();
		}
	})
	
	//pay way
	var liCurr = $(".pay-info-box").children('li');
	liCurr.click(function(){
		 var _th = $(this);
		 if(_th.hasClass('curr')){
			_th.siblings('li').removeClass('curr');	 
		 }else{
			_th.addClass('curr');
			_th.siblings('li').removeClass('curr');	
		 }
	})
	
	//star
	$('.star-bg a').click(function(){
		$(this).addClass('star-active').siblings().removeClass('star-active');
	})
	
	//agree
	$('.agree-btn').click(function(){
		if($(this).hasClass('agree-btn-checked')){
			$(this).removeClass('agree-btn-checked').html('L');	
		}else{
			$(this).addClass('agree-btn-checked').html('M');		
		}	
	})
})


/*注册页的按钮点击效果*/
var numclick=0;
$(".login_agre").on("click","label",function(){
	if($(this).hasClass("checklabel")){
		$(this).removeClass("checklabel").attr();
	}else{
		$(this).addClass("checklabel").attr("checked");
	}
})






