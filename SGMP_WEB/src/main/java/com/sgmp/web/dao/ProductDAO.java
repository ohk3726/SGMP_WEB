package com.sgmp.web.dao;

import java.util.List;

import com.sgmp.web.vo.ProductVO;

public interface ProductDAO {
	List<ProductVO> selectProductList() throws Exception;
}	
