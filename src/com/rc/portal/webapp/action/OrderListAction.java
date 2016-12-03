package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.InfoUtil;
import com.rc.portal.util.JsonUtil;
import com.rc.portal.vo.TMember;
import com.rc.portal.webapp.model.OrderPayment;
import com.rc.portal.webapp.util.PageResult;

public class OrderListAction extends BaseAction {
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private TSysParameterManager tsysparametermanager;
	private OpenSqlManage opensqlmanage;
	private String uri=InfoUtil.getInstance().getInfo("config", "ui1");
	public String getOrderList() throws Exception{
		String homePageConfigId = tsysparametermanager.getKeys("public_service_url");
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map map=new HashMap();
			map.put("member_id", member.getId().toString());
			map.put("memberId", member.getId().toString());
		map.put("pageNumber", "1");
		map.put("pageSize", "20");
		Map result = JsonUtil.getData(map, homePageConfigId+"getOffLineOrderList");
		this.getRequest().setAttribute("result", result);
		pw=opensqlmanage.selectForPageByMap(map, "t_order.select_count", "t_order.select_list", rs.getP_curPage(), rs.getP_pageSize());
		return "getOrderList";
	}
	
	public void appendOrderListByMemberId() throws Exception{
		this.getRequest().setCharacterEncoding("UTF-8");
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out =this.getResponse().getWriter();
		Map result =new HashMap();
		String homePageConfigId = tsysparametermanager.getKeys("public_service_url");
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map map=new HashMap();
			map.put("member_id", member.getId().toString());
			map.put("memberId", member.getId().toString());
		if(model.getType()!=null&&model.getType()!=0){
			if(model.getType()==1){
				rs.setP_pageSize(20);
				pw=opensqlmanage.selectForPageByMap(map, "t_order.select_count", "t_order.select_list", rs.getP_curPage(), rs.getP_pageSize());
			}else{
				if(model.getPageNumber()!=null&&model.getPageNumber()!=0){
					map.put("pageNumber", model.getPageNumber().toString());
				}
				map.put("pageSize", "20");
				result=JsonUtil.getData(map, homePageConfigId+"getOffLineOrderList");
			}
		}
		String content="";
		if(model.getType()!=null&&model.getType()!=0){
			if(model.getType()==1){
				List <OrderPayment> mapList = pw.getResult();
				if(mapList.size()>0){
					for(int i=0;i<mapList.size();i++){
						content+="<a href='"+this.getRequest().getContextPath()+"/member/order!detail.action?id="+mapList.get(i).getId()+"'><div class='tel-data'><div class='order-img'><img src='"+uri+mapList.get(i).getAbbreviationPicture()+"' alt=''/></div>"
								+"<div class='order-details'><dl><dt>订单编号</dt><dd>"+mapList.get(i).getOrderSn()+"</dd> </dl><dl><dt>订单金额</dt><dd>￥"+mapList.get(i).getPayableAmount()+"</dd></dl>"
								+"<dl><dt>订单状态</dt> <dd>";
						Integer status = mapList.get(i).getOrderStatus();
		                 if(status!=null&&status==0){
		                	 content+="待支付";
		                 }else if(status!=null&&status==1){
		                	 content+="待发货";
		                 }else if(status!=null&&status==2){
		                	 content+="待收货";
		                 }else if(status!=null&&status==3){
		                	 content+="已完成";
		                 }else if(status!=null&&status==4){
		                	 content+="已取消";
		                 }else if(status!=null&&status==5){
		                	 content+="已过期";
		                 }
		                content+="</dd></dl></div></div></a>";
					}
			}else{
				content="-1";
			}
		}else{
			List <Map> mapList=(List<Map>) result.get("list");
			if(mapList.size()>0){
				for(int i=0;i<mapList.size();i++){
					content+="<a href='"+this.getRequest().getContextPath()+"/storeOrder/storeOrder!storeOrderInfo.action?orderSn="+mapList.get(i).get("orderSn")+"'><div class='tel-data'><div class='order-details'><dl><dt>订单编号</dt><dd>"+mapList.get(i).get("orderSn")+"</dd></dl><dl><dt>订单金额</dt><dd>¥"+mapList.get(i).get("orderAmount")+"</dd></dl><dl><dt>订单状态</dt>"
							+"<dd>"+mapList.get(i).get("orderStatus")+"</dd></dl></div> </div></a>";
					}
			}else{
				content="-1";
			}
			}
		}
			out.print(content);
			out.close();
	}
	
	public class Condition {
		private Integer type;
		private Integer pageNumber;
		private Integer pageSize;
		
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public Integer getPageNumber() {
			return pageNumber;
		}
		public void setPageNumber(Integer pageNumber) {
			this.pageNumber = pageNumber;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		
	}
	
	@Override
	public Object getModel() {
		return this.model;
	}
	@Override
	public void setModel(Object o) {
		this.model = (Condition) o;
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
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}
	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

}
