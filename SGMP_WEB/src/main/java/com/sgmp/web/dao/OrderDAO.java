package com.sgmp.web.dao;

import java.util.List;

import com.sgmp.web.vo.OrderVO;

public interface OrderDAO {
	//지점 상품 주문 DAO
	List<OrderVO> order_all_list() throws Exception;
}
