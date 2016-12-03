package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.dst.client.util.ClientSubmitPublic;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.THealthyPlanManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.webapp.model.HealthyPlanContentModel;

public class HealthyPlanContentAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	private TSysParameterManager tsysparametermanager;
	private OpenSqlManage opensqlmanage;
	private THealthyPlanManager thealthyplanmanager;
	
	public void getHealthyPlanInfo() throws Exception
	{
		String id = getRequest().getParameter("id");
		Map<String, String> mapv = new HashMap<String, String>();
		mapv.put("id", id);
		String healthyplaninfo = tsysparametermanager.getKeys("healthyplaninfo");
		String josns = ClientSubmitPublic.getHealthyPlanInfo(mapv,healthyplaninfo);
		writeObjectToResponse(josns, ContentType.application_json);
	}
	
	//查看APP健康方案详情
	public String view() throws Exception
	{
		String hid = getRequest().getParameter("hid");
		String dataType = getRequest().getParameter("dataType");
		if(null == dataType)
		{
			dataType = "app";
		}
		Map<String, Object> ction = new HashMap<>();
		ction.put("offSet", 0);
		ction.put("pageSize", 5);
		ction.put("id", hid);
		List<HealthyPlanContentModel> list = thealthyplanmanager.getHealthyPlanContent(ction,dataType);
		HealthyPlanContentModel obj = list.get(0);
		getRequest().setAttribute("obj", obj);
		return "view";
	}
	
	//查看WAP健康方案详情
	public String vieww() throws Exception
	{
		String hid = getRequest().getParameter("hid");
		String dataType = getRequest().getParameter("dataType");
		if(null == dataType)
		{
			dataType = "app";
		}
		Map<String, Object> ction = new HashMap<>();
		ction.put("offSet", 0);
		ction.put("pageSize", 5);
		ction.put("id", hid);
		List<HealthyPlanContentModel> list = thealthyplanmanager.getHealthyPlanContent(ction, dataType);
		HealthyPlanContentModel obj = list.get(0);
		getRequest().setAttribute("obj", obj);
		return "vieww";
	}
	
	//获取健康计划分类名称
	@SuppressWarnings("unchecked")
	public void getPlanCategorys()
	{
		String hid = getRequest().getParameter("hid");
		Map<String, Object> ction = new HashMap<>();
		ction.put("hid", hid);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(ction, "t_healthy_plan.selectCategoryName");
		writeObjectToResponse(datas, ContentType.application_json);
	}
	
	//获取健康计划分类名称
	@SuppressWarnings("unchecked")
	public void getPlanCategorysJSONP() throws IOException
	{
		getResponse().setCharacterEncoding("utf-8");
		getRequest().setCharacterEncoding("utf-8");
		PrintWriter out =  getResponse().getWriter();
		String jsoncallback = getRequest().getParameter("jsoncallback");
		String hid = getRequest().getParameter("hid");
		Map<String, Object> ction = new HashMap<>();
		ction.put("hid", hid);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(ction, "t_healthy_plan.selectCategoryName");
		out.print(jsoncallback+"("+JSONArray.fromObject(datas)+");");
		out.flush();
		out.close();
	}
	
	public TSysParameterManager getTsysparametermanager() 
	{
		return tsysparametermanager;
	}
	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) 
	{
		this.tsysparametermanager = tsysparametermanager;
	}
	@Override
	public Object getModel() 
	{
		return null;
	}

	@Override
	public void setModel(Object o) 
	{
	}
	public OpenSqlManage getOpensqlmanage() 
	{
		return opensqlmanage;
	}
	public void setOpensqlmanage(OpenSqlManage opensqlmanage) 
	{
		this.opensqlmanage = opensqlmanage;
	}

	public THealthyPlanManager getThealthyplanmanager() {
		return thealthyplanmanager;
	}

	public void setThealthyplanmanager(THealthyPlanManager thealthyplanmanager) {
		this.thealthyplanmanager = thealthyplanmanager;
	}
	
}
