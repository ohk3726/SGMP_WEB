package com.sgmp.web.service;

import java.util.List;

import com.sgmp.web.vo.OrderVO;
import com.sgmp.web.vo.ProductVO;

public interface ChartService {
	List<ProductVO> select_chart(ProductVO vo) throws Exception;
	List<ProductVO> select_category(ProductVO vo) throws Exception;
	List<ProductVO> select_category_prod(ProductVO vo) throws Exception;
	List<OrderVO> p2p_output(OrderVO vo) throws Exception;
	List<OrderVO> select_p2p_output_list(OrderVO vo) throws Exception;
	List<OrderVO> p2p_input(OrderVO vo) throws Exception;
	List<OrderVO> select_p2p_input_list(OrderVO vo) throws Exception;
}
