package com.rc.portal.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.THealthyPlanDAO;
import com.rc.portal.service.THealthyPlanManager;
import com.rc.portal.vo.THealthyPlan;
import com.rc.portal.vo.THealthyPlanExample;
import com.rc.portal.webapp.model.HealthyPlanContentModel;
import com.rc.portal.webapp.model.HealthyPlanContentModel.Combination;
import com.rc.portal.webapp.model.HealthyPlanContentModel.Goods;
import com.rc.portal.webapp.model.HealthyPlanContentModel.Symptom;

public class THealthyPlanManagerImpl  implements THealthyPlanManager {

	private OpenSqlDAO opensqldao;
    private THealthyPlanDAO thealthyplandao;

    public THealthyPlanManagerImpl() {
        super();
    }
    public void  setThealthyplandao(THealthyPlanDAO thealthyplandao){
        this.thealthyplandao=thealthyplandao;
    }
    public THealthyPlanDAO getThealthyplandao(){
        return this.thealthyplandao;
    }
    public     int countByExample(THealthyPlanExample example) throws SQLException{
        return thealthyplandao. countByExample( example);
    }

    public     int deleteByExample(THealthyPlanExample example) throws SQLException{
        return thealthyplandao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return thealthyplandao. deleteByPrimaryKey( id);
    }

    public     Long insert(THealthyPlan record) throws SQLException{
        return thealthyplandao. insert( record);
    }

    public     Long insertSelective(THealthyPlan record) throws SQLException{
        return thealthyplandao. insertSelective( record);
    }

    @SuppressWarnings("rawtypes")
	public     List selectByExample(THealthyPlanExample example) throws SQLException{
        return thealthyplandao. selectByExample( example);
    }

