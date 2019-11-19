package com.sgmp.web.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgmp.web.service.OrderService;
import com.sgmp.web.vo.OrderVO;

@Controller
public class P2PController {
	
	@Resource(name="OrderService")
	private OrderService orderservice;
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	
	/* 지점 상품 주문 페이지 이동 */
	@RequestMapping(value="/order_p2p")
	public String Order(HttpServletRequest request, Model model) throws Exception{
		String result="login";
		HttpSession session = request.getSession();
		//로그인 세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			List<OrderVO> list = orderservice.order_all_list("1");
			List<OrderVO> list_3 = orderservice.order_condition();
			List<OrderVO> list_4 = orderservice.order_company();

			model.addAttribute("list",list);
			model.addAttribute("list_3",list_3);
			model.addAttribute("list_4",list_4);
			
			result="order_p2p";
		}
		return result;
	}
	/* 검색 */
	@RequestMapping(value = "search_p2p", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Model model) throws Exception {
		String result="login";
		HttpSession session = request.getSession();
		
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");
		//getParameter 한글처리
		String sel_condition = new String(request.getParameter("selectCondition").getBytes("ISO-8859-1"),"UTF-8");
		String sel_company = new String(request.getParameter("selectCompany").getBytes("ISO-8859-1"),"UTF-8");
		String sel_search = request.getParameter("searchSelect");
		String sel_search_keyword = new String(request.getParameter("search_keyword").getBytes("ISO-8859-1"),"UTF-8");
		//System.out.println(date1+date2+sel_condition+sel_company+sel_search+sel_search_keyword);

		if(session.getAttribute("user_id")!=null) {
			if(!sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기") && sel_search.equals("prod_name") && !sel_search_keyword.equals("")) {
				//order_search_all_com
				List<OrderVO> list = orderservice.order_search_all_name(date1, date2, sel_condition, sel_company, sel_search_keyword,"1");
				model.addAttribute("list",list);
				//System.out.println("처리상태 선택, 지점명 선택, 제품명으로 검색");
			}
			
			else if(sel_condition.equals("처리상태선택하기") && sel_company.equals("지점명선택하기") && sel_search.equals("prod_name") && !sel_search_keyword.equals("")) {
				//order_search_name
				List<OrderVO> list = orderservice.order_search_name(date1, date2, sel_search_keyword,"1");
				model.addAttribute("list",list);
				//System.out.println("제품명 검색");
			}
			
			else if(sel_condition.equals("처리상태선택하기") && sel_company.equals("지점명선택하기") && sel_search.equals("prod_wearing_id") && !sel_search_keyword.equals("")) {
				//order_search_id
				List<OrderVO> list = orderservice.order_search_id(date1, date2, sel_search_keyword,"1");
				model.addAttribute("list",list);
				//System.out.println("주문ID 검색");
			}
			
			else if(sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기") && sel_search.equals("prod_name") && !sel_search_keyword.equals("")) {
				//order_search_name_com
				List<OrderVO> list = orderservice.order_search_name_com(date1, date2,sel_company, sel_search_keyword,"1");
				model.addAttribute("list",list);
				//System.out.println("지점명 선택, 제품명 검색");
			}
			
			else if(sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기") && sel_search.equals("prod_wearing_id") && !sel_search_keyword.equals("")) {
				//order_search_id_com
				List<OrderVO> list = orderservice.order_search_id_com(date1, date2,sel_company, sel_search_keyword,"1");
				model.addAttribute("list",list);
				//System.out.println("지점명선택, 주문ID 검색");
			}
			
			else if(!sel_condition.equals("처리상태선택하기") && sel_company.equals("지점명선택하기") && sel_search.equals("prod_name") && !sel_search_keyword.equals("")) {
				//order_search_name_con
				List<OrderVO> list = orderservice.order_search_name_con(date1, date2,sel_condition, sel_search_keyword,"1");
				model.addAttribute("list",list);
				//System.out.println("처리상태선택, 제품명 검색");
			}
			
			else if(!sel_condition.equals("처리상태선택하기") && sel_company.equals("지점명선택하기") && sel_search.equals("prod_wearing_id") && !sel_search_keyword.equals("")) {
				//order_search_id_con
				List<OrderVO> list = orderservice.order_search_id_con(date1, date2,sel_condition, sel_search_keyword,"1");
				model.addAttribute("list",list);
				//System.out.println("처리상태선택, 주문ID 검색");
			}
			
			else if(!sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기") && sel_search.equals("prod_wearing_id") && !sel_search_keyword.equals("")) {
				//order_search_all_id
				List<OrderVO> list = orderservice.order_search_all_id(date1, date2, sel_condition, sel_company, sel_search_keyword,"1");
				model.addAttribute("list",list);
				//System.out.println("처리상태선택, 지점명선택,주문ID 검색");
			}
			
			else if(!sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기") && sel_search_keyword.equals("")) {
				//order_search_con_and_com
				List<OrderVO> list = orderservice.order_search_con_and_com(date1, date2, sel_condition, sel_company,"1");
				model.addAttribute("list",list);
				//System.out.println("처리상태선택, 지점명선택");
			}
			
			else if(!sel_condition.equals("처리상태선택하기") && sel_company.equals("지점명선택하기")) {
				//order_search_con
				List<OrderVO> list = orderservice.order_search_con(date1, date2, sel_condition,"1");
				model.addAttribute("list",list);
				//System.out.println("처리상태선택");
			}
			
			else if(sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기")) {
				//order_search_com
				List<OrderVO> list = orderservice.order_search_com(date1, date2, sel_company,"1");
				model.addAttribute("list",list);
				//System.out.println("지점명선택");
			}
			
			else if(!date1.equals("") && !date2.equals("") && sel_search_keyword.equals("")) {
				//order_search_date
				List<OrderVO> list = orderservice.order_search_date(date1, date2,"1");
				model.addAttribute("list",list);
				//System.out.println("날짜검색");
			}
			
			else {
				List<OrderVO> list = orderservice.order_all_list("1");
				model.addAttribute("list",list);
				//System.out.println("전체검색");
			}
			
			List<OrderVO> list_3 = orderservice.order_condition();
			List<OrderVO> list_4 = orderservice.order_company();
			

			model.addAttribute("list_3",list_3);
			model.addAttribute("list_4",list_4);
			model.addAttribute("date1",date1);
			model.addAttribute("date2",date2);
			model.addAttribute("sel_condition",sel_condition);
			model.addAttribute("sel_company",sel_company);
			model.addAttribute("sel_search",sel_search);
			model.addAttribute("sel_search_keyword",sel_search_keyword);
			
			result="order_search_p2p";
		}
		return result;
	}
	
	/* 지점간이동으로 주문 이동 */
	@RequestMapping(value="/order_p2p_goto")
	public void order_p2p_goto(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		HttpSession session = request.getSession();
		String prod_wearing_id = request.getParameter("prod_wearing_id");
		String company_id = request.getParameter("company_id");
		OrderVO vo = new OrderVO();
		vo.setProd_wearing_id(prod_wearing_id);
		vo.setProd_wearing_release(company_id);
		//로그인 세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			
			int update = orderservice.order_p2p_goto(vo);
			  
			if(update==1) { 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter(); 
				out.println("<script>alert('지점간이동으로 이동되었습니다.');opener.location.href='/web/order_p2p';window.close();</script>"); 
				out.flush(); 
				} 
			else { 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('지점간이동으로 실패하였습니다.');</script>"); 
				out.flush(); 
			}
			 
		}
	}
}
