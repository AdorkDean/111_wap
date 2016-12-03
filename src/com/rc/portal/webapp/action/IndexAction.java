package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberBaseMessageExtManager;
import com.rc.portal.service.TMemberCollectManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberBaseMessageExt;
import com.rc.portal.vo.TMemberCollect;
import com.rc.portal.webapp.util.PageResult;

/**
 * First page method invoke
 * @author LGP
 * @date 2015-9-16
 */
@SuppressWarnings("unchecked")
public class IndexAction extends BaseAction 
{
	private static final long serialVersionUID = 5294821245374431258L;
	private OpenSqlManage opensqlmanage;
	private TMemberCollectManager tmembercollectmanager;
	private TMemberCollect tMemberCollect;
	private TSysParameterManager tsysparametermanager;
	private static int tsize = 0;
	private static int count = 0;
	private TMemberBaseMessageExtManager tmemberbasemessageextmanager;
	
	/**
	 * This is promotional product area
	 */
	public void promotionproduct()
	{
		String pageNo = getRequest().getParameter("pageNo");
		String pageSize = getRequest().getParameter("pageSize");
		Map<String, Object> map = new HashMap<String,Object>();
		String statusCode = null;
		try
		{
			PageWraper pw = new PageWraper();
			PageResult rs = new PageResult();
			String promotion = tsysparametermanager.getKeys("baoyouhuodong");
			map.put("promId", Integer.parseInt(promotion));
			rs.setP_curPage(Integer.parseInt(pageNo)); 
			rs.setP_pageSize(Integer.parseInt(pageSize));
			pw = opensqlmanage.selectForPageByMap(map,"c_position.selectPromotionProductByPageCount","c_position.selectPromotionProductByPage",rs.getP_curPage(), rs.getP_pageSize());
			List<Map<String, Object>> totals = opensqlmanage.selectForListByMap(map, "c_position.selectPromotionProducttotals");
			if(pageNo.trim().equals("1"))
			{
				tsize = Integer.parseInt(String.valueOf(totals.get(0).get("num")));
			}
			List<Map<String, String>> result = pw.getResult();
			map.clear();
			int size = result.size();
			if (size == 0) 
			{
				map.put("moreurl", null);
			} 
			else 
			{
				map.put("moreurl", (Integer.parseInt(pageNo) + 1) + "");
			}
			count += size;
			if(tsize == count)
			{
				System.out.println("没有更多数据了....");
			}
			map.put("list", result);
			statusCode = "1";
		}
		catch (Exception e) 
		{
			statusCode = "0";
			e.printStackTrace();
		}
		map.put("statusCode", statusCode);
		map.put("pageNo", pageNo);
		this.writeObjectToResponse(map, ContentType.application_json);
	}
	
	/**
	 * Attention or not product
	 */
	public void attentionpro() throws NumberFormatException, SQLException, IOException
	{
		String id = this.getRequest().getParameter("id");
		String type = this.getRequest().getParameter("type");
		if(id != null && !id.trim().equals(""))
		{
			TMember tm = (TMember)this.getRequest().getSession().getAttribute("member");
			if(tm != null)
			{
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("relevance_id", id);
				map.put("collect_type", type);
				map.put("member_id", tm.getId());
				List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(map, "t_member_collect.selectTmcollectListMap");
				/** cancel attention */
				if(datas != null && datas.size() > 0)
				{
					tmembercollectmanager.deleteByPrimaryKey(Long.parseLong(String.valueOf(datas.get(0).get("id"))));
					writeObjectToResponse("0", ContentType.application_json);
				}
				/** continue attention */
				else
				{
					TMemberCollect tct = new TMemberCollect();
					tct.setCreateDate(new Date());
					tct.setMemberId(tm.getId());
					tct.setCollectType(Integer.parseInt(type));
					tct.setRelevanceId(Long.valueOf(id));
					tmembercollectmanager.insert(tct);
					writeObjectToResponse("1", ContentType.application_json);
				}
			}
		}
		else
		{
			writeObjectToResponse("2", ContentType.application_json);
		}
	}
	
	/**
	 * Juding attention or not
	 */
	public void isattent() throws NumberFormatException, SQLException, IOException
	{
		String id = this.getRequest().getParameter("id");
		if(id != null && !id.trim().equals(""))
		{
			TMember tm = (TMember)this.getRequest().getSession().getAttribute("member");
			if(tm != null)
			{
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("relevance_id", id);
				map.put("collect_type", "0");
				map.put("member_id", tm.getId());
				List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(map, "t_member_collect.selectTmcollectListMap");
				if(datas != null && datas.size() > 0)
				{
					writeObjectToResponse("0", ContentType.application_json);
				}
				else
				{
					writeObjectToResponse("1", ContentType.application_json);
				}
			}
		}
		else
		{
			writeObjectToResponse("2", ContentType.application_json);
		}
	}
	
