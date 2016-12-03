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
    <title>健康领秀-统计表</title>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/web/js/fusioncharts.js"></script>
    <script type="text/javascript" src="${base}/web/js/fusioncharts.theme.fint.js"></script>
    </head>
    	
	<body style="background: #FEFEFE;">
		<#include "/static/inc/wap/header2.ftl" />
		<script>
		$(function(){
			//定位标签
			$("#tabs li:eq(3)").addClass("cur");
		});
		</script>
		<div style="border-top:1px solid #666; border-bottom:1px solid #666; margin:10px 0; height:26px; padding:3px 0; line-height:26px; ">
			<select id="option" name="option" style="width: 100px; height:26px; line-height:26px; float:right;">
				<option value="1">最近一月</option>
				<option value="2">最近三月</option>
				<option value="3">最近半年</option>
				<option value="4">最近一年</option>
			</select>
			<b style="float:right; display:block; height:26px; line-height:26px;">推广效果统计:</b>
		</div>
	
		
		<div id="chart-container"></div>
		<#include "/static/inc/wap/footer.ftl"/>
	</body>
</html>

<script type="text/javascript" >
FusionCharts.ready(function () {

	var chart = {
        type: 'line',
        renderAt: 'chart-container',
        width: '100%',
        height: '250',
        dataFormat: 'json',
        dataSource: {
            "chart": {
                "subCaption": "最近一月",
                "xAxisName": "日期",
                "yAxisName": "数量",
                
                //Cosmetics
                "yAxisMaxValue":"10",
                "yAxisValueDecimals":"0", 
                "lineThickness" : "2",
                "paletteColors" : "#0075c2",
                "baseFontColor" : "#333333",
                "baseFont" : "Helvetica Neue,Arial",
                "captionFontSize" : "14",
                "subcaptionFontSize" : "14",
                "subcaptionFontBold" : "0",
                "showBorder" : "0",
                "bgColor" : "#ffffff",
                "showShadow" : "0",
                "canvasBgColor" : "#ffffff",
                "canvasBorderAlpha" : "0",
                "divlineAlpha" : "100",
                "divlineColor" : "#999999",
                "divlineThickness" : "1",
                "divLineIsDashed" : "1",
                "divLineDashLen" : "1",
                "divLineGapLen" : "1",
                "showXAxisLine" : "1",
                "xAxisLineThickness" : "1",
                "xAxisLineColor" : "#999999",
                "showAlternateHGridColor" : "0",
            },
            "data": [
                
            ]
        }
    };
    
    function load(){
    	var option = $("#option").val();
		$.ajax({
			url: "${base}/leader/leader!report_data.action",
			data:{
				"option":option,
			},
			datatype:"json",
			type:"post",
			async:false,
			cache:false,
			success: function(data) {
				if(option ==null ||option==""||option=="1"){
					chart["dataSource"]["chart"]["subCaption"]="最近一月"
				}
				if(option == "2"){
					chart["dataSource"]["chart"]["subCaption"]="最近三月";
				}
				if(option == "3"){
					chart["dataSource"]["chart"]["subCaption"]="最近半年";
				}
				if(option == "4"){
					chart["dataSource"]["chart"]["subCaption"]="最近一年";
				}
				
				chart["dataSource"]["data"] = data;
			}
	   });
	   var visitChart = new FusionCharts(chart);
	   visitChart.configure("ChartNoDataText", "最近没有产生新的秀粉,继续加油！");
       visitChart.render();
       setTimeout(function(){       
       	$("#chart-container").find("text").last().remove();
       },100);
    }
    load();
    
    $("#option").change(function(){
    	load();
	});
});
</script>
