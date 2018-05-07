package com.tlb.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tlb.admin.service.ChatOnlineService;

@Component
public class ChatOnlineServiceImpl implements ChatOnlineService{

	@Transactional(readOnly = true)
	public Map<String, Object> toIndex() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("url", "/admin/chatOnline/chatOnline_index");
		return model;
	}

}
