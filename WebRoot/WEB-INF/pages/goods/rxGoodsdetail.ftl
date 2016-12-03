<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="renderer" content="webkit" />
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
<link href="/web/css/??common.css,iconfont.css,rx.goodsdetail.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/web/js/??jquery-1.7.2.js,cookieUtil.js,common.new.js,alert.main.js,yxMobileSlider.js,pinchzoom.js,rxGoodsDetail.js"></script>
<#assign ui1="http://img.zdfei.com/">

    <#if goods.seo_describe?exists>
    	<#if goods.seo_describe?trim != ''>
    		<meta name="description" content="${goods.seo_describe?default('')}" />
    	<#else>
    		<meta name="description" content="111医药馆网上药店是国家药监局认证指定网上药房|打造为老百姓服务药品价格低品种全|网上购药买药就选壹壹壹医药馆药品特卖网站" />
    	</#if>
    <#else>
    	<meta name="description" content="111医药馆网上药店是国家药监局认证指定网上药房|打造为老百姓服务药品价格低品种全|网上购药买药就选壹壹壹医药馆药品特卖网站" />
    </#if>
   
    <#if goods.seo_keyword?exists>
    	<#if goods.seo_keyword?trim != ''>
    		<meta name="keywords" content="${goods.seo_keyword?default('')}" />
    	<#else>
	    	<meta name="keywords" content="壹壹壹医药馆|壹药馆|11医药馆|1药馆|医卡通|111医药馆|医药网|药品特卖网站" />
    	</#if>
    <#else>
    	<meta name="keywords" content="壹壹壹医药馆|壹药馆|11医药馆|1药馆|医卡通|111医药馆|医药网|药品特卖网站" />
    </#if>
    
    
    <#if goods.seo_title?exists>
    	<#if goods.seo_title?trim != ''>
    		<title>${goods.seo_title?default('')}</title>
    	<#else>
	    	<title>${goods.goodsName?default('')}-111医药馆-要健康 要美丽 要时尚-医卡通买药价格低便宜实惠-国家药监局指定壹壹壹医药馆网上连锁药店</title>
    	</#if>
    <#else>
    	<title>${goods.goodsName?default('')}-111医药馆-要健康 要美丽 要时尚-医卡通买药价格低便宜实惠-国家药监局指定壹壹壹医药馆网上连锁药店</title>
    </#if>

</head>


<body>
<header class="header">
    <a href="javascript:;" class="iconfont top-left-btn" id="btn_back_step">B</a>
    <h2 class="header-title">
        商品详情
    </h2>
    <a class="iconfont top-heart-btn" id="collect-btn">H</a>
    <a href="/" class="iconfont top-right-btn">h</a>
</header>

