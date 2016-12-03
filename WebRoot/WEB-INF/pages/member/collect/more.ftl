<#assign ui1 = "http://img.zdfei.com"/>  
<#list pw.result?if_exists as resc>
            <li>
            	<a href="${base}/m/${resc.relevance_id?default('')}.html" class="list-pro-info">
	                <div class="new-list-pro">
	                <#if resc.wap_price?default(0) != resc.price?default(0)&&resc.wap_price?default(0)!=0>
	                	<span class="new-discount">${(resc.wap_price?default(1)/resc.price?default(1)*10)?string("#.#")}折<i></i></span>
	                	</#if>
	                    <img src="${ui1}${resc.abbreviation_picture?default('')}">
	                </div>
                    <p class="new-list-pro-title">${resc.goods_name?default('')}</p>
                    <p class="new-list-pro-price">${currency(resc.wap_price?default(0),'true')}</p>
                    <p><b class="new-list-pro-original">${currency(resc.price?default(0),'true')}</b></p>
	              <#if resc.type?default(0)==2||resc.type?default(0)==3>
                    <a href="${base}/m/${resc.relevance_id?default('')}.html"  class="iconfont new-list-pro-into-rx"></a>
                    <#else>
                    <a href="javascript:void(0)" id="${resc.relevance_id?default('')}" class="iconfont new-list-pro-into-cart"></a>
                    </#if>
                    <div class="atten-shade">
                    	<b class="delete" val="${resc.id?default('')}">-</b>
                    </div> 
                </a>
            </li>
            </#list>