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
    <title>物流填写</title>
</head>

<body>
<form id="inputForm" action="${base}/member/return!update.action" method="post" enctype="multipart/form-data">
<input type="hidden" name="treturn.id" value="${id?default('')}"/>
<header class="header">
    <a href="javascript:;" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">物流填写</h2>
    <a id="save" href="javascript:;" class="top-right-btn"><span>完成</span></a>
</header>
<article class="single-box pt45">
    <ul class="fill-logistic-box m_top">
        <li>
        	<p>物流公司</p>
            <div class="for-ipt">
            	<input id="expressCompany" name="treturn.expressCompany" type="text" maxlength="30" class="greycolor writeinfo" placeholder="请填写物流公司" value="<#if return?exists>${return.expressCompany?default('')}</#if>"/>
            </div>
        </li>
        <li>
        	<p>快递单号</p>
            <div class="for-ipt">
            	<input id="expressDelivery" name="treturn.expressDelivery" type="text" maxlength="30" class="greycolor writeinfo" placeholder="请填写快递单号" value="<#if return?exists>${return.expressDelivery?default('')}</#if>"/>
            </div>
        </li>        
        <li>
        	<p>收货人</p>
            <div class="for-ipt">
            	<input type="text" class="greycolor writeinfo" value="111医药馆" readonly/>
            </div>
        </li>
        <li>
        	<p>手机号</p>
            <div class="for-ipt">
            	<input type="tel" class="greycolor writeinfo" value="4006063111" readonly/>
            </div>
        </li>
        <li>
        	<p>收货地址</p>
            <div class="for-ipt">
            	<input type="tel" class="greycolor writeinfo" value="北京北京市朝阳区望京湖光中街西口季景沁园216号楼" readonly/>
            </div>
        </li>
    </ul>
</article>
<#include "/static/inc/wap/footer.ftl">
</form>
</body>
</html>
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript">
$().ready(function(){

	$("#save").click(function(){
		
		if($("#expressCompany").val() == null || $("#expressCompany").val() == ""){
			alert("请输入快递公司名称");
			return ;
		}
		
		if($("#expressDelivery").val() == null || $("#expressDelivery").val() == ""){
			alert("请输入快递单号");
			return ;
		}
		
		$.ajax({
			url: "${base}/member/return!update.action",
			type: "post",
			data:$("#inputForm").serialize(),
			cache: false,
			success: function(data){
				alert(data.content);
				
				if(data.type){
					location.href = "${base}/member/return.action";	
				}
			}
		});
	});
});
</script>