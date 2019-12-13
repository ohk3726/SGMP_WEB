package com.sgmp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgmp.web.service.ChartService;
import com.sgmp.web.service.MainService;
import com.sgmp.web.vo.OrderVO;
import com.sgmp.web.vo.ProductVO;

@Controller
public class ChartController {
	@Resource(name="ChartService")
	private ChartService chartservice;
	@Resource(name="MainService")
	private MainService mainservice;
	
	@RequestMapping(value="/company_chart")
	public String chart(HttpServletRequest request, Model model) throws Exception{
		String result="login";
		HttpSession session = request.getSession();
		//로그인 세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			String prod_main_category = request.getParameter("prod_main_category");
			ProductVO vo = new ProductVO();
			vo.setCompany_id(session.getAttribute("user_id").toString());
			vo.setProd_main_category(prod_main_category);
			List<ProductVO> list = chartservice.select_chart(vo);
			String chart_x ="";
			String chart_y = "";
			for(int i=0;i<list.size();i++) {
				if(i!=list.size()-1) {
					chart_x += "'"+list.get(i).getProd_main_category()+"', ";
					chart_y += list.get(i).getProd_price()+", ";				
				}
				else {
					chart_x += "'"+list.get(i).getProd_main_category()+"'";
					chart_y += list.get(i).getProd_price();				
				}
			}
			List<ProductVO> list3 = mainservice.main_chart(vo);
			String chart_1 = "";
			String chart_2 = "";
			for(int i=0; i<list3.size();i++) {
				if(i != list3.size()-1) {					
					chart_1 += "'"+list3.get(i).getProd_date() +"'"+ ",";
					chart_2 += list3.get(i).getProd_price() + ",";
				}
				else {
					chart_1 += "'"+list3.get(i).getProd_date()+"'";
					chart_2 += "'"+list3.get(i).getProd_price()+"'";
				}
			}
			model.addAttribute("chart_x",chart_x);
			model.addAttribute("chart_y",chart_y);
			model.addAttribute("chart_1",chart_1);
			model.addAttribute("chart_2",chart_2);
			result="company_chart";
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/select_category",produces = "application/text; charset=utf8")
	public String select_category(HttpServletRequest request, Model model) throws Exception{
		String result="";
		HttpSession session = request.getSession();
		//로그인 세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			String prod_main_category = request.getParameter("prod_main_category");
			ProductVO vo = new ProductVO();
			vo.setCompany_id(session.getAttribute("user_id").toString());
			vo.setProd_main_category(prod_main_category);
			
			List<ProductVO> list = chartservice.select_category_prod(vo);
			
			result += "<thead class=\"thead-dark\">";
			result += "<tr>";
			result += "<th scope=\"col\" colspan=\"4\">선택 카테고리 리스트";
			result += "<tr>";
			result += "<th scope=\"col\">제품ID";
			result += "<th scope=\"col\">제품명";
			result += "<th scope=\"col\">판매량";
			result += "<th scope=\"col\">판매금액";
			for(int i=0;i<list.size();i++) {
				result += "<tr>";
				result += "<td>"+list.get(i).getProd_id();
				result += "<td>"+list.get(i).getProd_name();
				result += "<td>"+list.get(i).getProd_cnt();
				result += "<td>"+list.get(i).getProd_price();
			}	
			result += "</thead>";
		}
		return result;
	}
	
