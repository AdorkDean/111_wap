<#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://ui1.111yao.com"/> 
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/header2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>

<header class="health-header">
    <header class="health-header-title">
        <a href="${base}/member/profile.action" class="iconfont top-left-btn">B</a>
        <h2 class="header-title"></h2>
        <a onclick="window.location.href='${base}/';" class="iconfont top-right-btn">h</a>
    </header>
    <div class="health-header-img">
        <a class="header-img"><img id="img148"/></a>
        <a class="edit-info" onclick="showImageUpload()"></a>
    </div>
    <div class="health-header-info">
        <p id="nickname"></p>
        <p><span>秀粉</span><span id="xfcount"></span></p>
    </div>
</header>
<nav class="health-nav">
    <ul class="clearfix clear" id="tabs">
        <li><a onclick="window.location.href='${base}/leader/leader!leader.action';"><span><b class="health-center"></b>中心</span></a></li>
        <li><a onclick="window.location.href='${base}/leader/leader!list.action';"><span><b class="health-pharmacy"></b>药房</span></a></li>
        <li><a onclick="window.location.href='${base}/leader/leader!ranking.action';"><span><b class="health-ranking"></b>排行</span></a></li>
        <li><a onclick="window.location.href='${base}/leader/leader!leaderTool.action';"><span><b class="health-tools"></b>工具</span></a></li>
    </ul>
</nav>

<!-- 头像上传区域 -->
<div class="zwcMask" style="display:none;">
	<div id="clipArea"></div>
	<input type="file" id="file" />
	<button class="exitBtn" onclick="hideImageUpload()">取消</button>
	<span class="hand-top"></span>
	<span class="hand-bottom"></span>
	<div class="submit" id="upload_btn" style="margin:10px auto;">确定上传</div>
	<div class="submit" id="upload_btn_" style="margin:10px auto;display:none;">正在上传中...</div>
</div>

<input type="hidden" value="" id="dataURL"/>
<div class="divMasks"></div>

<script src="/web/js/jquery.min.js"></script>
<script src="/web/js/iscroll-zoom.js"></script>
<script src="/web/js/hammer.js"></script>
<script src="/web/js/jquery.photoClip.js"></script>
<script src="/web/js/header2.js"></script>


