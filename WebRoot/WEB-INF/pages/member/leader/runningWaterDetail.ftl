<#list pw.result?if_exists as resc>
<div class="tel-data">
    <span><#if resc.user_name?default('')?length gt 4 >${resc.user_name[0..2]?default('')}*****${resc.user_name[resc.user_name?length-3..resc.user_name?length-1]?default('')}<#else>${resc.user_name?default('')}</#if></span>
    <p>${resc.create_dt?string('yyyy-MM-dd HH:mm')}</p>
    <b <#if resc.amount lt 0>class="b-blue"</#if>><#if resc.amount gt 0>+</#if>${currency(resc.amount?default('0.00'),"false","false")}</b>
</div>
</#list>
<#if pw.pageInfo.page < pw.pageInfo.pages> 
<div class="more-btn"><a class="more-btn-a" url="${base}/leader/leader!runningWaterDetail.action?date=${date}&rs.p_curPage=${pw.pageInfo.nextPage}">点击加载更多<a/></div>
</#if>                