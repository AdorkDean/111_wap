package com.rc.portal.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.memcache.MemCached;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.webapp.model.ProductCategoryModel;

public class CategoryListAction extends BaseAction {
	private Condition model = new Condition();
	private OpenSqlManage  opensqlmanage;
	private TSysParameterManager tsysparametermanager;
	private List<ProductCategoryModel> listpcm ;
	
	
	public String getHomeData() throws Exception{
		String str=null;
		str=(String)MemCached.getmcc().get("homeDataWap");
		System.out.println("================="+str);
		if(str!=null&&!"".equals(str.trim())){
			JSONArray jo = JSONArray.fromObject(str);
			listpcm= (List<ProductCategoryModel>) jo.toCollection(jo, ProductCategoryModel.class);
		}else{
			try {
				listpcm=new ArrayList<ProductCategoryModel>();
				String category_id = tsysparametermanager.getKeys("goods_category_id");
				if(!StringUtils.hasText(category_id)){
					return null;
				}
				Map<String, Object> mapv = new HashMap();
//			mapv.put("grade", 0);
				mapv.put("parent", Long.parseLong(category_id));
				List<Map<String,Object>> list = opensqlmanage.selectForListByMap(mapv, "t_category.selectProductCategoryByGrade");
				for (int i = 0; i < list.size(); i++) {
					//1
					Map<String,Object> map = list.get(i);
					ProductCategoryModel obj = new ProductCategoryModel();
					obj.setPid(map.get("id")+"");
					obj.setName(map.get("name")+"");
					obj.setType(""+0);
					List<ProductCategoryModel> listpcm1 = new ArrayList<ProductCategoryModel>();
					//2      
					mapv.clear();
					mapv.put("parent", map.get("id"));
					List<Map<String,Object>> listn = opensqlmanage.selectForListByMap(mapv, "t_category.selectProductCategoryByGrade");
					for (Map<String, Object> map2 : listn) {
						//3
						boolean fla = false;
						ProductCategoryModel obj1 = new ProductCategoryModel();
						obj1.setPid(map2.get("id")+"");
						obj1.setName(map2.get("name")+"");
						obj1.setType(0+"");
						mapv.clear();
						mapv.put("parent", map2.get("id"));
						List<Map<String,Object>> listn1 = opensqlmanage.selectForListByMap(mapv, "t_category.selectProductCategoryByGrade");
						List<ProductCategoryModel> listpcm2 = new ArrayList<ProductCategoryModel>();
						if(null!=listn1&&0<listn1.size()){
							for (Map<String, Object> map3 : listn1) {
								ProductCategoryModel obj2 = new ProductCategoryModel();
								obj2.setPid(map3.get("id")+"");
								obj2.setName(map3.get("name")+"");
								obj2.setType(0+"");
								mapv.clear();
								mapv.put("pcid", map3.get("id"));
								List<Map<String,Object>> img = opensqlmanage.selectForListByMap(mapv, "t_category.selectProductImgByPc");
								if(null!=img&&0<img.size()){
									for (Map<String, Object> map4 : img) {
										obj2.setImgs(map4.get("image")+"");
									}
									listpcm2.add(obj2);
									fla=true;
								}
							}
						}else {
							ProductCategoryModel obj2 = new ProductCategoryModel();
							obj2.setPid(map2.get("id")+"");
							obj2.setName(map2.get("name")+"");
							obj2.setType(0+"");
							mapv.clear();
							mapv.put("pcid", map2.get("id"));
							List<Map<String,Object>> img = opensqlmanage.selectForListByMap(mapv, "t_category.selectProductImgByPc");
							if(null!=img&&0<img.size()){
								for (Map<String, Object> map4 : img) {
									obj2.setImgs(map4.get("image")+"");
								}
								listpcm2.add(obj2);
								fla=true;
							}
						}
						obj1.setPlist(listpcm2);
						if(fla){
							listpcm1.add(obj1);
						}
					}
					obj.setPlist(listpcm1);
					listpcm.add(obj);
				}
				if(listpcm!=null&&listpcm.size()>0){
					JSONArray json = JSONArray.fromObject(listpcm);
					str=json.toString();
					MemCached.getmcc().add("homeDataWap",str,new Date(1000*60*30));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "getHomeData";
	}
	public class Condition {
		private Long id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

	public List<ProductCategoryModel> getListpcm() {
		return listpcm;
	}

	public void setListpcm(List<ProductCategoryModel> listpcm) {
		this.listpcm = listpcm;
	}

	
}
