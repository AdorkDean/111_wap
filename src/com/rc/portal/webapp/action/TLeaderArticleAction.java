package com.rc.portal.webapp.action;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderArticleManager;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TLeaderQrCodeManager;
import com.rc.portal.service.TLxGoodsArticleManager;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.service.TMemberBaseMessageExtManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.cookieManager;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TLeaderQrCode;
import com.rc.portal.vo.TLeaderQrCodeExample;
import com.rc.portal.vo.TLxGoodsArticle;
import com.rc.portal.vo.TLxGoodsArticleExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberAccount;
import com.rc.portal.webapp.util.PageResult;

/**
 * 领袖文章Action
 * 
 * @author WWF
 * @createTime 2016-6-3 上午11:13:48
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class TLeaderArticleAction extends BaseAction {

	private static final long serialVersionUID = 7852311684956566992L;

	private TLeaderArticleManager tleaderarticlemanager;
	private TLeaderManager tleadermanager;
	private OpenSqlManage opensqlmanage;
	private TMemberAccountManager tmemberaccountmanager;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	/**
	 * 平台推荐文章
	 */
	private TLxGoodsArticleManager tlxgoodsarticlemanager;
	
   /**
    * 用户基本信息扩展表
    */
	private TMemberBaseMessageExtManager tmemberbasemessageextmanager;
	
	private TLeaderQrCodeManager tleaderqrcodemanager;
	
	//获取系统参数
	private TSysParameterManager tsysparametermanager;
	
	/**
	 * 我的领秀文章列表
	 * 
	 * @throws SQLException
	 */
	public String leaderArticle() throws SQLException {
		TMember tMember = (TMember) getSession().getAttribute("member");
		if (tMember == null) {// 返回登录页
			return "profile_page";
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(tMember.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		this.getRequest().setAttribute("leader", tLeader);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leaderId", tLeader.getId());
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(map, "t_leader_article.selectCountByLeaderMap",
				"t_leader_article.selectListByLeaderMap", rs.getP_curPage(), rs.getP_pageSize());
		TLxGoodsArticleExample articleExample = new TLxGoodsArticleExample();
		articleExample.createCriteria().andStatusEqualTo(1).andIsDeleteEqualTo(0).andWeightEqualTo(999);
		articleExample.setOrderByClause(" id desc");
		List<TLxGoodsArticle> goodsarticleList = tlxgoodsarticlemanager.selectByExampleWithoutBLOBs(articleExample);
		String goodsarticleFlag ="0";
		if(goodsarticleList!=null&&goodsarticleList.size()>0){
			goodsarticleFlag="1";
			this.getRequest().setAttribute("goodsarticleList",goodsarticleList);
		}
		this.getRequest().setAttribute("goodsarticleFlag",goodsarticleFlag);
		
		TMemberAccount tmemberaccount =this.tmemberaccountmanager.selectByPrimaryKey(tMember.getId());
		String accountFlag = "0";// 表示没有金额    1 表示有金额   2 表示可提现金额
		if(tmemberaccount!=null){
			if(tmemberaccount.getRemainingAmount()!=null&&tmemberaccount.getRemainingAmount().compareTo(new BigDecimal("0"))>0){
				accountFlag="2";
			}
		}
		this.getRequest().setAttribute("accountFlag",accountFlag);
		this.getRequest().setAttribute("tmemberaccount", tmemberaccount);
		return "leaderArticle_list";
	}
	/**
	 * 我的领秀文章分页
	 * 
	 * @throws SQLException
	 */
	public void ajaxLeaderArticlePage() throws SQLException {
		TMember tMember = (TMember) getSession().getAttribute("member");
		if (tMember == null) {// 返回登录页
			return ;
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(tMember.getId());
		if (tLeader == null) {// 
			return ;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leaderId", tLeader.getId());
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(map, "t_leader_article.selectCountByLeaderMap",
				"t_leader_article.selectListByLeaderMap", rs.getP_curPage(), rs.getP_pageSize());
		writeObjectToResponse(pw, ContentType.application_json);
	}
	
	
	/**
	 * 我的领袖药房
	 * 
	 * @throws SQLException
	 */
	public String leaderPharmacy() throws SQLException {
		TMember tMember = (TMember) getSession().getAttribute("member");
		String  brokerage= tsysparametermanager.getKeys("fanyong_default_bili");//返佣比例默认4.5%
		if (tMember == null) {// 返回登录页
			return "profile_page";//member/profile.action
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(tMember.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		if(this.getSession().getAttribute("leader_first")!=null){
			this.getSession().removeAttribute("leader_first");
			return "leader_guide";
		}else{
			this.getRequest().setAttribute("leader", tLeader);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("leaderId", tLeader.getId());
			map.put("brokerage", brokerage);
			 rs.setP_pageSize(10);
			// FIXME WWF(根据页面信息联查商品信息)
			pw = opensqlmanage.selectForPageByMap(map, "t_leader_pharmacy.selectCountByLeaderMap",
					"t_leader_pharmacy.selectListByLeaderMap", rs.getP_curPage(), rs.getP_pageSize());
			
			TMemberAccount tmemberaccount =this.tmemberaccountmanager.selectByPrimaryKey(tMember.getId());
			this.getRequest().setAttribute("tmemberaccount", tmemberaccount);
			
			
			List recommendList = opensqlmanage.selectForListByMap(map, "t_leader_pharmacy.t_leader_goods_recommend_listBy2");
			if(recommendList!=null&&recommendList.size()>0){
				this.getRequest().setAttribute("recommendListSize", "1");
				this.getRequest().setAttribute("recommendList", recommendList);
			}else{
				this.getRequest().setAttribute("recommendListSize", "0");
			}
			return "leaderPharmacy_list";
		}
	}
	
	
	/**
	 * 我的领秀药房分页
	 * 
	 * @throws SQLException
	 */
	public void ajaxLeaderPharmacyPage() throws SQLException {
		TMember tMember = (TMember) getSession().getAttribute("member");
		String  brokerage= tsysparametermanager.getKeys("fanyong_default_bili");//返佣比例默认4.5%
		if (tMember == null) {// 返回登录页
			return ;
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(tMember.getId());
		if (tLeader == null) {
			return ;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leaderId", tLeader.getId());
		map.put("brokerage", brokerage);
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(map, "t_leader_pharmacy.selectCountByLeaderMap",
				"t_leader_pharmacy.selectListByLeaderMap", rs.getP_curPage(), rs.getP_pageSize());
		writeObjectToResponse(pw, ContentType.application_json);
	}
	
	
	/**
	 * 领秀药房(通过药房二维码扫描进入的列表),暂时未用到20160613
	 * @return
	 * @throws SQLException
	 */
	public String leaderPharmacyByCode() throws SQLException {
		String leaderCode = this.getRequest().getParameter("code");//领秀编号
		if(leaderCode==null||"".equals(leaderCode)){
			//FIXME ME 
			return "";//跳转公共页
		}
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andCodeEqualTo(leaderCode);
		List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);
		if (list == null || list.size() <= 0) {
			return "";//跳转公共页
		}
		TLeader tLeader = list.get(0);
		this.getRequest().setAttribute("leader", tLeader);
		this.getRequest().setAttribute("code", leaderCode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leaderId", tLeader.getId());
		 rs.setP_pageSize(10);
		// FIXME WWF(根据页面信息联查商品信息)
		pw = opensqlmanage.selectForPageByMap(map, "t_leader_pharmacy.selectCountByLeaderMap",
				"t_leader_pharmacy.selectListByLeaderMap", rs.getP_curPage(), rs.getP_pageSize());
//		return "t_leader_pharmacy_code_list";
		return "t_leader_share_pharmacy";
	}
	/**
	 * 领秀药房分页(通过药房二维码扫描进入的列表),暂时未用到20160613
	 * 
	 * @throws SQLException
	 */
	public void ajaxLeaderPharmacyPageByCode() throws SQLException {
		String leaderCode = this.getRequest().getParameter("code");//领秀编号
		if(leaderCode==null||"".equals(leaderCode)){
			return;
		}
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andCodeEqualTo(leaderCode);
		List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);
		if (list == null || list.size() <= 0) {
			return;
		}
		TLeader tLeader = list.get(0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leaderId", tLeader.getId());
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(map, "t_leader_pharmacy.selectCountByLeaderMap",
				"t_leader_pharmacy.selectListByLeaderMap", rs.getP_curPage(), rs.getP_pageSize());
		writeObjectToResponse(pw, ContentType.application_json);
	}
	
	
	
	

	
	/**
	 * 领秀的粉
	 */
	public String leaderPowderList() throws Exception{
		TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		if (member == null) {// 返回登录页
			return "profile_page";
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(member.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String orderby = this.getRequest().getParameter("orderby");//排序   1 按照下单次数倒序   2 按照累计收益倒序
		if(!StringUtils.hasText(orderby)){
			orderby ="1";
		}
		paramMap.put("orderby",orderby);
		paramMap.put("memberId", member.getId());
		//领秀的粉分页查询
		rs.setP_pageSize(10);
		pw = this.opensqlmanage.selectForPageByMap(paramMap, "t_member.leaderPowderListCountByMemberId", "t_member.leaderPowderListByMemberId", rs.getP_curPage(), rs.getP_pageSize());
		this.getRequest().setAttribute("orderby", orderby);
		this.getRequest().setAttribute("powder_count", pw.getPageInfo().getCount());
		
		return "leader_powder_list";
	}
	
	/**
	 * 领秀的粉下拉刷新调用
	 * @return
	 * @throws Exception
	 */
	public void ajaxLeaderPowderList() throws Exception{
		TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("memberId", member.getId());
		String orderby = this.getRequest().getParameter("orderby");//排序   1 按照下单次数倒序   2 按照累计收益倒序
		if(!StringUtils.hasText(orderby)){
			orderby ="1";
		}
		paramMap.put("orderby",orderby);
		//领秀的粉分页查询
		pw = this.opensqlmanage.selectForPageByMap(paramMap, "t_member.leaderPowderListCountByMemberId", "t_member.leaderPowderListByMemberId", rs.getP_curPage(), rs.getP_pageSize());
		this.writeObjectToResponse(pw, ContentType.application_json);
	}
	
	
	/**
	 * 我的收益
	 * @return
	 * @throws Exception
	 */
	public String leaderProfit() throws Exception{
		TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		if (member == null) {// 返回登录页
			return "profile_page";
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(member.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		TMemberAccount tmemberaccount =this.tmemberaccountmanager.selectByPrimaryKey(member.getId());
		this.getRequest().setAttribute("tmemberaccount", tmemberaccount);
		//近3个月的收益统计
		List<Map<String,Object>> dateList =getDateList();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("memberId", member.getId());
		List<Map<String,Object>>  stayMoneyList =this.opensqlmanage.selectForListByMap(paramMap, "t_leader_stay_money.select_leader_three_money");
		
		List<Map<String,Object>> dateAmountList  = new ArrayList<Map<String,Object>>(); 
		Map<String,Object> dateAmountMap = null;
		if(stayMoneyList!=null){
			for(Map<String,Object> dateMap:dateList){
				String date_str= (String) dateMap.get("date_str");
				for(Map<String,Object> stayMoney:stayMoneyList){
					if(stayMoney.get("riqi").equals(date_str)){
						dateMap.put("amount", stayMoney.get("amount"));
					}
				}
				dateAmountMap = new HashMap<String,Object>();
				dateAmountMap.put("date_str", date_str);
				dateAmountMap.put("amount_new", dateMap.get("amount"));
				dateAmountList.add(dateAmountMap);
			}
		}
		this.getRequest().setAttribute("dateAmountList", dateAmountList);
		return "runningWater";
	}
	
	/**
	 * 根据日期查询 领秀的收益
	 * @throws Exception
	 */
	public void ajaxLeaderDateProfit() throws Exception{
         TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		
     	Map<String,Object> paramMap = new HashMap<String,Object>();
 		paramMap.put("memberId", member.getId());
 		String date_str = this.getRequest().getParameter("date_str");
 		if(StringUtils.hasText(date_str)){
 			paramMap.put("date_str", date_str);
 		}
 		rs.setP_pageSize(10);
 		//领秀的粉分页查询
 		pw = this.opensqlmanage.selectForPageByMap(paramMap, "t_leader_stay_money.select_leader_money_bydateCount", "t_leader_stay_money.select_leader_money_bydateList", rs.getP_curPage(), rs.getP_pageSize());
         
        List<Map<String,Object>> ajaxList = new ArrayList<Map<String,Object>>();
		
		if(pw.getResult()!=null){
			ajaxList = pw.getResult();
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(pw.getPageInfo().getPage() < pw.getPageInfo().getPages()){
			resultMap.put("isNextPage", "1");
		}else{
			resultMap.put("isNextPage", "0");
		}
		resultMap.put("ajaxList", ajaxList);
		this.writeObjectToResponse(resultMap, ContentType.application_json);
		
	}
	
	
	
	
	
	public List<Map<String,Object>> getDateList(){
		List<Map<String,Object>> dateList = new ArrayList<Map<String,Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		 Calendar cal = Calendar.getInstance();
         cal.setTime(new Date());
         Map<String,Object> dateMap = null;
         dateMap = new HashMap<String,Object>();
         dateMap.put("date_str", sdf.format(cal.getTime()));
         dateMap.put("amount", new BigDecimal("0"));
         dateList.add(dateMap);
         for (int i = 0; i < 2; i++) {
        	 dateMap = new HashMap<String,Object>();
        	 cal.add(Calendar.MONTH, -1);
        	 dateMap.put("date_str", sdf.format(cal.getTime()));
        	 dateMap.put("amount", new BigDecimal("0"));
        	 dateList.add(dateMap);
		}
		return dateList;
	}
	
	
	
	
	/**
	 * 领袖图片素材
	 * @return
	 * @throws SQLException
	 */
	public String leaderImg() throws SQLException{
		 TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		if (member == null) {// 返回登录页
			return "profile_page";
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(member.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		Map<String,Object> querMap = new HashMap<String,Object>();
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(querMap, "c_image.countImages", "c_image.selectImages", rs.getP_curPage(), rs.getP_pageSize());
		return "leaderImg";
	}
	public void leaderImgList() throws SQLException{
		String pageNo = getRequest().getParameter("pageNo");
		Map<String,Object> querMap = new HashMap<String,Object>();
		rs.setP_curPage(Integer.parseInt(pageNo)); 
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(querMap, "c_image.countImages", "c_image.selectImages", rs.getP_curPage(), rs.getP_pageSize());
		querMap.clear();
		querMap.put("list", pw.getResult());
		querMap.put("pageNo", pageNo);
		this.writeObjectToResponse(querMap, ContentType.application_json);
	}
	/**
	 * 领袖内容素材
	 * @return
	 * @throws SQLException
	 */
	public String leaderContent() throws SQLException{
		 TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		if (member == null) {// 返回登录页
			return "profile_page";
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(member.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		Map<String,Object> querMap = new HashMap<String,Object>();
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_lx_content.countContent", "t_lx_content.selectContent", rs.getP_curPage(), rs.getP_pageSize());
		return "leaderContent";
	}
	public void leaderContentList() throws SQLException{
		String pageNo = getRequest().getParameter("pageNo");
		Map<String,Object> querMap = new HashMap<String,Object>();
		rs.setP_curPage(Integer.parseInt(pageNo)); 
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_lx_content.countContent", "t_lx_content.selectContent", rs.getP_curPage(), rs.getP_pageSize());
		querMap.clear();
		querMap.put("list", pw.getResult());
		querMap.put("pageNo", pageNo);
		this.writeObjectToResponse(querMap, ContentType.application_json);
	}
	
	
	
	/**
	 * 领秀分享药房 (通过领袖药房二维码扫描跳转过来)
	 * @return
	 * @throws Exception
	 */
	public String leaderSharePharmacy() throws Exception{
		String leaderCode = this.getRequest().getParameter("leaderCode");//领秀code
		if(StringUtils.hasText(leaderCode)){
			TLeaderExample leaderExample = new TLeaderExample();
			leaderExample.createCriteria().andCodeEqualTo(leaderCode.trim());
			List<TLeader> leaderList =this.tleadermanager.selectByExample(leaderExample);
			if(leaderList!=null&&leaderList.size()>0){
				this.getRequest().setAttribute("leaderId", leaderList.get(0).getId());
//				TMemberBaseMessageExt memberBase =this.tmemberbasemessageextmanager.selectByPrimaryKey(leaderList.get(0).getMemberId());
				this.getRequest().setAttribute("nickName", leaderList.get(0).getNickName());
				this.getRequest().setAttribute("headPortrait", leaderList.get(0).getHeadUrl());
//				if(memberBase!=null){
//					this.getRequest().setAttribute("nickName", memberBase.getNickName());
//					this.getRequest().setAttribute("headPortrait", memberBase.getHeadPortrait());
//				}else{
//					
//				}
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("leaderId", leaderList.get(0).getId());
				
				String orderby = this.getRequest().getParameter("orderby");// 排序 1 综合相关  2 折扣优先  3 价格优先
				if(!StringUtils.hasText(orderby)){
					orderby ="1";
				}
				paramMap.put("orderby", orderby.trim());
				this.getRequest().setAttribute("orderby",orderby.trim());
				rs.setP_pageSize(10);
				this.getRequest().setAttribute("leaderCode", leaderCode);
				pw = opensqlmanage.selectForPageByMap(paramMap, "t_leader_pharmacy.selectCountLeaderSharePharmacyMap", "t_leader_pharmacy.selectLeaderSharePharmacyMap", rs.getP_curPage(), rs.getP_pageSize());
				//存领秀的code
				cookieManager.addCookie(this.getRequest(), this.getResponse(), "leader", leaderCode.trim(), 100000,".111yao.com");
				
				TLeaderQrCodeExample tLeaderQrCodeExample = new TLeaderQrCodeExample();
				tLeaderQrCodeExample.createCriteria().andLeaderIdEqualTo(leaderList.get(0).getId()).andTypeEqualTo(2);
				List<TLeaderQrCode> qrList = tleaderqrcodemanager.selectByExample(tLeaderQrCodeExample);
				if(qrList!=null&&qrList.size()>0){
					this.getRequest().setAttribute("haibaoImg",qrList.get(0).getImgUrl());
					System.out.println(qrList.get(0).getImgUrl());
				}
				
				return "leader_share_pharmacy_list";
			}else{
				return null;
			}
		}
		return null;
	}
	/**
	 * 领秀分享药房 下拉加载
	 * @return
	 * @throws Exception
	 */
	public void ajaxLeaderSharePharmacy() throws Exception{
		String leaderId = this.getRequest().getParameter("leaderId");//领秀Id
		if(StringUtils.hasText(leaderId)){
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("leaderId", Long.valueOf(leaderId.trim()));
				String orderby = this.getRequest().getParameter("orderby");// 排序 1 综合相关  2 折扣优先  3 价格优先
				if(!StringUtils.hasText(orderby)){
					orderby ="1";
				}
				paramMap.put("orderby", orderby.trim());
				pw = opensqlmanage.selectForPageByMap(paramMap, "t_leader_pharmacy.selectCountLeaderSharePharmacyMap", "t_leader_pharmacy.selectLeaderSharePharmacyMap", rs.getP_curPage(), rs.getP_pageSize());
				this.writeObjectToResponse(pw, ContentType.application_json);
		}
	}
	
	/**
	 * 平台推荐文章
	 * @return
	 * @throws Exception
	 */
	public String recommendArticle() throws Exception{
		TMember tMember = (TMember) getSession().getAttribute("member");
		if (tMember == null) {// 返回登录页
			return "profile_page";//member/profile.action
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(tMember.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		this.getRequest().setAttribute("leader", tLeader);
		
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(new HashMap<String,Object>(), "t_lx_goods_article.select_recommendArticle_count","t_lx_goods_article.select_recommendArticle_list", rs.getP_curPage(), rs.getP_pageSize());
		return "recommend_article_list";
	}
	
	/**
	 * 平台推荐文章 下拉刷新
	 * @return
	 * @throws Exception
	 */
	public void ajaxRecommendArticle() throws Exception{
		pw = opensqlmanage.selectForPageByMap(new HashMap<String,Object>(), "t_lx_goods_article.select_recommendArticle_count","t_lx_goods_article.select_recommendArticle_list", rs.getP_curPage(), rs.getP_pageSize());
		this.writeObjectToResponse(pw, ContentType.application_json);
	}
	
	/**查看文章**/
	public String publish() throws SQLException
	{
		String ratio = "4.5";
		String str = tsysparametermanager.getKeys("fanyong_default_bili");
		if(StringUtils.hasText(str))
		{
			ratio = str;
		}
		String type = getRequest().getParameter("type");
		Long articleId = Long.parseLong(getRequest().getParameter("articleId"));
		TLxGoodsArticle tla = tlxgoodsarticlemanager.selectByPrimaryKey(articleId);
		getRequest().setAttribute("tla", tla);
		getRequest().setAttribute("acontent", new String(tla.getContent()));
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("ratio", ratio);
		p.put("articleid", articleId);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(p, "t_lx_goods_article.selecctTuiJianGoodsByArticleId");
		getRequest().setAttribute("datas", datas);
		return "view"+type;
	}
	
	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {

	}

	public TLeaderArticleManager getTleaderarticlemanager() {
		return tleaderarticlemanager;
	}

	public void setTleaderarticlemanager(TLeaderArticleManager tleaderarticlemanager) {
		this.tleaderarticlemanager = tleaderarticlemanager;
	}

	public TLeaderManager getTleadermanager() {
		return tleadermanager;
	}

	public void setTleadermanager(TLeaderManager tleadermanager) {
		this.tleadermanager = tleadermanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
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


	public TMemberAccountManager getTmemberaccountmanager() {
		return tmemberaccountmanager;
	}


	public void setTmemberaccountmanager(TMemberAccountManager tmemberaccountmanager) {
		this.tmemberaccountmanager = tmemberaccountmanager;
	}
	public TLxGoodsArticleManager getTlxgoodsarticlemanager() {
		return tlxgoodsarticlemanager;
	}
	public void setTlxgoodsarticlemanager(
			TLxGoodsArticleManager tlxgoodsarticlemanager) {
		this.tlxgoodsarticlemanager = tlxgoodsarticlemanager;
	}
	public TMemberBaseMessageExtManager getTmemberbasemessageextmanager() {
		return tmemberbasemessageextmanager;
	}
	public void setTmemberbasemessageextmanager(
			TMemberBaseMessageExtManager tmemberbasemessageextmanager) {
		this.tmemberbasemessageextmanager = tmemberbasemessageextmanager;
	}
	public TLeaderQrCodeManager getTleaderqrcodemanager() {
		return tleaderqrcodemanager;
	}
	public void setTleaderqrcodemanager(TLeaderQrCodeManager tleaderqrcodemanager) {
		this.tleaderqrcodemanager = tleaderqrcodemanager;
	}
	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}
	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

}
