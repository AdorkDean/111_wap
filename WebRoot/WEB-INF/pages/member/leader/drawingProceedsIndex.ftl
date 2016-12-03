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
    <title>提现</title>
    <link href="/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/web/js/??jquery.min.js,new-health.js,alert.main.js"></script>
</head>
<body class="bg-white">
<article class="new-health-box">
	<article class="new-health-register">
    	<div class="account-info-box">
        	<div class="account-number clearfix">
            	<span>到账账号：</span>
                <p>
                	<#if leaderAccountNumber.bankName?exists && leaderAccountNumber.bankName?string !="">
                	<img src="${base}/web/images/new-health/bank_ico.png">
                	 ${leaderAccountNumber.bankName?default("")}(${leaderAccountNumber.accountNumber?default("")})
                	<#else>
                	<img src="${base}/web/images/new-health/card_alipay_ico.png">
                	${leaderAccountNumber.accountNumber?default("")}
                	</#if>
                </p>
            </div>
            <div class="fill-money-box">
            	<p>提现金额</p>
                <div class="fill-money-info">
                	<div class="for-fill-input">
                    	<b>￥</b>
                    	<input type="tel" id="tqAmount"/>
                    </div>
                    <div class="fill-tips">可提现收益(壹贝)¥${account.remainingAmount?default(0)}</div>
                </div>
            </div>
        </div>
    	<div class="new-health-btn">
            <!--不可用-->
            <a href="JavaScript:void(0)" id="tqButton"  onclick="javascript:tqshouyi('${leaderAccountNumber.id?default(0)}')">确认</a>
        </div>
    </article>
</article>
</body>
<script type="text/javascript">

//	var $tqAmount=$("#tqAmount");
//	$tqAmount.focus(function(){
//		if($tqAmount.val()==null || $.trim($tqAmount.val()) == ""){
//			$("#tqButton").addClass("gray");
//		}else{
//			$("#tqButton").removeClass("gray");
//		}
//	});
//	$tqAmount.blur(function(){
//		if($tqAmount.val()==null || $.trim($tqAmount.val()) == ""){
//			$("#tqButton").addClass("gray");
//		}else{
//			$("#tqButton").removeClass("gray");
//		}
//	});
function tqshouyi(numberAmountId)
{		
		var $tqAmount=$("#tqAmount");
		var $ktqAmount = "${account.remainingAmount?default(0)}";
		if($tqAmount.val()==null || $.trim($tqAmount.val()) == ""){
					$alert("warn","请输入提现金额！","友情提示",null);
					return false;
				}
				if(parseFloat($tqAmount.val()) >parseFloat($ktqAmount)){
					$alert("warn","提现金额大于可用收益！","友情提示",null);
					return false;
				}
				if(parseFloat($tqAmount.val()) <10){
					$alert("warn","提现最小金额为10元！","友情提示",null);
					return false;
				}
		$.ajax({
				    url: "${base}/leader/bindingAmount!drawingProceeds.action",
				    type: "GET",
				 	data: {
				 		numberAmountId: numberAmountId,
				 		tqAmount:$tqAmount.val(),
				 	},
				  	cache: false,
			      	success: function(data) {
					if(data==0){
						$alert("warn","您已成功申请提现，请等待审核！","111医药馆提示您",null);
						setTimeout(function()
						{
							location.href = "${base}/leader/leaderArticle!leaderProfit.action";
						}, 500);
					}else{
						$alert("warn","网络异常，提现失败！","111医药馆提示您",null);
						return false;
					}
				 }
			 });
}
</script>
</html>