    public     THealthyPlan selectByPrimaryKey(Long id) throws SQLException{
        return thealthyplandao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(THealthyPlan record, THealthyPlanExample example) throws SQLException{
        return thealthyplandao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(THealthyPlan record, THealthyPlanExample example) throws SQLException{
        return thealthyplandao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(THealthyPlan record) throws SQLException{
        return thealthyplandao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(THealthyPlan record) throws SQLException{
        return thealthyplandao. updateByPrimaryKey( record);
    }

    /**
     * 获取健康方案内容
     * @param id:方案id
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<HealthyPlanContentModel> getHealthyPlanContent(Map<String, Object> map, String dataType)
    {
    	List<HealthyPlanContentModel> objlist = new ArrayList<HealthyPlanContentModel>();
		List<Map<String, Object>> listo = opensqldao.selectForListByMap(map, "t_healthy_plan.selectHealthyPlanByMap");
    	for (Map<String, Object> lo: listo) 
    	{
    		HealthyPlanContentModel obj = new HealthyPlanContentModel(); 
    		//主要相关信息
    		Long hid = (Long)lo.get("id");
			obj.setId(hid);
			obj.setGoodsCategoryId((Long)lo.get("goods_category_id"));
			obj.setCategoryName(lo.get("cname").toString());
			if(lo.get("wx_share_url") != null)
			{
				obj.setShareImageUrl(lo.get("wx_share_url").toString());
			}
			else
			{
				obj.setShareImageUrl("");
			}
			if(lo.get("wx_share_content") != null)
			{
				obj.setShareWords(lo.get("wx_share_content").toString());
			}
			else
			{
				obj.setShareWords("");
			}
			obj.setStatus((Integer)lo.get("status"));
			obj.setName((String)lo.get("name"));
			obj.setExplain(null==lo.get("plan_explain")?"":(String)lo.get("plan_explain"));
			obj.setBannerImgUrl(null==lo.get("banner_img_url")?"":(String)lo.get("banner_img_url"));
			obj.setReminder(null==lo.get("reminder")?"":(String)lo.get("reminder"));
			//伴随症
			obj.setLisths(getHealthyPlanSymptom(obj));
			obj.setSsum(obj.getLisths()==null?0:obj.getLisths().size());
			//组合套装
			obj.setListhc(getHealthyPlanCombination(obj,dataType));
			obj.setCsum(obj.getListhc()==null?0:obj.getListhc().size());
			objlist.add(obj);
		}
    	System.out.println(listo.size());
    	return objlist;
    }
    
    //伴随症
    @SuppressWarnings("unchecked")
    private List<Symptom> getHealthyPlanSymptom(HealthyPlanContentModel obj)
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.clear();
		map.put("id", obj.getId());
		List<Map<String, Object>> lists = opensqldao.selectForListByMap(map, "t_healthy_plan.selectHealthyPlanSymptomByMap");
		List<Symptom> lisths = new ArrayList<Symptom>();
		Map<String, Object> mapv = new HashMap<String, Object>();
		for (Map<String, Object> los : lists) 
		{
			HealthyPlanContentModel.Symptom hs = obj.new Symptom();
			//id,name,symptom_explain,banner_img_url,weight,type
			Long sid = (Long)los.get("id");
			hs.setSid(sid);
			hs.setSname((String)los.get("name"));
			hs.setSexplain(null==los.get("symptom_explain")?"":(String)los.get("symptom_explain"));
			hs.setSbannerImgUrl(null==los.get("banner_img_url")?"":(String)los.get("banner_img_url"));
			hs.setWeight((Integer)los.get("weight"));//权重  从大到小
			hs.setType((Integer)los.get("type"));//类型    0 辅药   1 主药
			
			mapv.clear();
			mapv.put("id", sid);
			mapv.put("type", 1);//1 伴随证商品  2 套餐商品
			List<Map<String, Object>> listg = opensqldao.selectForListByMap(mapv, "t_healthy_plan.selectHealthyPlanGoodsByMap");
			List<Goods> lg = new ArrayList<Goods>();
			for (Map<String, Object> losg : listg) 
			{
				HealthyPlanContentModel.Goods hg = obj.new Goods();
				//tg.id,tg.abbreviation_picture,tg.app_price,tg.wap_price,tg.price,tg.short_name,tg.main_title,thpg.weight
				hg.setGid((Long)losg.get("id"));
				hg.setgImgUrl(null==losg.get("abbreviation_picture")?"":(String)losg.get("abbreviation_picture"));
				hg.setAppPrice((BigDecimal)(losg.get("app_price")));
				hg.setWapPrice((BigDecimal)(losg.get("wap_price")));
				hg.setPrice((BigDecimal)(losg.get("price")));
				hg.setMainTitle(null==losg.get("main_title")?"":(String)losg.get("main_title"));
				hg.setGname(null==losg.get("goods_name")?"":(String)losg.get("goods_name"));
				hg.setSname(null==losg.get("short_name")?"":(String)losg.get("short_name"));
				hg.setType(null==losg.get("type")?1:(Integer)losg.get("type"));
				lg.add(hg);
			}
			hs.setGsum(null==lg?0:lg.size());
			hs.setLists(lg);
			lisths.add(hs);
		}
		HealthyPlanContentModel.Symptom hs = obj.new Symptom();
		for (int i = 0; i < lisths.size(); i++) 
		{
			Symptom symptom = lisths.get(i);
			if(1==symptom.getType())
			{
				hs = symptom;
				lisths.remove(symptom);
			}
		}
		lisths.add(0, hs);
		return lisths;
    }
    
    /**
     * 组合套装
     * @param obj
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Combination> getHealthyPlanCombination(HealthyPlanContentModel obj, String dataType)
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Combination> listhc = new ArrayList<Combination>();
    	map.clear();
		map.put("id", obj.getId());
		List<Map<String, Object>> listc = opensqldao.selectForListByMap(map, "t_healthy_plan.selectHealthyPlanCombinationByMap");
		Map<String, Object> mapv = new HashMap<String, Object>();
		for (Map<String, Object> loc : listc) 
		{
			HealthyPlanContentModel.Combination hc = obj.new Combination();
			//id,package_name,comment_content 
			Long cid = (Long)loc.get("id");
			hc.setCid(cid);
			hc.setCname(null==loc.get("package_name")?"":(String)loc.get("package_name"));
			hc.setCommentContent(null==loc.get("comment_content")?"":(String)loc.get("comment_content"));
			hc.setRealName(null==loc.get("real_name")?"":(String)loc.get("real_name"));
			hc.setWorkYear(null==loc.get("work_year")?"":(String)loc.get("work_year"));
			hc.setRemark(null==loc.get("remark")?"":(String)loc.get("remark"));
			hc.setHeadUrl(null==loc.get("head_url")?"http://img.zdfei.com/static/image/temp/20160127/73569e0fe07f5e60234179baddec94eb.jpg":(String)loc.get("head_url"));
			
			mapv.clear();
			mapv.put("id", cid);
			mapv.put("type", 2);//1 伴随证商品  2 套餐商品
			mapv.put("dataType", dataType);
			List<Map<String, Object>> listg = opensqldao.selectForListByMap(mapv, "t_healthy_plan.selectHealthyPlanGoodsByMap");
			List<Goods> lg = new ArrayList<Goods>();
			for (Map<String, Object> losg : listg) 
			{
				HealthyPlanContentModel.Goods hg = obj.new Goods();
				//tg.id,tg.abbreviation_picture,tg.app_price,tg.wap_price,tg.price,tg.short_name,tg.main_title,thpg.weight
				hg.setGid((Long)losg.get("id"));
				hg.setgImgUrl(null==losg.get("abbreviation_picture")?"":(String)losg.get("abbreviation_picture"));
				hg.setAppPrice((BigDecimal)(losg.get("app_price")));
				hg.setWapPrice((BigDecimal)(losg.get("wap_price")));
				hg.setPrice((BigDecimal)(losg.get("price")));
				hg.setMainTitle(null==losg.get("main_title")?"":(String)losg.get("main_title"));
				hg.setGname(null==losg.get("goods_name")?"":(String)losg.get("goods_name"));
				hg.setSname(null==losg.get("short_name")?"":(String)losg.get("short_name"));
				hg.setSpec(null==losg.get("spec")?"":(String)losg.get("spec"));
				hg.setType(null==losg.get("type")?0:Integer.parseInt(losg.get("type")+""));
				lg.add(hg);
			}
			hc.setGsum(null==lg?0:lg.size());
			hc.setListc(lg);
			hc.setcPrice(new BigDecimal(0));
			hc.setCcPrice(new BigDecimal(0));
			hc.setSavePrice(new BigDecimal(0));
			listhc.add(hc);
		}
		return listhc;
    }
    
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}
	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}

}
