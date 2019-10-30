package com.sgmp.web.service;

import java.util.List;

import com.sgmp.web.vo.OrderVO;

public interface OrderService {
	//상품 주문 서비스 인터페이스
	List<OrderVO> order_all_list() throws Exception;
}
