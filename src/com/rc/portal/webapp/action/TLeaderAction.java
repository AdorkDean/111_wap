package com.rc.portal.webapp.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.InfoUtil;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.portal.memcache.MemCached;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TLeaderAccountNumberManager;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TLeaderPharmacyManager;
import com.rc.portal.service.TLeaderQrCodeManager;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.service.TMemberAmountOutManager;
import com.rc.portal.service.TMemberLeaderManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.CodeUtil;
import com.rc.portal.util.cookieManager;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderAccountNumber;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TLeaderPharmacy;
import com.rc.portal.vo.TLeaderQrCode;
import com.rc.portal.vo.TLeaderQrCodeExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberAccount;
import com.rc.portal.vo.TMemberAmountOut;
import com.rc.portal.vo.TMemberLeaderExample;
import com.rc.portal.webapp.util.PageResult;

/**
 * 新版健康领秀Action
 * 
 * @author WWF
 * @createTime 2016-6-3 下午9:00:42
 */
public class TLeaderAction extends BaseAction {

	private static final long serialVersionUID = 4547545603139602241L;
	private TMemberAccountManager tmemberaccountmanager;
	private TLeaderManager tleadermanager;
	private OpenSqlManage opensqlmanage;
	
	private TMemberLeaderManager tmemberleadermanager;
	
	private TGoodsManager tgoodsmanager;
	
	private TLeaderQrCodeManager tleaderqrcodemanager;
	
	private String code;
	private final String sharedLeaderUrl = InfoUtil.getInstance().getInfo("config", "shareLeaderUrl");
	private final String leaderUrl = InfoUtil.getInstance().getInfo("config", "leaderUrl");
	private final String YAOFANGQRURL = InfoUtil.getInstance().getInfo("healthleader",
			"healthleader.yaofang.haibao.url");
	private TSysParameterManager tsysparametermanager;
	
	private TLeaderPharmacyManager tleaderpharmacymanager;
	
	private TLeaderAccountNumber tleaderAccountNumber;
	
	private TMemberAmountOut amountOut;
	
	private TLeaderAccountNumberManager tleaderaccountnumbermanager;
	
	private TMemberAmountOutManager tmemberamountoutmanager;
	
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	
	private String url;
	
	
	@SuppressWarnings("rawtypes")
	private List result=new ArrayList();
	
	
	public TMemberAmountOutManager getTmemberamountoutmanager() {
		return tmemberamountoutmanager;
	}

	public void setTmemberamountoutmanager(
			TMemberAmountOutManager tmemberamountoutmanager) {
		this.tmemberamountoutmanager = tmemberamountoutmanager;
	}

	public TMemberAmountOut getAmountOut() {
		return amountOut;
	}

	public void setAmountOut(TMemberAmountOut amountOut) {
		this.amountOut = amountOut;
	}

	public TLeaderAccountNumber getTleaderAccountNumber() {
		return tleaderAccountNumber;
	}

	public void setTleaderAccountNumber(TLeaderAccountNumber tleaderAccountNumber) {
		this.tleaderAccountNumber = tleaderAccountNumber;
	}

	public TLeaderAccountNumberManager getTleaderaccountnumbermanager() {
		return tleaderaccountnumbermanager;
	}

	public void setTleaderaccountnumbermanager(
			TLeaderAccountNumberManager tleaderaccountnumbermanager) {
		this.tleaderaccountnumbermanager = tleaderaccountnumbermanager;
	}

	@SuppressWarnings("rawtypes")
	public List getResult() {
		return result;
	}

