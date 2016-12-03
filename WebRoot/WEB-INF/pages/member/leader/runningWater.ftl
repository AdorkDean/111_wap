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
    <title>我的收益</title>
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${base}/web/css/health2.0.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/new-health.js"></script>
    <script type="text/javascript" src="${base}/web/js/common.new.js"></script>
    <script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>
</head>
<body class="bg-white">
<article class="health-in-money">
	<ul class="profit-info-list clearfix">
		<li>
        	<p class="profit-details">${tmemberaccount.remainingAmount?string('###0.00')}</p>
            <p>到账收益（壹贝）</p>
        </li>
        <li>
        	<a href="#" onclick="javascript:isBindingAmount()">提现</a>
        </li>
        <li>
        	<p class="profit-details">${tmemberaccount.waitAmount?string('###0.00')}</p>
            <p>待入账收益（壹贝）</p>
        </li>
         <li>
        	<a href="#" onclick="javascript:isBindingAmount2()">账号管理</a>
        </li>
    </ul>
    <ul class="in-accordion">
        <#list dateAmountList?if_exists as obj>
	        <li>
	            <div class="list-link" val="${obj.date_str}">
	                 <b class="for-bold">${obj.date_str}月</b> [合计 ${obj.amount_new?string('###0.00')} 元]
	                <b class="fa-chevron-down"></b>
	                <i></i>
	            </div>
	            <div class="list-sub-menu clearfix" id="${obj.date_str}_div_id">
	            </div>
	        </li>
        </#list> 
    </ul>
</article>
<footer class="new-health-footer" >
	<nav class="new-health-footer-nav">
    	<ul>
        	<li>
            	<a href="../leader/leaderArticle!recommendArticle.action" >
                	<b class="my-article"></b>
                    <p>文章推荐</p>
                </a>
            </li>
            <li>
            	<a  href="../leader/leaderArticle!leaderPharmacy.action">
                	<b class="my-pharmacy"></b>
                    <p>我的药房</p>
                </a>
            </li>
            <li>
            	<a href="../leader/leaderCenter!index.action">
                	<b class="my-manage"></b>
                    <p>管理中心</p>
                </a>
            </li>
        </ul>
    </nav>
    <div class="new-health-copy">
    	<h1>健康领秀</h1>
        <p>&copy;111Yao</p>
    </div>
