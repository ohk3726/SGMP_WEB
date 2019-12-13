package com.sgmp.web.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.OrderDAO;
import com.sgmp.web.vo.OrderVO;
import com.sgmp.web.vo.ProductVO;
import com.sgmp.web.vo.SearchVO;

@Service("OrderService")
public class OrderServiceImpl implements OrderService{
	@Inject
	private OrderDAO orderDAO;
	
	//전체리스트
	@Override
	@Transactional
	public List order_all_list(String prod_wearing_flg) throws Exception{
		return orderDAO.order_all_list(prod_wearing_flg);
	}
	//처리상태 리스트
	@Override
	@Transactional
	public List order_condition() throws Exception{
		return orderDAO.order_condition();
	}
	//지점리스트
	
	@Override
	@Transactional
	public List order_company() throws Exception{
		return orderDAO.order_company();
	}
	//처리상태 변경
	@Override
	@Transactional
	public int order_condition_change(String prod_wearing_condition, String[] check_list) throws Exception{
		return orderDAO.order_condition_change(prod_wearing_condition, check_list);
	}
	
	//처리상태체크 상품준비중인것만 체크
	@Override
	@Transactional
	public int order_condition_check(String prod_wearing_condition, String[] check_list, String prod_wearing_flg) throws Exception{
		return orderDAO.order_condition_check(prod_wearing_condition, check_list,prod_wearing_flg);
	}
	//처리상태가 서로 다른것이 선택되었을떄 체크
	@Override
	@Transactional
	public int order_condition_check_1(String prod_wearing_condition, String[] check_list, String prod_wearing_flg) throws Exception{
		return orderDAO.order_condition_check_1(prod_wearing_condition, check_list,prod_wearing_flg);
	}
	//본사 재고수량 변경
	@Override
	@Transactional
	public int order_root_cnt_change(String prod_wearing_id) throws Exception{
		return orderDAO.order_root_cnt_change(prod_wearing_id);
	}
	//지점간이동으로 가기전에 확인절차
	@Override
	@Transactional
	public List<OrderVO> order_cnt_check(String[] check_list) throws Exception{
		return orderDAO.order_cnt_check(check_list);
	}
	
	@Override
	@Transactional
	public List<OrderVO> order_list(OrderVO vo) throws Exception{
		return orderDAO.order_list(vo);
	}
	
	@Override
	@Transactional
	public List<ProductVO> order_p2p(OrderVO vo) throws Exception{
		return orderDAO.order_p2p(vo);
	}
	
	@Override
	@Transactional
	public int order_p2p_goto(OrderVO vo) throws Exception{
		return orderDAO.order_p2p_goto(vo);
	}
	//p2p전체리스트
	@Override
	@Transactional
	public List order_p2p_list(String prod_wearing_flg) throws Exception{
		return orderDAO.order_p2p_list(prod_wearing_flg);
	}
	//전체리스트
	@Override
	@Transactional
	public List order_list_test(SearchVO vo) throws Exception{
		return orderDAO.order_list_test(vo);
	}
	
	//입고리스트 출력
	@Override
	@Transactional
	public List excel_list(OrderVO vo) throws Exception{
		return orderDAO.excel_list(vo);
	}
}
