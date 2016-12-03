<#list pw.result?if_exists as resc>
<li>
    <img src="${base}${resc.abbreviation_picture?default('')}">
    <div class="order-product">
        <div class="order-product-unit">
            <p class="order-product-price"><#if resc.service_type?exists && resc.service_type == 0>退货<#elseif resc.service_type?exists && resc.service_type == 1>换货<#else>退款</#if></p>
            <p class="order-product-count">x${resc.quantity?default(0)}</p>
        </div>
        <div class="order-product-title">
            <h2>${resc.goods_name?default('')}</h2>
        </div>
    </div>
</li>
</#list>