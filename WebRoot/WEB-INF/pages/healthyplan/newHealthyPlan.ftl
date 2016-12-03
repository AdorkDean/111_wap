<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="keywords" content="" />
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
<title><#if obj.name?length gt 10 >${obj.name[0..10]?default('')}...<#else>${obj.name?default('')}</#if></title>
<#assign ui1 = "http://img.zdfei.com"/>
<link href="/web/css/??common.css,iconfont.css,newHealthyPlan.css" rel="stylesheet" type="text/css" />
</head>
<body>

<header class="header">
    <a id="backListPage" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">${obj.categoryName?default('')}</h2>
    <a href="javascript:;" class="iconfont top-right-btn"><span id="shareBtnByhealthy">分享</span></a>
</header>

<div class="care-main pt45">
    <input type="hidden" id="csum" value="${obj.csum}"/>
    <input type="hidden" id="hid" value="${obj.id?c}"/>
    <input type="hidden"  id="p_curPage"  value="0" >
	<input type="hidden"  id="categoryid" value="${obj.goodsCategoryId?c}"/>
	<input type="hidden" id="shareImageUrl" value="${ui1}${obj.shareImageUrl}"/>
	<input type="hidden" id="shareWords" value="${obj.shareWords}"/>
	<input type="hidden" id="bannerImgUrl" value="${ui1}${obj.bannerImgUrl}"/>
    
	<input type="hidden" value="${obj.lisths?size}" id="priceLooperNumber"/>
