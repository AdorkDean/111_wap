package com.rc.portal.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.rc.commons.util.InfoUtil;
import com.rc.portal.dao.TLeaderQrCodeDAO;
import com.rc.portal.service.TLeaderQrCodeManager;
import com.rc.portal.util.LeaderPosterUtil;
import com.rc.portal.util.PharmacyPosterUtil;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderQrCode;
import com.rc.portal.vo.TLeaderQrCodeExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.webapp.util.zxing.QRCodeZXingUtil;

public class TLeaderQrCodeManagerImpl  implements TLeaderQrCodeManager {

	private TLeaderQrCodeDAO tleaderqrcodedao;
	// 图片链接地址前缀;
	private final String uiHttp = InfoUtil.getInstance().getInfo("healthleader", "healthleader.haibao.ui.http");;
	// 海报存储地址
	private final String savePath = InfoUtil.getInstance().getInfo("healthleader", "healthleader.haibao.save.path");;

	//海报里二维码中间的logo默认图
	private final String defaultHead = InfoUtil.getInstance().getInfo("healthleader",
			"healthleader.haibao.default.head");
	// 药房海报背景图
	private final String yaofangback = InfoUtil.getInstance().getInfo("healthleader",
			"healthleader.yaofang.haibao.background.img");
	
	//http://192.168.101.142:8080/111_wap/leader/leaderPharmacy!leaderSharePharmacy.action?leaderCode=201511301437183718101670
	private final String YAOFANGQRURL = InfoUtil.getInstance().getInfo("healthleader",
			"healthleader.yaofang.haibao.url");
	
	
	//领袖海报内二维码链接
	private final String leader_sharedLeaderUrl = InfoUtil.getInstance().getInfo("config", "shareLeaderUrl");
	private final String leader_leaderUrl = InfoUtil.getInstance().getInfo("config", "leaderUrl");
	// 领袖背景图
	private final String leader_defaultBackground = InfoUtil.getInstance().getInfo("healthleader",
			"healthleader.haibao.background.img");
			
	
    public TLeaderQrCodeManagerImpl() {
        super();
    }
    public void  setTleaderqrcodedao(TLeaderQrCodeDAO tleaderqrcodedao){
        this.tleaderqrcodedao=tleaderqrcodedao;
    }
    public TLeaderQrCodeDAO getTleaderqrcodedao(){
        return this.tleaderqrcodedao;
    }
    public     int countByExample(TLeaderQrCodeExample example) throws SQLException{
        return tleaderqrcodedao. countByExample( example);
    }

    public     int deleteByExample(TLeaderQrCodeExample example) throws SQLException{
        return tleaderqrcodedao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tleaderqrcodedao. deleteByPrimaryKey( id);
    }

    public     Long insert(TLeaderQrCode record) throws SQLException{
        return tleaderqrcodedao. insert( record);
    }

    public     Long insertSelective(TLeaderQrCode record) throws SQLException{
        return tleaderqrcodedao. insertSelective( record);
    }

    public     List selectByExample(TLeaderQrCodeExample example) throws SQLException{
        return tleaderqrcodedao. selectByExample( example);
    }

    public     TLeaderQrCode selectByPrimaryKey(Long id) throws SQLException{
        return tleaderqrcodedao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TLeaderQrCode record, TLeaderQrCodeExample example) throws SQLException{
        return tleaderqrcodedao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TLeaderQrCode record, TLeaderQrCodeExample example) throws SQLException{
        return tleaderqrcodedao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TLeaderQrCode record) throws SQLException{
        return tleaderqrcodedao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TLeaderQrCode record) throws SQLException{
        return tleaderqrcodedao. updateByPrimaryKey( record);
    }
    
    
    /**
     * 生成二维码(药房海报),药房海报
     * @throws IOException 
     * @throws SQLException 
     */
	@Override
	public Long createPharmacyPoster(TMember tMember, TLeader tLeader,String qianzhui) throws IOException, SQLException {
		// 生成海报里的二维码,存库;生成海报,存库;
		String yuanPath = yaofangback;//"E:\\temp\\aaa.jpg";//背景图片,物理地址
		String content = YAOFANGQRURL+"?leaderCode="+tLeader.getCode();
		File codeQrDir = new File(qianzhui+savePath);
		if (!codeQrDir.exists()) {
			codeQrDir.mkdirs();
		}
		String saveQrCode = randomDateName() + ".jpg";
		File codeQRFile = new File(codeQrDir + "/" + saveQrCode);// 药房二维码;
		if (!codeQRFile.exists()) {
			codeQRFile.createNewFile();
		}
		
		String headLogo = uiHttp+tLeader.getHeadUrl();//头像logo
		try {
			URL url = new URL(headLogo);
			URLConnection uc = url.openConnection();
			uc.getInputStream();
		} catch (IOException e) {//默认logo
//			headLogo = "http://img.zdfei.com/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png";
//			headLogo = "http://img.zdfei.com/static/image/head//ee2e9c2c-e915-43bd-8a65-a05f29029cb2.jpg";
			headLogo = defaultHead;
		}finally{
			
		}
		FileOutputStream fileOutputStream = new FileOutputStream(codeQRFile);
		QRCodeZXingUtil.encoderQRCoder_logoUrl(content, fileOutputStream, headLogo);
		TLeaderQrCode tLeaderQrCode = new TLeaderQrCode();
		tLeaderQrCode.setCreateDt(new Date());
		tLeaderQrCode.setImgUrl(savePath+saveQrCode);
		tLeaderQrCode.setLeaderId(tLeader.getId());
		tLeaderQrCode.setType(3);//药房海报里的二维码
		tleaderqrcodedao.insertSelective(tLeaderQrCode);
		
		int fontStyle = 10;
		int color = 0;
		int fontSize = 24;
		int x = 190;
		int y = 370;
		String fontName = "微软雅黑";
		
		
		URL headUrl = new URL(yuanPath);
//		File yuanFile = new File(yuanPath);
		String haibaoSavepath = savePath+randomDateName()+".jpg";
		File newFile = new File(qianzhui+haibaoSavepath);
		if(!newFile.exists()){
			newFile.createNewFile();
		}
		FileUtils.copyURLToFile(headUrl, newFile);
		
		PharmacyPosterUtil.pressTextAndImg(headLogo, // 头像
				codeQRFile, //二维码
				tLeader.getNickName(), // 昵称
				newFile, //目标文件,待写文件
				fontName,// 字体
				fontStyle, 
				color, 
				fontSize, 
				x, 
				y);
		
		TLeaderQrCode haibao = new TLeaderQrCode();
		haibao.setCreateDt(new Date());
		haibao.setImgUrl(haibaoSavepath);
		haibao.setLeaderId(tLeader.getId());
		haibao.setType(2);//药房海报
		Long qrId = tleaderqrcodedao.insertSelective(haibao);
		return qrId;
	}
	
