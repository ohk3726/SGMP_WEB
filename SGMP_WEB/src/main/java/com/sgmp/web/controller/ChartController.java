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
}
