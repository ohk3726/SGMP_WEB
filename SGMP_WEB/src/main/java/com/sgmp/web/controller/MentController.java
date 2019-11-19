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
	
	//댓글 입력
	@ResponseBody
	@RequestMapping("insert")
	public void insert(Model model,HttpSession session,HttpServletRequest request,@RequestParam int no_bid) throws Exception {
		MentVO vo = new MentVO();
		String content = request.getParameter("replytext");
		String ment_writer ="송유빈";
		//System.out.println(request.getParameter("bId"));
		//int no_bid = Integer.parseInt(request.getParameter("bId"));
		/*
		 * String ment_writer = (String) session.getAttribute("replytext"); String
		 * no_bid = (String) session.getAttribute("no_bid");
		 */
	
	
	vo.setNO_BID(no_bid);
	vo.setMENT_CONTENT(content);
	vo.setMENT_WRITER(ment_writer);
	
	mentservice.mentInsert(vo);
	System.out.println("인설트성공");
	}
	
	@ResponseBody
	@RequestMapping("list")
	public ModelAndView list(@RequestParam int no_bid,ModelAndView mav) throws Exception {
		
		System.out.println(no_bid);
		MentVO vo =new MentVO();
		vo.setNO_BID(no_bid);
		List<MentVO> list = mentservice.mentList(vo);
		mav.setViewName("/Notice_list_info");
		mav.addObject("list",list);
		return mav;
	}
	
	@RequestMapping("listJson")
	@ResponseBody
	public List<MentVO> listJson(@RequestParam int no_bid) throws Exception{
		System.out.println(no_bid);
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
	  
	
	
	
	
	/*
	 * @Resource(name = "MentService") private MentService mentservice;
	 * 
	 * 
	 * @RequestMapping(value = "/list") //댓글 리스트
	 * 
	 * @ResponseBody private List<MentVO> mentserviceList(Model model) throws
	 * Exception{ System.out.println("어디서 나는 에러인가");
	 * System.out.println(mentservice.mentList()); return mentservice.mentList(); }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value ="/insert" , method = RequestMethod.POST) private int
	 * mentserviceInsert(Model model,@RequestParam String bId, @RequestParam String
	 * content) throws Exception{ System.out.println("인서르트"); MentVO ment = new
	 * MentVO(); int bid_int = Integer.parseInt(bId); ment.setNO_BID(bid_int);
	 * ment.setMENT_CONTENT(content); ment.setMENT_WRITER("test");
	 * 
	 * 
	 * return mentservice.mentInsert(ment);
	 * 
	 * }
	 * 
	 * @RequestMapping("/update")
	 * 
	 * @ResponseBody private int mentserviceUpdate(@RequestParam int
	 * ment_id,@RequestParam String content) throws Exception{
	 * 
	 * MentVO ment = new MentVO(); ment.setMent_ID(ment_id);
	 * ment.setMENT_CONTENT(content);
	 * 
	 * return mentservice.mentUpdate(ment); }
	 * 
	 * @RequestMapping("/delete/{ment_id}") private int
	 * mentserviceDelete(@PathVariable int ment_id) throws Exception{ return
	 * mentservice.mentDelete(ment_id); }
	 * 
	 */
	
}
