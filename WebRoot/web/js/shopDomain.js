/**
 * 上线记得改域名
 * @param leaderId
 */
function toShopDomain(leaderId)
{
	window.top.location.href = "http://"+leaderId+".shop.111yao.com";
}

function getShopDomain(leaderId)
{
	return "http://"+leaderId+".shop.111yao.com";
}

function getErWeiCodeUrl(leaderId)
{
	return "http://m.111yao.com/share/leader!poster.action?leaderId=" + leaderId;
}