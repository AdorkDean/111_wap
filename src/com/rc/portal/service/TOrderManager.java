package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TOrder;
import com.rc.portal.vo.TOrderExample;

public interface TOrderManager {
    int countByExample(TOrderExample example) throws SQLException;

    int deleteByExample(TOrderExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TOrder record) throws SQLException;

    Long insertSelective(TOrder record) throws SQLException;

    List selectByExample(TOrderExample example) throws SQLException;

    TOrder selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TOrder record, TOrderExample example) throws SQLException;

    int updateByExample(TOrder record, TOrderExample example) throws SQLException;

    int updateByPrimaryKeySelective(TOrder record) throws SQLException;

    int updateByPrimaryKey(TOrder record) throws SQLException;
    
    /**
   	 * 根据购物车商品计算优惠券
   	 * 
   	 * @return
   	 * @throws SQLException
   	 */
//       public List<Map<String,Object>> getCouponByGoods(List<Map<String, Object>> memberCouponList,String memberId) throws SQLException;

    /**
     * 计算订单相关金额
     * @param couponCardId 优惠券id
     * @param receiverId   收货地址id
     * @param memberId   用户id
     * @param paymentWayId  支付方式
     * @param deliveryId  配送方式
     * @return
     * @throws SQLException
     */
//	public Map<String, Object> getOrderPrice(
//			String couponCardId, String receiverId,String memberId,String paymentWayId,String deliveryId) throws Exception;
	
	/**
	 * 保存订单  
	 * @param order
	 * @param receiverId
	 * @param payPluginId
	 * @param  deliveryId
	 * @return
	 * @throws Exception
	 */
//	public long saveOrder(TOrder order,String receiverId,String payPluginId,String deliveryId) throws Exception;
	
	/**
	 * 支付方式修改 重新计算运费
	 * @return
	 * @throws Exception
	 */
//	public Map<String,Object> orderPayChangePrice(TOrder order,String paymentWayId) throws Exception;
	
	/**
	 * 更改支付方式
	 * @param order
	 * @param paymentmethodId
	 * @param paymentPluginId
	 * @param request
	 * @throws Exception
	 */
	public void updateOrderPayment(TOrder order, long paymentmethodId, String paymentPluginId) throws Exception;
	
	
	/**
	 * 取消订单
	 * @param orderId
	 * @throws Exception
	 */
	public void cancelOrder(long orderId,Integer orderType,long memberId) throws Exception;
	
	/**
	 * 完成订单
	 */
	public void complete(TOrder order,String publicServiceUrl) throws Exception;
	
	
	/**
	 * 校验指定的优惠券是否可以使用
	 * @param couponCardId
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
//	public boolean checkCouponCard(String couponCardId,String memberId,List<Map<String, Object>> memberCouponList) throws Exception;
	
}
