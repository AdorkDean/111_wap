package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.ICartManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCartItemManager;
import com.rc.portal.service.TMemberCollectManager;
import com.rc.portal.util.cookieManager;
import com.rc.portal.vo.TCartItem;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberCollect;
import com.rc.portal.webapp.model.cart.CartGift;
import com.rc.portal.webapp.model.cart.CartParam;

public class CartAction extends BaseAction {
	
	private static final long serialVersionUID = 7250632789955804061L;
	private ICartManager cartmanager;
	private OpenSqlManage opensqlmanage;
	private TCartItemManager tcartitemmanager;
	private TMemberCollectManager tmembercollectmanager;
	public static String cartKey = "111yao";
	private String tempkey = "9B30C145-E63E-4BB1-9444-93FDDD445DFC";
	
	private String backurl;
	
	
	private CartParam arg = new CartParam();
	
	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().toUpperCase();
		System.out.println(uuid);
	}
	
	
	public String rebuy() throws NumberFormatException, SQLException{
		
		String orderid = this.getRequest().getParameter("orderid");
		String str = cartmanager.againBuy(Long.parseLong(orderid));
		this.getRequest().setAttribute("nostore", str);
		 return "rebuy";
	}
	
	public void getCartSum() throws IOException, SQLException{
		
		System.out.println("******************************");
		
		HttpServletResponse reponse = this.getResponse();
		
	    reponse.setContentType("text/html;charset=utf-8");
		
	    PrintWriter out = reponse.getWriter();
		  
//		String key = this.getRequest().getParameter("key");
		String key = cookieManager.getCookieValueByName(this.getRequest(), CartAction.cartKey);
		System.out.println("getcartsum = "+key);
		TMember user = (TMember) this.getSession().getAttribute("member");
		Map map = new HashMap();
		
		map.put("uuid", key);
		if(user!=null){
			map.put("userid", user.getId());
		}
		
		if(user!=null){
			System.out.println("同步.......");
			System.out.println("用户ID="+user.getId());
			try {
				cartmanager.synCart(user.getId(), key);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
//		Integer cnt = (Integer)opensqlmanage.selectForObjectByMap(map, "mycart.get-cart-sum");
		
		List<HashMap> list = opensqlmanage.selectForListByMap(map, "mycart.get-cart-item");
		
		int cnt = 0;
		for(HashMap m : list){
			cnt = cnt + ((Integer)m.get("quantity"));
		}
		
		System.out.println("cnt----------->"+cnt);
		
//		Map rs = new HashMap();
//		rs.put("cnt", cnt);
//		rs.put("list", list);
		
		if(user!=null){
			arg.setUserId(user.getId());
			arg.setIslogin(true);
		}else{
			if("null".equals(key) || key==null){	// 客户端 已经存在一个key
				arg.setCartkey(createKey());
			}else{
				arg.setCartkey(key);
			}
			arg.setIslogin(false);
		}
		
//		Map mp = cartmanager.accounts(arg);
//		BigDecimal money = (BigDecimal) mp.get("money");
//		BigDecimal youhui = (BigDecimal) mp.get("youhui");
		
//		CartSum cs = new CartSum();
//		cs.setCnt(cnt);
//		cs.setList(list);
//		cs.setYouhui(youhui);
//		cs.setZongjia(money);
		
		System.out.println("==============cnt="+cnt);
		
		JSONObject json = JSONObject.fromObject(cnt);
		
//		out.print(cnt);
		out.print(cnt);
		out.close();
	}
	
	public void recommend() throws IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		String key = cookieManager.getCookieValueByName(this.getRequest(), cartKey);
		TMember user = (TMember) this.getSession().getAttribute("member");
		Map map = new HashMap();
		map.put("key", key);
		List<HashMap> list = opensqlmanage.selectForListByMap(map, "mycart.cart-recommend");
		JSONArray json = JSONArray.fromObject(list);
		out.print(json.toString());
		out.close();
		
	}
	

	
   public String createKey(){
    	return UUID.randomUUID().toString().toUpperCase();
   }
	    
	   
   
   public void addTao() throws SQLException{
	   
	   
	   this.getResponse().setContentType("text/html;charset=utf-8");
	   
	   String gid = this.getRequest().getParameter("gid");
	   String flag = this.getRequest().getParameter("flag");
	   String key =  cookieManager.getCookieValueByName(this.getRequest(), cartKey);
	   int count = Integer.parseInt(this.getRequest().getParameter("count")==null?"1":this.getRequest().getParameter("count"));
	   
	   PrintWriter out = null;
	   String statusCode = "1";
	   short failfalg = 0;
	   StringBuilder sb = new StringBuilder();
	   
	   try{
		   out = this.getResponse().getWriter();
		   List<String> failGoodsList = new ArrayList<String>();
		   arg = new CartParam();
		   TMember user = (TMember) this.getSession().getAttribute("member");
		   if(user!=null){
				arg.setUserId(user.getId());
				arg.setIslogin(true);
			}else{
				arg.setIslogin(false);
				if(key==null){	// 客户端 已经存在一个key
					key = createKey();
				}
				arg.setCartkey(key);
				cookieManager.addCookie(this.getRequest(), this.getResponse(), cartKey, key, 1000000);
			}
		   long id = 0;
		   int n = 0;
		   if("1".equals(flag)){	// 正常商品
				arg.setGoodsId(Long.parseLong(gid));
				id = cartmanager.addCart(arg,count);
				String s = "{\"statusCode\":\""+2+"\",\"failfalg\":\""+failfalg+"\",\"name\":\""+sb+"\"}";
				System.out.print("s----->"+s);
				out.print(s);
				out.close();
				return;
			}else{
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("taoid", gid);
				List<Map<String,Object>> list = opensqlmanage.selectForListByMap(map, "mycart.taocan_goods");
				n = list.size();
				long goodsId = 0;
				for(Map<String,Object> m : list){
					arg.setGoodsId((Long)m.get("goods_id"));
					id = cartmanager.addCart(arg,count);
					System.out.println("=========>"+id);
					if(id<0){	// 加入购物车失败的商品
						failGoodsList.add((String)m.get("goods_name"));
					}
				}
			}
		   
			if(failGoodsList.size()>0)
			{   
				int x = 1;
				for(String name : failGoodsList){
					sb.append(x+"."+name+";\\n");
					x++;
				}
				failfalg = 1;
				
				if(n>failGoodsList.size()){ // 部分商品添加成功 
					statusCode = "2";
				}else if(n==failGoodsList.size()){	// 所有商品都没有库存或下架
					statusCode = "3";
				}
				
			}else{
				statusCode = "1"; // 如果所有商品都有库存或没有下架
			}
			
	   }catch(Exception e){
		   statusCode = "0";
		   e.printStackTrace();
	   }
	   
	   
		String s = "{\"statusCode\":\""+statusCode+"\",\"failfalg\":\""+failfalg+"\",\"name\":\""+sb+"\"}";

		out.print(s);
		out.close();
		
//		
   }
	   
	public void addCart() throws SQLException, IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		String gid = this.getRequest().getParameter("gid");
		String cnt = this.getRequest().getParameter("cnt");
		String key =  cookieManager.getCookieValueByName(this.getRequest(), cartKey);
		
		int count = 1;
		if(cnt!=null && !"0".equals(cnt) && !"".equals(cnt)){
			count = Integer.parseInt(cnt);
		}
		arg = new CartParam();
		
		TMember user = (TMember) this.getSession().getAttribute("member");
		if(user!=null){
			arg.setUserId(user.getId());
			arg.setIslogin(true);
		}else{
			arg.setIslogin(false);
			if(key==null){	// 客户端 已经存在一个key
				key = createKey();
			}
			arg.setCartkey(key);
			cookieManager.addCookie(this.getRequest(), this.getResponse(), cartKey, key, 1000000);
		}
		arg.setGoodsId(Long.parseLong(gid));
		long id = cartmanager.addCart(arg,count);
		
		
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("status",id);
		rs.put("key", key);
		JSONObject json = JSONObject.fromObject(rs);
		out.print(json.toString());
		out.close();
	}
	
	
	/*public String getCart() throws SQLException{
		
		String cookie = cookieManager.getCookieValueByName(this.getRequest(), "111yao");
		
		System.out.println("cookie==============="+cookie);
		
		String key = this.getRequest().getParameter("key");
		arg.setCartkey(key);
		TMember user = (TMember) this.getSession().getAttribute("member");
		if(user!=null){
			arg.setUserId(user.getId());
			arg.setIslogin(true);
		}
		TCart c = cartmanager.getCart(arg);
		this.getRequest().setAttribute("cart", c);
		return "cart-detail";
	}*/
	
	public void checked() throws IOException, SQLException{
		
		PrintWriter out = this.getResponse().getWriter();
		String flag= this.getRequest().getParameter("x");  // 1选中 0不选中
		String id = this.getRequest().getParameter("id");
//		CartParam arg = new CartParam();
		
//		cartmanager.updateGoodsCount(arg, flag);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("flag", flag);
		int n = opensqlmanage.updateByMap_drug(map, "mycart.check-cart-item");
		
		out.print(n);
		out.close();
		
	}
	
	
	public void allChecked() throws IOException, SQLException{
		
		PrintWriter out = this.getResponse().getWriter();
		String flag= this.getRequest().getParameter("x");  // 1选中 0不选中
		String id = this.getRequest().getParameter("id");

		Map map = new HashMap();
		map.put("id", id);
		map.put("flag", flag);
		int n = opensqlmanage.updateByMap_drug(map, "mycart.all-check-cart-item");
		
		out.print(n);
		out.close();
		
	}
	
	
	public void del() throws IOException, NumberFormatException, SQLException{
		
		PrintWriter out = this.getResponse().getWriter();
		String id = this.getRequest().getParameter("id");
		int n = tcartitemmanager.deleteByPrimaryKey(Long.parseLong(id));
		out.print(1);
		out.close();
	}
	
	
	public void collect() throws IOException, NumberFormatException, SQLException{
		
		PrintWriter out = this.getResponse().getWriter();
		String cartid = this.getRequest().getParameter("cartid");
		String goodsid = this.getRequest().getParameter("goodsid");
		
		TMemberCollect record = new TMemberCollect();
		record.setRelevanceId(Long.parseLong(goodsid));
		record.setMemberId(1L);
		record.setCollectType(0);
		record.setCreateDate(new Date());
		long a = tmembercollectmanager.insert(record);
		int n = 0;
		if(a>0){
			n = tcartitemmanager.deleteByPrimaryKey(Long.parseLong(cartid));
		}
		
		out.print(1);
		out.close();
	}
	

	public void isLogin() throws IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		TMember user = (TMember) this.getSession().getAttribute("member");
		int n= 1;
		if(user==null){
			n = 0;
		}
		out.print(n);
		out.close();
		
	}
	
	
	public String page() throws SQLException{
		

//		String key = this.getRequest().getParameter("key");
		
//		System.out.println("key====="+key);
		String key = cookieManager.getCookieValueByName(this.getRequest(), cartKey);
		System.out.println("cookie==============="+key);
		
		
		String url = this.getRequest().getParameter("url");
		
		System.out.println("url------>"+url);
		
		TMember user = (TMember) this.getSession().getAttribute("member");
		Map map = new HashMap();
		
		if(user!=null){
			map.put("userid", user.getId());
		}
		List<Map> list = new ArrayList<Map>();
		if(key!=null)
		{
			map.put("uuid", key);
		}
		
		list = opensqlmanage.selectForListByMap(map, "mycart.get-cart-item");
	
		
		this.getRequest().setAttribute("l", list);
		this.getRequest().setAttribute("url", url);
		
		return "cart-page";
	}

	
	public void update() throws IOException, SQLException{
		
		PrintWriter out = this.getResponse().getWriter();
		String cartid = this.getRequest().getParameter("cartid");
		String flag = this.getRequest().getParameter("flag");
		String goodsid = this.getRequest().getParameter("goodsid");
		String cnt = this.getRequest().getParameter("count");
		
		String key = cookieManager.getCookieValueByName(this.getRequest(), cartKey);
		
		Map<String,Object> rs = new HashMap<String,Object>();
		int count = 1;
		
		if(cnt!=null && !"".equals(cnt)){
			count = Integer.parseInt(cnt.trim());
		}
		
		int n = cartmanager.updateGoodsCount(cartid,goodsid,count,flag);
		
		if(n>0){	// 操作成功 
			CartParam arg = new CartParam();
			arg.setCartkey(key);
			TMember user = (TMember) this.getSession().getAttribute("member");
			if(user!=null){
				arg.setUserId(user.getId());
				arg.setIslogin(true);
			}else{
				if("null".equals(key) || key==null){	// 客户端 已经存在一个key
					arg.setCartkey(createKey());
				}else{
					arg.setCartkey(key);
				}
				arg.setIslogin(false);
			}
			
			//rs = cartmanager.accounts(arg);
		}else{
			if(n==-1){		// 购物车中只剩下一个商品了，不能做再减动作
				
			}else if(n==-2){	// 库存不足
				
			}else if(n==-3){	// 该商品正在做单品促销，超过了限制数量
				 
			}
			TCartItem item = tcartitemmanager.selectByPrimaryKey(Long.parseLong(cartid));
			rs.put("cnt", item.getQuantity());
		}
		System.out.println("update n = "+n);
		rs.put("data",n);
		JSONObject json = JSONObject.fromObject(rs);
		out.print(json.toString());
		out.close();
		
	}
	
	public void batchDelete() throws SQLException, IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		String idstr = this.getRequest().getParameter("str");
		List<Long> l = new ArrayList<Long>();
		String[] array = idstr.split(",");
		for(String s : array){
			l.add(Long.parseLong(s));
		}
		int n = cartmanager.batchRemoveGoodsFromCart(l, null);
		out.print(n);
		out.close();
	}
	
	
	
	public void money() throws IOException, SQLException{
		
		PrintWriter out = this.getResponse().getWriter();
//		String key = this.getRequest().getParameter("key");
		String key = cookieManager.getCookieValueByName(this.getRequest(), cartKey);
		System.out.println("money key = "+key);
		CartParam arg = new CartParam();
		arg.setCartkey(key);
		
		TMember user = (TMember) this.getSession().getAttribute("member");
		if(user!=null){
			arg.setUserId(user.getId());
			arg.setIslogin(true);
		}else{
			if("null".equals(key) || key==null){	// 客户端 已经存在一个key
				arg.setCartkey(createKey());
			}else{
				arg.setCartkey(key);
			}
			arg.setIslogin(false);
		}
		
		Map rs = cartmanager.accounts(arg);
		BigDecimal money = (BigDecimal)rs.get("money");
		int count = (Integer)rs.get("count");
		List<CartGift> giftList = (List<CartGift>)rs.get("gift");
		
		for(CartGift gif : giftList ){
			System.out.println("赠品------>："+gif.getGoodsid());
		}
		
//		Map<Long,CartGift> giftMap = (Map<Long, CartGift>) rs.get("gift");	// 赠品 
		
		JSONObject j = JSONObject.fromObject(rs);
		out.print(j.toString());
		out.close();
		
	}

	public ICartManager getCartmanager() {
		return cartmanager;
	}


	public void setCartmanager(ICartManager cartmanager) {
		this.cartmanager = cartmanager;
	}


	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}


	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}


	public TCartItemManager getTcartitemmanager() {
		return tcartitemmanager;
	}


	public void setTcartitemmanager(TCartItemManager tcartitemmanager) {
		this.tcartitemmanager = tcartitemmanager;
	}


	public TMemberCollectManager getTmembercollectmanager() {
		return tmembercollectmanager;
	}


	public void setTmembercollectmanager(TMemberCollectManager tmembercollectmanager) {
		this.tmembercollectmanager = tmembercollectmanager;
	}

	
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub

	}


	public String getBackurl() {
		return backurl;
	}


	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}
	
	
}
