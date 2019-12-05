package com.sgmp.web.service;

import java.util.List;

import com.sgmp.web.vo.CustomerVO;

public interface CustomerService {
	List<CustomerVO> selectCustomerList() throws Exception;
}
