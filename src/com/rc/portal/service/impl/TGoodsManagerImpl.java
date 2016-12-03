package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.TGoodsDAO;
import com.rc.portal.dao.TGoodsImagesDAO;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsExample;
import com.rc.portal.vo.TGoodsImages;
import com.rc.portal.vo.TGoodsImagesExample;
import com.rc.portal.webapp.model.GoodsGroupModel2;
import com.rc.portal.webapp.model.GroupModel;

@SuppressWarnings({"unchecked","rawtypes"})
public class TGoodsManagerImpl implements TGoodsManager 
{
    private TGoodsDAO tgoodsdao;
    private OpenSqlDAO opensqldao;
    private TGoodsImagesDAO tgoodsimagesdao;
	
    /**
     * 获取商品详情不分平台
     */
	public List<Map<String, Object>> selectByPrimaryKey2(Map<String,Object> mapv,String type) throws Exception
	{
	    	List<Map<String, Object>> listmap =  new ArrayList<Map<String, Object>>();
	    	listmap = (List<Map<String, Object>>) opensqldao.selectForListByMap(mapv, "t_goods.selectGoodsById");
	    	for (Map<String, Object> map : listmap) 
	    	{
	    		long id = (Long) map.get("id");
	    		byte[] instructions = (byte[]) map.get("instruction");
	    		if(null!=instructions)
	    		{
	    			map.put("instructions", new String(instructions,"utf-8"));
	    		}
	    		byte[] goodsDescribes = (byte[]) map.get("goodsDescribe");
	    		if(null!=goodsDescribes)
	    		{
	    			map.put("goodsDescribes", new String(goodsDescribes));
	    		}
	    		TGoodsImagesExample examplei = new TGoodsImagesExample();
	    		examplei.createCriteria().andGoodsidEqualTo(id).andUserTypeEqualTo(Integer.parseInt(type));
	    		examplei.setOrderByClause("sort desc");
	    		List<TGoodsImages> listi = tgoodsimagesdao.selectByExample(examplei);
	    		if(null!=listi&&0<listi.size())
	    		{
		    		map.put("defaultimg", listi.get(0).getArtworkUrl());
		    		if(type=="1"||"1".equals(type))
		    		{
		    			map.put("defaultimgs", listi.get(0).getImageUrl());
		    		}
	    		}
		    	Map<String, Object> valmap = new HashMap<String, Object>();
		    	valmap.put("id", id);
		    	List<GoodsGroupModel2> listg = opensqldao.selectForListByMap(valmap, "t_goods.selectGroupByGoodsId");
		    	List<Map<String, String>> listpc = getCategory(id);
		    	valmap.clear();
		    	for (GoodsGroupModel2 obj : listg) 
		    	{
		    		valmap.put("id", obj.getId());
		    		List<GroupModel> listgg = opensqldao.selectForListByMap(valmap, "t_goods.selectGoodsGroupByGoodsId");
		    		obj.setList(listgg);
				}
		    	valmap.clear();
		    	valmap.put("skuid", map.get("skuid"));
		    	List<Map<Long,String>> lists = opensqldao.selectForListByMap(valmap, "t_goods.selectSpecByMap1");
		    	map.put("lists", lists);
		    	map.put("listi", listi);
		    	map.put("listg", listg);
		    	map.put("listpc", listpc);
	    	}
	    	return listmap;
	 	}

