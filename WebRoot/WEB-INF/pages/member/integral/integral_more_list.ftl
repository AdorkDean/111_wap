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
    <!--上线时请删除-->
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <!--上线时请删除-->
    <title>更多记录</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <style rel="stylesheet" type="text/css">
        .health-in-money{padding-top:44px;overflow: hidden; font-family: "Microsoft YaHei", Arial, Helvetica, verdana, sans-serif;}
        .tel-data{ border-top:1px solid #d7d7d7; border-bottom:1px solid #d7d7d7; background:#FFF;position: relative; padding:10px 5%; margin-top:-1px;}
        .list-sub-menu .tel-data:last-child{border-bottom:0;}
        .tel-data span{line-height:24px; font-size:15px; font-weight: bold;  }
        .tel-data p{line-height:20px;font-size:14px;  color:#979797;}
        .tel-data b{position: absolute; display: block; top:50%; right:5%;font-size:15px; margin-top:-20px; height:40px; line-height:40px; color:#ff2828;font-weight: bold;}
        .tel-data b.b-blue{color:#5877db;}
        .more-record{height:20px; padding:10px 0; background: #FFF;}
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
    </style>
</head>
<body>
<header class="header" style="border-bottom:0;">
    <a href="${base}/member/jifen!getThirtyJifen.action" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">更多积分</h2>
</header>
<input type="hidden"  value="${rs.p_curPage?default(1)}" id="p_curPage">
<input type="hidden" name="pageNumber" value="1" id="pageNumber">
<input type="hidden" name="pageNo" value="1" id="pageNo">
<input type="hidden" name="type" value="1" id="type">
<article class="health-in-money">
        <div class="more-record"><span class="current" id="1"><b></b>线上积分</span><span id="2"><b></b>门店积分</span></div>
        <div class="center-line"><i></i></div>
        <div class="list-total">
            <div class="list-sub-menu current clearfix" id="xian">
            <#if resultOnLine.statusCode?default('0')!='0'>
            <#list resultOnLine.data.result?if_exists as resc>
                <div class="tel-data">
                    <span>${resc.remark?default('')}</span>
                    <p>${resc.createDate?default('')}</p>
                    <#if resc.integral?default(0)&gt;0>
                    <b>+${resc.integral?default(0)}</b>
                    <#elseif resc.integral?default(0)==0>
                     <b class="b-blue">-${resc.integral?default(0)}</b>
                    <#else>
                     <b class="b-blue">${resc.integral?default(0)}</b>
                    </#if>
                </div>
                </#list>
            </#if>
            </div>
            <div class="list-sub-menu clearfix" id="xia">
              <#if result.statusCode?default('0')!='0'>
            <#list result.data.list?if_exists as resc>
                <div class="tel-data">
                    <span>${resc.remark?default('')}</span>
                    <p>${resc.create_date?default('')}</p>
                    <#if resc.integral?default(0)&gt;0>
                    <b>+${resc.integral?default(0)}</b>
                    <#elseif resc.integral?default(0)==0>
                     <b class="b-blue">-${resc.integral?default(0)}</b>
                    <#else>
                     <b class="b-blue">${resc.integral?default(0)}</b>
                    </#if>
                </div>
                </#list>
            </#if>
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
		var pageNumber=parseInt($("#pageNumber").val())+1;
		var pageNo=parseInt($("#pageNo").val())+1;
		if(type==1){
		$("#pageNumber").val(parseInt($("#pageNumber").val())+1);
		}else{
		$("#pageNo").val(parseInt($("#pageNo").val())+1);
		}
				$.post(jsCtx+"/member/jifen!appendOrderListByMemberId.action",{"type":type,"pageNumber":pageNumber,"pageNo":pageNo,"random":Math.random()},function(data){
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

