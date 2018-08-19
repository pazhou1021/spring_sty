package com.ca.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ca.entity.UserInfo;

@Controller
public class loginController {
	private static Map<String, UserInfo> usersMap=new HashMap<String, UserInfo>();
	@RequestMapping("/login")
	public String login(HttpServletRequest req){
		UserInfo user = new UserInfo();
		user.setName("aaa");
		usersMap.put(user.getName(), user);
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("usersMap", usersMap);
		return "redirect:main.jsp";
	}
	
	
	@RequestMapping("/test")
	public String test(HttpServletRequest req){
		return "test.html";
	}
}
