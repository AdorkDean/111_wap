var client_key = "111yao";
var jsCtx = "";
function myback(){
	var backurl = $('#backurl').val();
	if(backurl==""){
		backurl = encodeURIComponent("http://"+window.location.host);
	}
    window.location.href=decodeURIComponent(backurl);
}



function gocart(){
	var cururl = window.location.href;
	window.location.href="/carts/cart!page.action?url="+cururl;
}

$(document).ready(function (){
	
	
	// 编辑按钮(记着加功能连接 )
	$('.top-right-btn').click(function(){	
		
		var bool = $('#center-btn').hasClass("delete-btn");
		
		var centent = $('#bianji').text();
		
		if(centent=='编辑'){
			$('#bianji').text("完成");
			$("a[name='xabtn']").show();  // 单个删除按钮
		}
		
		if(centent=='完成'){
			$('#bianji').text("编辑");
			$("a[name='xabtn']").attr("style","display:none;"); // 单个删除按钮
		}
		checkEmpty();
		
	})
	
	
	$("input[name='kuang']").focusout(function () {
		
		
		 var patrn=/^[0-9]{1,20}$/; 
		 var bakval = $(this).parent().find('div'); 
		 var ary = bakval.text().split("_");
		 var itemid = ary[1];
		 var oldval = ary[0];
		 var goodId = $(this).attr("gid");
		 
		 var kuang_val = $(this).val();
		 if(!patrn.exec($.trim(kuang_val))){
			 $(this).attr("value",oldval);
			 return ;
		 }
		 
		 if(kuang_val<=0){
			 $(this).val("1");
			 kuang_val=1;
		 }
		 if(parseInt(kuang_val)>5000){
			 $alert("warn","最多添加5000个","111医药馆提示您",null);
			 $(this).val(oldval);
			 return false;
		 }
		 update(itemid,goodId,3,kuang_val);

	})
	
	
});

function shortBug(id){
	var rs = add_cart(id,1);
    if(rs>0){
	// window.location.href="/member/order!toOrderAdd.action";
	 window.location.href="/carts/cart!page.action?url="+window.location.href;
	 return false;
   }else{
	   mywarn(rs);
   }
}

//获取购物车数量
function getCartSum(n)
{
	var url = "/carts/cart!getCartSum.action?r="+new Date();
    jQuery.ajax
    ({
       type: "post",
       url: url,	 
       async:false, 
       success: function(data)
       {
    	   if(data>99)
    	   {
    		   $('.count-msg').html("99+");
    	   }
    	   else
    	   {
    		   $('.count-msg').html(data);
    	   }
    	   if(parseInt(data) > 0)
    	   {
    		   $(".count-msg").fadeIn();
    	   }
       }
    }); 
}

function batchRemove(){
	
	
	var str = "-1000";
	var obj = $("a[name='item-checkbox']");
	
	obj.each(function(){
		flag = $(this).hasClass('check-pro-curr');
		if(flag==true){
			str = str + ","+ this.id ;
		}
	});
	
	if(str.length<=5){
		$alert("warn","没有选中的商品");
		return;
	}
	 
	var ary = str.split(",");
   jQuery.ajax
   ({
       type: "post",
       url: jsCtx+"/carts/cart!batchDelete.action",	  
       data:{"str":str},
       success: function(data)
       {
    	  if(data>0){
    		  //alert("删除成功");
    		  for(var i=0;i<ary.length;i++){
    			  $('#record_'+ary[i]).remove();
    		  }
    		  $('#bianji').text("编辑");
    		  checkEmpty();
    		  isEmpty();
    	  }else{
    		  $alert("error","删除失败");
    	  }
    	  
    	   
       }
   }); 
   
   
}




function add(id){
	
	var img = $("#goodaimg").val();
	var name = $("#goodsName").val();
	var price = $("#pcPrice").val();
	var id = $("#gid").val();
	$('#mprice').text(price);
	$('#mpic').attr("src",img);
	
	var count = $('.count-text').val();
	
	$('#mcnt').text(count);
	
	var rs = add_cart(id,count);
   if(rs>0){
	   $('#shade').show();
   }else{
	   mywarn(rs);
	   return false;
   }
	
}


function islogin(){
	  jQuery.ajax
	   ({
	       type: "post",
	       url: jsCtx+"/carts/cart!isLogin.action",	  
	       success: function(data)
	       {
	    	  
	    	  if(data>0){
	    	  	$('.cart-login-tips').hide();
	    	  }else{
	    	  	$('.cart-login-tips').show();
	    	  }
	       }
	   }); 
}



