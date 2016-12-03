package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.InfoUtil;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.dst.client.util.ClientSubmitPublic;
import com.rc.portal.commons.DataUtil;
import com.rc.portal.jms.MessageSender;
import com.rc.portal.payplugin.alipayWap.AlipayWapPlugin;
import com.rc.portal.payplugin.payWapYkt.PayWapYktPlugin;
import com.rc.portal.payplugin.wzfWapPlugin.WzfWapPlugin;
import com.rc.portal.service.CDeliveryWayManager;
import com.rc.portal.service.CLocationCityManager;
import com.rc.portal.service.CPaymentWayManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.RxTReserveOrderManager;
import com.rc.portal.service.TCouponCardManager;
import com.rc.portal.service.TCouponManager;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TLeaderStayMoneyManager;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.service.TMemberReceiverLatLonManager;
import com.rc.portal.service.TOrderFlowManager;
import com.rc.portal.service.TOrderManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.OrderEnum;
import com.rc.portal.vo.CDeliveryWay;
import com.rc.portal.vo.CDeliveryWayExample;
import com.rc.portal.vo.CLocationCity;
import com.rc.portal.vo.CLocationCityExample;
import com.rc.portal.vo.CPaymentWay;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberReceiverLatLon;
import com.rc.portal.vo.TMemberReceiverLatLonExample;
import com.rc.portal.vo.TOrder;
import com.rc.portal.vo.TOrderExample;
import com.rc.portal.vo.TOrderFlow;
import com.rc.portal.vo.TOrderFlowExample;
import com.rc.portal.vo.TOrderShipment;
import com.rc.portal.webapp.model.OrderGoodCart;
import com.rc.portal.webapp.model.ResultItem;
import com.rc.portal.webapp.model.order.CartGoodVO;
import com.rc.portal.webapp.model.order.OrderInitPublicVO;
import com.rc.portal.webapp.model.order.PaymethodVO;
import com.rc.portal.webapp.util.Base64ToImage;
import com.rc.portal.webapp.util.PageResult;

/**
 * 订单中心
 * @author 刘天灵
 *
 */
public class MemberReserveOrderAction extends BaseAction{
	
	private final Long MAXSIZE = 1024*1024*5L;

	private static final long serialVersionUID = 646464661L;
	
	private static int PAGE_SIZE = 10;
	
	private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult();
	
	private OpenSqlManage opensqlmanage;
	
	private String diskPath = InfoUtil.getInstance().getInfo("img", "images.public.image.reserveorder.path");
	
	/**
	 * 商品manager
	 */
	private TGoodsManager tgoodsmanager;
	
	private RxTReserveOrderManager rxtreserveordermanager;
	/**
	 * 优惠券manager
	 */
	private TCouponManager tcouponmanager;
	
	/**
	 * 优惠券
	 */
	private TCouponCardManager tcouponcardmanager;
	
	/**
	 * 用户收货地址manager
	 */
	private TMemberReceiverLatLonManager tmemberreceiverlatlonmanager;
	
	/**
	 * 订单manager
	 */
	private TOrderManager tordermanager;
	
	
	/**
	 * 地区
	 */
	private List<Map<String,Object>> clocationList;
	/**
	 * 收货地址
	 */
	private TMemberReceiverLatLon memberReceiver;
	
	/**
	 * 支付方式
	 */
	private CPaymentWayManager cpaymentwaymanager;
	
	/**
	 * 系统配置参数
	 */
	private TSysParameterManager tsysparametermanager;
	
	private TOrderFlowManager torderflowmanager ;
	
	
	private MessageSender topicMessageSender;
	
	/**
	 * 用户资金manager
	 */
	private TMemberAccountManager tmemberaccountmanager;

	private TLeaderStayMoneyManager tleaderstaymoneymanager;
	
	/**
	 * 配送方式manager
	 */
	private CDeliveryWayManager cdeliverywaymanager;
	
