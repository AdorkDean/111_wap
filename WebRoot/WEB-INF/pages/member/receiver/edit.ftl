<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="yes" name="apple-touch-fullscreen" />
    <meta content="fullscreen=yes,preventMove=no" name="ML-Config" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="format-detection" content="email=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <title>编辑收货地址</title>
</head>
<body>
<#assign title="编辑收货地址">
<#include "/static/inc/wap/header.ftl">
<link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
<article class="member-main">
    <form id="inputForm" name="inputForm" action="${base}/member/receiver!saveOrUpdate.action" method="post" enctype="multipart/form-data">
    <ul class="member-list">
    	<input id="isDefault" name="tmemberreceiver.isDefault" type="hidden" value="${tmrr.isDefault}"/>
    	<input id="reid" name="tmemberreceiver.id" type="hidden" value="${tmrr.id}"/>
        <li>
        	<span>收货人</span>
            <div class="for-ipt"><input type="text" id="recid" name="tmemberreceiver.receiver" placeholder="请输入收货人" value="${tmrr.receiver}" maxlength="9"/></div>
        </li>
        <li>
        	<span>手机号码</span>
            <div class="for-ipt"><input placeholder="请输入手机号码" id="mobile" name="tmemberreceiver.mobile" type="text" value="${tmrr.mobile}" maxlength="11"/></div>
        </li>
        <li>
        	<span>邮政编码</span>
            <div class="for-ipt"><input type="text" placeholder="请输入邮政编码" id="zipCode" name="tmemberreceiver.zipCode" value="
            <#if tmrr.zipCode?exists>${tmrr.zipCode}</#if>" maxlength="6"/></div>
        </li>
        <li class="edit-address">
        	<span>所在地区</span>
            <div class="for-ipt address-select"><input id="areaId" name="tmemberreceiver.areaId" type="hidden" value="${tmrr.areaId}"/></div>
        </li>
        <li>
        	<span>详细地址</span>
            <div class="for-ipt"><input id="address" name ="tmemberreceiver.address" type="text" value="${tmrr.address}" placeholder="请输入详细收货地址" maxlength="40"/></div>
        </li>
    </ul>
    <ul class="member-list change-add-set">
    	<#if tmrr.isDefault == 0>
        <li><a href="#" id="setd">设为默认收货地址</a></li>
        <#else>
        <li><a href="#" id="setd">取消默认收货地址</a></li>
        </#if>
    </ul>
    <ul class="member-list change-add-del">
        <li><a href="#" id="del-btn">删除收货地址</a></li>
    </ul>
    <div class="follow-btn"><a href="#" id="save-btn">完成</a></div>
    </form>
</article>
<#include "/static/inc/wap/footer.ftl">
<script type="text/javascript" src="${base}/web/js/member-center.js"></script>
<script type="text/javascript" src="${base}/web/js/jquery.lSelect.js"></script>
<script>
$(function(){
    /**区域加载*/
	$("#areaId").lSelect({
		url: "${base}/location/area.action"
	});
	/**默认收货地址*/
	var $isDefault = $("#isDefault");
	$("#setd").click(function(){
		if($isDefault.val() == '0'){
			$(this).html('取消默认收货地址');
			$isDefault.val("1");
		}else{
			$(this).html('设为默认收货地址');
			$isDefault.val("0");
		}
	});
	/**表单提交*/
	$("#save-btn").click(function(){
		//收货人
		var $recid = $.trim($("#recid").val());
		//手机号码
		var $mobile = $.trim($("#mobile").val());
		//邮政编码
		var $zipCode = $.trim($("#zipCode").val());
		//所在地区
		var $areaId = $.trim($(".for-ipt").find("select option:selected").text());
		//详细地址
		var $address = $.trim($("#address").val());
	    if($recid == ''){
	    	$alert("warn","收货人不能为空");
			return false;
	    }
	    if($mobile == ''){
	    	$alert("warn","手机号码不能为空");
			return false;
	    }	    
	    var mie = /^1[3|4|5|7|8]{1}\d{9}$/;
	    if(!mie.test($mobile)){
	    	$alert("warn","手机号格式不正确");
			return false;
	    }
	    if($zipCode != ''){
	    	var checkZipCode = /^\d{6}$/;
	    	if(!checkZipCode.test($zipCode)){
	    		$alert("warn","邮政编码格式不正确");
				return false;
	    	}
	    }
		if($areaId == '请选择...'){
			$alert("warn","请选择所在地区");
			return false;
		}
		if($address == ''){
			$alert("warn","请填写详细地址信息");
			return false;
		}
		$("#inputForm").submit();
	});
	/**删除收货地址*/
	$("#del-btn").click(function(){
		if(confirm("确认删除吗?")){
			var id = $.trim($("#reid").val());
			window.location.href = "/member/receiver!delete.action?id="+id;
		}
	});
});
</script>
</body>
</html>
<style>
.address-select{padding-top:13px;}
.address-select select{float:left; margin:0 5px 13px 0;max-width:65px;}
</style>