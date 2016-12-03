<!doctype html>
<html lang="zh-CN" ng-app="myApp" ng-controller="healthPlanController">
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
<title><#if obj.name?length gt 10 >${obj.name[0..10]?default('')}...<#else>${obj.name?default('')}</#if></title>
<#assign wap = "http://wap.portal.com/"/>
<#assign ui1 = "http://img.zdfei.com/"/>
<link href="${wap}/web/css/??common.css,iconfont.css,healthyPlanContentAPP.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="care-main">
	<input type="hidden"  id="app_categoryid" value="${obj.goodsCategoryId?c}"/>
    <input type="hidden" id="csum" value="${obj.csum}"/>
    <input type="hidden" id="hid" value="${obj.id?c}"/>
    <input type="hidden" value="${obj.lisths?size}" id="priceLooperNumber"/>
<#list obj.lisths?if_exists as lisths>
 	<#if lisths_index == 0>
		<div class="care-part clearfix">
		<#if obj.bannerImgUrl?default('')!=''>
	    	<div class="care-adv-tit"><img src="${ui1}${obj.bannerImgUrl?default('')}"></div>
		</#if>
		
		<!----------------------------------------------------滑动菜单START---------------------------------------------------->
		<nav class="health-nav-box wrapper3">
	            <div class="health-nav-box-list">
	                <ul id="newscroller">
	                </ul>
	            </div>
	    </nav>
		<!----------------------------------------------------滑动菜单END---------------------------------------------------->
		
		
		<!----------------------------------------------------症状描述START-------------------------------------------------->
		<#if obj.explain?default('')!=''>
	        <div class="zzms-ui-c">
	            <input type="hidden" id="exp-0" value="${obj.explain?default('')}"/>
	            <div class="zz-title-m-s"><span>症状描述</span></div>
	            <div class="symptom-text clearfix bszz-new-c-contents" style="border-bottom:none;padding-bottom:0px;">
	            <#if obj.explain?length gt 50 >
		           		<p id="old-c-id-1-0">${obj.explain[0..50]?default('')}...</p>
		           		<span class="bszz-arrow-down-erea" id="b-a-d-e-0" onclick="show_content(1,0);"></span>
				  	<#else>
						<p id="old-c-id-2-0">${obj.explain?default('')}</p>
				   </#if>
				   <p id="s-p-s-z-z-0"></p>
				   <span class="bszz-arrow-up-erea" id="b-a-u-e-0" onclick="show_content(2,0);" style="display:none;"></span>
				   </div>
	        </div>
		</#if>
		<!----------------------------------------------------症状描述END-------------------------------------------------->
		
        <!--分割线START-->
		<div class="split-line-ui" style="float:left;"></div>
		<!--分割线END-->
		
		<!--------------------------------------------------------------------------推荐套餐START---------------------------------------------------------------->
		<div class="_pha_rec_header"><img src="http://img.zdfei.com/static/image/temp/20160830/9d4054c160a0b5be65082ecea59d09ed.png"></div>
	    <input type="hidden" id="bigSize" value="${obj.listhc?size}"/>
	    <#list obj.listhc?if_exists as listhc>
	    <input type="hidden" id="smallSize_${listhc_index}" value="${listhc.listc?size}"/>
	    <article class="package-tj">
	    	<div class="_pha_meal_title clearfix <#if listhc_index !=0>is-not-first-one</#if>">${listhc.cname?default('')}</div>
	    	<div class="_pha_meal_man <#if listhc_index !=0>has-border-top</#if>">
                    <dl class="_pha_meal_dl clearfix">
                        <img class="_meal_dl_dt" src="${ui1}${listhc.headUrl?default('')}" />
                        <dd class="_meal_dl_dd _meal_dl_dd1">${listhc.realName?default('')}</dd>
                        <dd class="_meal_dl_dd _meal_dl_dd2"><b class="_work_five">从业${listhc.workYear?default('10')}年</b>${listhc.remark?default('擅长男性疾病')}</dd>
                    </dl>
                    <a class="_consult_btn" onclick="easemobIM()" tenantId=2461>
                        <span class="_consult_btn_sign"></span>咨询
                    </a>
            </div>
	    	
	        <div class="_pha_meal_pro">
	       		<#if listhc.listc?exists>
	       			<#if listhc.listc?size gt 2><span class="_left_notice_touch">${listhc.listc?size}</span></#if>
	       		</#if>
                <div id="_wraper"></div>
                <div class="_product_box clearfix swiper-container swiper-container-horizontal swiper-container-free-mode">
                    <div class="swiper-wrapper">
                        <#list listhc.listc?if_exists as listc>
                        <div class="swiper-slide" style="width:131px;margin-right:30px;">
                        	<#if listc_index != listhc.listc?size -1>
                            <span class="_product_add_sign"></span>
                            </#if>
                            <a onclick="product(${listc.gid?c},&quot;${listc.sname}&quot;);">
                                <div class="_subject_box">
                                    <div class="_product_img">
                                        <img src="${ui1}${listc.gImgUrl?default('http://img.zdfei.com/static/image/temp/20160831/5c08e5934b6ee6686713526a92b036e6.jpg')}">
                                    </div>
                                    <div class="_product_title" <#if listc.type != 1 && listc.type != 4>style="width:70%;"</#if>>${listc.sname?default('')}</div>
                                    <div class="_product_detail" <#if listc.type != 1 && listc.type != 4>style="width:70%;"</#if>>${listc.spec?default('')}</div>
                                    <#if listc.type != 1 && listc.type != 4>
                                    <span class="_submit_btn" style="background-size:25px;"></span>
                                    </#if>
                                    <input type="hidden" id="app_price_${listhc_index}_${listc_index}" value="${listc.appPrice?c}"/>
	                                <input type="hidden" id="old_price_${listhc_index}_${listc_index}" value="${listc.price?c}"/>
	                                <input type="hidden" id="goods_id_${listhc_index}_${listc_index}" value="${listc.gid?c}"/>
	                                <input type="hidden" id="goods_name_${listhc_index}_${listc_index}" value="${listc.sname}"/>
                                </div>
                            </a>
                        </div>
                        </#list>
                    </div>
                </div>
            </div>
	        
	        <div class="zzms-ui-c">
	            <input type="hidden" id="_exp-${listhc_index}" value="${listhc.commentContent?default('')}"/>
	            <div class="symptom-text clearfix bszz-new-c-contents" style="border-bottom:none;padding-bottom:0px;">
	            <#if listhc.commentContent?length gt 50 >
		           		<p id="_old-c-id-1-${listhc_index}">${listhc.commentContent[0..50]?default('')}...</p>
		           		<span class="bszz-arrow-down-erea" id="_b-a-d-e-${listhc_index}" onclick="show_content(3,${listhc_index});"></span>
				  	<#else>
						<p id="_old-c-id-2-${listhc_index}">${listhc.commentContent?default('')}</p>
				   </#if>
				   <p id="_s-p-s-z-z-${listhc_index}"></p>
				   <span class="bszz-arrow-up-erea" id="_b-a-u-e-${listhc_index}" onclick="show_content(4,${listhc_index});" style="display:none;"></span>
				 </div>
	        </div>
	        
	        <#if listhc.cname?default('') != '处方药'>
	        <div class="_pha_meal_price">
                <p class="fr"><strong class="_old_price">原价</strong><s class="_old_pricenumber" id="oldPrice_${listhc_index}">¥0</s><span class="_save_total">共节省</span><b class="_save_price" id="totalSave_${listhc_index}">¥0</b><span class="_save_total">组合套餐</span><b class="_save_price _save_price1" id="combinePrce_${listhc_index}">¥0</b></p>
            </div>
	        <div class="_pha_meal_buy" style="border:#ffffff 1px solid;">
                <span class="_buy_now_btn" type="button" onclick="addTao(2,${listhc.cid?c})">立即购买</span>
            </div>
	        </#if>
	    </article>
        
        <!--分割线START-->
		<div class="split-line-ui" style="float:left;"></div>
		<!--分割线END-->
	    </#list>
	    <!--------------------------------------------------------------------------推荐套餐END---------------------------------------------------------------->
		
	</div>
    </#if>
    
    <#if lisths_index == 0>
    <!----------------------------------------------------------------单品用药START----------------------------------------------------------------------->
        <div class="symptom clearfix" style="height:70px;padding-top:13px;background:#fff;">
			<img class="dpyy-banner-img" src="http://img.zdfei.com/static/image/temp/20160830/ff01f234e7954addb0c4a9d56dadb80b.png"/>
            <a href="javascript:;" class="out-details">
            	<input type="hidden" id="exp0" value="${obj.explain?default('')}"/>
            	<div class="zzjs_title_ui"><span class="left">主用药</span><span class="right">主用药</span></div>
            </a>
        </div>
	<!----------------------------------------------------------------单品用药END----------------------------------------------------------------------->
    </#if>
    
    
    <div class="care-part clearfix" <#if lisths_index !=0>style="margin-top:10px;"</#if> >
		
	<#if lisths.sexplain?default('')!=''>
        <div class="symptom clearfix">
            	<input type="hidden" id="exp-${lisths_index}" value="${lisths.sexplain?default('')}"/>
            	<div class="zzjs_title_ui"><span class="left">伴随症状</span><span class="right">${lisths.sname?default('')}</span></div>
                <div class="symptom-text clearfix bszz-new-c-contents">
		            <#if lisths.sexplain?length gt 50 >
		           		<p id="old-c-id-1-${lisths_index}">${lisths.sexplain[0..50]?default('')}...</p>
		           		<span class="bszz-arrow-down-erea" id="b-a-d-e-${lisths_index}" onclick="show_content(1,${lisths_index});"></span>
				  	<#else>
						<p id="old-c-id-2-${lisths_index}">${lisths.sexplain?default('')}</p>
				   </#if>
				   <p id="s-p-s-z-z-${lisths_index}"></p>
				   <span class="bszz-arrow-up-erea" id="b-a-u-e-${lisths_index}" onclick="show_content(2,${lisths_index});" style="display:none;"></span>
                </div>
        </div>
	 </#if>
            
            <div class="recomm-product">
        	<#if (lisths.gsum==1)>
	            <div class="recomm-one clearfix">
	                <div class="recomm-one-img"><a href="javascript:;" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
	                <div class="recomm-one-info">
	                    <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}</p>
	                    <p class="recomm-one-title">${lisths.lists[0].mainTitle}</p>
	                    <p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}" id="goods_price_one_in_${lisths_index}">¥${lisths.lists[0].appPrice}</p>
	                    <del class="recomm-one-price" style="color:#999999;left:15px;position:relative;">¥${lisths.lists[0].price}</del>
	                    <#if lisths.lists[0].type != 1 && lisths.lists[0].type != 4>
	                    	<span class="_add_cart_btn_n_w" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"></span>
	                    	<#else>
	                    	<span class="add_cart_btn_n_w" onclick="addTao(1,${lisths.lists[0].gid?c})"></span>
	                    </#if>
		            	<input type="hidden" value="${lisths.lists[0].gid?c}" id="goods_id_one_in_${lisths_index}"/>
	                </div>
	            </div>
	        	<div class="c-line"></div>
        	</#if>
        	
        	<#if (lisths.gsum==2)>
        	    <input type="hidden" value="${lisths.gsum}" id="obj_two_${lisths_index}"/>
	            <div class="recomm-two clearfix">
	            	<ul class="clearfix">
	            	<input type="hidden" value="${lisths.lists?size}" id="obj_two_size_${lisths_index}"/>
	            	<#list lisths.lists?if_exists as objs>
	                	<li>
	                    	<a href="javascript:;" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);"><img src="${ui1}${objs.gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-two-title" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);">${objs.gname}</p>
	                            <p class="recomm-two-title">${objs.mainTitle}</p>
	                            <p class="recomm-two-price bszjg${lisths.sid?c}_${objs.gid?c}" id="goods_price_two_in_${lisths_index}_${objs_index}">¥${objs.appPrice}</p>
	                           	<del class="recomm-one-price" style="color:#999999;left:15px;position:relative;">¥${objs.price}</del>
	                    		<#if objs.type != 1 && objs.type != 4>
			                    	<span class="_add_cart_btn_n_w" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);"></span>
			                    	<#else>
				                    <span class="add_cart_btn_n_w" onclick="addTao(1,${objs.gid?c})"></span>
			                    </#if>
		                		<input type="hidden" value="${objs.gid?c}" id="goods_id_two_in_${lisths_index}_${objs_index}"/>
	                        </div>
	                    </li>
                    </#list>
	                </ul>
	            </div>
        	</#if>
        	
        	<#if (lisths.gsum==3)>
        	    <input type="hidden" value="${lisths.gsum}" id="obj_three_${lisths_index}"/>
        		<div class="recomm-one clearfix">
	                <div class="recomm-one-img"><a href="javascript:;" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
	                <div class="recomm-one-info new-goods-s-a">
	                    <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}</p>
	                    <p class="recomm-one-title">${lisths.lists[0].mainTitle}</p>
	                    <p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}" id="goods_price_three_in_1_${lisths_index}">¥${lisths.lists[0].appPrice}</p>
	                    <del class="recomm-one-price" style="color:#999999;left:15px;position:relative;">¥${lisths.lists[0].price}</del>
	                    <#if lisths.lists[0].type != 1 && lisths.lists[0].type != 4>
	                    	<span class="_add_cart_btn_n_w" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"></span>
	                    	<#else>
		                    <span class="add_cart_btn_n_w" onclick="addTao(1,${lisths.lists[0].gid?c})"></span>
	                    </#if>
		                <input type="hidden" value="${lisths.lists[0].gid?c}" id="goods_id_three_in_1_${lisths_index}"/>
	                </div>
	            </div>
	        	<div class="c-line"></div>
	            <div class="recomm-two clearfix">
	            	<ul class="clearfix">
	                	<li>
	                    	<a href="javascript:;" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);"><img src="${ui1}${lisths.lists[1].gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-one-title" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);">${lisths.lists[1].gname}</p>
	                            <p class="recomm-one-title">${lisths.lists[1].mainTitle}</p>
	                    		<p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[1].gid?c}" id="goods_price_three_in_2_${lisths_index}">¥${lisths.lists[1].appPrice}</p>
	                    		<del class="recomm-one-price" style="color:#999999;left:15px;position:relative;">¥${lisths.lists[0].price}</del>
	                    		<#if lisths.lists[1].type != 1 && lisths.lists[1].type != 4>
			                    	<span class="_add_cart_btn_n_w" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);"></span>
			                    	<#else>
				                    <span class="add_cart_btn_n_w" onclick="addTao(1,${lisths.lists[1].gid?c})"></span>
			                    </#if>
                       			<input type="hidden" value="${lisths.lists[1].gid?c}" id="goods_id_three_in_2_${lisths_index}"/>
	                        </div>
	                    </li>
	                    <li>
	                    	<a href="javascript:;" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);"><img src="${ui1}${lisths.lists[2].gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-one-title" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);">${lisths.lists[2].gname}</p>
	                            <p class="recomm-one-title">${lisths.lists[2].mainTitle}</p>
	                    		<p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[2].gid?c}" id="goods_price_three_in_3_${lisths_index}">¥${lisths.lists[2].appPrice}</p>
	                    		<del class="recomm-one-price" style="color:#999999;left:15px;position:relative;">¥${lisths.lists[2].price}</del>
	                    		<#if lisths.lists[2].type != 1 && lisths.lists[2].type != 4>
			                    	<span class="_add_cart_btn_n_w" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);"></span>
			                    	<#else>
				                    <span class="add_cart_btn_n_w" onclick="addTao(1,${lisths.lists[2].gid?c})"></span>
			                    </#if>
                   				<input type="hidden" value="${lisths.lists[2].gid?c}" id="goods_id_three_in_3_${lisths_index}"/>
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
	        <p>${obj.reminder?default('')}</p>
	    </div>
	</#if>
    

