package com.rc.portal.webapp.action;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.service.TMemberAmountOutManager;
import com.rc.portal.util.InfoUtil;
import com.rc.portal.util.cookieManager;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberAccountExample;
import com.rc.portal.vo.TMemberAmountOut;
import com.rc.portal.webapp.util.Base64ToImage;
import com.rc.portal.webapp.util.PageResult;
/**
 * 健康领秀
 */
@SuppressWarnings({"unchecked","rawtypes","deprecation","unused"})
public class LeaderAction extends BaseAction
{
	private static final long serialVersionUID = 672274541L;

	private static int PAGE_SIZE = 10;
	
	private TMemberAccountManager tmemberaccountmanager;
	
	private TMemberAmountOutManager tmemberamountoutmanager;
	
	private TMemberAmountOut amountOut;
	
	private OpenSqlManage opensqlmanage;
	
	private TLeaderManager tleadermanager;
	
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private String name ;
	private String type = "1";
	private String url;
	/**
	 * 领袖工具
	 * @return
	 * @throws SQLException
	 */
	public String leaderTool() throws SQLException{
		return "leaderTool";
	}
	/**
	 * 领袖图片素材
	 * @return
	 * @throws SQLException
	 */
	public String leaderImg() throws SQLException{
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
	 * 领袖素材页面跳转(暂时废弃)
	 * @return
	 * @throws SQLException
	 */
	public String leaderContentPage() throws SQLException{
		return "lxcontent";
	}
	/**
	 * 领袖素材(暂时废弃)
	 * @return
	 * @throws SQLException
	 */
	public void leaderResourcesList() throws SQLException{
		String pageNo = getRequest().getParameter("pageNo");
		String pageSize = getRequest().getParameter("pageSize");
		String tabs = getRequest().getParameter("tabs");
		Map<String, Object> map = new HashMap<String,Object>();
		String statusCode = null;
		try
		{
			PageWraper pw = new PageWraper();
			PageResult rs = new PageResult();
			rs.setP_curPage(Integer.parseInt(pageNo)); 
			rs.setP_pageSize(Integer.parseInt(pageSize));
//			List<Map<String, Object>> totals = null;
			if("1".equals(tabs)){
				pw = opensqlmanage.selectForPageByMap(map,"c_image.countImages","c_image.selectImages",rs.getP_curPage(), rs.getP_pageSize());
//				totals = opensqlmanage.selectForListByMap(map, "c_image.imagesTotals");
			}else {
				pw = opensqlmanage.selectForPageByMap(map,"t_lx_content.countContent","t_lx_content.selectContent",rs.getP_curPage(), rs.getP_pageSize());
//				totals = opensqlmanage.selectForListByMap(map, "t_lx_content.contentTotals");
			}
//			if(pageNo.trim().equals("1")){
//				if("1".equals(tabs)){
//					tsize1 = Integer.parseInt(String.valueOf(totals.get(0).get("num")));
//				}else {
//					tsize2 = Integer.parseInt(String.valueOf(totals.get(0).get("num")));
//				}
//			}
			List<Map<String, String>> result = pw.getResult();
			map.clear();
//			int size = result.size();
//			if (size == 0){
//				map.put("moreurl", null);
//			}else{
//				map.put("moreurl", (Integer.parseInt(pageNo) + 1) + "");
//			}
//			if("1".equals(tabs)){
//				count1 += size;
//				if(tsize1 == count1){
//					System.out.println("没有更多数据了....");
//				}
//			}else {
//				count2 += size;
//				if(tsize2 == count2){
//					System.out.println("没有更多数据了....");
//				}
//			}
			map.put("list", result);
			statusCode = "1";
		}catch (Exception e){
			statusCode = "0";
			e.printStackTrace();
		}
		map.put("statusCode", statusCode);
		map.put("pageNo", pageNo);
		this.writeObjectToResponse(map, ContentType.application_json);
	}
	
	
	/**
	 * 领袖商品列表
	 */
	public String list() throws SQLException
	{
		//判断是否开通我的药房
		TMember sessionMember = (TMember) this.getSession().getAttribute("member");
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		List list = tleadermanager.selectByExample(tle);
		if(null != list && list.size()>0)
		{
			TLeader leader = (TLeader) list.get(0);
			getRequest().setAttribute("is_shop", leader.getIsShop());
		}
		//药房数据
		Map<String,Object> querMap = new HashMap<String,Object>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String istoday = sd.format(new Date());
		querMap.put("istoday",istoday);
		rs.setP_pageSize(10);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_goods_brokerage.selectListPageCount", "t_goods_brokerage.selectListPage", rs.getP_curPage(), rs.getP_pageSize());
		//通告数据
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("ntype", 1);
		List<Map<String, Object>> notices = opensqlmanage.selectForListByMap(p, "t_lx_announcement.selecctNoticesByType");
		getRequest().setAttribute("notices", notices);
		return "pharmacy";
	}
	
	
	/**
	 * 领袖药房下拉刷新
	 */
	public void listByPage() throws SQLException
	{
		String pageNo = getRequest().getParameter("pageNo");
		String pageSize = getRequest().getParameter("pageSize");
		Map<String,Object> querMap = new HashMap<String,Object>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String istoday = sd.format(new Date());
		querMap.put("istoday",istoday);
		rs.setP_curPage(Integer.parseInt(pageNo));
		rs.setP_pageSize(Integer.parseInt(pageSize));
		pw = opensqlmanage.selectForPageByMap(querMap, "t_goods_brokerage.selectListPageCount", "t_goods_brokerage.selectListPage", rs.getP_curPage(), rs.getP_pageSize());
		writeObjectToResponse(pw.getResult(), ContentType.application_json);
	}
	
	/**
	 * 下拉分页list
	 * @return
	 * @throws SQLException
	 */
	public void appendGoodsList()throws Exception{
		this.getRequest().setCharacterEncoding("UTF-8");
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out =this.getResponse().getWriter();
		
		Map<String,Object> querMap = new HashMap<String,Object>();
		querMap.put("orderby", " ORDER BY b.id DESC");
		rs.setP_pageSize(50);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_goods_brokerage.selectListPageCount", "t_goods_brokerage.selectListPage", rs.getP_curPage(), rs.getP_pageSize());

		 List <Map> result = pw.getResult();
		 String content="";
		 if(result.size()>0){
			 for(int i=0;i<result.size();i++){
			 content+= "<tr><td height='60' align='center' valign='middle' width='30%'>"+result.get(i).get("short_name")+"</td><td height='60' align='center' valign='middle' width='20%'>"+result.get(i).get("spec")+
					   "</td><td height='60' align='center' valign='middle' width='25%'>￥"+result.get(i).get("wap_price")+"</td><td height='60' align='center' valign='middle' width='25%'>"+result.get(i).get("brokerage")+"%</td></tr>";
			 }
		 }else{
			 content="-1";
		 }
		 out.print(content);
		 out.close();
		
	}
	
   /**
    * 领袖排行
    * @return
 * @throws SQLException 
    */
	public String ranking() throws SQLException{
		
		this.getRequest().setAttribute("list_center_notice", opensqlmanage.selectListByObject(null, "t_lx_announcement.select_center_all"));
		
		Map<String,Object> querMap = new HashMap<String,Object>();
		rs.setP_pageSize(5);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_member_leader.selectCountLeaderCount", "t_member_leader.selectCountLeader",  rs.getP_curPage(), rs.getP_pageSize());
		List leaderList = pw.getResult();
		this.getRequest().setAttribute("leaderList", leaderList); //领袖药丸数排行
		querMap.clear();
		pw = opensqlmanage.selectForPageByMap(querMap, "t_member_leader.selectLeaderAmountCount", "t_member_leader.selectLeaderAmount",  rs.getP_curPage(), rs.getP_pageSize());
		List amountList = pw.getResult();
		this.getRequest().setAttribute("amountList", amountList); //领袖佣金排行
		
		return "ranking";
	}
	
	/**
	 * 领袖药丸排行
	 * @return
	 * @throws SQLException
	 */
	public String leaderPill() throws SQLException{
		
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		this.getRequest().setAttribute("tleader", opensqlmanage.selectObjectByObject(member.getId(), "t_leader.select_leader_member_and_count")); 
		
		Map<String,Object> querMap = new HashMap<String,Object>();
		rs.setP_pageSize(20);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_member_leader.selectCountLeaderCount", "t_member_leader.selectCountLeader",  rs.getP_curPage(), rs.getP_pageSize());
		return "pill";
	}
	
	/**
	 * 佣金排行
	 * @return
	 * @throws SQLException
	 */
	public String leaderBrokerage() throws SQLException{
		
		TMember member = (TMember)this.getSession().getAttribute("member");
		this.getRequest().setAttribute("tleader", opensqlmanage.selectObjectByObject(member.getId(), "t_leader.select_leader_member_and_count")); 
		
		
		TMemberAccountExample example = new TMemberAccountExample();
		
		example.createCriteria().andMemberIdEqualTo(member.getId());
		List selectByExample = tmemberaccountmanager.selectByExample(example);
		
		if(selectByExample!=null && selectByExample.size()>0){
			this.getRequest().setAttribute("leaderAccount", selectByExample.get(0)); 
		}
		
		Map<String,Object> querMap = new HashMap<String,Object>();
		rs.setP_pageSize(20);
		pw = opensqlmanage.selectForPageByMap(querMap, "t_member_leader.selectLeaderAmountCount", "t_member_leader.selectLeaderAmount",  rs.getP_curPage(), rs.getP_pageSize());
		return "brokerage";
	}
	
	/**
	 * 领袖中心
	 * @return
	 * @throws SQLException 
	 */
	public String leader() throws SQLException
	{
		TMember sessionMember = (TMember)this.getSession().getAttribute("member");
		this.getRequest().setAttribute("memberAccount", opensqlmanage.selectObjectByObject(sessionMember.getId(), "t_member_account.select_member_account_by_member_id"));
		this.getRequest().setAttribute("waitAmount", opensqlmanage.selectObjectByObject(sessionMember.getId(), "t_leader_stay_money.select_wait_amount_by_leader_id"));
		this.getRequest().setAttribute("top_center_notice", opensqlmanage.selectObjectByObject(null, "t_lx_announcement.select_center_top"));
		
		Map leader = (Map)this.getRequest().getSession().getAttribute("leader");
		String poster = (String)opensqlmanage.selectObjectByObject(leader.get("id"), "t_leader_qr_code.select_poster");
		if(StringUtils.isEmpty(poster)){
			poster = "/static/image/haibao/"+leader.get("code")+"_"+leader.get("id")+".jpg";
		}
		this.getRequest().setAttribute("poster", poster);
		
		//判断是否开通我的药房
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andMemberIdEqualTo(sessionMember.getId());
		List list = tleadermanager.selectByExample(tle);
		if(null != list && list.size()>0)
		{
			TLeader tleader = (TLeader) list.get(0);
			getRequest().setAttribute("newtleader", tleader);
		}
		return "leader";
	}
	
	/**
	 * 开通我的药房
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void openMyPharmacy() throws NumberFormatException, SQLException
	{
		String strId = getRequest().getParameter("leaderId");
		int result = 0;
		if(!StringUtils.isEmpty(strId))
		{
			TLeader tleader = tleadermanager.selectByPrimaryKey(Long.parseLong(strId));
			tleader.setIsShop(1);
			int effect = tleadermanager.updateByPrimaryKey(tleader);
			if(effect > 0)
			{
				result = 1;
			}
		}
		writeObjectToResponse(result, ContentType.application_json);
	}
	
	/**
	 * 获取领袖信息
	 */
	public void leaderInfo() throws NumberFormatException, SQLException
	{
		String strId = getRequest().getParameter("leaderId");
		TLeader tleader = null;
		if(!StringUtils.isEmpty(strId))
		{
			tleader = tleadermanager.selectByPrimaryKey(Long.parseLong(strId));
		}
		writeObjectToResponse(tleader, ContentType.application_json);
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
			List list = tleadermanager.selectByExample(te);
			if(null != list && list.size()==1){
				cookieManager.addCookie(this.getRequest(), this.getResponse(), "leader", code, 100000,".111yao.com");
			    url = hurl + "?code=" + code;
			}else{
				url="/";
			}
		}else{
			url="/";
		}
		return "url";
	}
	
