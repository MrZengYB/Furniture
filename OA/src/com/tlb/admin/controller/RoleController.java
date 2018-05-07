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

import com.tlb.admin.service.RoleService;
import com.tlb.common.PageParam;
import com.tlb.entity.TTlbJs;

@Controller
@RequestMapping(value = "/admin/role.do")
public class RoleController {

	@Resource
	private RoleService roleService;
	
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletResponse response, HttpServletRequest request) 
			throws Exception {
		Map<String, Object> model = this.roleService.roleList();
		return new ModelAndView(model.get("model").toString(), model);
	}
	
	@RequestMapping(params = "data")
	public String data(HttpServletRequest request, HttpServletResponse response, PageParam page,
			@RequestParam(defaultValue = "") String keyword)
			throws IOException {
		String res = this.roleService.getRoles(page, keyword);
		response.getWriter().write(res);
		return null;
	}
	
	@RequestMapping(params = "checkmcexist")
	public String checkMcExist(HttpServletResponse response, HttpServletRequest request,
			String jsid, String mc) throws IOException {
		String res = this.roleService.toCheckMcExist(jsid, mc);
		response.getWriter().print(res);
		return null;
	}
	
	@RequestMapping(params = "add")
	public ModelAndView add(HttpServletResponse response, HttpServletRequest request) 
			throws Exception {
		Map<String, Object> model = this.roleService.addRole();
		return new ModelAndView(model.get("model").toString(), model);
	}
	
	@RequestMapping(params = "edit")
	public ModelAndView edit(HttpServletResponse response, HttpServletRequest request, String jsid) 
			throws Exception {
		Map<String, Object> model = this.roleService.editRole(jsid);
		return new ModelAndView(model.get("model").toString(), model);
	}
	
	@RequestMapping(params = "save")
	public String save(HttpServletRequest request, HttpServletResponse response, TTlbJs param, String [] menus)
			throws IOException {
		String res = this.roleService.saveRole(param, menus);
		response.getWriter().write(res);
		return null;
	}
	
	@RequestMapping(params = "checkjsused")
	public String checkJsUsed(HttpServletRequest request, HttpServletResponse response, String jsid)
			throws IOException {
		String res = this.roleService.toCheckJsUsed(jsid);
		response.getWriter().write(res);
		return null;
	}
	
	@RequestMapping(params = "delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, String jsid)
			throws IOException {
		String res = this.roleService.deleteRole(jsid);
		response.getWriter().write(res);
		return null;
	}
	
	@RequestMapping(params = "menu")
	public String menu(HttpServletRequest request, HttpServletResponse response, String jsid)
			throws IOException {
		String res = this.roleService.getActivationMenus(jsid);
		response.getWriter().write(res);
		return null;
	}
	
}
