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
    <!--上线时请删除-->
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <!--上线时请删除-->
    <title>新增收货人地址</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/iconfont.css" rel="stylesheet" type="text/css" />
    <style rel="stylesheet" type="text/css">
        html,body,div,span,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,address,cite,code,del,dfn,em,img,ins,kbd,q,samp,small,strong,sub,sup,var,b,i,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,textarea,input,span,figure,aside,time,figcaption{margin:0;padding:0;border:0;font-size:100%;vertical-align:baseline}
        article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block}
        audio,canvas,video{display:inline-block;*display:inline;*zoom:1}
        audio:not([controls]){display:none}
        html,body{height:100%; position:relative}
        html{font-size:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}
        body{width:100%;font-family:"SimHei",Helvetica,Arial,sans-serif;font-size:14px;color:#000;background:#fff}
        input,textarea{font-family:"SimHei",Helvetica,Arial,sans-serif;color:#000}
        input:-moz-placeholder,textarea:-moz-placeholder{color:#666}
        input:-ms-input-placeholder,textarea:-ms-input-placeholder{color:#666}
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{color:#666;}
        a{color:#0d0d0d;text-decoration:none;/*-webkit-tap-highlight-color:rgba(0,0,0,0.2);*/-webkit-tap-highlight-color:transparent}
        a:hover{text-decoration:none}
        a:focus{outline:thin dotted}
        a:hover,a:active{outline:0}
        textarea,input{resize:none;outline:0;font-size:100%;-webkit-tap-highlight-color:rgba(255,0,0,0)}
        textarea{resize:none;-webkit-appearance:none}
        ul,ol{list-style:none}
        em{font-style:normal}
        h1,h2,h3,h4,h5,h6,b{font-weight:normal;}

        .header{width:100%; height:44px; background:#fff; position:fixed; border-bottom:1px solid #dadada; z-index: 99;}
        .header-title{width:100%; height:44px; line-height:44px; position:absolute; left:0; top:0; font-size:20px; text-align:center; color:#0d0d0d;}
        .top-left-btn{height:44px; width:45px; line-height:46px; font-size:15px; color:#666; padding-left:15px; background: url("${base}/web/images/header_back_icon.jpg") no-repeat 8px center; background-size: 10px 17px;  text-align: center;  position: absolute;  left: 0;  top: 0;  z-index: 9;}
        .address-part{padding-top:45px;}
        .address-message{height:61px;}
        .address-message dt{line-height:60px; float:left; width:20%; text-align: center; font-size:14px;}
        .address-message dd{float:left; width:80%; border-bottom:1px solid #d7d7d7; height:20px; padding:20px 0; position: relative;}
        .address-message dd input{height:20px; width:90%;font-size:14px; line-height:20px; color:#222; }
        .position-icon{display: block; width:15px; height:20px; background: url("${base}/web/images/address_position_icon.png") no-repeat; background-size: 15px 20px; position: absolute; top:50%; right:20px; margin-top:-10px;}
        .pitch-on{height:50px; line-height:50px; padding-left:11%; position: relative;}
        .pitch-on b{display: block; height:15px; width:15px; background:#0fa9a3;  border-radius:50%; position: absolute; left:5%; top:50%; margin-top:-8px;}
        .pitch-on b.pitch-on-icon{ background:#0fa9a3 url("${base}/web/images/pitch_on.png") no-repeat center center; background-size: 9px; }
        .address-submit-btn{padding:10px 10%;}
        .address-submit-btn input{display: block; width:100%; background: #fe4310; height:44px; line-height:44px; border-radius:5px; color:#FFF; text-align: center; font-size:15px;}
        .address-submit-btn input.no-use{background: #d7d7d7;}
        .address-submit-btn input #btnSave:active{background: #fe5628;}
        .select-box{position: absolute; top:20px; left:0; height:20px; width:80%;font-size:14px; line-height:20px; color:#222;}
    </style>
    <link href="${base}/web/css/mui.picker.css" rel="stylesheet" />
    <link href="${base}/web/css/mui.poppicker.css" rel="stylesheet" />
</head>

<body>
<header class="header">
    <a href="javascript:;" class="top-left-btn">返回</a>
    <h2 class="header-title">新增收货地址111</h2>
</header>
	<form id="addressForm">
		<input type="hidden" id="citycode" name="citycode" value="" />
		<input type="hidden" id="area" name="area" value="" />
		<input type="hidden" id="isDefault" name="isDefault" value="" />
		<article class="address-part">
		    <dl class="address-message">
		        <dt>收货人</dt>
		        <dd>
		            <input type="text" id="receiver" name="memberReceiver.receiver" placeholder="请输入姓名"/>
		        </dd>
		    </dl>
		    <dl class="address-message">
		        <dt>手机号</dt>
		        <dd>
		            <input type="text" id="mobile" name="memberReceiver.mobile" placeholder="请输入手机号"/>
		        </dd>
		    </dl>
		    <dl class="address-message">
		        <dt>地　区</dt>
		        <dd id='showCityPicker3'>
		            <input type="text" placeholder="省/市/区" id="areaFirstTwoAddress" name="areaFirstTwoAddress"/>
		            <div id='cityResult3' class="select-box"></div>
		            <b class="position-icon"></b>
		        </dd>
		    </dl>
		    <dl class="address-message">
		        <dt>详　细</dt>
		        <dd>
		            <input type="text" id="address" name="memberReceiver.address" placeholder="街道/门牌号等"/>
		        </dd>
		    </dl>
		    <div class="pitch-on"><b class="pitch-on-icon"></b>设为默认收货地址</div>
		    <div class="address-submit-btn" id="address-submit-btn">
		        <input type="button" id="btnSave" value="保　存" />
		        <!--不可用时input添加class="no-use"-->
		    </div>
		    <div class="address-submit-btn" id="address-submit-btn-temp" style="display:none;">
		        <input type="button" class="no-use" id="btnSave_temp" value="保　存" />
		        <!--不可用时input添加class="no-use"-->
		    </div>
		</article>
	</form>
</body>
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script src="${base}/web/js/mui.min.js"></script>
<script src="${base}/web/js/mui.picker.js"></script>
<script src="${base}/web/js/mui.poppicker.js"></script>
<script src="${base}/web/js/shippingAddressAdd.js"></script>
<script src="${base}/web/js/city.data-3.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    (function($, doc) {
        $.init();
        $.ready(function() {
            //级联示例
            var cityPicker3 = new $.PopPicker({
                layer: 3
            });
            cityPicker3.setData(cityData3);
            var showCityPickerButton = doc.getElementById('showCityPicker3');
            var cityResult3 = doc.getElementById('cityResult3');
            var areaFirstTwoAddress = doc.getElementById('areaFirstTwoAddress');
            var area = doc.getElementById('area');
            showCityPickerButton.addEventListener('tap', function(event) {
                cityPicker3.show(function(items) {
                	var citycode = doc.getElementById('citycode');
                	citycode.value = items[1].citycode;
                	alert(citycode.value);
                    cityResult3.innerText = (items[0] || {}).text + "/" + (items[1] || {}).text + "/" + (items[2] || {}).text;
                    areaFirstTwoAddress.value = (items[0] || {}).text + "-" + (items[1] || {}).text;
                    area.value = (items[0] || {}).text + "-" + (items[1] || {}).text + "-" + (items[2] || {}).text;
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                    areaFirstTwoAddress.style.display="none";
                });
            }, false);
        });
    })(mui, document);


    $(function(){
        $('.pitch-on').click(function(){
            if($(this).children('b').hasClass('pitch-on-icon')){
                $(this).children('b').removeClass('pitch-on-icon');
                $("#isDefault").val(1);
            }else{
                $(this).children('b').addClass('pitch-on-icon');
                $("#isDefault").val(0);
            }
        })
    })
</script>
</html>
