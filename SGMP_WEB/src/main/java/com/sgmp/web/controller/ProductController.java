package com.sgmp.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sgmp.web.service.CustomerService;
import com.sgmp.web.service.ProductService;
import com.sgmp.web.vo.CustomerVO;
import com.sgmp.web.vo.ProductVO;

@Controller
public class ProductController {

	@Resource(name = "ProductService")
	private ProductService productservice;

	@Resource(name = "CustomerService")
	private CustomerService customerservice;

	// ���� �ֱ�
	@RequestMapping(value = "compExcelUpload", method = { RequestMethod.GET, RequestMethod.POST })
	public String execUpload(MultipartHttpServletRequest req, Model model, HttpServletResponse response)
			throws Exception {
		int result_id;

		String excelType = req.getParameter("excelType");

		if (excelType.equals("xlsx")) {
			result_id = productservice.xlsxExcelReader(req);
			if (result_id > 0) {
				System.out.println("�����糪?");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert(result_id+'���� ��ǰ�� ID�� �ߺ��Ǿ� ������ �߰��Ǿ����ϴ�.');</script>");
				out.flush();
			}
		} else if (excelType.equals("xls")) {
			result_id = productservice.xlsExcelReader(req);
			if (result_id > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert(result_id+'���� ��ǰ�� ID�� �ߺ��Ǿ� ������ �߰��Ǿ����ϴ�.');</script>");
				out.flush();
			}
		}

		return "productList";
	}

	// ���� �����ؼ� ���ϱ�
	@RequestMapping(value = "compExceUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String compExceUpdate(MultipartHttpServletRequest req, Model model, HttpServletResponse response)
			throws Exception {
		String result_id="";
		List<ProductVO> list = new ArrayList();
		String excelType = req.getParameter("excelType");

		if (excelType.equals("xlsx")) {
			result_id = productservice.xlsxExcelReader_modify(req);
		} 
		else if (excelType.equals("xls")) {
			result_id = productservice.xlsExcelReader_modify(req);
		} 
		return result_id;
	}

	// ======
	@RequestMapping(value = "/productList")
	public String ProductList(HttpServletRequest request,Model model) throws Exception {
		String result="login";
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")!=null) {
			if(session.getAttribute("user_id").equals("admin")) {
				List<ProductVO> list = productservice.selectProductList();
				model.addAttribute("list", list);
			}
			else {
				ProductVO vo = new ProductVO();
				vo.setCompany_id(session.getAttribute("user_id").toString());
				List<ProductVO> list = productservice.selectedCompanyList_A(vo);
				model.addAttribute("list", list);
			}
			
	
			List<ProductVO> list_3 = productservice.prodList();
			model.addAttribute("list_3", list_3);
			result="productList";
		}
		return result;
	}

	@RequestMapping(value = "/customer")
	public String CustomerList(Model model) throws Exception {

		List<CustomerVO> list = customerservice.selectCustomerList();
		model.addAttribute("C_list", list);

		return "customer";
	}

	@RequestMapping(value = "productInfo", method = RequestMethod.GET)
	public String productInfo(Model model, HttpServletRequest request) throws Exception {
		ProductVO vo = new ProductVO();
		vo.setProd_id(request.getParameter("product_id"));

		List<ProductVO> list = productservice.productInfo(vo);
		model.addAttribute("productInfo", list);

		return "productInfo";
	}

	@RequestMapping(value = "product_modify")
	public String modify(Model model, HttpServletRequest request) throws Exception {
		String product_ID = request.getParameter("product_ID");
		String product_NAME = request.getParameter("product_NAME");
		String product_CNT = request.getParameter("product_CNT");
		String product_ALL = request.getParameter("product_ALL");
		String product_WEARING_PRICE = request.getParameter("product_WEARING_PRICE");
		String product_PRICE = request.getParameter("product_PRICE");
		String product_MARGIN = request.getParameter("product_MARGIN");
		String company_id = request.getParameter("company_id");

		product_WEARING_PRICE = product_WEARING_PRICE.replaceAll(",", "");
		product_PRICE = product_PRICE.replaceAll(",", "");

		ProductVO vo = new ProductVO();
		vo.setProd_id(product_ID);
		vo.setProd_name(product_NAME);
		vo.setProd_cnt(product_CNT);
		vo.setProd_wearing_price(product_WEARING_PRICE);
		vo.setProd_price(product_PRICE);
		vo.setProd_margin(product_MARGIN);
		vo.setCompany_id(company_id);

		productservice.product_modify(vo);

		return "productInfo";
	}

