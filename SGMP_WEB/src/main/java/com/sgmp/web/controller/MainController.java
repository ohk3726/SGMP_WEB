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

import com.sgmp.web.service.MainService;
import com.sgmp.web.vo.NoticeVO;
import com.sgmp.web.vo.ProductVO;


@Controller
public class MainController {
	@Resource(name="MainService")
	private MainService mainservice;
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	//메인화면이동
	@RequestMapping(value="/main")
	public String main(HttpServletRequest request,Model model) throws Exception{
		String result="login";
		HttpSession session = request.getSession();
		
		//로그인 세션이 있는지 확인
		//세션이 없으면 로그인 페이지로 이동
		if(session.getAttribute("user_id")!=null) {
			ProductVO vo = new ProductVO();
			vo.setCompany_id(session.getAttribute("user_id").toString());
			List<NoticeVO> list = mainservice.main_bbs();
			List<ProductVO> list1 = mainservice.main_cnt_min(vo);
			List<ProductVO> list2 = mainservice.main_top_list(vo);
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
			model.addAttribute("list",list);
			model.addAttribute("list1",list1);
			model.addAttribute("list2",list2);
			model.addAttribute("chart_1",chart_1);
			model.addAttribute("chart_2",chart_2);
			model.addAttribute("user_id",session.getAttribute("user_id"));
			result="main";
		}
		
		return result;	
	}
}
