package com.sgmp.web.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sgmp.web.vo.ProductVO;

@Controller
public class OrderController {
	
	@Resource(name="OrderService")
	private OrderService orderservice;
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

/* 지점 상품 주문 페이지 이동 */
	@RequestMapping(value="/order")
	public String Order(HttpServletRequest request, Model model) throws Exception{
		String result="login";
		HttpSession session = request.getSession();
		//로그인 세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			List<OrderVO> list = orderservice.order_all_list("0");
			List<OrderVO> list_3 = orderservice.order_condition();
			List<OrderVO> list_4 = orderservice.order_company();

			model.addAttribute("list",list);
			model.addAttribute("list_3",list_3);
			model.addAttribute("list_4",list_4);
			
			result="order";
		}
		return result;
	}
/* 검색 */
	@RequestMapping(value = "search", method = RequestMethod.POST)
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
				List<OrderVO> list = orderservice.order_search_all_name(date1, date2, sel_condition, sel_company, sel_search_keyword,"0");
				model.addAttribute("list",list);
				//System.out.println("처리상태 선택, 지점명 선택, 제품명으로 검색");
			}
			
			else if(sel_condition.equals("처리상태선택하기") && sel_company.equals("지점명선택하기") && sel_search.equals("prod_name") && !sel_search_keyword.equals("")) {
				//order_search_name
				List<OrderVO> list = orderservice.order_search_name(date1, date2, sel_search_keyword,"0");
				model.addAttribute("list",list);
				//System.out.println("제품명 검색");
			}
			
			else if(sel_condition.equals("처리상태선택하기") && sel_company.equals("지점명선택하기") && sel_search.equals("prod_wearing_id") && !sel_search_keyword.equals("")) {
				//order_search_id
				List<OrderVO> list = orderservice.order_search_id(date1, date2, sel_search_keyword,"0");
				model.addAttribute("list",list);
				//System.out.println("주문ID 검색");
			}
			
			else if(sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기") && sel_search.equals("prod_name") && !sel_search_keyword.equals("")) {
				//order_search_name_com
				List<OrderVO> list = orderservice.order_search_name_com(date1, date2,sel_company, sel_search_keyword,"0");
				model.addAttribute("list",list);
				//System.out.println("지점명 선택, 제품명 검색");
			}
			
			else if(sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기") && sel_search.equals("prod_wearing_id") && !sel_search_keyword.equals("")) {
				//order_search_id_com
				List<OrderVO> list = orderservice.order_search_id_com(date1, date2,sel_company, sel_search_keyword,"0");
				model.addAttribute("list",list);
				//System.out.println("지점명선택, 주문ID 검색");
			}
			
			else if(!sel_condition.equals("처리상태선택하기") && sel_company.equals("지점명선택하기") && sel_search.equals("prod_name") && !sel_search_keyword.equals("")) {
				//order_search_name_con
				List<OrderVO> list = orderservice.order_search_name_con(date1, date2,sel_condition, sel_search_keyword,"0");
				model.addAttribute("list",list);
				//System.out.println("처리상태선택, 제품명 검색");
			}
			
			else if(!sel_condition.equals("처리상태선택하기") && sel_company.equals("지점명선택하기") && sel_search.equals("prod_wearing_id") && !sel_search_keyword.equals("")) {
				//order_search_id_con
				List<OrderVO> list = orderservice.order_search_id_con(date1, date2,sel_condition, sel_search_keyword,"0");
				model.addAttribute("list",list);
				//System.out.println("처리상태선택, 주문ID 검색");
			}
			
			else if(!sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기") && sel_search.equals("prod_wearing_id") && !sel_search_keyword.equals("")) {
				//order_search_all_id
				List<OrderVO> list = orderservice.order_search_all_id(date1, date2, sel_condition, sel_company, sel_search_keyword,"0");
				model.addAttribute("list",list);
				//System.out.println("처리상태선택, 지점명선택,주문ID 검색");
			}
			
			else if(!sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기") && sel_search_keyword.equals("")) {
				//order_search_con_and_com
				List<OrderVO> list = orderservice.order_search_con_and_com(date1, date2, sel_condition, sel_company,"0");
				model.addAttribute("list",list);
				//System.out.println("처리상태선택, 지점명선택");
			}
			
			else if(!sel_condition.equals("처리상태선택하기") && sel_company.equals("지점명선택하기")) {
				//order_search_con
				List<OrderVO> list = orderservice.order_search_con(date1, date2, sel_condition,"0");
				model.addAttribute("list",list);
				//System.out.println("처리상태선택");
			}
			
			else if(sel_condition.equals("처리상태선택하기") && !sel_company.equals("지점명선택하기")) {
				//order_search_com
				List<OrderVO> list = orderservice.order_search_com(date1, date2, sel_company,"0");
				model.addAttribute("list",list);
				//System.out.println("지점명선택");
			}
			
			else if(!date1.equals("") && !date2.equals("") && sel_search_keyword.equals("")) {
				//order_search_date
				List<OrderVO> list = orderservice.order_search_date(date1, date2,"0");
				model.addAttribute("list",list);
				//System.out.println("날짜검색");
			}
			
			else {
				List<OrderVO> list = orderservice.order_all_list("0");
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
			
			result="order_search";
		}
		return result;
	}
