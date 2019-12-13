package com.sgmp.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sgmp.web.vo.OrderVO;
import com.sgmp.web.vo.ProductVO;
import com.sgmp.web.vo.SearchVO;

public interface OrderDAO {
	//지점 상품 주문 DAO
	//전체리스트
	List<OrderVO> order_all_list(@Param("prod_wearing_flg")String prod_wearing_flg) throws Exception;
	//처리상태 리스트
	List<OrderVO> order_condition() throws Exception;
	//지점 리스트
	List<OrderVO> order_company() throws Exception;
	//처리상태 변경
	int order_condition_change(@Param("prod_wearing_condition") String prod_wearing_condition, @Param("check_list") String[] check_list) throws Exception;
	//처리상태체크 상품준비중인것만 체크
	int order_condition_check(@Param("prod_wearing_condition") String prod_wearing_condition, @Param("check_list") String[] check_list, @Param("prod_wearing_flg")String prod_wearing_flg) throws Exception;
	//처리상태가 서로 다른것이 선택되었을때 체크
	int order_condition_check_1(@Param("prod_wearing_condition") String prod_wearing_condition, @Param("check_list") String[] check_list, @Param("prod_wearing_flg")String prod_wearing_flg) throws Exception;
	//본사 재고수량 변경
	int order_root_cnt_change(@Param("prod_wearing_id") String prod_wearing_id) throws Exception;
	//지점간이동으로 가기전에 확인절차
	List<OrderVO> order_cnt_check(@Param("check_list") String[] check_list) throws Exception;
	
	List<OrderVO> order_list(OrderVO vo) throws Exception;
	List<ProductVO> order_p2p(OrderVO vo) throws Exception;
	
	int order_p2p_goto(OrderVO vo) throws Exception;
	//p2p전체리스트
	List<OrderVO> order_p2p_list(@Param("prod_wearing_flg")String prod_wearing_flg) throws Exception;
	//전체리스트
	List<OrderVO> order_list_test(SearchVO vo) throws Exception;
	//입고리스트 출력
	List<Map> excel_list(OrderVO vo) throws Exception;
}
