package com.sgmp.web.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sgmp.web.vo.ProductVO;

public interface ProductService {
	List<ProductVO> selectProductList() throws Exception;
	List<ProductVO> selectedCompanyList_A(ProductVO vo) throws Exception;
	List<ProductVO> prodList() throws Exception;
	List<ProductVO> productInfo(ProductVO vo) throws Exception;
	int product_modify(ProductVO vo) throws Exception;
	int product_insert(ProductVO vo) throws Exception;
	List<ProductVO> prod_search(ProductVO vo) throws Exception;
	
	//����
	int xlsExcelReader(MultipartHttpServletRequest req) throws Exception;
	int xlsxExcelReader(MultipartHttpServletRequest req) throws Exception;
	int ExcelReader_id_same_count(ProductVO vo) throws Exception;
	void Reader_modify_all(ProductVO vo) throws Exception;
	
	
	String xlsExcelReader_modify(MultipartHttpServletRequest req) throws Exception;
	String xlsxExcelReader_modify(MultipartHttpServletRequest req) throws Exception;
}

