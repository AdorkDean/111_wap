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
    <title>选择账号</title>
    <link href="/web/css/??common.css,iconfont.css,health2.0.css" rel="stylesheet" type="text/css" />
</head>
<body class="bg-white">
<article class="new-health-box">
	<article class="new-health-account-main">
        <ul class="choice-account-list">
        	<#list result.get(0)?if_exists as resc>
         <#if resc.bank_name?exists && resc.bank_name?string !="">
        	<li>
            	<a href="#" class="account" onclick="javascript:isUnBinding('${resc.id?default(0)}')">
                	<img src="${base}/web/images/new-health/bank_card_bg.png">
                    <p class="account-kind"><b class="bank_ico1"></b>${resc.bank_name?default("")}</p>
                    <p class="card-kind">银行卡</p>
                    <p class="account-details">${resc.account_number?default("")}</p>
                </a>
            </li>
            <#else>
            <li>
            	<a href="#" class="account" onclick="javascript:isUnBinding('${resc.id?default(0)}')">
                	<img src="${base}/web/images/new-health/ali_pay_bg.png">
                    <p class="account-kind"><b class="alipay_ico"></b>支付宝</p>
                    <p class="account-details">${resc.account_number?default("")}</p>
                </a>
            </li>
            </#if>
          </#list>
        </ul>
        <div class="account-manage-btn">
        	<a href="${base}/leader/bindingAmount!checkDrawingMethod.action"><b class="add_ico"></b>新增绑定账号</a>
        </div>
    </article>
</article>
 <div class="black-cover" style="display:none">
				<div class="account-manage-btn">
					<a href="#"><b class="unlock_ico"></b>解除绑定账号</a>
				</div>
 </div>
 <footer class="new-health-footer" >
	<nav class="new-health-footer-nav">
    	<ul>
        	<li>
            	<a href="../leader/leaderArticle!recommendArticle.action" >
                	<b class="my-article"></b>
                    <p>文章推荐</p>
                </a>
            </li>
            <li>
            	<a  href="../leader/leaderArticle!leaderPharmacy.action">
                	<b class="my-pharmacy"></b>
                    <p>我的药房</p>
                </a>
            </li>
            <li>
            	<a href="../leader/leaderCenter!index.action">
                	<b class="my-manage"></b>
                    <p>管理中心</p>
                </a>
            </li>
        </ul>
    </nav>
    <div class="new-health-copy">
    	<h1>健康领秀</h1>
        <p>&copy;111Yao</p>
    </div>
</footer>
</body>
</html>
 <script type="text/javascript" src="/web/js/??jquery-1.7.2.js,new-health.js"></script>
<script type="text/javascript">
//	$(function(){
//		$('.account').click(function(){
//			$('.black-cover').show();	
//		})	
//	})
	function isUnBinding(accountId)
{
		var result = confirm('您确定要解绑该账号吗?');  
		    if(result){  
		        location.href = "${base}/leader/bindingAmount!unbindingIndex.action?accountId="+accountId;
		    }else{  
		        alert('解绑失败！');  
		    }  
}
</script>