	private String randomDateName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssms");
		String randName = sdf.format(new Date()) + randomString();
		return randName;
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
	 * 生成海报
	 */
	@Override
	public Long createLeaderPoster(TMember tMember, TLeader tLeader, String qianzhui) throws IOException, SQLException {
		Long qrId = 0L;
		//1:生成领袖海报内的二维码;
		String contentString = leader_sharedLeaderUrl + "?code=" + tLeader.getCode() + "&hurl=" + leader_leaderUrl;
		File codeQrDir = new File(qianzhui+savePath);
		if (!codeQrDir.exists()) {
			codeQrDir.mkdirs();
		}
		String saveQrCode = randomDateName() + ".jpg";
		File codeQRFile = new File(codeQrDir + "/" + saveQrCode);// 药房二维码;
		if (!codeQRFile.exists()) {
			codeQRFile.createNewFile();
		}
		String headLogo = uiHttp+tLeader.getHeadUrl();//头像logo
		try {
			URL url = new URL(headLogo);
			URLConnection uc = url.openConnection();
			uc.getInputStream();
		} catch (IOException e) {//默认logo
			headLogo = defaultHead;
		}finally{
		}
		FileOutputStream fileOutputStream = new FileOutputStream(codeQRFile);
		QRCodeZXingUtil.encoderQRCoder_logoUrl(contentString, fileOutputStream, headLogo);
		TLeaderQrCode tLeaderQrCode = new TLeaderQrCode();
		tLeaderQrCode.setCreateDt(new Date());
		tLeaderQrCode.setImgUrl(savePath+saveQrCode);
		tLeaderQrCode.setLeaderId(tLeader.getId());
		tLeaderQrCode.setType(4);//药房海报里的二维码
		tleaderqrcodedao.insertSelective(tLeaderQrCode);
		//2:生成领袖海报
		
		
		int fontStyle = 10;
		int color = 0;
		int fontSize = 24;
		int x = 190;
		int y = 370;
		String fontName = "微软雅黑";
		
		
		
		String yuanPath = leader_defaultBackground;//"E:\\temp\\aaa.jpg";//背景图片,物理地址
		
		URL headUrl = new URL(yuanPath);
//		File yuanFile = new File(yuanPath);
		String haibaoSavepath = savePath+randomDateName()+".jpg";
		File newFile = new File(qianzhui+haibaoSavepath);
		if(!newFile.exists()){
			newFile.createNewFile();
		}
		FileUtils.copyURLToFile(headUrl, newFile);
		
		LeaderPosterUtil.pressTextAndImg(headLogo, // 头像
				codeQRFile, //二维码
				tLeader.getNickName(), // 昵称
				newFile, //目标文件,待写文件
				fontName,// 字体
				fontStyle, 
				color, 
				fontSize, 
				x, 
				y);
		
		TLeaderQrCode haibao = new TLeaderQrCode();
		haibao.setCreateDt(new Date());
		haibao.setImgUrl(haibaoSavepath);
		haibao.setLeaderId(tLeader.getId());
		haibao.setType(1);//药房海报
		qrId = tleaderqrcodedao.insertSelective(haibao);
		return qrId;
	}
}
