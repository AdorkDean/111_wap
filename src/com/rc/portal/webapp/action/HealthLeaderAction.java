package com.rc.portal.webapp.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.commons.util.InfoUtil;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.portal.memcache.MemCached;
import com.rc.portal.service.CImageManager;
import com.rc.portal.service.CLocationManager;
import com.rc.portal.service.ICartManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderAgentManager;
import com.rc.portal.service.TLeaderImageManager;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TLeaderQrCodeManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.CodeUtil;
import com.rc.portal.util.CookieUtil;
import com.rc.portal.util.NetworkUtil;
import com.rc.portal.util.SharePoster;
import com.rc.portal.util.cookieManager;
import com.rc.portal.vo.CImage;
import com.rc.portal.vo.CLocation;
import com.rc.portal.vo.CLocationExample;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderAgent;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TLeaderImage;
import com.rc.portal.vo.TLeaderImageExample;
import com.rc.portal.vo.TLeaderQrCode;
import com.rc.portal.vo.TLeaderQrCodeExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.webapp.util.QRCodeUtil;
import com.rc.portal.webapp.util.zxing.QRCodeZXingUtil;

/**
 * 健康领袖Action
 * 
 * @author user00
 * @createTime 2015-9-17 下午6:38:49
 */
public class HealthLeaderAction extends BaseAction {

	private static final long serialVersionUID = 8629590925309040239L;

	private TLeaderManager tleadermanager;

	private TLeaderImageManager tleaderimagemanager;

	private TSysParameterManager tsysparametermanager;

	private OpenSqlManage opensqlmanage;

	private CLocationManager clocationmanager;
	
	private CImageManager cimagemanager;
	
	private TLeaderQrCodeManager tleaderqrcodemanager;
	
	private TLeaderAgentManager tleaderagentmanager;
	
	private TMemberManager tmembermanager;
	
	private ICartManager cartmanager;
	
	private File headImage;// 头像
	private File leaderImage1;// 资质
	private File leaderImage2;// 资质
	private File leaderImage3;// 资质
	private File leaderImage4;// 资质
	private File leaderImage5;// 资质
	private File cardFirst;// 身份证
	private File cardTwo;// 身份证反面
	private String headImageFileName;// 头像
	private String leaderImage1FileName;// 资质
	private String leaderImage2FileName;// 资质
	private String leaderImage3FileName;// 资质
	private String leaderImage4FileName;// 资质
	private String leaderImage5FileName;// 资质
	private String cardFirstFileName;// 身份证
	private String cardTwoFileName;// 身份证反面
	// 图片路径
	private final String diskPath = InfoUtil.getInstance().getInfo("img", "images.public.image.leader.path");

	private final String sharedLeaderUrl = InfoUtil.getInstance().getInfo("config", "shareLeaderUrl");
	private final String leaderUrl = InfoUtil.getInstance().getInfo("config", "leaderUrl");

	// 二维码路径
	private final String qrCodeDiskPath = InfoUtil.getInstance()
			.getInfo("img", "images.public.image.leaderQRCode.path");

	// 二维码logo路径
	private final String qrLogoPath = InfoUtil.getInstance().getInfo("img", "qr.image.log.path");

	// 图片链接地址前缀;
	private final String uiHttp = InfoUtil.getInstance().getInfo("healthleader", "healthleader.haibao.ui.http");;
	// 默认头像
	private final String defaultHead = InfoUtil.getInstance().getInfo("healthleader",
			"healthleader.haibao.default.head");;
	// 默认二维码
	private final String defaultQr = InfoUtil.getInstance().getInfo("healthleader",
			"healthleader.haibao.default.qrcode");;
	// 默认背景图
	private final String defaultBackground = InfoUtil.getInstance().getInfo("healthleader",
			"healthleader.haibao.background.img");;
	// 海报存储地址
	private final String savePath = InfoUtil.getInstance().getInfo("healthleader", "healthleader.haibao.save.path");;

	
	private final String secoundQR = InfoUtil.getInstance().getInfo("healthleader", "healthleader.secound.qr");

	// 最新二维码图片
	private final String codeQR = InfoUtil.getInstance().getInfo("healthleader", "healthleader.qr.code");

	// 去海报页面
	private final String goHaibaoUrl = InfoUtil.getInstance().getInfo("healthleader", "healthleader.qr.code.gohaibao");

	/*
	 * 跳转领秀登录页
	 */
	public String leaderLoginIndex(){
		
		return "leaderLoginIndex";
	}
	/*
	 * 领秀登录
	 */
	@SuppressWarnings("unchecked")
	public void leaderLogin() throws Exception{
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		String key = cookieManager.getCookieValueByName(this.getRequest(), CartAction.cartKey);
		Map<String, Object> mobileMap = new HashMap<String, Object>();
		String username = this.getRequest().getParameter("username");
		String mobileCode = this.getRequest().getParameter("mobileCode");
		String amoblieCode = (String) MemCached.getmcc().get(username);
		
		mobileMap.put("mobile", username);
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andPhoneEqualTo(username);
		List<TLeader> list  = tleadermanager.selectByExample(tLeaderExample);
		if(list == null || list.size() <= 0){
			flag=4;//该手机号未验证
		}else{
			TLeader tLeader = list.get(0);
			if(tLeader!=null){
				//TMember mobileMember = (TMember) this.opensqlmanage.selectObjectByObject(mobileMap, "t_member.ibatorgenerated_selectMemberByMobile");
				TMember mobileMember = tmembermanager.selectByPrimaryKey(tLeader.getMemberId());
				if (mobileMember.getStatus() == 0) {
					if (!mobileCode.equals(amoblieCode)) {
						flag = 1;// 输入的密码不正确
					}else{
						mobileMember.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
						mobileMember.setLastDate(new Date());
						tmembermanager.updateByPrimaryKey(mobileMember);
						if(key!=null){
							cartmanager.synCart(mobileMember.getId(), key);
						}
						flag = 2;//login success
						this.getSession().setAttribute("member", mobileMember);
						if(mobileMember.getUserName()!=null && !"".equals(mobileMember.getUserName())){
							CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", mobileMember.getUserName(),30*24*60*60, "/", ".111yao.com", false);
						}else{
							CookieUtil.addCookie(getRequest(), getResponse(), "cookusername", mobileMember.getMobile(),30*24*60*60, "/", ".111yao.com", false);
						}
						CookieUtil.addCookie(getRequest(), getResponse(), "cookpassword", mobileMember.getPassword(), 30*24*60*60, "/", ".111yao.com", false);
					}
				}
			}else{
				flag=4;//手机号未绑定领秀
			}
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
		String mobile = this.getRequest().getParameter("mobile");
		
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andPhoneEqualTo(mobile);
		List<TLeader> list  = tleadermanager.selectByExample(tLeaderExample);
		
		MemCached.getmcc().set(mobile,captcha,new Date(1000*300));
		Pattern pattern = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号 
		int flag=-1;
		PrintWriter out = this.getResponse().getWriter();
		if(list == null || list.size() <= 0){
			flag=4;//该手机号未验证
		}else{
			if(mobile!=null &&  pattern.matcher(mobile).matches()){
				Map<String, String> map = new HashMap<String, String>();
				map.put("mobiles", mobile);
				map.put("smsContent", "您的111医药馆验证码:"+captcha+"。影视明星何政军先生推荐的中国好药房@111医药馆！");
				String YAO_GATEWAY_URL =tsysparametermanager.getKeys("sms");
				String buildRequestBySMS = ClientSubmit.buildRequestBySMS(map,YAO_GATEWAY_URL);
				System.out.println(buildRequestBySMS);
				flag=0;
			}
		}
		out.print(flag);
		out.close();
	}
	
	/**
	 * 跳转健康领袖页面(判断)
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String healthLeader() throws SQLException {
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		if (tMember == null) {
			return ERROR;
		}
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andMemberIdEqualTo(tMember.getId());
		List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);
		if (list == null || list.size() <= 0) {// 跳转申请页
			return "apply_health_leader";
		} else {
//			TLeader tLeader = list.get(0);
//			this.getRequest().setAttribute("tLeader", tLeader);
//			if (tLeader.getAuditType() != null && tLeader.getAuditType() == 1) {// 存在,审核通过,领袖中心
//				return "health_leader";
//			} else {// 审核等待页面
//				return "wait_health_leader";
//			}
			
			return "health_leader";
		}
	}

	/**
	 * 去申请健康领袖页面
	 * 
	 * @throws SQLException
	 */
	public String applyHealthLeaderPage() throws SQLException {
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		if (tMember == null) {
			return ERROR;
		}
		if (tMember.getId() != null && tMember.getId() > 0) {
			TLeaderExample tLeaderExample = new TLeaderExample();
			tLeaderExample.createCriteria().andMemberIdEqualTo(tMember.getId());
			List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);
			if (list != null && list.size() > 0) {
				return "health_leader";
			}
		}

		return "verify_phone_page";
	}

