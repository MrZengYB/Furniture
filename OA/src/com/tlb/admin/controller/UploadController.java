package com.tlb.admin.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tlb.admin.service.UploadService;

@Controller
@RequestMapping(value = "/admin/upload.do")
public class UploadController {
	
	@Resource
	private UploadService uploadService;
	
	@RequestMapping(params = "file")
	public String uploadImg(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		String res = this.uploadService.uploadFile(request);
		response.getWriter().print(res);
		return null;
	}
	
}
