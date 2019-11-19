package com.sgmp.web.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgmp.web.service.CompanyService;
import com.sgmp.web.vo.CompanyVO;

@Controller
public class CompanyController {


	@Resource(name = "CompanyService")
	private CompanyService companyservice;
	
	
	@RequestMapping(value = "Company_List")
	public String ProductList(Model model) throws Exception {
		List<CompanyVO> list = companyservice.selectCompanyList();
		
		model.addAttribute("list", list);
		
		return "Company_List";
	}
	
	@RequestMapping(value = "company_search")
	public String Company_search(Model model,HttpServletRequest request) throws Exception {
		String search_text = request.getParameter("search_text");
		CompanyVO vo = new CompanyVO();
		vo.setCOMPANY_NAME(search_text);
		
		List<CompanyVO> list = companyservice.company_search(vo);
		
		model.addAttribute("list", list);
		
		return "Company_List";
	}
	@RequestMapping(value = "company_insert_page")
	public String company_insert(Model model,HttpServletRequest request) throws Exception {

		
		return "company_insert";
	}
	
	@RequestMapping(value = "company_modify_info", method = RequestMethod.GET)
	public String company_modify(Model model,HttpServletRequest request) throws Exception {
		String company_id = request.getParameter("COMPANY_ID");
		CompanyVO vo = new CompanyVO();
		vo.setCOMPANY_ID(company_id);
		
		List<CompanyVO> list = companyservice.company_modify_info(vo);
		model.addAttribute("company_info", list);
		
		return "Company_modify";
	}
	
	
	@RequestMapping(value = "company_info_modify")
	public String company_info_modify(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String company_id = request.getParameter("company_id");
		String company_name = request.getParameter("company_name");
		String company_number = request.getParameter("company_number");
		String company_address = request.getParameter("company_address");
		
		CompanyVO vo = new CompanyVO();
		vo.setCOMPANY_ID(company_id);
		vo.setCOMPANY_ADDRESS(company_address);
		vo.setCOMPANY_NAME(company_name);
		vo.setCOMPANY_NUMBER(company_number);
		
		int company_modify =companyservice.company_modify(vo);
		if(company_modify == 1) {
			response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('수정되었습니다..');opener.location.reload();window.close();</script>");
	         out.flush();
	      }
		
		
		
		System.out.println("수정되었습니다.");
		
		return "Company_modify";
	}
	
	
	
	@RequestMapping(value = "company_info_insert")
	public void company_info_insert(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String company_id = request.getParameter("company_id");
		String company_name = request.getParameter("company_name");
		String company_number = request.getParameter("company_number");
		String company_address = request.getParameter("company_address");
		
		
		
		CompanyVO vo = new CompanyVO();
		vo.setCOMPANY_ADDRESS(company_address);
		vo.setCOMPANY_ID(company_id);
		vo.setCOMPANY_NAME(company_name);
		vo.setCOMPANY_NUMBER(company_number);
		
		int insert = companyservice.company_insert(vo);
		if(insert == 1) {
			response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('등록되었습니다.');opener.location.reload();window.close();</script>");
	         out.flush();
	      }
		
	}
	
	
	
}
