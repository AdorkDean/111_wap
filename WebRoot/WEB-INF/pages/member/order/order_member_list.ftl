<!doctype html>
<html lang="zh-CN">
<head>
<#include "/WEB-INF/pages/inc/taglibs.ftl">
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
    <title>在线订单</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <style rel="stylesheet" type="text/css">
        .health-in-money{padding-top:44px;overflow: hidden; font-family: "Microsoft YaHei", Arial, Helvetica, verdana, sans-serif;}
        .tel-data{  background:#FFF; padding:10px 5%; margin-bottom:15px; overflow: hidden;}
        .list-sub-menu .tel-data:last-child{border-bottom:0;}
        .more-record{height:20px; padding:10px 0; background: #FFF; border-bottom:1px solid #d7d7d7;}
        .more-record span{width:26%; border-bottom:2px solid #FFF; margin:0 12%; float:left; text-align: center; height:20px; line-height:20px; }
        .more-record span b{display:block; float:left; margin-top:2px; margin-right:5px; width:16px; height:16px; background: url("../web/images/more_record.png") no-repeat center center; background-size: 35px 40px;}
        .more-record span:first-child b{ background-position: 0 0;}
        .more-record span:last-child{ width:25.5%; position: relative;}
        .more-record span:last-child b{background-position: 0 -22px;}
        .more-record span.current{color:#00b8c9; border-bottom:2px solid #00b8c9;}
        .more-record span.current:first-child b{background-position: -19px 0;}
        .more-record span.current:last-child b{background-position: -19px -22px;}
        .center-line{position: absolute; top:55px; left:50%; height:16px;}
        .center-line i{display: block; height:16px; border-left:1px solid #d7d7d7; width:0;}
        .list-sub-menu{display: none;}
        .list-total .current{display: block;}
        .order-img{width:65px; height:65px; float:left;margin-right:10px;}
        .order-img img{display: block;width:65px; height:65px;}
        .order-details{float:left;}
        .order-details dl{height:22px; line-height:22px; font-size:14px;}
        .order-details dl dt{float:left; color:#999; margin-right:10px;}
        .order-details dl dd{color:#000; float:left;}
    </style>
</head>
<body>
<header class="header" style="border-bottom:0;">
    <a href="${base}/member/profile!index.action" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">全部订单</h2>
</header>
<input type="hidden"  value="${rs.p_curPage?default(1)}" id="p_curPage">
<input type="hidden" name="pageNumber" value="<#if result.statusCode?default(0)==1>${result.pageInfo.page?default(0)}<#else>0</#if>" id="page">
<input type="hidden" name="pageSize" value="20" id="pageSize">
<input type="hidden" name="type" value="1" id="type">

<article class="health-in-money">
    <div class="more-record"><span class="current" id="1"><b></b>线上订单</span><span id="2"><b></b>门店订单</span></div>
    <div class="center-line"><i></i></div>
    <div class="list-total">
        <div class="list-sub-menu current clearfix" id="xian">
        <#list  pw.result?if_exists as resc>
        <a href="${base}/member/order!detail.action?id=${resc.id?default('')}">
            <div class="tel-data">
                <div class="order-img">
                    <img src="${ui1}${resc.abbreviationPicture?default('')}" alt=""/>
                </div>
                <div class="order-details">
                    <dl>
                        <dt>订单编号</dt>
                        <dd>${resc.orderSn?default('')}</dd>
                    </dl>
                    <dl>
                        <dt>订单金额</dt>
                        <dd>￥${currency(resc.payableAmount?default(0))}</dd>
                    </dl>
                    <dl>
                        <dt>订单状态</dt>
                        <dd>
                        <#if resc.orderStatus?exists && resc.orderStatus == 0>
                                			待支付		                                      		
				                    	</#if>                                    	
				                    	<#if resc.orderStatus?exists && resc.orderStatus == 1>
                                			待发货	                                      		
				                    	</#if>
				                    	<#if resc.orderStatus?exists && resc.orderStatus == 2>
				                        	待收货
				                    	</#if>
				                    	<#if resc.orderStatus?exists && resc.orderStatus == 3>
				                        	已完成
				                    	</#if>
				                    	<#if resc.orderStatus?exists && resc.orderStatus == 4>
				                    		已取消
				                    	</#if>
				                    	<#if resc.orderStatus?exists && resc.orderStatus == 5>
				                    		已过期
				                    	</#if>
				        </dd>
                    </dl>
                </div>
            </div>
            </a>
            </#list>
        </div>
        <div class="list-sub-menu clearfix" id="xia">
        <#list  result.list?if_exists as resc>
        <a href="${base}/storeOrder/storeOrder!storeOrderInfo.action?orderSn=${resc.orderSn?default('')}">
            <div class="tel-data">
                <div class="order-details">
                    <dl>
                        <dt>订单编号</dt>
                        <dd>${resc.orderSn?default('')}</dd>
                    </dl>
                    <dl>
                        <dt>订单金额</dt>
                        <dd>¥${resc.orderAmount?default('')}</dd>
                    </dl>
                    <dl>
                        <dt>订单状态</dt>
                        <dd>${resc.orderStatus?default('')}</dd>
                    </dl>
                </div>
            </div>
            </a>
            </#list>
        </div>
    </div>

</article>
</body>
<script type="text/javascript">
    $(function(){
        $(".more-record span").click(function(){
            $(this).addClass('current').siblings().removeClass('current');
            var type=$(this).attr("id");
            $("#type").val(type);
            flag=true;
            $(".list-total .list-sub-menu:eq("+$(this).index()+")").addClass('current').siblings().removeClass('current');
        })
    })
</script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(window).scroll(function(){
		if(isBottom(this))
		{
		if(flag){
		var type=$("#type").val();
		var p_curPage=parseInt($("#p_curPage").val())+1;
		$("#p_curPage").val(parseInt($("#p_curPage").val())+1);
		var pageSize=$("#pageSize").val();
		var pageNumber=parseInt($("#page").val())+1;
		$("#page").val(parseInt($("#page").val())+1);
				$.post(jsCtx+"/member/orderList!appendOrderListByMemberId.action",{"type":type,"pageNumber":pageNumber,"rs.p_curPage":p_curPage,"random":Math.random()},function(data){
					var content=data;
					if(content=="-1"){
					flag=false;
					}else{
					if(type==1){
					$("#xian").append(content);
					}else{
					$("#xia").append(content);
					}
					}
				});
			}
		}
			});
});
</script>
</html>

