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
<title>APP商品详情</title>
<link href="/app/app_goods_detail.css" rel="stylesheet" type="text/css" />
<style>
	.rx-wx-tips 
	{
	    height: 30px;
	    background: #fff;
	    padding: 0 10px;
	    font-size: 12px;
	    overflow: hidden;
	    white-space: nowrap;
	    word-break: keep-all;
	    text-overflow: ellipsis;
	    color: #222;
	}
	.rx-text-info 
	{
	    padding: 10px;
	    background: #fff;
	}
	.rx-text-info li 
	{
	    color: #999;
	    font-size: 12px;
	    line-height: 18px;
	}
	.rx-model-box 
	{
	    margin-top: 10px;
	    background: #fff;
	    padding: 0 10px;
	}
	.rx-model-box h2 
	{
	    font-size: 15px;
	    color: #222;
	    height: 30px;
	    line-height: 30px;
	}
	.rx-step 
	{
    	padding: 5px 0 10px;
	}
	.rx-notice 
	{
	    padding: 5px 0 10px;
	}
	.rx-notice li 
	{
	    overflow: hidden;
	    color: #999;
	    line-height: 18px;
	    font-size: 12px;
	}
	img 
	{
		width:100%;
	}
	.rx-notice li p 
	{
    	padding-left: 21px;
    	margin-top: -18px;
	}
	.dot_line
	{
		width:100%;
		height:10px;
		background:#f3f5f7;
	}
</style>
</head>
<body>
<article class="content">
  <article class="product-details" style="padding:0px;">
	      <div class="dot_line"></div>
	      <ul class="rx-text-info">
	    	<li>厂名：${goods.manufacturer?default('')}</li>
	    	<li>产品剂型: ${goods.packing?default('')}</li>
	    	<li>保质期：${goods.lasts?default('')}</li>
	    	<li>批准文号: ${goods.approvalNumber?default('')}</li>
	      </ul>
	      <div class="dot_line"></div>
	      <div class="rx-model-box">
	           <h2>处方药需求须知</h2>
	           <ul class="rx-notice">
	            	<li><span>1、</span><p>提交预定后，我们的药师将在1小时内与您电话联系，请保持电话畅通；</p></li>
	            	<li><span>2、</span><p>药师值班时间（周一至周五：8:00-21:00，周六、周日：8:00-18:00），超过该时段，我们的药师将次日与您联系；</p></li>
	            	<li><span>3、</span><p>咨询服务热线：400-6063-111，药师专线：400-6063-111；</p></li>
	            	<li><span>4、</span><p>投诉举报邮箱：report@111yao.com</p></li>
	            </ul>
	      </div>
  		  <div class="dot_line"></div>
	      <div class="details-img" id="imgdiv" oncontextmenu="return false" ondragstart="return false" onselectstart ="return false" onselect="document.selection.empty()" oncopy="document.selection.empty()" onbeforecopy="return false">
	        <div class="rx-model-box">
        	<h2>商品详情</h2>
            <div class="rx-images">
            	${goods.goodsDescribes?default('')}
            </div>
      		</div>
	      </div>
  </article>
</article>
</body>
</html>