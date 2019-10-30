package com.sgmp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgmp.web.service.OrderService;
import com.sgmp.web.vo.OrderVO;

@Controller
public class OrderController {
	
	@Resource(name="OrderService")
	private OrderService orderservice;
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	//지점 상품 주문 페이지 이동
	@RequestMapping(value="/order")
	public String Order(HttpServletRequest request, Model model) throws Exception{
		String result="login";
		HttpSession session = request.getSession();
		
		//로그인 세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			List<OrderVO> list = orderservice.order_all_list();
			logger.info(list.toString());
			model.addAttribute("list",list);
			result="order";
		}
		
		return result;
	}
}
