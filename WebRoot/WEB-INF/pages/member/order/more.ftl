<#include "/WEB-INF/pages/inc/taglibs.ftl">
<#list pw.result?if_exists as resc>
<li class="colloctgoods clearfix">
    <a href="${base}/member/order!detail.action?id=${resc.id?default('')}">
        <dl>
            <dt><img src="${ui1}${resc.abbreviationPicture?default('')}"/></dt>
            <dd>
		<p><span>订单编号</span><b>${resc.orderSn?default('')}</b></p>
                <p><span>订单金额</span><b>￥${currency(resc.payableAmount?default(0))}</b></p>
                <p><span>订单状态</span>
                	<b>
                		<#if resc.orderStatus?exists && resc.orderStatus == 0>
                        	待支付
                    	</#if>                                    	
                    	<#if resc.orderStatus?exists && resc.orderStatus == 1>
                        	待发货
                    	</#if>
                    	<#if resc.orderStatus?exists && resc.orderStatus == 2>
                        	已发货
                    	</#if>
                    	<#if resc.orderStatus?exists && resc.orderStatus == 3>
                        	已完成
                    	</#if>
                    	<#if resc.orderStatus?exists && resc.orderStatus == 4>
                    		已取消
                    	</#if>
                    	<#if resc.orderStatus?exists && resc.orderStatus == 5>
                    		已过期
                    	</#if>
    			</b></p>
            </dd>
        </dl>
    </a>
    <div class="colloctgoods_button">
    <#if type== "2">
		<a class="colloctgoods_bluebtn" href="${base}/member/order!detail.action?id=${resc.id?default('')}">去支付</a>
	<#elseif type=="3">
		<a class="colloctgoods_yellowbtn" href="${base}/member/order!logistics.action?id=${resc.id?default('')}">查看物流</a>
		<a class="colloctgoods_bluebtn complete" href="javascript:void(0);" val="${resc.id?default('')}">确认收货</a>
	<#elseif type=="4">
		<a class="colloctgoods_bluebtn" href="${base}/member/comment.action?id=${resc.id?default('')}">去评论</a>					
	</#if>
	</div>
</li>
</#list> 