<article class="content" style="top:45px;">
	<article class="details-main">
	  <div class="slider">
	    <div class="slider-page"><span id="cur-num">1</span>/<span id="imglg">5</span></div>
		  <ul>
		    <#if listi?size==0>
	        	<li><a><img src="/web/images/noPro_img.png"></a></li>
	        	<li><a><img src="/web/images/noPro_img.png"></a></li>
	        	<li><a><img src="/web/images/noPro_img.png"></a></li>
	        	<li><a><img src="/web/images/noPro_img.png"></a></li>
	        	<li><a><img src="/web/images/noPro_img.png"></a></li>
	        <#else>
	            <#list listi?if_exists as resc>
			    <li><a><img src="${ui1}${resc.artworkUrl?default('')}"></a></li>
			    </#list>
		    </#if>
		  </ul>
	  	</div>
	  <script>
	      $(".slider").yxMobileSlider({width:640,height:640,during:3000})
	  </script>  
	  
	  
	  
	  <article class="product-box">
	      <header class="product-info">
		      <h1 class="product-title"><i class="rx-ico">RX</i>&nbsp;${goods.goodsName?default('')}</h1>
		      <p class="details-adv">${goods.mainTitle?default('')}</p>
		      <input type="hidden" id="goodsid" value="${goods.id?c}"/>
		      <p class="product-price">
		        <i>¥</i>
		        <b id="mprice">${goods.wap_price?default('')}</b>
		        <del id="oprice">¥${goods.price?default('')}</del>
		        <span id="pcount">${goods.zk?default(0)}折</span>
		        <input type="hidden" value="${goods.zk?default(0)}" id="zkouCount"/>
		      </p>
		      <p class="priduct-spec"><span>规格:${goods.spec?default('')}</span></p>
	      </header>
      </article>
  	  <div class="rx-wx-tips clearfix">
         <span>温馨提示</span>
                  提交需求后药师将在1个小时后与您联系
      </div> 
  		 
  		 
	  <article class="rx-product-info">
      <ul class="rx-text-info">
    	<li>厂名：${goods.manufacturer?default('')}</li>
    	<li>产品剂型: ${goods.packing?default('')}</li>
    	<li>配料表：${goods.bases?default('')}</li>
    	<li>保质期：${goods.lasts?default('')}</li>
    	<li>批准文号: ${goods.approvalNumber?default('')}</li>
      </ul>
      <div class="rx-model-box">
    	   <h2>流程说明</h2>
           <div class="rx-step">
        	    <img src="http://img.zdfei.com/static/image/temp/20160418/7792f5805910e50d542691bb715a9a35.jpg">
           </div>
      </div>
      <div class="rx-model-box">
           <h2>处方药需求须知</h2>
           <ul class="rx-notice">
            	<li><span>1、</span><p>提交预定后，我们的药师将在1小时内与您电话联系，请保持电话畅通；</p></li>
            	<li><span>2、</span><p>药师值班时间（周一至周五：8:00-24:00，周六、周日：8:00-21:00），超过该时段，我们的药师将次日与您联系；</p></li>
            	<li><span>3、</span><p>咨询服务热线：400-6063-111，药师专线：400-6063-111；</p></li>
            	<li><span>4、</span><p>投诉举报邮箱：report@111yao.com</p></li>
            </ul>
      </div>
      <div class="rx-model-box">
        	<h2>说明书</h2>
            <div class="rx-images">
            	<#if goods.instructions?exists>
		    		<#if goods.instructions?if_exists?has_content>
		    			${goods.instructions?default('')}
		    			<#else>
		    			<p><img src="http://img.zdfei.com/static/image/temp/20160628/a34c91030e0c635210b9363b4fede585.jpg" alt="普通商品说明书"/></p>
		    		</#if>
		    	<#else>
		    		<p><img src="http://img.zdfei.com/static/image/temp/20160628/a34c91030e0c635210b9363b4fede585.jpg" alt="普通商品说明书"/></p>
		    	</#if>
            </div>
      </div>
      <div class="rx-model-box">
        	<h2>商品详情</h2>
            <div class="rx-images">
            	${goods.goodsDescribes?default('')}
            </div>
      </div>
      <div class="rx-model-box">
        	<h2>服务承诺</h2>
            <div class="rx-promise">
            	<img src="http://img.zdfei.com/static/image/temp/20160322/276f55304d215816571d75f069196d26.png" />
            </div>
      </div>
      <div class="rx-model-box2">
        	<h2>服务优势</h2>
            <div class="rx-advantage">
            	<img src="http://img.zdfei.com/static/image/temp/20160418/3eafafc15856d04e7a08243ebb35baf4.jpg" />
            </div>
      </div>
  </article>
</article>
<div class="appointment-btn-fixed">
	<a class="appointment-btn" onclick="submitNeed()">需求预约</a>
</div>

<a class="back-to-top"></a>
<div class="mask_div_img"></div>
<script src="//kefu.easemob.com/webim/easemob.js?tenantId=2461&hide=true" async="async"></script>
<a id="kefu" onclick="easemobIM()" tenantId=2461></a>
</body>
</html>
