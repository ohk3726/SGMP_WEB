package com.sgmp.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgmp.web.service.ProductService;
import com.sgmp.web.vo.ProductVO;

@Controller
public class ProductController {
	
	@Resource(name = "ProductService")
	private ProductService productservice;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value="/productList")
	public String ProductList(Model model) throws Exception{
		
		List<ProductVO> list = productservice.selectProductList();
		logger.info(list.toString());
		model.addAttribute("list",list);
		
		return "productList";
	}
}
