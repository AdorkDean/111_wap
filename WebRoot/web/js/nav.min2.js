var showRel = 0;
var resetScrollEle = function()
{
    var a = $("#cate_lev2_div_" + showRel + ">li").find("a").eq(0).width() + "px";
    $(".cate_icon_img").css(
    {
        width: a,
        height: a
    });
};

var cSrc = function(a) 
{
    a.each(function(a, b) 
    {
        var c = $(b).attr("data-src"),
        d = $(b).attr("src");
        c !== d && $(b).attr("src", c);
    });
};

$(function()
{
	var nav = $("#content_div ul>a");
	nav.eq(0).trigger("click");
	nav.each(function(i) 
	{
		$(this).click(function()
		{
			$(this).children("li").addClass("on");
			$(this).siblings().children("li").removeClass("on");
			var a = $(this).attr("rel");
			showRel = a;
			a = $("#cate_lev2_div_" + a);
			a.show().siblings().hide();
			cSrc(a.find("img"));
			resetScrollEle();
		});
	});
});


