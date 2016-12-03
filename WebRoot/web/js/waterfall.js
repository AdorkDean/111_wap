var pageNo = 2;
var pageSize = 10;
var flag1 = true;
$( window ).on( "load", function(){
    waterfall('main','pin');
    window.onscroll=function(){
if(checkscrollside()&&flag1){
    		$("#loadimg").show();
	    	jQuery.ajax({
	 	       type: "post",
	 	       url: "/leader/leaderArticle!leaderImgList.action?pageNo="+pageNo+"&pageSize="+pageSize,
	 	       success: function(data){
	    		pageNo += 1;
	    		if(data.list.length>0){
	    			$.each( data.list, function( index, value ){
	    				var $oPin = $('<div>').addClass('pin').appendTo( $( "#main" ) );
	    				var $oBox = $('<div>').addClass('box').appendTo( $oPin );
	    				$('<img>').attr('src',"http://img.zdfei.com/"+value.imgurl).width($(window).width()/2-1).appendTo($oBox);
	    			});
	    			$(".box img").click(function(){
	    				imgUrl = $(this).attr("src");
	    				$('.black-cover').show();
	    				$('.img-yl img').attr("src",imgUrl);
	    	        })
	    		}else{
	    			flag1 = false;
	    		}
	    		window.setTimeout(function(){
	    			$("#loadimg").hide();
	    		},500);
	 	       },error:function(){
	 	       		$alert('error','网络异常!');
	 	       }
	 	   }); 
	        waterfall();
    };
    }
});
/*
    parend 父级id
    pin 元素id
*/
function waterfall(parent,pin){
    var $aPin = $( "#main>div" );
    var iPinW = $aPin.eq( 0 ).width();// 一个块框pin的宽
    var num = Math.floor( $( window ).width() / iPinW );//每行中能容纳的pin个数【窗口宽度除以一个块框宽度】
    //oParent.style.cssText='width:'+iPinW*num+'px;ma rgin:0 auto;';//设置父级居中样式：定宽+自动水平外边距
    $( "#main" ).css({
        'width:' : iPinW * num,
        'margin': '0 auto'
    });

    var pinHArr=[];//用于存储 每列中的所有块框相加的高度。

    $aPin.each( function( index, value ){
        var pinH = $aPin.eq( index ).height();
        if( index < num ){
            pinHArr[ index ] = pinH; //第一行中的num个块框pin 先添加进数组pinHArr
        }else{
            var minH = Math.min.apply( null, pinHArr );//数组pinHArr中的最小值minH
            var minHIndex = $.inArray( minH, pinHArr );
            $( value ).css({
                'position': 'absolute',
                'top': minH,
                'left': $aPin.eq( minHIndex ).position().left
            });
            //数组 最小高元素的高 + 添加上的aPin[i]块框高
            pinHArr[ minHIndex ] += $aPin.eq( index ).height();//更新添加了块框后的列高
        }
    });
}
function isBottom(i)
{
	var windowHeight = $(i).height();
	var scrollTop = $(i).scrollTop();
	var scrollHeight = $(document).height();
	if(((scrollTop + windowHeight) == scrollHeight) && flag)
	{
		return true;
	}
	return false;
}
function checkscrollside(){
    var $aPin = $( "#main>div" );
    var lastPinH = $aPin.last().get(0).offsetTop + Math.floor($aPin.last().height()/2);//创建【触发添加块框函数waterfall()】的高度：最后一个块框的距离网页顶部+自身高的一半(实现未滚到底就开始加载)
    var scrollTop = $( window ).scrollTop()//注意解决兼容性
    var documentH = $( document ).height();//页面高度
    return (lastPinH < (scrollTop + documentH) ) ? true : false;//到达指定高度后 返回true，触发waterfall()函数
}
