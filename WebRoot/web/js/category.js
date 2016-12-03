var category_visit_url = "http://testrouter.111yao.com";
var category_image_url = "http://testpc.111yao.com/";
$(function(){
	//初始化数据
	initDatas();
	//标签定位
	$("#tab_class_2").addClass("current");
	//搜索产生焦点
	$("#category_search_input").focus(function()
	{
		searchKW();
	});
});

function initDatas()
{
	$.ajax
	({
		type: "GET",
		url: category_visit_url + "/sltRouter?method=getProductCategory",	 
		data:{"type":"wap", "dataType":"wap"},
		async:false, 
		dataType: "jsonp",
		jsonp: "jsoncallback",
		success: function(data)
		{
			var lg = data.list.length;
			if(lg > 0)
			{
				var lis = "";
				var $_1_ui = "";
				for(var i=0; i<lg; i++)
				{
					var $_1_ui_1 = "<div class='lev2_class' id='cate_lev2_div_"+i+"'>";
					var $_1_ui_2 = "</div>";
					//一级分类
					var obj = data.list;
					var name = obj[i].name;
					lis += "<a name='cate_lev1_"+i+"' rel='"+i+"'><li class='''>"+name+"</li><input type='hidden' value='"+obj[i].pid+"'/></a>";
					
					//二级分类
					var subData = obj[i].plist;
					var subLg = subData.length;
					var $_2_ui = "";
					var $_2_ui_1 = "";
					var $_2_ui_2 = "";
					var $_3_ui = "";
					for(var j=0; j<subLg; j++)
					{
						var _o = subData[j];
						var _img = category_image_url + _o.imgs;
						var _dec = _o.describe;
						var _name = _o.name;
						var _pid = _o.pid;
						//三级分类
						var _subData = _o.plist;
						var _subLg = _subData.length;
						
						$_2_ui_1 = 
						"<section class='sort_cont_list'>"+
	                    "<div class='classify_ban'>"+
	                    "<img src='"+_img+"'/>"+
	                    "<div class='classify_ban_text'>"+
	                    "<span>"+_name+"</span>"+
	                    "<p>"+_dec+"</p>"+
	                    "</div>"+
	                    "</div>";
	                    $_2_ui_2 = "</section>";
						//数据整合
						var $_3_ui_1 = 
						"<div class='classify-main-list'>"+
                        "<ul class='clearfix'>";
						var $_3_ui_2 = "";
                        var $_3_ui_3 = "</ul></div>";
						for(var n=0; n<_subLg; n++)
						{
							var $o = _subData[n];
							var $img = $o.imgs;
							var $name = $o.name;
							var $pid = $o.pid;
							var $dec = $o.describe;
							$_3_ui_2 += "<li><a href='/subcategory.html?cId="+$pid+"'>"+$name+"</a></li>";
						}
						$_3_ui += ($_2_ui_1 + ($_3_ui_1 + $_3_ui_2 + $_3_ui_3) + $_2_ui_2);
					}
					$_1_ui += ($_1_ui_1 + $_3_ui + $_1_ui_2);
				}
				$("#sub_category_ui").html($_1_ui);
				$("#parent_category_ui").append(lis);
			}
			//动态引入JS文件
			var script=document.createElement("script");  
			script.setAttribute("type", "text/javascript");  
			script.setAttribute("src", "/web/js/category.min.js");  
			$("body").append(script);
		},
		error: function(e)
		{
			console.log("e=["+e+"]");
		}
	});   
}

//点击分类请求数据
function getDatas(pid, a)
{
	$(".mask-ui").show();
	$("#loadimg").show();
	console.log("pid="+pid + " a="+a);
	$.ajax
	({
		type: "GET",
		url: category_visit_url + "/sltRouter?method=getSubProductCategory",	 
		data:{"type":"wap","pid":pid, "dataType":"wap"},
		async:false, 
		dataType: "jsonp",
		jsonp: "jsoncallback",
		success: function(data)
		{
			var g1 = data.list.length;
			console.log("g1="+g1);
			if(g1 > 0)
			{
				var s2 = "";
				for(var i=0; i<g1; i++)
				{
					var o1 = data.list;
					var o2 = o1[i];
					var g2 = o2.plist.length;
					console.log("g2="+g2);
					var d2 = o2.plist;
					//二级分类数据
					var img = category_image_url + o1[i].imgs;
					var name = o1[i].name;
					var describe = o1[i].describe;
					var s2_1 =
						"<section class='sort_cont_list'>"
						+"<div class='classify_ban'>"
						+"<img src='"+img+"'/>"
						+"<div class='classify_ban_text'>"
						+"<span>"+name+"</span>"
						+"<p>"+describe+"</p>"
						+"</div>"
						+"</div>";
					var s2_2 = "";//"<div class='classify-part-list clearfix' id='arrow_erea'><img src='http://img.zdfei.com/static/image/temp/20160715/ef3f3e8a3c2bd35c1d7d96346bfcb322.png'/></div>";
					var s2_3 = "";
					var s2_4 = "</section>";
					//三级分类数据
					var s3_1 = "<div class='classify-main-list'><ul class='clearfix'>";
					var s3_2 = "";
					var s3_3 = "</ul></div>";
					for(var j=0; j<g2; j++)
					{
						var o3 = d2[j];
						s3_2 += "<li><a href='/subcategory.html?cId="+o3.pid+"'>"+o3.name+"</a></li>";
					}
					s2_3 = (s3_1 + s3_2 + s3_3);
					s2 += (s2_1 + s2_2 + s2_3 + s2_4);
				}
				//console.log("二级三级分类数据：" + s2);
				$("#cate_lev2_div_"+a).html(s2);
			}
			$(".mask-ui").hide();
			$("#loadimg").hide();
		},
		error: function(e)
		{
			$(".mask-ui").hide();
			$("#loadimg").hide();
			console.log("e=["+e+"]");
		}
	});   
}

function searchKW() 
{
	var script=document.createElement("script");  
	script.setAttribute("type", "text/javascript");  
	script.setAttribute("src", "/web/js/iscroll.js");  
	$("body").append(script);
	$(".search_include_ui").show();
	$("#search-value-ui-id").focus();
	//热搜词初始化滚动事件
	new IScroll('.wrapper3', {eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false});
}

