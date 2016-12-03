<#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://ui1.111yao.com"/>  
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
<header class="header">
    <a href="javascript:;" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">${title}</h2>
    <a href="javascript:;" class="iconfont top-right-btn" id="toHome" style="z-index:99">h</a>
</header>
<!--
<script type="text/javascript">
$(function(){
	var url="";
    if(isWeiXin()){
    	url= window.location.href;
    	this.location="/register/register!authUrl.action?hurl="+url;
    }
	function isWeiXin(){
	    var ua = window.navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	        return true;
	    }else{
	        return false;
	    }
	}
});
</script>
-->