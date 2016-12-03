package com.rc.portal.webapp.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import sun.misc.BASE64Decoder;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.freemarker.InfoUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TPrescriptionImageManager;
import com.rc.portal.service.TPrescriptionManager;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TPrescription;
import com.rc.portal.vo.TPrescriptionImage;
import com.rc.portal.vo.TPrescriptionImageExample;
import com.rc.portal.webapp.util.PageResult;

public class PrescriptionAction extends BaseAction {

	private static final long serialVersionUID = -7629780556019237948L;
	private Condition model = new Condition();
	private OpenSqlManage  opensqlmanage;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private TPrescriptionManager tprescriptionmanager;
	private String uri=InfoUtil.getInstance().getInfo("config", "ui1");
	private File imageFile1;// 资质
	private File imageFile2;//
	private File imageFile3;//
	private File imageFile4;//
	private String imageFile1FileName;//
	private String imageFile2FileName;//
	private String imageFile3FileName;//
	private String imageFile4FileName;//
	private TPrescription pre;
	private List<TPrescriptionImage> preImgList;
	private TPrescriptionImageManager tprescriptionimagemanager;
	
	
	
	
	public String getPrescriptionList(){
		Map map=new HashMap();
		TMember tm = (TMember)this.getRequest().getSession().getAttribute("member");
//		TMember tm=new TMember();
//		tm.setId(new Long(78));
		if(tm!=null){
			map.put("memberid", tm.getId());
			pw=opensqlmanage.selectForPageByMap(map, "t_prescription.selectPrescriptionCount", "t_prescription.selectPrescriptionList", rs.getP_curPage(), rs.getP_pageSize());
		}
		if(pw.getResult().size()!=0){
			return "getPrescriptionList";
		}else{
			return "getPrescriptionListNull";
		}
	}
	
	public void appendPrescriptionList() throws Exception{
		this.getRequest().setCharacterEncoding("UTF-8");
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out =this.getResponse().getWriter();
		rs.setP_pageSize(10);
		Map map=new HashMap();
		TMember tm = (TMember)this.getRequest().getSession().getAttribute("member");
//		TMember tm=new TMember();
//		tm.setId(new Long(78));
		if(tm!=null){
			map.put("memberid", tm.getId());
			pw=opensqlmanage.selectForPageByMap(map, "t_prescription.selectPrescriptionCount", "t_prescription.selectPrescriptionList", rs.getP_curPage(), rs.getP_pageSize());
		}
	
		 List <Map> result = pw.getResult();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String content="";
		 if(result.size()>0){
			 for(int i=0;i<result.size();i++){
				 content+="<dl class='case-list clearfix'><dt><a href='"+this.getRequest().getContextPath()+"/pre/prescription!toAddPerscription.action?preid="+result.get(i).get("id")+"'><img src='"+uri+result.get(i).get("image_url")+"' alt=''/></a></dt><dd>"
						 +"<div class='case-describe'><span>病情描述</span><time pubdate='pubdate'>"+sdf.format(result.get(i).get("create_date"))+"</time></div><p class='case-con'>"+result.get(i).get("disease_descrip")+"</p></dd></dl>";
			 }
		 }else{
			 content="";
		 }
			out.print(content);
			out.close();
	}
	public String toAddPerscription() throws Exception, Exception{
		if(model.getPreid()!=null&&!"".equals(model.getPreid().trim())){
			pre=tprescriptionmanager.selectByPrimaryKey(new Long(model.getPreid().trim()));
			if(pre.getId()!=null&&pre.getId()!=0){
				TPrescriptionImageExample tpie=new TPrescriptionImageExample();
				tpie.createCriteria().andPrescriptionIdEqualTo(pre.getId());
				preImgList=tprescriptionimagemanager.selectByExample(tpie);
			}
			return "toPerscriptionInfo";
		}else{
			return "toAddPerscription";
		}
	}
public void deletePerscription() throws Exception{
	this.getRequest().setCharacterEncoding("UTF-8");
	this.getResponse().setContentType("text/html;charset=utf-8");
	PrintWriter out =this.getResponse().getWriter();
	Integer flag=0;
	if(model.getPreid()!=null&&!"0".equals(model.getPreid().trim())&&!"".equals(model.getPreid().trim())){
		flag=tprescriptionmanager.deletePre(new Long(model.getPreid().trim()));
	}
	out.write(flag);
	out.close();
}
	public String addPerscription() throws Exception{
//		Map<String, Object> map =new HashMap<String, Object>();
//		Integer ff=0;
//		if(model.getImageFile1()!=null&&!"".equals(model.getImageFile1().trim())){
//			map.put("imageFile1", model.getImageFile1().trim());
//			ff=ff+1;
//		}
//		if(model.getImageFile2()!=null&&!"".equals(model.getImageFile2().trim())){
//			map.put("imageFile2", model.getImageFile2().trim());
//			ff=ff+1;
//		}
//		if(model.getImageFile3()!=null&&!"".equals(model.getImageFile3().trim())){
//			map.put("imageFile3", model.getImageFile3().trim());
//			ff=ff+1;
//		}
//		if(model.getImageFile4()!=null&&!"".equals(model.getImageFile4().trim())){
//			map.put("imageFile4", model.getImageFile4().trim());
//			ff=ff+1;
//		}
//		map.put("ff", ff);
//		List<String> pathList = getFilePath(map);
		
		//图片上传
		List<String> pathList = uploadImage();
		
		Integer flag=0;
		TMember tm = (TMember)this.getRequest().getSession().getAttribute("member");
//		TMember tm=new TMember();
//		tm.setId(new Long(78));
		if(tm!=null){
			TPrescription tPrescription = new TPrescription();
			if(model.getDiseaseDescrip()!=null&&!"".equals(model.getDiseaseDescrip().trim())){
				tPrescription.setDiseaseDescrip(model.getDiseaseDescrip().trim());
			}
			if(model.getIfhelp()!=null&&model.getIfhelp()!=-1){
				tPrescription.setIfhelp(model.getIfhelp());
			}
			tPrescription.setUserId(tm.getId());
			tPrescription.setUserName(tm.getUserName());
			tPrescription.setStatus(0);
			tPrescription.setCreateDate(new Date());
			tPrescription.setUploadTime(new Date());
			flag = tprescriptionmanager.insertPrescription(tPrescription,pathList);
		}else{
			flag=-1;//没有登录
		}
		return "topreList";
	}
	
