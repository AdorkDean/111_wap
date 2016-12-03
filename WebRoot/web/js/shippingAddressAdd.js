$("#btnSave").click(function(){
	saveOrUpdateReceiverNew();
});

//存或是修改收获地址
function saveOrUpdateReceiverNew(){
	$("#hbgForSave").show();
    if(checkReceiver()){
    	hideBtnSave();
    	$("#hbgForSave").show();
    	var formInfo=$("#addressForm").serialize();
    	$.ajax({
			url:"/member/order!ajaxSaveOrUpdateShippingAddress.action",
			type:"post",
			data:formInfo,
			success:function(data){
				if(data == '-1'){
					$alert("error","请选择地区",null,null);
					showBtnSave();
					$("#hbgForSave").hide();
					return;
				}
				
				if(data!=''){ //'1'更新,'2'修改
					$alert("success","操作成功",null,null);
					xuanzeReceiver(data);
					closeDivCen();
					$("#hbgForSave").hide();
//					/window.location.href="/member/order!toOrderAdd.action";
				}else{
				    $alert("error","操作失败，请稍后再试",null,null);
				    showBtnSave();
				    $("#hbgForSave").hide();
				}
			},
			error:function(){
				$alert("error","操作失败，请稍后再试",null,null);
				showBtnSave();
				$("#hbgForSave").hide();
			}
    	});
    }else{
    	$("#hbgForSave").hide();
    }
}


//检测收货地址 添加项
function checkReceiver(){
	// 收货人
	var receiver = $.trim($("#receiver").val());
	if (receiver == '') {
		$alert("warn","请填写收货人",null,null);
		return false;
	} 
	// 手机号码
	var mobile = $.trim($("#mobile").val());
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
	var citycode = $.trim($("#citycode").val());
	if (citycode == '' || citycode == 'undefined') {
		$alert("warn","请选择地区",null,null);
		return false;
	}
	// 街道地址
	var locationAddress = $.trim($("#areaFirstTwoAddress").val());
	if (locationAddress == '') {
	    $alert("warn","请选择街道地址",null,null);
		return false;
	} 

	// 详细地址
	var address = $.trim($("#address").val());
	if (address == '') {
		$alert("warn","请填写详细地址",null,null);
		return false;
	}
	return true;
}

function showBtnSave(){
	$("#address-submit-btn").show();
	$("#address-submit-btn-temp").hide();
}

function hideBtnSave(){
	$("#address-submit-btn").hide();
	$("#address-submit-btn-temp").show();
}



