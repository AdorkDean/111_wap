package com.rc.portal.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.vo.TGoodsExtendWithBLOBs;
import com.rc.portal.vo.TGoodsImages;
import com.rc.portal.webapp.model.GoodsGroupModel2;
import com.rc.portal.webapp.model.GoodsPremiumsModel;
import com.rc.portal.webapp.util.PageResult;

@SuppressWarnings("unchecked")
public class GoodsAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	private TCategoryManager tcategorymanager;
	private TGoodsManager tgoodsmanager;
	//图片集合
	private List<TGoodsImages> listi;
	//赠品集合
	private List<GoodsPremiumsModel> listp;
	//组合商品集合
	private List<GoodsGroupModel2> listg;
	//规格
	private List<Map<Long,String>> lists;
	//规格
	private List<Map<String,String>> specs;
	//商品
	Map<String, Object> goods =new HashMap<String, Object>();
	//商品集合
	private List<Map<String, Object>> goodsArray;
	//商品扩展
	private TGoodsExtendWithBLOBs goodsextend;
	
	//查看商品详情
	public String gd() throws Exception
	{
		String type = getRequest().getParameter("type");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", getRequest().getParameter("id"));
		List<Map<String, Object>> list = tgoodsmanager.selectByPrimaryKey2(map, "2");
		if(list != null && list.size() > 0)
		{
			for(Map<String, Object> tg : list)
			{
				goods = tg;
				goodsextend = (TGoodsExtendWithBLOBs) goods.get("goodse");
				listi = (List<TGoodsImages>) goods.get("listi");
				listg = (List<GoodsGroupModel2>) goods.get("listg");
				lists = (List<Map<Long, String>>) goods.get("lists");
				specs = (List<Map<String, String>>) goods.get("listpc");
			}
		}
		if(type.equals("1"))
		{
			return "rxGoodsdetail";
		}
		if(type.equals("0"))
		{
			return "goodsdetail";
		}
		if(type.equals("2"))
		{
			return "app_goods_detail";
		}
		return null;
	}
	
	//获取商品描述
	public String goodsdesc() throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", getRequest().getParameter("id"));
		List<Map<String, Object>> list = tgoodsmanager.selectByPrimaryKey2(map, "1");
		if(list != null && list.size() > 0)
		{
			for(Map<String, Object> tg : list)
			{
				goods = tg;
				goodsextend = (TGoodsExtendWithBLOBs) goods.get("goodse");
			}
		}
		return "goodsdesc";
	}
	
	//热销商品
	public void hotsalegoods()
	{
		writeObjectToResponse(opensqlmanage.selectForListByMap(null, "t_goods.selectHotsProListMap"), ContentType.application_json);
	}
	
	
	public PageWraper getPw()
	{
		return pw;
	}

	public void setPw(PageWraper pw)
	{
		this.pw = pw;
	}



	public PageResult getRs()
	{
		return rs;
	}



	public void setRs(PageResult rs)
	{
		this.rs = rs;
	}



	public OpenSqlManage getOpensqlmanage()
	{
		return opensqlmanage;
	}



	public void setOpensqlmanage(OpenSqlManage opensqlmanage)
	{
		this.opensqlmanage = opensqlmanage;
	}



	public TCategoryManager getTcategorymanager()
	{
		return tcategorymanager;
	}



	public void setTcategorymanager(TCategoryManager tcategorymanager)
	{
		this.tcategorymanager = tcategorymanager;
	}



	public TGoodsManager getTgoodsmanager()
	{
		return tgoodsmanager;
	}



	public void setTgoodsmanager(TGoodsManager tgoodsmanager)
	{
		this.tgoodsmanager = tgoodsmanager;
	}



	public List<TGoodsImages> getListi()
	{
		return listi;
	}



	public void setListi(List<TGoodsImages> listi)
	{
		this.listi = listi;
	}



	public List<GoodsPremiumsModel> getListp()
	{
		return listp;
	}



	public void setListp(List<GoodsPremiumsModel> listp)
	{
		this.listp = listp;
	}



	public List<GoodsGroupModel2> getListg()
	{
		return listg;
	}



	public void setListg(List<GoodsGroupModel2> listg)
	{
		this.listg = listg;
	}



	public List<Map<Long, String>> getLists()
	{
		return lists;
	}



	public void setLists(List<Map<Long, String>> lists)
	{
		this.lists = lists;
	}



	public List<Map<String, String>> getSpecs()
	{
		return specs;
	}



	public void setSpecs(List<Map<String, String>> specs)
	{
		this.specs = specs;
	}



	public Map<String, Object> getGoods()
	{
		return goods;
	}



	public void setGoods(Map<String, Object> goods)
	{
		this.goods = goods;
	}



	public List<Map<String, Object>> getGoodsArray()
	{
		return goodsArray;
	}



	public void setGoodsArray(List<Map<String, Object>> goodsArray)
	{
		this.goodsArray = goodsArray;
	}



	public TGoodsExtendWithBLOBs getGoodsextend()
	{
		return goodsextend;
	}



	public void setGoodsextend(TGoodsExtendWithBLOBs goodsextend)
	{
		this.goodsextend = goodsextend;
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
}


