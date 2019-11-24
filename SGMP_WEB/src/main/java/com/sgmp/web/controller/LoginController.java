package com.sgmp.web.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgmp.web.service.LoginService;
import com.sgmp.web.vo.LoginVO;

@Controller
public class LoginController {
	@Inject
	LoginService loginservice;
	//로그인 페이지
	@RequestMapping(value="/login")
	public String login() throws Exception{
		return "login";
	}
	//로그인 확인
	@RequestMapping(value="/loginCheck")
	public String loginCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String result="login";
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		HttpSession session = request.getSession();
		
		LoginVO vo = new LoginVO();
		vo.setUser_id(user_id);
		vo.setUser_pw(user_pw);
		int check = 0;
		check = loginservice.check(vo);
		if(check==1) {
			session.setAttribute("user_id", user_id);
			result="redirect:main";
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인해주세요.');</script>");
			out.flush();
		}
		
		return result;
	}
	//로그아웃
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String result="login";
		HttpSession session = request.getSession();
		
		session.removeAttribute("user_id");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('로그아웃 되었습니다.');</script>");
		out.flush();
		
		return result;
	}
}
