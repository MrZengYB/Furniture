package com.tlb.admin.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tlb.admin.service.UserService;
import com.tlb.common.PageParam;
import com.tlb.entity.TTlbYh;

@Controller
@RequestMapping( value = "/admin/user.do")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> model = this.userService.toList();
		return new ModelAndView(model.get("url").toString(), model);
	}
	
	@RequestMapping(params = "datas")
	public String datas(HttpServletResponse response, HttpServletRequest request,
			PageParam page, @RequestParam(defaultValue = "") String name) throws IOException {
		String res = this.userService.getUserDatas(page, name);
		response.getWriter().print(res);
		return null;		
	}
	
	@RequestMapping(params = "add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> model = this.userService.toAdd();
		return new ModelAndView(model.get("url").toString(),model);
	}
	
	@RequestMapping(params = "checkUsernameExist")
	public String checkUsernameExist(HttpServletResponse response, HttpServletRequest request,
			String username, String yhid) throws IOException {
		String res = this.userService.checkUsernameExist(username, yhid);
		response.getWriter().print(res);
		return null;		
	}
	
	@RequestMapping(params = "save")
	public String save(HttpServletResponse response, HttpServletRequest request,
			TTlbYh param) throws IOException {
		String res = this.userService.saveUser(param);
		response.getWriter().print(res);
		return null;		
	}
	
	@RequestMapping(params = "edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,
			String yhid){
		Map<String, Object> model = this.userService.toEdit(yhid);
		return new ModelAndView(model.get("url").toString(), model);
	}
	
	@RequestMapping(params = "delete")
	public String delete(HttpServletResponse response, HttpServletRequest request,
			String yhid) throws IOException {
		String res = this.userService.deleteUser(yhid);
		response.getWriter().print(res);
		return null;		
	}
	
	@RequestMapping(params = "users")
	public String users(HttpServletResponse response, HttpServletRequest request,
			String shid) throws IOException {
		String res = this.userService.getUserList(shid);
		response.getWriter().print(res);
		return null;		
	}
}
