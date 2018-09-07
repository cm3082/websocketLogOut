package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo.session.MySessionContext;
import com.example.demo.utils.RedisUtil;
import com.example.demo.websocket.WebSocketServer;

@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	RedisUtil redisUtil;
	
	@RequestMapping(value="log")
	public User user(String username, String password, HttpServletRequest request, HttpSession session) {
		String last_sessionId = redisUtil.get(username);
		String this_sessionId = session.getId();
		session.setAttribute("hi", username);
		if(null == last_sessionId) {
			redisUtil.set(username, this_sessionId, 60*30);
		}else {
			
			if(!last_sessionId.equals(this_sessionId)) {
				redisUtil.set(username, this_sessionId, 60*30);
				MySessionContext myc= MySessionContext.getInstance();  
				HttpSession sess = myc.getSession(last_sessionId); 
				if(null!=sess) {
					sess.removeAttribute("hi");
			        try {  
			            WebSocketServer.SendMessage(last_sessionId,"你的账号在别处登录");  
			        } catch (IOException e) {  
			            e.printStackTrace();  
			        }  
				}
			}
		}
		
		return userMapper.selectUserByUsernameAndPassword(username,password);
	}
	
	

}
