package com.sgmp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
	//메인화면이동
	@RequestMapping(value="/main")
	public String main(HttpServletRequest request,Model model) throws Exception{
		String result="login";
		HttpSession session = request.getSession();
		
		//로그인 세션이 있는지 확인
		//세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			model.addAttribute("user_id",session.getAttribute("user_id"));
			result="main";
		}
		
		return result;	
	}
}