	/**
	 * 分享领袖商品文章
	 * @return
	 * @throws SQLException 
	 */
	public String shareLeaderHtml() throws SQLException{
		String code = this.getRequest().getParameter("code"); //获取领袖编码
		String hurl = this.getRequest().getParameter("hurl"); //获取跳转地址
		String id = this.getRequest().getParameter("id");
		if(null != code && !("").equals(code) && null != hurl && !("").equals(hurl)){
			TLeaderExample te = new TLeaderExample();
			te.createCriteria().andCodeEqualTo(code);
			List list = tleadermanager.selectByExample(te);
			if(null != list && list.size()==1){
				//
				cookieManager.addCookie(this.getRequest(), this.getResponse(), "leader", code, 100000,".111yao.com");
			    url = hurl+"?code="+code+"&id="+id;
			}else{
				url="/";
			}
		}else{
			url="/";
		}
		return "url";
	}
	/**
	 * 领秀工具报表 
	 */
	public String report(){
		
		return "report";
	}
	
	/**
	 * 领秀工具报表数据(1:一个月,2:三个月,3:半年,4:一年)
	 */
	public void report_data(){
		String option = this.getRequest().getParameter("option");
		
		if(!(StringUtils.isNotEmpty(option) && option.matches("[1234]{1}"))){
			option = "1";
		}
		
		Map<String,Object> param = new HashMap<String,Object>();
		Map leader = (Map)this.getRequest().getSession().getAttribute("leader");
		param.put("leaderId", leader.get("id"));
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		Date now = new Date();
		if(option.equals("1")){
			now.setMonth(now.getMonth() - 1);
			param.put("date", now);
			param.put("date_format", "%Y-%m-%d");
		}else if(option.equals("2")){
			now.setMonth(now.getMonth() - 3);
			param.put("date", now);
			param.put("date_format", "%Y-%m");
		}else if(option.equals("3")){
			now.setMonth(now.getMonth() - 6);
			param.put("date", now);
			param.put("date_format", "%Y-%m");
		}else if(option.equals("4")){
			now.setMonth(now.getMonth() - 12);
			param.put("date", now);
			param.put("date_format", "%Y-%m");
		}
		
		this.writeObjectToResponse(opensqlmanage.selectForListByMap(param, "t_member_leader.select_leader_report"), ContentType.application_json);
	}

