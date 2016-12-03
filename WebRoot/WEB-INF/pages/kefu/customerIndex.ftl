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
    <title>联系客服</title>
   <link href="/web/css/??common.css,iconfont.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="/web/js/??jquery-1.7.2.js"></script>
   <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <style>
    /*my profit*/
	.new-kf{overflow:hidden; border-bottom:1px solid #d7d7d7; padding-top:45px;}
	.list-link{height:36px;padding:0 5%;position:relative;background:#fff;line-height:36px;border-top:1px solid #d7d7d7; border-bottom:1px solid #fff;transition:all .4s ease; margin-top:-1px;}
	.fa-chevron-down{position:absolute;top:50%;margin-top:-7px;right:5%;height:14px;width:8px;transform:rotate(0deg);background:url(images/new-health/health_arrow_right.png) no-repeat;background-size:8px;transition:all .4s ease}
	.list-link i{display:block;position:absolute;left:0;top:11px;height:20px;width:2px;background:#00c9f4}
	.list-sub-menu{display:none}
	.tel-data{height:40px;border-top:1px solid #d7d7d7;border-bottom:1px solid #d7d7d7;background:#FFF;position:relative;padding:10px;margin:-1px 10px 0 10px}
	.tel-data span{line-height:20px;font-size:15px;font-weight:700;color:#666}
	.tel-data p{line-height:20px;font-size:14px;color:#666}
	.tel-data b{position:absolute;display:block;top:50%;right:5%;font-size:15px;margin-top:-20px;height:40px;line-height:40px;color:#ff2828;font-weight:700}
	.tel-data b.b-blue{color:#5877db}
	.in-open .list-sub-menu{display:block}
	.in-open .fa-chevron-down{transform:rotate(90deg)}
	.in-open i{display:block}
	.more-btn{height:50px;border-bottom:1px solid #d7d7d7}
	.more-btn input{display:block;height:50px;line-height:50px;margin:0 auto}
	.for-bold{font-weight:700}
	
	.kf-list li{height:90px; margin:0 10px; padding:0 10px; border-top:1px solid #d7d7d7; margin-top:-1px;}
	.kf-list li img{width:60px; height:60px; display:block; border-radius:50%; -webkit-border-radius:50%; border:1px solid #d7d7d7; float:left; margin:14px 0;}
	.kf-info{padding:10px 0 10px 70px;}
	.kf-info p{ height:18px; line-height:18px; overflow:hidden; word-break:keep-all; white-space:nowrap; text-overflow:ellipsis; color:#222;}
	.kf-btn{height:26px; line-height:26px; display:block; margin:5px 0 0; background:#eafffe; text-align:center; color:#00aaa0; border:1px solid #00aaa0; position:relative;}
	.kf-btn i{width:12px; height:12px; display:inline-block; background:url(${base}/web/images/new/talk_ico.png) no-repeat; background-size:12px; position:absolute; top:7px; left:50%; margin-left:-44px;}
	.kf-btn.cannot{border:1px solid #999; background:#f3f5f7; color:#999;}
	.kf-btn.cannot i{ background:url(${base}/web/images/new/talk_un_ico.png) no-repeat; background-size:12px;}
	.kf-ico{height:18px; width:18px; display:inline-block; float:left; margin:9px 5px 0 0;}
	.kf-ico1{background:url(${base}/web/images/new/kf_ico1.png) no-repeat; background-size:18px;}
	.kf-ico2{background:url(${base}/web/images/new/kf_ico2.png) no-repeat; background-size:18px;}
	.kf-ico3{background:url(${base}/web/images/new/kf_ico3.png) no-repeat; background-size:18px;}
    </style>
</head>
<body class="bg-white">
	<header class="header">
        <a href="javascript:history.go(-1)" class="iconfont top-left-btn">B</a>
        <h2 class="header-title">
            咨询
        </h2>
    </header>
<article class="new-kf">
    <ul class="in-accordion">
        <li class="in-open">
            <div class="list-link">
            	<span class="kf-ico kf-ico1"></span>
            	药师咨询
				<b class="fa-chevron-down"></b>
            </div>
            <div class="list-sub-menu clearfix">
                <ul class="kf-list">
                	<li class="clearfix">
                    	<img src="${base}/web/images/new/kf_img1.png">
                        <div class="kf-info">
                            <p class="kf-name">唐药师</p>
                            <p class="kf-sc">擅长治疗常见病 男性疾病</p>
                            <script src='//kefu.easemob.com/webim/easemob.js?tenantId=2461&hide=true' async='async'></script>
							<a href="javascript:;" class="kf-btn" onclick="easemobIM()" tenantId=2461><i></i>立即咨询</a>
                        </div>
                    </li>
                    <li class="clearfix">
                    	<img src="${base}/web/images/new/kf_img2.png">
                        <div class="kf-info">
                            <p class="kf-name">王药师</p>
                            <p class="kf-sc">擅长治疗常见病 女性疾病</p>
                            <a href="javascript:;" class="kf-btn" onclick="easemobIM()" tenantId=2461><i></i>立即咨询</a>
                        </div>
                    </li>
                    <li class="clearfix">
                    	<img src="${base}/web/images/new/kf_img4.png">
                        <div class="kf-info">
                            <p class="kf-name">王药师</p>
                            <p class="kf-sc">擅长治疗三高（慢性病）女性疾病</p>
                            <a href="javascript:;" class="kf-btn" onclick="easemobIM()" tenantId=2461><i></i>立即咨询</a>
                        </div>
                    </li>
                    <li class="clearfix">
                    	<img src="${base}/web/images/new/kf_img3.png">
                        <div class="kf-info">
                            <p class="kf-name">邢药师</p>
                            <p class="kf-sc">擅长治疗三高（慢性病）男性疾病</p>
                           <a href="javascript:;" class="kf-btn" onclick="easemobIM()" tenantId=2461><i></i>立即咨询</a>
                        </div>
                    </li>
                </ul>
            </div>
        </li>
        <a href="javascript:;" onclick="easemobIM()" tenantId=2461>
	        <li>
	            <div class="list-link">
	            	<span class="kf-ico kf-ico2"></span>
	            	售后咨询
	                <b class="fa-chevron-down"></b>
	            </div>
	        </li>
        </a>
        <a href="tel:400-606-3111" >
	        <li>
	            <div class="list-link">
	            	<span class="kf-ico kf-ico3"></span>
	            	电话咨询
	                <b class="fa-chevron-down"></b>
	            </div>
	        </li>
       </a>
    </ul>
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
</html>

