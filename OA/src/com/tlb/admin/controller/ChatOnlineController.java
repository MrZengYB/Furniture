package com.tlb.admin.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tlb.admin.service.ChatOnlineService;

@Controller
@RequestMapping(value = "/admin/chatOnline.do")
public class ChatOnlineController {
	
	@Resource
	private ChatOnlineService chatOnlineService;
	
	@RequestMapping(params = "index")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> model = this.chatOnlineService.toIndex();
		return new ModelAndView(model.get("url").toString(), model);
	}
}
