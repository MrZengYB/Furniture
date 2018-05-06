package com.tlb.admin.service;

import javax.servlet.http.HttpServletRequest;

public interface UploadService {

	/**
	 * 普通方式上传文件
	 * @param request
	 * @return 结果json串
	 */
	public String uploadFile(HttpServletRequest request);
	
}