	private File head;
	
	private String headFileName;
	
	private String headContentType;
	
	private String diskPath = InfoUtil.getInstance().getInfo("img", "images.public.head.path");
	
	/**
	 * 上传头像 
	 */
	public void uploadHeadPortrait(){
		String fileType= FilenameUtils.getExtension(headFileName);
		try {
			String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");	
			if(checkFileType(fileType) && head.length() < 4*1024*1024){
				String webPath = diskPath+"/"+UUID.randomUUID()+"."+fileType;
				String fullName = basePath + webPath;
				
				File uploadFile = new File(fullName);
				FileUtils.copyFile(head, uploadFile);//上传图片
				
				this.scale(uploadFile.getPath(),uploadFile.getPath(),300,300,false);
				
				Map leader = (Map)this.getSession().getAttribute("leader");
				
				leader.put("head_url", webPath);
				
				TLeader tleader = new TLeader();
				
				tleader.setId((Long)leader.get("id"));
				tleader.setHeadUrl(webPath);
				
				this.tleadermanager.updateByPrimaryKeySelective(tleader);
				
				this.getSession().setAttribute("leader", leader);
				
				this.writeObjectToResponse(1, ContentType.application_json);
			}else{
				this.writeObjectToResponse(0, ContentType.application_json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.writeObjectToResponse(0, ContentType.application_json);
		}
	}
	
	/**
	 * 上传自定义图片
	 */
	public void uploadCustomsImage()
	{
		try 
		{
			String imgStr = getRequest().getParameter("imageData");
			imgStr = imgStr.replaceAll("data:image/jpeg;base64,", " ");
			imgStr = imgStr.trim();
			String webPath = Base64ToImage.GenerateImage(getRequest(), imgStr, diskPath);
			
			Map leader = (Map)this.getSession().getAttribute("leader");
			leader.put("head_url", webPath);
			TLeader tleader = new TLeader();
			tleader.setId((Long)leader.get("id"));
			tleader.setHeadUrl(webPath);
			this.tleadermanager.updateByPrimaryKeySelective(tleader);
			this.getSession().setAttribute("leader", leader);
			this.writeObjectToResponse(1, ContentType.application_json);
		} 
		catch (SQLException e) 
		{
			this.writeObjectToResponse(0, ContentType.application_json);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 检查文件类型
	 */
	public boolean checkFileType(String type){
		boolean flag=false;
		type = type.toLowerCase();
		String[] arrType={"jpg","png","gif","jpeg"};
		for(String s:arrType){
			if(type.equals(s)){
				return true;
			}
		}
		return flag;
	}
	
	/**
	 * 获取秀粉信息
	 */
	public void getinfo()
	{
		TMember member = (TMember)getRequest().getSession().getAttribute("member");
		if(member != null)
		{
			writeObjectToResponse(opensqlmanage.selectObjectByObject(member.getId(), "t_leader.select_leader_member_and_count"), ContentType.application_json);
		}
	}
	
	/**
	 * 领袖中心药房
	 */
	public void searchArticles()
	{
		String bkid = getRequest().getParameter("bkid");
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("bkid", bkid);
		List<Map<String, Object>> data = opensqlmanage.selectForListByMap(p, "t_goods_brokerage.searchArticleBybId");
		writeObjectToResponse(data, ContentType.application_json);
	}
	
	/**
     * 缩放图像（按高度和宽度缩放）
     * @param srcImageFile 源图像文件地址
     * @param result 缩放后的图像地址
     * @param height 缩放后的高度
     * @param width 缩放后的宽度
     * @param bb 比例不对时是否需要补白：true为补白; false为不补白;
     */
	 @SuppressWarnings("static-access")
	public void scale(String srcImageFile, String result, int height, int width, boolean bb) throws Exception {
            double ratio = 0.0; // 缩放比例
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform
                        .getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            String[] split = result.split("\\.");
            ImageIO.write((BufferedImage) itemp, split[split.length-1], new File(result));
	    }
	 
	 /**
	  * 带入账佣金流水 
	  * @return
	  */
	 public String runningWater(){
		 TMember member = (TMember)getRequest().getSession().getAttribute("member");
		 //当前三个月之内的每个月流水统计0:最近一个月,1最近两个月,2最近三个月  一次类推
		 int month = 2;
		 
		 Map<String,Object> param = new HashMap<String,Object>();
		 param.put("month", month);
		 param.put("memberId", member.getId());
		 
		 List runningWater = opensqlmanage.selectForListByMap(param, "t_leader_stay_money.runningWater");
		 
		 //字段date,month,amount
		 LinkedList<Map<String,Object>> runningWaterLink = new LinkedList<Map<String,Object>>();
		 
		 for(int i = 0; i <= month;i++){
			 
			 Date date = new Date();
			 date.setMonth(date.getMonth() - i );
			 
			 SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
			 SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMM");
				 
			 Map<String,Object> water = new HashMap<String,Object>();
			 
			 water.put("month", sdfMonth.format(date));
			 water.put("date", sdfDate.format(date));
			 water.put("amount", 0l);
			 
			 runningWaterLink.add(water);
		 }
		 
		 for(Object obj:runningWater){
			 Map map = (Map)obj;
			 for(Map mapT:runningWaterLink){
				 if(mapT.get("date").toString().equals(map.get("date"))){
					 mapT.put("amount", map.get("amount"));
				 }
			 }
		 }
		 
		 this.getRequest().setAttribute("runningWater", runningWaterLink );
		 return "runningWater";
	 }

	 /**
	  * 佣金流水每月明细
	  * @return
	  */
	 public String runningWaterDetail(){
		 
		 TMember member = (TMember)getRequest().getSession().getAttribute("member");
		 
		 Map<String,Object> param = new HashMap<String,Object>();
		 
		 param.put("date", this.getRequest().getParameter("date"));
		 
		 param.put("memberId", member.getId());
		 
		 pw = opensqlmanage.selectForPageByMap(param, "t_leader_stay_money.runningWaterDetailCount","t_leader_stay_money.runningWaterDetail",  rs.getP_curPage(), 5);
		 
		 this.getRequest().setAttribute("date", this.getRequest().getParameter("date"));
		 
		 return "runningWaterDetail";
	 }
	 
	public TMemberAccountManager getTmemberaccountmanager() {
		return tmemberaccountmanager;
	}

	public void setTmemberaccountmanager(TMemberAccountManager tmemberaccountmanager) {
		this.tmemberaccountmanager = tmemberaccountmanager;
	}

	
	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {}

	public TMemberAmountOut getAmountOut() {
		return amountOut;
	}

	public void setAmountOut(TMemberAmountOut amountOut) {
		this.amountOut = amountOut;
	}

	public TMemberAmountOutManager getTmemberamountoutmanager() {
		return tmemberamountoutmanager;
	}

	public void setTmemberamountoutmanager(
			TMemberAmountOutManager tmemberamountoutmanager) {
		this.tmemberamountoutmanager = tmemberamountoutmanager;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TLeaderManager getTleadermanager() {
		return tleadermanager;
	}

	public void setTleadermanager(TLeaderManager tleadermanager) {
		this.tleadermanager = tleadermanager;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public File getHead() {
		return head;
	}
	public void setHead(File head) {
		this.head = head;
	}
	public String getHeadFileName() {
		return headFileName;
	}
	public void setHeadFileName(String headFileName) {
		this.headFileName = headFileName;
	}
	public String getHeadContentType() {
		return headContentType;
	}
	public void setHeadContentType(String headContentType) {
		this.headContentType = headContentType;
	}
	
	public Map<String, Object> delRepeat(Map<String, Object> map)
	{
        Map<String, Object> map2 = new HashMap<String, Object>();
        Map<String, Object> map3 = new HashMap<String, Object>();
        TreeMap<String, Object> treemap = new TreeMap<String, Object>(map);
        Iterator<String> it = treemap.keySet().iterator();
        while (it.hasNext()) 
        {
            String key = it.next();
            Object value = treemap.get(key);
            if(map2.containsKey(value))
            {
                continue;
            }
            else
            {
                map2.put(value.toString(), value);
                map3.put(key, value);
            }
             
        }
        return map3;
    }
	
}
