<!DOCTYPE html>
<html lang="en">
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
    <title>分类</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <script type="text/javascript" src="js/jquery.min.js"></script>
<script src="${base}/web/js/zepto.min.js"></script>
<script src="${base}/web/js/zepto.lazyload.min.js"></script>
<script src="${base}/web/js/iscroll.min.js"></script>
<script src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/common.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
</head>
<body>
<header class="header">
    <a href="javascript:;" class="iconfont top-left-btn">B</a>
<#include "/static/inc/wap/searchbox.html">
    <div class="iconfont top-right-btn" id="show-list">
    	G
        <div id="showbox" class="showbox show">
            <i class="triangle"></i>
            <ol>
                <li class="clearfix"><a href="/"><b class="iconfont">h</b><p>首页</p></a>
                <li class="clearfix"><a href="/carts/cart!page.action"><b class="iconfont">s</b><p>购物车</p></a></li>
                <li class="clearfix"><a href="/member/profile.action"><b class="iconfont">m</b><p>我的</p></a>
            </ol>
        </div>
    </div>
</header>
<div class="index_wrap">
    <section class="column-box" style="top:44px;">
        <div class="main_box">
            <nav class="class_list_nav">
                <div class="class_list_nav_box" id="class_list_nav_box" style="overflow: hidden;">
                    <ul style="transition-property: transform; -webkit-transition-property: transform; transform-origin: 0px 0px 0px; transform: translate(0px, -180px) scale(1) translateZ(0px);">
                       <#list listpcm?if_exists as cate>
                        <a name="cate_lev1_${cate.pid?default('')}" rel="${cate.pid?default('')}"><li class="">${cate.name?default('')}</li></a>
                    </#list>
                    </ul>
                </div>
            </nav>
            <div class="class_box">
                <div class="class_box_inner" id="class_box_inner" style="overflow:hidden">
                    <div class="class_box_over">
                    <#list listpcm?if_exists as cate>
                        <div id="cate_lev2_div_${cate.pid?default('')}">
                        	<#list cate.plist?if_exists as cate2>
                            <section class="sort_cont_list">
                                <h3>${cate2.name?default('')}</h3>
                                <div class="sort_cont_menu">
                                    <ul class="clearfix">
                                    <#list cate2.plist?if_exists as cate3>
                                        <li>
                                            <a href="${base}/goods/goodsList!getGoodsList.action?categoryid=${cate3.pid?default('')}">
                                            <img src="${img_ui1}${cate3.imgs?default('')}" data-src="${img_ui1}${cate3.imgs?default('')}">
                                            <p>${cate3.name?default('')}</p>
                                            </a>
                                        </li>
                                     </#list>
                                    </ul>
                                </div>
                            </section>
                           </#list> 
                        </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
  <script src="${base}/web/js/nav.min.js"></script>
</body>
</html>


