	@RequestMapping(value = "select_search")
	public String select_search(Model model, HttpServletRequest request) throws Exception {
		String result = "login";
		String option = new String(request.getParameter("search_option").getBytes("ISO-8859-1"),"UTF-8");
		String search_text = new String(request.getParameter("search_text").getBytes("ISO-8859-1"),"UTF-8");
		String sort = request.getParameter("selectCompany");
		model.addAttribute("sort", sort);
		ProductVO vo = new ProductVO();
		vo.setCompany_id(request.getParameter("selectCompany"));
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")!=null) {
			if(session.getAttribute("user_id").toString().equals("admin")) {
				if (option.equals("option_title")) {
					vo.setProd_name(search_text);

					List<ProductVO> list = productservice.prod_search(vo);
					model.addAttribute("list", list);

				} else if (option.equals("option_id")) {
					vo.setProd_id(search_text);

					List<ProductVO> list = productservice.prod_search(vo);
					model.addAttribute("list", list);
				}
			}
			else {
				if (option.equals("option_title")) {
					vo.setCompany_id(session.getAttribute("user_id").toString());
					vo.setProd_name(search_text);

					List<ProductVO> list = productservice.prod_search(vo);
					model.addAttribute("list", list);

				} else if (option.equals("option_id")) {
					vo.setCompany_id(session.getAttribute("user_id").toString());
					vo.setProd_id(search_text);

					List<ProductVO> list = productservice.prod_search(vo);
					model.addAttribute("list", list);
				}
			}
			
			List<ProductVO> list_3 = productservice.prodList();
			model.addAttribute("list_3", list_3);
			
			model.addAttribute("search_text",search_text);
			result="productList_search";
		}
		
		
		return result;
	}

	@RequestMapping(value = "product_info_insert")
	public String product_info_insert(Model model, HttpServletResponse response, HttpServletRequest request)
			throws Exception {

		String product_ID = request.getParameter("product_ID");
		String product_NAME = request.getParameter("product_NAME");
		String product_CNT = request.getParameter("product_CNT");

		String prod_main_category = request.getParameter("prod_main_category");
		String prod_sub_category = request.getParameter("prod_sub_category");
		String prod_ssub_category = request.getParameter("prod_ssub_category");
		String prod_cnt_min = request.getParameter("prod_cnt_min");

		String product_WEARING_PRICE = request.getParameter("product_WEARING_PRICE");
		String product_PRICE = request.getParameter("product_PRICE");
		String company_id = request.getParameter("company_id");

		product_WEARING_PRICE = product_WEARING_PRICE.replaceAll(",", "");
		product_PRICE = product_PRICE.replaceAll(",", "");

		ProductVO vo = new ProductVO();
		vo.setProd_main_category(prod_main_category);
		vo.setProd_sub_category(prod_sub_category);
		vo.setProd_ssub_category(prod_ssub_category);
		vo.setProd_cnt_min(prod_cnt_min);

		vo.setProd_id(product_ID);
		vo.setProd_name(product_NAME);
		vo.setProd_cnt(product_CNT);
		vo.setProd_wearing_price(product_WEARING_PRICE);
		vo.setProd_price(product_PRICE);
		vo.setCompany_id(company_id);

		int prod_insert = productservice.product_insert(vo);

		if (prod_insert == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('��ϵǾ����ϴ�.');opener.location.reload();window.close();</script>");
			out.flush();
		}

		return "productInfo";
	}

	@RequestMapping(value = "product_insert")
	public String product_insert(Model model, HttpServletRequest request) throws Exception {

		return "product_info_insert";
	}

	@RequestMapping(value = "PRO", method = RequestMethod.POST)
	public String PRO(HttpServletRequest request, Model model) throws Exception {
		String result="login";
		String sort = request.getParameter("selectCompany");
		model.addAttribute("sort", sort);
		ProductVO vo = new ProductVO();
		vo.setCompany_id(request.getParameter("selectCompany"));
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")!=null) {
			if(sort.equals("all")) {
				List<ProductVO> list = productservice.selectProductList();
				model.addAttribute("list", list);
			}
			else {
				List<ProductVO> list = productservice.selectedCompanyList_A(vo);
				model.addAttribute("list", list);
			}
			

			List<ProductVO> list_3 = productservice.prodList();
			model.addAttribute("list_3", list_3);
			
			result="productList";
		}
		
		return result;
	}
}
