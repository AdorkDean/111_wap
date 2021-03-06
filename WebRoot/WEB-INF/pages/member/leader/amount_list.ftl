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
    <link href="${base}/web/css/health.css" rel="stylesheet" type="text/css" />
</head>
<body>
<#assign title="提现历史">
<#include "/static/inc/wap/header.ftl">
<article class="health-list">
    <h3 class="health-hour">24小时客服热线：4006063111</h3>
    <div class="health-pharmacy">
        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td height="25" align="center" valign="middle" width="25%">账户类型</td>
                <td height="25" align="center" valign="middle" width="25%">提交时间</td>
                <td height="25" align="center" valign="middle" width="25%">提现佣金</td>
                <td height="25" align="center" valign="middle" width="25%">审核状态</td>
            </tr>
            <#list pw.result?if_exists as resc>
            <tr>
                <td height="45" align="center" valign="middle" width="33.3%"><#if resc.out_type?default(0)==1>支付宝<#elseif resc.out_type?default(0)==2>银行卡</#if></td>
                <td height="20" align="center" valign="middle" width="20%"><#if resc.out_time?exists>${resc.out_time?string('yyyy-MM-dd')}</#if></td>
                <td height="20" align="center" valign="middle" width="20%">${resc.amount?default(0)}</td>
                <td height="20" align="center" valign="middle" width="20%"><#if resc.status?default(0)==1>申请提现<#elseif resc.status?default(0)==2>提现中<#elseif resc.status?default(0)==3>提现完成<#elseif resc.status?default(0)==4>审核驳回</#if></td>
            </tr>
            </#list>
        </table>
    </div>
</article>
<#include "/static/inc/wap/footer.ftl"/>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery.min.js"></script>