</footer>
</body>
<script type="text/javascript">
   var pageno = 1;


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
         $(".list-link").click(function(){
        	if($(this).parent().hasClass("in-open")){
        	   pageno=1;
        	   $("#jzgd_div").remove();
        		var current = $(this);
        		var date_str = $(this).attr("val");
        		$.ajax({
					url: "${base}/leader/leaderArticle!ajaxLeaderDateProfit.action",
					type: "GET",
					data:{
						"date_str":date_str
					},
					cache: false,
					success: function(data){
					   var  dataHtml ="";
					    for(var i=0;i<data.ajaxList.length;i++){
					       dataHtml+="<div class=\"tel-data\">";
					       var nick_name ="您的秀粉"
					       if(data.ajaxList[i].nick_name!=null&&data.ajaxList[i].nick_name!=''){
					            nick_name =data.ajaxList[i].nick_name;
					            if(nick_name.length>6){
					                nick_name = nick_name.substring(0,6)+"...";
					            }
					       }
					       if(data.ajaxList[i].type==1){
					           dataHtml+="<span>"+nick_name+"成功下单</span>";
					           dataHtml+="<p>"+data.ajaxList[i].create_str+"</p>";
					           dataHtml+="<b>+"+parseFloat(data.ajaxList[i].amount).toFixed(2)+"</b>";
					       }else if(data.ajaxList[i].type==2){
					           dataHtml+="<span>"+nick_name+"取消订单</span>";
					           dataHtml+="<p>"+data.ajaxList[i].create_str+"</p>";
					           dataHtml+="<b class=\"b-blue\">"+parseFloat(data.ajaxList[i].amount).toFixed(2)+"</b>";
					       }else if(data.ajaxList[i].type==3){
					           dataHtml+="<span>"+nick_name+"退款</span>";
					           dataHtml+="<p>"+data.ajaxList[i].create_str+"</p>";
					           dataHtml+="<b class=\"b-blue\">"+parseFloat(data.ajaxList[i].amount).toFixed(2)+"</b>";
					       }
					       dataHtml+="</div>";
					    }
					    if(data.isNextPage=='1'){
					        dataHtml+="<div class=\"more-btn\" id=\"jzgd_div\"><input onclick=\"gdother('"+date_str+"')\" type=\"button\" value=\"点击加载更多\"/></div>";
					    }
						current.parent().find(".list-sub-menu").html(dataHtml);
					}
				});
        	}
        	
        });
        
    });
    
    //加载更多
    function gdother(date_str){
             $.ajax({
					url: "${base}/leader/leaderArticle!ajaxLeaderDateProfit.action",
					type: "GET",
					data: {"rs.p_curPage":pageno+1,"date_str":date_str,"random":Math.random()},
					cache: false,
					success: function(data){
					   $("#jzgd_div").remove();
					   var  dataHtml ="";
					    for(var i=0;i<data.ajaxList.length;i++){
					       dataHtml+="<div class=\"tel-data\">";
					       var nick_name ="您的秀粉"
					       if(data.ajaxList[i].nick_name!=null&&data.ajaxList[i].nick_name!=''){
					            nick_name =data.ajaxList[i].nick_name;
					       }
					       if(data.ajaxList[i].type==1){
					           dataHtml+="<span>"+nick_name+"成功下单</span>";
					           dataHtml+="<p>"+data.ajaxList[i].create_str+"</p>";
					           dataHtml+="<b>+"+parseFloat(data.ajaxList[i].amount).toFixed(2)+"</b>";
					       }else if(data.ajaxList[i].type==2){
					           dataHtml+="<span>"+nick_name+"取消订单</span>";
					           dataHtml+="<p>"+data.ajaxList[i].create_str+"</p>";
					           dataHtml+="<b class=\"b-blue\">"+parseFloat(data.ajaxList[i].amount).toFixed(2)+"</b>";
					       }else if(data.ajaxList[i].type==3){
					           dataHtml+="<span>"+nick_name+"退款</span>";
					           dataHtml+="<p>"+data.ajaxList[i].create_str+"</p>";
					           dataHtml+="<b class=\"b-blue\">"+parseFloat(data.ajaxList[i].amount).toFixed(2)+"</b>";
					       }
					       dataHtml+="</div>";
					    }
					    if(data.isNextPage=='1'){
					         dataHtml+="<div class=\"more-btn\" id=\"jzgd_div\"><input onclick=\"gdother('"+date_str+"')\" type=\"button\" value=\"点击加载更多\"/></div>";
					    }
						$("#"+date_str+"_div_id").append(dataHtml);
						pageno=pageno+1;
					}
				});
    
    }
    
    
  function isBindingAmount()
{		
		$.ajax({
				    url: "${base}/leader/bindingAmount!isBindingAmount.action",
				    type: "GET",
				  	cache: false,
			      	success: function(data) {
					if(data==1){
						location.href = "${base}/leader/bindingAmount!checkDrawingAccountIndex.action";//转到提现账户页
					}else if(data==2){
						location.href = "${base}/leader/bindingAmount!checkBindingAccount.action";//转到绑定帐号页
					}else{
						$alert("warn","数据异常！","111医药馆提示您",null);
						return false;
					}
				 }
			 });
}  

function isBindingAmount2()
{		
		$.ajax({
				    url: "${base}/leader/bindingAmount!isBindingAmount.action",
				    type: "GET",
				  	cache: false,
			      	success: function(data) {
					if(data==1){
						location.href = "${base}/leader/bindingAmount!accountManage.action";//转到账户管理页
					}else if(data==2){
						location.href = "${base}/leader/bindingAmount!checkBindingAccount.action";//转到绑定帐号页
					}else{
						$alert("warn","数据异常！","111医药馆提示您",null);
						return false;
					}
				 }
			 });
}  
</script>
</html>

