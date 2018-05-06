package com.tlb.admin.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tlb.admin.service.UploadService;
import com.tlb.common.ElementConst;
import com.tlb.common.CommonUtil;
import com.tlb.common.FileUtil;

@SuppressWarnings({"rawtypes"})
@Component
public class UploadServiceImpl implements UploadService {
	
	public String uploadFile(HttpServletRequest request) {
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
		extMap.put(ElementConst.Ext_File, "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,tif,pps,ppt,pptx,gif,jpg,jpeg,png,bmp,swf,flv,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,zip,rar,pdf");
		//最大文件大小
		long maxSize = 157286400;
		//判断是否存在文件
		if(!ServletFileUpload.isMultipartContent(request)){
			return getError("请选择文件");
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			return getError("上传目录不存在");
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			return getError("上传目录没有写权限");
		}
		
		MultipartHttpServletRequest mulrequest = (MultipartHttpServletRequest) request;
		Iterator itr = mulrequest.getFileNames();
		while (itr.hasNext()) {
			FileItem item = ((CommonsMultipartFile)mulrequest.getFileMap().get((String)itr.next())).getFileItem();
			String fileName = item.getName();
			if (!item.isFormField()) {
				//检查文件大小
				if(item.getSize() > maxSize){
					return getError("上传文件大小超过限制");
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				String dirName = null;
				for(String extDir : extMap.keySet()) {
					if(Arrays.<String>asList(extMap.get(extDir).split(",")).contains(fileExt)) dirName = extDir;
				}
				if(!extMap.containsKey(dirName)){
					return getError("目录名不正确");
				}
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
				
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式");
				}
				String newFileName = CommonUtil.getUUID() + "." + fileExt;
				try{
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				}catch(Exception e){
					return getError("上传文件失败");
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", 00);
				map.put("size", item.getSize());
				map.put("name", item.getName());
				map.put("url", saveUrl + newFileName);
				JSONObject json = JSONObject.fromObject(map);
				return json.toString();
			}
		}
		return getError("上传失败");
	}

	private String getError(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", 1);
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
}
