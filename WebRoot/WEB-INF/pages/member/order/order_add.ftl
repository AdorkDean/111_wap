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
    <title>结算</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
	<style>
	   .single-select-order{float:left; margin:10px 5px 0 0; width:50px;max-width:50px;}
	   .brand_sale .capital{border:none; font-weight:normal;}
        .brand_cont{top:45px; position: absolute;}
    .street-list li i{position: absolute; top:50%; left:-20px; margin-top:-11px; display: block; height:21px; width:14px; line-height:18px; text-align:center; background: url("${base}/web/images/street_icon02.png") no-repeat; background-size:14px; color:#FFF; font-size:12px; font-style: normal;}
	</style>
</head>
<body id="phone">

<form name="orderForm" id="orderForm" method="post">
      <input id="receiverId" name="receiverId" type="hidden" value="<#if receiverId?exists>${receiverId?default('')}</#if>"/>
      <input id="paymentWayId" name="paymentWayId" type="hidden" value="<#if paymentWayId?exists>${paymentWayId?default('')}</#if>"/>
      <input id="deliveryId" name="deliveryId" type="hidden" value="<#if deliveryId?exists>${deliveryId?default('')}</#if>"/>
      <input id="couponCardId" name="couponCardId" type="hidden" value="<#if couponCardId?exists>${couponCardId?default('')}</#if>"/>
      <input id="ifInvoice" name="ifInvoice" type="hidden" value="<#if ifInvoice?exists>${ifInvoice?default('')}</#if>"/>
      <input id="invoiceTitle" name="invoiceTitle" type="hidden" value="<#if invoiceTitle?exists>${invoiceTitle?default('')}</#if>"/>
      <input id="remark" name="remark" type="hidden" value="<#if remark?exists>${remark?default('')}</#if>"/>
</form>

<header class="header">
    <a href="${base}/carts/cart!page.action?url=${base}" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">结算</h2>
</header>
<article class="order-box pb70">
    <div class="settle-address" onclick="receiverListShowNew()">
        <#if receiver?exists>
        	   <p class="receipt-p" id="ajaxReceiver_renmobile">收货人:${receiver.consignee?default('')}<span>${receiver.phone?default('')}</span></p>
               <p class="address-p" id="ajaxReceiver_areaaddress">${receiver.areaName?default('')}&nbsp;&nbsp;${receiver.address?default('')}</p>
        <#else>
               <p class="receipt-p" id="ajaxReceiver_renmobile">请添加收货地址</p>
               <p id="ajaxReceiver_areaaddress"></p>
        </#if>
        <b class="new-s-icon-left"></b>
        <b class="new-s-icon-right"></b>
    </div>
    <!-- 支付方式-->
    <div class="choose-pay-way">
        <h2>选择支付方式</h2>
        <div class="pay-list clearfix" >
            <span class="pay-btn pay-way-current" id="online_1" onclick="selectPayMethod(1)">在线支付</span>
            <span class="pay-btn" <#if hdfkFlag=='0'>style="display:none;"</#if> id="offline_4" onclick="selectPayMethod(4)">货到付款</span>
        </div>
    </div>
    <!-- 商品金额-->
    <div class="shopping-sum">
        <ul>
            <li class="sum-list01"><span id="goodsPrice_span">¥<#if goodsPrice?exists>${currency(goodsPrice?default(''))}</#if></span>商品金额</li>
            <li class="sum-list02 sum-distribution" onclick="ajaxDeliveryWayList()">
                <h2><span id="deliveryName_span"><#if deliveryName?exists>${deliveryName?default('')}</#if></span>配送方式 <b class="new-s-icon-right"></b></h2>
                <p><b>推荐</b>药师到家：执业药师上门，用药指导、健康咨询、身体诊查一网打尽！体验价仅需9.9元！</p>
            </li>
            <li class="sum-list03 sum-coupon" onclick="couponCardSubmit()">
                <h2><span id="couponSize_span"><#if couponSize?exists>${couponSize?default(0)}</#if>张抵用券可用</span>现金抵用券 <b class="new-s-icon-right"></b></h2>
            </li>
            <li class="sum-list04">
                <h2><b>我要留言： </b><input id="remark_test" maxlength="70" id="remark_test"  type="text" placeholder="选填，写下您的留言"/></h2>
            </li>
        </ul>
    </div>
    <!-- 发票信息-->
    <div class="invoice-massage add-current" style="border-bottom:0;">
        <b class="invoice-m-icon invoice-m-no" id="invoice_b_class"></b>
        <h2>
            <span>发票信息</span>
            <input type="text" onfocus="invoiceOnfocus()"  onblur="invoiceOnblur()" id="invoiceTitle_b" maxlength="50" name="invoiceTitle_b" placeholder="个人或公司名称"  />
        </h2>
    </div>
    <!-- 优惠后金额-->
    <div class="coupon-money">
        <ul>
            <li><span id="priceNofreight_span">¥<#if priceNofreight?exists>${currency(priceNofreight?default(''))}</#if></span>优惠后金额</li>
            <li><span id="freight_span">+¥<#if freight?exists>${currency(freight?default(''))}</#if></span>运费</li>
        </ul>
    </div>
    <!-- 总金额-->
    <div class="total-money">
       <p>应付金额 <span id="amountPayable_span">¥<#if amountPayable?exists>${currency(amountPayable?default(''))}</#if></span></p>
        <a href="javascript:createOrder()" class="new-settle-btn" id="order_submit">去结算</a>
    </div>
</article>
<!--弹窗-->
<article class="popup-box">
    <!-- 选择收货地址-->
    <!--
    <div class="select-address click-select">
        <div class="address-content click-address">
            <header class="address-header">
                <h2 class="address-title">选择收货地址</h2>
                <div class="address-close" onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
            <div class="address-list" >
                <ul id="receiverList_Div_ul_id">
                </ul>
            </div>
            <div class="augment-address" onclick="addReceiver()">
                <i class="augment-icon"></i>
                <span class="augment-tit">新增地址</span>
                <b class="new-s-icon-right"></b>
            </div>
        </div>
    </div>
    -->
    <!-- 编辑收货地址-->
    <!--
    <div class="select-address click-select-edit">
        <div class="address-content click-address-edit">
            <header class="address-header">
                <h2 class="address-title" id="xzshdz_h2_id">新增收货地址</h2>
                <div class="address-close" onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
            <form name="receiverForm" id="receiverForm" method="post">
            <input id="memberReceiver_id" name="memberReceiver.id" type="hidden" value=""/>
            <input id="memberReceiver_adcode" name="memberReceiver_adcode" type="hidden" value=""/>
            <input id="memberReceiver_locationAddress" name="memberReceiver.locationAddress" type="hidden" value=""/>
            <input id="memberReceiver_longitude" name="memberReceiver.longitude" type="hidden" value=""/>
            <input id="memberReceiver_latitude" name="memberReceiver.latitude" type="hidden" value=""/>
            <input id="memberReceiver_areaId" name="memberReceiver_areaId" type="hidden" value=""/>
            <input type="hidden" name="city_name_set" id="city_name_set"  value=""/>
            <ul class="edit-list">
                <li><span>收货人</span><input type="text" id="memberReceiver_receiver" maxlength="10" name="memberReceiver.receiver" placeholder="请输入您的姓名"  class="black"/></li>
                <li><span>手机号</span><input type="tel" id="memberReceiver_mobile" name="memberReceiver.mobile" placeholder="请输入您的手机"  class="black"/></li>
                <li><span>城　市</span><i class="city-click" id="city_li_id" onclick="cityShow('1')">请选择所在城市</i><b class="new-s-icon-right"></b></li>
                <li><span>地　区</span><i class="street-click" id="area_li_id" onclick="mapShow()">小区/大厦/学校/街道</i><b class="new-s-icon-right"></b></li>
                <li><span>详　细</span><input type="text" id="memberReceiver_address"  maxlength="40" name="memberReceiver.address" placeholder="楼层/门牌号等详细信息"  class="black"/></li>
            </ul>
             </form>
            <div class="follow-btn" ><a href="#" onclick="saveOrUpdateReceiver()">保　存</a></div>
        </div>
    </div>
    -->
    <!-- 我的券包-->
    <div class="my-coupon click-my-coupon">
    <input id="couponCardId_user" name="couponCardId_user" type="hidden" value=""/>
        <div class="coupon-content click-coupon-content">
            <header class="address-header">
                <h2 class="address-title">我的券包</h2>
                <div class="address-close" onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
            <div class="address-list" style="padding-bottom: 110px;">
                <ul id="couponList_Div_ul_Id">
                
                </ul>
            </div>
            <div class="fixed-box" style="position:fixed; bottom:0; left:0; right:0; z-index:111; background:#FFF;">
            	<div class="coupon-num-input" style="margin-top:0;">
               	 	<input type="text" id="couponCardNo" name="couponCardNo" onfocus="clearCouponCardSelect()"  placeholder="请输入优惠券编码" class="black"/>
            	</div>
            	<div class="coupon-follow-btn"><a href="#" onclick="userCouponCard()">使　用</a></div>
            </div>
        </div>
    </div>
    <!-- 选择配送方式-->
    <div class="my-coupon distribution-way">
        <div class="coupon-content distribution-content">
            <header class="address-header">
                <h2 class="address-title">选择配送方式</h2>
                <div class="address-close" onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
            <div class="address-list">
                <ul id="deliveryList_Div_ul_Id">
                   
                </ul>
            </div>
        </div>
    </div>
    
    
 <!-- 选择城市-->
    <div class="select-city">
        <div class="select-city-content">
            <header class="address-header" style="top:0; position: absolute;">
                <h2 class="address-title">选择城市</h2>
                <div class="address-close" onclick="closeCity()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
            <section class="brand_cont">
                <section >
                    <dl class="brand_sale">
                        <#list zimuMap?keys as key> 
					      <dt><a class="capital" name="a${key_index+1}" id="a${key_index+1}">${key}</a></dt>
					       <dd class="brand_list">
                            <ul>
                               <#list zimuMap[key] as citylist> 
	                                <li onclick="selectCity('${citylist.citycode}','${citylist.name}')">${citylist.name}</li>
                                </#list>
                            </ul>
                        </dd>
					    </#list>
                    </dl>
                </section>
                <div class="brand_rightlist">
                    <ol>
                    <#list zimuMap?keys as key> 
				          <li><a href="#a${key_index+1}">${key}</a></li>
				    </#list>
                        <li><a href="#">#</a></li>
                    </ol>
                </div>
            </section>
        </div>
    </div>
    <!-- 选择街道-->
    <div class="select-street">
        <div class="select-street-content">
            <header class="header" style="top:0;position: absolute;">
                <span class="iconfont top-left-btn" style="width:62px;" onclick="closeCity()">B</span>
                <h2 class="header-title index-header-title">
                    <div class="search-box" style="background: #f8f8f8; border:1px solid #d7d7d7; margin:6px 62px;">
                        <p class="search-input-box" style="margin:0 5px;"><input class="search-input" type="search" id="search_keyword" oninput="key_search()" onpropertychange="key_search()" placeholder="查找小区/大厦/学校/街道等"/></p>
                    </div>
                </h2>
                <strong class="cancel-btn" style="color:#00b8c9;" onclick="key_search()">搜索</strong>
            </header>
            <!--<aside class="street-info"><p>温馨提示：拖动地图可进行区域选择，点击列表可选择地址</p></aside>-->
            <div class="map" style="height:0px; width:0px;top:50px;" id="container"></div>
            <div class="street-list" style="top:45px">
                <div id="no_search_sd" onclick="showAddressSd()" style="height:20px; line-height:20px; text-align:center; background:#f4f5f7; color:#999;display:none">搜不到我的地址?试试手动添加</div>
                <ul id="map_search_ul_id">
                    
                </ul>
            </div>
        </div>
    </div>   
    
    <!-- 手动添加地址信息-->
    <div class="select-address click-select-edit-sd">
        <div class="address-content click-select-edit-sd">
            <header class="address-header">
                <h2 class="address-title" id="xzshdz_h2_id">手动填写</h2>
                <div class="address-close" onclick="closeSdDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
            <input id="memberReceiver_adcode_sd" name="memberReceiver_adcode_sd" type="hidden" value=""/>
            <ul class="edit-list">
                <li><span>城　市</span><i class="city-click" id="city_li_sd_id" onclick="cityShow('2')">请选择所在城市</i><b class="new-s-icon-right"></b></li>
                <li>
                	<span>区　县</span>
                	 <div class="for-ipt choose-address">
                		<select class="single-click" style="width:150px" id="quxian_select_id" onchange="ajaxSqByAreaId()">
                			<option value="">请选择</option>
                		</select>
                	</div>	
                </li>
                <li style="display:none" id="shangquan_select_id_li">
                	 <span>商　圈</span>
                	 <div class="for-ipt choose-address">
	                	<select class="single-click" style="width:150px" id="shangquan_select_id">
	                		<option value="">请选择</option>
	                	</select>
                	</div>	
                </li>
                <li><span>详　细</span><input type="text" id="memberReceiver_address_sd"  maxlength="40"  placeholder="楼层/门牌号等详细信息"  class="black"/></li>
            </ul>
            <div class="follow-btn" ><a href="#" onclick="addressSdQd()">确　定</a></div>
        </div>
    </div>
    
</article>
</body>
</html>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=51293f48fbe2e1e51de5299446404273&plugin=AMap.PlaceSearch,AMap.Geocoder"></script>
<script type="text/javascript">
//城市来源  1表示从地址进入  2 表示手动填写进入
var city_source='1';
$(function(){
        $('dl').groupList(true);
});
$.fn.groupList = function(selfScroll){
    var list = this;
    var listTopOffset = list.offset().top;
    var titleHeight = $(list).find('dt:first').height();

    var currentContext = selfScroll ? list : window;
    if(selfScroll){
        list.css({
            'height':$(window).height() - listTopOffset,
            'overflow':'auto'
        });
    }
    $(currentContext).on('scroll',function(){
        if($(currentContext).scrollTop()-(selfScroll ? 0 : listTopOffset) < titleHeight){
            clearFixed();
        }else{
            var lis = list.find('li');
            for(var i = 0, len = lis.length ; i < len ; i++){
                if(isOnSight(lis[i])){
                    clearFixed().filter('.clone').remove();
                    lis.eq(i).parent().parent().prev().clone(true).addClass('clone').css({
                        'top': selfScroll ? listTopOffset : 0,
                        'position':'fixed',
                        'width':'100%'
                    }).appendTo(list);
                    break;
                }
            }
        }
    });
    var isOnSight = function(item){
        return selfScroll ? listTopOffset < $(item).offset().top + $(item).outerHeight() : $(currentContext).scrollTop() <  $(item).offset().top + $(item).outerHeight();
    };
    var clearFixed = function(){
        return $(list).find('dt').css({
            'position':''
        });
    }
};
//关闭城市层
function closeCity(){
    $(".select-city").hide();
    $(".select-street").hide();
    $("#no_search_sd").hide();
    $(".select-city-content").animate({right:"-100%"});
    $(".select-street-content").animate({right:"-100%"});
}
//打开手动填写地址层
function showAddressSd(){
   var citycode = $("#memberReceiver_adcode").val();
   $("#memberReceiver_adcode_sd").val(citycode);
   $("#shangquan_select_id").html("");
   $("#memberReceiver_address_sd").val($("#memberReceiver_address").val());
   $("#shangquan_select_id_li").hide();
   ajaxGetAreaByCity(citycode);
   $("#city_li_sd_id").html($("#city_li_id").html());
   $(".click-select-edit-sd").show();
   $('.click-select-edit-sd').css({bottom:"0"});
   closeCity();
}
//根据城市选择区域
function ajaxGetAreaByCity(citycode){
  if(citycode!=''){
      $("#quxian_select_id").html("<option value=\"\">请选择</option>");
     $.ajax({
				url:'${base}/member/order!ajaxGetAreaByCity.action',
				data:{"citycode":citycode},
				type:'post',
				success:function(data){
				    if(data.length > 0){
				      var area_select_one_html="<option value=\"\">请选择</option>";
				       for(var i=0;i<data.length;i++){
				          area_select_one_html+="<option value=\""+data[i].id+"\">"+data[i].name+"</option>";
				       }
				       $("#quxian_select_id").html(area_select_one_html);
				    }
				}
	  });
  }
}

//根据城市选择区域
function ajaxGetAreaByCity(citycode){
  if(citycode!=''){
      $("#quxian_select_id").html("<option value=\"\">请选择</option>");
         $.ajax({
				url:'${base}/member/order!ajaxGetAreaByCity.action',
				data:{"citycode":citycode},
				type:'post',
				success:function(data){
				    if(data.length > 0){
				      var area_select_one_html="<option value=\"\">请选择</option>";
				       for(var i=0;i<data.length;i++){
				          area_select_one_html+="<option value=\""+data[i].id+"\">"+data[i].name+"</option>";
				       }
				       $("#quxian_select_id").html(area_select_one_html);
				    }
				}
	  });
  }
}
//选择地区判断是否有商圈
function ajaxSqByAreaId(){
   var quxian_select_id= $("#quxian_select_id").children('option:selected').val();
   $("#area_sq_span_id").html("");
   $("#shangquan_select_id_li").hide();
        if(quxian_select_id!=''){
		       $.ajax({
				url:'${base}/member/order!ajaxGetTwoAreaByAreaId.action',
				data:{"areaId":quxian_select_id},
				type:'post',
				success:function(data){
				    if(data.length > 0){
				      var area_sq_span_id_html="";
				       for(var i=0;i<data.length;i++){
				          area_sq_span_id_html+="<option value=\""+data[i].id+"\">"+data[i].name+"</option>";
					   }
				       $("#shangquan_select_id").html(area_sq_span_id_html);
				       $("#shangquan_select_id_li").show();
				    }
				  }
			   });
		     } 
}
//手动填写确定
function addressSdQd(){
   if($("#quxian_select_id").val()==""){
         $alert("warn","请选择区县!",null,null);
         return ;
   }
	var memberReceiver_address_sd = $.trim($("#memberReceiver_address_sd").val());
	if (memberReceiver_address_sd == '') {
		$alert("warn","请输入详细地址",null,null);
		return ;
	}
	if($("#shangquan_select_id").val()){
	  $("#memberReceiver_areaId").val($("#shangquan_select_id").val());
	}else{
	  $("#memberReceiver_areaId").val($("#quxian_select_id").val());
	}
   
   $("#memberReceiver_address").val(memberReceiver_address_sd);
   $("#memberReceiver_longitude").val("");
   $("#memberReceiver_latitude").val("");
   if($("#shangquan_select_id").val()){
      $("#memberReceiver_locationAddress").val($("#quxian_select_id").find("option:selected").text()+"-"+$("#shangquan_select_id").find("option:selected").text());
   }else{
     $("#memberReceiver_locationAddress").val($("#quxian_select_id").find("option:selected").text());
   }
   $("#area_li_id").html($("#memberReceiver_locationAddress").val());
   $("#memberReceiver_adcode").val($("#memberReceiver_adcode_sd").val());
   $("#city_li_id").html($("#city_li_sd_id").html());
  closeSdDivCen();
}

//关闭手动填写层弹出层
function closeSdDivCen(){
	 $(".click-select-edit-sd").hide();
	 $('.click-select-edit-sd').css({bottom:"-70%"});
}

//选择城市
function selectCity(adcode,cityname){
    if(city_source=='1'){
        $("#city_li_id").html(cityname);
	    if($("#memberReceiver_adcode").val()!=adcode){
	        $("#memberReceiver_adcode").val(adcode);
		    $("#area_li_id").html("请选择街道地址");
		    $("#memberReceiver_locationAddress").val("");
		    $("#memberReceiver_longitude").val("");
		    $("#memberReceiver_latitude").val("");
		    $("#memberReceiver_address").val("");
		    $("#memberReceiver_areaId").val("");
	    }
    }else{
       if($("#memberReceiver_adcode_sd").val()!=adcode){
           $("#memberReceiver_adcode_sd").val(adcode);
           $("#city_li_sd_id").html(cityname);
           $("#memberReceiver_address_sd").val("");
           $("#shangquan_select_id").html("");
		   $("#shangquan_select_id_li").hide();
           ajaxGetAreaByCity(adcode);
           
       }
    }
    closeCity();
}

//城市弹出层显示
function cityShow(citysource){
   city_source=citysource;
  $(".select-city").show();
  $(".select-city-content").animate({right:"0"});
}


var map ;
var placeSearch;
var  geocoder;
//地图显示
function mapShow(){
    var memberReceiver_adcode = $("#memberReceiver_adcode").val();
    if(memberReceiver_adcode!=''){
        $("#map_search_ul_id").html("");
        $("#search_keyword").val("");
        $(".select-street").show();
	    $(".select-street-content").animate({right:"0"});
	    map = new AMap.Map("container", {
		    resizeEnable: true
		});
		var city_li_id_html =$("#city_li_id").html();
		if(city_li_id_html.split("-").length>1){
		   map.setCity(city_li_id_html.split("-")[1],function() {
           map.setZoom(11);
           });
		}else{
		   map.setCity(city_li_id_html.split("-")[0],function() {
           map.setZoom(11);
          });
		}
		
		var placeSearchOptions = { //构造地点查询类
		    pageSize: 10,
		    pageIndex: 1,
		    city: memberReceiver_adcode, //城市
		    map:map
		};
		
	    placeSearch = new AMap.PlaceSearch(placeSearchOptions);
	    placeSearch.setCity(memberReceiver_adcode);
	    placeSearch.setCityLimit(true);
    }else{
       $alert("warn","请先选择城市!",null,null);
    }
}
//地图搜索
function key_search(){
	    var search_keyword = $("#search_keyword").val();
	    $("#no_search_sd").show();
	    if($.trim(search_keyword)!=''){
	      placeSearch.search(search_keyword, callback);
	    }else{
	      $("#map_search_ul_id").html("");
	    }
}

//使用搜索地址
function userSearchLocationAddress(city_name,locationAddress,longitude,latitude){
   if($("#memberReceiver_longitude").val()!=longitude || $("#memberReceiver_latitude").val()!=latitude){
        $("#memberReceiver_locationAddress").val(locationAddress);
	    $("#memberReceiver_longitude").val(longitude);
	    $("#memberReceiver_latitude").val(latitude);
	    $("#area_li_id").html(locationAddress);
	    $("#memberReceiver_address").val("");
	    $("#memberReceiver_areaId").val("");
	    $("#city_name_set").val(city_name);
   }
     closeCity();
}

//地图搜索回调函数
function callback(status, result) {
    $("#map_search_ul_id").html("");
    if (status === 'complete' && result.info === 'OK') {
	   if(result.poiList&&result.poiList.pois){
	      var pois = result.poiList.pois;
		   if(pois.length>0){
		       var map_search_ul_id_html="";
		       var xuhao =1;
			  for(var i=0;i<pois.length;i++){
				    var pois_address=pois[i].address;
				    var pois_name=pois[i].name;
				    var pname_p =  pois[i].pname;
				    if(pname_p.substring(pname_p.length-1,pname_p.length)=='市'){
				       pname_p= pname_p.substring(0,pname_p.length-1)
				    }
				    var city_name=pname_p+"-"+pois[i].cityname+"-"+pois[i].adname;
			        map_search_ul_id_html+="<li>";
				    map_search_ul_id_html+="<span>"+pois_name+"</span>";
				    map_search_ul_id_html+="<p>"+pois_address+"</p>";
				    map_search_ul_id_html+="<b onclick=\"userSearchLocationAddress('"+city_name+"','"+pois_address+pois_name+"','"+pois[i].location.getLng()+"','"+pois[i].location.getLat()+"')\">使用地址</b>";
				    map_search_ul_id_html+="<i>"+xuhao+"</i>";
				    map_search_ul_id_html+="</li>";
				    xuhao++;
			  }
			    $("#map_search_ul_id").html(map_search_ul_id_html);
		   }else{
			      //$alert("warn","未搜索到相关地址信息",null,null);
			      $("#map_search_ul_id").html("");
		   }  
	   }
    }else{
        //$alert("warn","未搜索到相关地址信息",null,null);
        $("#map_search_ul_id").html("");
    }
}
  

//发票获取焦点事件
function invoiceOnfocus(){
     if($("#invoice_b_class").hasClass('invoice-m-no')){
          $("#invoice_b_class").removeClass('invoice-m-no');
     } 
}
//发票失去焦点事件
function invoiceOnblur(){
   if($.trim($("#invoiceTitle_b").val())==''){
     if(!$("#invoice_b_class").hasClass('invoice-m-no')){
          $("#invoice_b_class").addClass('invoice-m-no');
     }
     $("#ifInvoice").val("0");
   }else{
      $("#ifInvoice").val("1");
   }
}

//创建订单计算运费
function createOrderPrice(){
    var couponCardId = $("#couponCardId").val();// 优惠券id
	var receiverId = $("#receiverId").val();// 收货地址id
	var paymentWayId = $("#paymentWayId").val();// 支付方式
	var deliveryId = $("#deliveryId").val();// 配送方式id
	$.ajax({
		url : "${base}/member/order!getOrderPrice.action",
		type : "post",
		data : {
			"couponCardId" : couponCardId,
			"paymentWayId" : paymentWayId,
			"receiverId" : receiverId,
			"deliveryId" : deliveryId
		},
		success : function(data) {
			$("#deliveryId").val(data.deliveryId);
			$("#deliveryName_span").html(data.deliveryName);//配送方式
			$("#goodsPrice_span").html("¥" + data.goodsPrice.toFixed(2));//商品金额
			$("#priceNofreight_span").html("¥" + data.priceNofreight.toFixed(2));//优惠后金额
			$("#freight_span").html("+¥" + data.freight.toFixed(2));//运费
			$("#amountPayable_span").html("¥" + data.amountPayable.toFixed(2));//应付金额
			if(data.couponName!=''){
			    $("#couponSize_span").html(data.couponName);//优惠券名称
			}else{
			    $("#couponSize_span").html(data.couponSize+"张抵用券可用");//优惠券名称
			}
		},
		error : function() {
		}
	});
}

    //选择支付方式
    function selectPayMethod(id){
       if(id==1){
           $("#offline_4").removeClass('pay-way-current');
           $("#online_1").addClass('pay-way-current');
       }else if(id==4){
            $("#offline_4").addClass('pay-way-current');
            $("#online_1").removeClass('pay-way-current');
       }
       $("#paymentWayId").val(id);
       createOrderPrice();
       
    }
    
 //选择配送方式
function xuanzeDeliveryWay(deliveryId){
   $("#deliveryId").val(deliveryId);
   createOrderPrice();
   closeDivCen();
}   
    
    
 //动态获取配送方式
function ajaxDeliveryWayList(){
    $("#deliveryList_Div_ul_Id").html("");
	var deliveryId = $("#deliveryId").val();
	var paymentWayId =$("#paymentWayId").val();
	var receiverId = $("#receiverId").val();
	if(receiverId==''){
	  $alert("warn","请先选择收货地址!",null,null);
	  return ;
	}
    $.ajax({
			url : "${base}/member/order!ajaxDeliveryWayList.action",
			type : "post",
			data : {"deliveryId":deliveryId,"paymentWayId":paymentWayId,"receiverId":receiverId},
			success : function(data) {
			    if(data.flag=='1'){
			            var receiverhtml = "";
						for ( var i = 0; i < data.resultList.length; i++) {
						    if(data.resultList[i].isflag=='1'){
							    receiverhtml += "<li onclick=\"xuanzeDeliveryWay("+data.resultList[i].id+")\">";
							    receiverhtml += "<div class=\"add-current\">";
								receiverhtml += "<div class=\"add-li-bd\">";
								receiverhtml += "<div class=\"font-28\">";
								receiverhtml += "<label name=\"coupon-name\" class=\"coupon-name\">"+data.resultList[i].name+"<b>推荐</b></label>";
								receiverhtml += "</div>";
								receiverhtml += "<div  class=\"font-24\">";
								receiverhtml += "<label name=\"\">"+data.resultList[i].instro+"</label>";
								receiverhtml += "</div>";
								receiverhtml += "</div>";
								receiverhtml += "</div>";
								receiverhtml += "<span class=\"red-font\">¥"+data.resultList[i].initPrice.toFixed(2)+"</span>";
								if(data.resultList[i].isflag=='1'){
								    if(deliveryId==data.resultList[i].id){
								      receiverhtml += "<b class=\"invoice-m-icon\" style=\"left:11px;\"></b>";
								    }else{
								      receiverhtml += "<b class=\"invoice-m-icon invoice-m-no\" style=\"left:11px;\"></b>";
								    }
								}else{
								  receiverhtml += "<b class=\"invoice-m-icon invoice-m-no gray-font\" style=\"left:11px;\"></b>";
								}
							   receiverhtml += "</li>";
							   }
						}
						for ( var i = 0; i < data.resultList.length; i++) {
						    if(data.resultList[i].isflag!='1'){
							      receiverhtml += "<li>";
								  receiverhtml += "<div class=\"add-current\">";
								  receiverhtml += "<div class=\"add-li-bd\">";
								  receiverhtml += "<div class=\"font-28\">";
								  receiverhtml += "<label name=\"coupon-name\" class=\"coupon-name\">"+data.resultList[i].name+"<b class=\"no-use\">不可用</b></label>";
									receiverhtml += "</div>";
									receiverhtml += "<div  class=\"font-24\">";
									receiverhtml += "<label name=\"\">"+data.resultList[i].instro+"</label>";
									receiverhtml += "</div>";
									receiverhtml += "</div>";
									receiverhtml += "</div>";
									receiverhtml += "<span class=\"red-font\">¥"+data.resultList[i].initPrice.toFixed(2)+"</span>";
									if(data.resultList[i].isflag=='1'){
									    if(deliveryId==data.resultList[i].id){
									      receiverhtml += "<b class=\"invoice-m-icon\" style=\"left:11px;\"></b>";
									    }else{
									      receiverhtml += "<b class=\"invoice-m-icon invoice-m-no\" style=\"left:11px;\"></b>";
									    }
									}else{
									  receiverhtml += "<b class=\"invoice-m-icon invoice-m-no gray-font\" style=\"left:11px;\"></b>";
									}
						           receiverhtml += "</li>";
						}
						}
			        $("#deliveryList_Div_ul_Id").html(receiverhtml);
			         $(".distribution-way").show();
        			$('.distribution-content').css({bottom:"0"});
			    }
			},
			error : function() {
			}
		});
}   
    
    
//清楚已选择的优惠券
function clearCouponCardSelect(){
   $("#couponCardId_user").val("");
   $("#couponList_Div_ul_Id").children("li").each(function(){
      if(!$($(this).children("b").get(0)).hasClass('invoice-m-no')){
          $($(this).children("b").get(0)).addClass('invoice-m-no');
       } 
    });
}
//选择优惠券
function selectCouponCard(couponCardId){
    $("#couponList_Div_ul_Id").children("li").each(function(){
      if(!$($(this).children("b").get(0)).hasClass('invoice-m-no')){
          $($(this).children("b").get(0)).addClass('invoice-m-no');
       } 
    });
    $("#couponCard_b"+couponCardId).removeClass('invoice-m-no');
    $("#couponCardId").val(couponCardId);
    createOrderPrice();
    closeDivCen();
}



//使用优惠券
function userCouponCard(){
     var couponCardId_user=$("#couponCardId_user").val();
     var couponCardNo = $("#couponCardNo").val(); 
     if(couponCardId_user==''&&$.trim(couponCardNo)==''){
        $alert("warn","请选择或是填写优惠券券码!",null,null);
        return;
     }else if(couponCardId_user!=''){
        $("#couponCardId").val(couponCardId_user);
        createOrderPrice();
        closeDivCen();
     }else if($.trim(couponCardNo)!=''){
          $.ajax({
			url : "${base}/member/order!checkCouponCard.action",
			type : "post",
			data : {
				"couponCardNo" : couponCardNo
			},
			success : function(data) {
				if(data.resultFlag=='1'){
				   $("#couponCardId").val(data.couponCardId);
				   createOrderPrice();
				   closeDivCen();
				}else if(data.resultFlag=='2'){
				   $alert("warn","请输入优惠券编码!",null,null);
				}else if(data.resultFlag=='4'){
				   shuaxinCouponCardDiv();
				   $alert("warn",data.errorMessage,null,null);
				}else{
				   shuaxinCouponCardDiv();
				   $alert("warn","该优惠券不符合使用规则,无法使用!",null,null);
				}
			},
			error : function() {
				$alert("error","操作失败，请稍后再试",null,null);
			}
		});
     }
}
function shuaxinCouponCardDiv(){
     $("#couponList_Div_ul_Id").html("");
     $("#couponCardId_user").val("");
     $.ajax({
			url : "${base}/member/order!ajaxOrderCouponList.action",
			type : "post",
			data : {},
			success : function(data) {
			if(data.length>0){
			    var receiverhtml = "";
	            var couponCardId =$("#couponCardId").val();
					for ( var i = 0; i < data.length; i++) {
					   if(data[i].isuse=='1'){
					       receiverhtml+="<li onclick=\"selectCouponCard("+data[i].id+")\">";
					       receiverhtml+="<div class=\"add-current\">";
					       receiverhtml+="<div class=\"add-li-bd\">";
					       receiverhtml+="<div class=\"font-28\">";
					       receiverhtml+="<label name=\"coupon-name\">"+data[i].name+"</label>";
					       receiverhtml+="</div>";
					       receiverhtml+="<div  class=\"font-24\">";
					       receiverhtml+="<label name=\"\">有效期:"+data[i].begin_Date+"至"+data[i].end_date+"</label>";
					       receiverhtml+="</div>";
					       receiverhtml+="</div>";
					       receiverhtml+="</div>";
					       receiverhtml+="<span class=\"red-font\">¥"+data[i].dis_price.toFixed(2)+"</span>";
					       if(couponCardId==data[i].id){
					         $("#couponCardId_user").val(couponCardId);
						     receiverhtml += "<b id=\"couponCard_b"+data[i].id+"\" class=\"invoice-m-icon\" style=\"left:11px;\"></b>";
						   }else{
						     receiverhtml += "<b id=\"couponCard_b"+data[i].id+"\" class=\"invoice-m-icon invoice-m-no\" style=\"left:11px;\"></b>";
						   }
					       receiverhtml+="</li>";
					   }
					}
					for ( var i = 0; i < data.length; i++) {
					   if(data[i].isuse=='0'){
					       receiverhtml+="<li style=\"padding-top:10px;\">";
					       receiverhtml+="<div class=\"add-current\">";
					       receiverhtml+="<div class=\"add-li-bd\">";
					       receiverhtml+="<div class=\"font-28\">";
					       receiverhtml+="<label name=\"coupon-name\">"+data[i].name+"</label>";
					       receiverhtml+="</div>";
					       receiverhtml+="<div  class=\"font-24\">";
					       receiverhtml+="<label name=\"\">有效期:"+data[i].begin_Date+"至"+data[i].end_date+"</label>";
					       receiverhtml+="</div>";
					       receiverhtml+="</div>";
					       receiverhtml+="</div>";
					       if(i==0){
					         receiverhtml+="<span class=\"user-no-coupon\">以下是不可用的优惠券</span>";
					       }
					       receiverhtml+=" <span class=\"red-font black-font\">¥"+data[i].dis_price.toFixed(2)+"</span>";
						   receiverhtml += "<b class=\"invoice-m-icon invoice-m-no gray-font\" style=\"left:11px;\"></b>";
					       receiverhtml+="</li>";
					   }
					}
				 $("#couponList_Div_ul_Id").html(receiverhtml);
             }else{
			 }    
			},
			error : function() {
			}
		});

}
    
//优惠券使用弹出层
function couponCardSubmit(){
     $("#couponList_Div_ul_Id").html("");
     $("#couponCardNo").val("");
     $("#couponCardId_user").val("");
     $.ajax({
			url : "${base}/member/order!ajaxOrderCouponList.action",
			type : "post",
			data : {},
			success : function(data) {
			if(data.length>0){
			    var receiverhtml = "";
	            var couponCardId =$("#couponCardId").val();
					for ( var i = 0; i < data.length; i++) {
					   if(data[i].isuse=='1'){
					       receiverhtml+="<li onclick=\"selectCouponCard("+data[i].id+")\">";
					       receiverhtml+="<div class=\"add-current\">";
					       receiverhtml+="<div class=\"add-li-bd\">";
					       receiverhtml+="<div class=\"font-28\">";
					       receiverhtml+="<label name=\"coupon-name\">"+data[i].name+"</label>";
					       receiverhtml+="</div>";
					       receiverhtml+="<div  class=\"font-24\">";
					       receiverhtml+="<label name=\"\">有效期:"+data[i].begin_Date+"至"+data[i].end_date+"</label>";
					       receiverhtml+="</div>";
					       receiverhtml+="</div>";
					       receiverhtml+="</div>";
					       receiverhtml+="<span class=\"red-font\">¥"+data[i].dis_price.toFixed(2)+"</span>";
					       if(couponCardId==data[i].id){
					         $("#couponCardId_user").val(couponCardId);
						     receiverhtml += "<b id=\"couponCard_b"+data[i].id+"\" class=\"invoice-m-icon\" style=\"left:11px;\"></b>";
						   }else{
						     receiverhtml += "<b id=\"couponCard_b"+data[i].id+"\" class=\"invoice-m-icon invoice-m-no\" style=\"left:11px;\"></b>";
						   }
					       receiverhtml+="</li>";
					   }
					}
					for ( var i = 0; i < data.length; i++) {
					   if(data[i].isuse=='0'){
					       receiverhtml+="<li style=\"padding-top:10px;\">";
					       receiverhtml+="<div class=\"add-current\">";
					       receiverhtml+="<div class=\"add-li-bd\">";
					       receiverhtml+="<div class=\"font-28\">";
					       receiverhtml+="<label name=\"coupon-name\">"+data[i].name+"</label>";
					       receiverhtml+="</div>";
					       receiverhtml+="<div  class=\"font-24\">";
					       receiverhtml+="<label name=\"\">有效期:"+data[i].begin_Date+"至"+data[i].end_date+"</label>";
					       receiverhtml+="</div>";
					       receiverhtml+="</div>";
					       receiverhtml+="</div>";
					       if(i==0){
					         receiverhtml+="<span class=\"user-no-coupon\">以下是不可用的优惠券</span>";
					       }
					       receiverhtml+=" <span class=\"red-font black-font\">¥"+data[i].dis_price.toFixed(2)+"</span>";
						   receiverhtml += "<b class=\"invoice-m-icon invoice-m-no gray-font\" style=\"left:11px;\"></b>";
					       receiverhtml+="</li>";
					   }
					}
				 $("#couponList_Div_ul_Id").html(receiverhtml);
				 $(".click-my-coupon").show();
                 $('.click-coupon-content').css({bottom:"0"});
             }else{
			     $(".click-my-coupon").show();
                 $('.click-coupon-content').css({bottom:"0"});
			 }    
			},
			error : function() {
			}
		});
}  
 //新增收获地址弹出层
function addReceiver(){
   $("#xzshdz_h2_id").html("新增收货地址");
   $("#memberReceiver_id").val("");
   $("#memberReceiver_adcode").val("");
   $("#memberReceiver_locationAddress").val("");
   $("#memberReceiver_longitude").val("");
   $("#memberReceiver_latitude").val("");
   $("#memberReceiver_receiver").val("");
   $("#memberReceiver_mobile").val("");
   $("#memberReceiver_address").val("");
   $("#memberReceiver_areaId").val("");
   $("#city_name_set").val("");
   $("#city_li_id").html("请选择所在城市");
   $("#area_li_id").html("小区/大厦/学校/街道");
   $(".click-select-edit").show();
   $(".click-select").hide();
   $('.click-address-edit').css({bottom:"0"});
   // ajaxClocationList();
}   
 
//修改收获地址弹出层
function updateReceiver(receiverId){
      $("#xzshdz_h2_id").html("编辑收货地址");
      $.ajax({
			url : "${base}/member/order!ajaxReceiver.action",
			type : "post",
			data : {"receiverId":receiverId},
			success : function(data) {
	           if(data!=null&&data!=''){
				   $("#memberReceiver_id").val(data.receiver.id);
				   $("#memberReceiver_adcode").val(data.receiver.citycode);
				   $("#memberReceiver_locationAddress").val(data.receiver.location_address);
				   $("#memberReceiver_longitude").val(data.receiver.longitude);
				   $("#memberReceiver_latitude").val(data.receiver.latitude);
				   $("#memberReceiver_receiver").val(data.receiver.receiver);
				   $("#memberReceiver_mobile").val(data.receiver.mobile);
				   $("#memberReceiver_address").val(data.receiver.address);
				   $("#memberReceiver_areaId").val(data.receiver.area_id);
		           $("#city_name_set").val(data.receiver.area);
				   if(data.receiver.area.split("-").length>1){
				     $("#city_li_id").html(data.receiver.area.split("-")[1]);
				   }else{
				      $("#city_li_id").html(data.receiver.area);
				   }
				   $("#area_li_id").html(data.receiver.location_address);
				   $("#addOrUpdate_Receiver_Address").html("<div onclick=\"areaViewSelect()\" style=\"width:auto;\">"+data.receiver.area+"</div>")
				   $(".click-select-edit").show();
				   $(".click-select").hide();
				   $('.click-address-edit').css({bottom:"0"});
	           } 
			},
			error : function() {
			}
		});
}  
 
//收货地址列表弹出层显示
function receiverListShow(){
     $("#receiverList_Div_ul_id").html("");
     $.ajax({
			url : "${base}/member/order!ajaxReceiverList.action",
			type : "post",
			data : {},
			success : function(data) {
			   if(data.length>0){
			    var receiverhtml = "";
	            var receiverId =$("#receiverId").val();
				for ( var i = 0; i < data.length; i++) {
				  receiverhtml += "<li ><div class=\"add-current\" ><div class=\"add-li-bd\"><div class=\"font-28\">";
				  receiverhtml += "<label name=\"user-name\">"+data[i].receiver+"</label><label name=\"iphone-num\" style=\"float:right;\">"+data[i].mobile+"</label></div>";
				  receiverhtml += "<div  class=\"font-24\">";
				  receiverhtml += "<label name=\"user-address\">"+data[i].area+"&nbsp;&nbsp;"+data[i].location_address+data[i].address+"</label>";
			      receiverhtml += "</div>";
				  receiverhtml += "</div>";
				  
				   if(receiverId==data[i].id){
				     receiverhtml += "<b class=\"invoice-m-icon\" onclick=\"xuanzeReceiver("+data[i].id+")\" id=\"addressList_"+data[i].id+"\" style=\"left:11px;\"></b>";
				   }else{
				     receiverhtml += "<b class=\"invoice-m-icon invoice-m-no\" onclick=\"xuanzeReceiver("+data[i].id+")\" id=\"addressList_"+data[i].id+"\" style=\"left:11px;\"></b>";
				   }
				  receiverhtml += "</div>";
				  receiverhtml += "<span class=\"set-icon\" onclick=\"updateReceiver("+data[i].id+")\"></span></li>";
				}
				$("#receiverList_Div_ul_id").html(receiverhtml);
                $(".click-select").show();
                $('.click-address').css({bottom:"0"});
			   }else{
			       addReceiver();
			   }
			},
			error : function() {
			}
		});
}
//选择收获地址
function xuanzeReceiver(receiverId){
    if(ajaxCheckArea('',receiverId)){
       $.ajax({
			url : "${base}/member/order!ajaxReceiver.action",
			type : "post",
			data : {"receiverId":receiverId},
			success : function(data) {
	            if(data!=''&&data!=null){
	                $("#ajaxReceiver_renmobile").html("收货人:"+data.receiver.receiver+"<span>"+data.receiver.mobile+"</span>");
					
					var locationAddress = "";
	                if(data.receiver.latitude!=''&&data.receiver.longitude!=''){
	                  $("#ajaxReceiver_areaaddress").html(data.receiver.area+"&nbsp;&nbsp;"+locationAddress+data.receiver.address);
	                }else{
	                  $("#ajaxReceiver_areaaddress").html(data.receiver.area+"&nbsp;&nbsp;"+locationAddress+data.receiver.address);
	                }
	                
	                $("#receiverId").val(data.receiver.id);
	                if(data.beijingFlag == '1'){
	                  $("#offline_4").show();
	                }else{
	                  $("#offline_4").hide();
	                  $("#offline_4").removeClass('pay-way-current');
	                  if($("#paymentWayId").val()=='4'){
				          $("#paymentWayId").val("");
	                  }
	                }
	                if($("#paymentWayId").val()==''){
	                    $("#paymentWayId").val("1");
	                    $("#offline_4").removeClass('pay-way-current');
                        $("#online_1").addClass('pay-way-current');
	                }
	                createOrderPrice();
	                closeDivCen();
	                $("body").scrollTop(0);
	            }
			},
			error : function() {
			}
		});
    }
}

//检查收货地址 层级  
function ajaxCheckArea(areaId,receiverId){
	return true;	
}

//保存或是修改收获地址
function saveOrUpdateReceiver(){
    if(checkReceiver()&&ajaxCheckArea($("#memberReceiver_areaId").val(),'')){
       var formInfo=$("#receiverForm").serialize();
		$.ajax({
			url:"${base}/member/order!ajaxSaveOrUpdateReceiver.action",
			type:"post",
			data:formInfo,
			success:function(data){
				if(data!=''){ //'1'更新,'2'修改
					$alert("success","操作成功",null,null);
					closeDivCen();
					//receiverListShow();
					xuanzeReceiver(data);
				}else{
				    $alert("error","操作失败，请稍后再试",null,null);
				}
			},
			error:function(){
				$alert("error","操作失败，请稍后再试",null,null);
			}
		});
    }
}
  
//检测收货地址 添加项
  function checkReceiver(){
	// 收货人
	var receiver = $.trim($("#memberReceiver_receiver").val());
	if (receiver == '') {
		$alert("warn","请填写收货人",null,null);
		return false;
	} 
	// 手机号码
	var mobile = $.trim($("#memberReceiver_mobile").val());
	if (mobile == '') {
		$alert("warn","请填写手机号码",null,null);
		return false;
	} else {
		var re = /^[1][3,4,7,5,8][0-9]{9}$/;
		if (!re.test(mobile)) {
			$alert("warn","手机号码格式不正确",null,null);
			return false;
		}
	}
	// 城市
	var memberReceiver_adcode = $.trim($("#memberReceiver_adcode").val());
	if (memberReceiver_adcode == '') {
		$alert("warn","请选择所在城市",null,null);
		return false;
	}
	// 街道地址
	var memberReceiver_locationAddress = $.trim($("#memberReceiver_locationAddress").val());
	if (memberReceiver_locationAddress == '') {
	    $alert("warn","请选择街道地址",null,null);
		return false;
	} 

	// 详细地址
	var address = $.trim($("#memberReceiver_address").val());
	if (address == '') {
		$alert("warn","请输入详细地址",null,null);
		return false;
	} else {
		$("#addressSpanFont").html("");
	}
	return true;
}
//关闭弹出层
function closeDivCen(){
	    $(".click-select").hide();
        $('.click-address').css({bottom:"-70%"});
        $(".click-select-edit").hide();
        $('.click-address-edit').css({bottom:"-70%"});
        $(".my-coupon").hide();
        $('.coupon-content').css({bottom:"-70%"});
}
//检查订单提交项
function checkOrderForm(){
   var receiverId =$("#receiverId").val();
   if(receiverId==''){
      $alert("warn","请选择收货地址",null,null);
      return false;
   }
   var paymentWayId =$("#paymentWayId").val();
   if(paymentWayId==''){
      $alert("warn","请选择支付方式",null,null);
      return false;
   }
   return true;
}

//创建订单
function createOrder(){
$("#order_submit").attr("href", "javascript:void(0)");
$("#order_submit").html("提交中...");
if(checkOrderForm()&&ajaxCheckArea('',$("#receiverId").val())){
    $("#invoiceTitle").val($("#invoiceTitle_b").val());
    $("#invoiceTitle").val($("#invoiceTitle_b").val());
    $("#remark").val($("#remark_test").val());
    var formInfo = $("#orderForm").serialize();
    $.ajax({
				url : "${base}/member/order!saveOrder.action",
				type : "post",
				data : formInfo,
				success : function(data) {
				data = eval('(' + data + ')');
				$("#order_submit").attr("href", "javascript:createOrder()");
				$("#order_submit").html("去结算");
					if (data.flag) { // '1'更新,'2'修改
					    if($("#paymentWayId").val()!='4'){
					         if(data.messageContent!='0'){
					           window.location.href="${base}/member/order!toOrderPay.action?orderId="+data.message;
					         }else{
					           window.location.href="${base}/member/orderList!getOrderList.action?tag=pay";
					         }
					    }else{
                            $alert("success","提交成功!",null,null);
                            //跳转到 我的订单
                            window.location.href="${base}/member/orderList!getOrderList.action?tag=pay";
					    }
					} else {
						if (data.message == '1') {
							$alert("warn","请选择收货地址!",null,null);
						} else if (data.message == '2') {
							$alert("warn","请选择支付方式!",null,null);
						} else if (data.message == '3') {
							$alert("warn","请填写发票抬头!",null,null);
						}else if (data.message == '4') {
							$alert("warn","购物车没有商品,请选购结算商品!",null,null);
						} else if (data.message == '5') {
							$alert("warn","购物车中存在库存不足商品,请返回购物车修改!",null,null);
						} else if (data.message == '6') {
							$alert("warn","购物车存在已经下架商品!",null,null);
						} else if (data.message == '9') {
							$alert("warn","提交订单失败!",null,null);
						}else if (data.message == '7') {
							$alert("warn","商品:"+data.messageContent+"为处方药不可在线购买!",null,null);
						}else if (data.message == '8') {
							$alert("warn","商品:"+data.messageContent+"是处方药，目前处方药仅支持北京部分地区以货到付款的方式购买。",null,null);
						}else {
							$alert("warn",data.message,null,null);
						}
					}
				},
				error : function() {
					$alert("error","系统异常，请稍后再试",null,null);
					$("#order_submit").attr("href", "javascript:createOrder()");
				    $("#order_submit").html("去结算");
				}
			});
  }else{
      $("#order_submit").attr("href", "javascript:createOrder()");
	  $("#order_submit").html("去结算");
  }
}

</script>

<#include "shippingAddressAddDiv.ftl"/>
<#include "shippingAddressSelectDiv.ftl"/>