	/**
	 * 填写健康领袖页面
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addHealthLeaderPage() throws SQLException {
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		if (tMember == null) {
			return ERROR;
		}
		if (tMember.getId() != null && tMember.getId() > 0) {
			TLeaderExample tLeaderExample = new TLeaderExample();
			tLeaderExample.createCriteria().andMemberIdEqualTo(tMember.getId());
			List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);
			if (list != null && list.size() > 0) {
				return "health_leader";
			}
		}

		// 手机号带过去,删除缓存的验证码
		String phone = this.getRequest().getParameter("phone");
		// apply_health_code_
		this.getSession().removeAttribute("apply_health_code_" + phone);
		CLocationExample cLocationExample = new CLocationExample();
		cLocationExample.createCriteria().andGradeEqualTo(1);
		List<CLocation> firstList = clocationmanager.selectByExample(cLocationExample);
		this.getRequest().setAttribute("firstList", firstList);
		this.getRequest().setAttribute("phone", phone);
//		return "add_healthleader_page";
		
		
		return "add_healthleader_basicPage";
		
	}

	/**
	 * 去修改页面
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public String editHealthLeaderPage() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		if (id == null || "".equals(id)) {
			// FIXME WangMing 错误页面处理
			return ERROR;
		}
		TLeader tLeader = tleadermanager.selectByPrimaryKey(Long.valueOf(id));
		if (tLeader == null) {
			// FIXME WangMing 错误页面处理
			return ERROR;
		}
		this.getRequest().setAttribute("tLeader", tLeader);
		TLeaderImageExample tLeaderImageExample = new TLeaderImageExample();
		tLeaderImageExample.createCriteria().andLeaderIdEqualTo(tLeader.getId());
		List<TLeaderImage> imageList = tleaderimagemanager.selectByExample(tLeaderImageExample);
		if (imageList != null && imageList.size() > 0) {
			this.getRequest().setAttribute("leaderImage1", imageList.get(0));
		}
		if (imageList != null && imageList.size() > 1) {
			this.getRequest().setAttribute("leaderImage2", imageList.get(1));
		}
		if (imageList != null && imageList.size() > 2) {
			this.getRequest().setAttribute("leaderImage3", imageList.get(2));
		}
		if (imageList != null && imageList.size() > 3) {
			this.getRequest().setAttribute("leaderImage4", imageList.get(3));
		}
		if (imageList != null && imageList.size() > 4) {
			this.getRequest().setAttribute("leaderImage5", imageList.get(4));
		}
		CLocationExample cLocationExample = new CLocationExample();
		cLocationExample.createCriteria().andGradeEqualTo(1);
		List<CLocation> firstList = clocationmanager.selectByExample(cLocationExample);
		CLocation thirdLocation = clocationmanager.selectByPrimaryKey(tLeader.getAreaId().intValue());
		this.getRequest().setAttribute("firstList", firstList);
		System.out.println("第三层:\t" + (thirdLocation == null ? "" : thirdLocation.getName()));
		System.out.println("第一层List:\t" + firstList);
		this.getRequest().setAttribute("thirdLocation", thirdLocation);
		if (thirdLocation != null) {
			CLocation secondLocation = clocationmanager.selectByPrimaryKey(thirdLocation.getParentid().intValue());
			List<CLocation> thirdList = clocationmanager.selectAllChildrenByParendId(secondLocation.getId());
			this.getRequest().setAttribute("secondLocation", secondLocation);
			this.getRequest().setAttribute("thirdList", thirdList);
			System.out.println("第二层:\t" + (secondLocation == null ? "" : secondLocation.getName()));
			System.out.println("第三层List:\t" + (thirdList == null ? "" : thirdList));
			if (secondLocation != null) {
				CLocation firstLocation = clocationmanager.selectByPrimaryKey(secondLocation.getParentid().intValue());
				this.getRequest().setAttribute("firstLocation", firstLocation);
				System.out.println("第一层:\t" + (firstLocation == null ? "" : firstLocation.getName()));
				List<CLocation> secondList = clocationmanager.selectAllChildrenByParendId(firstLocation.getId());
				System.out.println("第二层List:\t" + secondList);
				this.getRequest().setAttribute("secondList", secondList);
			}
		}
		return "edit_health_leader_page";
	}

	/**
	 * 二维码页面
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public String qrCodePage() throws NumberFormatException, SQLException {
		// String id = this.getRequest().getParameter("id");
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		List<TLeader> list = null;
		if (tMember == null) {
			return "health_leader_page";
		} else {
			TLeaderExample tLeaderExample = new TLeaderExample();
			tLeaderExample.createCriteria().andMemberIdEqualTo(Long.valueOf(tMember.getId())).andAuditTypeEqualTo(1);
			list = tleadermanager.selectByExample(tLeaderExample);
		}
		String leaderUrl = InfoUtil.getInstance().getInfo("config", "leaderUrl");
		this.getRequest().setAttribute("leaderUrl", leaderUrl);
		if (list == null || list.size() <= 0) {
			return "health_leader_page";
		} else {
			this.getRequest().setAttribute("tLeader", list.get(0));
			return "qr_code_page";
		}
	}

	/**
	 * 保存健康领袖
	 * 
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public String saveHealthLeader() throws IOException, SQLException {
		String id = this.getRequest().getParameter("id");
		String realName = this.getRequest().getParameter("realName");
		String nickName = this.getRequest().getParameter("nickName");
		String sex = this.getRequest().getParameter("sex");
		String cardCode = this.getRequest().getParameter("cardCode");
		String thirdLocation = this.getRequest().getParameter("thirdLocation");
		String address = this.getRequest().getParameter("address");
		String phone = this.getRequest().getParameter("phone");
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		System.out.println(this.getRequest().getParameter("headImageFileName"));
		if (tMember == null) {
			return ERROR;
		}
		// String verifyCode = this.getRequest().getParameter("verifyCode");
		// if (id == null && (headImageFileName == null ||
		// "".equals(headImageFileName.trim()))) {
		// return ERROR;
		// }
		// if (id == null
		// && ((leaderImage1FileName == null ||
		// "".equals(leaderImage1FileName.trim()))
		// && (leaderImage2FileName == null ||
		// "".equals(leaderImage2FileName.trim()))
		// && (leaderImage3FileName == null ||
		// "".equals(leaderImage3FileName.trim()))
		// && (leaderImage4FileName == null ||
		// "".equals(leaderImage4FileName.trim())) && (leaderImage5FileName ==
		// null || ""
		// .equals(leaderImage5FileName.trim())))) {
		// // 资质不能为空;
		// return ERROR;
		// }
		// if (id == null && (cardFirstFileName == null ||
		// "".equals(cardFirstFileName.trim()))) {
		// return ERROR;
		// }
		// if (id == null && (cardTwoFileName == null ||
		// "".equals(cardTwoFileName.trim()))) {
		// return ERROR;
		// }
		Map<String, Object> map = uploadImage();
		TLeader tLeader = null;
		if (id != null && !"".equals(id)) {
			tLeader = tleadermanager.selectByPrimaryKey(Long.valueOf(id));
			if (tLeader == null) {
				tLeader = new TLeader();
			}
		} else {
			if (tMember.getId() != null && tMember.getId() > 0L) {
				TLeaderExample tLeaderExample = new TLeaderExample();
				tLeaderExample.createCriteria().andMemberIdEqualTo(tMember.getId());
				List<TLeader> llLeaders = tleadermanager.selectByExample(tLeaderExample);
				if (llLeaders == null || llLeaders.size() <= 0) {
					tLeader = new TLeader();
				} else {
					tLeader = llLeaders.get(0);
				}
			} else {
				tLeader = new TLeader();
			}
		}
		if (map != null && map.get("headImage") != null) {
			tLeader.setHeadUrl(map.get("headImage").toString());
		}
		if (map != null && map.get("cardFirst") != null) {
			tLeader.setCardFirstUrl(map.get("cardFirst").toString());
		}
		if (map != null && map.get("cardTwo") != null) {
			tLeader.setCardTwoUrl(map.get("cardTwo").toString());
		}
		if (realName != null && !"".equals(realName)) {
			tLeader.setRealName(realName);
		}
		if (nickName != null && !"".equals(nickName)) {
			tLeader.setNickName(nickName);
		}
		if (sex != null && !"".equals(sex)) {
			tLeader.setSex(Integer.valueOf(sex));
		}
		if (cardCode != null && !"".equals(cardCode)) {
			tLeader.setCardCode(cardCode);
		}
		if (thirdLocation != null && !"".equals(thirdLocation)) {
			tLeader.setAreaId(Long.valueOf(thirdLocation));
		}
		if (address != null && !"".equals(address)) {
			tLeader.setAddress(address);
		}
		if (phone != null && !"".equals(phone)) {
			tLeader.setPhone(phone);
		}
		tLeader.setAuditType(0);
		tLeader.setStatus(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssms");
		String code = sdf.format(new Date()) + randomString();
		if (tLeader.getCode() == null || "".equals(tLeader.getCode())) {
			tLeader.setCode(code);
		}
		File qrCodeDir = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + qrCodeDiskPath);
		File qrCodeFile = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + qrCodeDiskPath
				+ code + ".jpg");
		if (!qrCodeDir.exists()) {
			qrCodeDir.mkdirs();
		}
		if (!qrCodeFile.exists()) {
			qrCodeFile.createNewFile();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(qrCodeFile);

		String contentString = sharedLeaderUrl + "?code=" + code + "&hurl=" + leaderUrl;
		try {
			URL url = new URL(qrLogoPath);
			URLConnection uc = url.openConnection();
			uc.getInputStream();
			QRCodeZXingUtil.encoderQRCoder_logoUrl(contentString, fileOutputStream, qrLogoPath);
		} catch (IOException e) {
			log.error("生成健康领袖logo二维码不存在,配置链接为:[" + qrLogoPath + "]");
			QRCodeUtil.encoderQRCoder(contentString, fileOutputStream, 10);
		}

		// boolean ret = QRCodeUtil.encoderQRCoder(sharedLeaderUrl + "?code=" +
		// code + "&hurl=" + leaderUrl,
		// fileOutputStream, 10);
		// if (ret) {
		tLeader.setDimensionalCodeUrl(qrCodeDiskPath + code + ".jpg");
		// }
		fileOutputStream.flush();
		fileOutputStream.close();
		Long memberId = tMember.getId();
		tLeader.setMemberId(memberId);
		tLeader.setAuditType(0);
		Long leaderId = null;
		if (tLeader.getId() == null) {
			tLeader.setCreateDt(new Date());
			leaderId = tleadermanager.insertSelective(tLeader);
		} else {
			leaderId = tLeader.getId();
			tleadermanager.updateByPrimaryKeySelective(tLeader);
		}
		TLeaderImage tLeaderImage = null;
		for (int i = 1; i <= 5; i++) {
			if (map != null && map.get(String.valueOf(("leaderImage" + String.valueOf(i)))) != null) {
				tLeaderImage = new TLeaderImage();
				tLeaderImage.setImageUrl(map.get(String.valueOf(("leaderImage" + String.valueOf(i)))).toString());
				tLeaderImage.setLeaderId(leaderId);
				tleaderimagemanager.insertSelective(tLeaderImage);
			}
		}
		// 1:头像;2:二维码;3:昵称;
		createPosterImg(code + "_" + leaderId, tMember, qrCodeFile,tLeader);
		return "apply_health_leader_success";
	}

	/**
	 * 生成海报文件
	 */
	// public void createPosterImg(String httpHeadFile, String httpQRCodeFile,
	// String nickName, String httpBackImg) {
	private void createPosterImg(String backImgName, TMember tMember, File qrCodeFile,TLeader tLeader) {
		String fontName = "宋体";
		int fontStyle = 10;
		int color = 0;
		int fontSize = 36;
		int x = 190;
		int y = 370;
		URL url = null;
		URLConnection connection = null;
		try {
			url = new URL(defaultBackground);
			connection = url.openConnection();
			connection.setDoOutput(true);
			BufferedImage image = ImageIO.read(connection.getInputStream());
			File _savePath = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + savePath);
			File _saveFile = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + savePath
					+ backImgName + ".jpg");
			if (!_savePath.exists()) {
				_savePath.mkdirs();
			}
			if (!_saveFile.exists()) {
				_saveFile.createNewFile();
			}