/* 처리상태 변경 */
	@RequestMapping(value = "condition_change", method = RequestMethod.POST)
	public String condition_change(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String result="login";
		HttpSession session = request.getSession();
		String[] check = request.getParameterValues("condition_check");
		//getParameter 한글처리
		String prod_wearing_condition = new String(request.getParameter("change_condition").getBytes("ISO-8859-1"),"UTF-8");
		if(session.getAttribute("user_id")!=null) {
			//다른 처리상태를 선택하였을때 체크
			int temp = orderservice.order_condition_check_1(prod_wearing_condition, check,"0");
			if(temp == 1) {
				
				int temp_1 = orderservice.order_condition_check(prod_wearing_condition, check,"0");
				//상품준비중인것만 체크
				if(temp_1 == check.length) {
					
					List<OrderVO> cnt_check = orderservice.order_cnt_check(check);
					if(cnt_check.size() == 1) {
						String go_to = "/web/order_goto?prod_wearing_id="+cnt_check.get(0).getProd_wearing_id();
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println("<script>alert('본사재고량이 부족하여 지점간이동으로 넘어갑니다.');window.open('"+go_to+"','BSM','width=500px,height=500px',true);</script>");
						out.flush();
					}
					else if(cnt_check.size() > 1) {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println("<script>alert('본사재고량이 부족한 상품이 다수 있습니다.한가지만 선택해 주세요.');history.back();</script>");
						out.flush();
					}
					else {
						orderservice.order_condition_change("출고완료", check);
						orderservice.order_root_cnt_change(check);
					}
				}
				else {
					orderservice.order_condition_change(prod_wearing_condition, check);
				}
				
				List<OrderVO> list = orderservice.order_all_list("0");
				List<OrderVO> list_3 = orderservice.order_condition();
				List<OrderVO> list_4 = orderservice.order_company();
			
				model.addAttribute("list",list);
				model.addAttribute("list_3",list_3);
				model.addAttribute("list_4",list_4);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('같은 처리상태를 선택해주세요.');history.back();</script>");
				out.flush();
			}
			result="order";
		}
		return result;
	}
	
	@RequestMapping(value="/order_goto")
	public String anniversary_new(HttpServletRequest request, Model model) throws Exception{
		String result = "order_goto";
		String prod_wearing_id = request.getParameter("prod_wearing_id");
		OrderVO vo = new OrderVO();
		vo.setProd_wearing_id(prod_wearing_id);
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")!=null) {
			List<OrderVO> list = orderservice.order_list(vo);
			List<ProductVO> list1 = orderservice.order_p2p(vo);
			
			model.addAttribute("list", list);
			model.addAttribute("list1", list1);
		}
		
		return result;
	}
}