	@RequestMapping(value="/p2p_chart")
	public String p2p_chart(HttpServletRequest request, Model model) throws Exception{
		String result="login";
		HttpSession session = request.getSession();
		//로그인 세션이 없으면 로그인 페이지로 이동
		OrderVO vo = new OrderVO();
		if(session.getAttribute("user_id").equals("admin")) {
			vo.setProd_wearing_release("ROOT");
		}
		else {			
			vo.setProd_wearing_release(session.getAttribute("user_id").toString());
		}
		if(session.getAttribute("user_id")!=null) {
			List<OrderVO> list = chartservice.p2p_output(vo);
			
			String chart_x ="";
			String chart_y = "";
			for(int i=0;i<list.size();i++) {
				if(i!=list.size()-1) {
					chart_x += "'"+list.get(i).getProd_wearing_company_id()+"', ";
					chart_y += list.get(i).getProd_wearing_id()+", ";				
				}
				else {
					chart_x += "'"+list.get(i).getProd_wearing_company_id()+"'";
					chart_y += list.get(i).getProd_wearing_id();				
				}
			}
			model.addAttribute("chart_x",chart_x);
			model.addAttribute("chart_y",chart_y);
			
			List<OrderVO> list1 = chartservice.p2p_input(vo);
			
			String chart_1 ="";
			String chart_2 = "";
			if(list1.size()!=0) {
				
			}
			for(int i=0;i<list1.size();i++) {
				if(i!=list1.size()-1) {
					chart_1 += "'"+list1.get(i).getProd_wearing_release()+"', ";
					chart_2 += list1.get(i).getProd_wearing_id()+", ";				
				}
				else {
					chart_1 += "'"+list1.get(i).getProd_wearing_release()+"'";
					chart_2 += list1.get(i).getProd_wearing_id();				
				}
			}
			model.addAttribute("chart_1",chart_1);
			model.addAttribute("chart_2",chart_2);
			
			result="p2p_chart";
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/select_p2p_output",produces = "application/text; charset=utf8")
	public String select_p2p_output(HttpServletRequest request, Model model) throws Exception{
		String result="";
		HttpSession session = request.getSession();
		//로그인 세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			String prod_wearing_company_id = request.getParameter("prod_wearing_company_id");
			OrderVO vo = new OrderVO();
			if(session.getAttribute("user_id").equals("admin")) {
				vo.setProd_wearing_release("ROOT");
			}
			else {			
				vo.setProd_wearing_release(session.getAttribute("user_id").toString());
			}
			vo.setProd_wearing_company_id(prod_wearing_company_id);
			
			List<OrderVO> list = chartservice.select_p2p_output_list(vo);
			
			result += "<thead class=\"thead-dark\">";
			result += "<tr>";
			result += "<th scope=\"col\" colspan=\"4\">선택 지점 출고 리스트";
			result += "<tr>";
			result += "<th scope=\"col\">주문ID";
			result += "<th scope=\"col\">제품ID";
			result += "<th scope=\"col\">제품명";
			result += "<th scope=\"col\">출고량";
			
			for(int i=0;i<list.size();i++) {
				result += "<tr>";
				result += "<td>"+list.get(i).getProd_wearing_id();
				result += "<td>"+list.get(i).getProd_id();
				result += "<td>"+list.get(i).getProd_name();
				result += "<td>"+list.get(i).getProd_wearing_cnt();
			}	
			result += "</thead>";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/select_p2p_input",produces = "application/text; charset=utf8")
	public String select_p2p_input(HttpServletRequest request, Model model) throws Exception{
		String result="";
		HttpSession session = request.getSession();
		//로그인 세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			String prod_wearing_company_id = request.getParameter("prod_wearing_company_id");
			OrderVO vo = new OrderVO();
			if(session.getAttribute("user_id").equals("admin")) {
				vo.setProd_wearing_release("ROOT");
			}
			else {			
				vo.setProd_wearing_release(session.getAttribute("user_id").toString());
			}
			vo.setProd_wearing_company_id(prod_wearing_company_id);
			
			List<OrderVO> list = chartservice.select_p2p_input_list(vo);
			
			result += "<thead class=\"thead-dark\">";
			result += "<tr>";
			result += "<th scope=\"col\" colspan=\"4\">선택 지점 입고 리스트";
			result += "<tr>";
			result += "<th scope=\"col\">주문ID";
			result += "<th scope=\"col\">제품ID";
			result += "<th scope=\"col\">제품명";
			result += "<th scope=\"col\">입고량";
			
			for(int i=0;i<list.size();i++) {
				result += "<tr>";
				result += "<td>"+list.get(i).getProd_wearing_id();
				result += "<td>"+list.get(i).getProd_id();
				result += "<td>"+list.get(i).getProd_name();
				result += "<td>"+list.get(i).getProd_wearing_cnt();
			}	
			result += "</thead>";
		}
		return result;
	}
}
