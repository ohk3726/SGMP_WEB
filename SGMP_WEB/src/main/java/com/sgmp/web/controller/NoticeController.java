package com.sgmp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgmp.web.service.NoticeService;
import com.sgmp.web.vo.NoticeVO;

@Controller
public class NoticeController {
	// ��� , �����ϰ� ������ ����¡�� �ȉ� ,

	@Resource(name = "NoticeService")
	private NoticeService noticeservice;

	@RequestMapping(value = "Notice_list")
	public String Notice_list(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		NoticeVO vo = new NoticeVO();
		  
		String viewpage = request.getParameter("page");
		String est_id = "";
		
		if (viewpage.equals("1")) {
			est_id = "1";
		} else {
			est_id = String.valueOf((Integer.parseInt(viewpage) - 1) * 10 + 1);
		}

		int pagenum = noticeservice.notice_page();
		model.addAttribute("viewpage", viewpage);
		model.addAttribute("pagenum", pagenum);
		int est_bid = Integer.parseInt(est_id);

		vo.setNo_rownum(est_bid);

		List<NoticeVO> list = noticeservice.notice_list_page(vo);

		model.addAttribute("list", list);

		return "Notice_list";
	}

	@RequestMapping(value = "Notice_write")
	public String Notice_write(HttpServletRequest request) throws Exception {
		return "Notice_write";
	}

	@RequestMapping(value = "modify")
	public String modify(Model model, HttpServletRequest request) throws Exception {
		String Name = new String(request.getParameter("bName").getBytes("ISO-8859-1"),"UTF-8");
		String Title = new String(request.getParameter("bTitle").getBytes("ISO-8859-1"),"UTF-8");
		String Content = new String(request.getParameter("bContent").getBytes("ISO-8859-1"),"UTF-8");
		String bid = new String(request.getParameter("bId").getBytes("ISO-8859-1"),"UTF-8");

		NoticeVO vo = new NoticeVO();
		vo.setNo_name(Name);
		vo.setNo_title(Title);
		vo.setNo_content(Content);
		vo.setNo_bid(bid);

		noticeservice.notice_modify(vo);
		
		
		String viewpage = "1";
		String est_id = "";
		if (viewpage.equals("1")) {
			est_id = "1";
		} else {
			est_id = String.valueOf((Integer.parseInt(viewpage) - 1) * 10 + 1);
		}

		int pagenum = noticeservice.notice_page();
		model.addAttribute("pagenum", pagenum);
		int est_bid = Integer.parseInt(est_id);

		vo.setNo_rownum(est_bid);

		List<NoticeVO> list = noticeservice.notice_list_page(vo);

		model.addAttribute("list", list);
		return "Notice_list";
	}

	@RequestMapping(value = "write")
	public String write(HttpServletRequest request, Model model) throws Exception {
		String Name = new String(request.getParameter("bName").getBytes("ISO-8859-1"),"UTF-8");
		String Title = new String(request.getParameter("bTitle").getBytes("ISO-8859-1"),"UTF-8");
		String Content = new String(request.getParameter("bContent").getBytes("ISO-8859-1"),"UTF-8");

		NoticeVO vo = new NoticeVO();
		vo.setNo_name(Name);
		vo.setNo_title(Title);
		vo.setNo_content(Content);

		noticeservice.notice_write(vo);
		return "Notice_write";
	}

	@RequestMapping(value = "Notice_list_info")
	public String Notice_list_info(Model model, HttpServletRequest request) throws Exception {

		System.out.println(request.getParameter("no_bid"));

		String bid = request.getParameter("no_bid");
		String b_id = bid;
		NoticeVO vo = new NoticeVO();
		vo.setNo_bid(b_id);
		if (noticeservice.notice_uphit(vo) == 1) {
			List<NoticeVO> list = noticeservice.notice_list_info(vo);
			model.addAttribute("Notice_list_info", list);
		}

		return "Notice_list_info";
	}

	@RequestMapping(value = "Notice_delete")
	public String Notice_Delete(Model model, HttpServletRequest request) throws Exception {
		NoticeVO vo = new NoticeVO();
		vo.setNo_bid(request.getParameter("no_bid"));

		if (noticeservice.notice_delete(vo) == 1) {
			String viewpage = "1";
			String est_id = "";
			if (viewpage.equals("1")) {
				est_id = "1";
			} else {
				est_id = String.valueOf((Integer.parseInt(viewpage) - 1) * 10 + 1);
			}

			int pagenum = noticeservice.notice_page();
			model.addAttribute("pagenum", pagenum);
			int est_bid = Integer.parseInt(est_id);

			vo.setNo_rownum(est_bid);

			List<NoticeVO> list = noticeservice.notice_list_page(vo);

			model.addAttribute("list", list);
		}

		return "Notice_list";
	}
	
	
	
	// �������θ� �˻�
	@RequestMapping(value = "Notice_Search")
	public String Notice_Search(Model model, HttpServletRequest request) throws Exception {
	
		String PSW = new String(request.getParameter("product_search_word").getBytes("ISO-8859-1"),"UTF-8");
		
	
		NoticeVO vo = new NoticeVO();

		    System.out.println(request.getParameter("page"));
		    String viewpage = request.getParameter("page");
			String est_id = "";
			if (viewpage.equals("1")) {
				est_id = "1";
			} else {
				est_id = String.valueOf((Integer.parseInt(viewpage) - 1) * 10 + 1);
			}
			
			int est_bid = Integer.parseInt(est_id);
			vo.setNo_rownum(est_bid);
			vo.setNo_title(PSW);
			
			int pagenum = noticeservice.notice_page_Title(vo);
			model.addAttribute("pagenum", pagenum);
			model.addAttribute("product_search_word", PSW);
			
			
			List<NoticeVO> list = noticeservice.notice_search_title(vo);
			model.addAttribute("list", list);
			

		return "Notice_list_search";
	}

}