function checkEmpty(){
	
	
	var centent = $('#bianji').text();
	var btn = $('#center-btn'); 
	

	
	if(centent=='编辑')
	{
		
		var flag = false;
		var n = 0;
		var len = $("a[name='item-checkbox']").each(function(){
			flag = $(this).hasClass('check-pro-curr');
			if(flag==true){
				n++;
			}
		});
		
		$('#center-btn').text("结算");
		$('#qubie').show();
		btn.removeClass("delete-btn");
		
		if(n==0){
			btn.addClass("settle-gray-btn");
			$('#center-btn').attr("onclick","mynull()");
			
		}else{
			btn.removeClass("settle-gray-btn");
			$('#center-btn').attr("onclick","accounts()");
		}
		
		$("a[name='xabtn']").hide(); // 单个删除按钮
		
	}
	
	if(centent=='完成'){
		
		$('#center-btn').addClass("delete-btn");
		$('#qubie').hide();
		$('#center-btn').text("删除");
		$('#center-btn').attr("onclick","batchRemove()");
		$("a[name='xabtn']").attr("style","display:;"); // 单个删除按钮
		
	}
	
	
	
}

function back(){
	var backurl = $('#backurl').val();
	window.location.href=backurl;
}

function mynull(){

	return;
}

function accounts(){
	window.location.href=jsCtx+"/member/order!toOrderAdd.action";
}

function add_cart(gid,cnt){
	
	
   var status = 0;
   jQuery.ajax
   ({
       type: "post",
       url: jsCtx+"/carts/cart!addCart.action",	  
       data:{'gid':gid,'cnt':cnt},
       async:false,
       success: function(data)
       {
    	   
    	   var s=jQuery.parseJSON(data);
    	   status = s.status;
//    	   mywarn(status)
    	   if(s.status>0)
    	   {
    		  //  getCartSum(); 
    	   }
       }
   }); 
   
   return status;

}

function money(){
	
	jQuery.ajax
   ({
       type: "post",
       url: jsCtx+"/carts/cart!money.action",	  
       success: function(data)
       {
    	 
    	   var s=jQuery.parseJSON(data);
    	   $('#money').text(s.money.toFixed(2));
    	   $('#jifen').text((s.money/2).toFixed(0));
    	   
       }
   }); 
}

function update(cartid,goodsid,flag,count){
	var old_val=  $("#count_input_"+cartid).val();
	  if(flag==1){
		  if(old_val==999)return;
		  $("#count_"+cartid).text(parseInt(old_val)+1);
		  $("#count_input_"+cartid).val(parseInt(old_val)+1);
	  }else if(flag==2){
		  if(old_val==1)return;
		  $("#count_"+cartid).text(parseInt(old_val)-1);
		  $("#count_input_"+cartid).val(parseInt(old_val)-1);
		 
	  }
	 if(flag=="1"&&(parseInt(old_val)+count>5000)){
		 $("#count_input_"+cartid).val(old_val);
		 $alert("warn","最多添加5000个","111医药馆提示您",null);
		 return false;
	 }
	 jQuery.ajax
	   ({
	       type: "post",
	       url: jsCtx+"/carts/cart!update.action",	  
	       async:false,
	       data:{"cartid":cartid,"goodsid":goodsid,"flag":flag,"count":count},
	       success: function(data)
	       {
	    	   var s=jQuery.parseJSON(data);
	    	  
	    	   if(s.data>0)
	    	   {
	               $('#heji_'+cartid).text((parseFloat($('#price_'+cartid).text())*$("#count_"+cartid).val()).toFixed(2));
	               money();
	               
	    	   }else{
	    		   $("#count_input_"+cartid).val(s.cnt+"");
	    		   mywarn(s.data);
	    	   }
	       }
	   }); 
}



function collect(cartid,goodsid){
	
   jQuery.ajax
   ({
       type: "post",
       url: jsCtx+"/carts/cart!collect.action",	  
       data:{"cartid":cartid,"goodsid":goodsid},
       success: function(data)
       {
          if(data>0){
        	  $('#record_'+cartid).remove();
          }
       }
   }); 
}

function del(id){
	
   jQuery.ajax
   ({
       type: "post",
       url: jsCtx+"/carts/cart!del.action",	  
       data:{"id":id},
       success: function(data)
       {
    	   //alert(data);
          if(data>0){
        	  $('#record_'+id).remove();
        	  money();
        	  isEmpty();
          }
       }
   }); 
}



