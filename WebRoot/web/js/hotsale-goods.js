/** 
 * 推荐商品数据加载[猜你在找]
 * @author LGP
 * @date 2015-09-17
 */
var visitUrl = "http://app.portal.com:8083/";
$(function()
{
	var url = visitUrl + "/sltRouter?method=getRecommendGoods&platform_type=2";
	$.ajax(
	{  
    	type: "GET",               
        url: url,  
        async:false, 
		dataType: "jsonp",
		jsonp: "jsoncallback",
        success: function(data)
        {  
        	var lg = data.rlist.length;
        	if(lg > 0)
        	{
        		var o = data.rlist;
	        	var lis = "";
	        	for(var i=0; i<lg; i++)
	        	{
	        		 var id = o[i].pid;
	        		 var price = o[i].price;
	        		 var image = "/web/images/300.jpg";
	        		 if(o[i].image != '' && o[i].image != null)
	        		 {
	        			 image = imgUrl + o[i].image;
	        		 }
	        		 var name = o[i].name;
	        		 lis += 
                     "<li>"
                	 +"<a href='/m/"+id+".html'>"
                	 +"<img src='"+image+"' style='width:85px;height:85px;'>"
                	 +"<p>"+name+"</p>"
                	 +"<p class='guess-price' style='font-family:Microsoft Yahei'>￥"+price+"</p>"
                	 +"</a>"
                	 +"</li>";
	        	}
	        	$("#scroller ul").append(lis);
        	}
        },
        error: function(e){console.log("e=["+e+"]");}  
	 });
	
});