package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("myTest2")
	public String myTest2(HttpSession session) {
		if(null == session.getAttribute("hi")) {
			return "login";
		}
		return "myTest2";
	}
	
	@RequestMapping("myTest")
	public String myTest(HttpSession session) {
		if(null == session.getAttribute("hi")) {
			return "login";
		}
		return "myTest";
	}
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
}