	/**
	 * This is brand sale area
	 */
	public void brandsale()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = opensqlmanage.selectForListByMap(null, "t_promotion.selectPromotionBrand");
		List<BrandData> mapList = listToLitMap(list);
		map.put("list", mapList);
		this.writeObjectToResponse(map, ContentType.application_json);
	}
	
	private List<BrandData> listToLitMap(List<Map<String,Object>> list)
	{
		Map<String, List<BrandList>> listMap = new LinkedHashMap<String,List<BrandList>>();
		for (Map<String,Object> brandList : list) 
		{
			BrandList bean = new BrandList();
			Object id = brandList.get("id");
			bean.setId(id.toString());
			bean.setName(brandList.get("name").toString());
			String pys = (String) brandList.get("pingyin");
			String py = (String) pys.subSequence(0,1);
			bean.setPingyin(py);
			if(null==listMap.get(py))
			{
				List<BrandList> nlist = new ArrayList<BrandList>();
				if(isNum(py))
				{
					if(null==listMap.get("num"))
					{
						nlist.add(bean);
						listMap.put("num", nlist);
					}
					else 
					{
						List<BrandList> numlist = listMap.get("num");
						numlist.add(bean);
					}
				}
				else 
				{
					nlist.add(bean);
					listMap.put(py, nlist);
				}
			}
			else
			{
				List<BrandList> nlist = listMap.get(py);
				nlist.add(bean);
			}
		}
        List<BrandData> nlistobj = new ArrayList<BrandData>();
	    String[] zimu = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","num"};
		for (String zm : zimu)
		{
			if(null!=listMap.get(zm))
			{
				List<BrandList> bl = listMap.get(zm);
				BrandData bd = new BrandData();
				bd.setList(bl);
				bd.setZimu(zm);
				nlistobj.add(bd);
			}
			else
			{
				BrandData bd = new BrandData();
				bd.setList(null);
				bd.setZimu(zm);
				nlistobj.add(bd);
			}
		}
	    return nlistobj;
	}
	
	private boolean isNum(String str)
	{
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	/**
	 * checking the status of user's login
	 */
	public void clogin() throws Exception
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		TMember tm = (TMember)this.getRequest().getSession().getAttribute("member");
		if(tm != null)
		{
			//the user is logined 
			out.println(1);
		}
		else
		{
			//the user is logouted
			out.println(2);
		}
		out.flush();
		out.close();
	}
	
	//根据商品ID判断库存、上下架状态
	public void jugeStatus()
	{
		String id = getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(map, "t_goods.jugeStatus");
		if(datas != null && datas.size() > 0)
		{
			String result = String.valueOf(datas.get(0).get("pcstatus"));
			int newResult = 0;
			//上架
			if(result.equals("1"))
			{
				newResult = 1;
				String stock = String.valueOf(datas.get(0).get("stock"));
				//有库存
				if(stock.equals("3"))
				{
					newResult = 3;
				}
				//无库存
				else
				{
					newResult = 4;
				}
			}
			//下架
			else
			{
				newResult = 2;
			}
			writeObjectToResponse(newResult, ContentType.application_json);
		}
		else
		{
			writeObjectToResponse(0, ContentType.application_json);
		}
	}
	
	/** Inner class */
	public class BrandData
	{
		private String zimu;
		private List<BrandList> list;
		public String getZimu() 
		{
			return zimu;
		}
		public void setZimu(String zimu) 
		{
			this.zimu = zimu;
		}
		public List<BrandList> getList() 
		{
			return list;
		}
		public void setList(List<BrandList> list) 
		{
			this.list = list;
		}
	}
	
	/**
	 * WAP站首页获取信息
	 */
	public void getLeaderInfo()
	{
		String str = getRequest().getParameter("leaderId");
		Map<String, Object> data = null;
		if(str != null && !str.trim().equals(""))
		{
			Long leaderId = Long.parseLong(str);
			data = (Map<String, Object>) opensqlmanage.selectObjectByObject(leaderId, "t_leader.select_leader_info_at_home");
		}
		writeObjectToResponse(data, ContentType.application_json);
	}
	
	/** Inner class */
	public class BrandList
	{
		private String id;
		private String pingyin;
		private String name;
		public String getId() 
		{
			return id;
		}
		public void setId(String id) 
		{
			this.id = id;
		}
		public String getPingyin() 
		{
			return pingyin;
		}
		public void setPingyin(String pingyin)
		{
			this.pingyin = pingyin;
		}
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}
	}
	
	//获取商品价格
	public void countpricefrompc() throws IOException
	{
		long start = System.currentTimeMillis();
		System.out.println("开始时间：："+start);
		//解析参数组
		String params = getRequest().getParameter("params");
		JSONArray jsonarray = JSONArray.fromObject(params);
		List<Map<String,Object>> paramsarray =  (List<Map<String, Object>>) JSONArray.toCollection(jsonarray, Map.class);
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		if(paramsarray != null)
		{
			for(Map<String, Object> m : paramsarray)
			{
				//商品ID
				String goodsId = String.valueOf(m.get("goodsId"));
				//平台
				String platform = String.valueOf(m.get("platform")).trim();
				//HTML元素ID
				String htmlObjId = String.valueOf(m.get("htmlObjId"));
				
				Map<String, Object> nowDatas = null;
				Map<String, Object> map = new HashMap<String, Object>();
				try 
				{
					Map<String, Object> p = new HashMap<String, Object>();
					p.put("id", goodsId);
					nowDatas = (Map<String, Object>) opensqlmanage.selectForListByMap(p, "t_goods.selectGoodsPriceById").get(0);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				if(nowDatas != null)
				{
					String price = null;
					if(platform.equals("1"))
					{
						price = String.valueOf(nowDatas.get("pc_price"));
					}
					if(platform.equals("2"))
					{
						price = String.valueOf(nowDatas.get("wap_price"));
					}
					if(platform.equals("3"))
					{
						price = String.valueOf(nowDatas.get("app_price"));
					}
					map.put("goodsId", goodsId);
					map.put("nowPrice", price);
					map.put("platform", platform);
					map.put("htmlObjId", htmlObjId);
					result.add(map);
				}
				else
				{
					result.add(m);
				}
			}
			writeObjectToResponse(result, ContentType.application_json);
		}
		else
		{
			writeObjectToResponse("params null", ContentType.application_json);
		}
		long end = System.currentTimeMillis();
		System.out.println("消耗时间：：：："+(end-start));
	}
	
	//获取已登录用户名
	public void getMemberUserName() throws SQLException
	{
		TMember tm = (TMember)this.getRequest().getSession().getAttribute("member");
		if(tm != null)
		{
			TMemberBaseMessageExt tmbme = tmemberbasemessageextmanager.selectByPrimaryKey(tm.getId());
			Map<String, Object> map = new HashMap<String, Object>();
			String headUrl = tm.getHeadPortrait();
			String disPlayName = "";
			String nickName = null;
			String realName = null;
			if(tmbme != null)
			{
				nickName = tmbme.getNickName();
				realName = tmbme.getRealName();
			}
			String userName = tm.getUserName();
			String mobile = tm.getMobile();
			if(nickName != null && !nickName.equals(""))
			{
				disPlayName = nickName;
			}
			else
			{
				if(userName != null && !userName.equals(""))
				{
					disPlayName = userName;
				}
				else
				{
					if(mobile != null && !mobile.equals(""))
					{
						disPlayName = mobile;
					}
					else
					{
						if(realName != null && !realName.equals(""))
						{
							disPlayName = realName;
						}
					}
				}
			}
			map.put("disPlayName", disPlayName);
			map.put("headUrl", headUrl);
			writeObjectToResponse(map, ContentType.application_json);
		}
		else
		{
			writeObjectToResponse(0, ContentType.application_json);
		}
	}
	
	//根据商品ID判断库存
	public void isStockNotNull()
	{
		String id = getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(map, "t_goods.jugeStock");
		if(datas != null && datas.size() > 0)
		{
			String result = String.valueOf(datas.get(0).get("stock"));
			int newResult = 0;
			if(result.equals("1"))
			{
				newResult = 1;
			}
			writeObjectToResponse(newResult, ContentType.application_json);
		}
		else
		{
			writeObjectToResponse(0, ContentType.application_json);
		}
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

	public TMemberCollectManager getTmembercollectmanager()
	{
		return tmembercollectmanager;
	}

	public void setTmembercollectmanager(TMemberCollectManager tmembercollectmanager)
	{
		this.tmembercollectmanager = tmembercollectmanager;
	}

	public TMemberCollect gettMemberCollect()
	{
		return tMemberCollect;
	}

	public void settMemberCollect(TMemberCollect tMemberCollect)
	{
		this.tMemberCollect = tMemberCollect;
	}

	public TSysParameterManager getTsysparametermanager()
	{
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager)
	{
		this.tsysparametermanager = tsysparametermanager;
	}

	public TMemberBaseMessageExtManager getTmemberbasemessageextmanager() {
		return tmemberbasemessageextmanager;
	}

	public void setTmemberbasemessageextmanager(
			TMemberBaseMessageExtManager tmemberbasemessageextmanager) {
		this.tmemberbasemessageextmanager = tmemberbasemessageextmanager;
	}

}
