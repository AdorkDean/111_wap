package com.rc.portal.webapp.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import com.rc.portal.service.CLocationManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderImageManager;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.SharePoster;
import com.rc.portal.vo.CLocation;
import com.rc.portal.vo.CLocationExample;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TLeaderImage;
import com.rc.portal.vo.TLeaderImageExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.webapp.util.QRCodeUtil;
import com.rc.portal.webapp.util.zxing.QRCodeZXingUtil;

/**
 * 健康领袖Action
 * 
 * @author user00
 * @createTime 2015-9-17 下午6:38:49
 */
public class AppHealthLeaderAction extends BaseAction {

	private static final long serialVersionUID = 8629590925309040239L;

	private TLeaderManager tleadermanager;

	private TLeaderImageManager tleaderimagemanager;

	private TSysParameterManager tsysparametermanager;

	private OpenSqlManage opensqlmanage;

	private CLocationManager clocationmanager;
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

	/**
	 * 跳转健康领袖页面(判断)
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String healthLeader() throws SQLException {
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		System.out.println("【healthLeader方法取session】" + tMember);
		// FIXME WWF(app)跳转session为空待处理
		if (tMember == null) {
			// tMember = new TMember();
			// tMember.setId(78L);
			// this.getSession().setAttribute("member", tMember);
			return ERROR;
		}
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andMemberIdEqualTo(tMember.getId());
		List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);
		System.out.println("【healthLeader方法取领袖】" + list);
		if (list == null || list.size() <= 0) {// 跳转申请页
			return "apply_health_leader";
		} else {
			TLeader tLeader = list.get(0);
			this.getRequest().setAttribute("tLeader", tLeader);
			if (tLeader.getAuditType() != null && tLeader.getAuditType() == 1) {// 存在,审核通过,领袖中心
				return "health_leader";
			} else {// 审核等待页面
				return "wait_health_leader";
			}
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
		return "add_healthleader_page";
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
	 * app接口调用存session
	 */
	public String appSaveSession() {
		String sessionId = this.getRequest().getParameter("sessionId");
		System.out.println("【appSaveSession方法】" + sessionId);
		if (sessionId == null || "".equals(sessionId)) {
			// FIXME WWF(wap)app跳转友好页面
			return "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		TMember tMember = (TMember) opensqlmanage.selectForObjectByMap(map, "t_member_session.selectMemberBySessionId");
		System.out.println("【appSaveSession方法查询member】" + tMember);
		if (tMember != null) {
			this.getSession().setAttribute("member", tMember);
			return "app_health_leader";
		} else {
			// tMember = new TMember();
			// tMember.setId(285L);
			// this.getSession().setAttribute("member", tMember);
			// FIXME WWF(wap)app跳转友好页面
			return "app_health_leader";
		}
	}

	/**
	 * 二维码页面
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public String qrCodePage() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		if (id == null || "".equals(id.trim())) {
			return "health_leader_page";
		}
		TLeaderExample tLeaderExample = new TLeaderExample();
		tLeaderExample.createCriteria().andIdEqualTo(Long.valueOf(id)).andAuditTypeEqualTo(1);
		List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);

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
		createPosterImg(code + "_" + leaderId, tMember, qrCodeFile);
		return "apply_health_leader_success";
	}

	/**
	 * 生成海报文件
	 */
	// public void createPosterImg(String httpHeadFile, String httpQRCodeFile,
	// String nickName, String httpBackImg) {
	private void createPosterImg(String backImgName, TMember tMember, File qrCodeFile) {
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
		if (tMember.getHeadPortrait() != null && !"".equals(tMember.getHeadPortrait())) {
			String getHeadPortrait = tMember.getHeadPortrait();
			if (getHeadPortrait.startsWith("http://")) {
				httpHeadFile = tMember.getHeadPortrait();// 头像
			} else {
				httpHeadFile = uiHttp + tMember.getHeadPortrait();// 头像
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
		String nickName = tMember.getNickName() == null ? tMember.getUserName() : tMember.getNickName();
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
		String messageCode = randomString();
		boolean flag = sendMessage(phone, messageCode);
		if (flag) {
			this.getSession().setAttribute("apply_health_code_" + phone, messageCode);
			System.out.println("健康领袖手机验证码:\t" + messageCode);
			ResultData resultData = new ResultData();
			resultData.setMessage("已发送");
			resultData.setStatus(1);
			this.writeObjectToResponse(resultData, ContentType.application_json);
		} else {
			ResultData resultData = new ResultData();
			resultData.setMessage("未发送成功");
			resultData.setStatus(0);
			this.writeObjectToResponse(resultData, ContentType.application_json);
		}
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

}
