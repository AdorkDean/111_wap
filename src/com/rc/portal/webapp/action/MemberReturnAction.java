package com.rc.portal.webapp.action;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork.ActionContext;
import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.InfoUtil;
import com.rc.portal.service.CPaymentWayManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TOrderManager;
import com.rc.portal.service.TReturnItemManager;
import com.rc.portal.service.TReturnManager;
import com.rc.portal.vo.CPaymentWay;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TOrder;
import com.rc.portal.vo.TReturn;
import com.rc.portal.vo.TReturn.ReturnStatus;
import com.rc.portal.vo.TReturnImage;
import com.rc.portal.vo.TReturnItem;
import com.rc.portal.webapp.util.PageResult;
/**
 * 售后服务
 * @author 刘天灵
 *
 */
public class MemberReturnAction extends BaseAction{

	private static final long serialVersionUID = 34242342423421L;

	private TReturnManager treturnmanager;
	
	private TOrderManager tordermanager;
	
	private CPaymentWayManager cpaymentwaymanager;
	
	private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult(); 
	
	private static int PAGE_SIZE = 10;
	
	private TReturnItemManager treturnitemmanager;
	
	private OpenSqlManage opensqlmanage;
	
	public Object getModel() {
		return null;
	}
	
	public void setModel(Object o) {}

	public TReturnItemManager getTreturnitemmanager() {
		return treturnitemmanager;
	}

