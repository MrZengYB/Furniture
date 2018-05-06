package com.tlb.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tlb.common.ElementConst;

public class FileUtil {
	/**
	 * 反斜杆.
	 */
	public static final String speratorAnti = "/";
	
	/**
	 * 获取WebContent根目录地址.
	 */
	public static String getWebContent() {
		String path = FileUtil.class.getResource("/").getPath();
		path = path.substring(1);
		File file = new File(path);
		return "/"+file.getParentFile().getParent();
	}
	
	/**
	 * 获取WEB-INF目录地址.
	 */
	public static String getRoot() {
		String path = FileUtil.class.getResource("/").getPath();
		path = path.substring(1);
		File file = new File(path);
		return "/"+file.getParent();
	}
	
	/**
	 * 删除文件.
	 */
	public static void deleteFile(String strDir, String fileName)
	{
		String targetPath = FileUtil.getWebContent()+ strDir;
		File file = new File(targetPath + File.separator + fileName);
		if (file.exists()) file.delete();
	}
	
	/**
	 * 保存文件(上传).
	 */
	public static String uploadFile(CommonsMultipartFile upload) {
		//文件保存目录路径
		String savePath = FileUtil.getWebContent() + File.separator 
				+ ElementConst.File_Target_Dir + File.separator + ElementConst.File_Save_Dir;
		//文件保存目录URL
		String saveUrl  = FileUtil.speratorAnti 
				+ ElementConst.File_Target_Dir + FileUtil.speratorAnti + ElementConst.File_Save_Dir;
		//定义允许上传的文件扩展名
		String imgext = "gif,jpg,jpeg,png,bmp";
		String flashext = "swf,flv";
		String mediaext = "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4,ogg";
		
		FileItem item = upload.getFileItem();
		String fileName = item.getName();
		//检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		String newFileName = CommonUtil.getUUID() + "." + fileExt;
		
		String dirName = ElementConst.Ext_File;
		if(imgext.indexOf(fileExt) != -1) dirName = ElementConst.Ext_Image;
		if(flashext.indexOf(fileExt) != -1) dirName = ElementConst.Ext_Flash;
		if(mediaext.indexOf(fileExt) != -1) dirName = ElementConst.Ext_Media;
		
		//创建文件夹
		savePath += File.separator + dirName + FileUtil.speratorAnti;
		saveUrl += FileUtil.speratorAnti + dirName + FileUtil.speratorAnti;
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		try{
			File uploadedFile = new File(savePath, newFileName);
			item.write(uploadedFile);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return saveUrl + newFileName;
	}
	
	/**
	 * 保存文件(MultipartFile上传).
	 */
	public static Map<String, String> uploadFileByMap(MultipartFile upload, String folderName) {
		//文件保存目录路径
		String savePath = FileUtil.getWebContent() + File.separator 
				+ ElementConst.File_Target_Dir + File.separator + ElementConst.File_Save_Dir;
		//文件保存目录URL
		String saveUrl  = FileUtil.speratorAnti 
				+ ElementConst.File_Target_Dir + FileUtil.speratorAnti + ElementConst.File_Save_Dir;
		//定义允许上传的文件扩展名
		Map<String, String> extMap = new HashMap<String, String>();
		extMap.put(ElementConst.Ext_Image, "gif,jpg,jpeg,png,bmp");
		extMap.put(ElementConst.Ext_Flash, "swf,flv");
		extMap.put(ElementConst.Ext_Media, "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put(ElementConst.Ext_File, "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,tif,pps,ppt,pptx,gif,jpg,jpeg,png,bmp,swf,flv,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		
		//创建文件夹
		savePath += File.separator + folderName + FileUtil.speratorAnti;
		saveUrl += FileUtil.speratorAnti + folderName + FileUtil.speratorAnti;
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		//创建年月日文件夹分存
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		//FileItem item = upload.getFileItem();
		//String fileName = item.getName();
		String fileName = upload.getOriginalFilename();
		//检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		String newFileName = CommonUtil.getUUID() + "." + fileExt;
		try{
			File uploadedFile = new File(savePath, newFileName);
			//item.write(uploadedFile);
			upload.transferTo(uploadedFile);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("realPath", savePath + newFileName);//全路径+文件名
		map.put("newFileName", newFileName);
		map.put("saveUrl", saveUrl);//项目下的文件夹路径
		map.put("realUrl", saveUrl + newFileName);//文件保存目录URL
		map.put("savePath", savePath);//全路径文件夹
		return map;
	}
}