$(document).ready(function(){

   $('.check-all').click(function(){
	   
   		var flag = $(this).hasClass('check-all-curr');
   	    var str = "";
       $("a[name='item-checkbox']").each(function(){
       	  str =  str +","+ this.id;
       });
       str = str.substring(1,str.length);
       var x = 0;
         if(flag){
        	 $(this).removeClass('check-all-curr').find('b').html('L');
         }else{
             x = 1;
             $(this).addClass('check-all-curr').find('b').html('M');
         }
         
        jQuery.ajax
	   ({
	       type: "post",
	       url: jsCtx+"/carts/cart!allChecked.action",	  
	       data:{"x":x,"id":str},
	       success: function(data)
	       {
	          if(data>0){
	          	 if(flag){
		         	 $("a[name='item-checkbox']").removeClass('check-pro-curr').html('L');	
		         }else{
		         	 $("a[name='item-checkbox']").addClass('check-pro-curr').html('M');	
		         }
	          	 money();
	          	 checkEmpty();
	          }
	         
	       }
	   }); 
      
   });
   
    $("a[name='item-checkbox']").click(function() {
    	
         var flag = $(this).hasClass('check-pro-curr');
        
         var x = 0;
         if(flag){
        	 $(this).removeClass('check-pro-curr').html('L');	
         }else{
             x = 1;
             $(this).addClass('check-pro-curr').html('M');	
         }
        
	    jQuery.ajax
	   ({
	       type: "post",
	       url: jsCtx+"/carts/cart!checked.action",	  
	       data:{"x":x,"id":this.id},
	       success: function(data)
	       {
	          if(data>0){
	          	 if(flag){
		         	 $(this).addClass('checked-curr');
		         }else{
		         	 $(this).removeClass('checked-curr');
		         }
	          	 money();
	          	 checkEmpty();
	          }
	         
	       }
	   }); 
	    checkedStatus();
        
    });
});

function isEmpty(){
	var itemCnt= $("a[name='item-checkbox']").length;
	if(itemCnt==0){
		window.location.reload();
	}
}


function checkedStatus(){
	
	 var a1= $("a[name='item-checkbox']").length;
	    var n = 0;
	    var len = $("a[name='item-checkbox']").each(function(){
			flag = $(this).hasClass('check-pro-curr');
			if(flag==true){
				n++;
			}
		});
		  
		if(a1!=n){
			$('.check-all').removeClass('check-all-curr').find('b').html('L');
		}else{
			$('.check-all').addClass('check-all-curr').find('b').html('M');
		}
}

function mywarn(rs){
	//:-1:处方药;-2:库存不足;-3:已下架;-4:您已购买该促销商品的数量超过限购数量;-5:促销,超过购买数量
   if(rs>0){
	   $alert("success","商品已成功加入购物车!");
   }else if(rs==-2){
	  $alert("warn","库存不足!");
	  return false;
   }else if(rs==-5){
	  $alert("warn","该商品正促销中，超出了购买数量!");
	 return false;
   }else if(rs==-1){
	   $alert("warn","该商品为处方药，请咨询购买!");
	   return false;
   }else if(rs==-3){
	   $alert("warn","该商品为下架商品","111医药馆提示您!");
	   return;
   }else if(rs==-4){
	   $alert("warn","您已经购买过该促销商品了!");
   }else if(rs<=0){
	  $alert("error","操作失败!");
	  return false;
   }
}



var uuid = "";
var system = "";
var sessionid = "";
var app_wap="";
function common(jsondata)
{
	 var json = JSON.parse(jsondata);
     uuid = json.uuid;
     sessionid = json.sessionid;
}

/**
 * 功能：移动端加入购物车
 * 参数：flag=1是单品、flag=2是套餐、id商品或套餐ID
 * 作者：LGP&LXL&LJL&WWF
 */
function addTao(flag,id)
{
	 app_wap = GetQueryString();
	 if(app_wap=='a')
	 {
		var u = navigator.userAgent, app = navigator.appVersion;
		//android终端或者uc浏览器
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; 
		//ios终端
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); 
		if(isAndroid)
		{
			system = "android";
		}
		else if(isiOS)
		{
			system = "ios";
		}
		else
		{
		}
		
		if(system=="android")
		{
			contact.common();	
		}
		else if(system=="ios")
		{
			document.location = "type*1";
		}
	 }
	window.setTimeout(function(){
		addTaoStep(flag,id);
	},100); 
}

/**
 * 功能：移动端加入购物车
 * 参数：flag=1是单品、flag=2是套餐、id商品或套餐ID
 * 作者：LGP&LXL&LJL&WWF
 */
