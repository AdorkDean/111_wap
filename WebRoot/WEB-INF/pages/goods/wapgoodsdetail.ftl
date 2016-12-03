<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="fullscreen=yes,preventMove=no" name="ML-Config">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<link href="/web/css/??common.css,iconfont.css,normal_goodsdetail_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/web/js/??jquery-1.7.2.js,cookieUtil.js,common.new.js,alert.main.js,iscroll.js,yxMobileSlider.js,hotsale-goods.js,wap-cart.js,jquery.fly.min.js"></script>
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
    <h2 class="header-title">商品详情</h2>
    <a href="/" class="iconfont top-right-btn">h</a>
</header>

<article class="content" style="top:45px;">
	<article class="details-main">
	  <!--效果html开始-->
	  <div class="slider">
	    <div class="slider-page"><span id="cur-num">1</span>/<span id="imglg">5</span></div>
		  <ul>
		    <#if listi?size==0>
	        	<li><a href="javascript:;"><img src="/web/images/noPro_img.png"></a></li>
	        	<li><a href="javascript:;"><img src="/web/images/noPro_img.png"></a></li>
	        	<li><a href="javascript:;"><img src="/web/images/noPro_img.png"></a></li>
	        	<li><a href="javascript:;"><img src="/web/images/noPro_img.png"></a></li>
	        	<li><a href="javascript:;"><img src="/web/images/noPro_img.png"></a></li>
	        <#else>
	            <#list listi?if_exists as resc>
			    <li><a href="javascript:;"><img src="${ui1}${resc.artworkUrl?default('')}"></a></li>
			    </#list>
		    </#if>
		  </ul>
	  </div>
	  <script>
	      $(".slider").yxMobileSlider({width:640,height:640,during:3000})
	  </script>  
	  <!--效果html结束-->
	  <article class="product-box">
	    <header class="product-info">
	      <h1 class="product-title">${goods.goodsName?default('')}</h1>
	      <p class="details-adv">${goods.mainTitle?default('')}</p>
	      <input type="hidden" id="goodsid" value="${goods.id?c}"/>
	      <p class="product-price">
	        <i style="font-family:'Microsoft Yahei'">￥</i><b id="mprice">${goods.wap_price?default('')}</b>
	        <span id="pcount">${goods.zk?default(0)}折</span>
	      </p>
	      <p class="priduct-spec"><span>原价:<i style="font-family:'Microsoft Yahei'">￥</i><b id="oprice">${goods.price?default('')}</b></span><span style="text-decoration:none;">规格:${goods.spec?default('')}</span></p>
	    </header>
	  </article>
	  <ul class="explain clearfix">
	  	<li><span class="iconfont">M</span>7天无理由退换货</li>
	    <li><span class="iconfont">M</span>真品假一赔十</li>
	    <li><span class="iconfont">M</span>全程冷藏运输</li>
	    <li><span class="iconfont">M</span>全国规模化采购</li>
	    <#if goods.promotion_id?exists>
		    <li class="w100"><span class="iconfont">M</span>购物满99元包邮，不满99元收取邮费8元</li>
	    </#if>
	  </ul>
	  
	  <!-- 推荐商品 -->
      <div class="groom-box">
	    <h2 class="guess-title"><span>推荐商品</span></h2>
	    <div class="guess-box" id="wrapper">
	        <div class="guess-box-list" id="scroller">
	            <ul>
	            </ul>
	        </div>
	  	</div>
	  </div>
	  
	  <article class="product-info-link">
        <div class="wrap">
            <div class="tabs">
                <a hidefocus="true" class="active"><span>图文详情</span></a>
                <a hidefocus="true"><span>说明书</span></a>
                <a hidefocus="true"><span id="cri_num_new">评论（0）</span></a>
            </div>    
            <div class="swiper-container">
                <div class="swiper-wrapper" style="float:left;">
                    <div class="swiper-slide" id="big_content_1">
                        <div class="content-slide">
                        	<#if goods.goodsDescribes?exists>
                        		${goods.goodsDescribes?default('')}
                        	<#else>
                        	<p><img src="http://img.zdfei.com/static/image/temp/20160628/a34c91030e0c635210b9363b4fede585.jpg" alt="普通商品说明书"/></p>
                        	</#if>
                        	
                    	    <div class="rx-model-box">
						        	<h2>服务承诺</h2>
						            <div class="rx-promise">
						            	<img src="http://img.zdfei.com/static/image/temp/20160322/276f55304d215816571d75f069196d26.png" />
						            </div>
						    </div>
						    <!--div style="width:100%;height:10px;background:#f5f5f5;"></div>
						    <div class="rx-model-box2">
						        	<h2>服务优势</h2>
						            <div class="rx-advantage">
						            	<img src="http://img.zdfei.com/static/image/temp/20160418/3eafafc15856d04e7a08243ebb35baf4.jpg" />
						            </div>
						    </div-->
                        	
                        </div>
                    </div>
                    <div class="swiper-slide" id="big_content_2">
                        <div class="content-slide" style="padding:8px;">
                        <#if goods.instructions?exists>
							${goods.instructions?default('')}
				    	<#else>
				    		<p><img src="http://img.zdfei.com/static/image/temp/20160628/a34c91030e0c635210b9363b4fede585.jpg" alt="普通商品说明书"/></p>
				    	</#if>
                        </div>
                    </div>
                    <div class="swiper-slide" id="big_content_3">
                        <div class="content-slide">
                            <article class="comment-percent-count clearfix">
                                <span class="comment-percent">商品满意度<b class="show-percent">100%</b></span>
                                <span class="comment-count">共<b class="show-count">0</b>人评论</span>
                            </article>
                            <article class="comment-level clearfix">
                                <ul class="comment-grade clearfix">
                                    <li class="current"><a id="good_cri">好评</a></li>
                                    <li><a id="better_cri">中评</a></li>
                                    <li><a id="poor_cri">差评</a></li>
                                </ul>
                            </article>
                            <ul class="content" id="content1">
                            </ul>
                            <ul class="content" id="content2" style="display:none;">
                            </ul>
                            <ul class="content" id="content3" style="display:none;">
                            </ul>
                        </div>
                        <a class="more_cri">查看更多评论>>&nbsp;</a>
                    </div>
                </div>
            </div>
        </div>
      </article>
      
      <!-- 底部 -->
	  <div class="new-details-btn-fixed">
        <ul class="clearfix">
            <li>
            	<a href="#">
                	<b class="iconfont" id="collect-btn">H</b>
                    <p>关注</p>
                </a>
            </li>
            <li>
            	<a onclick="gocart()" class="end">
            		<span class="count-msg">0</span>
                	<b class="iconfont" id="iconfont">C</b>
                    <p>购物车</p>
                </a>
            </li>
            <li>
            	<a class="details-pro-into-cart">加入购物车</a>
            </li>
        </ul>
    </div>
<a href="#" class="back-to-top"></a>
<div class="mask_div_img"></div>
<!-- 环信客服接口 -->
<script src='//kefu.easemob.com/webim/easemob.js?tenantId=2461&hide=true' async='async'></script>
<a href="javascript:;" class="kefu" onclick="easemobIM()" tenantId=2461></a>
<script type="text/javascript" src="/web/js/??idangerous.swiper.min.js,pinchzoom.js,normal_goodsdetail_new.js"></script>
</body>
</html>
