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
    <!--上线时请删除-->
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <!--上线时请删除-->
    <title>个人中心</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
	<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
	<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
	<style>
	  .member-list li .single-select{float: left; margin: 13px 5px 0 0;  max-width: 65px;}
	</style>
</head>
<body>
<header class="header">
    <a href="javascript:receiverSubmit();" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">收货地址</h2>
    <a href="/" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
<form name="orderForm" id="orderForm" method="post">
      <input id="paymentWayId" name="paymentWayId" type="hidden" value="<#if paymentWayId?exists>${paymentWayId?default('')}</#if>"/>
      <input id="receiverId" name="receiverId" type="hidden" value="<#if receiverId?exists>${receiverId?default('')}</#if>"/>
      <input id="invoiceTitle" name="invoiceTitle" type="hidden" value="<#if invoiceTitle?exists>${invoiceTitle?default('')}</#if>"/>
      <input id="ifInvoice" name="ifInvoice" type="hidden" value="<#if ifInvoice?exists>${ifInvoice?default('')}</#if>"/>
      <input id="invoiceType" name="invoiceType" type="hidden" value="<#if invoiceType?exists>${invoiceType?default('')}</#if>"/>
      <input id="couponCardId" name="couponCardId" type="hidden" value="<#if couponCardId?exists>${couponCardId?default('')}</#if>"/>
      <input id="payPluginId" name="payPluginId" type="hidden" value="<#if payPluginId?exists>${payPluginId?default('')}</#if>"/>
</form>
<form name="receiverForm" id="receiverForm" method="post">
<input id="id" name="memberReceiver.id" type="hidden" value="<#if receiver?exists>${receiver.id?default('')}</#if>"/>
<input id="isDefault" name="memberReceiver.isDefault" type="hidden" value="<#if receiver?exists>${receiver.isDefault?default('')}<#else>0</#if>"/>
<input id="areaId" name="memberReceiver.areaId" type="hidden" value="<#if receiver?exists>${receiver.areaId?default('')}</#if>"/>
<input id="area" name="memberReceiver.area" type="hidden" value="<#if receiver?exists>${receiver.area?default('')}</#if>"/>
<article class="member-main">
    <ul class="member-list">
        <li>
        	<span>收货人</span>
            <div class="for-ipt"><input type="text" id="receiver" maxlength="10" name="memberReceiver.receiver" placeholder="请填写收货人" value="<#if receiver?exists>${receiver.receiver?default('')}</#if>"/></div>
        </li>
        <li>
        	<span>手机号码</span>
            <div class="for-ipt"><input type="text" id="mobile" name="memberReceiver.mobile" placeholder="请填写手机号码" value="<#if receiver?exists>${receiver.mobile?default('')}</#if>"/></div>
        </li>
        <li>
        	<span>邮政编码</span>
            <div class="for-ipt"><input type="text" id="zipCode" name="memberReceiver.zipCode" placeholder="请填写邮政编码" value="<#if receiver?exists>${receiver.zipCode?default('')}</#if>"/></div>
        </li>
        <li id="areaIdDiv" style="height: 70px;">
        	<span>所在地区</span>
        	<#if receiver?exists &&receiver.areaId?exists>
        	    <input type="text" placeholder="<#if receiver?exists>${receiver.area?default('')}</#if>" style="width:50%" value="<#if receiver?exists>${receiver.area?default('')}</#if>" onclick="areaViewSelect()" id="areaview"/>
        	    <p id="areaselectView" style="display:none">
                 <select class="single-select" id="areaId1" onchange="areaSelect('areaId',1)" name="areaId1">
                    <option value="">请选择</option>
                    <#list clocationList as clocation>
		      	 		 <option value="${clocation.id?default('')}">${clocation.name?default('')}</option>
		      		</#list>
                 </select>
               </p>
        	    
        	<#else>
        	  <p>
                 <select class="single-select" id="areaId1" onchange="areaSelect('areaId',1)" name="areaId1">
                    <option value="">请选择</option>
                    <#list clocationList as clocation>
		      	 		 <option value="${clocation.id?default('')}">${clocation.name?default('')}</option>
		      		</#list>
                 </select>
               </p>
        	</#if>
        </li>
        <li>
        	<span>详细地址</span>
            <div class="for-ipt"><input type="text" id="address" maxlength="40" name="memberReceiver.address" placeholder="请填写详细收货地址" value="<#if receiver?exists>${receiver.address?default('')}</#if>"/></div>
        </li>
    </ul>
    <#--<#if receiver?exists>
     <ul class="member-list change-add-del">
        <li><a href="javascript:deleteReceiver()">删除收货地址</a></li>
     </ul>
    </#if>-->
    <ul class="member-list change-add-set">
        <li><a href="javascript:isDefaultSelect()" id="isDefaultA"><#if receiver?exists><#if receiver.isDefault==0>设为<#else>取消</#if><#else>设为</#if>默认收货地址</a></li>
    </ul>
    <div class="follow-btn"><a href="javascript:saveReceiver()">完成</a></div>
