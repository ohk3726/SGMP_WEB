package com.sgmp.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sgmp.web.vo.OrderVO;
import com.sgmp.web.vo.ProductVO;

public interface OrderService {
	//상품 주문 서비스 인터페이스
	//전체리스트
	List<OrderVO> order_all_list(String prod_wearing_flg) throws Exception;
	//처리상태 리스트
	List<OrderVO> order_condition() throws Exception;
	//지점 리스트
	List<OrderVO> order_company() throws Exception;
	//처리상태 변경
	int order_condition_change(String prod_wearing_condition, String[] check_list) throws Exception;
	//검색부분
	List<OrderVO> order_search_all_name(String date1,String date2,String prod_wearing_condition,String prod_wearing_company,String prod_wearing_name, String prod_wearing_flg) throws Exception;
	List<OrderVO> order_search_all_id(String date1,String date2,String prod_wearing_condition,String prod_wearing_company,String prod_wearing_id, String prod_wearing_flg) throws Exception;
	
	List<OrderVO> order_search_name(String date1,String date2,String prod_wearing_name, String prod_wearing_flg) throws Exception;
	List<OrderVO> order_search_id(String date1,String date2,String prod_wearing_id, String prod_wearing_flg) throws Exception;
	
	List<OrderVO> order_search_name_com(String date1,String date2,String prod_wearing_company,String prod_wearing_name, String prod_wearing_flg) throws Exception;
	List<OrderVO> order_search_id_com(String date1,String date2,String prod_wearing_company,String prod_wearing_id, String prod_wearing_flg) throws Exception;
	
	List<OrderVO> order_search_name_con(String date1,String date2,String prod_wearing_condition,String prod_wearing_name, String prod_wearing_flg) throws Exception;
	List<OrderVO> order_search_id_con(String date1,String date2,String prod_wearing_condition,String prod_wearing_id, String prod_wearing_flg) throws Exception;
	
	List<OrderVO> order_search_con_and_com(String date1,String date2,String prod_wearing_condition,String prod_wearing_company, String prod_wearing_flg) throws Exception;
	List<OrderVO> order_search_con(String date1,String date2,String prod_wearing_condition, String prod_wearing_flg) throws Exception;
	List<OrderVO> order_search_com(String date1,String date2,String prod_wearing_company, String prod_wearing_flg) throws Exception;
	
	List<OrderVO> order_search_date(String date1,String date2, String prod_wearing_flg) throws Exception;
	//처리상태체크 상풍준비중인것만 체크
	int order_condition_check(String prod_wearing_condition, String[] check_list, String prod_wearing_flg) throws Exception;
	//처리상태가 서로 다른것이 선택되었을떄 체크
	int order_condition_check_1(String prod_wearing_condition, String[] check_list, String prod_wearing_flg) throws Exception;
	//본사 재고 수량 변경
	int order_root_cnt_change(String[] check_list) throws Exception;
	//지점간이동으로 가기전에 확인절차
	List<OrderVO> order_cnt_check(String[] check_list) throws Exception;
	
	List<OrderVO> order_list(OrderVO vo) throws Exception;
	List<ProductVO> order_p2p(OrderVO vo) throws Exception;
	
	int order_p2p_goto(OrderVO vo) throws Exception;
}
