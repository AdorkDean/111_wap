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
    <title>绑定账号</title>
    <link href="/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/web/js/??jquery.min.js,new-health.js"></script>
</head>
<body class="bg-white">
<article class="health-in-money">
	<div class="choice-way-box">
    <!--	<h2 class="choice-way-tips"><b></b>温馨提示：<span>为保障交易安全请先绑定您的帐号</span></h2> -->
        <div class="choice-way-btn clearfix">
        	<h1>请选择一种提现方式</h1>
            <ul class="clearfix">
            	<li>
                	<a href="JavaScript:void(0)" onclick="javascript:BindingAmount('2')"><b>银行卡</b><img src="${base}/web/images/new-health/bank_card.png"></a>
                </li>
                <li>
                	<a href="JavaScript:void(0)" onclick="javascript:BindingAmount('1')"><b>支付宝</b><img src="${base}/web/images/new-health/ali_pay.png"></a>
                </li>
            </ul>
        </div>
    </div>
</article>
</body>
<script type="text/javascript">
function BindingAmount(numberType)
{		
		$.ajax({
				    url: "${base}/leader/bindingAmount!bindingAmountIndex.action",
				    type: "GET",
				 	data: {
				 		numberType: numberType,
				 	},
				  	cache: false,
			      	success: function(data) {
					if(data==1){
						location.href = "${base}/leader/bindingAmount!bindingZfbAmountIndex.action";//转到支付宝账号绑定页
					}else if(data==2){
						location.href = "${base}/leader/bindingAmount!bindingYhkAmountIndex.action";//转到银行卡账户账号绑定页
					}else{
						$alert("warn","数据异常！","111医药馆提示您",null);
						return false;
					}
				 }
			 });
}
</script>
</html>

