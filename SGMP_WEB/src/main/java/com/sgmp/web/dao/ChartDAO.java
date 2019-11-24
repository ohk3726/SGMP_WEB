package com.sgmp.web.dao;

import java.util.List;

import com.sgmp.web.vo.ProductVO;

public interface ChartDAO {
	List<ProductVO> select_chart(ProductVO vo) throws Exception;
	List<ProductVO> select_category(ProductVO vo) throws Exception;
	List<ProductVO> select_category_prod(ProductVO vo) throws Exception;
}
