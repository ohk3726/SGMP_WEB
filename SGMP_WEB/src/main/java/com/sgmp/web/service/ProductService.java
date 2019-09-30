package com.sgmp.web.service;

import java.util.List;

import com.sgmp.web.vo.ProductVO;

public interface ProductService {
	List<ProductVO> selectProductList() throws Exception;
}
