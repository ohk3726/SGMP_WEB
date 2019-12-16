package com.sgmp.web.controller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
		vo.setUser_id(search_text);
		
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
		vo.setUser_id(company_id);
		
		List<CompanyVO> list = companyservice.company_modify_info(vo);
		model.addAttribute("company_info", list);
		
		return "Company_modify";
	}
	
	
	
	
	
	@RequestMapping(value = "company_info_modify")
	public String company_info_modify(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String company_id = request.getParameter("company_id");
		String company_pw = request.getParameter("company_pw");
		
		CompanyVO vo = new CompanyVO();
		vo.setUser_id(company_id);
		vo.setUser_pw(company_pw);
		
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
		String company_pw = request.getParameter("company_pw");
		
		CompanyVO vo = new CompanyVO();
		vo.setUser_id(company_id);
		vo.setUser_pw(company_pw);
		
		
		int same = companyservice.company_same_id(vo);
		if(same==0) {
			int insert = companyservice.company_insert(vo);
			if(insert == 1) {
				response.setContentType("text/html; charset=UTF-8");
		         PrintWriter out = response.getWriter();
		         out.println("<script>alert('등록되었습니다.');opener.location.reload();window.close();</script>");
		         out.flush();
		      }
		}else {
			response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('아이디가 중복되었습니다. 다른 아이디를 입력해주시기 바랍니다.');history.back();</script>");
	         out.flush();
		}
		
		
	}
	
	
		@RequestMapping(value="ExcelPoi")
		  public void ExcelPoi(HttpServletResponse response, Model model,HttpServletRequest request) throws Exception {
			
			String fileName = request.getParameter("fileName");
			List<Map> list_1 = companyservice.selectMap();
	
			
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
		  objCell.setCellValue("회사ID");
		  objCell.setCellStyle(styleHd);

		  
		  // 2행
		  int index = 1;
		  for (Map map : list_1) {
			
		    objRow = objSheet.createRow(index);
		    objRow.setHeight((short) 0x150);

		    objCell = objRow.createCell(0);
		    objCell.setCellValue((String)map.get("ID"));
		    objCell.setCellStyle(styleHd);

		    index++;
		  }


		  response.setContentType("Application/Msexcel");
		  response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode(fileName,"UTF-8")+".xls");

		  OutputStream fileOut  = response.getOutputStream();
		  objWorkBook.write(fileOut);
		  fileOut.close();

		  response.getOutputStream().flush();
		  response.getOutputStream().close();
		}
	
	
	
	
}
