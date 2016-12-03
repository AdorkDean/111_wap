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
    <title>积分中心</title>
   <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <style rel="stylesheet" type="text/css">
        .health-in-money{padding-top:44px; overflow: hidden; font-family: "Microsoft YaHei", Arial, Helvetica, verdana, sans-serif;}
        .list-link{height:40px; padding:0 5%; position: relative; line-height:40px; border-bottom:1px solid #d7d7d7; transition: all 0.4s ease; font-family:"宋体";}
        .fa-chevron-down{position: absolute; top:50%; margin-top:-7px; right:5%; height:14px; width:8px; transform:rotate(-270deg); background: url("../web/images/health_arrow_right.png") no-repeat; background-size: 8px; transition: all 0.4s ease;}
        .list-link i{display: none;position: absolute; left:0; top:11px; height:20px; width:2px; background: #00b8c9;}
        .list-sub-menu{display:none;}
        .tel-data{ height:40px; border-top:1px solid #d7d7d7; border-bottom:1px solid #d7d7d7; background:#FFF;position: relative; padding:10px 5%; margin-top:-1px;}
        .tel-data span{line-height:20px; font-size:15px; font-weight: bold;  }
        .tel-data p{line-height:20px;font-size:14px;  color:#979797;}
        .tel-data b{position: absolute; display: block; top:50%; right:5%;font-size:15px; margin-top:-20px; height:40px; line-height:40px; color:#ff2828;font-weight: bold;}
        .tel-data b.b-blue{color:#5877db;}
        .in-open .list-sub-menu{display: none;}
        .in-open .fa-chevron-down{ transform:rotate(-90deg);}
        .in-open i{display: block;}
        .more-btn{height:50px; border-bottom:1px solid #d7d7d7;}
        .more-btn input{display: block;height:50px;line-height:50px; margin:0 auto; }
        .integral-bg{position: relative;height:75px; color:#FFF; background: url("../web/images/integral_bg.jpg") no-repeat; background-size: cover;}
        .integral-bg p{height:30px;  font-size: 19px; padding-left:60px; padding-top:15px;}
        .integral-bg span{display: block; padding-left:80px; background: url("../web/images/integral_icon.png") no-repeat 60px center ; background-size: 13px;}
        .integral-bg a{position: absolute; display: block; top:24px; right:44px; height:25px; width:75px; line-height:25px; text-align: center; background: #FFF; color:#fe2f14; font-size:12px; border-radius:5px;}
    </style>
</head>
<body>
<header class="header" style="border-bottom:0;">
    <a href="${base}/member/profile!index.action" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">积分</h2>
</header>
<article class="health-in-money">
    <div class="integral-bg">
        <p>${jifen?default(0)}</p>
        <span>剩余积分</span>
        <a href="${base}/member/profile!pointExchange.action">兑换积分</a>
    </div>
    <ul class="in-accordion">
        <li>
            <div class="list-link">
                最近积分
                <b class="fa-chevron-down"></b>
                <i></i>
            </div>
            <div class="list-sub-menu clearfix">
            <#if result.statusCode?default('0')!='0'>
           <#list  result.list?if_exists as resc>
                <div class="tel-data">
                    <span>${resc.remark?default('')}</span>
                    <p>${resc.create_date?default('')}</p>
                    <#if resc.integral?default(0)&gt;0>
                      <b> +${resc.integral?default(0)}</b>
                    <#elseif resc.integral?default(0)==0> 
                    <b class="b-blue">-${resc.integral?default(0)}</b> 
                    <#else>
                   <b class="b-blue">${resc.integral?default(0)}</b>
                    </#if>
                </div>
                </#list>
                </#if>
            </div>
        </li>
    </ul>
    <div class="list-link">
        <a  href="${base}/member/jifen!getIntegralList.action" style="display: block;">
            更多记录
            <b class="fa-chevron-down" style="  transform: rotate(0deg);"></b>
        </a>
    </div>
</article>
</body>
<script type="text/javascript">
    $(function() {
        var Accordion = function(el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            // Variables privadas
            var links = this.el.find('.list-link');
            // Evento
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
        }
        Accordion.prototype.dropdown = function(e) {
            var $el = e.data.el;
            $this = $(this),
                    $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('in-open');
            if (!e.data.multiple) {
                $el.find('.list-sub-menu').not($next).slideUp().parent().removeClass('in-open');
            };
        }
        var accordion = new Accordion($('.in-accordion'), false);
    });
</script>
<script type="text/javascript">

</script>
</html>