function addTaoStep(flag,id)
{
	if(app_wap == "a")
	{
		var s = "uuid="+uuid+"system="+system+"sessionid="+sessionid;
		console.log("s="+s);
		jQuery.ajax
		({
			type: "GET",
			url: "http://testrouter.111yao.com/sltRouter?method=addGoodsToCartByLGP",	 
			data:{'sessionid':sessionid,'uuid':uuid,'productid':id,'count':'1','flag':flag},
			async:false, 
			dataType: "jsonp",
			jsonp: "jsoncallback",
			success: function(data)
			{
				  if(flag==2)
				  {
					   if(data.statusCode==1)
					   {
						   if(system=="ios")
						   {
							   $alert("success","商品已成功加入购物车!");
						   }
						   else if(system=="android")
						   {
							   contact.toast("商品已成功加入购物车!");
						   }
					   }
					   else if(data.statusCode==2)
					   {
						   if(system=="ios")
						   {
							    if(!window.confirm("部分商品添加成功!\n以下商品已售罄\n"+data.name))
							    {
								  return;
								}
							    else
							    {
									document.location = "type*3";
								}
						   }
						   else if(system=="android")
						   {
							   contact.toast("部分商品添加成功!\n以下商品已售罄\n"+data.name);
						   }
						  
					   }
					   else if(data.statusCode==3)
					   {
						   if(system=="ios")
						   {
							   $alert("success","加入购物车失败！该套餐中的商品已经全部售罄!");
						   }
						   else if(system=="android")
						   {
							   contact.toast("加入购物车失败！该套餐中的商品已经全部售罄!");
						   }
					   }
					   
				  }
				  //单品提示
				  else
				  {	
					   var rs = data.statusCode;
					   if(system=="ios")
					   {
						   mywarn(rs);
					   }
					   else if(system=="android")
					   {
						   if(rs>0)
						   {	
								contact.toast("商品已成功加入购物车!");
						   }
						   else if(rs==-2)
						   {
							  contact.toast("加入购物车失败，库存不足！");
							  return false;
						   }
						   else if(rs==-5)
						   {
							  contact.toast("加入购物车失败，该商品正促销中，超出了购买数量");
							 return false;
						   }
						   else if(rs==-1)
						   {
							   contact.toast("加入购物车失败，该商品为处方药，请咨询购买");
							   return false;
						   }
						   else if(rs==-3)
						   {
							    contact.toast("商品为下架商品");
							   return;
						   }
						   else if(rs==-4)
						   {
							    contact.toast("您已经购买过该促销商品了");
						   }
						   else if(rs==0)
						   {
							  contact.toast("加入购物车失败！");
							  return false;
						   }
					   }
				  }
				  if(system=="ios")
				  {
					  document.location = "type*5*1";
				  }
				  else if(system=='android')
				  {
					  contact.addCart();
				  }
			}
		});   
	}
	else
	{
	   jQuery.ajax
	   ({
	       type: "post",
	       url: jsCtx+"/carts/cart!addTao.action",	  
	       data:{'gid':id,'count':'1','flag':flag},
	       success: function(data)
	       {
	           var s=jQuery.parseJSON(data);
	           if(flag==2)
	           {
	        	   if(s.statusCode==1)
	        	   {
	        		   $alert("success","商品已成功加入购物车!");
		           }
	        	   else if(s.statusCode==2)
	        	   {
	        		   $alert("success","部分商品添加成功！\n 以下商品已售罄\n"+s.name);
		           }
	        	   else if(s.statusCode==3)
	        	   {
	        		   $alert("success","对不起!该套餐中的商品已经全部售罄");
		           }
	           }
	           else
	           {
	        	   mywarn(s.statusCode);
	           }
	           getCartSum(2);
	       }
	   }); 
	}
}


function GetQueryString()
{	
	var patrn=/^[0-9]{1,20}$/; 
	var href = location.href;
	var a = href.indexOf("-a.html");
	var w = href.indexOf("-w.html");
	if(a>0){
		return 'a';
	}
	if(w>0){
		return 'w';
	}
}


function staticadd(id,count){
	var url=window.location.search;
	var s=url.substring(1, 2);
	if(s!=null&&s=="2"){
		window.location.href="/p/"+id+".html";
	}else{
		var rs = add_cart(id,count);
		if(rs>0){
			$alert("success","操作成功","111医药馆提示您",null);
			getCartSum(2);
		}else{
			mywarn(rs);
			return false;
		}
	}
}





function validateMaxMoney(obj) {
	// 先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d]/g, "");
	// 必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^/g, "");
	// 保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g, ".");
	// 保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",
			".");
//	if (obj.value.indexOf(".") != -1) {
		var subVar = obj.value.substring(obj.value.indexOf(".") + 1,
				obj.value.length);
		if (subVar.length > 6) {
			obj.value = obj.value.substring(0, obj.value.length
					- (subVar.length - 6));
		}
//	}
}

