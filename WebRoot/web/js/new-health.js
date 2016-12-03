// JavaScript Document
$(function(){
	//footer scroll
//	if($(window).scrollTop() == $(document).height() - $(window).height()){
//		$('.new-health-footer').animate({bottom:'0' },200);
//	}
//	$(window).scroll(function(){
//		if($(window).scrollTop() == $(document).height() - $(window).height()){
//			$('.new-health-footer').animate({bottom:'0' },200);
//		}else{
//			$('.new-health-footer').animate({bottom:'-55px' },200);	
//		}
//	});	
	$('.accept-rules i').click(function(){
		if($(this).hasClass('agree')){
			$(this).removeClass('agree');	
		}else{
			$(this).addClass('agree');	
		}
	})
	/*cancle search*/
	$('.new-health-search-bar input').focus(function(){
		$('.cancel-search').show();	
	})
	$('.new-health-search-bar input').blur(function(){
		$('.cancel-search').hide();	
	})
});