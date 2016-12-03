package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.InfoUtil;
import com.rc.portal.util.JsonUtil;
import com.rc.portal.vo.TMember;
import com.rc.portal.webapp.util.PageResult;

public class JifenListAction extends BaseAction {
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private TMemberManager tmembermanager;
	private TSysParameterManager tsysparametermanager;
	private Integer jifen=0;
	private Map result=new HashMap();
	private Map resultOnLine=new HashMap();
	private String uri=InfoUtil.getInstance().getInfo("config", "ui1");
	public String getThirtyJifen() throws Exception{
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map map=new HashMap();
		map.put("memberId", member.getId().toString());
		member=tmembermanager.selectByPrimaryKey(member.getId());
		String homePageConfigId = tsysparametermanager.getKeys("public_service_url");
		result = JsonUtil.getData(map, homePageConfigId+"getJifenOutline");
		if(result.get("statusCode").equals("1")){
			jifen=member.getIntegral()+(Integer)result.get("jifen");
		}else{
			jifen=member.getIntegral();
		}
		result=JsonUtil.getData(map, homePageConfigId+"getThirtyJifen");
		if(!"0".equals(result.get("statusCode"))){
		List<Map> list =(List<Map>)result.get("list");	
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				if(list.get(i).get("source")!=null){
					list.get(i).put("remark", source((Integer)list.get(i).get("source")));
				}
			}
			result.put("list", list);
		}
		}
		return "getThirtyJifen";
	}
	public String getIntegralList() throws Exception{
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map map=new HashMap();
		map.put("memberId", member.getId().toString());
		map.put("pageNo", "1");
		map.put("pageSize", "20");
		String homePageConfigId = tsysparametermanager.getKeys("public_service_url");
		resultOnLine=JsonUtil.getData(map, homePageConfigId+"getIntegralOnLine");//获取线上积分
		result=JsonUtil.getData(map, homePageConfigId+"getIntegralOutLine");//获取线下积分
		if(!"0".equals(resultOnLine.get("statusCode"))){
			Map data = (Map)resultOnLine.get("data");
			List<Map> list =(List<Map>)data.get("result");	
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					if(list.get(i).get("source")!=null){
					list.get(i).put("remark", source((Integer)list.get(i).get("source")));
					}
				}
				data.put("result", list);
				resultOnLine.put("data", data);
			}
			}
		if(!"0".equals(result.get("statusCode"))){
			Map data = (Map)result.get("data");
			List<Map> list =(List<Map>)data.get("list");	
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					if(list.get(i).get("source")!=null){
					list.get(i).put("remark", source((Integer)list.get(i).get("source")));
					}
				}
				data.put("list", list);
				result.put("data", data);
			}
		}
		return "getIntegralList";
	}
	public void appendOrderListByMemberId() throws Exception{
		this.getRequest().setCharacterEncoding("UTF-8");
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out =this.getResponse().getWriter();
		TMember member = (TMember)this.getSession().getAttribute("member");
		Map map=new HashMap();
		map.put("memberId", member.getId().toString());
		map.put("pageSize", "20");
		String homePageConfigId = tsysparametermanager.getKeys("public_service_url");
		if(model.getType()!=null&&model.getType()!=0){
			if(model.getType()==1){
				if(model.getPageNumber()!=null&&model.getPageNumber()!=0){
					map.put("pageNo", model.getPageNumber().toString());
				}
				
				resultOnLine=JsonUtil.getData(map, homePageConfigId+"getIntegralOnLine");//获取线上积分
				if(!"0".equals(resultOnLine.get("statusCode"))){
					Map data = (Map)resultOnLine.get("data");
					List<Map> list =(List<Map>)data.get("result");	
					if(list.size()>0){
						for(int i=0;i<list.size();i++){
							if(list.get(i).get("source")!=null){
							list.get(i).put("remark", source((Integer)list.get(i).get("source")));
							}
						}
						data.put("result", list);
						resultOnLine.put("data", data);
					}
					}
				
			}else{
				if(model.getPageNo()!=null&&model.getPageNo()!=0){
					map.put("pageNo", model.getPageNo().toString());
				}
				result=JsonUtil.getData(map, homePageConfigId+"getIntegralOutLine");//获取线下积分
				if(!"0".equals(result.get("statusCode"))){
					Map data = (Map)result.get("data");
					List<Map> list =(List<Map>)data.get("list");	
					if(list.size()>0){
						for(int i=0;i<list.size();i++){
							if(list.get(i).get("source")!=null){
							list.get(i).put("remark", source((Integer)list.get(i).get("source")));
							}
						}
						data.put("list", list);
						result.put("data", data);
					}
				}
			}
		}
		String content="";
		if(model.getType()!=null&&model.getType()!=0){
			if(model.getType()==1){
				if(!"0".equals(resultOnLine.get("statusCode"))){
					Map m=(Map)resultOnLine.get("data");
					List<Map> onlineList=(List<Map>) m.get("result");
					if(onlineList.size()>0){
					for(int i=0;i<onlineList.size();i++){
						content+="<div class='tel-data'><span>"+onlineList.get(i).get("remark")+"<p>"+onlineList.get(i).get("createDate")+"</p>";
						if((Integer)onlineList.get(i).get("integral")>=0){
							content+="<b>+"+onlineList.get(i).get("integral")+"</b> </div>";
						}else{
							content+="<b class='b-blue'>"+onlineList.get(i).get("integral")+"</b> </div>";
						}
					}
					}else{
						content="-1";
					}
				}else{
					content="-1";
				}
		}else{
			if(!"0".equals(result.get("statusCode"))){
				Map m=(Map)result.get("data");
				List<Map> outList=(List<Map>) m.get("list");
				if(outList.size()>0){
					
				
				for(int i=0;i<outList.size();i++){
					content+="<div class='tel-data'><span>"+outList.get(i).get("remark")+"<p>"+outList.get(i).get("create_date")+"</p>";
					double c=0.00D;
					Integer r=new BigDecimal(outList.get(i).get("integral").toString()).compareTo(new BigDecimal(c));
					if(r>=0){
						content+="<b>+"+outList.get(i).get("integral")+"</b> </div>";
					}else{
						content+="<b class='b-blue'>"+outList.get(i).get("integral")+"</b> </div>";
					}
				}
				}else{
					content="-1";
				}
				
			}else{
				content="-1";
			}
			}
		}
			out.print(content);
			out.close();
	}
	
	
	
	public String source( Integer source){
		String remark="未知赠送";
		if(source==1){
			remark="注册赠送";
		}else if(source==2){
			remark="完善昵称赠送";
		}else if(source==3){
			remark="完善真实姓名赠送";
		}else if(source==4){
			remark="完善性别赠送";
		}else if(source==5){
			remark="完善血型赠送";
		}else if(source==6){
			remark="完善病例赠送";
		}else if(source==7){
			remark="评论赠送";
		}else if(source==8){
			remark="签到赠送";
		}else if(source==9){
			remark="下单购买完成赠送";
		}else if(source==10){
			remark="兑换优惠劵";
		}else if(source==11){
			remark="交易使用";
		}
		return remark;
	}
	public class Condition {
		private Integer type;
		private Integer pageNumber;
		private Integer pageNo;
		
		
		
		
		public Integer getPageNo() {
			return pageNo;
		}
		public void setPageNo(Integer pageNo) {
			this.pageNo = pageNo;
		}
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

	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}

	public Integer getJifen() {
		return jifen;
	}

	public void setJifen(Integer jifen) {
		this.jifen = jifen;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}
	public Map getResultOnLine() {
		return resultOnLine;
	}
	public void setResultOnLine(Map resultOnLine) {
		this.resultOnLine = resultOnLine;
	}
	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}
	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

}