			private List<Map<String, String>> getCategory(long id){
				Map<String, Object> valmap = new HashMap<String, Object>();
				valmap.put("id", id);
				valmap.put("type", 2);
		    	Map<String,String> mappc =(Map<String, String>) opensqldao.selectForObjectByMap(valmap, "t_goods.selectCategoryByCId");
		    	List<Map<String, String>> listpc = new ArrayList<Map<String,String>>();
		    	if(null!=mappc){
		    		String[] ids = null;
		    		if(null!=mappc.get("idall")&&!"".equals(mappc.get("idall"))){
		    			ids = mappc.get("idall").split(",");
		    		}
		    		String level = mappc.get("clevel");
		    		if("4"==level||"4".equals(level)){
		    			Map<String, String> map1 = new HashMap<String, String>();
		    			map1.put("name", mappc.get("name3"));
		    			map1.put("id", ids[2]);
		    			listpc.add(map1);
		    			Map<String, String> map2 = new HashMap<String, String>();
		    			map2.put("name", mappc.get("name2"));
		    			map2.put("id", ids[3]);
		    			listpc.add(map2);
		    			Map<String, String> map3 = new HashMap<String, String>();
		    			map3.put("name", mappc.get("name1"));
		    			map3.put("id", mappc.get("id"));
		    			listpc.add(map3);
		    		}else if("3"==level||"3".equals(level)){
		    			Map<String, String> map1 = new HashMap<String, String>();
		    			map1.put("name", mappc.get("name2"));
		    			map1.put("id", ids[2]);
		    			listpc.add(map1);
		    			Map<String, String> map3 = new HashMap<String, String>();
		    			map3.put("name", mappc.get("name1"));
		    			map3.put("id", mappc.get("id"));
		    			listpc.add(map3);
		    		}else if("2"==level||"2".equals(level)){
		    			Map<String, String> map3 = new HashMap<String, String>();
		    			map3.put("name", mappc.get("name1"));
		    			map3.put("id", mappc.get("id"));
		    			listpc.add(map3);
		    		}
		    	}
				return listpc;
			}
	    	
	    	
		    public TGoodsManagerImpl() {
		        super();
		    }
		    public void  setTgoodsdao(TGoodsDAO tgoodsdao){
		        this.tgoodsdao=tgoodsdao;
		    }
		    public TGoodsDAO getTgoodsdao(){
		        return this.tgoodsdao;
		    }
		    public     int countByExample(TGoodsExample example) throws SQLException{
		        return tgoodsdao. countByExample( example);
		    }

		    public     int deleteByExample(TGoodsExample example) throws SQLException{
		        return tgoodsdao. deleteByExample( example);
		    }

		    public     int deleteByPrimaryKey(Long id) throws SQLException{
		        return tgoodsdao. deleteByPrimaryKey( id);
		    }

		    public TGoodsImagesDAO getTgoodsimagesdao()
			{
				return tgoodsimagesdao;
			}
			public void setTgoodsimagesdao(TGoodsImagesDAO tgoodsimagesdao)
			{
				this.tgoodsimagesdao = tgoodsimagesdao;
			}
			public     Long insert(TGoods record) throws SQLException{
		        return tgoodsdao. insert( record);
		    }

		    public     Long insertSelective(TGoods record) throws SQLException{
		        return tgoodsdao. insertSelective( record);
		    }

			public     List selectByExample(TGoodsExample example) throws SQLException{
		        return tgoodsdao. selectByExample( example);
		    }

		    public     TGoods selectByPrimaryKey(Long id) throws SQLException{
		        return tgoodsdao. selectByPrimaryKey( id);
		    }

		    public     int updateByExampleSelective(TGoods record, TGoodsExample example) throws SQLException{
		        return tgoodsdao. updateByExampleSelective( record, example);
		    }

		    public     int updateByExample(TGoods record, TGoodsExample example) throws SQLException{
		        return tgoodsdao. updateByExample( record, example);
		    }

		    public     int updateByPrimaryKeySelective(TGoods record) throws SQLException{
		        return tgoodsdao. updateByPrimaryKeySelective( record);
		    }

		    public     int updateByPrimaryKey(TGoods record) throws SQLException{
		        return tgoodsdao. updateByPrimaryKey( record);
		    }
		    
		    
			public OpenSqlDAO getOpensqldao()
			{
				return opensqldao;
			}
			public void setOpensqldao(OpenSqlDAO opensqldao)
			{
				this.opensqldao = opensqldao;
			}
			
			
}
