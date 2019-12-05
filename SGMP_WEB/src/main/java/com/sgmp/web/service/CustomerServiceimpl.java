package com.sgmp.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.CustomerDAO;

@Service("CustomerService")
@Repository
public class CustomerServiceimpl implements CustomerService {
	@Autowired
	private CustomerDAO customerMapper;
	
	@Override
	@Transactional
	public List selectCustomerList() throws Exception{
		return customerMapper.selectCustomerList();
	}
	

}