			FileOutputStream fout = new FileOutputStream(this.getRequest().getSession().getServletContext()
					.getRealPath("/")
					+ savePath + backImgName + ".jpg");
			ImageIO.write(image, "jpg", fout);
			fout.flush();
			fout.close();
		} catch (MalformedURLException e) {// http背景图片不存在
			log.error("海报背景图片不存在");
			e.printStackTrace();
		} catch (IOException e) {// http背景图片不存在
			log.error("海报背景图片不存在");
			e.printStackTrace();
		}
		String _backImgName = this.getRequest().getSession().getServletContext().getRealPath("/") + savePath
				+ backImgName + ".jpg";
		String httpHeadFile = "";
		if (tLeader.getHeadUrl() != null && !"".equals(tLeader.getHeadUrl())) {
			String getHeadPortrait = tLeader.getHeadUrl();
			if (getHeadPortrait.startsWith("http://")) {
				httpHeadFile = tLeader.getHeadUrl();// 头像
			} else {
				httpHeadFile = uiHttp + tLeader.getHeadUrl();// 头像
			}
			try {
				url = new URL(httpHeadFile);
				connection = url.openConnection();
				connection.setDoOutput(true);
				ImageIO.read(connection.getInputStream());
			} catch (MalformedURLException e) {
				e.printStackTrace();
				httpHeadFile = defaultHead;
			} catch (IOException e) {
				e.printStackTrace();
				httpHeadFile = defaultHead;
			}
		} else {
			httpHeadFile = defaultHead;
		}
		System.out.println("----------------------------------------------------------");
		System.out.println("背景图片:【" + _backImgName + "】");
		System.out.println("头像图片:【" + httpHeadFile + "】");
		System.out.println("二维码图片:【" + qrCodeFile.getAbsolutePath() + "】");
		String nickName = tLeader.getNickName();
		SharePoster.pressTextAndImg(httpHeadFile, qrCodeFile, nickName, _backImgName, fontName, fontStyle, color,
				fontSize, x, y);
	}

	/**
	 * 发送手机验证码
	 * 
	 * @throws Exception
	 * @throws SQLException
	 */
	public void sendPhoneCode() throws SQLException, Exception {
		String phone = this.getRequest().getParameter("phone");
		this.getResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter writer = this.getResponse().getWriter();
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andPhoneEqualTo(phone);
		List list = tleadermanager.selectByExample(tle);
		if(null != list && list.size()>0){  //判断领秀手机号码是否存在
			writer.write("-1"); //手机号码已经绑定
		}else{
			String messageCode = randomString();
			boolean flag = sendMessage(phone, messageCode);
			if (flag) {
				this.getSession().setAttribute("apply_health_code_" + phone, messageCode);
				System.out.println("健康领袖手机验证码:\t" + messageCode);
				writer.write("1");
			} else {
				writer.write("0");
			}
		}
		this.getResponse().getWriter().flush();
		this.getResponse().getWriter().close();
	}

	/**
	 * 健康领袖公用发短信(仅限于健康领袖)
	 * 
	 * @param phone
	 * @param messageCode
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	private boolean sendMessage(String phone, String messageCode) throws SQLException, Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobiles", phone);
		map.put("smsContent", messageCode + "。这是您的手机验证码，5分钟内有效。健康领秀，成就健康巨人。");
		ClientSubmit.buildRequestBySMS(map, tsysparametermanager.getKeys("sms"));
		return true;
	}

	/**
	 * 验证验证码
	 */
	public void verifyPhoneCode() {
		ResultData resultData = new ResultData();

		String phone = this.getRequest().getParameter("phone");// 手机号
		String verifyCode = this.getRequest().getParameter("verifyCode");// 验证码

		Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
		Matcher m = p.matcher(phone);
		if (phone == null || "".equals(phone) || !m.matches()) {
			resultData.setMessage("请输入有效的手机号");
			resultData.setStatus(0);
			this.writeObjectToResponse(resultData, ContentType.application_json);
			return;
		}
		if (verifyCode == null || "".equals(verifyCode)) {
			resultData.setMessage("请输入手机验证码");
			resultData.setStatus(0);
			this.writeObjectToResponse(resultData, ContentType.application_json);
			return;
		}
		String code = (String) this.getSession().getAttribute("apply_health_code_" + phone);
		if (code != null && code.equals(verifyCode)) {// 验证是否一致;
			resultData.setMessage("一致");
			resultData.setStatus(1);
		} else {
			resultData.setMessage("验证码不正确");
			resultData.setStatus(0);
		}
		this.writeObjectToResponse(resultData, ContentType.application_json);
	}

	/**
	 * 根据省查市,市查区,区查县
	 * 
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void selectLocationByParentId() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		// CLocation clocation =
		// clocationmanager.selectByPrimaryKey(Integer.valueOf(id));
		CLocationExample cLocationExample = new CLocationExample();
		cLocationExample.createCriteria().andParentidEqualTo(Integer.valueOf(id));
		List<CLocation> list = clocationmanager.selectByExample(cLocationExample);
		this.writeObjectToResponse(list, ContentType.application_json);
	}
	
	//新版action---------------开始-------------------------------------------------------
	
	/**
	 * 海报背景列表页
	 * @throws SQLException 
	 */
	public String backgroundHaibaoList() throws SQLException{
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		if(tMember==null){
			return ERROR;
		}
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andMemberIdEqualTo(tMember.getId());
		List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);
		if(list==null || list.size()<1){
			return "health_leader_page";
		}
		this.getRequest().setAttribute("leader", list.get(0));
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("imgType", "4");//海报的
		map.put("status", "1");//海报的
		List<CImage> _list = opensqlmanage.selectForListByMap(map, "c_image.selectHaibaoImage");
		this.getRequest().setAttribute("list", _list);
		return "background_haibao_list";
	}
	
	/**
	 * 跳转海报页面
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String gotoHaibaoPage() throws NumberFormatException, SQLException {
//		String leaderId = this.getRequest().getParameter("leaderId");
//		if (leaderId == null || "".equals(leaderId)) {
//			return ERROR;
//		}
		
//		TLeader tLeader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		if(tMember==null){
			return ERROR;
		}
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andMemberIdEqualTo(tMember.getId());
		List<TLeader> leaderList = tleadermanager.selectByExample(tLeaderExample);
		if(leaderList==null||leaderList.size()<=0){
			return "health_leader_page";
		}
		TLeader tLeader = leaderList.get(0);
		if (tLeader == null) {
			return "health_leader_page";
		}
		TLeaderQrCodeExample tLeaderQrCodeExample = new TLeaderQrCodeExample();
		tLeaderQrCodeExample.createCriteria().andLeaderIdEqualTo(tLeader.getId());
		List<TLeaderQrCode> list = tleaderqrcodemanager.selectByExample(tLeaderQrCodeExample);
		if(list!=null && list.size()>=1){
			this.getRequest().setAttribute("tLeaderQrCode", list.get(0));
		}
		this.getRequest().setAttribute("tLeader", tLeader);
		//this.getRequest().setAttribute("leader", tLeader);
		
		
		String leaderUrl = InfoUtil.getInstance().getInfo("config", "leaderUrl");
		
		this.getRequest().setAttribute("leaderUrl", leaderUrl);
		
		return "single_haibao_page";
	}
	
	
	/**
	 * 自定义海报
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	public void createHaibaoCustom() throws NumberFormatException, SQLException, IOException{
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		if(tMember==null){
			ResultData resultData = new ResultData();
			resultData.setStatus(3);//跳转申请页
			resultData.setMessage("请先登录");
			this.writeObjectToResponse(resultData, ContentType.application_json);
			return;
		}
		//图片id
		String haibaoId = this.getRequest().getParameter("haibaoId");
		String leaderId = this.getRequest().getParameter("leaderId");
		ResultData resultData = new ResultData();
		if(leaderId==null || "".equals(leaderId)){
			resultData.setStatus(3);//跳转申请页
			resultData.setMessage("您尚未申请健康领秀");
			this.writeObjectToResponse(resultData, ContentType.application_json);
			return;
		}
		TLeader tLeader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
		if(tLeader==null){
			resultData.setStatus(3);//跳转申请页
			resultData.setMessage("您尚未申请健康领秀");
			this.writeObjectToResponse(resultData, ContentType.application_json);
			return;
		}
		
		if (haibaoId != null && !"".equals(haibaoId)) {
			CImage cImage = cimagemanager.selectByPrimaryKey(Integer.valueOf(haibaoId));
			if (cImage != null && cImage.getImgurl() != null) {
				// FIXME WWF(健康领袖新版海报图片存数据库)
				String backUrl = uiHttp + cImage.getImgurl();// 背景图片
				String erweimaUrl = uiHttp + tLeader.getDimensionalCodeUrl();// 生成海报上二维码地址；
				
				if (backUrl == null || erweimaUrl == null || "".equals(backUrl) || "".equals(erweimaUrl)) {
					resultData.setStatus(0);
					resultData.setMessage("您的资料尚未完善，请联系客服完善图片资料");
					this.writeObjectToResponse(resultData, ContentType.application_json);
					return;
				}
				//生成选择的海报
				String filePath = createFileCustom(backUrl, erweimaUrl,tLeader,tMember);
				//生成海报图片存/更新数据库
				TLeaderQrCodeExample tLeaderQrCodeExample = new TLeaderQrCodeExample();
				tLeaderQrCodeExample.createCriteria().andLeaderIdEqualTo(tLeader.getId()).andTypeEqualTo(1);
				List<TLeaderQrCode> list = tleaderqrcodemanager.selectByExample(tLeaderQrCodeExample);
				if (list == null || list.size() <= 0) {
					TLeaderQrCode tLeaderQrCode = new TLeaderQrCode();
					tLeaderQrCode.setCreateDt(new Date());
					tLeaderQrCode.setImgUrl(filePath);
					tLeaderQrCode.setLeaderId(tLeader.getId());
					tLeaderQrCode.setType(1);
					tleaderqrcodemanager.insertSelective(tLeaderQrCode);
				} else if (list.size() == 1) {
					TLeaderQrCode tLeaderQrCode = list.get(0);
					tLeaderQrCode.setImgUrl(filePath);
					tleaderqrcodemanager.updateByPrimaryKey(tLeaderQrCode);
				}
				resultData.setStatus(1);//跳转申请页
				resultData.setMessage("生成成功！");
				this.writeObjectToResponse(resultData, ContentType.application_json);
				return;
			}else{
				resultData.setStatus(0);//跳转申请页
				resultData.setMessage("请选择海报");
				this.writeObjectToResponse(resultData, ContentType.application_json);
				return;
			}
		}else{
			resultData.setStatus(0);//跳转申请页
			resultData.setMessage("请选择海报");
			this.writeObjectToResponse(resultData, ContentType.application_json);
			return;
		}
	}

	
	/**
	 * 生成海报
	 * @param httpBackUrl-选择海报背景图
	 * @param erweimaUrl-二维码
	 * @param tLeader-领袖信息
	 * @param tMember-用户信息
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private String createFileCustom(String httpBackUrl, String erweimaUrl, TLeader tLeader, TMember tMember)
			throws IOException, SQLException {
		// 生成海报图片；
		String nickName = tLeader.getNickName();
		String haibaoName = randomName();
		String httpHeadUrl = "";
		
		//头像图片，不存在取默认，
		if (tLeader.getHeadUrl() != null && !"".equals(tLeader.getHeadUrl() )) {
			String getHeadPortrait = tLeader.getHeadUrl() ;
			if (getHeadPortrait.startsWith("http://")) {
				httpHeadUrl = tLeader.getHeadUrl() ;// 头像
			} else {
				httpHeadUrl = uiHttp + tLeader.getHeadUrl() ;// 头像
			}
			URL url = null;
			URLConnection connection = null;
			try {
				url = new URL(httpHeadUrl);
				connection = url.openConnection();
				connection.setDoOutput(true);
				ImageIO.read(connection.getInputStream());
			} catch (MalformedURLException e) {
				e.printStackTrace();
				httpHeadUrl = defaultHead;
			} catch (IOException e) {
				e.printStackTrace();
				httpHeadUrl = defaultHead;
			}
		} else {
			httpHeadUrl = defaultHead;
		}

		// 拷贝一份海报背景到服务器；
		String tartegPath = this.getRequest().getSession().getServletContext().getRealPath("/") + savePath;
		File tartegPathFile = new File(tartegPath);
		if (!tartegPathFile.exists()) {
			tartegPathFile.mkdirs();
		}
		
//		File targetFile = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + savePath
//				+ haibaoName + ".jpg");
//		if (!targetFile.exists()) {
//			targetFile.createNewFile();
//		}
		
		URL url = new URL(httpBackUrl);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		
		
//		BufferedImage image = ImageIO.read(connection.getInputStream());
//		FileOutputStream fout = new FileOutputStream(targetFile);
//		ImageIO.write(image, "jpg", fout);
//		fout.flush();
//		fout.close();
//		System.out.println("目标文件：\t\t" + targetFile.getAbsolutePath());
		
		
		
		//TODO 海报模板确认后修改海报拼接位置
		int nickX = 161;//昵称X轴
		int nickY = 78;//昵称Y轴
		int headX = 40;//头像X轴
		int headY = 43;//头像Y轴
		int headWidth = 100;//头像宽
		int headHeight = 100;//头像高
		int erweimaX = 41;//二维码X轴
		int erweimaY = 587;//二维码Y轴
		int erweimaWidth = 183;//二维码宽
		int erweimaHeight = 183;//二维码高
	
		String backFilePath = this.getRequest().getSession().getServletContext().getRealPath("/") + savePath
				+ haibaoName + ".jpg";
		
		SharePoster.pressTextAndImgByCustom(httpBackUrl,backFilePath, httpHeadUrl, erweimaUrl, nickName,
				 nickX, nickY, headX, headY, headWidth, headHeight, erweimaX, erweimaY, erweimaWidth, erweimaHeight);
		String filePath = savePath + haibaoName + ".jpg";
		return filePath;
	}
	
	/**
	 * 保存基础信息,(生成完整的二维码,海报等信息)
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String saveHealthLeaderBaseInfo() throws IOException, NumberFormatException, SQLException{
		String phone = this.getRequest().getParameter("phone");
		String realName = this.getRequest().getParameter("realName");
		String nickName = this.getRequest().getParameter("nickName");
		String sex = this.getRequest().getParameter("sex");
		String thirdLocation = this.getRequest().getParameter("thirdLocation");
		String address = this.getRequest().getParameter("address");
		String inviteCode = this.getRequest().getParameter("inviteCode");//邀请码
		//headImage;
		if(headImage==null){
			this.writeObjectToResponse("请先上传头像", ContentType.text_html);
			return ERROR;
		}
		if(phone==null || "".equals(phone)){
			this.writeObjectToResponse("请先验证手机", ContentType.text_html);
			return ERROR;
		}
		if(realName==null || "".equals(realName)){
			this.writeObjectToResponse("姓名不能为空", ContentType.text_html);
			return ERROR;
		}
		if(nickName==null || "".equals(nickName)){
			this.writeObjectToResponse("昵称不能为空", ContentType.text_html);
			return ERROR;
		}
		if(address==null || "".equals(address)){
			this.writeObjectToResponse("地址不能为空", ContentType.text_html);
			return ERROR;
		}
		
		

		String id = this.getRequest().getParameter("id");
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		System.out.println(this.getRequest().getParameter("headImageFileName"));
		if (tMember == null) {
			return ERROR;
		}
		Map<String, Object> map = uploadImage();
		TLeader tLeader = null;
		if (id != null && !"".equals(id)) {
			tLeader = tleadermanager.selectByPrimaryKey(Long.valueOf(id));
			if (tLeader == null) {
				tLeader = new TLeader();
			}
		} else {
			if (tMember.getId() != null && tMember.getId() > 0L) {
				TLeaderExample tLeaderExample = new TLeaderExample();
				tLeaderExample.createCriteria().andMemberIdEqualTo(tMember.getId());
				List<TLeader> llLeaders = tleadermanager.selectByExample(tLeaderExample);
				if (llLeaders == null || llLeaders.size() <= 0) {
					tLeader = new TLeader();
				} else {
					tLeader = llLeaders.get(0);
				}
			} else {
				tLeader = new TLeader();
			}
		}
		if (map != null && map.get("headImage") != null) {
			tLeader.setHeadUrl(map.get("headImage").toString());
		}
		if (map != null && map.get("cardFirst") != null) {
			tLeader.setCardFirstUrl(map.get("cardFirst").toString());
		}
		if (map != null && map.get("cardTwo") != null) {
			tLeader.setCardTwoUrl(map.get("cardTwo").toString());
		}
		if (realName != null && !"".equals(realName)) {
			tLeader.setRealName(realName);
		}
		if (nickName != null && !"".equals(nickName)) {
			tLeader.setNickName(nickName);
		}
		if (sex != null && !"".equals(sex)) {
			tLeader.setSex(Integer.valueOf(sex));
		}
		if (thirdLocation != null && !"".equals(thirdLocation)) {
			tLeader.setAreaId(Long.valueOf(thirdLocation));
		}
		if (address != null && !"".equals(address)) {
			tLeader.setAddress(address);
		}
		if (phone != null && !"".equals(phone)) {
			tLeader.setPhone(phone);
		}
		
		if(inviteCode!=null && !"".equals(inviteCode)){//邀请码
			try {
				Long agentId = Long.valueOf(inviteCode);
				TLeaderAgent tLeaderAgent = tleaderagentmanager.selectByPrimaryKey(agentId);
				if(tLeaderAgent!=null){
					tMember.setAgentId(agentId);
					tmembermanager.updateByPrimaryKeySelective(tMember);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				log.error("邀请码有误,非数字");
			}
		}
		tLeader.setAuditType(3);
		tLeader.setStatus(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssms");
		String code = sdf.format(new Date()) + randomString();
		if (tLeader.getCode() == null || "".equals(tLeader.getCode())) {
			tLeader.setCode(code);
		}
		File qrCodeDir = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + qrCodeDiskPath);
		File qrCodeFile = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + qrCodeDiskPath
				+ code + ".jpg");
		if (!qrCodeDir.exists()) {
			qrCodeDir.mkdirs();
		}
		if (!qrCodeFile.exists()) {
			qrCodeFile.createNewFile();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(qrCodeFile);

		String contentString = sharedLeaderUrl + "?code=" + code + "&hurl=" + leaderUrl;
		try {
			URL url = new URL(qrLogoPath);
			URLConnection uc = url.openConnection();
			uc.getInputStream();
			QRCodeZXingUtil.encoderQRCoder_logoUrl(contentString, fileOutputStream, qrLogoPath);
		} catch (IOException e) {
			log.error("生成健康领袖logo二维码不存在,配置链接为:[" + qrLogoPath + "]");
			QRCodeUtil.encoderQRCoder(contentString, fileOutputStream, 10);
		}

		// boolean ret = QRCodeUtil.encoderQRCoder(sharedLeaderUrl + "?code=" +
		// code + "&hurl=" + leaderUrl,
		// fileOutputStream, 10);
		// if (ret) {
		tLeader.setDimensionalCodeUrl(qrCodeDiskPath + code + ".jpg");
		// }
		fileOutputStream.flush();
		fileOutputStream.close();
		Long memberId = tMember.getId();
		tLeader.setMemberId(memberId);
		tLeader.setAuditType(3);
		Long leaderId = null;
		if (tLeader.getId() == null) {
			tLeader.setCreateDt(new Date());
			leaderId = tleadermanager.insertSelective(tLeader);
		} else {
			leaderId = tLeader.getId();
			tleadermanager.updateByPrimaryKeySelective(tLeader);
		}
		TLeaderImage tLeaderImage = null;
		for (int i = 1; i <= 5; i++) {
			if (map != null && map.get(String.valueOf(("leaderImage" + String.valueOf(i)))) != null) {
				tLeaderImage = new TLeaderImage();
				tLeaderImage.setImageUrl(map.get(String.valueOf(("leaderImage" + String.valueOf(i)))).toString());
				tLeaderImage.setLeaderId(leaderId);
				tleaderimagemanager.insertSelective(tLeaderImage);
			}
		}
		
		
		// 1:头像;2:二维码;3:昵称;
		createPosterImg(code + "_" + leaderId, tMember, qrCodeFile,tLeader);//生成海报
		generateLeaderSecoundQR(tLeader);//生成第二个二维码,文章二维码
		return "goto_secound_page";
	}
	
	
	public String gotoHealthLeaderZizhi() throws SQLException{
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		if (tMember == null) {
			return ERROR;
		}
		TLeader _leader = null;
		if (tMember.getId() != null && tMember.getId() > 0) {
			TLeaderExample tLeaderExample = new TLeaderExample();
			tLeaderExample.createCriteria().andMemberIdEqualTo(tMember.getId());
			List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);
			if (list != null && list.size() > 0) {
				_leader = list.get(0);
				this.getRequest().setAttribute("tLeader", _leader);
			}
		}
		if(_leader!=null){
			return "goto_zizhi_page";
		}else{
			return "goto_apply_page";
		}
	}
	
	/**
	 * 存资质信息
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	public String saveZizhiInfo() throws NumberFormatException, SQLException, IOException{
		String cardCode = this.getRequest().getParameter("cardCode");
		String leaderId = this.getRequest().getParameter("leaderId");
		if(cardCode==null || "".equals(cardCode)){
			return ERROR;
		}
		if(leaderId==null || "".equals(leaderId)){
			return ERROR;
		}
		TLeader tLeader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
		Map<String, Object> map = uploadImage();
		if (map != null && map.get("cardFirst") != null) {
			tLeader.setCardFirstUrl(map.get("cardFirst").toString());
		}
		if (map != null && map.get("cardTwo") != null) {
			tLeader.setCardTwoUrl(map.get("cardTwo").toString());
		}
		tLeader.setCardCode(cardCode);
//		TLeaderImageExample _TLeaderImageExample = new TLeaderImageExample();
//		_TLeaderImageExample.createCriteria().andLeaderIdEqualTo(tLeader.getId());
//		tleaderimagemanager.deleteByExample(_TLeaderImageExample);
		TLeaderImage tLeaderImage = null;
		List<TLeaderImage> imageList = new ArrayList<TLeaderImage>();
		for (int i = 1; i <= 5; i++) {
			if (map != null && map.get(String.valueOf(("leaderImage" + String.valueOf(i)))) != null) {
				tLeaderImage = new TLeaderImage();
				tLeaderImage.setImageUrl(map.get(String.valueOf(("leaderImage" + String.valueOf(i)))).toString());
				tLeaderImage.setLeaderId(tLeader.getId());
				imageList.add(tLeaderImage);
			}
		}
		tLeader.setAuditType(0);
		tleadermanager.updateLeaderWithZizhi(tLeader,imageList);
		this.getRequest().setAttribute("leader", tLeader);
//		TLeader.class;
//		this.getSession().setAttribute("leader", tLeader);
		return "goto_leader_page";
	}
	
	/**
	 * 游客身份进去领袖首页
	 */
	public String gotoLeaderPageByNoAudit(){
		
		
		return "goto_leader_page_youke";
	}
	
	
	
	private void generateLeaderSecoundQR(TLeader tLeader) throws IOException {
		// 生成第二个二维码
		if (tLeader.getCode() != null && !"".equals(tLeader.getCode())) {
			// savePath存海报的路径下
			File _savePath = new File(savePath);
			String realPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();// System.getProperty("user.dir");
			if (realPath.endsWith("/WEB-INF/classes/")) {
				realPath = realPath.substring(0, realPath.length() - 17);
			}
			File secoundQRFile = new File(realPath + savePath + tLeader.getId() + "_" + tLeader.getCode() + ".jpg");
			System.out.println("文章页二维码图片路径:\t\t"
					+ (realPath + savePath + tLeader.getId() + "_" + tLeader.getCode() + ".jpg"));
			if (!_savePath.exists()) {
				_savePath.mkdirs();
			}
			File realPathFile = new File(realPath + savePath);
			if (!realPathFile.exists()) {
				realPathFile.mkdirs();
			}
			if (!secoundQRFile.exists()) {
				secoundQRFile.createNewFile();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(secoundQRFile);
			String content = secoundQR + tLeader.getCode() + "&hurl=" + java.net.URLEncoder.encode("/", "utf-8");
			try {
				URL url = new URL(qrLogoPath);
				URLConnection uc = url.openConnection();
				uc.getInputStream();
				QRCodeZXingUtil.encoderQRCoder_logoUrl(content, fileOutputStream, qrLogoPath);
			} catch (IOException e) {
				// log.error("生成健康领袖logo二维码不存在,配置链接为:[" + qrLogoPath + "]");
				QRCodeUtil.encoderQRCoder(content, fileOutputStream, 10);
			}
			fileOutputStream.flush();
			fileOutputStream.close();
		}

		// code对应的二维码,跳转海报的页面

		if (tLeader.getCode() != null && !"".equals(tLeader.getCode())) {
			String realPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();// System.getProperty("user.dir");
			if (realPath.endsWith("/WEB-INF/classes/")) {
				realPath = realPath.substring(0, realPath.length() - 17);
			}
			File codeQrDir = new File(realPath + codeQR);
			if (!codeQrDir.exists()) {
				codeQrDir.mkdirs();
			}
			File codeQRFile = new File(codeQrDir + "/" + tLeader.getCode() + ".jpg");
			if (!codeQRFile.exists()) {
				codeQRFile.createNewFile();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(codeQRFile);
			System.out.println("最新二维码路径:\t\t" + codeQRFile.getAbsolutePath());

			String content = goHaibaoUrl + "?code=" + tLeader.getCode() + "&id=" + tLeader.getId();
			try {
				URL url = new URL(qrLogoPath);
				URLConnection uc = url.openConnection();
				uc.getInputStream();
				QRCodeZXingUtil.encoderQRCoder_logoUrl(content, fileOutputStream, qrLogoPath);
			} catch (IOException e) {
				// log.error("生成健康领袖logo二维码不存在,配置链接为:[" + qrLogoPath + "]");
				QRCodeUtil.encoderQRCoder(content, fileOutputStream, 10);
			}
			fileOutputStream.flush();
			fileOutputStream.close();
		}
	}
	
	
	
	
	
	
	
	
	
	
	//新版action---------------结束-------------------------------------------------------
	
	
	
	/**
	 * 生成药房海报(新版需求)
	 * @return
	 */
	public String createPharmacyPoster(String backImgName, TMember tMember, File qrCodeFile,TLeader tLeader){
		String fontName = "宋体";
		int fontStyle = 10;
		int color = 0;
		int fontSize = 36;
		int x = 190;
		int y = 370;
//		PharmacyPosterUtil.pressTextAndImg(tLeader.getHeadUrl(), qrCodeFile, tLeader.getNickName(), backImgName, fontName, fontStyle, color, fontSize, x, y);
		
		
		
		return "";
	}
	
	public static void main(String[] args) {
		
		//http://nimenhaihaoma.com/150214211089711.jpg
		
//		createPharmacyPoster(cardFirstFileName, null, cardFirst, null);
	}
	
	

	public String randomString() {
		int i = (int) (Math.random() * 1000000 + 100000);
		String messageCode = String.valueOf(i);
		if (messageCode.length() == 7) {
			messageCode = messageCode.substring(0, 6);
		}
		return messageCode;
	}

	/**
	 * 上传图片,保存路径
	 * 
	 * @return
	 * @throws IOException
	 */
	private Map<String, Object> uploadImage() throws IOException {
		Map<String, Object> fileMap = new HashMap<String, Object>();
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssms");
		String _subFilePath = sdf.format(new Date());
		File folderFile = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + diskPath
				+ _subFilePath);
		if (!folderFile.exists()) {
			folderFile.mkdirs();
		}
		if (headImage != null && headImage.exists()) {
			String newFileName = randomFileName(headImageFileName);
			String headPath = folderFile + "/" + newFileName;
			File _image1 = new File(headPath);
			if (!_image1.exists()) {
				_image1.createNewFile();
			}
			FileUtils.copyFile(headImage, _image1);
			fileMap.put("headImage", diskPath + _subFilePath + "/" + newFileName);
		}
		if (leaderImage1 != null && leaderImage1.exists()) {
			String newFileName = randomFileName(leaderImage1FileName);
			String headPath = folderFile + "/" + newFileName;
			File _image1 = new File(headPath);
			if (!_image1.exists()) {
				_image1.createNewFile();
			}
			FileUtils.copyFile(leaderImage1, _image1);
			fileMap.put("leaderImage1", diskPath + _subFilePath + "/" + newFileName);
		}
		if (leaderImage2 != null && leaderImage2.exists()) {
			String newFileName = randomFileName(leaderImage2FileName);
			String headPath = folderFile + "/" + newFileName;
			File _image2 = new File(headPath);
			if (!_image2.exists()) {
				_image2.createNewFile();
			}
			FileUtils.copyFile(leaderImage2, _image2);
			fileMap.put("leaderImage2", diskPath + _subFilePath + "/" + newFileName);
		}
		if (leaderImage3 != null && leaderImage3.exists()) {
			String newFileName = randomFileName(leaderImage3FileName);
			String headPath = folderFile + "/" + newFileName;
			File _image3 = new File(headPath);
			if (!_image3.exists()) {
				_image3.createNewFile();
			}
			FileUtils.copyFile(leaderImage3, _image3);
			fileMap.put("leaderImage3", diskPath + _subFilePath + "/" + newFileName);
		}
		if (leaderImage4 != null && leaderImage4.exists()) {
			String newFileName = randomFileName(leaderImage4FileName);
			String headPath = folderFile + "/" + newFileName;
			File _image4 = new File(headPath);
			if (!_image4.exists()) {
				_image4.createNewFile();
			}
			FileUtils.copyFile(leaderImage4, _image4);
			fileMap.put("leaderImage4", diskPath + _subFilePath + "/" + newFileName);
		}
		if (leaderImage5 != null && leaderImage5.exists()) {
			String newFileName = randomFileName(leaderImage5FileName);
			String headPath = folderFile + "/" + newFileName;
			File _image5 = new File(headPath);
			if (!_image5.exists()) {
				_image5.createNewFile();
			}
			FileUtils.copyFile(leaderImage5, _image5);
			fileMap.put("leaderImage5", diskPath + _subFilePath + "/" + newFileName);
		}
		if (cardFirst != null && cardFirst.exists()) {
			String newFileName = randomFileName(cardFirstFileName);
			String headPath = folderFile + "/" + newFileName;
			File _cardFirst = new File(headPath);
			if (!_cardFirst.exists()) {
				_cardFirst.createNewFile();
			}
			FileUtils.copyFile(cardFirst, _cardFirst);
			fileMap.put("cardFirst", diskPath + _subFilePath + "/" + newFileName);
		}
		if (cardTwo != null && cardTwo.exists()) {
			String newFileName = randomFileName(cardTwoFileName);
			String headPath = folderFile + "/" + newFileName;
			File _cardTwo = new File(headPath);
			if (!_cardTwo.exists()) {
				_cardTwo.createNewFile();
			}
			FileUtils.copyFile(cardTwo, _cardTwo);
			fileMap.put("cardTwo", diskPath + _subFilePath + "/" + newFileName);
		}
		return fileMap;
	}

	private String randomFileName(String fileName) {
		// messageCode
		String sub = "";
		if (fileName.contains(".")) {
			sub = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssms");
		String randName = sdf.format(new Date()) + randomString();
		if (sub != null && !"".equals(sub)) {
			randName += sub;
		}
		return randName;
	}
	
	
	private String randomName() {
		// messageCode
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssms");
		String randName = sdf.format(new Date()) + randomString();
		return randName;
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

	public TLeaderImageManager getTleaderimagemanager() {
		return tleaderimagemanager;
	}

	public void setTleaderimagemanager(TLeaderImageManager tleaderimagemanager) {
		this.tleaderimagemanager = tleaderimagemanager;
	}

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

	class ResultData {
		private int status;
		private String message;

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	public CLocationManager getClocationmanager() {
		return clocationmanager;
	}

	public void setClocationmanager(CLocationManager clocationmanager) {
		this.clocationmanager = clocationmanager;
	}

	public File getHeadImage() {
		return headImage;
	}

	public void setHeadImage(File headImage) {
		this.headImage = headImage;
	}

	public File getLeaderImage1() {
		return leaderImage1;
	}

	public void setLeaderImage1(File leaderImage1) {
		this.leaderImage1 = leaderImage1;
	}

	public File getLeaderImage2() {
		return leaderImage2;
	}

	public void setLeaderImage2(File leaderImage2) {
		this.leaderImage2 = leaderImage2;
	}

	public File getLeaderImage3() {
		return leaderImage3;
	}

	public void setLeaderImage3(File leaderImage3) {
		this.leaderImage3 = leaderImage3;
	}

	public File getLeaderImage4() {
		return leaderImage4;
	}

	public void setLeaderImage4(File leaderImage4) {
		this.leaderImage4 = leaderImage4;
	}

	public File getLeaderImage5() {
		return leaderImage5;
	}

	public void setLeaderImage5(File leaderImage5) {
		this.leaderImage5 = leaderImage5;
	}

	public File getCardFirst() {
		return cardFirst;
	}

	public void setCardFirst(File cardFirst) {
		this.cardFirst = cardFirst;
	}

	public File getCardTwo() {
		return cardTwo;
	}

	public void setCardTwo(File cardTwo) {
		this.cardTwo = cardTwo;
	}

	public String getHeadImageFileName() {
		return headImageFileName;
	}

	public void setHeadImageFileName(String headImageFileName) {
		this.headImageFileName = headImageFileName;
	}

	public String getLeaderImage1FileName() {
		return leaderImage1FileName;
	}

	public void setLeaderImage1FileName(String leaderImage1FileName) {
		this.leaderImage1FileName = leaderImage1FileName;
	}

	public String getLeaderImage2FileName() {
		return leaderImage2FileName;
	}

	public void setLeaderImage2FileName(String leaderImage2FileName) {
		this.leaderImage2FileName = leaderImage2FileName;
	}

	public String getLeaderImage3FileName() {
		return leaderImage3FileName;
	}

	public void setLeaderImage3FileName(String leaderImage3FileName) {
		this.leaderImage3FileName = leaderImage3FileName;
	}

	public String getLeaderImage4FileName() {
		return leaderImage4FileName;
	}

	public void setLeaderImage4FileName(String leaderImage4FileName) {
		this.leaderImage4FileName = leaderImage4FileName;
	}

	public String getLeaderImage5FileName() {
		return leaderImage5FileName;
	}

	public void setLeaderImage5FileName(String leaderImage5FileName) {
		this.leaderImage5FileName = leaderImage5FileName;
	}

	public String getCardFirstFileName() {
		return cardFirstFileName;
	}

	public void setCardFirstFileName(String cardFirstFileName) {
		this.cardFirstFileName = cardFirstFileName;
	}

	public String getCardTwoFileName() {
		return cardTwoFileName;
	}

	public void setCardTwoFileName(String cardTwoFileName) {
		this.cardTwoFileName = cardTwoFileName;
	}

	public String getDiskPath() {
		return diskPath;
	}

	public String getQrCodeDiskPath() {
		return qrCodeDiskPath;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public CImageManager getCimagemanager() {
		return cimagemanager;
	}

	public void setCimagemanager(CImageManager cimagemanager) {
		this.cimagemanager = cimagemanager;
	}

	public TLeaderQrCodeManager getTleaderqrcodemanager() {
		return tleaderqrcodemanager;
	}

	public void setTleaderqrcodemanager(TLeaderQrCodeManager tleaderqrcodemanager) {
		this.tleaderqrcodemanager = tleaderqrcodemanager;
	}

	public TLeaderAgentManager getTleaderagentmanager() {
		return tleaderagentmanager;
	}

	public void setTleaderagentmanager(TLeaderAgentManager tleaderagentmanager) {
		this.tleaderagentmanager = tleaderagentmanager;
	}

	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}
	public ICartManager getCartmanager() {
		return cartmanager;
	}
	public void setCartmanager(ICartManager cartmanager) {
		this.cartmanager = cartmanager;
	}

}
