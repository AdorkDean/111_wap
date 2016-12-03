package com.rc.portal.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.rc.commons.util.InfoUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 生成领秀海报
 * @author WWF
 * @createTime 2016-7-7 下午5:01:03
 */
public final class LeaderPosterUtil {
	private final static String pharmacy_haibao_kuang = InfoUtil.getInstance().getInfo("healthleader", "healthleader.yaofang.haibao.background.kuang");
	
	public LeaderPosterUtil() {
	}

	/**
	 * 
	 * @param httpHeadFile:头像
	 * @param qrCodeFile:二维码图片
	 * @param nickName:昵称
	 * @param backImg:本地背景图片(待写目标文件)
	 * @param fontName:字体
	 * @param fontStyle:字体样式
	 * @param color:颜色
	 * @param fontSize:字体大小
	 * @param x:x轴坐标
	 * @param y:y轴坐标
	 * @throws IOException 
	 */
	public static void pressTextAndImg(String httpHeadFile, File qrCodeFile, String nickName, File backImg,
			String fontName, int fontStyle, int color, int fontSize, int x, int y) throws IOException {
			// URL backImgUrl = new URL(httpBackImg);
//			File backFile = new File(backImg);
//			System.out.println("背景图片生成是否存在:" + backFile.exists());
			Image src = ImageIO.read(backImg);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			// 昵称
			g.setColor(Color.white);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.drawString(nickName, 290, 66);// 此处匹配唯一背景图片的位置,

			// 二维码图片
			// URL qrUrl = new URL(httpQRCodeFile);
			Image src_biao = ImageIO.read(qrCodeFile);
			g.drawImage(src_biao, 222, 234, 190, 190, null);// 此处匹配唯一背景图片的位置,

			// 头像图片
			URL headUrl = new URL(httpHeadFile);
			Image touxiangFilebiao_src_biao = ImageIO.read(headUrl);
			g.drawImage(touxiangFilebiao_src_biao, 195, 13, 80, 80, null);// 此处匹配唯一背景图片的位置,
			URL headUrl1 = new URL(pharmacy_haibao_kuang);
			Image kuang = ImageIO.read(headUrl1);
			g.drawImage(kuang, 194, 13, 82, 82, null);// 此处匹配唯一背景图片的位置,
			
			g.dispose();
			FileOutputStream out = new FileOutputStream(backImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.flush();
			out.close();
	}

	public static void main(String[] args) throws IOException {
//		String httpHeadFile, File qrCodeFile, String nickName, String httpBackImg,
//		String fontName, int fontStyle, int color, int fontSize, int x, int y
		//http://ui1.111yao.com/static/image/head/ee2e9c2c-e915-43bd-8a65-a05f29029cb2.jpg
//		File qrCodeFile = new File("http://img.zdfei.com/static/image/codeqr/20151016232400240508162.jpg");
		File qrCodeFile = new File("E:\\temp\\haibao\\qr.jpg");
		
		
		
		int fontStyle = 10;
		int color = 0;
		int fontSize = 24;
		int x = 190;
		int y = 370;
		
		File newFile = new File("E:\\temp\\haibao\\a8.png");
		
//		for (int i = 2; i < 20; i++) {
//			File destFile = new File("E:\\temp\\haibao\\a"+i+".png");
//			FileUtils.copyFile(newFile, destFile);
//		}
		pressTextAndImg("http://www.111yao.com/static/image/head/3da8ba51-b641-411b-b79e-3ba7824bba9f.jpg", 
				qrCodeFile, "小魏", newFile, "微软雅黑", fontStyle, color, fontSize, x, y);
	}

}