<#list obj.lisths?if_exists as lisths>
 	<#if lisths_index == 0>
	<div class="care-part clearfix">
	
	<!--Banner图-开始-->
	<#if obj.bannerImgUrl?default('')!=''>
    	<div class="care-adv-tit"><img src="${ui1}${obj.bannerImgUrl}"></div>
	</#if>
	<!--Banner图-结束-->
	
	<!--滑动菜单-开始-->
	<nav class="health-nav-box wrapper3">
            <div class="health-nav-box-list">
                <ul id="newscroller">
                </ul>
            </div>
    </nav>
	<!--滑动菜单-结束-->
	
	<!--症状介绍-开始-->
    <div class="symptom clearfix">
        <a href="javascript:void(0)" class="out-details" onclick="zhankai(1,0);">
        <input type="hidden" id="exp0" value="${obj.explain}"/>
            <span class="symptom-tag">症状介绍</span>
            <div class="symptom-text clearfix">
                <span class="symptom-corner fr"></span>
	            <#if obj.explain?length gt 50 >
	           		<p>${obj.explain[0..50]?default('')}...</p>
			  	<#else>
					<p>${obj.explain?default('')}</p>
			   </#if>
            </div>
        </a>
     </div>
	<!--症状介绍-结束-->
        
    <!--推荐套餐-START-->
    <input type="hidden" id="bigSize" value="${obj.listhc?size}"/>
    <#list obj.listhc?if_exists as listhc>
    <article class="package-tj">
    	
    	<h2 class="package-tj-title clearfix">
        	<span>药师推荐</span>${listhc.cname}
        </h2>
        <div class="wrapper wrapper${listhc_index}">
                <div class="scroller" style="width:${listhc.gsum*100}px">
                    <ul class="recomm-pro-plus clearfix">
                	<input type="hidden" id="smallSize_${listhc_index}" value="${listhc.listc?size}"/>
                    <#list listhc.listc?if_exists as listc>
                        <li>
                            <a class="recomm-a" onclick="product(${listc.gid?c},&quot;${listc.sname}&quot;);">
                                <img src="${ui1}${listc.gImgUrl}"/>
                                <p class="recomm-pro-tit">${listc.sname}</p>
                                <input type="hidden" id="wap_price_${listhc_index}_${listc_index}" value="${listc.wapPrice?c}" />
                                <input type="hidden" id="old_price_${listhc_index}_${listc_index}" value="${listc.price?c}" />
                                <input type="hidden" id="goods_id_${listhc_index}_${listc_index}" value="${listc.gid?c}" />
                                <input type="hidden" id="goods_name_${listhc_index}_${listc_index}" value="${listc.sname}" />
                            </a>
                            <span>+</span>
                        </li>
                    </#list>
                    </ul>
                </div>
			</div>
			
			<div class="symptom sy-comment clearfix" style="border-bottom:#efefef 1px solid;">
            <a class="out-details" onclick="zhankai(2,${listhc_index});">
            <input type="hidden" id="ccexp${listhc_index}" value="${listhc.commentContent}"/>
                <span class="sy-comment-tag">
                	<img class="ys_head_url" src="${ui1}${listhc.headUrl?default('')}"/>
                	<span class="ys_real_name">${listhc.realName?default('')}</span>
                	<span class="ys_work_year">从业${listhc.workYear?default('10')}年</span>
                </span>
                <span class="arrow_right_h"></span>
                <div class="symptom-text clearfix">
                    <span class="symptom-corner fr"></span>
		            <#if listhc.commentContent?length gt 50 >
		           		<p>${listhc.commentContent[0..50]?default('')}...</p>
				  	<#else>
						<p>${listhc.commentContent?default('')}</p>
				   </#if>
                </div>
            </a>
        </div>
			
        <div class="price-box clearfix">
        	<div class="total-price">
            	<span>共节省</span><b id="totalSave_${listhc_index}">¥0</b><span>组合套餐</span><b id="combinePrce_${listhc_index}">¥0</b>
                <p>原价<i id="oldPrice_${listhc_index}">¥0</i></p>
            </div>
            <a href="javascript:;" onclick="healthysAddCart(${listhc_index})" class="sy-buy">立即购买</a>
        </div>
    </article>
    </#list>
    <!--推荐套餐-END-->
	</#if>
	

    <div class="recomm-product">
	<#if lisths_index == 0>
    	<h2 class="package-tj-title clearfix">
            <span>单品用药</span>主用药
        </h2>
	<#else>
		<div class="symptom clearfix">
            <a class="out-details" onclick="zhankai(1,${lisths_index});">
            <input type="hidden" id="exp${lisths_index}" value="${lisths.sexplain?default('')}">
                <span class="symptom-tag">${lisths.sname?default('')}</span>
                <div class="symptom-text clearfix">
                    <span class="symptom-corner fr"></span>
		           	<p>${lisths.sexplain?default('')}</p>
                </div>
            </a>
        </div>
	</#if>
    
	<#if (lisths.gsum==1)>
        <div class="recomm-one clearfix">
            <div class="recomm-one-img"><a onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
            <div class="recomm-one-info">
                <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}<span>${lisths.lists[0].mainTitle}</span></p>
            	<p class="recomm-two-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}" id="goods_price_one_in_${lisths_index}">¥${lisths.lists[0].wapPrice}</p>
            	<input type="hidden" value="${lisths.lists[0].gid?c}" id="goods_id_one_in_${lisths_index}"/>
                <p class="original-cost">¥${lisths.lists[0].price}</p>
                <a class="new-car-red" onclick="healthyAddCart(1,${lisths.lists[0].gid?c})" style="margin-right:15px;"></a>
            </div>
        </div>
	</#if>
        	
	<#if (lisths.gsum==2)>
		<input type="hidden" value="${lisths.gsum}" id="obj_two_${lisths_index}"/>
        <div class="recomm-two clearfix">
        <div class="c-line"></div>
        	<ul class="clearfix">
    		<input type="hidden" value="${lisths.lists?size}" id="obj_two_size_${lisths_index}"/>
        	<#list lisths.lists?if_exists as objs>
            	<li>
                	<a  class="recomm-a" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);"><img src="${ui1}${objs.gImgUrl}"></a>
                    <div class="recomm-two-info">
                        <p class="recomm-two-title" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);">${objs.gname}<span>${objs.mainTitle}</span></p>
                        <p class="recomm-two-price bszjg${lisths.sid?c}_${objs.gid?c}" id="goods_price_two_in_${lisths_index}_${objs_index}">¥${objs.wapPrice}</p>
                		<input type="hidden" value="${objs.gid?c}" id="goods_id_two_in_${lisths_index}_${objs_index}"/>
                		<p class="original-cost">¥${objs.price}</p>
                		<a class="new-car-red" onclick="healthyAddCart(1,${objs.gid?c})" style="margin-right:15px;"></a>
                    </div>
                </li>
             </#list>
             </ul>
         </div>
	 </#if>
        	
	<#if (lisths.gsum==3)>
		<input type="hidden" value="${lisths.gsum}" id="obj_three_${lisths_index}"/>
		<div class="recomm-one clearfix">
            <div class="recomm-one-img"><a onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
            <div class="recomm-one-info">
                <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}<span>${lisths.lists[0].mainTitle}</span></p>
                <p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}" id="goods_price_three_in_1_${lisths_index}">¥${lisths.lists[0].wapPrice}</p>
                <input type="hidden" value="${lisths.lists[0].gid?c}" id="goods_id_three_in_1_${lisths_index}"/>
                <p class="original-cost">¥${lisths.lists[0].price}</p>
                <a class="new-car-red" onclick="healthyAddCart(1,${lisths.lists[0].gid?c})" style="margin-right:15px;"></a>
            </div>
        </div>
    	<div class="c-line" style="height:0px;"></div>
        <div class="recomm-two clearfix">
        	<ul class="clearfix">
            	<li>
                	<a class="recomm-a" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);"><img src="${ui1}${lisths.lists[1].gImgUrl}"></a>
                    <div class="recomm-two-info">
                        <p class="recomm-one-title" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);">${lisths.lists[1].gname}<span>${lisths.lists[1].mainTitle}</span></p>
                    	<p class="recomm-two-price bszjg${lisths.sid?c}_${lisths.lists[1].gid?c}" id="goods_price_three_in_2_${lisths_index}">¥${lisths.lists[1].wapPrice}</p>
                       	<input type="hidden" value="${lisths.lists[1].gid?c}" id="goods_id_three_in_2_${lisths_index}"/>
                        <p class="original-cost">¥${lisths.lists[1].price}</p>
                        <a class="new-car-red" onclick="healthyAddCart(1,${lisths.lists[1].gid?c})" style="margin-top:-20px;"></a>
                    </div>
                </li>
                <li>
                	<a class="recomm-a" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);"><img src="${ui1}${lisths.lists[2].gImgUrl}"></a>
                    <div class="recomm-two-info">
                        <p class="recomm-one-title" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);">${lisths.lists[2].gname}<span>${lisths.lists[2].mainTitle}</span></p>
                   		<p class="recomm-two-price bszjg${lisths.sid?c}_${lisths.lists[2].gid?c}" id="goods_price_three_in_3_${lisths_index}">¥${lisths.lists[2].wapPrice}</p>
                   		<input type="hidden" value="${lisths.lists[2].gid?c}" id="goods_id_three_in_3_${lisths_index}"/>
                        <p class="original-cost">¥${lisths.lists[2].price}</p>
                        <a class="new-car-red" onclick="healthyAddCart(1,${lisths.lists[2].gid?c})" style="margin-top:-20px;"></a>
                    </div>
                </li>
            </ul>
        </div>
	</#if>
    </div>
    </div>
    </#list>
    
    
    <#if obj.reminder?default('')!=''>
	    <div class="care-tips clearfix">
	    	<h2>温馨提示：</h2>
	        <p>${obj.reminder}</p>
	    </div>
	</#if>

	<div class="center-line"><p>继续拖动,查看更多商品</p></div>
	<div class="product-list-all">
	    <ul class="product-list-box clearfix" id="goodsList">
	    </ul>
	</div>
	    
</div>

<div class="b-cover">
	<div class="describ">
    	<div class="describ-tit"><a onclick="closeDocument()" class="describ-x"></a>详细描述</div>
        <div class="describ-details"></div>
    </div>
</div>

<script type="text/javascript" src="/web/js/??jquery.min.js,iscroll.js,alert.main.js,cookieUtil.js,common.new.js,goodList.js,jweixin-1.0.0.js,newHealthyPlan.js"></script>
<div style="position:relative;top:45px;">
<!--#include virtual="/static/inc/wap/footer.html" -->
</div>
<div class="mask-ui"></div>
<img class="share-img" src="/web/images/share.png"/>
<div class="loading-img" id="loadimg"><img src="/web/images/loading_img.jpg"/></div>
</body>
</html>

