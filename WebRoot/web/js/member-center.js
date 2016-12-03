$(function(){
    $(".case-help").click(function(){
        if($(this).hasClass('case-help-current')){
            $(this).removeClass('case-help-current');
        }else{
            $(this).addClass('case-help-current');
        }
    });
});

$(function(){
    $(".select").click(function(){
    	if($(this).hasClass('select-current')){
            $(this).removeClass('select-current');
            $(this).find("strong").html('L');
            $(this).siblings().find("strong").html('M');
        }else{
            $(this).addClass('select-current').siblings().removeClass('select-current');
            $(this).find("strong").html('M');
            $(this).siblings().find("strong").html('L');
        }
    	var id = $.trim($(this).find(".rid").val());
    	window.location.href = "/member/receiver!edit.action?id="+id;
    });
});

