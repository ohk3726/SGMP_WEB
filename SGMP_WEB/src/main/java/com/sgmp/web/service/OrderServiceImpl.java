package com.sgmp.web.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.OrderDAO;
import com.sgmp.web.vo.OrderVO;
import com.sgmp.web.vo.ProductVO;

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
	//검색부분
	@Override
	@Transactional
	public List order_search_all_name(String date1,String date2,String prod_wearing_condition,String prod_wearing_company,String prod_wearing_name, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_all_name( date1, date2, prod_wearing_condition, prod_wearing_company, prod_wearing_name,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_all_id(String date1,String date2,String prod_wearing_condition,String prod_wearing_company,String prod_wearing_id, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_all_id( date1, date2, prod_wearing_condition, prod_wearing_company, prod_wearing_id,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_name(String date1,String date2,String prod_wearing_name, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_name( date1, date2, prod_wearing_name,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_id(String date1,String date2,String prod_wearing_id, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_id( date1, date2, prod_wearing_id,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_name_com(String date1,String date2,String prod_wearing_company,String prod_wearing_name, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_name( date1, date2, prod_wearing_name,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_id_com(String date1,String date2,String prod_wearing_company,String prod_wearing_id, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_id_com( date1, date2, prod_wearing_company, prod_wearing_id,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_name_con(String date1,String date2,String prod_wearing_condition,String prod_wearing_name, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_name_con( date1, date2, prod_wearing_condition,prod_wearing_name,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_id_con(String date1,String date2,String prod_wearing_condition,String prod_wearing_id, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_id_con( date1, date2, prod_wearing_condition, prod_wearing_id,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_con_and_com(String date1,String date2,String prod_wearing_condition,String prod_wearing_company, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_con_and_com( date1, date2, prod_wearing_condition, prod_wearing_company,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_con(String date1,String date2,String prod_wearing_condition, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_con( date1, date2, prod_wearing_condition,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_com(String date1,String date2,String prod_wearing_company, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_com( date1, date2, prod_wearing_company,prod_wearing_flg);
	}
	
	@Override
	@Transactional
	public List order_search_date(String date1,String date2, String prod_wearing_flg) throws Exception{
		return orderDAO.order_search_date( date1, date2,prod_wearing_flg);
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
	public int order_root_cnt_change(String[] check_list) throws Exception{
		return orderDAO.order_root_cnt_change(check_list);
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
}