	/**
	 * 地区城市码表
	 */
	private CLocationCityManager clocationcitymanager;
	
	
	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {} 

	
	
	
	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}

	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public RxTReserveOrderManager getRxtreserveordermanager() {
		return rxtreserveordermanager;
	}

	public void setRxtreserveordermanager(
			RxTReserveOrderManager rxtreserveordermanager) {
		this.rxtreserveordermanager = rxtreserveordermanager;
	}

	/**
	 * 订单列表
	 */
	public String list(){
		
		String type = this.getRequest().getParameter("type");
		
		String tag = this.getRequest().getParameter("tag");
		this.getRequest().setAttribute("tag", tag==null||tag.equals("")?"":tag);
		
		
		if(!org.apache.commons.lang.StringUtils.isNumeric(type) || Integer.parseInt(type) > 4  || Integer.parseInt(type) < 1){
			type = "1";
		}
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		
		Map<String,Object> param = new HashMap<String,Object>();
		
		param.put("memberId", member.getId());
		
		if(type.equals("2")){
			//未支付订单
			pw = this.opensqlmanage.selectForPageByMap(param, "t_order.select_unpaid_count", "t_order.select_unpaid_list", rs.getP_curPage(), PAGE_SIZE);
		}else if (type.equals("3")){
			//待收货订单
			pw = this.opensqlmanage.selectForPageByMap(param, "t_order.select_unconfirm_count", "t_order.select_unconfirm_list", rs.getP_curPage(), PAGE_SIZE);
		}else if(type.equals("4")){
			//待评价订单
			pw = this.opensqlmanage.selectForPageByMap(param, "t_order.select_to_evaluate_count", "t_order.select_to_evaluate_list", rs.getP_curPage(), PAGE_SIZE);
		}else{
			//全部订单
			pw = this.opensqlmanage.selectForPageByMap(param, "t_order.select_count", "t_order.select_list", rs.getP_curPage(), PAGE_SIZE);
		}
		
		this.getRequest().setAttribute("type", type);
		
		return "list";
	}
	
	

	/**
	 * 订单列表下一页
	 */
	public String more(){
		
		String type = this.getRequest().getParameter("type");
		
		if(!org.apache.commons.lang.StringUtils.isNumeric(type) || Integer.parseInt(type) > 4  || Integer.parseInt(type) < 1){
			type = "1";
		}
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		
		Map<String,Object> param = new HashMap<String,Object>();
		
		param.put("memberId", member.getId());
		
		if(type.equals("2")){
			//未支付订单
			pw = this.opensqlmanage.selectForPageByMap(param, "t_order.select_unpaid_count", "t_order.select_unpaid_list", rs.getP_curPage(), PAGE_SIZE);
		}else if (type.equals("3")){
			//待收货订单
			pw = this.opensqlmanage.selectForPageByMap(param, "t_order.select_unconfirm_count", "t_order.select_unconfirm_list", rs.getP_curPage(), PAGE_SIZE);
		}else if(type.equals("4")){
			//待评价订单
			pw = this.opensqlmanage.selectForPageByMap(param, "t_order.select_to_evaluate_count", "t_order.select_to_evaluate_list", rs.getP_curPage(), PAGE_SIZE);
		}else{
			//全部订单
			pw = this.opensqlmanage.selectForPageByMap(param, "t_order.select_count", "t_order.select_list", rs.getP_curPage(), PAGE_SIZE);
		}
		
		this.getRequest().setAttribute("type", type);
		
		return "more";
	}
	
	/**
	 * 订单详情
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String detail() throws Exception{
		String id = this.getRequest().getParameter("id");
		
		if(org.apache.commons.lang.StringUtils.isNotEmpty(id)){
			
			TOrder order = this.tordermanager.selectByPrimaryKey(Long.parseLong(id));
			
			this.getRequest().setAttribute("order", order);
			
			if(order!=null){
				//订单明细
				Object orderItems = this.opensqlmanage.selectListByObject(id, "t_order_item.selectOrderItemByOrderId");
				this.getRequest().setAttribute("orderItems", orderItems);
				//支付记录
				Object orderFlow = this.opensqlmanage.selectObjectByObject(id, "t_order_flow.selectByOrderId");
				this.getRequest().setAttribute("orderFlow", orderFlow);
			
				//支付方式
				CPaymentWay orderPaymentWay = cpaymentwaymanager.selectByPrimaryKey(order.getPaymentId());
				this.getRequest().setAttribute("orderPaymentWay", orderPaymentWay);
				
				//发货记录
				Object orderShipment = this.opensqlmanage.selectObjectByObject(Integer.parseInt(id), "t_order_shipment.selectByOrderId");
				this.getRequest().setAttribute("orderShipment", orderShipment);
			}
		}
		
		return "detail";
	}
	
	/**
	 * 物流信息
	 */
	public String logistics() throws Exception{
		
		String id = this.getRequest().getParameter("id");
		
		if(org.apache.commons.lang.StringUtils.isNotEmpty(id)){
			
			TOrder order = this.tordermanager.selectByPrimaryKey(Long.parseLong(id));
			
			this.getRequest().setAttribute("order", order);
			
			if(order!=null){
				//订单明细
				Object orderItems = this.opensqlmanage.selectListByObject(id, "t_order_item.selectOrderItemByOrderId");
				this.getRequest().setAttribute("orderItems", orderItems);
				
				//发货记录
				TOrderShipment orderShipment = (TOrderShipment)this.opensqlmanage.selectObjectByObject(Integer.parseInt(id), "t_order_shipment.selectByOrderId");
				this.getRequest().setAttribute("orderShipment", orderShipment);
				
				if(orderShipment!=null && org.apache.commons.lang.StringUtils.isNotEmpty(orderShipment.getLogisticsPosition())){
					
					String jsonArray = orderShipment.getLogisticsPosition();
					
					JSONArray array = JSONArray.fromObject(jsonArray); 
					
					Object[] array2 = JSONArray.toCollection(array, ResultItem.class).toArray();
					
					List<ResultItem> result = new ArrayList<ResultItem>();
					for (Object object : array2) {
						
						result.add((ResultItem)object);
					}
					this.getRequest().setAttribute("result", result);
				}
			}
		}
		return "logistics";
	}
	
	/**
	 * 购物车跳转到结算界面
	 * @return
	 * @throws Exception 
	 */
	public String toOrderAdd() throws Exception{
		TMember member = (TMember)this.getSession().getAttribute("member");
		Long memberId = member.getId();
		String receiverId = this.getRequest().getParameter("receiverId");//收货地址id
		String paymentWayId = this.getRequest().getParameter("paymentWayId");//支付方式
		String couponCardId = this.getRequest().getParameter("couponCardId");//优惠券id
		String invoiceTitle = this.getRequest().getParameter("invoiceTitle");//发票抬头
		String ifInvoice = this.getRequest().getParameter("ifInvoice");//是否开具发票
		String invoiceType = this.getRequest().getParameter("invoiceType");//发票类型
		String deliveryId = this.getRequest().getParameter("deliveryId");//配送方式id
		if(!StringUtils.hasText(paymentWayId)){
			paymentWayId="1";
		}
        String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		Map<String,String> publicMap = new HashMap<String,String>();
		publicMap.put("paymentMethodId", paymentWayId);
		publicMap.put("receiverId", receiverId);
		publicMap.put("couponCodeId", couponCardId);
		publicMap.put("deliveryId", deliveryId);
		publicMap.put("platform", "2");
		publicMap.put("memberId", String.valueOf(memberId));
		publicMap.put("receiverNewFlag", "1");// 使用新的收货地址标记   Flag  1 表示是
		OrderInitPublicVO orderinitpublicvo = new OrderInitPublicVO();
		String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"orderinit");
		if(JSONObject.fromObject(resultJsonstr).get("flag").equals("1")){
			Map<String, Class> classMap = new HashMap<String, Class>();  
			classMap.put("paymethodList", PaymethodVO.class);  
			classMap.put("goodsList", CartGoodVO.class);    
			classMap.put("memberCouponUserList", Map.class);
			orderinitpublicvo =  (OrderInitPublicVO) JSONObject.toBean(JSONObject.fromObject(JSONObject.fromObject(resultJsonstr).get("orderInitVO")),OrderInitPublicVO.class,classMap);
		}
		String hdfkFlag="1";//货到付款是否显示标记   1 显示  0 不显示
		
		String hdfk_area =tsysparametermanager.getKeys("paymethod_hdfk_area");// 得到支持 货到付款的区域
		if(orderinitpublicvo.getReceiver()!=null&&orderinitpublicvo.getReceiver().getAreaName().indexOf(hdfk_area)==-1){
			hdfkFlag="0";
		}
		this.getRequest().setAttribute("hdfkFlag", hdfkFlag);
		this.getRequest().setAttribute("paymentWayId", paymentWayId);
		this.getRequest().setAttribute("goodsPrice", orderinitpublicvo.getPrice());
		this.getRequest().setAttribute("receiver", orderinitpublicvo.getReceiver());
		if(orderinitpublicvo.getReceiver()!=null){
			this.getRequest().setAttribute("receiverId", String.valueOf(orderinitpublicvo.getReceiver().getReceiverId()));
		}
		this.getRequest().setAttribute("deliveryName", orderinitpublicvo.getDeliveryName());//配送方式名称
		this.getRequest().setAttribute("deliveryId", orderinitpublicvo.getDeliveryId());//配送方式id
		this.getRequest().setAttribute("freight", orderinitpublicvo.getFreight());//运费
		this.getRequest().setAttribute("amountPayable", orderinitpublicvo.getAmountPayable());//应付金额
		this.getRequest().setAttribute("priceNofreight", (orderinitpublicvo.getAmountPayable()==null?new BigDecimal("0"):orderinitpublicvo.getAmountPayable()).subtract((orderinitpublicvo.getFreight()==null?new BigDecimal("0"):orderinitpublicvo.getFreight())));//不包含运费的订单金额
		if(orderinitpublicvo.getMemberCouponUserList()!=null){
			this.getRequest().setAttribute("couponSize", orderinitpublicvo.getMemberCouponUserList().size());
		}else{
			this.getRequest().setAttribute("couponSize", 0);
		}
		this.getRequest().setAttribute("couponName", orderinitpublicvo.getCouponName());//优惠券名称
		this.getRequest().setAttribute("couponCardId", orderinitpublicvo.getCouponCardId());//优惠券Id
		
	
		CLocationCityExample cityexample = new CLocationCityExample();
		cityexample.createCriteria().andGradeEqualTo(2);
		cityexample.setOrderByClause(" pinyin asc,name asc ");
		List<CLocationCity> cityList =this.clocationcitymanager.selectByExample(cityexample);
		List<CLocationCity> cityzimuList = null;
		TreeMap<String,List<CLocationCity>> zimuMap = new TreeMap<String,List<CLocationCity>>();
		for(CLocationCity city:cityList){
			if(zimuMap.get(city.getPinyin())!=null){
				cityzimuList = zimuMap.get(city.getPinyin());
				cityzimuList.add(city);
				zimuMap.put(city.getPinyin(), cityzimuList);
			}else{
				cityzimuList = new ArrayList<CLocationCity>();
				cityzimuList.add(city);
				zimuMap.put(city.getPinyin(), cityzimuList);
			}
		}
		this.getRequest().setAttribute("zimuMap", zimuMap);
		return "order_add";
	}
	
	
	/**
	 * 购物车跳转到结算界面
	 * @return
	 * @throws Exception 
	 */
	public String toReserveOrderAdd() throws Exception{
		TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		if(member == null){
			return null;
		}
		
		Long memberId =member.getId();
		
		// 查询收获地址
		TMemberReceiverLatLonExample example = new TMemberReceiverLatLonExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		example.setOrderByClause(" is_default desc");
		List<TMemberReceiverLatLon> receiverLatLonList = tmemberreceiverlatlonmanager.selectByExample(example);
		String receiverId = null;
		TMemberReceiverLatLon receiverLatLon = null;
		if(receiverLatLonList!=null&&receiverLatLonList.size()>0){
			receiverLatLon=receiverLatLonList.get(0);
			receiverId = String.valueOf(receiverLatLonList.get(0).getId());
			this.getRequest().setAttribute("receiverId", receiverId);
			this.getRequest().setAttribute("receiver", receiverLatLon);
		}
		
		// 查询可以用的优惠券
		this.getRequest().setAttribute("receiverList", receiverLatLonList);//收货地址
		
		CLocationCityExample cityexample = new CLocationCityExample();
		cityexample.createCriteria().andGradeEqualTo(2);
		cityexample.setOrderByClause(" pinyin asc");
		List<CLocationCity> cityList =this.clocationcitymanager.selectByExample(cityexample);
		List<CLocationCity> cityzimuList = null;
		TreeMap<String,List<CLocationCity>> zimuMap = new TreeMap<String,List<CLocationCity>>();
		for(CLocationCity city:cityList){
			if(zimuMap.get(city.getPinyin())!=null){
				cityzimuList = zimuMap.get(city.getPinyin());
				cityzimuList.add(city);
				zimuMap.put(city.getPinyin(), cityzimuList);
			}else{
				cityzimuList = new ArrayList<CLocationCity>();
				cityzimuList.add(city);
				zimuMap.put(city.getPinyin(), cityzimuList);
			}
		}
		this.getRequest().setAttribute("zimuMap", zimuMap);
		
		String goodsId = this.getRequest().getParameter("goodsId");//商品id
		if(goodsId == null || goodsId.trim().length() == 0){
			return null;
		}
		Map<String,Object> reserveOrderMap = rxtreserveordermanager.getReserveOrderPrice(receiverId,memberId,Long.valueOf(goodsId),1);
		Map goodsMap = (Map) reserveOrderMap.get("goods");
		this.getRequest().setAttribute("goods", goodsMap);
		return "reserve_order_add";
	}
	