function receiverListShowNewNew(){
    //$("#receiverList_Div_ul_id").html("");
    $.ajax({
			url : "/member/order!ajaxReceiverList.action",
			type : "post",
			data : {},
			success : function(data) {
			   if(data.length>0){
			    var receiverhtml = "";
	            var receiverId =$("#receiverId").val();
				for ( var i = 0; i < data.length; i++) {
				  receiverhtml += "<li ><div class=\"add-current-select\" ><div class=\"add-li-bd\"><div class=\"font-28\">";
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
				//$("#receiverList_Div_ul_id").html(receiverhtml);
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

function receiverListShowNew(){
	$(".address-part-select").html("");
    $.ajax({
		url : "/member/order!ajaxReceiverList.action",
		type : "post",
		data : {},
		success : function(data) {
		   if(data.length>0){
			   var receiverhtml = "";
			   var receiverId =$("#receiverId").val();
			   for ( var i = 0; i < data.length; i++) {
					receiverhtml += 	"<div class=\"address-list-new \" onclick=\"addressListClick($(this))\" >";
					if(receiverId==data[i].id){
						receiverhtml += 	"	<div class=\"select-add-icon invoice-icon\  onclick=\"xuanzeReceiver("+data[i].id+")\"></div>";
					}else{
						receiverhtml += 	"	<div class=\"select-add-icon\"  onclick=\"xuanzeReceiver("+data[i].id+")\"></div>";
					}
					receiverhtml +=    	"	<div class=\"add-current-select\" onclick=\"xuanzeReceiver("+data[i].id+")\" >";
					receiverhtml +=     "   	<p class=\"add-name\">";
					receiverhtml +=     "			<span>"+data[i].receiver+"</span>";
					receiverhtml +=     "			<b>"+data[i].mobile+"</b>";
					receiverhtml +=     "		</p>";
					var locationAddress = data[i].location_address;
					if(locationAddress == '-'){
						locationAddress = '';
					}
					receiverhtml +=     "       <p class=\"add-message\">"+data[i].area.replace(/-/g,'')+data[i].address+"</p>";
					receiverhtml +=     "	</div>";
					receiverhtml +=     "   <div class=\"edit-add-icon\" onclick=\"updateReceiverNew("+data[i].id+")\"><b></b></div>";
					receiverhtml +=     "</div>";
				}
				$(".address-part-select").html(receiverhtml);
				$(".click-select").show();
				$('.click-address').css({bottom:"0"});
		   }else{
		       addReceiverNew();
		   }
		},
		error : function() {
		}
	});
}



/**
 * 点击编辑收货地址
 */
$(".edit-add-icon").click(function(){
	//ajax获取编辑收货地址明细
	//新增地址弹出层清除及赋值
});


//新增收获地址弹出层
function addReceiverNew(){
	$("#addressTitle").html("新增收货地址");
	clearAddressAddDiv();
   /*
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
   */
	$(".click-select-edit").show();
	$(".click-select").hide();
	$('.click-address-edit').css({bottom:"0"});
   // ajaxClocationList();
}   
 
//修改收获地址弹出层
function updateReceiverNew(receiverId){
      $("#addressTitle").html("编辑收货地址");
      $.ajax({
			url : "/member/order!ajaxReceiver.action",
			type : "post",
			data : {"receiverId":receiverId},
			success : function(data) {
	           if(data!=null&&data!=''){
	        	   	clearAddressAddDiv();
				   	$("#memberReceiver_id").val(data.receiver.id);
					$("#receiver").val(data.receiver.receiver);
					$("#mobile").val(data.receiver.mobile);
					$("#areaFirstTwoAddress").val(data.receiver.area.replace(/-/g,'/'));
					var locationAddress = data.receiver.location_address;
					if(locationAddress == '-'){
						locationAddress = '';
					}
					$("#address").val(data.receiver.address);
					$("#citycode").val(data.receiver.citycode);
					$("#area").val(data.receiver.area);
					$("#isDefault").val(data.receiver.isDefault);
					
					if(data.receiver.is_default == 1){
						$('.pitch-on').children('b').addClass('pitch-on-icon');
					}else{
						$('.pitch-on').children('b').removeClass('pitch-on-icon');
					}
				   
				   /*$("#memberReceiver_adcode").val(data.receiver.citycode);
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
				   */
				   $(".click-select-edit").show();
				   $(".click-select").hide();
				   $('.click-address-edit').css({bottom:"0"});
				   switchButton();
				   var areaArray = data.receiver.area.split('-');
				   setPopPickerSeclected(areaArray[0],areaArray[1],areaArray[2],cityPicker3);
	           } 
			},
			error : function() {
			}
		});
}  

/**
 * 清除地址弹出层
 */
function clearAddressAddDiv(){
	$("#memberReceiver_id").val("");
	$("#receiver").val("");
	$("#mobile").val("");
	$("#cityResult3").text("");
	$("#areaFirstTwoAddress").val("");
	$("#areaFirstTwoAddress").show();
	
	cityPicker3.pickers[0].setSelectedIndex(0);
	cityPicker3.pickers[1].setItems(cityPicker3.pickers[0].getSelectedItem().children);
	cityPicker3.pickers[1].setSelectedIndex(0);
	cityPicker3.pickers[2].setItems(cityPicker3.pickers[1].getSelectedItem().children);
	cityPicker3.pickers[2].setSelectedIndex(0);
	
	$("#address").val("");
	$("#citycode").val("");
	$("#area").val("");
	$("#isDefault").val("");
	$('.pitch-on').children('b').removeClass('pitch-on-icon');
}

/**
 * 新增用户收货地址校验及交互效果
 * @returns
 */
function verifyAllInputNotBlank(){
	var a = $.trim($("#receiver").val());
	var b = $.trim($("#mobile").val());
	var c = $.trim($("#areaFirstTwoAddress").val());
	var d = $.trim($("#address").val());
	return Boolean(a&&b&&c&&d);
}
function switchButton(){
	if(verifyAllInputNotBlank()){
		showBtnSave();
	}else{
		hideBtnSave();
	}
}
$("#receiver")[0].oninput=function(){switchButton();};
$("#mobile")[0].oninput=function(){switchButton();};
$("#areaFirstTwoAddress")[0].oninput=function(){switchButton();};
$("#address")[0].oninput=function(){switchButton();};

$("#receiver").blur(function(){switchButton();});
$("#mobile").blur(function(){switchButton();});
$("#areaFirstTwoAddress").blur(function(){switchButton();});
$("#address").blur(function(){switchButton();});

/**
 * 
 * 获取省份cityData
 */
function getCityDataProvince(proviceText){
	for(x in cityData3){
		if(cityData3[x].text == proviceText){
			return cityData3[x];
		}
	}
	for(x in cityData3){
		if(cityData3[x].text.indexOf( proviceText) >= 0 ){
			return cityData3[x];
		}
	}
	return null;
}

/**
 * 
 * 获取市cityData
 */
function getCityDataCity(cityDataProvince,cityText){
	if(cityDataProvince == null){
		return null;
	}
	var cityData3Citys = cityDataProvince.children;
	for(x in cityData3Citys){
		if(cityData3Citys[x].text == cityText){
			return cityData3Citys[x];
		}
	}
	return null;
}


/**
 * 
 * 获取区cityData
 */
function getCityDataArea(cityDataCity,areaText){
	if(cityDataCity == null){
		return null;
	}
	var cityData3Areas = cityDataCity.children;
	for(x in cityData3Areas){
		if(cityData3Areas[x].text == areaText){
			return cityData3Areas[x];
		}
	}
	return null;
}

function setPopPickerSeclected(province,city,area,cityPicker3){
	/*
	var province = "河北省";
	var city = "保定市";
	var area = "易县";
	*/
	
	var cityDataProvince = getCityDataProvince(province);
	var cityDataCity = getCityDataCity(cityDataProvince,city);
	var cityDataArea = getCityDataArea(cityDataCity,area);
	
	
	/* 
    cityPicker3.pickers[0].setSelectedValue('510000');
    cityPicker3.pickers[1].setItems(cityPicker3.pickers[0].getSelectedItem().children);
	cityPicker3.pickers[1].setSelectedValue('510300');
	cityPicker3.pickers[2].setItems(cityPicker3.pickers[1].getSelectedItem().children);
	cityPicker3.pickers[2].setSelectedValue('510304');
	*/
	
	if(cityDataProvince != null){
		cityPicker3.pickers[0].setSelectedValue(cityDataProvince.value);
		cityPicker3.pickers[1].setItems(cityPicker3.pickers[0].getSelectedItem().children);
	}
	if(cityDataCity != null){
		cityPicker3.pickers[1].setSelectedValue(cityDataCity.value);
		cityPicker3.pickers[2].setItems(cityPicker3.pickers[1].getSelectedItem().children);
	}
	if(cityDataArea != null){
		cityPicker3.pickers[2].setSelectedValue(cityDataArea.value);
	}
	/*
	alert(cityDataProvince.value);
	alert(cityDataCity.value);
	alert(cityDataArea.value);
	*/
}

//选择收获地址
function xuanzeReceiverNew(receiverId){
    if(ajaxCheckArea('',receiverId)){
       $.ajax({
			url : "${base}/member/order!ajaxReceiver.action",
			type : "post",
			data : {"receiverId":receiverId},
			success : function(data) {
	            if(data!=''&&data!=null){
	                $("#ajaxReceiver_renmobile").html("收货人:"+data.receiver.receiver+"<span>"+data.receiver.mobile+"</span>");
	                var locationAddress = "";
	                if(data.receiver.location_address != '-'){
	                	locationAddress = data.receiver.location_address;
	                }
	                if(data.receiver.latitude!=''&&data.receiver.longitude!=''){
	                  $("#ajaxReceiver_areaaddress").html(data.receiver.area+"&nbsp;&nbsp;"+data.receiver.address);
	                }else{
	                  $("#ajaxReceiver_areaaddress").html(data.receiver.area+"&nbsp;&nbsp;" + data.receiver.address);
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
	            }
			},
			error : function() {
			}
		});
    }
}

$(".address-message-keyhide").click(function(){
	cityPicker3.hide();
});

$("#cityResult3").click(
		function(){
			$('.pitch-on-on').click();
		}
);





