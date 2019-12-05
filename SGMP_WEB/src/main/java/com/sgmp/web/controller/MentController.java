package com.sgmp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sgmp.web.service.MentService;
import com.sgmp.web.vo.MentVO;

@Controller

public class MentController {
	
	@Resource(name = "MentService")
	private MentService mentservice;
	
	//��� �Է�
	@ResponseBody
	@RequestMapping("insert")
	public void insert(Model model,HttpServletRequest request,@RequestParam int no_bid) throws Exception {
		MentVO vo = new MentVO();
		
		HttpSession session = request.getSession();
		
		//로그인 세션이 있는지 확인
		//세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			String content = request.getParameter("replytext");
			String ment_writer = session.getAttribute("user_id").toString();
			
			vo.setNO_BID(no_bid);
			vo.setMENT_CONTENT(content);
			vo.setMENT_WRITER(ment_writer);
			
			mentservice.mentInsert(vo);
		}
	}
	
	@ResponseBody
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,@RequestParam int no_bid,ModelAndView mav) throws Exception {
		HttpSession session = request.getSession();
		
		//로그인 세션이 있는지 확인
		//세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			MentVO vo =new MentVO();
			vo.setNO_BID(no_bid);
			List<MentVO> list = mentservice.mentList(vo);
			mav.setViewName("/Notice_list_info");
			mav.addObject("list",list);
		}
		return mav;
	}
	
	@RequestMapping("listJson")
	@ResponseBody
	public List<MentVO> listJson(@RequestParam int no_bid) throws Exception{
		MentVO vo =new MentVO();
		vo.setNO_BID(no_bid);
		List<MentVO> list = mentservice.mentList(vo);
		//System.out.println(list);
	return list;
	}
	
	@RequestMapping("delete/{ment_id}") 
	@ResponseBody
	private int mentserviceDelete(@PathVariable int ment_id) throws Exception{
		return mentservice.mentDelete(ment_id);
	}
	 
	
	@RequestMapping("update")
	@ResponseBody
	private int mentserviceUpdate(@RequestParam int ment_id,@RequestParam String content) throws Exception{
	  
	  MentVO ment = new MentVO(); 
	  ment.setMent_ID(ment_id);
	  ment.setMENT_CONTENT(content);
	  
	  return mentservice.mentUpdate(ment); 
	}
}