</div>

	<div class="center_line_app"><p>继续拖动,查看更多商品</p></div>
	<div class="product-list-all">
	    <ul class="product-list-box clearfix" infinite-scroll='demo.loadMore()' infinite-scroll-distance='1'>
	    	<li ng-repeat="i in demo.items"><a ng-click="demo.view(i.id,i.short_name)" class="list-pro-info"><div class="new-list-pro"><span class="new-discount">{{i.discount}}折</span> <img src="${ui1}{{i.abbreviation_picture}}"></div><p class="new-list-pro-title">{{i.short_name}}</p><p class="new-list-pro-price">¥{{i.price}}</p><p><b class="new-list-pro-original">¥{{i.market_price}}</b></p></a><a ng-click="demo.addCart(i.id, i.type, $index)" class="iconfont new-list-pro-into-cart"></a></li>
	    </ul>
	</div>
	
<div class="b-cover">
	<div class="describ">
    	<div class="describ-tit">
        	<a href="javascript:void(0)" class="describ-x"></a>
                        详细描述
        </div>
        <div class="describ-details">
        </div>
    </div>
</div>
<script type="text/javascript" src="${wap}/web/js/??jquery.min.js,angular-glober-constant.js,iscroll.js,wap-cart.js,alert.main.js,healthyPlanContentAPP.js,swiper.jquery.min.js"></script>
<script type="text/javascript" src="${wap}/web/js/controller/??angular.min.js,ng-infinite-scroll.min.js,app-HealthPlan-Controller.js"></script>
<script src='//kefu.easemob.com/webim/easemob.js?tenantId=2461&hide=true' async='async'></script>
<script>
var mySwiper  = new Swiper('.swiper-container', 
{
    slidesPerView: 2,
    spaceBetween: 30,
    autoplay: 0,
    freeMode: false,
    autoplayDisableOnInteraction: true,
    freeModeFluid : false,
    direction : 'horizontal'
});
</script>
</body>
</html>
