    <style rel="stylesheet" type="text/css">
        html,body,div,span,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,address,cite,code,del,dfn,em,img,ins,kbd,q,samp,small,strong,sub,sup,var,b,i,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,textarea,input,span,figure,aside,time,figcaption{margin:0;padding:0;border:0;font-size:100%;vertical-align:baseline}
        article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block}
        audio,canvas,video{display:inline-block;*display:inline;*zoom:1}
        audio:not([controls]){display:none}
        html,body{height:100%; position:relative}
        html{font-size:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}
        body{width:100%;font-family:"SimHei",Helvetica,Arial,sans-serif;font-size:14px;color:#000;}
        input,textarea{font-family:"SimHei",Helvetica,Arial,sans-serif;color:#000}
        a{color:#0d0d0d;text-decoration:none;/*-webkit-tap-highlight-color:rgba(0,0,0,0.2);*/-webkit-tap-highlight-color:transparent}
        a:hover{text-decoration:none}
        a:focus{outline:thin dotted}
        a:hover,a:active{outline:0}
        textarea,input{resize:none;outline:0;font-size:100%;-webkit-tap-highlight-color:rgba(255,0,0,0)}
        textarea{resize:none;-webkit-appearance:none}
        ul,ol{list-style:none}
        em{font-style:normal}
        h1,h2,h3,h4,h5,h6,b{font-weight:normal;}

        .header{width:100%; height:44px; background:#fff; position:fixed; border-bottom:1px solid #dadada; z-index: 99;}
        .header-title{width:100%; height:44px; line-height:44px; position:absolute; left:0; top:0; font-size:20px; text-align:center; color:#0d0d0d;}
        /*.top-left-btn{height:44px; width:45px; line-height:46px; font-size:15px; color:#666; padding-left:15px; background: url("${base}/web/images/header_back_icon.jpg") no-repeat 8px center; background-size: 10px 17px;  text-align: center;  position: absolute;  left: 0;  top: 0;  z-index: 9;}*/
        .address-part-select{padding:45px 0 65px;}
        .address-list-new{ position: relative; padding-right:15%; height:80px;}
        .select-add-icon{border: 1px solid #0fa9a3;  position: absolute; top: 50%;  left: 16px;  margin-top: -7px;  height: 14px;  width: 14px;  display: block;  border-radius: 50%;}
        .edit-add-icon{border-bottom:1px solid #d7d7d7;  width:15%; height:79px; position: absolute; top:0; right:0;}
        .edit-add-icon b{position: absolute; top: 50%;  right: 14px;  margin-top: -10px;  width: 16px;  height: 20px;  background: url("${base}/web/images/new_settle_icon05.png") no-repeat;  background-size: 16px;}
        .add-current-select{ padding:15px 0 5px 15%; height:60px;}
        .add-current-select:active{background:#f8f8f8;}
        .add-current-select{border-bottom: 0px solid #d7d7d7;}
        .edit-add-icon:active{background: #e2e2e2;}
        .add-name{font-size:14px; font-weight: bold; padding-right:30px; height:21px; line-height:21px; }
        .add-name b{float:right; font-weight: bold;font-size:14px; }
        .add-message{line-height:20px;height:40px; overflow: hidden;border-bottom:1px solid #d7d7d7; padding-right:30px; padding-bottom:3px; word-wrap:break-word;}
        .invoice-icon{background: #0fa9a3 url("${base}/web/images/new_settle_icon04.png") no-repeat center center;  background-size: 9px;}
        .new-add-btn{height:70px; position: fixed; bottom:-5px; left:0; right:0;z-index: 99; border-top:1px solid #d7d7d7; background:#f8f8f8 ;}
        .new-add-btn a{ display: block; }
        .new-add-btn a span{display: block; position: absolute; top:50%; left:50%; margin:-10px 0 0 -50px; height:20px; line-height:20px; width:100px; color:#fe4310; font-size:14px;background: url("${base}/web/images/new_add_btn.png") no-repeat left center; padding-left:20px;background-size: 15px;}
    </style>
<!-- 选择收货地址-->
    <div class="select-address click-select">
        <div class="address-content click-address">
            <header class="address-header">
                <h2 class="address-title">选择收货地址</h2>
                <div class="address-close" onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
			<header class="header">
			    <a href="javascript:;" class="top-left-btn11">返回</a>
			    <h2 class="header-title">选择收货地址</h2>
			</header>
			<article class="address-part-select">
			    <div class="address-list-new">
			        <div class="select-add-icon invoice-icon"></div>
			        <div class="add-current-select">
			            <p class="add-name">
			                <span>奉贤</span>
			                <b>18348945524</b>
			            </p>
			            <p class="add-message">北京市朝阳区望京利泽中一路号院博雅国际中心5层德胜堂技术部</p>
			        </div>
			        <div class="edit-add-icon"><b></b></div>
			    </div>
			    <div class="address-list-new">
			        <div class="select-add-icon"></div>
			        <div class="add-current-select">
			            <p class="add-name">
			                <span>奉贤</span>
			                <b>18348945524</b>
			            </p>
			            <p class="add-message">北京市朝阳区望京利泽中一路号院博雅国际中心5层德胜堂技术部</p>
			        </div>
			        <div class="edit-add-icon"><b></b></div>
			    </div>
			    <div class="address-list-new">
			        <div class="select-add-icon"></div>
			        <div class="add-current-select">
			            <p class="add-name">
			                <span>奉贤</span>
			                <b>18348945524</b>
			            </p>
			            <p class="add-message">北京市朝阳区望京利泽中一路号院博雅国际中心5层德胜堂技术部</p>
			        </div>
			        <div class="edit-add-icon"><b></b></div>
			    </div>
			    <div class="address-list-new">
			        <div class="select-add-icon"></div>
			        <div class="add-current-select">
			            <p class="add-name">
			                <span>奉贤</span>
			                <b>18348945524</b>
			            </p>
			            <p class="add-message">北京市朝阳区望京利泽中一路号院博雅国际中心5层德胜堂技术部</p>
			        </div>
			        <div class="edit-add-icon"><b></b></div>
			    </div>
			    <div class="address-list-new">
			        <div class="select-add-icon"></div>
			        <div class="add-current-select">
			            <p class="add-name">
			                <span>奉贤</span>
			                <b>18348945524</b>
			            </p>
			            <p class="add-message">北京市朝阳区望京利泽中一路号院博雅国际中心5层德胜堂技术部</p>
			        </div>
			        <div class="edit-add-icon"><b></b></div>
			    </div>
			    <div class="address-list-new">
			        <div class="select-add-icon"></div>
			        <div class="add-current-select">
			            <p class="add-name">
			                <span>奉贤</span>
			                <b>18348945524</b>
			            </p>
			            <p class="add-message">北京市朝阳区望京利泽中一路号院博雅国际中心5层德胜堂技术部</p>
			        </div>
			        <div class="edit-add-icon"><b></b></div>
			    </div>
			</article>
			<div class="new-add-btn"">
			    <a href="javascript:void(0);" onclick="addReceiverNew()"><span>新增收货地址</span></a>
			</div>
    
            
	</div>
</div>


<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    $(function(){
        $('.address-list-new').click(function(){
            /*
            if($(this).children('.select-add-icon').hasClass('invoice-icon')){
                $(this).children('.select-add-icon').removeClass('invoice-icon');
            }else{
                $(this).children('.select-add-icon').addClass('invoice-icon');
                $(this).siblings().children('.select-add-icon').removeClass('invoice-icon');
            }
            */
            $(this).children('.select-add-icon').addClass('invoice-icon');
            $(this).siblings().children('.select-add-icon').removeClass('invoice-icon');
        })
    })
    function addressListClick($this){
    	/*
 		if($this.children('.select-add-icon').hasClass('invoice-icon')){
            $this.children('.select-add-icon').removeClass('invoice-icon');
        }else{
            $this.children('.select-add-icon').addClass('invoice-icon');
            $this.siblings().children('.select-add-icon').removeClass('invoice-icon');
        }
        */
        $this.children('.select-add-icon').addClass('invoice-icon');
        $this.siblings().children('.select-add-icon').removeClass('invoice-icon');
    }
</script>
</html>
