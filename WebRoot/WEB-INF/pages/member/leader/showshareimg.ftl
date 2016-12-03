<div class="mask-ui"></div>
<img class="share-img" src="${base}/web/images/share.png"/>
<style>
.mask-ui 
{
	position:fixed;
	z-index:99;
	background: rgba(22,22,22,.9);
	top:0;
	left:0;
	width:100%;
	height:100%;
	display:none;
}
.share-img
{
	position:fixed;
	z-index:999;
	top:0;
	left:0;
	width:100%;
	display:none;
}
</style>
<script>
function showShareImg(){
	//显示/隐藏/遮罩层
	$(".mask-ui").show();
	$(".share-img").show();
	window.setTimeout(function()
	{
		$(".mask-ui").hide();
		$(".share-img").hide();
	},3000);
}
</script>