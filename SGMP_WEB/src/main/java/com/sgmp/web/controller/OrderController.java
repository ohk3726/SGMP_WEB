package com.sgmp.web.controller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgmp.web.service.OrderService;
import com.sgmp.web.vo.OrderVO;
import com.sgmp.web.vo.ProductVO;
import com.sgmp.web.vo.SearchVO;

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
			SearchVO vo = new SearchVO();
			vo.setProd_wearing_flg("0");
			vo.setProd_wearing_company_id(session.getAttribute("user_id").toString());
			List<OrderVO> list = orderservice.order_list_test(vo);
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
			SearchVO vo = new SearchVO();
			vo.setDate1(date1);
			vo.setDate2(date2);
			vo.setProd_wearing_company_id(sel_company);
			vo.setProd_wearing_condition(sel_condition);
			vo.setProd_wearing_flg("0");
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
						for(int i=0; i<check.length;i++) {
							orderservice.order_root_cnt_change(check[i]);
						}
						
					}
				}
				else {
					orderservice.order_condition_change(prod_wearing_condition, check);
				}
				SearchVO vo = new SearchVO();
				vo.setProd_wearing_flg("0");
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
			result="order";
		}
		return result;
	}
	
	@RequestMapping(value="/order_goto")
	public String order_goto(HttpServletRequest request, Model model) throws Exception{
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
	
	@RequestMapping(value="excel_list")
	public void excel_list(HttpServletResponse response, Model model,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		Date date = new Date();
		SimpleDateFormat format1; 
		format1 = new SimpleDateFormat("yyyy-MM-dd");
		String fileName="";
		OrderVO vo = new OrderVO();
		
		if(session.getAttribute("user_id")!=null) {
			if(session.getAttribute("user_id").equals("admin")) {
				vo.setProd_wearing_company_id("ROOT");
				fileName = format1.format(date)+"-출고리스트";
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); 
			}
			else {
				vo.setProd_wearing_company_id(session.getAttribute("user_id").toString());
				fileName = format1.format(date)+"-입고리스트";
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); 
			}
			
			List<Map> list_1 = orderservice.excel_list(vo);

			
		    HSSFWorkbook objWorkBook = new HSSFWorkbook();
		    HSSFSheet objSheet = null;
		    HSSFRow objRow = null;
		    HSSFCell objCell = null;       //셀 생성
		    
		  //제목 폰트
			  HSSFFont font = objWorkBook.createFont();
			  font.setFontHeightInPoints((short)9);
			  font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
			  font.setFontName("맑은고딕");

			  //제목 스타일에 폰트 적용, 정렬
			  HSSFCellStyle styleHd = objWorkBook.createCellStyle();    //제목 스타일
			  styleHd.setFont(font);
			  styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			  styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);

			  objSheet = objWorkBook.createSheet("첫번째 시트");     //워크시트 생성

			  // 1행
			  objRow = objSheet.createRow(0);
			  objRow.setHeight ((short) 0x150);

			  objCell = objRow.createCell(0);
			  objCell.setCellValue("주문ID");
			  objCell.setCellStyle(styleHd);

			  objCell = objRow.createCell(1);
			  objCell.setCellValue("제품ID");
			  objCell.setCellStyle(styleHd);
			  
			  objCell = objRow.createCell(2);
			  objCell.setCellValue("제품명");
			  objCell.setCellStyle(styleHd);
			  
			  if(session.getAttribute("user_id").equals("admin")) {
				  objCell = objRow.createCell(3);
				  objCell.setCellValue("출고수량");
				  objCell.setCellStyle(styleHd);
				  
				  objCell = objRow.createCell(4);
				  objCell.setCellValue("배송지점");
				  objCell.setCellStyle(styleHd);
			  }
			  else {
				  objCell = objRow.createCell(3);
				  objCell.setCellValue("입고수량");
				  objCell.setCellStyle(styleHd);
				  
				  objCell = objRow.createCell(4);
				  objCell.setCellValue("출고지점");
				  objCell.setCellStyle(styleHd);
			  }
			  
			  

			  
			  // 2행
			  int index = 1;
			  for (Map map : list_1) {
				
			    objRow = objSheet.createRow(index);
			    objRow.setHeight((short) 0x150);

			    objCell = objRow.createCell(0);
			    objCell.setCellValue((String)map.get("prod_wearing_id"));
			    objCell.setCellStyle(styleHd);

			    objCell = objRow.createCell(1);
			    objCell.setCellValue((String)map.get("prod_id"));
			    objCell.setCellStyle(styleHd);

			    objCell = objRow.createCell(2);
			    objCell.setCellValue((String)map.get("prod_name"));
			    objCell.setCellStyle(styleHd);
			    
			    if(session.getAttribute("user_id").equals("admin")) {
			    	objCell = objRow.createCell(3);
				    objCell.setCellValue((String)map.get("prod_wearing_cnt"));
				    objCell.setCellStyle(styleHd);
				    
				    objCell = objRow.createCell(4);
				    objCell.setCellValue((String)map.get("prod_wearing_company_id"));
				    objCell.setCellStyle(styleHd);
				  }
				  else {
					  objCell = objRow.createCell(3);
					    objCell.setCellValue((String)map.get("prod_wearing_cnt"));
					    objCell.setCellStyle(styleHd);
					    
					    objCell = objRow.createCell(4);
					    objCell.setCellValue((String)map.get("prod_wearing_release"));
					    objCell.setCellStyle(styleHd);
				  }
			    
			    
			    
			    index++;
			  }


			  response.setContentType("Application/Msexcel");
			  response.setHeader("Content-Disposition", "ATTachment; Filename="+fileName+".xls");

			  OutputStream fileOut  = response.getOutputStream();
			  objWorkBook.write(fileOut);
			  fileOut.close();

			  response.getOutputStream().flush();
			  response.getOutputStream().close();
		}
	}
}