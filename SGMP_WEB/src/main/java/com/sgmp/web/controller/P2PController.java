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
import com.sgmp.web.vo.SearchVO;

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
			SearchVO vo = new SearchVO();
			vo.setProd_wearing_flg("1");
			vo.setProd_wearing_company_id(session.getAttribute("user_id").toString());
			List<OrderVO> list = orderservice.order_list_test(vo);
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
			SearchVO vo = new SearchVO();
			vo.setDate1(date1);
			vo.setDate2(date2);
			vo.setProd_wearing_company_id(sel_company);
			vo.setProd_wearing_condition(sel_condition);
			vo.setProd_wearing_flg("1");
			if(sel_search.equals("prod_name")) {
				vo.setProd_name(sel_search_keyword);
			}
			else {
				vo.setProd_wearing_id(sel_search_keyword);
			}
			
			List<OrderVO> list = orderservice.order_list_test(vo);
			List<OrderVO> list_3 = orderservice.order_condition();
			List<OrderVO> list_4 = orderservice.order_company();
			
			model.addAttribute("list",list);
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
	
	@RequestMapping(value="/order_p2p_condition_change")
	public String order_p2p_condition_change(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception{
		String result = "order_goto";
		HttpSession session = request.getSession();
		String[] check = request.getParameterValues("condition_check");
		//getParameter 한글처리
		String prod_wearing_condition = new String(request.getParameter("change_condition").getBytes("ISO-8859-1"),"UTF-8");
		if(session.getAttribute("user_id")!=null) {
			//다른 처리상태를 선택하였을때 체크
			int temp = orderservice.order_condition_check_1(prod_wearing_condition, check,"1");
			if(temp == 1) {
				
				int temp_1 = orderservice.order_condition_check(prod_wearing_condition, check,"1");
				//상품준비중인것만 체크
				if(temp_1 == check.length) {
						orderservice.order_condition_change("출고완료", check);
						for(int i=0; i<check.length;i++) {
							orderservice.order_root_cnt_change(check[i]);
						}
				}
				else {
					orderservice.order_condition_change(prod_wearing_condition, check);
				}
				SearchVO vo = new SearchVO();
				vo.setProd_wearing_flg("1");
				vo.setProd_wearing_company_id(session.getAttribute("user_id").toString());
				List<OrderVO> list = orderservice.order_list_test(vo);
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
			result="order_p2p";
		}
		
		return result;
	}
}
