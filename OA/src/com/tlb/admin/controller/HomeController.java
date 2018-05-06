package com.tlb.admin.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tlb.admin.service.HomeService;

@Controller
@RequestMapping(value = "/admin/home.do")
public class HomeController {
	
	@Resource
	private HomeService homeService;
	
	@RequestMapping(params = "login")
	public ModelAndView login(HttpServletResponse response,HttpServletRequest request) throws IOException {
		Map<String, Object> model = this.homeService.getLoginPath();
		return new ModelAndView(model.get("model").toString(),model);
	}
	
	@RequestMapping(params = "homePage")
	public ModelAndView homePage(HttpServletResponse response,HttpServletRequest request) throws IOException {
		Map<String, Object> model = this.homeService.getHomePagePath();
		return new ModelAndView(model.get("model").toString(),model);
	}
	
	@RequestMapping(params = "user")
	public String user(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String res = this.homeService.getTTlbYh();
		response.getWriter().print(res);
		return null;		
	}

	@RequestMapping(params = "menus")
	public String menus(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String res = this.homeService.getMenus();
		response.getWriter().print(res);
		return null;
	}
}
