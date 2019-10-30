package com.sgmp.web.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.OrderDAO;
import com.sgmp.web.vo.OrderVO;

@Service("OrderService")
public class OrderServiceImpl implements OrderService{
	@Inject
	private OrderDAO orderDAO;
	
	
	@Override
	@Transactional
	public List order_all_list() throws Exception{
		return orderDAO.order_all_list();
	}
}