	public void setTreturnitemmanager(TReturnItemManager treturnitemmanager) {
		this.treturnitemmanager = treturnitemmanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
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
	
	public TOrderManager getTordermanager() {
		return tordermanager;
	}

	public void setTordermanager(TOrderManager tordermanager) {
		this.tordermanager = tordermanager;
	}

	public TReturnManager getTreturnmanager() {
		return treturnmanager;
	}

	public void setTreturnmanager(TReturnManager treturnmanager) {
		this.treturnmanager = treturnmanager;
	}

	public CPaymentWayManager getCpaymentwaymanager() {
		return cpaymentwaymanager;
	}

	public void setCpaymentwaymanager(CPaymentWayManager cpaymentwaymanager) {
		this.cpaymentwaymanager = cpaymentwaymanager;
	}

	/**
	 * 申请售后商品列表
	 * @return
	 */
	public String list(){
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map<String,Object> param = new HashMap<String,Object>();
		
		param.put("memberId", member.getId());
		
		pw = opensqlmanage.selectForPageByMap(param, "t_return_item.selectMemberServiceCount", "t_return_item.selectMemberServicePage", rs.getP_curPage(), PAGE_SIZE);
		
		return "list";
	}
	
	
	/**
	 * 申请售后异步加载
	 */
	public String more(){
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map<String,Object> param = new HashMap<String,Object>();
		
		param.put("memberId", member.getId());
		
		pw = opensqlmanage.selectForPageByMap(param, "t_return_item.selectMemberServiceCount", "t_return_item.selectMemberServicePage", rs.getP_curPage(), PAGE_SIZE);
		
		return "more";
	}
	
	/**
	 * 订单退货
	 */
	public String index() throws Exception{
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		
		String id = this.getRequest().getParameter("id");
		
		if(org.apache.commons.lang.StringUtils.isNotEmpty(this.getRequest().getParameter("flag")) && (this.getRequest().getParameter("flag").equals("true")||this.getRequest().getParameter("flag").equals("false"))){
			this.getRequest().setAttribute("flag", new Boolean(this.getRequest().getParameter("flag")));
		}
		
		if(org.apache.commons.lang.StringUtils.isNotEmpty(id)){
			
			TOrder order = this.tordermanager.selectByPrimaryKey(Long.parseLong(id));
			
			this.getRequest().setAttribute("order", order);
			
			if(order!=null){
				
				this.getRequest().setAttribute("id", id);
				//已退货明细
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("orderId", id);
				param.put("memberId", member.getId());
				
				Object retrunItems = this.opensqlmanage.selectListByObject(param, "t_return_item.selectHistoryReturnsByOrderId");
				this.getRequest().setAttribute("retrunItems", retrunItems);
				
				//订单明细
				Object orderItems = this.opensqlmanage.selectListByObject(id, "t_order_item.selectOrderItemByOrderId");
				this.getRequest().setAttribute("orderItems", orderItems);
			}
		}
		
		return "index";
	}
	
	private File[] images;
	
	private String [] imagesFileName;
	
	private String [] imagesContentType;
	
	public File[] getImages() {
		return images;
	}

	public void setImages(File[] images) {
		this.images = images;
	}

	public String[] getImagesFileName() {
		return imagesFileName;
	}

	public void setImagesFileName(String[] imagesFileName) {
		this.imagesFileName = imagesFileName;
	}

	public String[] getImagesContentType() {
		return imagesContentType;
	}

	public void setImagesContentType(String[] imagesContentType) {
		this.imagesContentType = imagesContentType;
	}

	/**
	 * 商品退货
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String goods() throws Exception{
		
		TMember member = (TMember)this.getSession().getAttribute("member");		
		String id = this.getRequest().getParameter("id");
		String type = this.getRequest().getParameter("type");
		String problem = this.getRequest().getParameter("problem");
		
		
		ActionContext.getContext().put("id", id);
		
		if(org.apache.commons.lang.StringUtils.isNotEmpty(id) && org.apache.commons.lang.StringUtils.isNotEmpty(type)){
			
			TOrder order = this.tordermanager.selectByPrimaryKey(Long.parseLong(id));
			
			this.getRequest().setAttribute("order", order);
			
			if(order!=null){
				
				this.getRequest().setAttribute("id", id);
				
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("orderId", id);
				param.put("memberId", member.getId());
				//已退货明细
				List<Map> retrunItems = (List<Map>)this.opensqlmanage.selectListByObject(param, "t_return_item.selectHistoryReturnsByOrderId");
				this.getRequest().setAttribute("retrunItems", retrunItems);
				
				//订单明细
				List<Map> orderItems = (List<Map>)this.opensqlmanage.selectListByObject(id, "t_order_item.selectOrderItemByOrderId");
				
				this.getRequest().setAttribute("orderItems", orderItems);

				//订单中所有的商品
				for(Map orderMap :orderItems){
					
					long goodsId = (Long)orderMap.get("goods_id");
					int quantity = (Integer)orderMap.get("quantity");
					
					String returnCount = this.getRequest().getParameter(orderMap.get("goods_id").toString());
					//需要退货的商品
					System.out.println("--------------"+goodsId+"需要退货的商品数量:" + returnCount);
					if(returnCount!=null && Integer.parseInt(returnCount) > 0){
						
						int yetQuantity = 0;
						//获取已退货商品数量
						for(Map returnMap:retrunItems){
							long yetGoodsId = (Long)returnMap.get("goods_id");
							if(goodsId == yetGoodsId){
								yetQuantity += new Integer(returnMap.get("quantity").toString());
							}
						}
						//处理退货
						if(Integer.parseInt(returnCount) <= (quantity-yetQuantity)){
							
							TReturn tReturn = new TReturn();
							tReturn.setCreateTime(new Date());
							tReturn.setMemberId(member.getId());
							tReturn.setOldOrderId(Long.valueOf(id));
							tReturn.setRefundDescribe(problem);
							tReturn.setServiceType(Integer.valueOf(type));
							
							
							Map<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("len", 6);
							String returnOrderSn = (String) opensqlmanage.selectForObjectByMap(paramMap,
									"t_return.select_new_return_ordersn");
							while (returnOrderSn == null || "".equals(returnOrderSn) || "-1".equals(returnOrderSn)) {
								returnOrderSn = (String) opensqlmanage.selectForObjectByMap(paramMap, "t_return.select_new_return_ordersn");
							}
							
							tReturn.setOrderSn(returnOrderSn);
							tReturn.setOrderStatus(ReturnStatus.untreated.ordinal());

							tReturn.setShipperPhone(member.getPhone());

							// 此处金额算不出该退多少(考虑到优惠价,优惠券等),后台退款节点的时候手动填
							// tReturn.setRefundAmount(tOrder.getPayableAmount());

							TReturnItem tReturnItem = new TReturnItem();
							tReturnItem = new TReturnItem();
							tReturnItem.setCreateTime(new Date());
							tReturnItem.setGoodsId(Long.valueOf(goodsId));
							tReturnItem.setProductAmount(new BigDecimal(orderMap.get("price").toString()));
							tReturnItem.setQuantity(Integer.parseInt(returnCount));							
							
							
							List<TReturnImage> returnImages = new ArrayList<TReturnImage>(); 
							
							if(images != null){
								TReturnImage tReturnImage = null;
								String uploadImage = "";
								for (int i = 0; i < images.length; i++) {
									
									uploadImage = uploadImage(images[i],imagesFileName[i]);
									
									tReturnImage = new TReturnImage();
									
									tReturnImage.setImageUrl(uploadImage);
									
									returnImages.add(tReturnImage);
								}
							}
							
							treturnmanager.insert(tReturn, tReturnItem, returnImages);
							
						}
					}
				}
			}
			ActionContext.getContext().put("flag", true);
//			this.writeObjectToResponse(new Message(true,"退货或者退款成功"), ContentType.application_json);
		}else{
			ActionContext.getContext().put("flag", false);
//			this.writeObjectToResponse(new Message(false,"异常操作！"), ContentType.application_json);
		}
		
		return "redirect_index";
//		return "returnList";
	}
	
	private String diskPath = InfoUtil.getInstance().getInfo("img", "images.public.head.path");
	
	/**
	 * 上传文件
	 * 
	 * @throws IOException
	 */
	private String uploadImage(File file ,String filename) throws IOException {
		
		String houzui = org.apache.commons.io.FilenameUtils.getExtension(filename);
		try {
			String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");	
			if(checkFileType(houzui) && file.length() < 4*1024*1024){
				String webPath = diskPath+"/"+UUID.randomUUID().toString().replaceAll("-", "")+"."+houzui;
				String fullName = basePath + webPath;
				
				File uploadFile = new File(fullName);
				FileUtils.copyFile(file, uploadFile);
				return webPath;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * 售后详细
	 */
	public String detail() throws Exception{
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		
		String id = this.getRequest().getParameter("id");
		
		if(org.apache.commons.lang.StringUtils.isNotEmpty(id)){
			
			//已退货明细
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", id);
			param.put("memberId", member.getId());
			
			Map returnItemMap = (Map)opensqlmanage.selectObjectByObject(param, "t_return_item.selectMemberReturnItemById");
			this.getRequest().setAttribute("returnItemMap", returnItemMap);
			
			if(returnItemMap!=null){
				
				TOrder order = this.tordermanager.selectByPrimaryKey(Long.parseLong(returnItemMap.get("old_order_id").toString()));
				
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
		}
		
		return "detail";
	}
	
	/**
	 * 专项添加物流信息 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String logistics() throws Exception{
		this.getRequest().setAttribute("id", this.getRequest().getParameter("id"));
		
		this.getRequest().setAttribute("return", treturnmanager.selectByPrimaryKey(Long.parseLong(this.getRequest().getParameter("id"))));
		
		return "logistics";
	}
	
	private TReturn treturn = new TReturn();
	
	public TReturn getTreturn() {
		return treturn;
	}

	public void setTreturn(TReturn treturn) {
		this.treturn = treturn;
	}

	/**
	 * 更新物流信息
	 */
	public void update() throws SQLException{
		
		if(treturn.getId() > 0 && org.apache.commons.lang.StringUtils.isNotEmpty(treturn.getExpressCompany()) && org.apache.commons.lang.StringUtils.isNotEmpty(treturn.getExpressDelivery())){
			treturnmanager.updateByPrimaryKeySelective(treturn);
			this.writeObjectToResponse(new Message(true,"保存成功！"), ContentType.application_json);
		}else{
			this.writeObjectToResponse(new Message(false,"异常操作！"), ContentType.application_json);
		}
		
	}
	
	/**
	 * 检查文件类型
	 * @param type
	 * @return
	 */
	public boolean checkFileType(String type){
		boolean flag=false;
		type = type.toLowerCase();
		String[] arrType={"jpg","png","gif","jpeg"};
		for(String s:arrType){
			if(type.equals(s)){
				return true;
			}
		}
		return flag;
	}
	
	public class Message {
		
		private boolean type;
		
		private String content;
		
		public Message() {
			super();
		}
		
		public Message(boolean type, String content) {
			super();
			this.type = type;
			this.content = content;
		}
		public boolean isType() {
			return type;
		}
		public void setType(boolean type) {
			this.type = type;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
}
