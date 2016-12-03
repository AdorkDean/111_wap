<article class="popup-box">
    <!-- 选择收货地址-->
    <!--
    <div class="select-address click-select">
        <div class="address-content click-address">
            <header class="address-header">
                <h2 class="address-title">选择收货地址</h2>
                <div class="address-close" onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
            <div class="address-list" >
                <ul id="receiverList_Div_ul_id">
                </ul>
            </div>
            <div class="augment-address" onclick="addReceiver()">
                <i class="augment-icon"></i>
                <span class="augment-tit">新增地址</span>
                <b class="new-s-icon-right"></b>
            </div>
        </div>
    </div>
    -->
    <!-- 编辑收货地址-->
    <!--
    <div class="select-address click-select-edit">
        <div class="address-content click-address-edit">
            <header class="address-header">
                <h2 class="address-title" id="xzshdz_h2_id">新增收货地址</h2>
                <div class="address-close" onclick="closeDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
            <form name="receiverForm" id="receiverForm" method="post">
            <input id="memberReceiver_id" name="memberReceiver.id" type="hidden" value=""/>
            <input id="memberReceiver_adcode" name="memberReceiver_adcode" type="hidden" value=""/>
            <input id="memberReceiver_locationAddress" name="memberReceiver.locationAddress" type="hidden" value=""/>
            <input id="memberReceiver_longitude" name="memberReceiver.longitude" type="hidden" value=""/>
            <input id="memberReceiver_latitude" name="memberReceiver.latitude" type="hidden" value=""/>
            <input id="memberReceiver_areaId" name="memberReceiver_areaId" type="hidden" value=""/>
            <input type="hidden" name="city_name_set" id="city_name_set"  value=""/>
            <ul class="edit-list">
                <li><span>收货人</span><input type="text" id="memberReceiver_receiver" maxlength="10" name="memberReceiver.receiver" placeholder="请输入您的姓名"  class="black"/></li>
                <li><span>手机号</span><input type="tel" id="memberReceiver_mobile" name="memberReceiver.mobile" placeholder="请输入您的手机"  class="black"/></li>
                <li><span>城　市</span><i class="city-click" id="city_li_id" onclick="cityShow('1')">请选择所在城市</i><b class="new-s-icon-right"></b></li>
                <li><span>地　区</span><i class="street-click" id="area_li_id" onclick="mapShow()">小区/大厦/学校/街道</i><b class="new-s-icon-right"></b></li>
                <li><span>详　细</span><input type="text" id="memberReceiver_address"  maxlength="40" name="memberReceiver.address" placeholder="楼层/门牌号等详细信息"  class="black"/></li>
            </ul>
             </form>
            <div class="follow-btn" ><a href="#" onclick="saveOrUpdateReceiver()">保　存</a></div>
        </div>
    </div>
   	-->
    
 <!-- 选择城市-->
    <div class="select-city">
        <div class="select-city-content">
            <header class="address-header" style="top:0; position: absolute;">
                <h2 class="address-title">选择城市</h2>
                <div class="address-close" onclick="closeCity()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
            <section class="brand_cont">
                <section >
                    <dl class="brand_sale">
                        <#list zimuMap?keys as key> 
					      <dt><a class="capital" name="a${key_index+1}" id="a${key_index+1}">${key}</a></dt>
					       <dd class="brand_list">
                            <ul>
                               <#list zimuMap[key] as citylist> 
	                                <li onclick="selectCity('${citylist.citycode}','${citylist.name}')">${citylist.name}</li>
                                </#list>
                            </ul>
                        </dd>
					    </#list>
                    </dl>
                </section>
                <div class="brand_rightlist">
                    <ol>
                    <#list zimuMap?keys as key> 
				          <li><a href="#a${key_index+1}">${key}</a></li>
				    </#list>
                        <li><a href="#">#</a></li>
                    </ol>
                </div>
            </section>
        </div>
    </div>
    <!-- 选择街道-->
    <div class="select-street">
        <div class="select-street-content">
            <header class="header" style="top:0;position: absolute;">
                <span class="iconfont top-left-btn" style="width:62px;" onclick="closeCity()">B</span>
                <h2 class="header-title index-header-title">
                    <div class="search-box" style="background: #f8f8f8; border:1px solid #d7d7d7; margin:6px 62px;">
                        <p class="search-input-box" style="margin:0 5px;"><input class="search-input" type="search" id="search_keyword" oninput="key_search()" onpropertychange="key_search()" placeholder="查找小区/大厦/学校/街道等"/></p>
                    </div>
                </h2>
                <strong class="cancel-btn" style="color:#00b8c9;" onclick="key_search()">搜索</strong>
            </header>
            <!--<aside class="street-info"><p>温馨提示：拖动地图可进行区域选择，点击列表可选择地址</p></aside>-->
            <div class="map" style="height:0px; width:0px;top:50px;" id="container"></div>
            <div class="street-list" style="top:45px">
                <div id="no_search_sd" onclick="showAddressSd()" style="height:20px; line-height:20px; text-align:center; background:#f4f5f7; color:#999;display:none">搜不到我的地址?试试手动添加</div>
                <ul id="map_search_ul_id">
                    
                </ul>
            </div>
        </div>
    </div>   
    
    <!-- 手动添加地址信息-->
    <div class="select-address click-select-edit-sd">
        <div class="address-content click-select-edit-sd">
            <header class="address-header">
                <h2 class="address-title" id="xzshdz_h2_id">手动填写</h2>
                <div class="address-close" onclick="closeSdDivCen()"><img src="${base}/web/images/member_icon04.png" alt="" width="20" height="20"/></div>
            </header>
            <input id="memberReceiver_adcode_sd" name="memberReceiver_adcode_sd" type="hidden" value=""/>
            <ul class="edit-list">
                <li><span>城　市</span><i class="city-click" id="city_li_sd_id" onclick="cityShow('2')">请选择所在城市</i><b class="new-s-icon-right"></b></li>
                <li>
                	<span>区　县</span>
                	 <div class="for-ipt choose-address">
                		<select class="single-click" style="width:150px" id="quxian_select_id" onchange="ajaxSqByAreaId()">
                			<option value="">请选择</option>
                		</select>
                	</div>	
                </li>
                <li style="display:none" id="shangquan_select_id_li">
                	 <span>商　圈</span>
                	 <div class="for-ipt choose-address">
	                	<select class="single-click" style="width:150px" id="shangquan_select_id">
	                		<option value="">请选择</option>
	                	</select>
                	</div>	
                </li>
                <li><span>详　细</span><input type="text" id="memberReceiver_address_sd"  maxlength="40"  placeholder="楼层/门牌号等详细信息"  class="black"/></li>
            </ul>
            <div class="follow-btn" ><a href="#" onclick="addressSdQd()">确　定</a></div>
        </div>
    </div>
    
     <!-- 预约成功弹窗-->
    <div class="m-shade-bg" id="m-shade-bg" style="display: none;">
        <div class="m-popup-shade">
            <div class="bind-box">
                <div class="bind-close"></div>
                <h2 class="bind-title">预约成功</h2>
                <img class="bind-img" src="${base}/web/images/bind_img.png" alt="" height="115" width="230"/>
                <p class="bind-text">您已预约成功，药师将在1小时之内与您电话联系，请您耐心等待。</p>
                <a href="/" class="bind-btn">知道了</a>
            </div>
        </div>
    </div>
 </article>
 
 
