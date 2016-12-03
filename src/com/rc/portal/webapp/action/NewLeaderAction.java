package com.rc.portal.webapp.action;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.service.TMemberAmountOutManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.InfoUtil;
import com.rc.portal.util.NetworkUtil;
import com.rc.portal.util.SharePoster;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberAmountOut;
import com.rc.portal.vo.TMemberBaseMessageExt;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.vo.TMemberGrade;
import com.rc.portal.webapp.util.MD5;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.QRCodeUtil;
import com.rc.portal.webapp.util.zxing.QRCodeZXingUtil;
/**
 * 新版健康领秀
 * 2016-6-3
 * 王明
 */
@SuppressWarnings({"unchecked","rawtypes","deprecation","unused"})
public class NewLeaderAction extends BaseAction
{
	private static final long serialVersionUID = 672274541L;

	private static int PAGE_SIZE = 10;
	
	private TMemberAccountManager tmemberaccountmanager;
	
	private TMemberManager tmembermanager;
	
	private TMemberAmountOutManager tmemberamountoutmanager;
	
	private TSysParameterManager tsysparametermanager;
	
	private TMemberAmountOut amountOut;
	
	private OpenSqlManage opensqlmanage;
	
	private TLeaderManager tleadermanager;
	
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private String name ;
	private String type = "1";
	private String url;
	
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
	
	
	/**
	 * 跳转申请
	 * @return
	 */
	public String skipLeader(){
		return "skipLeader";
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
			System.out.println(messageCode);
			boolean flag = sendMessage(phone, messageCode);
			if (flag) {
				this.getSession().setAttribute("apply_health_code_" + phone, messageCode);
				System.out.println("健康领袖手机验证码:\t" + messageCode);
				writer.write("1");
			} else {
				writer.write("0");
			}
			//writer.write("1");
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
	
	public String randomString() {
		int i = (int) (Math.random() * 1000000 + 100000);
		String messageCode = String.valueOf(i);
		if (messageCode.length() == 7) {
			messageCode = messageCode.substring(0, 6);
		}
		return messageCode;
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
	 * 填写健康领袖页面
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addHealthLeaderPage() throws SQLException {
		TMember tMember = (TMember) this.getSession().getAttribute("member");
		String phone = this.getRequest().getParameter("phone");
		if (tMember == null) {
			this.getRequest().setAttribute("stype", "1");
		}
		if (null!= tMember && tMember.getId() != null && tMember.getId() > 0) {
			TLeaderExample tLeaderExample = new TLeaderExample();
			tLeaderExample.createCriteria().andMemberIdEqualTo(tMember.getId());
			List<TLeader> list = tleadermanager.selectByExample(tLeaderExample);
			if (list != null && list.size() > 0) {   //判断用户是否已是健康领秀  如果已经是领秀 直接进入领秀中心
				/**
				 * 跳转至领秀中心首页
				 */
				return "leader";
			}
			this.getRequest().setAttribute("stype", "2");
		}else{
			TMemberExample example = new TMemberExample();
			example.createCriteria().andMobileEqualTo(phone).andIsMobileCheckEqualTo(1);
			List list = tmembermanager.selectByExample(example);
			if(null !=list && list.size()>0){
				this.getRequest().setAttribute("stype", "2");
			}else{
				this.getRequest().setAttribute("stype", "1");
			}
		}
		
		this.getSession().removeAttribute("apply_health_code_" + phone);
		this.getRequest().setAttribute("phone", phone);
		return "add_healthleader_basicPage";
		
	}

	/**
	 * 注册领秀
	 * @return
	 * @throws SQLException 
	 */
	public  String  regLeader()  throws IOException, SQLException {
		String password = this.getRequest().getParameter("password");
		String nickName = this.getRequest().getParameter("nickName");
		String realName = this.getRequest().getParameter("realName");
		String phone = this.getRequest().getParameter("phone");
		String stype = this.getRequest().getParameter("stype");
		
		TLeaderExample tle = new TLeaderExample();
		tle.createCriteria().andPhoneEqualTo(phone);
		List listLeader = tleadermanager.selectByExample(tle);
		if(null != listLeader && listLeader.size()>0){  //手机已经注册过领秀
			/**
			 * 跳转至领秀中心首页
			 */
			return "leader";
		}
		TMemberExample example = new TMemberExample();
		example.createCriteria().andMobileEqualTo(phone).andIsMobileCheckEqualTo(1);
		List list = tmembermanager.selectByExample(example);
		if(null!=list && list.size()>0){  //已有用户
			TMember tmember= (TMember) list.get(0);  //将领秀绑定到已有官网用户上
			TLeader tLeader = new TLeader();
			TMember member = (TMember) this.getSession().getAttribute("member"); 
			//判断用户是否登录 如果登录取登录用户ID 未登录取已注册用户ID
			if(null != member && null != member.getId()){
				tLeader.setMemberId(member.getId());
			}else{
				tLeader.setMemberId(tmember.getId());
				this.getSession().setAttribute("member", tmember);
			}
			tLeader.setRealName(realName);
			tLeader.setNickName(nickName);
			tLeader.setPhone(phone);
			tLeader.setStatus(1);
			tLeader.setCreateDt(new Date());
			tLeader.setAuditType(0);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssms");
			String code = sdf.format(new Date()) + randomString();
			tLeader.setCode(code);
            /**
             * 生成领秀二维码
             */
			File qrCodeDir = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + qrCodeDiskPath);
			File qrCodeFile = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + qrCodeDiskPath
					+ code + ".jpg");
//			createLeaderCode(code,qrCodeDir,qrCodeFile);
			tLeader.setDimensionalCodeUrl(qrCodeDiskPath + code + ".jpg");
			tleadermanager.insertSelectiveLeaderAndGoods(tLeader);
//			createPosterImg(code + "_" + tLeader.getId(), tmember, qrCodeFile,tLeader);
			generateLeaderSecoundQR(tLeader);//生成第二个二维码,文章二维码
		}else{  //没有注册用户
			TMemberGrade memberGrade1 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectDefaultGrade");
			TMember tmember=new TMember();
			//创建官网用户
			tmember.setMobile(phone);
			tmember.setPassword(MD5.MD5("111"+password+"yao"));
			if(memberGrade1!=null){
				tmember.setMemberGradeId(memberGrade1.getId());
			}else{
				TMemberGrade memberGrade2 = (TMemberGrade) opensqlmanage.selectObjectByObject(null, "t_member_grade.selectlowGrade");
				tmember.setMemberGradeId(memberGrade2.getId());
			}
			tmember.setUserName("yyg_"+phone);
			tmember.setAreaId(0l);
			tmember.setStatus(0);
			tmember.setEnterpriseDiscount(new BigDecimal(0));
			tmember.setIsLeader(0);
			tmember.setIntegral(0);
			tmember.setSource(5);//5表示手机注册
			tmember.setPlatform(2);//2表示WAP平台
			tmember.setRegisterIp(NetworkUtil.getIpAddress(this.getRequest()));
			tmember.setRegisterDate(new Date());
			tmember.setLastDate(new Date());
			tmember.setLastIp(NetworkUtil.getIpAddress(this.getRequest()));
			tmember.setIsEmailCheck(0);
			tmember.setIsMobileCheck(1);
			//tmember.setAgentId(getAgentId());
			tmember.setIsAgent(0);
			//创建健康领袖
			TLeader tLeader = new TLeader();
			tLeader.setRealName(realName);
			tLeader.setNickName(nickName);
			tLeader.setPhone(phone);
			tLeader.setStatus(1);
			tLeader.setAuditType(0);
			tLeader.setCreateDt(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssms");
			String code = sdf.format(new Date()) + randomString();
			tLeader.setCode(code);
            /**
             * 生成领秀二维码
             */
			File qrCodeDir = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + qrCodeDiskPath);
			File qrCodeFile = new File(this.getRequest().getSession().getServletContext().getRealPath("/") + qrCodeDiskPath
					+ code + ".jpg");
//			createLeaderCode(code,qrCodeDir,qrCodeFile);
			tLeader.setDimensionalCodeUrl(qrCodeDiskPath + code + ".jpg");
			
			//官网用户附属表
			TMemberBaseMessageExt tmemberBaseMessageext=new TMemberBaseMessageExt();
			tmemberBaseMessageext.setNickName(phone);
			tleadermanager.savetLeaderAndMember(tmember, tLeader, tmemberBaseMessageext);
			
//			createPosterImg(code + "_" + tLeader.getId(), tmember, qrCodeFile,tLeader);
			generateLeaderSecoundQR(tLeader);//生成第二个二维码,文章二维码
			System.out.println("================成功注册");
			this.getSession().setAttribute("member", tmember);
		}
		this.getSession().setAttribute("leader_first", "1");
		return "welcome";
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
		//海报背景图修改;改成
		SharePoster.pressTextAndImg(httpHeadFile, qrCodeFile, nickName, _backImgName, fontName, fontStyle, color,
				fontSize, x, y);
		
		
		
	}
	
	
	/**
	 * 生成领秀二维码
	 * @throws IOException 
	 */
	public void createLeaderCode(String code,File qrCodeDir,File qrCodeFile) throws IOException {

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
		fileOutputStream.flush();
		fileOutputStream.close();
	}
	
	
	/**
	 * 生成海报及相关(请勿复用),存qrcode图片信息
	 * @param tLeader
	 * @throws IOException
	 */
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

	
	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

	
	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
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