	/**
	 * 上传图片,仅限于该类,病例图片
	 * @return
	 * @throws IOException 
	 */
	private List<String> uploadImage() throws IOException {
		List<String> resultList = new ArrayList<String>();
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");
		String filePath =InfoUtil.getInstance().getInfo("config", "pre.img.path");
		File newFilePathFile = new File(basePath+filePath); 
		if(!newFilePathFile.exists()){
			newFilePathFile.mkdir();
			newFilePathFile.mkdirs();
		}
		if (imageFile1 != null && imageFile1.exists()) {
			String filePath1 = filePath+ "/" + randomFileName(imageFile1FileName);
			File _image1 = new File(basePath+filePath1);
			if (!_image1.exists()) {
				_image1.createNewFile();
			}
			FileUtils.copyFile(imageFile1, _image1);
			resultList.add(filePath1);
		}
		if (imageFile2 != null && imageFile2.exists()) {
			String filePath2 = filePath+ "/" + randomFileName(imageFile2FileName);
			File _image2 = new File(basePath+filePath2);
			if (!_image2.exists()) {
				_image2.createNewFile();
			}
			FileUtils.copyFile(imageFile2, _image2);
			resultList.add(filePath2);
		}
		if (imageFile3 != null && imageFile3.exists()) {
			String filePath3 = filePath+ "/" + randomFileName(imageFile3FileName);
			File _image3 = new File(basePath+filePath3);
			if (!_image3.exists()) {
				_image3.createNewFile();
			}
			FileUtils.copyFile(imageFile3, _image3);
			resultList.add(filePath3);
		}
		if (imageFile4 != null && imageFile4.exists()) {
			String filePath4 =filePath+ "/" + randomFileName(imageFile4FileName);
			File _image4 = new File(basePath+filePath4);
			if (!_image4.exists()) {
				_image4.createNewFile();
			}
			FileUtils.copyFile(imageFile4, _image4);
			resultList.add(filePath4);
		}
		
		
		return resultList;
	}
	/**
	 * 生成不重复文件
	 * @param fileName
	 * @return
	 */
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
	
	public String randomString() {
		int i = (int) (Math.random() * 1000000 + 100000);
		String messageCode = String.valueOf(i);
		if (messageCode.length() == 7) {
			messageCode = messageCode.substring(0, 6);
		}
		return messageCode;
	}

