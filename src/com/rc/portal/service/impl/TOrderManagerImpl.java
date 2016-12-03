package com.rc.portal.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.rc.dst.client.util.ClientSubmitPublic;
import com.rc.portal.dao.CDeliveryWayDAO;
import com.rc.portal.dao.CPaymentWayDAO;
import com.rc.portal.dao.IDaoManager;
import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.TCartDAO;
import com.rc.portal.dao.TCartItemDAO;
import com.rc.portal.dao.TCouponCardDAO;
import com.rc.portal.dao.TCouponDAO;
import com.rc.portal.dao.TGoodsBrokerageDAO;
import com.rc.portal.dao.TGoodsDAO;
import com.rc.portal.dao.TMemberBalanceDAO;
import com.rc.portal.dao.TMemberIntegralDAO;
import com.rc.portal.dao.TMemberLeaderDAO;
import com.rc.portal.dao.TMemberReceiverDAO;
import com.rc.portal.dao.TOrderDAO;
import com.rc.portal.dao.TOrderFlowDAO;
import com.rc.portal.dao.TOrderItemDAO;
import com.rc.portal.dao.TOrderLeaderDAO;
import com.rc.portal.dao.TPromotionDAO;
import com.rc.portal.dao.TReturnDAO;
import com.rc.portal.dao.TReturnItemDAO;
import com.rc.portal.dao.TShortOrderDAO;
import com.rc.portal.dao.TSysParameterDAO;
import com.rc.portal.jms.MessageSender;
import com.rc.portal.service.TOrderManager;
import com.rc.portal.util.OrderEnum;
import com.rc.portal.vo.CPaymentWay;
import com.rc.portal.vo.TMemberIntegral;
import com.rc.portal.vo.TOrder;
import com.rc.portal.vo.TOrderExample;
import com.rc.portal.vo.TOrderFlow;
import com.rc.portal.vo.TOrderFlowExample;
import com.rc.portal.vo.TOrderItem;
import com.rc.portal.vo.TOrderItemExample;
import com.rc.portal.vo.TReturn;
import com.rc.portal.vo.TReturnItem;
import com.rc.portal.vo.TShortOrder;
import com.rc.portal.vo.TShortOrderExample;

public class TOrderManagerImpl  implements TOrderManager {

	private TOrderDAO torderdao;

	private OpenSqlDAO opensqldao;

	private TPromotionDAO tpromotiondao;

	private TMemberReceiverDAO tmemberreceiverdao;

	private CPaymentWayDAO cpaymentwaydao;

	private TOrderItemDAO torderitemdao;

	private TCartDAO tcartdao;

	private TCartItemDAO tcartitemdao;

	private TMemberBalanceDAO tmemberbalancedao;

	private TOrderFlowDAO torderflowdao;

	private IDaoManager cartdao;

	private TMemberIntegralDAO tmemberintegraldao;

	private TShortOrderDAO tshortorderdao;
	private TReturnDAO treturndao;
	private TReturnItemDAO treturnitemdao;
	private TGoodsDAO tgoodsdao;

	private TCouponCardDAO tcouponcarddao;
	private TCouponDAO tcoupondao;
	private TSysParameterDAO tsysparameterdao;

	private TMemberLeaderDAO tmemberleaderdao;

	private TOrderLeaderDAO torderleaderdao;
	
	private CDeliveryWayDAO cdeliverywaydao;
	 
	private MessageSender topicMessageSender;
	
	public TOrderLeaderDAO getTorderleaderdao() {
		return torderleaderdao;
	}

	public void setTorderleaderdao(TOrderLeaderDAO torderleaderdao) {
		this.torderleaderdao = torderleaderdao;
	}

	public TMemberLeaderDAO getTmemberleaderdao() {
		return tmemberleaderdao;
	}

	public void setTmemberleaderdao(TMemberLeaderDAO tmemberleaderdao) {
		this.tmemberleaderdao = tmemberleaderdao;
	}

	private TGoodsBrokerageDAO tgoodsbrokeragedao;

	public TOrderManagerImpl() {
		super();
	}

	public void setTorderdao(TOrderDAO torderdao) {
		this.torderdao = torderdao;
	}

	public TOrderDAO getTorderdao() {
		return this.torderdao;
	}

	public int countByExample(TOrderExample example) throws SQLException {
		return torderdao.countByExample(example);
	}

	public int deleteByExample(TOrderExample example) throws SQLException {
		return torderdao.deleteByExample(example);
	}

	public int deleteByPrimaryKey(Long id) throws SQLException {
		return torderdao.deleteByPrimaryKey(id);
	}

	public Long insert(TOrder record) throws SQLException {
		return torderdao.insert(record);
	}

	public Long insertSelective(TOrder record) throws SQLException {
		return torderdao.insertSelective(record);
	}

	public List selectByExample(TOrderExample example) throws SQLException {
		return torderdao.selectByExample(example);
	}

	public TOrder selectByPrimaryKey(Long id) throws SQLException {
		return torderdao.selectByPrimaryKey(id);
	}

	public int updateByExampleSelective(TOrder record, TOrderExample example)
			throws SQLException {
		return torderdao.updateByExampleSelective(record, example);
	}

