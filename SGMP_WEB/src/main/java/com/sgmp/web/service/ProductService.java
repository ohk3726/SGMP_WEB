package com.sgmp.web.service;

import java.util.List;

import com.sgmp.web.vo.ProductVO;

public interface ProductService {
	List<ProductVO> selectProductList() throws Exception;
	List<ProductVO> selectedCompanyList_A(ProductVO vo) throws Exception;
	List<ProductVO> prodList() throws Exception;
	List<ProductVO> productInfo(ProductVO vo) throws Exception;
}
