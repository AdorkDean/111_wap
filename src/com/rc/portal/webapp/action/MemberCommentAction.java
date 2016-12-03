package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsCommentManager;
import com.rc.portal.service.TOrderItemManager;
import com.rc.portal.service.TOrderManager;
import com.rc.portal.vo.TGoodsComment;
import com.rc.portal.vo.TMember;

/**
 * 会员商品评论
 */
public class MemberCommentAction extends BaseAction{

	private static final long serialVersionUID = 32432423421L;

	/**
	 * 订单manager
	 */
	private TOrderManager tordermanager;
	
	
	private OpenSqlManage opensqlmanage;
	
	private TGoodsCommentManager tgoodscommentmanager;

	private TOrderItemManager torderitemmanager;
	
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}
	
	public TOrderItemManager getTorderitemmanager() {
		return torderitemmanager;
	}

	public void setTorderitemmanager(TOrderItemManager torderitemmanager) {
		this.torderitemmanager = torderitemmanager;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}
	
	public TGoodsCommentManager getTgoodscommentmanager() {
		return tgoodscommentmanager;
	}

	public void setTgoodscommentmanager(TGoodsCommentManager tgoodscommentmanager) {
		this.tgoodscommentmanager = tgoodscommentmanager;
	}

	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {}

	public TOrderManager getTordermanager() {
		return tordermanager;
	}

	public void setTordermanager(TOrderManager tordermanager) {
		this.tordermanager = tordermanager;
	}

	/**
	 * 订单微评论商品列表
	 */
	public String index() throws NumberFormatException, SQLException{
		
		String id = this.getRequest().getParameter("id");
		
		if(org.apache.commons.lang.StringUtils.isNotEmpty(id)){
			
			//订单明细
			Object orderItems = this.opensqlmanage.selectListByObject(id, "t_order_item.selectOrderItemNoReviewByOrderId");
			this.getRequest().setAttribute("orderItems", orderItems);
		}
		return "index";
	}
	
	
	private TGoodsComment goodscomment = new TGoodsComment();
	
	
	public TGoodsComment getGoodscomment() {
		return goodscomment;
	}

	public void setGoodscomment(TGoodsComment goodscomment) {
		this.goodscomment = goodscomment;
	}

	/**
	 * 添加商品评论
	 */
	public void add() throws SQLException{
		
		if(goodscomment.getGoods() < 0 || goodscomment.getGoods() > 5 || goodscomment.getSeller() < 0 || goodscomment.getSeller() > 5 ||
				goodscomment.getFastMail() < 0 || goodscomment.getFastMail() > 5 || goodscomment.getFastMailPeople() < 0 || goodscomment.getFastMailPeople() > 5 ||
				goodscomment.getGoodsId().longValue() == 0l || goodscomment.getOrderId().longValue() == 0l
				){
			this.writeObjectToResponse(new Message(false,"异常操作！"), ContentType.application_json);
		}else{
			TMember member = (TMember)this.getSession().getAttribute("member");
			
			goodscomment.setMemberId(member.getId());
			goodscomment.setCreateTime(new Date());
			goodscomment.setIsShow(0);
			
			if(goodscomment.getComment().trim().equals("")){
				goodscomment.setComment("挺好的，绝对正品，而且超快，第二次购买了，下次还来");
			}
			
			goodscomment.setSumFraction(goodscomment.getGoods()+goodscomment.getSeller()+goodscomment.getFastMail()+goodscomment.getFastMailPeople());
			
			if(goodscomment.getSumFraction() < 5){
				goodscomment.setType(3);
			}else if (goodscomment.getSumFraction() < 13) {
				goodscomment.setType(2);
			}else {
				goodscomment.setType(1);
			}
			
			//插入评论
			tgoodscommentmanager.insertSelective(goodscomment);
			//修改订单项是否已评论
			Map<String, Object> param = new HashMap<String,Object>();
			param.put("goodsIs", goodscomment.getGoodsId());
			param.put("orderId", goodscomment.getOrderId());
			
			//商品评论+1
			opensqlmanage.updateByMap_drug(param, "t_goods_property.updateCommentCount");
			
			opensqlmanage.updateByMap_drug(param, "t_order_item.updateByOrderIdAndGoodsId");
			
			this.writeObjectToResponse(new Message(true,"评论添加成功！"), ContentType.application_json);
		}
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