//	/**
//	 * 优惠券列表页面
//	 * @return
//	 * @throws SQLException
//	 */
//	public String orderCouponList() throws SQLException{
//		TMember member = (TMember)this.getSession().getAttribute("member");
//		Long memberId = member.getId();
//		// 查询可以用的优惠券
//	    List<Map<String, Object>> couponList = this.tordermanager.getCouponByGoods(null,String.valueOf(memberId));
//	    if(couponList!=null&&couponList.size()>0){
//	    	this.getRequest().setAttribute("couponList", couponList);//优惠券
//	    }
////	    pushRequest(this.getRequest());
//	   return "order_coupon_list";
//	}
//	public void pushRequest(HttpServletRequest request){
//		request.setAttribute("couponCardType", request.getParameter("couponCardType"));
//		request.setAttribute("receiverId", request.getParameter("receiverId"));
//		request.setAttribute("paymentWayId", request.getParameter("paymentWayId"));
//		request.setAttribute("couponCardId", request.getParameter("couponCardId"));
//		request.setAttribute("invoiceTitle", request.getParameter("invoiceTitle"));
//		request.setAttribute("ifInvoice", request.getParameter("ifInvoice"));
//		request.setAttribute("invoiceType", request.getParameter("invoiceType"));
//		request.setAttribute("payPluginId", request.getParameter("payPluginId"));
//	}
//	/**
//	 * 收货地址列表
//	 * @return
//	 * @throws SQLException
//	 */
//	public String orderReceiverList() throws SQLException{
//		TMember member = (TMember)this.getSession().getAttribute("member");
//		Long memberId = member.getId();
//		// 查询收获地址
//		TMemberReceiverExample example = new TMemberReceiverExample();
//		example.createCriteria().andMemberIdEqualTo(memberId);
//		example.setOrderByClause(" is_default desc");
//		List<TMemberReceiver> receiverList = tmemberreceivermanager.selectByExample(example);
//		if(receiverList!=null&&receiverList.size()>0){
//			this.getRequest().setAttribute("receiverList", receiverList);//收货地址
//		}
////		pushRequest(this.getRequest());
//		return "order_receiver_list";
//	}
	/**
	 * 计算创建订单相关金额
	 * @throws Exception 
	 */
	public void getOrderPrice() throws Exception{
		TMember member = (TMember) this.getSession().getAttribute("member");
		 Long memberId = member.getId();
//		// 计算订单相关金额
		String receiverId = this.getRequest().getParameter("receiverId");//收货地址id
		String paymentWayId = this.getRequest().getParameter("paymentWayId");//支付方式
		String couponCardId = this.getRequest().getParameter("couponCardId");//优惠券id
		String deliveryId = this.getRequest().getParameter("deliveryId");//配送方式id
	    String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		Map<String,String> publicMap = new HashMap<String,String>();
		publicMap.put("paymentMethodId", paymentWayId);
		publicMap.put("receiverId", receiverId);
		publicMap.put("couponCodeId", couponCardId);
		publicMap.put("deliveryId", deliveryId);
		publicMap.put("platform", "2");
		publicMap.put("memberId", String.valueOf(memberId));
		publicMap.put("receiverNewFlag", "1");// 使用新的收货地址标记   Flag  1 表示是
		OrderInitPublicVO orderinitpublicvo = new OrderInitPublicVO();
		String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"orderinit");
		if(JSONObject.fromObject(resultJsonstr).get("flag").equals("1")){
			Map<String, Class> classMap = new HashMap<String, Class>();  
			classMap.put("paymethodList", PaymethodVO.class);  
			classMap.put("goodsList", CartGoodVO.class);    
			classMap.put("memberCouponUserList", Map.class);
			orderinitpublicvo =  (OrderInitPublicVO) JSONObject.toBean(JSONObject.fromObject(JSONObject.fromObject(resultJsonstr).get("orderInitVO")),OrderInitPublicVO.class,classMap);
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("goodsPrice", orderinitpublicvo.getPrice());
		resultMap.put("deliveryName", orderinitpublicvo.getDeliveryName());//配送方式名称
		resultMap.put("deliveryId", orderinitpublicvo.getDeliveryId());//配送方式id
		resultMap.put("freight", orderinitpublicvo.getFreight());//运费
		resultMap.put("amountPayable", orderinitpublicvo.getAmountPayable());//应付金额
		resultMap.put("priceNofreight", (orderinitpublicvo.getAmountPayable()==null?new BigDecimal("0"):orderinitpublicvo.getAmountPayable()).subtract((orderinitpublicvo.getFreight()==null?new BigDecimal("0"):orderinitpublicvo.getFreight())));//不包含运费的订单金额
		if(orderinitpublicvo.getMemberCouponUserList()!=null){
			resultMap.put("couponSize", orderinitpublicvo.getMemberCouponUserList().size());
		}else{
			resultMap.put("couponSize", 0);
		}
		resultMap.put("couponName", orderinitpublicvo.getCouponName());//优惠券名称
		resultMap.put("couponCardId", orderinitpublicvo.getCouponCardId());//优惠券Id
		this.writeObjectToResponse(resultMap,ContentType.application_json);
	}
	
	
	/**
	 * 跳转到订单支付页面
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String toOrderPay() throws NumberFormatException, SQLException{
		String orderId = this.getRequest().getParameter("orderId");
		TOrder order = this.tordermanager.selectByPrimaryKey(Long.valueOf(orderId.trim()));
		this.getRequest().setAttribute("order", order);
		return "order_pay";
	}
	
	
	
//	/**
//	 * 跳转到添加修改收货地址页面
//	 * @return
//	 * @throws Exception
//	 */
//	public String toReceiver() throws Exception{
//		String id = this.getRequest().getParameter("id");
//		TMemberReceiver receiver = new TMemberReceiver();
//		if(StringUtils.hasText(id)){//修改
//		   receiver =tmemberreceivermanager.selectByPrimaryKey(Long.valueOf(id.trim()));
//		   this.getRequest().setAttribute("receiver", receiver);//收货地址
//		}
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("parentid", -1);
//		clocationList = opensqlmanage.selectForListByMap(paramMap, "c_location.selectLocationByMap");
////		pushRequest(this.getRequest());
//		return "receiver_update";
//	}
	
	/**
	 * 选择地区
	 * @throws IOException
	 */
	public void clocationSelect() throws IOException{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		String parentid = this.getRequest().getParameter("parentid");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("parentid", Integer.valueOf(parentid));
		clocationList = opensqlmanage.selectForListByMap(paramMap, "c_location.selectLocationByMap");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("clocationList", clocationList);
		if(clocationList!=null&&clocationList.size()>0){
			map.put("level", clocationList.get(0).get("grade"));
		}
		JSONObject json = JSONObject.fromObject(map);
		write.write(json.toString());
		write.close();
	}
	
	/**
	 * 删除收货地址
	 */
	public void deleteReceiver() throws Exception {
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		if (StringUtils.hasText(this.getRequest().getParameter("id"))) {
			this.tmemberreceiverlatlonmanager.deleteByPrimaryKey(Long.valueOf(this.getRequest().getParameter("id")));
			write.write("1");
			write.close();
		} else {
			write.write("0");
			write.close();
		}
	}
	/**
	 * 保存订单
	 */
	public void saveOrder() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		TMember member = (TMember) this.getSession().getAttribute("member");
		 Long memberId = member.getId();
		String receiverId = this.getRequest().getParameter("receiverId");//收货地址id
		String paymentWayId = this.getRequest().getParameter("paymentWayId");//支付方式
		String couponCardId = this.getRequest().getParameter("couponCardId");//优惠券id
		String invoiceTitle = this.getRequest().getParameter("invoiceTitle");//发票抬头
		String ifInvoice = this.getRequest().getParameter("ifInvoice");//是否开具发票
		String deliveryId = this.getRequest().getParameter("deliveryId");//配送方式id
		String remark = this.getRequest().getParameter("remark");//订单备注
		ErrorMessage errorMessage= null;
		boolean validateFlag =true;
		if(!StringUtils.hasText(ifInvoice)){
			ifInvoice="0";
		}
		// 查询购物车商品
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		List<OrderGoodCart> goodsList = this.opensqlmanage.selectForListByMap(paramMap,"t_goods.selectGoodsByCartMemberid");
		if(!StringUtils.hasText(receiverId)&&validateFlag){//判断收获地址
			validateFlag = false;
			errorMessage=new ErrorMessage(false,"1","请选择收获地址");
		}
		if(!StringUtils.hasText(paymentWayId)&&validateFlag){//判断支付方式
			validateFlag = false;
			errorMessage=new ErrorMessage(false,"2","请选支付方式");
		}
		if(ifInvoice.equals("1")&&validateFlag&&!StringUtils.hasText(invoiceTitle)){//发票抬头不能为空
			validateFlag = false;
			errorMessage=new ErrorMessage(false,"3","请填写发票抬头");
		}
		//验证余额支付
		CPaymentWay paymentWay=	cpaymentwaymanager.selectByPrimaryKey(Long.valueOf(paymentWayId.trim()));
		if(goodsList!=null&&goodsList.size()>0&&validateFlag){
			for(OrderGoodCart orderGoodCart:goodsList){
				if(orderGoodCart.getIfPremiums()!=null&&orderGoodCart.getIfPremiums().intValue()==0){//不是赠品
					if(orderGoodCart.getStquan().intValue()<=0){
						errorMessage= new ErrorMessage(false,"5","购物车部分商品库存不足");//购物车部分商品库存不足
						validateFlag=false;
						break;
					}
					
					if(orderGoodCart.getWapStatus().intValue()!=1){
						errorMessage= new ErrorMessage(false,"6","购物车部分商品已经下架");//购物车部分商品已经下架
						validateFlag=false;
						break;
					}
				}
			}
			if(validateFlag){
				String messageContent="";
				for(OrderGoodCart orderGoodCart:goodsList){
					if(orderGoodCart.getType().intValue()==3){//非购买处方药
						messageContent=messageContent+orderGoodCart.getGoodsName()+";";
						log.info("======>手机端提交订单"+orderGoodCart.getGoodsName()+"为不可加入购物车的处方药！");
						validateFlag=false;
					}
				}
				if(validateFlag){//查询收获地址
					TMemberReceiverLatLon tmemberReceiver =tmemberreceiverlatlonmanager.selectByPrimaryKey(Long.valueOf(receiverId.trim()));
					String wjarea ="0";//是否属于望京  0 不属于  1 属于
					boolean ysd_ysdj_areaid_flag = false;
					if(StringUtils.hasText(tmemberReceiver.getStoreId())){
						ysd_ysdj_areaid_flag= true;
					}
					if(tmemberReceiver!=null&&tmemberReceiver.getAreaId()!=null&&ysd_ysdj_areaid_flag){//判断是否属于望京
						wjarea="1";
					}
					for(OrderGoodCart orderGoodCart:goodsList){
						if(orderGoodCart.getType().intValue()==2&&("0".equals(wjarea)||!paymentWay.getPaymentCode().equals(InfoUtil.getInstance().getInfo("config", "payment.hdfk")))){//非购买处方药
							messageContent=messageContent+orderGoodCart.getGoodsName()+";";
							validateFlag=false;
						}
					}
					if(!validateFlag){
						errorMessage= new ErrorMessage(false,"8",messageContent);//购物车商品存在可购买处方药 付款方式不是货到付款或是收获地址不是望京地区	
					}
				}else{
					errorMessage= new ErrorMessage(false,"7",messageContent);//购物车商品存在不可购买的处方药
				}	
			}
		}else{
			errorMessage= new ErrorMessage(false,"4","购物车商品为空");//购物车商品为空
			validateFlag=false;
		}
		if(validateFlag){
			String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
			Map<String,String> publicMap = new HashMap<String,String>();
			publicMap.put("paymentmethodId", paymentWayId);
			publicMap.put("receiverId", receiverId);
			publicMap.put("couponCodeId", couponCardId);
			publicMap.put("deliveryId", deliveryId);
			publicMap.put("platform", "2");//wap
			publicMap.put("memberId", String.valueOf(memberId));
			publicMap.put("isInvoice",ifInvoice);//  1开  2不开
			publicMap.put("invoiceType", "1");
			publicMap.put("invoiceTitle", invoiceTitle);
			publicMap.put("remark", remark);
			publicMap.put("source", "2");//wap
			publicMap.put("receiverNewFlag", "1");// 使用新的收货地址标记   Flag  1 表示是
			String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"ordersave");
			Map<String,Object> resultMap =(Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(resultJsonstr),HashMap.class);
			if(resultMap.get("flag").equals("1")){
				String orderId = String.valueOf(resultMap.get("orderId"));
				if(!"0".equals(orderId)){
					TOrder order = tordermanager.selectByPrimaryKey(Long.valueOf(orderId));
					errorMessage= new ErrorMessage(true,String.valueOf(orderId),String.valueOf(order.getPayableAmount().compareTo(new BigDecimal("0"))));//提交成功
				}else{
					errorMessage= new ErrorMessage(false,"9","提交失败");//提交失败
				}
			}else{
				errorMessage= new ErrorMessage(false,"9","提交失败");//提交失败
			}
		}
		JSONObject json = JSONObject.fromObject(errorMessage);
		write.write(json.toString());
		write.close();
		return ;
	}
	/**
	 * 更改支付方式 修改订单
	 */
	public void updateOrderPaymentMethod() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		ErrorMessage errorMessage= null;
		try{
			TMember member = (TMember) this.getSession().getAttribute("member");
			Long memberId = member.getId();			
			String orderId = this.getRequest().getParameter("orderId");//订单id
			String paymentWayId = this.getRequest().getParameter("paymentMethodId");//支付方式
			String paymentPluginId = this.getRequest().getParameter("paymentPluginId");//支付插件id
			TOrder order = tordermanager.selectByPrimaryKey(Long.valueOf(orderId));
			if(order!=null){
				if (order.getOrderStatus()!=0) {//待支付
					errorMessage= new ErrorMessage(false,"0","支付失败");
				}else if (order.getPayableAmount().compareTo(new BigDecimal(0)) <= 0) {//订单金额小于0
					errorMessage= new ErrorMessage(false,"0","支付失败");
				}else if(memberId.longValue()!=order.getMemberId().longValue()){//订单不属于当前用户
					errorMessage= new ErrorMessage(false,"0","支付失败");
				}else{
					tordermanager.updateOrderPayment(order,Long.valueOf(paymentWayId), paymentPluginId);
					errorMessage= new ErrorMessage(true,"1","提交成功");
				}
			}else{
				errorMessage= new ErrorMessage(false,"2","该订单不存在");
			}
		}catch(Exception e){
			e.printStackTrace();
			errorMessage= new ErrorMessage(false,"0","支付失败");
		}
		 JSONObject json = JSONObject.fromObject(errorMessage);
		 write.write(json.toString());
		 write.close();
		
	}
	/**
	 * ajax获取配送方式
	 * @throws SQLException 
	 */
	public void ajaxDeliveryWayList() throws SQLException{
		String deliveryId = this.getRequest().getParameter("deliveryId");//配送方式id
		String paymentWayId = this.getRequest().getParameter("paymentWayId");//支付方式
		String receiverId = this.getRequest().getParameter("receiverId");//收货地址id
		this.getRequest().setAttribute("deliveryId", deliveryId);
		if(!StringUtils.hasText(paymentWayId)){
			paymentWayId="1";
		}
		CPaymentWay paymentWay=	this.cpaymentwaymanager.selectByPrimaryKey(Long.valueOf(paymentWayId.trim()));
		Map<String,Object> resultsmap  = new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		String flag="0";
			CDeliveryWayExample example = new CDeliveryWayExample();
			example.createCriteria().andDeliveryCodeNotEqualTo("hdfk");
			List<CDeliveryWay> deliveryList = this.cdeliverywaymanager.selectByExample(example);
			Map<String,Object> resultMap = null;
			TMemberReceiverLatLon memberReceiver = this.tmemberreceiverlatlonmanager.selectByPrimaryKey(Long.valueOf(receiverId.trim()));
			 String receiverFlag ="0";// 0 表示外地   
			 String hdfk_area =tsysparametermanager.getKeys("paymethod_hdfk_area");// 得到支持 货到付款的区域
			 
		    boolean ysd_ysdj_areaid_flag = false;
			if(StringUtils.hasText(memberReceiver.getStoreId())){
				ysd_ysdj_areaid_flag = true;
			}
			 if(ysd_ysdj_areaid_flag){//判断望京   是望京
				 receiverFlag="1";//表示 望京
    		 }else if(memberReceiver.getArea().indexOf(hdfk_area)>0){//判断是否是北京   
    			 receiverFlag="2";//表示北京
    		 }
			
			if(deliveryList!=null&&deliveryList.size()>0){
				flag="1";
				for(CDeliveryWay delivery:deliveryList){
					resultMap = new HashMap<String,Object>();
					resultMap.put("id", delivery.getId());
					resultMap.put("name", delivery.getName());
					resultMap.put("instro", delivery.getInstro());
					resultMap.put("isflag", "0");//表示否
					if(!paymentWay.getPaymentCode().equals("hdfk")){
						    if(delivery.getDeliveryCode().equals("ptkd")){//普通快递
							   if(receiverFlag.equals("0")||receiverFlag.equals("2")){//外地  或是北京
								   resultMap.put("isflag", "1");//表示是
							   }
							}else if(delivery.getDeliveryCode().equals("ysd")){//药士达
							   if(receiverFlag.equals("1")){//表示 望京
								   resultMap.put("isflag", "1");//表示是
							   }
							}else if(delivery.getDeliveryCode().equals("ysdj")){//药师到家
							   if(receiverFlag.equals("1")){//表示 望京
									   resultMap.put("isflag", "1");//表示是
								}
							}
					}else{
						if(receiverFlag.equals("1")){//表示 望京
							if(delivery.getDeliveryCode().equals("ysd")|| delivery.getDeliveryCode().equals("ysdj")){//药士达    药师到家
								 resultMap.put("isflag", "1");//表示是
							}
						}
					}
					resultMap.put("initPrice", delivery.getInitPrice());//价格
					resultList.add(resultMap);
				}
			}
		resultsmap.put("flag", flag);
		resultsmap.put("resultList", resultList);
		this.writeObjectToResponse(resultsmap, ContentType.application_json);
		
	}
	
	/**
	 * 订单支付
	 */
	public String payOrder() throws Exception{
		try{
			String orderId = this.getRequest().getParameter("orderId");//订单id
			String paymentPluginId = this.getRequest().getParameter("paymentPluginId");//支付插件id
			TMember member = (TMember) this.getSession().getAttribute("member");
			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			if(StringUtils.hasText(orderId)){
				TOrder order = this.tordermanager.selectByPrimaryKey(Long.parseLong(orderId.trim())); 
				if(order.getMemberId().longValue()==member.getId().longValue()){
					TOrderFlowExample flowExample = new TOrderFlowExample();
					flowExample.createCriteria().andMemberIdEqualTo(order.getMemberId()).andOrderIdEqualTo(order.getId());
					List<TOrderFlow> orderFlowList = torderflowmanager.selectByExample(flowExample);
					TOrderFlow orderFlow= null;
					String paymentPluginName="";
					if ("alipayWapPlugin".equals(paymentPluginId)) {// 支付宝wap支付
						paymentPluginName = "WAP支付宝支付";
					} else if ("payWapYktPlugin".equals(paymentPluginId)) {// wap医卡通支付
						paymentPluginName = "WAP医卡通支付";
					} else if ("wzfWapPlugin".equals(paymentPluginId)) {// wap微信支付
						paymentPluginName = "WAP微信支付";
					}
					if(orderFlowList!=null&&orderFlowList.size()>0){
						orderFlow = orderFlowList.get(0);
						orderFlow.setPaymentPlugin(paymentPluginId);
						orderFlow.setPayMethod(paymentPluginName);
						orderFlow.setHavePay(order.getPayableAmount());
						orderFlow.setOrderId(order.getId());
						torderflowmanager.updateByPrimaryKeySelective(orderFlow);
					}else{
						orderFlow =new TOrderFlow();
						orderFlow.setMemberId(order.getMemberId());
						orderFlow.setPaymentStatus(0);//支付状态 0 未支付 1已支付
						orderFlow.setPaymentPlugin(paymentPluginId);
						orderFlow.setPayMethod(paymentPluginName);
						orderFlow.setHavePay(order.getPayableAmount());
						orderFlow.setCreateTime(new Date());
						orderFlow.setOrderId(order.getId());
						torderflowmanager.insertSelective(orderFlow);
					}
					
				// 如果使用医卡通,添加商品清单
				if (paymentPluginId.equals("payWapYktPlugin")) {//医卡通
					Map<String, Object> parMap = new HashMap<String, Object>();
					parMap.put("orderId", order.getId());
					List<Map<String,Object>> orderItemList =this.opensqlmanage.selectForListByMap(parMap, "t_order_item.selectOrderItemGoodsByOrderid");
					this.getRequest().setAttribute("items", orderItemList);
					BigDecimal shouldAmount = new BigDecimal(0);
					for (Map<String,Object> oi : orderItemList) {
						shouldAmount = shouldAmount.add(((BigDecimal)oi.get("price")).multiply(new BigDecimal((Integer)oi.get("quantity"))));
					}
					// 减免金额
					parameterMap.put("costAdjust",order.getPayableAmount().subtract(shouldAmount));
					PayWapYktPlugin payWapYkt = new PayWapYktPlugin();
					parameterMap.putAll(payWapYkt.getParameterMap(order,order.getOrderSn(), this.getRequest()));
					this.getRequest().setAttribute("parameterMap", parameterMap);
					this.getRequest().setAttribute("requestUrl", payWapYkt.getRequestUrl());
					this.getRequest().setAttribute("requestMethod", payWapYkt.getRequestMethod());
					this.getRequest().setAttribute("requestCharset", payWapYkt.getRequestCharset());
					return "pay_submit";
				 }else if(paymentPluginId.equals("alipayWapPlugin")){//支付宝wap
					 AlipayWapPlugin alipayWap = new AlipayWapPlugin();
					 parameterMap.putAll(alipayWap.getParameterMap(order,order.getOrderSn(), this.getRequest()));
					 this.getRequest().setAttribute("parameterMap", parameterMap);
					 this.getRequest().setAttribute("requestUrl", alipayWap.getRequestUrl());
					 this.getRequest().setAttribute("requestMethod", alipayWap.getRequestMethod());
					 this.getRequest().setAttribute("requestCharset", alipayWap.getRequestCharset());
					 return "pay_submit";
				 }else if(paymentPluginId.equals("wzfWapPlugin")){//微信WAP支付
					 WzfWapPlugin wzfWapPlugin= new WzfWapPlugin();
					 parameterMap.putAll(wzfWapPlugin.getParameterMap(order,order.getOrderSn(), this.getRequest()));
					 this.getRequest().setAttribute("parameterMap", parameterMap);
					 this.getRequest().setAttribute("requestUrl", wzfWapPlugin.getRequestUrl());
					 this.getRequest().setAttribute("requestMethod", wzfWapPlugin.getRequestMethod());
					 this.getRequest().setAttribute("requestCharset", wzfWapPlugin.getRequestCharset());
					 return "pay_submit";
				 }
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 支付回调方法 跳转支付页面
	 * @return
	 * @throws Exception
	 */
	public String pageReturnUrl() throws Exception{
		String ordersn ="";
		if(StringUtils.hasText(this.getRequest().getParameter("serialNo"))){//医卡通
			ordersn =this.getRequest().getParameter("serialNo");
		}else if(StringUtils.hasText(this.getRequest().getParameter("out_trade_no"))){//支付宝 
			ordersn =this.getRequest().getParameter("out_trade_no");
		}else if(StringUtils.hasText(this.getRequest().getParameter("order_no"))){//微信支付
			ordersn =this.getRequest().getParameter("order_no");
		}else if(StringUtils.hasText(this.getRequest().getParameter("ordersn"))){//订单编号
			ordersn = this.getRequest().getParameter("ordersn");
		}
		if(StringUtils.hasText(ordersn)){
			TOrderExample orderExample = new TOrderExample();
			orderExample.createCriteria().andOrderSnEqualTo(ordersn.trim());
			List<TOrder> orderList =this.tordermanager.selectByExample(orderExample);
			if (orderList != null && orderList.size() > 0) {
				TOrder order = orderList.get(0);
				CPaymentWay paymentWay = this.cpaymentwaymanager.selectByPrimaryKey(order.getPaymentId());
				this.getRequest().setAttribute("paymentWay", paymentWay);
				this.getRequest().setAttribute("order", order);
				return "pay_result";
			}
		}
		return null;
	}
	
	/**
	 * 取消订单
	 * @throws Exception
	 */
	public void cancelOrder() throws Exception{
		TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		String orderId = this.getRequest().getParameter("orderId");
		ErrorMessage errorMessage = null;
		try{
			if(StringUtils.hasText(orderId)){
		           TOrder order = this.tordermanager.selectByPrimaryKey(Long.valueOf(orderId));
				   if(order!=null){
					   if(order.getMemberId().longValue()==member.getId().longValue()){
						   if(order.getOrderStatus().intValue()==OrderEnum.UNPAY.getIndex()||order.getOrderStatus().intValue()==OrderEnum.UNDELIVERY.getIndex()){
							   tordermanager.cancelOrder(order.getId(), order.getOrderType(), order.getMemberId());
							   errorMessage = new ErrorMessage(true,"取消成功！","");
						   }else{
							   errorMessage = new ErrorMessage(false,"该订单无法取消,请联系客服！",""); 
						   }
					   }else{
						   errorMessage = new ErrorMessage(false,"非法操作！","");
					   }
				   }else{
					   errorMessage = new ErrorMessage(false,"该订单不存在！","");
				   }
				}else{
					errorMessage = new ErrorMessage(false,"该订单不存在！","");
				}
		}catch(Exception e){
			errorMessage = new ErrorMessage(false,"取消失败！","");
			e.printStackTrace();
		}
		
		writeObjectToResponse(errorMessage, ContentType.application_json);
	}
	
	public class ErrorMessage {
		private boolean flag;
		private String message;
		private String messageContent;
		
		public ErrorMessage() {
			super();
		}
		public ErrorMessage(boolean flag, String message,String messageContent) {
			super();
			this.flag = flag;
			this.message = message;
			this.messageContent= messageContent;
		}
		public boolean isFlag() {
			return flag;
		}
		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getMessageContent() {
			return messageContent;
		}
		public void setMessageContent(String messageContent) {
			this.messageContent = messageContent;
		}
	}
	/**
	 * 完成订单
	 */
	public void complete() throws Exception {
		// 这里获取登陆的用户id
		TMember member = (TMember) this.getSession().getAttribute("member");

		String id = this.getRequest().getParameter("id");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);

		if (id.matches("[0-9]+")) {

			param.put("memberId", member.getId());

			TOrder order = (TOrder) this.opensqlmanage.selectObjectByObject(param, "t_order.select_member_order_by_id");

			if (order.getOrderStatus() == 2) {
				TOrder updateOrder = new TOrder();
				updateOrder.setId(Long.parseLong(id));
				updateOrder.setOrderStatus(3);
				try{
					String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
					tordermanager.complete(updateOrder,publicServiceUrl);
					this.writeObjectToResponse(new ErrorMessage(true, "交易已完成", ""), ContentType.application_json);
				}catch(Exception e){
					e.printStackTrace();
					this.writeObjectToResponse(new ErrorMessage(false, "订单有误,请联系客服", ""), ContentType.application_json);
				}
			} else {
				this.writeObjectToResponse(new ErrorMessage(false, "订单有误,请联系客服", ""), ContentType.application_json);
			}
		} else {
			this.writeObjectToResponse(new ErrorMessage(false, "异常操作", ""), ContentType.application_json);
		}

	}
	/**
	 * 动态获取收货地址列表
	 * @throws SQLException 
	 */
	public void ajaxReceiverList() throws Exception{
		TMember member = (TMember)this.getSession().getAttribute("member");
		Long memberId = member.getId();
		
	   
		String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		Map<String,String> publicMap = new HashMap<String,String>();
		publicMap.put("member_id", String.valueOf(memberId));
	    String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"getReceiverAddress");
	   
	    List<Map<String, Object>> resultList = null;
	    JSONObject jsonObject = JSONObject.fromObject(resultJsonstr);
		if (jsonObject != null) {
			JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("list"));
			resultList = (List<Map<String, Object>>) JSONArray.toCollection(jsonArray, HashMap.class);
		}
		if(resultList!=null&&resultList.size()>0){
			for(Map<String, Object> map:resultList){
				if(map.get("latitude") instanceof JSONNull && map.get("longitude") instanceof JSONNull){
					map.put("latitude","");
					map.put("longitude","");
					map.put("location_address","");
				}
				if(map.get("location_address") instanceof JSONNull){
					map.put("location_address","");
				}
				if(map.get("store_id") instanceof JSONNull){
					map.put("store_id","");
				}
			}
		}
//	 // 查询收获地址
// 		TMemberReceiverExample example = new TMemberReceiverExample();
// 		example.createCriteria().andMemberIdEqualTo(memberId);
// 		example.setOrderByClause(" is_default desc");
// 		List<TMemberReceiver> receiverList = tmemberreceivermanager.selectByExample(example);
 		this.writeObjectToResponse(resultList,ContentType.application_json);
		
	}
	
	/**
	 * ajax动态获取收货地址信息
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void ajaxReceiver() throws Exception{
		String receiverId = this.getRequest().getParameter("receiverId");//收货地址id
		if(StringUtils.hasText(receiverId)){
			
			TMember member = (TMember)this.getSession().getAttribute("member");
			Long memberId = member.getId();
		   
			String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
			Map<String,String> publicMap = new HashMap<String,String>();
			publicMap.put("member_id", String.valueOf(memberId));
			publicMap.put("id", receiverId);
		    String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"getReceiverAddressById");
		   
		    List<Map<String, Object>> resultList = null;
		    Map<String, Object> resultMap = null;
		    JSONObject jsonObject = JSONObject.fromObject(resultJsonstr);
			if (jsonObject != null) {
				resultMap = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(jsonObject.get("address")),Map.class);
			}
			String beijingFlag ="0";//收获地址是否是北京  1 是  0 否
			String hdfk_area =tsysparametermanager.getKeys("paymethod_hdfk_area");// 得到支持 货到付款的区域
			if(resultMap!=null&&resultMap.get("area")!=null&&String.valueOf(resultMap.get("area")).indexOf(hdfk_area)!=-1){
				beijingFlag="1";
			}
			Map<String,Object> receiverMap = new HashMap<String,Object>();
			receiverMap.put("beijingFlag", beijingFlag);
			if(resultMap.get("latitude") instanceof JSONNull && resultMap.get("longitude") instanceof JSONNull){
				resultMap.put("latitude","");
				resultMap.put("longitude","");
			}
			if(resultMap.get("location_address") instanceof JSONNull){
				resultMap.put("location_address","");
			}
			if(resultMap.get("store_id") instanceof JSONNull){
				resultMap.put("store_id","");
			}
			if (resultMap != null && resultMap.get("area_id") != null) {
				CLocationCity cLocationCity = clocationcitymanager.selectByPrimaryKey(Integer.valueOf(resultMap.get("area_id").toString()));
				if(cLocationCity!=null){
					if(cLocationCity.getGrade().intValue()==3){
						cLocationCity = clocationcitymanager.selectByPrimaryKey(cLocationCity.getParentid());
					}else if(cLocationCity.getGrade().intValue()==4){
						cLocationCity = clocationcitymanager.selectByPrimaryKey(cLocationCity.getParentid());
						cLocationCity = clocationcitymanager.selectByPrimaryKey(cLocationCity.getParentid());
					}
					resultMap.put("citycode", cLocationCity.getCitycode());
				}
			}
			receiverMap.put("receiver", resultMap);
			this.writeObjectToResponse(receiverMap,ContentType.application_json);
		}
	}
	
	/**
	 * ajax获取一级地区
	 */
	public void ajaxClocationList(){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("parentid", -1);
		clocationList = opensqlmanage.selectForListByMap(paramMap, "c_location.selectLocationByMap");
		this.writeObjectToResponse(clocationList,ContentType.application_json);
	}
	/**
	 *  ajax 保存或是修改  收货地址
	 * @throws SQLException 
	 */
	public void ajaxSaveOrUpdateReceiver() throws Exception{
		TMember member = (TMember) this.getSession().getAttribute("member");
		Long memberId = member.getId();
		String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		Map<String,String> publicMap = new HashMap<String,String>();
		publicMap.put("member_id", String.valueOf(memberId));
		publicMap.put("mobile", memberReceiver.getMobile().trim());
		publicMap.put("receiver", memberReceiver.getReceiver().trim());
		publicMap.put("longitude", memberReceiver.getLongitude());
		publicMap.put("latitude", memberReceiver.getLatitude());
		publicMap.put("locationAddress", memberReceiver.getLocationAddress().trim());
		publicMap.put("address", memberReceiver.getAddress().trim());
//		publicMap.put("zipCode", tmemberreceiver.getZipCode().trim());
		if(StringUtils.hasText(memberReceiver.getLongitude())&&StringUtils.hasText(memberReceiver.getLatitude())){
			String memberReceiver_adcode = this.getRequest().getParameter("memberReceiver_adcode");
			CLocationCityExample example = new CLocationCityExample();
			example.createCriteria().andCitycodeEqualTo(memberReceiver_adcode).andGradeEqualTo(2);
			List<CLocationCity> cityList =this.clocationcitymanager.selectByExample(example);
			if(cityList!=null&&cityList.size()>0){
				publicMap.put("areaId",String.valueOf(cityList.get(0).getId()));
			}
			publicMap.put("cityName", this.getRequest().getParameter("city_name_set"));
		}else{
			publicMap.put("areaId",this.getRequest().getParameter("memberReceiver_areaId"));
			String cityname =this.clocationcitymanager.selectAreaName(Long.valueOf(this.getRequest().getParameter("memberReceiver_areaId")));
			publicMap.put("cityName", cityname);
		}
		publicMap.put("lonlatToAddressFlag", "1");// 是否根据经纬度 反解析地区信息  1表示否   其他表示不用
		if (memberReceiver.getId() != null) {
			publicMap.put("id", String.valueOf(memberReceiver.getId()));
		    String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"updateReceiverAddress");
		    JSONObject jsonObject = JSONObject.fromObject(resultJsonstr);
			if (jsonObject != null && jsonObject.get("statusCode") != null && "1".equals(jsonObject.get("statusCode"))) {
				this.writeObjectToResponse(memberReceiver.getId(),ContentType.application_json);
			}else{
				this.writeObjectToResponse("",ContentType.application_json);
			}
		} else {
			String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"addReceiverAddress");
			JSONObject jsonObject = JSONObject.fromObject(resultJsonstr);
			if (jsonObject != null && jsonObject.get("statusCode") != null && "1".equals(jsonObject.get("statusCode"))) {
				Long id = Long.valueOf(String.valueOf(jsonObject.get("id")));
				this.writeObjectToResponse(id,ContentType.application_json);
			}else{
				this.writeObjectToResponse("",ContentType.application_json);
			}
		}
	}
	
	
	/**
	 * 根据城市查询 区域
	 * @throws Exception
	 */
	public void ajaxGetAreaByCity() throws Exception{
		String citycode = this.getRequest().getParameter("citycode");
		if(StringUtils.hasText(citycode)){
			CLocationCityExample cLocationCityExample = new CLocationCityExample();
			cLocationCityExample.createCriteria().andCitycodeEqualTo(citycode);
		    List<CLocationCity> cityAreaList =this.clocationcitymanager.selectByExample(cLocationCityExample);
		    if(cityAreaList!=null&&cityAreaList.size()>0){
		    	cLocationCityExample = new CLocationCityExample();
				cLocationCityExample.createCriteria().andParentidEqualTo(cityAreaList.get(0).getId());
				cityAreaList = clocationcitymanager.selectByExample(cLocationCityExample);
				this.writeObjectToResponse(cityAreaList,ContentType.application_json);
		    }
		}
	}
	/**
	 * 根据城市查询 区域
	 * @throws Exception
	 */
	public void ajaxGetTwoAreaByAreaId() throws Exception{
		String areaId = this.getRequest().getParameter("areaId");
		if(StringUtils.hasText(areaId)){
		    CLocationCityExample cLocationCityExample = new CLocationCityExample();
			cLocationCityExample.createCriteria().andParentidEqualTo(Integer.valueOf(areaId));
			List<CLocationCity> cityAreaList = clocationcitymanager.selectByExample(cLocationCityExample);
			this.writeObjectToResponse(cityAreaList,ContentType.application_json);
		    }
	}
	
	/**
	 * 检验优惠券是否可以使用
	 * @throws Exception
	 */
	public void checkCouponCard() throws Exception{
		TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		String couponCardNo = this.getRequest().getParameter("couponCardNo");//优惠券卡号
		Map<String,String> resultMap = new HashMap<String,String>();
		String resultFlag="0";
		String couponName="";
		String couponCardId="";
		String errorMessage="";
		try{
		  if(StringUtils.hasText(couponCardNo)){
				String publicServiceUrl = tsysparametermanager
						.getKeys("public_service_url");
				Map<String, String> publicMap = new HashMap<String, String>();
				publicMap.put("couponCardNo", couponCardNo.trim());
				publicMap.put("platform", "2");
				publicMap.put("memberId", String.valueOf(member.getId()));
				String resultJsonstr = ClientSubmitPublic.getPublicService(
						publicMap, publicServiceUrl + "ordercouponuser");
				Map<String, String> resultPublicMap = (Map<String, String>) JSONObject
						.toBean(JSONObject.fromObject(resultJsonstr),
								HashMap.class);
				if (resultPublicMap.get("resultflag").equals("1")) {
					couponCardId = resultPublicMap.get("couponCardId");
					couponName = resultPublicMap.get("couponName");
					resultFlag="1";
				}else{
					errorMessage=resultPublicMap.get("errorMessage");
					resultFlag="4";
				}
			}else{
			 resultFlag="2";
			}
		}catch(Exception e){
			e.printStackTrace();
			resultFlag="-1";
		}
		resultMap.put("resultFlag", resultFlag);
		resultMap.put("name", couponName);
		resultMap.put("couponCardId", couponCardId);
		resultMap.put("errorMessage", errorMessage);
		writeObjectToResponse(resultMap, ContentType.application_json);
	}
	
	
	/**
	 * 优惠券列表页面
	 * @return
	 * @throws SQLException
	 */
	public void ajaxOrderCouponList() throws Exception{
		TMember member = (TMember)this.getSession().getAttribute("member");
		Long memberId = member.getId();
//		// 查询可以用的优惠券
//	    List<Map<String, Object>> couponList = this.tordermanager.getCouponByGoods(null,String.valueOf(memberId));
//	    if(couponList!=null&&couponList.size()>0){
//	    	writeObjectToResponse(couponList, ContentType.application_json);
//	    }
        String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		Map<String,String> publicMap = new HashMap<String,String>();
		publicMap.put("platform", "2");
		publicMap.put("memberId", String.valueOf(memberId));
		String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"ordercouponlist");
		Map<String, Class> classMap = new HashMap<String, Class>();  
		classMap.put("couponList", HashMap.class);  
		Map<String,Object> resultMap =(Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(resultJsonstr),HashMap.class,classMap);
		if(resultMap.get("flag").equals("1")){
			List<Map<String,Object>> couponPublicList =  (List<Map<String, Object>>) resultMap.get("couponList");
			if(couponPublicList!=null&&couponPublicList.size()>0){
				writeObjectToResponse(couponPublicList, ContentType.application_json);
			}
		}
	}
	
	
	public PageWraper getPw() {
		return pw;
	}

	public void setPw(PageWraper pw) {
		this.pw = pw;
	}

	public PageResult getRs() {
		return rs;
	}

	public void setRs(PageResult rs) {
		this.rs = rs;
	}

	public TCouponManager getTcouponmanager() {
		return tcouponmanager;
	}

	public void setTcouponmanager(TCouponManager tcouponmanager) {
		this.tcouponmanager = tcouponmanager;
	}

	public TCouponCardManager getTcouponcardmanager() {
		return tcouponcardmanager;
	}

	public void setTcouponcardmanager(TCouponCardManager tcouponcardmanager) {
		this.tcouponcardmanager = tcouponcardmanager;
	}

	public TMemberReceiverLatLonManager getTmemberreceiverlatlonmanager() {
		return tmemberreceiverlatlonmanager;
	}

	public void setTmemberreceiverlatlonmanager(
			TMemberReceiverLatLonManager tmemberreceiverlatlonmanager) {
		this.tmemberreceiverlatlonmanager = tmemberreceiverlatlonmanager;
	}

	public TOrderManager getTordermanager() {
		return tordermanager;
	}
	public void setTordermanager(TOrderManager tordermanager) {
		this.tordermanager = tordermanager;
	}

	public List<Map<String, Object>> getClocationList() {
		return clocationList;
	}
	public void setClocationList(List<Map<String, Object>> clocationList) {
		this.clocationList = clocationList;
	}
	

	public TMemberReceiverLatLon getMemberReceiver() {
		return memberReceiver;
	}

	public void setMemberReceiver(TMemberReceiverLatLon memberReceiver) {
		this.memberReceiver = memberReceiver;
	}

	public CPaymentWayManager getCpaymentwaymanager() {
		return cpaymentwaymanager;
	}

	public void setCpaymentwaymanager(CPaymentWayManager cpaymentwaymanager) {
		this.cpaymentwaymanager = cpaymentwaymanager;
	}

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

	public MessageSender getTopicMessageSender() {
		return topicMessageSender;
	}

	public void setTopicMessageSender(MessageSender topicMessageSender) {
		this.topicMessageSender = topicMessageSender;
	}

	public TMemberAccountManager getTmemberaccountmanager() {
		return tmemberaccountmanager;
	}

	public void setTmemberaccountmanager(TMemberAccountManager tmemberaccountmanager) {
		this.tmemberaccountmanager = tmemberaccountmanager;
	}

	public TLeaderStayMoneyManager getTleaderstaymoneymanager() {
		return tleaderstaymoneymanager;
	}

	public void setTleaderstaymoneymanager(TLeaderStayMoneyManager tleaderstaymoneymanager) {
		this.tleaderstaymoneymanager = tleaderstaymoneymanager;
	}

	public CDeliveryWayManager getCdeliverywaymanager() {
		return cdeliverywaymanager;
	}

	public void setCdeliverywaymanager(CDeliveryWayManager cdeliverywaymanager) {
		this.cdeliverywaymanager = cdeliverywaymanager;
	}

	public TOrderFlowManager getTorderflowmanager() {
		return torderflowmanager;
	}

	public void setTorderflowmanager(TOrderFlowManager torderflowmanager) {
		this.torderflowmanager = torderflowmanager;
	}

	public CLocationCityManager getClocationcitymanager() {
		return clocationcitymanager;
	}

	public void setClocationcitymanager(CLocationCityManager clocationcitymanager) {
		this.clocationcitymanager = clocationcitymanager;
	}
	
	/**
	 * 计算预订单价格
	 * @return
	 * @throws Exception
	 */
	public void calculatePrice() throws Exception{
		//获取参数：商品id,商品数量,receiveId
		String goodsId = getRequest().getParameter("goodsId");
		int goodsNum = DataUtil.getIntVal(getRequest().getParameter("goodsNum"));
		String receiverId = getRequest().getParameter("receiverId");
		TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		
		Long memberId =member.getId();
		if(goodsNum < 0){
			Map map = new HashMap();
			map.put("state", 5);//商品数量不正确
			writeObjectToResponse(map, ContentType.text_html);
			return ;
		}
		
		if(goodsId == null || goodsId.trim().length() == 0){
			Map map = new HashMap();
			map.put("state", 4);//商品不存在
			writeObjectToResponse(map, ContentType.text_html);
			return ;
		}
		
		String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		Map<String,String> publicMap = new HashMap<String,String>();
		publicMap.put("memberId", String.valueOf(memberId));
		publicMap.put("receiverId", receiverId);
		publicMap.put("goodid", goodsId);
		publicMap.put("num", goodsNum+"");
		publicMap.put("source", "2");
		String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"getReserveOrderPricesURLService");
	    JSONObject jsonObject = JSONObject.fromObject(resultJsonstr);
	    System.out.println("***"+jsonObject.toString());
	    writeObjectToResponse(jsonObject, ContentType.text_html);
		return ;
	}
	
	/**
	 * 提交订单
	 * @throws Exception
	 */
	public void saveReserveOrder() throws Exception{
		TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		Long memberId =member.getId();
		getResponse().setContentType("text/html;charset=utf-8");
		
		String receiverId = this.getRequest().getParameter("receiverId");
		if(!StringUtils.hasText(receiverId)){//收货地址错误
			writeObjectToResponse(new ErrorMessage(false,"1",""), ContentType.application_json);
			return;
		}
		
		String replyMobile = this.getRequest().getParameter("replyMobile");
		if(!StringUtils.hasText(replyMobile)  ){//回拨电话为空
			writeObjectToResponse(new ErrorMessage(false,"2",""), ContentType.application_json);
			return;
		}
		replyMobile = replyMobile.trim();
		if(!DataUtil.validateMobile(replyMobile)){//回拨电话错误
			writeObjectToResponse(new ErrorMessage(false,"21",""), ContentType.application_json);
			return;
		}
		
		String goodsNum = this.getRequest().getParameter("goodsNum");
		if(!StringUtils.hasText(goodsNum) ){//商品数量
			writeObjectToResponse(new ErrorMessage(false,"3",""), ContentType.application_json);
			return;
		}
		
		String goodsId = this.getRequest().getParameter("goodsId");
		if(!StringUtils.hasText(goodsId) ){//商品不能为空
			writeObjectToResponse(new ErrorMessage(false,"4",""), ContentType.application_json);
			return;
		}else{
			TGoods goods = tgoodsmanager.selectByPrimaryKey(Long.valueOf(goodsId));
			if(goods == null){//商品不存在
				writeObjectToResponse(new ErrorMessage(false,"5",""), ContentType.application_json);
				return;
			}else{//库存不足
				if(goods.getStock() < Long.valueOf(goodsNum)){
					writeObjectToResponse(new ErrorMessage(false,"6",""), ContentType.application_json);
					return;
				}
			}
		}
		
		String remark = getRequest().getParameter("remark");
		String rxImgUrl = getRequest().getParameter("rxImgUrl");
		//所有校验已经通过
		//调用接口保存预订单
		//根据保存结果返回数据
		
		String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		Map<String,String> publicMap = new HashMap<String,String>();
		publicMap.put("memberId", String.valueOf(memberId));
		publicMap.put("receiverId", receiverId);
		publicMap.put("goodid", goodsId);
		publicMap.put("num", goodsNum);
		publicMap.put("replyMobile", replyMobile);
		publicMap.put("remark",remark);
		publicMap.put("rxImgUrl", rxImgUrl);
		publicMap.put("source", "2");
		
	    String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"createReserveOrderURLService");
	    JSONObject jsonObject = JSONObject.fromObject(resultJsonstr);
	    if(jsonObject != null){
	    	if(jsonObject.get("statusCode") != null){
	    		String statusCode = jsonObject.get("statusCode").toString();
	    		if(statusCode.equals("1")){
	    			String rxTReserveOrderId = jsonObject.get("rxTReserveOrderId").toString();//生成的预订单id
	    			String orderAmount = jsonObject.get("orderAmount").toString();//订单金额
	    			Map resultMap = new HashMap();
	    			resultMap.put("rxTReserveOrderId", rxTReserveOrderId);
	    			resultMap.put("orderAmount", orderAmount);
	    			resultMap.put("flag", true);
	    			writeObjectToResponse(resultMap, ContentType.application_json);//保存预订单成功
	    			//发送短信
	    			sendSuccessMsg(replyMobile);
	    			return;
	    		}else if(statusCode.equals("0")){
	    			String rxTReserveOrderId = jsonObject.get("rxTReserveOrderId").toString();
	    			if(rxTReserveOrderId.equals("-1000")){//如果 -1000(缺货) -1001(商品不存在)
	    				writeObjectToResponse(new ErrorMessage(false,"8",""), ContentType.application_json);//缺货
	    				return;
	    			}else if(rxTReserveOrderId.equals("-1001")){
	    				writeObjectToResponse(new ErrorMessage(false,"9",""), ContentType.application_json);//商品不存在
	    				return;
	    			}else if(rxTReserveOrderId.equals("-1002")){
	    				writeObjectToResponse(new ErrorMessage(false,"10",""), ContentType.application_json);//商品已下架
	    				return;
	    			}
	    		}
	    	}
	    }
	    
	    writeObjectToResponse(new ErrorMessage(false,"7",""), ContentType.application_json);//保存预订单失败
	    System.out.println(resultJsonstr);
	    
	}
	
	private void sendSuccessMsg(String mobile) throws Exception{
		//发送短信
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobiles", mobile);
		map.put("smsContent", InfoUtil.getInstance().getInfo("smsmessages", "createReserveOrderSuccessMsg"));
		String YAO_GATEWAY_URL =tsysparametermanager.getKeys("sms");
		String buildRequestBySMS = ClientSubmit.buildRequestBySMS(map,YAO_GATEWAY_URL);
	}
	/**
	 * 上传自定义图片
	 */
	public void uploadCustomsImage()
	{
		try 
		{
			String imgStr = getRequest().getParameter("imageData");
			imgStr = imgStr.replaceAll("data:image/jpeg;base64,", " ");
			imgStr = imgStr.trim();
			if(imgStr.getBytes().length < MAXSIZE){
				String webPath = Base64ToImage.GenerateImage(getRequest(), imgStr, diskPath);
				
				Map resultMap = new HashMap();
				resultMap.put("state", 1);
				resultMap.put("webPath", webPath);
				this.writeObjectToResponse(resultMap, ContentType.application_json);
			}else{
				Map resultMap = new HashMap();
				resultMap.put("state", 0);
				resultMap.put("message", "img size large than 5M");
				this.writeObjectToResponse(resultMap, ContentType.application_json);
			}
			
		} 
		catch (Exception e) 
		{
			Map resultMap = new HashMap();
			resultMap.put("state", 2);
			resultMap.put("webPath", "");
			this.writeObjectToResponse(0, ContentType.application_json);
			e.printStackTrace();
		}
	}
}
