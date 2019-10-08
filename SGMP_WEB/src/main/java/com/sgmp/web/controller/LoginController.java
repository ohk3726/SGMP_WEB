package com.sgmp.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgmp.web.service.LoginService;
import com.sgmp.web.vo.LoginVO;

@Controller
public class LoginController {
	@Inject
	LoginService loginservice;
	//로그인 화면 이동
	@RequestMapping(value="/login")
	public String login() throws Exception{
		return "login";
	}
	//로그인 체크
	@RequestMapping(value="/loginCheck")
	public String loginCheck(HttpServletRequest request) throws Exception{
		String result="login";
		LoginVO vo = new LoginVO();
		vo.setUser_id(request.getParameter("user_id"));
		vo.setUser_pw(request.getParameter("user_pw"));
		int check = 0;
		check = loginservice.check(vo);
		if(check==1) {
			result="main";
		}
		
		return result;
	}
}
