<#assign ww=JspTaglibs["/WEB-INF/webwork.tld"]>
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
    <title>个人中心</title>
     <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
</head>
<body style="padding-top:0px;">
<header class="header" style="padding-top:20px;">
 	<a href="javascript:history.go(-1);" class="iconfont top-left-btn" style="padding-top:20px;">B</a>
    <h2 class="header-title" style="padding-top:20px;">申请提现</h2>
    <a href="${base}/appleader/amountout!list.action" class="iconfont top-right-btn" style="padding-top:20px;"><span>账单</span></a>
</header>
<article class="health-list" style="padding-top:65px;">
    <div class="health-apply-into" id="choose-info">
        <span class="current">支付宝</span>
        <span>银行卡</span>
    </div>
    <div class="health-apply-change"  id="choose-list">
        <div class="list-con current">
            <ul class="health-center clearfix">
                <li>
                    <div class="health-sort-head clearfix">可提现壹贝</div>
                    <p class="health-sort-name">${account.remainingAmount?default(0)}</p>
                </li>
            </ul>
            <div class="health-explain">
                <p>最小提现单位为10元。每月发放奖金日为10号，每月申请提现的截止日期为5号。5号之后申请提现顺延至下月10号发放奖金。每月只可申请一次。</p>
            </div>
            <ul class="health-center clearfix">
                <li>
                    <div class="health-sort-head clearfix">本次提现金额</div>
                    <p class="health-input"><input type="text" placeholder="请输入金额" id="zamount" onkeyup="clearNoNum(this)"/></p>
                </li>
            </ul>
            <ul class="health-center-bank clearfix">
                <li>
                    <input type="text" placeholder="请输入您的支付宝号" id="zname"/>
                </li>
                <li>
                    <input type="text" placeholder="请输入您的姓名" id="zxm"/>
                </li>
            </ul>
            <div class="health-btn"><a href="#" onclick="subform(1);">提交申请</a></div>
        </div>
        <div class="list-con">
            <ul class="health-center clearfix">
                <li>
                    <div class="health-sort-head clearfix">可提现壹贝</div>
                    <p class="health-sort-name">${account.remainingAmount?default(0)}</p>
                </li>
            </ul>
            <div class="health-explain">
                <p>最小提现单位为10元。每月发放奖金日为10号，每月申请提现的截止日期为5号。5号之后申请提现顺延至下月10号发放奖金。每月只可申请一次。</p>
            </div>
            <ul class="health-center clearfix">
                <li>
                    <div class="health-sort-head clearfix">本次提现金额</div>
                    <p class="health-input"><input type="text" placeholder="请输入金额" id="yamount" onkeyup="clearNoNum(this)"/></p>
                </li>
            </ul>
            <ul class="health-center-bank clearfix">
                <li>
                    <input type="text" placeholder="请输入您的开户行" id="yh"/>
                </li>
                <li>
                    <input type="text" placeholder="请输入您的银行卡号" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" id="yhk"/>
                </li>
                <li>
                    <input type="text" placeholder="请输入您的姓名" id="yxm"/>
                </li>
            </ul>
			<div class="health-btn"><a href="#" onclick="subform(2);">提交申请</a></div>
        </div>
    </div>
</article>
</body>
<form name="form1" id="form1"  action="${base}/member/amountout_st!subAmountOut.action" method="post">
<@ww.token/>
<input type="hidden" name="amountOut.outType" value="" id="outType">
<input type="hidden" name="amountOut.amount" value="" id="amount">
<input type="hidden" name="amountOut.alipayNo" value="" id="alipayNo">
<input type="hidden" name="amountOut.bankName" value="" id="bankName">
<input type="hidden" name="amountOut.bankNo" value="" id="bankNo">
<input type="hidden" name="amountOut.realName" value="" id="realName">
</form>
</html>
<script type="text/javascript">
    $(function(){
        $("#choose-info span").click(function(){
            $(this).addClass('current').siblings().removeClass('current');
            $("#choose-list .list-con:eq("+$(this).index()+")").addClass('current').siblings().removeClass('current');
        })
    })
</script>
<script language="JavaScript" type="text/javascript">
    var m = ${account.remainingAmount?default(0)};
	function clearNoNum(obj){
		obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
		obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是
		obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
	}
	
	function subform(type){
	   if(type==2){  //银行卡提现
	     var yamount = $('#yamount').val();
	     if($.trim(yamount)==""){
		      $alert("warn","提现金额不能为空",null,null);
		      return false;
		 }
		 if(parseFloat(m)<parseFloat(yamount)){
		      $alert("warn","提现金额大于可用壹贝",null,null);
		      return false;		 
		 }
		 $('#amount').val(yamount);
	     var yh = $('#yh').val();
	     if($.trim(yh)=="" || yh=="如中国银行"){
	     	  $alert("warn","开户行不能为空",null,null);
		      return false;
		 }	     
		  $('#bankName').val(yh);
	     var yhk = $('#yhk').val();
	     if($.trim(yhk)==""){
	          $alert("warn","银行卡号不能为空",null,null);
		      return false;
		 }	  
		  $('#bankNo').val(yhk);   
	     var yxm = $('#yxm').val();
	     if($.trim(yxm)==""){
	          $alert("warn","姓名不能为空",null,null);
		      return false;
		 }	
		  $('#realName').val(yxm);
		  $('#outType').val(type); 
		  $('#form1').submit();  
	   }else{    //支付宝提现
	     var zamount = $('#zamount').val();
	     if($.trim(zamount)==""){
	          $alert("warn","提现金额不能为空",null,null);
		      return false;
		 }
		 if(parseFloat(m)<parseFloat(zamount)){
		      $alert("warn","提现金额大于可用壹贝",null,null);
		      return false;		 
		 }
		 $('#amount').val(zamount);
	     var zname = $('#zname').val();
	     if($.trim(zname)==""){
	          $alert("warn","支付宝账号不能为空",null,null);
		      return false;
		 }
		  $('#alipayNo').val(zname);	     
	     var zxm = $('#zxm').val();
	     if($.trim(zxm)==""){
	          $alert("warn","姓名不能为空",null,null);
		      return false;
		 }
		 $('#realName').val(zxm);
		 $('#outType').val(type); 
		 $('#form1').submit();  
	   }
	
	}
</script>
<script>
$(function(){
<#if err?exists>
   <#if err?default(0)==-2>
     $alert("warn","该用户无资金账户",null,null);
   <#elseif err?default(0)==-3>
     $alert("warn","提现金额大于可提现金额",null,null); 
   <#else>
     $alert("warn","申请成功",null,null);  
   </#if>
</#if>
});
</script>