	public List<String> getFilePath(Map<String, Object> map) throws IOException {
		String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");
		String filePath =InfoUtil.getInstance().getInfo("config", "pre.img.path");
		File newFilePathFile = new File(filePath); 
		if(!newFilePathFile.exists()){
			newFilePathFile.mkdir();
			newFilePathFile.mkdirs();
		}
		List<String> resultList = new ArrayList<String>();
		for (int i = 1; i < (Integer)map.get("ff")+1; i++) {
			String imgStr = map.get("imageFile"+i).toString();
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssms");
			String fileP = filePath+sdFormat.format(new Date())+".jpg";
			if (imgStr!=null) {
				imgStr = imgStr.substring(imgStr.indexOf(",")+1,imgStr.length());
				// 图像数据为空
				BASE64Decoder decoder = new BASE64Decoder();
				// Base64解码
				byte[] bytes = decoder.decodeBuffer(imgStr);
				for (int j = 0; j < bytes.length; ++j) {
					if (bytes[j] < 0) {// 调整异常数据
						bytes[j] += 256;
					}
				}
				// 生成jpeg图片
				OutputStream out = new FileOutputStream(fileP);
				out.write(bytes);
				out.flush();
				out.close();
				resultList.add(fileP);
			}
		}
		return resultList;
	}
	
	public class Condition {
		private String diseaseDescrip;
		private List<String> imageFile;
		private Integer ifhelp;
		private String preid;
		
		public String getPreid() {
			return preid;
		}
		public void setPreid(String preid) {
			this.preid = preid;
		}
		public String getDiseaseDescrip() {
			return diseaseDescrip;
		}
		public void setDiseaseDescrip(String diseaseDescrip) {
			this.diseaseDescrip = diseaseDescrip;
		}
		
		public List<String> getImageFile() {
			return imageFile;
		}
		public void setImageFile(List<String> imageFile) {
			this.imageFile = imageFile;
		}
		public Integer getIfhelp() {
			return ifhelp;
		}
		public void setIfhelp(Integer ifhelp) {
			this.ifhelp = ifhelp;
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

	public TPrescriptionManager getTprescriptionmanager() {
		return tprescriptionmanager;
	}

	public void setTprescriptionmanager(TPrescriptionManager tprescriptionmanager) {
		this.tprescriptionmanager = tprescriptionmanager;
	}

	public File getImageFile1() {
		return imageFile1;
	}

	public void setImageFile1(File imageFile1) {
		this.imageFile1 = imageFile1;
	}

	public File getImageFile2() {
		return imageFile2;
	}

	public void setImageFile2(File imageFile2) {
		this.imageFile2 = imageFile2;
	}

	public File getImageFile3() {
		return imageFile3;
	}

	public void setImageFile3(File imageFile3) {
		this.imageFile3 = imageFile3;
	}

	public File getImageFile4() {
		return imageFile4;
	}

	public void setImageFile4(File imageFile4) {
		this.imageFile4 = imageFile4;
	}

	public String getImageFile1FileName() {
		return imageFile1FileName;
	}

	public void setImageFile1FileName(String imageFile1FileName) {
		this.imageFile1FileName = imageFile1FileName;
	}

	public String getImageFile2FileName() {
		return imageFile2FileName;
	}

	public void setImageFile2FileName(String imageFile2FileName) {
		this.imageFile2FileName = imageFile2FileName;
	}

	public String getImageFile3FileName() {
		return imageFile3FileName;
	}

	public void setImageFile3FileName(String imageFile3FileName) {
		this.imageFile3FileName = imageFile3FileName;
	}

	public String getImageFile4FileName() {
		return imageFile4FileName;
	}

	public void setImageFile4FileName(String imageFile4FileName) {
		this.imageFile4FileName = imageFile4FileName;
	}

	public TPrescription getPre() {
		return pre;
	}

	public void setPre(TPrescription pre) {
		this.pre = pre;
	}

	public List<TPrescriptionImage> getPreImgList() {
		return preImgList;
	}

	public void setPreImgList(List<TPrescriptionImage> preImgList) {
		this.preImgList = preImgList;
	}

	public TPrescriptionImageManager getTprescriptionimagemanager() {
		return tprescriptionimagemanager;
	}

	public void setTprescriptionimagemanager(
			TPrescriptionImageManager tprescriptionimagemanager) {
		this.tprescriptionimagemanager = tprescriptionimagemanager;
	}

	
}
