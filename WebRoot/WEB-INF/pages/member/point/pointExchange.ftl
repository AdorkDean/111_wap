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
    <title>积分兑换</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/member-center.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <script src="${base}/web/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
    <style type="text/css">
        .get-coupon-details label:first-child{font-weight: bold; margin-bottom:0;}
        .get-coupon-details label{height:20px;}
        .get-coupon-details label:last-child{margin-top:5px;}
        .get-coupon-details label b{color:#ec3030;}
    </style>
</head>

<body>
<header class="header">
    <a href="${base}/member/jifen!getThirtyJifen.action" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">积分兑换</h2>
</header>
<article class="get-coupon">
   <#list resultList?if_exists as resc>
    <div class="get-coupon-part get-down">
        <div class="get-coupon-details">
            <label for="">${resc.get("name")?default('')}</label>
            <label for="">所需积分：<b>${resc.get("exchangeCount")?default(0)}</b>积分</label>
          	<label for="">有效期：${resc.get("endTime")?date('yyyy-MM-dd')}</label>
        </div>
        <div class="get-coupon-num">
            <label style="font-size:30px;"><b>￥</b>${resc.get("disPrice")?default(0)}</label>
            <label style="font-size:12px; margin-bottom:5px;">活动券</label>
            <label style="font-size:15px;"><span onclick="javascript:pointExchange(${resc.get("id")?default(0)})">立即兑换</span></label>
        </div>
    </div>
   </#list>
</article>
<div class="get-shade-bg">
    <div class="get-shade-coupon">
        <span class="get-shade-icon"></span>
        <label for="" style="margin-bottom:7px;">领取成功!</label>
        <label for="">感谢您的参与，祝您购物愉快</label>
    </div>
</div>
</body>

<script type="text/javascript">
function pointExchange(couponId)
{			
			 $.ajax({
				    url: "${base}/member/profile!ajaxExchange.action",
				    type: "GET",
				 	data: {
				 		couponId: couponId,
				 	},
				  	cache: false,
				  	async:false,
			      	success: function(data) {
					if(data==1){
						$alert("warn","兑换成功！");
					}else{
						$alert("warn",data);
						return false;
					}
				}
			 });
}
</script>
</html>
