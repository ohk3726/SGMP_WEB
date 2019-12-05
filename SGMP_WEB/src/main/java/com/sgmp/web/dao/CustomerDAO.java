package com.sgmp.web.dao;

import java.util.List;

import com.sgmp.web.vo.CustomerVO;


public interface CustomerDAO {
	List<CustomerVO> selectCustomerList() throws Exception;
}
