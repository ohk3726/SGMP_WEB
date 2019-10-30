package com.sgmp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgmp.web.service.ProductService;
import com.sgmp.web.vo.ProductVO;

@Controller
public class ProductController {
	
	@Resource(name = "ProductService")
	private ProductService productservice;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	//제품 리스트로 이동
	@RequestMapping(value = "/productList")
	public String ProductList(Model model) throws Exception {
		List<ProductVO> list = productservice.selectProductList();
		logger.info(list.toString());
		model.addAttribute("list", list);

		List<ProductVO> list_3 = productservice.prodList();
		model.addAttribute("list_3", list_3);

		return "productList";
	}
	
	@RequestMapping(value = "productInfo", method = RequestMethod.GET)
	public String productInfo(Model model,HttpServletRequest request) throws Exception {
		ProductVO vo = new ProductVO();
		vo.setProd_id(request.getParameter("product_id"));
		
		List<ProductVO> list = productservice.productInfo(vo);
		model.addAttribute("productInfo", list);

		return "productInfo";
	}

	@RequestMapping(value = "PRO", method = RequestMethod.POST)
	public String PRO(HttpServletRequest request, Model model) throws Exception {
		ProductVO vo = new ProductVO();
		vo.setCompany_id(request.getParameter("selectCompany"));

		List<ProductVO> list = productservice.selectedCompanyList_A(vo);
		logger.info(list.toString());
		model.addAttribute("list", list);

		List<ProductVO> list_3 = productservice.prodList();
		model.addAttribute("list_3", list_3);

		return "productList";
	}
}
