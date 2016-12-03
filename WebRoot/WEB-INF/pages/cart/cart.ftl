<!doctype html>
<html lang="zh-CN">
<head>
<#include "/WEB-INF/pages/inc/taglibs.ftl">
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
    <title>购物车</title>
    <link href="${base}/web/css/??common.css,iconfont.css,new_footer.css" rel="stylesheet" type="text/css" />
 	<style>
 	.cart-no-pro{width:100%; height:100%; position:absolute; left:0; top:0; right:0; bottom:0; text-align:center;display:-webkit-box;display:-ms-flexbox;display:-webkit-flex;display:flex;-webkit-box-pack:center;-ms-flex-pack:center;-webkit-justify-content:center;justify-content:center;-webkit-box-align:center;-ms-flex-align:center;-webkit-align-items:center;align-items:center;flex:1; z-index:10;}
	a.no-pro-btn{border:1px solid #00b4b8; display: block;  margin:20px 70px 0;border-radius: 6px; text-align: center; line-height:40px; height:40px;font-size:18px !important; color:#00b4b8 !important;}
	.cart-no-pro dl{ -webkit-box-flex: 1; text-align: center; margin-top:-100px;}
	.cart-no-pro img{ width:50%;}
	.cart-no-pro dd{ margin-top: 15px;  color: #c8c8c8; font-size: 24px;}
	.back-to-top {bottom:100px;display:none;}
	.cart-bottom-box {bottom:50px;}
	.kefu {display:none;}
 	</style>
 	
</head>

<html>


<body>
<input type="hidden" id="backurl" value="${url?default('')}"　/>
<header class="header">
    <a href="javascript:void(0)" onclick="myback()" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">购物车</h2>
    <#if l.size() &gt; 0>
    <a href="javascript:void(0);" class="top-right-btn" style="right:2px;"><span id="bianji" >编辑</span></a>
    <#else>
    </#if>
    <#--
    <a href="/" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
    -->
</header>


   <#if l.size() &gt; 0>
    
    <article class="order-box pb70" style="padding-bottom: 120px;">
	<ul class="shopping-cart-list">
	 <#list l?if_exists as c >
	 <li class="clearfix" id="record_${c.itemid?default(0)}">
        	<div class="cart-pro-operate clearfix">
            	<a href="javascript:void(-1)" name="xabtn" onclick="del(${c.itemid?default(0)})" class="iconfont delete-pro fr" style="display:none" >X</a>
            	
            	<#if c.is_selected==1 >
            	<a href="javascript:void(-1)" name="item-checkbox" id="${c.itemid?default(0)}" class="iconfont check-pro check-pro-curr">M</a>
            	<#else>
            	<a href="javascript:void(-1)" name="item-checkbox" id="${c.itemid?default(0)}" class="iconfont check-pro">L</a>
            	</#if>
            	<span style="color:red;">${c.advertise?default('')}</span>
            	
            </div>
                <dl class="cart-pro-info clearfix">
                    <dt><a href="${base}/m/${c.goodsid}.html"><img src="${img_ui1}${c.imageurl?default('')}"></a></dt>
                    <dd>
                    	<div class="order-product">
                            <div class="order-product-unit">
                                <p class="order-product-price">￥${c.wap_price?string("0.00")}</p>
                                <p class="order-product-count" style="display:none">x<span id="count_${c.itemid?default(0)}">${c.quantity?default('1')}</span></p>
                            </div>
                            <div class="order-product-title">
                                <h2><a href="${base}/m/${c.goodsid}.html">${c.goods_name?default('')}</a></h2>
                                <p class="order-product-spec">规格:${c.spec?default('')}</p>
                            </div>
                        </div>
                    </dd>
                </dl>
            <div class="cart-product-count">
            	<a href="javascript:void(-1)" onclick="update(${c.itemid},${c.goodsid},2,1)" >-</a>
                <input type="text" onKeyUp="validateMaxMoney(this)" maxlength="5" gid="${c.goodsid}" cid="${c.itemid}" value="${c.quantity?default('1')}" name="kuang" id="count_input_${c.itemid?default(0)}"  >
                <a href="javascript:void(-1)" onclick="update(${c.itemid},${c.goodsid},1,1)" >+</a>
                <div  style="display:none"> ${c.quantity?default('')}_${c.itemid}</div>
            </div>
        </li>
        </#list>
    </ul>
    
</article>
<#--
<div class="cart-tips">
	<div class="close-tip-btn fl"><a href="#" class="iconfont">X</a></div>
    <p class="cart-tips-text">--${nostore?default('')}--商品复方双黄连口服液已经被别人抢光了，下次记得再眼疾手快一点哦！！</p>
</div>
-->
<div class="cart-bottom-box clearfix">
    <a href="javascript:void(0);" id="center-btn" class="settle-btn fr">结算</a>
    <div class="settle-info fr" id="qubie" >
    	<p class="settle-peice">合计：￥<span id="money">10</span></p>
        <p>赠送积分：<span id="jifen">10</span></p>
    </div>
    <a href="javascript:void(0)"  class="check-all check-all-curr fl">
    	<b id="allchk" class="iconfont">M</b>全选
    </a>
</div>


    <#else>
        <article class="cart-no-pro">
        <dl>
            <dt><img src="${base}/web/images/member-icon03.png"></dt>
            <dd>
                <p>一个商品都没有<br/>好忧桑啊~</p>
                <p><a href="/" class="no-pro-btn">去逛逛</a></p>    
            </dd>
        </dl>
    </article>
    </#if>
    

<script type="text/javascript" src="${base}/web/js/??jquery-1.7.2.js,alert.main.js,common.js"></script>
<#include "/static/inc/wap/new_footer.html">
<script>
	$(function()
	{
		money();
		checkEmpty();
		islogin();
		checkedStatus();
		//标签定位
		$("#tab_class_3").addClass("current");
	});
</script>
</body>
</html>