	@SuppressWarnings("rawtypes")
	public void setResult(List result) {
		this.result = result;
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

	public TLeaderPharmacyManager getTleaderpharmacymanager() {
		return tleaderpharmacymanager;
	}

	public void setTleaderpharmacymanager(
			TLeaderPharmacyManager tleaderpharmacymanager) {
		this.tleaderpharmacymanager = tleaderpharmacymanager;
	}

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}

	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
	}

	public TMemberLeaderManager getTmemberleadermanager() {
		return tmemberleadermanager;
	}

	public void setTmemberleadermanager(TMemberLeaderManager tmemberleadermanager) {
		this.tmemberleadermanager = tmemberleadermanager;
	}

	public TMemberAccountManager getTmemberaccountmanager() {
		return tmemberaccountmanager;
	}

	public void setTmemberaccountmanager(TMemberAccountManager tmemberaccountmanager) {
		this.tmemberaccountmanager = tmemberaccountmanager;
	}


	/**
	 * 管理页面
	 * @throws SQLException 
	 */
	public String index() throws SQLException {
		//领秀头像,名称,秀粉个数,我的收益;
		TMember member = (TMember)this.getSession().getAttribute("member");
		Long memberId = member.getId();
		TLeader leader = tleadermanager.selectLeaderByMemberId(memberId);
		
		TMemberAccount tMemberAccount = tmemberaccountmanager.selectByPrimaryKey(memberId);
		
		BigDecimal incomeAmount = getIncomeAmount(tMemberAccount);
		
		
		TMemberLeaderExample tMemberLeaderExample = new TMemberLeaderExample();
		tMemberLeaderExample.createCriteria().andLeaderIdEqualTo(leader.getId());
		int fanCount = tmemberleadermanager.countByExample(tMemberLeaderExample);
		
		
		getRequest().setAttribute("leader", leader);
		getRequest().setAttribute("incomeAmount", incomeAmount);
		getRequest().setAttribute("fanCount", fanCount);
		return "leadermanagecenter";
	}
	
	public void getGoodAndLeaderDetail() throws NumberFormatException, SQLException{
		String goodId = getRequest().getParameter("goodId");
		String leaderId = getRequest().getParameter("leaderId");
		
		TLeader leader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
		TGoods good = tgoodsmanager.selectByPrimaryKey(Long.valueOf(goodId));
		
		Map goodAndLeaderMap = new HashMap();
		goodAndLeaderMap.put("good", good);
		goodAndLeaderMap.put("leader", leader);
		writeObjectToResponse(goodAndLeaderMap, ContentType.application_json);
		
	}
	
	
	public BigDecimal getIncomeAmount(TMemberAccount tMemberAccount){
		BigDecimal remainAmount = tMemberAccount.getRemainingAmount()==null?new BigDecimal(0):tMemberAccount.getRemainingAmount();
		
		BigDecimal waitAmount = tMemberAccount.getWaitAmount()==null?new BigDecimal(0):tMemberAccount.getWaitAmount();
		
		return remainAmount.add(waitAmount);
		
	}
	
	/**
	 * 分享领袖
	 * @return
	 * @throws SQLException 
	 */
	public String shareLeader() throws SQLException{
		String code = this.getRequest().getParameter("code"); //获取领袖编码
		String hurl = this.getRequest().getParameter("hurl"); //获取跳转地址
		if(null != code && !("").equals(code) && null != hurl && !("").equals(hurl)){
			TLeaderExample te = new TLeaderExample();
			te.createCriteria().andCodeEqualTo(code);
			List<TLeader> list = tleadermanager.selectByExample(te);
			if(null != list && list.size()==1){
				cookieManager.addCookie(this.getRequest(), this.getResponse(), "leader", code, 100000,".111yao.com");
			    url = hurl;
			}else{
				url="/";
			}
		}else{
			url="/";
			this.getRequest().setAttribute("url", url);
		}
		return "url";
	}
	
	
	/**
	 * 我的领秀海报页面;
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String myPosterPage() throws SQLException, FileNotFoundException, IOException{
		TMember tMember = (TMember) getSession().getAttribute("member");
		if (tMember == null) {// 返回登录页
			return "profile_page";//member/profile.action
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(tMember.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		TLeaderQrCodeExample tLeaderQrCodeExample = new TLeaderQrCodeExample();
		tLeaderQrCodeExample.createCriteria().andLeaderIdEqualTo(tLeader.getId()).andTypeEqualTo(1);
		List<TLeaderQrCode> list = tleaderqrcodemanager.selectByExample(tLeaderQrCodeExample);
		if(list!=null && list.size()>=1){
			this.getRequest().setAttribute("tLeaderQrCode", list.get(0));
		}else{
			//生成领袖海报;(新需求-20160707/WWF)
			String qianzhui = this.getRequest().getSession().getServletContext().getRealPath("/");
			Long qrId = tleaderqrcodemanager.createLeaderPoster(tMember,tLeader,qianzhui);
		}
		code = tLeader.getCode();
		String tiaozhuan = sharedLeaderUrl + "?code=" + code + "&hurl=" + leaderUrl;
		this.getRequest().setAttribute("tiaozhuan", tiaozhuan);
		this.getRequest().setAttribute("tLeader", tLeader);
		this.getRequest().setAttribute("code", code);
		return "my_poster_page";
	}
	/**
	 * 我的药房海报页面;(只局限于生成药房海报)
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public String myPharmacyPage() throws SQLException, IOException{
		TMember tMember = (TMember) getSession().getAttribute("member");
		if (tMember == null) {// 返回登录页
			return "profile_page";//member/profile.action
		}
		TLeader tLeader = tleadermanager.selectLeaderByMemberId(tMember.getId());
		if (tLeader == null || tLeader.getId() == null) {// 跳转申请页面
			return "apply_health_leader";
		}
		//查询是否存在领秀药房海报;么有的话生成入库
		TLeaderQrCodeExample tLeaderQrCodeExample = new TLeaderQrCodeExample();
		tLeaderQrCodeExample.createCriteria().andLeaderIdEqualTo(tLeader.getId()).andTypeEqualTo(2);
		List<TLeaderQrCode> ll = tleaderqrcodemanager.selectByExample(tLeaderQrCodeExample);
		
		TLeaderQrCode qrCode = null;
		if(ll==null||ll.size()<=0){
			String qianzhui = this.getRequest().getSession().getServletContext().getRealPath("/");
			Long qrId = tleaderqrcodemanager.createPharmacyPoster(tMember,tLeader,qianzhui);
			qrCode = tleaderqrcodemanager.selectByPrimaryKey(qrId);
		}else{
			qrCode = ll.get(0);
		}
		code = tLeader.getCode();
		this.getRequest().setAttribute("tLeaderQrCode", qrCode);
		this.getRequest().setAttribute("tLeader", tLeader);
		this.getRequest().setAttribute("code", code);
		
		
		return "my_pharmacy_page";
	}
	
	/**
	 * 跳转海报也
	 * @return
	 * @throws SQLException 
	 */
	public String toHaibaoPage() throws SQLException{
		String flag = ""; 
		TMember m = (TMember) getSession().getAttribute("member");
		if (m != null) {// 返回登录页
			TLeader l = tleadermanager.selectLeaderByMemberId(m.getId());
			if (l != null && l.getId() != null) {// 跳转申请页面
				flag = "1";
			}
		}
		String type = this.getRequest().getParameter("type");//类型:1:领秀海报;2:领袖药房海报
		String code = this.getRequest().getParameter("code");
		if(code==null||"".equals(code)){
			code = "201510111737583758553870";
		}
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andCodeEqualTo(code);
		TLeader leader = null;
		String url = "";
		List<TLeader> ll = tleadermanager.selectByExample(tLeaderExample);
		if(ll!=null&&ll.size()>0){
			leader = ll.get(0);
		}
		if(leader!=null && type!=null&&"2".equals(type)){//2:领袖药房海报
			TLeaderQrCodeExample tLeaderQrCodeExample = new TLeaderQrCodeExample();
			tLeaderQrCodeExample.createCriteria().andLeaderIdEqualTo(leader.getId()).andTypeEqualTo(2);
			List<TLeaderQrCode> qrList = tleaderqrcodemanager.selectByExample(tLeaderQrCodeExample);
			if(qrList!=null&&qrList.size()>0){
				url = qrList.get(0).getImgUrl();
			}
			System.out.println("药房海报:\t"+url);
		}else if(leader!=null && type!=null&&"1".equals(type)){//1:领秀海报;
			TLeaderQrCodeExample tLeaderQrCodeExample = new TLeaderQrCodeExample();
			tLeaderQrCodeExample.createCriteria().andLeaderIdEqualTo(leader.getId()).andTypeEqualTo(1);
			List<TLeaderQrCode> qrList = tleaderqrcodemanager.selectByExample(tLeaderQrCodeExample);
			if(qrList!=null&&qrList.size()>0){
				url = qrList.get(0).getImgUrl();
			}else{
				url = "/static/image/haibao/"+leader.getCode()+"_"+leader.getId()+".jpg";
			}
			System.out.println("领袖海报url:\t\t"+url+"("+((qrList!=null&&qrList.size()>0)?"重新生成的":"规则地址图片")+")");
		}
		if(url==null||"".equals(url.trim())){
			System.out.println("url为空:");
			url="/static/image/attention-code.png";
		}
		String tiaozhuan = sharedLeaderUrl + "?code=" + code + "&hurl=" + leaderUrl;
		
		this.getRequest().setAttribute("tiaozhuan", tiaozhuan);
		this.getRequest().setAttribute("url", url);
		this.getRequest().setAttribute("flag", flag);
		if(type!=null&&"2".equals(type)){//跳转药房海报
			String moreUrl = YAOFANGQRURL+"?leaderCode="+code;
			System.out.println(moreUrl);
			this.getRequest().setAttribute("moreUrl", moreUrl);
			return "go_pharmacy_page";
		}else{//跳到领袖海报
			return "go_poster_page";
		}
	}
	/*
	 * 领秀药房商品列表
	 */
	public String leaderPharmacyGoodsList() throws Exception{
		String name = getRequest().getParameter("name");
		Map<String,Object> querMap = new HashMap<String,Object>();
		String  brokerage= tsysparametermanager.getKeys("fanyong_default_bili");//返佣比例默认4.5%
		querMap.put("brokerage", brokerage);
		if(null != name && !("").equals(name)&&!name.equals("请输入搜索的内容..."))
		{
			querMap.put("name", "%"+name+"%");
			this.getRequest().setAttribute("name", name);
		}
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_goods_brokerage.selectLeaderGoodsListCount", "t_goods_brokerage.selectLeaderGoodsList", rs.getP_curPage(), rs.getP_pageSize());
		return "leaderPharmacyGoodsList";
	}
	
	/**
	 * 领秀药房商品列表分页
	 * 
	 * @throws SQLException
	 */
	public void ajaxLeaderPharmacyGoodsPage() throws SQLException {
		String name = getRequest().getParameter("name");
		String  brokerage= tsysparametermanager.getKeys("fanyong_default_bili");//返佣比例默认4.5%
		Map<String,Object> querMap = new HashMap<String,Object>();
		querMap.put("brokerage", brokerage);
		if(null != name && !("").equals(name)&&!name.equals("请输入搜索的内容..."))
		{
			querMap.put("name", "%"+name+"%");
			this.getRequest().setAttribute("name", name);
		}
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_goods_brokerage.selectLeaderGoodsListCount",
				"t_goods_brokerage.selectLeaderGoodsList", rs.getP_curPage(), rs.getP_pageSize());
		writeObjectToResponse(pw, ContentType.application_json);
	}
	
	/**
	 *  领秀药房添加商品
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void leaderPharmacyAddGoods() throws Exception{
		int flag=-1;
		String  leader_goods_size= tsysparametermanager.getKeys("leader_goods_size");//领秀商品数量
		this.getRequest().setAttribute("leader_goods_size", leader_goods_size);
		PrintWriter out =  getResponse().getWriter();
		TMember member =(TMember)this.getSession().getAttribute("member");//这里获取登陆的用户id
		String goodsId = this.getRequest().getParameter("goodsId");//商品id
		//获取领袖ID
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(member.getId());
		List<TLeader> lists = tleadermanager.selectByExample(tle);
		Long leaderId = lists.get(0).getId();
		
		Map<String,Object> querMap = new HashMap<String,Object>();
		querMap.put("leaderId", leaderId);
		querMap.put("goodsId", goodsId);
		Map<String,Object> limitquerMap = new HashMap<String,Object>();
		limitquerMap.put("leaderId", leaderId);
		limitquerMap.put("memberId", member.getId());
		int goodsCount = (Integer) opensqlmanage.selectObjectByObject(querMap, "t_leader_pharmacy.select_leader_pharmacy_goods_count");
		int leadergoodsCount = (Integer) opensqlmanage.selectObjectByObject(limitquerMap, "t_leader_pharmacy.select_leader_pharmacy_limit_goods_count");
		try{
			if(leadergoodsCount>=Integer.parseInt(leader_goods_size)){
				flag=Integer.parseInt(leader_goods_size);//超过添加商品数量
			}else{
				if(StringUtils.hasText(goodsId) && goodsCount==0){
					TLeaderExample leaderExample = new TLeaderExample();
					leaderExample.createCriteria().andMemberIdEqualTo(member.getId());
					List<TLeader> leaderList =this.tleadermanager.selectByExample(leaderExample);
					if(leaderList!=null&&leaderList.size()>0){
						TLeaderPharmacy leaderPharmacy = new TLeaderPharmacy();
						leaderPharmacy.setMemberId(member.getId());
						leaderPharmacy.setGoodsId(Long.valueOf(goodsId.trim()));
						leaderPharmacy.setLeaderId(leaderList.get(0).getId());
						this.tleaderpharmacymanager.insertSelective(leaderPharmacy);
						flag=1;//添加成功
					}
				}else{
					flag=2;//已添加过此商品
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		out.print(flag);
		out.close();
	}
	/**
	 * 删除我的药房里面的商品
	 */
	public void delPharmacyGoods() throws Exception
	{
		int rs = 0;
		String pharmacy_goods_id_str = getRequest().getParameter("pharmacy_goods_id");
		if(StringUtils.hasText(pharmacy_goods_id_str))
		{
			Long pharmacy_goods_id = Long.parseLong(pharmacy_goods_id_str);
			int resutl = tleaderpharmacymanager.deleteByPrimaryKey(pharmacy_goods_id);
			if(resutl > 0)
			{
				rs = 1;
			}
		}
		writeObjectToResponse(rs, ContentType.application_json);
	}
	/*
	 * 账户管理
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String accountManage() throws Exception{
		
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		
		Map<String, Object> _map = new HashMap<String, Object>();
		_map.put("memberId", sessionMember.getId());
		Object leaderAmountList = opensqlmanage.selectListByObject(_map, "t_leader_account_number.selectLeaderAccountNumberByMemberId");
	    result.add(leaderAmountList);
	    System.out.println(result.size());
		return "accountManage";
	}
	/*
	 * 选择提现帐户
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String checkDrawingAccountIndex() throws Exception{
		
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		
		Map<String, Object> _map = new HashMap<String, Object>();
		_map.put("memberId", sessionMember.getId());
		Object leaderAmountList = opensqlmanage.selectListByObject(_map, "t_leader_account_number.selectLeaderAccountNumberByMemberId");
	    result.add(leaderAmountList);
	    System.out.println(result.size());
		return "checkDrawingAccountIndex";
	}
	
	/*
	 * 提取收益页
	 */
	public String drawingProceedsIndex() throws Exception{
		String accountId = this.getRequest().getParameter("accountId");//账号id
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		
		TMemberAccount account = tmemberaccountmanager.selectByPrimaryKey(sessionMember.getId());
		this.getRequest().setAttribute("account", account);
		
		TLeaderAccountNumber leaderAccountNumber = tleaderaccountnumbermanager.selectByPrimaryKey(Long.valueOf(accountId));
		this.getRequest().setAttribute("leaderAccountNumber", leaderAccountNumber);
		
		return "drawingProceedsIndex";
	}
	
	/*
	 * 提取收益
	 */
	public void drawingProceeds() throws Exception{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		String tqAmount = this.getRequest().getParameter("tqAmount");
		String numberAmountId = this.getRequest().getParameter("numberAmountId");
		PrintWriter out = this.getResponse().getWriter();
		int flag=-1;
		
		amountOut=new TMemberAmountOut();
		if(numberAmountId!=null && !"".equals(numberAmountId)){
			TLeaderAccountNumber leaderAccountNumber = this.tleaderaccountnumbermanager.selectByPrimaryKey(Long.valueOf(numberAmountId));
			if(leaderAccountNumber!=null){
				amountOut.setMemberId(sessionMember.getId());
				amountOut.setOutType(leaderAccountNumber.getNumberType());
				amountOut.setBankNo(leaderAccountNumber.getAccountNumber());
				amountOut.setAlipayNo(leaderAccountNumber.getAccountNumber());
				amountOut.setBankName(leaderAccountNumber.getBankName());
				amountOut.setRealName(leaderAccountNumber.getAccountRealName());
				BigDecimal bd=new BigDecimal(tqAmount);
				amountOut.setAmount(bd);
				amountOut.setSerialNumber(String.valueOf(System.currentTimeMillis()));
				tmemberamountoutmanager.insertAmountOut(amountOut);
				flag=0;
			}
		}
		out.print(flag);
		out.close();
	}
	/*
	 * 判断是否有绑定账号
	 */
	@SuppressWarnings("rawtypes")
	public void isBindingAmount() throws Exception{
		int flag=-1;
		PrintWriter out = this.getResponse().getWriter();
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		Map<String, Object> _map = new HashMap<String, Object>();
		_map.put("memberId", sessionMember.getId());
		List leaderAmountList = (List) opensqlmanage.selectListByObject(_map, "t_leader_account_number.selectLeaderAccountNumberByMemberId");
		if(leaderAmountList.size()>0){
			flag=1;//转到提现页
		}else{
			flag=2;//转到绑定帐号页
		}
		out.print(flag);
		out.close();
	}	
	/*
	 * 转到第一次绑定账户页面
	 */
	public String checkBindingAccount(){
		
		return "checkBindingAccount";
	}
	/*
	 * 转到新增绑定账号页面
	 * 
	 */
	public String checkDrawingMethod() throws Exception{
		
		return "checkDrawingMethodIndex";
	}
	/*
	 * 判断绑定哪个账号
	 */
	public void bindingAmountIndex() throws Exception{
		int flag=-1;
		String numberType = this.getRequest().getParameter("numberType");//绑定账号类型
		PrintWriter out = this.getResponse().getWriter();
		if(numberType.equals("1")){
			flag=1;//转到支付宝账号绑定页
		}else{
			flag=2;//转到银行卡账户账号绑定页
		}
		out.print(flag);
		out.close();
	}	
	/*
	 * 转到绑定银行卡账号页面
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String bindingYhkAmountIndex() throws Exception{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		//获取领袖
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		List<TLeader> lists = tleadermanager.selectByExample(tle);
		TLeader tleader = lists.get(0);
		this.getRequest().setAttribute("mobile", tleader.getPhone());
		return "bindingYhkAmountIndex";
	}
	/*
	 * 转到绑定支付宝账号页面
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String bindingZfbAmountIndex() throws Exception{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		//获取领袖
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		List<TLeader> lists = tleadermanager.selectByExample(tle);
		TLeader tleader = lists.get(0);
		this.getRequest().setAttribute("mobile", tleader.getPhone());
		return "bindingZfbAmountIndex";
	}
	/*
	 * 绑定帐号
	 */
	
	@SuppressWarnings("unchecked")
	public void bindingAmount() throws Exception{
		int flag=-1;
		PrintWriter out = this.getResponse().getWriter();
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		List<TLeader> lists = tleadermanager.selectByExample(tle);
		TLeader tleader = lists.get(0);
		String numberType = this.getRequest().getParameter("numberType");//账号类型  1 支付宝 2银行卡
		String numberAmount = this.getRequest().getParameter("numberAmount");//帐号
		String bankName = this.getRequest().getParameter("bankName");//银行名称
		String accountRealName = this.getRequest().getParameter("accountRealName");//持卡人姓名
		String mobileCode =	this.getRequest().getParameter("mobileCode");//手机验证码
		String amoblieCode = (String) MemCached.getmcc().get(tleader.getPhone());
		
		Map<String, Object> _map = new HashMap<String, Object>();
		_map.put("memberId", sessionMember.getId());
		Long leaderId = (Long) opensqlmanage.selectForObjectByMap(_map, "t_leader.selectLeaderIdByMemberId");
		
		if(mobileCode!=null && !"".equals(mobileCode) && mobileCode.equals(amoblieCode)){
			if(numberType.equals("1")){
				tleaderAccountNumber=new TLeaderAccountNumber();
				tleaderAccountNumber.setLeaderId(leaderId);
				tleaderAccountNumber.setMemberId(sessionMember.getId());
				tleaderAccountNumber.setAccountNumber(numberAmount);
				tleaderAccountNumber.setCreateDt(new Date());
				tleaderAccountNumber.setNumberType(1);
				tleaderaccountnumbermanager.insertSelective(tleaderAccountNumber);
				flag=0;//绑定成功
			}else{
				tleaderAccountNumber=new TLeaderAccountNumber();
				tleaderAccountNumber.setLeaderId(leaderId);
				tleaderAccountNumber.setMemberId(sessionMember.getId());
				tleaderAccountNumber.setAccountNumber(numberAmount);
				tleaderAccountNumber.setBankName(bankName);
				tleaderAccountNumber.setAccountRealName(accountRealName);
				tleaderAccountNumber.setCreateDt(new Date());
				tleaderAccountNumber.setNumberType(2);
				tleaderaccountnumbermanager.insertSelective(tleaderAccountNumber);
				flag=0;//绑定成功
			}
		}else{
			flag=1;//验证码错误
		}
		out.print(flag);
		out.close();
	}
	/*
	 * 转到解除绑定页
	 */
	@SuppressWarnings("unchecked")
	public String unbindingIndex() throws Exception{
		String accountId = this.getRequest().getParameter("accountId");//账号id
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		//获取领袖
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		List<TLeader> lists = tleadermanager.selectByExample(tle);
		TLeader tleader = lists.get(0);
		this.getRequest().setAttribute("mobile", tleader.getPhone());
		TLeaderAccountNumber leaderAccountNumber = tleaderaccountnumbermanager.selectByPrimaryKey(Long.valueOf(accountId));
		this.getRequest().setAttribute("leaderAccountNumber", leaderAccountNumber);
		
		return "unbindingIndex";
	}
	/*
	 * 解除绑定
	 */
	@SuppressWarnings("unchecked")
	public void unbinding() throws Exception{
		int flag=-1;
		PrintWriter out =  getResponse().getWriter();
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		//获取领袖
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		List<TLeader> lists = tleadermanager.selectByExample(tle);
		TLeader tleader = lists.get(0);
		String numberAmountId = this.getRequest().getParameter("numberAmountId");
		String mobileCode =	this.getRequest().getParameter("mobileCode");//手机验证码
		String amoblieCode = (String) MemCached.getmcc().get(tleader.getPhone());
		if(mobileCode!=null && !"".equals(mobileCode) && mobileCode.equals(amoblieCode)){
			if(null!=numberAmountId && !"".equals(numberAmountId)){
				this.tleaderaccountnumbermanager.deleteByPrimaryKey(Long.valueOf(numberAmountId));
				flag=0;//删除成功
			}
		}else{
			flag=1;//验证码错误
		}
		out.print(flag);
		out.close();
	}
	/*
	 * 发送验证码
	 */
	@SuppressWarnings("unchecked")
	public void validateMobileCode() throws Exception{
		String captcha = CodeUtil .getVcode(4);
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		
		//获取领袖
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		List<TLeader> lists = tleadermanager.selectByExample(tle);
		TLeader tleader = lists.get(0);
		String mobile = tleader.getPhone();
		MemCached.getmcc().set(mobile,captcha,new Date(1000*300));
		Pattern pattern = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号 
		int flag=-1;
		PrintWriter out = this.getResponse().getWriter();
		if(mobile!=null &&  pattern.matcher(mobile).matches()){
			Map<String, String> map = new HashMap<String, String>();
			map.put("mobiles", mobile);
			map.put("smsContent", "您的111医药馆验证码:"+captcha+"。影视明星何政军先生推荐的中国好药房@111医药馆！");
			String YAO_GATEWAY_URL =tsysparametermanager.getKeys("sms");
			String buildRequestBySMS = ClientSubmit.buildRequestBySMS(map,YAO_GATEWAY_URL);
			System.out.println(buildRequestBySMS);
			flag=0;
		}
		out.print(flag);
		out.close();
	}
	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {

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

	public TLeaderQrCodeManager getTleaderqrcodemanager() {
		return tleaderqrcodemanager;
	}

	public void setTleaderqrcodemanager(TLeaderQrCodeManager tleaderqrcodemanager) {
		this.tleaderqrcodemanager = tleaderqrcodemanager;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