</article>
</form>
	 <#include "/static/inc/wap/footer.ftl">
</body>
<script>
  function areaViewSelect(){
     $("#areaview").hide();
     $("#areaselectView").show();
  }

  function isDefaultSelect(){
   var isDefault = $("#isDefault").val();
   if(isDefault==''||isDefault=='0'){
      $("#isDefault").val("1");
      $("#isDefaultA").html("取消默认收货地址");
   }else if(isDefault=='1'){
      $("#isDefault").val("0");
      $("#isDefaultA").html("设为默认收货地址");
   }
  }

  //选择地区
  function areaSelect(obj,level){
    var parentid=$("#"+obj+level).val();
    if(parentid!=''){  
       $.ajax({
			url:'${base}/member/order!clocationSelect.action',
			data:{"parentid":parentid},
			type:'post',
			success:function(data){
			    data = eval('(' + data + ')');
			    if(data.clocationList.length>0){
			        var trhtml="<select name=\"areaId"+data.level+"\" onchange=\"areaSelect('areaId',"+data.level+")\" class=\"single-select\" id=\"areaId"+data.level+"\" ><option value=\"\">请选择</option>";
					for(var i=0;i<data.clocationList.length;i++){
					  trhtml+=" <option value="+data.clocationList[i].id+">"+data.clocationList[i].name+"</option>";
					}
			       
			        
					$("#areaIdDiv").append(trhtml);
			    }
			}
		});
    }
    for(var i=level+1;i<5;i++){
	   $("#"+obj+i).remove();
	}
	$("#areaId").val(parentid);
	if(parentid==''&&level!=1){
	   $("#areaId").val($("#"+obj+(level-1)).val());
	}
	var area ="";
	for(var i=0;i<5;i++){
	  if($("#"+obj+(i+1)).val()&&$("#"+obj+(i+1)).val()!=''){
	      if(area==''){
	         area=$("#"+obj+(i+1)).find("option:selected").text();
	      }else{
	         area=area+"-"+$("#"+obj+(i+1)).find("option:selected").text();
	      }
	      
	  }
	}
	$("#area").val(area);
  }
  //删除收获地址
function deleteReceiver(){
        var id =$("#id").val();
		$.ajax({
			url:"${base}/member/order!deleteReceiver.action",
			type:"post",
			data:{"id":id},
			success:function(data){
				if(data=='1'){ //'1'更新,'2'修改
				    var receiverId = $("#receiverId").val();
				    if(receiverId==id){
				       $("#receiverId").val("");
				    }
					$alert("success","删除成功",null,null);
					receiverSubmit();
				}else{
				    $alert("error","操作失败，请稍后再试",null,null);
				}
			},
			error:function(){
				$alert("error","操作失败，请稍后再试",null,null);
			}
		});
}
function saveReceiver(){
    if(checkReceiver()){
       var formInfo=$("#receiverForm").serialize();
		$.ajax({
			url:"${base}/member/order!saveOrUpdate.action",
			type:"post",
			data:formInfo,
			success:function(data){
				if(data!=''){ //'1'更新,'2'修改
					$alert("success","操作成功",null,null);
					receiverSubmit();
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
 //跳转到收货地址列表页面
   function receiverSubmit(){
      $("#orderForm").attr("action","${base}/member/order!orderReceiverList.action")
      $("#orderForm").submit();
   }
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
	// 邮编
	var zipCode = $.trim($("#zipCode").val());
	if (zipCode != '') {
		var re = /^[1-9][0-9]{5}$/;
		if (!re.test(zipCode)) {
			$alert("warn","邮编格式不正确",null,null);
			return false;
		} 
	}
	// 地址
	var areaId = $.trim($("#areaId").val());
	if (areaId == '') {
	    $alert("warn","请填写地址",null,null);
		return false;
	} 

	// 详细地址
	var address = $.trim($("#address").val());
	if (address == '') {
		$alert("warn","请填写详细地址",null,null);
		return false;
	} else {
		$("#addressSpanFont").html("");
	}
	return true;
}


</script>
</html>