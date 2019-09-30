package com.sgmp.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.ProductDAO;

@Service("ProductService")
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productMapper;
	
	@Override
	@Transactional
	public List selectProductList() throws Exception{
		return productMapper.selectProductList();
	}
}