	public int updateByExample(TOrder record, TOrderExample example)
			throws SQLException {
		return torderdao.updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(TOrder record) throws SQLException {
		return torderdao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TOrder record) throws SQLException {
		return torderdao.updateByPrimaryKey(record);
	}

	public TMemberIntegralDAO getTmemberintegraldao() {
		return tmemberintegraldao;
	}

	public void setTmemberintegraldao(TMemberIntegralDAO tmemberintegraldao) {
		this.tmemberintegraldao = tmemberintegraldao;
	}
//	/**
//     * 计算订单相关金额
//     * @param goodsList  商品list
//     * @param couponCardId 优惠券id
//     * @param receiverId   收货地址id
//     * @param memberId   用户id
//     * @param paymentWayId  支付方式
//     * @param deliveryId  配送方式Id
//     * @return
//     * @throws SQLException
//     */
//	public Map<String, Object> getOrderPrice(String couponCardId, String receiverId,String memberId,String paymentWayId,String deliveryId) throws Exception {
//		 Map<String,Object> resultMap = new HashMap<String,Object>();
//         try{
//        	 BigDecimal goodsPrice = new BigDecimal("0");//商品金额
//        	 BigDecimal shippingFee = new BigDecimal("0");//运费
//        	 BigDecimal couponDiscount = new BigDecimal("0");//优惠券优惠金额
//        	 BigDecimal promotionalDiscount = new BigDecimal("0");//促销折扣金额
//        	 Map<String, Object> paramMap = new HashMap<String, Object>();
//        	 List<OrderGoodCart> goodsList = new ArrayList<OrderGoodCart>();
//			paramMap.put("memberId", memberId);
//			goodsList = this.opensqldao.selectForListByMap(paramMap,"t_goods.selectGoodsByCartMemberid");
//			// 这里需要调用建龙计算优惠金额
//			CartParam cartParam = new CartParam();
//			cartParam.setUserId(Long.parseLong(memberId));
//			Map cartMap = cartdao.accounts(cartParam);
//			promotionalDiscount = (BigDecimal) cartMap.get("youhui");
//			//  李建龙修改
//			List<CartGift> giftList = (List<CartGift>) cartMap.get("gift");
//			
//			if(giftList!=null&&giftList.size()>0){
//				TGoods goods = null;
//				OrderGoodCart goodCart = null;
//				for(CartGift cartGift:giftList){
//					goods = this.tgoodsdao.selectByPrimaryKey(cartGift.getGoodsid());
//					goodCart = new OrderGoodCart();
//					goodCart.setId(goods.getId());
//					goodCart.setWapPrice(new BigDecimal("0"));
//					goodCart.setGoodsName(goods.getGoodsName());
//					goodCart.setStock(goods.getStock());
//					goodCart.setQuantity(cartGift.getCnt());
//					goodCart.setAbbreviationPicture(goods.getAbbreviationPicture());
//					goodCart.setSpec(goods.getSpec());
//					goodCart.setIfPremiums(1);
//					goodCart.setWeight(goods.getWeight());
//					goodsList.add(goodCart);
//				}
//			}
//					
//        	 BigDecimal weight = new BigDecimal("0");
//        	 boolean feeflag=false; //是否免运费
//        	 if(goodsList!=null&&goodsList.size()>0){//计算商品总金额  总重量
//        		for(OrderGoodCart orderGoodCart:goodsList){
//        			if(orderGoodCart.getIfPremiums()==0){
//        				if (orderGoodCart.getWapPrice() != null) {
//            				goodsPrice = goodsPrice.add(orderGoodCart.getWapPrice().multiply(new BigDecimal(orderGoodCart.getQuantity())));
//    					}
//        			}
//        			if(orderGoodCart.getWeight() != null){
//        				weight =weight.add(orderGoodCart.getWeight().multiply(new BigDecimal(orderGoodCart.getQuantity())));
//        			}
//        		}
//        	 }
//        	 //计算优惠券 优惠金额
//        	 if(StringUtils.hasText(couponCardId)){
//        		 //查询该用户所能使用的优惠券
//        		 paramMap.clear();
// 				 paramMap.put("couponCardId", Long.valueOf(couponCardId.trim()));
// 				 paramMap.put("memberId", Long.valueOf(memberId.trim()));
//        		 List<Map<String, Object>> memberCouponList= this.opensqldao.selectForListByMap(paramMap, "t_coupon_card.selectCouponCardByCardNo");
//        		 List<Map<String, Object>> memberCouponUserList =getCouponByGoods(memberCouponList,String.valueOf(memberId));
//        		 for(Map<String, Object> couponMap : memberCouponUserList){
//        			 if(couponMap.get("id")!=null&&Long.valueOf(couponCardId.trim()).longValue()==((Long)couponMap.get("id")).longValue()){
//        				 if(String.valueOf(couponMap.get("scope")).equals("1")){//免运费
//        					 feeflag=true;
//        				 }else{
//        					 if(couponMap.get("dis_price")!=null){
//            					 couponDiscount = (BigDecimal)couponMap.get("dis_price");
//            				 }
//        				 }
//        			 }
//        		 }
//        	 }
//        	 String ysdjFlag ="0";// 0否   1 是
//        	 CDeliveryWay deliveryWay = new CDeliveryWay();
//        	 String deliveryCode="ptkd";//普通快递
//        	  if(StringUtils.hasText(receiverId)){//收获地址
//          		 TMemberReceiver memberReceiver = this.tmemberreceiverdao.selectByPrimaryKey(Long.valueOf(receiverId.trim()));
//          		 if(String.valueOf(memberReceiver.getAreaId()).equals("3218")){
//          			if (StringUtils.hasText(deliveryId)) {// 配送方式id
//     					deliveryWay = this.cdeliverywaydao.selectByPrimaryKey(Long
//     							.valueOf(deliveryId.trim()));
//     					if (deliveryWay.getDeliveryCode().equals("ysdj")) {// 药师到家
//     						ysdjFlag = "1";
//     						shippingFee = deliveryWay.calculateFreight(weight);
//     					}
//     				} 
//          		 }
//          		if(ysdjFlag.equals("0")){
//          			 if(String.valueOf(memberReceiver.getAreaId()).equals("3218")){//判断望京   是望京
//              			 deliveryCode="ysd";//药士达
//              		 }else if(memberReceiver.getArea().indexOf(InfoUtil.getInstance().getInfo("config","area.beijing"))!=-1){//判断是否是北京   
//              			    if(!StringUtils.hasText(paymentWayId)){
//              			    	paymentWayId ="1";
//              			    }
//              			    CPaymentWay paymentWay=	cpaymentwaydao.selectByPrimaryKey(Long.valueOf(paymentWayId.trim()));
//     	         				if(paymentWay.getPaymentCode().equals("hdfk")){//货到付款
//     	         					deliveryCode="hdfk";
//     	         		}
//              		 }
//          		}
//          	 }
//        	 if(ysdjFlag.equals("0")){//不是 药师到家配送方式
//        		//计算全场免运费金额
//            	 if(!feeflag){
//    			    	String free_shippingfee_price =tsysparameterdao.getKeys("free_shippingfee_price");
//    			    	if(StringUtils.hasText(free_shippingfee_price)){
//    			    		if(goodsPrice.compareTo(new BigDecimal(free_shippingfee_price.trim()))>=0){
//    			    			feeflag=true;
//    			    		}
//    			    	}
//    			    }
//            	 
//            	    paramMap.clear();
//	 				paramMap.put("deliveryCode", deliveryCode);
//	 			    List<CDeliveryWay> deliveryList =this.opensqldao.selectForListByMap(paramMap, "c_delivery_way.selectDeliveryWayByMap");
//	 			    if(deliveryList!=null&&deliveryList.size()>0){
//	 			    	deliveryWay = deliveryList.get(0);
//	 			    	shippingFee=deliveryWay.calculateFreight(weight);
//	 			    }
//	            	if(feeflag){//是否免运费
//	            		  shippingFee = new BigDecimal("0");
//	            	 }
//            	 
////            	 //计算运费
////            	 if(StringUtils.hasText(receiverId)&&!feeflag&&!StringUtils.hasText(deliveryId)){//收货地址
////            		 TMemberReceiver memberReceiver = this.tmemberreceiverdao.selectByPrimaryKey(Long.valueOf(receiverId.trim()));
////            		 if(!String.valueOf(memberReceiver.getAreaId()).equals("3218")){//判断望京
////            			if(StringUtils.hasText(paymentWayId)){
////            				CPaymentWay paymentWay=	cpaymentwaydao.selectByPrimaryKey(Long.valueOf(paymentWayId.trim()));
////            				String deliveryCode="";
////            				if(paymentWay.getPaymentCode().equals("hdfk")){//货到付款
////            					deliveryCode="hdfk";
////            				}else{
////            					deliveryCode="ptkd";
////            				}
////            				paramMap.clear();
////            				paramMap.put("deliveryCode", deliveryCode);
////            			    List<CDeliveryWay> deliveryList =this.opensqldao.selectForListByMap(paramMap, "c_delivery_way.selectDeliveryWayByMap");
////            			    if(deliveryList!=null&&deliveryList.size()>0){
////            			    	CDeliveryWay deliveryWay = deliveryList.get(0);
////            			    	shippingFee=deliveryWay.calculateFreight(weight);
////            			    }
////            			}
////            		 }
////            	 }
//        	 }
//        	//订单金额
//        	 BigDecimal orderPrice = goodsPrice.add(shippingFee).subtract(couponDiscount).subtract(promotionalDiscount);
//        	 if(orderPrice.compareTo(new BigDecimal("0"))<=0){
//        		 orderPrice = new BigDecimal("0");
//        	 }
//        	 resultMap.put("goodsPrice", goodsPrice);
//        	 resultMap.put("orderPrice", orderPrice.setScale(2,   BigDecimal.ROUND_HALF_UP));
//        	 resultMap.put("shippingFee", shippingFee);
//        	 resultMap.put("couponDiscount", couponDiscount);
//        	 resultMap.put("promotionalDiscount",promotionalDiscount);
//        	 resultMap.put("payableAmount", orderPrice.setScale(2,   BigDecimal.ROUND_HALF_UP));
//        	 resultMap.put("goodsList", goodsList);
//        	 resultMap.put("deliveryWay", deliveryWay);
//         }catch(Exception e){
//        	 e.printStackTrace();
//        	 throw new Exception(e.getMessage());
//         }
//		return resultMap;
//	}
	
//	/**
//	 * 根据购物车商品计算优惠券
//	 * 
//	 * @return
//	 * @throws SQLException
//	 */
//	public List<Map<String, Object>> getCouponByGoods(List<Map<String, Object>> memberCouponList,String memberId)
//			throws SQLException {
//		// 可以使用的优惠券
//		List<Map<String, Object>> couponList = new ArrayList<Map<String, Object>>();
//		try {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("memberId", Long.parseLong(memberId));
//			paramMap.put("ifPremiums", 0);
//			List<OrderGoodCart> goodsList = this.opensqldao.selectForListByMap(paramMap,"t_goods.selectGoodsByCartMemberid");
//			if (goodsList != null && goodsList.size() > 0) {
//				Map<String, List<OrderGoodCart>> promotionGoodsMap = new HashMap<String, List<OrderGoodCart>>();
//				List<OrderGoodCart> porList = null;
//				// 把商品按照促销划分一下 按照促销放到 map里面
//				for (OrderGoodCart orderGoodCart : goodsList) {
//					if (orderGoodCart.getPromotionId()!= null) {
//						if (promotionGoodsMap.get(String.valueOf(orderGoodCart.getPromotionId())) != null) {
//							porList = promotionGoodsMap.get(String.valueOf(orderGoodCart.getPromotionId()));
//						} else {
//							porList = new ArrayList<OrderGoodCart>();
//						}
//						porList.add(orderGoodCart);
//						promotionGoodsMap.put(String.valueOf(orderGoodCart.getPromotionId()), porList);
//					}
//				}
//				// 调用建龙方法计算购物车商品 参与的促销活动
//				for (String promotionId : promotionGoodsMap.keySet()) {
//					// 查询促销
//					TPromotion promotion = tpromotiondao.selectByPrimaryKey(Long.parseLong(promotionId));
//					if (promotion.getIsTiket() != null
//							&& promotion.getIsTiket().intValue() == 2) {// 不能使用优惠券
//						// 这里需要根据 建龙的方法判断 该促销的商品是否 满足促销 如果满足促销 则移除 该促销的使所有商品
//						porList = promotionGoodsMap.get(promotionId);
//						if (porList != null) {
//							goodsList.removeAll(porList);
//						}
//					}
//				}
//
//				// 用户拥有的优惠券
//				if(memberCouponList==null){
//					memberCouponList = this.opensqldao.selectForListByMap(paramMap,"t_coupon_card.selectCouponCardByMemberid");
//				}
//				if (memberCouponList != null && memberCouponList.size() > 0 &&goodsList!=null&&goodsList.size()>0) {
//					String goodsIds = "";// 商品ids
//					String brandIds = "";// 品牌ids
//					for (OrderGoodCart orderGoodCart :goodsList) {
//						if (orderGoodCart.getId()!= null) {
//							goodsIds = goodsIds+ String.valueOf(orderGoodCart.getId()) + ",";
//						}
//						if (orderGoodCart.getBrandId()!= null) {
//							brandIds = brandIds+ String.valueOf(orderGoodCart.getBrandId())+ ",";
//						}
//					}
//					BigDecimal goodsumPrice = null;
//					for (Map<String, Object> couponMap : memberCouponList) {
//						if (couponMap.get("scope") != null) {
//							if (String.valueOf(couponMap.get("scope")).equals("1")) {// 免运费
//								couponList.add(couponMap);
//							} else {
//								goodsumPrice = new BigDecimal("0");
//								if (String.valueOf(couponMap.get("scope")).equals("0")) {// 全场
//									for (OrderGoodCart orderGoodCart :goodsList) {
//										if (orderGoodCart.getWapPrice()!= null&&orderGoodCart.getIfPremiums()==0) {
//											goodsumPrice = goodsumPrice.add(orderGoodCart.getWapPrice().multiply(new BigDecimal(orderGoodCart.getQuantity())));
//										}
//									}
//								} else if (String.valueOf(couponMap.get("scope")).equals("2")) {// 分类
//									paramMap.clear();
//									paramMap.put("couponId",couponMap.get("couponid"));
//									List<TCouponRelevance> couponRelevanceList = this.opensqldao
//											.selectForListByMap(paramMap,"t_coupon_relevance.selectCouponRelevanceByMap");
//									if (couponRelevanceList != null
//											&& couponRelevanceList.size() > 0) {
//										for (TCouponRelevance couponRelevance : couponRelevanceList) {
//											for (OrderGoodCart orderGoodCart :goodsList) {
//												if (orderGoodCart.getCategoryid() != null&& couponRelevance.getRelevanceId().longValue() == orderGoodCart.getCategoryid().longValue()) {
//													if (orderGoodCart.getWapPrice() != null&&orderGoodCart.getIfPremiums()==0) {
//														goodsumPrice = goodsumPrice.add(orderGoodCart.getWapPrice().multiply(new BigDecimal(orderGoodCart.getQuantity())));
//													}
//												} else if (orderGoodCart.getCategoryid() != null) {
//													paramMap.clear();
//													paramMap.put("id",couponRelevance.getRelevanceId());
//													List<TCategory> categoryList = this.opensqldao
//															.selectForListByMap(paramMap,"t_category.selectCategoryByMap");
//													if (categoryList != null&& categoryList.size() > 0) {
//														if (categoryList.get(0).getAllParentId().indexOf(String.valueOf(orderGoodCart.getCategoryid())) > 0) {
//															if (orderGoodCart.getWapPrice() != null&&orderGoodCart.getIfPremiums()==0) {
//																goodsumPrice = goodsumPrice.add(orderGoodCart.getWapPrice().multiply(new BigDecimal(orderGoodCart.getQuantity())));
//															}
//														}
//													}
//												}
//											}
//										}
//									}
//
//								} else if (String.valueOf(couponMap.get("scope")).equals("3")) {// 品牌
//									if (StringUtils.hasText(brandIds)) {
//										brandIds = brandIds.substring(0,
//												brandIds.length() - 1);
//										paramMap.clear();
//										paramMap.put("couponId",couponMap.get("couponid"));
//										paramMap.put("relevanceId", brandIds);
//										List<TCouponRelevance> couponRelevanceList = this.opensqldao
//												.selectForListByMap(paramMap,"t_coupon_relevance.selectCouponRelevanceByMap");
//										if (couponRelevanceList != null
//												&& couponRelevanceList.size() > 0) {
//											for (TCouponRelevance couponRelevance : couponRelevanceList) {
//												for (OrderGoodCart orderGoodCart : goodsList) {
//													if (orderGoodCart.getBrandId()!= null
//															&& orderGoodCart.getBrandId().longValue() == couponRelevance.getId().longValue()) {
//														if (orderGoodCart.getWapPrice() != null&&orderGoodCart.getIfPremiums()==0) {
//															goodsumPrice = goodsumPrice.add(orderGoodCart.getWapPrice().multiply(new BigDecimal(orderGoodCart.getQuantity())));
//														}
//													}
//												}
//											}
//										}
//									}
//								} else if (String.valueOf(couponMap.get("scope")).equals("4")) {// 商品
//									if (StringUtils.hasText(goodsIds)) {
//										goodsIds = goodsIds.substring(0,goodsIds.length() - 1);
//										paramMap.clear();
//										paramMap.put("couponId",couponMap.get("couponid"));
//										paramMap.put("relevanceId", goodsIds);
//										List<TCouponRelevance> couponRelevanceList = this.opensqldao
//												.selectForListByMap(paramMap,"t_coupon_relevance.selectCouponRelevanceByMap");
//										if (couponRelevanceList != null
//												&& couponRelevanceList.size() > 0) {
//											for (TCouponRelevance couponRelevance : couponRelevanceList) {
//												for (OrderGoodCart orderGoodCart :goodsList) {
//													if (orderGoodCart.getId()!= null
//															&& orderGoodCart.getId().longValue() == couponRelevance.getId().longValue()) {
//														if (orderGoodCart.getWapPrice()  != null&&orderGoodCart.getIfPremiums()==0) {
//															goodsumPrice = goodsumPrice.add(orderGoodCart.getWapPrice().multiply(new BigDecimal(orderGoodCart.getQuantity())));
//														}
//													}
//												}
//											}
//										}
//									}
//								}
//								if (checkCoupon(couponMap, goodsumPrice)) {
//									couponList.add(couponMap);
//								}
//							}
//						}
//					}
//				}
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return couponList;
//	}

	/**
	 * 判断优惠券是否能用
	 * 
	 * @param couponMap
	 * @param goodsumPrice
	 * @return
	 */
	public boolean checkCoupon(Map<String, Object> couponMap,
			BigDecimal goodsumPrice)  {
		boolean flag = false;
		if (couponMap.get("start_price") != null
				&& couponMap.get("end_price") != null) {
			if (goodsumPrice.compareTo((BigDecimal) couponMap.get("start_price")) >= 0&& goodsumPrice.compareTo((BigDecimal) couponMap.get("end_price")) <= 0) {
				flag = true;
			}
		}
		return flag;
	}
	
	
//	/**
//	 * 保存订单
//	 */
//	public long saveOrder(TOrder order, String receiverId, String payPluginId,String deliveryId)
//			throws Exception {
//	    long orderId =0;
//	    try{
//			String orderSn = getOrderSn();
//			order.setOrderSn("m"+orderSn);
//			order.setOrderSource(2);// 订单来源 1 pc 2 wap 3 安卓 4 ios 5 手动下单
//			// 计算各种运费
//			Map<String, Object> orderPriceMap = getOrderPrice(
//					order.getCouponId() == null ? null : String.valueOf(order.getCouponId()),
//					receiverId,String.valueOf(order.getMemberId()),
//					order.getPaymentId() == null ? null : String.valueOf(order.getPaymentId()),deliveryId);
//			order.setShippingFee((BigDecimal) orderPriceMap.get("shippingFee"));// 运费
//			order.setPromotionalDiscount((BigDecimal) orderPriceMap.get("promotionalDiscount"));// 促销折扣
//			order.setCouponDiscount((BigDecimal) orderPriceMap.get("couponDiscount"));// 优惠券折扣
//			order.setIntegrationDiscount(new BigDecimal("0"));// 积分折扣
//			order.setOrderAmount((BigDecimal) orderPriceMap.get("orderPrice"));// 订单金额
//			order.setPayableAmount((BigDecimal) orderPriceMap.get("payableAmount"));// 应付金额
//			//用于测试使用
////			order.setOrderAmount(new BigDecimal("0.01"));// 订单金额
////			order.setPayableAmount(new BigDecimal("0.01"));// 应付金额
//			order.setUseIntegration(0);// 使用积分
//			order.setAdjustAmount(new BigDecimal("0"));// 调整金额
//			order.setPaidAmount(new BigDecimal("0"));// 已付金额
//			order.setRewardIntegration(order.getOrderAmount().intValue()/2);// 赠送积分
//			// 查询收货地址
//			TMemberReceiver memberReceiver = tmemberreceiverdao
//					.selectByPrimaryKey(Long.valueOf(receiverId.trim()));
//	    	
//			 boolean hdfkFlag = false;
//			 Map<String, Object> paramMap = new HashMap<String, Object>();
//			 CPaymentWay paymentWay= cpaymentwaydao.selectByPrimaryKey(order.getPaymentId());
//			 if(paymentWay.getPaymentCode().equals("hdfk")){//货到付款
//					hdfkFlag = true;
//			  }
//			 if(memberReceiver!=null){
//				 order.setReceiver(memberReceiver.getReceiver());//收货人 
//				 order.setAreaId(memberReceiver.getAreaId());//地区id
//				 order.setAreaName(memberReceiver.getArea());//地区名称
//				 order.setDetailedAddress(memberReceiver.getAddress());//详细地址
//				 order.setZipCode(memberReceiver.getZipCode());//邮编
//				 order.setPhone(memberReceiver.getMobile());//联系电话
//			 }
//			 CDeliveryWay deliveryWay= (CDeliveryWay) orderPriceMap.get("deliveryWay");
//			 order.setDeliveryId(deliveryWay.getId());
//			 order.setIsPush(0);//是否推送海典 0 否 1 是
//			 order.setPushTime(null);//推送海淀时间
//			 order.setDeleteStatus(0);//删除状态 0 未删除 1 已删除
//			 if(hdfkFlag){
//				 order.setOrderStatus(OrderEnum.UNDELIVERY.getIndex());//订单状态  待发货
//			 }else{
//				 order.setOrderStatus(OrderEnum.UNPAY.getIndex());//订单状态 0 未支付
//			 }
//			 order.setVerifyStatus(0);//核实状态  0 未核实  1 已核实
//			 order.setOrderType(1);//订单类型  1-正常 2-秒杀 3-团购
//			 order.setReturnType(1);//返佣状态 1 未返佣  2 已返佣
//			 order.setCreateDate(new Date());//下单时间
//			 //////////////////////////////增加 大兴免运费暂时使用/////////////////////////////////////////////////////
//      		 if(String.valueOf(memberReceiver.getAreaId()).equals("1158")){//大兴区域
//      			order.setShippingFee(new BigDecimal("0"));//运费
//      			order.setOrderAmount(((BigDecimal) orderPriceMap.get("orderPrice")).subtract((BigDecimal) orderPriceMap.get("shippingFee")));// 订单金额
//    			order.setPayableAmount(((BigDecimal) orderPriceMap.get("payableAmount")).subtract((BigDecimal) orderPriceMap.get("shippingFee")));// 应付金额
//    			order.setDeliveryId(2l);//药士达
//      		 }
//			 
//			//保存订单
//			 orderId =this.torderdao.insertSelective(order);
//			 
//			//判断商品
//			 Map<String,Object> goodMap = null;
//			 TOrderItem orderItem = null;
//			 List<OrderGoodCart> goodsList = (List<OrderGoodCart>) orderPriceMap.get("goodsList");
//			 TGoodsBrokerageExample brokerageExample = null;
//			 for(OrderGoodCart goodCart: goodsList){
//				 paramMap.clear();
//				 paramMap.put("id", goodCart.getId());
//				 goodMap = (Map<String, Object>) this.opensqldao.selectForObjectByMap(paramMap, "t_goods.selectByPrimaryKeyForUpdate");
//			     orderItem = new TOrderItem();
//				 orderItem.setOrderId(orderId);
//				 orderItem.setCreateTime(new Date());
//				 orderItem.setGoodsId((Long)goodMap.get("id"));
//				 orderItem.setIfPremiums(goodCart.getIfPremiums());
//				 if(goodCart.getIfPremiums().intValue()==1){//赠品
//					 orderItem.setPrice(new BigDecimal("0"));
//					 if(goodCart.getStquan().intValue()>=0){//判断赠品库存是否充足
//						 orderItem.setQuantity(goodCart.getQuantity());
//					 }else{
//						 orderItem.setQuantity(goodCart.getStock().intValue());
//					 }
//				 }else{
//					 orderItem.setPrice((BigDecimal)goodMap.get("pc_price"));
//					 orderItem.setQuantity(goodCart.getQuantity());
//				 }
//				 orderItem.setIfReviews(0);
//				 brokerageExample = new TGoodsBrokerageExample();
//				 brokerageExample.createCriteria().andGoodsIdEqualTo(orderItem.getGoodsId());
//				 List<TGoodsBrokerage> brokerageList= tgoodsbrokeragedao.selectByExample(brokerageExample);
//				 if(brokerageList!=null&&brokerageList.size()>0){
//					 orderItem.setBrokerage(brokerageList.get(0).getBrokerage());
//				 }else{
//					 orderItem.setBrokerage(Long.valueOf(InfoUtil.getInstance().getInfo("config", "good_yongjin_lilv").trim()));
//				 }
//				 this.torderitemdao.insertSelective(orderItem);
//				 paramMap.clear();
//				 paramMap.put("id", goodCart.getId());
//				 paramMap.put("quantity", goodCart.getQuantity());
//				 this.opensqldao.updateByMap_drug(paramMap, "t_goods.updateGoodStockById");
//			 }
////			 删除购物车
//			 TCartExample cartExample = new TCartExample();
//			 cartExample.createCriteria().andMemberIdEqualTo(order.getMemberId());
//			 List<TCart> cartList =this.tcartdao.selectByExample(cartExample);
//			 if(cartList!=null&&cartList.size()>0){
//				 TCartItemExample cartItemExample = new TCartItemExample();
//				 cartItemExample.createCriteria().andCartIdEqualTo(cartList.get(0).getId()).andIsSelectedEqualTo(1);
//				 this.tcartitemdao.deleteByExample(cartItemExample);
//			 }	 
//			 
//			//更新优惠券使用信息
//			 if(order.getCouponId()!=null){
//				 TCouponCard couponCard = new TCouponCard();
//				 couponCard.setId(order.getCouponId());
//				 couponCard.setIsUse(1);//是否使用 1 已使用
//				 couponCard.setUseTime(new Date());//使用时间
//				 couponCard.setMemberId(order.getMemberId());
//				 tcouponcarddao.updateByPrimaryKeySelective(couponCard);
//			 }
//			if(!hdfkFlag){//货到付款
//			    TOrderFlow orderFlow =new TOrderFlow();
//				orderFlow.setMemberId(order.getMemberId());
//				orderFlow.setPaymentStatus(0);//支付状态 0 未支付 1已支付
//				if("alipayWapPlugin".equals(payPluginId)){//支付宝wap支付
//				  orderFlow.setPaymentPlugin("alipayWapPlugin");
//				  orderFlow.setPayMethod("WAP支付宝支付");
//				}else if("payWapYktPlugin".equals(payPluginId)){//wap医卡通支付
//				  orderFlow.setPaymentPlugin("payWapYktPlugin");
//				  orderFlow.setPayMethod("WAP医卡通支付");
//				}else if("wzfWapPlugin".equals(payPluginId)){//wap微信支付
//				  orderFlow.setPaymentPlugin("wzfWapPlugin");
//				  orderFlow.setPayMethod("WAP微信支付");
//				}
//				orderFlow.setHavePay(order.getPayableAmount());
//				orderFlow.setCreateTime(new Date());
//				orderFlow.setOrderId(orderId);
//				orderFlow.setPayDate(new Date());
//			    this.torderflowdao.insertSelective(orderFlow);
//			}
//	    }catch(Exception e){
//	    	e.printStackTrace();
//	    	throw new Exception(e.getMessage());
//	    }
//		return orderId;
//	}
	
	/**
	 * 获取订单号
	 * @return
	 */
	public String getOrderSn(){
		   Map<String,Object> paramMap = new HashMap<String,Object>();
		   paramMap.put("len", 6);
		   String ordersn =(String) opensqldao.selectForObjectByMap(paramMap, "t_order.select_order_sn_call");
		   if("-1".equals(ordersn)){
			  return getOrderSn();
		   }else{
			   return ordersn;
		   }
	}
//	public Map<String, Object> orderPayChangePrice(TOrder order,String paymentWayId) throws Exception {
//		BigDecimal freight = new BigDecimal("0");//运费
//		BigDecimal amountPayable =  new BigDecimal("0");//应付金额
//		BigDecimal price = new BigDecimal("0"); //商品金额
//			boolean feeflag = false;
//			if(order.getCouponId()!=null){//判断是否使用免运费优惠券
//				TCouponCard couponCard = tcouponcarddao.selectByPrimaryKey(order.getCouponId());
//				if(couponCard!=null&&couponCard.getTicketId()!=null){
//					TCoupon tcoupon =tcoupondao.selectByPrimaryKey(couponCard.getTicketId());
//					if(tcoupon!=null&&tcoupon.getScope()!=null&&tcoupon.getScope().intValue()==1){//免运费
//						feeflag = true;
//					}
//				}
//			}
//			BigDecimal weight = new BigDecimal("0");
//			Map<String,Object> paramMap = new HashMap<String,Object>(); 
//		    paramMap.put("orderId", order.getId());
//		    List orderPirceWeightlist =opensqldao.selectForListByMap(paramMap, "t_order.selectGoodsPriceWeightByorderID");
//		    if(orderPirceWeightlist!=null&&orderPirceWeightlist.size()>0){
//		    	Map<String,Object> resultMap = (Map<String, Object>) orderPirceWeightlist.get(0);
//		    	if(resultMap.get("pricesum")!=null){
//		    		price =(BigDecimal) resultMap.get("pricesum");
//		    	}
//		    	if(resultMap.get("weightsum")!=null){
//		    		weight=(BigDecimal) resultMap.get("weightsum");
//		    	}
//		    }
//			
//		 String ysdjFlag ="0";// 0否   1 是
//       	 CDeliveryWay deliveryWay = new CDeliveryWay();
//       	 if(order.getDeliveryId()!=null){//配送方式id
//       		 deliveryWay= this.cdeliverywaydao.selectByPrimaryKey(Long.valueOf(order.getDeliveryId()));
//       		 if(deliveryWay.getDeliveryCode().equals("ysdj")){//药师到家
//       			 ysdjFlag ="1";
//       			freight=deliveryWay.calculateFreight(weight);
//       		 }
//       	 }
//       	 if(ysdjFlag.equals("0")){//不是 药师到家配送方式
//       		//计算全场免运费金额
//       		 if(!feeflag){
// 		    	String free_shippingfee_price =tsysparameterdao.getKeys("free_shippingfee_price");
// 		    	if(StringUtils.hasText(free_shippingfee_price)){
// 		    		if(price.compareTo(new BigDecimal(free_shippingfee_price.trim()))>=0){
// 		    			feeflag=true;
// 		    		}
// 		    	}
// 		      }
//        	String deliveryCode="ptkd";//普通快递
//    		 if(String.valueOf(order.getAreaId()).equals("3218")){//判断望京   是望京
//    			 deliveryCode="ysd";//药士达
//    		 }else if(order.getAreaName().indexOf(InfoUtil.getInstance().getInfo("config","area.beijing"))>0){//判断是否是北京   
//			    CPaymentWay paymentWay=	cpaymentwaydao.selectByPrimaryKey(Long.valueOf(paymentWayId.trim()));
// 				if(paymentWay.getPaymentCode().equals("hdfk")){//货到付款
// 					deliveryCode="hdfk";
// 				}
//    		 }
//   	        paramMap.clear();
//			paramMap.put("deliveryCode", deliveryCode);
//		    List<CDeliveryWay> deliveryList =this.opensqldao.selectForListByMap(paramMap, "c_delivery_way.selectDeliveryWayByMap");
//		    if(deliveryList!=null&&deliveryList.size()>0){
//		    	deliveryWay = deliveryList.get(0);
//		    	freight=deliveryWay.calculateFreight(weight);
//		    }
//        	if(feeflag){//是否免运费
//        		freight = new BigDecimal("0");
//        	 }
//       	 }
////		    if(!feeflag){
////		    	String free_shippingfee_price =tsysparameterdao.getKeys("free_shippingfee_price");
////		    	if(StringUtils.hasText(free_shippingfee_price)){
////		    		if(price.compareTo(new BigDecimal(free_shippingfee_price.trim()))>=0){
////		    			feeflag=true;
////		    		}
////		    	}
////		    }
////		    //计算运费
////		    if(!String.valueOf(order.getAreaId()).equals("3218")&&!feeflag){//判断望京
////				CPaymentWay paymentWay=	cpaymentwaydao.selectByPrimaryKey(Long.valueOf(paymentWayId.trim()));
////				String deliveryCode="";
////				if(paymentWay.getPaymentCode().equals("hdfk")){//货到付款
////					deliveryCode="hdfk";
////				}else{
////					deliveryCode="ptkd";
////				}
////				paramMap.clear();
////				paramMap.put("deliveryCode", deliveryCode);
////				
////			    List<CDeliveryWay> deliveryList =opensqldao.selectForListByMap(paramMap, "c_delivery_way.selectDeliveryWayByMap");
////			    if(deliveryList!=null&&deliveryList.size()>0){
////			    	CDeliveryWay deliveryWay = deliveryList.get(0);
////			    	freight=deliveryWay.calculateFreight(weight);
////			    }
////		   }
//		 amountPayable = order.getPayableAmount();//订单应付金额
//		 amountPayable =amountPayable.subtract(order.getShippingFee()).add(freight);
//		 Map<String, Object> resultMap = new HashMap<String,Object>();
//		 resultMap.put("freight", freight);
//		 resultMap.put("amountPayable", amountPayable);
//		 resultMap.put("price", price);
//		 resultMap.put("deliveryWay", deliveryWay);
//		 return resultMap;
//	}
	
	public void updateOrderPayment(TOrder order, long paymentmethodId, String paymentPluginId) throws Exception{
		try{
//			 TOrder updateOrder = new TOrder();  
//			 updateOrder.setId(order.getId());
//			 updateOrder.setPaymentId(paymentmethodId);
//		    BigDecimal freight = new BigDecimal("0");
//		    BigDecimal weight = new BigDecimal("0");
//		    BigDecimal price = new BigDecimal("0"); //商品金额		    
//		    boolean feeflag = false;
//			if(order.getCouponId()!=null){//判断是否使用免运费优惠券
//				TCouponCard couponCard = tcouponcarddao.selectByPrimaryKey(order.getCouponId());
//				if(couponCard!=null&&couponCard.getTicketId()!=null){
//					TCoupon tcoupon =tcoupondao.selectByPrimaryKey(couponCard.getTicketId());
//					if(tcoupon!=null&&tcoupon.getScope()!=null&&tcoupon.getScope().intValue()==1){//免运费
//						feeflag = true;
//					}
//				}
//			}
//			Map<String,Object> paramMap = new HashMap<String,Object>(); 
//		    paramMap.put("orderId", order.getId());
//		    List orderPirceWeightlist =opensqldao.selectForListByMap(paramMap, "t_order.selectGoodsPriceWeightByorderID");
//		    if(orderPirceWeightlist!=null&&orderPirceWeightlist.size()>0){
//		    	Map<String,Object> resultMap = (Map<String, Object>) orderPirceWeightlist.get(0);
//		    	if(resultMap.get("pricesum")!=null){
//		    		price =(BigDecimal) resultMap.get("pricesum");
//		    	}
//		    	if(resultMap.get("weightsum")!=null){
//		    		weight=(BigDecimal) resultMap.get("weightsum");
//		    	}
//		    }
//		    CPaymentWay paymentWay=	cpaymentwaydao.selectByPrimaryKey(paymentmethodId);
//		    String ysdjFlag ="0";// 0否   1 是
//	       	 CDeliveryWay deliveryWay = new CDeliveryWay();
//	       	 if(order.getDeliveryId()!=null){//配送方式id
//	       		 deliveryWay= this.cdeliverywaydao.selectByPrimaryKey(Long.valueOf(order.getDeliveryId()));
//	       		 if(deliveryWay.getDeliveryCode().equals("ysdj")){//药师到家
//	       			 ysdjFlag ="1";
//	       			freight=deliveryWay.calculateFreight(weight);
//	       		 }
//	       	 }
//	       	 if(ysdjFlag.equals("0")){//不是 药师到家配送方式
//	       		//计算全场免运费金额
//	       		 if(!feeflag){
//	 		    	String free_shippingfee_price =tsysparameterdao.getKeys("free_shippingfee_price");
//	 		    	if(StringUtils.hasText(free_shippingfee_price)){
//	 		    		if(price.compareTo(new BigDecimal(free_shippingfee_price.trim()))>=0){
//	 		    			feeflag=true;
//	 		    		}
//	 		    	}
//	 		      }
//	        	String deliveryCode="ptkd";//普通快递
//	    		 if(String.valueOf(order.getAreaId()).equals("3218")){//判断望京   是望京
//	    			 deliveryCode="ysd";//药士达
//	    		 }else if(order.getAreaName().indexOf(InfoUtil.getInstance().getInfo("config","area.beijing"))!=-1){//判断是否是北京   
//	 				if(paymentWay.getPaymentCode().equals("hdfk")){//货到付款
//	 					deliveryCode="hdfk";
//	 				}
//	    		 }
//	   	        paramMap.clear();
//				paramMap.put("deliveryCode", deliveryCode);
//			    List<CDeliveryWay> deliveryList =this.opensqldao.selectForListByMap(paramMap, "c_delivery_way.selectDeliveryWayByMap");
//			    if(deliveryList!=null&&deliveryList.size()>0){
//			    	deliveryWay = deliveryList.get(0);
//			    	freight=deliveryWay.calculateFreight(weight);
//			    }
//	        	if(feeflag){//是否免运费
//	        		freight = new BigDecimal("0");
//	        	 }
//	       	 }
//	       	updateOrder.setDeliveryId(deliveryWay.getId());
//		    
////		    if(!feeflag){
////		    	String free_shippingfee_price =tsysparameterdao.getKeys("free_shippingfee_price");
////		    	if(StringUtils.hasText(free_shippingfee_price)){
////		    		if(price.compareTo(new BigDecimal(free_shippingfee_price.trim()))>=0){
////		    			feeflag=true;
////		    		}
////		    	}
////		    }
////		    //计算运费
////		    CPaymentWay paymentWay= null;
////		    paymentWay=	cpaymentwaydao.selectByPrimaryKey(paymentmethodId);
////		    if(!String.valueOf(order.getAreaId()).equals("3218")&&!feeflag){//判断望京
////				String deliveryCode="";
////				if(paymentWay.getPaymentCode().equals("hdfk")){//货到付款
////					deliveryCode="hdfk";
////					updateOrder.setOrderStatus(1);//待发货
////				}else{
////					deliveryCode="ptkd";
////				}
////				paramMap.clear();
////				paramMap.put("deliveryCode", deliveryCode);
////				
////			    List<CDeliveryWay> deliveryList =opensqldao.selectForListByMap(paramMap, "c_delivery_way.selectDeliveryWayByMap");
////			    if(deliveryList!=null&&deliveryList.size()>0){
////			    	CDeliveryWay deliveryWay = deliveryList.get(0);
////			    	updateOrder.setDeliveryId(deliveryWay.getId());
////			    	freight=deliveryWay.calculateFreight(weight);
////			    }
////		   }
//		    if(paymentWay.getPaymentCode().equals("hdfk")){//货到付款
//				updateOrder.setOrderStatus(1);//待发货
//			}
//			 updateOrder.setPayableAmount(order.getPayableAmount().subtract(order.getShippingFee()).add(freight));
//			 updateOrder.setOrderAmount(order.getPayableAmount().subtract(order.getShippingFee()).add(freight));
//			 updateOrder.setShippingFee(freight);
//			 this.torderdao.updateByPrimaryKeySelective(updateOrder); 
//			 if(!paymentWay.getPaymentCode().equals("hdfk")){//货到付款 线下支付
				    TOrderFlowExample flowExample = new TOrderFlowExample();
					flowExample.createCriteria().andMemberIdEqualTo(order.getMemberId()).andOrderIdEqualTo(order.getId());
					List<TOrderFlow> orderFlowList = this.torderflowdao.selectByExample(flowExample);
					TOrderFlow orderFlow= null;
					String paymentPluginName="";
					if("alipayWapPlugin".equals(paymentPluginId)){//支付宝wap支付
					  paymentPluginName="WAP支付宝支付";
					}else if("payWapYktPlugin".equals(paymentPluginId)){//wap医卡通支付
					  paymentPluginName="WAP医卡通支付";
					}else if("wzfWapPlugin".equals(paymentPluginId)){//wap微信支付
					  paymentPluginName="WAP微信支付";
					}
					if(orderFlowList!=null&&orderFlowList.size()>0){
						orderFlow = orderFlowList.get(0);
						orderFlow.setPaymentPlugin(paymentPluginId);
						orderFlow.setPayMethod(paymentPluginName);
						orderFlow.setHavePay(order.getPayableAmount());
						orderFlow.setOrderId(order.getId());
						this.torderflowdao.updateByPrimaryKeySelective(orderFlow);
					}else{
						orderFlow =new TOrderFlow();
						orderFlow.setMemberId(order.getMemberId());
						orderFlow.setPaymentStatus(0);//支付状态 0 未支付 1已支付
						orderFlow.setPaymentPlugin(paymentPluginId);
						orderFlow.setPayMethod(paymentPluginName);
						orderFlow.setHavePay(order.getPayableAmount());
						orderFlow.setCreateTime(new Date());
						orderFlow.setOrderId(order.getId());
						this.torderflowdao.insertSelective(orderFlow);
					}
//			 }
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	@Override
	public void cancelOrder(long orderId,Integer orderType,long memberId) throws Exception {
		
		TOrder orderold = this.torderdao.selectByPrimaryKey(orderId);
		
		TOrder order = new TOrder();
		order.setId(orderId);
		order.setOrderStatus(OrderEnum.CANCEL.getIndex());
		this.torderdao.updateByPrimaryKeySelective(order);
		
		TOrderItemExample orderItemExample = new TOrderItemExample();
		orderItemExample.createCriteria().andOrderIdEqualTo(orderId);
		List<TOrderItem> orderItemList =  this.torderitemdao.selectByExample(orderItemExample);
		if(orderItemList!=null&&orderItemList.size()>0){
			 Map<String, Object> paramMap = new HashMap<String, Object>();
			for(TOrderItem orderItem:orderItemList){
				paramMap.clear();
				paramMap.put("id", orderItem.getGoodsId());
				this.opensqldao.selectForObjectByMap(paramMap, "t_goods.selectByPrimaryKeyForUpdate");
				paramMap.clear();
				paramMap.put("id", orderItem.getGoodsId());
				paramMap.put("quantity", orderItem.getQuantity());
				this.opensqldao.updateByMap_drug(paramMap, "t_goods.updateGoodAddStockById");
			}
			
		}
		if(orderType!=null&&orderType.intValue()==2){//秒杀
			
			TShortOrderExample shortOrderExample = new TShortOrderExample();
			shortOrderExample.createCriteria().andOrderIdEqualTo(orderId).andMemberIdEqualTo(memberId);
			List<TShortOrder> shortOrderList =this.tshortorderdao.selectByExample(shortOrderExample);
			if(shortOrderList!=null&&shortOrderList.size()>0){
				TShortOrder shortOrder = shortOrderList.get(0);
				shortOrder.setStatus(1);
				shortOrder.setOrderId(null);
				this.tshortorderdao.updateByPrimaryKey(shortOrder);
			}
			
		}
       //退换货
		TReturn tReturn = new TReturn();
		if(orderold.getOrderStatus().intValue()==0||orderold.getOrderStatus().intValue()==1){
			if(orderold.getOrderStatus().intValue()==1){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("len", 6);
				String returnOrderSn = (String) opensqldao.selectForObjectByMap(paramMap,
						"t_return.select_new_return_ordersn");
				while (returnOrderSn == null || "".equals(returnOrderSn) || "-1".equals(returnOrderSn)) {
					returnOrderSn = (String) opensqldao.selectForObjectByMap(paramMap, "t_return.select_new_return_ordersn");
				}
				tReturn.setOrderSn(returnOrderSn);
				tReturn.setServiceType(0);
				tReturn.setShipperPhone(orderold.getPhone());
				tReturn.setMemberId(orderold.getMemberId());
				tReturn.setRefundDescribe(orderold.getOrderSn()+"取消订单");
				tReturn.setOrderStatus(0);
				tReturn.setCreateTime(new Date());
				tReturn.setOldOrderId(orderold.getId());
				CPaymentWay paymentWay =cpaymentwaydao.selectByPrimaryKey(orderold.getPaymentId());
				if(paymentWay.getPaymentWay().intValue()==0){//线上
					tReturn.setRefundAmount(orderold.getPayableAmount());
				}else if(paymentWay.getPaymentWay().intValue()==1){//线下
					tReturn.setRefundAmount(new BigDecimal("0"));
				}
				Long returnId=this.treturndao.insertSelective(tReturn);
				TReturnItem returnItem = null;
				for(TOrderItem orderItem:orderItemList){
					returnItem = new TReturnItem();
					returnItem.setCreateTime(new Date());
					returnItem.setGoodsId(orderItem.getGoodsId());
					returnItem.setProductAmount(orderItem.getPrice());
					returnItem.setQuantity(orderItem.getQuantity());
					returnItem.setReturnId(returnId);
					this.treturnitemdao.insertSelective(returnItem);
				}
			}
		}else{
			throw new Exception("取消订单失败订单状态未在可取消范围内");
		}
		
	}
	
	/**
	 * 完成订单
	 */
	public void complete(TOrder order,String publicServiceUrl) throws Exception{
		
		TOrder dbOrder = torderdao.selectByPrimaryKey(order.getId());
		TMemberIntegral tmemberintegral = new TMemberIntegral();
		tmemberintegral.setCreateDate(new Date());
		tmemberintegral.setIntegral(dbOrder.getRewardIntegration());
		tmemberintegral.setMemberId(dbOrder.getMemberId());
		tmemberintegral.setSource(9);
		tmemberintegral.setRemark("订单确认送积分:"+order.getOrderSn());
		tmemberintegraldao.insertSelective(tmemberintegral);
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", dbOrder.getMemberId().toString());
		map.put("jifen", dbOrder.getRewardIntegration().toString());
		map.put("version", "v7");
		topicMessageSender.sendMapMessage(map);
		order.setFinishDate(new Date());
		this.updateByPrimaryKeySelective(order);
		if (dbOrder.getPaymentId() != null && dbOrder.getPaymentId() > 0L) {
			CPaymentWay _cPaymentWay = cpaymentwaydao.selectByPrimaryKey(dbOrder.getPaymentId());
			if (_cPaymentWay != null && _cPaymentWay.getPaymentCode() != null && dbOrder.getMemberId() != null) {
				Map<String,String> publicMap = new HashMap<String,String>();
				publicMap.put("orderId",String.valueOf(order.getId()));
				String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"leaderStayMoneyURLService");
				if(JSONObject.fromObject(resultJsonstr).get("statsCode").equals("0")){
					throw new Exception(String.valueOf(order.getId())+":佣金计算失败,失败原因:"+JSONObject.fromObject(resultJsonstr).get("message"));
				}
			}
		}
		
	}
	
//	/**
//	 * 校验指定的优惠券是否可以使用
//	 * @param couponCardId
//	 * @param memberId
//	 * @return
//	 * @throws Exception
//	 */
//	public boolean checkCouponCard(String couponCardId,String memberId,List<Map<String, Object>> memberCouponList) throws Exception{
//		boolean flag = false;
//	    try{
//	     if(StringUtils.hasText(couponCardId)&&StringUtils.hasText(memberId)){
//			 List<Map<String, Object>> memberCouponUserList =getCouponByGoods(memberCouponList,String.valueOf(memberId));
//			 for(Map<String, Object> couponMap : memberCouponUserList){
//				 if(couponMap.get("id")!=null&&Long.parseLong(couponCardId.trim())==((Long)couponMap.get("id")).longValue()){
//					 flag = true;
//				 }
//			 }
//	     }
//	    }catch(Exception e){
//	    	e.printStackTrace();
//	    }
//	    return flag;
//	}
	
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}
	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}
	public TPromotionDAO getTpromotiondao() {
		return tpromotiondao;
	}
	public void setTpromotiondao(TPromotionDAO tpromotiondao) {
		this.tpromotiondao = tpromotiondao;
	}
	public TMemberReceiverDAO getTmemberreceiverdao() {
		return tmemberreceiverdao;
	}
	public void setTmemberreceiverdao(TMemberReceiverDAO tmemberreceiverdao) {
		this.tmemberreceiverdao = tmemberreceiverdao;
	}
	public CPaymentWayDAO getCpaymentwaydao() {
		return cpaymentwaydao;
	}
	public void setCpaymentwaydao(CPaymentWayDAO cpaymentwaydao) {
		this.cpaymentwaydao = cpaymentwaydao;
	}
	public TOrderItemDAO getTorderitemdao() {
		return torderitemdao;
	}
	public void setTorderitemdao(TOrderItemDAO torderitemdao) {
		this.torderitemdao = torderitemdao;
	}
	public TCartDAO getTcartdao() {
		return tcartdao;
	}
	public void setTcartdao(TCartDAO tcartdao) {
		this.tcartdao = tcartdao;
	}
	public TCartItemDAO getTcartitemdao() {
		return tcartitemdao;
	}
	public void setTcartitemdao(TCartItemDAO tcartitemdao) {
		this.tcartitemdao = tcartitemdao;
	}
	public TMemberBalanceDAO getTmemberbalancedao() {
		return tmemberbalancedao;
	}
	public void setTmemberbalancedao(TMemberBalanceDAO tmemberbalancedao) {
		this.tmemberbalancedao = tmemberbalancedao;
	}
	public TOrderFlowDAO getTorderflowdao() {
		return torderflowdao;
	}
	public void setTorderflowdao(TOrderFlowDAO torderflowdao) {
		this.torderflowdao = torderflowdao;
	}
	public IDaoManager getCartdao() {
		return cartdao;
	}
	public void setCartdao(IDaoManager cartdao) {
		this.cartdao = cartdao;
	}
	public TShortOrderDAO getTshortorderdao() {
		return tshortorderdao;
	}
	public void setTshortorderdao(TShortOrderDAO tshortorderdao) {
		this.tshortorderdao = tshortorderdao;
	}
	public TReturnDAO getTreturndao() {
		return treturndao;
	}
	public void setTreturndao(TReturnDAO treturndao) {
		this.treturndao = treturndao;
	}
	public TReturnItemDAO getTreturnitemdao() {
		return treturnitemdao;
	}
	public void setTreturnitemdao(TReturnItemDAO treturnitemdao) {
		this.treturnitemdao = treturnitemdao;
	}
	public TGoodsDAO getTgoodsdao() {
		return tgoodsdao;
	}
	public void setTgoodsdao(TGoodsDAO tgoodsdao) {
		this.tgoodsdao = tgoodsdao;
	}
	public TCouponCardDAO getTcouponcarddao() {
		return tcouponcarddao;
	}
	public void setTcouponcarddao(TCouponCardDAO tcouponcarddao) {
		this.tcouponcarddao = tcouponcarddao;
	}
	public TSysParameterDAO getTsysparameterdao() {
		return tsysparameterdao;
	}
	public void setTsysparameterdao(TSysParameterDAO tsysparameterdao) {
		this.tsysparameterdao = tsysparameterdao;
	}
	public TCouponDAO getTcoupondao() {
		return tcoupondao;
	}
	public void setTcoupondao(TCouponDAO tcoupondao) {
		this.tcoupondao = tcoupondao;
	}
	public TGoodsBrokerageDAO getTgoodsbrokeragedao() {
		return tgoodsbrokeragedao;
	}
	public void setTgoodsbrokeragedao(TGoodsBrokerageDAO tgoodsbrokeragedao) {
		this.tgoodsbrokeragedao = tgoodsbrokeragedao;
	}

	public CDeliveryWayDAO getCdeliverywaydao() {
		return cdeliverywaydao;
	}
	public void setCdeliverywaydao(CDeliveryWayDAO cdeliverywaydao) {
		this.cdeliverywaydao = cdeliverywaydao;
	}
	public MessageSender getTopicMessageSender() {
		return topicMessageSender;
	}

	public void setTopicMessageSender(MessageSender topicMessageSender) {
		this.topicMessageSender = topicMessageSender;
	}
	
}
